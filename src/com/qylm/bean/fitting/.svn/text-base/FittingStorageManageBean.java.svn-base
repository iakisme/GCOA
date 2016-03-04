package com.qylm.bean.fitting;

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
import com.qylm.bean.returner.fitting.FittingStorageManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.fitting.FittingStorageManageDto;
import com.qylm.entity.FittingStorage;
import com.qylm.service.FittingStorageService;

/**
 * 配件入库管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingStorageManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8164175075064545416L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingStorageManageBean.class);
	
	/**
	 * 保存配件入库管理画面需要保存的值
	 */
	private FittingStorageManageDto fittingStorageManageDto = new FittingStorageManageDto();

	/**
	 * 配件入库列表（检索结果）
	 */
	private List<FittingStorage> fittingStorageList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingStorage[] selectedModels;

	/**
	 * 配件入库管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 配件入库管理业务类
	 */
	@ManagedProperty(value="#{fittingStorageService}")
	private FittingStorageService fittingStorageService;
	
	/**
	 * 查询出所有配件入库列表
	 * 
	 * @return 配件入库管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_配件入库管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_STORAGE_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至配件入库登陆画面，新建一家配件入库
	 * @return 配件入库登陆画面
	 */
	public String newFittingStorage() {
		Tool.sendLog(LOG, userBean, "按下【配件入库管理画面_新建按钮】");
		return Tool.getBackBean(FittingStorageCreateBean.class).newCreate(new FittingStorageManageReturner(fittingStorageManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至配件入库登陆画面
	 * @return 配件入库登陆画面
	 */
	public String updateFittingStorage(FittingStorage transferFittingStorage) {
		Tool.sendLog(LOG, userBean, "按下【配件入库管理画面_修改按钮】");
		return Tool.getBackBean(FittingStorageCreateBean.class).updateDetail(new FittingStorageManageReturner(fittingStorageManageDto, currentPage), transferFittingStorage);
	}
	
	/**
	 * 此方法绑定于配件入库管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出配件入库
	 * @return 画面不跳转
	 */
	public void selectFittingStorage() {
		Tool.sendLog(LOG, userBean, "按下【配件入库管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于配件入库管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【配件入库管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<FittingStorage> asList = Arrays.asList(selectedModels);
			fittingStorageList.removeAll(asList);
			fittingStorageService.deleteEntityAll(asList);
			removeData(1, fittingStorageList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于配件入库管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteFittingStorage(FittingStorage transferFittingStorage) {
		Tool.sendLog(LOG, userBean, "按下【配件入库管理画面的_删除按钮】");
		fittingStorageList.remove(transferFittingStorage);
		fittingStorageService.deleteEntity(transferFittingStorage);
		removeData(1, fittingStorageList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 是否可进行删除操作
	 * @return 
	 */
	public boolean isDeleteInfo(FittingStorage transferFittingStorage) {
		return MothedUtil.getDeleteInfo(userBean.getUser(), transferFittingStorage.getCreater());
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetached();
		String number = fittingStorageManageDto.getNumber();
		Date beginSorageDate = fittingStorageManageDto.getBeginSorageDate();
		Date endSorageDate = fittingStorageManageDto.getEndSorageDate();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(FittingStorage.NUMBER, number, MatchMode.ANYWHERE));
		}
		if (beginSorageDate != null) {
			detachedCriteria.add(Restrictions.ge(FittingStorage.STORAGE_DATE, beginSorageDate));
		}
		if (endSorageDate != null) {
			detachedCriteria.add(Restrictions.le(FittingStorage.STORAGE_DATE, endSorageDate));
		}
		fittingStorageList = fittingStorageService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingStorageService.getRowCount(detachedCriteria));
		}
	}
	
	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingStorage.class);
		detachedCriteria.createAlias(FittingStorage.FITTING_PURCHASE, FittingStorage.FITTING_PURCHASE, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return detachedCriteria;
	}
	
	/**
	 * 保存入库申请
	 * @param fittingStorage
	 */
	public void saveFittingStorage(FittingStorage fittingStorage) {
		fittingStorage.setState(true);
		fittingStorageService.saveFittingStorage(fittingStorage, fittingStorage.getFittingStorageDetailList());
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人配件入库管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.FITTING_STORAGE_MANAGE;
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
	 * get fittingStorageManageDto
	 * @return the fittingStorageManageDto
	 */
	public FittingStorageManageDto getFittingStorageManageDto() {
		return fittingStorageManageDto;
	}

	/**
	 * set fittingStorageManageDto
	 * @param fittingStorageManageDto the fittingStorageManageDto to set
	 */
	public void setFittingStorageManageDto(FittingStorageManageDto fittingStorageManageDto) {
		this.fittingStorageManageDto = fittingStorageManageDto;
	}

	/**
	 * get fittingStorageList
	 * @return the fittingStorageList
	 */
	public List<FittingStorage> getFittingStorageList() {
		return fittingStorageList;
	}

	/**
	 * set fittingStorageList
	 * @param fittingStorageList the fittingStorageList to set
	 */
	public void setFittingStorageList(List<FittingStorage> fittingStorageList) {
		this.fittingStorageList = fittingStorageList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingStorage[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingStorage[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
