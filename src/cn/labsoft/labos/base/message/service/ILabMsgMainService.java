package cn.labsoft.labos.base.message.service;

import java.util.Map;
import cn.labsoft.labos.base.message.vo.LabMsgMainVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 消息管理Service
 * @author danlee Li
 *
 */
@SuppressWarnings("unchecked")
public interface ILabMsgMainService {
	/**
	 * 
	 * 增加消息
	 * @param labMsgMainVo
	 *            封装了消息主表的视图对象
	 * @throws GlobalException
	 * @return 返回类型 LabMsgMainVo 封装了消息主表的视图对象
	 */
	public LabMsgMainVo addLabMsgMain(LabMsgMainVo labMsgMainVo)
			throws GlobalException;

	/**
	 * 向消息主表中插入一条记录
	 * @param labMsgMainVo
	 *            封装了消息主表信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 LabMsgMainVo 封装了消息主表信息的视图对象
	 */
	public LabMsgMainVo addLabMsgDetailbyName(LabMsgMainVo labMsgMainVo)
			throws GlobalException;
	/**
	 * 根据消息类型获得消息主表信息
	 * @Description: TODO
	 * @param @param column
	 * @param @param value
	 * @param @param type
	 * @param @return Map
	 * @param @throws GlobalException  
	 * @return Map
	 * @throws
	 */
	public Map getLabMsgMainVoByType(String[] column, String[] value,
			String type) throws GlobalException;
	/**
	 * 
	 * 根据ID查看消息
	 * @param id
	 *            消息主表的ID
	 * @throws GlobalException
	 * @return 返回类型 LabMsgMainVo 封装了消息主表的视图对象
	 */
	public LabMsgMainVo getLabMsgMain(String id)
			throws GlobalException;
	/**
	 * 将消息放入垃圾箱
	 * @param @param labMsgMainVo封装了消息主表的视图对象
	 * @return boolean
	 * @throws GlobalException 
	 */
	public boolean updateLabMsgMain2Laji(LabMsgMainVo labMsgMainVo) throws GlobalException;
	/**
	 * 删除主表消息
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public boolean deleteLabMsgMain(LabMsgMainVo labMsgMainVo) throws GlobalException;
	/**
	 * 删除草稿箱消息
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @return boolean 
	 * @throws GlobalException 
	 */
	public boolean deleteLabMsgMain4CaoGao(LabMsgMainVo labMsgMainVo) throws GlobalException;

	/**
	 * 获得当前登录人已发送信息的分页列表
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @param pageResult
	 * @return PageResult 
	 * @throws GlobalException 
	 */
	public PageResult getLabMsgMainPR4Sender(LabMsgMainVo labMsgMainVo, PageResult pageResult) throws GlobalException;
	/**
	 * 获得当前登录人草稿箱中的分页信息
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @param pageResult
	 * @return PageResult 
	 * @throws GlobalException 
	 */
	public PageResult getLabMsgMainPR4CaoGao(LabMsgMainVo labMsgMainVo, PageResult pageResult) throws GlobalException;
	/**
	 * 获得当前登录人收件箱中的消息列表
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabMsgMainPR4Recive(LabMsgMainVo labMsgMainVo,PageResult pageResult) throws GlobalException;
	/**
	 * 获得当前登录人垃圾箱中的消息列表
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @param pageResult
	 * @return PageResult 
	 * @throws GlobalException 
	 */
	public PageResult getLabMsgMainPR4Delete(LabMsgMainVo labMsgMainVo, PageResult pageResult) throws GlobalException;
	/**
	 * 修改消息主表信息
	 * @param labMsgMainVo 封装了消息主表的视图对象
	 * @throws GlobalException  
	 * @return LabMsgMainVo 
	 */
	public LabMsgMainVo updateLabMsgMain(LabMsgMainVo labMsgMainVo)throws GlobalException;
	/**
	 * 根据消息详情表ID获得主表信息
	 * @param id 消息详情表ID
	 * @throws GlobalException  
	 * @return LabMsgMainVo 封装了消息主表的视图对象
	 */
	public LabMsgMainVo getLabMsgMainBydetailId(String id)throws GlobalException;
	/**
	 * 根据用户ID统计未读消息数量
	 * @param @param userId
	 * @throws GlobalException  
	 * @return int 
	 */
	public int getLabMsgMessage4UnreadCount(String userId) throws GlobalException;
	/**
	 * 根据用户ID统计草稿箱消息数量
	 * @param @param userId
	 * @throws GlobalException  
	 * @return int 
	 */
	public int getLabMsgMessage4CaoGaoCount(String userId) throws GlobalException;
	/**
	 * 根据用户ID统计发送箱消息数量
	 * @param @param userId
	 * @throws GlobalException  
	 * @return int 
	 */
	public int getLabMsgMessage4SenderCount(String userId) throws GlobalException;
	/**
	 * 根据用户ID统计垃圾箱消息数量
	 * @param @param userId
	 * @throws GlobalException  
	 * @return int 
	 */
	public int getLabMsgMessage4DeleteCount(String userId) throws GlobalException;
}
