package cn.labsoft.labos.common.workflow.action;
/**
 * 流程功能类
 * @author Quinn
 * @version 8.0
 */
public class LabWfConstant {
	
	public static final String STEP_START="开始";
	public static final String STEP_END="结束";
	
	public static final String NODES_STATUS_INIT = "0";// 初始化
	public static final String NODES_STATUS_UNRUN = "1";// 未完成
	public static final String NODES_STATUS_RUNNED = "2";// 已完成
	public static final String NODES_STATUS_UNNED = "3";// 流程重复回到该点
	public static final String NODES_STATUS_NED = "4";// 流程重复该点完毕
	
	public static final String LINKS_STATUS_INIT = "0";// 初始
	public static final String LINKS_STATUS_UNRUN = "1";// 未完成
	public static final String LINKS_STATUS_RUNNED = "2";// 已完成
	public static final String LINKS_STATUS_UNNED = "3";// 重复
	
	public static final String LINKS_START = "START";
	public static final String LINKS_END = "END";
	
	//流程定义状态
	public static final String PROCESS_EDIT="0";//模型状态为可编辑
	public static final String PROCESS_OPEN="1";//模型状态为启用中
	public static final String PROCESS_CLOSE="2";//模型状态为已关闭
	
	
	
	//流程实例状态
	public static final String BUS_PROCESS_INIT="0";  //实例初始
	public static final String BUS_PROCESS_UNRUN="1"; //实例预处理
	public static final String BUS_PROCESS_RUN="2";   //实例进行中
	public static final String BUS_PROCESS_END="3";   //实例完成
	
	//节点状态
	public static final String BUS_STEP_ALL="0";//节点实例完全提交
	public static final String BUS_STEP_SUB="1";//节点实例部分提交
	//判断流向
	public static final String BUS_BACK="-1";//后退
	public static final String BUS_WAIT="0";//停止
	public static final String BUS_GO="1";   //前进
	//操作者类型
	public static final String OPER_TYPE_USER="user";      //人员
	public static final String OPER_TYPE_ROLE="role";      //角色
	public static final String OPER_TYPE_ORG="org";        //组织
	
//	public static final String  BUS_INFO_N="0";  //未读
//	public static final String  BUS_INFO_Y="1";  //已读
//	//对应业务是否已作删除标志
//	public static final String  DEL_N="0";  //否
//	public static final String  DEL_Y="1";  //是
	
}
