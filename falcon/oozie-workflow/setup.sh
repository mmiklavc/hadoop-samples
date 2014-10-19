#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

source env.sh

PROC_DATE=20141018
INGEST_LOCATION=${IN_TBL_LOCATION}/proc_date=${PROC_DATE}
WORKFLOW_APP_DIR=/apps/falcon/oozie/workflow-test

# SETUP DATA AND TABLES
hdfs dfs -mkdir -p $IN_TBL_LOCATION
hdfs dfs -mkdir -p $OUT_TBL_LOCATION

hdfs dfs -mkdir $INGEST_LOCATION
hdfs dfs -rm ${INGEST_LOCATION}/*
hdfs dfs -put indata.csv $INGEST_LOCATION

hive -f in.ddl
hive -f out.ddl

hive -e "USE ${IN_DATABASE_NAME}; ALTER TABLE ${IN_TABLE_NAME} ADD PARTITION (proc_date='${PROC_DATE}')"

# SETUP OOZIE WORKFLOW APP
hdfs dfs -mkdir -p $WORKFLOW_APP_DIR
hdfs dfs -rm -r ${WORKFLOW_APP_DIR}/*
hdfs dfs -put app/* $WORKFLOW_APP_DIR

popd > /dev/null

