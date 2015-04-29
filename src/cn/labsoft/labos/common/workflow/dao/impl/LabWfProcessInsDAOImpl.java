package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfConfig;
import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.common.workflow.entity.LabWfPathIns;
import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.common.workflow.entity.LabWfProcess;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.common.workflow.entity.LabWfStep;
import cn.labsoft.labos.common.workflow.entity.LabWfStepIns;
import cn.labsoft.labos.common.workflow.entity.LabWfStepLogs;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 流程实例 数据访问层
 * 
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfProcessInsDAO")
public class LabWfProcessInsDAOImpl extends BaseDAO implements
		ILabWfProcessInsDAO {

	@Override
	public LabWfProcessIns getWfProcessByBusId(String busId) throws GlobalException {
		String hql = "FROM LabWfProcessIns WHERE isDel='"+Constants_Base.N+"' AND busId='" + busId + "'";
		try {
			return (LabWfProcessIns) super.find(hql, 0);
		} catch (RuntimeException e) {
			Log4J.error("labWfProcessInsDAO.."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabWfProcessIns getWfProcessById(String id) throws GlobalException {
		try {
			LabWfProcessIns ins = (LabWfProcessIns) super.findById(
					LabWfProcessIns.class, id);
			return ins;
		} catch (RuntimeException e) {
			Log4J.error("labWfProcessInsDAO.."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}
//	/**
//	 * 流程实例（没有父流程的实例使用）
//	 * @param busId 业务id
//	 * @param busType 业务类型
//	 * @param funId 功能id
//	 * @param status 当前状态
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public LabWfProcessIns addLabWfProcessIns(String busId,String busType) {
//		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
//		// 获取或修改子流程
//		String hql="FROM LabWfProcessIns WHERE isDel='"+Constants.N+"' AND busId='"+busId+"' ";
//		LabWfProcessIns ins=(LabWfProcessIns)super.find(hql,0);
//		if(ins==null){
//			ins=new LabWfProcessIns();
//			String busHql = "FROM LabWfConfig WHERE isDel='"+Constants.N+"' AND code='" + busType+"'";
//			LabWfConfig wfConfig =(LabWfConfig)super.find(busHql,0);
//			if (wfConfig != null) {
//				LabWfProcess process = wfConfig.getWfProcess();
//				ins.setBusId(busId);
//				ins.setType(busType);
//				ins.setProcess(process);
//				ins.setProcessName(process.getName());
//				ins.setOrgId(son.getOrgId());
//				ins.setOrgName(son.getOrgName());
//				ins.setUserId(son.getUserId());
//				ins.setUserName(son.getUserName());
//				ins.setStartDate(DateUtils.getCurrDate());
//				ins.setNum(0);
//				super.save(ins);
//			}else{
//				return null;
//			}
//		}
//		return ins;
//	}
	
	@SuppressWarnings("unchecked")
	public LabWfProcessIns addLabWfStepIns(String stepBusId,String nextStepBusId,String busType,String funId,String msg,String result,String autoType,String[] nextId,String nextType) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabWfProcessIns ins=null;
		LabWfStep step=null;
		//获取实例中业务在当前功能下的未完成实例节点
		LabWfStepIns stepins=null;
		String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+stepBusId+"'";
		List<LabWfStepIns> list=super.find(stephql);
		if(list!=null&&list.size()==1){
			stepins=list.get(0);
		}else if(list!=null&&list.size()>1){
			stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"'  AND code='"+funId+"' AND stepBusId='"+stepBusId+"'";
			stephql+=" AND userId='"+son.getUserId()+"'";
			stepins=(LabWfStepIns)super.find(stephql,0);
			if(stepins==null){
				stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+stepBusId+"'";
				stephql+=" AND tenantId like '"+son.getOrgTenantId()+"%'";
				stepins=(LabWfStepIns)super.find(stephql,0);
			}
		}
		if(stepins==null){
			ins=new LabWfProcessIns();
			String busHql = "FROM LabWfConfig WHERE isDel='"+Constants_Base.N+"' AND code='" + busType+"'";
			LabWfConfig wfConfig =(LabWfConfig)super.find(busHql,0);
			if (wfConfig != null) {
				LabWfProcess process = wfConfig.getWfProcess();
				ins.setBusId(stepBusId);
				ins.setType(busType);
				ins.setProcess(process);
				ins.setProcessName(process.getName());
				ins.setOrgId(son.getOrgId());
				ins.setOrgName(son.getOrgName());
				ins.setUserId(son.getUserId());
				ins.setUserName(son.getUserName());
				ins.setStartDate(DateUtils.getCurrDate());
				ins.setNum(0);
				super.save(ins);
			}
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return null;
			}
			String subhql="FROM LabWfStep WHERE processId='"+ins.getProcess().getId()+"'" +
			" AND code like '"+funId+"'";
			step=(LabWfStep)super.find(subhql,0);
			
			stepins=new LabWfStepIns();
			stepins.setBusId(ins.getBusId());
			stepins.setStepBusId(stepBusId);
			stepins.setBusType(busType);
			stepins.setProcess(ins);
			stepins.setStep(step);
			stepins.setStepName(step.getName());
			stepins.setName(step.getName());
			stepins.setCode(funId);
			stepins.setStartDate(new Date());
			stepins.setEndDate(new Date());
			stepins.setOverDate(step.getOverDate());
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			stepins.setStatus(LabWfConstant.BUS_PROCESS_RUN);
			stepins.setNum(step.getNum());
			super.save(stepins);
			
			String status=ins.getStatus();
			if(status==null)status="";
			if(!status.contains(funId)){
				status+=funId+",";
			}
			ins.setStatus(status);
			super.update(ins);
		}
		else{
			String hql="FROM LabWfProcessIns WHERE id='"+stepins.getProcess().getId()+"'";
			ins=(LabWfProcessIns)super.find(hql, 0);
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return null;
			}
			String subhql="FROM LabWfStep WHERE processId='"+ins.getProcess().getId()+"'" +
			" AND code like '"+funId+"'";
			step=(LabWfStep)super.find(subhql,0);
			
			stepins.setStatus(LabWfConstant.BUS_PROCESS_RUN);
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			super.update(stepins);
		}
		if(ins==null||step==null){
			return null;
		}
		if(!StrUtils.isBlankOrNull(result)){
			// 获取该节点的指向方向，auditResult与每个方向上的变量比对，获取下一步走向
		    String hql2="FROM LabWfPath WHERE startStep.id='"+step.getId()+"'";
			List<LabWfPath> pathDefList=super.find(hql2);
			if(pathDefList!=null&&pathDefList.size()>0){
				for (LabWfPath path : pathDefList) {
					// 获取该线路上的定义条件，并且比对，获取下一步方向
					boolean flag=isQualified(path, result);
					if (flag) {//若通过所有的条件
						// 日志记录
						LabWfStepLogs stepLogs=new LabWfStepLogs();
						stepLogs.setBusId(ins.getBusId());
						stepLogs.setProcessInsId(ins.getId());
						stepLogs.setCode(funId);
						stepLogs.setStepins(stepins);
						stepLogs.setStepName(stepins.getStepName());
						stepLogs.setAuditMessage(msg);
						stepLogs.setAuditResult(result);
						stepLogs.setAuditTime(DateUtils.getCurrDateStr());
						stepLogs.setUserId(son.getUserId());
						stepLogs.setUserName(son.getUserName());
						Date startDate=stepins.getStartDate();
						Date endDate =stepins.getEndDate();
						if(endDate!=null&&startDate!=null){
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}else if(startDate!=null){
							endDate=new Date();
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}
						super.save(stepLogs);
						initNextStep(ins, stepins, path,nextStepBusId,autoType,nextId,nextType,result);
						ins.setResult(LabWfConstant.BUS_GO);
						break;//当前只支持一个分支流转
					}
				}
			}
		}
		return ins;
	}
	public LabWfProcessIns addLabWfStepIns4Other(String busId, String busType) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		// 获取或修改流程,若未配置，则返回null
		LabWfProcessIns ins=this.getWfProcessByBusId(busId);
		if(ins==null){
			ins=new LabWfProcessIns();
			String busHql = "FROM LabWfConfig WHERE isDel='"+Constants_Base.N+"' AND code='" + busType+"'";
			LabWfConfig wfConfig =(LabWfConfig)super.find4All(busHql,0);
			if (wfConfig != null) {
				LabWfProcess process = wfConfig.getWfProcess();
				ins.setBusId(busId);
				ins.setType(busType);
				ins.setProcess(process);
				ins.setProcessName(process.getName());
				ins.setOrgId(son.getOrgId());
				ins.setOrgName(son.getOrgName());
				ins.setUserId(son.getUserId());
				ins.setUserName(son.getUserName());
				ins.setStartDate(DateUtils.getCurrDate());
				ins.setNum(0);
				super.save(ins);
			}
		}
		// 获取子流程的第一个节点
		String subhql="FROM LabWfPath WHERE startStep.type='Start' AND processId='"+ins.getProcess().getId()+"'";
		LabWfPath path=(LabWfPath)super.find(subhql,0);
		if(path==null){
			return null;
		}
		LabWfStep step=path.getEndStep();
		if(step==null){
			return null;
		}
		String stephql="FROM LabWfStepIns WHERE process.id='"+ins.getId()+"' AND code='"+step.getCode()+"'";
		LabWfStepIns stepins=(LabWfStepIns)super.find4All(stephql, 0);
		if(stepins==null){
			
//			if(ins==null||ins.getId()==null||ins.getProcess()==null){
//				return null;
//			}
//			String subhql="FROM LabWfPath WHERE startStep.type='Start' AND processId='"+ins.getProcess().getId()+"'";
//			LabWfPath path=(LabWfPath)super.find4All(subhql,0);
//			if(path==null){
//				return null;
//			}
//			step=path.getEndStep();
			
			stepins=new LabWfStepIns();
			stepins.setBusId(busId);
			stepins.setStepBusId(busId);
			stepins.setBusType(busType);
			stepins.setProcess(ins);
			stepins.setStep(step);
			stepins.setStepName(step.getName());
			stepins.setName(step.getName());
			stepins.setCode(step.getCode());
			stepins.setStartDate(new Date());
			stepins.setEndDate(new Date());
			stepins.setOverDate(step.getOverDate());
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			stepins.setStatus(LabWfConstant.BUS_PROCESS_RUN);
			stepins.setNum(step.getNum());
			super.save(stepins);
			
			String status=ins.getStatus();
			if(status==null)status="";
			if(!status.contains(step.getCode())){
				status+=step.getCode()+",";
			}
			ins.setStatus(status);
			super.update(ins);
		}
//		if(ins==null||step==null){
//			return null;
//		}
		return ins;
	}
	@SuppressWarnings("unchecked")
	public LabWfProcessIns addLabWfStepIns(String busId,String busType,String funId,String msg,String result) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabWfProcessIns ins=null;
		LabWfStep step=null;
		//获取实例中业务在当前功能下的未完成实例节点
		LabWfStepIns stepins=null;
		String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+busId+"'";
		List<LabWfStepIns> list=super.find4All(stephql);
		if(list!=null&&list.size()==1){
			stepins=list.get(0);
		}else if(list!=null&&list.size()>1){
			stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+busId+"'";
			stephql+=" AND userId='"+son.getUserId()+"'";
			stepins=(LabWfStepIns)super.find4All(stephql,0);
			if(stepins==null){
				stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+busId+"'";
				stephql+=" AND userId='"+son.getOrgId()+"'";
				stepins=(LabWfStepIns)super.find4All(stephql,0);
			}
		}
		if(stepins==null){
			ins=new LabWfProcessIns();
			String busHql = "FROM LabWfConfig WHERE isDel='"+Constants_Base.N+"' AND code='" + busType+"'";
			LabWfConfig wfConfig =(LabWfConfig)super.find4All(busHql,0);
			if (wfConfig != null) {
				LabWfProcess process = wfConfig.getWfProcess();
				ins.setBusId(busId);
				ins.setType(busType);
				ins.setProcess(process);
				ins.setProcessName(process.getName());
				ins.setOrgId(son.getOrgId());
				ins.setOrgName(son.getOrgName());
				ins.setUserId(son.getUserId());
				ins.setUserName(son.getUserName());
				ins.setStartDate(DateUtils.getCurrDate());
				ins.setNum(0);
				super.save(ins);
			}
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return null;
			}
			String subhql="FROM LabWfStep WHERE processId='"+ins.getProcess().getId()+"'" +
			" AND code like '"+funId+"'";
			step=(LabWfStep)super.find4All(subhql,0);
			
			stepins=new LabWfStepIns();
			stepins.setBusId(busId);
			stepins.setStepBusId(busId);
			stepins.setBusType(busType);
			stepins.setProcess(ins);
			stepins.setStep(step);
			stepins.setStepName(step.getName());
			stepins.setName(step.getName());
			stepins.setCode(funId);
			stepins.setStartDate(new Date());
			stepins.setEndDate(new Date());
			stepins.setOverDate(step.getOverDate());
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			stepins.setStatus(LabWfConstant.BUS_PROCESS_RUN);
			stepins.setNum(step.getNum());
			super.save(stepins);
			
			String status=ins.getStatus();
			if(status==null)status="";
			if(!status.contains(funId)){
				status+=funId+",";
			}
			ins.setStatus(status);
			super.update(ins);
		}
		else{
			String hql="FROM LabWfProcessIns WHERE id='"+stepins.getProcess().getId()+"'";
			ins=(LabWfProcessIns)super.find4All(hql, 0);
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return null;
			}
			String subhql="FROM LabWfStep WHERE processId='"+ins.getProcess().getId()+"'" +
			" AND code like '"+funId+"'";
			step=(LabWfStep)super.find4All(subhql,0);
			
			
			stepins.setStatus(LabWfConstant.BUS_PROCESS_RUN);
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			super.update(stepins);
		}
		if(ins==null||step==null){
			return null;
		}
		if(!StrUtils.isBlankOrNull(result)){
			// 获取该节点的指向方向，auditResult与每个方向上的变量比对，获取下一步走向
		    String hql2="FROM LabWfPath WHERE startStep.id='"+step.getId()+"'";
			List<LabWfPath> pathDefList=super.find4All(hql2);
			if(pathDefList!=null&&pathDefList.size()>0){
				for (LabWfPath path : pathDefList) {
					// 获取该线路上的定义条件，并且比对，获取下一步方向
					boolean flag=isQualified(path, result);
					if (flag) {//若通过所有的条件
						// 日志记录
						LabWfStepLogs stepLogs=new LabWfStepLogs();
						stepLogs.setBusId(busId);
						stepLogs.setProcessInsId(ins.getId());
						stepLogs.setCode(funId);
						stepLogs.setStepins(stepins);
						stepLogs.setStepName(stepins.getStepName());
						stepLogs.setAuditMessage(msg);
						stepLogs.setAuditResult(result);
						stepLogs.setAuditTime(DateUtils.getCurrDateStr());
						stepLogs.setUserId(son.getUserId());
						stepLogs.setUserName(son.getUserName());
						Date startDate=stepins.getStartDate();
						Date endDate =stepins.getEndDate();
						if(endDate!=null&&startDate!=null){
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}else if(startDate!=null){
							endDate=new Date();
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}
						super.save(stepLogs);
						// 实例化下一个节点
						initNextStep(ins, stepins, path,busId,LabWfConstant.BUS_STEP_ALL,null,null,result);
						ins.setResult(LabWfConstant.BUS_GO);
						break;//当前只支持一个分支流转
					}
				}
			}
		}
		return ins;
	}
	//初始化下一步
	@SuppressWarnings("unchecked")
	public void initNextStep(LabWfProcessIns ins,LabWfStepIns stepins,LabWfPath path,String nextStepBusId,String type,String[] nextId,String nextType,String result) throws GlobalException{
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		
		//完全提交节点修改节点状态
		if(type.equals(LabWfConstant.BUS_STEP_ALL)){
			stepins.setEndDate(DateUtils.getCurrDate());
			stepins.setStatus(LabWfConstant.BUS_PROCESS_END);
			super.update(stepins);
			
			String status=ins.getStatus();
			if(status==null)status="";
			String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+stepins.getCode()+"' ";
			List list=super.find4All(stephql);
			if(list==null||list.size()<=0){
				//当前节点处没有该实例的任何业务，则移除
				status=status.replace(stepins.getCode(), "");
			}
			status=status.replace(",,", ",");
			ins.setStatus(status);
			super.update(ins);
		}
		
		// 实例化流程下一步
		LabWfStep endStep=path.getEndStep();
		if(null!=endStep&&endStep.getType().equals("Node")){
			if(nextType!=null&&nextId!=null&&nextType.equals("user")){
				for (String userId : nextId) {
					String tenantIdx="";
					String nextName="";
					if(userId==null||userId.trim().length()==0)continue;
					String hqlx="FROM LabUserFun WHERE 1=1";
					hqlx+=" AND user.id='"+userId.trim()+"'";
					hqlx+=" AND function.id='"+endStep.getCode()+"'";
					List<LabUserFun> ufList=super.find4All(hqlx);
					if(ufList!=null){
						for (LabUserFun labUserFun : ufList) {
							tenantIdx+=labUserFun.getTenantId()+",";
							nextName+=labUserFun.getUser().getName()+",";
						}
						if(tenantIdx.endsWith(",")){
							tenantIdx=tenantIdx.substring(0, tenantIdx.length()-1);
							nextName=nextName.substring(0, nextName.length()-1);
						}
					}else{
						tenantIdx=son.getOrgTenantId();//所指定的下一步人没有权限，则取部门权限
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId='"+tenantIdx+"'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find4All(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(tenantIdx);
						endStepIns.setUserId(userId);
						endStepIns.setUserName(nextName);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find4All(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else if(nextType!=null&&nextId!=null&&nextType.equals("org")){
				for (String orgId : nextId) {
					String tenantIdx="";
					String nextName="";
					if(orgId==null||orgId.trim().length()==0)continue;
					String hqlx="FROM LabOrg WHERE isDel='"+Constants_Base.N+"' AND id='"+orgId+"'";
					LabOrg org=(LabOrg)super.find4All(hqlx,0);
					if(org!=null){
						tenantIdx=org.getTenantId();
						nextName=org.getName();
					}else{
						//传进来的既不是用户也不是组织id，数据异常
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId='"+tenantIdx+"'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find4All(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(tenantIdx);
						endStepIns.setUserId(orgId);
						endStepIns.setUserName(nextName);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find4All(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else if(result!=null&&result.equals(LabWfConstant.BUS_BACK)){
				//先根据线路找退回节点(只适合单业务流转退回)
				String stephql="FROM LabWfStepIns WHERE isDel='"+Constants_Base.N+"' " +
				"AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
				stephql+=" AND step.id='"+endStep.getId()+"'";
				LabWfStepIns endStepIns=(LabWfStepIns)super.find4All(stephql, 0);
				if(endStepIns!=null){
					endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
					endStepIns.setEndDate(null);
					endStepIns.setOverDate(endStep.getOverDate());
					super.update(endStepIns);
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find4All(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else {
				String ufHql="FROM LabFunction WHERE id='"+endStep.getCode()+"'";
				LabFunction fun=(LabFunction)super.find4All(ufHql, 0);
				if(null!=fun.getDataStr()&&fun.getDataStr().equals("org")){
					int n=1;
					try {
						n=Integer.valueOf(fun.getValStr()==null?"1":fun.getValStr());
						n++;
					} catch (NumberFormatException e) {
						n=1;
						throw new GlobalException("" + e.getMessage());
					}
					String orgs[]=son.getOrgTenantId().split("-");
					String orgStr="";
					if(orgs.length<=n){
						orgStr=son.getOrgTenantId();
					}else{
						for(int i=0;i<n;i++){
							orgStr+=orgs[i]+"-";
						}
						if(orgStr.endsWith("-")){
							orgStr=orgStr.substring(0, orgStr.length()-1);
						}
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId like '"+orgStr+"%'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find4All(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(orgStr);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find4All(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}
		} else{// 当前流程已经结束
			String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' ";
			List list=super.find4All(stephql);
			if(list==null||list.size()<=0){
				//当前 实例的任何业务，则置完成
				ins.setStatus(LabWfConstant.BUS_PROCESS_END);
				ins.setEndDate(DateUtils.getCurrDate());
				ins.setNum(50);
				super.update(ins);
				
				LabWfStepIns endStepIns = new LabWfStepIns();
				endStepIns.setBusId(ins.getBusId());
				endStepIns.setStepBusId(ins.getBusId());
				endStepIns.setProcess(ins);
				endStepIns.setStep(endStep);
				endStepIns.setCode(endStep.getCode());
				endStepIns.setBusType(ins.getType());
				endStepIns.setStepName(endStep.getName());
				endStepIns.setName(endStep.getName());
				endStepIns.setStatus(LabWfConstant.BUS_PROCESS_END);
				endStepIns.setStartDate(DateUtils.getCurrDate());
				endStepIns.setOverDate(endStep.getOverDate());
				endStepIns.setNum(endStep.getNum());
				endStepIns.setTenantId(son.getOrgTenantId());
				super.save(endStepIns);
				
				String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
				LabWfPathIns pathIns=(LabWfPathIns)super.find4All(hqlz, 0);
				if(pathIns==null){
					pathIns=new LabWfPathIns();
					pathIns.setProcessInsId(ins.getId());
					pathIns.setPath(path);
					pathIns.setStartStepIns(stepins);
					pathIns.setEndStepIns(endStepIns);
					pathIns.setNum("1");
					super.save(pathIns);
				}else{
					int num=1;
					try {
						num=Integer.valueOf(pathIns.getNum())+1;
					} catch (NumberFormatException e) {
						num=1;
						throw new GlobalException("" + e.getMessage());
					}
					pathIns.setNum(num+"");
					super.update(pathIns);
				}
			}
		}
	}
	//初始化下一步（不改变业务状态）
	@SuppressWarnings("unchecked")
	public void initNextStep(LabWfProcessIns ins,LabWfStepIns stepins,LabWfPath path,String nextStepBusId,String[] nextId,String nextType,String result) throws GlobalException{
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		// 实例化流程下一步
		LabWfStep endStep=path.getEndStep();
		if(null!=endStep&&endStep.getType().equals("Node")){
			if(nextType!=null&&nextId!=null&&nextType.equals("user")){
				for (String userId : nextId) {
					String tenantIdx="";
					String nextName="";
					if(userId==null||userId.trim().length()==0)continue;
					String hqlx="FROM LabUserFun WHERE 1=1";
					hqlx+=" AND user.id='"+userId.trim()+"'";
					hqlx+=" AND function.id='"+endStep.getCode()+"'";
					List<LabUserFun> ufList=super.find(hqlx);
					if(ufList!=null){
						for (LabUserFun labUserFun : ufList) {
							tenantIdx+=labUserFun.getTenantId()+",";
							nextName+=labUserFun.getUser().getName()+",";
						}
						if(tenantIdx.endsWith(",")){
							tenantIdx=tenantIdx.substring(0, tenantIdx.length()-1);
							nextName=nextName.substring(0, nextName.length()-1);
						}
					}else{
						tenantIdx=son.getOrgTenantId();//所指定的下一步人没有权限，则取部门权限
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId='"+tenantIdx+"'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(tenantIdx);
						endStepIns.setUserId(userId);
						endStepIns.setUserName(nextName);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else if(nextType!=null&&nextId!=null&&nextType.equals("org")){
				for (String orgId : nextId) {
					String tenantIdx="";
					String nextName="";
					if(orgId==null||orgId.trim().length()==0)continue;
					String hqlx="FROM LabOrg WHERE isDel='"+Constants_Base.N+"' AND id='"+orgId+"'";
					LabOrg org=(LabOrg)super.find(hqlx,0);
					if(org!=null){
						tenantIdx=org.getTenantId();
						nextName=org.getName();
					}else{
						//传进来的既不是用户也不是组织id，数据异常
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId='"+tenantIdx+"'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(tenantIdx);
						endStepIns.setUserId(orgId);
						endStepIns.setUserName(nextName);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else if(result!=null&&result.equals(LabWfConstant.BUS_BACK)){
				//先根据线路找退回节点(只适合单业务流转退回)
				String stephql="FROM LabWfStepIns WHERE isDel='"+Constants_Base.N+"' " +
				"AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
				stephql+=" AND step.id='"+endStep.getId()+"'";
				LabWfStepIns endStepIns=(LabWfStepIns)super.find(stephql, 0);
				if(endStepIns!=null){
					endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
					endStepIns.setEndDate(null);
					endStepIns.setOverDate(endStep.getOverDate());
					super.update(endStepIns);
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}else {
				String ufHql="FROM LabFunction WHERE id='"+endStep.getCode()+"'";
				LabFunction fun=(LabFunction)super.find(ufHql, 0);
				if(null!=fun.getDataStr()&&fun.getDataStr().equals("org")){
					int n=1;
					try {
						n=Integer.valueOf(fun.getValStr()==null?"1":fun.getValStr());
						n++;
					} catch (NumberFormatException e) {
						n=1;
						throw new GlobalException("" + e.getMessage());
					}
					String orgs[]=son.getOrgTenantId().split("-");
					String orgStr="";
					if(orgs.length<=n){
						orgStr=son.getOrgTenantId();
					}else{
						for(int i=0;i<n;i++){
							orgStr+=orgs[i]+"-";
						}
						if(orgStr.endsWith("-")){
							orgStr=orgStr.substring(0, orgStr.length()-1);
						}
					}
					String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+endStep.getCode()+"' AND stepBusId='"+nextStepBusId+"'";
					stephql+=" AND tenantId like '"+orgStr+"%'";
					LabWfStepIns endStepIns=(LabWfStepIns)super.find(stephql, 0);
					if(endStepIns==null){
						endStepIns = new LabWfStepIns();
						endStepIns.setBusId(ins.getBusId());
						endStepIns.setStepBusId(nextStepBusId);
						endStepIns.setProcess(ins);
						endStepIns.setStep(endStep);
						endStepIns.setCode(endStep.getCode());
						endStepIns.setBusType(ins.getType());
						endStepIns.setStepName(endStep.getName());
						endStepIns.setName(endStep.getName());
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setStartDate(DateUtils.getCurrDate());
						endStepIns.setOverDate(endStep.getOverDate());
						endStepIns.setNum(endStep.getNum());
						endStepIns.setTenantId(orgStr);
						super.save(endStepIns);
					}else{
						endStepIns.setStatus(LabWfConstant.BUS_PROCESS_UNRUN);
						endStepIns.setEndDate(null);
						endStepIns.setOverDate(endStep.getOverDate());
						super.update(endStepIns);
					}
					// 实例化迁移线路
					String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
					LabWfPathIns pathIns=(LabWfPathIns)super.find(hqlz, 0);
					if(pathIns==null){
						pathIns=new LabWfPathIns();
						pathIns.setProcessInsId(ins.getId());
						pathIns.setPath(path);
						pathIns.setStartStepIns(stepins);
						pathIns.setEndStepIns(endStepIns);
						pathIns.setNum("1");
						super.save(pathIns);
					}else{
						int num=1;
						try {
							num=Integer.valueOf(pathIns.getNum())+1;
						} catch (NumberFormatException e) {
							num=1;
							throw new GlobalException("" + e.getMessage());
						}
						pathIns.setNum(num+"");
						super.update(pathIns);
					}
					// 流程状态
					String status=ins.getStatus();
					if(!status.contains(endStepIns.getCode())){
						status+=endStepIns.getCode()+",";
					}
					ins.setStatus(status);
					ins.setNum(endStep.getNum());
					super.update(ins);
				}
			}
		} else{// 当前流程已经结束
			String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' ";
			List list=super.find(stephql);
			if(list==null||list.size()<=0){
				//当前 实例的任何业务，则置完成
				ins.setStatus(LabWfConstant.BUS_PROCESS_END);
				ins.setEndDate(DateUtils.getCurrDate());
				ins.setNum(50);
				super.update(ins);
				
				String hqlz="FROM LabWfPathIns WHERE processInsId='"+ins.getId()+"' AND path.id='"+path.getId()+"'";
				LabWfPathIns pathIns=(LabWfPathIns)super.find(hqlz, 0);
				if(pathIns==null){
					pathIns=new LabWfPathIns();
					pathIns.setProcessInsId(ins.getId());
					pathIns.setPath(path);
					pathIns.setStartStepIns(stepins);
					pathIns.setEndStepIns(null);
					pathIns.setNum("1");
					super.save(pathIns);
				}else{
					int num=1;
					try {
						num=Integer.valueOf(pathIns.getNum())+1;
					} catch (NumberFormatException e) {
						num=1;
						throw new GlobalException("" + e.getMessage());
					}
					pathIns.setNum(num+"");
					super.update(pathIns);
				}
			}
		}
	}
	//判断是否满足条件
	@SuppressWarnings("unchecked")
	public boolean isQualified(LabWfPath path,String auditResult) throws GlobalException{
		String hql3="FROM LabWfPathVar WHERE pathId='"+path.getId()+"'";
		List<LabWfPathVar> pvList=super.find(hql3);
		boolean flag=true;
		if(null!=pvList&&pvList.size()>0){
			// 定义条件与实际对比
			// '1':'=','2':'>','3':'>=','4':'<','5':'<=','6':'!='
			for (LabWfPathVar pv : pvList) {
				if(pv.getOperator()==null){
				}else if(pv.getOperator().equals("1")){
					if(!pv.getValue().equals(auditResult)){
						flag=false;
					}
				}else if(pv.getOperator().equals("2")){
					if(!(Integer.valueOf(pv.getValue())>Integer.valueOf(auditResult))){
						flag=false;
					}
				}else if(pv.getOperator().equals("3")){
					if(!(Integer.valueOf(pv.getValue())>=Integer.valueOf(auditResult))){
						flag=false;
					}
				}else if(pv.getOperator().equals("4")){
					if(!(Integer.valueOf(pv.getValue())<Integer.valueOf(auditResult))){
						flag=false;
					}
				}else if(pv.getOperator().equals("5")){
					if(!(Integer.valueOf(pv.getValue())<=Integer.valueOf(auditResult))){
						flag=false;
					}
				}else if(pv.getOperator().equals("6")){
					if(pv.getOperator().equals("6")&&pv.getValue().equals(auditResult)){
						flag=false;
					}
				}
				if(!flag){
					break;
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLabWfStepStrByInsId(String insId) throws GlobalException {
		String hql = "SELECT stepName FROM LabWfStepIns WHERE process.id='"+ insId + "'";
		hql += " AND (status='" + LabWfConstant.BUS_PROCESS_UNRUN + "' or status='" + LabWfConstant.BUS_PROCESS_RUN + "')";
		List<String> stepList = super.find(hql);
		String str = "";
		if (stepList != null) {
			for (String name : stepList) {
				if(!str.contains(name)){
					str += name + ",";
				}
			}
		}
		if (str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String[] getLabWfStepByInsId(String busId) throws GlobalException {
		String hql = "FROM LabWfStepIns WHERE stepBusId='"+ busId + "'";
		hql += " AND (status='" + LabWfConstant.BUS_PROCESS_UNRUN + "' or status='" + LabWfConstant.BUS_PROCESS_RUN + "')";
		List<LabWfStepIns> stepList = super.find(hql);
		String str = "";
		String funStr="";
		if (stepList != null) {
			for (LabWfStepIns stepIns : stepList) {
				if(!str.contains(stepIns.getStepName())){
					str += stepIns.getStepName() + ",";
					funStr+=stepIns.getCode()+"*"+stepIns.getTenantId()+",";
				}
			}
		}
		if (str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		String[] string=new String[2];
		string[0]=str;
		string[1]=funStr;
		return string;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean delLabWfProcessInsByBusId(String busId) throws GlobalException {
		boolean flag=false;
		String hql = "FROM LabWfProcessIns WHERE busId='" + busId + "' ";
		List<LabWfProcessIns> insList = super.find(hql);
		if (null != insList && insList.size() == 1) {
			try {
				LabWfProcessIns ins = insList.get(0);
				if (ins != null) {
					String hql1 = "DELETE FROM LabWfStepLogs WHERE busId='" + busId
							+ "' ";
					super.delete(hql1);
					String hql2 = "DELETE FROM LabWfPathIns WHERE processInsId='"
							+ ins.getId() + "' ";
					super.delete(hql2);
					String hql3 = "DELETE FROM LabWfStepIns WHERE process.id='"
							+ ins.getId() + "' ";
					super.delete(hql3);
					super.delete(ins);
					flag=true;
				}
			} catch (RuntimeException e) {
				Log4J.error("labWfProcessInsDAO.."+e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabWfProcessIns4delByBusId(String busId) throws GlobalException {
		boolean flag=false;
		try {
			String hql = "FROM LabWfProcessIns WHERE busId='" + busId + "' ";
			List<LabWfProcessIns> insList = super.find(hql);
			if (null != insList && insList.size()>0) {
				LabWfProcessIns ins = insList.get(0);
				if (ins != null) {
					ins.setIsDel(Constants_Base.Y);
					super.update(ins);
					String subhql = "FROM LabWfStepIns WHERE busId='" + busId + "' ";
					List<LabWfStepIns> stepList = super.find(subhql);
					if (null != stepList ) {
						for (LabWfStepIns labWfStepIns : stepList) {
							labWfStepIns.setIsDel(Constants_Base.Y);
							super.update(labWfStepIns);
						}
					}
					flag=true;
				}
			}
		} catch (RuntimeException e) {
			Log4J.error("labWfProcessInsDAO.."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkLabWfProcessIns4isBack(String busId,String stepBusId, String funId) throws GlobalException {
		String stephql="FROM LabWfStepIns WHERE status='"+LabWfConstant.BUS_PROCESS_END+"'" +
		" AND isDel='"+Constants_Base.N+"' AND busId='"+busId+"' AND code='"+funId+"' AND stepBusId='"+stepBusId+"'";
		List  pathList=super.find(stephql);
		if(pathList!=null&&pathList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean delLabWfStepIns(String curBusId, String funId, String msg) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabWfProcessIns ins=null;
		LabWfStep step=null;
		//获取实例中业务在当前功能下的未完成实例节点
		LabWfStepIns stepins=null;
		String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
		List<LabWfStepIns> list=super.find(stephql);
		if(list!=null&&list.size()==1){
			stepins=list.get(0);
		}else if(list!=null&&list.size()>1){
			stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"'  AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
			stephql+=" AND userId='"+son.getUserId()+"'";
			stepins=(LabWfStepIns)super.find(stephql,0);
			if(stepins==null){
				stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
				stephql+=" AND tenantId like '"+son.getOrgTenantId()+"%'";
				stepins=(LabWfStepIns)super.find(stephql,0);
			}
		}
		if(stepins!=null){
			stepins.setStatus(LabWfConstant.BUS_PROCESS_END);
			stepins.setUserId(son.getUserId());
			stepins.setUserName(son.getUserName());
			super.update(stepins);
			
			String hql="FROM LabWfProcessIns WHERE id='"+stepins.getProcess().getId()+"'";
			ins=(LabWfProcessIns)super.find(hql, 0);
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return false;
			}else{
				String status=ins.getStatus();
				if(status!=null){
					String statushql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
					" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+stepins.getCode()+"' ";
					List lista=super.find(statushql);
					if(lista==null||lista.size()<=0){
						//当前节点处没有该实例的任何业务，则移除
						status=status.replace(stepins.getCode(), "");
					}
					status=status.replace(",,", ",");
					ins.setStatus(status);
					super.update(ins);
				}
			}
		}
		if(ins==null||step==null){
			return false;
		}
		LabWfStepLogs stepLogs=new LabWfStepLogs();
		stepLogs.setBusId(ins.getBusId());
		stepLogs.setProcessInsId(ins.getId());
		stepLogs.setCode(funId);
		stepLogs.setStepins(stepins);
		stepLogs.setStepName(stepins.getStepName());
		stepLogs.setAuditMessage(msg);
		stepLogs.setAuditResult("1");
		stepLogs.setAuditTime(DateUtils.getCurrDateStr());
		stepLogs.setUserId(son.getUserId());
		stepLogs.setUserName(son.getUserName());
		Date startDate=stepins.getStartDate();
		Date endDate =stepins.getEndDate();
		if(endDate!=null&&startDate!=null){
			String times="";
			long minute =(endDate.getTime() - startDate.getTime())/1000/60;
			if(minute<60){
				times=minute+"分";
			}else{
				long hour =minute/60;
				long mins =minute%60;
				if(hour<24){
					times=hour+"时"+mins+"分";
				}else{
					long days=hour/24;
					long hours =hour%24;
					times=days+"天"+hours+"时"+mins+"分";
				}
			}
			stepLogs.setUseTime(times);
		}else if(startDate!=null){
			endDate=new Date();
			String times="";
			long minute =(endDate.getTime() - startDate.getTime())/1000/60;
			if(minute<60){
				times=minute+"分";
			}else{
				long hour =minute/60;
				long mins =minute%60;
				if(hour<24){
					times=hour+"时"+mins+"分";
				}else{
					long days=hour/24;
					long hours =hour%24;
					times=days+"天"+hours+"时"+mins+"分";
				}
			}
			stepLogs.setUseTime(times);
		}
		super.save(stepLogs);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delLabWfStepIns(String curBusId, String msg) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		//获取实例中业务在当前功能下的未完成实例节点
		String stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND stepBusId='"+curBusId+"'";
		List<LabWfStepIns> list=super.find(stephql);
		try {
			if(list!=null&&list.size()>0){
				for (LabWfStepIns stepins : list) {
					stepins.setStatus(LabWfConstant.BUS_PROCESS_END);
					stepins.setUserId(son.getUserId());
					stepins.setUserName(son.getUserName());
					super.update(stepins);
					LabWfProcessIns ins=stepins.getProcess();
					String status=ins.getStatus();
					if(status!=null){
						String statushql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
						" AND isDel='"+Constants_Base.N+"' AND process.id='"+ins.getId()+"' AND code='"+stepins.getCode()+"' ";
						List lista=super.find(statushql);
						if(lista==null||lista.size()<=0){
							//当前节点处没有该实例的任何业务，则移除
							status=status.replace(stepins.getCode(), "");
						}
						status=status.replace(",,", ",");
						ins.setStatus(status);
						super.update(ins);
					}
					
					LabWfStepLogs stepLogs=new LabWfStepLogs();
					stepLogs.setBusId(stepins.getProcess().getBusId());
					stepLogs.setProcessInsId(stepins.getProcess().getId());
					stepLogs.setStepins(stepins);
					stepLogs.setStepName(stepins.getStepName());
					stepLogs.setAuditMessage(msg);
					stepLogs.setAuditResult("-1");
					stepLogs.setAuditTime(DateUtils.getCurrDateStr());
					stepLogs.setUserId(son.getUserId());
					stepLogs.setUserName(son.getUserName());
					super.save(stepLogs);
				}
			}
		} catch (RuntimeException e) {
			Log4J.error("停止流程节点出错"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabWfProcessIns addLabWfStepIns4Finish(String curBusId, String nextBusId, String funId, String msg,String result,String[] nextId, String nextType) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabWfProcessIns ins=null;
		LabWfStep step=null;
		//获取实例中业务在当前功能下的未完成实例节点
		LabWfStepIns stepins=null;
		String stephql="FROM LabWfStepIns WHERE status='"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
		List<LabWfStepIns> list=super.find(stephql);
		if(list!=null&&list.size()==1){
			stepins=list.get(0);
		}else if(list!=null&&list.size()>1){
			stephql="FROM LabWfStepIns WHERE status='"+LabWfConstant.BUS_PROCESS_END+"'" +
			" AND isDel='"+Constants_Base.N+"'  AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
			stephql+=" AND userId='"+son.getUserId()+"'";
			stepins=(LabWfStepIns)super.find(stephql,0);
			if(stepins==null){
				stephql="FROM LabWfStepIns WHERE status<>'"+LabWfConstant.BUS_PROCESS_END+"'" +
				" AND isDel='"+Constants_Base.N+"' AND code='"+funId+"' AND stepBusId='"+curBusId+"'";
				stephql+=" AND tenantId like '"+son.getOrgTenantId()+"%'";
				stepins=(LabWfStepIns)super.find(stephql,0);
			}
		}
		if(stepins==null){
			return null;
		}else if(stepins.getProcess()==null){
			return null;
		}else{
			String hql="FROM LabWfProcessIns WHERE id='"+stepins.getProcess().getId()+"'";
			ins=(LabWfProcessIns)super.find(hql, 0);
			if(ins==null||ins.getId()==null||ins.getProcess()==null){
				return null;
			}
			String subhql="FROM LabWfStep WHERE processId='"+ins.getProcess().getId()+"'" +
			" AND code like '"+funId+"'";
			step=(LabWfStep)super.find(subhql,0);
		}
		if(step==null){
			return null;
		}
		if(!StrUtils.isBlankOrNull(result)){
			// 获取该节点的指向方向，auditResult与每个方向上的变量比对，获取下一步走向
		    String hql2="FROM LabWfPath WHERE startStep.id='"+step.getId()+"'";
			List<LabWfPath> pathDefList=super.find(hql2);
			if(pathDefList!=null&&pathDefList.size()>0){
				for (LabWfPath path : pathDefList) {
					// 获取该线路上的定义条件，并且比对，获取下一步方向
					boolean flag=isQualified(path, result);
					if (flag) {//若通过所有的条件
						// 日志记录
						LabWfStepLogs stepLogs=new LabWfStepLogs();
						stepLogs.setBusId(ins.getBusId());
						stepLogs.setProcessInsId(ins.getId());
						stepLogs.setCode(funId);
						stepLogs.setStepins(stepins);
						stepLogs.setStepName(stepins.getStepName());
						stepLogs.setAuditMessage(msg);
						stepLogs.setAuditResult(result);
						stepLogs.setAuditTime(DateUtils.getCurrDateStr());
						stepLogs.setUserId(son.getUserId());
						stepLogs.setUserName(son.getUserName());
						Date startDate=stepins.getStartDate();
						Date endDate =stepins.getEndDate();
						if(endDate!=null&&startDate!=null){
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}else if(startDate!=null){
							endDate=new Date();
							String times="";
							long minute =(endDate.getTime() - startDate.getTime())/1000/60;
							if(minute<60){
								times=minute+"分";
							}else{
								long hour =minute/60;
								long mins =minute%60;
								if(hour<24){
									times=hour+"时"+mins+"分";
								}else{
									long days=hour/24;
									long hours =hour%24;
									times=days+"天"+hours+"时"+mins+"分";
								}
							}
							stepLogs.setUseTime(times);
						}
						super.save(stepLogs);
						initNextStep(ins, stepins, path,nextBusId,nextId,nextType,result);
						ins.setResult(LabWfConstant.BUS_GO);
						break;//当前只支持一个分支流转
					}
				}
			}
		}
		return ins;
	}
}