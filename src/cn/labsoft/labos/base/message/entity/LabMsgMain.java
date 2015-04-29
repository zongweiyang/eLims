package cn.labsoft.labos.base.message.entity;

// default package

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 
 * <strong>Title : MessageMain </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Nov 16, 2010 3:11:37 PM </strong>. <br>
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
@Entity
@Table(name="lab_msg_main")
public class LabMsgMain extends AbstractBasePo{
	
	private static final long serialVersionUID = 1L;
	private String subject;
	private String position;//2草稿箱 1收件箱 0发件箱  3 垃圾箱
	private Short type;//0-消息 1-公告
	private String content;
	private String attachName;
	private String msg;
	private String demo1;
	private String demo2;
	private String receiverIds;
	private String receiverNames;
	private LabUser sender;
	private String senderName;
	
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getDemo1() {
		return this.demo1;
	}

	public void setDemo1(String demo1) {
		this.demo1 = demo1;
	}

	public String getDemo2() {
		return this.demo2;
	}

	public void setDemo2(String demo2) {
		this.demo2 = demo2;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReceiverNames() {
		return receiverNames;
	}
	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	@ManyToOne
	@JoinColumn(name="sender_id")
	public LabUser getSender() {
		return sender;
	}

	public void setSender(LabUser sender) {
		this.sender = sender;
	}
	
	public String getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(String receiverIds) {
		this.receiverIds = receiverIds;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Transient
	@Override
	public String getModelName() {
		
		return "消息管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "消息主表";
	}
	
}