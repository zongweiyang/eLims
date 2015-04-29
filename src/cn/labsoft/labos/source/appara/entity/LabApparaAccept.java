package cn.labsoft.labos.source.appara.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 仪器设备验收表
 * @author Administrator
 *
 */
@Entity
@Table(name="lab_appara_accept")
public class LabApparaAccept extends AbstractBasePo{
	
	private String appId;
	private String appName;
	private String appNo;
	private String spec;//规格/型号
	private String manufact;//生产商
	private Double price;
	
	private String exp1;
	private String exp2;
	private String exp3;
	private String exp4;
	private String exp5;
	private String exp6;
	private String exp7;
	private String user1;//提出人
	private Date date1; 
	
	private String view2;
	private String user2;//审核人
	private Date date2;
	
	private String view3;
	private String user3;//审批人
	private Date date3;
	
	private String userId;
	private String userName;
	private String remark;
	private String status;
	private LabWfProcessIns processIns;//流程对象
	private String auditMessage;//审核意见
	private String pauditMessage;//审批意见
	private String auditUser;//审核人
	private String pauditUser;//审批人
	private String auditDate;//审核时间
	private String pauditDate;//审批时间
	
	@ManyToOne
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}
	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public String getView2() {
		return view2;
	}
	public void setView2(String view2) {
		this.view2 = view2;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public String getView3() {
		return view3;
	}
	public void setView3(String view3) {
		this.view3 = view3;
	}
	public String getUser3() {
		return user3;
	}
	public void setUser3(String user3) {
		this.user3 = user3;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getExp1() {
		return exp1;
	}
	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}
	public String getExp2() {
		return exp2;
	}
	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}
	public String getExp3() {
		return exp3;
	}
	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}
	public String getExp4() {
		return exp4;
	}
	public void setExp4(String exp4) {
		this.exp4 = exp4;
	}
	public String getExp5() {
		return exp5;
	}
	public void setExp5(String exp5) {
		this.exp5 = exp5;
	}
	public String getExp6() {
		return exp6;
	}
	public void setExp6(String exp6) {
		this.exp6 = exp6;
	}
	public String getExp7() {
		return exp7;
	}
	public void setExp7(String exp7) {
		this.exp7 = exp7;
	}
	public String getAuditMessage() {
		return auditMessage;
	}


	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}


	public String getPauditMessage() {
		return pauditMessage;
	}


	public void setPauditMessage(String pauditMessage) {
		this.pauditMessage = pauditMessage;
	}


	public String getAuditUser() {
		return auditUser;
	}


	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}


	public String getPauditUser() {
		return pauditUser;
	}


	public void setPauditUser(String pauditUser) {
		this.pauditUser = pauditUser;
	}


	public String getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}


	public String getPauditDate() {
		return pauditDate;
	}


	public void setPauditDate(String pauditDate) {
		this.pauditDate = pauditDate;
	}
	
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getManufact() {
		return manufact;
	}
	public void setManufact(String manufact) {
		this.manufact = manufact;
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器验收";
	}
	
}
