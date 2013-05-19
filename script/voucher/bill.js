/**
 * 請求書得意先ダイアログ起動
 * @param dealCode
 * @returns
 */
function openBillCustomerDialog(dealCode) {
	var voucherDate = document.getElementById("voucherDate").value;
	openListDialog(contextPath + '/BillCustomerListAction.do', 'dealCode=' + dealCode + '&voucherDate=' + voucherDate);
}

/**
 * 再表示(取引先)
 * @returns
 */
function redispDeal() {
	var form = document.forms[0];
	form.action = contextPath + '/BillInputAction.do?redispDeal';
	form.submit();
}

/**
 * 取引先区分取得
 * @returns
 */
function getDealKind() {
	var dealKindRadio = document.getElementsByName("dealKind");
	for (i=0;i<dealKindRadio.length;i++) {
		if (dealKindRadio[i].checked) {
			return dealKindRadio[i].value;
		}
	}
}

/**
 * 取引先検索
 * @param dealCode
 * @returns
 */
function searchDeal(dealCode) {
	var voucherDate = document.getElementById("voucherDate").value;
	var form = document.forms[0];
	form.action = contextPath + '/BillInputAction.do?searchDeal&targetDealCode=' + dealCode + '&targetVoucherDate=' + voucherDate;
	form.submit();
}

/**
 * 更新処理
 * @returns
 */
function update() {
	if(document.getElementById('dealName').innerText == ''){
		alert(dealErrMsgRequired);
		return false;
	}
	var form = document.forms[0];
	form.action = contextPath + '/BillInputAction.do?update';
	form.submit();
}

/**
 * プレビュー処理
 * @returns
 */
function printBill() {
	if(document.getElementById('dealName').innerText == ''){
		alert(dealErrMsgRequired);
		return false;
	}
	openReportDialog(reportDialogUrl);
}
