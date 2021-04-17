DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  available_points INT NOT NULL
);

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  code VARCHAR(5) NOT NULL,
  cost_points INT NOT NULL
);

INSERT INTO customer (name, available_points) VALUES
  ('Kulani Desmond Nkwinika', 10000),
  ('Credo Mutwa',15000),
  ('Cheik Anta Diop', 1500);


INSERT INTO product (name, code,cost_points) VALUES
  ('Banana Bendtner','BB', 100),
  ('Pear Phone', 'PP',150),
  ('Kulani Beans', 'KB',50);