package cn.labsoft.labos.framework.common.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.DateUtils;
@MappedSuperclass
public class Po {
	
	private String id; //主键
	private String isDel=Constants_Base.N;
	private String createTime=DateUtils.getCurrDateTimeStr(); //创建时间
	private String createUserId; //创建人
	private String tenantId;
	private Integer sort = 0;
	
	static final String N = Constants_Base.N;
	static final String Y = Constants_Base.Y;;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Id
	@Column(length = 32, nullable = false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 转换 vo 实例到当前 po
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public Object toPo(Object vo) {
		return toPo(vo, this,  new String[]{}) ;
	}
	/**
	 * 转换 vo 实例到当前 po
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo 
	 * @param @param po
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public Object toPo(Object vo,Object po) {
		return toPo(vo, po,  new String[]{}) ;
	}
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo vo
	 * @param @param po po
	 * @param @param ignoreProperty 例外的实例
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public Object toPo(Object vo,Object po,String[] ignoreProperty) {
		BeanUtils.copyProperties(vo, po,ignoreProperty);
		return po;
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
		if(createUserId==null&&SessionContainer.getSessionContainer()!=null){
			createUserId=SessionContainer.getSessionContainer().getUserId();
		}
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name="tenantid")
	public String getTenantId() {
		try{
		if(tenantId==null&&SessionContainer.getSessionContainer()!=null){
			if(!SessionContainer.getSessionContainer().getUserId().equals("0")){
				tenantId=SessionContainer.getSessionContainer().getOrgTenantId()+"*"+SessionContainer.getSessionContainer().getUserId();
			}else{
				tenantId=SessionContainer.getSessionContainer().getOrgTenantId();
			}
		}
		}catch(Exception e){
			tenantId = "0";
		}
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
