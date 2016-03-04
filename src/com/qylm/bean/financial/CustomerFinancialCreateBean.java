package com.qylm.bean.financial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.financial.CustomerFinancialCreateDto;
import com.qylm.dxo.CustomerFinancialCreateDxo;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.EngineeringProject;
import com.qylm.service.CustomerFinancialService;
import com.qylm.service.EngineeringFinancialService;
import com.qylm.service.EngineeringProjectService;

/**
 * 客户收支明细登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class CustomerFinancialCreateBean implements CreateBean<CustomerFinancial>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3812600612874768829L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(CustomerFinancialCreateBean.class);

	/**
	 * 存放客户收支明细登陆画面需要保存的值
	 */
	private CustomerFinancialCreateDto customerFinancialCreateDto = new CustomerFinancialCreateDto();
	
	/**
	 * 客户收支明细bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 工程项目
	 */
	private List<EngineeringProject> engineeringProjectList;
	
	/**
	 * 客户收支明细业务类
	 */
	@ManagedProperty(value="#{customerFinancialService}")
	private CustomerFinancialService customerFinancialService;
	
	/**
	 * 工程项目业务类
	 */
	@ManagedProperty(value="#{engineeringProjectService}")
	private EngineeringProjectService engineeringProjectService;
	
	/**
	 * 收款项目业务类
	 */
	@ManagedProperty(value="#{engineeringFinancialService}")
	private EngineeringFinancialService engineeringFinancialService;
	
	/**
	 * 此方法绑定于客户收支明细登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个客户收支明细
	 * @return 客户收支明细登陆画面
	 */
	public String newCustomerFinancial() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_新建按钮】");
		customerFinancialCreateDto.setNumber(MothedUtil.financialNumber(customerFinancialService, null));
		customerFinancialCreateDto.setCustomer(null);
		customerFinancialCreateDto.setMoney(null);
		customerFinancialCreateDto.setType(null);
		customerFinancialCreateDto.setPumpPrice(null);
		customerFinancialCreateDto.setState(null);
		customerFinancialCreateDto.setFinancialDate(DateUtil.getNowyyyymmdd());
		customerFinancialCreateDto.setCreater(null);
		customerFinancialCreateDto.setBelongingUser(null);
		customerFinancialCreateDto.setTransferCustomerFinancial(null);
		return Navigation.CUSTOMER_FINANCIAL_CREATE;
	}
	
	/**
	 * 此方法绑定于客户收支明细登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_返回按钮】");
		return customerFinancialCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于客户收支明细登陆画面的保存按钮 
	 * 实现功能：根据transferCustomerFinancial对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveCustomerFinancial() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_保存按钮】");
		saveOrUpdate();
	}

	/**
	 * 确认收款，确认后不可更改收款项目
	 */
	public void queryCustomerFinancial() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_确认收款按钮】");
		customerFinancialCreateDto.setState(CustomerFinancial.STATE_2);
		customerFinancialCreateDto.setType(customerFinancialCreateDto.getCustomer().getType());
		customerFinancialCreateDto.setPumpPrice(customerFinancialCreateDto.getCustomer().getPumpPrice());
		saveOrUpdate();
	}
	
	/**
	 * 保存或者更新操作
	 */
	private void saveOrUpdate() {
		try {
			CustomerFinancial transferCustomerFinancial = customerFinancialCreateDto.getTransferCustomerFinancial();
			if (transferCustomerFinancial == null) {
				transferCustomerFinancial = new CustomerFinancial();
				customerFinancialCreateDto.setCreater(userBean.getUser());
				customerFinancialCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferCustomerFinancial);
				transferCustomerFinancial.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				customerFinancialService.saveEntity(transferCustomerFinancial);
				customerFinancialCreateDto.setTransferCustomerFinancial(transferCustomerFinancial);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferCustomerFinancial);
				transferCustomerFinancial.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				customerFinancialService.updateCustomerFinancial(transferCustomerFinancial, customerFinancialCreateDto.getEngineeringFinancialList());
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("财务编号已存在！");
		}
	}
	
	/**
	 * 赋值
	 * @param transferCustomerFinancial
	 */
	private void create(CustomerFinancial transferCustomerFinancial) {
		List<EngineeringFinancial> engineeringFinancialList = customerFinancialCreateDto.getEngineeringFinancialList();
		if (engineeringFinancialList != null && !engineeringFinancialList.isEmpty()) {
			for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
				engineeringFinancial.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			}
		}
		CustomerFinancialCreateDxo.dtoToEntity(customerFinancialCreateDto, transferCustomerFinancial);
	}
	
	public String newCreate(Returner<?, ?, ?> returner) {
		customerFinancialCreateDto.setReturner(returner);
		customerFinancialCreateDto.setFinancialDate(DateUtil.getNowyyyymmdd());
		customerFinancialCreateDto.setNumber(MothedUtil.financialNumber(customerFinancialService, null));
		return Navigation.CUSTOMER_FINANCIAL_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, CustomerFinancial customerFinancial) {
		customerFinancialCreateDto.setReturner(returner);
		CustomerFinancialCreateDxo.entityToDto(customerFinancial, customerFinancialCreateDto);
		customerFinancialCreateDto.setTransferCustomerFinancial(customerFinancial);
		
		// 搜索出收款客户对应的收款项目
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
		detachedCriteria.createAlias(EngineeringFinancial.ENGINEERING_PROJECT, EngineeringFinancial.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringFinancial.CUSTOMER_FINANCIAL, EngineeringFinancial.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringFinancial.CUSTOMER_FINANCIAL, customerFinancial));
		customerFinancialCreateDto.setEngineeringFinancialList(engineeringFinancialService.getByDetachedCriteria(detachedCriteria));
		getSumSchedule(customerFinancialCreateDto.getEngineeringFinancialList());
		customerFinancialCreateDto.setMoney(customerFinancial.getMoney());
		return Navigation.CUSTOMER_FINANCIAL_CREATE;
	}
	
	/**
	 * 查询出收款项目
	 */
	public void selectEngineeringProject() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_查询出收款项目按钮】");
		DetachedCriteria detachedCriteria = getDetached();
		List<EngineeringFinancial> engineeringFinancialList = customerFinancialCreateDto.getEngineeringFinancialList();
		// 已存在的就不显示
		if (engineeringFinancialList != null && !engineeringFinancialList.isEmpty()) {
			List<Integer> projectList = new ArrayList<Integer>();
			for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
				projectList.add(engineeringFinancial.getEngineeringProject().getId());
			}
			detachedCriteria.add(Restrictions.not(Restrictions.in(EngineeringProject.BASE_ID, projectList)));
		}
		engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}

	/**
	 * 共通搜索条件
	 * @return
	 */
	private DetachedCriteria getDetached() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProject.class);
		detachedCriteria.createAlias(EngineeringProject.CUSTOMER, EngineeringProject.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProject.CUSTOMER, customerFinancialCreateDto.getCustomer()));
		String workAddress = customerFinancialCreateDto.getWorkAddress();
		Date beginDate = customerFinancialCreateDto.getBeginDate();
		Date endDate = customerFinancialCreateDto.getEndDate();
		if (StringUtil.isNotBlank(workAddress)) {
			detachedCriteria.add(Restrictions.like(EngineeringProject.WORK_ADDRESS, workAddress, MatchMode.ANYWHERE));
		}
		if (beginDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProject.BEGIN_DATE, beginDate));
		}
		if (endDate != null) {
			detachedCriteria.add(Restrictions.ge(EngineeringProject.BEGIN_DATE, endDate));
		}
		detachedCriteria.addOrder(Order.desc(EngineeringProject.BEGIN_DATE));
		return detachedCriteria;
	}
	
	/**
	 * 选择项目
	 * @param engineeringProject
	 */
	public void findProject(EngineeringProject engineeringProject) {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_选择收款项目按钮】");
		List<EngineeringProject> projectList = customerFinancialCreateDto.getEngineeringProjectList();
		if (projectList == null) {
			projectList = new ArrayList<EngineeringProject>();
			customerFinancialCreateDto.setEngineeringProjectList(projectList);
		}
		projectList.add(engineeringProject);
		engineeringProjectList.remove(engineeringProject);
		// 如果是最后一条，就重新获取下十条
		if (engineeringProjectList.isEmpty()) {
			DetachedCriteria detachedCriteria = getDetached();
			if (!projectList.isEmpty()) {
				List<Integer> idList = new ArrayList<Integer>();
				for (EngineeringProject project : projectList) {
					idList.add(project.getId());
				}
				detachedCriteria.add(Restrictions.not(Restrictions.in(EngineeringProject.BASE_ID, idList)));
			}
			engineeringProjectList = engineeringProjectService.getByDetachedCriteria(detachedCriteria, 0, 10);
		}
	}
	
	/**
	 * 选取项目后并关闭
	 * 实现功能：解析出选中的项目
	 */
	public void closeProject() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_选取项目后并关闭按钮】");
		List<EngineeringProject> projectList = customerFinancialCreateDto.getEngineeringProjectList();
		List<EngineeringFinancial> engineeringFinancialList = customerFinancialCreateDto.getEngineeringFinancialList();
		if (projectList != null && !projectList.isEmpty()) {
			if (engineeringFinancialList == null) {
				engineeringFinancialList = new ArrayList<EngineeringFinancial>();
				customerFinancialCreateDto.setEngineeringFinancialList(engineeringFinancialList);
			}
			for (EngineeringProject engineeringProject : projectList) {
				EngineeringFinancial engineeringFinancial = new EngineeringFinancial();
				engineeringFinancial.setCustomerFinancial(customerFinancialCreateDto.getTransferCustomerFinancial());
				engineeringFinancial.setEngineeringProject(engineeringProject);
				engineeringFinancialList.add(engineeringFinancial);
			}
		}
		customerFinancialCreateDto.setEngineeringProjectList(null);
		getSumSchedule(engineeringFinancialList);
	}

	/**
	 * 计算出总方量
	 * @param engineeringFinancialList
	 */
	private void getSumSchedule(List<EngineeringFinancial> engineeringFinancialList) {
		// 计算出总方量
		if (engineeringFinancialList != null && !engineeringFinancialList.isEmpty()) {
			BigDecimal sumSchedule = Constants.BIGDECIMAL_ZERO;
			for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
				sumSchedule = BigDecimalUtil.add(sumSchedule, engineeringFinancial.getEngineeringProject().getSchedule());
			}
			customerFinancialCreateDto.setSumSchedule(sumSchedule);
			// 计算出项目金额
			customerFinancialCreateDto.setMoney(BigDecimalUtil.multiply(sumSchedule, customerFinancialCreateDto.getPumpPrice()));
		}
	}
	
	/**
	 * 此方法绑定于客户收支明细登录画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteEngineeringFinancial(EngineeringFinancial transEngineeringFinancial) {
		Tool.sendLog(LOG, userBean, "按下【客户收支明细登录画面的_删除按钮】");
		List<EngineeringFinancial> engineeringFinancialList = customerFinancialCreateDto.getEngineeringFinancialList();
		engineeringFinancialList.remove(transEngineeringFinancial);
		if (transEngineeringFinancial.getId() != null) {
			engineeringFinancialService.deleteEntity(transEngineeringFinancial);
		}
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * @param engineeringFinancialService the engineeringFinancialService to set
	 */
	public void setEngineeringFinancialService(
			EngineeringFinancialService engineeringFinancialService) {
		this.engineeringFinancialService = engineeringFinancialService;
	}

	/**
	 * @param engineeringProjectService the engineeringProjectService to set
	 */
	public void setEngineeringProjectService(
			EngineeringProjectService engineeringProjectService) {
		this.engineeringProjectService = engineeringProjectService;
	}

	/**
	 * set customerFinancialService
	 * @param customerFinancialService the customerFinancialService to set
	 */
	public void setCustomerFinancialService(CustomerFinancialService customerFinancialService) {
		this.customerFinancialService = customerFinancialService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get customerFinancialCreateDto
	 * @return the customerFinancialCreateDto
	 */
	public CustomerFinancialCreateDto getCustomerFinancialCreateDto() {
		return customerFinancialCreateDto;
	}

	/**
	 * set customerFinancialCreateDto
	 * @param customerFinancialCreateDto the customerFinancialCreateDto to set
	 */
	public void setCustomerFinancialCreateDto(CustomerFinancialCreateDto customerFinancialCreateDto) {
		this.customerFinancialCreateDto = customerFinancialCreateDto;
	}

	/**
	 * @return the engineeringProjectList
	 */
	public List<EngineeringProject> getEngineeringProjectList() {
		return engineeringProjectList;
	}

	/**
	 * @param engineeringProjectList the engineeringProjectList to set
	 */
	public void setEngineeringProjectList(
			List<EngineeringProject> engineeringProjectList) {
		this.engineeringProjectList = engineeringProjectList;
	}

}
