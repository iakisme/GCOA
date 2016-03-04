package com.qylm.bean.procedure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.procedure.FittingPurchaseExamineManageDto;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.entity.FittingPurchaseExamine;
import com.qylm.service.FittingPurchaseDetailService;
import com.qylm.service.FittingPurchaseExamineService;
import com.qylm.service.FittingPurchaseService;

/**
 * 采购单审核管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingPurchaseExamineManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6422477489121065360L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingPurchaseExamineManageBean.class);
	
	/**
	 * 保存采购单审核管理画面需要保存的值
	 */
	private FittingPurchaseExamineManageDto fittingPurchaseExamineManageDto = new FittingPurchaseExamineManageDto();

	/**
	 * 采购单审核列表（检索结果）
	 */
	private List<FittingPurchaseExamine> fittingPurchaseExamineList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingPurchaseExamine[] selectedModels;

	/**
	 * 采购单审核管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 采购单审核管理业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseExamineService}")
	private FittingPurchaseExamineService fittingPurchaseExamineService;
	
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
	 * 查询出所有采购单审核列表
	 * 
	 * @return 采购单审核管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_采购单审核管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_PURCHASE_EXAMINE_MANAGE;
	}
	
	/**
	 * 此方法绑定于配件审核管理画面的审核详细按钮 
	 * @param transferFittingPurchaseExamine
	 */
	public void examineDetail(FittingPurchaseExamine transferFittingPurchaseExamine) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_审核详细按钮】");
		createExamine(transferFittingPurchaseExamine);
		fittingPurchaseExamineManageDto.setExamineRemark(transferFittingPurchaseExamine.getRemark());
		fittingPurchaseExamineManageDto.setShowExamine(true);
	}

	/**
	 * 此方法绑定于配件审核管理画面的审核按钮 
	 */
	public void examine(FittingPurchaseExamine transferFittingPurchaseExamine) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_审核按钮】");
		createExamine(transferFittingPurchaseExamine);
		fittingPurchaseExamineManageDto.setShowExamine(false);
	}

	private void createExamine(FittingPurchaseExamine transferFittingPurchaseExamine) {
		fittingPurchaseExamineManageDto.setFittingPurchaseExamine(transferFittingPurchaseExamine);
		// 重新获取配件采购单
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchase.class);
		detachedCriteria.createAlias(FittingPurchase.PURCHASE_USER, FittingPurchase.PURCHASE_USER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchase.STORAGE_USER, FittingPurchase.STORAGE_USER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchase.CREATER, FittingPurchase.CREATER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchase.BASE_ID, transferFittingPurchaseExamine.getFittingPurchase().getId()));
		List<FittingPurchase> fittingPurchaseList = fittingPurchaseService.getByDetachedCriteria(detachedCriteria);
		if (!fittingPurchaseList.isEmpty()) {
			FittingPurchase fittingPurchase = fittingPurchaseList.get(0);
			// 配件采购单对应的详细
			detachedCriteria = DetachedCriteria.forClass(FittingPurchaseDetail.class);
			detachedCriteria.createAlias(FittingPurchaseDetail.FITTING_PURCHASE, FittingPurchaseDetail.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.eq(FittingPurchaseDetail.FITTING_PURCHASE, fittingPurchase));
			fittingPurchaseExamineManageDto.setFittingPurchaseDetailList(fittingPurchaseDetailService.getByDetachedCriteria(detachedCriteria));
			// 赋值到dto中用于画面显示
			fittingPurchaseExamineManageDto.setSerialNumbers(fittingPurchase.getSerialNumber());
			fittingPurchaseExamineManageDto.setApplyDate(fittingPurchase.getApplyDate());
			fittingPurchaseExamineManageDto.setApplyPrice(fittingPurchase.getApplyPrice());
			fittingPurchaseExamineManageDto.setBudgetPrice(fittingPurchase.getBudgetPrice());
			fittingPurchaseExamineManageDto.setTitle(fittingPurchase.getTitle());
			fittingPurchaseExamineManageDto.setPurchaseSource(fittingPurchase.getPurchaseSource());
			fittingPurchaseExamineManageDto.setRemark(fittingPurchase.getRemark());
			fittingPurchaseExamineManageDto.setState(fittingPurchase.getState());
			fittingPurchaseExamineManageDto.setPurchaseUser(fittingPurchase.getPurchaseUser());
			fittingPurchaseExamineManageDto.setStorageUser(fittingPurchase.getStorageUser());
			fittingPurchaseExamineManageDto.setCreater(fittingPurchase.getCreater());
		}
	}
	
	/**
	 * 验证是否可以审核
	 * @param fittingPurchaseExamine
	 * @return true不可操作，反之可以
	 */
	public boolean isExamineProving(FittingPurchaseExamine fittingPurchaseExamine) {
		boolean state = false;
		// 验证本条记录是否已经审核过
		if (FittingPurchaseExamine.STATE_1.equals(fittingPurchaseExamine.getState())) {
			state = false;
		} else {
			return true;
		}
		// 查询出所有与本条记录相关的信息记录
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseExamine.class);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE, FittingPurchaseExamine.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.FITTING_PURCHASE, fittingPurchaseExamine.getFittingPurchase()));
		// 不是本条
		detachedCriteria.add(Restrictions.not(Restrictions.eq(FittingPurchaseExamine.BASE_ID, fittingPurchaseExamine.getId())));
		// 排位在本条前面的审核
		detachedCriteria.add(Restrictions.le(FittingPurchaseExamine.SEQUENCE, fittingPurchaseExamine.getSequence()));
		// 并且处于审核中的
		detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.STATE, FittingPurchaseExamine.STATE_1));
		List<FittingPurchaseExamine> fittingPurchaseExamineList = fittingPurchaseExamineService.getByDetachedCriteria(detachedCriteria);
		if (!fittingPurchaseExamineList.isEmpty()) {
			state = true;
		}
		return state;
	}
	
	/**
	 * 此方法绑定于采购单审核管理画面的审核通过按钮
	 * 实现功能：将审核提交给下一位，如果已经没有下一位就设置为审核通过
	 */
	public void examineThrough() {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_审核通过按钮】");
		FittingPurchaseExamine fittingPurchaseExamine = fittingPurchaseExamineManageDto.getFittingPurchaseExamine();
		fittingPurchaseExamine.setState(FittingPurchaseExamine.STATE_2);
		fittingPurchaseExamine.setRemark(fittingPurchaseExamineManageDto.getExamineRemark());
		FittingPurchase fittingPurchase = fittingPurchaseExamine.getFittingPurchase();
		fittingPurchase.setBudgetPrice(fittingPurchaseExamineManageDto.getBudgetPrice());
		examine(fittingPurchaseExamine, fittingPurchase, true);
	}

	/**
	 * 审核通过
	 * @param fittingPurchaseExamine
	 * @param fittingPurchase
	 * @param state true审核通过，反之不通过
	 */
	public void examine(FittingPurchaseExamine fittingPurchaseExamine, FittingPurchase fittingPurchase, boolean state) {
		if (state) {
			// 验证是否是最后一个审核，如果最后一个审核就更改采购单的审核状态为通过
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseExamine.class);
			detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.STATE, FittingPurchaseExamine.STATE_1));
			detachedCriteria.add(Restrictions.not(Restrictions.eq(FittingPurchaseExamine.BASE_ID, fittingPurchaseExamine.getId())));
			if (fittingPurchaseExamineService.getByDetachedCriteria(detachedCriteria).isEmpty()) {
				fittingPurchase.setState(FittingPurchase.STATE_4);
			}
			fittingPurchaseExamineService.updateExamine(fittingPurchaseExamine, fittingPurchase);
			Tool.sendErrorMessage("审核通过！");
		} else {
			fittingPurchaseExamineService.updateExamine(fittingPurchaseExamine, fittingPurchase);
			Tool.sendErrorMessage("审核不通过！");
		}
	}
	
	/**
	 * 此方法绑定于采购单审核管理画面审核不通过按钮
	 * 实现功能：设置审核未通过
	 */
	public void examineNotThrough() {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_审核不通过按钮】");
		FittingPurchaseExamine fittingPurchaseExamine = fittingPurchaseExamineManageDto.getFittingPurchaseExamine();
		fittingPurchaseExamine.setState(FittingPurchaseExamine.STATE_3);
		fittingPurchaseExamine.setRemark(fittingPurchaseExamineManageDto.getExamineRemark());
		FittingPurchase fittingPurchase = fittingPurchaseExamine.getFittingPurchase();
		fittingPurchase.setState(FittingPurchase.STATE_3);
		examine(fittingPurchaseExamine, fittingPurchase, false);
	}
	
	/**
	 * 此方法绑定于采购单审核管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出采购单审核
	 * @return 画面不跳转
	 */
	public void selectFittingPurchaseExamine() {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于采购单审核管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<FittingPurchaseExamine> asList = Arrays.asList(selectedModels);
			fittingPurchaseExamineList.removeAll(asList);
			fittingPurchaseExamineService.deleteEntityAll(asList);
			removeData(1, fittingPurchaseExamineList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于采购单审核管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteFittingPurchaseExamine(FittingPurchaseExamine transferFittingPurchaseExamine) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核管理画面的_删除按钮】");
		fittingPurchaseExamineList.remove(transferFittingPurchaseExamine);
		fittingPurchaseExamineService.deleteEntity(transferFittingPurchaseExamine);
		removeData(1, fittingPurchaseExamineList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 获取到需要当前用户审核的信息
	 * @return
	 */
	public List<FittingPurchaseExamine> getFittingPurchaseExamine() {
		DetachedCriteria detachedCriteria = getDetachedCriteria();
		detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.STATE, FittingPurchaseExamine.STATE_1));
		List<FittingPurchaseExamine> list = fittingPurchaseExamineService.getByDetachedCriteria(detachedCriteria, 0, 10);
		if (!list.isEmpty()) {
			List<FittingPurchase> fittingPurchaseList = new ArrayList<FittingPurchase>();
			for (FittingPurchaseExamine fittingPurchaseExamine : list) {
				fittingPurchaseList.add(fittingPurchaseExamine.getFittingPurchase());
			}
			// 获取到对应的配件详细
			detachedCriteria = DetachedCriteria.forClass(FittingPurchaseDetail.class);
			detachedCriteria.createAlias(FittingPurchaseDetail.FITTING_PURCHASE, FittingPurchaseDetail.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(FittingPurchaseDetail.CREATER, FittingPurchaseDetail.CREATER, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FittingPurchaseDetail.FITTING_PURCHASE, fittingPurchaseList));
			List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseDetailService.getByDetachedCriteria(detachedCriteria);
			if (!fittingPurchaseDetailList.isEmpty()) {
				for (FittingPurchaseExamine fittingPurchaseExamine : list) {
					List<FittingPurchaseDetail> detailList = new ArrayList<FittingPurchaseDetail>();
					for (FittingPurchaseDetail fittingPurchaseDetail : fittingPurchaseDetailList) {
						if (fittingPurchaseExamine.getFittingPurchase().equals(fittingPurchaseDetail.getFittingPurchase())) {
							detailList.add(fittingPurchaseDetail);
						}
					}
					fittingPurchaseExamine.setFittingPurchaseDetailList(detailList);
				}
			}
		}
		return list;
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetachedCriteria();
		String serialNumber = fittingPurchaseExamineManageDto.getSerialNumber();
		Date beginApplyDate = fittingPurchaseExamineManageDto.getBeginApplyDate();
		Date endApplyDate = fittingPurchaseExamineManageDto.getEndApplyDate();
		String state = fittingPurchaseExamineManageDto.getState();
		if (StringUtil.isNotBlank(serialNumber)) {
			detachedCriteria.add(Restrictions.like(FittingPurchaseExamine.FITTING_PURCHASE_SERIAL_NUMBER, serialNumber, MatchMode.ANYWHERE));
		}
		if (beginApplyDate != null) {
			detachedCriteria.add(Restrictions.ge(FittingPurchaseExamine.FITTING_PURCHASE_APPLY_DATE, beginApplyDate));
		}
		if (endApplyDate != null) {
			detachedCriteria.add(Restrictions.le(FittingPurchaseExamine.FITTING_PURCHASE_APPLY_DATE, endApplyDate));
		}
		if (!StringUtil.isUnSelected(state)) {
			detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.STATE, state));
		}
		fittingPurchaseExamineList = fittingPurchaseExamineService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingPurchaseExamineService.getRowCount(detachedCriteria));
		}
	}

	private DetachedCriteria getDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseExamine.class);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE, FittingPurchaseExamine.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE_CREATER, FittingPurchaseExamine.FITTING_PURCHASE_CREATER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE_PURCHASE_USER, FittingPurchaseExamine.FITTING_PURCHASE_PURCHASE_USER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE_STORAGE_USER, FittingPurchaseExamine.FITTING_PURCHASE_STORAGE_USER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.AUDITOR, userBean.getUser()));
		return detachedCriteria;
	}
	
	/**
	 * @param fittingPurchaseDetailService the fittingPurchaseDetailService to set
	 */
	public void setFittingPurchaseDetailService(
			FittingPurchaseDetailService fittingPurchaseDetailService) {
		this.fittingPurchaseDetailService = fittingPurchaseDetailService;
	}
	
	/**
	 * @param fittingPurchaseService the fittingPurchaseService to set
	 */
	public void setFittingPurchaseService(
			FittingPurchaseService fittingPurchaseService) {
		this.fittingPurchaseService = fittingPurchaseService;
	}

	/**
	 * set fittingPurchaseExamineService
	 * @param fittingPurchaseExamineService the fittingPurchaseExamineService to set
	 */
	public void setFittingPurchaseExamineService(FittingPurchaseExamineService fittingPurchaseExamineService) {
		this.fittingPurchaseExamineService = fittingPurchaseExamineService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingPurchaseExamineManageDto
	 * @return the fittingPurchaseExamineManageDto
	 */
	public FittingPurchaseExamineManageDto getFittingPurchaseExamineManageDto() {
		return fittingPurchaseExamineManageDto;
	}

	/**
	 * set fittingPurchaseExamineManageDto
	 * @param fittingPurchaseExamineManageDto the fittingPurchaseExamineManageDto to set
	 */
	public void setFittingPurchaseExamineManageDto(FittingPurchaseExamineManageDto fittingPurchaseExamineManageDto) {
		this.fittingPurchaseExamineManageDto = fittingPurchaseExamineManageDto;
	}

	/**
	 * get fittingPurchaseExamineList
	 * @return the fittingPurchaseExamineList
	 */
	public List<FittingPurchaseExamine> getFittingPurchaseExamineList() {
		return fittingPurchaseExamineList;
	}

	/**
	 * set fittingPurchaseExamineList
	 * @param fittingPurchaseExamineList the fittingPurchaseExamineList to set
	 */
	public void setFittingPurchaseExamineList(List<FittingPurchaseExamine> fittingPurchaseExamineList) {
		this.fittingPurchaseExamineList = fittingPurchaseExamineList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingPurchaseExamine[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingPurchaseExamine[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
