<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="master.item.ItemClassListForm"%>
<%@page import="master.item.bean.MItemClassBean"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<script type="text/javascript">
function init() {
	var updFlg = '<bean:write property="updFlg" name="ItemForm"/>';
	if (updFlg == "true") {
		document.getElementById('kisyuStock').disabled = true;
	}
}
</script>

<body onload="init();">

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="ITEM_LIST.VIEW"/> > <bean:message key="ITEM_UPDATE.VIEW"/>
</div>

<h5>◆<bean:message key="ITEM_UPDATE.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/ItemUpdateAction" onsubmit="document.getElementById('stock').disabled=false;">
<html:hidden property="updDate" name="ItemForm"/>

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<logic:empty name="ItemListForm" property="sel">
<html:text property="code" name="ItemForm" size="20" maxlength="10" style="ime-mode: disabled;"/>
</logic:empty>
<logic:notEmpty name="ItemListForm" property="sel">
<bean:write property="code" name="ItemForm"/>
<html:hidden property="code" name="ItemForm"/>
</logic:notEmpty>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="ItemForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.KANA"/></td>
<td class="td02"><html:text property="kana" name="ItemForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.KIKAKU"/></td>
<td class="td02"><html:text property="kikaku" name="ItemForm" size="80" maxlength="40"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.CLASS_CODE"/></td>
<td class="td02">
<html:select property="classCode" name="ItemForm" styleId="classCode">
<html:option value=""><bean:message key="ITEM_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="ItemClassListForm">

<%
ItemClassListForm itemClassListForm = (ItemClassListForm)request.getSession(false).getAttribute("ItemClassListForm");
Set<Entry<String, MItemClassBean>> set = itemClassListForm.getItemClassMap().entrySet();
Iterator<Entry<String, MItemClassBean>> itr = set.iterator();
while (itr.hasNext()) {
	Entry<String, MItemClassBean> e = itr.next();
%>
<html:option value="<%=e.getKey()%>"><%=e.getValue().getName()%></html:option>
<%
}
%>

</logic:notEmpty>
</html:select>
<html:button property="itemClassUpdate" onclick="<%= \"openClassDialog('\" + request.getContextPath() + \"/ItemClassListAction.do')\" %>"><bean:message key="COMMON.BTN.CLASS_REGIST"/></html:button>
</td>
</tr>


<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.STOCK"/></td>
<td class="td02"><html:text property="stock" name="ItemForm" size="14" disabled="true"></html:text></td>
<html:hidden styleId="hiddenStock" property="stock" name="ItemForm"/>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.PROPER_STOCK"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="properStock" name="ItemForm" size="14" maxlength="10" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.KISYU_STOCK"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="kisyuStock" name="ItemForm" styleId="kisyuStock" size="14" maxlength="10" style="ime-mode: disabled;" onblur="calcStock();"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.KICYU_STOCK_UPDOWN"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="kicyuStockUpDown" name="ItemForm" styleId="kicyuStockUpDown" size="14" maxlength="10" style="ime-mode: disabled;" onblur="calcStock();"></html:text></td>
<html:hidden styleId="hiddenKicyuStockUpDown" property="kicyuStockUpDown" name="ItemForm"/>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.UNIT"/></td>
<td class="td02"><html:text property="unit" name="ItemForm" size="18" maxlength="10"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.PRICE"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="price" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.KISYU_PRICE"/></td>
<td class="td02"><html:text property="kisyuPrice" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.STANDARD_PRICE"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="standardPrice" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.SELLING_PRICE2"/></td>
<td class="td02"><html:text property="sellingPrice2" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.SELLING_PRICE3"/></td>
<td class="td02"><html:text property="sellingPrice3" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.SELLING_PRICE4"/></td>
<td class="td02"><html:text property="sellingPrice4" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.SELLING_PRICE5"/></td>
<td class="td02"><html:text property="sellingPrice5" name="ItemForm" size="18" maxlength="14" style="ime-mode: disabled;"></html:text></td>
</tr>

<tr>
<td class="td01"><bean:message key="ITEM_UPDATE.LABEL.UPD_DATE"/></td>
<td class="td02"><bean:write property="dispUpdDate" name="ItemForm" /></td>
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
