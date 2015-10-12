#!/bin/bash

mysql -u root -e "\
CREATE DATABASE IF NOT EXISTS hadoop_samples; \
USE hadoop_samples; \
DROP TABLE IF EXISTS sqoop_action_pig; \
CREATE TABLE sqoop_action_pig ( \
    ID INT NOT NULL AUTO_INCREMENT, \
    COL1 VARCHAR(10) NOT NULL, \
    COL2 INT NOT NULL, \
    PRIMARY KEY (ID) );"
