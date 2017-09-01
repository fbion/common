SELECT (SELECT count(1) FROM data) +  (SELECT count(1) FROM data_t WHERE date<'2017-01-23 19:45:49' AND date>'2017-01-11 01:18:51') as total FROM data_t WHERE date='2017-01-23 19:45:49'
UNION
SELECT count(1) as total FROM data_t
UNION
SELECT (SELECT count(1) FROM data_total) +  (SELECT count(1) FROM data_t WHERE date<'2017-01-23 19:45:49' AND date>'2017-01-11 01:18:51') as total FROM data_t WHERE date='2017-01-23 19:45:49'
#17206184
#12477670
#17206184

#17207252
#12477670
#17207252

#2017-01-23 19:45:49
#2017-01-11 01:18:51

INSERT INTO data(date,price) SELECT date,price FROM data_t WHERE date<'2017-01-23 19:45:49' AND date>'2017-01-11 01:18:51'
INSERT INTO data_total(date,price) SELECT date,price FROM data_t WHERE date<'2017-01-23 19:45:49' AND date>'2017-01-11 01:18:51'
INSERT INTO data_total(date,price) SELECT date,price FROM data_t WHERE date>='2017-01-23 19:45:49'

SELECT count(1) FROM data
UNION
SELECT count(1) as total FROM data_t
UNION
SELECT count(1) FROM data_total
#17206184
#12477670
#17206184


SELECT count(1) FROM data
UNION
SELECT count(1) as total FROM data_t
UNION
SELECT count(1) FROM data_total
UNION
SELECT count(1) FROM data_t WHERE date <= '2017-01-11 01:18:51'
UNION
SELECT count(1) FROM data_t WHERE date<'2017-01-23 19:45:49' AND date>'2017-01-11 01:18:51'
#17206184
#12477670
#28579852
#1068
#1102934


#查数据库大小
use 数据库名
SELECT sum(DATA_LENGTH)+sum(INDEX_LENGTH) FROM information_schema.TABLES where TABLE_SCHEMA='数据库名';
#查表大小
use information_schema;
select (data_length+index_length)/1024/1024  from tables where table_schema='data' and table_name = 'data'
union select (data_length+index_length)/1024/1024  from tables where table_schema='data' and table_name = 'data_t'
union select (data_length+index_length)/1024/1024  from tables where table_schema='data' and table_name = 'data_total';
#716.65625000
#404.89062500
#1188.75000000



DELETE from data WHERE price < 100;
DELETE from data_t WHERE price < 100;
DELETE from data_total WHERE price < 100;
CREATE TABLE backup LIKE data;
INSERT INTO backup(date,price) SELECT date,price FROM data;


INSERT INTO data(date, price)
SELECT n.date,n.price FROM data_n n WHERE n.date not exists (SELECT date FROM data);


SELECT count(*) from `data` a WHERE NOT EXISTS(SELECT * FROM data_n n WHERE n.date=a.date) AND  NOT EXISTS(SELECT *
FROM data_p p WHERE p.date=a.date);