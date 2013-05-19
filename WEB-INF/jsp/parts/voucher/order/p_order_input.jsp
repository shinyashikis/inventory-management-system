<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!-- ↓↓↓伝票↓↓↓ -->
<div>
<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.VOUCHER_NO"/></td>
<td class="td02" style="width:90px;"><bean:write property="voucherNo" name="VoucherBean"/></td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.VOUCHER_DATE"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02" style="width:90px;">
<html:text styleId="voucherDate" property="voucherDate" name="OrderForm" size="20" maxlength="10" style="ime-mode: disabled;" onblur="if(!checkVoucherDate(this.value)) { this.focus(); }"/>
</td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.VOUCHER_NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02" style="width:90px;"><html:text styleId="voucherName" property="voucherName" name="OrderForm" size="80" maxlength="40" onblur="if(!checkVoucherName(this.value)) { this.focus(); }"/></td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.PAYMENT_LIMIT"/></td>
<td class="td02" style="width:90px;"><html:text property="paymentLimit" name="OrderForm" size="40" maxlength="20"/></td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.PAYMENT_PLACE"/></td>
<td class="td02" style="width:90px;"><html:text property="paymentPlace" name="OrderForm" size="40" maxlength="20"/></td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.PAYMENT_CONDITION"/></td>
<td class="td02" style="width:90px;"><html:text property="paymentCondition" name="OrderForm" size="40" maxlength="20"/></td>
</tr>
<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.EXPIRATION_DATE"/></td>
<td class="td02" style="width:90px;"><html:text property="expirationDate" name="OrderForm" size="40" maxlength="20"/></td>
</tr>
<tr>
<td class="td01"></td>
<td class="td02" style="width:90px;"></td>
</tr>
<tr>
<td class="td01" rowspan="5"><bean:message key="VOUCHER_INPUT.LABEL.MEMO"/></td>
<td class="td02" rowspan="5"><html:textarea property="memo" name="OrderForm" rows="8" cols="50"/></td>
</tr>
</table>
</div>
<!-- ↑↑↑伝票↑↑↑ -->

