
CREATE VIEW V_QUOTATION_DETAILS AS
	SELECT
		QD.voucherNo,
		QD.seq,
		QD.itemCode,
		I.name AS itemName,
		I.kikaku AS itemKikaku,
		QD.itemCount,
		QD.itemUnitPrice,
		I.unit AS itemUnit,
		QD.itemMemo,
		I.price AS itemPurchasePrice,
		QD.updDate
	FROM
		T_QUOTATION_DETAILS QD,
		M_ITEM I
	WHERE
		QD.itemCode = I.code 

