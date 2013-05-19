
CREATE TABLE T_QUOTATION_DETAILS
(
	voucherNo int NOT NULL,
	seq smallint NOT NULL,
	itemCode varchar(10) NOT NULL,
	itemUnitPrice decimal(8) NOT NULL,
	itemCount smallint NOT NULL,
	itemMemo varchar(100),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(voucherNo, seq)
)
DEFAULT CHARSET=utf8;
