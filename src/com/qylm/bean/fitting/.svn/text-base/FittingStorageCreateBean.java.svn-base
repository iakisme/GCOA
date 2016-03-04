package com.qylm.bean.fitting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.fitting.FittingStorageCreateDto;
import com.qylm.dxo.FittingStorageCreateDxo;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingStorage;
import com.qylm.entity.FittingStorageDetail;
import com.qylm.service.FittingInfoService;
import com.qylm.service.FittingStorageDetailService;
import com.qylm.service.FittingStorageService;

/**
 * 配件入库登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class FittingStorageCreateBean implements CreateBean<FittingStorage>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingStorageCreateBean.class);

	/**
	 * 存放配件入库登陆画面需要保存的值
	 */
	private FittingStorageCreateDto fittingStorageCreateDto = new FittingStorageCreateDto();
	
	/**
	 * 配件入库bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 配件入库业务类
	 */
	@ManagedProperty(value="#{fittingStorageService}")
	private FittingStorageService fittingStorageService;
	
	/**
	 * 配件入库详细业务类
	 */
	@ManagedProperty(value="#{fittingStorageDetailService}")
	private FittingStorageDetailService fittingStorageDetailService;
	
	/**
	 * 配件信息业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 此方法绑定于配件入库登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个配件入库
	 * @return 配件入库登陆画面
	 */
	public String newFittingStorage() {
		Tool.sendLog(LOG, userBean, "【配件入库登陆画面_新建按钮】");
		fittingStorageCreateDto.setFittingStorage(null);
		fittingStorageCreateDto.setNumber(null);
		fittingStorageCreateDto.setStorageDate(null);
		fittingStorageCreateDto.setRemark(null);
		fittingStorageCreateDto.setState(false);
		fittingStorageCreateDto.setFittingStorageDetailList(null);
		fittingStorageCreateDto.setFittingStorageDetail(null);
		fittingStorageCreateDto.setFittingName(null);
		fittingStorageCreateDto.setFittingInfoList(null);
		fittingStorageCreateDto.setTransferFittingStorage(null);
		fittingStorageCreateDto.setCreater(null);
		fittingStorageCreateDto.setBelongingUser(null);
		return Navigation.FITTING_STORAGE_CREATE;
	}
	
	/**
	 * 此方法绑定于配件入库登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【配件入库登陆画面_返回按钮】");
		return fittingStorageCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于配件入库登陆画面的保存按钮 
	 * 实现功能：根据transferFittingStorage对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveFittingStorage() {
		Tool.sendLog(LOG, userBean, "【配件入库登陆画面_保存按钮】");
		try {
			FittingStorage transferFittingStorage = fittingStorageCreateDto.getTransferFittingStorage();
			if (transferFittingStorage == null) {
				transferFittingStorage = new FittingStorage();
				getNumber();
				fittingStorageCreateDto.setCreater(userBean.getUser());
				fittingStorageCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferFittingStorage);
				transferFittingStorage.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingStorageService.saveFittingStorage(transferFittingStorage, fittingStorageCreateDto.getFittingStorageDetailList());
				fittingStorageCreateDto.setTransferFittingStorage(transferFittingStorage);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferFittingStorage);
				transferFittingStorage.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingStorageService.updateFittingStorage(transferFittingStorage, fittingStorageCreateDto.getFittingStorageDetailList());
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("配件入库单号已存在，请确认！");
		}
	}
	
	/**
	 * 此方法绑定于配件入库登陆画面的提交按钮 
	 * 实现功能：根据transferFittingStorage对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void apply() {
		Tool.sendLog(LOG, userBean, "【配件入库登陆画面_确认按钮】");
		FittingStorage transferFittingStorage = fittingStorageCreateDto.getTransferFittingStorage();
		fittingStorageCreateDto.setState(true);
		create(transferFittingStorage);
		transferFittingStorage.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		fittingStorageService.updateFittingStorage(transferFittingStorage, fittingStorageCreateDto.getFittingStorageDetailList());
		Tool.sendErrorMessage("配件入库成功！");
	}
	
	/**
	 * 赋值
	 * @param transferFittingStorage
	 */
	private void create(FittingStorage transferFittingStorage) {
		FittingStorageCreateDxo.dtoToEntity(fittingStorageCreateDto, transferFittingStorage);
	}
	
	public String newCreate(Returner<?, ?, ?> returner) {
		fittingStorageCreateDto.setReturner(returner);
		fittingStorageCreateDto.setStorageDate(DateUtil.getNowyyyymmdd());
		fittingStorageCreateDto.setCreater(userBean.getUser());
		return Navigation.FITTING_STORAGE_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, FittingStorage fittingStorage) {
		fittingStorageCreateDto.setReturner(returner);
		FittingStorageCreateDxo.entityToDto(fittingStorage, fittingStorageCreateDto);
		fittingStorageCreateDto.setTransferFittingStorage(fittingStorage);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingStorageDetail.class);
		detachedCriteria.createAlias(FittingStorageDetail.FITTING_STORAGE, FittingStorageDetail.FITTING_STORAGE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingStorageDetail.FITTING_STORAGE, fittingStorage));
		fittingStorageCreateDto.setFittingStorageDetailList(fittingStorageDetailService.getByDetachedCriteria(detachedCriteria));
		return Navigation.FITTING_STORAGE_CREATE;
	}
	
	/**
	 * 增加一行采购配件信息
	 */
	public void addFittingStorageDateil() {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_添加采购配件按钮】");
		List<FittingStorageDetail> fittingStorageDetailList = fittingStorageCreateDto.getFittingStorageDetailList();
		if (fittingStorageDetailList == null) {
			fittingStorageDetailList = new ArrayList<FittingStorageDetail>();
			fittingStorageCreateDto.setFittingStorageDetailList(fittingStorageDetailList);
		}
		FittingStorageDetail fittingStorageDetail = new FittingStorageDetail();
		FittingStorage transferFittingStorage = fittingStorageCreateDto.getTransferFittingStorage();
		fittingStorageDetail.setFittingStorage(transferFittingStorage);
		fittingStorageDetail.setCreater(userBean.getUser());
		fittingStorageDetailList.add(fittingStorageDetail);
	}
	
	/**
	 * 删除配件入库
	 * @param fittingStorageDetail
	 */
	public void deleteFittingStorageDetail(FittingStorageDetail fittingStorageDetail) {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_删除按钮】");
		List<FittingStorageDetail> fittingStorageDetailList = fittingStorageCreateDto.getFittingStorageDetailList();
		fittingStorageDetailList.remove(fittingStorageDetail);
		if (fittingStorageDetail.getId() != null) {
			fittingStorageDetailService.deleteEntity(fittingStorageDetail);
		}
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 查询出所有配件信息
	 * @param fittingStorageDetail
	 */
	public void selectFittingInfo(FittingStorageDetail fittingStorageDetail) {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_选择配件按钮】");
		fittingStorageCreateDto.setFittingStorageDetail(fittingStorageDetail);
		selectFitting();
	}

	/**
	 * 查询出所有配件列表信息
	 * @param fittingStorageDetail
	 */
	public void selectFittingInfo() {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_搜索配件信息按钮】");
		selectFitting();
	}
	
	/**
	 * 查询出配件信息
	 */
	private void selectFitting() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String fittingName = fittingStorageCreateDto.getFittingName();
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingStorageCreateDto.setFittingInfoList(fittingInfoService.getByDetachedCriteria(detachedCriteria, 0, 20));
	}
	
	/**
	 * 选择车辆列表信息
	 * @param fittingInfo
	 */
	public void findFittingInfo(FittingInfo fittingInfo) {
		FittingStorageDetail fittingStorageDetail = fittingStorageCreateDto.getFittingStorageDetail();
		fittingStorageDetail.setCarName(fittingInfo.getCarName());
		fittingStorageDetail.setFittingBrand(fittingInfo.getFittingBrand());
		fittingStorageDetail.setFittingName(fittingInfo.getFittingName());
		fittingStorageDetail.setModel(fittingInfo.getModel());
		fittingStorageDetail.setPrice(fittingInfo.getPrice());
		fittingStorageDetail.setQuantity(BigDecimal.ONE);
	}
	
	/**
	 * 返回到本画面
	 * @return
	 */
	public String returner() {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_返回按钮】");
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * 获取编号
	 */
	private void getNumber() {
		String purchaseNumber = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingStorage.class);
		detachedCriteria.add(Restrictions.like(FittingStorage.NUMBER, purchaseNumber, MatchMode.START));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(FittingStorage.NUMBER));
	}
	
	/**
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}
	
	/**
	 * @param fittingStorageDetailService the fittingStorageDetailService to set
	 */
	public void setFittingStorageDetailService(
			FittingStorageDetailService fittingStorageDetailService) {
		this.fittingStorageDetailService = fittingStorageDetailService;
	}

	/**
	 * set fittingStorageService
	 * @param fittingStorageService the fittingStorageService to set
	 */
	public void setFittingStorageService(FittingStorageService fittingStorageService) {
		this.fittingStorageService = fittingStorageService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingStorageCreateDto
	 * @return the fittingStorageCreateDto
	 */
	public FittingStorageCreateDto getFittingStorageCreateDto() {
		return fittingStorageCreateDto;
	}

	/**
	 * set fittingStorageCreateDto
	 * @param fittingStorageCreateDto the fittingStorageCreateDto to set
	 */
	public void setFittingStorageCreateDto(FittingStorageCreateDto fittingStorageCreateDto) {
		this.fittingStorageCreateDto = fittingStorageCreateDto;
	}

}
