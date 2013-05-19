<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<meta lang="ja" http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="SYSTEM.NAME"/>:<bean:message key="BOOK_PURCHASE.VIEW"/></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css" type="text/css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/book.js"></script>
<script type="text/javascript">
function setDispTargetRadioDisabled() {
	// 表示対象詳細指定(伝票別)
	document.getElementById('dispTargetDetailVoucherRadio').disabled = true;
	// 表示対象詳細指定(詳細)
	document.getElementById('dispTargetDetailDetailRadio').disabled = true;
}
</script>
</head>

<body onload="setDispTargetRadioDisabled();">
<html:form action="/BookPurchaseDispAction">
<tiles:insert template="/WEB-INF/jsp/template/book.jsp">
<tiles:put name="header" value="/WEB-INF/jsp/parts/common/p_header.jsp" />
<tiles:put name="book_header" value="/WEB-INF/jsp/parts/book/p_book_header.jsp" />
<tiles:put name="disp_target" value="/WEB-INF/jsp/parts/book/p_disp_target.jsp" />
<tiles:put name="disp_target_detail" value="/WEB-INF/jsp/parts/book/p_disp_target_detail.jsp" />
<tiles:put name="search_condition" value="/WEB-INF/jsp/parts/book/p_search_condition.jsp" />
<tiles:put name="date_condition" value="/WEB-INF/jsp/parts/book/p_date_condition.jsp" />
<tiles:put name="disp_sort" value="/WEB-INF/jsp/parts/book/p_disp_sort.jsp" />
<tiles:put name="book_footer" value="/WEB-INF/jsp/parts/book/p_book_footer.jsp" />
<tiles:put name="footer" value="/WEB-INF/jsp/parts/common/p_footer.jsp" />
</tiles:insert>
</html:form>
</body>
</html:html>
