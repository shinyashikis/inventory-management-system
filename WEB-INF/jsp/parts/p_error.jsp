<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<body>
<div class="errmsg" align="center">
<html:messages id="msg" message="false" bundle="com"><bean:write name="msg" ignore="true"/></html:messages>
</div>

<br>
<br>
<div align="center">
<html:link href="<%= request.getContextPath() %>">ログインへ</html:link>
</div>
</body>
