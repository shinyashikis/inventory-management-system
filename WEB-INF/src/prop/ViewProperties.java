package prop;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

import fw.common.prop.AbstractProperties;
import fw.common.util.CommonUtil;

public class ViewProperties extends AbstractProperties {

	public static final String VALUE = "value";
	public static final String DISP_VALUE = "dispValue";
	public static final String CALC_VALUE = "calcValue";

	public static final String DECIMAL_DIGIT = "DECIMAL_DIGIT";
	public static final String TAX_SETTING_KAZEI = "TAX_SETTING_KAZEI";
	public static final String TAX_SETTING_HIKAZEI = "TAX_SETTING_HIKAZEI";
	public static final String CALC_UCHIZEI = "CALC_UCHIZEI";
	public static final String CALC_SOTOZEI = "CALC_SOTOZEI";
	public static final String CALC_HASU_SHISYAGONYU = "CALC_HASU_SHISYAGONYU";
	public static final String CALC_HASU_KIRIAGE = "CALC_HASU_KIRIAGE";
	public static final String CALC_HASU_KIRISUTE = "CALC_HASU_KIRISUTE";
	public static final String KEISYO_SAMA = "KEISYO_SAMA";
	public static final String KEISYO_ONCYU = "KEISYO_ONCYU";
	public static final String KEISYO_TONO = "KEISYO_TONO";
	public static final String DEAL_DIVISION_CASH = "DEAL_DIVISION_CASH";
	public static final String DEAL_DIVISION_KAKE = "DEAL_DIVISION_KAKE";
	public static final String KESSAI_DIVISION_CASH = "KESSAI_DIVISION_CASH";
	public static final String KESSAI_DIVISION_FURIKOMI = "KESSAI_DIVISION_FURIKOMI";
	public static final String KESSAI_DIVISION_KOGITTE = "KESSAI_DIVISION_KOGITTE";
	public static final String KESSAI_DIVISION_TEGATA = "KESSAI_DIVISION_TEGATA";
	public static final String KESSAI_DIVISION_SOUSAI = "KESSAI_DIVISION_SOUSAI";
	public static final String KESSAI_DIVISION_ETC = "KESSAI_DIVISION_ETC";
	public static final String SALE_PRICE_DIVISION_1 = "SALE_PRICE_DIVISION_1";
	public static final String SALE_PRICE_DIVISION_2 = "SALE_PRICE_DIVISION_2";
	public static final String SALE_PRICE_DIVISION_3 = "SALE_PRICE_DIVISION_3";
	public static final String SALE_PRICE_DIVISION_4 = "SALE_PRICE_DIVISION_4";
	public static final String SALE_PRICE_DIVISION_5 = "SALE_PRICE_DIVISION_5";
	public static final String SHIMEBI_MONTHLY_1 = "SHIMEBI_MONTHLY_1";
	public static final String SHIMEBI_MONTHLY_5 = "SHIMEBI_MONTHLY_5";
	public static final String SHIMEBI_MONTHLY_10 = "SHIMEBI_MONTHLY_10";
	public static final String SHIMEBI_MONTHLY_15 = "SHIMEBI_MONTHLY_15";
	public static final String SHIMEBI_MONTHLY_20 = "SHIMEBI_MONTHLY_20";
	public static final String SHIMEBI_MONTHLY_25 = "SHIMEBI_MONTHLY_25";
	public static final String SHIMEBI_MONTHLY_END = "SHIMEBI_MONTHLY_END";
	public static final String SHIMEBI_KESSAI_THIS = "SHIMEBI_KESSAI_THIS";
	public static final String SHIMEBI_KESSAI_NEXT = "SHIMEBI_KESSAI_NEXT";
	public static final String SHIMEBI_KESSAI_TWO = "SHIMEBI_KESSAI_TWO";
	public static final String SHIMEBI_KESSAI_THREE = "SHIMEBI_KESSAI_THREE";
	public static final String SHIMEBI_KESSAI_FOUR = "SHIMEBI_KESSAI_FOUR";
	public static final String SHIMEBI_KESSAI_FIVE = "SHIMEBI_KESSAI_FIVE";
	public static final String SHIMEBI_KESSAI_SIX = "SHIMEBI_KESSAI_SIX";
	public static final String KESSAI_MONTHLY_1 = "KESSAI_MONTHLY_1";
	public static final String KESSAI_MONTHLY_5 = "KESSAI_MONTHLY_5";
	public static final String KESSAI_MONTHLY_10 = "KESSAI_MONTHLY_10";
	public static final String KESSAI_MONTHLY_15 = "KESSAI_MONTHLY_15";
	public static final String KESSAI_MONTHLY_20 = "KESSAI_MONTHLY_20";
	public static final String KESSAI_MONTHLY_25 = "KESSAI_MONTHLY_25";
	public static final String KESSAI_MONTHLY_END = "KESSAI_MONTHLY_END";

