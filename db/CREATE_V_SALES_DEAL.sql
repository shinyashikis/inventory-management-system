
CREATE VIEW V_SALES_DEAL AS
	SELECT
		voucherNo,
		dealKind,
		dealCode,
		C.name AS dealName,
		CC.code AS dealClassCode,
		CC.name AS dealClassName,
		customerStaffSei AS dealStaffSei,
		customerStaffName AS dealStaffName,
		keisyo AS dealStaffKeisyo,
		C.busyo AS dealStaffBusyo,
		tel AS dealTel,
		postCode1 AS dealPostCode1,
		postCode2 AS dealPostCode2,
		addr1 AS dealAddr1,
		addr2 AS dealAddr2,
		shimebiMonthly,
		kessaiMonthly,
		shimebiKessai,
		dealDivision,
		salePriceDivision AS priceDivision,
		staffCode,
		ST.sei AS staffSei,
		ST.name AS staffName,
		ST.busyo AS staffBusyo,
		SD.updDate AS updDate
	FROM
		T_SALES_DEAL SD
		LEFT JOIN M_CUSTOMER C ON SD.dealCode = C.code 
		LEFT JOIN M_CUSTOMER_CLASS CC ON C.classCode = CC.code 
		LEFT JOIN M_STAFF ST ON C.staffCode = ST.code

