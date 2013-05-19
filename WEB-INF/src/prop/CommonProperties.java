package prop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import fw.common.prop.AbstractProperties;

public class CommonProperties extends AbstractProperties {

	/*
	 * 帳票
	 */
	public static final String REPORT_FORM_DIR = "report.form.dir";
	public static final String REPORT_FORM_FILE_QUOTATION = "report.form.file.quotation";
	public static final String REPORT_FORM_FILE_ORDER = "report.form.file.order";
	public static final String REPORT_FORM_FILE_SALES = "report.form.file.sales";
	public static final String REPORT_FORM_FILE_STATEMENT_OF_DELIVERY = "report.form.file.statement_of_delivery";
	public static final String REPORT_FORM_FILE_BILL = "report.form.file.bill";
	public static final String REPORT_FORM_FILE_RECEIPT = "report.form.file.receipt";
	public static final String REPORT_FORM_FILE_PURCHASE = "report.form.file.purchase";
	public static final String REPORT_FORM_FILE_BILL_DETAIL = "report.form.file.bill_detail";
	public static final String REPORT_FORM_FILE_ACKNOWLEDGMENT = "report.form.file.acknowledgment";

	/*
	 * 帳票(シート名)
	 */
	public static final String REPORT_FORM_SHEET_QUOTATION = "report.form.sheet.quotation";
	public static final String REPORT_FORM_SHEET_ORDER = "report.form.sheet.order";
	public static final String REPORT_FORM_SHEET_SALES = "report.form.sheet.sales";
	public static final String REPORT_FORM_SHEET_STATEMENT_OF_DELIVERY = "report.form.sheet.statement_of_delivery";
	public static final String REPORT_FORM_SHEET_BILL = "report.form.sheet.bill";
	public static final String REPORT_FORM_SHEET_RECEIPT = "report.form.sheet.receipt";
	public static final String REPORT_FORM_SHEET_PURCHASE = "report.form.sheet.purchase";
	public static final String REPORT_FORM_SHEET_BILL_DETAIL = "report.form.sheet.bill_detail";
	public static final String REPORT_FORM_SHEET_ACKNOWLEDGMENT = "report.form.sheet.acknowledgment";

	/*
	 * 帳簿
	 */
	public static final String BOOK_FORM_DIR = "book.form.dir";
	public static final String BOOK_FORM_FILE_ITEM_STOCK = "book.form.file.item_stock";
	public static final String BOOK_FORM_FILE_STOCK_TRANSITION = "book.form.file.stock_transition";
	public static final String BOOK_FORM_FILE_SHIP_SUM = "book.form.file.ship_sum";
	public static final String BOOK_FORM_FILE_STORAGE_SUM = "book.form.file.storage_sum";
	public static final String BOOK_FORM_FILE_SHIP_BOOK = "book.form.file.ship_book";
	public static final String BOOK_FORM_FILE_STORAGE_BOOK = "book.form.file.strage_book";
	public static final String BOOK_FORM_FILE_SALES_PRICE_AVE = "book.form.file.sales_price_ave";
	public static final String BOOK_FORM_FILE_PURCHASE_PRICE_AVE = "book.form.file.purchase_price_ave";
	public static final String BOOK_FORM_FILE_GROSS = "book.form.file.gross";
	public static final String BOOK_FORM_FILE_PROFIT = "book.form.file.profit";
	public static final String BOOK_FORM_FILE_SALES_TOTAL = "book.form.file.sales_total";
	public static final String BOOK_FORM_FILE_SALES_BOOK = "book.form.file.sales_book";
	public static final String BOOK_FORM_FILE_URIKAKE_BOOK = "book.form.file.urikake_book";
	public static final String BOOK_FORM_FILE_PURCHASE_TOTAL = "book.form.file.purchase_total";
	public static final String BOOK_FORM_FILE_PURCHASE_BOOK = "book.form.file.purchase_book";
	public static final String BOOK_FORM_FILE_KAIKAKE_BOOK = "book.form.file.kaikake_book";
	public static final String BOOK_FORM_FILE_QUOTATION = "book.form.file.quotation";
	public static final String BOOK_FORM_FILE_BILL_TOTAL = "book.form.file.bill_total";
	public static final String BOOK_FORM_FILE_ACKNOWLEDMENT = "book.form.file.acknowledgment";
	public static final String BOOK_FORM_FILE_ORDER = "book.form.file.order";

