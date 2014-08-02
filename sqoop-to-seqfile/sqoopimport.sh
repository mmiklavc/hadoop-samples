#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

. env.sh

echo "Running import..."

export queryStatement="SELECT ID, COL1, COL2, COL3 FROM SQOOP_IMPORT WHERE \$CONDITIONS"

# sqoop import --connect $DB_CONNECTION \
#              --username $DB_USERNAME \
#              --password "$DB_PASSWORD" \
#              --target-dir /tmp/datain \
#              --fields-terminated-by ',' \
#              --split-by ID \
#              --outdir sqoop-import \
#              --query "$queryStatement" \
#              -m 1 \
#              --hive-table SQOOP_IMPORT \
#              --hive-overwrite \
#              --hive-import

sqoop import --connect $DB_CONNECTION \
             --username $DB_USERNAME \
             --password "$DB_PASSWORD" \
             --target-dir /tmp/datain \
             --split-by ID \
             --outdir sqoop-import \
             --query "$queryStatement" \
             -m 1 \
             --fields-terminated-by ',' \
             --hive-table $SQOOP_TABLE \
             --hive-overwrite \
             --hive-import

echo "Done running"

popd > /dev/null

