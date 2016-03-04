package com.qylm.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 客户持久化类
 * @author smj
 */
@Entity
@Table(uniqueConstraints = {}, name = "customer")
public class Customer extends BaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7749840431171864067L;

	/**
	 * 检索条件:serialNumber
	 */
	public static final String SERIAL_NUMBER = "serialNumber";

	/**
	 * 检索条件:name
	 */
	public static final String NAME = "name";

	/**
	 * 检索条件:trade
	 */
	public static final String TRADE = "trade";

	/**
	 * 检索条件:payment
	 */
	public static final String PAYMENT = "payment";
	
	/**
	 * 检索条件:bank
	 */
	public static final String BANK = "bank";
	
	/**
	 * 检索条件:registerDate
	 */
	public static final String REGISTER_DATE = "registerDate";
	
	/**
	 * 检索条件:收款类型
	 */
	public static final String TYPE = "type";

	/**
	 * 检索条件:单价
	 */
	public static final String TYPE_1 = "1";
	
	/**
	 * 检索条件:包月
	 */
	public static final String TYPE_2 = "2";
	
	/**
	 * 检索条件:包年
	 */
	public static final String TYPE_3 = "3";
	
	/**
	 * 客户编号
	 */
	private String serialNumber;
	
	/**
	 * 负责人 法人
	 */
	private String manager;
	
	/**
	 * 公司名称
	 */
	private String name;
	
	/**
	 * 地址
	 */
	@Embedded
	private AddressEntity addressEntity;
	
	/**
	 * 公司地址
	 */
	private String address;
	
	/**
	 * 开户银行
	 */
	private String bank;
	
	/**
	 * 开户行账号
	 */
	private String bankNumber;
	
	/**
	 * 所属行业
	 */
	private String trade;
	
	/**
	 * 纳税号
	 */
	private String taxNo;
	
	/**
	 * 公司网址
	 */
	private String url;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 手机手机号码
	 */
	private String mobile;
	
	/**
	 * 是否含税
	 */
	private boolean taxState;
	
	/**
	 * 税点
	 */
	private BigDecimal tax;
	
	/**
	 * 付款方式
	 */
	private String payment;
	
	/**
	 * 成立时间
	 */
	private Date registerDate;
	
	/**
	 * 收款类型
	 * 1：单价
	 * 2：包月
	 * 3：包年
	 */
	private String type;
	
	/**
	 * 方量单价
	 */
	private BigDecimal pumpPrice;
	
	/**
	 * 应收款
	 */
	@Transient
	private BigDecimal receivables;
	
	/**
	 * 实收款
	 */
	@Transient
	private BigDecimal actualRecePrice;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
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
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the bankNumber
	 */
	public String getBankNumber() {
		return bankNumber;
	}

	/**
	 * @param bankNumber the bankNumber to set
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	/**
	 * @return the trade
	 */
	public String getTrade() {
		return trade;
	}

	/**
	 * @param trade the trade to set
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}

	/**
	 * @return the taxNo
	 */
	public String getTaxNo() {
		return taxNo;
	}

	/**
	 * @param taxNo the taxNo to set
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	 * @return the taxState
	 */
	public boolean isTaxState() {
		return taxState;
	}

	/**
	 * @param taxState the taxState to set
	 */
	public void setTaxState(boolean taxState) {
		this.taxState = taxState;
	}

	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
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
	 * @return the pumpPrice
	 */
	public BigDecimal getPumpPrice() {
		return pumpPrice;
	}

	/**
	 * @param pumpPrice the pumpPrice to set
	 */
	public void setPumpPrice(BigDecimal pumpPrice) {
		this.pumpPrice = pumpPrice;
	}

	/**
	 * @return the receivables
	 */
	public BigDecimal getReceivables() {
		return receivables;
	}

	/**
	 * @param receivables the receivables to set
	 */
	public void setReceivables(BigDecimal receivables) {
		this.receivables = receivables;
	}

	/**
	 * @return the actualRecePrice
	 */
	public BigDecimal getActualRecePrice() {
		return actualRecePrice;
	}

	/**
	 * @param actualRecePrice the actualRecePrice to set
	 */
	public void setActualRecePrice(BigDecimal actualRecePrice) {
		this.actualRecePrice = actualRecePrice;
	}

}
