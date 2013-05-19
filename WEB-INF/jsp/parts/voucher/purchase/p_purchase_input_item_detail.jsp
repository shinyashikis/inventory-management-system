<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script>var check = true;</script>

<!-- ↓↓↓金額↓↓↓ -->
<div>
<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.TAX_EXCLUDED_AMOUNT"/></td>
<td class="td02" style="width:90px;" id="taxExcludedAmount"><bean:write property="taxExcludedAmount" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.TAX"/></td>
<td class="td02" style="width:90px;" id="tax"><bean:write property="tax" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.TAX_INCLUDED_AMOUNT"/></td>
<td class="td02" style="width:90px;" id="taxIncludedAmount"><bean:write property="taxIncludedAmount" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"></td>
<td class="td02" style="width:90px;" id="grossProfit"></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DISCOUNT"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02" style="width:90px;"><html:text styleId="discount" property="discount" name="PurchaseForm" size="15" maxlength="11" style="ime-mode: disabled;" onblur="if(!checkDiscount(this.value)) { this.focus(); }"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.AMOUNT"/></td>
<td class="td02" style="width:90px;" id="amount"><bean:write property="amount" name="VoucherBean"/></td>
</tr>

</table>
<!-- ↑↑↑金額↑↑↑ -->

<br>

<!-- ↓↓↓商品↓↓↓ -->
<table class="table01" cellpadding="3" id="itemTable">
<tr>
<td class="td02" colspan="10">
<input type="button" value='<bean:message key="VOUCHER_INPUT.LABEL.ITEM"/>' onclick="openItemDialog('');">
<html:button property="delItem" onclick="deleteItem();"><bean:message key="COMMON.BTN.DELETE"/></html:button>
</td>
</tr>

<tbody id="itemTbody">
<th class="th01"><html:link href="javascript:void(0);" onclick="changeAllCheckBox(check); check=!check;">▼</html:link></th>
<th class="th01" style="height:30px; width:70px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_CD"/><bean:message key="COMMON.LABEL.HISSU"/></th>
<th class="th01" style="height:30px; width:120px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_NAME"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_KIKAKU"/></th>
<th class="th01" style="height:30px; width:40px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_COUNT"/><bean:message key="COMMON.LABEL.HISSU"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_UNIT"/></th>
<th class="th01" style="height:30px; width:60px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_UNIT_PRICE"/><bean:message key="COMMON.LABEL.HISSU"/></th>
<th class="th01" style="height:30px; width:60px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_PRICE"/></th>
<th class="th01" style="height:30px; width:120px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_MEMO"/></th>
<th class="th01" style="height:30px; width:60px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_PURCHASE_PRICE"/></th>

<logic:iterate id="detail" property="detailList" name="PurchaseForm" indexId="idx">
<tr>
<td class="td02"><input type="checkbox" name='detail[<bean:write name="idx" />].itemIndex' value='<bean:write name="idx" />'/></td>
<td class="td02"><input type="text" name='detail[<bean:write name="idx" />].itemCode' value='<bean:write name="detail" property="itemCode" />' style="ime-mode: disabled;" maxlength="10" onblur='if(!checkItemCode(this.value)) { this.focus(); } else { searchItem(<bean:write name="idx" />,this.value);}'/></td>
<td class="td02"><bean:write name="detail" property='itemName' /></td>
<td class="td02"><bean:write name="detail" property='itemKikaku' /></td>
<td class="td02"><input type="text" name='detail[<bean:write name="idx" />].itemCount' value='<bean:write name="detail" property="itemCount" />' style="ime-mode: disabled;" maxlength="4" onblur="if(!checkItemCount(this.value)) { this.focus(); }"/></td>
<td class="td02"><bean:write name="detail" property='itemUnit' /></td>
<td class="td02"><input type="text" name='detail[<bean:write name="idx" />].itemUnitPrice' value='<bean:write name="detail" property="itemUnitPrice" />' style="ime-mode: disabled;" maxlength="14" onblur="if(!checkItemUnitPrice(this.value)) { this.focus(); }"/></td>
<td class="td02"><bean:write name="detail" property='itemPrice' /></td>
<td class="td02"><input type="text" name='detail[<bean:write name="idx" />].itemMemo' value='<bean:write name="detail" property="itemMemo" />' maxlength="20"/></td>
<td class="td02"><bean:write name="detail" property='itemPurchasePrice' /></td>
</tr>
</logic:iterate>
</tbody>
</table>
</div>
<!-- ↑↑↑商品↑↑↑ -->
