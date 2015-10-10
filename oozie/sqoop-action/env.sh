#!/bin/bash

export OOZIE_APP_HOME=/apps/oozie
export OOZIE_WORKFLOW_HOME=${OOZIE_APP_HOME}/workflow
export OOZIE_COORDINATOR_HOME=${OOZIE_APP_HOME}/coordinator
export APP_ROOT=${OOZIE_WORKFLOW_HOME}/sqoop-action
export OOZIE_HOST=sandbox.hortonworks.com

export SQOOP_TABLE=stg_sqoop_action
export SQOOP_DIR=/data/hadoop-samples/sqoop-action
