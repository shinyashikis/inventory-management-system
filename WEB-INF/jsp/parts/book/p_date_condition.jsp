<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tr>
<th class="th01" colspan="6"><bean:message key="BOOK.LABEL.SELECT_DATE_RANGE"/></th>
</tr>
<!-- 検索日付指定 -->
<tr>
<td class="td02"><html:radio styleId="selectDateRangeRadio" property="selectDateRange" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DATE_RANGE"/></html:radio></td>
<td class="td02"><bean:message key="BOOK.LABEL.SELECT_SEARCH_DATE"/></td>
<td class="td02">
<html:select property="searchDateMonthFrom" styleId="searchDateMonthFromComb">
<%
for (int i = 1; i <= 12; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.MONTH"/>
<html:select property="searchDateDayFrom" styleId="searchDateDayFromComb">
<%
for (int i = 1; i <= 31; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.DATE"/>
</td>
<td class="td02" colspan="3"><bean:message key="COMMON.LABEL.FROM"/></td>
</tr>
<tr>
<td class="td02"></td>
<td class="td02"></td>
<td class="td02">
<html:select property="searchDateMonthTo" styleId="searchDateMonthToComb">
<%
for (int i = 1; i <= 12; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.MONTH"/>
<html:select property="searchDateDayTo" styleId="searchDateDayToComb">
<%
for (int i = 1; i <= 31; i++) {
%>
	<html:option value="<%= Integer.toString(i) %>"><%= i %></html:option>
<%
}
%>
</html:select>
<bean:message key="COMMON.LABEL.DATE"/>
</td>
<td class="td02" colspan="3"><bean:message key="COMMON.LABEL.TO"/></td>
</tr>

<!-- 月次推移表 -->
<logic:notEqual property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<tr>
<td class="td02"><html:radio styleId="selectDateRangeGetsujiRadio" property="selectDateRange" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DATE_RANGE_MONTHLY_TRANSITION"/></html:radio></td>
<td class="td02"><bean:message key="BOOK.LABEL.SHIMEBI"/></td>
<td class="td02" colspan="4"><html:select property="shimebi" styleId="shimebiComb">
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.DISP_VALUE) %>
</html:option>
<html:option value="<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.DISP_VALUE) %>
</html:option>
</html:select></td>
</tr>
</logic:notEqual>

<!-- 年度合計 -->
<tr>
<logic:notEqual property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<td class="td02"><html:radio styleId="selectDateRangeNendoRadio" property="selectDateRange" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DATE_RANGE_YEAR_SUM"/></html:radio></td>
</logic:notEqual>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<td class="td02">&nbsp;</td>
</logic:equal>

<td class="td02"><bean:message key="BOOK.LABEL.TARGET_SEARCH_YEAR"/></td>
<td class="td02"><html:text property="nendo" size="16" maxlength="4"></html:text></td>
<td class="td02" colspan="3"><bean:message key="COMMON.LABEL.NENDO"/></td>
</tr>
