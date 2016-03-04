package com.qylm.common.expand.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

import com.qylm.common.Message;
import com.qylm.common.MothedUtil;
import com.qylm.common.Tool;
import com.qylm.common.expand.FileExportExtService;
import com.qylm.common.utils.BigDecimalUtil;
import com.qylm.common.utils.DateUtil;
import com.qylm.common.utils.ExcelUtil;
import com.qylm.common.utils.StringUtil;
import com.qylm.constants.Constants;
import com.qylm.dto.export.EngineeringProjectExportDto;
import com.qylm.dto.export.ExportDataDto;
import com.qylm.entity.Customer;
import com.qylm.entity.Employee;
import com.qylm.entity.EngineeringProject;
import com.qylm.entity.EngineeringProjectDetail;
import com.qylm.entity.ProjectEmployeeDetail;
import com.qylm.entity.VehicleInfo;
import com.qylm.exception.ExcelUpfileRowNumOverSizeException;
import com.qylm.service.CustomerService;
import com.qylm.service.EmployeeService;
import com.qylm.service.EngineeringProjectDetailService;
import com.qylm.service.EngineeringProjectService;
import com.qylm.service.ProjectEmployeeDetailService;
import com.qylm.service.VehicleInfoService;
import com.qylm.spring.application.ApplicationContextHelper;

/**
 * 工程项目管理导入接口实现类
 * @author Administrator
 */
