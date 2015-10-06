; download data and push to HDFS

;hdfs:/data/drake/enron <- [get]

hdfs:/data/drake/enron <-
    ./ingest.sh http://bailando.sims.berkeley.edu/enron/enron_with_categories.tar.gz $OUTPUT

; filter the data with Pig
hdfs:/data/drake/output <- hdfs:/data/drake/enron
    pig -param input=$INPUT -param output=$OUTPUT id.pig 
