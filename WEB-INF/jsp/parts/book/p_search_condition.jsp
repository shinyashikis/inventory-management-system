<%@page import="prop.ViewProperties"%>
<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"  %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
// [0][0]code, [0][1]classCode
var items = new Array();
var customers = new Array();
var suppliers = new Array();

/**
 * 検索条件指定コンボボックス変更時
 * @param value
 */
function changeConditionCmb(value) {

	if (value == <%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER, ViewProperties.VALUE) %>) {
		// 得意先選択時

		document.getElementById('customerClassCondition').disabled = false;
		document.getElementById('customerCondition').disabled = false;
		document.getElementById('customerStaff').disabled = true;

	} else if (value == <%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS, ViewProperties.VALUE) %>) {
		// 得意先分類選択時

		document.getElementById('customerClassCondition').disabled = false;
		document.getElementById('customerCondition').disabled = true;
		document.getElementById('customerStaff').disabled = true;

	} else if (value == <%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE) %>) {
		// 担当者選択時

		if (document.getElementById('customerClassCondition') != null) {
			document.getElementById('customerClassCondition').disabled = true;
			document.getElementById('customerCondition').disabled = true;
			document.getElementById('customerStaff').disabled = false;
		} else {
			document.getElementById('supplierClassCondition').disabled = true;
			document.getElementById('supplierCondition').disabled = true;
			document.getElementById('supplierStaff').disabled = false;
		}

	} else if (value == <%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER, ViewProperties.VALUE) %>) {
		// 仕入先選択時

		document.getElementById('supplierClassCondition').disabled = false;
		document.getElementById('supplierCondition').disabled = false;
		document.getElementById('supplierStaff').disabled = true;

	} else if (value == <%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER_CLASS, ViewProperties.VALUE) %>) {
		// 仕入先分類選択時

		document.getElementById('supplierClassCondition').disabled = false;
		document.getElementById('supplierCondition').disabled = true;
		document.getElementById('supplierStaff').disabled = true;
	}
}

/**
 * 商品分類指定コンボボックス変更時
 */
function changeItemClassCmb(value) {
	if (value == "") {
		document.getElementById("itemCondition").getElementsByTagName('option')[0].selected = true;
		return;
	}

	var targetCode = "";
	for (i=0; i<items.length;i++) {
		if (items[i]['classCode'] == value) {
			targetCode = items[i]['code'];
			break;
		}
	}

	var code = document.getElementById("itemCondition").getElementsByTagName('option');
	for (i=0; i<code.length;i++){
		if (code[i].value == targetCode) {
			code[i].selected = true;
			break;
		}
	}
}

/**
 * 得意先分類指定コンボボックス変更時
 */
function changeCustomerClassCmb(value) {
	if (value == "") {
		document.getElementById("customerCondition").getElementsByTagName('option')[0].selected = true;
		return;
	}

	var targetCode = "";
	for (i=0; i<customers.length;i++) {
		if (customers[i]['classCode'] == value) {
			targetCode = customers[i]['code'];
			break;
		}
	}

	var code = document.getElementById("customerCondition").getElementsByTagName('option');
	for (i=0; i<code.length;i++){
		if (code[i].value == targetCode) {
			code[i].selected = true;
			break;
		}
	}
}

/**
 * 仕入先分類指定コンボボックス変更時
 */
function changeSupplierClassCmb(value) {
	if (value == "") {
		document.getElementById("supplierCondition").getElementsByTagName('option')[0].selected = true;
		return;
	}

	var targetCode = "";
	for (i=0; i<suppliers.length;i++) {
		if (suppliers[i]['classCode'] == value) {
			targetCode = suppliers[i]['code'];
			break;
		}
	}

	var code = document.getElementById("supplierCondition").getElementsByTagName('option');
	for (i=0; i<code.length;i++){
		if (code[i].value == targetCode) {
			code[i].selected = true;
			break;
		}
	}
}
</script>

