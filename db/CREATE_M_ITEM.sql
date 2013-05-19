
CREATE TABLE M_ITEM
(
	code varchar(10) primary key,
	name varchar(40),
	kana varchar(40),
	kikaku varchar(40),
	classCode smallint(4),
	stock int,
	properStock int,
	kisyuStock int,
	kicyuStockUpDown int,
	unit varchar(10),
	price decimal(8),
	kisyuPrice decimal(8),
	standardPrice decimal(8),
	sellingPrice2 decimal(8),
	sellingPrice3 decimal(8),
	sellingPrice4 decimal(8),
	sellingPrice5 decimal(8),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	foreign key(classCode) references M_ITEM_CLASS(code)
)
DEFAULT CHARSET=utf8;
