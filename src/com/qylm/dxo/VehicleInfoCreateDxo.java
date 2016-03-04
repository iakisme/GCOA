package com.qylm.dxo;

import com.qylm.dto.vehicle.VehicleInfoCreateDto;
import com.qylm.entity.VehicleInfo;

public final class VehicleInfoCreateDxo {

	public static void dtoToEntity(VehicleInfoCreateDto dto, VehicleInfo entity) {
		entity.setNumber(dto.getNumber());
		entity.setRemark(dto.getRemark());
		entity.setEmployee(dto.getEmployee());
		entity.setPurchaseDate(dto.getPurchaseDate());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(VehicleInfo entity, VehicleInfoCreateDto dto) {
		dto.setNumber(entity.getNumber());
		dto.setRemark(entity.getRemark());
		dto.setEmployee(entity.getEmployee());
		dto.setPurchaseDate(entity.getPurchaseDate());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
