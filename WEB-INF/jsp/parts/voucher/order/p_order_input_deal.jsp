<%@ page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!-- ↓↓↓得意先↓↓↓ -->
<div>
<table class="table01" cellpadding="3">

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_NAME"/></td>
<td class="td02" style="width:90px;" id="dealName"><bean:write property="dealBean.dealName" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02" style="width:90px;"><html:text styleId='dealCode' property='dealCode' name='OrderForm' size='20' maxlength='10' onblur='if(!checkDealCode(this.value)) { this.focus(); } else { searchDeal(this.value); }'/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_CLASS_CD"/></td>
<td class="td02" style="width:90px;" id="dealClassCode"><bean:write property="dealBean.dealClassCode" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_CLASS_NAME"/></td>
<td class="td02" style="width:90px;" id="dealClassName"><bean:write property="dealBean.dealClassName" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_STAFF"/></td>
<td class="td02" style="border-right: none;" id="dealStaff"><bean:write property="dealBean.dealStaff" name="VoucherBean"/><td>
<td class="td02" id="dealStaffKeisyo"><bean:write property="dealBean.dealStaffKeisyo" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_BUSYO"/></td>
<td class="td02" style="width:90px;" colspan="3" id="dealBusyo"><bean:write property="dealBean.dealBusyo" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.TEL"/></td>
<td class="td02" style="width:90px;" id="tel"><bean:write property="dealBean.tel" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.POSTCODE"/></td>
<td class="td02" style="width:90px;" id="postCode"><bean:write property="dealBean.postCode" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.ADDR1"/></td>
<td class="td02" colspan="3" id="addr1"><bean:write property="dealBean.addr1" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.ADDR2"/></td>
<td class="td02" colspan="3" id="addr2"><bean:write property="dealBean.addr2" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.SHIMEBI"/></td>
<td class="td02" style="width:90px;" id="shimebi"><bean:write property="dealBean.shimebi" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.KESSAI"/></td>
<td class="td02" style="width:90px;" id="kessai"><bean:write property="dealBean.kessai" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.DEAL_DIVISION"/></td>
<td class="td02" style="width:90px;" id="dealDivision"><bean:write property="dealBean.dealDivision" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.CALC"/></td>
<td class="td02" style="width:90px;" id="tax"><bean:write property="dealBean.tax" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td02" colspan="4" style=" border-top: none; border-bottom: none; border-left: none; border-right: none;">&nbsp;</td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.STAFF"/></td>
<td class="td02" style="width:90px;" id="staff"><bean:write property="dealBean.staff" name="VoucherBean"/></td>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.STAFF_CD"/></td>
<td class="td02" style="width:90px;" id="staffCode"><bean:write property="dealBean.staffCode" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="VOUCHER_INPUT.LABEL.BUSYO"/></td>
<td class="td02" colspan="3" id="busyo"><bean:write property="dealBean.busyo" name="VoucherBean"/></td>
</tr>

<tr>
<td class="td02" colspan="4">
<input type="button" id="deal" value="<bean:message key="COMMON.BTN.DEAL"/>" onclick="openDealDialog('');">
<html:radio styleId="dealKindCustomer" property="dealKind" name="OrderForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.VALUE) %>" disabled="true"><bean:message key="COMMON.LABEL.CUSTOMER"/></html:radio>
<html:radio styleId="dealKindSupplier" property="dealKind" name="OrderForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.VALUE) %>"><bean:message key="COMMON.LABEL.SUPPLIER"/></html:radio>
</td>
</tr>

</table>
</div>
<!-- ↑↑↑得意先↑↑↑ -->

