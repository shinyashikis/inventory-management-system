
CREATE TABLE T_QUOTATION_DEAL
(
	voucherNo int NOT NULL PRIMARY KEY,
    dealKind tinyint NOT NULL,
	dealCode varchar(10) NOT NULL,
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;
