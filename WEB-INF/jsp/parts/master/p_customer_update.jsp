<%@page import="prop.ViewProperties"%>
<%@page import="master.staff.bean.MStaffBean"%>
<%@page import="java.util.List"%>
<%@page import="master.customer.bean.MCustomerClassBean"%>
<%@page import="master.customer.CustomerClassListForm"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<body>

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="CUSTOMER_LIST.VIEW"/> > <bean:message key="CUSTOMER_UPDATE.VIEW"/>
</div>

<h5>◆<bean:message key="CUSTOMER_UPDATE.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true">	<bean:write name="msg" ignore="true"/><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages>
</div>

<html:form action="/CustomerUpdateAction">
<html:hidden property="updDate" name="CustomerForm"/>

<div align="left">

<table class="table01" cellpadding="3">
<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.CD"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02">
<logic:empty name="CustomerListForm" property="sel">
<html:text property="code" name="CustomerForm" size="20" maxlength="10" style="ime-mode: disabled;"/>
</logic:empty>
<logic:notEmpty name="CustomerListForm" property="sel">
<bean:write property="code" name="CustomerForm"/>
<html:hidden property="code" name="CustomerForm"/>
</logic:notEmpty>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.NAME"/><bean:message key="COMMON.LABEL.HISSU"/></td>
<td class="td02"><html:text property="name" name="CustomerForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.KANA"/></td>
<td class="td02"><html:text property="kana" name="CustomerForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.CLASS_CODE"/></td>
<td class="td02">
<html:select property="classCode" name="CustomerForm" styleId="classCode">
<html:option value=""><bean:message key="CUSTOMER_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="CustomerClassListForm">

<%
CustomerClassListForm customerClassListForm = (CustomerClassListForm)request.getSession(false).getAttribute("CustomerClassListForm");
Set<Entry<String, MCustomerClassBean>> set = customerClassListForm.getCustomerClassMap().entrySet();
Iterator<Entry<String, MCustomerClassBean>> itr = set.iterator();
while (itr.hasNext()) {
	Entry<String, MCustomerClassBean> e = itr.next();
%>
<html:option value="<%=e.getKey()%>"><%=e.getValue().getName()%></html:option>
<%
}
%>

</logic:notEmpty>
</html:select>
<html:button property="customerClassUpdate" onclick="<%= \"openClassDialog('\" + request.getContextPath() + \"/CustomerClassListAction.do')\" %>"><bean:message key="COMMON.BTN.CLASS_REGIST"/></html:button>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.ADDR1"/></td>
<td class="td02"><html:text property="addr1" name="CustomerForm" size="80" maxlength="40"></html:text></td>
</tr>
<tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.ADDR2"/></td>
<td class="td02"><html:text property="addr2" name="CustomerForm" size="80" maxlength="40"></html:text></td>
</tr>
<tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.POSTCODE"/></td>
<td class="td02">
<html:text property="postCode1" name="CustomerForm" size="3" maxlength="3" style="ime-mode: disabled;"/>-
<html:text property="postCode2" name="CustomerForm" size="4" maxlength="4" style="ime-mode: disabled;"/>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.TEL"/></td>
<td class="td02"><html:text property="tel" name="CustomerForm" size="15" maxlength="15" style="ime-mode: disabled;"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.FAX"/></td>
<td class="td02"><html:text property="fax" name="CustomerForm" size="15" maxlength="15" style="ime-mode: disabled;"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.CUSTOMER_STAFF_SEI"/></td>
<td class="td02"><html:text property="customerStaffSei" name="CustomerForm" size="80" maxlength="40" /></td>
</tr>
<tr>

<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.CUSTOMER_STAFF_NAME"/></td>
<td class="td02"><html:text property="customerStaffName" name="CustomerForm" size="80" maxlength="40" /></td>
</tr>
<tr>

<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.KEISYO"/></td>
<td class="td02">
<html:select property="keisyo" name="CustomerForm" >
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
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.BUSYO"/></td>
<td class="td02"><html:text property="busyo" name="CustomerForm" size="80" maxlength="40" /></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.DEAL_DIVISION"/></td>
<td class="td02">

<script type="text/javascript">
var cash = <%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE) %>;
</script>

<html:select property="dealDivision" name="CustomerForm" onchange="changeDealDivision(this.value, cash)">
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
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.KESSAI_DIVISION"/></td>
<td class="td02">
<html:select property="kessaiDivision" name="CustomerForm">
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

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.SALE_PRICE_DIVISION"/></td>
<td class="td02">
<html:select property="salePriceDivision" name="CustomerForm">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_1, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_1, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_2, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_2, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_3, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_3, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_4, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_4, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_5, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SALE_PRICE_DIVISION_5, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<bean:define id="dealDivision" property="dealDivision" name="CustomerForm"/>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.KISYU_BALANCE"/></td>
<td class="td02"><html:text styleId="kisyuBalance" property="kisyuBalance" name="CustomerForm" size="22" maxlength="11" style="ime-mode: disabled;" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>"/></td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.SHIMEBI_MONTHLY"/></td>
<td class="td02">
<html:select property="shimebiMonthly" name="CustomerForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.SHIMEBI_KESSAI"/></td>
<td class="td02">
<html:select styleId="shimebiKessai" property="shimebiKessai" name="CustomerForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_KESSAI_NEXT, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_KESSAI_NEXT, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.KESSAI_MONTHLY"/></td>
<td class="td02">
<html:select styleId="kessaiMonthly" property="kessaiMonthly" name="CustomerForm" disabled="<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE).equals(dealDivision) %>">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_MONTHLY_END, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.KESSAI_MONTHLY_END, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select>
</td>
</tr>

<tr>
<td class="td01"><bean:message key="CUSTOMER_UPDATE.LABEL.STAFF"/></td>
<td class="td02">
<html:select property="staffCode" name="CustomerForm" styleId="classCode">
<html:option value=""><bean:message key="CUSTOMER_UPDATE.NOSTAFF"/></html:option>
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

<html:hidden property="updDate" name="CustomerForm"/>
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
