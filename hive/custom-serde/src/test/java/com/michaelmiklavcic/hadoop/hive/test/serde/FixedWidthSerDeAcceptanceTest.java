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
      .append("ROW FORMAT SERDE 'com.michaelmiklavcic.hadoop.hive.serde.FixedWidthSerDe' ")
      .append("WITH SERDEPROPERTIES (")
      .append("\"RANGES\"=\"1-10,11-20,21-30\"")
      .append(") ")
      .append("stored as textfile")
      .toString());
      hiveShell
      .insertInto("test_db", "test_table")
      .newRow()
      .set("c0", "     hello")
      .set("c1", "     world")
      .set("c2", "      mike")
      .commit();
    }

    @Test
    public void deserializes_row() throws Exception {
        List<Object[]> result = hiveShell.executeStatement("select * from test_db.test_table");

        assertThat(result.size(), equalTo(1));

        Object[] row = result.get(0);
        assertThat((String) row[0], equalTo("     hello"));
        assertThat((String) row[1], equalTo("     world"));
        assertThat((String) row[2], equalTo("      mike"));
    }

}
