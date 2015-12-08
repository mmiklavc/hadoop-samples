package com.michaelmiklavcic.hadoop.hive.test.serde;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;

import com.klarna.hiverunner.*;
import com.klarna.hiverunner.annotations.HiveSQL;

@RunWith(StandaloneHiveRunner.class)
public class FixedWidthSerDeAcceptanceTest {
    @HiveSQL(files = {}) private HiveShell hiveShell;

    @Before
    public void before() {
      hiveShell.execute("create database test_db");
      hiveShell.execute(new StringBuilder()
      .append("create table test_db.test_table (")
      .append("c0 string,")
      .append("c1 string,")
      .append("c2 string")
      .append(") ")
      .append("ROW FORMAT SERDE 'com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDe'")
      .append("WITH SERDEPROPERTIES (")
      .append("\"FIXED_WIDTH_RANGES\"=\"1-10,11-20,21-30\",")
      .append("\"FIXED_WIDTH_PADDING_TYPE\"=\"padleft\",")
      .append("\"FIXED_WIDTH_PADDING_CHAR\"=\" \"")
      .append(") ")
      .append("stored as textfile")
      .toString());
      hiveShell
      .insertInto("test_db", "test_table")
      .newRow()
      .set("c0", "hello")
      .set("c1", "12345")
      .set("c2", "mike")
      .newRow()
      .set("c0", "    hello2")
      .set("c1", "     67890")
      .set("c2", "     mike2")
      .commit();
    }

    /**
     * Should work for inserts that have and don't have existing padding
     * Note: data will be misaligned if in-bound data has more padding than allowable
     * by that column's fixed width input format
     * @throws Exception
     */
    @Test
    public void deserializes_row() throws Exception {
        List<Object[]> result = hiveShell.executeStatement("select * from test_db.test_table");

        assertThat(result.size(), equalTo(2));

        Object[] row1 = result.get(0);
        assertThat((String) row1[0], equalTo("     hello"));
        assertThat((String) row1[1], equalTo("     12345"));
        assertThat((String) row1[2], equalTo("      mike"));
        Object[] row2 = result.get(1);
        assertThat((String) row2[0], equalTo("    hello2"));
        assertThat((String) row2[1], equalTo("     67890"));
        assertThat((String) row2[2], equalTo("     mike2"));
    }

}
