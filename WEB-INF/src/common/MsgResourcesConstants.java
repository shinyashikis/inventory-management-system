package common;

public class MsgResourcesConstants {

	/*
	 * 共通メッセージ
	 */

	/** {0}が正しく更新されました。*/
	public static final String COMMON_MSG_UPDATE = "COMMON.MSG.UPDATE";
	/** {0}が正しく削除されました。*/
	public static final String COMMON_MSG_DELETE = "COMMON.MSG.DELETE";

	/*
	 * ログイン画面
	 */

	/** ログイン認証に失敗しました。*/
	public static final String LOGIN_ERRMSG_LOGIN_FAIL = "LOGIN.ERRMSG.LOGIN_FAIL";
	/** 既にログインしています。*/
	public static final String LOGIN_ERRMSG_ALREADY_LOGIN = "LOGIN.ERRMSG.ALREADY_LOGIN";
	/** 既に他のユーザがログインしています。*/
	public static final String LOGIN_ERRMSG_OTHER_LOGIN = "LOGIN.ERRMSG.OTHER_LOGIN";

	/*
	 * ログアウト画面
	 */

	/** ログアウトしました。*/
	public static final String LOGOUT_MSG_LOGOUT = "LOGOUT.MSG.LOGOUT";

	/*
	 * メニュー画面
	 */
	/** 商品マスタが登録されていません。*/
	public static final String MENU_ERRMSG_NOITEM = "MENU.ERRMSG.NOITEM";

	/*
	 * 基本設定画面
	 */
	/** 基本設定は他のユーザに変更されています。*/
	public static final String BASIC_ERRMSG_LOCK = "BASIC.ERRMSG.LOCK";

	/*
	 * 担当者登録画面
	 */
	/** 担当者コード[{0}]は既に使用されています。*/
	public static final String STAFF_UPDATE_ERRMSG_DUPLICATE = "STAFF_UPDATE.ERRMSG.DUPLICATE";
	/** 担当者[担当者コード:{0}]は他のユーザに変更されています。*/
	public static final String STAFF_UPDATE_ERRMSG_LOCK = "STAFF_UPDATE.ERRMSG.LOCK";


	/*
	 * 商品登録
	 */
	/** 商品コード[{0}]は既に使用されています。*/
	public static final String ITEM_UPDATE_ERRMSG_DUPLICATE = "ITEM_UPDATE.ERRMSG.DUPLICATE";
	/** 商品[商品コード:{0}]は他のユーザに変更されています。 */
	public static final String ITEM_UPDATE_ERRMSG_LOCK = "ITEM_UPDATE.ERRMSG.LOCK";

	/*
	 * 商品分類登録
	 */
	/** 商品分類コード[{0}]は既に使用されています。*/
	public static final String ITEM_CLASS_UPDATE_ERRMSG_DUPLICATE = "ITEM_CLASS_UPDATE.ERRMSG.DUPLICATE";
	/** 商品分類[{0}]は他のユーザに変更されています。*/
	public static final String ITEM_CLASS_UPDATE_ERRMSG_LOCK = "ITEM_CLASS_UPDATE.ERRMSG.LOCK";
	/** 使用中の商品分類は削除できません。*/
	public static final String ITEM_CLASS_UPDATE_ERRMSG_FK = "ITEM_CLASS_UPDATE.ERRMSG.FK";

	/*
	 * 得意先登録
	 */
	/** 得意先コード[{0}]は既に使用されています。*/
	public static final String CUSTOMER_UPDATE_ERRMSG_DUPLICATE = "CUSTOMER_UPDATE.ERRMSG.DUPLICATE";
	/** 得意先[得意先コード:{0}]は他のユーザに変更されています。 */
	public static final String CUSTOMER_UPDATE_ERRMSG_LOCK = "CUSTOMER_UPDATE.ERRMSG.LOCK";

	/*
	 * 得意先分類登録
	 */
	/** 得意先分類コード[{0}]は既に使用されています。*/
	public static final String CUSTOMER_CLASS_UPDATE_ERRMSG_DUPLICATE = "CUSTOMER_CLASS_UPDATE.ERRMSG.DUPLICATE";
	/** 得意先分類[{0}]は他のユーザに変更されています。*/
	public static final String CUSTOMER_CLASS_UPDATE_ERRMSG_LOCK = "CUSTOMER_CLASS_UPDATE.ERRMSG.LOCK";
	/** 使用中の得意先分類は削除できません。*/
	public static final String CUSTOMER_CLASS_UPDATE_ERRMSG_FK = "CUSTOMER_CLASS_UPDATE.ERRMSG.FK";

	/*
	 * 仕入先登録
	 */
	/** 仕入先コード[{0}]は既に使用されています。*/
	public static final String SUPPLIER_UPDATE_ERRMSG_DUPLICATE = "SUPPLIER_UPDATE.ERRMSG.DUPLICATE";
	/** 仕入先[仕入先コード:{0}]は他のユーザに変更されています。 */
	public static final String SUPPLIER_UPDATE_ERRMSG_LOCK = "SUPPLIER_UPDATE.ERRMSG.LOCK";

	/*
	 * 仕入先分類登録
	 */
	/** 仕入先分類コード[{0}]は既に使用されています。*/
	public static final String SUPPLIER_CLASS_UPDATE_ERRMSG_DUPLICATE = "SUPPLIER_CLASS_UPDATE.ERRMSG.DUPLICATE";
	/** 仕入先分類[{0}]は他のユーザに変更されています。*/
	public static final String SUPPLIER_CLASS_UPDATE_ERRMSG_LOCK = "SUPPLIER_CLASS_UPDATE.ERRMSG.LOCK";
	/** 使用中の仕入先分類は削除できません。*/
	public static final String SUPPLIER_CLASS_UPDATE_ERRMSG_FK = "SUPPLIER_CLASS_UPDATE.ERRMSG.FK";

	/*
	 * 伝票
	 */
	/** 伝票で使用されている商品[商品コード:{0}]は削除できません。 */
	public static final String VOUCHER_ITEM_ERRMSG_NON_DELETE_ALREADY_USE_ITEM = "VOUCHER_ITEM_ERRMSG_NON_DELETE_ALREADY_USE_ITEM";
	/** 商品[商品コード:{0}]は存在しません。 */
	public static final String VOUCHER_ITEM_ERRMSG_NOEXIST = "VOUCHER_ITEM_ERRMSG_NOEXIST";
	/** 取引先[取引先コード:{0}]は存在しません。 */
	public static final String VOUCHER_DEAL_ERRMSG_NOEXIST = "VOUCHER_DEAL_ERRMSG_NOEXIST";

}
