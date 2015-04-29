package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfStepLogsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfStepLogs;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 步骤实例 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfStepLogsDAO")
public class LabWfStepLogsDAOImpl extends BaseDAO implements ILabWfStepLogsDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStepLogs> getWfStepLogsListByBusId(String busId) throws GlobalException {
		String hql="FROM LabWfStepLogs WHERE busId='"+busId+"' ORDER BY auditTime DESC";
		List<LabWfStepLogs> poList=super.find(hql);
		return poList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStepLogs> getWfStepLogsList(String busId, String code) throws GlobalException {
		String hql="FROM LabWfStepLogs WHERE busId='"+busId+"'";
		if(code!=null&&code.length()>0){
			hql+=" AND code='"+code+"'";
		}
		hql+=" ORDER BY auditTime DESC";
		List<LabWfStepLogs> poList=super.find(hql);
		return poList;
	}

	


}