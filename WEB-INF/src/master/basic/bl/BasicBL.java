package master.basic.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import master.basic.BasicForm;
import master.basic.bean.BasicBean;
import master.basic.dao.MBasicDAO;
import master.basic.dto.MBasicDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import common.MsgResourcesConstants;
import fw.core.base.TransactionInfo;

/**
 * <p>基本設定ビジネスロジック</p>
 */
public class BasicBL {

	private static final String CODE = "1";

	/**
	 * <p>基本設定フォーム取得</p>
	 * @return BasicForm
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BasicForm getBasicForm() throws IOException, SQLException {
		BasicForm basicForm = new BasicForm();

		for (CommonDTO ret : getBasicMaster()) {
			MBasicDTO dto = (MBasicDTO)ret;
			basicForm.setAccountName(dto.getAccountName());
			basicForm.setAccountNo(dto.getAccountNo());
			basicForm.setAddr1(dto.getAddr1());
			basicForm.setAddr2(dto.getAddr2());
			basicForm.setBank(dto.getBank());
			basicForm.setBranch(dto.getBranch());
			// TODO 定数化
			basicForm.setCalc(dto.getCalc() == null ? "0" : dto.getCalc().toString());
			basicForm.setCalcHasu(dto.getCalcHasu() == null ? "0" : dto.getCalcHasu().toString());
			basicForm.setFax(dto.getFax());
			basicForm.setKessan(dto.getKessan().toString());
			basicForm.setKisyuYear(dto.getKisyuYear().toString());
			basicForm.setKisyuMonth(dto.getKisyuMonth().toString());
			basicForm.setKisyuDate(dto.getKisyuDate().toString());
			basicForm.setKimatsuYear(dto.getKimatsuYear().toString());
			basicForm.setKimatsuMonth(dto.getKimatsuMonth().toString());
			basicForm.setKimatsuDate(dto.getKimatsuDate().toString());
			basicForm.setName(dto.getName());
			basicForm.setPostCode1(dto.getPostCode1());
			basicForm.setPostCode2(dto.getPostCode2());
			basicForm.setTax(dto.getTax() == null ? "0" : dto.getTax().toString());
			basicForm.setTaxVal(dto.getTaxVal() == null ? null : dto.getTaxVal().toString());
			basicForm.setTel(dto.getTel());
			basicForm.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		}

		return basicForm;
	}

	/**
	 * <p>基本設定Bean取得</p>
	 * @return BasicBean
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BasicBean getBasicBean() throws IOException, SQLException {
		BasicBean bean = new BasicBean();

		for (CommonDTO ret : getBasicMaster()) {
			MBasicDTO dto = (MBasicDTO)ret;
			bean.setAccountName(dto.getAccountName());
			bean.setAccountNo(dto.getAccountNo());
			bean.setAddr1(dto.getAddr1());
			bean.setAddr2(dto.getAddr2());
			bean.setBank(dto.getBank());
			bean.setBranch(dto.getBranch());
			// TODO 定数化
			bean.setCalc(dto.getCalc() == null ? "0" : dto.getCalc().toString());
			bean.setCalcHasu(dto.getCalcHasu() == null ? "0" : dto.getCalcHasu().toString());
			bean.setFax(dto.getFax());
			bean.setKessan(dto.getKessan().toString());
			bean.setKisyuYear(dto.getKisyuYear().toString());
			bean.setKisyuMonth(dto.getKisyuMonth().toString());
			bean.setKisyuDate(dto.getKisyuDate().toString());
			bean.setKimatsuYear(dto.getKimatsuYear().toString());
			bean.setKimatsuMonth(dto.getKimatsuMonth().toString());
			bean.setKimatsuDate(dto.getKimatsuDate().toString());
			bean.setName(dto.getName());
			bean.setPostCode1(dto.getPostCode1());
			bean.setPostCode2(dto.getPostCode2());
			bean.setTax(dto.getTax() == null ? "0" : dto.getTax().toString());
			bean.setTaxVal(dto.getTaxVal() == null ? null : dto.getTaxVal().divide(BigDecimal.valueOf(100)));
			bean.setTel(dto.getTel());
			bean.setUpdDate(String.valueOf(dto.getUpdDate().getTime()));
		}
		return bean;
	}

	/**
	 * <p>基本設定マスタ取得</p>
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private static List<CommonDTO> getBasicMaster() throws IOException, SQLException {
		MBasicDAO dao = new MBasicDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MBasicDAO.SQLID001);
		return dao.select(sql, new ArrayList<Object>());
	}

	/**
	 * 基本設定Form⇒基本設定Bean変換
	 * @param form
	 * @return
	 */
	public static BasicBean convBasicFormToBasicBean(BasicForm form) {
		BasicBean bean = new BasicBean();
		try {
			BeanUtils.copyProperties(bean, form);
			return bean;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ActionMessages update(BasicForm form) throws IOException, SQLException {
		MBasicDAO dao = new MBasicDAO(TransactionInfo.getConnection());
		String sql = SQLProperties.getInstance().getValue(MBasicDAO.SQLID002);
		List<Object> params = new ArrayList<Object>();
		params.add(form.getName());
		params.add(form.getPostCode1());
		params.add(form.getPostCode2());
		params.add(form.getAddr1());
		params.add(form.getAddr2());
		params.add(form.getTel());
		params.add(form.getFax());
		params.add(form.getBank());
		params.add(form.getBranch());
		params.add(form.getAccountNo());
		params.add(form.getAccountName());
		params.add(form.getKisyuYear());
		params.add(form.getKisyuMonth());

		Calendar kimatsu = calcKimatsu(
				form.getKisyuYear(), form.getKisyuMonth(), form.getKisyuDate());
		params.add(kimatsu.get(Calendar.YEAR));
		params.add(kimatsu.get(Calendar.MONTH)+1);
		params.add(kimatsu.get(Calendar.DATE));

		params.add(form.getKessan());
		params.add(form.getTax());
		params.add(form.getTaxVal());
		params.add(form.getCalc());
		params.add(form.getCalcHasu());
		params.add(CODE);
		params.add(new Timestamp(Long.parseLong(form.getUpdDate())));

		if (dao.update(sql, params) == 0) {
			// 基本設定は他のユーザに変更されています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(MsgResourcesConstants.BASIC_ERRMSG_LOCK);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			return errors;
		}

		return null;
	}

	/**
	 * 期末日算出
	 * @param kisyuYear
	 * @param kisyuMonth
	 * @param kisyuDate
	 * @return
	 */
	public static Calendar calcKimatsu(String kisyuYear, String kisyuMonth, String kisyuDate) {
		Calendar cal = new GregorianCalendar(
				Integer.parseInt(kisyuYear),
				Integer.parseInt(kisyuMonth)-1,
				Integer.parseInt(kisyuDate));
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

		return cal;
	}
}
