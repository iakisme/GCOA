package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 采购审核设定管理
 * @author ShengMinJun
 */
@Entity
@Table(uniqueConstraints = {}, name = "procedureset")
public class ProcedureSet extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4106624678185227636L;

	/**
	 * 检索条件：申请单编号
	 */
	public static final String APPLY_NUMBER = "applyNumber";
	
	/**
	 * 检索条件：配件采购单编号
	 */
	public static final String APPLY_NUMBER_1 = "1";
	
	/**
	 * 检索条件：设定状态 true 已设定
	 */
	public static final String STATE = "state";

	/**
	 * 申请单编号
	 */
	private String applyNumber;
	
	/**
	 * 设定状态 true 已设定
	 */
	private boolean state;
	
	/**
	 * 此属性用于显示审核名称
	 */
	@Transient
	private String showName;
	
	/**
	 * get applyNumber
	 * @return the applyNumber
	 */
	public String getApplyNumber() {
		return applyNumber;
	}

	/**
	 * set applyNumber
	 * @param applyNumber the applyNumber to set
	 */
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	/**
	 * get state
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * set state
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * get showName
	 * @return the showName
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * set showName
	 * @param showName the showName to set
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

}
