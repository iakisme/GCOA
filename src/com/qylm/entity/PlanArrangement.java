package com.qylm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日常计划安排持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "planarrangement")
public class PlanArrangement extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3644156886142557873L;
	
	/**
	 * beginDate
	 */
	public static final String BEGIN_DATE = "beginDate";
	
	/**
	 * endDate
	 */
	public static final String END_DATE = "endDate";
	
	/**
	 * state
	 */
	public static final String STATE = "state";
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 开始时间
	 */
	private Date beginDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 内容
	 */
	private String remark;
	
	/**
	 * true：已提醒，反之需要提醒，但是过期不提醒
	 */
	private boolean state;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

}
