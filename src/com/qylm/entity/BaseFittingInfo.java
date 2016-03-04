package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

/**
 * 配件信息父类
 * @author smj
 */
@MappedSuperclass
public class BaseFittingInfo extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2234845252342936923L;
	
	/**
	 * 配件编号（用于建立唯一标识）
	 */
	private String number;
	
	/**
	 * 车辆名称
	 */
	private String carName;
	
	/**
	 * 配件品牌
	 */
	private String fittingBrand;
	
	/**
	 * 配件名称
	 */
	private String fittingName;
	
	/**
	 * 型号
	 */
	private String model;
	
	/**
	 * 保养天数
	 */
	private Integer maintenanceDay;
	
	/**
	 * 保养立方
	 */
	private BigDecimal maintenanceCubic;
	
	/**
	 * 最新单价
	 */
	private BigDecimal price;
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}

	/**
	 * @param carName the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}

	/**
	 * @return the fittingBrand
	 */
	public String getFittingBrand() {
		return fittingBrand;
	}

	/**
	 * @param fittingBrand the fittingBrand to set
	 */
	public void setFittingBrand(String fittingBrand) {
		this.fittingBrand = fittingBrand;
	}

	/**
	 * @return the fittingName
	 */
	public String getFittingName() {
		return fittingName;
	}

	/**
	 * @param fittingName the fittingName to set
	 */
	public void setFittingName(String fittingName) {
		this.fittingName = fittingName;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the maintenanceDay
	 */
	public Integer getMaintenanceDay() {
		return maintenanceDay;
	}

	/**
	 * @param maintenanceDay the maintenanceDay to set
	 */
	public void setMaintenanceDay(Integer maintenanceDay) {
		this.maintenanceDay = maintenanceDay;
	}

	/**
	 * @return the maintenanceCubic
	 */
	public BigDecimal getMaintenanceCubic() {
		return maintenanceCubic;
	}

	/**
	 * @param maintenanceCubic the maintenanceCubic to set
	 */
	public void setMaintenanceCubic(BigDecimal maintenanceCubic) {
		this.maintenanceCubic = maintenanceCubic;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
