package com.qylm.logic.fitting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;

import com.qylm.common.MothedUtil;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dao.FittingInfoDao;
import com.qylm.dao.FittingPurchaseDao;
import com.qylm.dao.FittingRepertoryDao;
import com.qylm.dao.FittingStorageDao;
import com.qylm.dao.FittingStorageDetailDao;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingRepertory;
import com.qylm.entity.FittingStorage;
import com.qylm.entity.FittingStorageDetail;
import com.qylm.entity.User;

public class FittingStorageLogic {

	@Autowired
	private FittingStorageDao fittingStorageDao;
	
	@Autowired
	private FittingStorageDetailDao fittingStorageDetailDao;
	
	@Autowired
	private FittingPurchaseDao fittingPurchaseDao;
	
	@Autowired
	private FittingRepertoryDao fittingRepertoryDao;
	
	@Autowired
	private FittingInfoDao fittingInfoDao;
	
	public void saveFittingStorage(FittingStorage fittingStorage,
			List<FittingStorageDetail> fittingStorageDetailList) {
		fittingStorage.setNumber(getNumber(fittingStorage.getCreater()));
		fittingStorageDao.saveEntity(fittingStorage);
		if (fittingStorageDetailList != null && !fittingStorageDetailList.isEmpty()) {
			fittingStorageDetailDao.saveEntityAll(fittingStorageDetailList);
		}
		if (fittingStorage.isState()) {
			storage(fittingStorage, fittingStorageDetailList);
		}
	}

	public void updateFittingStorage(FittingStorage fittingStorage,
			List<FittingStorageDetail> fittingStorageDetailList) {
		fittingStorageDao.updateEntity(fittingStorage);
		if (fittingStorageDetailList != null && !fittingStorageDetailList.isEmpty()) {
			fittingStorageDetailDao.saveOrUpdateAll(fittingStorageDetailList);
		}
		if (fittingStorage.isState()) {
			storage(fittingStorage, fittingStorageDetailList);
		}
	}
	
	/**
	 * 获取入库单编号，例子2015060400001
	 * @return
	 */
	private String getNumber(User user) {
		String number = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingStorage.class);
		detachedCriteria.add(Restrictions.like(FittingStorage.NUMBER, number, MatchMode.START));
		detachedCriteria.addOrder(Order.desc(FittingStorage.NUMBER));
		MothedUtil.getBelongingUser(detachedCriteria, user);
		List<FittingStorage> fittingStorageList = fittingStorageDao.getByDetachedCriteria(detachedCriteria);
		if (fittingStorageList.isEmpty()) {
			number = number + "00001";
		} else {
			FittingStorage fittingStorage = fittingStorageList.get(0);
			String number2 = fittingStorage.getNumber();
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
	
	private void storage(FittingStorage fittingStorage, List<FittingStorageDetail> fittingStorageDetailList) {
		FittingPurchase fittingPurchase = fittingStorage.getFittingPurchase();
		if (fittingPurchase != null) {
			fittingPurchase.setState(FittingPurchase.STATE_7);
			fittingPurchaseDao.updateEntity(fittingPurchase);
		}
		// 更新库存
		if (fittingStorageDetailList != null && !fittingStorageDetailList.isEmpty()) {
			// 提取出本次配件信息及数量
			List<FittingInfo> fittingInfoList = new ArrayList<FittingInfo>();
			List<String> carNameList = new ArrayList<String>();
			List<String> fittingBrandList = new ArrayList<String>();
			List<String> fittingNameList = new ArrayList<String>();
			List<String> modelList = new ArrayList<String>();
			for (FittingStorageDetail fittingStorageDetail : fittingStorageDetailList) {
				FittingInfo fittingInfo = new FittingInfo();
				fittingInfo.setCarName(fittingStorageDetail.getCarName());
				fittingInfo.setFittingBrand(fittingStorageDetail.getFittingBrand());
				fittingInfo.setFittingName(fittingStorageDetail.getFittingName());
				fittingInfo.setModel(fittingStorageDetail.getModel());
				fittingInfo.setMaintenanceCubic(fittingStorageDetail.getMaintenanceCubic());
				fittingInfo.setMaintenanceDay(fittingStorageDetail.getMaintenanceDay());
				fittingInfo.setPrice(fittingStorageDetail.getPrice());
				fittingInfo.setCount(fittingStorageDetail.getQuantity());
				fittingInfoList.add(fittingInfo);
				carNameList.add(fittingStorageDetail.getCarName());
				fittingBrandList.add(fittingStorageDetail.getFittingBrand());
				fittingNameList.add(fittingStorageDetail.getFittingName());
				modelList.add(fittingStorageDetail.getModel());
			}
			// 验证配件信息是否已经存在
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
			detachedCriteria.add(Restrictions.in(FittingInfo.CAR_NAME, carNameList));
			detachedCriteria.add(Restrictions.in(FittingInfo.FITTING_BRAND, fittingBrandList));
			detachedCriteria.add(Restrictions.in(FittingInfo.FITTING_NAME, fittingNameList));
			detachedCriteria.add(Restrictions.in(FittingInfo.MODEL, modelList));
			List<FittingInfo> fittingList = fittingInfoDao.getByDetachedCriteria(detachedCriteria);
			for (FittingInfo fittingInfo : fittingInfoList) {
				for (FittingInfo fitting : fittingList) {
					if (verification(fittingInfo, fitting)) {
						fittingInfo.setId(fitting.getId());
						fittingInfo.setVersion(fitting.getVersion());
						fittingInfo.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
						break;
					}
				}
			}
			
			// 取出对应的所有库存
			detachedCriteria = DetachedCriteria.forClass(FittingRepertory.class);
			detachedCriteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FittingRepertory.CAR_NAME, carNameList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_BRAND, fittingBrandList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_NAME, fittingNameList));
			detachedCriteria.add(Restrictions.in(FittingRepertory.MODEL, modelList));
			MothedUtil.getBelongingUser(detachedCriteria, fittingStorage.getCreater());
			List<FittingRepertory> fittingRepertoryList = fittingRepertoryDao.getByDetachedCriteria(detachedCriteria);
			for (FittingInfo fittingInfo : fittingInfoList) {
				boolean state = false;
				for (FittingRepertory fittingRepertory : fittingRepertoryList) {
					if (verification(fittingInfo, fittingRepertory.getFittingInfo())) {
						BigDecimal repertorySum = fittingRepertory.getRepertorySum() == null ? BigDecimal.ZERO : fittingRepertory.getRepertorySum();
						fittingRepertory.setRepertorySum(BigDecimalUtil.add(repertorySum, fittingInfo.getCount()));
						state = true;
						break;
					}
				}
				if (!state) {
					FittingRepertory repertory = new FittingRepertory();
					repertory.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
					repertory.setCreater(fittingStorage.getCreater());
					repertory.setFittingInfo(fittingInfo);
					repertory.setRepertorySum(fittingInfo.getCount());
					fittingRepertoryList.add(repertory);
				}
			}
			if (!fittingList.isEmpty()) {
				fittingInfoDao.saveOrUpdateAll(fittingList);
			}
			if (!fittingRepertoryList.isEmpty()) {
				fittingRepertoryDao.saveOrUpdateAll(fittingRepertoryList);
			}
		}
	}
	
	/**
	 * 对此次保存需要更新的配件库存信息与老数据对比
	 * @param fittingInfo 此次需要存入的库存信息
	 * @param updateFittingInfo 老的库存信息
	 * @return true 已存在，需要库存更新，，，false 未存在需要建立库存
	 */
	private boolean verification(FittingInfo fittingInfo, FittingInfo updateFittingInfo) {
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
