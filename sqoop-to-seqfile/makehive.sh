#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

. env.sh

echo "Running..."

hive -f makehive.hql

echo "Done running"

popd > /dev/null

