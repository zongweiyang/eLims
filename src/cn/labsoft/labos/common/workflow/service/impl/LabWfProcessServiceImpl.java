package cn.labsoft.labos.common.workflow.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfFunStepDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfPathDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfPathVarDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfStepDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfVariableDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfFunStep;
import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.common.workflow.entity.LabWfProcess;
import cn.labsoft.labos.common.workflow.entity.LabWfStep;
import cn.labsoft.labos.common.workflow.entity.LabWfVariable;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfPathVarVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessVo;
import cn.labsoft.labos.common.workflow.vo.LabWfVariableVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 流程定义
 * 
 * @author Administrator
 * 
 */
@Service("labWfProcessService")
public class LabWfProcessServiceImpl implements ILabWfProcessService {

	private ILabWfProcessDAO labWfProcessDAO;
	private ILabWfStepDAO labWfStepDAO;
	private ILabWfPathDAO labWfPathDAO;
	private ILabWfVariableDAO labWfVariableDAO;
	private ILabWfPathVarDAO labWfPathVarDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabWfFunStepDAO labWfFunStepDAO;
	private int count=1;
	@Resource
	public void setLabWfProcessDAO(ILabWfProcessDAO labWfProcessDAO) {
		this.labWfProcessDAO = labWfProcessDAO;
	}
	@Resource
	public void setLabWfStepDAO(ILabWfStepDAO labWfStepDAO) {
		this.labWfStepDAO = labWfStepDAO;
	}
	@Resource
	public void setLabWfPathDAO(ILabWfPathDAO labWfPathDAO) {
		this.labWfPathDAO = labWfPathDAO;
	}
	@Resource
	public void setLabWfVariableDAO(ILabWfVariableDAO labWfVariableDAO) {
		this.labWfVariableDAO = labWfVariableDAO;
	}
	@Resource
	public void setLabWfPathVarDAO(ILabWfPathVarDAO labWfPathVarDAO) {
		this.labWfPathVarDAO = labWfPathVarDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabWfFunStepDAO(ILabWfFunStepDAO labWfFunStepDAO) {
		this.labWfFunStepDAO = labWfFunStepDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfProcessVo> getWfProcessList(LabWfProcessVo processDefVo)
			throws GlobalException {
		String hql="FROM LabWfProcess WHERE 1=1";
		hql+=" AND parProcess.id is null";
		if(!StrUtils.isBlankOrNull(processDefVo.getId())){
			hql+=" AND id='"+processDefVo.getId()+"'";
		}
		if(!StrUtils.isBlankOrNull(processDefVo.getStatus())){
			hql+=" AND status='"+processDefVo.getStatus()+"'";
		}
		if(!StrUtils.isBlankOrNull(processDefVo.getFunId())){
			hql+=" AND funId='"+processDefVo.getFunId()+"'";
		}
		List<LabWfProcess> poList=labWfProcessDAO.find(hql);
		List<LabWfProcessVo> voList=new ArrayList<LabWfProcessVo>();
		if(poList!=null&&poList.size()>0){
			for(LabWfProcess po:poList){
				LabWfProcessVo vo=new LabWfProcessVo();
				vo.setId(po.getId());
				vo.setName(po.getName());
				vo.setCode(po.getCode());
				vo.setComment(po.getComment());
				vo.setModifyDate(po.getModifyDate());
				vo.setCreateDate(po.getCreateDate());
				vo.setUserName(po.getUserName());
				vo.setStatus(po.getStatus());
				voList.add(vo);
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getWfProcessPR(LabWfProcessVo labWfProcessVo,
			PageResult pageResult) throws GlobalException {
		String hql="FROM LabWfProcess WHERE 1=1 AND isDel='"+Constants_Base.N+"'";
		hql+=" AND parProcess.id is null";
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getFunId())){
			hql+=" AND funId='"+labWfProcessVo.getFunId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getName())){
			hql+=" AND name LIKE '%"+labWfProcessVo.getName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getCode())){
			hql+=" AND code LIKE '%"+labWfProcessVo.getCode()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getStatus())){
			hql+=" AND status='"+labWfProcessVo.getStatus()+"'";
		}
		if(null!=labWfProcessVo.getStatus()&&(labWfProcessVo.getStatus().equals(LabWfConstant.PROCESS_OPEN)
				||labWfProcessVo.getStatus().equals(LabWfConstant.PROCESS_CLOSE))){
			hql+=" AND parProcess.id is null";
		}
		hql+=" ORDER BY status asc";
		pageResult=labWfProcessDAO.getPageResult(hql, pageResult);
		List<LabWfProcess> poList=pageResult.getResultList();
		List<LabWfProcessVo> voList=null;
		if(poList!=null&&poList.size()>0){
			 voList=new ArrayList<LabWfProcessVo>();
			for(LabWfProcess po:poList){
				LabWfProcessVo vo=new LabWfProcessVo();
				vo.setId(po.getId());
				vo.setName(po.getName());
				vo.setCode(po.getCode());
				vo.setComment(po.getComment());
				vo.setModifyDate(po.getModifyDate());
				vo.setCreateDate(po.getCreateDate());
				vo.setUserName(po.getUserName());
				vo.setStatus(po.getStatus());
				vo.setFunId(po.getFunId());
				vo.setFunName(po.getFunName());
				if(po.getParProcess()!=null){
					vo.setParProcessId(po.getParProcess().getId());
					vo.setParProcessName(po.getParProcess().getName());
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getWfProcessPR4Logs(LabWfProcessVo labWfProcessVo,
			PageResult pageResult) throws GlobalException {
		
		String sql="SELECT poc.id,poc.fun_Name,poc.name,poc.code,poc.start_date,poc.end_date," +
				"(select count(*) FROM lab_wf_process_ins ins  WHERE ins.status='"+LabWfConstant.BUS_PROCESS_END+"' AND ins.is_del='N' AND ins.process_id=poc.id)," +
				"(select count(*) FROM lab_wf_process_ins insx  WHERE insx.status <>'"+LabWfConstant.BUS_PROCESS_INIT+"' AND insx.is_del='N' AND insx.status<>'"+LabWfConstant.BUS_PROCESS_END+"' AND insx.process_id=poc.id)," +
				"poc.status";
		sql+=" FROM lab_wf_process poc WHERE poc.status<>'0' ";
		sql+=" AND poc.parent_id is null";
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getFunId())){
			sql+=" AND poc.fun_id='"+labWfProcessVo.getFunId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getName())){
			sql+=" AND poc.name LIKE '%"+labWfProcessVo.getName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labWfProcessVo.getCode())){
			sql+=" AND poc.code LIKE '%"+labWfProcessVo.getCode()+"%'";
		}
		pageResult=labWfProcessDAO.getPageResultBySQL(sql, pageResult);
		List<Object[]> poList=pageResult.getResultList();
		List<LabWfProcessVo> voList=null;
		if(poList!=null&&poList.size()>0){
			 voList=new ArrayList<LabWfProcessVo>();
			for(Object[] po:poList){
				LabWfProcessVo vo=new LabWfProcessVo();
				vo.setId(String.valueOf(po[0]));
				vo.setFunName(String.valueOf(po[1]));
				vo.setName(String.valueOf(po[2]));
				vo.setCode(String.valueOf(po[3]));
				vo.setStartDate(String.valueOf(po[4]));
				vo.setStatus(String.valueOf(po[8]));
				if(po[5]==null){
					vo.setEndDate(DateUtils.getCurrDateStr());
				}else if(vo.getStatus()!=null&&!vo.getStatus().equals(LabWfConstant.PROCESS_CLOSE)){
					vo.setEndDate(DateUtils.getCurrDateStr());
				}else{
					vo.setEndDate(String.valueOf(po[5]));
				}
				if(po[6]==null){
					vo.setCounty(0);
				}else{
					vo.setCounty(Integer.valueOf(String.valueOf(po[6])));
				}
				if(po[7]==null){
					vo.setCountn(0);
				}else{
					vo.setCountn(Integer.valueOf(String.valueOf(po[7])));
				}
				vo.setCount(vo.getCounty()+vo.getCountn());
				
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}
	@Override
	public String getFlowDefXmlByBusId(String busId,String status) throws GlobalException {
		String flowXml = "";
		if(null!=status&&status.equals("1")){//表示初始化状态
			flowXml = this.getInitFlowXml(busId);
		}else{
			flowXml = this.getDefFlowXml(busId);
		}
		return flowXml;
	}
	/**
	 * 初始化 根据业务ID初始模型信息
	 * @param busId
	 * @return
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String getInitFlowXml(String busId)
			throws GlobalException {
		count=1;
		String initFlowXml = "<WORKFLOW BPDID=\"0001\"><NODES>";
		initFlowXml += "<NODE ID=\""
			+ java.util.UUID.randomUUID().toString().replace("-","")
			+ "\" STATUS=\"0\" DBID=\"\" TYPE=\"Start\" LEFT=\""
			+ (50)
			+ "\" TOP=\""
			+ ((1 / 6) * 100+50)
			+ "\" ><ATT><BASIC NAME=\""+
			LabWfConstant.STEP_START
			+"\" DESC=\"\" /></ATT></NODE>";
		
		initFlowXml+=getNodeXmlStr(busId);
		
		if(count==1)count++;
		//添加结束节点
		if((count%6)>=0&&(count/6)==1){
			count=count/6;
		}else if((count%6)>=0&&(count/6)>1){
			count=count/6-1;
		}else if((count%6)>0&&(count/6)==0){
			count=count/6+1;
		}
		initFlowXml += "<NODE ID=\""
			+ java.util.UUID.randomUUID().toString().replace("-","")
			+ "\" STATUS=\"0\" DBID=\"\" TYPE=\"End\" LEFT=\""
			+ (50)
			+ "\" TOP=\""
			+ (count * 100+50)
			+ "\" ><ATT><BASIC NAME=\""+
			LabWfConstant.STEP_END
			+"\" DESC=\"\" /></ATT></NODE>";
		
		initFlowXml += "</NODES><LINKS></LINKS></WORKFLOW>";
		return initFlowXml;
	}
	@SuppressWarnings("unchecked")
	public String getNodeXmlStr(String busId) throws GlobalException{
		String initFlowXml="";
		String hql="FROM LabWfFunStep WHERE process.id='"+busId+"' order by sort asc";
		List<LabWfFunStep> funStepList=labWfFunStepDAO.find(hql);
		if (null != funStepList && funStepList.size()>0) {
			//添加开始节点
			for (LabWfFunStep labWfFunStep : funStepList) {
				if(null!=labWfFunStep.getStepType()&&labWfFunStep.getStepType().equals("Node")){
					if(count%6==0){
						count++;
					}
					initFlowXml +=  "<NODE ID=\""
							+ labWfFunStep.getId()
							+ "\" STATUS=\"0\" DBID=\"\" TYPE=\"Node\" LEFT=\""
							+ ((count % 6) * 150+50)
							+ "\" TOP=\""
							+ ((count / 6) * 100+50)
							+ "\" TASKDEFID=\"\"><ATT><BASIC NAME=\""
							+ labWfFunStep.getStepName()
							+ "\" DESC=\"\" BUSINESSCODE=\""+labWfFunStep.getStepId()+"\" URL=\"\"/></ATT></NODE>";
					count++;
				}else if(null!=labWfFunStep.getStepType()&&labWfFunStep.getStepType().equals("ChildWorkFlow")){
					String subhql="FROM LabWfProcess WHERE isDel='"+Constants_Base.N+"'";
					subhql+=" AND funStep.id='"+labWfFunStep.getId()+"'";
					LabWfProcess subProcess=(LabWfProcess)labWfProcessDAO.find(subhql, 0);
					if(subProcess!=null){
						initFlowXml+=this.getNodeXmlStr(subProcess.getId());
					}
				}
			}
		}
		return initFlowXml;
	};
	/**
	 * @title 根据业务Id获取已经定义的模型数据
	 * @param busId 
	 * @return
	 * @throws GlobalException 
	 */
	@SuppressWarnings("unchecked")
	private String getDefFlowXml(String busId) throws GlobalException {
		String flowXml = "";
		List<LabWfStep> stepDefList = labWfStepDAO.getStepAllListByBusId(busId);
		List<LabWfPath> pathDefList = labWfPathDAO.getPathListByBusId(busId);
		if (null != stepDefList && 0 < stepDefList.size()) {
			try {
				StringBuffer sb = new StringBuffer(flowXml);
				sb.append("<WORKFLOW BPDID=\"0001\"><NODES>");
				for (LabWfStep stepDef : stepDefList) {
					if(null!=stepDef.getType()&&(stepDef.getType().equals("Start")||stepDef.getType().equals("End"))){
						sb.append("<NODE ID=\""
								+ stepDef.getUuid()
								+ "\" STATUS=\"0\" DBID=\"\" TYPE=\""+
								((stepDef.getType()==null||stepDef.getType().equals(""))?"Node":stepDef.getType())
								+"\" LEFT=\""
								+ stepDef.getLeftx()
								+ "\" TOP=\""
								+ stepDef.getTopx()
								+ "\" TASKDEFID=\"\"><ATT><BASIC NAME=\""
								+ stepDef.getName()
								+ "\" DESC=\"\" BUSINESSCODE=\"\" URL=\"\"/></ATT></NODE>");
					}else{
						sb.append("<NODE ID=\""
								+ stepDef.getUuid()
								+ "\" STATUS=\"0\" DBID=\"\" TYPE=\""+
								((stepDef.getType()==null||stepDef.getType().equals(""))?"Node":stepDef.getType())
								+"\" LEFT=\""
								+ stepDef.getLeftx()
								+ "\" TOP=\""
								+ stepDef.getTopx()
								+ "\" TASKDEFID=\"\"><ATT><BASIC NAME=\""
								+ stepDef.getName()
								+ "\" DESC=\"\" BUSINESSCODE=\""+stepDef.getCode()+"\" URL=\"\"/><EXCUTOR TYPE=\"3\" SELECTEXCUTOR=\"0\" FORMSELECT=\"\" EXCUTEROLE=\"\" OBJECTMODEEXCUTOR=\"\" FORMCOLUMN=\"\" PARUSERID=\"\" PARUSERNAME=\"\"/><RUNRULE RUNMETHOD=\"0\" RUNROB=\"0\" SIGNIN=\"0\"/><ROLLBACK ISBACK=\"1\" NAME=\"\" DESC=\"\" BACKSCHEME=\"\" BACKNODE=\"\" RUNTIMEBACKNODETYPE=\"\" RUNTIMENODES=\"\"/>"
								+"<OBJEXTEND OBJSTATUS=\""+stepDef.getOverDate()+"\" OBJTEMPLATE=\""+stepDef.getOperType()+"\" VIEWSTYPE=\"\" VIEWSSTR=\""+(stepDef.getUrl()==null?"":stepDef.getUrl())+"\" LOGO=\"\" TAG=\"\"/></ATT></NODE>");
					}
					
				}
				sb.append("</NODES><LINKS>");
				if (null != pathDefList && 0 < pathDefList.size()) {
					for (LabWfPath pathDef : pathDefList) {
						List<LabWfPathVar> pvListx=labWfPathVarDAO.getPathVarDefList(pathDef.getId());
						String ids="";
						for(LabWfPathVar pv:pvListx){
							ids+=pv.getVariableName()+"*"+pv.getOperator()+"*"+pv.getValue()+",";
						}
						if(ids.length()>0&&ids.endsWith(",")){
							ids=ids.substring(0, ids.length()-1);
						}
						sb.append("<LINK ID=\""
								+ pathDef.getId()
								+ "\" STATUS=\"0\" STARTID=\""
								+ pathDef.getStartStep().getUuid()
								+ "\" ENDID=\""
								+ pathDef.getEndStep().getUuid()
								+ "\" DBID=\"\" POINTS=\""
								+ pathDef.getPoints()
								+ "\" TEXTPOS=\""
								+ pathDef.getTextpos()
								+ "\"><ATT><BASIC NAME=\""
								+ pathDef.getName()
								+ "\" DESC=\""
								+ ids
								+"\" CON=\"\"/></ATT></LINK>");
					}
				}
				sb.append("</LINKS></WORKFLOW>");
				flowXml = sb.toString();
			} catch (Exception e) {
				flowXml = this.getInitFlowXml(busId);
			}
		}else{
			flowXml = this.getInitFlowXml(busId);
		}
		return flowXml;
	}
	/**
	 * 获取步骤或线路的状态
	 * @param busId
	 * @param defId
	 * @param nodeOrLink
	 * @param isInit
	 * @return
	 */
//	private String getNodeOrLinkStatus(String busId, String defId,
//			String nodeOrLink, boolean isInit) {
//		String status = "0";
//		if (isInit) {
//			return status;
//		}
//		if ("NODE".equals(nodeOrLink)) {
//			WfStepIns stepIns = wfStepInsDAO.getWfStepIns(busId, defId);
//			if (null != stepIns && null != stepIns.getStatus()
//					&& !"".equals(stepIns.getStatus())) {
//				 status = stepIns.getStatus();
//			} else {
//				status = Constant.NODES_STATUS_UNRUN;// 未到达
//			}
//		} else {
//			WfPath pathDef = wfPathDAO.getWfConditionpathDefById(defId);
//			WfStepIns startStep = wfStepInsDAO.getWfStepIns(busId,
//					pathDef.getStepDefId());
//			WfStepIns endStep = wfStepInsDAO.getWfStepIns(busId,
//					pathDef.getNextStepDefId());
//			if (null != startStep
//					&& null != startStep.getStatus()
//					&& !"".equals(startStep.getStatus())
//					&& null != endStep
//					&& null != endStep.getStatus()
//					&& !"".equals(endStep.getStatus())) {
//				String tempStartStatus = startStep.getStatus();
//				String tempEndStatus = endStep.getStatus();
//
//				if ((Constant.NODES_STATUS_RUNNED.equals(tempStartStatus) && (Constant.NODES_STATUS_RUNNED
//						.equals(tempEndStatus)))) {
//					status = Constant.LINKS_STATUS_RUNNED;// 已到达
//				}
//			} else {
//				status = Constant.LINKS_STATUS_UNRUN;// 未到达
//			}
//		}
//		return status;
//	}
	@Override
	public LabWfProcessVo addWfProcess(LabWfProcessVo vo)
			throws GlobalException {
		LabWfProcess po= new LabWfProcess();
		if(null==vo.getFunId())return null;
		LabFunction function=labFunctionDAO.getLabFunction(vo.getFunId());
		po.setFunId(function.getId());
		po.setFunName(function.getName());
		po.setFunCode(function.getCode());
		po.setCode(vo.getCode());
		po.setName(vo.getName());
		po.setComment(vo.getComment());
		po.setCreateDate(DateUtils.getCurrDateStr());
		po.setModifyDate(DateUtils.getCurrDateStr());
		po.setUserId(vo.getUserId());
		po.setUserName(vo.getUserName());
		po.setStatus(LabWfConstant.PROCESS_EDIT);
		labWfProcessDAO.addWfProcess(po);
		//节点初始化
		int n=1;
		if(null!=vo.getFunStepList()){
			for (LabWfFunStepVo funStepVo : vo.getFunStepList()) {
				if(funStepVo==null)continue;
				LabWfFunStep fs=new LabWfFunStep();
				BeanUtils.copyProperties(funStepVo, fs, new String[]{});
				fs.setProcess(po);
				fs.setProcessName(po.getName());
				fs.setCreateTime(DateUtils.getCurrDateTimeStr());
				LabFunction stepfun=labFunctionDAO.getLabFunction(funStepVo.getStepId());
				if(stepfun==null){
					return null;
				}
				fs.setStepId(stepfun.getId());
				fs.setStepName(stepfun.getName());
				fs.setStepUrl(stepfun.getUrl());
				fs.setUserId(vo.getUserId());
				fs.setStepType("Node");
				fs.setSort(stepfun.getSort());
				labWfFunStepDAO.addLabWfFunStep(fs);
				n++;
			}
		}
		//子流程
		if(null!=vo.getIsSubWf()&&vo.getIsSubWf().equals(Constants_Base.Y)){
			List<LabWfProcessVo> subProcessList= vo.getSubProcessList();
			if(subProcessList!=null){
				for (LabWfProcessVo labWfProcessVo : subProcessList) {
					if(labWfProcessVo==null||StrUtils.isBlankOrNull(labWfProcessVo.getId()))
						continue;
					//修改子流程
					LabWfProcess subProcess=labWfProcessDAO.getWfProcess(labWfProcessVo.getId());
					//添加子流程节点
					LabWfFunStep fs=new LabWfFunStep();
					fs.setProcess(po);
					fs.setProcessName(po.getName());
					fs.setCreateTime(DateUtils.getCurrDateTimeStr());
					fs.setUserId(vo.getUserId());
					fs.setStepId(subProcess.getFunId());
					fs.setStepName(subProcess.getFunName());
					fs.setStepType("ChildWorkFlow");
					fs.setStepCode(subProcess.getCode());
					fs.setSort(n);
					labWfFunStepDAO.addLabWfFunStep(fs);
					subProcess.setParProcess(po);
					subProcess.setFunStep(fs);
					labWfProcessDAO.updateWfProcess(subProcess);
					n++;
				}
			}
		}
		//变量初始化
		if(null!=vo.getVarList()){
			for (LabWfVariableVo varVo : vo.getVarList()) {
				if(varVo==null||StrUtils.isBlankOrNull(varVo.getName())
						||StrUtils.isBlankOrNull(varVo.getValue()))continue;
				LabWfVariable var =new LabWfVariable();
				BeanUtils.copyProperties(varVo, var, new String[]{});
				var.setProcessId(po.getId());
				var.setProcessName(po.getName());
				labWfVariableDAO.addLabWfVariable(var);
			}
		}
		return vo;
	}
	@SuppressWarnings("unchecked")
	public boolean updateWfProcess(LabWfProcessVo vo)
		throws GlobalException {
		if(null==vo.getFunId())return false;
		LabWfProcess po=null;
		if(null!=vo.getId()){
			po=labWfProcessDAO.getWfProcess(vo.getId());
		}
		if(null!=po){
			String hql1="FROM LabWfFunStep WHERE process.id='"+vo.getId()+"'";
			List<LabWfFunStep> list1=labWfProcessDAO.find(hql1);
			if(list1!=null){
				for (LabWfFunStep labWfFunStep : list1) {
					labWfFunStepDAO.delLabWfFunStep(labWfFunStep);
				}
			}
			LabFunction function=(LabFunction)labWfProcessDAO.findById(LabFunction.class, vo.getFunId());
			po.setFunId(function.getId());
			po.setFunName(function.getName());
			po.setCode(vo.getCode());
			po.setName(vo.getName());
			po.setComment(vo.getComment());
			po.setModifyDate(DateUtils.getCurrDateStr());
			labWfProcessDAO.updateWfProcess(po);
			try {
				String sql="update lab_wf_process set parent_id = null , fun_step_id = null  WHERE is_del='"+Constants_Base.N+"'";
				sql+=" AND parent_id ='"+po.getId()+"'";
				labWfProcessDAO.executeSQL(sql);
			} catch (Exception e) {
				Log4J.error("LabWfProcessServiceImpl  执行批量修改sql异常！", e);
			}
			int n=1;
			//节点
			if(null!=vo.getFunStepList()){
				for (LabWfFunStepVo funStepVo : vo.getFunStepList()) {
					if(funStepVo==null)continue;
					LabWfFunStep fs=new LabWfFunStep();
					BeanUtils.copyProperties(funStepVo, fs, new String[]{"isDel","createTime"});
					fs.setProcess(po);
					fs.setProcessName(po.getName());
					fs.setCreateTime(DateUtils.getCurrDateTimeStr());
					LabFunction stepfun=labFunctionDAO.getLabFunction(funStepVo.getStepId());
					if(stepfun==null){
						return false;
					}
					fs.setStepId(stepfun.getId());
					fs.setStepName(stepfun.getName());
					fs.setStepUrl(stepfun.getUrl());
					fs.setUserId(vo.getUserId());
					fs.setStepType("Node");
					fs.setSort(stepfun.getSort());
					labWfFunStepDAO.addLabWfFunStep(fs);
					n++;
				}
			}
			//子流程
			if(null!=vo.getIsSubWf()&&vo.getIsSubWf().equals(Constants_Base.Y)){
				List<LabWfProcessVo> subProcessList= vo.getSubProcessList();
				if(subProcessList!=null){
					for (LabWfProcessVo labWfProcessVo : subProcessList) {
						if(labWfProcessVo==null||StrUtils.isBlankOrNull(labWfProcessVo.getId()))
							continue;
						//修改子流程
						LabWfProcess subProcess=labWfProcessDAO.getWfProcess(labWfProcessVo.getId());
						//添加子流程节点
						LabWfFunStep fs=new LabWfFunStep();
						fs.setProcess(po);
						fs.setProcessName(po.getName());
						fs.setCreateTime(DateUtils.getCurrDateTimeStr());
						fs.setUserId(vo.getUserId());
						fs.setStepId(subProcess.getFunId());
						fs.setStepName(subProcess.getFunName());
						fs.setStepType("ChildWorkFlow");
						fs.setStepCode(subProcess.getCode());
						fs.setSort(n);
						labWfFunStepDAO.addLabWfFunStep(fs);
						n++;
						subProcess.setParProcess(po);
						subProcess.setFunStep(fs);
						labWfProcessDAO.updateWfProcess(subProcess);
					}
				}
			}
			String hql2="FROM LabWfVariable WHERE processId='"+vo.getId()+"'";
			List<LabWfVariable> list2=labWfVariableDAO.find(hql2);
			if(list1!=null){
				for (LabWfVariable variable : list2) {
					labWfVariableDAO.deleteLabWfVariable(variable);
				}
			}
			if(null!=vo.getVarList()){
				for (LabWfVariableVo varVo : vo.getVarList()) {
					if(varVo==null||null==varVo.getName()||"".equals(varVo.getName()))continue;
					LabWfVariable var =new LabWfVariable();
					BeanUtils.copyProperties(varVo, var, new String[]{});
					var.setProcessId(po.getId());
					var.setProcessName(po.getName());
					labWfVariableDAO.addLabWfVariable(var);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean updateProcess2Open(LabWfProcessVo vo)
		throws GlobalException {
		LabWfProcess po=null;
		if(null!=vo.getId()){
			po=labWfProcessDAO.getWfProcess(vo.getId());
//			String hql="FROM LabWfProcess WHERE 1=1 AND status='"+LabWfConstant.PROCESS_OPEN+"'";
//			hql+=" AND funId = '"+po.getFunId()+"'";
//			List<LabWfProcess> poList=labWfProcessDAO.find(hql);
//			if(poList!=null&&poList.size()>0){
//				for (LabWfProcess labWfProcess : poList) {
//					labWfProcess.setStatus(LabWfConstant.PROCESS_CLOSE);
//					labWfProcess.setEndDate(DateUtils.getCurrDateTimeStr());
//					labWfProcessDAO.updateWfProcess(labWfProcess);
//					String subHql="FROM LabWfProcess WHERE 1=1 AND status='"+LabWfConstant.PROCESS_OPEN+"'";
//					subHql+=" AND parProcess.id='"+labWfProcess.getId()+"'";
//					List<LabWfProcess> subPoList=labWfProcessDAO.find(hql);
//					if(subPoList!=null&&subPoList.size()>0){
//						for (LabWfProcess subProcess : subPoList) {
//							subProcess.setStatus(LabWfConstant.PROCESS_CLOSE);
//							subProcess.setEndDate(DateUtils.getCurrDateTimeStr());
//							labWfProcessDAO.updateWfProcess(subProcess);
//						}
//					}
//				}
//			}
			po.setStatus(LabWfConstant.PROCESS_OPEN);
			po.setModifyDate(DateUtils.getCurrDateStr());
			po.setStartDate(DateUtils.getCurrDateTimeStr());
			labWfProcessDAO.updateWfProcess(po);
			String subHql="FROM LabWfProcess WHERE 1=1 AND status='"+LabWfConstant.PROCESS_EDIT+"'";
			subHql+=" AND parProcess.id='"+po.getId()+"'";
			List<LabWfProcess> subPoList=labWfProcessDAO.find(subHql);
			if(subPoList!=null&&subPoList.size()>0){
				for (LabWfProcess subProcess : subPoList) {
					subProcess.setStatus(LabWfConstant.PROCESS_OPEN);
					subProcess.setModifyDate(DateUtils.getCurrDateStr());
					subProcess.setStartDate(DateUtils.getCurrDateTimeStr());
					labWfProcessDAO.updateWfProcess(subProcess);
				}
			}
			return true;
		}else{
			return false;
		}
		
	}
	@SuppressWarnings("unchecked")
	public boolean delWfProcess(String id)
		throws GlobalException {
		LabWfProcess po=null;
		if(null!=id){
			po=labWfProcessDAO.getWfProcess(id);
		}
		if(null!=po){
			try {
				//删除已关联的迁移变量
				List<LabWfPathVar> pvList=labWfPathVarDAO.getPathVarListByBusId(id);
				if(pvList!=null){
					for(LabWfPathVar pv:pvList){
						labWfPathVarDAO.deleteLabWfPathVar(pv);
					}
				}
				//删除模型线路
				List<LabWfPath> pathList = labWfPathDAO.getPathListByBusId(id);
				if(pathList!=null){
					for(LabWfPath path:pathList){
						labWfPathDAO.delPath(path);
					}
				}
				//删除模型节点
				List<LabWfStep> stepList = labWfStepDAO.getStepAllListByBusId(id);
				if(stepList!=null){
					for(LabWfStep step:stepList){
						labWfStepDAO.delWfStep(step);
					}
				}
				//删除该流程下的变量
				List<LabWfVariable> varlist=labWfVariableDAO.getLabWfVarListByBusId(id);
				if(varlist!=null){
					for(LabWfVariable var:varlist){
						labWfVariableDAO.deleteLabWfVariable(var);
					}
				}
				//删除
				String hql="FROM LabWfFunStep WHERE process.id='"+po.getId()+"'";
				List<LabWfFunStep> fslist=labWfVariableDAO.find(hql);
				if(fslist!=null){
					for(LabWfFunStep fs:fslist){
						labWfFunStepDAO.delLabWfFunStep(fs);
					}
				}
				//若关联子类，清空子类
				String subhql="FROM LabWfProcess WHERE parProcess.id='"+po.getId()+"'";
				List<LabWfProcess> sublist=labWfVariableDAO.find(subhql);
				if(sublist!=null){
					for(LabWfProcess subPo:sublist){
						subPo.setParProcess(null);
						labWfProcessDAO.updateWfProcess(subPo);
					}
				}
				labWfProcessDAO.delWfProcess(po);
				return true;
			} catch (RuntimeException e) {
				Log4J.error("labWfVariableDAO"+e.getMessage());
				return false;
			}
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabWfProcessVo getWfProcess(String id)
			throws GlobalException {
		LabWfProcess po=labWfProcessDAO.getWfProcess(id);
		LabWfProcessVo vo=null;
		if(null!=po){
			vo=new LabWfProcessVo();
			BeanUtils.copyProperties(po,vo,new String[]{});
			String hql="FROM LabWfProcess WHERE isDel='"+Constants_Base.N+"'";
			hql+=" AND parProcess.id='"+po.getId()+"'";
			List<LabWfProcess> subList=labWfProcessDAO.find(hql);
			List<LabWfProcessVo> subVoList=new ArrayList<LabWfProcessVo>();
			if(subList!=null&&subList.size()>0){
				for (LabWfProcess subPo : subList) {
					LabWfProcessVo subVo=new LabWfProcessVo();
					BeanUtils.copyProperties(subPo, subVo,new String[]{});
					subVoList.add(subVo);
				}
				vo.setIsSubWf(Constants_Base.Y);
			}else{
				vo.setIsSubWf(Constants_Base.N);
			}
			vo.setSubProcessList(subVoList);
		}
		return vo;
	}
	/**
	 * 
	 * @param xmlStr
	 * @return
	 */
	private Element xmlStrToElement(String xmlStr) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		xmlStr = xmlStr.replaceAll("&lt;", "<");
		xmlStr = xmlStr.replaceAll("&gt;", ">");
		try {
			document = saxReader.read(new StringReader(xmlStr));
		} catch (DocumentException e) {
			//e.printStackTrace();
		}
		Element root = document.getRootElement();
		return root;
	}
	/**
	 * 解析xml文件所有节点
	 * @param rootElement
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<LabWfStep> getDisNodesDefFromXmlStr(Element rootElement) {
		List<LabWfStep> disNodesDefList = new ArrayList<LabWfStep>();
		List nodeList = rootElement.selectNodes("/WORKFLOW/NODES/NODE");
		for (Iterator i = nodeList.iterator(); i.hasNext();) {
			Element node = (Element) i.next();
			LabWfStep stepDef = new LabWfStep();
			stepDef.setUuid(node.attributeValue("ID"));
			stepDef.setLeftx(node.attributeValue("LEFT"));
			stepDef.setTopx(node.attributeValue("TOP"));
			stepDef.setType(node.attributeValue("TYPE"));
			List nodesList =node.content();
			if(nodesList!=null&&nodesList.size()>0){
				for (Object object : nodesList) {
					Element subNode =(Element)object;
					if(subNode.getName().equals("ATT")){
						List nodesList1 =subNode.content();
						if(nodesList1!=null){
							for (Object object2 : nodesList1) {
								Element subALL =(Element)object2;
								if(subALL.getName().equals("BASIC")){
									String name=subALL.attributeValue("NAME");
									stepDef.setName(name);
									String code=subALL.attributeValue("BUSINESSCODE");
									stepDef.setCode(code);
								}
								if(subALL.getName().equals("OBJEXTEND")){
//									String type=subALL.attributeValue("OBJTEMPLATE");
//									String ids=subALL.attributeValue("VIEWSTYPE");
//									String url=subALL.attributeValue("VIEWSSTR");
//									String dates=subALL.attributeValue("OBJSTATUS");
								}
							}
						}
					}
				}
			}
			disNodesDefList.add(stepDef);
		}
		return disNodesDefList;
	}
	/**
	 * 解析xml所有节点的名称
	 * @param rootElement
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	private List<String> getDisNodesNamesFromXmlStr(Element rootElement) {
//		List<String> stepNamesList = new ArrayList<String>();
//		List nodesList = rootElement.selectNodes("/WORKFLOW/NODES/NODE/ATT/BASIC");
//		for (Iterator i = nodesList.iterator(); i.hasNext();) {
//			Element basics = (Element) i.next();
//			stepNamesList.add(basics.attributeValue("NAME"));
//		}
//		return stepNamesList;
//	}
	/**
	 * 解析xml所有节点的属性
	 * @param rootElement
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	private List<WfStep> getDisNodesPropertyFromXmlStr(List<WfStep> stepList,Element rootElement) {
//		List nodesList = rootElement.selectNodes("/WORKFLOW/NODES/NODE/ATT/OBJEXTEND");
//		int n=2;
//		for (Iterator i = nodesList.iterator(); i.hasNext();) {
//			Element basics = (Element) i.next();
//			String type=basics.attributeValue("OBJTEMPLATE");
//			//String ids=basics.attributeValue("VIEWSTYPE");
//			String url=basics.attributeValue("VIEWSSTR");
//			//url=url.replace("/", "\\/");
//			String dates=basics.attributeValue("OBJSTATUS");
//			stepList.get(n).setOperType(type);
//			stepList.get(n).setUrl(url);
//			//if(ids!=null&&ids.length()>0){
//				//String idstr[]=ids.split(",");
//				if(null!=dates&&!"".equals(dates)){
//					stepList.get(n).setOverDate(Long.parseLong(dates));
//				//}
//			//}
//			n++;
//		}
//		return stepList;
//	}
	/**
	 * 解析xml文件所有路径
	 * @param rootElement
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<LabWfPath> getDisNodesLinksFromXmlStr(Element rootElement) {
		//List nodesList = rootElement.selectNodes("/WORKFLOW/LINKS/LINK/ATT/BASIC");
		List<LabWfPath> disLinksDefList = new ArrayList<LabWfPath>();
		List nodeList = rootElement.selectNodes("/WORKFLOW/LINKS/LINK");
		for (Iterator i = nodeList.iterator(); i.hasNext();) {
			Element node = (Element) i.next();
			LabWfPath pathDef = new LabWfPath();
			pathDef.setId(node.attributeValue("ID"));
			pathDef.setPoints(node.attributeValue("POINTS"));
			pathDef.setTextpos(node.attributeValue("TEXTPOS"));
			
			LabWfStep startStep=new LabWfStep();
			startStep.setUuid(node.attributeValue("STARTID"));
			pathDef.setStartStep(startStep);
			LabWfStep endStep=new LabWfStep();
			endStep.setUuid(node.attributeValue("ENDID"));
			pathDef.setEndStep(endStep);
			
			List nodesList =node.content();
			if(nodesList!=null&&nodesList.size()>0){
				for (Object object : nodesList) {
					Element subNode =(Element)object;
					if(subNode.getName().equals("ATT")){
						List nodesList1 =subNode.content();
						if(nodesList1!=null){
							for (Object object2 : nodesList1) {
								Element subALL =(Element)object2;
								if(subALL.getName().equals("BASIC")){
									String name=subALL.attributeValue("NAME");
									if(name!=null&&name.length()>0){
										name=name.trim();
									}
									pathDef.setName(name);
									String ids=subALL.attributeValue("DESC");
									List<LabWfPathVar> pathVarList = new ArrayList<LabWfPathVar>();
									if(ids!=null&&ids.length()>0){
										String[] idStr=ids.split(",");
										for (String idd : idStr) {
											if(idd==null||idd.length()==0)continue;
											String id[]=idd.split("\\*");
											LabWfPathVar pVarDef=new LabWfPathVar();
											pVarDef.setPathId(pathDef.getId());
											pVarDef.setPathName(pathDef.getName());
											pVarDef.setVariableName(id[0].trim());
											pVarDef.setOperator(id[1].trim());
											pVarDef.setValue(id[2].trim());
											pathVarList.add(pVarDef);
										}
									}
									pathDef.setPathVarList(pathVarList);
								}
							}
						}
					}
				}
			}
			disLinksDefList.add(pathDef);
		}
		return disLinksDefList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveOrUpdateFlowDefXml(String flowXml, String busId)
			throws GlobalException {
		LabWfProcess  processDef=labWfProcessDAO.getWfProcess(busId);
		//解析xml
		Element rootElement = this.xmlStrToElement(flowXml);
		//解析节点
		List<LabWfStep> stepDefList = this.getDisNodesDefFromXmlStr(rootElement);
		//解析线路
		List<LabWfPath> pathDefList = this.getDisNodesLinksFromXmlStr(rootElement);
		
		
		//删除已关联的迁移变量
		List<LabWfPathVar> pvList=labWfPathVarDAO.getPathVarListByBusId(busId);
		for(LabWfPathVar pv:pvList){
			labWfPathVarDAO.deleteLabWfPathVar(pv);
		}
		//先删除所有节点和迁移线
		List<LabWfPath> pathList = labWfPathDAO.getPathListByBusId(busId);
		if(pathList!=null&&pathList.size()>0){
			for (LabWfPath path : pathList) {
				labWfPathDAO.delPath(path);
			}
		}
		List<LabWfStep> stepList =labWfStepDAO.getStepAllListByBusId(busId);
		if(stepList!=null&&stepList.size()>0){
			for (LabWfStep step : stepList) {
				labWfStepDAO.delWfStep(step);
			}
		}
		//节点维护
		if(stepDefList!=null&&stepDefList.size()>0){
			int n=1;
			for(LabWfStep step:stepDefList){
				//完善步骤业务信息
				step.setStatus(LabWfConstant.NODES_STATUS_INIT);
				step.setProcessId(processDef.getId());
				step.setProcessName(processDef.getName());
				step.setNum(n);
				labWfStepDAO.addWfStep(step);
				n++;
			}
		}
		if(pathDefList!=null&&pathDefList.size()>0){
			for(LabWfPath path:pathDefList){
				//完善线路业务信息
				path.setStatus(LabWfConstant.LINKS_STATUS_INIT);
				path.setProcessId(busId);
				String uuid=path.getStartStep().getUuid();
				LabWfStep startStep= labWfStepDAO.getWfStepBybusIdAndUuid(busId,uuid);
				path.setStartStepName(startStep.getName());
				path.setStartStep(startStep);
				uuid=path.getEndStep().getUuid();
				LabWfStep endStep= labWfStepDAO.getWfStepBybusIdAndUuid(busId,uuid);
				path.setEndStepName(endStep.getName());
				path.setEndStep(endStep);
				labWfPathDAO.addPath(path);
				if(path.getPathVarList()!=null&&path.getPathVarList().size()>0){
					for (LabWfPathVar pv:path.getPathVarList()) {
						pv.setProcessId(busId);
						pv.setPathId(path.getId());
						pv.setPathName(path.getName());
						labWfPathVarDAO.addLabWfPathVar(pv);
					}
				}
			}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfFunStepVo> getWfFunStepList(String processId)
			throws GlobalException {
		String hql="FROM LabWfFunStep WHERE process.id='"+processId+"'";
		hql+=" AND stepType='Node'";
		List<LabWfFunStep> fsList=labWfProcessDAO.find(hql);
		List<LabWfFunStepVo> fsVoList=new ArrayList<LabWfFunStepVo>();
		if(null!=fsList){
			for (LabWfFunStep fs : fsList) {
				LabWfFunStepVo fsVo=new LabWfFunStepVo();
				BeanUtils.copyProperties(fs, fsVo, new String[]{""});
				fsVoList.add(fsVo);
			}
		}
		return fsVoList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfProcessVo> getWfProcessList(String busCode)
			throws GlobalException {
		String hql="FROM LabWfProcess WHERE 1=1";
		hql+=" AND funCode like '"+busCode+"'";
		List<LabWfProcess> poList=labWfProcessDAO.find(hql);
		List<LabWfProcessVo> voList=null;
		if(poList!=null&&poList.size()>0){
			 voList=new ArrayList<LabWfProcessVo>();
			for(LabWfProcess po:poList){
				LabWfProcessVo vo=new LabWfProcessVo();
				vo.setId(po.getId());
				vo.setName(po.getName());
				vo.setCode(po.getCode());
				vo.setComment(po.getComment());
				vo.setModifyDate(po.getModifyDate());
				vo.setCreateDate(po.getCreateDate());
				vo.setUserName(po.getUserName());
				vo.setStatus(po.getStatus());
				voList.add(vo);
			}
		}
		return voList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfVariableVo> getWfVariableList(String processId)
			throws GlobalException {
		List<LabWfVariableVo> voList=new ArrayList<LabWfVariableVo>();;
		if(StrUtils.isBlankOrNull(processId)){//新增时给默认变量
			LabWfVariableVo vo1=new LabWfVariableVo();
			vo1.setName("提交");
			vo1.setComment("初始默认，可删除");
			vo1.setValue("1");
			voList.add(vo1);
//			LabWfVariableVo vo2=new LabWfVariableVo();
//			vo2.setName("保存");
//			vo2.setComment("初始默认，可删除");
//			vo2.setValue("0");
//			voList.add(vo2);
			LabWfVariableVo vo3=new LabWfVariableVo();
			vo3.setName("退回");
			vo3.setComment("初始默认，可删除");
			vo3.setValue("-1");
			voList.add(vo3);
		}else{
			String hql="FROM LabWfVariable WHERE 1=1";
			if(null!=processId&&!processId.equals("")){
				hql+=" AND processId='"+processId+"'";
			}
			List<LabWfVariable> poList=labWfVariableDAO.find(hql);
			if(null!=poList&&poList.size()>0){
				for(LabWfVariable po:poList){
					LabWfVariableVo vo=new LabWfVariableVo();
					vo.setId(po.getId());
					vo.setProcessId(po.getProcessId());
					vo.setProcessName(po.getProcessName());
					vo.setName(po.getName());
					vo.setComment(po.getComment());
					vo.setValue(po.getValue());
					voList.add(vo);
				}
			}else{
				LabWfVariableVo vo1=new LabWfVariableVo();
				vo1.setProcessId(processId);
				vo1.setProcessName("");
				vo1.setName("提交");
				vo1.setComment("初始默认，可删除");
				vo1.setValue("1");
				voList.add(vo1);
				LabWfVariableVo vo2=new LabWfVariableVo();
				vo2.setProcessId(processId);
				vo2.setProcessName("");
				vo2.setName("保存");
				vo2.setComment("初始默认，可删除");
				vo2.setValue("0");
				voList.add(vo2);
				LabWfVariableVo vo3=new LabWfVariableVo();
				vo3.setProcessId(processId);
				vo3.setProcessName("");
				vo3.setName("退回");
				vo3.setComment("初始默认，可删除");
				vo3.setValue("-1");
				voList.add(vo3);
			}
		}
		return voList;
	}

	public List<LabWfPathVarVo> getWfPathVarList(String[] idstr)
			throws GlobalException {
		List<LabWfPathVarVo> varList =new ArrayList<LabWfPathVarVo>();
		if(null!=idstr){
			for (String idst : idstr) {
				if(idst.contains("*")){
					LabWfPathVarVo pv=new LabWfPathVarVo();
					String id[]=idst.split("\\*");
					if(id.length>2&&!StrUtils.isBlankOrNull(id[0])){
						LabWfVariable po=(LabWfVariable)labWfVariableDAO.findById(LabWfVariable.class, id[0]);
						if(null!=po){
							pv.setVariableName(po.getName());
							pv.setVariableId(po.getId());
						}
						pv.setValue(id[2]);
						pv.setOperator(id[1]);
					}
					varList.add(pv);
				}
			}
		}
		return varList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isHasOpenedProcess4Fun(String funId) throws GlobalException {
		String hql="FROM LabWfProcess WHERE 1=1 AND status='"+LabWfConstant.PROCESS_OPEN+"'";
		hql+=" AND funId like '"+funId+"'";
		List<LabWfProcess> poList=labWfProcessDAO.find(hql);
		if(poList!=null&&poList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<LabWfFunStepVo> getLabWfFunStepVo(LabWfProcessVo processDefVo) throws GlobalException {
		// TODO Auto-generated method stub
		List<LabWfFunStepVo> labWfFunStepVoList=new ArrayList<LabWfFunStepVo>();
		if(!StrUtils.isBlankOrNull(processDefVo.getId())){
			String hql="FROM LabWfFunStep WHERE process.id='"+processDefVo.getId()+"'";
			List<LabWfFunStep> labWfFunStepList=labWfProcessDAO.find(hql);
			if(labWfFunStepList.size()>0){
				for(LabWfFunStep labWfFunStep:labWfFunStepList){
					LabWfFunStepVo labWfFunStepVo=new LabWfFunStepVo();
					BeanUtils.copyProperties(labWfFunStep, labWfFunStepVo,new String[]{"process"});
					labWfFunStepVoList.add(labWfFunStepVo);
				}
			}
		}
		return labWfFunStepVoList;
	}
}