public class EngineeringProjectFileExportServiceImpl implements FileExportExtService<EngineeringProjectExportDto>, Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8487910824229817888L;
	
	/**
	 * 每页条数
	 */
	public final static Integer PAGE_SIZE = 50;
	
	public ExportDataDto<EngineeringProjectExportDto> validatorExcel(UploadedFile uploadedFile) {
		ExportDataDto<EngineeringProjectExportDto> exportDataDto = new ExportDataDto<EngineeringProjectExportDto>();
		exportDataDto.setExportDtoList(new ArrayList<EngineeringProjectExportDto>());
		exportDataDto.setErrorList(new ArrayList<String>());
		upload(uploadedFile, exportDataDto);
		return exportDataDto;
	}
	
	public ExportDataDto<EngineeringProjectExportDto> validatorExportDto(
			ExportDataDto<EngineeringProjectExportDto> exportDataDto) {
		return exportDataDto;
	}

	public void saveExportData(List<EngineeringProjectExportDto> baseExportDtoList) {
		if (baseExportDtoList != null && !baseExportDtoList.isEmpty()) {
			// 获取到service
			EngineeringProjectService engineeringProjectService = ApplicationContextHelper.getBean("engineeringProjectService");
			EngineeringProjectDetailService engineeringProjectDetailService = ApplicationContextHelper.getBean("engineeringProjectDetailService");
			ProjectEmployeeDetailService projectEmployeeDetailService = ApplicationContextHelper.getBean("projectEmployeeDetailService");
			CustomerService customerService = ApplicationContextHelper.getBean("customerService");
			int size = baseExportDtoList.size();
			// 客户
			List<Customer> customerList = new ArrayList<Customer>(size);
			// 车辆编号
			List<VehicleInfo> vehicleInfoList = new ArrayList<VehicleInfo>(size);
			// 工作日期
			List<Date> dateList = new ArrayList<Date>(size);
			// 泵送部位
			List<String> pumpPartsList = new ArrayList<String>(size);
			// 工地名称
			List<String> workAddressList = new ArrayList<String>(size);
			for (EngineeringProjectExportDto baseExportDto : baseExportDtoList) {
				customerList.add(baseExportDto.getCustomer());
				vehicleInfoList.add(baseExportDto.getVehicleInfo());
				dateList.add(baseExportDto.getDate());
				pumpPartsList.add(baseExportDto.getPumpParts());
				workAddressList.add(baseExportDto.getWorkAddress());
			}
			// 进行封装
			List<EngineeringProject> engineeringProjectList = new ArrayList<EngineeringProject>();
			createEngineeringProject(engineeringProjectList, baseExportDtoList);
			// 查询出已存在数据信息
			// 从工程项目详细内进行查询
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EngineeringProjectDetail.class);
//			detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT, EngineeringProjectDetail.ENGINEERING_PROJECT, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.createAlias(EngineeringProjectDetail.ENGINEERING_PROJECT_CUSTOMER, EngineeringProjectDetail.ENGINEERING_PROJECT_CUSTOMER, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.createAlias(EngineeringProjectDetail.VEHICLE_INFOL, EngineeringProjectDetail.VEHICLE_INFOL, JoinType.LEFT_OUTER_JOIN);
//			detachedCriteria.add(Restrictions.in(EngineeringProjectDetail.ENGINEERING_PROJECT_CUSTOMER, customerList));
//			detachedCriteria.add(Restrictions.in(EngineeringProjectDetail.VEHICLE_INFOL, vehicleInfoList));
//			detachedCriteria.add(Restrictions.in(EngineeringProjectDetail.ENGINEERING_PROJECT_BEGIN_DATE, dateList));
//			detachedCriteria.add(Restrictions.in(EngineeringProjectDetail.PUMP_PARTS, pumpPartsList));
//			detachedCriteria.add(Restrictions.in(EngineeringProjectDetail.WORK_ADDRESS, workAddressList));
//			List<EngineeringProjectDetail> oldEngineeringProjectDateilList = engineeringProjectDetailService.getByDetachedCriteria(detachedCriteria);
//			if (!oldEngineeringProjectDateilList.isEmpty()) {
//				Tool.sendErrorMessage("此上传功能只用于第一次上传！");
//			} else {
			
				// 解析保存数据信息
				List<EngineeringProjectDetail> saveEngineeringProjectDetailList = new ArrayList<EngineeringProjectDetail>();
				List<ProjectEmployeeDetail> projectEmployeeDetailList = new ArrayList<ProjectEmployeeDetail>();
				for (EngineeringProject engineeringProject : engineeringProjectList) {
					List<EngineeringProjectDetail> engineeringProjectDetailList = engineeringProject.getEngineeringProjectDetailList();
					for (EngineeringProjectDetail engineeringProjectDetail : engineeringProjectDetailList) {
						projectEmployeeDetailList.addAll(engineeringProjectDetail.getProjectEmployeeDetailList());
					}
					saveEngineeringProjectDetailList.addAll(engineeringProjectDetailList);
				}
				if (!customerList.isEmpty()) {
					Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
					for (Customer customer : customerList) {
						customer.setSerialNumber(StringUtil.toPinYin(customer.getName()));
						customer.setCreateDate(nowyyyymmddhhmmss);
					}
					customerService.saveOrUpdateAll(customerList);
				}
				engineeringProjectService.saveEntityAll(engineeringProjectList);
				if (!saveEngineeringProjectDetailList.isEmpty()) {
					engineeringProjectDetailService.saveEntityAll(saveEngineeringProjectDetailList);
				}
				if (!projectEmployeeDetailList.isEmpty()) {
					projectEmployeeDetailService.saveEntityAll(projectEmployeeDetailList);
				}
				Tool.sendErrorMessage("新增加数据：" + engineeringProjectList.size());
//				
//			}
			
		}
	}
	
	/**
	 * 封装
	 * @param engineeringProjectList
	 * @param baseExportDtoList
	 */
	private void createEngineeringProject(List<EngineeringProject> engineeringProjectList, List<EngineeringProjectExportDto> baseExportDtoList) {
		// 查询到员工
		EmployeeService employeeService = ApplicationContextHelper.getBean("employeeService");
		List<Employee> employeeList = employeeService.getAll();
		for (EngineeringProjectExportDto baseExportDto : baseExportDtoList) {
			EngineeringProject engineeringProject = new EngineeringProject();
			engineeringProject.setCustomer(baseExportDto.getCustomer());
			Date nowyyyymmddhhmmss = DateUtil.getNowyyyymmddhhmmss();
			engineeringProject.setCreateDate(nowyyyymmddhhmmss);
			engineeringProject.setBeginDate(baseExportDto.getDate());
			engineeringProject.setWorkAddress(baseExportDto.getWorkAddress());
			engineeringProject.setActualVolume(baseExportDto.getSchedule());
			engineeringProject.setSchedule(baseExportDto.getSchedule());
			engineeringProject.setPumpPrice(baseExportDto.getPumpPrice());
			// 如果预算方量和单价金额都不为空就自动计算出预算金额
			if (BigDecimalUtil.isNotNullOrZero(engineeringProject.getActualVolume()) && BigDecimalUtil.isNotNullOrZero(engineeringProject.getPumpPrice())) {
				engineeringProject.setMoney(BigDecimalUtil.multiply(engineeringProject.getActualVolume(), engineeringProject.getPumpPrice()));
			}
			engineeringProject.setType(EngineeringProject.TYPE_2);
			engineeringProjectList.add(engineeringProject);
			// 获取到工程安排详细
			List<EngineeringProjectDetail> engineeringProjectDetailList = new ArrayList<EngineeringProjectDetail>();
			engineeringProject.setEngineeringProjectDetailList(engineeringProjectDetailList);
			EngineeringProjectDetail engineeringProjectDetail = new EngineeringProjectDetail();
			engineeringProjectDetail.setEngineeringProject(engineeringProject);
			engineeringProjectDetail.setVehicleInfo(baseExportDto.getVehicleInfo());
			engineeringProjectDetail.setVehicleNumber(baseExportDto.getVehicleNumber());
			engineeringProjectDetail.setPumpParts(baseExportDto.getPumpParts());
			engineeringProjectDetail.setWorkVolume(baseExportDto.getSchedule());
			engineeringProjectDetail.setSchedule(baseExportDto.getSchedule());
			engineeringProjectDetail.setPumpPrice(baseExportDto.getPumpPrice());
			engineeringProjectDetail.setStartPumpDate(baseExportDto.getStartPumpDate());
			engineeringProjectDetail.setEndPumpDate(baseExportDto.getEndPumpDate());
			engineeringProjectDetail.setGasVolume(baseExportDto.getGasVolume());
			engineeringProjectDetail.setGasPrice(baseExportDto.getGasPrice());
			engineeringProjectDetail.setRemark(baseExportDto.getRemark());
			engineeringProjectDetail.setCreateDate(nowyyyymmddhhmmss);
			engineeringProjectDetailList.add(engineeringProjectDetail);
			// 获取到开机人员详细
			List<ProjectEmployeeDetail> projectEmployeeDetailList = new ArrayList<ProjectEmployeeDetail>();
			engineeringProjectDetail.setProjectEmployeeDetailList(projectEmployeeDetailList);
			for (String employee : baseExportDto.getEmployeeList()) {
				ProjectEmployeeDetail projectEmployeeDetail = new ProjectEmployeeDetail();
				projectEmployeeDetail.setEmployee(MothedUtil.getEmployee(employeeList, employee));
				projectEmployeeDetail.setName(employee);
				projectEmployeeDetail.setEngineeringProjectDetail(engineeringProjectDetail);
				projectEmployeeDetail.setCreateDate(nowyyyymmddhhmmss);
				projectEmployeeDetailList.add(projectEmployeeDetail);
			}
		}
	}
	
	/**
	 * 人员信息上传
	 * @param uploadedFile
	 */
	private void upload(UploadedFile uploadedFile, ExportDataDto<EngineeringProjectExportDto> exportDataDto) {
		ExcelUtil excelUtil = new ExcelUtil();
		int columNum = 15;// 导入订单的列数
		int row = 2;// 从第几行开始读取
		try {
			excelUtil.initReader(uploadedFile.getInputstream(), row);// 从第三行开始读取
			excelUtil.checkSize();
			Object[] output;
			output = excelUtil.readLine(columNum);
			// 查询到所有客户
			CustomerService customerService = ApplicationContextHelper.getBean("customerService");
			// 查询到车辆信息
			VehicleInfoService vehicleInfoService = ApplicationContextHelper.getBean("vehicleInfoService");
			List<Customer> customerList = customerService.getAll();
			List<VehicleInfo> vehicleInfoList = vehicleInfoService.getAll();
			String year = DateUtil.formatDate(DateUtil.getNowyyyymmdd(), Constants.YYYY);
			while (output != null) {
				create(exportDataDto, customerList, vehicleInfoList, output, row+1, year);
				output = excelUtil.readLine(columNum);// 读取下一行
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			exportDataDto.getErrorList().add(Message.GENERAL_FILE_FETCH_ERROR);
		} catch (ExcelUpfileRowNumOverSizeException e) {
			e.printStackTrace();
			exportDataDto.getErrorList().add(Message.GENERAL_FILE_OVERSTEP_LENGTH);
		} catch (Exception e){
			e.printStackTrace();
			exportDataDto.getErrorList().add(Message.GENERAL_FILE_FETCH_ERROR);
		}
	}
	
	/**
	 * 赋值
	 * 产品名称	产品型号 产品版本 产品尺寸 产品分类	计量单位 产品规格 生产工艺 拼版 平方 产品重量 产品材质 单价	备注
	 * @param exportDataDto
	 * @param output
	 * @param row 当前行数
	 */
	private void create(ExportDataDto<EngineeringProjectExportDto> exportDataDto, List<Customer> customerList, 
			List<VehicleInfo> vehicleInfoList, Object[] output, int row, String year) {
		EngineeringProjectExportDto materialDocumentEngineerExportDto = new EngineeringProjectExportDto();
		List<String> errorList = exportDataDto.getErrorList();
		materialDocumentEngineerExportDto.setRow(row);
		List<EngineeringProjectExportDto> materialDocumentEngineerExportDtoDtoList = exportDataDto.getExportDtoList();
		String value;
		if (!StringUtil.isObjBlank(output[0])) {
			value = StringUtil.trimAll(output[0].toString());
			if (value.length() < 10) {
				value = value.replace(Constants.DOT, Constants.HORIZONTAL_LINE);
				Date objDate = DateUtil.toObjDate(year + Constants.HORIZONTAL_LINE + value, Constants.YYYY_MM_DD);
				materialDocumentEngineerExportDto.setDate(objDate);
			} else {
				Date objDate = DateUtil.toObjDate(value + Constants.HORIZONTAL_LINE + value, Constants.YYYY_MM_DD);
				materialDocumentEngineerExportDto.setDate(objDate);
			}
		} else {
			errorList.add("第" + row + "行，日期不能为空！");
		}
		if (!StringUtil.isObjBlank(output[1])) {
			value = StringUtil.trimAll(output[1].toString());
			VehicleInfo vehicleInfo = MothedUtil.getVehicleInfo(vehicleInfoList, value);
			if (vehicleInfo == null) {
				materialDocumentEngineerExportDto.setVehicleNumber(value);
			} else {
				materialDocumentEngineerExportDto.setVehicleInfo(vehicleInfo);
			}
		} else {
			errorList.add("第" + row + "行，车辆编号不能为空！");
		}
		if (!StringUtil.isObjBlank(output[2])) {
			value = StringUtil.trimAll(output[2].toString());
			Customer customer = MothedUtil.getCustomer(customerList, value);
			if (customer == null) {
				customer = new Customer();
				customer.setName(value);
				customerList.add(customer);
			}
			materialDocumentEngineerExportDto.setCustomer(customer);
		} else {
			errorList.add("第" + row + "行，工作地址不能为空！");
		}
		if (!StringUtil.isObjBlank(output[3])) {
			value = StringUtil.trimAll(output[3].toString());
			materialDocumentEngineerExportDto.setWorkAddress(value);
		}
		// 泵送部位
		if (!StringUtil.isObjBlank(output[4])) {
			value = StringUtil.trimAll(output[4].toString());
			materialDocumentEngineerExportDto.setPumpParts(value);
		}
		// 实际方量
		if (!StringUtil.isObjBlank(output[5])) {
			value = StringUtil.trimAll(output[5].toString());
			materialDocumentEngineerExportDto.setSchedule(BigDecimalUtil.toBigDecimal(value));
		}
		// 开始时间
		if (!StringUtil.isObjBlank(output[6])) {
			value = StringUtil.trim(output[6].toString());
			if (value.length() < 10) {
				String yyyyMMdd = DateUtil.formatDate(materialDocumentEngineerExportDto.getDate(), Constants.YYYY_MM_DD) + Constants.BLANK + value;
				materialDocumentEngineerExportDto.setStartPumpDate(DateUtil.toObjDate(yyyyMMdd, Constants.YYYY_MM_DD_HH_MM_SS));
			} else {
				materialDocumentEngineerExportDto.setStartPumpDate(DateUtil.toObjDate(value, Constants.YYYY_MM_DD_HH_MM_SS));
			}
		} else {
			// 按日期的早上六点定
			String date = DateUtil.formatDate(materialDocumentEngineerExportDto.getDate(), Constants.YYYY_MM_DD) + Constants.BLANK + "06:00:00";
			materialDocumentEngineerExportDto.setStartPumpDate(DateUtil.toObjDate(date, Constants.YYYY_MM_DD_HH_MM_SS));
		}
		// 结束时间
		if (!StringUtil.isObjBlank(output[7])) {
			value = StringUtil.trim(output[7].toString());
			if (value.length() < 10) {
				String yyyyMMdd = DateUtil.formatDate(materialDocumentEngineerExportDto.getDate(), Constants.YYYY_MM_DD) + Constants.BLANK + value;
				materialDocumentEngineerExportDto.setEndPumpDate(DateUtil.toObjDate(yyyyMMdd, Constants.YYYY_MM_DD_HH_MM_SS));
			} else {
				materialDocumentEngineerExportDto.setEndPumpDate(DateUtil.toObjDate(value, Constants.YYYY_MM_DD_HH_MM_SS));
			}
		} else {
			// 按日期的晚上六点定
			String date = DateUtil.formatDate(materialDocumentEngineerExportDto.getDate(), Constants.YYYY_MM_DD) + Constants.BLANK + "18:00:00";
			materialDocumentEngineerExportDto.setEndPumpDate(DateUtil.toObjDate(date, Constants.YYYY_MM_DD_HH_MM_SS));
		}
		// 泵送价
		if (!StringUtil.isObjBlank(output[8])) {
			value = StringUtil.trimAll(output[8].toString());
			materialDocumentEngineerExportDto.setPumpPrice(BigDecimalUtil.toBigDecimal(value));
		}
		// 加油量
		if (!StringUtil.isObjBlank(output[9])) {
			value = StringUtil.trimAll(output[9].toString());
			materialDocumentEngineerExportDto.setGasVolume(BigDecimalUtil.toBigDecimal(value));
		}
		// 油费
		if (!StringUtil.isObjBlank(output[10])) {
			value = StringUtil.trimAll(output[10].toString());
			materialDocumentEngineerExportDto.setGasPrice(BigDecimalUtil.toBigDecimal(value));
		}
		List<String> employeeList = new ArrayList<String>();
		// 开机人1
		if (!StringUtil.isObjBlank(output[11])) {
			value = StringUtil.trimAll(output[11].toString());
			employeeList.add(value);
		}
		// 开机人2
		if (!StringUtil.isObjBlank(output[12])) {
			value = StringUtil.trimAll(output[12].toString());
			employeeList.add(value);
		}
		// 开机人3
		if (!StringUtil.isObjBlank(output[13])) {
			value = StringUtil.trimAll(output[13].toString());
			employeeList.add(value);
		}
		materialDocumentEngineerExportDto.setEmployeeList(employeeList);
		if (!StringUtil.isObjBlank(output[14])) {
			value = StringUtil.trimAll(output[14].toString());
			materialDocumentEngineerExportDto.setRemark(value);
		}
		materialDocumentEngineerExportDtoDtoList.add(materialDocumentEngineerExportDto);
	}

}
