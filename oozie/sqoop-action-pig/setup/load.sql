USE hadoop_samples;
LOAD DATA INFILE '/devprojects/hadoop-samples/oozie/sqoop-action-pig/setup/sqoop-action-pig.txt' INTO TABLE sqoop_action_pig
FIELDS TERMINATED BY ',';
