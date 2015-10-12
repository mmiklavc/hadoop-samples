#!/bin/bash

. ../env.sh

OOZIE_SHARELIB=/user/oozie/share/lib/lib_20150820083727/sqoop/
hdfs dfs -put /usr/hdp/2.3.0.0-2557/sqoop/lib/mysql-connector-java.jar $OOZIE_SHARELIB

sudo -u oozie oozie admin -oozie http://$OOZIE_HOST:11000/oozie -sharelibupdate
