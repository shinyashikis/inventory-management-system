<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<meta lang="ja" http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="SYSTEM.NAME"/>:<bean:message key="SUPPLIER_CLASS_LIST.VIEW"/></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css" type="text/css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/master.js"></script>
<base target="_self">
</head>
<tiles:insert template="/WEB-INF/jsp/template/dialog.jsp">
<tiles:put name="body" value="/WEB-INF/jsp/parts/master/p_supplier_class_list.jsp" />
</tiles:insert>
</html:html>
