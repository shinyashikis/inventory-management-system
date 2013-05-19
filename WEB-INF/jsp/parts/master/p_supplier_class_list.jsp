<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script>
var check = true;
var array = new Array();
</script>

<body onload="changeBtnStatus();">

<h5>◆<bean:message key="SUPPLIER_CLASS_LIST.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/SupplierClassListAction">

<div align="left">

<div>
<html:submit property="add" styleId="addBtn"><bean:message key="COMMON.BTN.REGIST"/></html:submit>
<html:submit property="update" styleId="updBtn" disabled="true"><bean:message key="COMMON.BTN.UPDATE"/></html:submit>
<html:submit property="delete" styleId="delBtn" disabled="true" onclick="return confirm('削除しますか？');"><bean:message key="COMMON.BTN.DELETE"/></html:submit>
<html:button property="close" onclick="closeClassListDialog(array);"><bean:message key="COMMON.BTN.CLOSE"/></html:button>

</div>

<br>

<table class="table01" cellpadding="3">
<tr>
<th class="th01"><html:link href="#" onclick="changeAllCheckBox(check); check=!check; changeBtnStatus();">▼</html:link></th>
<th class="th01"><bean:message key="SUPPLIER_CLASS_LIST.LABEL.CD"/></th>
<th class="th01"><bean:message key="SUPPLIER_CLASS_LIST.LABEL.NAME"/></th>
<th class="th01"><bean:message key="SUPPLIER_CLASS_LIST.LABEL.KANA"/></th>
</tr>
<logic:notEmpty name="SupplierClassListForm">
<script>var i = 0;</script>
<logic:iterate id="map" name="SupplierClassListForm" property="supplierClassMap">
<tr>
<td class="td02"><html:multibox name="SupplierClassListForm" property="sel" onclick="changeBtnStatus();"><bean:write name="map" property="key"></bean:write></html:multibox></td>
<td class="td02"><bean:write name="map" property="key"/></td>
<td class="td02"><bean:write name="map" property="value.name"/></td>
<td class="td02"><bean:write name="map" property="value.kana"/></td>
</tr>
<script>
array[i] = new Array();
array[i][0]=<bean:write name="map" property="key"/>;
array[i][1]='<bean:write name="map" property="value.name"/>';
i++;
</script>
</logic:iterate>
</logic:notEmpty>
</table>

<br>


</div>
</html:form>

</body>
