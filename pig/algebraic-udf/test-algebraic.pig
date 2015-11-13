register 'target/algebraic-udf-1.0-SNAPSHOT.jar';

fs -rm -r -f count-algebraic;

raw = load 'data*' using PigStorage() as (val:int);
grouped = group raw all;
count_items = foreach grouped generate com.michaelmiklavcic.pig.udf.COUNT(raw);
store count_items into 'count-algebraic' using PigStorage();
