package com.qylm.bean.engineering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.dto.engineering.ProjectStateCreateDto;
import com.qylm.dxo.ProjectStateCreateDxo;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.ProjectEmployeeDetailService;

/**
 * 工作登记登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class ProjectStateCreateBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(ProjectStateCreateBean.class);

	/**
	 * 存放工作登记登陆画面需要保存的值
	 */
	private ProjectStateCreateDto projectStateCreateDto = new ProjectStateCreateDto();
	
	/**
	 * 工作登记bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 工作登记详细业务类
	 */
	@ManagedProperty(value="#{engineeringProjectDetailService}")
	private EngineeringProjectDetailService engineeringProjectDetailService;
	
	/**
	 * 工作登记详细下的工作人员业务类
	 */
	@ManagedProperty(value="#{projectEmployeeDetailService}")
	private ProjectEmployeeDetailService projectEmployeeDetailService;
	
	/**
	 * 此方法绑定于工作登记登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【工作登记登陆画面_返回按钮】");
		return projectStateCreateDto.getReturner().returnOnly();
	}

	public String updateDetail(Returner<?, ?, ?> returner, EngineeringProject engineeringProject) {
		projectStateCreateDto.setReturner(returner);
		ProjectStateCreateDxo.entityToDto(engineeringProject, projectStateCreateDto);
		projectStateCreateDto.setTransferEngineeringProject(engineeringProject);
		getVehicleDateil();
		return Navigation.PROJECT_STATE_CREATE;
	}
	
	/**
	 * 获取工作登记详细列表
	 */
	private void getVehicleDateil() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.ENGINEERING_PROJECT, projectStateCreateDto.getTransferEngineeringProject()));
		List<EngineeringProjectDetail> list = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
		if (!list.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(ProjectEmployeeDetail.class);
			detachedCriteria.createAlias(ProjectEmployeeDetail.EMPLOYEE, ProjectEmployeeDetail.EMPLOYEE, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(ProjectEmployeeDetail.ENGINEERING_PROJECT_DETAIL, list));
			List<ProjectEmployeeDetail> projectEmployeeDetailList = projectEmployeeDetailService.getByDetachedCriteria(detachedCriteria);
			if (!projectEmployeeDetailList.isEmpty()) {
				List<ProjectEmployeeDetail> detailList;
				for (EngineeringProjectDetail engineeringProjectDetail : list) {
					detailList = new ArrayList<ProjectEmployeeDetail>();
					for (ProjectEmployeeDetail projectEmployeeDetail : projectEmployeeDetailList) {
						if (projectEmployeeDetail.getEngineeringProjectDetail().equals(engineeringProjectDetail)) {
							detailList.add(projectEmployeeDetail);
						}
					}
					engineeringProjectDetail.setProjectEmployeeDetailList(detailList);
				}
			}
		}
		projectStateCreateDto.setEngineeringProjectDetailList(list);
	}

	/**
	 * @param projectEmployeeDetailService the projectEmployeeDetailService to set
	 */
	public void setProjectEmployeeDetailService(
			ProjectEmployeeDetailService projectEmployeeDetailService) {
		this.projectEmployeeDetailService = projectEmployeeDetailService;
	}

	/**
	 * @param engineeringProjectDetailService the engineeringProjectDetailService to set
	 */
	public void setEngineeringProjectDetailService(
			EngineeringProjectDetailService engineeringProjectDetailService) {
		this.engineeringProjectDetailService = engineeringProjectDetailService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * @return the projectStateCreateDto
	 */
	public ProjectStateCreateDto getProjectStateCreateDto() {
		return projectStateCreateDto;
	}

	/**
	 * @param projectStateCreateDto the projectStateCreateDto to set
	 */
	public void setProjectStateCreateDto(ProjectStateCreateDto projectStateCreateDto) {
		this.projectStateCreateDto = projectStateCreateDto;
	}

}
