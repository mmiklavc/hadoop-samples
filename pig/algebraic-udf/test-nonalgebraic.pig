register 'target/algebraic-udf-1.0-SNAPSHOT.jar';

fs -rm -r -f count-nonalgebraic;

raw = load 'data*' using PigStorage() as (val:int);
grouped = group raw all;
count_items = foreach grouped generate com.michaelmiklavcic.pig.udf.COUNTNonAlgebraic(raw);
store count_items into 'count-nonalgebraic' using PigStorage();
