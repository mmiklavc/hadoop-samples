--set mapreduce.map.memory.mb 500;
--set mapreduce.reduce.java.opts -Xmx400m;

main_data = load 'raw_main_table' using org.apache.hive.hcatalog.pig.HCatLoader();
store main_data into 'maintable' using org.apache.hive.hcatalog.pig.HCatStorer();

--join_data = load 'raw_join_table' using org.apache.hive.hcatalog.pig.HCatLoader();
--store join_data into 'jointable' using org.apache.hive.hcatalog.pig.HCatStorer();
