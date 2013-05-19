/**
 * ボタン活性・非活性
 */
function changeBtnStatus() {
	var addBtn = document.getElementById('addBtn');
	var updBtn = document.getElementById('updBtn');
	var delBtn = document.getElementById('delBtn');
	var cnt = getCheckBoxCnt(true);
	if(cnt==0){
		if (addBtn != null) {
			addBtn.disabled = false;
		}
		if (updBtn != null) {
			updBtn.disabled = true;
		}
		if (delBtn != null) {
			delBtn.disabled = true;
		}
	}else if(cnt==1){
		if (addBtn != null) {
			addBtn.disabled = true;
		}
		if (updBtn != null) {
			updBtn.disabled = false;
		}
		if (delBtn != null) {
			delBtn.disabled = false;
		}
	}else if(cnt>1){
		if (addBtn != null) {
			addBtn.disabled = true;
		}
		if (updBtn != null) {
			updBtn.disabled = true;
		}
		if (delBtn != null) {
			delBtn.disabled = false;
		}
	}
}

/**
 * 全チェックボックス設定処理
 * @param check
 */
function changeAllCheckBox(check) {
	var elements = document.getElementsByTagName("input");
	for (i=0;i<elements.length;i++) {
		if(elements[i].type == "checkbox"){
			elements[i].checked = check;
		}
	}
}

/**
 * チェック状態チェックボックス数取得処理
 * @param check
 * @returns {Number}
 */
function getCheckBoxCnt(check) {
	var cnt=0;
	var elements = document.getElementsByTagName("input");
	for (i=0;i<elements.length;i++) {
		if(elements[i].type == "checkbox"){
			if(elements[i].checked == check){
				cnt++;
			}
		}
	}
	return cnt;
}

/**
 * hidden項目生成
 * @param form
 * @param name
 */
function createHidden(form,name) {
	var q = document.createElement('input');
	q.type = 'hidden';
	q.name = name;
	form.appendChild(q);
}

/**
 * view.properties値取得
 * @param props
 * @param targetName
 * @param fromName
 * @param fromValue
 * @returns
 */
function getViewPropertiesValue(props, targetName, fromName, fromValue) {
	if (fromValue == "") {
		return "";
	}
	for (i=0;i<props.length;i++) {
		if (props[i].indexOf(fromName + "=" + fromValue) != -1) {
			var start = props[i].indexOf(targetName) + targetName.length + 1;
			var ret = props[i].slice(start,props[i].length);
			if (ret.indexOf(",") == -1) {
				return ret;
			} else {
				return ret.slice(0,ret.indexOf(","));
			}
		}
	}
	return "";
}

/**
 * 未入力チェック
 * @param value
 * @returns {Boolean}
 */
function isBlank(value) {
	return (value == null || value == "");
}

/**
 * 数字チェック
 * @param value
 * @returns {Boolean}
 */
function isNumber(value) {
	return !isBlank(value) && !isNaN(value);
}

/**
 * 数字入力制御
 * @returns {Boolean}
 */
function numberOnly() {
	 st = String.fromCharCode(event.keyCode);
	 if ("0123456789\b\r\t".indexOf(st,0) < 0) return false;
	 return true;
}

/**
 * 金額入力制御
 * @returns {Boolean}
 */
function moneyOnly(value) {
	if (value.match(/^(-)\d+$/) == null && value.match(/^\d+$/) == null) return false;
	return true;
}

/**
 * 日付フォーマットチェック
 * @param value
 */
function chkDateFormat(value) {
	if (value == null || value == "") {
		return true;
	}
	if(!value.match(/^\d{8}$/)) {
		return false;
	}
	return true;
}

/**
 * ラジオボタン選択有無
 */
function isSelectedRadio() {
	var selRadio = document.getElementsByName("sel");
	for (i=0;i<selRadio.length;i++) {
		if (selRadio[i].checked) {
			return true;
		}
	}
	return false;
}

