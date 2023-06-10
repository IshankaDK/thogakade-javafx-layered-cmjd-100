drop database kade;
CREATE DATABASE kade;
USE kade;

CREATE TABLE Customer(
	id int NOT NULL,
	name VARCHAR(50) NOT NULL,
	address VARCHAR(200) NOT NULL,
	salary decimal(10,2),
	CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE Item(
	code VARCHAR(50) NOT NULL,
	description VARCHAR(50) NOT NULL,
	unitPrice decimal(10,2),
	qtyOnHand decimal(10,2),
	CONSTRAINT PRIMARY KEY (code)
);