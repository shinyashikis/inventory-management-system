<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
/**
 * 表示対象指定ラジオボタン変更時
 * @param value
 */
function changeDispTargetRadio(value) {

	if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>) {
		// 商品在庫表選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = true;
		document.getElementById('searchDateMonthFromComb').disabled = true;
		document.getElementById('searchDateDayFromComb').disabled = true;
		document.getElementById('searchDateMonthToComb').disabled = true;
		document.getElementById('searchDateDayToComb').disabled = true;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = true;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = false;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_STOCK_TRANSITION, ViewProperties.VALUE) %>) {
		// 在庫推移表選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = true;
		document.getElementById('searchDateMonthFromComb').disabled = true;
		document.getElementById('searchDateDayFromComb').disabled = true;
		document.getElementById('searchDateMonthToComb').disabled = true;
		document.getElementById('searchDateDayToComb').disabled = true;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = true;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = false;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_SHIP_SUM, ViewProperties.VALUE) %>) {
		// 出庫集計表選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = false;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_STORAGE_SUM, ViewProperties.VALUE) %>) {
		// 入庫集計表選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = false;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_SHIP_BOOK, ViewProperties.VALUE) %>) {
		// 出庫台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_STORAGE_BOOK, ViewProperties.VALUE) %>) {
		// 入庫台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_SALES_PRICE_AVE, ViewProperties.VALUE) %>) {
		// 平均販売価格選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_PURCHASE_PRICE_AVE, ViewProperties.VALUE) %>) {
		// 平均仕入価格選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_GROSS, ViewProperties.VALUE) %>) {
		// 粗利益選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_PROFIT, ViewProperties.VALUE) %>) {
		// 利益率選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(数量表示)
		document.getElementById('dispTargetDetailCountRadio').disabled = true;
		// 表示対象詳細指定(金額表示)
		document.getElementById('dispTargetDetailPriceRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_SALES_TOTAL, ViewProperties.VALUE) %>) {
		// 売上集計表選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = true;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_SALES_BOOK, ViewProperties.VALUE) %>) {
		// 売上台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = false;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_URIKAKE_BOOK, ViewProperties.VALUE) %>) {
		// 売掛台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = false;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_PURCHASE_TOTAL, ViewProperties.VALUE) %>) {
		// 仕入集計選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = false;
		document.getElementById('shimebiComb').disabled = false;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = true;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = true;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_PURCHASE_BOOK, ViewProperties.VALUE) %>) {
		// 仕入台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = false;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = false;

	} else if (value == <%= ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_TARGET_KAIKAKE_BOOK, ViewProperties.VALUE) %>) {
		// 買掛台帳選択時

		// 日付範囲指定(日付範囲指定)
		document.getElementById('selectDateRangeRadio').disabled = false;
		document.getElementById('searchDateMonthFromComb').disabled = false;
		document.getElementById('searchDateDayFromComb').disabled = false;
		document.getElementById('searchDateMonthToComb').disabled = false;
		document.getElementById('searchDateDayToComb').disabled = false;
		// 日付範囲指定(月次推移表)
		document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
		document.getElementById('shimebiComb').disabled = true;
		// 日付範囲指定(年度合計)
		document.getElementById('selectDateRangeNendoRadio').disabled = false;

		// 表示対象詳細指定(伝票別)
		document.getElementById('dispTargetDetailVoucherRadio').disabled = false;
		// 表示対象詳細指定(詳細)
		document.getElementById('dispTargetDetailDetailRadio').disabled = false;
	}

}
</script>

<tr>
<th class="th01" colspan="6"><bean:message key="BOOK.LABEL.SELECT_DISP_TARGET"/></th>
</tr>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<tr>
<td class="td02"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_ITEM_STOCK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.ITEM_STOCK"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_STOCK_TRANSITION, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.STOCK_TRANSITION"/></html:radio></td>
</tr>
<tr>
<td class="td02"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SHIP_SUM, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.SHIP_SUM"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_STORAGE_SUM, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.STORAGE_SUMK"/></html:radio></td>
</tr>
<tr>
<td class="td02"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SHIP_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.SHIP_BOOK"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_STORAGE_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.STORAGE_BOOK"/></html:radio></td>
</tr>
<tr>
<td class="td02"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SALES_PRICE_AVE, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.SALES_PRICE_AVE"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_PURCHASE_PRICE_AVE, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.PURCHASE_PRICE_AVE"/></html:radio></td>
</tr>
<tr>
<td class="td02"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_GROSS, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.GROSS"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="dispTarget" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_PROFIT, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_ITEM.LABEL.PROFIT"/></html:radio></td>
</tr>
</logic:equal>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookSalesForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SALES_TOTAL, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_SALES.LABEL.SALES_TOTAL"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookSalesForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SALES_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_SALES.LABEL.SALES_BOOK"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookSalesForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_URIKAKE_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_SALES.LABEL.URIKAKE_BOOK"/></html:radio></td>
</tr>
</logic:equal>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookPurchaseForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_PURCHASE_TOTAL, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_PURCHASE.LABEL.PURCHASE_TOTAL"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookPurchaseForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_PURCHASE_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_PURCHASE.LABEL.PURCHASE_BOOK"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookPurchaseForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_KAIKAKE_BOOK, ViewProperties.VALUE) %>" onclick="changeDispTargetRadio(this.value);"><bean:message key="BOOK_PURCHASE.LABEL.KAIKAKE_BOOK"/></html:radio></td>
</tr>
</logic:equal>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookVoucherListForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_QUOTATION, ViewProperties.VALUE) %>"><bean:message key="BOOK_VOUCHER_LIST.LABEL.QUOTATION"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookVoucherListForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_BILL_TOTAL, ViewProperties.VALUE) %>"><bean:message key="BOOK_VOUCHER_LIST.LABEL.BILL_TOTAL"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookVoucherListForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_ACKNOWLEDGMENT, ViewProperties.VALUE) %>"><bean:message key="BOOK_VOUCHER_LIST.LABEL.ACKNOWLEDGMENT"/></html:radio></td>
</tr>
<tr>
<td class="td02" colspan="6"><html:radio property="dispTarget" name="BookVoucherListForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_ORDER, ViewProperties.VALUE) %>"><bean:message key="BOOK_VOUCHER_LIST.LABEL.ORDER"/></html:radio></td>
</tr>
</logic:equal>

