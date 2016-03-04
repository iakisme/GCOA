package com.qylm.bean.procedure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.qylm.bean.BasePagingBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.procedure.ProcedureSetManageReturner;
import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.dto.procedure.ProcedureSetManageDto;
import com.qylm.entity.ProcedureSet;
import com.qylm.entity.ProcedureSetDetail;
import com.qylm.entity.User;
import com.qylm.service.ProcedureSetDetailService;
import com.qylm.service.ProcedureSetService;

/**
 * 采购单审核设定管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class ProcedureSetManageBean extends BasePagingBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6422477489121065360L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(ProcedureSetManageBean.class);
	
	/**
	 * 保存采购单审核设定管理画面需要保存的值
	 */
	private ProcedureSetManageDto procedureSetManageDto = new ProcedureSetManageDto();

	/**
	 * 采购单审核设定列表（检索结果）
	 */
	private List<ProcedureSet> procedureSetList;
	
	/**
	 * 删除复选框选择的值
	 */
	private ProcedureSet[] selectedModels;

	/**
	 * 采购单审核设定管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	/**
	 * 采购单审核设定管理业务类
	 */
	@ManagedProperty(value="#{procedureSetService}")
	private ProcedureSetService procedureSetService;
	
	/**
	 * 采购单审核设定详细业务类
	 */
	@ManagedProperty(value="#{procedureSetDetailService}")
	private ProcedureSetDetailService procedureSetDetailService;
	
	/**
	 * 查询出所有采购单审核设定列表
	 * 
	 * @return 采购单审核设定管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_采购单审核设定管理按钮】");
		fetchData(0, true);
		return Navigation.PROCEDURE_SET_MANAGE;
	}

	/**
	 * 此方法绑定于项目管理画面的新建按钮 
	 * 实现功能：跳转至采购单审核设定登陆画面，新建一家采购单审核设定
	 * @return 采购单审核设定登陆画面
	 */
	public String newProcedureSet() {
		Tool.sendLog(LOG, userBean, "按下【采购单审核设定管理画面_新建按钮】");
		return Tool.getBackBean(ProcedureSetCreateBean.class).newCreate(new ProcedureSetManageReturner(procedureSetManageDto, currentPage));
	}

	/**
	 * 此方法绑定于项目管理画面的修改按钮 
	 * 实现功能：根据修改的对象，跳转至采购单审核设定登陆画面
	 * @return 采购单审核设定登陆画面
	 */
	public String updateProcedureSet(ProcedureSet transferProcedureSet) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核设定管理画面_修改按钮】");
		return Tool.getBackBean(ProcedureSetCreateBean.class).updateDetail(new ProcedureSetManageReturner(procedureSetManageDto, currentPage), transferProcedureSet);
	}
	
	/**
	 * 此方法绑定于采购单审核设定管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出采购单审核设定
	 * @return 画面不跳转
	 */
	public void selectProcedureSet() {
		Tool.sendLog(LOG, userBean, "按下【采购单审核设定管理画面_检索按钮】");
		fetchData(0, true);
	}
	
	/**
	 * 绑定于采购单审核设定管理画面的全选删除按钮
	 * @param event
	 */
	public void deleteMul(ActionEvent event) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核设定管理画面_批量删除按钮】");
		if (selectedModels != null) {
			List<ProcedureSet> asList = Arrays.asList(selectedModels);
			procedureSetList.removeAll(asList);
			procedureSetService.deleteEntityAll(asList);
			removeData(1, procedureSetList.isEmpty());
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
	}

	/**
	 * 此方法绑定于采购单审核设定管理画面的删除按钮 
	 * 实现功能：先移除集合内的数据，在移除数据库内的数据
	 * @return 画面不跳转
	 */
	public void deleteProcedureSet(ProcedureSet transferProcedureSet) {
		Tool.sendLog(LOG, userBean, "按下【采购单审核设定管理画面的_删除按钮】");
		procedureSetList.remove(transferProcedureSet);
		procedureSetService.deleteEntity(transferProcedureSet);
		removeData(1, procedureSetList.isEmpty());
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}

	@Override
	protected void fetchData(int start, boolean needInit) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProcedureSet.class);
		MothedUtil.getBelongingUser(detachedCriteria, userBean.getUser());
		procedureSetList = procedureSetService.getByDetachedCriteria(detachedCriteria, start, onePageCount);
		if (needInit) {
			init(procedureSetService.getRowCount(detachedCriteria));
		}
		if (!procedureSetList.isEmpty()) {
			detachedCriteria = DetachedCriteria.forClass(ProcedureSetDetail.class);
			detachedCriteria.createAlias(ProcedureSetDetail.AUDITOR, ProcedureSetDetail.AUDITOR, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.createAlias(ProcedureSetDetail.PROCEDURE_SET, ProcedureSetDetail.PROCEDURE_SET, JoinType.LEFT_OUTER_JOIN);
			detachedCriteria.add(Restrictions.in(ProcedureSetDetail.PROCEDURE_SET, procedureSetList));
			detachedCriteria.addOrder(Order.asc(ProcedureSetDetail.SEQUENCE));
			List<ProcedureSetDetail> procedureSetDetailList = procedureSetDetailService.getByDetachedCriteria(detachedCriteria);
			if (!procedureSetDetailList.isEmpty()) {
				List<User> userList;
				int size = procedureSetDetailList.size();
				ProcedureSetDetail procedureSetDetail;
				User user;
				for (ProcedureSet procedureSet : procedureSetList) {
					userList = new ArrayList<User>();
					for (int i = 0; i < size; i++) {
						procedureSetDetail = procedureSetDetailList.get(i);
						if (procedureSet.getId().equals(procedureSetDetail.getProcedureSet().getId())) {
							userList.add(procedureSetDetail.getAuditor());
						}
					}
					StringBuilder sb = new StringBuilder();
					int userListSize = userList.size();
					for (int i = 0; i < userListSize; i++) {
						user = userList.get(i);
						sb.append(user.getUserName());
						if (size - 1 > i) {
							sb.append("-------");
						}
					}
					procedureSet.setShowName(sb.toString());
				}
			}
		}
	}

	/**
	 * 需要返回本画面时都调用此共通方法
	 * @param currentPage 当前页数
	 * @return 个人采购单审核设定管理画面
	 */
	public String back(int currentPage) {
		reflushCurrentPage(currentPage);
		return Navigation.PROCEDURE_SET_MANAGE;
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
	 * get procedureSetManageDto
	 * @return the procedureSetManageDto
	 */
	public ProcedureSetManageDto getProcedureSetManageDto() {
		return procedureSetManageDto;
	}

	/**
	 * set procedureSetManageDto
	 * @param procedureSetManageDto the procedureSetManageDto to set
	 */
	public void setProcedureSetManageDto(ProcedureSetManageDto procedureSetManageDto) {
		this.procedureSetManageDto = procedureSetManageDto;
	}

	/**
	 * get procedureSetList
	 * @return the procedureSetList
	 */
	public List<ProcedureSet> getProcedureSetList() {
		return procedureSetList;
	}

	/**
	 * set procedureSetList
	 * @param procedureSetList the procedureSetList to set
	 */
	public void setProcedureSetList(List<ProcedureSet> procedureSetList) {
		this.procedureSetList = procedureSetList;
	}

	/**
	 * get selectedModels
	 * @return the selectedModels
	 */
	public ProcedureSet[] getSelectedModels() {
		return selectedModels;
	}

	/**
	 * set selectedModels
	 * @param selectedModels the selectedModels to set
	 */
	public void setSelectedModels(ProcedureSet[] selectedModels) {
		this.selectedModels = selectedModels;
	}

}