<tr>
<th class="th01" colspan="6"><bean:message key="BOOK.LABEL.SEARCH_CONDITION"/></th>
</tr>
<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE) %>">
<tr>
<td class="td02"><bean:message key="BOOK_ITEM.LABEL.SELECT_ITEM_CLASS"/></td>
<td class="td02" colspan="5">
<html:select property="itemClassCondition" name="BookItemForm" styleId="itemClassCondition" onclick="changeItemClassCmb(this.value);">
<html:option value=""><bean:message key="ITEM_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mItemClassBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
</logic:notEmpty>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_ITEM.LABEL.SELECT_ITEM"/></td>
<td class="td02" colspan="5">
<html:select property="itemCondition" name="BookItemForm" styleId="itemCondition">
<html:option value=""><bean:message key="ITEM.ALL"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mItemBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
<script type="text/javascript">
var cnt=0;
</script>
<logic:iterate id="map" property="mItemBeanMap" name="BookBean">
<script type="text/javascript">
items[cnt] = new Array();
items[cnt]["code"] = '<bean:write name="map" property="value.code"/>';
items[cnt]["classCode"] = '<bean:write name="map" property="value.classCode"/>';
cnt++;
</script>
</logic:iterate>
</logic:notEmpty>
</html:select>
</td>
</tr>
</logic:equal>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE) %>">
<tr>
<td class="td02" colspan="6">
<html:select property="condition" name="BookSalesForm" onchange="changeConditionCmb(this.value);">
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_CUSTOMER"/></option>
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_CUSTOMER_CLASS"/></option>
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_STAFF"/></option>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_SALES.LABEL.SELECT_CUSTOMER_CLASS"/></td>
<td class="td02" colspan="5">
<html:select property="customerClassCondition" name="BookSalesForm" styleId="customerClassCondition" onclick="changeCustomerClassCmb(this.value);">
<html:option value=""><bean:message key="CUSTOMER_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mCustomerClassBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
</logic:notEmpty>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_SALES.LABEL.SELECT_CUSTOMER"/></td>
<td class="td02" colspan="5">
<html:select property="customerCondition" name="BookSalesForm" styleId="customerCondition">
<html:option value=""><bean:message key="COMMON.ALL"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mCustomerBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
<script type="text/javascript">
var cnt=0;
</script>
<logic:iterate id="map" property="mCustomerBeanMap" name="BookBean">
<script type="text/javascript">
customers[cnt] = new Array();
customers[cnt]["code"] = '<bean:write name="map" property="value.code"/>';
customers[cnt]["classCode"] = '<bean:write name="map" property="value.classCode"/>';
cnt++;
</script>
</logic:iterate>
</logic:notEmpty>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_SALES.LABEL.SELECT_STAFF"/></td>
<td class="td02" colspan="5">
<html:select property="customerStaff" name="BookSalesForm" styleId="customerStaff" disabled="true">
<html:option value=""><bean:message key="COMMON.ALL"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mStaffBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.fullName"/>
</logic:notEmpty>
</html:select>
</td>
</tr>
</logic:equal>

<logic:equal property="bookKind" name="BookBean" value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE) %>">
<tr>
<td class="td02" colspan="6">
<html:select property="condition" name="BookPurchaseForm" onchange="changeConditionCmb(this.value);">
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_SUPPLIER"/></option>
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_SUPPLIER_CLASS, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_SUPPLIER_CLASS"/></option>
<option value="<%= prop.ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE) %>"><bean:message key="BOOK.LABEL.SELECT_DISP_SORT_STAFF"/></option>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_PURCHASE.LABEL.SELECT_SUPPLIER_CLASS"/></td>
<td class="td02" colspan="5">
<html:select property="supplierClassCondition" name="BookPurchaseForm" styleId="supplierClassCondition" onclick="changeSupplierClassCmb(this.value);">
<html:option value=""><bean:message key="SUPPLIER_UPDATE.NOCLASS"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mSupplierClassBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
</logic:notEmpty>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_PURCHASE.LABEL.SELECT_SUPPLIER"/></td>
<td class="td02" colspan="5">
<html:select property="supplierCondition" name="BookPurchaseForm" styleId="supplierCondition">
<html:option value=""><bean:message key="COMMON.ALL"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mSupplierBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.name"/>
<script type="text/javascript">
var cnt=0;
</script>
<logic:iterate id="map" property="mSupplierBeanMap" name="BookBean">
<script type="text/javascript">
suppliers[cnt] = new Array();
suppliers[cnt]["code"] = '<bean:write name="map" property="value.code"/>';
suppliers[cnt]["classCode"] = '<bean:write name="map" property="value.classCode"/>';
cnt++;
</script>
</logic:iterate>
</logic:notEmpty>
</html:select>
</td>
</tr>
<tr>
<td class="td02"><bean:message key="BOOK_PURCHASE.LABEL.SELECT_STAFF"/></td>
<td class="td02" colspan="5">
<html:select property="supplierStaff" name="BookPurchaseForm" styleId="supplierStaff" disabled="true">
<html:option value=""><bean:message key="COMMON.ALL"/></html:option>
<logic:notEmpty name="BookBean">
<bean:define id="map" property="mStaffBeanMap" name="BookBean"></bean:define>
<html:options collection="map" property="key" labelProperty="value.fullName"/>
</logic:notEmpty>
</html:select>
</td>
</tr>
</logic:equal>