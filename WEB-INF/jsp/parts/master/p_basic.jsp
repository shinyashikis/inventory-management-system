<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<body onload="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))">

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="BASIC.VIEW"/>
</div>

<h5>◆<bean:message key="BASIC.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/BasicAction">

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="BASIC.LABEL.CAMPANY"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.POSTCODE"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<html:text property="postCode1" name="BasicForm" size="3" maxlength="3" style="ime-mode: disabled;" />-
<html:text property="postCode2" name="BasicForm" size="4" maxlength="4" style="ime-mode: disabled;" />
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.ADDR1"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="addr1" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.ADDR2"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="addr2" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.TEL"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="tel" name="BasicForm" size="20" maxlength="15"  style="ime-mode: disabled;" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.FAX"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="fax" name="BasicForm" size="20" maxlength="15" style="ime-mode: disabled;" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.BANK"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="bank" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.BRANCH"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="branch" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.ACCOUNT_NO"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="accountNo" name="BasicForm" size="80" maxlength="40" style="ime-mode: disabled;"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.ACCOUNT_NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="accountName" name="BasicForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.KISYU"/></td>
<td class="td02">
<html:select property="kisyuYear" name="BasicForm">
<%
for (int i = 1970; i <= 2050; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.YEAR"/>
<html:select property="kisyuMonth" name="BasicForm">
<%
//for (int i = 1; i <= 12; i++) {
for (int i = 4; i <= 4; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.MONTH"/>
<bean:write name="BasicForm" property="kisyuDate" />
<html:hidden name="BasicForm" property="kisyuDate"/>
<bean:message key="COMMON.LABEL.DATE"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.KIMATSU_DATE"/></td>
<td class="td02">
<bean:write name="BasicForm" property="kimatsuYear" />
<bean:message key="COMMON.LABEL.YEAR"/>
<bean:write name="BasicForm" property="kimatsuMonth" />
<bean:message key="COMMON.LABEL.MONTH"/>
<bean:write name="BasicForm" property="kimatsuDate" />
<bean:message key="COMMON.LABEL.DATE"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.KESSAN"/></td>
<td class="td02">
<html:select property="kessan" name="BasicForm">
<%
for (int i = 1; i <= 12; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.MONTH"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.TAXVAL"/></td>
<td class="td02"><html:text property="taxVal" name="BasicForm" size="5" maxlength="5" style="ime-mode: disabled;"></html:text>%</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.TAX"/></td>
<td class="td02">
<html:radio property="tax" name="BasicForm" value="0" onclick="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))"><bean:message key="BASIC.LABEL.KAZEI"/></html:radio>
<html:radio property="tax" name="BasicForm" value="1" onclick="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))"><bean:message key="BASIC.LABEL.HIKAZEI"/></html:radio>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.CALC"/></td>
<td class="td02">
<html:radio property="calc" name="BasicForm" value="0"><bean:message key="BASIC.LABEL.UTHIZEI"/></html:radio>
<html:radio property="calc" name="BasicForm" value="1"><bean:message key="BASIC.LABEL.SOTOZEI"/></html:radio>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="BASIC.LABEL.CALCHASU"/></td>
<td class="td02">
<html:radio property="calcHasu" name="BasicForm" value="0"><bean:message key="BASIC.LABEL.SHISYA_GONYU"/></html:radio>
<html:radio property="calcHasu" name="BasicForm" value="1"><bean:message key="BASIC.LABEL.KIRIAGE"/></html:radio>
<html:radio property="calcHasu" name="BasicForm" value="2"><bean:message key="BASIC.LABEL.KIRISUTE"/></html:radio>
</td>
</tr>

</table>

<br>

<div>
<html:submit property="regist"><bean:message key="COMMON.BTN.REGIST"/></html:submit>
<html:reset><bean:message key="COMMON.BTN.CANCEL"/></html:reset>
<html:submit property="back"><bean:message key="COMMON.BTN.BACK"/></html:submit>
</div>

<html:hidden property="updDate" name="BasicForm"/>
</div>
</html:form>

</body>
