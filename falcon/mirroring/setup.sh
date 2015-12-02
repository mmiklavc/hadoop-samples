#!/bin/bash

ROOT_DIR=/user/root/falcon/
TARGET_DIR=/user/root/falcon/mirrorTarget

hdfs dfs -mkdir -p $TARGET_DIR

hdfs dfs -put mirrorSrc $ROOT_DIR

