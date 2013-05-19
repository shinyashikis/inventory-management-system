
CREATE TABLE M_ITEM_CLASS
(
	code smallint(4) primary key,
	name varchar(20),
	kana varchar(20),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
