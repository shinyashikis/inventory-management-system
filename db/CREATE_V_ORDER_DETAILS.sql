
CREATE VIEW V_ORDER_DETAILS AS
	SELECT
		OD.voucherNo,
		OD.seq,
		OD.itemCode,
		I.name AS itemName,
		I.kikaku AS itemKikaku,
		OD.itemCount,
		OD.itemUnitPrice,
		I.unit AS itemUnit,
		OD.itemMemo,
		I.price AS itemPurchasePrice,
		OD.updDate
	FROM
		T_ORDER_DETAILS OD,
		M_ITEM I
	WHERE
		OD.itemCode = I.code 

