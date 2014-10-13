IN_DATA = LOAD '$INPUT1' using PigStorage(',') as (col1:chararray,date_stamp:chararray);
FILTERED = FILTER IN_DATA BY date_stamp == '$CURRENT_DATE';
STORE FILTERED INTO '$OUTPUT1' using PigStorage();
