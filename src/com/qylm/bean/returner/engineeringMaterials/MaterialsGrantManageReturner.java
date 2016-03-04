package com.qylm.bean.returner.engineeringMaterials;

import com.qylm.bean.engineeringMaterials.MaterialsGrantManageBean;
import com.qylm.bean.returner.Returner;
import com.qylm.dto.engineeringMaterials.MaterialsGrantManageDto;
import com.qylm.entity.MaterialsGrant;

public class MaterialsGrantManageReturner extends Returner<MaterialsGrantManageBean, MaterialsGrantManageDto, MaterialsGrant> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2824175212548189585L;

	public MaterialsGrantManageReturner(MaterialsGrantManageDto materialsGrantManageDto, int currentPage) {
		super(materialsGrantManageDto, currentPage);
	}
	
	@Override
	public String returnOnly(MaterialsGrantManageBean backBean) {
		backBean.setMaterialsGrantManageDto(super.dto);
		return backBean.back(currentPage);
	}

}
