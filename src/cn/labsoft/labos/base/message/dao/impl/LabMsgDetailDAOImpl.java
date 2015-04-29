package cn.labsoft.labos.base.message.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.message.dao.ILabMsgDetailDAO;
import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
@Repository(value="labMsgDetailDAO")
public class LabMsgDetailDAOImpl extends BaseDAO implements ILabMsgDetailDAO {


	@Override
	public LabMsgDetail addLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException {
		try {
		super.save(labMsgDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMsgDetail;
	}

	@Override
	public boolean deleteLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException {
		try{
		super.delete(labMsgDetail);
		return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabMsgDetail getLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException {
		try{
		super.findById(LabMsgDetail.class, labMsgDetail.getId());
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMsgDetail;
	}

	@Override
	public LabMsgDetail updateLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException {
		try{
		super.update(labMsgDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMsgDetail;
	}

}
