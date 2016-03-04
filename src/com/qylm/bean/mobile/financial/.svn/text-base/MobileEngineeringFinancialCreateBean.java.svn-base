package com.qylm.bean.mobile.financial;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.dto.mobile.financial.MobileEngineeringFinancialCreateDto;
import com.qylm.entity.CustomerFinancial;

/**
 * 客户收支明细登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class MobileEngineeringFinancialCreateBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7872556391522580534L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MobileEngineeringFinancialCreateBean.class);
	
	/**
	 * 存放客户收支明细登录页面需要保存的值
	 */
	private MobileEngineeringFinancialCreateDto mobileEngineeringFinancialCreateDto = new MobileEngineeringFinancialCreateDto();

	/**
	 * 客户收支明细bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 此方法绑定于客户收支明细登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【客户收支明细登陆画面_返回按钮】");
		return mobileEngineeringFinancialCreateDto.getReturner().returnOnly();
	}

	public String updateDetail(Returner<?, ?, ?> returner, CustomerFinancial customerFinancial) {
		mobileEngineeringFinancialCreateDto.setReturner(returner);
		mobileEngineeringFinancialCreateDto.setCustomerFinancial(customerFinancial);
		return Navigation.MOBILE_ENGINEERING_FINANCIAL_CREATE;
	}

	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	/**
	 * @return the mobileEngineeringFinancialCreateDto
	 */
	public MobileEngineeringFinancialCreateDto getMobileEngineeringFinancialCreateDto() {
		return mobileEngineeringFinancialCreateDto;
	}

	/**
	 * @param mobileEngineeringFinancialCreateDto the mobileEngineeringFinancialCreateDto to set
	 */
	public void setMobileEngineeringFinancialCreateDto(
			MobileEngineeringFinancialCreateDto mobileEngineeringFinancialCreateDto) {
		this.mobileEngineeringFinancialCreateDto = mobileEngineeringFinancialCreateDto;
	}
	
}
