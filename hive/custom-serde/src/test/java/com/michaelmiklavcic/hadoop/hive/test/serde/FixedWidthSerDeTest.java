package com.michaelmiklavcic.hadoop.hive.test.serde;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;
import org.junit.*;

import com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDe;

public class FixedWidthSerDeTest {

    private final Properties props = new Properties();
    private final String colNames = "a,b,c";
    private final String colTypes = "string,string,string";
    private final String colRanges = "1-10,11-20,21-30";

    @Before
    public void setup() throws Exception {
        props.setProperty(serdeConstants.LIST_COLUMNS, colNames);
        props.setProperty(serdeConstants.LIST_COLUMN_TYPES, colTypes);
        props.setProperty("FIXED_WIDTH_RANGES", colRanges);
        props.setProperty("FIXED_WIDTH_PADDING_TYPE", "padleft");
    }

    @Test
    public void deserializes_record() throws Exception {
        FixedWidthSerDe serde = new FixedWidthSerDe();
        serde.initialize(null, props);
        final Text in = new Text("     hello     world      mike");
        @SuppressWarnings("unchecked")
        // we control the return type
        List<String> row = (List<String>) serde.deserialize(in);
        assertThat(row.get(0), equalTo("     hello"));
        assertThat(row.get(1), equalTo("     world"));
        assertThat(row.get(2), equalTo("      mike"));
    }

    @Test
    public void serializes_record() throws Exception {
        FixedWidthSerDe serde = new FixedWidthSerDe();
        serde.initialize(null, props);
        final List<String> columnNames = Arrays.asList(colNames.split(","));
        final List<String> columnValues = Arrays.asList(new String[] {
                "hello",
                "world",
                "mike" });
        int numCols = columnNames.size();
        final List<ObjectInspector> columnOIs = new ArrayList<ObjectInspector>(numCols);
        for (int i = 0; i < numCols; i++) {
            columnOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        }
        StandardStructObjectInspector inspector = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs);
        Text record = (Text) serde.serialize(columnValues, inspector);
        final Text expected = new Text("     hello     world      mike");
        assertThat(record, equalTo(expected));
    }
}
