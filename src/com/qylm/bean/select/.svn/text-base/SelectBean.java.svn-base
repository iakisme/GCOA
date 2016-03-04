package com.qylm.bean.select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.UserBean;
import com.qylm.common.MothedUtil;
import com.qylm.entity.Customer;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.FittingInfo;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.entity.User;
import com.qylm.entity.VehicleInfo;
import com.qylm.service.CustomerService;
import com.qylm.service.EmployeeService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.FittingInfoService;
import com.qylm.service.ProjectEmployeeDetailService;
import com.qylm.service.UserService;
import com.qylm.service.VehicleInfoService;

/**
 * 用于所有输入框查询
 * @author 
 */
@ManagedBean
@RequestScoped
public class SelectBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1681709681228643903L;
	
	/**
	 * bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 员工业务类
	 */
	@ManagedProperty(value="#{employeeService}")
	private EmployeeService employeeService;
	
	/**
	 * 客户业务类
	 */
	@ManagedProperty(value="#{customerService}")
	private CustomerService customerService;
	
	/**
	 * 车辆信息业务类
	 */
	@ManagedProperty(value="#{vehicleInfoService}")
	private VehicleInfoService vehicleInfoService;
	
	/**
	 * 配件信息业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 用户业务类
	 */
	@ManagedProperty(value="#{userService}")
	private UserService userService;

	/**
	 * 项目详细业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 项目工作人员详细业务类
	 */
	@ManagedProperty(value="#{projectEmployeeDetailService}")
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 根据输入的查询条件，查询出所有员工信息
	 * @param query 将军姓名
	 * @return 对象集合
	 */
	public List<Employee> selectEmployee(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.add(Restrictions.like(Employee.NAME, query, MatchMode.ANYWHERE));
		return employeeService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}
	
	/**
	 * 根据输入的查询条件，查询出所有项目工程负责人员
	 * @param query 将军姓名
	 * @return 对象集合
	 */
	public List<Employee> selectResponsibleEmployee(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		detachedCriteria.add(Restrictions.eq(Employee.TYPE, Employee.TYPE_1));
		detachedCriteria.add(Restrictions.like(Employee.NAME, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return employeeService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}
	
	/**
	 * 根据输入的查询条件，查询出所有客户信息
	 * @param query 客户名称
	 * @return 对象集合
	 */
	public List<Customer> selectCustomer(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like(Customer.NAME, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return customerService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}
	
	/**
	 * 根据输入的查询条件，查询出所有车辆信息
	 * @param query 将军姓名
	 * @return 对象集合
	 */
	public List<VehicleInfo> selectVehicleInfo(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfo.class);
		detachedCriteria.add(Restrictions.like(VehicleInfo.NUMBER, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return vehicleInfoService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}
	
	/**
	 * 搜索出配件信息的车辆名称
	 * @param query
	 * @return
	 */
	public List<String> fittingCarNames(String query) {
		List<String> list = new ArrayList<String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		detachedCriteria.add(Restrictions.like(FittingInfo.CAR_NAME, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.setProjection(Projections.groupProperty(FittingInfo.CAR_NAME));
		List<Object[]> objectList = fittingInfoService.getByDetachedCriteriaForObject(detachedCriteria);
		if (!objectList.isEmpty()) {
			Object[] array = objectList.toArray();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i].toString());
			}
		}
		return list;
	}
	
	/**
	 * 搜索出配件信息的配件品牌
	 * @param query
	 * @return
	 */
	public List<String> fittingBrands(String query) {
		List<String> list = new ArrayList<String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_BRAND, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.setProjection(Projections.groupProperty(FittingInfo.FITTING_BRAND));
		List<Object[]> objectList = fittingInfoService.getByDetachedCriteriaForObject(detachedCriteria);
		if (!objectList.isEmpty()) {
			Object[] array = objectList.toArray();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i].toString());
			}
		}
		return list;
	}
	
	/**
	 * 搜索出配件信息的配件名称
	 * @param query
	 * @return
	 */
	public List<String> fittingNames(String query) {
		List<String> list = new ArrayList<String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		detachedCriteria.add(Restrictions.like(FittingInfo.FITTING_NAME, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.setProjection(Projections.groupProperty(FittingInfo.FITTING_NAME));
		List<Object[]> objectList = fittingInfoService.getByDetachedCriteriaForObject(detachedCriteria);
		if (!objectList.isEmpty()) {
			Object[] array = objectList.toArray();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i].toString());
			}
		}
		return list;
	}
	
	/**
	 * 搜索出配件信息的型号
	 * @param query
	 * @return
	 */
	public List<String> models(String query) {
		List<String> list = new ArrayList<String>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FittingInfo.class);
		detachedCriteria.add(Restrictions.like(FittingInfo.MODEL, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.setProjection(Projections.groupProperty(FittingInfo.MODEL));
		List<Object[]> objectList = fittingInfoService.getByDetachedCriteriaForObject(detachedCriteria);
		if (!objectList.isEmpty()) {
			Object[] array = objectList.toArray();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i].toString());
			}
		}
		return list;
	}
	
	/**
	 * 根据输入的查询条件，查询出所有车辆信息
	 * @param query 将军姓名
	 * @return 对象集合
	 */
	public List<String> selectVehicleNumber(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleInfo.class);
		detachedCriteria.add(Restrictions.like(VehicleInfo.NUMBER, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.setProjection(Projections.property(VehicleInfo.NUMBER));
		List<Object[]> objectList = vehicleInfoService.getByDetachedCriteriaForObject(detachedCriteria, 0, 10);
		List<String> list = new ArrayList<String>();
		if (!objectList.isEmpty()) {
			Object[] array = objectList.toArray();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i].toString());
			}
			
		}
		return list;
	}
	
	/**
	 * 根据输入的查询条件，查询出所有客户信息
	 * @param query 客户名称
	 * @return 对象集合
	 */
	public List<User> selectUser(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.like(User.USER_NAME, query, MatchMode.ANYWHERE));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		return userService.getByDetachedCriteria(detachedCriteria, 0, 10);
	}
	
	/**
	 * 根据输入的查询条件，查询出所有项目详细
	 * @param query 项目详细
	 * @return 对象集合
	 */
	public List<EngineeringProjectDetail> selectEngineeringProjectDetail(String query) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc("id"));
		List<EngineeringProjectDetail> list = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria,0,10);
