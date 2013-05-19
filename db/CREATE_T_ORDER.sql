
CREATE TABLE T_ORDER
(
	voucherNo int NOT NULL PRIMARY KEY,
	voucherDate date NOT NULL,
	discount decimal(8),
	orderName varchar(40) NOT NULL,
	paymentLimit varchar(20),
	paymentPlace varchar(20),
	paymentCondition varchar(20),
	expirationDate varchar(20),
	memo varchar(100),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
