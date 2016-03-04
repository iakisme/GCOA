package com.qylm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 配件出库久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "fittinglibrary")
public class FittingLibrary extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2993743810563030048L;

	/**
	 * 出库单编号
	 */
	public static final String NUMBER = "number";

	/**
	 * 出库日期
	 */
	public static final String LIBRARY_DATE = "libraryDate";
	
	/**
	 * 出库类型
	 */
	public static final String LIBRARY_TYPE = "libraryType";
	
	/**
	 * 出库类型:车辆维修出库
	 */
	public static final String LIBRARY_TYPE_1 = "1";
	
	/**
	 * 出库单编号
	 */
	private String number;
	
	/**
	 * 出库日期
	 */
	private Date libraryDate;
	
	/**
	 * 出库类型
	 * <ul>
	 * <li>1:车辆维修出库</li>
	 * </ul>
	 */
	private String libraryType;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 出库状态
	 * true：已出库，反之未出库
	 */
	private boolean state;
	
	/**
	 * 存放对于的出库详细
	 */
	@Transient
	private List<FittingLibraryDetail> fittingLibraryDetailList;

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
	 * @return the libraryDate
	 */
	public Date getLibraryDate() {
		return libraryDate;
	}

	/**
	 * @param libraryDate the libraryDate to set
	 */
	public void setLibraryDate(Date libraryDate) {
		this.libraryDate = libraryDate;
	}

	/**
	 * @return the libraryType
	 */
	public String getLibraryType() {
		return libraryType;
	}

	/**
	 * @param libraryType the libraryType to set
	 */
	public void setLibraryType(String libraryType) {
		this.libraryType = libraryType;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @return the fittingLibraryDetailList
	 */
	public List<FittingLibraryDetail> getFittingLibraryDetailList() {
		return fittingLibraryDetailList;
	}

	/**
	 * @param fittingLibraryDetailList the fittingLibraryDetailList to set
	 */
	public void setFittingLibraryDetailList(
			List<FittingLibraryDetail> fittingLibraryDetailList) {
		this.fittingLibraryDetailList = fittingLibraryDetailList;
	}
	
}
