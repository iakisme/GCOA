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

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.fitting.FittingLibraryManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.fitting.FittingLibraryManageDto;
import com.qylm.entity.FittingLibrary;
import com.qylm.service.FittingLibraryService;

/**
 * 配件出库管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingLibraryManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8164175075064545416L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingLibraryManageBean.class);
	
	/**
	 * 保存配件出库管理画面需要保存的值
	 */
	private FittingLibraryManageDto fittingLibraryManageDto = new FittingLibraryManageDto();

	/**
	 * 配件出库列表（检索结果）
	 */
	private List<FittingLibrary> fittingLibraryList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingLibrary[] selectedModels;

	/**
	 * 配件出库管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 配件出库管理业务类
	 */
	@ManagedProperty(value="#{fittingLibraryService}")
	private FittingLibraryService fittingLibraryService;
	
	/**
	 * 查询出所有配件出库列表
	 * 
	 * @return 配件出库管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_配件出库管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_LIBRARY_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至配件出库登陆画面，新建一家配件出库
	 * @return 配件出库登陆画面
	 */
	public String newFittingLibrary() {
		Tool.sendLog(LOG, userBean, "按下【配件出库管理画面_新建按钮】");
		return Tool.getBackBean(FittingLibraryCreateBean.class).newCreate(new FittingLibraryManageReturner(fittingLibraryManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至配件出库登陆画面
	 * @return 配件出库登陆画面
	 */
	public String updateFittingLibrary(FittingLibrary transferFittingLibrary) {
		Tool.sendLog(LOG, userBean, "按下【配件出库管理画面_修改按钮】");
		return Tool.getBackBean(FittingLibraryCreateBean.class).updateDetail(new FittingLibraryManageReturner(fittingLibraryManageDto, currentPage), transferFittingLibrary);
	}
	
	/**
	 * 此方法绑定于配件出库管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出配件出库
	 * @return 画面不跳转
	 */
	public void selectFittingLibrary() {
		Tool.sendLog(LOG, userBean, "按下【配件出库管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于配件出库管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【配件出库管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<FittingLibrary> asList = Arrays.asList(selectedModels);
			fittingLibraryList.removeAll(asList);
			fittingLibraryService.deleteEntityAll(asList);
			removeData(1, fittingLibraryList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于配件出库管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteFittingLibrary(FittingLibrary transferFittingLibrary) {
		Tool.sendLog(LOG, userBean, "按下【配件出库管理画面的_删除按钮】");
		fittingLibraryList.remove(transferFittingLibrary);
		fittingLibraryService.deleteEntity(transferFittingLibrary);
		removeData(1, fittingLibraryList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 是否可进行删除操作
	 * @return 
	 */
	public boolean isDeleteInfo(FittingLibrary transferFittingLibrary) {
		return MothedUtil.getDeleteInfo(userBean.getUser(), transferFittingLibrary.getCreater());
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getDetached();
		String number = fittingLibraryManageDto.getNumber();
		Date beginSorageDate = fittingLibraryManageDto.getBeginLibraryDate();
		Date endSorageDate = fittingLibraryManageDto.getEndLibraryDate();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(FittingLibrary.NUMBER, number, MatchMode.ANYWHERE));
		}
		if (beginSorageDate != null) {
			detachedCriteria.add(Restrictions.ge(FittingLibrary.LIBRARY_DATE, beginSorageDate));
		}
		if (endSorageDate != null) {
			detachedCriteria.add(Restrictions.le(FittingLibrary.LIBRARY_DATE, endSorageDate));
		}
		fittingLibraryList = fittingLibraryService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingLibraryService.getRowCount(detachedCriteria));
		}
	}
	
	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingLibrary.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return detachedCriteria;
	}
	
	/**
	 * 保存出库申请
	 * @param fittingLibrary
	 */
	public void saveFittingLibrary(FittingLibrary fittingLibrary) {
		fittingLibrary.setState(true);
		fittingLibraryService.saveFittingLibrary(fittingLibrary, fittingLibrary.getFittingLibraryDetailList());
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人配件出库管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.FITTING_LIBRARY_MANAGE;
	}

	/**
	 * set fittingLibraryService
	 * @param fittingLibraryService the fittingLibraryService to set
	 */
	public void setFittingLibraryService(FittingLibraryService fittingLibraryService) {
		this.fittingLibraryService = fittingLibraryService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingLibraryManageDto
	 * @return the fittingLibraryManageDto
	 */
	public FittingLibraryManageDto getFittingLibraryManageDto() {
		return fittingLibraryManageDto;
	}

	/**
	 * set fittingLibraryManageDto
	 * @param fittingLibraryManageDto the fittingLibraryManageDto to set
	 */
	public void setFittingLibraryManageDto(FittingLibraryManageDto fittingLibraryManageDto) {
		this.fittingLibraryManageDto = fittingLibraryManageDto;
	}

	/**
	 * get fittingLibraryList
	 * @return the fittingLibraryList
	 */
	public List<FittingLibrary> getFittingLibraryList() {
		return fittingLibraryList;
	}

	/**
	 * set fittingLibraryList
	 * @param fittingLibraryList the fittingLibraryList to set
	 */
	public void setFittingLibraryList(List<FittingLibrary> fittingLibraryList) {
		this.fittingLibraryList = fittingLibraryList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingLibrary[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingLibrary[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
