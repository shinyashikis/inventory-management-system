
CREATE VIEW V_ORDER_DEAL AS
	SELECT
		voucherNo,
		dealKind,
		dealCode,
		S.name AS dealName,
		SC.code AS dealClassCode,
		SC.name AS dealClassName,
		supplierStaffSei AS dealStaffSei,
		supplierStaffName AS dealStaffName,
		keisyo AS dealStaffKeisyo,
		S.busyo AS dealStaffBusyo,
		tel AS dealTel,
		postCode1 AS dealPostCode1,
		postCode2 AS dealPostCode2,
		addr1 AS dealAddr1,
		addr2 AS dealAddr2,
		shimebiMonthly,
		kessaiMonthly,
		shimebiKessai,
		dealDivision,
		tax,
		calc,
		calcHasu,
		staffCode,
		ST.sei AS staffSei,
		ST.name AS staffName,
		ST.busyo AS staffBusyo,
		OD.updDate AS updDate
	FROM
		T_ORDER_DEAL OD
		LEFT JOIN M_SUPPLIER S ON OD.dealCode = S.code 
		LEFT JOIN M_SUPPLIER_CLASS SC ON S.classCode = SC.code 
		LEFT JOIN M_STAFF ST ON S.staffCode = ST.code

