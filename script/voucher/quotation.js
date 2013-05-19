/**
 * 再表示(取引先)
 * @returns
 */
function redispDeal() {
	var form = document.forms[0];
	form.action = contextPath + '/QuotationInputAction.do?redispDeal';
	form.submit();
}

/**
 * 再表示(明細)
 * @returns
 */
function redispDetail() {
	var form = document.forms[0];
	form.action = contextPath + '/QuotationInputAction.do?redispDetail';
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
	form.action = contextPath + '/QuotationInputAction.do?searchDeal&targetVoucherDate=' + voucherDate + '&targetDealCode=' + dealCode + '&targetDealKind=' + dealKindVal;
	form.submit();
}

/**
 * 取引先変更
 * @param dealKind
 * @returns
 */
function changeDeal(dealKind) {
	var form = document.forms[0];
	form.action = contextPath + '/QuotationInputAction.do?changeDeal&targetDealKind=' + dealKind;
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
	form.action = contextPath + '/QuotationInputAction.do?searchItem&targetItemIndex=' + itemIndex + '&targetItemCode=' + itemCode;
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
	form.action = contextPath + '/QuotationInputAction.do?deleteItem';
	form.submit();
}

/**
 * 更新処理
 * @returns
 */
function update() {
	if(checkValidation()){
		var form = document.forms[0];
		form.action = contextPath + '/QuotationInputAction.do?update';
		form.submit();
	}
}
