CREATE TABLE M_STAFF
(
	code smallint NOT NULL primary key,
	sei varchar(40),
	name varchar(40),
	seiKana varchar(40),
	nameKana varchar(40),
	busyo varchar(40),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