	public static final String DEAL_KIND_CUSTOMER = "DEAL_KIND_CUSTOMER";
	public static final String DEAL_KIND_SUPPLIER = "DEAL_KIND_SUPPLIER";

	public static final String VOUCHER_KIND_QUOTATION = "VOUCHER_KIND_QUOTATION";
	public static final String VOUCHER_KIND_ORDER = "VOUCHER_KIND_ORDER";
	public static final String VOUCHER_KIND_SALES = "VOUCHER_KIND_SALES";
	public static final String VOUCHER_KIND_PURCHASE = "VOUCHER_KIND_PURCHASE";
	public static final String VOUCHER_KIND_ACCOUNT_RECEIVABLE_BILL = "VOUCHER_KIND_ACCOUNT_RECEIVABLE_BILL";
	public static final String VOUCHER_KIND_BILL = "VOUCHER_KIND_BILL";

	public static final String PRINT_KIND_QUOTATION = "PRINT_KIND_QUOTATION";
	public static final String PRINT_KIND_ORDER = "PRINT_KIND_ORDER";
	public static final String PRINT_KIND_SALES = "PRINT_KIND_SALES";
	public static final String PRINT_KIND_STATEMENT_OF_DELIVERY = "PRINT_KIND_STATEMENT_OF_DELIVERY";
	public static final String PRINT_KIND_BILL = "PRINT_KIND_BILL";
	public static final String PRINT_KIND_RECEIPT = "PRINT_KIND_RECEIPT";
	public static final String PRINT_KIND_PURCHASE = "PRINT_KIND_PURCHASE";
	public static final String PRINT_KIND_ACKNOWLEDGMENT = "PRINT_KIND_ACKNOWLEDGMENT";
	public static final String PRINT_KIND_BILL_DETAIL = "PRINT_KIND_BILL_DETAIL";

	public static final String BOOK_KIND_ITEM = "BOOK_KIND_ITEM";
	public static final String BOOK_KIND_SALES = "BOOK_KIND_SALES";
	public static final String BOOK_KIND_PURCHASE = "BOOK_KIND_PURCHASE";
	public static final String BOOK_KIND_VOUCHER_LIST = "BOOK_KIND_VOUCHER_LIST";

	public static final String BOOK_DISP_TARGET_ITEM_STOCK = "BOOK_DISP_TARGET_ITEM_STOCK";
	public static final String BOOK_DISP_TARGET_STOCK_TRANSITION = "BOOK_DISP_TARGET_STOCK_TRANSITION";
	public static final String BOOK_DISP_TARGET_SHIP_SUM = "BOOK_DISP_TARGET_SHIP_SUM";
	public static final String BOOK_DISP_TARGET_STORAGE_SUM = "BOOK_DISP_TARGET_STORAGE_SUM";
	public static final String BOOK_DISP_TARGET_SHIP_BOOK = "BOOK_DISP_TARGET_SHIP_BOOK";
	public static final String BOOK_DISP_TARGET_STORAGE_BOOK = "BOOK_DISP_TARGET_STORAGE_BOOK";
	public static final String BOOK_DISP_TARGET_SALES_PRICE_AVE = "BOOK_DISP_TARGET_SALES_PRICE_AVE";
	public static final String BOOK_DISP_TARGET_PURCHASE_PRICE_AVE = "BOOK_DISP_TARGET_PURCHASE_PRICE_AVE";
	public static final String BOOK_DISP_TARGET_GROSS = "BOOK_DISP_TARGET_GROSS";
	public static final String BOOK_DISP_TARGET_PROFIT = "BOOK_DISP_TARGET_PROFIT";

	public static final String BOOK_DISP_TARGET_SALES_TOTAL = "BOOK_DISP_TARGET_SALES_TOTAL";
	public static final String BOOK_DISP_TARGET_SALES_BOOK = "BOOK_DISP_TARGET_SALES_BOOK";
	public static final String BOOK_DISP_TARGET_URIKAKE_BOOK = "BOOK_DISP_TARGET_URIKAKE_BOOK";

