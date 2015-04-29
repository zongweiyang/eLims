package cn.labsoft.labos.base.message.dao;

import cn.labsoft.labos.base.message.entity.LabMsgMain;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
	
/**
 * 消息管理DAO
 * <strong>Title : ILabMsgMainDAO </strong>. <br>
 * <strong>Create on : Sep 4, 2014 1:15:48 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>CORE8 v 1.0.0</strong> <br>
 *          <br>
 */
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabMsgMainDAO extends IBaseDAO {
	/**
	 * 新增消息
	 * @Description: 发送消息者给已发送邮箱中添加一条数据
	 * @param labMsgMain 封装了消息主表po对象
	 * @return LabMsgMain 封装了消息主表po对象
	 * @throws GlobalException
	 */
	public LabMsgMain addLabMsgMain(LabMsgMain labMsgMain)throws GlobalException;
	/**
	 * 删除消息
	 * @param labMsgMain 封装了消息主表po对象
	 * @return boolean 
	 * @throws GlobalException
	 */
	public boolean deleteLabMsgMain(LabMsgMain labMsgMain)throws GlobalException;
	/**
	 * 修改消息
	 * @Description: TODO
	 * @param labMsgMain 封装了消息主表po对象
	 * @return LabMsgMain 封装了消息主表po对象
	 * @throws GlobalException
	 */
	public LabMsgMain updateLabMsgMain(LabMsgMain labMsgMain)throws GlobalException;
	/**
	 * 根据ID获得消息
	 * @param id 消息主表ID
	 * @return LabMsgMain  封装了消息主表po对象
	 * @throws GlobalException
	 */
	public LabMsgMain getLabMsgMain(String id)throws GlobalException;

	/**
	 * 发送站内消息
	 * @param senderId 发件人ID
	 * @param receiverId 收件人ID
	 * @param messageTitle 消息主题
	 * @param messageContent 消息内容
	 * @return boolean
	 * @throws GlobalException
	 */

	// 发送站内信
	public boolean sendLabMsgToPerson(String senderId, String receiverId,
			String messageTitle, String messageContent)throws GlobalException;
	// 发送站内信
	/*public boolean sendLabMsgToStation(String senderId, String orgId,
			String stationName, String messageTitle, String messageContent);*/
}
