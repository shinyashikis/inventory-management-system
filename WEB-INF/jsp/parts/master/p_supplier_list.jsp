<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script>var check = true;</script>

<body onload="changeBtnStatus();">

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="SUPPLIER_LIST.VIEW"/>
</div>

<h5>◆<bean:message key="SUPPLIER_LIST.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/SupplierListAction">

<div align="left">

<div>
<html:submit property="add" styleId="addBtn"><bean:message key="COMMON.BTN.REGIST"/></html:submit>
<html:submit property="update" styleId="updBtn" disabled="true"><bean:message key="COMMON.BTN.UPDATE"/></html:submit>
<html:submit property="delete" styleId="delBtn" disabled="true" onclick="return confirm('削除しますか？');"><bean:message key="COMMON.BTN.DELETE"/></html:submit>
<html:submit property="back"><bean:message key="COMMON.BTN.BACK"/></html:submit>
</div>

<br>

<table class="table01" cellpadding="3">
<tr>
<th class="th01"><html:link href="#" onclick="changeAllCheckBox(check); check=!check; changeBtnStatus();">▼</html:link></th>
<th class="th01"><bean:message key="SUPPLIER_LIST.LABEL.CD"/></th>
<th class="th01"><bean:message key="SUPPLIER_LIST.LABEL.NAME"/></th>
<th class="th01"><bean:message key="SUPPLIER_LIST.LABEL.KANA"/></th>
</tr>
<logic:notEmpty name="SupplierListForm">
<logic:iterate id="map" name="SupplierListForm" property="supplierMap">
<tr>
<td class="td02"><html:multibox name="SupplierListForm" property="sel" onclick="changeBtnStatus();"><bean:write name="map" property="key"></bean:write></html:multibox></td>
<td class="td02"><bean:write name="map" property="key"/></td>
<td class="td02"><bean:write name="map" property="value.name"/></td>
<td class="td02"><bean:write name="map" property="value.kana"/></td>
</tr>
</logic:iterate>
</logic:notEmpty>
</table>

<br>


</div>
</html:form>

</body>
