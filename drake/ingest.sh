#!/bin/bash

# tar xz $1 && hadoop fs -mkdir -p $2 && hadoop fs -put enron_with_categories/*/*.txt $2

hdfs dfs -rm -r $2

curl -sS $1 | tar xz && hadoop fs -mkdir -p $2 && hadoop fs -put enron_with_categories/*/*.txt $2

# curl -sS http://bailando.sims.berkeley.edu/enron/enron_with_categories.tar.gz | tar xz && hadoop fs -mkdir -p $1 && hadoop fs -put enron_with_categories/*/*.txt $1

