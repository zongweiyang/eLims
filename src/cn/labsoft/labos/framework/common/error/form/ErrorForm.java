package cn.labsoft.labos.framework.common.error.form;

import java.io.Serializable;

/**
 * 
 * <strong>Title : ErrorForm </strong>. <br>
 * <strong>Description : 错误对象,其中包含了错误信息以及出错处理页面等信息</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
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
public class ErrorForm implements Serializable {

	private String displayErrorName = "当前请求页面不存在!";// 显示给用户的错误信息
	// private String errorMessage;
	private String gotoUrl = "";// 错误处理页

	// private String fromUrl;//返回哪里
	public String getDisplayErrorName() {
		return displayErrorName;
	}

	public void setDisplayErrorName(String displayErrorName) {
		this.displayErrorName = displayErrorName;

	}

	// public String getErrorMessage() {
	// return errorMessage;
	// }
	// public void setErrorMessage(String errorMessage) {
	// this.errorMessage = errorMessage;
	// }
	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;

	}
	// public String getFromUrl() {
	// return fromUrl;
	// }
	// public void setFromUrl(String fromUrl) {
	// this.fromUrl = fromUr;
	// }

}
