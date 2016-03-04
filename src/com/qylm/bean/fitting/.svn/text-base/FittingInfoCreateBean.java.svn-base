package com.qylm.bean.fitting;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.qylm.bean.FileUploadBean;
import com.qylm.bean.UserBean;
import com.qylm.bean.returner.Returner;
import com.qylm.bean.returner.fitting.FittingInfoCreateReturner;
import com.qylm.common.CreateBean;
import com.qylm.common.Message;
import com.qylm.common.Navigation;
import com.qylm.common.Tool;
import com.qylm.common.utils.DateUtil;
import com.qylm.dto.fitting.FittingInfoCreateDto;
import com.qylm.dxo.FittingInfoCreateDxo;
import com.qylm.entity.FileControl;
import com.qylm.entity.FileEntity;
import com.qylm.entity.FittingInfo;
import com.qylm.service.FileControlService;
import com.qylm.service.FittingInfoService;

/**
 * 配件登陆画面bean
 * @author 
 */
@ManagedBean
@RequestScoped
public class FittingInfoCreateBean implements CreateBean<FittingInfo>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5886231499796256531L;

	/**
	 * LOG
	 */
	private static final Log LOG = LogFactory.getLog(FittingInfoCreateBean.class);

	/**
	 * 存放配件登陆画面需要保存的值
	 */
	private FittingInfoCreateDto fittingInfoCreateDto = new FittingInfoCreateDto();
	
	/**
	 * 配件bean
	 */
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	/**
	 * 配件业务类
	 */
	@ManagedProperty(value="#{fittingInfoService}")
	private FittingInfoService fittingInfoService;
	
	/**
	 * 文件业务类
	 */
	@ManagedProperty(value = "#{fileControlService}")
	private FileControlService fileControlService;
	
	/**
	 * 此方法绑定于配件登陆画面的新建按钮 
	 * 实现功能：清空注入信息，并新建一个配件
	 * @return 配件登陆画面
	 */
	public String newFittingInfo() {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_新建按钮】");
		fittingInfoCreateDto.setCarName(null);
		fittingInfoCreateDto.setFittingBrand(null);
		fittingInfoCreateDto.setFittingName(null);
		fittingInfoCreateDto.setModel(null);
		fittingInfoCreateDto.setPrice(null);
		fittingInfoCreateDto.setMaintenanceDay(null);
		fittingInfoCreateDto.setMaintenanceCubic(null);
		fittingInfoCreateDto.setFileControlList(null);
		fittingInfoCreateDto.setCreater(null);
		fittingInfoCreateDto.setBelongingUser(null);
		fittingInfoCreateDto.setTransferFittingInfo(null);
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * 此方法绑定于配件登陆画面的返回按钮 
	 * 实现功能：返回共通方法
	 * @return 返回共通方法
	 */
	public String back() {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_返回按钮】");
		return fittingInfoCreateDto.getReturner().returnOnly();
	}

	/**
	 * 此方法绑定于配件登陆画面的保存按钮 
	 * 实现功能：根据transferFittingInfo对象判断当前操作时保存还是更新
	 * @return 画面不跳转
	 */
	public void saveFittingInfo() {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_保存按钮】");
		try {
			FittingInfo transferFittingInfo = fittingInfoCreateDto.getTransferFittingInfo();
			if (transferFittingInfo == null) {
				transferFittingInfo = new FittingInfo();
				fittingInfoCreateDto.setCreater(userBean.getUser());
				fittingInfoCreateDto.setBelongingUser(userBean.getUser().getBelongingUser());
				create(transferFittingInfo);
				transferFittingInfo.setCreateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingInfoService.saveEntity(transferFittingInfo);
				fittingInfoCreateDto.setTransferFittingInfo(transferFittingInfo);
				Tool.sendErrorMessage(Message.GENERAL_SAVESUCCESS);
			} else {
				create(transferFittingInfo);
				transferFittingInfo.setUpdateDate(DateUtil.getNowyyyymmddhhmmss());
				fittingInfoService.updateEntity(transferFittingInfo);
				Tool.sendErrorMessage(Message.GENERAL_UPDATESUCCESS);
			}
		} catch (DataIntegrityViolationException e) {
			Tool.sendErrorMessage("配件已存在，请确认！");
		}
	}
	
	/**
	 * 赋值
	 * @param transferFittingInfo
	 */
	private void create(FittingInfo transferFittingInfo) {
		FittingInfoCreateDxo.dtoToEntity(fittingInfoCreateDto, transferFittingInfo);
	}

	public String newCreate(Returner<?, ?, ?> returner) {
		fittingInfoCreateDto.setReturner(returner);
		return Navigation.FITTING_INFO_CREATE;
	}

	public String updateDetail(Returner<?, ?, ?> returner, FittingInfo fittingInfo) {
		fittingInfoCreateDto.setReturner(returner);
		FittingInfoCreateDxo.entityToDto(fittingInfo, fittingInfoCreateDto);
		fittingInfoCreateDto.setTransferFittingInfo(fittingInfo);
		fittingInfoCreateDto.setFileControlList(fileControlService.getFileControlList(fittingInfo));
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * 设置为默认
	 * @param event
	 */
	public void defaultFielControl(FileControl fileControl) {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_默认显示按钮】");
		List<FileControl> fileControlList = fittingInfoCreateDto.getFileControlList();
		for (FileControl control : fileControlList) {
			control.setDefaultState(false);
		}
		fileControl.setDefaultState(true);
		fileControlService.updateEntityAll(fileControlList);
	}
	
	/**
	 * 文件删除
	 * @param event
	 */
	public void deleteFileControl(FileControl fileControl) {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_文件删除按钮】");
		fittingInfoCreateDto.getFileControlList().remove(fileControl);
		fileControlService.deleteEntity(fileControl);
		Tool.sendErrorMessage(Message.GENERAL_DELETESUCCESS);
	}
	
	/**
	 * 上传文件
	 * @return
	 */
	public String uploadFile() {
		Tool.sendLog(LOG, userBean, "【配件登陆画面_上传文件按钮】");
		FittingInfoCreateReturner fittingInfoCreateReturner = new FittingInfoCreateReturner(fittingInfoCreateDto);
		return Tool.getBackBean(FileUploadBean.class).infoFileUpload(fittingInfoCreateReturner, new FileEntity(fittingInfoCreateDto.getTransferFittingInfo()));
	}
	
	/**
	 * 返回到本画面
	 * @return
	 */
	public String returner() {
		return Navigation.FITTING_INFO_CREATE;
	}
	
	/**
	 * @param fileControlService the fileControlService to set
	 */
	public void setFileControlService(FileControlService fileControlService) {
		this.fileControlService = fileControlService;
	}

	/**
	 * set fittingInfoService
	 * @param fittingInfoService the fittingInfoService to set
	 */
	public void setFittingInfoService(FittingInfoService fittingInfoService) {
		this.fittingInfoService = fittingInfoService;
	}

	/**
	 * set userBean
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * get fittingInfoCreateDto
	 * @return the fittingInfoCreateDto
	 */
	public FittingInfoCreateDto getFittingInfoCreateDto() {
		return fittingInfoCreateDto;
	}

	/**
	 * set fittingInfoCreateDto
	 * @param fittingInfoCreateDto the fittingInfoCreateDto to set
	 */
	public void setFittingInfoCreateDto(FittingInfoCreateDto fittingInfoCreateDto) {
		this.fittingInfoCreateDto = fittingInfoCreateDto;
	}

}
