package com.qylm.common;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.entity.BaseEntity;
import com.qylm.entity.Customer;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.User;
import com.qylm.entity.VehicleInfo;
import com.qylm.entity.VehiclePeccancy;
import com.qylm.entity.VehicleServicing;
import com.qylm.service.CustomerFinancialService;

/**
 * 共通方法
 * @author
 */
public class MothedUtil {
	
	/**
	 * 此方法用于构建dTree，在页面上必须有openCreatePage(id)的js
	 * @param obj 二维数组值序列{(本级)id, (上级)pid, (标题名称)name, url, title, target, (图标路径)icon, iconOpen, open}
	 * @return 字符串
	 */
	public static String getDtree(Object[][] obj) {
		return getDtree(obj, false, null, false);
	}
	
	/**
	 * 此方法用于构建带复选框的dTree
	 * @param obj 二维数组值序列{(本级)id, (上级)pid, (标题名称)name, url, title, target, (图标路径)icon, iconOpen, open}
	 * @return 字符串
	 */
	public static String getCheckboxesDtree(Object[][] obj) {
		return getDtree(obj, true, null, false);
	}
	
	/**
	 * 此方法用于构建带复选框的dTree，复选框普通选择
	 * @param obj 二维数组值序列{(本级)id, (上级)pid, (标题名称)name, url, title, target, (图标路径)icon, iconOpen, open}
	 * @return 字符串
	 */
	public static String getCommonCheckboxesDtree(Object[][] obj) {
		return getDtree(obj, true, null, true);
	}
	
	/**
	 * 此方法用于构建带复选框的dTree，并默认选中某些
	 * @param obj 二维数组值序列{(本级)id, (上级)pid, (标题名称)name, url, title, target, (图标路径)icon, iconOpen, open}
	 * @param idList 需要默认选中的报表ID集合
	 * @param commonChoice 复选框选择状态，true普通选择没有事件
	 * @return 字符串
	 */
	public static String getCheckboxesDtreeDefaultChoice(Object[][] obj, List<Object> idList, boolean commonChoice) {
		return getDtree(obj, true, idList, commonChoice);
	}
	
	/**
	 * 此方法用于构建dTree
	 * @param obj 二维数组值序列{(本级)id, (上级)pid, (标题名称)name, url, title, target, (图标路径)icon, iconOpen, open}
	 * @param chockBox 是否需要复选框 true 需要反之不需要
	 * @param idList 需要默认选中的报表ID
	 * @param commonChoice 复选框选择状态，true普通选择没有事件
	 * @return 字符串
	 */
	private static String getDtree(Object[][] obj, boolean chockBox, List<Object> idList, boolean commonChoice) {
		StringBuffer sb = new StringBuffer();
		sb.append("d=new dTree(\"d\");\n");
		if (chockBox) {
			sb.append("d.config.useCheckbox = true;");
		}
		if (commonChoice) {
			sb.append("d.config.useCheckboxState = true;");
		}
		if (obj != null) {
			int length = obj.length;
			Object[] object;
			sb.append("d.add(0, '-1', \"功能菜单列表\", \"javascript: openCreatePage('-1');\", \"\", \"\", \"\", \"\", \"\", \"\");");
			for (int i = 1; i < length; i++) {
				object = obj[i];
				sb.append("d.add(");
				Object obj2 = object[0];
				sb.append("'" + obj2 + "', '");
				if (StringUtil.isObjBlank(object[1])) {
					sb.append("', \"");
				} else {
					sb.append(object[1] + "', \"");
				}
				if (StringUtil.isObjBlank(object[2])) {
					sb.append("\", \"");
				} else {
					sb.append(object[2] + "\", \"");
				}
				if (!StringUtil.isObjBlank(object[3])) {
					sb.append(object[3]);
				}
				sb.append("\", \"" + object[4] + "\", \"" + object[5] + "\", \""
						+ object[6] + "\", \"" + object[7] + "\", \"" + object[8] + "\"");
				if (idList != null && !idList.isEmpty()) {
					int size = idList.size();
					Object id;
					boolean state = false;;
					for (int j = 0; j < size; j++) {
						id = idList.get(j);
						if (StringUtil.isEquals(obj2, id)) {
							sb.append(", true");
							state = true;
							break;
						}
					}
					if (!state) {
						sb.append(", \"\"");
					}
				} else {
					sb.append(", \"\"");
				}
				sb.append(");\n");
			}
			sb.append("document.write(d);");
		}
		return sb.toString();
	}
	
