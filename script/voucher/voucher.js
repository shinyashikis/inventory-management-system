/**
 * 入力チェック(伝票日付)
 */
function checkVoucherDate(value) {
	if (isBlank(value)) {
		alert(voucherDateErrMsgRequired);
		return false;
	}
	if (!isNumber(value)) {
		alert(voucherDateErrMsgNoN);
		return false;
	}
	return true;
}

/**
 * 入力チェック(伝票名称)
 */
function checkVoucherName(value) {
	if (isBlank(value)) {
		alert(voucherNameErrMsgRequired);
		return false;
	}
	return true;
}

/**
 * 入力チェック(取引先コード)
 */
function checkDealCode(value) {
	if (isBlank(value)) {
		alert(dealCodeErrMsgRequired);
		return false;
	}
	return true;
}

/**
 * 入力チェック(調整額)
 */
function checkDiscount(value) {
	if (isBlank(value)) {
		alert(discountErrMsgRequired);
		return false;
	}
	if (!isNumber(value)) {
		alert(discountErrMsgNoN);
		return false;
	}
	return true;
}

/**
 * 入力チェック(商品コード)
 */
function checkItemCode(value) {
	if (isBlank(value)) {
		alert(itemCodeErrMsgRequired);
		return false;
	}
	return true;
}

/**
 * 入力チェック(数量)
 */
function checkItemCount(value) {
	if (isBlank(value)) {
		alert(itemCountErrMsgRequired);
		return false;
	}
	if (!isNumber(value)) {
		alert(itemCountErrMsgNoN);
		return false;
	}
	return true;
}

/**
 * 入力チェック(単価)
 */
function checkItemUnitPrice(value) {
	if (isBlank(value)) {
		alert(itemUnitPriceErrMsgRequired);
		return false;
	}
	if (!isNumber(value)) {
		alert(itemUnitPriceErrMsgNoN);
		return false;
	}
	return true;
}

/**
 * バリデーションチェック
 */
function checkValidation() {
	if(document.getElementById('dealName').innerText == ''){
		alert(dealErrMsgRequired);
		return false;
	}

	var itemCnt = document.getElementById('itemTable').rows.length - 2;
	if(itemCnt == 0){
		alert(itemErrMsgRequired);
		return false;
	}
	return true;
}

/**
 * 取引先ダイアログ起動
 * @param dealCode
 * @returns
 */
function openDealDialog(dealCode) {
	var dealKindVal = getDealKind();
	var voucherDate = document.getElementById('voucherDate').value;

	if (dealKindVal == dealKindCustomer) {
		openListDialog(contextPath + '/VoucherCustomerListAction.do', '&voucherDate=' + voucherDate + 'dealCode=' + dealCode);
	} else if (dealKindVal == dealKindSupplier) {
		openListDialog(contextPath + '/VoucherSupplierListAction.do', '&voucherDate=' + voucherDate + 'dealCode=' + dealCode);
	}
}

/**
 * (伝票)商品ダイアログ起動
 * @param itemCode
 * @returns
 */
function openItemDialog(itemCode) {
	if(document.getElementById('dealName').innerText == ''){
		alert(dealErrMsgRequired);
		return;
	}
	openListDialog(contextPath + '/VoucherItemListAction.do', 'itemCode=' + itemCode);
}

/**
 * プレビュー処理
 * @returns
 */
function print() {
	if(checkValidation()){
		openReportDialog(reportDialogUrl);
	}
}

/**
 * 一覧ダイアログ(モーダル)起動
 * @param url
 * @param param
 */
function openListDialog(url,param) {
	var tokenName = 'org.apache.struts.taglib.html.TOKEN';
	var tokenVal = document.getElementById(tokenName).value;
	var display = "dialogWidth:500px;dialogHeight:500px;center:yes;";

	// キャッシュ防止用として、タイムスタンプをURLへ付与
	var timstamp= new Date().getTime();

	if (param != null) {
		return showModalDialog(url + '?' + timstamp + '&' + tokenName + '=' + tokenVal + '&' + param, window, display);
	} else {
		return showModalDialog(url + '?' + timstamp + '&' + tokenName + '=' + tokenVal, window, display);
	}
}

/**
 * 帳票参照ダイアログ起動
 * @param url
 */
function openReportDialog(url) {
	var form = document.forms[0];

	var oldAction = form.action;
	var oldTarget = form.target;

	form.target = "parent";
	var newWin = window.open(
			"about:blank",
			"parent",
			"width=800, height=600, resizable=yes"
	);

	form.action = url;
	form.submit();
	form.action = oldAction;
	form.target = oldTarget;
	newWin.focus();
}

