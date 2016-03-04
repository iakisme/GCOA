package com.qylm.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ApplicationScoped
@ManagedBean(eager=true)
public class GenericCodeBean {


	private SelectItem[] c0101;

	private SelectItem[] c0102;

	private SelectItem[] c0103;

	private SelectItem[] c0104;

	private SelectItem[] c0105;

	private SelectItem[] c0106;

	private SelectItem[] c0201;

	private SelectItem[] c0202;

	private SelectItem[] c0301;

	private SelectItem[] c0302;

	private SelectItem[] c0401;

	private SelectItem[] c0402;

	private SelectItem[] c0403;

	private SelectItem[] c0501;

	private SelectItem[] c0502;

	private SelectItem[] c0601;

	private SelectItem[] c0602;

	private SelectItem[] c0701;

	private SelectItem[] c0702;

	private SelectItem[] c0801;
	
	private SelectItem[] c0901;

	private SelectItem[] c1001;

	public GenericCodeBean() {

		c0101 = new SelectItem[57];
		c0101[0] = new SelectItem("-1", "未选择");
		c0101[1] = new SelectItem("1", "汉族");
		c0101[2] = new SelectItem("2", "朝鲜族");
		c0101[3] = new SelectItem("3", "赫哲族");
		c0101[4] = new SelectItem("4", "达斡尔族");
		c0101[5] = new SelectItem("5", "鄂伦春族");
		c0101[6] = new SelectItem("6", "鄂温克族");
		c0101[7] = new SelectItem("7", "蒙古族");
		c0101[8] = new SelectItem("8", "回族");
		c0101[9] = new SelectItem("9", "东乡族");
		c0101[10] = new SelectItem("10", "保安族");
		c0101[11] = new SelectItem("11", "撒拉族");
		c0101[12] = new SelectItem("12", "土族");
		c0101[13] = new SelectItem("13", "裕固族");
		c0101[14] = new SelectItem("14", "俄罗斯族");
		c0101[15] = new SelectItem("15", "乌兹别克族");
		c0101[16] = new SelectItem("16", "塔塔尔族");
		c0101[17] = new SelectItem("17", "锡伯族");
		c0101[18] = new SelectItem("18", "哈萨克族");
		c0101[19] = new SelectItem("19", "克尔克孜族");
		c0101[20] = new SelectItem("20", "维吾尔族");
		c0101[21] = new SelectItem("21", "塔吉克族");
		c0101[22] = new SelectItem("22", "藏族");
		c0101[23] = new SelectItem("23", "珞巴族");
		c0101[24] = new SelectItem("24", "门巴族");
		c0101[25] = new SelectItem("25", "纳西族");
		c0101[26] = new SelectItem("26", "彝族");
		c0101[27] = new SelectItem("27", "普米族");
		c0101[28] = new SelectItem("28", "白族");
		c0101[29] = new SelectItem("29", "傈僳族");
		c0101[30] = new SelectItem("30", "怒族");
		c0101[31] = new SelectItem("31", "独龙族");
		c0101[32] = new SelectItem("32", "景颇族");
		c0101[33] = new SelectItem("33", "阿昌族");
		c0101[34] = new SelectItem("34", "德昂族");
		c0101[35] = new SelectItem("35", "佤族");
		c0101[36] = new SelectItem("36", "拉祜族");
		c0101[37] = new SelectItem("37", "布朗族");
		c0101[38] = new SelectItem("38", "傣族");
		c0101[39] = new SelectItem("39", "基诺族");
		c0101[40] = new SelectItem("40", "哈尼族");
		c0101[41] = new SelectItem("41", "京族");
		c0101[42] = new SelectItem("42", "黎族");
		c0101[43] = new SelectItem("43", "毛南族");
		c0101[44] = new SelectItem("44", "壮族");
		c0101[45] = new SelectItem("45", "仫佬族");
		c0101[46] = new SelectItem("46", "瑶族");
		c0101[47] = new SelectItem("47", "侗族");
		c0101[48] = new SelectItem("48", "苗族");
		c0101[49] = new SelectItem("49", "水族");
		c0101[50] = new SelectItem("50", "布依族");
		c0101[51] = new SelectItem("51", "仡佬族");
		c0101[52] = new SelectItem("52", "羌族");
		c0101[53] = new SelectItem("53", "土家族");
		c0101[54] = new SelectItem("54", "畲族");
		c0101[55] = new SelectItem("55", "高山族");
		c0101[56] = new SelectItem("56", "满族");

		c0102 = new SelectItem[4];
		c0102[0] = new SelectItem("-1", "未选择");
		c0102[1] = new SelectItem("1", "男");
		c0102[2] = new SelectItem("2", "女");
		c0102[3] = new SelectItem("3", "中性");

		c0103 = new SelectItem[4];
		c0103[0] = new SelectItem("-1", "未选择");
		c0103[1] = new SelectItem("1", "青年");
		c0103[2] = new SelectItem("2", "团员");
		c0103[3] = new SelectItem("3", "党员");

		c0104 = new SelectItem[4];
		c0104[0] = new SelectItem("-1", "未选择");
		c0104[1] = new SelectItem("1", "已婚");
		c0104[2] = new SelectItem("2", "未婚");
		c0104[3] = new SelectItem("3", "离异");

		c0105 = new SelectItem[8];
		c0105[0] = new SelectItem("-1", "未选择");
		c0105[1] = new SelectItem("1", "小学");
		c0105[2] = new SelectItem("2", "初中");
		c0105[3] = new SelectItem("3", "高中");
		c0105[4] = new SelectItem("4", "大专");
		c0105[5] = new SelectItem("5", "本科");
		c0105[6] = new SelectItem("6", "研究生");
		c0105[7] = new SelectItem("7", "博士");

		c0106 = new SelectItem[10];
		c0106[0] = new SelectItem("-1", "未选择");
		c0106[1] = new SelectItem("1", "草稿状态");
		c0106[2] = new SelectItem("2", "已提交，待审核");
		c0106[3] = new SelectItem("3", "审核未通过");
		c0106[4] = new SelectItem("4", "审核通过");
		c0106[5] = new SelectItem("5", "采购中");
		c0106[6] = new SelectItem("6", "采购归来，待入库");
		c0106[7] = new SelectItem("7", "已入库");
		c0106[8] = new SelectItem("8", "采购完结");
		c0106[9] = new SelectItem("9", "采购失败");

		c0201 = new SelectItem[5];
		c0201[0] = new SelectItem("-1", "未选择");
		c0201[1] = new SelectItem("1", "现金");
		c0201[2] = new SelectItem("2", "汇款");
		c0201[3] = new SelectItem("3", "在线支付");
		c0201[4] = new SelectItem("4", "虚拟货币");

		c0202 = new SelectItem[4];
		c0202[0] = new SelectItem("-1", "未选择");
		c0202[1] = new SelectItem("1", " 单价");
		c0202[2] = new SelectItem("2", "包月");
		c0202[3] = new SelectItem("3", "包年");

		c0301 = new SelectItem[4];
		c0301[0] = new SelectItem("-1", "未选择");
		c0301[1] = new SelectItem("1", "在职");
		c0301[2] = new SelectItem("2", "离职");
		c0301[3] = new SelectItem("3", "待职");

		c0302 = new SelectItem[3];
		c0302[0] = new SelectItem("-1", "未选择");
		c0302[1] = new SelectItem("1", "项目负责人");
		c0302[2] = new SelectItem("2", "普通员工");

		c0401 = new SelectItem[3];
		c0401[0] = new SelectItem("-1", "未选择");
		c0401[1] = new SelectItem("1", "主手");
		c0401[2] = new SelectItem("2", "副手");

		c0402 = new SelectItem[4];
		c0402[0] = new SelectItem("-1", "未选择");
		c0402[1] = new SelectItem("1", "未生效");
		c0402[2] = new SelectItem("2", "维修中");
		c0402[3] = new SelectItem("3", "维修完毕");

		c0403 = new SelectItem[3];
		c0403[0] = new SelectItem("-1", "未选择");
		c0403[1] = new SelectItem("1", "处理中");
		c0403[2] = new SelectItem("2", "处理完毕");

		c0501 = new SelectItem[9];
		c0501[0] = new SelectItem("-1", "未选择");
		c0501[1] = new SelectItem("1", "未生效");
		c0501[2] = new SelectItem("2", "工作安排中");
		c0501[3] = new SelectItem("3", "工作进行中");
		c0501[4] = new SelectItem("4", "工作已汇报");
		c0501[5] = new SelectItem("5", "工作暂停");
		c0501[6] = new SelectItem("6", "工作完结");
		c0501[7] = new SelectItem("7", "存在拒绝信息");
		c0501[8] = new SelectItem("8", "此项目原料运输异常");

		c0502 = new SelectItem[4];
		c0502[0] = new SelectItem("1", "通知中");
		c0502[1] = new SelectItem("2", "已接手");
		c0502[2] = new SelectItem("3", "已拒绝");
		c0502[3] = new SelectItem("4", "已结束");

		c0601 = new SelectItem[4];
		c0601[0] = new SelectItem("-1", "未选择");
		c0601[1] = new SelectItem("1", "未生效");
		c0601[2] = new SelectItem("2", "确认收款中");
		c0601[3] = new SelectItem("3", "收款完毕");

		c0602 = new SelectItem[6];
		c0602[0] = new SelectItem("-1", "未选择");
		c0602[1] = new SelectItem("1", "油费");
		c0602[2] = new SelectItem("2", "车辆维修");
		c0602[3] = new SelectItem("3", "车辆违章事故");
		c0602[4] = new SelectItem("4", "车辆租用");
		c0602[5] = new SelectItem("5", "其他");

		c0701 = new SelectItem[2];
		c0701[0] = new SelectItem("-1", "未选择");
		c0701[1] = new SelectItem("1", "配件采购");

		c0702 = new SelectItem[4];
		c0702[0] = new SelectItem("-1", "未选择");
		c0702[1] = new SelectItem("1", "待审核");
		c0702[2] = new SelectItem("2", "审核通过");
		c0702[3] = new SelectItem("3", "审核不通过");

		c0801 = new SelectItem[1];
		c0801[0] = new SelectItem("1", "维修出库");
		
		c0901 = new SelectItem[3];
		c0901[0] = new SelectItem("1", "未领取");
		c0901[1] = new SelectItem("2", "领取中");
		c0901[2] = new SelectItem("3", "确认领取");

		c1001 = new SelectItem[4];
		c1001[0] = new SelectItem("1", "未发放");
		c1001[1] = new SelectItem("2", "发放中");
		c1001[2] = new SelectItem("3", "已出发");
		c1001[3] = new SelectItem("4", "已确认");
	}

	public SelectItem[] getC0101() {return c0101;}

	public SelectItem[] getC0102() {return c0102;}

	public SelectItem[] getC0103() {return c0103;}

	public SelectItem[] getC0104() {return c0104;}

	public SelectItem[] getC0105() {return c0105;}

	public SelectItem[] getC0106() {return c0106;}

	public SelectItem[] getC0201() {return c0201;}

	public SelectItem[] getC0202() {return c0202;}

	public SelectItem[] getC0301() {return c0301;}

	public SelectItem[] getC0302() {return c0302;}

	public SelectItem[] getC0401() {return c0401;}

	public SelectItem[] getC0402() {return c0402;}

	public SelectItem[] getC0403() {return c0403;}

	public SelectItem[] getC0501() {return c0501;}

	public SelectItem[] getC0502() {return c0502;}

	public SelectItem[] getC0601() {return c0601;}

	public SelectItem[] getC0602() {return c0602;}

	public SelectItem[] getC0701() {return c0701;}

	public SelectItem[] getC0702() {return c0702;}

	public SelectItem[] getC0801() {return c0801;}
	
	public SelectItem[] getC0901() {return c0901;}
	
	public SelectItem[] getC1001() {return c1001;}

}
