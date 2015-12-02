create database if not exists dynamic_partition;
use dynamic_partition;

drop table rawdata;
drop table raw_partitioned;
drop table staged_partitioned;

create external table rawdata (
    state           STRING,
    datestamp       STRING,
    idx             INT
)
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/data/hadoop-samples/hive/dynamic-partition/raw';

create external table raw_partitioned (
    state           STRING,
    idx             INT
)
PARTITIONED BY (datestamp STRING)
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/data/hadoop-samples/hive/dynamic-partition/raw-partitioned';

create external table staged_partitioned (
    idx             INT
)
PARTITIONED BY (state STRING, year STRING, month STRING)
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/data/hadoop-samples/hive/dynamic-partition/staged-partitioned';
