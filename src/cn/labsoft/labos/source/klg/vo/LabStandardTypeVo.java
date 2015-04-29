package cn.labsoft.labos.source.klg.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabStandardTypeVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String code;
	private String demo;
	private String parentid;
    private String isDel;

    
	@Override
	public String getIsDel() {
		return isDel;
	}

	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDemo() {
		return this.demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
