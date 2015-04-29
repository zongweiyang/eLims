/**
 * <strong>Title : MesgMessageDetailVo.java </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2010-3-9 下午01:29:48  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author Molly Hao<br>
 * @version <strong>LabOSM1 v </strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
package cn.labsoft.labos.base.message.vo;

import java.util.List;

import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 
 * <strong>Title : MessageDetailVo </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Nov 16, 2010 3:10:07 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author jim yang<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class LabMsgDetailVo extends BaseVo {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String[] ids;
	private Short readFlag;
	private Short position;//1收件箱 0发件箱  2草稿箱   3 垃圾箱
	private String receiveId;
	private String receiveName;
	private String type;
	private String senderName;
	private String receivers;
	private String loginName;
	private String senderId;
	private String content;// 消息内容
	private String attachName;
	private String mainMsgID;
	private String demo1;
	private String demo2;// 存入所有接收人的ID
	private String subject;// 存放的是主题
	private List<LabUploadVo> msg;
	private int flag;
	
	
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	

	public List<LabUploadVo> getMsg() {
		return msg;
	}

	public void setMsg(List<LabUploadVo> msg) {
		this.msg = msg;
	}

	public String getReceivers() {
		return receivers;
	}

	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the attachname
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * @param attachname
	 *            the attachname to set
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the receiveId
	 */
	public String getReceiveId() {
		return receiveId;
	}

	/**
	 * @param receiveId
	 *            the receiveId to set
	 */
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	/**
	 * @return the ids
	 */
	@Override
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName
	 *            the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	/**
	 * @return the position
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * @return the sysUserByReceiver
	 */

	/**
	 * @return the demo1
	 */
	public String getDemo1() {
		return demo1;
	}

	/**
	 * @param demo1
	 *            the demo1 to set
	 */
	public void setDemo1(String demo1) {
		this.demo1 = demo1;
	}

	/**
	 * @return the demo2
	 */
	public String getDemo2() {
		return demo2;
	}

	/**
	 * @param demo2
	 *            the demo2 to set
	 */
	public void setDemo2(String demo2) {
		this.demo2 = demo2;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the receiveName
	 */
	public String getReceiveName() {
		return receiveName;
	}

	/**
	 * @param receiveName
	 *            the receiveName to set
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMainMsgID() {
		return mainMsgID;
	}

	public void setMainMsgID(String mainMsgID) {
		this.mainMsgID = mainMsgID;
	}

}
