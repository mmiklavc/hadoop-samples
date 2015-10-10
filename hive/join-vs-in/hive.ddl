DROP TABLE RAW_MAIN_TABLE; 
DROP TABLE MAINTABLE; 
DROP TABLE RAW_JOIN_TABLE;
DROP TABLE JOINTABLE;

CREATE EXTERNAL TABLE IF NOT EXISTS RAW_MAIN_TABLE (some_val STRING, per_id INT)
ROW FORMAT
    DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION
    '/data/join_vs_in/raw/maintable';

CREATE EXTERNAL TABLE IF NOT EXISTS MAINTABLE (some_val STRING)
PARTITIONED BY (per_id INT)
ROW FORMAT
    DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION
    '/data/join_vs_in/final/maintable';

CREATE EXTERNAL TABLE IF NOT EXISTS RAW_JOIN_TABLE (ad_day STRING, description STRING, per_id INT)
ROW FORMAT
    DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION
    '/data/join_vs_in/raw/jointable';

CREATE EXTERNAL TABLE IF NOT EXISTS JOINTABLE (ad_day STRING, description STRING)
PARTITIONED BY (per_id INT)
ROW FORMAT
    DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION
    '/data/join_vs_in/final/jointable';