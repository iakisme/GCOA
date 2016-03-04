package com.qylm.bean.customer;

import java.util.Arrays;
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
import com.qylm.bean.returner.customer.CustomerManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.customer.CustomerManageDto;
import com.qylm.entity.Customer;
import com.qylm.service.CustomerService;

/**
 * 客户信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class CustomerManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9200349285201120937L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(CustomerManageBean.class);
	
	/**
	 * 保存客户管理画面需要保存的值
	 */
	private CustomerManageDto customerManageDto = new CustomerManageDto();

	/**
	 * 客户列表（检索结果）
	 */
	private List<Customer> customerList;
	
	/**
	 * 删除复选框选择的值
	 */
	private Customer[] selectedModels;

	/**
	 * 客户管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 客户管理业务类
	 */
	@ManagedProperty(value="#{customerService}")
	private CustomerService customerService;
	
	/**
	 * 查询出所有客户列表
	 * 
	 * @return 客户管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_客户管理按钮】");
		fetchData(0, true);
		return Navigation.CUSTOMER_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至客户登陆画面，新建一家客户
	 * @return 客户登陆画面
	 */
	public String newCustomer() {
		Tool.sendLog(LOG, userBean, "按下【客户管理画面_新建按钮】");
		return Tool.getBackBean(CustomerCreateBean.class).newCreate(new CustomerManageReturner(customerManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至客户登陆画面
	 * @return 客户登陆画面
	 */
	public String updateCustomer(Customer transferCustomer) {
		Tool.sendLog(LOG, userBean, "按下【客户管理画面_修改按钮】");
		return Tool.getBackBean(CustomerCreateBean.class).updateDetail(new CustomerManageReturner(customerManageDto, currentPage), transferCustomer);
	}
	
	/**
	 * 此方法绑定于客户管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出客户
	 * @return 画面不跳转
	 */
	public void selectCustomer() {
		Tool.sendLog(LOG, userBean, "按下【客户管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于客户管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【客户管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<Customer> asList = Arrays.asList(selectedModels);
			customerList.removeAll(asList);
			customerService.deleteEntityAll(asList);
			removeData(1, customerList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于客户管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteCustomer(Customer transferCustomer) {
		Tool.sendLog(LOG, userBean, "按下【客户管理画面的_删除按钮】");
		customerList.remove(transferCustomer);
		customerService.deleteEntity(transferCustomer);
		removeData(1, customerList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 供其他页面调用，根据客户id获取到客户名称
	 * @param id
	 * @return
	 */
	public String findCustomerName(Integer id) {
		return customerService.getById(id).getName();
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String serialNumber = customerManageDto.getSerialNumber();
		String name = customerManageDto.getName();
		if (StringUtil.isNotBlank(serialNumber)) {
			detachedCriteria.add(Restrictions.like(Customer.SERIAL_NUMBER, serialNumber, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like(Customer.NAME, name, MatchMode.ANYWHERE));
		}
		customerList = customerService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(customerService.getRowCount(detachedCriteria));
		}
		
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人客户管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.CUSTOMER_MANAGE;
	}

	/**
	 * set customerService
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get customerManageDto
	 * @return the customerManageDto
	 */
	public CustomerManageDto getCustomerManageDto() {
		return customerManageDto;
	}

	/**
	 * set customerManageDto
	 * @param customerManageDto the customerManageDto to set
	 */
	public void setCustomerManageDto(CustomerManageDto customerManageDto) {
		this.customerManageDto = customerManageDto;
	}

	/**
	 * get customerList
	 * @return the customerList
	 */
	public List<Customer> getCustomerList() {
		return customerList;
	}

	/**
	 * set customerList
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public Customer[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(Customer[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
