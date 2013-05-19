<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<table width="100%">

<!-- header -->
<tr height="30">
<td colspan="2"><tiles:insert name="header"/></td>
</tr>

<tr>
<!-- book_header -->
<td valign="top" align="left"><tiles:insert name="book_header"/></td>
</tr>

<tr>
<td>
<table class="table01">
<!-- disp_target -->
<tiles:insert name="disp_target"/>
<tr><td>&nbsp;</td></tr>
<!-- disp_target_detail -->
<tiles:insert name="disp_target_detail"/>
<tr><td>&nbsp;</td></tr>
<!-- search_condition -->
<tiles:insert name="search_condition"/>
<tr><td>&nbsp;</td></tr>
<!-- date_condition -->
<tiles:insert name="date_condition"/>
<tr><td>&nbsp;</td></tr>
<!-- disp_sort -->
<tiles:insert name="disp_sort"/>
</table>
</td>
</tr>


<tr><td>&nbsp;</td></tr>

<tr>
<!-- book_footer -->
<td valign="top" align="left"><tiles:insert name="book_footer"/></td>
</tr>

<!-- footer -->
<tr height="30">
<td colspan="2"><tiles:insert name="footer"/></td>
</tr>

</table>

