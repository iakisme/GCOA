package com.qylm.bean.customer;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.AddressFinderBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.customer.CustomerCreateDto;
import com.qylm.dxo.CustomerCreateDxo;
import com.qylm.entity.AddressEntity;
import com.qylm.entity.Customer;
import com.qylm.service.CustomerService;

/**
 * 客户登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class CustomerCreateBean implements CreateBean<Customer>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(CustomerCreateBean.class);

	/**
	 * 存放客户登陆画面需要保存的值
	 */
	private CustomerCreateDto customerCreateDto = new CustomerCreateDto();
	
	/**
	 * 客户bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 地点查询bean
	 */
	@ManagedProperty(value = "#{addressFinderBean}")
	private AddressFinderBean addressFinderBean;
	
	/**
	 * 客户业务类
	 */
	@ManagedProperty(value="#{customerService}")
	private CustomerService customerService;
	
	/**
	 * 此方法绑定于客户登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个客户
	 * @return 客户登陆画面
	 */
	public String newCustomer() {
		Tool.sendLog(LOG, userBean, "【客户登陆画面_新建按钮】");
		customerCreateDto.setSerialNumber(null);
		customerCreateDto.setManager(null);
		customerCreateDto.setName(null);
		customerCreateDto.setAddressEntity(new AddressEntity());
		addressFinderBean.clearAll();
		customerCreateDto.setAddress(null);
		customerCreateDto.setBank(null);
		customerCreateDto.setBankNumber(null);
		customerCreateDto.setTrade(null);
		customerCreateDto.setTaxNo(null);
		customerCreateDto.setUrl(null);
		customerCreateDto.setEmail(null);
		customerCreateDto.setFax(null);
		customerCreateDto.setPhone(null);
		customerCreateDto.setMobile(null);
		customerCreateDto.setTaxState(false);
		customerCreateDto.setTax(null);
		customerCreateDto.setPayment(null);
		customerCreateDto.setType(null);
		customerCreateDto.setRegisterDate(null);
		customerCreateDto.setPumpPrice(null);
		customerCreateDto.setCreater(null);
		customerCreateDto.setBelongingUser(null);
		customerCreateDto.setTransferCustomer(null);
		return Navigation.CUSTOMER_CREATE;
	}
	
	/**
	 * 此方法绑定于客户登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【客户登陆画面_返回按钮】");
		return customerCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于客户登陆画面的保存按钮 
	 * 实现功能：根据transferCustomer对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveCustomer() {
		Tool.sendLog(LOG, userBean, "【客户登陆画面_保存按钮】");
		try {
			Customer transferCustomer = customerCreateDto.getTransferCustomer();
			if (transferCustomer == null) {
				transferCustomer = new Customer();
				customerCreateDto.setCreater(userBean.getUser());
				customerCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferCustomer);
				transferCustomer.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				customerService.saveEntity(transferCustomer);
				customerCreateDto.setTransferCustomer(transferCustomer);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferCustomer);
				transferCustomer.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				customerService.updateEntity(transferCustomer);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("客户：" + customerCreateDto.getName() + "，已存在，请确认！");
		}
		
	}
	
	/**
	 * 赋值
	 * @param transferCustomer
	 */
	private void create(Customer transferCustomer) {
		CustomerCreateDxo.dtoToEntity(customerCreateDto, transferCustomer);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		customerCreateDto.setReturner(returner);
		customerCreateDto.setAddressEntity(new AddressEntity());
		addressFinderBean.clearAll();
		return Navigation.CUSTOMER_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, Customer customer) {
		customerCreateDto.setReturner(returner);
		CustomerCreateDxo.entityToDto(customer, customerCreateDto);
		customerCreateDto.setTransferCustomer(customer);
		if(customer.getAddressEntity()==null) {
			customerCreateDto.setAddressEntity(new AddressEntity());
			addressFinderBean.clearAll();
		}else{
			addressFinderBean.initAllByAddressEntity(customer.getAddressEntity());
		}
		return Navigation.CUSTOMER_CREATE;
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
	 * @param addressFinderBean the addressFinderBean to set
	 */
	public void setAddressFinderBean(AddressFinderBean addressFinderBean) {
		this.addressFinderBean = addressFinderBean;
	}

	/**
	 * get customerCreateDto
	 * @return the customerCreateDto
	 */
	public CustomerCreateDto getCustomerCreateDto() {
		return customerCreateDto;
	}

	/**
	 * set customerCreateDto
	 * @param customerCreateDto the customerCreateDto to set
	 */
	public void setCustomerCreateDto(CustomerCreateDto customerCreateDto) {
		this.customerCreateDto = customerCreateDto;
	}

}
