package com.qylm.bean.procedure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.dto.procedure.ProcedureSetCreateDto;
import com.qylm.dxo.ProcedureSetCreateDxo;
import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;
import com.qylm.entity.User;
import com.qylm.service.ProcedureSetDetailService;
import com.qylm.service.ProcedureSetService;
import com.qylm.service.UserService;

/**
 * 采购单审核设定登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class ProcedureSetCreateBean implements CreateBean<ProcedureSet>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(ProcedureSetCreateBean.class);

	/**
	 * 存放采购单审核设定登陆画面需要保存的值
	 */
	private ProcedureSetCreateDto procedureSetCreateDto = new ProcedureSetCreateDto();
	
	/**
	 * 用户列表
	 */
	private List<User> userList;
	
	/**
	 * 选中用户列表
	 */
	private List<User> users;
	
	/**
	 * 采购单审核设定bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 采购单审核设定业务类
	 */
	@ManagedProperty(value="#{procedureSetService}")
	private ProcedureSetService procedureSetService;
	
	/**
	 * 采购单审核设定详细业务类
	 */
	@ManagedProperty(value = "#{procedureSetDetailService}")
	private ProcedureSetDetailService procedureSetDetailService;
	
	/**
	 * 用户业务类
	 */
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	/**
	 * 此方法绑定于采购单审核设定登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个采购单审核设定
	 * @return 采购单审核设定登陆画面
	 */
	public String newProcedureSet() {
		Tool.sendLog(LOG, userBean, "【采购单审核设定登陆画面_新建按钮】");
		clear();
		return Navigation.PROCEDURE_SET_CREATE;
	}
	
	/**
	 * 此方法绑定于采购单审核设定登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【采购单审核设定登陆画面_返回按钮】");
		return procedureSetCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于采购单审核设定登陆画面的保存按钮 
	 * 实现功能：根据transferProcedureSet对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveProcedureSet() {
		Tool.sendLog(LOG, userBean, "【采购单审核设定登陆画面_保存按钮】");
		try {
			if (StringUtil.isUnSelected(procedureSetCreateDto.getApplyNumber())) {
				Tool.sendErrorMessage("采购单类型必须填写！");
				return;
			}
			ProcedureSet transferProcedureSet = procedureSetCreateDto.getTransferProcedureSet();
			List<ProcedureSetDetail> procedureSetDetailList = new ArrayList<ProcedureSetDetail>();
			if (transferProcedureSet == null) {
				transferProcedureSet = new ProcedureSet();
				procedureSetCreateDto.setCreater(userBean.getUser());
				procedureSetCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferProcedureSet, procedureSetDetailList);
				transferProcedureSet.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				procedureSetService.saveProcedureSet(transferProcedureSet, procedureSetDetailList);
				procedureSetCreateDto.setTransferProcedureSet(transferProcedureSet);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferProcedureSet, procedureSetDetailList);
				transferProcedureSet.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				procedureSetService.updateProcedureSet(transferProcedureSet, procedureSetDetailList);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("采购单审核设定已存在，请确认！");
		}
	}
	
	/**
	 * 赋值
	 * @param transferProcedureSet
	 */
	private void create(ProcedureSet transferProcedureSet, List<ProcedureSetDetail> procedureSetDetailList) {
		ProcedureSetCreateDxo.dtoToEntity(procedureSetCreateDto, transferProcedureSet);
		if (users != null && !users.isEmpty()) {
			int size = users.size();
			User user;
			ProcedureSetDetail procedureSetDetail;
			for (int i = 0; i < size; i++) {
				user = users.get(i);
				procedureSetDetail = new ProcedureSetDetail();
				procedureSetDetail.setAuditor(user);
				procedureSetDetail.setSequence(i+1);
				procedureSetDetail.setProcedureSet(transferProcedureSet);
				procedureSetDetailList.add(procedureSetDetail);
			}
		}
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		procedureSetCreateDto.setReturner(returner);
		return Navigation.PROCEDURE_SET_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, ProcedureSet procedureSet) {
		procedureSetCreateDto.setReturner(returner);
		ProcedureSetCreateDxo.entityToDto(procedureSet, procedureSetCreateDto);
		procedureSetCreateDto.setTransferProcedureSet(procedureSet);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProcedureSetDetail.class);
		detachedCriteria.createAlias(ProcedureSetDetail.AUDITOR, ProcedureSetDetail.AUDITOR, JoinType.LEFT_OUTER_JOIN);
		detachedCriteria.add(Restrictions.eq(ProcedureSetDetail.PROCEDURE_SET, procedureSet));
		detachedCriteria.addOrder(Order.asc(ProcedureSetDetail.SEQUENCE));
		List<ProcedureSetDetail> procedureSetDetailList = procedureSetDetailService.getByDetachedCriteria(detachedCriteria);
		if (!procedureSetDetailList.isEmpty()) {
			users = new ArrayList<User>();
			for (ProcedureSetDetail procedureSetDetail : procedureSetDetailList) {
				users.add(procedureSetDetail.getAuditor());
			}
		}
		return Navigation.PROCEDURE_SET_CREATE;
	}
	
	/**
	 * 此方法绑定与采购单审核设定登录画面的选择用户按钮
	 * 实现功能：查询出所有后台用户
	 */
	public void findUser() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		detachedCriteria.add(Restrictions.eq(User.STOP, false));
		if (users != null && !users.isEmpty()) {
			List<Integer> userIdList = new ArrayList<Integer>();
			for (User user : users) {
				userIdList.add(user.getId());
			}
			detachedCriteria.add(Restrictions.not(Restrictions.in(User.BASE_ID, userIdList)));
		}
		userList = userService.getByDetachedCriteria(detachedCriteria);
	}
	
	/**
	 * 此方法绑定与采购单审核设定登录画面的选择用户画面的确认按钮
	 * 实现功能：获取到用户信息
	 */
	public void queryUser(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		users.add(user);
	}
	
	/**
	 * 获取到选择的用户列表
	 * @return 按顺序显示出审核人员列表
	 */
	public String getProcedUsers() {
		if (users == null || users.isEmpty()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int size = users.size();
		User user;
		for (int i = 0; i < size; i++) {
			user = users.get(i);
			sb.append(user.getUserName());
			if (size - 1 > i) {
				sb.append("-------");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 此方法绑定于采购单审核设定登录画面的确认按钮
	 * 实现功能：如果为锁定将其锁定，如果锁定将其设置为未锁定
	 * @return
	 */
	public void query() {
		ProcedureSet transferProcedureSet = procedureSetCreateDto.getTransferProcedureSet();
		transferProcedureSet.setState(procedureSetCreateDto.isState() ? false : true);
		procedureSetService.updateEntity(transferProcedureSet);
		procedureSetCreateDto.setState(transferProcedureSet.isState());
		Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
	}
	
	/**
	 * 此方法绑定于采购单审核设定登录画面的下拉框选择按钮
	 * 实现功能：根据选择到的下拉框列表值，来获取到与其相对于的审核设定信息
	 */
	public void selectProcedureSet() {
		String applyNumber = procedureSetCreateDto.getApplyNumber();
		if (StringUtil.isUnSelected(applyNumber)) {
			clear();
		} else {
			// 查询出对应的审核信息
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProcedureSet.class);
			detachedCriteria.add(Restrictions.eq(ProcedureSet.APPLY_NUMBER, applyNumber));
			List<ProcedureSet> procedureSetList = procedureSetService.getByDetachedCriteria(detachedCriteria);
			if (procedureSetList.isEmpty()) {
				clear();
				procedureSetCreateDto.setApplyNumber(applyNumber);
			} else {
				ProcedureSet procedureSet = procedureSetList.get(0);
				detachedCriteria = DetachedCriteria.forClass(ProcedureSetDetail.class);
				detachedCriteria.createAlias(ProcedureSetDetail.AUDITOR, ProcedureSetDetail.AUDITOR, JoinType.LEFT_OUTER_JOIN);
				detachedCriteria.add(Restrictions.eq(ProcedureSetDetail.PROCEDURE_SET, procedureSet));
				detachedCriteria.addOrder(Order.asc(ProcedureSetDetail.SEQUENCE));
				List<ProcedureSetDetail> procedureSetDetailList = procedureSetDetailService.getByDetachedCriteria(detachedCriteria);
				if (!procedureSetDetailList.isEmpty()) {
					users = new ArrayList<User>();
					for (ProcedureSetDetail procedureSetDetail : procedureSetDetailList) {
						users.add(procedureSetDetail.getAuditor());
					}
				}
				procedureSetCreateDto.setTransferProcedureSet(procedureSet);
				procedureSetCreateDto.setState(procedureSet.isState());
				procedureSetCreateDto.setApplyNumber(procedureSet.getApplyNumber());
			}
		}
	}
	
	/**
	 * 清除数据
	 */
	private void clear() {
		procedureSetCreateDto.setApplyNumber(null);
		procedureSetCreateDto.setState(false);
		procedureSetCreateDto.setTransferProcedureSet(null);
		userList = null;
		users = null;
	}
	
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param procedureSetDetailService the procedureSetDetailService to set
	 */
	public void setProcedureSetDetailService(
			ProcedureSetDetailService procedureSetDetailService) {
		this.procedureSetDetailService = procedureSetDetailService;
	}

	/**
	 * set procedureSetService
	 * @param procedureSetService the procedureSetService to set
	 */
	public void setProcedureSetService(ProcedureSetService procedureSetService) {
		this.procedureSetService = procedureSetService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get procedureSetCreateDto
	 * @return the procedureSetCreateDto
	 */
	public ProcedureSetCreateDto getProcedureSetCreateDto() {
		return procedureSetCreateDto;
	}

	/**
	 * set procedureSetCreateDto
	 * @param procedureSetCreateDto the procedureSetCreateDto to set
	 */
	public void setProcedureSetCreateDto(ProcedureSetCreateDto procedureSetCreateDto) {
		this.procedureSetCreateDto = procedureSetCreateDto;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