	public static final String BOOK_DISP_TARGET_PURCHASE_TOTAL = "BOOK_DISP_TARGET_PURCHASE_TOTAL";
	public static final String BOOK_DISP_TARGET_PURCHASE_BOOK = "BOOK_DISP_TARGET_PURCHASE_BOOK";
	public static final String BOOK_DISP_TARGET_KAIKAKE_BOOK = "BOOK_DISP_TARGET_KAIKAKE_BOOK";

	public static final String BOOK_DISP_TARGET_QUOTATION = "BOOK_DISP_TARGET_QUOTATION";
	public static final String BOOK_DISP_TARGET_BILL_TOTAL = "BOOK_DISP_TARGET_BILL_TOTAL";
	public static final String BOOK_DISP_TARGET_ACKNOWLEDGMENT = "BOOK_DISP_TARGET_ACKNOWLEDGMENT";
	public static final String BOOK_DISP_TARGET_ORDER = "BOOK_DISP_TARGET_ORDER";

	public static final String BOOK_SELECT_DATE_RANGE_DATE = "BOOK_SELECT_DATE_RANGE_DATE";
	public static final String BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION = "BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION";
	public static final String BOOK_SELECT_DATE_RANGE_YEAR_SUM = "BOOK_SELECT_DATE_RANGE_YEAR_SUM";

	public static final String BOOK_SELECT_SORT_ASC = "BOOK_SELECT_SORT_ASC";
	public static final String BOOK_SELECT_SORT_DSC = "BOOK_SELECT_SORT_DSC";

	public static final String BOOK_SELECT_SORT_KIND_KANA = "BOOK_SELECT_SORT_KIND_KANA";
	public static final String BOOK_SELECT_SORT_KIND_CODE = "BOOK_SELECT_SORT_KIND_CODE";


	public static final String BOOK_DISP_DETAIL_COUNT = "BOOK_DISP_DETAIL_COUNT";
	public static final String BOOK_DISP_DETAIL_PRICE = "BOOK_DISP_DETAIL_PRICE";

	public static final String BOOK_DISP_DETAIL_VOUCHER = "BOOK_DISP_DETAIL_VOUCHER";
	public static final String BOOK_DISP_DETAIL_DETAIL = "BOOK_DISP_DETAIL_DETAIL";


	public static final String BOOK_SELECT_DISP_SORT_ITEM = "BOOK_SELECT_DISP_SORT_ITEM";
	public static final String BOOK_SELECT_DISP_SORT_ITEM_CLASS = "BOOK_SELECT_DISP_SORT_ITEM_CLASS";
	public static final String BOOK_SELECT_DISP_SORT_VOUCHER_DATE = "BOOK_SELECT_DISP_SORT_VOUCHER_DATE";
	public static final String BOOK_SELECT_DISP_SORT_VOUCHER_NO = "BOOK_SELECT_DISP_SORT_VOUCHER_NO";

	public static final String BOOK_SELECT_DISP_SORT_CUSTOMER = "BOOK_SELECT_DISP_SORT_CUSTOMER";
	public static final String BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS = "BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS";
	public static final String BOOK_SELECT_DISP_SORT_STAFF = "BOOK_SELECT_DISP_SORT_STAFF";

	public static final String BOOK_SELECT_DISP_SORT_SUPPLIER = "BOOK_SELECT_DISP_SORT_SUPPLIER";
	public static final String BOOK_SELECT_DISP_SORT_SUPPLIER_CLASS = "BOOK_SELECT_DISP_SORT_SUPPLIER_CLASS";

	private static final String FILE = "view.properties";
	private static ViewProperties me = new ViewProperties();

	private ViewProperties() {}

	public static ViewProperties getInstance() {
		return me;
	}

	/**
	 * @see common.prop.AbstractProperties#load()
	 */
	@Override
	protected void load() throws IOException {
		load(new BufferedInputStream(getClass().getResourceAsStream(FILE)));
	}

	/**
	 * @param key
	 * @param name
	 * @return
	 * @throws RuntimeException
	 */
	public String getValue(String key, String name) throws RuntimeException {
		try {
			return CommonUtil.getPropertyValue(getValue(key), name);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param keys
	 * @param targetName
	 * @param fromName
	 * @param fromValue
	 * @return
	 * @throws RuntimeException
	 */
	public String getValue(List<String> keys, String targetName, String fromName, String fromValue) throws RuntimeException {
		for (String key : keys) {
			String value = getValue(key, fromName);
			if (value != null && value.equals(fromValue)) {
				return getValue(key, targetName);
			}
		}
		return null;
	}
}
