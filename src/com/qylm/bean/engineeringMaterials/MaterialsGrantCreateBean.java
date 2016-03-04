package com.qylm.bean.engineeringMaterials;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;

import com.qylm.bean.UserBean;
import com.qylm.bean.engineering.EngineeringProjectManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.engineeringMaterials.MaterialsGrantCreateDto;
import com.qylm.dxo.MaterialsGrantCreateDxo;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.MaterialsGrant;
import com.qylm.service.MaterialsGrantService;

/**
 * 原料发放记录登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class MaterialsGrantCreateBean implements CreateBean<MaterialsGrant>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MaterialsGrantCreateBean.class);

	/**
	 * 存放原料发放记录登陆画面需要保存的值
	 */
	private MaterialsGrantCreateDto materialsGrantCreateDto = new MaterialsGrantCreateDto();
	/**
	 * 原料发放记录bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 原料发放记录业务类
	 */
	@ManagedProperty(value="#{materialsGrantService}")
	private MaterialsGrantService materialsGrantService;
	
	/**
	 * 此方法绑定于原料发放记录登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个原料发放记录
	 * @return 原料发放记录登陆画面
	 */
	public String newMaterialsGrant() {
		Tool.sendLog(LOG, userBean, "【原料发放记录登陆画面_新建按钮】");
		materialsGrantCreateDto.setEngineeringProjectDetail(null);
		materialsGrantCreateDto.setEmployee(null);
		materialsGrantCreateDto.setReceiveSum(null);
		materialsGrantCreateDto.setState(null);
		materialsGrantCreateDto.setType(null);
		materialsGrantCreateDto.setRemark(null);
		materialsGrantCreateDto.setCreater(null);
		materialsGrantCreateDto.setBelongingUser(null);
		materialsGrantCreateDto.setTransferMaterialsGrant(null);
		return Navigation.MATERIALS_GRANT_CREATE;
	}
	
	/**
	 * 此方法绑定于原料发放记录登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【原料发放记录登陆画面_返回按钮】");
		return materialsGrantCreateDto.getReturner().returnOnly();	
	}

	/**
	 * 此方法绑定于原料发放记录登陆画面的保存按钮 
	 * 实现功能：根据transferMaterialsGrant对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveMaterialsGrant() {
		Tool.sendLog(LOG, userBean, "【原料发放记录登陆画面_保存按钮】");
		MaterialsGrant transferMaterialsGrant = materialsGrantCreateDto.getTransferMaterialsGrant();

		if (transferMaterialsGrant == null) {
			transferMaterialsGrant = new MaterialsGrant();
			materialsGrantCreateDto.setCreater(userBean.getUser());
			materialsGrantCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
			create(transferMaterialsGrant);
			transferMaterialsGrant.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
			materialsGrantService.saveEntity(transferMaterialsGrant);
			materialsGrantCreateDto.setTransferMaterialsGrant(transferMaterialsGrant);
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			create(transferMaterialsGrant);
			transferMaterialsGrant.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			materialsGrantService.updateEntity(transferMaterialsGrant);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
	}
	
	/**
	 * 赋值
	 * @param transferMaterialsGrant
	 */
	private void create(MaterialsGrant transferMaterialsGrant) {
		MaterialsGrantCreateDxo.dtoToEntity(materialsGrantCreateDto, transferMaterialsGrant);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		materialsGrantCreateDto.setReturner(returner);
		return Navigation.MATERIALS_GRANT_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, MaterialsGrant materialsGrant) {
		materialsGrantCreateDto.setReturner(returner);
		MaterialsGrantCreateDxo.entityToDto(materialsGrant, materialsGrantCreateDto);
		materialsGrantCreateDto.setTransferMaterialsGrant(materialsGrant);
		return Navigation.MATERIALS_GRANT_CREATE;
	}

	/**
	 * set materialsGrantService
	 * @param materialsGrantService the materialsGrantService to set
	 */
	public void setMaterialsGrantService(MaterialsGrantService materialsGrantService) {
		this.materialsGrantService = materialsGrantService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get materialsGrantCreateDto
	 * @return the materialsGrantCreateDto
	 */
	public MaterialsGrantCreateDto getMaterialsGrantCreateDto() {
		return materialsGrantCreateDto;
	}

	/**
	 * set materialsGrantCreateDto
	 * @param materialsGrantCreateDto the materialsGrantCreateDto to set
	 */
	public void setMaterialsGrantCreateDto(MaterialsGrantCreateDto materialsGrantCreateDto) {
		this.materialsGrantCreateDto = materialsGrantCreateDto;
	}

}
