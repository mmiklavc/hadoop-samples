#!/bin/bash

export SETUP_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/setup
export OOZIE_APP_HOME=/apps/oozie
export OOZIE_WORKFLOW_HOME=${OOZIE_APP_HOME}/workflow
export OOZIE_COORDINATOR_HOME=${OOZIE_APP_HOME}/coordinator
export APP_ROOT=${OOZIE_WORKFLOW_HOME}/sqoop-action-pig
export OOZIE_HOST=sandbox.hortonworks.com

export DATA_ROOT=/data/hadoop-samples
export STG_DIR=${DATA_ROOT}/stg-sqoop-action-pig
export PREP_DB=default
export PREP_TABLE=sqoop_action_pig
export PREP_DIR=${DATA_ROOT}/sqoop-action-pig
export ARCHIVE_DIR=${DATA_ROOT}/archive/raw-sqoop-action-pig
