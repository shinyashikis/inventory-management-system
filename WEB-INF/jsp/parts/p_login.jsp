<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<body>

<div class="msg" align="center">
<html:messages id="msg" message="true"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg" align="center">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/LoginAction">
<div align="center">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="LOGIN.LABEL.USER"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="user" size="20" maxlength="20" style="ime-mode: disabled;" /></td>
</tr>
<tr>
<td class="td01"><bean:message key="LOGIN.LABEL.PSWD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:password property="pswd" size="20" maxlength="20" /></td>
</tr>
</table>

<br>

<div>
<html:submit property="login"><bean:message key="LOGIN.BTN.LOGIN"/></html:submit>
<html:reset><bean:message key="COMMON.BTN.CANCEL"/></html:reset>
</div>

</div>
</html:form>

</body>
