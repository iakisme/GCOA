package com.qylm.dxo;

import com.qylm.dto.fitting.FittingLibraryCreateDto;
import com.qylm.entity.FittingLibrary;

public final class FittingLibraryCreateDxo {

	public static void dtoToEntity(FittingLibraryCreateDto dto, FittingLibrary entity) {
		entity.setNumber(dto.getNumber());
		entity.setLibraryDate(dto.getLibraryDate());
		entity.setLibraryType(dto.getLibraryType());
		entity.setRemark(dto.getRemark());
		entity.setState(dto.isState());
		entity.setFittingLibraryDetailList(dto.getFittingLibraryDetailList());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(FittingLibrary entity, FittingLibraryCreateDto dto) {
		dto.setNumber(entity.getNumber());
		dto.setLibraryDate(entity.getLibraryDate());
		dto.setLibraryType(entity.getLibraryType());
		dto.setRemark(entity.getRemark());
		dto.setState(entity.isState());
		dto.setFittingLibraryDetailList(entity.getFittingLibraryDetailList());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
