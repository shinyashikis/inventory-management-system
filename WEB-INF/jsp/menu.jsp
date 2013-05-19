<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css" type="text/css"/>
<title><bean:message key="SYSTEM.NAME"/>:<bean:message key="MENU.VIEW"/></title>
</head>
<tiles:insert template="/WEB-INF/jsp/template/master.jsp">
<tiles:put name="header" value="/WEB-INF/jsp/parts/common/p_header.jsp" />
<tiles:put name="body" value="/WEB-INF/jsp/parts/p_menu.jsp" />
<tiles:put name="footer" value="/WEB-INF/jsp/parts/common/p_footer.jsp" />
</tiles:insert>
</html:html>
