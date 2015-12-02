set dbname=dynamic_partition;

INSERT OVERWRITE TABLE ${hiveconf:dbname}.staged_partitioned PARTITION(state, year, month)
SELECT idx, state, substr(datestamp, 1, 4) as year, substr(datestamp, 5, 2) FROM ${hiveconf:dbname}.raw_partitioned;

