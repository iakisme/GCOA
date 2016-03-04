package com.qylm.dxo;

import com.qylm.dto.financial.CustomerFinancialCreateDto;
import com.qylm.entity.CustomerFinancial;

public final class CustomerFinancialCreateDxo {

	public static void dtoToEntity(CustomerFinancialCreateDto dto, CustomerFinancial entity) {
		entity.setNumber(dto.getNumber());
		entity.setCustomer(dto.getCustomer());
		entity.setType(dto.getType());
		entity.setPumpPrice(dto.getPumpPrice());
		entity.setState(dto.getState());
		entity.setFinancialDate(dto.getFinancialDate());
		entity.setMoney(dto.getMoney());
		entity.setCreater(dto.getCreater());
		entity.setBelongingUser(dto.getBelongingUser());
	}

	public static void entityToDto(CustomerFinancial entity, CustomerFinancialCreateDto dto) {
		dto.setNumber(entity.getNumber());
		dto.setCustomer(entity.getCustomer());
		dto.setType(entity.getType());
		dto.setPumpPrice(entity.getPumpPrice());
		dto.setState(entity.getState());
		dto.setFinancialDate(entity.getFinancialDate());
		dto.setMoney(entity.getMoney());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
