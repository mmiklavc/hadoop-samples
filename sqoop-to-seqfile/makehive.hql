DROP TABLE IF EXISTS ${env:SQOOP_TABLE};
CREATE EXTERNAL TABLE ${env:SQOOP_TABLE} (
    id int,
    col1 string,
    col2 string,
    col3 string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    STORED AS TEXTFILE
LOCATION '${env:SQOOP_DIR}';

DROP TABLE IF EXISTS ${env:SEQ_TABLE};
CREATE EXTERNAL TABLE ${env:SEQ_TABLE} (
    id int,
    col1 string,
    col2 string,
    col3 string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    STORED AS SEQUENCEFILE
LOCATION '${env:SEQ_DIR}';
