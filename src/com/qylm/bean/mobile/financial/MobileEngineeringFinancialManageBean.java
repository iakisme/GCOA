package com.qylm.bean.mobile.financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.MobileBasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.mobile.financial.MobileEngineeringFinancialManageReturner;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.mobile.financial.MobileEngineeringFinancialManageDto;
import com.qylm.entity.CustomerFinancial;
import com.qylm.entity.EngineeringFinancial;
import com.qylm.entity.FinancialCollectDetail;
import com.qylm.entity.FinancialPayDetail;
import com.qylm.service.CustomerFinancialService;
import com.qylm.service.EngineeringFinancialService;
import com.qylm.service.FinancialCollectDetailService;
import com.qylm.service.FinancialPayDetailService;

/**
 * 工程收支明细管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class MobileEngineeringFinancialManageBean extends MobileBasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9194958835129113301L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(MobileEngineeringFinancialManageBean.class);
	
	/**
	 * 保存工程收支明细管理画面需要保存的值
	 */
	private MobileEngineeringFinancialManageDto mobileEngineeringFinancialManageDto = new MobileEngineeringFinancialManageDto();

	/**
	 * 客户收支明细列表（检索结果）
	 */
	private List<CustomerFinancial> customerFinancialList;
	
	/**
	 * 工程收支明细管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 客户收支明细管理业务类
	 */
	@ManagedProperty(value="#{customerFinancialService}")
	private CustomerFinancialService customerFinancialService;
	
	/**
	 * 收款项目业务类
	 */
	@ManagedProperty(value="#{engineeringFinancialService}")
	private EngineeringFinancialService engineeringFinancialService;
	
	/**
	 * 工程收款明细业务类
	 */
	@ManagedProperty(value="#{financialCollectDetailService}")
	private FinancialCollectDetailService financialCollectDetailService;
	
	/**
	 * 工程支付明细业务类
	 */
	@ManagedProperty(value="#{financialPayDetailService}")
	private FinancialPayDetailService financialPayDetailService;
	
	/**
	 * 查询出所有工程收支明细列表
	 * 
	 * @return 工程收支明细管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_工程收支明细管理按钮】");
		fetchData(0, true);
		return Navigation.MOBILE_ENGINEERING_FINANCIAL_MANAGE;
	}

	/**
	 * 此方法绑定于工程收支明细管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出工程收支明细
	 * @return 画面不跳转
	 */
	public void selectMobileEngineeringFinancial() {
		Tool.sendLog(LOG, userBean, "按下【工程收支明细管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 此方法绑定与详细按钮
	 * @param customerFinancial
	 * @return
	 */
	public String updateEngineeringProject(CustomerFinancial customerFinancial) {
		Tool.sendLog(LOG, userBean, "按下【工程收支明细管理画面_详细按钮】");
		MobileEngineeringFinancialManageReturner returner = new MobileEngineeringFinancialManageReturner(mobileEngineeringFinancialManageDto, currentPage);
		return Tool.getBackBean(MobileEngineeringFinancialCreateBean.class).updateDetail(returner, customerFinancial);
	}
	
	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerFinancial.class);
		detachedCriteria.createAlias(CustomerFinancial.CUSTOMER, CustomerFinancial.CUSTOMER, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(CustomerFinancial.STATE, CustomerFinancial.STATE_2));
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.addOrder(Order.desc(CustomerFinancial.FINANCIAL_DATE));
		String customerName = mobileEngineeringFinancialManageDto.getCustomerName();
		if (StringUtil.isNotBlank(customerName)) {
			detachedCriteria.add(Restrictions.like(CustomerFinancial.CUSTOMER_NAME, customerName, MatchMode.ANYWHERE));
		}
		customerFinancialList = customerFinancialService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(customerFinancialService.getRowCount(detachedCriteria));
		}
		if (!customerFinancialList.isEmpty()) {
			// 查询对应的收款项目
			detachedCriteria = DetachedCriteria.forClass(EngineeringFinancial.class);
			detachedCriteria.createAlias(EngineeringFinancial.ENGINEERING_PROJECT, EngineeringFinancial.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(EngineeringFinancial.CUSTOMER_FINANCIAL, EngineeringFinancial.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(EngineeringFinancial.CUSTOMER_FINANCIAL, customerFinancialList));
			List<EngineeringFinancial> engineeringFinancialList = engineeringFinancialService.getByDetachedCriteria(detachedCriteria);
			
			// 收款明细
			detachedCriteria = DetachedCriteria.forClass(FinancialCollectDetail.class);
			detachedCriteria.createAlias(FinancialCollectDetail.CUSTOMER_FINANCIAL, FinancialCollectDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FinancialCollectDetail.CUSTOMER_FINANCIAL, customerFinancialList));
			detachedCriteria.add(Restrictions.eq(FinancialCollectDetail.STATE, true));
			List<FinancialCollectDetail> financialCollectDetailList = financialCollectDetailService.getByDetachedCriteria(detachedCriteria);
			
			// 付款明细
			detachedCriteria = DetachedCriteria.forClass(FinancialPayDetail.class);
			detachedCriteria.createAlias(FinancialPayDetail.CUSTOMER_FINANCIAL, FinancialPayDetail.CUSTOMER_FINANCIAL, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(FinancialPayDetail.CUSTOMER_FINANCIAL, customerFinancialList));
			detachedCriteria.add(Restrictions.eq(FinancialPayDetail.STATE, true));
			List<FinancialPayDetail> financialPayDetailList = financialPayDetailService.getByDetachedCriteria(detachedCriteria);
			// 统计出收支明细
			for (CustomerFinancial customerFinancial : customerFinancialList) {
				List<EngineeringFinancial> financialList = new ArrayList<EngineeringFinancial>();
				// 收款金额
				BigDecimal collectMoney = Constants.BIGDECIMAL_ZERO;
				// 消费金额
				BigDecimal payMoney = Constants.BIGDECIMAL_ZERO;
				// 方量
				BigDecimal sumSchedule = Constants.BIGDECIMAL_ZERO;
				for (EngineeringFinancial engineeringFinancial : engineeringFinancialList) {
					if (customerFinancial.equals(engineeringFinancial.getCustomerFinancial())) {
						sumSchedule = BigDecimalUtil.add(sumSchedule, engineeringFinancial.getEngineeringProject().getSchedule());
						financialList.add(engineeringFinancial);
					}
				}
				customerFinancial.setSumSchedule(sumSchedule);
				customerFinancial.setEngineeringFinancialList(financialList);
				// 统计出收款金额
				for (FinancialCollectDetail financialCollectDetail : financialCollectDetailList) {
					if (customerFinancial.equals(financialCollectDetail.getCustomerFinancial())) {
						collectMoney = BigDecimalUtil.add(collectMoney, financialCollectDetail.getMoney());
					}
				}
				customerFinancial.setCollectMoney(collectMoney);
				// 统计出支付明细
				for (FinancialPayDetail financialPayDetail : financialPayDetailList) {
					if (customerFinancial.equals(financialPayDetail.getCustomerFinancial())) {
						payMoney = BigDecimalUtil.add(payMoney, financialPayDetail.getMoney());
					}
				}
				customerFinancial.setPayMoney(payMoney);
			}
		}
	}
	
	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人工程收支明细管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.MOBILE_ENGINEERING_FINANCIAL_MANAGE;
	}
	
	/**
	 * 需要返回本画面时都调用此共通方法
	 * @return 桌面
	 */
	public String back() {
		return Navigation.MOBILE_MY_DESK;
	}

	/**
	 * @param customerFinancialService the customerFinancialService to set
	 */
	public void setCustomerFinancialService(
			CustomerFinancialService customerFinancialService) {
		this.customerFinancialService = customerFinancialService;
	}

	/**
	 * @param engineeringFinancialService the engineeringFinancialService to set
	 */
	public void setEngineeringFinancialService(
			EngineeringFinancialService engineeringFinancialService) {
		this.engineeringFinancialService = engineeringFinancialService;
	}

	/**
	 * @param financialCollectDetailService the financialCollectDetailService to set
	 */
	public void setFinancialCollectDetailService(
			FinancialCollectDetailService financialCollectDetailService) {
		this.financialCollectDetailService = financialCollectDetailService;
	}

	/**
	 * @param financialPayDetailService the financialPayDetailService to set
	 */
	public void setFinancialPayDetailService(
			FinancialPayDetailService financialPayDetailService) {
		this.financialPayDetailService = financialPayDetailService;
	}

	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * @return the mobileEngineeringFinancialManageDto
	 */
	public MobileEngineeringFinancialManageDto getMobileEngineeringFinancialManageDto() {
		return mobileEngineeringFinancialManageDto;
	}

	/**
	 * @param mobileEngineeringFinancialManageDto the mobileEngineeringFinancialManageDto to set
	 */
	public void setMobileEngineeringFinancialManageDto(
			MobileEngineeringFinancialManageDto mobileEngineeringFinancialManageDto) {
		this.mobileEngineeringFinancialManageDto = mobileEngineeringFinancialManageDto;
	}

	/**
	 * @return the customerFinancialList
	 */
	public List<CustomerFinancial> getCustomerFinancialList() {
		return customerFinancialList;
	}

	/**
	 * @param customerFinancialList the customerFinancialList to set
	 */
	public void setCustomerFinancialList(
			List<CustomerFinancial> customerFinancialList) {
		this.customerFinancialList = customerFinancialList;
	}

}
