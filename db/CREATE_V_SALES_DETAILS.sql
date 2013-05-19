
CREATE VIEW V_SALES_DETAILS AS
	SELECT
		SD.voucherNo,
		SD.seq,
		SD.itemCode,
		I.name AS itemName,
		I.kikaku AS itemKikaku,
		SD.itemCount,
		SD.itemUnitPrice,
		I.unit AS itemUnit,
		SD.itemMemo,
		I.price AS itemPurchasePrice,
		SD.updDate
	FROM
		T_SALES_DETAILS SD,
		M_ITEM I
	WHERE
		SD.itemCode = I.code 

