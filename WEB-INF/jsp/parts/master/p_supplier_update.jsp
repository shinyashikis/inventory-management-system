<%@page import="prop.ViewProperties"%>
<%@page import="master.staff.bean.MStaffBean"%>
<%@page import="java.util.List"%>
<%@page import="master.supplier.bean.MSupplierClassBean"%>
<%@page import="master.supplier.SupplierClassListForm"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<body onload="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))">

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="SUPPLIER_LIST.VIEW"/> > <bean:message key="SUPPLIER_UPDATE.VIEW"/>
</div>

<h5>◆<bean:message key="SUPPLIER_UPDATE.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/SupplierUpdateAction">
<html:hidden property="updDate" name="SupplierForm"/>

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<logic:empty name="SupplierListForm" property="sel">
<html:text property="code" name="SupplierForm" size="20" maxlength="10" style="ime-mode: disabled;"/>
</logic:empty>
<logic:notEmpty name="SupplierListForm" property="sel">
<bean:write property="code" name="SupplierForm"/>
<html:hidden property="code" name="SupplierForm"/>
</logic:notEmpty>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="SupplierForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.KANA"/></td>
<td class="td02"><html:text property="kana" name="SupplierForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.CLASS_CODE"/></td>
<td class="td02">
<html:select property="classCode" name="SupplierForm" styleId="classCode">
<html:option value=""><bean:message key="SUPPLIER_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="SupplierClassListForm">

<%
SupplierClassListForm supplierClassListForm = (SupplierClassListForm)request.getSession(false).getAttribute("SupplierClassListForm");
Set<Entry<String, MSupplierClassBean>> set = supplierClassListForm.getSupplierClassMap().entrySet();
Iterator<Entry<String, MSupplierClassBean>> itr = set.iterator();
while (itr.hasNext()) {
	Entry<String, MSupplierClassBean> e = itr.next();
%>
<html:option value="<%=e.getKey()%>"><%=e.getValue().getName()%></html:option>
<%
}
%>

</logic:notEmpty>
</html:select>
<html:button property="supplierClassUpdate" onclick="<%= \"openClassDialog('\" + request.getContextPath() + \"/SupplierClassListAction.do')\" %>"><bean:message key="COMMON.BTN.CLASS_REGIST"/></html:button>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.ADDR1"/></td>
<td class="td02"><html:text property="addr1" name="SupplierForm" size="80" maxlength="40"></html:text></td>
</tr>
<tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.ADDR2"/></td>
<td class="td02"><html:text property="addr2" name="SupplierForm" size="80" maxlength="40"></html:text></td>
</tr>
<tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.POSTCODE"/></td>
<td class="td02">
<html:text property="postCode1" name="SupplierForm" size="3" maxlength="3" style="ime-mode: disabled;"/>-
<html:text property="postCode2" name="SupplierForm" size="4" maxlength="4" style="ime-mode: disabled;"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.TEL"/></td>
<td class="td02"><html:text property="tel" name="SupplierForm" size="15" maxlength="15" style="ime-mode: disabled;"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.FAX"/></td>
<td class="td02"><html:text property="fax" name="SupplierForm" size="15" maxlength="15" style="ime-mode: disabled;"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.SUPPLIER_STAFF_SEI"/></td>
<td class="td02"><html:text property="supplierStaffSei" name="SupplierForm" size="80" maxlength="40" /></td>
</tr>
<tr>

<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.SUPPLIER_STAFF_NAME"/></td>
<td class="td02"><html:text property="supplierStaffName" name="SupplierForm" size="80" maxlength="40" /></td>
</tr>
<tr>

<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.KEISYO"/></td>
<td class="td02">
<html:select property="keisyo" name="SupplierForm" >
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_SAMA, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_SAMA, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_ONCYU, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_ONCYU, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_TONO, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KEISYO_TONO, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.BUSYO"/></td>
<td class="td02"><html:text property="busyo" name="SupplierForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.DEAL_DIVISION"/></td>
<td class="td02">

