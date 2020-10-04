INSERT INTO CLIENT (id,first_name, last_name) VALUES
  (1,'James', 'Bond'),
  (2,'Albert', 'Einstein'),
  (3,'Leonard', 'De vinci');

INSERT INTO ACCOUNT (id,balance,client_id) values
    ('FR30001',3000,'1'),
    ('FR30002',1000,'2'),
    ('FR30003',15000,'3');

INSERT INTO OPERATION (account_id,amount,label,date_time) values
    ('FR30001',200,'DEPOSIT','2020-10-03 16:00:00.000'),
    ('FR30002',-100,'WITHDRAWAL','2020-10-03 16:00:00.000'),
    ('FR30003',2000,'DEPOSIT','2020-10-03 16:00:00.000');