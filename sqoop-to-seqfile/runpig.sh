#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

. env.sh

echo "Running..."

pig -useHCatalog -p input=$SQOOP_TABLE -p output=$SEQ_TABLE script.pig

echo "Done running"

popd > /dev/null

