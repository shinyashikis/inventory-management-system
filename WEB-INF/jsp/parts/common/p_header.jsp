<%@page import="fw.common.util.SystemSessionKey"%>
<%@page import="fw.core.base.SystemSessionManager"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<div class="header" align="right">
<html:link action="/LogoutAction.do">ログアウト:<%= SystemSessionManager.getValue(session, SystemSessionKey.LOGIN_USER) %></html:link>
</div>
<hr size="1">