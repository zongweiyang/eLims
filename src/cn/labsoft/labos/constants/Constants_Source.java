package cn.labsoft.labos.constants;

public class Constants_Source  extends Constants{

	// 编号类型
	public static final String CODE_USE_INIT = "0";// 预处理
	public static final String CODE_USE_RUN = "1";// 启用
	// 编号定义的公共编号
	public static final String CODE_SJ = "SJBH";// 试剂编号
	public static final String CODE_HT = "HTBH";// 合同编号
	public static final String CODE_RW = "RWBH";// 任务编号
	public static final String CODE_BG = "BGBH";// 报告编号
	public static final String CODE_LY = "LYBH";// 来样编号
	public static final String CODE_YP = "YPBH";// 样品编号
	public static final String CODE_SF = "SFBH";// 收费编号
	public static final String CODE_HC = "HCBH";// 耗材编号
	public static final String CODE_KH = "KHBH";// 客户编号
	public static final String CODE_BZP = "BZPBH";// 标准品编号
	public static final String CODE_GYS = "GYSBH";// 供应商编号
	public static final String CODE_CC = "CCBH"; // 抽查编号
	public static final String CODE_NB = "NBPSBGBH";// 内部评审报告编号
	public static final String CODE_GL = "GLPSBGBH";// 管理评审报告编号
	public static final String CODE_KYXM = "KYXMBH";// 科研项目编号
	public static final String CODE_YWTS = "TSBH";// 投诉编号
	public static final String CODE_KHHF = "HFDJBH";// 回访登记编号
	public static final String CODE_GC = "GCBH";// 过程管理

	public static final String PAPER = "PAPER";// 论文
	public static final String BOOK = "BOOK";// 论著
	public static final String AWARD = "AWARD";// 获奖
	public static final String PATENT = "PATENT";// 专利

	// 功能编号 必须与功能下的wfcode保持一致(用于功能间的相互集成)
	//WFCODE
	public static final String WF_CODE_BUS_RW = "WF_YW_RW"; // 任务管理
	public static final String WF_CODE_REA = "WF_SJ"; // 试剂
	public static final String WF_CODE_CONS = "WF_HC"; // 耗材
	public static final String WF_CODE_REF = "WF_BZP"; // 标准品
	public static final String WF_CODE_APP_GZ = "WF_YQ_GZ"; // 仪器故障
	public static final String WF_CODE_APP_YS = "WF_YQ_YS"; // 仪器验收
	public static final String WF_CODE_APP_BF = "WF_YQ_BF"; // 仪器报废
	public static final String WF_CODE_APP_TY = "WF_YQ_TY"; // 仪器停用
	public static final String WF_CODE_APP_QY = "WF_YQ_QY"; // 仪器启用
	public static final String WF_CODE_APP_JD = "WF_YQ_JD"; // 仪器检定
	public static final String WF_CODE_APP_HC = "WF_YQ_HC"; // 仪器核查
	public static final String WF_CODE_APP_WX = "WF_YQ_WX"; // 仪器维修
	public static final String WF_CODE_APP_JY = "WF_YQ_JY"; // 仪器借用
	public static final String WF_CODE_QUA_NL = "WF_ZL_NL"; // 能力验证

	// 质量管理转事故
	public static final String ACC_NBSP = "ACC-NBSP"; // 内部评审
	public static final String ACC_GLPS = "ACC-GLPS"; // 管理评审
	public static final String ACC_YWTS = "ACC-YWTS"; // 业务投诉
	public static final String ACC_JDCC = "ACC-JDCC"; // 监督抽查
	public static final String ACC_NLYZ = "ACC-NLYZ"; // 能力验证
	public static final String ACC_KHHFJL = "ACC-KHHFJL"; // 客户回访记录
	public static final String ACC_SJGL = "ACC-SJGL"; // 数据管理
}
