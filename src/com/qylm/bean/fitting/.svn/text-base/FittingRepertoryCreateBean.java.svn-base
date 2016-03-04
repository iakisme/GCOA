package com.qylm.bean.fitting;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
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
import com.qylm.dto.fitting.FittingRepertoryCreateDto;
import com.qylm.dxo.FittingRepertoryCreateDxo;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingRepertory;
import com.qylm.service.FittingInfoService;
import com.qylm.service.FittingRepertoryService;

/**
 * 配件库存登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class FittingRepertoryCreateBean implements CreateBean<FittingRepertory>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingRepertoryCreateBean.class);

	/**
	 * 存放配件库存登陆画面需要保存的值
	 */
	private FittingRepertoryCreateDto fittingRepertoryCreateDto = new FittingRepertoryCreateDto();
	
	/**
	 * 配件库存bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 配件库存业务类
	 */
	@ManagedProperty(value="#{fittingRepertoryService}")
	private FittingRepertoryService fittingRepertoryService;
	
	/**
	 * 配件信息业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 此方法绑定于配件库存登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【配件库存登陆画面_返回按钮】");
		return fittingRepertoryCreateDto.getReturner().returnOnly();
	}
	
	/**
	 * 此方法绑定于配件登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个配件
	 * @return 配件登陆画面
	 */
	public String newFittingInfo() {
		Tool.sendLog(LOG, userBean, "【配件库存登陆画面_新建按钮】");
		fittingRepertoryCreateDto.setFittingInfo(null);
		fittingRepertoryCreateDto.setRepertorySum(null);
		fittingRepertoryCreateDto.setCreater(null);
		fittingRepertoryCreateDto.setTransferFittingRepertory(null);
		fittingRepertoryCreateDto.setFittingName(null);
		fittingRepertoryCreateDto.setFittingInfoList(null);
		fittingRepertoryCreateDto.setBelongingUser(null);
		return Navigation.FITTING_REPERTORY_CREATE;
	}
	
	/**
	 * 此方法绑定于配件采购登陆画面的保存按钮 
	 * 实现功能：根据transferFittingPurchase对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveFittingRepertory() {
		Tool.sendLog(LOG, userBean, "【配件库存登陆画面_保存按钮】");
		if (fittingRepertoryCreateDto.getFittingInfo() == null) {
			Tool.sendErrorMessage("请选择配件信息");
			return;
		}
		try {
			FittingRepertory transferFittingRepertory = fittingRepertoryCreateDto.getTransferFittingRepertory();
			if (transferFittingRepertory == null) {
				transferFittingRepertory = new FittingRepertory();
				fittingRepertoryCreateDto.setCreater(userBean.getUser());
				fittingRepertoryCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferFittingRepertory);
				transferFittingRepertory.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingRepertoryService.saveEntity(transferFittingRepertory);
				fittingRepertoryCreateDto.setTransferFittingRepertory(transferFittingRepertory);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferFittingRepertory);
				transferFittingRepertory.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingRepertoryService.updateEntity(transferFittingRepertory);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("配件已存在，请确认！");
		}
	}
	
	/**
	 * 赋值
	 * @param transferFittingInfo
	 */
	private void create(FittingRepertory transferFittingRepertory) {
		FittingRepertoryCreateDxo.dtoToEntity(fittingRepertoryCreateDto, transferFittingRepertory);
	}
	
	public String newCreate(Returner<?, ?, ?> returner) {
		fittingRepertoryCreateDto.setReturner(returner);
		return Navigation.FITTING_REPERTORY_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, FittingRepertory fittingRepertory) {
		fittingRepertoryCreateDto.setReturner(returner);
		FittingRepertoryCreateDxo.entityToDto(fittingRepertory, fittingRepertoryCreateDto);
		fittingRepertoryCreateDto.setTransferFittingRepertory(fittingRepertory);
		return Navigation.FITTING_REPERTORY_CREATE;
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
		// 去除已经存在的
		DetachedCriteria criteria = DetachedCriteria.forClass(FittingRepertory.class);
		criteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
		criteria.setProjection(Property.forName(FittingRepertory.FITTING_INFO_ID));
		detachedCriteria.add(Property.forName(FittingInfo.BASE_ID).notIn(criteria));
		
		String fittingName = fittingRepertoryCreateDto.getFittingName();
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingRepertoryCreateDto.setFittingInfoList(fittingInfoService.getByDetachedCriteria(detachedCriteria, 0, 10));
	}
	
	/**
	 * 选择车辆列表信息
	 * @param fittingInfo
	 */
	public void findFittingInfo(FittingInfo fittingInfo) {
		fittingRepertoryCreateDto.setFittingInfo(fittingInfo);
	}

	
	/**
	 * 返回到本画面
	 * @return
	 */
	public String returner() {
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}

	/**
	 * set fittingRepertoryService
	 * @param fittingRepertoryService the fittingRepertoryService to set
	 */
	public void setFittingRepertoryService(FittingRepertoryService fittingRepertoryService) {
		this.fittingRepertoryService = fittingRepertoryService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingRepertoryCreateDto
	 * @return the fittingRepertoryCreateDto
	 */
	public FittingRepertoryCreateDto getFittingRepertoryCreateDto() {
		return fittingRepertoryCreateDto;
	}

	/**
	 * set fittingRepertoryCreateDto
	 * @param fittingRepertoryCreateDto the fittingRepertoryCreateDto to set
	 */
	public void setFittingRepertoryCreateDto(FittingRepertoryCreateDto fittingRepertoryCreateDto) {
		this.fittingRepertoryCreateDto = fittingRepertoryCreateDto;
	}

}
