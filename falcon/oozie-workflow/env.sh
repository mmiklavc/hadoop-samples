#!/bin/bash

pushd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" > /dev/null

export IN_DATABASE_NAME=mmiklavcic
export OUT_DATABASE_NAME=mmiklavcic

export IN_TABLE_NAME=rawdata
export OUT_TABLE_NAME=filtereddata

export IN_TBL_LOCATION=/user/mmiklavcic/falcon-test-in
export OUT_TBL_LOCATION=/user/mmiklavcic/falcon-test-out

popd > /dev/null

