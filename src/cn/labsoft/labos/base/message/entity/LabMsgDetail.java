package cn.labsoft.labos.base.message.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 
 * <strong>Title : MessageDetail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Nov 16, 2010 3:12:08 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author jim yang<br>
 * @version <strong>LabOSM1 v 10.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
/**
 * <strong>Title : LabMsgDetail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2014-4-27 下午10:48:26  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

@Entity
@Table(name="lab_msg_detail")
public class LabMsgDetail extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private int readFlag;
	private Short position;//1收件箱 0发件箱  2草稿箱   3 垃圾箱
	private LabUser receiver;
	private String receiveName;
	private LabUser sender;
	private String senderName;
	private LabMsgMain msgMain;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	@ManyToOne
	@JoinColumn(name="sender_id")
	public LabUser getSender() {
		return sender;
	}

	public void setSender(LabUser sender) {
		this.sender = sender;
	}

	public int getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}

	public Short getPosition() {
		return this.position;
	}

	public void setPosition(Short position) {
		this.position = position;
	}

	@ManyToOne
	@JoinColumn(name="main_id", nullable=false)
	public LabMsgMain getMsgMain() {
		return msgMain;
	}

	public void setMsgMain(LabMsgMain msgMain) {
		this.msgMain = msgMain;
	}

	@ManyToOne
	@JoinColumn(name="receiver_id")
	public LabUser getReceiver() {
		return receiver;
	}

	public void setReceiver(LabUser receiver) {
		this.receiver = receiver;
	}
	
	@Transient
	@Override
	public String getModelName() {
		
		return "消息管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "消息子表";
	}

}