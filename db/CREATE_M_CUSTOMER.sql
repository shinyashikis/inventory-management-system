
CREATE TABLE M_CUSTOMER
(
	code varchar(10) NOT NULL PRIMARY KEY,
	name varchar(40) NOT NULL,
	kana varchar(40),
	classCode smallint(4),
	addr1 varchar(40),
	addr2 varchar(40),
	postCode1 char(3),
	postCode2 char(4),
	tel char(15),
	fax char(15),
	customerStaffSei varchar(40),
	customerStaffName varchar(40),
	keisyo tinyint NOT NULL,
	busyo varchar(40),
	dealDivision tinyint NOT NULL,
	kessaiDivision tinyint NOT NULL,
	salePriceDivision tinyint NOT NULL,
	kisyuBalance decimal(11),
	shimebiMonthly tinyint,
	shimebiKessai tinyint,
	kessaiMonthly tinyint,
	staffCode smallint,
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	foreign key(classCode) references M_CUSTOMER_CLASS(code),
	foreign key(staffCode) references M_STAFF(code)
)
DEFAULT CHARSET=utf8;
