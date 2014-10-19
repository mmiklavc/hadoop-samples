DROP TABLE IF EXISTS ${env:OUT_DATABASE_NAME}.${env:OUT_TABLE_NAME};

CREATE EXTERNAL TABLE ${env:OUT_DATABASE_NAME}.${env:OUT_TABLE_NAME} (
    col1 STRING,
    col2 STRING
)
PARTITIONED BY
    (proc_date STRING)
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY   '\n'
STORED AS TEXTFILE
LOCATION
    '${env:OUT_TBL_LOCATION}';