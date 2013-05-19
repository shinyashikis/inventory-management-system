<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ page import="prop.ViewProperties"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div>
<input type="button" value='<bean:message key="COMMON.BTN.UPDATE"/>' onclick="update();">
<input type="button" value='<bean:message key="ORDER_INPUT.BTN.CREATE_PURCHASE"/>' onclick="createPurchase();">
<html:submit property="back"><bean:message key="COMMON.BTN.BACK"/></html:submit>
</div>
<br>

<div>
<input type="button" value='<bean:message key="COMMON.BTN.PRINT_PREVIEW"/>' onclick="print();">

<div class="lbl01"><html:radio property="printKind" name="OrderForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_ORDER, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_ORDER, ViewProperties.DISP_VALUE) %>
</html:radio></div>

</div>
