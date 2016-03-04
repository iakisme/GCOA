package com.qylm.logic.fitting;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.common.MothedUtil;
import com.qylm.common.Tool;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dao.FittingLibraryDao;
import com.qylm.dao.FittingLibraryDetailDao;
import com.qylm.dao.FittingRepertoryDao;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingLibrary;
import com.qylm.entity.FittingLibraryDetail;
import com.qylm.entity.FittingRepertory;
import com.qylm.entity.User;
import com.qylm.exception.FittingLibraryException;

public class FittingLibraryLogic {

	@Autowired
	private FittingLibraryDao fittingLibraryDao;
	
	@Autowired
	private FittingLibraryDetailDao fittingLibraryDetailDao;
	
	@Autowired
	private FittingRepertoryDao fittingRepertoryDao;
	
	public void saveFittingLibrary(FittingLibrary fittingLibrary,
			List<FittingLibraryDetail> fittingLibraryDetailList) {
		fittingLibrary.setNumber(getNumber(fittingLibrary.getCreater()));
		fittingLibraryDao.saveEntity(fittingLibrary);
		if (fittingLibraryDetailList != null && !fittingLibraryDetailList.isEmpty()) {
			fittingLibraryDetailDao.saveEntityAll(fittingLibraryDetailList);
		}
		if (fittingLibrary.isState()) {
			try {
				storage(fittingLibrary, fittingLibraryDetailList);
			} catch (FittingLibraryException e) {
				
			}
		}
	}

	public void updateFittingLibrary(FittingLibrary fittingLibrary,
			List<FittingLibraryDetail> fittingLibraryDetailList) throws FittingLibraryException {
		fittingLibraryDao.updateEntity(fittingLibrary);
		if (fittingLibraryDetailList != null && !fittingLibraryDetailList.isEmpty()) {
			fittingLibraryDetailDao.saveOrUpdateAll(fittingLibraryDetailList);
		}
		if (fittingLibrary.isState()) {
			storage(fittingLibrary, fittingLibraryDetailList);
		}
	}
	
	/**
	 * 获取入库单编号，例子2015060400001
	 * @return
	 */
	private String getNumber(User user) {
		String number = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingLibrary.class);
		detachedCriteria.add(Restrictions.like(FittingLibrary.NUMBER, number, MatchMode.START));
		detachedCriteria.addOrder(Order.desc(FittingLibrary.NUMBER));
		MothedUtil.getBelongingUser(detachedCriteria, user);
		List<FittingLibrary> fittingLibraryList = fittingLibraryDao.getByDetachedCriteria(detachedCriteria);
		if (fittingLibraryList.isEmpty()) {
			number = number + "00001";
		} else {
			FittingLibrary fittingLibrary = fittingLibraryList.get(0);
			String number2 = fittingLibrary.getNumber();
			Integer valueOf = Integer.valueOf(number2.substring(10)) + 1;
			String value = valueOf.toString();
			StringBuilder sb = new StringBuilder(64);
			sb.append(number2.substring(0, 8));
			int i = (5 - value.length()) - 1;
			for (; i >= 0; i--) {
				sb.append(0);
			}
			sb.append(value);
			number = sb.toString();
		}
		return number;
	}
	
	private void storage(FittingLibrary fittingLibrary, List<FittingLibraryDetail> fittingLibraryDetailList) throws FittingLibraryException {
		// 更新库存
		if (fittingLibraryDetailList != null && !fittingLibraryDetailList.isEmpty()) {
			// 提取出本次配件信息及数量
			List<String> carNameList = new ArrayList<String>();
			List<String> fittingBrandList = new ArrayList<String>();
			List<String> fittingNameList = new ArrayList<String>();
			List<String> modelList = new ArrayList<String>();
			for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
				carNameList.add(fittingLibraryDetail.getCarName());
				fittingBrandList.add(fittingLibraryDetail.getFittingBrand());
				fittingNameList.add(fittingLibraryDetail.getFittingName());
				modelList.add(fittingLibraryDetail.getModel());
			}
			
			// 取出对应的所有库存
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingRepertory.class);
			detachedCriteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FittingRepertory.CAR_NAME, carNameList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_BRAND, fittingBrandList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_NAME, fittingNameList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.MODEL, modelList));
			MothedUtil.getBelongingUser(detachedCriteria, fittingLibrary.getCreater());
			List<FittingRepertory> fittingRepertoryList = fittingRepertoryDao.getByDetachedCriteria(detachedCriteria);
			boolean error = false;
			// 减少库存数量，如果库存数量不足就不允许操作并提示出对应的数据信息
			for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
				for (FittingRepertory fittingRepertory : fittingRepertoryList) {
					if (verification(fittingLibraryDetail, fittingRepertory.getFittingInfo())) {
						if (BigDecimalUtil.bigThanOrEqual(fittingRepertory.getRepertorySum(), fittingLibraryDetail.getQuantity())) {
							fittingRepertory.setRepertorySum(BigDecimalUtil.subtract(fittingRepertory.getRepertorySum(), fittingLibraryDetail.getQuantity()));
						} else {
							Tool.sendErrorMessage("车辆名称为“" + fittingLibraryDetail.getCarName() + "”，型号为“" + fittingLibraryDetail.getModel() + "”的配件库存不足，请确认！");
							error = true;
						}
					}
				}
			}
			if (error) {
				throw new FittingLibraryException();
			} else {
				if (!fittingRepertoryList.isEmpty()) {
					fittingRepertoryDao.saveOrUpdateAll(fittingRepertoryList);
				}
			}
		}
	}
	
	/**
	 * 对此次保存需要更新的配件库存信息与老数据对比
	 * @param fittingInfo 此次需要存入的库存信息
	 * @param updateFittingInfo 老的库存信息
	 * @return true 已存在，需要库存更新，，，false 未存在需要建立库存
	 */
	private boolean verification(FittingLibraryDetail fittingInfo, FittingInfo updateFittingInfo) {
		if (StringUtil.isEquals(fittingInfo.getCarName(), updateFittingInfo.getCarName())
				&& StringUtil.isEquals(fittingInfo.getFittingBrand(), updateFittingInfo.getFittingBrand())
				&& StringUtil.isEquals(fittingInfo.getFittingName(), updateFittingInfo.getFittingName())
				&& StringUtil.isEquals(fittingInfo.getModel(), updateFittingInfo.getModel())) {
			if (fittingInfo.getMaintenanceCubic() != null) {
				updateFittingInfo.setMaintenanceCubic(fittingInfo.getMaintenanceCubic());
			}
			if (fittingInfo.getMaintenanceDay() != null) {
				updateFittingInfo.setMaintenanceDay(fittingInfo.getMaintenanceDay());
			}
			return true;
		}
		return false;
	}
}
