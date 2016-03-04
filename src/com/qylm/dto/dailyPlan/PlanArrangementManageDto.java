package com.qylm.dto.dailyPlan;

import java.io.Serializable;
import java.util.Date;

import com.qylm.entity.PlanArrangement;

/**
 * 保存日常计划信息管理画面需要的值
 * @author smj
 */
public class PlanArrangementManageDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7980400824858918586L;
	
	/**
	 * 保存或者修改传值
	 */
	private PlanArrangement planArrangement;
	
	/**
	 * 当前日期
	 */
	private Date nowDate;

	/**
	 * @return the planArrangement
	 */
	public PlanArrangement getPlanArrangement() {
		return planArrangement;
	}

	/**
	 * @param planArrangement the planArrangement to set
	 */
	public void setPlanArrangement(PlanArrangement planArrangement) {
		this.planArrangement = planArrangement;
	}

	/**
	 * @return the nowDate
	 */
	public Date getNowDate() {
		return nowDate;
	}

	/**
	 * @param nowDate the nowDate to set
	 */
	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	
}
