package com.qylm.bean.returner.engineeringMaterials;

import com.qylm.bean.engineeringMaterials.MaterialsGrantManageProjectBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.engineeringMaterials.MaterialsGrantManageProjectDto;
import com.qylm.entity.MaterialsGrant;

public class MaterialsGrantManageProjectReturner extends Returner<MaterialsGrantManageProjectBean, MaterialsGrantManageProjectDto, MaterialsGrant> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1059919089258193141L;

	public MaterialsGrantManageProjectReturner(MaterialsGrantManageProjectDto MaterialsGrantManageProjectDto, int currentPage) {
		super(MaterialsGrantManageProjectDto, currentPage);
	}
	
	@Override
	public String returnOnly(MaterialsGrantManageProjectBean backBean) {
		backBean.setMaterialsGrantManageProjectDto(super.dto);
		return backBean.back(currentPage);
	}

}
