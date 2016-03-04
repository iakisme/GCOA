package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 出库单详细久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittinglibrary_detail")
public class FittingLibraryDetail extends BaseFittingInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8993422118285029442L;

	/**
	 * 出库单
	 */
	public static final String FITTING_LIBRARY = "fittingLibrary";
	
	/**
	 * 使用车辆
	 */
	public static final String VEHICLE_INFO = "vehicleInfo";
	
	/**
	 * 出库单
	 */
	@ManyToOne(targetEntity = FittingLibrary.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "fittingLibraryId")
	private FittingLibrary fittingLibrary;
	
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	
	/**
	 * 使用车辆
	 */
	@ManyToOne(targetEntity = VehicleInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleInfoId")
	private VehicleInfo vehicleInfo;
	
	/**
	 * 库存现数量
	 */
	@Transient
	private BigDecimal repertorySum;
	
	/**
	 * @return the fittingLibrary
	 */
	public FittingLibrary getFittingLibrary() {
		return fittingLibrary;
	}

	/**
	 * @param fittingLibrary the fittingLibrary to set
	 */
	public void setFittingLibrary(FittingLibrary fittingLibrary) {
		this.fittingLibrary = fittingLibrary;
	}

	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the vehicleInfo
	 */
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	/**
	 * @param vehicleInfo the vehicleInfo to set
	 */
	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	/**
	 * @return the repertorySum
	 */
	public BigDecimal getRepertorySum() {
		return repertorySum;
	}

	/**
	 * @param repertorySum the repertorySum to set
	 */
	public void setRepertorySum(BigDecimal repertorySum) {
		this.repertorySum = repertorySum;
	}

}
