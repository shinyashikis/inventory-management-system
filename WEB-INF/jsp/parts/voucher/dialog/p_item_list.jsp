<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
var itemArray = new Array();
var itemInfo;
function getItemInfo(code) {
	for (i=0;i<itemArray.length;i++) {
		if (itemArray[i][0] == code){
			itemInfo = itemArray[i];
			break;
		}
	}
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
<html:button property="close" onclick="closeListDialog(itemInfo);"><bean:message key="COMMON.BTN.CLOSE"/></html:button>
</div>

<br>

<table class="table01" cellpadding="3" >
<tr>
<th class="th01"></th>
<th class="th01"><bean:message key="ITEM_LIST.LABEL.CD"/></th>
<th class="th01"><bean:message key="ITEM_LIST.LABEL.NAME"/></th>
<th class="th01"><bean:message key="ITEM_LIST.LABEL.KANA"/></th>
</tr>
<logic:notEmpty name="VoucherItemListForm">
<script type="text/javascript">
var cnt=0;
</script>

<logic:iterate id="map" name="VoucherItemListForm" property="itemMap">
<tr>
<td class="td02"><input type="radio" name="sel" value="<bean:write name='map' property='key'/>" onclick="getItemInfo(this.value)"></td>
<td class="td02"><bean:write name="map" property="key"/></td>
<td class="td02"><bean:write name="map" property="value.name"/></td>
<td class="td02"><bean:write name="map" property="value.kana"/></td>
</tr>

<script type="text/javascript">
itemArray[cnt] = new Array();
itemArray[cnt][0]='<bean:write name="map" property="key"/>';
itemArray[cnt][1]='<bean:write name="map" property="value.name"/>';
itemArray[cnt][2]='<bean:write name="map" property="value.kikaku"/>';
itemArray[cnt][3]='<bean:write name="map" property="value.unit"/>';
itemArray[cnt][4]='<bean:write name="map" property="value.standardPrice"/>';
itemArray[cnt][5]='<bean:write name="map" property="value.sellingPrice2"/>';
itemArray[cnt][6]='<bean:write name="map" property="value.sellingPrice3"/>';
itemArray[cnt][7]='<bean:write name="map" property="value.sellingPrice4"/>';
itemArray[cnt][8]='<bean:write name="map" property="value.sellingPrice5"/>';
itemArray[cnt][9]='<bean:write name="map" property="value.price"/>';
cnt++;
</script>
</logic:iterate>
</logic:notEmpty>
</table>

</div>
</html:form>

</body>
