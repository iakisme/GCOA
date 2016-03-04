package com.qylm.entity;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 配件信息久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittinginfo")
@DiscriminatorValue(FittingInfo.DISCRIMINATOR_FITTING_INFO)
public class FittingInfo extends BaseFittingInfo {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8058744109443246158L;
	
	/**
	 * @DiscriminatorValue
	 */
	public static final String DISCRIMINATOR_FITTING_INFO = "fittingInfo";
	
	/**
	 * 车辆名称（搜索条件）
	 */
	public static final String CAR_NAME = "carName";
	
	/**
	 * 配件品牌（搜索条件）
	 */
	public static final String FITTING_BRAND = "fittingBrand";
	
	/**
	 * 配件名称（搜索条件）
	 */
	public static final String FITTING_NAME = "fittingName";
	
	/**
	 * 型号（搜索条件）
	 */
	public static final String MODEL = "model";
	
	/**
	 * 默认显示文件图片
	 */
	@Transient
	private FileControl fileControl;
	
	/**
	 * 配件数量
	 */
	@Transient
	private BigDecimal count;

	/**
	 * @return the fileControl
	 */
	public FileControl getFileControl() {
		return fileControl;
	}

	/**
	 * @param fileControl the fileControl to set
	 */
	public void setFileControl(FileControl fileControl) {
		this.fileControl = fileControl;
	}

	/**
	 * @return the count
	 */
	public BigDecimal getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}

}
