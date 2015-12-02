set dbname=dynamic_partition;

INSERT OVERWRITE TABLE ${hiveconf:dbname}.raw_partitioned PARTITION(datestamp)
SELECT state, idx, datestamp FROM ${hiveconf:dbname}.rawdata;

