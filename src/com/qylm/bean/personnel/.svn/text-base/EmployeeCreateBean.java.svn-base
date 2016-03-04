package com.qylm.bean.personnel;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.AddressFinderBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.personnel.EmployeeCreateDto;
import com.qylm.dxo.EmployeeCreateDxo;
import com.qylm.entity.AddressEntity;
import com.qylm.entity.BaseEntity;
import com.qylm.entity.Employee;
import com.qylm.service.EmployeeService;

/**
 * 员工登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class EmployeeCreateBean implements CreateBean<Employee>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(EmployeeCreateBean.class);

	/**
	 * 存放员工登陆画面需要保存的值
	 */
	private EmployeeCreateDto employeeCreateDto = new EmployeeCreateDto();
	
	/**
	 * 员工bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 地点查询bean
	 */
	@ManagedProperty(value = "#{addressFinderBean}")
	private AddressFinderBean addressFinderBean;
	
	/**
	 * 员工业务类
	 */
	@ManagedProperty(value="#{employeeService}")
	private EmployeeService employeeService;
	
	/**
	 * 此方法绑定于员工登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个员工
	 * @return 员工登陆画面
	 */
	public String newEmployee() {
		Tool.sendLog(LOG, userBean, "【员工登陆画面_新建按钮】");
		employeeCreateDto.setWorkNumber(null);
		employeeCreateDto.setName(null);
		employeeCreateDto.setWorkState(null);
		employeeCreateDto.setSex(null);
		employeeCreateDto.setBirthDate(null);
		employeeCreateDto.setPoliticalStatus(null);
		employeeCreateDto.setNation(null);
		employeeCreateDto.setMarriage(null);
		employeeCreateDto.setIdentification(null);
		employeeCreateDto.setFormalSchooling(null);
		employeeCreateDto.setGraduationDate(null);
		employeeCreateDto.setGraduationSchool(null);
		employeeCreateDto.setProfession(null);
		employeeCreateDto.setAddress(null);
		employeeCreateDto.setHomeState(null);
		employeeCreateDto.setType(null);
		employeeCreateDto.setPhone(null);
		employeeCreateDto.setMobile(null);
		employeeCreateDto.setAddressEntity(new AddressEntity());
		addressFinderBean.clearAll();
		employeeCreateDto.setCreater(null);
		employeeCreateDto.setBelongingUser(null);
		employeeCreateDto.setTransferEmployee(null);
		return Navigation.EMPLOYEE_CREATE;
	}
	
	/**
	 * 此方法绑定于员工登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【员工登陆画面_返回按钮】");
		return employeeCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于员工登陆画面的保存按钮 
	 * 实现功能：根据transferEmployee对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveEmployee() {
		Tool.sendLog(LOG, userBean, "【员工登陆画面_保存按钮】");
		try {
			Employee transferEmployee = employeeCreateDto.getTransferEmployee();
			if (transferEmployee == null) {
				transferEmployee = new Employee();
				employeeCreateDto.setCreater(userBean.getUser());
				employeeCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferEmployee);
				transferEmployee.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				employeeService.saveEntity(transferEmployee);
				employeeCreateDto.setTransferEmployee(transferEmployee);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferEmployee);
				transferEmployee.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				employeeService.updateEntity(transferEmployee);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("员工：" + employeeCreateDto.getName() + "的身份证号" + employeeCreateDto.getIdentification() + "，已存在，请确认！");
		}
		
	}
	
	/**
	 * 赋值
	 * @param transferEmployee
	 */
	private void create(Employee transferEmployee) {
		employeeCreateDto.setBirthDate(StringUtil.cardValidate(employeeCreateDto.getIdentification()));
		System.out.println(DateUtil.formatDate(employeeCreateDto.getBirthDate(), Constants.YYYY_MM_DD));
		employeeCreateDto.setSex(StringUtil.cardSex(employeeCreateDto.getIdentification()));
		EmployeeCreateDxo.dtoToEntity(employeeCreateDto, transferEmployee);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		employeeCreateDto.setReturner(returner);
		employeeCreateDto.setAddressEntity(new AddressEntity());
		addressFinderBean.clearAll();
		return Navigation.EMPLOYEE_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, Employee employee) {
		employeeCreateDto.setReturner(returner);
		EmployeeCreateDxo.entityToDto(employee, employeeCreateDto);
		employeeCreateDto.setTransferEmployee(employee);
		if(employee.getAddressEntity()==null) {
			employeeCreateDto.setAddressEntity(new AddressEntity());
			addressFinderBean.clearAll();
		}else{
			addressFinderBean.initAllByAddressEntity(employee.getAddressEntity());
		}
		return Navigation.EMPLOYEE_CREATE;
	}
	
	/**
	 * 验证人员省份证
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validatorIdentification(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		detachedCriteria.add(Restrictions.eq(Employee.IDENTIFICATION, String.valueOf(value)));
		if (employeeCreateDto.getTransferEmployee() != null	&& employeeCreateDto.getTransferEmployee().getId() != null) {
			detachedCriteria.add(Restrictions.ne(BaseEntity.BASE_ID, employeeCreateDto.getTransferEmployee().getId()));
		}
		int count = employeeService.getRowCount(detachedCriteria);
		if (count != 0) {
			MessageFormat format = new MessageFormat("身份证{0}，已存在");
			String mfMsg = format.format(new Object[] { value });
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,	mfMsg, mfMsg);
			throw new ValidatorException(message);
		}
	}

	/**
	 * set employeeService
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
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
	 * get employeeCreateDto
	 * @return the employeeCreateDto
	 */
	public EmployeeCreateDto getEmployeeCreateDto() {
		return employeeCreateDto;
	}

	/**
	 * set employeeCreateDto
	 * @param employeeCreateDto the employeeCreateDto to set
	 */
	public void setEmployeeCreateDto(EmployeeCreateDto employeeCreateDto) {
		this.employeeCreateDto = employeeCreateDto;
	}

}
