
CREATE TABLE T_BILL_DETAILS
(
	voucherNo int NOT NULL,
	seq smallint NOT NULL,
	salesNo int NOT NULL,
	salesSeq smallint NOT NULL,
	salesDate date NOT NULL,
	itemCode varchar(10) NOT NULL,
	itemName varchar(40) NOT NULL,
	itemKikaku varchar(40),
	itemCount smallint NOT NULL,
	itemUnit varchar(10),
	itemUnitPrice decimal(8) NOT NULL,
	itemMemo varchar(100),
	itemPurchasePrice,
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(voucherNo, seq)
)
DEFAULT CHARSET=utf8;
