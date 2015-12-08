package com.michaelmiklavcic.hadoop.hive.serde;

import static com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDeConstants.FIXED_WIDTH_PADDING_CHAR;
import static com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDeConstants.FIXED_WIDTH_PADDING_TYPE;
import static com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDeConstants.FIXED_WIDTH_RANGES;

import java.io.StringWriter;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.*;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.*;
import org.apache.hadoop.io.*;

public class FixedWidthSerDe extends AbstractSerDe {
    private List<String> row;
    private List<String> ranges;
    private String paddingType;
    private char paddingChar;
    private String[] outputFields;
    private int numCols;
    private ObjectInspector inspector;

    @Override
    public void initialize(final Configuration conf, final Properties props) throws SerDeException {
        ranges = Arrays.asList(props.getProperty(FIXED_WIDTH_RANGES.toString()).split(","));
        paddingType = props.getProperty(FIXED_WIDTH_PADDING_TYPE.toString(), "none");
        paddingChar = props.getProperty(FIXED_WIDTH_PADDING_CHAR.toString(), " ").charAt(0);
        numCols = ranges.size();
        row = new ArrayList<String>(numCols);
        outputFields = new String[numCols];
        for (int i = 0; i < numCols; i++) {
            row.add(null);
        }
        final List<String> columnNames = Arrays.asList(props.getProperty(serdeConstants.LIST_COLUMNS).split(","));
        final List<ObjectInspector> columnOIs = new ArrayList<ObjectInspector>(numCols);
        for (int i = 0; i < numCols; i++) {
            columnOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        }
        inspector = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs);
    }

    @Override
    public Object deserialize(final Writable blob) throws SerDeException {
        Text rowText = (Text) blob;
        String rowString = rowText.toString();
        for (int i = 0; i < numCols; i++) {
            String r = ranges.get(i);
            int start = Integer.parseInt(r.split("-")[0]) - 1;
            int end = Integer.parseInt(r.split("-")[1]);
            row.set(i, rowString.substring(start, end));
        }
        return row;
    }

    @Override
    public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
        final StructObjectInspector outputRowOI = (StructObjectInspector) objInspector;
        final List<? extends StructField> outputFieldRefs = outputRowOI.getAllStructFieldRefs();

        if (outputFieldRefs.size() != numCols) {
            throw new SerDeException("Cannot serialize the object because there are " + outputFieldRefs.size()
                    + " fields but the table has " + numCols + " columns.");
        }

        // Get all data out.
        for (int c = 0; c < numCols; c++) {
            final Object field = outputRowOI.getStructFieldData(obj, outputFieldRefs.get(c));
            final ObjectInspector fieldOI = outputFieldRefs.get(c).getFieldObjectInspector();

            // The data must be of type String
            final StringObjectInspector fieldStringOI = (StringObjectInspector) fieldOI;

            // Convert the field to Java class String, because objects of String type
            // can be stored in String, Text, or some other classes.
            int size = getFinalFieldSize(ranges.get(c));
            outputFields[c] = padIfNecessary(fieldStringOI.getPrimitiveJavaObject(field), paddingType, paddingChar, size);
        }

        final StringWriter writer = new StringWriter();
        for (String field : outputFields) {
            writer.append(field);
        }

        return new Text(writer.toString());
    }

    private int getFinalFieldSize(String range) {
        int start = Integer.parseInt(range.split("-")[0]) - 1;
        int end = Integer.parseInt(range.split("-")[1]);
        int size = end - start;
        return size;
    }

    private String padIfNecessary(String obj, String paddingType, char paddingChar, int size) {
        if (isEmptyOrNull(paddingType)) {
            return obj;
        }
        if ("padleft".equals(paddingType)) {
            return padLeft(obj, paddingChar, size);
        }
        throw new RuntimeException("Unable to pad using scheme: " + paddingType);
    }

    private boolean isEmptyOrNull(String obj) {
        return null == obj || "".equals(obj);
    }

    private String padLeft(String obj, char paddingChar, int size) {
        int add = size - obj.length();
        char[] padding = new char[add];
        Arrays.fill(padding, paddingChar);
        return new String(padding).concat(obj);
    }

    @Override
    public ObjectInspector getObjectInspector() throws SerDeException {
        return inspector;
    }

    @Override
    public Class<? extends Writable> getSerializedClass() {
        return Text.class;
    }

    @Override
    public SerDeStats getSerDeStats() {
        return null;
    }

}
