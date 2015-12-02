drop table if exists decimal_size1;
drop table if exists decimal_size2;

create external table decimal_size1(
    col1 int
)
location
    '/data/hadoop-samples/hive/decimal-size1';

create external table decimal_size2(
    col1 decimal(7,0)
)
location
    '/data/hadoop-samples/hive/decimal-size2';
