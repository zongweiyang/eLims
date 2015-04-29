package cn.labsoft.labos.base.desktop.dao;

import cn.labsoft.labos.base.desktop.entity.LabDesktopstyle;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : ILabDutyDAO </strong>. <br>
 * <strong>Description : 用户职务管理数据访问对象接口</strong> <br>
 * <strong>Create on : 2009-12-11 下午05:21:06 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author Charles<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public interface ILabDesktopstyleDAO extends IBaseDAO {
	public LabDesktopstyle addLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException;

	public boolean delLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException;

	public boolean updateLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException;

	public LabDesktopstyle getLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException;

}
