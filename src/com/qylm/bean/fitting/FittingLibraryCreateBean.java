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
import com.qylm.dto.fitting.FittingLibraryCreateDto;
import com.qylm.dxo.FittingLibraryCreateDxo;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.FittingLibrary;
import com.qylm.entity.FittingLibraryDetail;
import com.qylm.entity.FittingRepertory;
import com.qylm.exception.FittingLibraryException;
import com.qylm.service.FittingLibraryDetailService;
import com.qylm.service.FittingLibraryService;
import com.qylm.service.FittingRepertoryService;

/**
 * 配件出库登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class FittingLibraryCreateBean implements CreateBean<FittingLibrary>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingLibraryCreateBean.class);

	/**
	 * 存放配件出库登陆画面需要保存的值
	 */
	private FittingLibraryCreateDto fittingLibraryCreateDto = new FittingLibraryCreateDto();
	
	/**
	 * 配件出库bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 配件出库业务类
	 */
	@ManagedProperty(value="#{fittingLibraryService}")
	private FittingLibraryService fittingLibraryService;
	
	/**
	 * 配件出库详细业务类
	 */
	@ManagedProperty(value="#{fittingLibraryDetailService}")
	private FittingLibraryDetailService fittingLibraryDetailService;
	
	/**
	 * 配件库存详细业务类
	 */
	@ManagedProperty(value="#{fittingRepertoryService}")
	private FittingRepertoryService fittingRepertoryService;
	
	/**
	 * 此方法绑定于配件出库登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个配件出库
	 * @return 配件出库登陆画面
	 */
	public String newFittingLibrary() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_新建按钮】");
		fittingLibraryCreateDto.setNumber(null);
		fittingLibraryCreateDto.setLibraryDate(null);
		fittingLibraryCreateDto.setLibraryType(null);
		fittingLibraryCreateDto.setRemark(null);
		fittingLibraryCreateDto.setState(false);
		fittingLibraryCreateDto.setFittingLibraryDetailList(null);
		fittingLibraryCreateDto.setFittingLibraryDetail(null);
		fittingLibraryCreateDto.setFittingName(null);
		fittingLibraryCreateDto.setFittingRepertoryList(null);
		fittingLibraryCreateDto.setTransferFittingLibrary(null);
		fittingLibraryCreateDto.setCreater(null);
		fittingLibraryCreateDto.setBelongingUser(null);
		return Navigation.FITTING_LIBRARY_CREATE;
	}
	
	/**
	 * 此方法绑定于配件出库登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_返回按钮】");
		return fittingLibraryCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于配件出库登陆画面的保存按钮 
	 * 实现功能：根据transferFittingLibrary对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveFittingLibrary() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_保存按钮】");
		try {
			FittingLibrary transferFittingLibrary = fittingLibraryCreateDto.getTransferFittingLibrary();
			if (transferFittingLibrary == null) {
				transferFittingLibrary = new FittingLibrary();
				getNumber();
				fittingLibraryCreateDto.setCreater(userBean.getUser());
				fittingLibraryCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferFittingLibrary);
				transferFittingLibrary.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingLibraryService.saveFittingLibrary(transferFittingLibrary, fittingLibraryCreateDto.getFittingLibraryDetailList());
				fittingLibraryCreateDto.setTransferFittingLibrary(transferFittingLibrary);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferFittingLibrary);
				transferFittingLibrary.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingLibraryService.updateFittingLibrary(transferFittingLibrary, fittingLibraryCreateDto.getFittingLibraryDetailList());
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("配件出库单号已存在，请确认！");
		} catch (FittingLibraryException e) {
			
		}
	}
	
	/**
	 * 此方法绑定于配件出库登陆画面的提交按钮 
	 * 实现功能：根据transferFittingLibrary对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void apply() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_确认按钮】");
		FittingLibrary transferFittingLibrary = fittingLibraryCreateDto.getTransferFittingLibrary();
		fittingLibraryCreateDto.setState(true);
		create(transferFittingLibrary);
		transferFittingLibrary.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		try {
			fittingLibraryService.updateFittingLibrary(transferFittingLibrary, fittingLibraryCreateDto.getFittingLibraryDetailList());
			Tool.sendErrorMessage("配件出库成功！");
		} catch (FittingLibraryException e) {
			
		}
	}
	
	/**
	 * 赋值
	 * @param transferFittingLibrary
	 */
	private void create(FittingLibrary transferFittingLibrary) {
		List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryCreateDto.getFittingLibraryDetailList();
		if (fittingLibraryDetailList != null) {
			for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
				fittingLibraryDetail.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingLibraryDetail.setFittingLibrary(transferFittingLibrary);
			}
		}
		FittingLibraryCreateDxo.dtoToEntity(fittingLibraryCreateDto, transferFittingLibrary);
	}
	
	public String newCreate(Returner<?, ?, ?> returner) {
		fittingLibraryCreateDto.setReturner(returner);
		fittingLibraryCreateDto.setLibraryDate(DateUtil.getNowyyyymmdd());
		fittingLibraryCreateDto.setCreater(userBean.getUser());
		return Navigation.FITTING_LIBRARY_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, FittingLibrary fittingLibrary) {
		fittingLibraryCreateDto.setReturner(returner);
		FittingLibraryCreateDxo.entityToDto(fittingLibrary, fittingLibraryCreateDto);
		fittingLibraryCreateDto.setTransferFittingLibrary(fittingLibrary);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingLibraryDetail.class);
		detachedCriteria.createAlias(FittingLibraryDetail.FITTING_LIBRARY, FittingLibraryDetail.FITTING_LIBRARY, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FittingLibraryDetail.VEHICLE_INFO, FittingLibraryDetail.VEHICLE_INFO, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FittingLibraryDetail.FITTING_LIBRARY, fittingLibrary));
		List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryDetailService.getByDetachedCriteria(detachedCriteria);
		if (!fittingLibrary.isState()) {
			if (!fittingLibraryDetailList.isEmpty()) {
				detachedCriteria = DetachedCriteria.forClass(FittingRepertory.class);
				detachedCriteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
				MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
				List<String> carNameList = new ArrayList<String>();
				List<String> fittingBrandList = new ArrayList<String>();
				List<String> fittingNameList = new ArrayList<String>();
				List<String> modelList = new ArrayList<String>();
				for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
					carNameList.add(fittingLibraryDetail.getCarName());
					fittingBrandList.add(fittingLibraryDetail.getFittingBrand());
					fittingNameList.add(fittingLibraryDetail.getFittingName());
					modelList.add(fittingLibraryDetail.getModel());
				}
				detachedCriteria.add(Restrictions.in(FittingRepertory.CAR_NAME, carNameList));
				detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_BRAND, fittingBrandList));
				detachedCriteria.add(Restrictions.in(FittingRepertory.FITTING_NAME, fittingNameList));
				detachedCriteria.add(Restrictions.in(FittingRepertory.MODEL, modelList));
				List<FittingRepertory> fittingRepertoryList = fittingRepertoryService.getByDetachedCriteria(detachedCriteria);
				for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
					for (FittingRepertory fittingRepertory : fittingRepertoryList) {
						FittingInfo fittingInfo = fittingRepertory.getFittingInfo();
						if (StringUtil.isEquals(fittingInfo.getCarName(), fittingLibraryDetail.getCarName())
								&& StringUtil.isEquals(fittingInfo.getFittingBrand(), fittingLibraryDetail.getFittingBrand())
								&& StringUtil.isEquals(fittingInfo.getFittingName(), fittingLibraryDetail.getFittingName())
								&& StringUtil.isEquals(fittingInfo.getModel(), fittingLibraryDetail.getModel())) {
							fittingLibraryDetail.setRepertorySum(fittingRepertory.getRepertorySum());
							break;
						}
					}
				}
			}
		}
		fittingLibraryCreateDto.setFittingLibraryDetailList(fittingLibraryDetailList);
		return Navigation.FITTING_LIBRARY_CREATE;
	}
	
	/**
	 * 增加一行采购配件信息
	 */
	public void addFittingLibraryDateil() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_添加采购配件按钮】");
		List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryCreateDto.getFittingLibraryDetailList();
		if (fittingLibraryDetailList == null) {
			fittingLibraryDetailList = new ArrayList<FittingLibraryDetail>();
			fittingLibraryCreateDto.setFittingLibraryDetailList(fittingLibraryDetailList);
		}
		FittingLibraryDetail fittingLibraryDetail = new FittingLibraryDetail();
		FittingLibrary transferFittingLibrary = fittingLibraryCreateDto.getTransferFittingLibrary();
		fittingLibraryDetail.setFittingLibrary(transferFittingLibrary);
		fittingLibraryDetail.setCreater(userBean.getUser());
		fittingLibraryDetailList.add(fittingLibraryDetail);
	}
	
	/**
	 * 删除配件出库
	 * @param fittingLibraryDetail
	 */
	public void deleteFittingLibraryDetail(FittingLibraryDetail fittingLibraryDetail) {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_删除按钮】");
		List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryCreateDto.getFittingLibraryDetailList();
		fittingLibraryDetailList.remove(fittingLibraryDetail);
		if (fittingLibraryDetail.getId() != null) {
			fittingLibraryDetailService.deleteEntity(fittingLibraryDetail);
		}
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 查询出所有配件列表信息
	 * @param fittingLibraryDetail
	 */
	public void selectFittingRepertory() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_搜索配件信息按钮】");
		fittingLibraryCreateDto.setQueryFittingRepertoryList(null);
		selectFitting();
	}
	
	/**
	 * 查询出配件信息
	 */
	private void selectFitting() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingRepertory.class);
		detachedCriteria.createAlias(FittingRepertory.FITTING_INFO, FittingRepertory.FITTING_INFO, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryCreateDto.getFittingLibraryDetailList();
		if (fittingLibraryDetailList != null && !fittingLibraryDetailList.isEmpty()) {
			List<String> carNameList = new ArrayList<String>();
			List<String> fittingBrandList = new ArrayList<String>();
			List<String> fittingNameList = new ArrayList<String>();
			List<String> modelList = new ArrayList<String>();
			for (FittingLibraryDetail fittingLibraryDetail : fittingLibraryDetailList) {
				carNameList.add(fittingLibraryDetail.getCarName());
				fittingBrandList.add(fittingLibraryDetail.getFittingBrand());
				fittingNameList.add(fittingLibraryDetail.getFittingName());
				modelList.add(fittingLibraryDetail.getModel());
			}
			detachedCriteria.add(
					Restrictions.or(Restrictions.not(Restrictions.in(FittingRepertory.CAR_NAME, carNameList)), 
					Restrictions.or(Restrictions.not(Restrictions.in(FittingRepertory.FITTING_BRAND, fittingBrandList)), 
					Restrictions.or(Restrictions.not(Restrictions.in(FittingRepertory.FITTING_NAME, fittingNameList)), 
					Restrictions.not(Restrictions.in(FittingRepertory.MODEL, modelList))))));
		}
		String fittingName = fittingLibraryCreateDto.getFittingName();
		if (StringUtil.isNotBlank(fittingName)) {
			detachedCriteria.add(Restrictions.like(FittingRepertory.FITTING_NAME, fittingName, MatchMode.ANYWHERE));
		}
		fittingLibraryCreateDto.setFittingRepertoryList(fittingRepertoryService.getByDetachedCriteria(detachedCriteria, 0, 20));
	}
	
	/**
	 * 确认选择配件库存
	 */
	public void queryFindFittingRepertory() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_确认选择按钮】");
		List<FittingRepertory> queryFittingRepertoryList = fittingLibraryCreateDto.getQueryFittingRepertoryList();
		if (queryFittingRepertoryList != null && !queryFittingRepertoryList.isEmpty()) {
			List<FittingLibraryDetail> fittingLibraryDetailList = fittingLibraryCreateDto.getFittingLibraryDetailList();
			if (fittingLibraryDetailList == null) {
				fittingLibraryDetailList = new ArrayList<FittingLibraryDetail>();
				fittingLibraryCreateDto.setFittingLibraryDetailList(fittingLibraryDetailList);
			}
			int size = fittingLibraryDetailList.size();
			FittingLibraryDetail fittingLibraryDetail;
			for (FittingRepertory fittingRepertory : queryFittingRepertoryList) {
				FittingInfo fittingInfo = fittingRepertory.getFittingInfo();
				boolean state = false;
				for (int i = 0; i < size; i++) {
					fittingLibraryDetail = fittingLibraryDetailList.get(i);
					if (StringUtil.isEquals(fittingInfo.getCarName(), fittingLibraryDetail.getCarName())
							&& StringUtil.isEquals(fittingInfo.getFittingBrand(), fittingLibraryDetail.getFittingBrand())
							&& StringUtil.isEquals(fittingInfo.getFittingName(), fittingLibraryDetail.getFittingName())
							&& StringUtil.isEquals(fittingInfo.getModel(), fittingLibraryDetail.getModel())) {
						state = true;
						break;
					}
				}
				if (!state) {
					fittingLibraryDetail = new FittingLibraryDetail();
					fittingLibraryDetail.setCarName(fittingInfo.getCarName());
					fittingLibraryDetail.setFittingBrand(fittingInfo.getFittingBrand());
					fittingLibraryDetail.setFittingName(fittingInfo.getFittingName());
					fittingLibraryDetail.setModel(fittingInfo.getModel());
					fittingLibraryDetail.setPrice(fittingInfo.getPrice());
					fittingLibraryDetail.setQuantity(BigDecimal.ONE);
					fittingLibraryDetail.setRepertorySum(fittingRepertory.getRepertorySum());
					fittingLibraryDetailList.add(fittingLibraryDetail);
				}
			}
		}
		fittingLibraryCreateDto.setQueryFittingRepertoryList(null);
	}
	
	/**
	 * 选择车辆列表信息
	 * @param fittingInfo
	 */
	public void findFittingRepertory(FittingRepertory fittingRepertory) {
		fittingLibraryCreateDto.getFittingRepertoryList().remove(fittingRepertory);
		List<FittingRepertory> queryFittingRepertoryList = fittingLibraryCreateDto.getQueryFittingRepertoryList();
		if (queryFittingRepertoryList == null) {
			queryFittingRepertoryList = new ArrayList<FittingRepertory>();
			fittingLibraryCreateDto.setQueryFittingRepertoryList(queryFittingRepertoryList);
		}
		queryFittingRepertoryList.add(fittingRepertory);
	}
	
	/**
	 * 返回到本画面
	 * @return
	 */
	public String returner() {
		Tool.sendLog(LOG, userBean, "【配件出库登陆画面_返回按钮】");
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * 获取编号
	 */
	private void getNumber() {
		String purchaseNumber = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYYMMDD);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingLibrary.class);
		detachedCriteria.add(Restrictions.like(FittingLibrary.NUMBER, purchaseNumber, MatchMode.START));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(FittingLibrary.NUMBER));
	}
	
	/**
	 * @param fittingRepertoryService the fittingRepertoryService to set
	 */
	public void setFittingRepertoryService(
			FittingRepertoryService fittingRepertoryService) {
		this.fittingRepertoryService = fittingRepertoryService;
	}

	/**
	 * @param fittingLibraryDetailService the fittingLibraryDetailService to set
	 */
	public void setFittingLibraryDetailService(
			FittingLibraryDetailService fittingLibraryDetailService) {
		this.fittingLibraryDetailService = fittingLibraryDetailService;
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
	 * get fittingLibraryCreateDto
	 * @return the fittingLibraryCreateDto
	 */
	public FittingLibraryCreateDto getFittingLibraryCreateDto() {
		return fittingLibraryCreateDto;
	}

	/**
	 * set fittingLibraryCreateDto
	 * @param fittingLibraryCreateDto the fittingLibraryCreateDto to set
	 */
	public void setFittingLibraryCreateDto(FittingLibraryCreateDto fittingLibraryCreateDto) {
		this.fittingLibraryCreateDto = fittingLibraryCreateDto;
	}

}
