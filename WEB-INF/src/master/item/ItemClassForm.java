package master.item;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * <p>商品分類フォーム</p>
 */
public class ItemClassForm extends ValidatorForm {
	private static final long serialVersionUID = -5618070919119440482L;

	private String code;
	private String name;
	private String kana;
	private String updDate;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Map m = request.getParameterMap();
		if (m.size() == 0 || m.get("back") != null) {
			return new ActionErrors();
		}
		return super.validate(mapping, request);
	}

}
