--external table
CREATE EXTERNAL TABLE IF NOT EXISTS INTABLE1 (col1 STRING)
PARTITIONED BY (last_update_date STRING)
ROW FORMAT
    DELIMITED FIELDS TERMINATED BY ','
LOCATION
    '/data/intable1';

--internal table
DROP TABLE INTABLE2;
CREATE TABLE IF NOT EXISTS INTABLE2 (col1 STRING)
PARTITIONED BY (last_update_date STRING)
STORED AS ORC
LOCATION
    '/data/intable2';
