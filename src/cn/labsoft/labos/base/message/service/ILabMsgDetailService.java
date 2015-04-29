package cn.labsoft.labos.base.message.service;

import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.message.vo.LabMsgMainVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 消息管理详情Service
 * @author danlee Li
 *
 */ 
@SuppressWarnings("unchecked")
public interface ILabMsgDetailService {
	/**
	 * 根据ID获得消息详情
	 * @param labMsgDetailVo
	 * 						封装了消息详情表的视图对象
	 * @throws GlobalException  
	 * @return LabMsgDetailVo 封装了消息详情表的视图对象
	 */
	public LabMsgDetailVo getLabMsgDetailById(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException;
	/**
	 * 修改消息详情
	 * @param @param labMsgDetailVo
	 * 							封装了消息详情表的视图对象
	 * @param @throws GlobalException  
	 * @return void
	 * @throws
	 */
	public abstract void updateLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException;
	/**
	 * 将消息修改为已读
	 * @param labMsgDetailVo
	 * 							封装了消息详情表的视图对象
	 * @return LabMsgDetailVo
	 * @throws GlobalException  
	 * @return LabMsgDetailVo 
	 * 							封装了消息详情表的视图对象
	 */
	public LabMsgDetailVo updateLabMsg4Readed(LabMsgDetailVo labMsgDetailVo)throws GlobalException;
	/**
	 * 获得消息列表
	 * @param labMsgDetailVo
	 * 					封装了消息详情表的视图对象
	 * @param pageResult
	 * @return PageResult 
	 * @throws GlobalException
	 */
	public PageResult getLabMsgDetailList(LabMsgDetailVo labMsgDetailVo, PageResult pageResult) throws GlobalException;
	/**
	 * 根据ids将消息标志为已读/未读
	 * @param ids
	 * @param flag
	 * @return boolean
	 * @throws GlobalException 
	 */
	public boolean showlabMsgDetailAll(String[] ids, int flag)
			throws GlobalException;
	/**
	 * 根据Id查看当前消息
	 * @param id
	 * @return LabMsgDetailVo 封装了消息主表的视图对象
	 * @throws GlobalException 
	 */
	public LabMsgDetailVo showLabMsgById(String id) throws GlobalException;
	/**
	 * 删除当前信息
	 * @param labMsgDetailVo 封装了消息主表的视图对象
	 * @return boolean
	 * @throws GlobalException  
	 */
	public boolean deleteLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException;
	/**
	 * 获得已删除消息列表
	 * @param labMsgDetailVo 封装了消息主表的视图对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException 
	 */
	public PageResult isDeleteLabMsgList(LabMsgDetailVo labMsgDetailVo, PageResult pageResult)
			throws GlobalException;
		/**
		 * 保存草稿信息
		 * @param labMsgDetailVo 封装了消息详情表的视图对象
		 * @throws GlobalException
		 */
	public void saveLabCaoGao(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException;
		/**
		 * 获得草稿分页列表
		 * @param labMsgDetailVo 封装了消息详情表的视图对象
		 * @param pageResult
		 * @return PageResult
		 * @throws GlobalException
		 */
	public PageResult getLabCaoGaoList(LabMsgDetailVo labMsgDetailVo, PageResult pageResult) throws GlobalException;
		/**
		 * 根据收件人ID统计收件人未读消息总数
		 * @param id 收件人ID
		 * @return int 
		 * 				返回收件人未读消息总数
		 * @throws GlobalException
		 */
	public int getUnreadLabMsg(String id) throws GlobalException;
	/**
	 * 根据登录者名称获得登录人未读消息总数
	 * @param loginname
	 * @return long 
	 * 			返回收件人未读消息总数
	 * @throws GlobalException
	 */
	public long getUnreadLabMsgMessage(String loginname) throws GlobalException;
	/**
	 * 将消息发送给指定人
	 * @param senderId 发件人ID
	 * @param receiverId 收件人ID
	 * @param messageTitle 消息主题
	 * @param messageContent 消息内容
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean sendLabMsgToPerson(String senderId, String receiverId,
			String messageTitle, String messageContent) throws GlobalException;
	/**
	 * 发送消息给制定部门的人
	 * @param senderId 发件人ID
	 * @param orgId 部门ID
	 * @param messageTitle
	 * @param messageContent
	 * @return boolean
	 */
	public boolean sendLabMsgToOrg(String senderId, String orgId,
			String messageTitle, String messageContent) throws GlobalException;
	/**
	 * 发送消息指定角色
	 * @param senderId 发件人ID
	 * @param roleId 角色ID
	 * @param messageTitle 消息主题
	 * @param messageContent 消息内容
	 * @return boolean
	 */
	public boolean sendOLabMsgToRole(String senderId, String roleId,
			String messageTitle, String messageContent) throws GlobalException;

	/**
	 * 根据登录人ID统计草稿箱消息总数
	 * @param id 
	 * 				登录者ID
	 * @return int 
	 * 				返回统计条数
	 * @throws GlobalException
	 */
	public int getCaogaoLabMsg(String id) throws GlobalException;
	/**
	 * 根据当前用户信息获得当前用户发件箱信息
	 * @param vo 封装了用户表视图对象（LabUserVo）
	 * @return long
	 * @throws GlobalException
	 */
	public long getCountLabMsgMessageDetail(LabUserVo vo)
	throws GlobalException;
	
	/**
	 * 根据登录者登录名称统计登录着草稿箱信息总条数
	 * @param loginName 登录者姓名
	 * @return	long 返回登陆者草稿箱总条数
	 * @throws GlobalException
	 */
	public long getCaogaoLabMsgMessage(String loginName) throws GlobalException;
	
	/**
	 * 根据据当前用户信息获得当前用户草稿箱信息
	 * @param vo 封装了用户表视图对象（LabUserVo）
	 * @return long 返回草稿箱统计条数
	 * @throws GlobalException
	 */
	public long getCaogaoLabMsgMessageDetail(LabUserVo vo)
			throws GlobalException;
	/**
	 * 
	 *根据据当前用户信息获得当前用户发件箱信息
	 * @param vo 封装了用户表视图对象（LabUserVo）
	 * @return long 返回发件箱统计条数
	 * @throws GlobalException
	 */
	public long getSendLabMsgMessageDetail(LabUserVo vo) throws GlobalException;
	/**
	 * 
	 *根据据当前用户信息获得当前用户垃圾箱信息
	 * @param vo 封装了用户表视图对象（LabUserVo）
	 * @return long 返回发件箱统计条数
	 * @throws GlobalException
	 */
	public long getIsDeleteLabMsgMessageDetail(LabUserVo vo)
			throws GlobalException;
	/**
	 * 发送草稿箱信息
	 * @param labMsgDetailVo 封装了消息详情表的视图对象
	 * @throws GlobalException
	 */
	public void sendCaoGaoLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException;
	/**
	 * 将垃圾箱的消息会恢复到收件箱
	 * @param labMsgDetailVo 封装了消息详情表的视图对象
	 * @throws GlobalException
	 */
	public void updateLabMsgDetail4IsDelete(LabMsgDetailVo labMsgDetailVo)throws GlobalException;
	/**
	 * 删除消息详情-真删除
	 * @param labMsgDetailVo 封装了消息详情表的视图对象
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean deleteLabMsgDetail4Real(LabMsgDetailVo labMsgDetailVo)throws GlobalException;
	/**
	 * 删除消息详情-假删除
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean deleteLabMsgDetail(LabMsgMainVo labMsgMainVo)throws GlobalException;
}
