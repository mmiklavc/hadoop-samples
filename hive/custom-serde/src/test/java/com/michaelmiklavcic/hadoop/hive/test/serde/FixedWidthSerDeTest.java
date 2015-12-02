package com.michaelmiklavcic.hadoop.hive.test.serde;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.io.Text;
import org.junit.*;

import com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDe;

public class FixedWidthSerDeTest {

    private final Properties props = new Properties();

    @Before
    public void setup() throws Exception {
        props.setProperty(serdeConstants.LIST_COLUMNS, "a,b,c");
        props.setProperty(serdeConstants.LIST_COLUMN_TYPES, "string,string,string");
        props.setProperty("RANGES", "1-10,11-20,21-30");
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
    
//    @Test
//    public void serializes_record() throws Exception {
//        FixedWidthSerDe serde = new FixedWidthSerDe();
//        serde.initialize(null, props);
//        final Text out = new Text("     hello     world      mike");
//        List<String> fields new ArrayList();
//        List<ObjectInspector> inspectors;
//        StructObjectInspector ins = ObjectInspectorFactory.getStandardStructObjectInspector(fields, inspectors)
//        Text record = (Text) serde.serialize();
//        assertThat(row.get(0), equalTo("     hello"));
//        assertThat(row.get(1), equalTo("     world"));
//        assertThat(row.get(2), equalTo("      mike"));
//    }

}
