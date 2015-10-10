USE hadoop_samples;
LOAD DATA INFILE '/devprojects/hadoop-samples/oozie/sqoop-action/setup/sqoop-action.txt' INTO TABLE sqoop_action
FIELDS TERMINATED BY ',';
