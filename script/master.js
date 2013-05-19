/**
 * 分類一覧ダイアログ(モーダル)起動
 * @param url
 */
function openClassDialog(url) {
	var tokenName = 'org.apache.struts.taglib.html.TOKEN';
	var tokenVal = document.getElementById(tokenName).value;

	var display = "dialogWidth:500px;dialogHeight:500px;center:yes;";
	var classList = showModalDialog(url + '?' + tokenName + '=' + tokenVal, window, display);

	if(classList==null){
		return;
	}

	// 分類セレクトボックスの初期化
	var sel = document.getElementById('classCode');
	var iniVal = sel.options[0].value;
	var iniTxt = sel.options[0].text;
	var selIndex = sel.selectedIndex;

	// 全削除
	while(sel.lastChild){
		sel.removeChild(sel.lastChild);
	}

	// 初期値(分類なし)を設定
	if(selIndex==0){
		sel.options[0] = new Option(iniTxt,iniVal,false,true);
	}else{
		sel.options[0] = new Option(iniTxt,iniVal);
	}

	// 最新の分類一覧を追加
	for(i=0;i<classList.length;i++){
		var index = i+1;
		if(selIndex==index){
			sel.options[index] = new Option(classList[i][1],classList[i][0],false,true);
		}else{
			sel.options[index] = new Option(classList[i][1],classList[i][0]);
		}
	}

}

/**
 * 分類一覧ダイアログクローズ
 */
function closeClassListDialog(classList) {
	returnValue = classList;
	self.close();
}

/**
 * 税設定変更
 * @param tax
 * @param calc
 * @param calcHasu
 */
function changeTaxStatus(tax,calc,calcHasu) {
	var i;
	var val;
	for(i=0;i<tax.length;i++){
		if(tax[i].checked){
			val = tax[i].value;
			break;
		}
	}

	var flg = "true";
	if (val == 0) {
		flg = "";
	}
	for (i = 0; i < calc.length; i++){
		calc[i].disabled = flg;
	}
	for (i = 0; i < calcHasu.length; i++){
		calcHasu[i].disabled = flg;
	}
}

/**
 * 取引区分変更時
 * @param dealDivision
 * @param dealDivisionCash
 * @returns {Boolean}
 */
function changeDealDivision(dealDivision, dealDivisionCash) {
	var flg = (dealDivision == dealDivisionCash);
	document.getElementById('kisyuBalance').disabled = flg;
	document.getElementById('shimebiMonthly').disabled = flg;
	document.getElementById('shimebiKessai').disabled = flg;
	document.getElementById('kessaiMonthly').disabled = flg;
}
