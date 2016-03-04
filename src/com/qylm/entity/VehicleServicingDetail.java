package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 车辆维修配件持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "vehicleservicing_detail")
public class VehicleServicingDetail extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7807741400857286372L;

	/**
	 * 车辆维修
	 */
	@ManyToOne(targetEntity = VehicleServicing.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicleServicingId")
	private VehicleServicing vehicleServicing;
	
}
