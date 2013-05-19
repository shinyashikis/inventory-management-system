<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<meta lang="ja" http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="SYSTEM.NAME"/>:<bean:message key="BOOK_ITEM.VIEW"/></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css" type="text/css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/book.js"></script>
<script type="text/javascript">
function setDispTargetRadioDisabled() {
	// 日付範囲指定(日付範囲指定)
	document.getElementById('selectDateRangeRadio').disabled = true;
	document.getElementById('searchDateMonthFromComb').disabled = true;
	document.getElementById('searchDateDayFromComb').disabled = true;
	document.getElementById('searchDateMonthToComb').disabled = true;
	document.getElementById('searchDateDayToComb').disabled = true;
	// 日付範囲指定(月次推移表)
	document.getElementById('selectDateRangeGetsujiRadio').disabled = true;
	document.getElementById('shimebiComb').disabled = true;
	// 日付範囲指定(年度合計)
	document.getElementById('selectDateRangeNendoRadio').disabled = true;
}
</script>
</head>

<body onload="setDispTargetRadioDisabled();">
<html:form action="/BookItemDispAction">
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
