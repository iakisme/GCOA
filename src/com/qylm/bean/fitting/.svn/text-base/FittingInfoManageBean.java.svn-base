package com.qylm.bean.fitting;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.qylm.bean.returner.fitting.FittingInfoManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.fitting.FittingInfoManageDto;
import com.qylm.entity.FileControl;
import com.qylm.entity.FittingInfo;
import com.qylm.service.FileControlService;
import com.qylm.service.FittingInfoService;

/**
 * 配件信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingInfoManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9200349285201120937L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingInfoManageBean.class);
	
	/**
	 * 保存配件管理画面需要保存的值
	 */
	private FittingInfoManageDto fittingInfoManageDto = new FittingInfoManageDto();

	/**
	 * 配件列表（检索结果）
	 */
	private List<FittingInfo> fittingInfoList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingInfo[] selectedModels;

	/**
	 * 配件管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 配件管理业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 文件业务类
	 */
	@ManagedProperty(value = "#{fileControlService}")
	private FileControlService fileControlService;
	
	/**
	 * 查询出所有配件列表
	 * 
	 * @return 配件管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_配件管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_INFO_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至配件登陆画面，新建一家配件
	 * @return 配件登陆画面
	 */
	public String newFittingInfo() {
		Tool.sendLog(LOG, userBean, "按下【配件管理画面_新建按钮】");
		return Tool.getBackBean(FittingInfoCreateBean.class).newCreate(new FittingInfoManageReturner(fittingInfoManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至配件登陆画面
	 * @return 配件登陆画面
	 */
	public String updateFittingInfo(FittingInfo transferFittingInfo) {
		Tool.sendLog(LOG, userBean, "按下【配件管理画面_修改按钮】");
		return Tool.getBackBean(FittingInfoCreateBean.class).updateDetail(new FittingInfoManageReturner(fittingInfoManageDto, currentPage), transferFittingInfo);
	}
	
	/**
	 * 此方法绑定于配件管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出配件
	 * @return 画面不跳转
	 */
	public void selectFittingInfo() {
		Tool.sendLog(LOG, userBean, "按下【配件管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于配件管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【配件管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<FittingInfo> asList = Arrays.asList(selectedModels);
			fittingInfoList.removeAll(asList);
			fittingInfoService.deleteEntityAll(asList);
			removeData(1, fittingInfoList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于配件管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteFittingInfo(FittingInfo transferFittingInfo) {
		Tool.sendLog(LOG, userBean, "按下【配件管理画面的_删除按钮】");
		fittingInfoList.remove(transferFittingInfo);
		fittingInfoService.deleteEntity(transferFittingInfo);
		removeData(1, fittingInfoList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String carName = fittingInfoManageDto.getCarName();
		String fittingBrand = fittingInfoManageDto.getFittingBrand();
		String fittingName = fittingInfoManageDto.getFittingName();
		if (StringUtil.isNotBlank(carName)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.CAR_NAME, carName, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(fittingBrand)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_BRAND, fittingBrand, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingInfoList = fittingInfoService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingInfoService.getRowCount(detachedCriteria));
		}
		if (!fittingInfoList.isEmpty()) {
			Map<Integer, FileControl> fileControlOneList = fileControlService.getFileControlOneList(fittingInfoList);
			for (FittingInfo fittingInfo : fittingInfoList) {
				for (Entry<Integer, FileControl> map : fileControlOneList.entrySet()) {
					if (fittingInfo.getId().equals(map.getKey())) {
						fittingInfo.setFileControl(map.getValue());
						break;
					}
				}
			}
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人配件管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.FITTING_INFO_MANAGE;
	}

	/**
	 * @param fileControlService the fileControlService to set
	 */
	public void setFileControlService(FileControlService fileControlService) {
		this.fileControlService = fileControlService;
	}

	/**
	 * set fittingInfoService
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingInfoManageDto
	 * @return the fittingInfoManageDto
	 */
	public FittingInfoManageDto getFittingInfoManageDto() {
		return fittingInfoManageDto;
	}

	/**
	 * set fittingInfoManageDto
	 * @param fittingInfoManageDto the fittingInfoManageDto to set
	 */
	public void setFittingInfoManageDto(FittingInfoManageDto fittingInfoManageDto) {
		this.fittingInfoManageDto = fittingInfoManageDto;
	}

	/**
	 * get fittingInfoList
	 * @return the fittingInfoList
	 */
	public List<FittingInfo> getFittingInfoList() {
		return fittingInfoList;
	}

	/**
	 * set fittingInfoList
	 * @param fittingInfoList the fittingInfoList to set
	 */
	public void setFittingInfoList(List<FittingInfo> fittingInfoList) {
		this.fittingInfoList = fittingInfoList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingInfo[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingInfo[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
