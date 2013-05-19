
CREATE TABLE T_BILL
(
	voucherNo int NOT NULL PRIMARY KEY,
	voucherDate date NOT NULL,
	discount decimal(8),
	memo varchar(100),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
