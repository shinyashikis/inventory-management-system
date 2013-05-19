<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tr>
<th class="th01" colspan="6"><bean:message key="BOOK.LABEL.SELECT_DISP_TARGET_DETAIL"/></th>
</tr>
<tr>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<td class="td02"><html:radio styleId="dispTargetDetailCountRadio" property="dispTargetDetail" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_COUNT"/></html:radio></td>
<td class="td02" colspan="5"><html:radio styleId="dispTargetDetailPriceRadio" property="dispTargetDetail" name="BookItemForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_PRICE"/></html:radio></td>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<td class="td02"><html:radio styleId="dispTargetDetailVoucherRadio" property="dispTargetDetail" name="BookSalesForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_VOUCHER"/></html:radio></td>
<td class="td02" colspan="5"><html:radio styleId="dispTargetDetailDetailRadio" property="dispTargetDetail" name="BookSalesForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_DETAIL"/></html:radio></td>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<td class="td02"><html:radio styleId="dispTargetDetailVoucherRadio" property="dispTargetDetail" name="BookPurchaseForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_VOUCHER"/></html:radio></td>
<td class="td02" colspan="5"><html:radio styleId="dispTargetDetailDetailRadio" property="dispTargetDetail" name="BookPurchaseForm" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.DISP_DETAIL_DETAIL"/></html:radio></td>
</logic:equal>
</tr>
