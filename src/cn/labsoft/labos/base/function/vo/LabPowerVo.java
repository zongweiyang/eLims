package cn.labsoft.labos.base.function.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 系统功能用户权限VO
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@SuppressWarnings("serial")
public class LabPowerVo extends BaseVo {
	private String url;
	private boolean hasPower;
	private String action;
	private String img;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getHasPower() {
		return hasPower;
	}

	public void setHasPower(boolean hasPower) {
		this.hasPower = hasPower;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
