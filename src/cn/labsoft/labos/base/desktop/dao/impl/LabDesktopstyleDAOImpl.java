package cn.labsoft.labos.base.desktop.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.desktop.dao.ILabDesktopstyleDAO;
import cn.labsoft.labos.base.desktop.entity.LabDesktopstyle;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : LabDutyDAOImpl </strong>. <br>
 * <strong>Description : 用户职务管理数据访问对象/strong> <br>
 * <strong>Create on : 2009-12-11 下午05:39:51 </strong>. <br>
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
@Repository(value="labDesktopstyleDAO")
public class LabDesktopstyleDAOImpl extends BaseDAO implements
		ILabDesktopstyleDAO {
	private static Log log = LogFactory.getLog(LabDesktopstyleDAOImpl.class);

	@Override
	public LabDesktopstyle addLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException {
		save(labDesktopstyle);
		return labDesktopstyle;
	}

	@Override
	public boolean delLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException {
		try {
			super.delete(labDesktopstyle);
		} catch (Exception ex) {
			log.error("LabDesktopstyleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabDesktopstyle getLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException {
		try {
			labDesktopstyle = (LabDesktopstyle) super.findById(
					LabDesktopstyle.class, labDesktopstyle.getId());
			return labDesktopstyle;
		} catch (Exception ex) {
			log.error("LabDesktopstyleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabDesktopstyle(LabDesktopstyle labDesktopstyle) throws GlobalException {
		try {
			super.update(labDesktopstyle);
		} catch (Exception ex) {
			log.error("LabDesktopstyleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
