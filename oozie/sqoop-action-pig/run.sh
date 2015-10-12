#!/bin/bash

. env.sh

export OOZIE_URL=http://${OOZIE_HOST}:11000/oozie
oozie job -run -config config.xml
