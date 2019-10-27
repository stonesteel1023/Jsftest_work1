/* Create Database */
CREATE DATABASE mahoDb;

/* Use The Newly Created Database */
USE mahoDb;

/* Creating Table */
CREATE TABLE mahocast (
  id INTEGER NOT NULL,
  name VARCHAR(120),
  PRIMARY KEY(id)
);

/* Insert Values In Table */
INSERT INTO mahocast (id, name) VALUES (1, "CEO kim");
INSERT INTO mahocast (id, name) VALUES (2, "CFO yoen");
INSERT INTO mahocast (id, name) VALUES (3, "Team Leader han");
INSERT INTO mahocast (id, name) VALUES (4, "Engineer Kim");
INSERT INTO mahocast (id, name) VALUES (5, "Engineer Choi");