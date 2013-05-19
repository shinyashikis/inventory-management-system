<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-tiles"prefix="tiles"%>
<table width="100%" height="100%">

<!-- header -->
<tr height="30">
<td colspan="2"><tiles:insert name="header"/></td>
</tr>

<tr>
<!-- voucher_input_header -->
<td valign="top" align="left"><tiles:insert name="voucher_input_header"/></td>
</tr>

<tr>
<td valign="top" align="left">
<table width="100%"><tr>
<!-- voucher_input_deal -->
<td valign="top" align="left"><tiles:insert name="voucher_input_deal"/></td>
<!-- voucher_input -->
<td valign="top" align="left"><tiles:insert name="voucher_input"/></td>
</tr></table>
</td>
</tr>

<tr>
<!-- voucher_input_item_detail -->
<td valign="top" align="left"><tiles:insert name="voucher_input_item_detail"/></td>
</tr>

<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>

<tr>
<!-- voucher_input_footer -->
<td valign="top" align="left"><tiles:insert name="voucher_input_footer"/></td>
</tr>

<!-- footer -->
<tr height="30">
<td colspan="2"><tiles:insert name="footer"/></td>
</tr>

</table>