//		if (!list.isEmpty()) {
//			detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
//			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, list));
//			List<ProjectEmployeeDetail> projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria,0,10);
//			if (!projectEmployeeDetailList.isEmpty()) {
//				List<ProjectEmployeeDetail> detailList;
//				for (EngineeringProjectDetail engineeringProjectDetail : list) {
//					detailList = new ArrayList<ProjectEmployeeDetail>();
//					for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
//						if (projectEmployeeDetail.getEngineeringProjectDetail().equals(engineeringProjectDetail)) {
//							detailList.add(projectEmployeeDetail);
//						}
//					}
//					engineeringProjectDetail.setProjectEmployeeDetailList(detailList);
//				}
//			}
//		}
		return list;
	}



	/**
	 * @param engineeringProjectDetailService the engineeringProjectDetailService to set
	 */
	public void setEngineeringProjectDetailService(
			EngineeringProjectDetailService engineeringProjectDetailService) {
		this.engineeringProjectDetailService = engineeringProjectDetailService;
	}

	/**
	 * @param projectEmployeeDetailService the projectEmployeeDetailService to set
	 */
	public void setProjectEmployeeDetailService(
			ProjectEmployeeDetailService projectEmployeeDetailService) {
		this.projectEmployeeDetailService = projectEmployeeDetailService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}

	/**
	 * @param vehicleInfoService the vehicleInfoService to set
	 */
	public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
		this.vehicleInfoService = vehicleInfoService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