	/*
	 * 帳簿(シート名)
	 */
	public static final String BOOK_FORM_SHEET_ITEM_STOCK_COUNT = "book.form.sheet.item_stock_count";
	public static final String BOOK_FORM_SHEET_ITEM_STOCK_PRICE = "book.form.sheet.item_stock_price";
	public static final String BOOK_FORM_SHEET_STOCK_TRANSITION_COUNT = "book.form.sheet.stock_transition_count";
	public static final String BOOK_FORM_SHEET_STOCK_TRANSITION_PRICE = "book.form.sheet.stock_transition_price";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_COUNT_DATE = "book.form.sheet.ship_sum_count_date";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_COUNT_MONTHLY = "book.form.sheet.ship_sum_count_monthly";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_COUNT_YEAR = "book.form.sheet.ship_sum_count_year";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_PRICE_DATE = "book.form.sheet.ship_sum_price_date";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_PRICE_MONTHLY = "book.form.sheet.ship_sum_price_monthly";
	public static final String BOOK_FORM_SHEET_SHIP_SUM_PRICE_YEAR = "book.form.sheet.ship_sum_price_year";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_COUNT_DATE = "book.form.sheet.storage_sum_count_date";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_COUNT_MONTHLY = "book.form.sheet.storage_sum_count_monthly";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_COUNT_YEAR = "book.form.sheet.storage_sum_count_year";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_PRICE_DATE = "book.form.sheet.storage_sum_price_date";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_PRICE_MONTHLY = "book.form.sheet.storage_sum_price_monthly";
	public static final String BOOK_FORM_SHEET_STORAGE_SUM_PRICE_YEAR = "book.form.sheet.storage_sum_price_year";
	public static final String BOOK_FORM_SHEET_SHIP_BOOK_DATE = "book.form.sheet.ship_book_date";
	public static final String BOOK_FORM_SHEET_SHIP_BOOK_YEAR = "book.form.sheet.ship_book_year";
	public static final String BOOK_FORM_SHEET_STORAGE_BOOK_DATE = "book.form.sheet.storage_book_date";
	public static final String BOOK_FORM_SHEET_STORAGE_BOOK_YEAR = "book.form.sheet.storage_book_year";
	public static final String BOOK_FORM_SHEET_SALES_PRICE_AVE_DATE = "book.form.sheet.sales_price_ave_date";
	public static final String BOOK_FORM_SHEET_SALES_PRICE_AVE_MONTHLY = "book.form.sheet.sales_price_ave_monthly";
	public static final String BOOK_FORM_SHEET_SALES_PRICE_AVE_YEAR = "book.form.sheet.sales_price_ave_year";
	public static final String BOOK_FORM_SHEET_PURCHASE_PRICE_AVE_DATE = "book.form.sheet.purchase_price_ave_date";
	public static final String BOOK_FORM_SHEET_PURCHASE_PRICE_AVE_MONTHLY = "book.form.sheet.purchase_price_ave_monthly";
	public static final String BOOK_FORM_SHEET_PURCHASE_PRICE_AVE_YEAR = "book.form.sheet.purchase_price_ave_year";
	public static final String BOOK_FORM_SHEET_GROSS_DATE = "book.form.sheet.gross_date";
	public static final String BOOK_FORM_SHEET_GROSS_MONTHLY = "book.form.sheet.gross_monthly";
	public static final String BOOK_FORM_SHEET_GROSS_YEAR = "book.form.sheet.gross_year";
	public static final String BOOK_FORM_SHEET_PROFIT_DATE = "book.form.sheet.profit_date";
	public static final String BOOK_FORM_SHEET_PROFIT_MONTHLY = "book.form.sheet.profit_monthly";
	public static final String BOOK_FORM_SHEET_PROFIT_YEAR = "book.form.sheet.profit_year";
	public static final String BOOK_FORM_SHEET_SALES_TOTAL_DATE = "book.form.sheet.sales_total_date";
	public static final String BOOK_FORM_SHEET_SALES_TOTAL_MONTHLY = "book.form.sheet.sales_total_monthly";
	public static final String BOOK_FORM_SHEET_SALES_TOTAL_YEAR = "book.form.sheet.sales_total_year";
	public static final String BOOK_FORM_SHEET_SALES_BOOK_VOUCHER_DATE = "book.form.sheet.sales_book_voucher_date";
	public static final String BOOK_FORM_SHEET_SALES_BOOK_DETAIL_DATE = "book.form.sheet.sales_book_detail_date";
	public static final String BOOK_FORM_SHEET_SALES_BOOK_VOUCHER_YEAR = "book.form.sheet.sales_book_voucher_year";
	public static final String BOOK_FORM_SHEET_SALES_BOOK_DETAIL_YEAR = "book.form.sheet.sales_book_detail_year";
	public static final String BOOK_FORM_SHEET_URIKAKE_BOOK_VOUCHER_DATE = "book.form.sheet.urikake_book_voucher_date";
	public static final String BOOK_FORM_SHEET_URIKAKE_BOOK_DETAIL_DATE = "book.form.sheet.urikake_book_detail_date";
	public static final String BOOK_FORM_SHEET_URIKAKE_BOOK_VOUCHER_YEAR = "book.form.sheet.urikake_book_voucher_year";
	public static final String BOOK_FORM_SHEET_URIKAKE_BOOK_DETAIL_YEAR = "book.form.sheet.urikake_book_detail_year";
	public static final String BOOK_FORM_SHEET_PURCHASE_TOTAL_DATE = "book.form.sheet.purchase_total_date";
	public static final String BOOK_FORM_SHEET_PURCHASE_TOTAL_MONTHLY = "book.form.sheet.purchase_total_monthly";
	public static final String BOOK_FORM_SHEET_PURCHASE_TOTAL_YEAR = "book.form.sheet.purchase_total_year";
	public static final String BOOK_FORM_SHEET_PURCHASE_BOOK_VOUCHER_DATE = "book.form.sheet.purchase_book_voucher_date";
	public static final String BOOK_FORM_SHEET_PURCHASE_BOOK_DETAIL_DATE = "book.form.sheet.purchase_book_detail_date";
	public static final String BOOK_FORM_SHEET_PURCHASE_BOOK_VOUCHER_YEAR = "book.form.sheet.purchase_book_voucher_year";
	public static final String BOOK_FORM_SHEET_PURCHASE_BOOK_DETAIL_YEAR = "book.form.sheet.purchase_book_detail_year";
	public static final String BOOK_FORM_SHEET_KAIKAKE_BOOK_VOUCHER_DATE = "book.form.sheet.kaikake_book_voucher_date";
	public static final String BOOK_FORM_SHEET_KAIKAKE_BOOK_DETAIL_DATE = "book.form.sheet.kaikake_book_detail_date";
	public static final String BOOK_FORM_SHEET_KAIKAKE_BOOK_VOUCHER_YEAR = "book.form.sheet.kaikake_book_voucher_year";
	public static final String BOOK_FORM_SHEET_KAIKAKE_BOOK_DETAIL_YEAR = "book.form.sheet.kaikake_book_detail_year";
	public static final String BOOK_FORM_SHEET_QUOTATION = "book.form.sheet.quotation";
	public static final String BOOK_FORM_SHEET_BILL_TOTAL = "book.form.sheet.bill_total";
	public static final String BOOK_FORM_SHEET_ACKNOWLEDMENT = "book.form.sheet.acknowledgment";
	public static final String BOOK_FORM_SHEET_ORDER = "book.form.sheet.order";


	public static final String TMP_DIR = "tmp.dir";

	// TODO テスト用
	private static final String PATH = "C:\\cana\\localtest\\def";
	private static final String FILE =
		new StringBuilder(PATH).append(File.separator).append("common.properties").toString();

	private static CommonProperties me = new CommonProperties();

	private CommonProperties() {}

	public static CommonProperties getInstance() {
		return me;
	}

	/**
	 * @see common.prop.AbstractProperties#load()
	 */
	@Override
	protected void load() throws IOException {
		load(new BufferedInputStream(new FileInputStream(new File(FILE))));
	}
}
