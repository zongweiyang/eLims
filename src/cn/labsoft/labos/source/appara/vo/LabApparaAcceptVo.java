package cn.labsoft.labos.source.appara.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;


/**
 * 仪器设备验收表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LabApparaAcceptVo extends BaseVo{

	private String id;
	private String appId;
	private String appName;
	private String appNo;//仪器编号
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
	private String date1; 
	
	private String view2;
	private String user2;//审核人
	private String date2;
	
	private String view3;
	private String user3;//审批人
	private String date3;
	
	private String createDate;
	private String userId;
	private String userName;
	private String remark;
	private String auditUser;//审核人
	private String pauditUser;//审批人
	private String auditDate;//审核时间
	private String pauditDate;//审批时间
	private String pauditMessage;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
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
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
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
	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public String getPauditMessage() {
		return pauditMessage;
	}
	public void setPauditMessage(String pauditMessage) {
		this.pauditMessage = pauditMessage;
	}
	
}
