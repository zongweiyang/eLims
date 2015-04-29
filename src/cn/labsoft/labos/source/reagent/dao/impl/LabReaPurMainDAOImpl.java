package cn.labsoft.labos.source.reagent.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reagent.dao.ILabReaPurMainDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaPurMain;
import cn.labsoft.labos.utils.DateUtils;

/**
 * 
 * <strong>Title : LabReaPurMainDAOImpl </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 22, 2014 3:01:02 PM </strong>. <br>
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
@Repository(value="labReaPurMainDAO")
public class LabReaPurMainDAOImpl extends BaseDAO implements ILabReaPurMainDAO {
	private static Log log = LogFactory.getLog(LabReaPurMainDAOImpl.class);

	@Override
	public LabReaPurMain addLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException {
		try {
			labReaPurMain.setIsDel(Constants_Source.N);
			labReaPurMain.setCreateTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			super.save(labReaPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabReaPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReaPurMain;
	}

	@Override
	public boolean deleteLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException {
		try {
			super.delete(labReaPurMain);
		} catch (Exception ex) {
			log.error("LabReaPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabReaPurMain> getLabReaPurBySql(String hql)
			throws GlobalException {
		try {
			List<LabReaPurMain> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			log.error("LabReaPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}

	}

	@Override
	public PageResult getLabReaPurPageResult(String hql, PageResult pageResult)
			throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabReaPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException {
		try {
			super.update(labReaPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabReaPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
