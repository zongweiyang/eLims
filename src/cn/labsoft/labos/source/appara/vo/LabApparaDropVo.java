package cn.labsoft.labos.source.appara.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;


/**
 * 仪器设备降级（报废）申请表
 * @author Administrator
 *
 */
public class LabApparaDropVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	private String id;
	private String appId;
	private String appName;
	private String appNo;
	
	private String view1;
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
	public String getView1() {
		return view1;
	}
	public void setView1(String view1) {
		this.view1 = view1;
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
	
	
}
