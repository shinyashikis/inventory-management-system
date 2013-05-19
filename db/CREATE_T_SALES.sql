
CREATE TABLE T_SALES
(
	voucherNo int NOT NULL PRIMARY KEY,
	voucherDate date NOT NULL,
	discount decimal(8),
	etc1 varchar(40),
	etc2 varchar(40),
	receiptNo varchar(5),
	proviso varchar(40),
	receiptDate date,
	billNo int,
	memo varchar(100),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
