
CREATE VIEW V_QUOTATION_DEAL AS
	SELECT
		voucherNo,
		dealKind,
		IF (dealKind='0',C.code,S.code) AS dealCode, 
		IF (dealKind='0',C.name,S.name) AS dealName, 
		IF (dealKind='0',C.classCode,S.classCode) AS dealClassCode, 
		IF (dealKind='0',CC.name,SC.name) AS dealClassName, 
		IF (dealKind='0',C.customerStaffSei,S.supplierStaffSei) AS dealStaffSei, 
		IF (dealKind='0',C.customerStaffName,S.supplierStaffName) AS dealStaffName, 
		IF (dealKind='0',C.keisyo,S.keisyo) AS dealStaffKeisyo, 
		IF (dealKind='0',C.busyo,S.busyo) AS dealStaffBusyo, 
		IF (dealKind='0',C.tel,S.tel) AS dealTel, 
		IF (dealKind='0',C.postCode1,S.postCode1) AS dealPostCode1, 
		IF (dealKind='0',C.postCode2,S.postCode2) AS dealPostCode2, 
		IF (dealKind='0',C.addr1,S.addr1) AS dealAddr1, 
		IF (dealKind='0',C.addr2,S.addr2) AS dealAddr2, 
		IF (dealKind='0',C.shimebiMonthly,S.shimebiMonthly) AS shimebiMonthly, 
		IF (dealKind='0',C.kessaiMonthly,S.kessaiMonthly) AS kessaiMonthly, 
		IF (dealKind='0',C.shimebiKessai,S.shimebiKessai) AS shimebiKessai, 
		IF (dealKind='0',C.dealDivision,S.dealDivision) AS dealDivision, 
		IF (dealKind='0',C.salePriceDivision,NULL) AS priceDivision, 
		IF (dealKind='0',C.staffCode,S.staffCode) AS staffCode, 
		ST.sei AS staffSei, 
		ST.name AS staffName, 
		ST.busyo AS staffBusyo, 
		QD.updDate AS updDate
	FROM
		T_QUOTATION_DEAL QD
        LEFT JOIN M_CUSTOMER C ON QD.dealCode = C.code AND dealKind = '0'
	    LEFT JOIN M_SUPPLIER S ON QD.dealCode = S.code AND dealKind = '1'
		LEFT JOIN M_CUSTOMER_CLASS CC ON C.classCode = CC.code
		LEFT JOIN M_SUPPLIER_CLASS SC ON S.classCode = SC.code
		LEFT JOIN M_STAFF ST ON C.staffCode = ST.code OR S.staffCode = ST.code 
