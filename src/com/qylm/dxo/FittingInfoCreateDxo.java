package com.qylm.dxo;

import com.qylm.dto.fitting.FittingInfoCreateDto;
import com.qylm.entity.FittingInfo;

public final class FittingInfoCreateDxo {

	public static void dtoToEntity(FittingInfoCreateDto dto, FittingInfo entity) {
		entity.setCarName(dto.getCarName());
		entity.setFittingBrand(dto.getFittingBrand());
		entity.setFittingName(dto.getFittingName());
		entity.setModel(dto.getModel());
		entity.setPrice(dto.getPrice());
		entity.setMaintenanceDay(dto.getMaintenanceDay());
		entity.setMaintenanceCubic(dto.getMaintenanceCubic());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(FittingInfo entity, FittingInfoCreateDto dto) {
		dto.setCarName(entity.getCarName());
		dto.setFittingBrand(entity.getFittingBrand());
		dto.setFittingName(entity.getFittingName());
		dto.setModel(entity.getModel());
		dto.setPrice(entity.getPrice());
		dto.setMaintenanceDay(entity.getMaintenanceDay());
		dto.setMaintenanceCubic(entity.getMaintenanceCubic());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
