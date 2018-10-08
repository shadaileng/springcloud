CREATE TABLE IF NOT EXISTS department (
id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
name VARCHAR(60),
db_source VARCHAR(60)
);

INSERT INTO department(name, db_source) VALUES('开发部','demo');
INSERT INTO department(name, db_source) VALUES('测试部','demo');
INSERT INTO department(name, db_source) VALUES('运维部','demo');

SELECT * FROM department;