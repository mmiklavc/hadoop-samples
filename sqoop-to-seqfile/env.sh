#!/bin/bash

export DB_CONNECTION=jdbc:mysql://localhost/hadoop_samples
export DB_USERNAME=root
export DB_PASSWORD=

export BASE_DIR=/data/hadoopsamples/sqoop-to-seqfile
export SQOOP_DIR=${BASE_DIR}/sqoop
export SEQ_DIR=${BASE_DIR}/seq
export SQOOP_TABLE=SQOOP_IMPORT
export SEQ_TABLE=SEQ_IMPORT
