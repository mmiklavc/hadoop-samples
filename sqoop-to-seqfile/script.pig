REGISTER /usr/lib/pig/piggybank.jar; 
REGISTER sqoop-import/sqoop.jar;
REGISTER /usr/lib/sqoop/sqoop-1.4.4.2.1.1.0-385.jar;

--DEFINE SequenceFileLoader org.apache.pig.piggybank.storage.SequenceFileLoader();
DEFINE HCatLoader org.apache.hcatalog.pig.HCatLoader();
DEFINE HCatStorer org.apache.hcatalog.pig.HCatStorer();

--LOADED = LOAD '$input' USING SequenceFileLoader; -- AS (id:chararray,col1:chararray,col2:chararray,col3:chararray);
LOADED = LOAD '$input' USING HCatLoader AS (id:int,col1:chararray,col2:chararray,col3:chararray);
STORE LOADED INTO '$output' USING HCatStorer;
