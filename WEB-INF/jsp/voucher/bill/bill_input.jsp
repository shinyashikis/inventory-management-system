<%@ page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Shift_JIS" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<meta lang="ja" http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="SYSTEM.NAME"/>:<bean:message key="BILL_INPUT.VIEW"/></title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common.css" type="text/css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/voucher/voucher.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/voucher/voucher_deal.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/voucher/voucher_item_detail.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/voucher/bill.js"></script>
<script type="text/javascript">
var contextPath = '<%= request.getContextPath() %>';

// 取引先区分
var dealKindCustomer = '<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.VALUE) %>';
var dealKindSupplier = '<%= ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_SUPPLIER, ViewProperties.VALUE) %>';

// エラーメッセージ(必須エラー)
var dealErrMsgRequired = '<bean:message key="VOUCHER_INPUT.ERRMSG.DEAL.REQUIRED"/>';
var itemErrMsgRequired = '<bean:message key="VOUCHER_INPUT.ERRMSG.ITEM.REQUIRED"/>';
var voucherDateErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.VOUCHER_DATE.REQUIRED"/>';
var voucherNameErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.VOUCHER_NAME.REQUIRED"/>';
var discountErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.DISCOUNT.REQUIRED"/>';
var dealCodeErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.DEAL_CODE.REQUIRED"/>';
var itemCodeErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.ITEM_CODE.REQUIRED"/>';
var itemCountErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.ITEM_COUNT.REQUIRED"/>';
var itemUnitPriceErrMsgRequired = '<bean:message key="VOUHCER_INPUT.ERRMSG.ITEM_UNIT_PRICE.REQUIRED"/>';
//エラーメッセージ(数字以外エラー)
var voucherDateErrMsgNoN = '<bean:message key="VOUHCER_INPUT.ERRMSG.VOUCHER_DATE.NOTNUMBER"/>';
var receiptDateErrMsgNoN = '<bean:message key="VOUHCER_INPUT.ERRMSG.RECEIPT_DATE.NOTNUMBER"/>';
var discountErrMsgNoN = '<bean:message key="VOUHCER_INPUT.ERRMSG.DISCOUNT.NOTNUMBER"/>';
var itemCountErrMsgNoN = '<bean:message key="VOUHCER_INPUT.ERRMSG.ITEM_COUNT.NOTNUMBER"/>';
var itemUnitPriceErrMsgNoN = '<bean:message key="VOUHCER_INPUT.ERRMSG.ITEM_UNIT_PRICE.NOTNUMBER"/>';

// 帳票アクションURL
var reportDialogUrl = contextPath + '/BillInputAction.do?print';
</script>
</head>

<body>
<html:form action="/BillInputAction">
<tiles:insert template="/WEB-INF/jsp/template/voucher_input.jsp">
<tiles:put name="header" value="/WEB-INF/jsp/parts/common/p_header.jsp" />
<tiles:put name="voucher_input_header" value="/WEB-INF/jsp/parts/voucher/bill/p_bill_input_header.jsp" />
<tiles:put name="voucher_input_deal" value="/WEB-INF/jsp/parts/voucher/bill/p_bill_input_deal.jsp" />
<tiles:put name="voucher_input" value="/WEB-INF/jsp/parts/voucher/bill/p_bill_input.jsp" />
<tiles:put name="voucher_input_item_detail" value="/WEB-INF/jsp/parts/voucher/bill/p_bill_input_item_detail.jsp" />
<tiles:put name="voucher_input_footer" value="/WEB-INF/jsp/parts/voucher/bill/p_bill_input_footer.jsp" />
<tiles:put name="footer" value="/WEB-INF/jsp/parts/common/p_footer.jsp" />
</tiles:insert>
</html:form>
</body>
</html:html>
