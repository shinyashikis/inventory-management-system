<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> >
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<bean:message key="BOOK_ITEM.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<bean:message key="BOOK_SALES.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<bean:message key="BOOK_PURCHASE.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<bean:message key="BOOK_VOUCHER_LIST.VIEW"/>
</logic:equal>
</div>

<h5>◆
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<bean:message key="BOOK_ITEM.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<bean:message key="BOOK_SALES.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<bean:message key="BOOK_PURCHASE.VIEW"/>
</logic:equal>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE) %>">
<bean:message key="BOOK_VOUCHER_LIST.VIEW"/>
</logic:equal>
</h5>
<div class="msg" ><html:messages id="msg" message="true"><bean:write name="msg" ignore="true"/><br></html:messages></div>
<div class="errmsg"><html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages></div>