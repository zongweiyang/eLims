package cn.labsoft.labos.common.workflow.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfPathDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfPathInsDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfPathVarDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfStepDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfStepInsDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfStepLogsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfFunStep;
import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.common.workflow.entity.LabWfPathIns;
import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.common.workflow.entity.LabWfStep;
import cn.labsoft.labos.common.workflow.entity.LabWfStepIns;
import cn.labsoft.labos.common.workflow.entity.LabWfStepLogs;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessInsVo;
import cn.labsoft.labos.common.workflow.vo.LabWfStepLogsVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 流程实例化
 * 
 * @author Administrator
 * 
 */
@Service("labWfProcessInsService")
public class LabWfProcessInsServiceImpl implements ILabWfProcessInsService {

	private ILabWfProcessDAO labWfProcessDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabWfStepDAO labWfStepDAO;
	private ILabWfStepInsDAO labWfStepInsDAO;
	private ILabWfPathDAO labWfPathDAO;
	private ILabWfPathInsDAO labWfPathInsDAO;
	private ILabWfPathVarDAO labWfPathVarDAO;
	private ILabWfStepLogsDAO labWfStepLogsDAO;
	
	@Resource
	public void setLabWfStepLogsDAO(ILabWfStepLogsDAO labWfStepLogsDAO) {
		this.labWfStepLogsDAO = labWfStepLogsDAO;
	}
	@Resource
	public void setLabWfProcessDAO(ILabWfProcessDAO labWfProcessDAO) {
		this.labWfProcessDAO = labWfProcessDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabWfStepDAO(ILabWfStepDAO labWfStepDAO) {
		this.labWfStepDAO = labWfStepDAO;
	}
	@Resource
	public void setLabWfStepInsDAO(ILabWfStepInsDAO labWfStepInsDAO) {
		this.labWfStepInsDAO = labWfStepInsDAO;
	}
	@Resource
	public void setLabWfPathDAO(ILabWfPathDAO labWfPathDAO) {
		this.labWfPathDAO = labWfPathDAO;
	}
	@Resource
	public void setLabWfPathInsDAO(ILabWfPathInsDAO labWfPathInsDAO) {
		this.labWfPathInsDAO = labWfPathInsDAO;
	}
	@Resource
	public void setLabWfPathVarDAO(ILabWfPathVarDAO labWfPathVarDAO) {
		this.labWfPathVarDAO = labWfPathVarDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabWfProcessInsVo getWfProcessIns(LabWfProcessInsVo vo)
			throws GlobalException {
		LabWfProcessIns ins=null;
		if(!StrUtils.isBlankOrNull(vo.getId())){
			ins=labWfProcessInsDAO.getWfProcessById(vo.getId());
		}else if (!StrUtils.isBlankOrNull(vo.getBusId())) {
			ins=labWfProcessInsDAO.getWfProcessByBusId(vo.getBusId());
		}
		if(ins!=null){
			BeanUtils.copyProperties(ins, vo, new String[]{"startDate","endDate"});
			if(null!=ins.getStartDate()&&!"".equals(ins.getStartDate())){
				vo.setStartDate(DateUtils.format(ins.getStartDate()));
			}
			if(null!=ins.getEndDate()&&!"".equals(ins.getEndDate())){
				vo.setEndDate(DateUtils.format(ins.getEndDate()));
			}
			if(null!=ins.getProcess()){
				vo.setProcessId(ins.getProcess().getId());
				String flowXml=this.getDefFlowXml(ins.getId());
				vo.setFlowXml(flowXml);
			}
			int height=300;
			String hql="SELECT max(topx) FROM LabWfStep WHERE processId='"+vo.getProcessId()+"'";
			Object stepHeight=labWfStepDAO.find(hql,0);
			String sql="select max(CONVERT(reverse(substring(reverse(textpos),1,INSTR(REVERSE(textpos),',')-1)),SIGNED)) from lab_wf_path WHERE process_id='"+vo.getProcessId()+"'";
			List pathHeightList=labWfPathDAO.createSqlQuery(sql);
			if(stepHeight!=null&&pathHeightList!=null&&pathHeightList.size()>0){
				Object pathHeight=pathHeightList.get(0);
				try {
					height=Integer.valueOf(String.valueOf(stepHeight));
					height=height>Integer.valueOf(String.valueOf(pathHeight))?height:Integer.valueOf(String.valueOf(pathHeight));
				} catch (NumberFormatException e) {
					height=300;
					throw new GlobalException("" + e.getMessage());
				}
			}
			vo.setViewHeight((height+50)+"");
		}
		return vo;
	}
	private String getDefFlowXml(String insId) throws GlobalException {
		LabWfProcessIns ins=labWfProcessInsDAO.getWfProcessById(insId);
		String flowXml = "";
		if(null!=ins){
			List<LabWfStep> stepDefList = labWfStepDAO.getStepAllListByBusId(ins.getProcess().getId());
			List<LabWfPath> pathDefList = labWfPathDAO.getPathListByBusId(ins.getProcess().getId());
			flowXml = this.getDefFlowXml(stepDefList, pathDefList, insId);
		}
		return flowXml;
	}
	@SuppressWarnings("unchecked")
	private String getDefFlowXml(List<LabWfStep> stepDefList,
			List<LabWfPath> pathDefList,String insId) throws GlobalException {
		String flowXml = "";
		if (null != stepDefList && 0 < stepDefList.size()) {
			try {
				StringBuffer sb = new StringBuffer(flowXml);
				sb.append("<WORKFLOW BPDID=\"0001\"><NODES>");
				for (LabWfStep stepDef : stepDefList) {
					sb.append("<NODE ID=\""
									+ stepDef.getId()
									+ "\" STATUS=\""+
									this.getNodeOrLinkStatus(insId, stepDef.getId(), "NODE")
									+"\" DBID=\"\" TYPE=\""+
									((stepDef.getType()==null||stepDef.getType().equals(""))?"Node":stepDef.getType())
									+"\" LEFT=\""
									+ stepDef.getLeftx()
									+ "\" TOP=\""
									+ stepDef.getTopx()
									+ "\" TASKDEFID=\"\"><ATT><BASIC NAME=\""
									+ stepDef.getName()
									+ "\" DESC=\"\" BUSINESSCODE=\""+stepDef.getCode()+"\" URL=\"\"/><EXCUTOR TYPE=\"3\" SELECTEXCUTOR=\"0\" FORMSELECT=\"\" " +
											"EXCUTEROLE=\"\" OBJECTMODEEXCUTOR=\"\" FORMCOLUMN=\"\" PARUSERID=\"\" PARUSERNAME=\"\"/><RUNRULE RUNMETHOD=\"0\" " +
											"RUNROB=\"0\" SIGNIN=\"0\"/><ROLLBACK ISBACK=\"1\" NAME=\"\" DESC=\"\" BACKSCHEME=\"\" BACKNODE=\"\" RUNTIMEBACKNODETYPE=\"\" RUNTIMENODES=\"\"/>"
									+"<OBJEXTEND OBJSTATUS=\""+stepDef.getOverDate()+"\" OBJTEMPLATE=\""+stepDef.getOperType()+"\" VIEWSTYPE=\"\" VIEWSSTR=\""+(stepDef.getUrl()==null?"":stepDef.getUrl())+"\" LOGO=\"\" TAG=\"\"/></ATT></NODE>");
				}
				sb.append("</NODES><LINKS>");
				if (null != pathDefList && 0 < pathDefList.size()) {
					for (LabWfPath pathDef : pathDefList) {
						String hqlx="FROM LabWfPathVar WHERE pathId='"+pathDef.getId()+"'";
						List<LabWfPathVar> pvListx=labWfPathVarDAO.find(hqlx);
						String ids="";
						for(LabWfPathVar pv:pvListx){
							ids+=pv.getVariableId()+"*"+pv.getOperator()+"*"+pv.getValue()+",";
						}
						if(ids.length()>0&&ids.endsWith(",")){
							ids=ids.substring(0, ids.length()-1);
						}
						String status="0";
						if(pathDef.getStartStep().getType().equals("Start")){
							status="2";
						}
//						else if(pathDef.getEndStep().getType().equals("End")){
//							try {
//								status=this.getNodeOrLinkStatus(insId, pathDef.getStartStep().getId(), "NODE");
//								if(Integer.valueOf(status)>0){
//									status="2";
//								}
//							} catch (RuntimeException e) {
//								status="0";
//							}
//						}
						else{
							status=this.getNodeOrLinkStatus(insId, pathDef.getId(), "LINK");
							try {
								if(ids.contains("*-")&&status!="0"){
									status="-"+status;
								}
							} catch (RuntimeException e) {
								throw new GlobalException("" + e.getMessage());
							}
						}
						sb.append("<LINK ID=\""
								+ pathDef.getId()
								+ "\" STATUS=\""+status+"\" STARTID=\""
								+ pathDef.getStartStep().getId()
								+ "\" ENDID=\""
								+ pathDef.getEndStep().getId()
								+ "\" DBID=\"\" POINTS=\""
								+ pathDef.getPoints()
								+ "\" TEXTPOS=\""
								+ pathDef.getTextpos()
								+ "\"><ATT><BASIC NAME=\""
								+ ids
								+ "\" DESC=\""+
								pathDef.getName()
								+"\" CON=\"\"/></ATT></LINK>");
					}
				}
				sb.append("</LINKS></WORKFLOW>");
				flowXml = sb.toString();
			} catch (Exception e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return flowXml;
	}
	private String getNodeOrLinkStatus(String insId, String defId,String nodeOrLink) throws GlobalException {
		String status = "0";
		if ("NODE".equals(nodeOrLink)) {
			LabWfStepIns stepIns = labWfStepInsDAO.getWfStepIns(insId, defId);
			if (null != stepIns && null != stepIns.getStatus()&& !"".equals(stepIns.getStatus())) {
				status = stepIns.getStatus();// 完成
			}else{
				status = LabWfConstant.NODES_STATUS_INIT;// 初始化
			}
		} else {
			LabWfPathIns pathIns=labWfPathInsDAO.getWfPathInsByIds(insId,defId);
			if (null !=pathIns) {
				int num=0;
				try {
					num = Integer.valueOf(pathIns.getNum());
				} catch (NumberFormatException e) {
					num=0;
					throw new GlobalException("" + e.getMessage());
				}
				if(num>1){
					status = LabWfConstant.LINKS_STATUS_UNNED;// 重复
				}else if(num==1){
					status = LabWfConstant.LINKS_STATUS_RUNNED;// 已到达
				}else{
					status = LabWfConstant.LINKS_STATUS_INIT;// 未到达
				}
			} else {
				status = LabWfConstant.LINKS_STATUS_INIT;// 未到达
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfFunStepVo> getLabWfFunStepList(String funId)
			throws GlobalException {
		boolean flag=false;
		String hql="SELECT fs.stepName,fs.stepId,fs.stepType,fs.id FROM LabWfFunStep fs,LabWfConfig wf WHERE fs.process.id=wf.wfProcess.id  AND wf.isDel='"+Constants_Base.N+"' AND wf.wfProcess.id in " +
		"(select case when process.parProcess.id is NULL then process.id else process.parProcess.id end from LabWfFunStep where stepId='"+funId+"')";
		hql+=" ORDER BY fs.sort ASC";
		List<Object[]> fsList= labWfProcessDAO.find4All(hql);
		List<LabWfFunStepVo> fsVoList= new ArrayList<LabWfFunStepVo>();
		if(fsList!=null){
			for (Object[] fs : fsList) {
				if(fs[2].equals("Node")){
					if(String.valueOf(fs[1]).equals(funId)){
						flag=true;
					}
					if(!flag){
						continue;
					}
					LabWfFunStepVo fsVo=new LabWfFunStepVo();
					fsVo.setStepId(String.valueOf(fs[1]));
					fsVo.setStepName(String.valueOf(fs[0]));
					fsVo.setStepType(String.valueOf(fs[2]));
					fsVoList.add(fsVo);
				}else if(fs[2].equals("ChildWorkFlow")){
					String subHql="FROM LabWfFunStep WHERE process.funStep.id='"+fs[3]+"' ORDER BY sort ASC";
					List<LabWfFunStep> sbfsList= labWfProcessDAO.find4All(subHql);
					if(fsList!=null){
						for (LabWfFunStep sbfs : sbfsList) {
							if(sbfs.getStepType().equals("Node")){
								if(sbfs.getStepId().equals(funId)){
									flag=true;
								}
								if(!flag){
									continue;
								}
								LabWfFunStepVo fsVo=new LabWfFunStepVo();
								BeanUtils.copyProperties(sbfs, fsVo, new String[]{});
								fsVoList.add(fsVo);
							}
						}
					}	
				}
			}
			LabWfFunStepVo fsVo=new LabWfFunStepVo();
			fsVo.setStepId(LabWfConstant.BUS_PROCESS_END);
			fsVo.setStepName("已完结");
			fsVo.setStepType("End");
			fsVoList.add(fsVo);
		}
		return fsVoList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfFunStepVo> getLabWfFunStepList4Bus(String bustype)
			throws GlobalException {
		String hql="SELECT fs.stepName,fs.stepId,fs.stepType,fs.id FROM LabWfFunStep fs,LabWfConfig wf WHERE fs.process.id=wf.wfProcess.id  AND wf.isDel='"+Constants_Base.N+"' AND wf.code='"+bustype+"'";
		hql+=" ORDER BY fs.sort ASC";
		List<Object[]> fsList= labWfProcessDAO.find(hql);
		List<LabWfFunStepVo> fsVoList= new ArrayList<LabWfFunStepVo>();
		if(fsList!=null){
			for (Object[] fs : fsList) {
				if(fs[2].equals("Node")){
					LabWfFunStepVo fsVo=new LabWfFunStepVo();
					fsVo.setStepId(String.valueOf(fs[1]));
					fsVo.setStepName(String.valueOf(fs[0]));
					fsVo.setStepType(String.valueOf(fs[2]));
					fsVoList.add(fsVo);
				}else if(fs[2].equals("ChildWorkFlow")){
					String subHql="FROM LabWfFunStep WHERE process.funStep.id='"+fs[3]+"' ORDER BY sort ASC";
					List<LabWfFunStep> sbfsList= labWfProcessDAO.find(subHql);
					if(fsList!=null){
						for (LabWfFunStep sbfs : sbfsList) {
							if(sbfs.getStepType().equals("Node")){
								LabWfFunStepVo fsVo=new LabWfFunStepVo();
								BeanUtils.copyProperties(sbfs, fsVo, new String[]{});
								fsVoList.add(fsVo);
							}
						}
					}	
				}
			}
		}
		return fsVoList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStepLogsVo> getLabWfStepLogsList(
			LabWfProcessInsVo labWfProcessInsVo) throws GlobalException {
		List<LabWfStepLogsVo> logList=new ArrayList<LabWfStepLogsVo>();
		if(labWfProcessInsVo.getParBusId()!=null&&labWfProcessInsVo.getParBusId().trim().length()>0){
			String hql="SELECT ins.busId FROM LabWfProcessIns ins WHERE ins.isDel='"+Constants_Base.N+"' and ins.parIns.busId='"+labWfProcessInsVo.getParBusId()+"'";
			List<String> busIds=labWfProcessInsDAO.find(hql);
			String ids="'"+labWfProcessInsVo.getParBusId()+"','";
			if(busIds!=null){
				for (String busid : busIds) {
					ids+=busid+"','";
				}
			}
			ids+="'";
			String loghql="FROM LabWfStepLogs WHERE busId in("+ids+")";
			loghql+=" ORDER BY createTime DESC";
			List<LabWfStepLogs> list=labWfStepLogsDAO.find(loghql);
			if(list!=null){
				for (LabWfStepLogs po : list) {
					LabWfStepLogsVo vo=new LabWfStepLogsVo();
					BeanUtils.copyProperties(po, vo);
					if(null!=po.getAuditResult()&&po.getAuditResult().equals("1")){
						vo.setAuditResult("<font color=\"blue\">提交 / 通过</font>");
					}else if(null!=po.getAuditResult()&&po.getAuditResult().equals("-1")){
						vo.setAuditResult("<font color=\"red\">不通过</font>");
					}else{
						vo.setAuditResult("保存 / 修改");
					}
					logList.add(vo);
				}
			}
		}else if(labWfProcessInsVo.getBusId()!=null&&labWfProcessInsVo.getBusId().trim().length()>0){
			String loghql="FROM LabWfStepLogs WHERE busId='"+labWfProcessInsVo.getBusId()+"'";
			loghql+=" ORDER BY createTime DESC";
			List<LabWfStepLogs> list=labWfStepLogsDAO.find(loghql);
			if(list!=null){
				for (LabWfStepLogs po : list) {
					LabWfStepLogsVo vo=new LabWfStepLogsVo();
					BeanUtils.copyProperties(po, vo);
					if(null!=po.getAuditResult()&&po.getAuditResult().equals("1")){
						vo.setAuditResult("<font color=\"blue\">提交 / 通过</font>");
					}else if(null!=po.getAuditResult()&&po.getAuditResult().equals("-1")){
						vo.setAuditResult("<font color=\"red\">不通过</font>");
					}else{
						vo.setAuditResult("保存 / 修改");
					}
					logList.add(vo);
				}
			}
		}
		return logList;
	}
	
}
