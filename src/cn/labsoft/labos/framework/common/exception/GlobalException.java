package cn.labsoft.labos.framework.common.exception;

import cn.labsoft.labos.framework.common.log.Log4J;

/**
 * 
 * <strong>Title : GlobalException </strong>. <br>
 * <strong>Description : 自定义抛出的异常</strong> <br>
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
@SuppressWarnings("serial")
public class GlobalException extends Exception {
	public GlobalException() {
		super();
		Log4J.error(this+":"+this.getStackTrace()[0]);
	}

	public GlobalException(String msg) {
		super(msg);
		Log4J.error(this+":"+this.getStackTrace()[0]);
	}
	
	public GlobalException(Exception e) {
		super(e);
		Log4J.error(e+":"+e.getStackTrace()[0]);
	}

	public GlobalException(String msg, Exception e) {
		super(msg, e);
		Log4J.error(msg+":"+e+":"+e.getStackTrace()[0]);
		
	}
}
