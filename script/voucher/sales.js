/**
 * 入力チェック(領収書日付)
 */
function checkReceiptDate(value) {
	if (isBlank(value)) {
		return true;
	}
	if (!isNumber(value)) {
		alert(receiptDateErrMsgNoN);
		return false;
	}
	return true;
}

/**
 * 再表示(取引先)
 * @returns
 */
function redispDeal() {
	var form = document.forms[0];
	form.action = contextPath + '/SalesInputAction.do?redispDeal';
	form.submit();
}

/**
 * 再表示(明細)
 * @returns
 */
function redispDetail() {
	var form = document.forms[0];
	form.action = contextPath + '/SalesInputAction.do?redispDetail';
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
	var voucherDate = document.getElementById('voucherDate').value;
	var dealKindVal = getDealKind();
	var form = document.forms[0];
	form.action = contextPath + '/SalesInputAction.do?searchDeal&targetVoucherDate=' + voucherDate + '&targetDealCode=' + dealCode + '&targetDealKind=' + dealKindVal;
	form.submit();
}

/**
 * 商品検索
 * @param itemIndex
 * @param itemCode
 * @returns
 */
function searchItem(itemIndex,itemCode) {
	if(document.getElementById('dealName').innerText == ''){
		alert(dealErrMsgRequired);
		return;
	}
	var form = document.forms[0];
	form.action = contextPath + '/SalesInputAction.do?searchItem&targetItemIndex=' + itemIndex + '&targetItemCode=' + itemCode;
	form.submit();
}

/**
 * 商品削除
 * @returns
 */
function deleteItem() {
	if(getCheckBoxCnt(true) == 0){
		alert(itemErrMsgRequired);
		return;
	}
	var form = document.forms[0];
	form.action = contextPath + '/SalesInputAction.do?deleteItem';
	form.submit();
}

/**
 * 更新処理
 * @returns
 */
function update() {
	if(checkValidation()){
		var form = document.forms[0];
		form.action = contextPath + '/SalesInputAction.do?update';
		form.submit();
	}
}
