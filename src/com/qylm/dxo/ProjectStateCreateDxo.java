package com.qylm.dxo;

import com.qylm.dto.engineering.ProjectStateCreateDto;
import com.qylm.entity.EngineeringProject;

public final class ProjectStateCreateDxo {

	public static void dtoToEntity(ProjectStateCreateDto dto, EngineeringProject entity) {
		entity.setCustomer(dto.getCustomer());
		entity.setEmployee(dto.getEmployee());
		entity.setBeginDate(dto.getBeginDate());
		entity.setEndDate(dto.getEndDate());
		entity.setWorkAddress(dto.getWorkAddress());
		entity.setConstructionName(dto.getConstructionName());
		entity.setRemark(dto.getRemark());
		entity.setMoney(dto.getMoney());
		entity.setPumpPrice(dto.getPumpPrice());
		entity.setActualVolume(dto.getActualVolume());
		entity.setSchedule(dto.getSchedule());
		entity.setType(dto.getType());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(EngineeringProject entity, ProjectStateCreateDto dto) {
		dto.setCustomer(entity.getCustomer());
		dto.setEmployee(entity.getEmployee());
		dto.setBeginDate(entity.getBeginDate());
		dto.setEndDate(entity.getEndDate());
		dto.setWorkAddress(entity.getWorkAddress());
		dto.setConstructionName(entity.getConstructionName());
		dto.setRemark(entity.getRemark());
		dto.setMoney(entity.getMoney());
		dto.setPumpPrice(entity.getPumpPrice());
		dto.setActualVolume(entity.getActualVolume());
		dto.setSchedule(entity.getSchedule());
		dto.setType(entity.getType());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
