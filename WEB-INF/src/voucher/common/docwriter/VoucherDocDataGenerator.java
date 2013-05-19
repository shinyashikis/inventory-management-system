package voucher.common.docwriter;

import master.basic.bean.BasicBean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;

import voucher.common.bean.DealBean;
import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;

import fw.common.docwriter.DocWriterException;

/**
 * <p>伝票文書ファイルデータ作成クラス</p>
 */
public class VoucherDocDataGenerator {

	/**
	 * <p>伝票文書ファイルデータ作成</p>
	 * @param basic
	 * @param voucher
	 * @return
	 * @throws DocWriterException
	 */
	public static VoucherDocData generate(BasicBean basic, VoucherBean voucher) throws DocWriterException {
		try {
			VoucherDocData docData = new VoucherDocData();

			// 自社情報の反映
			BeanUtils.copyProperties(docData, SerializationUtils.clone(basic));

			// 伝票情報の反映
			docData.setVoucherDate(voucher.getVoucherDate());
			docData.setVoucherNo(voucher.getVoucherNo());
			docData.setVoucherName(voucher.getVoucherName());
			docData.setPaymentLimit(voucher.getPaymentLimit());
			docData.setPaymentPlace(voucher.getPaymentPlace());
			docData.setPaymentCondition(voucher.getPaymentCondition());
			docData.setExpirationDate(voucher.getExpirationDate());
			docData.setMemo(voucher.getMemo());
			docData.setDiscount(voucher.getDiscount().toString());
			docData.setEtc1(voucher.getEtc1());
			docData.setEtc2(voucher.getEtc2());
			docData.setReceiptDate(voucher.getReceiptDate());
			docData.setReceiptNo(voucher.getReceiptNo());
			docData.setProviso(voucher.getProviso());
			docData.setTaxExcludedAmount(voucher.getTaxExcludedAmount().toString());
			docData.setTax(voucher.getTax().toString());
			docData.setTaxIncludedAmount(voucher.getTaxIncludedAmount().toString());
			docData.setAmount(voucher.getAmount().toString());

			// 取引先情報の反映
			docData.setDeal(generate(voucher.getDealBean()));

			// 明細情報の反映
			for (DetailBean detailBean : voucher.getDetailList()) {
				docData.addDetail(generate(detailBean));
			}

			return docData;

		} catch (Exception e) {
			throw new DocWriterException(e);
		}
	}

	/**
	 * <p>伝票文書ファイルデータ作成(取引先情報)</p>
	 *
	 * @param deal
	 * @return
	 */
	private static DealDocData generate(DealBean deal) {
		// 取引先情報の反映
		DealDocData docData = new DealDocData();
		docData.setPostCode(deal.getPostCode());
		docData.setAddr1(deal.getAddr1());
		docData.setAddr2(deal.getAddr2());
		docData.setDealName(deal.getDealName());
		docData.setDealBusyo(deal.getDealBusyo());
		docData.setDealStaff(deal.getDealStaff());
		docData.setDealStaffKeisyo(deal.getDealStaffKeisyo());
		docData.setTel(deal.getTel());
		docData.setBusyo(deal.getBusyo());
		docData.setStaff(deal.getStaff());
		docData.setDealDivisionCode(deal.getDealDivisionCode());
		return docData;
	}

	/**
	 * <p>伝票文書ファイルデータ作成(明細情報)</p>
	 *
	 * @param detail
	 * @return
	 */
	private static DetailDocData generate(DetailBean detail) {
		// 明細情報の反映
		DetailDocData docData = new DetailDocData();
		docData.setItemName(detail.getItemName());
		docData.setItemKikaku(detail.getItemKikaku());
		docData.setItemCount(detail.getItemCount());
		docData.setItemUnit(detail.getItemUnit());
		docData.setItemPrice(detail.getItemPrice());
		docData.setItemMemo(detail.getItemMemo());
		docData.setItemUnitPrice(detail.getItemUnitPrice());
		docData.setSalesDate(detail.getSalesDate());
		docData.setSalesNo(detail.getSalesNo());
		return docData;
	}

}
