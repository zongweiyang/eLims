package cn.labsoft.labos.framework.common.vo;
/**
 * 
 * <strong>Title : SubDivVo </strong>. <br>
 * <strong>Description : 用来给通用弹出层做现实数据的vo,包含id和lable(显示名称)</strong> <br>
 * <strong>Create on : 2009-12-30 上午11:20:26  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author TonyLee<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class CommonVo extends BaseVo{
	
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String lable;
	
	
	
	public CommonVo() {
		
	}
	
	public CommonVo(String id, String lable) {
		super();
		this.id = id;
		this.lable=lable;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
}
