package com.qylm.bean.fitting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.fitting.FittingRepertoryManageReturner;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.fitting.FittingRepertoryManageDto;
import com.qylm.entity.FileControl;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingRepertory;
import com.qylm.service.FileControlService;
import com.qylm.service.FittingRepertoryService;

/**
 * 配件库存信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class FittingRepertoryManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2783897612072674726L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingRepertoryManageBean.class);
	
	/**
	 * 保存配件库存管理画面需要保存的值
	 */
	private FittingRepertoryManageDto fittingRepertoryManageDto = new FittingRepertoryManageDto();

	/**
	 * 配件库存列表（检索结果）
	 */
	private List<FittingRepertory> fittingRepertoryList;
	
	/**
	 * 删除复选框选择的值
	 */
	private FittingRepertory[] selectedModels;

	/**
	 * 配件库存管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 配件库存管理业务类
	 */
	@ManagedProperty(value="#{fittingRepertoryService}")
	private FittingRepertoryService fittingRepertoryService;
	
	/**
	 * 文件业务类
	 */
	@ManagedProperty(value = "#{fileControlService}")
	private FileControlService fileControlService;
	
	/**
	 * 查询出所有配件库存列表
	 * 
	 * @return 配件库存管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_配件库存管理按钮】");
		fetchData(0, true);
		return Navigation.FITTING_REPERTORY_MANAGE;
	}
	
	/**
	 * 此方法绑定与配件库存管理画面的添加按钮
	 * @return
	 */
	public String newFittingRepertory() {
		Tool.sendLog(LOG, userBean, "按下【配件库存管理画面_添加按钮】");
		return Tool.getBackBean(FittingRepertoryCreateBean.class).newCreate(new FittingRepertoryManageReturner(fittingRepertoryManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至配件库存登陆画面
	 * @return 配件库存登陆画面
	 */
	public String updateFittingRepertory(FittingRepertory transferFittingRepertory) {
		Tool.sendLog(LOG, userBean, "按下【配件库存管理画面_修改按钮】");
		return Tool.getBackBean(FittingRepertoryCreateBean.class).updateDetail(new FittingRepertoryManageReturner(fittingRepertoryManageDto, currentPage), transferFittingRepertory);
	}
	
	/**
	 * 此方法绑定于配件库存管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出配件库存
	 * @return 画面不跳转
	 */
	public void selectFittingRepertory() {
		Tool.sendLog(LOG, userBean, "按下【配件库存管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingRepertory.class);
		detachedCriteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String carName = fittingRepertoryManageDto.getCarName();
		String fittingBrand = fittingRepertoryManageDto.getFittingBrand();
		String fittingName = fittingRepertoryManageDto.getFittingName();
		if (StringUtil.isNotBlank(carName)) {
			detachedCriteria.add(Restrictions.like(FittingRepertory.CAR_NAME, carName, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(fittingBrand)) {
			detachedCriteria.add(Restrictions.like(FittingRepertory.FITTING_BRAND, fittingBrand, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingRepertory.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingRepertoryList = fittingRepertoryService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(fittingRepertoryService.getRowCount(detachedCriteria));
		}
		if (!fittingRepertoryList.isEmpty()) {
			List<FittingInfo> fittingInfoList = new ArrayList<FittingInfo>();
			for (FittingRepertory fittingRepertory : fittingRepertoryList) {
				fittingInfoList.add(fittingRepertory.getFittingInfo());
			}
			Map<Integer, FileControl> fileControlOneList = fileControlService.getFileControlOneList(fittingInfoList);
			for (FittingRepertory fittingRepertory : fittingRepertoryList) {
				for (Entry<Integer, FileControl> map : fileControlOneList.entrySet()) {
					if (fittingRepertory.getFittingInfo().getId().equals(map.getKey())) {
						fittingRepertory.getFittingInfo().setFileControl(map.getValue());
						break;
					}
				}
			}
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人配件库存管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.FITTING_REPERTORY_MANAGE;
	}

	/**
	 * @param fileControlService the fileControlService to set
	 */
	public void setFileControlService(FileControlService fileControlService) {
		this.fileControlService = fileControlService;
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
	 * get fittingRepertoryManageDto
	 * @return the fittingRepertoryManageDto
	 */
	public FittingRepertoryManageDto getFittingRepertoryManageDto() {
		return fittingRepertoryManageDto;
	}

	/**
	 * set fittingRepertoryManageDto
	 * @param fittingRepertoryManageDto the fittingRepertoryManageDto to set
	 */
	public void setFittingRepertoryManageDto(FittingRepertoryManageDto fittingRepertoryManageDto) {
		this.fittingRepertoryManageDto = fittingRepertoryManageDto;
	}

	/**
	 * get fittingRepertoryList
	 * @return the fittingRepertoryList
	 */
	public List<FittingRepertory> getFittingRepertoryList() {
		return fittingRepertoryList;
	}

	/**
	 * set fittingRepertoryList
	 * @param fittingRepertoryList the fittingRepertoryList to set
	 */
	public void setFittingRepertoryList(List<FittingRepertory> fittingRepertoryList) {
		this.fittingRepertoryList = fittingRepertoryList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public FittingRepertory[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(FittingRepertory[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
