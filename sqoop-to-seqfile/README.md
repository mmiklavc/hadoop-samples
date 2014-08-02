# Sqoop to sequence file with Pig example

## The transformation

* mysql -> sqoop -> hive plain text
* hive plain text -> pig hcatalog -> hive sequence file

## Setup

```
$ ./makedb.sh
$ ./populatedb.sh
$ ./makehive.sh
```

## Running

```
$ ./sqoopimport.sh
$ ./runpig.sh
```

## Results

```
$ hive -e "select * from sqoop_import"
$ hive -e "select * from seq_import"
```

