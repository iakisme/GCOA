package com.qylm.bean.dailyPlan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.PhaseId;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.qylm.bean.UserBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.dailyPlan.PlanArrangementManageDto;
import com.qylm.entity.PlanArrangement;
import com.qylm.faces.event.PlanScheduleEvent;
import com.qylm.service.PlanArrangementService;

/**
 * 日常计划信息管理
 * @author qylm
 */
@ManagedBean
@RequestScoped
public class PlanArrangementManageBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9200349285201120937L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(PlanArrangementManageBean.class);
	
	/**
	 * 保存日常计划管理画面需要保存的值
	 */
	private PlanArrangementManageDto planArrangementManageDto = new PlanArrangementManageDto();

	/**
	 * 页面列表显示
	 */
	private ScheduleModel planArrangementModel;
	
	/**
	 * 页面列表显示
	 */
	private ScheduleModel lazyEventModel;

	/**
	 * 修改传值
	 */
	private PlanScheduleEvent event = new PlanScheduleEvent();

	/**
	 * 日常计划管理bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 日常计划业务类
	 */
	@ManagedProperty(value="#{planArrangementService}")
	private PlanArrangementService planArrangementService;

	/**
	 * 查询出所有日常计划列表
	 * 
	 * @return 日常计划管理画面
	 */
	public String getAll() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_日常计划管理按钮】");
		planArrangementModel = new DefaultScheduleModel();
		// 初始化数据
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlanArrangement.class);
		detachedCriteria.add(Restrictions.eq(PlanArrangement.CREATER, userBean.getUser()));
		List<PlanArrangement> list = planArrangementService.getByDetachedCriteria(detachedCriteria);
		for (PlanArrangement planArrangement : list) {
			PlanScheduleEvent defaultScheduleEvent = new PlanScheduleEvent();
			defaultScheduleEvent.setPlanScheduleId(planArrangement.getId());
			defaultScheduleEvent.setStartDate(planArrangement.getBeginDate());
			defaultScheduleEvent.setEndDate(planArrangement.getEndDate());
			defaultScheduleEvent.setTitle(planArrangement.getTitle());
			defaultScheduleEvent.setDescription(planArrangement.getRemark());
			defaultScheduleEvent.setAllDay(planArrangement.isState());
			planArrangementModel.addEvent(defaultScheduleEvent);
		}
		return Navigation.PLAN_ARRANGEMENT_MANAGE;
	}
	
	/**
	 * 此方法绑定于日常计划管理画面的检索按钮 
	 * 实现功能：根据检索条件，检索出日常计划
	 */
	public void savePlanArrangement() {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_保存按钮】");
		PlanArrangement planArrangement = planArrangementManageDto.getPlanArrangement();
		if (planArrangement == null) {
			planArrangement = new PlanArrangement();
			planArrangement.setCreater(userBean.getUser());
			create(planArrangement);
			planArrangementService.saveEntity(planArrangement);
			planArrangementModel.addEvent(event);
			event.setId(planArrangement.getId().toString());
			Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
		} else {
			create(planArrangement);
			planArrangement.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
			planArrangementService.updateEntity(planArrangement);
			planArrangementModel.updateEvent(event);
			Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
		}
	}
	
	/**
	 * 删除本条记录
	 */
	public void deletePlanArrangement() {
		planArrangementModel.deleteEvent(event);
		PlanArrangement planArrangement = planArrangementManageDto.getPlanArrangement();
		if (planArrangement != null) {
			planArrangementService.deleteEntity(planArrangement);
			Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
		}
		planArrangementManageDto.setPlanArrangement(null);
	}
	
	/**
	 * 赋值
	 */
	private void create(PlanArrangement planArrangement) {
		String title = event.getTitle();
		Date startDate = event.getStartDate();
		Date endDate = event.getEndDate();
		String description = event.getDescription();
		boolean allDay = event.isAllDay();
		planArrangement.setTitle(title);
		planArrangement.setBeginDate(startDate);
		planArrangement.setEndDate(endDate);
		planArrangement.setRemark(description);
		planArrangement.setState(allDay);
		planArrangement.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
		planArrangementManageDto.setPlanArrangement(planArrangement);
	}
	
	/**
	 * 日期双击事件
	 * @param selectEvent
	 */
	public void onEventSelect(SelectEvent selectEvent) {
		event = (PlanScheduleEvent) selectEvent.getObject();
		planArrangementManageDto.setPlanArrangement(planArrangementService.getById(event.getPlanScheduleId()));
	}

	/**
	 * 空列表事件
	 * @param selectEvent
	 */
	public void onDateSelect(SelectEvent selectEvent) {
		event = new PlanScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}
	
	public void onEventMove(ScheduleEntryMoveEvent scheduleEntryMoveEvent) {
		Tool.sendLog(LOG, userBean, "按下【功能菜单_移动按钮】");
		event = (PlanScheduleEvent)scheduleEntryMoveEvent.getScheduleEvent();
		PhaseId phaseId = scheduleEntryMoveEvent.getPhaseId();
		PlanArrangement planArrangement = planArrangementService.getById(phaseId.getOrdinal());
		planArrangement.setBeginDate(event.getStartDate());
		planArrangement.setEndDate(event.getEndDate());
		planArrangement.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
		planArrangementService.updateEntity(planArrangement);
	}
	
	/**
	 * 获取提醒信息
	 * @return
	 */
	public List<PlanArrangement> getRemindPlanArrangement() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlanArrangement.class);
		detachedCriteria.add(Restrictions.eq(PlanArrangement.CREATER, userBean.getUser()));
		detachedCriteria.add(Restrictions.eq(PlanArrangement.STATE, true));
		Date nowyyyymmdd = DateUtil.getNowyyyymmdd();
		detachedCriteria.add(Restrictions.le(PlanArrangement.BEGIN_DATE, nowyyyymmdd));
		detachedCriteria.add(Restrictions.ge(PlanArrangement.END_DATE, nowyyyymmdd));
		return planArrangementService.getByDetachedCriteria(detachedCriteria);
	}

	/**
	 * @param planArrangementService the planArrangementService to set
	 */
	public void setPlanArrangementService(
			PlanArrangementService planArrangementService) {
		this.planArrangementService = planArrangementService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get planArrangementManageDto
	 * @return the planArrangementManageDto
	 */
	public PlanArrangementManageDto getPlanArrangementManageDto() {
		return planArrangementManageDto;
	}

	/**
	 * set planArrangementManageDto
	 * @param planArrangementManageDto the planArrangementManageDto to set
	 */
	public void setPlanArrangementManageDto(PlanArrangementManageDto planArrangementManageDto) {
		this.planArrangementManageDto = planArrangementManageDto;
	}

	/**
	 * @return the planArrangementModel
	 */
	public ScheduleModel getPlanArrangementModel() {
		return planArrangementModel;
	}

	/**
	 * @param planArrangementModel the planArrangementModel to set
	 */
	public void setPlanArrangementModel(ScheduleModel planArrangementModel) {
		this.planArrangementModel = planArrangementModel;
	}

	/**
	 * @return the event
	 */
	public PlanScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(PlanScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return the lazyEventModel
	 */
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	/**
	 * @param lazyEventModel the lazyEventModel to set
	 */
	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

}
