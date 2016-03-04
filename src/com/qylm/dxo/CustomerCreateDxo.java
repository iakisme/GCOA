package com.qylm.dxo;

import com.qylm.dto.customer.CustomerCreateDto;
import com.qylm.entity.Customer;

public final class CustomerCreateDxo {

	public static void dtoToEntity(CustomerCreateDto dto, Customer entity) {
		entity.setSerialNumber(dto.getSerialNumber());
		entity.setManager(dto.getManager());
		entity.setName(dto.getName());
		entity.setAddressEntity(dto.getAddressEntity());
		entity.setAddress(dto.getAddress());
		entity.setBank(dto.getBank());
		entity.setBankNumber(dto.getBankNumber());
		entity.setTrade(dto.getTrade());
		entity.setTaxNo(dto.getTaxNo());
		entity.setUrl(dto.getUrl());
		entity.setEmail(dto.getEmail());
		entity.setFax(dto.getFax());
		entity.setPhone(dto.getPhone());
		entity.setMobile(dto.getMobile());
		entity.setTaxState(dto.isTaxState());
		entity.setTax(dto.getTax());
		entity.setPayment(dto.getPayment());
		entity.setRegisterDate(dto.getRegisterDate());
		entity.setType(dto.getType());
		entity.setPumpPrice(dto.getPumpPrice());
		entity.setBelongingUser(dto.getBelongingUser());
		entity.setCreater(dto.getCreater());
	}

	public static void entityToDto(Customer entity, CustomerCreateDto dto) {
		dto.setSerialNumber(entity.getSerialNumber());
		dto.setManager(entity.getManager());
		dto.setName(entity.getName());
		dto.setAddressEntity(entity.getAddressEntity());
		dto.setAddress(entity.getAddress());
		dto.setBank(entity.getBank());
		dto.setBankNumber(entity.getBankNumber());
		dto.setTrade(entity.getTrade());
		dto.setTaxNo(entity.getTaxNo());
		dto.setUrl(entity.getUrl());
		dto.setEmail(entity.getEmail());
		dto.setFax(entity.getFax());
		dto.setPhone(entity.getPhone());
		dto.setMobile(entity.getMobile());
		dto.setTaxState(entity.isTaxState());
		dto.setTax(entity.getTax());
		dto.setPayment(entity.getPayment());
		dto.setRegisterDate(entity.getRegisterDate());
		dto.setType(entity.getType());
		dto.setPumpPrice(entity.getPumpPrice());
		dto.setCreater(entity.getCreater());
		dto.setBelongingUser(entity.getBelongingUser());
	}

}
