raw = load '$INPUT' using PigStorage(',') as (id: int, col1: chararray, col2: chararray);
with_datestamp = foreach raw generate *, '$datestamp' as datestamp;
store with_datestamp into '$output_database_name.$output_table_name' using org.apache.hive.hcatalog.pig.HCatStorer();

-- now archive staging data

fs -mkdir $archiveDir/$datestamp
fs -mv $INPUT/* $archiveDir/$datestamp
fs -rm -r -f $INPUT

