package cn.labsoft.labos.base.logs.dao.impl;

import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;

import cn.labsoft.labos.base.logs.dao.ILabLogRecordDAO;
import cn.labsoft.labos.base.logs.entity.LabLogRecord;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labLogRecordDAO")
public class LabLogRecordDAOImpl extends BaseDAO
  implements ILabLogRecordDAO
{

  public LabLogRecord addLabLogrecord(LabLogRecord labLogrecord) throws GlobalException
  {
    try {
      super.save(labLogrecord);
    } catch (Exception ex) {
    	Log4J.error("labLogrecordDAOImpl error...." + ex.getMessage(), ex);
    	throw new GlobalException("" + ex.getMessage());
    }
    return labLogrecord;
  }

  public boolean delLabLogrecord(LabLogRecord labLogrecord) throws GlobalException
  {
    try {
      super.delete(labLogrecord);
    } catch (Exception ex) {
    	Log4J.error("labLogrecordDAOImpl error...." + ex.getMessage(), ex);
    	throw new GlobalException("" + ex.getMessage());
    }
    return true;
  }

  public LabLogRecord getLabLogrecord(LabLogRecord labLogrecord) throws GlobalException
  {
    try {
    	labLogrecord = (LabLogRecord)super.findById(LabLogRecord.class, 
    			labLogrecord.getId());
      return labLogrecord;
    } catch (Exception ex) {
    	throw new GlobalException("" + ex.getMessage());
    	}
  }

  public boolean updateLabLogrecord(LabLogRecord labLogrecord) throws GlobalException
  {
    try
    {
      super.update(labLogrecord);
    } catch (Exception ex) {
    	Log4J.error("LabLogrecordDAOImpl error...." + ex.getMessage(), ex);
    	throw new GlobalException("" + ex.getMessage());
    }
    return true;
  }

	@Override
	public LabLogRecord addLabLogrecord4Bus(String content,
			String busId, String busName, String busType, String operator) throws GlobalException {
		SessionContainer son=(SessionContainer) ActionContext.getContext().getSession().get(SessionContainer.Session_Container);
		try {
			LabLogRecord labLogrecord=new LabLogRecord();
			labLogrecord.setOperatorid(son.getUserId());
			labLogrecord.setOperator(son.getUserName());
			labLogrecord.setContent(content);
			labLogrecord.setWorkId(busId);
			labLogrecord.setWorkTable(busName);
			labLogrecord.setModule(busType);
			labLogrecord.setMethod(operator);
			labLogrecord.setIp(son.getIp());
			labLogrecord.setDateTime(DateUtils.getCurrDate());
			super.save(labLogrecord);
			return labLogrecord;
		} catch (RuntimeException e) {
			Log4J.error("LabLogrecordDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}
}