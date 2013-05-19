<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ page import="prop.ViewProperties"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
var itemAddErrMsg = '<bean:message key="QUOTATION_DETAIL_UPDATE.ERRMSG.DEAL.REQUIRED"/>';
var check = true;
var itemListActionUrl = '<%= request.getContextPath() %>/VoucherItemListAction.do';
var itemButtonName = '<bean:message key="QUOTATION_INPUT.LABEL.ITEM"/>';
var priceDivisions = new Array();
priceDivisions[0] = <%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_1, ViewProperties.VALUE) %>;
priceDivisions[1] = <%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_2, ViewProperties.VALUE) %>;
priceDivisions[2] = <%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_3, ViewProperties.VALUE) %>;
priceDivisions[3] = <%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_4, ViewProperties.VALUE) %>;
priceDivisions[4] = <%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_5, ViewProperties.VALUE) %>;
</script>

<!-- ↓↓↓金額↓↓↓ -->
<div>
<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="QUOTATION_INPUT.LABEL.TAX_EXCLUDED_AMOUNT"/></td>
<td class="td02" style="width:90px;" id="taxExcludedAmount"><bean:write property="voucherBean.taxExcludedAmount" name="VoucherForm"/></td>
<td class="td01"><bean:message key="QUOTATION_INPUT.LABEL.TAX"/></td>
<td class="td02" style="width:90px;" id="tax"><bean:write property="voucherBean.tax" name="VoucherForm"/></td>
<td class="td01"><bean:message key="QUOTATION_INPUT.LABEL.TAX_INCLUDED_AMOUNT"/></td>
<td class="td02" style="width:90px;" id="taxIncludedAmount"><bean:write property="voucherBean.taxIncludedAmount" name="VoucherForm"/></td>
</tr>
<tr>
<td class="td01">
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_QUOTATION, ViewProperties.VALUE) %>">
<bean:message key="QUOTATION_INPUT.LABEL.GROSS_PROFIT"/>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_ORDER, ViewProperties.VALUE) %>">
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_SALES, ViewProperties.VALUE) %>">
<bean:message key="QUOTATION_INPUT.LABEL.GROSS_PROFIT"/>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_PURCHASE, ViewProperties.VALUE) %>">
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_BILL, ViewProperties.VALUE) %>">
</logic:equal>
</td>

<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_QUOTATION, ViewProperties.VALUE) %>">
<td class="td02" style="width:90px;" id="grossProfit"><bean:write property="voucherBean.grossProfit" name="VoucherForm"/></td>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_ORDER, ViewProperties.VALUE) %>">
<td class="td02" style="width:90px;"></td>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_SALES, ViewProperties.VALUE) %>">
<td class="td02" style="width:90px;" id="grossProfit"><bean:write property="voucherBean.grossProfit" name="VoucherForm"/></td>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_PURCHASE, ViewProperties.VALUE) %>">
<td class="td02" style="width:90px;"></td>
</logic:equal>
<logic:equal property="voucherBean.voucherKind" name="VoucherForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.VOUCHER_KIND_BILL, ViewProperties.VALUE) %>">
<td class="td02" style="width:90px;"></td>
</logic:equal>

<td class="td01"><bean:message key="QUOTATION_INPUT.LABEL.DISCOUNT"/></td>
<td class="td02" style="width:90px;"><html:text styleId="discount" property="voucherBean.discount" name="VoucherForm" size="15" maxlength="11" style="ime-mode: disabled;" onblur="if(moneyOnly(this.value)) { calcItemAmountPrice(tempMaxSeq,taxVal); }"/></td>
<td class="td01"><bean:message key="QUOTATION_INPUT.LABEL.AMOUNT"/></td>
<td class="td02" style="width:90px;" id="amount"><bean:write property="voucherBean.amount" name="VoucherForm"/></td>
</tr>
<tr>
<td class="td02" colspan="6">
<html:button property="calcItem" onclick="calcItemAmountPrice(tempMaxSeq,taxVal);"><bean:message key="COMMON.BTN.CALC_AMMOUNT"/></html:button>
</td>
</tr>

