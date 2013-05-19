<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
<td class="td02" style="width:90px;"><html:text styleId="discount" property="discount" name="BillForm" size="15" maxlength="11" style="ime-mode: disabled;" onblur="if(!checkDiscount(this.value)) { this.focus(); }"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.AMOUNT"/></td>
<td class="td02" style="width:90px;" id="amount"><bean:write property="amount" name="VoucherBean"/></td>
</tr>

</table>
<!-- ↑↑↑金額↑↑↑ -->

<br>

<!-- ↓↓↓商品↓↓↓ -->
<table class="table01" cellpadding="3" id="itemTable">
<tbody id="itemTbody">
<th class="th01" style="height:30px; width:70px;"><bean:message key="VOUCHER_INPUT.LABEL.VOUCHER_DATE"/></th>
<th class="th01" style="height:30px; width:70px;"><bean:message key="VOUCHER_INPUT.LABEL.VOUCHER_NO"/></th>
<th class="th01" style="height:30px; width:70px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_CD"/></th>
<th class="th01" style="height:30px; width:120px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_NAME"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_KIKAKU"/></th>
<th class="th01" style="height:30px; width:40px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_COUNT"/></th>
<th class="th01" style="height:30px; width:90px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_UNIT"/></th>
<th class="th01" style="height:30px; width:60px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_UNIT_PRICE"/></th>
<th class="th01" style="height:30px; width:60px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_PRICE"/></th>
<th class="th01" style="height:30px; width:120px;"><bean:message key="VOUCHER_INPUT.LABEL.ITEM_MEMO"/></th>

<logic:iterate id="detailBean" property="detailList" name="VoucherBean">
<tr>
<td class="td02"><bean:write name="detailBean" property='salesDate' /></td>
<td class="td02"><bean:write name="detailBean" property='salesNo' /></td>
<td class="td02"><bean:write name="detailBean" property='itemCode' /></td>
<td class="td02"><bean:write name="detailBean" property='itemName' /></td>
<td class="td02"><bean:write name="detailBean" property='itemKikaku' /></td>
<td class="td02"><bean:write name="detailBean" property='itemCount' /></td>
<td class="td02"><bean:write name="detailBean" property='itemUnit' /></td>
<td class="td02"><bean:write name="detailBean" property='itemUnitPrice' /></td>
<td class="td02"><bean:write name="detailBean" property='itemPrice' /></td>
<td class="td02"><bean:write name="detailBean" property='itemMemo' /></td>
</tr>
</logic:iterate>
</tbody>
</table>
</div>
<!-- ↑↑↑商品↑↑↑ -->
