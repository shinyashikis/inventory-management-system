<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<body>

<h5>◆<bean:message key="MENU.VIEW"/></h5>

<div class="msg">
<html:messages id="msg" message="true"><bean:write name="msg" ignore="true"/><br><br></html:messages>
</div>
<div class="errmsg">
<html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br><br></html:messages>
</div>

<div align="center">
<table class="table01" width="100%">
<tr align="center">
<td class="td01" width="25%"><bean:message key="MENU.LABEL.MASTER"/></td>
<td class="td01" width="25%"><bean:message key="MENU.LABEL.VOUCHER"/></td>
<td class="td01" width="25%"><bean:message key="MENU.LABEL.REPORT"/></td>
<td class="td01" width="25%"><bean:message key="MENU.LABEL.ETC"/></td>
</tr>

<!-- 登録／設定 -->
<tr>
<td class="td02" valign="top"><table>
<tr><td><html:link action="/MenuAction/basic" transaction="true"><bean:message key="MENU.LINK.BASIC"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/staff" transaction="true"><bean:message key="MENU.LINK.STAFF"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/item" transaction="true"><bean:message key="MENU.LINK.ITEM"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/customer" transaction="true"><bean:message key="MENU.LINK.CUSTOMER"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/supplier" transaction="true"><bean:message key="MENU.LINK.SUPPLIER"/></html:link></td></tr>
</table></td>

<!-- 伝票／印刷 -->
<td class="td02" valign="top"><table>
<tr><td><html:link action="/MenuAction/quotation" transaction="true"><bean:message key="MENU.LABEL.QUOTATION"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/order" transaction="true"><bean:message key="MENU.LABEL.ORDER"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/sales" transaction="true"><bean:message key="MENU.LABEL.SALES"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/purchase" transaction="true"><bean:message key="MENU.LABEL.PURCHASE"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/bill" transaction="true"><bean:message key="MENU.LABEL.BILL"/></html:link></td></tr>
</table></td>

<!-- 帳簿 -->
<td class="td02" valign="top"><table>
<tr><td><html:link action="/MenuAction/bookItem" transaction="true"><bean:message key="MENU.LINK.BOOK_ITEM"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/bookSales" transaction="true"><bean:message key="MENU.LABEL.BOOK_SALES"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/bookPurchase" transaction="true"><bean:message key="MENU.LINK.BOOK_PURCHASE"/></html:link></td></tr>
<tr><td><html:link action="/MenuAction/bookVoucherList" transaction="true"><bean:message key="MENU.LABEL.BOOK_VOUCHER_LIST"/></html:link></td></tr>
</table></td>

<!-- その他 -->
<td class="td02" valign="top"><table>
<tr><td>xxxx</td></tr>
<tr><td>xxxx</td></tr>
<tr><td>xxxx</td></tr>
</table></td>
</tr>

</table>
</div>

</body>
