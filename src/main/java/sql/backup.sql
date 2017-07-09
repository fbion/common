SELECT (SELECT count(1) FROM data) +  (SELECT count(1) FROM data_t WHERE date<'2017-01-23 19:45:49') as total FROM data_t WHERE date='2017-01-23 19:45:49'
UNION
SELECT count(1) as total FROM data_t
UNION
SELECT (SELECT count(1) FROM data_total) +  (SELECT count(1) FROM data_t WHERE date<'2017-01-23 19:45:49') as total FROM data_t WHERE date='2017-01-23 19:45:49'
#17207252
#12477670
#17207252

#2017-01-23 19:45:49
INSERT INTO data(date,price) SELECT date,price FROM data_t WHERE date<'2017-01-23 19:45:49'
INSERT INTO data_total(date,price) SELECT date,price FROM data_t WHERE date<'2017-01-23 19:45:49'


SELECT count(1) FROM data
UNION
SELECT count(1) as total FROM data_t
UNION
SELECT count(1) FROM data_total
