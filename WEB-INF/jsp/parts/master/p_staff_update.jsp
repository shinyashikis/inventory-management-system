<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<body>

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="STAFF_LIST.VIEW"/> > <bean:message key="STAFF_UPDATE.VIEW"/>
</div>

<h5>◆<bean:message key="STAFF_UPDATE.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/StaffUpdateAction">
<html:hidden property="updDate" name="StaffForm"/>

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<logic:empty name="StaffListForm" property="sel">
<html:text property="code" name="StaffForm" size="8" maxlength="4" style="ime-mode: disabled;"/>
</logic:empty>
<logic:notEmpty name="StaffListForm" property="sel">
<bean:write property="code" name="StaffForm"/>
<html:hidden property="code" name="StaffForm"/>
</logic:notEmpty>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.SEI"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<html:text property="sei" name="StaffForm" size="80" maxlength="40"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="StaffForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.SEI_KANA"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="seiKana" name="StaffForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.NAME_KANA"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="nameKana" name="StaffForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="STAFF_UPDATE.LABEL.BUSYO"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="busyo" name="StaffForm" size="80" maxlength="40" /></td>
</tr>

</table>

<br>

<div>
<html:submit property="update"><bean:message key="COMMON.BTN.UPDATE"/></html:submit>
<html:reset><bean:message key="COMMON.BTN.CANCEL"/></html:reset>
<html:submit property="back"><bean:message key="COMMON.BTN.BACK"/></html:submit>
</div>

</div>
</html:form>

</body>
