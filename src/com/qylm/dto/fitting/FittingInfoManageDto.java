package com.qylm.dto.fitting;

import java.io.Serializable;

/**
 * 保存配件信息管理画面需要的值
 * @author smj
 */
public class FittingInfoManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7851917011341308336L;

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
	
}
