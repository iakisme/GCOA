package com.qylm.dto.personnel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import com.qylm.bean.returner.Returner;
import com.qylm.entity.AddressEntity;
import com.qylm.entity.Employee;
import com.qylm.entity.User;

/**
 * 保存员工信息管理画面需要的值
 * @author smj
 */
public class EmployeeCreateDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4035418565746586403L;

	/**
	 * 工号
	 */
	private String workNumber;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 在职状态
	 * <ul>
	 * <li>1:在职</li>
	 * <li>2:离职</li>
	 * </ul>
	 */
	private String workState;
	
	/**
	 * 性别
	 * <ul>
	 * <li>1：男</li>
	 * <li>2：女</li>
	 * </ul>
	 */
	private String sex;
	
	/**
	 * 出生日期
	 */
	private Date birthDate;
	
	/**
	 * 政治面貌
	 * <ul>
	 * <li>1：青年</li>
	 * <li>2：团员</li>
	 * <li>3：党员</li>
	 * </ul>
	 */
	private String politicalStatus;
	
	/**
	 * 民族
	 * <ul>
	 * <li>56个名族</li>
	 * </ul>
	 */
	private String nation;
	
	/**
	 * 婚否
	 * <ul>
	 * <li>1：已婚</li>
	 * <li>2：未婚</li>
	 * <li>3：离异</li>
	 * </ul>
	 */
	private String marriage;
	
	/**
	 * 身份证
	 */
	private String identification;
	
	/**
	 * 学历
	 * <ul>
	 * <li>1：小学</li>
	 * <li>2：初中</li>
	 * <li>3：高中</li>
	 * <li>4：大专</li>
	 * <li>5：本科</li>
	 * <li>6：研究生</li>
	 * <li>7：博士</li>
	 * </ul>
	 */
	private String formalSchooling;
	
	/**
	 * 毕业时间
	 */
	private Date graduationDate;
	
	/**
	 * 毕业学校
	 */
	private String graduationSchool;
	
	/**
	 * 专业
	 */
	private String profession;
	
	/**
	 * 地址
	 */
	@Embedded
	private AddressEntity addressEntity;
	
	/**
	 * 现住址
	 */
	private String address;
	
	/**
	 * 家庭情况
	 */
	@Lob
	@Basic(fetch=FetchType.EAGER) 
	@Column(name="homeState", columnDefinition="text")
	private String homeState;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 手机手机号码
	 */
	private String mobile;
	
	/**
	 * 员工类型
	 * <ul>
	 * <li>1：项目负责人</li>
	 * <li>10：普通员工</li>
	 * </ul>
	 */
	private String type;
	
	/**
	 * 创建员工
	 */
	private User creater;
	
	/**
	 * 归属公司
	 */
	private User belongingUser;
	
	/**
	 * 修改传值
	 */
	private Employee transferEmployee;
	
	/**
	 * 返回共通方法
	 */
	private Returner<?, ?, ?> returner;

	/**
	 * @return the workNumber
	 */
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * @param workNumber the workNumber to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the workState
	 */
	public String getWorkState() {
		return workState;
	}

	/**
	 * @param workState the workState to set
	 */
	public void setWorkState(String workState) {
		this.workState = workState;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the politicalStatus
	 */
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	/**
	 * @param politicalStatus the politicalStatus to set
	 */
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	/**
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * @return the marriage
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * @param marriage the marriage to set
	 */
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}

	/**
	 * @return the formalSchooling
	 */
	public String getFormalSchooling() {
		return formalSchooling;
	}

	/**
	 * @param formalSchooling the formalSchooling to set
	 */
	public void setFormalSchooling(String formalSchooling) {
		this.formalSchooling = formalSchooling;
	}

	/**
	 * @return the graduationDate
	 */
	public Date getGraduationDate() {
		return graduationDate;
	}

	/**
	 * @param graduationDate the graduationDate to set
	 */
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	/**
	 * @return the graduationSchool
	 */
	public String getGraduationSchool() {
		return graduationSchool;
	}

	/**
	 * @param graduationSchool the graduationSchool to set
	 */
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the addressEntity
	 */
	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	/**
	 * @param addressEntity the addressEntity to set
	 */
	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the homeState
	 */
	public String getHomeState() {
		return homeState;
	}

	/**
	 * @param homeState the homeState to set
	 */
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the creater
	 */
	public User getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(User creater) {
		this.creater = creater;
	}

	/**
	 * @return the belongingUser
	 */
	public User getBelongingUser() {
		return belongingUser;
	}

	/**
	 * @param belongingUser the belongingUser to set
	 */
	public void setBelongingUser(User belongingUser) {
		this.belongingUser = belongingUser;
	}

	/**
	 * @return the transferEmployee
	 */
	public Employee getTransferEmployee() {
		return transferEmployee;
	}

	/**
	 * @param transferEmployee the transferEmployee to set
	 */
	public void setTransferEmployee(Employee transferEmployee) {
		this.transferEmployee = transferEmployee;
	}

	/**
	 * @return the returner
	 */
	public Returner<?, ?, ?> getReturner() {
		return returner;
	}

	/**
	 * @param returner the returner to set
	 */
	public void setReturner(Returner<?, ?, ?> returner) {
		this.returner = returner;
	}

}
