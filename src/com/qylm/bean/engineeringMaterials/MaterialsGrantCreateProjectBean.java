package com.qylm.bean.engineeringMaterials;


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
import com.qylm.dto.engineeringMaterials.MaterialsGrantCreateProjectDto;
import com.qylm.dxo.MaterialsGrantCreateProjectDxo;
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
public class  MaterialsGrantCreateProjectBean implements Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1785301185324818017L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MaterialsGrantCreateProjectBean.class);

	/**
	 * 存放工作登记登陆画面需要保存的值
	 */
	private MaterialsGrantCreateProjectDto materialsGrantCreateProjectDto = new MaterialsGrantCreateProjectDto();
	
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
		return materialsGrantCreateProjectDto.getReturner().returnOnly();
	}

	public String updateDetail(Returner<?, ?, ?> returner, EngineeringProject engineeringProject) {
		materialsGrantCreateProjectDto.setReturner(returner);
		MaterialsGrantCreateProjectDxo.entityToDto(engineeringProject, materialsGrantCreateProjectDto);
		materialsGrantCreateProjectDto.setTransferEngineeringProject(engineeringProject);
		getVehicleDateil();
		return Navigation.MATERIALS_GRANT_PROJECT_CREATE;
	}
	
	/**
	 * 获取工作登记详细列表
	 */
	private void getVehicleDateil() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
		detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(EngineeringProjectDetail.ENGINEERING_PROJECT, materialsGrantCreateProjectDto.getTransferEngineeringProject()));
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
		materialsGrantCreateProjectDto.setEngineeringProjectDetailList(list);
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
	 * @return the materialsGrantCreateProjectDto
	 */
	public MaterialsGrantCreateProjectDto getMaterialsGrantCreateProjectDto() {
		return materialsGrantCreateProjectDto;
	}

	/**
	 * @param materialsGrantCreateProjectDto the materialsGrantCreateProjectDto to set
	 */
	public void setMaterialsGrantCreateProjectDto(
			MaterialsGrantCreateProjectDto materialsGrantCreateProjectDto) {
		this.materialsGrantCreateProjectDto = materialsGrantCreateProjectDto;
	}

}
