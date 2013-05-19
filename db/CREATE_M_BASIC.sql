CREATE TABLE M_BASIC
(
	code tinyint primary key,
	name varchar(40),
	postCode1 char(3),
	postCode2 char(4),
	addr1 varchar(40),
	addr2 varchar(40),
	tel char(15),
	fax char(15),
	bank varchar(40),
	branch varchar(40),
	accountNo varchar(40),
	accountName varchar(40),
	kisyuYear smallint,
	kisyuMonth tinyint,
	kisyuDate tinyint,
	kimatsuYear smallint,
	kimatsuMonth tinyint,
	kimatsuDate tinyint,
	kessan tinyint,
	tax tinyint,
	taxVal decimal(4,2),
	calc tinyint,
	calcHasu tinyint,
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
