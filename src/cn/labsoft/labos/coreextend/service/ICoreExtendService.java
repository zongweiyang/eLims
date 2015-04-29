package cn.labsoft.labos.coreextend.service;

import java.util.List;

import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ICoreExtendService {

	public static final String WFCODE_SUN = "SUN";
	public static final String WFCODE_SUN_1 = "SUN1";
	public static final String WFCODE_SUN_2 = "SUN2";
	public static final String WFCODE_SUN_3 = "SUN3";
	public static final String WFCODE_SUN_4 = "SUN4";

	/**
	 * 根据条件查找未读消息列表
	 * 
	 * @param mesgMessageDetailVo
	 *            封装了消息从表信息的视图对象
	
	 * @throws GlobalException
	 * @return 返回类型 PageResult 返回未读消息列表
	 */
	@SuppressWarnings("unchecked")
	public List<LabMsgDetailVo> getUnreadMesgMessageList(LabMsgDetailVo labMsgDetailVo) throws GlobalException;

	/**
	 * 根据流程编号和步骤编号推送消息
	 * 
	 * @param taskCode
	 *            流程编号
	 * @param processCode
	 *            步骤编号
	 * @param processCode
	 *            指定角色的人，如果不为空，则发送给userId的人
	 * @throws GlobalException
	 * @return 
	 */
	public void getPushMessageTargetRole(String taskCode, String processCode, String userId);

	/**
	 * 根据流程编号和步骤编号和步骤的子流程号推送消息
	 * 
	 * @param taskCode
	 *            流程编号
	 * @param processCode
	 *            主步骤编号
	 * @param sunProcessCode
	 *            子步骤编号
	 * @param processCode
	 *            指定角色的人，如果不为空，则发送给userId的人
	 * @throws GlobalException
	 * @return 
	 */
	public void getPushMessageTargetRoleIsHaveSunWfCode(String taskCode, String processCode, String sunProcessCode, String userId);

}