</table>
<!-- ↑↑↑金額↑↑↑ -->

<br>
<!-- ↓↓↓商品↓↓↓ -->
<table class="table01" cellpadding="3" id="itemTable">
<tr>
<td class="td02" colspan="11">
<html:button property="addItem" onclick="if(isMaxItem()){return;} if(document.getElementById('hiddenDealCode').value == ''){alert(itemAddErrMsg); return;} tempMaxSeq++; addItemTableRow(tempMaxSeq, itemListActionUrl,itemButtonName, priceDivisions);"><bean:message key="COMMON.BTN.ADDROW"/></html:button>
<html:button property="delItem" onclick="deleteItemTableRow(existingMaxSeq,false); calcItemAmountPrice(tempMaxSeq,taxVal);"><bean:message key="COMMON.BTN.DELROW"/></html:button>
<html:button property="clearItem" onclick="clearItemTableRow(); calcItemAmountPrice(tempMaxSeq,taxVal);"><bean:message key="COMMON.BTN.CLEARROW"/></html:button>
</td>
</tr>

<tbody id="itemTbody">
<th class="th01"><html:link href="javascript:void(0);" onclick="changeAllCheckBox(check); check=!check;">▼</html:link></th>
<th class="th01" style="height:30px;"></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_CD"/></th>
<th class="th01" style="height:30px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_NAME"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_KIKAKU"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_COUNT"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_UNIT"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_UNIT_PRICE"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_PRICE"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_MEMO"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="QUOTATION_INPUT.LABEL.ITEM_PURCHASE_PRICE"/></th>

<logic:iterate id="map" property="voucherBean.detailMap" name="VoucherForm">
<tr>
<td class="td02"><input type="checkbox" id='selItem[<bean:write name="map" property="key"/>]' value='<bean:write name="map" property="key"/>'/></td>
<td class="td02"><input type="button" value='<bean:message key="QUOTATION_INPUT.LABEL.ITEM"/>' onclick='setItemInfo(openListDialog(itemListActionUrl),<bean:write name="map" property="key"/>,priceDivisions);'></td>
<td class="td02" id='itemCode[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemCode"/></td>
<td class="td02" id='itemName[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemName"/></td>
<td class="td02" id='itemKikaku[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemKikaku"/></td>
<td class="td02" ><input type="text" id='itemCount[<bean:write name="map" property="key"/>]' name='voucherBean.detailMap(<bean:write name="map" property="key"/>).itemCount' value='<bean:write name="map" property="value.itemCount"/>' style="ime-mode: disabled;" onblur='calcItemPrice(<bean:write name="map" property="key"/>);' onkeydown="return numberOnly();" maxlength="4"/></td>
<td class="td02" id='itemUnit[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemUnit"/></td>
<td class="td02" id='itemUnitPrice[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemUnitPrice"/></td>
<td class="td02" id='itemPrice[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemPrice"/></td>
<td class="td02" ><input type="text" id='itemMemo[<bean:write name="map" property="key"/>]' name='voucherBean.detailMap(<bean:write name="map" property="key"/>).itemMemo' value='<bean:write name="map" property="value.itemMemo"/>' maxlength="20"/></td>
<td class="td02" id='itemPurchasePrice[<bean:write name="map" property="key"/>]'><bean:write name="map" property="value.itemPurchasePrice"/></td>
</tr>
<input type="hidden" id='hiddenItemCode[<bean:write name="map" property="key"/>]' name='voucherBean.detailMap(<bean:write name="map" property="key"/>).itemCode' value='<bean:write name="map" property="value.itemCode"/>'/>
</logic:iterate>
</tbody>

</table>
</div>
<!-- ↑↑↑商品↑↑↑ -->
