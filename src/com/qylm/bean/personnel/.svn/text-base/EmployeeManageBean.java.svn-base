package com.qylm.bean.personnel;

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
import com.qylm.bean.returner.personnel.EmployeeManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.personnel.EmployeeManageDto;
import com.qylm.entity.Employee;
import com.qylm.service.EmployeeService;

/**
 * 员工信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class EmployeeManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9200349285201120937L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(EmployeeManageBean.class);
	
	/**
	 * 保存员工管理画面需要保存的值
	 */
	private EmployeeManageDto employeeManageDto = new EmployeeManageDto();

	/**
	 * 员工列表（检索结果）
	 */
	private List<Employee> employeeList;
	
	/**
	 * 删除复选框选择的值
	 */
	private Employee[] selectedModels;

	/**
	 * 员工管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 员工管理业务类
	 */
	@ManagedProperty(value="#{employeeService}")
	private EmployeeService employeeService;
	
	/**
	 * 查询出所有员工列表
	 * 
	 * @return 员工管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_员工管理按钮】");
		fetchData(0, true);
		return Navigation.EMPLOYEE_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至员工登陆画面，新建一家员工
	 * @return 员工登陆画面
	 */
	public String newEmployee() {
		Tool.sendLog(LOG, userBean, "按下【员工管理画面_新建按钮】");
		return Tool.getBackBean(EmployeeCreateBean.class).newCreate(new EmployeeManageReturner(employeeManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至员工登陆画面
	 * @return 员工登陆画面
	 */
	public String updateEmployee(Employee transferEmployee) {
		Tool.sendLog(LOG, userBean, "按下【员工管理画面_修改按钮】");
		return Tool.getBackBean(EmployeeCreateBean.class).updateDetail(new EmployeeManageReturner(employeeManageDto, currentPage), transferEmployee);
	}
	
	/**
	 * 此方法绑定于员工管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出员工
	 * @return 画面不跳转
	 */
	public void selectEmployee() {
		Tool.sendLog(LOG, userBean, "按下【员工管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于员工管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【员工管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<Employee> asList = Arrays.asList(selectedModels);
			employeeList.removeAll(asList);
			employeeService.deleteEntityAll(asList);
			removeData(1, employeeList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于员工管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteEmployee(Employee transferEmployee) {
		Tool.sendLog(LOG, userBean, "按下【员工管理画面的_删除按钮】");
		employeeList.remove(transferEmployee);
		employeeService.deleteEntity(transferEmployee);
		removeData(1, employeeList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		String workNumber = employeeManageDto.getWorkNumber();
		String name = employeeManageDto.getName();
		String workState = employeeManageDto.getWorkState();
		if (StringUtil.isNotBlank(workNumber)) {
			detachedCriteria.add(Restrictions.like(Employee.WORK_NUMBER, workNumber, MatchMode.ANYWHERE));
		}
		if (StringUtil.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like(Employee.NAME, name, MatchMode.ANYWHERE));
		}
		if (!StringUtil.isUnSelected(workState)) {
			detachedCriteria.add(Restrictions.eq(Employee.WORK_STATE, workState));
		}
		employeeList = employeeService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(employeeService.getRowCount(detachedCriteria));
		}
		
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人员工管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.EMPLOYEE_MANAGE;
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
	 * get employeeManageDto
	 * @return the employeeManageDto
	 */
	public EmployeeManageDto getEmployeeManageDto() {
		return employeeManageDto;
	}

	/**
	 * set employeeManageDto
	 * @param employeeManageDto the employeeManageDto to set
	 */
	public void setEmployeeManageDto(EmployeeManageDto employeeManageDto) {
		this.employeeManageDto = employeeManageDto;
	}

	/**
	 * get employeeList
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * set employeeList
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public Employee[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(Employee[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
