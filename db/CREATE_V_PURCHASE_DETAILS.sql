
CREATE VIEW V_PURCHASE_DETAILS AS
	SELECT
		PD.voucherNo,
		PD.seq,
		PD.itemCode,
		I.name AS itemName,
		I.kikaku AS itemKikaku,
		PD.itemCount,
		PD.itemUnitPrice,
		I.unit AS itemUnit,
		PD.itemMemo,
		I.price AS itemPurchasePrice,
		PD.updDate
	FROM
		T_PURCHASE_DETAILS PD,
		M_ITEM I
	WHERE
		PD.itemCode = I.code 

