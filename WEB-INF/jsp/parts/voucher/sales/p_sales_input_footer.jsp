<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ page import="prop.ViewProperties"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div>
<input type="button" value='<bean:message key="COMMON.BTN.UPDATE"/>' onclick="update();">
<html:submit property="back"><bean:message key="COMMON.BTN.BACK"/></html:submit>
</div>
<br>

<div>
<input type="button" value='<bean:message key="COMMON.BTN.PRINT_PREVIEW"/>' onclick="print();">

<div class="lbl01">
<html:radio property="printKind" name="SalesForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_SALES, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_SALES, ViewProperties.DISP_VALUE) %>
</html:radio>
<html:radio property="printKind" name="SalesForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_STATEMENT_OF_DELIVERY, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_STATEMENT_OF_DELIVERY, ViewProperties.DISP_VALUE) %>
</html:radio>
<html:radio property="printKind" name="SalesForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_BILL, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_BILL, ViewProperties.DISP_VALUE) %>
</html:radio>
<html:radio property="printKind" name="SalesForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_RECEIPT, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_RECEIPT, ViewProperties.DISP_VALUE) %>
</html:radio>
<html:radio property="printKind" name="SalesForm" value="<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_ACKNOWLEDGMENT, ViewProperties.VALUE) %>">
<%= ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_ACKNOWLEDGMENT, ViewProperties.DISP_VALUE) %>
</html:radio>
</div>

</div>
