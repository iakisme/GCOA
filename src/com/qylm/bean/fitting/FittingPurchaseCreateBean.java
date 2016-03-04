package com.qylm.bean.fitting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

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
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.fitting.FittingPurchaseCreateDto;
import com.qylm.dxo.FittingPurchaseCreateDxo;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.service.FittingInfoService;
import com.qylm.service.FittingPurchaseDetailService;
import com.qylm.service.FittingPurchaseService;

/**
 * 配件采购登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class FittingPurchaseCreateBean implements CreateBean<FittingPurchase>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingPurchaseCreateBean.class);

	/**
	 * 存放配件采购登陆画面需要保存的值
	 */
	private FittingPurchaseCreateDto fittingPurchaseCreateDto = new FittingPurchaseCreateDto();
	
	/**
	 * 配件采购bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 配件采购业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseService}")
	private FittingPurchaseService fittingPurchaseService;
	
	/**
	 * 配件采购详细业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseDetailService}")
	private FittingPurchaseDetailService fittingPurchaseDetailService;
	
	/**
	 * 配件信息业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 此方法绑定于配件采购登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个配件采购
	 * @return 配件采购登陆画面
	 */
	public String newFittingPurchase() {
		Tool.sendLog(LOG, userBean, "【配件采购登陆画面_新建按钮】");
		fittingPurchaseCreateDto.setSerialNumber(null);
		fittingPurchaseCreateDto.setApplyDate(DateUtil.getNowyyyymmdd());
		fittingPurchaseCreateDto.setApplyPrice(null);
		fittingPurchaseCreateDto.setBudgetPrice(null);
		fittingPurchaseCreateDto.setPurchaseDate(null);
		fittingPurchaseCreateDto.setTitle(null);
		fittingPurchaseCreateDto.setPurchaseSource(null);
		fittingPurchaseCreateDto.setRemark(null);
		fittingPurchaseCreateDto.setPurchasePrice(null);
		fittingPurchaseCreateDto.setState(FittingPurchase.STATE_1);
		fittingPurchaseCreateDto.setFreight(null);
		fittingPurchaseCreateDto.setPurchaseUser(null);
		fittingPurchaseCreateDto.setStorageUser(null);
		fittingPurchaseCreateDto.setFittingPurchaseDetailList(null);
		fittingPurchaseCreateDto.setFittingPurchaseDetail(null);
		fittingPurchaseCreateDto.setFittingName(null);
		fittingPurchaseCreateDto.setFittingInfoList(null);
		fittingPurchaseCreateDto.setCreater(null);
		fittingPurchaseCreateDto.setBelongingUser(null);
		fittingPurchaseCreateDto.setTransferFittingPurchase(null);
		return Navigation.FITTING_PURCHASE_CREATE;
	}
	
	/**
	 * 此方法绑定于配件采购登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【配件采购登陆画面_返回按钮】");
		return fittingPurchaseCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于配件采购登陆画面的保存按钮 
	 * 实现功能：根据transferFittingPurchase对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveFittingPurchase() {
		Tool.sendLog(LOG, userBean, "【配件采购登陆画面_保存按钮】");
		try {
			FittingPurchase transferFittingPurchase = fittingPurchaseCreateDto.getTransferFittingPurchase();
			if (transferFittingPurchase == null) {
				transferFittingPurchase = new FittingPurchase();
				getPurchaseNumber();
				fittingPurchaseCreateDto.setState(FittingPurchase.STATE_1);
				fittingPurchaseCreateDto.setCreater(userBean.getUser());
				fittingPurchaseCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferFittingPurchase);
				transferFittingPurchase.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingPurchaseService.saveEntity(transferFittingPurchase);
				fittingPurchaseCreateDto.setTransferFittingPurchase(transferFittingPurchase);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferFittingPurchase);
				transferFittingPurchase.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingPurchaseService.updateFittingPurchase(transferFittingPurchase, fittingPurchaseCreateDto.getFittingPurchaseDetailList());
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("配件采购已存在，请确认！");
		}
	}
	
	/**
	 * 此方法绑定于配件采购登陆画面的提交按钮 
	 * 实现功能：根据transferFittingPurchase对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void apply() {
		Tool.sendLog(LOG, userBean, "【配件采购登陆画面_提交按钮】");
		FittingPurchase transferFittingPurchase = fittingPurchaseCreateDto.getTransferFittingPurchase();
		fittingPurchaseCreateDto.setState(FittingPurchase.STATE_2);
		create(transferFittingPurchase);
		transferFittingPurchase.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		fittingPurchaseService.updateFittingPurchase(transferFittingPurchase, fittingPurchaseCreateDto.getFittingPurchaseDetailList());
		Tool.sendErrorMessage("提交申请成功，请等待审核！");
	}
	
	/**
	 * 赋值
	 * @param transferFittingPurchase
	 */
	private void create(FittingPurchase transferFittingPurchase) {
		FittingPurchaseCreateDxo.dtoToEntity(fittingPurchaseCreateDto, transferFittingPurchase);
	}
	
	/**
	 * 用于采购申请管理画面调用，用于重新申请采购单
	 * 申请时，保留所有的基本资料信息
	 * @param returner
	 * @param entity
	 * @return
	 */
	public String reApply(Returner<?, ?, ?> returner, FittingPurchase entity) {
		fittingPurchaseCreateDto.setReturner(returner);
		FittingPurchaseCreateDxo.entityToDto(entity, fittingPurchaseCreateDto);
		// 获取到当前采购单下的采购单详情
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseDetail.class);
		detachedCriteria.createAlias(FittingPurchaseDetail.FITTING_PURCHASE, FittingPurchaseDetail.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchaseDetail.FITTING_PURCHASE, entity));
		fittingPurchaseCreateDto.setFittingPurchaseDetailList(fittingPurchaseDetailService.getByDetachedCriteria(detachedCriteria));
		fittingPurchaseCreateDto.setState(FittingPurchase.STATE_1);
		fittingPurchaseCreateDto.setApplyDate(DateUtil.getNowyyyymmdd());
		return Navigation.FITTING_PURCHASE_CREATE;
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		fittingPurchaseCreateDto.setReturner(returner);
		fittingPurchaseCreateDto.setApplyDate(DateUtil.getNowyyyymmdd());
		fittingPurchaseCreateDto.setCreater(userBean.getUser());
		fittingPurchaseCreateDto.setState(FittingPurchase.STATE_1);
		return Navigation.FITTING_PURCHASE_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, FittingPurchase fittingPurchase) {
		fittingPurchaseCreateDto.setReturner(returner);
		FittingPurchaseCreateDxo.entityToDto(fittingPurchase, fittingPurchaseCreateDto);
		fittingPurchaseCreateDto.setTransferFittingPurchase(fittingPurchase);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseDetail.class);
		detachedCriteria.createAlias(FittingPurchaseDetail.FITTING_PURCHASE, FittingPurchaseDetail.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchaseDetail.FITTING_PURCHASE, fittingPurchase));
		fittingPurchaseCreateDto.setFittingPurchaseDetailList(fittingPurchaseDetailService.getByDetachedCriteria(detachedCriteria));
		
		// 取总数量之和
		List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseCreateDto.getFittingPurchaseDetailList();
		BigDecimal applyPrice = fittingPurchaseCreateDto.getSumFittingPrice();
		applyPrice = applyPrice == null ? Constants.BIGDECIMAL_1_ZERO : applyPrice;
		for (FittingPurchaseDetail detail : fittingPurchaseDetailList) {
			if (BigDecimalUtil.isNotNullOrZero(detail.getPrice())) {
				applyPrice = BigDecimalUtil.add(applyPrice, BigDecimalUtil.multiply(detail.getPrice(), detail.getQuantity()));
			}
		}
		// 在加上本次的
		fittingPurchaseCreateDto.setSumFittingPrice(applyPrice);
		return Navigation.FITTING_PURCHASE_CREATE;
	}
	
	/**
	 * 增加一行采购配件信息
	 */
	public void addFittingPurchaseDateil() {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_添加采购配件按钮】");
		List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseCreateDto.getFittingPurchaseDetailList();
		if (fittingPurchaseDetailList == null) {
			fittingPurchaseDetailList = new ArrayList<FittingPurchaseDetail>();
			fittingPurchaseCreateDto.setFittingPurchaseDetailList(fittingPurchaseDetailList);
		}
		FittingPurchaseDetail fittingPurchaseDetail = new FittingPurchaseDetail();
		FittingPurchase transferFittingPurchase = fittingPurchaseCreateDto.getTransferFittingPurchase();
		fittingPurchaseDetail.setFittingPurchase(transferFittingPurchase);
		fittingPurchaseDetail.setCreater(userBean.getUser());
		fittingPurchaseDetailList.add(fittingPurchaseDetail);
	}
	
	/**
	 * 删除配件采购
	 * @param fittingPurchaseDetail
	 */
	public void deleteFittingPurchaseDetail(FittingPurchaseDetail fittingPurchaseDetail) {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_删除按钮】");
		List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseCreateDto.getFittingPurchaseDetailList();
		fittingPurchaseDetailList.remove(fittingPurchaseDetail);
		if (fittingPurchaseDetail.getId() != null) {
			fittingPurchaseDetailService.deleteEntity(fittingPurchaseDetail);
		}
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 查询出所有配件信息
	 * @param fittingPurchaseDetail
	 */
	public void selectFittingInfo(FittingPurchaseDetail fittingPurchaseDetail) {
		Tool.sendLog(LOG, userBean, "【采购登陆画面_选择配件按钮】");
		fittingPurchaseCreateDto.setFittingPurchaseDetail(fittingPurchaseDetail);
		selectFitting();
	}

	/**
	 * 查询出所有配件列表信息
	 * @param fittingPurchaseDetail
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
		String fittingName = fittingPurchaseCreateDto.getFittingName();
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingPurchaseCreateDto.setFittingInfoList(fittingInfoService.getByDetachedCriteria(detachedCriteria, 0, 20));
	}
	
	/**
	 * 选择车辆列表信息
	 * @param fittingInfo
	 */
	public void findFittingInfo(FittingInfo fittingInfo) {
		FittingPurchaseDetail fittingPurchaseDetail = fittingPurchaseCreateDto.getFittingPurchaseDetail();
		fittingPurchaseDetail.setCarName(fittingInfo.getCarName());
		fittingPurchaseDetail.setFittingBrand(fittingInfo.getFittingBrand());
		fittingPurchaseDetail.setFittingName(fittingInfo.getFittingName());
		fittingPurchaseDetail.setModel(fittingInfo.getModel());
		fittingPurchaseDetail.setPrice(fittingInfo.getPrice());
		fittingPurchaseDetail.setQuantity(BigDecimal.ONE);
		// 取总数量之和
		List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseCreateDto.getFittingPurchaseDetailList();
		BigDecimal applyPrice = fittingPurchaseCreateDto.getSumFittingPrice();
		applyPrice = applyPrice == null ? Constants.BIGDECIMAL_1_ZERO : applyPrice;
		for (FittingPurchaseDetail detail : fittingPurchaseDetailList) {
			if (BigDecimalUtil.isNotNullOrZero(detail.getPrice())) {
				applyPrice = BigDecimalUtil.add(applyPrice, BigDecimalUtil.multiply(detail.getPrice(), detail.getQuantity()));
			}
		}
		// 在加上本次的
		fittingPurchaseCreateDto.setSumFittingPrice(applyPrice);
	}
	
	/**
	 * 更新申请金额
	 * @param event
	 */
	public void updateApplyPrice(AjaxBehaviorEvent event) {
		List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseCreateDto.getFittingPurchaseDetailList();
		BigDecimal applyPrice = Constants.BIGDECIMAL_1_ZERO;
		applyPrice = applyPrice == null ? Constants.BIGDECIMAL_1_ZERO : applyPrice;
		for (FittingPurchaseDetail detail : fittingPurchaseDetailList) {
			// 累加不是本次修改的配件总金额
			if (BigDecimalUtil.isNotNullOrZero(detail.getPrice())) {
				applyPrice = BigDecimalUtil.add(applyPrice, BigDecimalUtil.multiply(detail.getPrice(), detail.getQuantity()));
			}
		}
		// 在加上本次的
		fittingPurchaseCreateDto.setSumFittingPrice(applyPrice);
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
	 * 获取采购单编号
	 * 采购单编号格式为：yyyymmdd*****
	 */
	private void getPurchaseNumber() {
		String purchaseNumber = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		if (StringUtil.isNotBlank(FittingPurchase.PURCHASE_NUMBER_VALUE)) {
			int indexOf = FittingPurchase.PURCHASE_NUMBER_VALUE.indexOf(purchaseNumber);
			if (indexOf != -1) {
				getNextPurchaseNumber(FittingPurchase.PURCHASE_NUMBER_VALUE);
			} else {
				getNewPurchaseNumber();
			}
		} else {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchase.class);
			detachedCriteria.add(Restrictions.like(FittingPurchase.SERIAL_NUMBER, purchaseNumber, MatchMode.START));
			detachedCriteria.addOrder(Order.desc(FittingPurchase.SERIAL_NUMBER));
			List<FittingPurchase> purchaseList = fittingPurchaseService.getByDetachedCriteria(detachedCriteria);
			if (purchaseList.isEmpty()) {
				getNewPurchaseNumber();
			} else {
				getNextPurchaseNumber(purchaseList.get(0).getSerialNumber());
			}
		}
	}
	
	/**
	 * 创建一个新的采购单编号
	 */
	private void getNewPurchaseNumber() {
		String purchaseNumber = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		purchaseNumber = purchaseNumber + "00001";
		FittingPurchase.PURCHASE_NUMBER_VALUE = purchaseNumber;
		fittingPurchaseCreateDto.setSerialNumber(purchaseNumber);
	}
	
	/**
	 * 根据本次传入的采购单编号，获取到下一个编号
	 * @param purchaseNumber
	 * @return
	 */
	private void getNextPurchaseNumber(String purchaseNumber) {
		Integer valueOf = Integer.valueOf(purchaseNumber.substring(10)) + 1;
		String value = valueOf.toString();
		StringBuilder sb = new StringBuilder(64);
		sb.append(purchaseNumber.substring(0, 8));
		int i = (5 - value.length()) - 1;
		for (; i >= 0; i--) {
			sb.append(0);
		}
		sb.append(value);
		// 更改appliction中的值
		FittingPurchase.PURCHASE_NUMBER_VALUE = sb.toString();
		fittingPurchaseCreateDto.setSerialNumber(sb.toString());
	}
	
	/**
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}
	
	/**
	 * @param fittingPurchaseDetailService the fittingPurchaseDetailService to set
	 */
	public void setFittingPurchaseDetailService(
			FittingPurchaseDetailService fittingPurchaseDetailService) {
		this.fittingPurchaseDetailService = fittingPurchaseDetailService;
	}

	/**
	 * set fittingPurchaseService
	 * @param fittingPurchaseService the fittingPurchaseService to set
	 */
	public void setFittingPurchaseService(FittingPurchaseService fittingPurchaseService) {
		this.fittingPurchaseService = fittingPurchaseService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingPurchaseCreateDto
	 * @return the fittingPurchaseCreateDto
	 */
	public FittingPurchaseCreateDto getFittingPurchaseCreateDto() {
		return fittingPurchaseCreateDto;
	}

	/**
	 * set fittingPurchaseCreateDto
	 * @param fittingPurchaseCreateDto the fittingPurchaseCreateDto to set
	 */
	public void setFittingPurchaseCreateDto(FittingPurchaseCreateDto fittingPurchaseCreateDto) {
		this.fittingPurchaseCreateDto = fittingPurchaseCreateDto;
	}

}
