mysql -u user -pdbname password < CREATE_C_LOGIN_STATUS.sql
mysql -u user -pdbname password < CREATE_C_USER.sql
mysql -u user -pdbname password < CREATE_M_BASIC.sql
mysql -u user -pdbname password < CREATE_M_STAFF.sql

mysql -u user -pdbname password < CREATE_M_ITEM_CLASS.sql
mysql -u user -pdbname password < CREATE_M_ITEM.sql

mysql -u user -pdbname password < CREATE_M_CUSTOMER_CLASS.sql
mysql -u user -pdbname password < CREATE_M_CUSTOMER.sql

mysql -u user -pdbname password < CREATE_M_SUPPLIER_CLASS.sql
mysql -u user -pdbname password < CREATE_M_SUPPLIER.sql

mysql -u user -pdbname password < CREATE_T_QUOTATION.sql
mysql -u user -pdbname password < CREATE_T_QUOTATION_DEAL.sql
mysql -u user -pdbname password < CREATE_T_QUOTATION_DETAILS.sql

mysql -u user -pdbname password < CREATE_T_ORDER.sql
mysql -u user -pdbname password < CREATE_T_ORDER_DEAL.sql
mysql -u user -pdbname password < CREATE_T_ORDER_DETAILS.sql

mysql -u user -pdbname password < CREATE_T_SALES.sql
mysql -u user -pdbname password < CREATE_T_SALES_DEAL.sql
mysql -u user -pdbname password < CREATE_T_SALES_DETAILS.sql

mysql -u user -pdbname password < CREATE_T_PURCHASE.sql
mysql -u user -pdbname password < CREATE_T_PURCHASE_DEAL.sql
mysql -u user -pdbname password < CREATE_T_PURCHASE_DETAILS.sql

mysql -u user -pdbname password < CREATE_T_BILL.sql
mysql -u user -pdbname password < CREATE_T_BILL_DEAL.sql
mysql -u user -pdbname password < CREATE_T_BILL_DETAILS.sql

mysql -u user -pdbname password < CREATE_V_ORDER_DEAL.sql
mysql -u user -pdbname password < CREATE_V_ORDER_DETAILS.sql
mysql -u user -pdbname password < CREATE_V_PURCHASE_DEAL.sql
mysql -u user -pdbname password < CREATE_V_PURCHASE_DETAILS.sql
mysql -u user -pdbname password < CREATE_V_QUOTATION_DEAL.sql
mysql -u user -pdbname password < CREATE_V_QUOTATION_DETAILS.sql
mysql -u user -pdbname password < CREATE_V_SALES_DEAL.sql
mysql -u user -pdbname password < CREATE_V_SALES_DETAILS.sql
mysql -u user -pdbname password < CREATE_V_BILL_DEAL.sql

mysql -u user -pdbname password < DEFAULT_DATA.sql
