package com.qylm.dxo;

import com.qylm.dto.procedure.ProcedureSetCreateDto;
import com.qylm.entity.ProcedureSet;

public final class ProcedureSetCreateDxo {

	public static void dtoToEntity(ProcedureSetCreateDto dto, ProcedureSet entity) {
		entity.setApplyNumber(dto.getApplyNumber());
		entity.setState(dto.isState());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(ProcedureSet entity, ProcedureSetCreateDto dto) {
		dto.setApplyNumber(entity.getApplyNumber());
		dto.setState(entity.isState());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
