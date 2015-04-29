package cn.labsoft.labos.framework.common.error.action;

import cn.labsoft.labos.framework.common.action.BaseAction;

/**
 * 
 * <strong>Title : ExceptionAction </strong>. <br>
 * <strong>Description : 错误处理Action,用来对系统的Exception的跳转进行重新定向</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class ExceptionAction extends BaseAction {

	public String processException() {
		return SUCCESS;
	}
}
