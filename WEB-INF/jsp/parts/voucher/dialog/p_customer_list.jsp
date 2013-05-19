<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
var dealArray = new Array();
var dealInfo;
function getDealInfo(code) {
	for (i=0;i<dealArray.length;i++) {
		if (dealArray[i]["code"] == code){
			dealInfo = dealArray[i];
			break;
		}
	}
}
</script>

<body>

<h5>◆<bean:message key="CUSTOMER_LIST.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/VoucherCustomerListAction">

<div align="left">

<div>
<html:button property="close" onclick="closeListDialog(dealInfo);"><bean:message key="COMMON.BTN.CLOSE"/></html:button>
</div>

<br>

<table class="table01" cellpadding="3" >
<tr>
<th class="th01"></th>
<th class="th01"><bean:message key="CUSTOMER_LIST.LABEL.CD"/></th>
<th class="th01"><bean:message key="CUSTOMER_LIST.LABEL.NAME"/></th>
<th class="th01"><bean:message key="CUSTOMER_LIST.LABEL.KANA"/></th>
</tr>
<logic:notEmpty name="VoucherCustomerListForm">
<script type="text/javascript">
var cnt=0;
</script>

<logic:iterate id="map" name="VoucherCustomerListForm" property="customerMap">
<tr>
<td class="td02"><input type="radio" name="sel" value="<bean:write name='map' property='key'/>" onclick="getDealInfo(this.value)"></td>
<td class="td02"><bean:write name="map" property="key"/></td>
<td class="td02"><bean:write name="map" property="value.name"/></td>
<td class="td02"><bean:write name="map" property="value.kana"/></td>
</tr>

<script type="text/javascript">
dealArray[cnt] = new Array();
dealArray[cnt]["code"]='<bean:write name="map" property="key"/>';
dealArray[cnt]["name"]='<bean:write name="map" property="value.name"/>';
dealArray[cnt]["classCode"]='<bean:write name="map" property="value.classCode"/>';
dealArray[cnt]["className"]='<bean:write name="map" property="value.className"/>';
dealArray[cnt]["addr1"]='<bean:write name="map" property="value.addr1"/>';
dealArray[cnt]["addr2"]='<bean:write name="map" property="value.addr2"/>';
dealArray[cnt]["postCode1"]='<bean:write name="map" property="value.postCode1"/>';
dealArray[cnt]["postCode2"]='<bean:write name="map" property="value.postCode2"/>';
dealArray[cnt]["tel"]='<bean:write name="map" property="value.tel"/>';
dealArray[cnt]["customerStaffSei"]='<bean:write name="map" property="value.customerStaffSei"/>';
dealArray[cnt]["customerStaffName"]='<bean:write name="map" property="value.customerStaffName"/>';

var keisyoAry = new Array();
keisyoAry[0] = '<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_ONCYU) %>';
keisyoAry[1] = '<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_SAMA) %>';
keisyoAry[2] = '<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_TONO) %>';
dealArray[cnt]["keisyo"] = getViewPropertiesValue(
		keisyoAry,'<%= ViewProperties.DISP_VALUE %>','<%= ViewProperties.VALUE %>','<bean:write name="map" property="value.keisyo"/>');

dealArray[cnt]["busyo"]='<bean:write name="map" property="value.busyo"/>';

var dealDivisionAry = new Array();
dealDivisionAry[0] = '<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH) %>';
dealDivisionAry[1] = '<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_KAKE) %>';
dealArray[cnt]["dealDivision"] = getViewPropertiesValue(
		dealDivisionAry,'<%= ViewProperties.DISP_VALUE %>','<%= ViewProperties.VALUE %>','<bean:write name="map" property="value.dealDivision"/>');
dealArray[cnt]["dealDivisionCode"] = '<bean:write name="map" property="value.dealDivision"/>';

dealArray[cnt]["kessai"]='<bean:write name="map" property="value.kessai"/>';
dealArray[cnt]["salePriceDivision"]='<bean:write name="map" property="value.salePriceDivision"/>';
dealArray[cnt]["calcCalcHasu"]='';
dealArray[cnt]["shimebi"]='<bean:write name="map" property="value.shimebi"/>';
dealArray[cnt]["staffCode"]='<bean:write name="map" property="value.staffCode"/>';
dealArray[cnt]["staffSei"]='<bean:write name="map" property="value.staffSei"/>';
dealArray[cnt]["staffName"]='<bean:write name="map" property="value.staffName"/>';
dealArray[cnt]["staffBusyo"]='<bean:write name="map" property="value.staffBusyo"/>';
cnt++;
</script>
</logic:iterate>
</logic:notEmpty>
</table>

</div>
</html:form>

</body>
