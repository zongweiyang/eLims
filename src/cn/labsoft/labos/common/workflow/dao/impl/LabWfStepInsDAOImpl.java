package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfStepInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.common.workflow.entity.LabWfStepIns;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 步骤实例 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfStepInsDAO")
public class LabWfStepInsDAOImpl extends BaseDAO implements ILabWfStepInsDAO {


	@SuppressWarnings("unchecked")
	@Override
	public LabWfStepIns getWfStepIns(String busId, String stepId) throws GlobalException {
		String hql="FROM LabWfStepIns WHERE process.id='"+busId+"' AND step.id='"+stepId+"' ORDER BY status asc";
		List<LabWfStepIns> poList=super.find(hql);
		LabWfStepIns po=null;
		if(poList!=null&&poList.size()>0){
			po=poList.get(0);
		}
		return po;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStepIns> getWfStepInsList(String busId) throws GlobalException {
		String hql="FROM LabWfStepIns WHERE process.id='"+busId+"' ORDER BY startDate DESC";
		List<LabWfStepIns> poList=super.find(hql);
		return poList;
	}

	

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LabWfStepIns> getThisNextStepIns(String busId, String stepName) {
//		List<LabWfStepIns> stepInsList=new ArrayList<LabWfStepIns>();
//		String hql="FROM LabWfProcessIns WHERE busId='"+busId+"' ";
//		List<LabWfProcessIns> insList=super.find(hql);
//		if(null!=insList&&insList.size()==1){
//			LabWfProcessIns ins=insList.get(0);
//			String subhql="FROM LabWfPath WHERE processId='"+ins.getProcess().getId()+"'" +
//			" AND startStep.name like '"+stepName+"' ";
//			List<LabWfPath> stepList=super.find(subhql);
//			if(null!=stepList&&stepList.size()>0){
//				for (LabWfPath labWfPath : stepList) {
//					LabWfStepIns stepIns=new LabWfStepIns();
//					stepIns.setStep(labWfPath.getEndStep());
//					stepIns.setStepName(labWfPath.getEndStepName());
//					String subhqlx="FROM LabWfPathVar WHERE processId='"+ins.getProcess().getId()+"'" +
//					" AND path.id= '"+labWfPath.getId()+"' ";
//					List<LabWfPathVar> pathVarList=super.find(subhqlx);
//					stepIns.setPahtVarList(pathVarList);
//					stepInsList.add(stepIns);
//				}
//			}
//		}	
//		return stepInsList;
//	}

}