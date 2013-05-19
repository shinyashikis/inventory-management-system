/**
 * 再表示(取引先)
 * @returns
 */
function redispDeal() {
	var form = document.forms[0];
	form.action = contextPath + '/OrderInputAction.do?redispDeal';
	form.submit();
}

/**
 * 再表示(明細)
 * @returns
 */
function redispDetail() {
	var form = document.forms[0];
	form.action = contextPath + '/OrderInputAction.do?redispDetail';
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
	form.action = contextPath + '/OrderInputAction.do?searchDeal&targetVoucherDate=' + voucherDate + '&targetDealCode=' + dealCode + '&targetDealKind=' + dealKindVal;
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
	form.action = contextPath + '/OrderInputAction.do?searchItem&targetItemIndex=' + itemIndex + '&targetItemCode=' + itemCode;
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
	form.action = contextPath + '/OrderInputAction.do?deleteItem';
	form.submit();
}

/**
 * 更新処理
 * @returns
 */
function update() {
	if(checkValidation()){
		var form = document.forms[0];
		form.action = contextPath + '/OrderInputAction.do?update';
		form.submit();
	}
}

/**
 * 仕入伝票作成処理
 * @returns
 */
function createPurchase() {
	if (!confirm(msgCreatePurchase)) {
		return;
	}
	if(checkValidation()){
		var form = document.forms[0];
		form.action = contextPath + '/OrderInputAction.do?createPurchase';
		form.submit();
	}
}