<script type="text/javascript">
var cash = <%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE) %>;
</script>

<html:select property="dealDivision" name="SupplierForm" onchange="changeDealDivision(this.value, cash)">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_KAKE, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_KAKE, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.KESSAI_DIVISION"/></td>
<td class="td02">
<html:select property="kessaiDivision" name="SupplierForm">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_CASH, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_CASH, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_FURIKOMI, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_FURIKOMI, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_KOGITTE, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_KOGITTE, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_TEGATA, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_TEGATA, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_SOUSAI, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_SOUSAI, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_ETC, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_DIVISION_ETC, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<bean:define id="dealDivision" property="dealDivision" name="SupplierForm"/>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.KISYU_BALANCE"/></td>
<td class="td02"><html:text property="kisyuBalance" name="SupplierForm" size="22" maxlength="11" style="ime-mode: disabled;" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.SHIMEBI_MONTHLY"/></td>
<td class="td02">
<html:select property="shimebiMonthly" name="SupplierForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.SHIMEBI_KESSAI"/></td>
<td class="td02">
<html:select property="shimebiKessai" name="SupplierForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_KESSAI_NEXT, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_KESSAI_NEXT, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.KESSAI_MONTHLY"/></td>
<td class="td02">
<html:select property="kessaiMonthly" name="SupplierForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_MONTHLY_END, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_MONTHLY_END, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.STAFF"/></td>
<td class="td02">
<html:select property="staffCode" name="SupplierForm" styleId="classCode">
<html:option value=""><bean:message key="SUPPLIER_UPDATE.NOSTAFF"/></html:option>
<logic:notEmpty name="MStaffBeanList">
<%
List<MStaffBean> list = (List<MStaffBean>)request.getSession(false).getAttribute("MStaffBeanList");
Iterator<MStaffBean> itr = list.iterator();
while (itr.hasNext()) {
	MStaffBean staff = itr.next();
%>
<html:option value="<%=staff.getCode()%>"><%=staff.getSei() + staff.getName()%></html:option>
<%
}
%>
</logic:notEmpty>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.TAX"/></td>
<td class="td02">
<html:radio property="tax" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.TAX_SETTING_KAZEI, ViewProperties.VALUE) %>" onclick="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))"><bean:message key="SUPPLIER_UPDATE.LABEL.KAZEI"/></html:radio>
<html:radio property="tax" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.TAX_SETTING_HIKAZEI, ViewProperties.VALUE) %>" onclick="changeTaxStatus(document.getElementsByName('tax'),document.getElementsByName('calc'),document.getElementsByName('calcHasu'))"><bean:message key="SUPPLIER_UPDATE.LABEL.HIKAZEI"/></html:radio>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.CALC"/></td>
<td class="td02">
<html:radio property="calc" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.CALC_UCHIZEI, ViewProperties.VALUE) %>"><bean:message key="SUPPLIER_UPDATE.LABEL.UTHIZEI"/></html:radio>
<html:radio property="calc" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.CALC_SOTOZEI, ViewProperties.VALUE) %>"><bean:message key="SUPPLIER_UPDATE.LABEL.SOTOZEI"/></html:radio>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="SUPPLIER_UPDATE.LABEL.CALCHASU"/></td>
<td class="td02">
<html:radio property="calcHasu" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.CALC_HASU_SHISYAGONYU, ViewProperties.VALUE) %>"><bean:message key="SUPPLIER_UPDATE.LABEL.SHISYA_GONYU"/></html:radio>
<html:radio property="calcHasu" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.CALC_HASU_KIRIAGE, ViewProperties.VALUE) %>"><bean:message key="SUPPLIER_UPDATE.LABEL.KIRIAGE"/></html:radio>
<html:radio property="calcHasu" name="SupplierForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.CALC_HASU_KIRISUTE, ViewProperties.VALUE) %>"><bean:message key="SUPPLIER_UPDATE.LABEL.KIRISUTE"/></html:radio>
</td>
</tr>

<html:hidden property="updDate" name="SupplierForm"/>
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
