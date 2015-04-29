package cn.labsoft.labos.source.consumables.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.user.dao.impl.LabUserDAOImpl;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.consumables.dao.ILabConPurMainDAO;
import cn.labsoft.labos.source.consumables.entity.LabConPurMain;
import cn.labsoft.labos.utils.DateUtils;

/**
 * 
 * <strong>Title : LabConPurMainDAOImpl </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Cconte on : Feb 22, 2014 3:01:02 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 * 
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Repository(value="labConPurMainDAO")
public class LabConPurMainDAOImpl extends BaseDAO implements ILabConPurMainDAO {
	private static Log log = LogFactory.getLog(LabUserDAOImpl.class);

	@Override
	public LabConPurMain addLabConPur(LabConPurMain labConPurMain)
			throws GlobalException {
		try {
			labConPurMain.setIsDel(Constants_Source.N);
			labConPurMain.setCreateTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			super.save(labConPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabConPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConPurMain;
	}

	@Override
	public boolean deleteLabConPur(LabConPurMain labConPurMain)
			throws GlobalException {
		try {
			super.delete(labConPurMain);
		} catch (Exception ex) {
			log.error("LabConPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabConPurMain> getLabConPurBySql(String hql)
			throws GlobalException {
		try {
			List<LabConPurMain> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			log.error("LabConPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}

	}

	@Override
	public PageResult getLabConPurPageResult(String hql, PageResult pageResult)
			throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabConPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabConPur(LabConPurMain labConPurMain)
			throws GlobalException {
		try {
			super.update(labConPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabConPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
