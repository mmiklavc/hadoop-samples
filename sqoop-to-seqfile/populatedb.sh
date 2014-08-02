#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

echo "Running..."

mysql -e "\
INSERT INTO hadoop_samples.SQOOP_IMPORT (COL1, COL2, COL3) VALUES \
(11, 12, 13), \
(21, 22, 23), \
(31, 32, 33);"

echo "Done running"

popd > /dev/null

