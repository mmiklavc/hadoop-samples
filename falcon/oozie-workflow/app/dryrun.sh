#!/bin/bash

pig -useHCatalog \
-file filter.pig \
-param CURRENT_DATE=2014-10-18-00-00 \
-param falcon_INPUT1_database=mmiklavcic \
-param falcon_INPUT1_table=rawdata \
-param falcon_INPUT1_filter="(proc_date=='20141018')" \
-param falcon_OUTPUT1_database=mmiklavcic \
-param falcon_OUTPUT1_table=filtereddata \
-param falcon_OUTPUT1_dataout_partitions="'proc_date=20141018'" \
-dryrun
