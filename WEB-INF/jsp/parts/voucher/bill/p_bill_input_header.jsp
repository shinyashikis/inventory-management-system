<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="breadcrumbList">
<bean:message key="MENU.VIEW"/> > <bean:message key="BILL_LIST.VIEW"/> > <bean:message key="BILL_INPUT.VIEW"/>
</div>

<h5>◆<bean:message key="BILL_INPUT.VIEW"/></h5>

<div class="msg" ><html:messages id="msg" message="true"><bean:write name="msg" ignore="true"/><br></html:messages></div>
<div class="errmsg"><html:messages id="msg" message="false"><bean:write name="msg" ignore="true"/><br></html:messages></div>
