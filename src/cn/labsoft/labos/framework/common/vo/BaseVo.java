package cn.labsoft.labos.framework.common.vo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import cn.labsoft.labos.utils.BeanUtils;

/**
 * 
 * <strong>Title : BaseVo </strong>. <br>
 * <strong>Description :所有Vo的父类, 加入了分页参数</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class BaseVo implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String ids[];
	private String uuid;
	private String isDel;
	private String createTime; //创建时间
	private String createUserId; //创建人
	private String tenantId;
	private String status;
	private String isOper;     //流程下 是否可操作
	private String auditMessage = "";  
	private String auditResult = "";  
	private String auditTime = "";
	private Integer sort;
	private String startDate;
	private String endDate;
	
	private String fileName;
	private String filePath;
	private String typeId;
	private List list;
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * @Title:  source 转换到当前 vo 
	 * @Description: TODO
	 * @param source 
	 * @return 返回类型 
	 * @throws
	 */
	public Object toVo(Object source) {
		return toVo(source, this, new String[]{});
	}
	/**
	 * @Title:  source 转换到 source 当前 vo 
	 * @Description: TODO
	 * @param source
	 * @param target
	 * @return 返回类型 
	 * @throws
	 */
	public Object toVo(Object source,Object target) {
		return toVo(source, target, new String[]{});
	}
	/**
	 * @Title:  对象转化
	 * @Description: TODO
	 * @param po
	 * @param vo
	 * @param ignoreProperty 
	 * @return 返回类型 
	 * @throws
	 */
	public Object toVo(Object po,Object vo,String[] ignoreProperty) {
		BeanUtils.copyProperties(po, vo,ignoreProperty);
		return vo;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getUuid() {
		if(uuid==null)uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getAuditMessage() {
		return auditMessage;
	}
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsOper() {
		return isOper;
	}
	public void setIsOper(String isOper) {
		this.isOper = isOper;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}  
	
}

