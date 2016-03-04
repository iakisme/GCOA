package com.qylm.dto.customer;

import java.io.Serializable;

/**
 * 保存客户信息管理画面需要的值
 * @author smj
 */
public class CustomerManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3907157686025117745L;

	/**
	 * 客户编号
	 */
	private String serialNumber;
	
	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
