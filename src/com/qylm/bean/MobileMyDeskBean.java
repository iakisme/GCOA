package com.qylm.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qylm.bean.mobile.financial.MobileEngineeringFinancialManageBean;
import com.qylm.bean.mobile.report.MobileEngineeringStatisticsManageBean;
import com.qylm.bean.mobile.vehicle.MobileVehicleInfoManageBean;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.entity.EngineeringProject;

/**
 * 手机访问桌面
 * @author 
 */
@ManagedBean
@RequestScoped
public class MobileMyDeskBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6573276092728239283L;

	/**
	 * LOG 日志
	 */
	private static final Log LOG = LogFactory.getLog(MobileMyDeskBean.class);
	
	/**
	 * 工作登记列表（检索结果）
	 */
	private List<EngineeringProject> engineeringProjectList;
	
	/**
	 * 登陆用户信息
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 绑定与我的桌面按钮
	 * @return
	 */
	public String selectEngineering(String type) {
		if ("1".equals(type)) {
			Tool.sendLog(LOG, userBean, "按下【功能菜单_手机访问工作方量情况按钮】");
			return Tool.getBackBean(MobileEngineeringStatisticsManageBean.class).getAll();
		} else if ("2".equals(type)) {
			Tool.sendLog(LOG, userBean, "按下【功能菜单_手机访问收款情况按钮】");
			return Tool.getBackBean(MobileEngineeringFinancialManageBean.class).getAll();
		} else {
			Tool.sendLog(LOG, userBean, "按下【功能菜单_车辆工作情况按钮】");
			return Tool.getBackBean(MobileVehicleInfoManageBean.class).getAll();
		}
	}
	
	/**
	 * 刷新桌面
	 * @return
	 */
	public String refurbish() {
		return Navigation.MY_DESK;
	}

	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
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
