package com.qylm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 采购单审核设定详细
 * @author ShengMinJun
 */
@Entity
@Table(uniqueConstraints = {}, name = "procedureset_detail")
public class ProcedureSetDetail extends BaseEntity implements Comparable<ProcedureSetDetail> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6799290591383094694L;

	/**
	 * 采购单审核
	 */
	public static final String PROCEDURE_SET = "procedureSet";
	
	/**
	 * 审核员
	 */
	public static final String AUDITOR = "auditor";
	
	/**
	 * 等级顺序
	 */
	public static final String SEQUENCE = "sequence";
	
	/**
	 * 采购单审核
	 */
	@ManyToOne(targetEntity = ProcedureSet.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "procedureSetId")
	private ProcedureSet procedureSet;

	/**
	 * 审核员
	 */
	@ManyToOne(targetEntity = User.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "auditorId")
	private User auditor;
	
	/**
	 * 等级顺序
	 */
	private Integer sequence;
	
	public int compareTo(ProcedureSetDetail o) {
		return this.sequence.compareTo(o.getSequence());
	}
	
	/**
	 * get procedureSet
	 * @return the procedureSet
	 */
	public ProcedureSet getProcedureSet() {
		return procedureSet;
	}

	/**
	 * set procedureSet
	 * @param procedureSet the procedureSet to set
	 */
	public void setProcedureSet(ProcedureSet procedureSet) {
		this.procedureSet = procedureSet;
	}

	/**
	 * get auditor
	 * @return the auditor
	 */
	public User getAuditor() {
		return auditor;
	}

	/**
	 * set auditor
	 * @param auditor the auditor to set
	 */
	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	/**
	 * get sequence
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * set sequence
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
