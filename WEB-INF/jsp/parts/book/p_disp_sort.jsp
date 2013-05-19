<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tr>
<th class="th01" colspan="6"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT"/></th>
</tr>
<tr>
<td class="td02" colspan="6">
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<html:select property="selectDispSort">
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_ITEM, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_ITEM"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_ITEM_CLASS, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_ITEM_CLASS"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_VOUCHER_DATE, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_VOUCHER_DATE"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_VOUCHER_NO, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_VOUCHER_NO"/></html:option>
</html:select>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<html:select property="selectDispSort">
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_CUSTOMER"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_CUSTOMER_CLASS"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_STAFF"/></html:option>
</html:select>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<html:select property="selectDispSort">
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_SUPPLIER"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER_CLASS, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_SUPPLIER_CLASS"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_STAFF"/></html:option>
</html:select>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<html:select property="selectDispSort">
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_VOUCHER_DATE, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_VOUCHER_DATE"/></html:option>
<html:option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_VOUCHER_NO, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_VOUCHER_NO"/></html:option>
</html:select>
</logic:equal>
</td>
</tr>
<tr>
<td class="td02"><html:radio property="selectSort" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_SORT_ASC"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="selectSort" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_DSC, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_SORT_DSC"/></html:radio></td>
</tr>

<logic:notEqual property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<tr>
<td class="td02"><html:radio property="selectSortKind" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_SORT_KIND_KANA"/></html:radio></td>
<td class="td02" colspan="5"><html:radio property="selectSortKind" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_KIND_CODE, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_SORT_KIND_CODE"/></html:radio></td>
</tr>
</logic:notEqual>
