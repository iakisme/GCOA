package com.qylm.bean.fitting;

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
import com.qylm.bean.returner.fitting.FittingPurchaseManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.fitting.FittingPurchaseManageDto;
import com.qylm.entity.FittingPurchase;
import com.qylm.entity.FittingPurchaseDetail;
import com.qylm.entity.FittingPurchaseExamine;
import com.qylm.entity.User;
import com.qylm.service.FittingPurchaseDetailService;
import com.qylm.service.FittingPurchaseExamineService;
import com.qylm.service.FittingPurchaseService;

/**
 * 配件采购管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingPurchaseManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8164175075064545416L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingPurchaseManageBean.class);
	
	/**
	 * 保存配件采购管理画面需要保存的值
	 */
	private FittingPurchaseManageDto fittingPurchaseManageDto = new FittingPurchaseManageDto();

	/**
	 * 配件采购列表（检索结果）
	 */
	private List<FittingPurchase> fittingPurchaseList;
	
	/**
	 * 采购单审核列表（检索结果）
	 */
	private List<FittingPurchaseExamine> fittingPurchaseExamineList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingPurchase[] selectedModels;

	/**
	 * 配件采购管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 配件采购管理业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseService}")
	private FittingPurchaseService fittingPurchaseService;
	
	/**
	 * 采购单审核管理业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseExamineService}")
	private FittingPurchaseExamineService fittingPurchaseExamineService;
	
	/**
	 * 配件采购详细业务类
	 */
	@ManagedProperty(value="#{fittingPurchaseDetailService}")
	private FittingPurchaseDetailService fittingPurchaseDetailService;
	
	/**
	 * 查询出所有配件采购列表
	 * 
	 * @return 配件采购管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_配件采购管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_PURCHASE_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至配件采购登陆画面，新建一家配件采购
	 * @return 配件采购登陆画面
	 */
	public String newFittingPurchase() {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面_新建按钮】");
		return Tool.getBackBean(FittingPurchaseCreateBean.class).newCreate(new FittingPurchaseManageReturner(fittingPurchaseManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至配件采购登陆画面
	 * @return 配件采购登陆画面
	 */
	public String updateFittingPurchase(FittingPurchase transferFittingPurchase) {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面_修改按钮】");
		return Tool.getBackBean(FittingPurchaseCreateBean.class).updateDetail(new FittingPurchaseManageReturner(fittingPurchaseManageDto, currentPage), transferFittingPurchase);
	}
	
	/**
	 * 绑定与采购单管理画面的查看审批情况按钮
	 * @return 跳转至仓库登录页面
	 */
	public void reApply(FittingPurchase transferFittingPurchase) {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面_查看审批情况按钮】");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchaseExamine.class);
		detachedCriteria.createAlias(FittingPurchaseExamine.AUDITOR, FittingPurchaseExamine.AUDITOR, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchaseExamine.FITTING_PURCHASE, FittingPurchaseExamine.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingPurchaseExamine.FITTING_PURCHASE, transferFittingPurchase));
		fittingPurchaseExamineList = fittingPurchaseExamineService.getByDetachedCriteria(detachedCriteria);
	}
	
	/**
	 * 此方法绑定于配件采购管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出配件采购
	 * @return 画面不跳转
	 */
	public void selectFittingPurchase() {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于配件采购管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<FittingPurchase> asList = Arrays.asList(selectedModels);
			fittingPurchaseList.removeAll(asList);
			fittingPurchaseService.deleteEntityAll(asList);
			removeData(1, fittingPurchaseList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于配件采购管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteFittingPurchase(FittingPurchase transferFittingPurchase) {
		Tool.sendLog(LOG, userBean, "按下【配件采购管理画面的_删除按钮】");
		fittingPurchaseList.remove(transferFittingPurchase);
		fittingPurchaseService.deleteEntity(transferFittingPurchase);
		removeData(1, fittingPurchaseList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 是否可进行删除操作
	 * @return 
	 */
	public boolean isDeleteInfo(FittingPurchase transferFittingPurchase) {
		return MothedUtil.getDeleteInfo(userBean.getUser(), transferFittingPurchase.getCreater());
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetached();
		String serialNumber = fittingPurchaseManageDto.getSerialNumber();
		Date beginApplyDate = fittingPurchaseManageDto.getBeginApplyDate();
		Date endApplyDate = fittingPurchaseManageDto.getEndApplyDate();
		Date beginPurchaseDate = fittingPurchaseManageDto.getBeginPurchaseDate();
		Date endPurchaseDate = fittingPurchaseManageDto.getEndPurchaseDate();
		if (StringUtil.isNotBlank(serialNumber)) {
			detachedCriteria.add(Restrictions.like(FittingPurchase.SERIAL_NUMBER, serialNumber, MatchMode.ANYWHERE));
		}
		if (beginApplyDate != null) {
			detachedCriteria.add(Restrictions.ge(FittingPurchase.APPLY_DATE, beginApplyDate));
		}
		if (endApplyDate != null) {
			detachedCriteria.add(Restrictions.le(FittingPurchase.APPLY_DATE, endApplyDate));
		}
		if (beginPurchaseDate != null) {
			detachedCriteria.add(Restrictions.ge(FittingPurchase.PURCHASE_DATE, beginPurchaseDate));
		}
		if (endPurchaseDate != null) {
			detachedCriteria.add(Restrictions.le(FittingPurchase.PURCHASE_DATE, endPurchaseDate));
		}
		fittingPurchaseList = fittingPurchaseService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingPurchaseService.getRowCount(detachedCriteria));
		}
	}
	
	/**
	 * 根据类型获取到其它bean需要获取的采购单信息
	 * @param type 1：需要采购的，2：需要入库的
	 * @param user 登录用户
	 * @return
	 */
	public List<FittingPurchase> getFittingPurchase(String type, User user) {
		DetachedCriteria detachedCriteria = getDetached();
		if ("1".equals(type)) {
			detachedCriteria.add(Restrictions.eq(FittingPurchase.PURCHASE_USER, user));
			detachedCriteria.add(Restrictions.or(Restrictions.eq(FittingPurchase.STATE, FittingPurchase.STATE_4), Restrictions.eq(FittingPurchase.STATE, FittingPurchase.STATE_5)));
		} else {
			detachedCriteria.add(Restrictions.eq(FittingPurchase.STORAGE_USER, user));
			detachedCriteria.add(Restrictions.eq(FittingPurchase.STATE, FittingPurchase.STATE_6));
		}
		List<FittingPurchase> fittingPurchaseList = fittingPurchaseService.getByDetachedCriteria(detachedCriteria, 0, 10);
		if (!fittingPurchaseList.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(FittingPurchaseDetail.class);
			detachedCriteria.createAlias(FittingPurchaseDetail.FITTING_PURCHASE, FittingPurchaseDetail.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FittingPurchaseDetail.FITTING_PURCHASE, fittingPurchaseList));
			List<FittingPurchaseDetail> fittingPurchaseDetailList = fittingPurchaseDetailService.getByDetachedCriteria(detachedCriteria);
			for (FittingPurchase fittingPurchase : fittingPurchaseList) {
				List<FittingPurchaseDetail> detailList = new ArrayList<FittingPurchaseDetail>();
				for (FittingPurchaseDetail fittingPurchaseDetail : fittingPurchaseDetailList) {
					if (fittingPurchase.equals(fittingPurchaseDetail.getFittingPurchase())) {
						detailList.add(fittingPurchaseDetail);
					}
				}
				fittingPurchase.setFittingPurchaseDetailList(detailList);
			}
		}
		return fittingPurchaseList;
	}
	
	/**
	 * 用于别的bean调用的更新
	 * @param fittingPurchase
	 */
	public void updateFittingPurchaseInfo(FittingPurchase fittingPurchase) {
		fittingPurchaseService.updateFittingPurchase(fittingPurchase);
	}

	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingPurchase.class);
		detachedCriteria.createAlias(FittingPurchase.PURCHASE_USER, FittingPurchase.PURCHASE_USER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingPurchase.STORAGE_USER, FittingPurchase.STORAGE_USER, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return detachedCriteria;
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人配件采购管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.FITTING_PURCHASE_MANAGE;
	}

	/**
	 * @param fittingPurchaseDetailService the fittingPurchaseDetailService to set
	 */
	public void setFittingPurchaseDetailService(
			FittingPurchaseDetailService fittingPurchaseDetailService) {
		this.fittingPurchaseDetailService = fittingPurchaseDetailService;
	}

	/**
	 * @param fittingPurchaseExamineService the fittingPurchaseExamineService to set
	 */
	public void setFittingPurchaseExamineService(
			FittingPurchaseExamineService fittingPurchaseExamineService) {
		this.fittingPurchaseExamineService = fittingPurchaseExamineService;
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
	 * get fittingPurchaseManageDto
	 * @return the fittingPurchaseManageDto
	 */
	public FittingPurchaseManageDto getFittingPurchaseManageDto() {
		return fittingPurchaseManageDto;
	}

	/**
	 * set fittingPurchaseManageDto
	 * @param fittingPurchaseManageDto the fittingPurchaseManageDto to set
	 */
	public void setFittingPurchaseManageDto(FittingPurchaseManageDto fittingPurchaseManageDto) {
		this.fittingPurchaseManageDto = fittingPurchaseManageDto;
	}

	/**
	 * get fittingPurchaseList
	 * @return the fittingPurchaseList
	 */
	public List<FittingPurchase> getFittingPurchaseList() {
		return fittingPurchaseList;
	}

	/**
	 * set fittingPurchaseList
	 * @param fittingPurchaseList the fittingPurchaseList to set
	 */
	public void setFittingPurchaseList(List<FittingPurchase> fittingPurchaseList) {
		this.fittingPurchaseList = fittingPurchaseList;
	}

	/**
	 * @return the fittingPurchaseExamineList
	 */
	public List<FittingPurchaseExamine> getFittingPurchaseExamineList() {
		return fittingPurchaseExamineList;
	}

	/**
	 * @param fittingPurchaseExamineList the fittingPurchaseExamineList to set
	 */
	public void setFittingPurchaseExamineList(
			List<FittingPurchaseExamine> fittingPurchaseExamineList) {
		this.fittingPurchaseExamineList = fittingPurchaseExamineList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingPurchase[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingPurchase[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
