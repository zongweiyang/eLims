/**
 * <strong>Title : MesgMessageMainVo.java </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2010-3-9 下午01:30:03  </strong>. <br>
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

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 
 * <strong>Title : MessageMainVo </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Nov 16, 2010 3:10:28 PM </strong>. <br>
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

public class LabMsgMainVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String id;
	private String subject;
	private Short type;
	private String content;
	private String attachName;
	private String receiverNames;
	private String receiverIds;
	private String demo1;
	private String demo2;
	private String position;//1收件箱 0发件箱  2草稿箱   3 垃圾箱
	private String senderId;
	private String senderName;
	private String flag;
	public String getReceiverNames() {
		return receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	public String getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(String receiverIds) {
		this.receiverIds = receiverIds;
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

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the type
	 */
	public Short getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Short type) {
		this.type = type;
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


	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
