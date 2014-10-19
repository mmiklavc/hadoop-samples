DEFINE HCatLoader org.apache.hcatalog.pig.HCatLoader();
DEFINE HCatStorer org.apache.hcatalog.pig.HCatStorer();

IN_DATA = LOAD '$falcon_INPUT1_database.$falcon_INPUT1_table' using HCatLoader() as (col1:chararray,col2:chararray,proc_date:chararray);
FILTERED = FILTER IN_DATA BY col2 == SUBSTRING(REPLACE('$CURRENT_DATE', '-', ''), 0, 8);
STORE FILTERED INTO '$falcon_OUTPUT1_database.$falcon_OUTPUT1_table' using HCatStorer('$falcon_OUTPUT1_dataout_partitions');
