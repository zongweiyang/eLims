package cn.labsoft.labos.constants;

public class Constants_Business extends Constants{
	
	public static final String LABORATORY = "检验科";
	public static final String TESTROLE = "检测人";
	public static final String CHECKROLE = "检验人";

	// 编号定义的公共编号
	public static final String CODE_RW = "RWBH";// 任务编号
	public static final String CODE_BG = "BGBH";// 报告编号
	public static final String CODE_LY = "LYBH";// 来样编号
	public static final String CODE_YP = "YPBH";// 样品编号
	public static final String CODE_SF = "SFBH";// 收费编号
	public static final String CODE_HC = "HCBH";// 耗材编号
	public static final String CODE_KH = "KHBH";// 客户编号
	public static final String CODE_BZP = "BZPBH";// 标准品编号
	public static final String CODE_KYXM = "KYXMBH";// 科研项目编号
	public static final String CODE_GC = "GCBH";// 过程管理

	public static final String PAPER = "PAPER";// 论文
	public static final String BOOK = "BOOK";// 论著
	public static final String AWARD = "AWARD";// 获奖
	public static final String PATENT = "PATENT";// 专利

	// 功能编号 必须与功能下的wfcode保持一致(用于功能间的相互集成)
	//WFCODE
	public static final String WF_CODE_SCORE = "WF_SCORE"; // 任务管理
	public static final String WF_CODE_BUS_RW = "WF_YW_RW"; // 任务管理
	public static final String WF_CODE_SCIE = "WF_KY_XM"; // 科研项目
	public static final String WF_CODE_QUA_GC = "WF_KY_XM_GC"; // 过程管理

}
