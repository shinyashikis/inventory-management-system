/**
 * 在庫数量の計算
 */
function calcStock() {
	var stock = document.getElementById('hiddenStock').value;
	var kisyuStock = document.getElementById('kisyuStock').value;
	var kicyuStockUpDown = document.getElementById('kicyuStockUpDown').value;
	var hiddenKicyuStockUpDown = document.getElementById('hiddenKicyuStockUpDown').value;

	if(stock == ""){
		stock = 0;
	}
	if(kisyuStock == ""){
		kisyuStock = 0;
	}
	if(kicyuStockUpDown == ""){
		kicyuStockUpDown = 0;
	}
	if(hiddenKicyuStockUpDown == ""){
		hiddenKicyuStockUpDown = 0;
	}

	var sum = 0;
	if (!document.getElementById('kisyuStock').disabled) {
		sum = eval(kisyuStock + "+" + kicyuStockUpDown);
	} else {
		var calcValue = eval(kicyuStockUpDown + "-" + hiddenKicyuStockUpDown);
		sum = eval(stock + "+" + calcValue);
	}

	document.getElementById('stock').value = sum;
}