	/**
	 * 根据车辆编号，获取到车辆信息
	 * @param vehicleInfoList
	 * @param number
	 * @return
	 */
	public static VehicleInfo getVehicleInfo(List<VehicleInfo> vehicleInfoList, String number) {
		if (!vehicleInfoList.isEmpty()) {
			for (VehicleInfo vehicleInfo : vehicleInfoList) {
				if (number.equals(vehicleInfo.getNumber())) {
					return vehicleInfo;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据客户名称，获取到客户信息
	 * @param customerList
	 * @param name
	 * @return
	 */
	public static Customer getCustomer(List<Customer> customerList, String name) {
		if (!customerList.isEmpty()) {
			for (Customer customer : customerList) {
				if (name.equals(customer.getName())) {
					return customer;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据员工姓名，获取到员工信息
	 * @param employeeList
	 * @param name
	 * @return
	 */
	public static Employee getEmployee(List<Employee> employeeList, String name) {
		if (!employeeList.isEmpty()) {
			for (Employee employee : employeeList) {
				if (name.equals(employee.getName())) {
					return employee;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据车辆获取到车辆当前的工作状态
	 * @param vehicleInfo 车辆信息
	 * @param engineeringProjectDetailList 车辆工作信息
	 * @param vehicleServicingList 车辆维修信息
	 * @param vehiclePeccancyList 车辆违章事故处理信息
	 * @return
	 */
	public static String getVehicleInfoState(VehicleInfo vehicleInfo, 
			List<EngineeringProjectDetail> engineeringProjectDetailList,
			List<VehicleServicing> vehicleServicingList, List<VehiclePeccancy> vehiclePeccancyList) {
		StringBuilder sb = new StringBuilder();
		Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
		// 获取工作状态
		for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
			if (vehicleInfo.equals(engineeringProjectDetail.getVehicleInfo())) {
				Date startPumpDate = engineeringProjectDetail.getStartPumpDate();
				Date endPumpDate = engineeringProjectDetail.getEndPumpDate();
				sb.append("已安排工作，工作时间");
				sb.append(DateUtil.formatDate(startPumpDate, Constants.YYYY_MM_DD_HH_MM_SS));
				sb.append(Constants.HORIZONTAL_LINE);
				sb.append(DateUtil.formatDate(endPumpDate, Constants.YYYY_MM_DD_HH_MM_SS));
				sb.append("，还需要 ");
				sb.append(DateUtil.getSubtractHour(endPumpDate, nowyyyymmddhhmmss));
				sb.append(" 小时，此车才可以完成工作！");
				if (EngineeringProject.TYPE_5.equals(engineeringProjectDetail.getEngineeringProject().getType())) {
					sb.append("<span style=\"color:#FF0000;\">注意：该车所分配的工作处于工作暂停中</span>");
					
				} else if (EngineeringProject.TYPE_6.equals(engineeringProjectDetail.getEngineeringProject().getType())) {
					sb.append("<span style=\"color:#FF0000;\">注意：该车所分配的工作已完结</span>");
				}
				sb.append("<br />");
				break;
			}
		}
		// 获取维修状态
		for (VehicleServicing vehicleServicing : vehicleServicingList) {
			if (vehicleInfo.equals(vehicleServicing.getVehicleInfo())) {
				sb.append("当前车辆出现：“");
				sb.append(vehicleServicing.getBreakdown());
				sb.append("” 故障，正在维修中");
				sb.append("<br />");
				break;
			}
		}
		// 获取违章事故处理信息
		for (VehiclePeccancy vehiclePeccancy : vehiclePeccancyList) {
			if (vehicleInfo.equals(vehiclePeccancy.getVehicleInfo())) {
				sb.append("当前车辆出现：“");
				sb.append(vehiclePeccancy.getCause());
				sb.append("” 违章事故，正在处理中");
				sb.append("<br />");
				break;
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取到最新的财务编号
	 * @param customerFinancialService
	 * @return 最新财务编号
	 */
	public static String financialNumber(CustomerFinancialService customerFinancialService, String number) {
		if (StringUtil.isBlank(number)) {
			number = DateUtil.formatDate(DateUtil.getNowyyyymm(), "yyyyMM");
		} else {
			number = number.substring(0, 6);
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerFinancial.class);
		detachedCriteria.add(Restrictions.like(CustomerFinancial.NUMBER, number, MatchMode.START));
		detachedCriteria.addOrder(Order.desc(CustomerFinancial.CREATE_DATE));
		List<CustomerFinancial> customerFinancialList = customerFinancialService.getByDetachedCriteria(detachedCriteria);
		if (!customerFinancialList.isEmpty()) {
			CustomerFinancial customerFinancial = customerFinancialList.get(0);
			String substring = customerFinancial.getNumber().substring(7);
			if (StringUtil.isNumber(substring)) {
				return number = number + "_"+ (Integer.valueOf(substring) + 1);
			} 
		}
		return number + "_1";
	}
	
	/**
	 * 根据登录用户，获取到用户的归属公司
	 * @param user
	 * @return
	 */
	public static DetachedCriteria getBelongingUser(DetachedCriteria detachedCriteria, User user) {
		detachedCriteria.createAlias(BaseEntity.CREATER, BaseEntity.CREATER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(BaseEntity.BELONGING_USER, BaseEntity.BELONGING_USER, JoinType.LEFT_OUTER_JOIN);
		// 如果不是超级系统管理员，就取出归属公司下的数据列表信息
		if (!User.USER_LEVEL_1.equals(user.getUserlevel())) {
			detachedCriteria.add(Restrictions.eq(BaseEntity.BELONGING_USER, user.getBelongingUser()));
		}
		return detachedCriteria;
	}
	
	/**
	 * 是否可删除信息
	 * 是系统管理员，又是本人创建才可以删除
	 * true不可删除，反之可以
	 * @param user 登录用户
	 * @param Create 信息建立者
	 * @return
	 */
	public static boolean getDeleteInfo(User user, User create) {
		if (User.USER_LEVEL_3.equals(user.getUserlevel())) {
			return !user.getId().equals(create.getId());
		}
		return false;
	}
}
