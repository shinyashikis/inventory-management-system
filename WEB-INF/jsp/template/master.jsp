<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<body>
<table width="100%" height="100%">

<!-- header -->
<tr height="30">
<td colspan="2"><tiles:insert name="header"/></td>
</tr>

<tr>
<!-- body -->
<td valign="top" align="left"><tiles:insert name="body"/></td>
</tr>

<!-- footer -->
<tr height="30">
<td colspan="2"><tiles:insert name="footer"/></td>
</tr>

</table>
</body>
