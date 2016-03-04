package com.qylm.dxo;

import com.qylm.dto.fitting.FittingPurchaseCreateDto;
import com.qylm.entity.FittingPurchase;

public final class FittingPurchaseCreateDxo {

	public static void dtoToEntity(FittingPurchaseCreateDto dto, FittingPurchase entity) {
		entity.setSerialNumber(dto.getSerialNumber());
		entity.setApplyDate(dto.getApplyDate());
		entity.setApplyPrice(dto.getApplyPrice());
		entity.setBudgetPrice(dto.getBudgetPrice());
		entity.setPurchaseDate(dto.getPurchaseDate());
		entity.setTitle(dto.getTitle());
		entity.setPurchaseSource(dto.getPurchaseSource());
		entity.setRemark(dto.getRemark());
		entity.setPurchasePrice(dto.getPurchasePrice());
		entity.setState(dto.getState());
		entity.setFreight(dto.getFreight());
		entity.setPurchaseUser(dto.getPurchaseUser());
		entity.setStorageUser(dto.getStorageUser());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(FittingPurchase entity, FittingPurchaseCreateDto dto) {
		dto.setSerialNumber(entity.getSerialNumber());
		dto.setApplyDate(entity.getApplyDate());
		dto.setApplyPrice(entity.getApplyPrice());
		dto.setBudgetPrice(entity.getBudgetPrice());
		dto.setPurchaseDate(entity.getPurchaseDate());
		dto.setTitle(entity.getTitle());
		dto.setPurchaseSource(entity.getPurchaseSource());
		dto.setRemark(entity.getRemark());
		dto.setPurchasePrice(entity.getPurchasePrice());
		dto.setState(entity.getState());
		dto.setFreight(entity.getFreight());
		dto.setPurchaseUser(entity.getPurchaseUser());
		dto.setStorageUser(entity.getStorageUser());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
