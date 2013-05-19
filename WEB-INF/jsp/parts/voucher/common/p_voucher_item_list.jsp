<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
function closeDialog() {

	var cnt = getCheckBoxCnt(true);
	if(cnt==0){
		self.close();
		return;
	}

	var form = document.forms[0];
	form.action = '<%= request.getContextPath() %>/VoucherItemListAction.do?close';
	form.submit();
	self.close();
	window.dialogArguments.redispDetail();
}
</script>

<body>

<h5>◆<bean:message key="ITEM_LIST.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/VoucherItemListAction">

<div align="left">

<div>
<html:button property="close" onclick="closeDialog();"><bean:message key="COMMON.BTN.CLOSE"/></html:button>
</div>

<br>

<table class="table01" cellpadding="2" >
<tr>
<th class="th01"></th>
<th class="th01"><bean:message key="ITEM_LIST.LABEL.CD"/></th>
<th class="th01"><bean:message key="ITEM_LIST.LABEL.NAME"/></th>
</tr>
<logic:notEmpty name="DetailBeanMap">
<logic:iterate id="map" name="DetailBeanMap">
<tr>
<td class="td02"><html:multibox name="VoucherItemListForm" property="sel" ><bean:write name="map" property="key"></bean:write></html:multibox></td>
<td class="td02"><bean:write name="map" property="key"/></td>
<td class="td02"><bean:write name="map" property="value.itemName"/></td>
</tr>
</logic:iterate>
</logic:notEmpty>
</table>

</div>
</html:form>

</body>
