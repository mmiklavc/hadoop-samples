#!/bin/bash

. ../env.sh

echo "Setup Oozie app directories (if necessary)"

sudo -u hdfs hdfs dfs -mkdir -p $OOZIE_WORKFLOW_HOME
sudo -u hdfs hdfs dfs -mkdir -p $OOZIE_COORDINATOR_HOME
sudo -u hdfs hdfs dfs -chown -R oozie:hdfs $OOZIE_APP_HOME
sudo -u hdfs hdfs dfs -chmod -R 777 $OOZIE_APP_HOME
sudo -u hdfs hdfs dfs -mkdir -p /apps/share

echo "Setup app directories (use user that will execute workflows, e.g. your username)"
# su <your username>
sudo -u hdfs hdfs dfs -rm -r $APP_ROOT
hdfs dfs -mkdir $APP_ROOT
hdfs dfs -mkdir -p $ARCHIVE_DIR

echo "Upload application"
hdfs dfs -put ../workflow.xml $APP_ROOT
hdfs dfs -put ../prep.pig $APP_ROOT
hdfs dfs -put /etc/hive/conf/hive-site.xml /apps/share

echo "Setup MySQL"
./makedb.sh

echo "Loading data"
mysql < load.sql

echo "Setup hive table"
hdfs dfs -rm -r $PREP_DIR
hive -f makehive.hql

