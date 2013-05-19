<%@page import="master.customer.bean.MCustomerClassBean"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<body>

<h5>◆<bean:message key="CUSTOMER_CLASS_UPDATE.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/CustomerClassUpdateAction">
<html:hidden property="updDate" name="CustomerClassForm"/>

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="CUSTOMER_CLASS_UPDATE.LABEL.CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<logic:empty name="CustomerClassListForm" property="sel">
<html:text property="code" name="CustomerClassForm" size="8" maxlength="4" style="ime-mode: disabled;"/>
</logic:empty>
<logic:notEmpty name="CustomerClassListForm" property="sel">
<bean:write property="code" name="CustomerClassForm"/>
<html:hidden property="code" name="CustomerClassForm"/>
</logic:notEmpty>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_CLASS_UPDATE.LABEL.NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="CustomerClassForm" size="40" maxlength="20" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_CLASS_UPDATE.LABEL.KANA"/></td>
<td class="td02"><html:text property="kana" name="CustomerClassForm" size="40" maxlength="20" /></td>
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
