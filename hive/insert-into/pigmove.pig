data = load 'intable1' using org.apache.hive.hcatalog.pig.HCatLoader();
store data into 'intable2' using org.apache.hive.hcatalog.pig.HCatStorer();
