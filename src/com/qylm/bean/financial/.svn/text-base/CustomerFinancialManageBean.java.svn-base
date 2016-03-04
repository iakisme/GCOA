package com.qylm.bean.financial;

import java.math.BigDecimal;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.financial.CustomerFinancialManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.financial.CustomerFinancialManageDto;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.FinancialCollectDetail;
import com.qylm.entity.FinancialPayDetail;
import com.qylm.service.CustomerFinancialService;
import com.qylm.service.EngineeringFinancialService;
import com.qylm.service.FinancialCollectDetailService;
import com.qylm.service.FinancialPayDetailService;

/**
 * 客户收支明细管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class CustomerFinancialManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2092014040015417190L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(CustomerFinancialManageBean.class);
	
	/**
	 * 保存客户收支明细管理画面需要保存的值
	 */
	private CustomerFinancialManageDto customerFinancialManageDto = new CustomerFinancialManageDto();

	/**
	 * 客户收支明细列表（检索结果）
	 */
	private List<CustomerFinancial> customerFinancialList;
	
	/**
	 * 收款明细
	 */
	private List<FinancialCollectDetail> financialCollectDetailList;
	
	/**
	 * 付款明细
	 */
	private List<FinancialPayDetail> financialPayDetailList;
	
	/**
	 * 删除复选框选择的值
	 */
	private CustomerFinancial[] selectedModels;

	/**
	 * 客户收支明细管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 客户收支明细管理业务类
	 */
	@ManagedProperty(value="#{customerFinancialService}")
	private CustomerFinancialService customerFinancialService;
	
	/**
	 * 收款项目业务类
	 */
	@ManagedProperty(value="#{engineeringFinancialService}")
	private EngineeringFinancialService engineeringFinancialService;
	
	/**
	 * 工程收款明细业务类
	 */
	@ManagedProperty(value="#{financialCollectDetailService}")
	private FinancialCollectDetailService financialCollectDetailService;
	
	/**
	 * 工程支付明细业务类
	 */
	@ManagedProperty(value="#{financialPayDetailService}")
	private FinancialPayDetailService financialPayDetailService;
	
	/**
	 * 查询出所有客户收支明细列表
	 * 
	 * @return 客户收支明细管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_客户收支明细管理按钮】");
		fetchData(0, true);
		return Navigation.CUSTOMER_FINANCIAL_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至客户收支明细登陆画面，新建一家客户收支明细
	 * @return 客户收支明细登陆画面
	 */
	public String newCustomerFinancial() {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面_新建按钮】");
		return Tool.getBackBean(CustomerFinancialCreateBean.class).newCreate(new CustomerFinancialManageReturner(customerFinancialManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至客户收支明细登陆画面
	 * @return 客户收支明细登陆画面
	 */
	public String updateCustomerFinancial(CustomerFinancial transferCustomerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面_修改按钮】");
		return Tool.getBackBean(CustomerFinancialCreateBean.class).updateDetail(new CustomerFinancialManageReturner(customerFinancialManageDto, currentPage), transferCustomerFinancial);
	}
	
	/**
	 * 此方法绑定于客户收支明细管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出客户收支明细
	 * @return 画面不跳转
	 */
	public void selectCustomerFinancial() {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于客户收支明细管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<CustomerFinancial> asList = Arrays.asList(selectedModels);
			customerFinancialList.removeAll(asList);
			customerFinancialService.deleteEntityAll(asList);
			removeData(1, customerFinancialList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于客户收支明细管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteCustomerFinancial(CustomerFinancial transferCustomerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面的_删除按钮】");
		customerFinancialList.remove(transferCustomerFinancial);
		customerFinancialService.deleteEntity(transferCustomerFinancial);
		removeData(1, customerFinancialList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 此方法绑定于工程收支明细管理画面的安排车辆按钮 
	 * 实现功能：列出车辆安排列表
	 * @return 画面不跳转
	 */
	public void arrangeVehicle(CustomerFinancial customerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面的_查看收支明细按钮】");
		customerFinancialManageDto.setEngineeringFinancialList(customerFinancial.getEngineeringFinancialList());
	}
	
	/**
	 * 此方法绑定与客户收支明细管理画面的确认收款完结按钮
	 * @param transferCustomerFinancial
	 */
	public void queryFinish(CustomerFinancial transferCustomerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面的_确认完结收款按钮】");
		transferCustomerFinancial.setState(CustomerFinancial.STATE_3);
		customerFinancialService.updateEntity(transferCustomerFinancial);
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}
	
	/**
	 * 获取到总完成方量
	 * @return
	 */
	public BigDecimal getSumMoney() {
		DetachedCriteria detachedCriteria = getCriteria();
		detachedCriteria.setProjection(Property.forName(CustomerFinancial.MONEY));
		List<Object[]> objectList = customerFinancialService.getByDetachedCriteriaForObject(detachedCriteria);
		BigDecimal money = Constants.BIGDECIMAL_ZERO;
		if (!objectList.isEmpty()) {
			Object[] objects = objectList.toArray();
			money = BigDecimalUtil.toBigDecimal(objects[0]);
		}
		return money;
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = getCriteria();
		customerFinancialList = customerFinancialService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(customerFinancialService.getRowCount(detachedCriteria));
		}
		if (!customerFinancialList.isEmpty()) {
			// 查询对应的收款项目
			detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
			detachedCriteria.createAlias(EngineeringFinancial.ENGINEERING_PROJECT, EngineeringFinancial.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(EngineeringFinancial.CUSTOMER_FINANCIAL, EngineeringFinancial.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(EngineeringFinancial.CUSTOMER_FINANCIAL, customerFinancialList));
			List<EngineeringFinancial> engineeringFinancialList = engineeringFinancialService.getByDetachedCriteria(detachedCriteria);
			
			// 收款明细
			detachedCriteria = DetachedCriteria.forClass(FinancialCollectDetail.class);
			detachedCriteria.createAlias(FinancialCollectDetail.CUSTOMER_FINANCIAL, FinancialCollectDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FinancialCollectDetail.CUSTOMER_FINANCIAL, customerFinancialList));
			detachedCriteria.add(Restrictions.eq(FinancialCollectDetail.STATE, true));
			List<FinancialCollectDetail> financialCollectDetailList = financialCollectDetailService.getByDetachedCriteria(detachedCriteria);
			
			// 付款明细
			detachedCriteria = DetachedCriteria.forClass(FinancialPayDetail.class);
			detachedCriteria.createAlias(FinancialPayDetail.CUSTOMER_FINANCIAL, FinancialPayDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FinancialPayDetail.CUSTOMER_FINANCIAL, customerFinancialList));
			detachedCriteria.add(Restrictions.eq(FinancialPayDetail.STATE, true));
			List<FinancialPayDetail> financialPayDetailList = financialPayDetailService.getByDetachedCriteria(detachedCriteria);
			// 统计出收支明细
			for (CustomerFinancial customerFinancial : customerFinancialList) {
				List<EngineeringFinancial> financialList = new ArrayList<EngineeringFinancial>();
				// 收款金额
				BigDecimal collectMoney = Constants.BIGDECIMAL_ZERO;
				// 消费金额
				BigDecimal payMoney = Constants.BIGDECIMAL_ZERO;
				// 方量
				BigDecimal sumSchedule = Constants.BIGDECIMAL_ZERO;
				for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
					if (customerFinancial.equals(engineeringFinancial.getCustomerFinancial())) {
						sumSchedule = BigDecimalUtil.add(sumSchedule, engineeringFinancial.getEngineeringProject().getSchedule());
						financialList.add(engineeringFinancial);
					}
				}
				customerFinancial.setSumSchedule(sumSchedule);
				customerFinancial.setEngineeringFinancialList(financialList);
				// 统计出收款金额
				for (FinancialCollectDetail financialCollectDetail : financialCollectDetailList) {
					if (customerFinancial.equals(financialCollectDetail.getCustomerFinancial())) {
						collectMoney = BigDecimalUtil.add(collectMoney, financialCollectDetail.getMoney());
					}
				}
				customerFinancial.setCollectMoney(collectMoney);
				// 统计出支付明细
				for (FinancialPayDetail financialPayDetail : financialPayDetailList) {
					if (customerFinancial.equals(financialPayDetail.getCustomerFinancial())) {
						payMoney = BigDecimalUtil.add(payMoney, financialPayDetail.getMoney());
					}
				}
				customerFinancial.setPayMoney(payMoney);
			}
		}
	}

	/**
	 * 共通搜索条件
	 * @return
	 */
	private DetachedCriteria getCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerFinancial.class);
		detachedCriteria.createAlias(CustomerFinancial.CUSTOMER, CustomerFinancial.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(CustomerFinancial.FINANCIAL_DATE));
		String number = customerFinancialManageDto.getNumber();
		String customerName = customerFinancialManageDto.getCustomerName();
		Date beginFinancialDate = customerFinancialManageDto.getBeginFinancialDate();
		Date endFinancialDate = customerFinancialManageDto.getEndFinancialDate();
		String state = customerFinancialManageDto.getState();
		if (StringUtil.isNotBlank(number)) {
			detachedCriteria.add(Restrictions.like(CustomerFinancial.NUMBER, number, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(customerName)) {
			detachedCriteria.add(Restrictions.like(CustomerFinancial.CUSTOMER_NAME, customerName, MatchMode.ANYWHERE));
		}
		if (beginFinancialDate != null) {
			detachedCriteria.add(Restrictions.ge(CustomerFinancial.FINANCIAL_DATE, beginFinancialDate));
		}
		if (endFinancialDate != null) {
			detachedCriteria.add(Restrictions.le(CustomerFinancial.FINANCIAL_DATE, endFinancialDate));
		}
		if (!StringUtil.isUnSelected(state)) {
			detachedCriteria.add(Restrictions.eq(CustomerFinancial.TYPE, state));
		}
		return detachedCriteria;
	}
	
	/**
	 * 此方法绑定与客户收支明细管理画面的登记收款明细按钮
	 * @param transferCustomerFinancial
	 */
	public void addCollectDetail(CustomerFinancial transferCustomerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面的_登记收款明细按钮】");
		customerFinancialManageDto.setCustomerFinancial(transferCustomerFinancial);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialCollectDetail.class);
		detachedCriteria.createAlias(FinancialCollectDetail.CUSTOMER_FINANCIAL, FinancialCollectDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FinancialCollectDetail.EMPLOYEE, FinancialCollectDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FinancialCollectDetail.CUSTOMER_FINANCIAL, transferCustomerFinancial));
		customerFinancialManageDto.setFinancialCollectDetailList(financialCollectDetailService.getByDetachedCriteria(detachedCriteria));
	}
	
	/**
	 * 此方法绑定与客户收支明细管理画面的登记付款明细按钮
	 * @param transferCustomerFinancial
	 */
	public void addPayDetail(CustomerFinancial transferCustomerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细管理画面的_登记付款明细按钮】");
		customerFinancialManageDto.setCustomerFinancial(transferCustomerFinancial);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialPayDetail.class);
		detachedCriteria.createAlias(FinancialPayDetail.CUSTOMER_FINANCIAL, FinancialPayDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(FinancialPayDetail.EMPLOYEE, FinancialPayDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(FinancialPayDetail.CUSTOMER_FINANCIAL, transferCustomerFinancial));
		customerFinancialManageDto.setFinancialPayDetailList(financialPayDetailService.getByDetachedCriteria(detachedCriteria));
	}
	
	/**
	 * 增加一行收款明细
	 */
	public void addEngineeringFinancial() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_增加收款明细按钮】");
		List<FinancialCollectDetail> financialCollectDetailList = customerFinancialManageDto.getFinancialCollectDetailList();
		if (financialCollectDetailList == null) {
			financialCollectDetailList = new ArrayList<FinancialCollectDetail>();
			customerFinancialManageDto.setFinancialCollectDetailList(financialCollectDetailList);
		}
		FinancialCollectDetail financialCollectDetail = new FinancialCollectDetail();
		financialCollectDetail.setCustomerFinancial(customerFinancialManageDto.getCustomerFinancial());
		financialCollectDetail.setCreater(userBean.getUser());
		financialCollectDetail.setCollectDate(DateUtil.getNowyyyymmdd());
		financialCollectDetail.setMoney(BigDecimalUtil.subtract(customerFinancialManageDto.getCustomerFinancial().getMoney(), customerFinancialManageDto.getCustomerFinancial().getCollectMoney()));
		financialCollectDetailList.add(financialCollectDetail);
	}
	
	/**
	 * 保存收款明细
	 * @param financialCollectDetail
	 */
	public void saveFinancialCollectDetail(FinancialCollectDetail financialCollectDetail) {
		financialCollectDetail.setState(true);
		financialCollectDetailService.saveOrUpdate(financialCollectDetail);
		CustomerFinancial customerFinancial = customerFinancialManageDto.getCustomerFinancial();
		customerFinancial.setCollectMoney(BigDecimalUtil.add(financialCollectDetail.getMoney(), customerFinancial.getCollectMoney()));
		Tool.sendErrorMessage("收款成功！");
	}
	
	/**
	 * 删除收款明细
	 * @param financialCollectDetail
	 */
	public void deleteFinancialCollectDetail(FinancialCollectDetail financialCollectDetail) {
		if (financialCollectDetail.getId() != null) {
			financialCollectDetailService.deleteEntity(financialCollectDetail);
		}
		customerFinancialManageDto.getFinancialCollectDetailList().remove(financialCollectDetail);
		CustomerFinancial customerFinancial = customerFinancialManageDto.getCustomerFinancial();
		customerFinancial.setCollectMoney(BigDecimalUtil.subtract(customerFinancial.getCollectMoney(), financialCollectDetail.getMoney()));
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 增加一行付款明细
	 */
	public void addFinancialPayDetail() {
		Tool.sendLog(LOG, userBean, "【工程项目登陆画面_安排工作车辆按钮】");
		List<FinancialPayDetail> financialPayDetailList = customerFinancialManageDto.getFinancialPayDetailList();
		if (financialPayDetailList == null) {
			financialPayDetailList = new ArrayList<FinancialPayDetail>();
			customerFinancialManageDto.setFinancialPayDetailList(financialPayDetailList);
		}
		FinancialPayDetail financialPayDetail = new FinancialPayDetail();
		financialPayDetail.setCustomerFinancial(customerFinancialManageDto.getCustomerFinancial());
		financialPayDetail.setCreater(userBean.getUser());
		financialPayDetail.setPayDate(DateUtil.getNowyyyymmdd());
		financialPayDetailList.add(financialPayDetail);
	}
	
	/**
	 * 保存付款明细
	 * @param financialCollectDetail
	 */
	public void saveFinancialPayDetail(FinancialPayDetail financialPayDetail) {
		financialPayDetail.setState(true);
		financialPayDetailService.saveOrUpdate(financialPayDetail);
		CustomerFinancial customerFinancial = customerFinancialManageDto.getCustomerFinancial();
		customerFinancial.setPayMoney(BigDecimalUtil.add(financialPayDetail.getMoney(), customerFinancial.getPayMoney()));
		Tool.sendErrorMessage("付款成功！");
	}
	
	/**
	 * 删除付款明细
	 * @param financialCollectDetail
	 */
	public void deleteFinancialPayDetail(FinancialPayDetail financialPayDetail) {
		if (financialPayDetail.getId() != null) {
			financialPayDetailService.deleteEntity(financialPayDetail);
		}
		customerFinancialManageDto.getFinancialPayDetailList().remove(financialPayDetail);
		CustomerFinancial customerFinancial = customerFinancialManageDto.getCustomerFinancial();
		customerFinancial.setPayMoney(BigDecimalUtil.subtract(customerFinancial.getPayMoney(), financialPayDetail.getMoney()));
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人客户收支明细管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.CUSTOMER_FINANCIAL_MANAGE;
	}
	
	/**
	 * @param financialCollectDetailService the financialCollectDetailService to set
	 */
	public void setFinancialCollectDetailService(
			FinancialCollectDetailService financialCollectDetailService) {
		this.financialCollectDetailService = financialCollectDetailService;
	}

	/**
	 * @param financialPayDetailService the financialPayDetailService to set
	 */
	public void setFinancialPayDetailService(
			FinancialPayDetailService financialPayDetailService) {
		this.financialPayDetailService = financialPayDetailService;
	}

	/**
	 * set customerFinancialService
	 * @param customerFinancialService the customerFinancialService to set
	 */
	public void setCustomerFinancialService(CustomerFinancialService customerFinancialService) {
		this.customerFinancialService = customerFinancialService;
	}

	/**
	 * @param engineeringFinancialService the engineeringFinancialService to set
	 */
	public void setEngineeringFinancialService(
			EngineeringFinancialService engineeringFinancialService) {
		this.engineeringFinancialService = engineeringFinancialService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get customerFinancialManageDto
	 * @return the customerFinancialManageDto
	 */
	public CustomerFinancialManageDto getCustomerFinancialManageDto() {
		return customerFinancialManageDto;
	}

	/**
	 * set customerFinancialManageDto
	 * @param customerFinancialManageDto the customerFinancialManageDto to set
	 */
	public void setCustomerFinancialManageDto(CustomerFinancialManageDto customerFinancialManageDto) {
		this.customerFinancialManageDto = customerFinancialManageDto;
	}

	/**
	 * get customerFinancialList
	 * @return the customerFinancialList
	 */
	public List<CustomerFinancial> getCustomerFinancialList() {
		return customerFinancialList;
	}

	/**
	 * set customerFinancialList
	 * @param customerFinancialList the customerFinancialList to set
	 */
	public void setCustomerFinancialList(List<CustomerFinancial> customerFinancialList) {
		this.customerFinancialList = customerFinancialList;
	}

	/**
	 * @return the financialCollectDetailList
	 */
	public List<FinancialCollectDetail> getFinancialCollectDetailList() {
		return financialCollectDetailList;
	}

	/**
	 * @param financialCollectDetailList the financialCollectDetailList to set
	 */
	public void setFinancialCollectDetailList(
			List<FinancialCollectDetail> financialCollectDetailList) {
		this.financialCollectDetailList = financialCollectDetailList;
	}

	/**
	 * @return the financialPayDetailList
	 */
	public List<FinancialPayDetail> getFinancialPayDetailList() {
		return financialPayDetailList;
	}

	/**
	 * @param financialPayDetailList the financialPayDetailList to set
	 */
	public void setFinancialPayDetailList(
			List<FinancialPayDetail> financialPayDetailList) {
		this.financialPayDetailList = financialPayDetailList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public CustomerFinancial[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(CustomerFinancial[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
