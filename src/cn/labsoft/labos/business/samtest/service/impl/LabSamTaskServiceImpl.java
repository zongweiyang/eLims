package cn.labsoft.labos.business.samtest.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestBeatchDAO;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestDAO;
import cn.labsoft.labos.business.samtest.entity.LabSamTest;
import cn.labsoft.labos.business.samtest.entity.LabSamTestBeatch;
import cn.labsoft.labos.business.samtest.service.ILabSamTaskService;
import cn.labsoft.labos.business.samtest.vo.LabDemoVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSamTaskService")
public class LabSamTaskServiceImpl implements ILabSamTaskService {
	@Resource
	private ILabSamTestDAO labSamTestDAO;
	@Resource
	private ILabSamTestBeatchDAO labSamTestBeatchDAO;
	@Resource
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	@Resource
	private ILabSampRegisterDAO labSampRegisterDAO;
	@Resource
	private ILabItemDAO labItemDAO;
	@Resource
	private ILabOrgDAO labOrgDAO;
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTestVo> getLabSamTeskVo4Issued(String busId) throws GlobalException {
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		if (!StrUtils.isBlankOrNull(busId)) {
			String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + busId + "'";
				   hql += " AND isSued='" + Constants_Business.N+ "'";
			List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
			if (listLabSamTest != null && listLabSamTest.size() > 0) {
				hql = "SELECT itemName,itemId,COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND orgId IS NULL  AND taskId='" + busId + "'";
				hql += "  GROUP BY itemId";
				List<Object[]> list = labSamTestDAO.find(hql);
				if (list != null && list.size() > 0) {
					for (Object[] labSamTest : list) {
						if (labSamTest != null) {
							LabSamTestVo labSamTestVo = new LabSamTestVo();
							labSamTestVo.setItemName(labSamTest[0].toString());
							labSamTestVo.setItemId(labSamTest[1].toString());
							labSamTestVo.setSampNum(Integer.valueOf(labSamTest[2].toString()));
							labSamTestVo.setTaskId(busId);
							listLabSamTestVo.add(labSamTestVo);
						}
					}
				}

			}
		}
		return listLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTeskVo4Issued(List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		boolean key = true;
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		if (listLabSamTestVo != null && listLabSamTestVo.size() > 0) {
			String busId="";
			Set<String> orgSet=new HashSet<String>();
			Set<String> itemSet=new HashSet<String>();
			for (LabSamTestVo labSamTestVo : listLabSamTestVo) {
				if (!StrUtils.isBlankOrNull(labSamTestVo.getOrgId())&&!labSamTestVo.getOrgId().equals("0")) {
					busId=labSamTestVo.getTaskId();
					String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSamTestVo.getTaskId() + "' AND itemId='" + labSamTestVo.getItemId() + "'";
					   hql += " AND isSued='"+Constants_Business.N+"'";
					List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
					if (listLabSamTest != null && listLabSamTest.size() > 0) {
						for (LabSamTest labSamTest : listLabSamTest) {
							labSamTest.setOrgId(labSamTestVo.getOrgId().split("&")[0]);
							labSamTest.setOrgName(labSamTestVo.getOrgId().split("&")[1]);
							labSamTest.setIsSued(Constants_Business.Y);
							try {
								labSamTestDAO.updateLabSamTest(labSamTest);
							} catch (Exception e) {
								throw new GlobalException("" + e.getMessage());
							}
							itemSet.add(labSamTest.getItemName());
							orgSet.add(labSamTest.getOrgId());
						}
					}
				}
			}
			String hqlx = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + busId + "'";
			       hqlx += " AND isSued='"+Constants_Business.N+"'";
			List<LabSamTest> listLabSamTestx = labSamTestDAO.find(hqlx);
			if (listLabSamTestx != null && listLabSamTestx.size() > 0) {
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(busId,busId,
						Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务下达："+itemSet.toString(),
						LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_SUB,orgSet.toArray(new String[orgSet.size()]),LabWfConstant.OPER_TYPE_ORG);
				if (ins == null) {
					throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
				}
			}else{
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(busId,busId,
						Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务下达："+itemSet.toString(),
						LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_ALL,orgSet.toArray(new String[orgSet.size()]),LabWfConstant.OPER_TYPE_ORG);
				if (ins == null) {
					throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
				}
			}
		}
		return key;
	}

	public List<LabSamTestVo> getLabSamTeskVoListByWhere(String wereHql) throws GlobalException {
		List<LabSamTestVo> labSamTestVoList = new ArrayList<LabSamTestVo>();
		String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabSamTest> labSamTestList = labSamTestDAO.find(hql);
		if (labSamTestList != null && labSamTestList.size() > 0) {
			for (LabSamTest labSamTest : labSamTestList) {
				LabSamTestVo labSamTestVo = new LabSamTestVo();
				labSamTestVo = this.po2Vo(labSamTest, labSamTestVo);
				labSamTestVoList.add(labSamTestVo);
			}
		}
		return labSamTestVoList;
	}

	public LabSamTest vo2Po(LabSamTestVo labSamTestVo, LabSamTest labSamTest) {
		BeanUtils.copyProperties(labSamTestVo, labSamTest, new String[] { "isDel" });
		return labSamTest;
	}

	public LabSamTestVo po2Vo(LabSamTest labSamTest, LabSamTestVo labSamTestVo) {
		BeanUtils.copyProperties(labSamTest, labSamTestVo);
		return labSamTestVo;
	}

	@Override
	public List<LabSamTestVo> getLabSamTeskVoBatch4Issued(String[] busIds) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		List<LabDemoVo> listTitle = new ArrayList<LabDemoVo>();
		String ids = "";
		if (busIds != null && busIds.length > 0) {
			for (String id : busIds) {
				if (!StrUtils.isBlankOrNull(id)) {
					LabSampRegister labSampRegister = (LabSampRegister) labSamTestDAO.findById(LabSampRegister.class, id);
					LabDemoVo demoVo = new LabDemoVo();
					demoVo.setDemo(labSampRegister.getId());
					demoVo.setDemo1(labSampRegister.getNo());
					ids = ids + "'" + labSampRegister.getId() + "'";
					ids += ",";
					listTitle.add(demoVo);
				}
			}
			if (!StrUtils.isBlankOrNull(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				String hql = "SELECT itemName,itemId,COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND isSued='" + Constants_Business.N+ "' AND taskId IN (" + ids + ") GROUP BY itemId";
				List<Object[]> listObj = labSamTestDAO.find(hql);
				if (listObj != null && listObj.size() > 0) {
					for (Object[] objs : listObj) {
						LabSamTestVo labSamTestVo = new LabSamTestVo();
						labSamTestVo.setItemId(objs[1].toString());
						labSamTestVo.setItemName(objs[0].toString());
						labSamTestVo.setSampNum(Integer.valueOf(objs[2].toString()));
						if (listTitle.size() > 0) {
							List<LabDemoVo> listLabDemoVo = new ArrayList<LabDemoVo>();
							for (LabDemoVo vo : listTitle) {
								hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND isSued='" + Constants_Business.N+ "' AND taskId='" + vo.getDemo() + "' AND itemId='" + objs[1].toString() + "'";
								List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
								LabDemoVo demoVo = new LabDemoVo();
								if (listLabSamTest != null && listLabSamTest.size() > 0) {
									demoVo.setDemo("N");
									demoVo.setDemo1(vo.getDemo());
								}
								listLabDemoVo.add(demoVo);
							}
							labSamTestVo.setListLabDemoVo(listLabDemoVo);
						}
						listLabSamTestVo.add(labSamTestVo);
					}
				}
				if (listLabSamTestVo.size() > 0)
					listLabSamTestVo.get(0).setListTitle(listTitle);
			}

		}
		return listLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTeskVo4IssuedBeach(List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		boolean key = true;
		Map<String,Set[]> mapList=new HashMap<String,Set[]>();
		if (listLabSamTestVo != null && listLabSamTestVo.size() > 0) {
			for (LabSamTestVo labSamTestVo : listLabSamTestVo) {
				if(labSamTestVo==null)continue;
				if (!StrUtils.isBlankOrNull(labSamTestVo.getItemId()) && !StrUtils.isBlankOrNull(labSamTestVo.getOrgId())&&(!labSamTestVo.getOrgId().trim().equals("0"))&& !StrUtils.isBlankOrNull(labSamTestVo.getTaskId())) {
					String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSamTestVo.getTaskId() + "' AND itemId='" + labSamTestVo.getItemId() + "'";
					hql += " AND isSued='"+Constants_Business.N+"'";
					List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
					if (listLabSamTest != null && listLabSamTest.size() > 0) {
						for (LabSamTest labSamTest : listLabSamTest) {
							labSamTest.setOrgId(labSamTestVo.getOrgId().split("&")[0]);
							labSamTest.setOrgName(labSamTestVo.getOrgId().split("&")[1]);
							labSamTest.setIsSued(Constants_Business.Y);
							try {
								labSamTestDAO.updateLabSamTest(labSamTest);
							} catch (Exception e) {
								throw new GlobalException("" + e.getMessage());
							}
							//组装校验人及对应的项目
							Set[] sets=mapList.get(labSamTest.getTaskId());
							if(sets==null){
								sets=new Set[2];
								sets[0]=new HashSet<String>();
								sets[1]=new HashSet<String>();
							}
							sets[0].add(labSamTest.getOrgId());
							sets[1].add(labSamTest.getItemName());
							mapList.put(labSamTest.getTaskId(),sets);
						}
					}
				}
				Set<String> keysSet = mapList.keySet();	
				if(keysSet.size()>0){
					for (Object keys : keysSet) {
						Set[] set=mapList.get(keys);
						if(set.length!=2)continue;
						String taskId=String.valueOf(keys);
						String hqlx = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + taskId.trim() + "'";
						       hqlx += " AND isSued='"+Constants_Business.N+"'";
						List<LabSamTest> listLabSamTestx = labSamTestDAO.find(hqlx);
						if (listLabSamTestx != null && listLabSamTestx.size() > 0) {
							LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(taskId,taskId,
									Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务下达："+set[1].toString(),
									LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_SUB,(String[])set[0].toArray(new String[set[0].size()]),LabWfConstant.OPER_TYPE_ORG);
							if (ins == null) {
								throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
							}
						}else{
							LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(taskId,taskId,
									Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务下达："+set[1].toString(),
									LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_ALL,(String[]) set[0].toArray(new String[set[0].size()]),LabWfConstant.OPER_TYPE_ORG);
							if (ins == null) {
								throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
							}
						}
					}
				}
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTeskIssuedPR(LabSamTestVo labSamTestVo, PageResult pageResult) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		
		String hql = "FROM LabSampRegister WHERE isDel='" + Constants_Business.N + "'";
		if(!StrUtils.isBlankOrNull(labSamTestVo.getTaskNo())){
			hql+=" AND no LIKE '%"+labSamTestVo.getTaskNo().trim()+"%'";
		}
		if (null != labSamTestVo.getStatus()
				&& !"".equals(labSamTestVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labSamTestVo.getStatus()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}
		pageResult = labSamTestDAO.getPageResult(hql, pageResult);
		List<LabSampRegister> listLabSamTest = pageResult.getResultList();
		List<LabSampRegisterVo> listLabSamTestVo = new ArrayList<LabSampRegisterVo>();
		if (listLabSamTest != null && listLabSamTest.size() > 0) {
			for (LabSampRegister sampRegister : listLabSamTest) {
				LabSampRegisterVo vo = new LabSampRegisterVo();
				BeanUtils.copyProperties(sampRegister, vo);
				LabWfProcessIns ins=sampRegister.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(sampRegister.getId(),sampRegister.getId(),son.getFunId());
							if(flag){
								vo.setIsBack(Constants_Business.Y);
							}else{
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
						vo.setStatus(str);
					}
				}
				listLabSamTestVo.add(vo);
			}
		}
		pageResult.setResultList(listLabSamTestVo);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTesk4AllotPR(LabSamTestVo labSamTestVo, PageResult pageResult) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql = "FROM LabSampRegister WHERE isDel='" + Constants_Business.N + "'";
		if(!StrUtils.isBlankOrNull(labSamTestVo.getTaskNo())){
			hql+=" AND no LIKE '%"+labSamTestVo.getTaskNo().trim()+"%'";
		}
		if (null != labSamTestVo.getStatus()
				&& !"".equals(labSamTestVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labSamTestVo.getStatus()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}
		pageResult = labSamTestDAO.getPageResult(hql, pageResult);
		List<LabSampRegister> listLabSamTest = pageResult.getResultList();
		List<LabSampRegisterVo> listLabSamTestVo = new ArrayList<LabSampRegisterVo>();
		String ids[]=labOrgDAO.getLabOrgList4Sub(son.getOrgId());
		for (LabSampRegister labSampRegister : listLabSamTest) {
			LabSampRegisterVo vo = new LabSampRegisterVo();
			BeanUtils.copyProperties(labSampRegister, vo, new String[] { });
			String sonHql = "SELECT itemName,COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSampRegister.getId()+ "' ";
			sonHql+=" AND orgId IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
			if (null != labSamTestVo.getStatus() && !"".equals(labSamTestVo.getStatus())) {
				sonHql += " AND isSued='"+Constants_Business.Y+"'";
			}
			sonHql+="GROUP BY itemId";
			List<Object[]> list = labSamTestDAO.find(sonHql);
			if (list != null && list.size() > 0) {
				String names="";
				int num=0;
				for (Object[] obj : list) {
					names+=obj[0]+",";
					num+=Integer.valueOf(obj[1].toString());
				}
				if (names.endsWith(",")) {
					names=names.substring(0, names.length() - 1);
				}
				vo.setItemNames(names);
				vo.setSampNum(num+"");
			}
			LabWfProcessIns ins=labSampRegister.getProcessIns();
			if (ins != null) {
				String status = ins.getStatus();
				if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
					vo.setStatus("已完结");
				} else {
					if (status.contains(son.getFunId())) {
						vo.setIsOper(Constants_Business.Y);
						boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSampRegister.getId(),labSampRegister.getId(),son.getFunId());
						if(flag){
							vo.setIsBack(Constants_Business.Y);
						}else{
							vo.setIsBack(Constants_Business.N);
						}
					}
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
					vo.setStatus(str);
				}
			}
			listLabSamTestVo.add(vo);
		}
		pageResult.setResultList(listLabSamTestVo);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTestVo> getLabSamTeskVo4Allot(String busId) throws GlobalException {
		SessionContainer son=SessionContainer.getSessionContainer();
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		String hql = "SELECT itemName,itemId,COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "'  AND taskId='" + busId + "'";
		hql += " AND isSued='"+Constants_Business.Y+"'";
		String ids[]=labOrgDAO.getLabOrgList4Sub(son.getOrgId());
		hql+=" AND orgId IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
		hql += "  GROUP BY itemId";
		List<Object[]> list = labSamTestDAO.find(hql);
		if (list != null && list.size() > 0) {
			for (Object[] labSamTest : list) {
				if (labSamTest != null) {
					LabSamTestVo labSamTestVo = new LabSamTestVo();
					labSamTestVo.setItemName(labSamTest[0].toString());
					labSamTestVo.setItemId(labSamTest[1].toString());
					labSamTestVo.setSampNum(Integer.valueOf(labSamTest[2].toString()));
					labSamTestVo.setTaskId(busId);
					hql = "SELECT COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "'   AND labSamTestBeatch.id!=''  AND taskId='" + busId + "'";
					hql += " AND itemId='" + labSamTest[1].toString() + "'";
					ids=labOrgDAO.getLabOrgList4Sub(son.getOrgId());
					hql+=" AND orgId IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
					Object o = labSamTestDAO.find0(hql);
					if (o != null){
						if(Integer.valueOf(o.toString())==Integer.valueOf(labSamTest[2].toString()))
							continue;
						labSamTestVo.setSampNumTask(Integer.valueOf(o.toString()));
					}else
						labSamTestVo.setSampNumTask(0);
					listLabSamTestVo.add(labSamTestVo);
				}
			}
		}
		return listLabSamTestVo;
	}

	@Override
	public List<LabSamTestVo> getLabSamTeskVoAllotShow(LabSamTestVo labSamTestVo) throws GlobalException, UnsupportedEncodingException {
		List<LabSamTestVo> returnLabSamTestVo=new ArrayList<LabSamTestVo>();
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		Map<String,Integer> map=new HashMap<String, Integer>();  
		if (!StrUtils.isBlankOrNull(labSamTestVo.getItemName())) {
			for (int i = 0; i < labSamTestVo.getItemName().split(",").length; i++) {
				String item = URLDecoder.decode(labSamTestVo.getItemName(), "UTF-8").split(",")[i].trim();
				String sampNum = labSamTestVo.getDemo().split(",")[i];
				String testMan = URLDecoder.decode(labSamTestVo.getTestManName(), "UTF-8").split(",")[i];
				String checkMan = URLDecoder.decode(labSamTestVo.getCheckManName(), "UTF-8").split(",")[i];
				LabSamTestVo vo = new LabSamTestVo();
				vo.setItemName(item.replace("'|'", ",").split(",")[0]);
				vo.setItemId(item.replace("'|'", ",").split(",")[1].trim());
				vo.setSampNum(Integer.valueOf(sampNum));
				vo.setTestManName(testMan.replace("|", ",").split(",")[1]);
				vo.setTestManId(testMan.replace("|", ",").split(",")[0]);
				vo.setCheckManName(checkMan.replace("|", ",").split(",")[1]);
				vo.setCheckManId(checkMan.replace("|", ",").split(",")[0]);
				vo.setTaskId(labSamTestVo.getTaskId().split(",")[i]);
				Integer count=map.get(labSamTestVo.getTaskId().split(",")[i]);
				if(count==null)count=0;
				map.put(labSamTestVo.getTaskId().split(",")[i], (count+1));
				listLabSamTestVo.add(vo);
			}
		}
		Set keysSet = map.keySet();	
		Iterator iterator = keysSet.iterator();	
		int sort=0;
		while(iterator.hasNext()) {		
			Object key = iterator.next();
			LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(key.toString());
			LabSamTestVo vo=new LabSamTestVo();
			vo.setTaskNo(labSampRegister.getNo());
			List<LabSamTestVo> titleLabSamTestVo=new ArrayList<LabSamTestVo>();
			for(LabSamTestVo samTestVo:listLabSamTestVo){
				if(samTestVo.getTaskId().equals(labSampRegister.getId())){
					samTestVo.setSort(sort);
					sort++;
					titleLabSamTestVo.add(samTestVo);
					
				}
			}
			vo.setSpanRow(titleLabSamTestVo.size());
			vo.setListLabSamTest(titleLabSamTestVo);
			returnLabSamTestVo.add(vo);
		}
		return returnLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTeskVo4Allot(LabSamTestBeatchVo labSamTestBeatchVo, List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		boolean key = false;
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getTaskIds()) && !StrUtils.isBlankOrNull(labSamTestBeatchVo.getName())) {
			String taskIds[]=labSamTestBeatchVo.getTaskIds().split(",");
			Set<String> busSet=new HashSet<String>();
			for (String string : taskIds) {
				if(string==null||string.trim().length()==0)continue;
				busSet.add(string.trim());
			}
			for (String taskId: busSet) {
				Set<String> testSet=new HashSet<String>();
				Set<String> itemSet=new HashSet<String>();
				LabSamTestBeatch labSamTestBeatch = new LabSamTestBeatch();
				BeanUtils.copyProperties(labSamTestBeatchVo, labSamTestBeatch);
				labSamTestBeatch.setTaskIds(taskId);
				labSamTestBeatch = labSamTestBeatchDAO.addLabLabSamTestBeatch(labSamTestBeatch);
				String itemIds = "";
				String itemNames = "";
				 String ids[]=labOrgDAO.getLabOrgList4Sub(son.getOrgId());
				if (listLabSamTestVo != null && listLabSamTestVo.size() > 0) {
					for (LabSamTestVo labSamTestVo : listLabSamTestVo) {
						if (!StrUtils.isNull(labSamTestVo.getTaskId())&&labSamTestVo.getTaskId().equals(taskId)) {
							String sql = "SELECT id FROM lab_sam_test AS test  WHERE is_del='N' AND item_id='" + labSamTestVo.getItemId() + "' AND task_id='" + labSamTestVo.getTaskId() + "' ";
							sql+=" AND org_id IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
							sql+=" AND beatch_id IS NULL ORDER BY id LIMIT "+labSamTestVo.getSampNum();
							List<String> listId = labSamTestDAO.createSqlQuery(sql);
							if (listId != null && listId.size() > 0) {
								for (int i=0;i<listId.size();i++) {
									String id=listId.get(i);
									if(StrUtils.isBlankOrNull(id))continue;
									LabSamTest labSamTest=labSamTestDAO.getLabSamTest(id);
									labSamTest.setCheckManId(labSamTestVo.getCheckManId());
									labSamTest.setCheckManName(labSamTestVo.getCheckManName());
									labSamTest.setTestManId(labSamTestVo.getTestManId());
									labSamTest.setTestManName(labSamTestVo.getTestManName());
									labSamTest.setLabSamTestBeatch(labSamTestBeatch);
									labSamTest.setIsTask(Constants_Business.Y);
									labSamTestDAO.updateLabSamTest(labSamTest);
									//组装校验人及对应的项目
									itemSet.add(labSamTest.getItemName());
									testSet.add(labSamTest.getTestManId());
								}
							}
						}
						itemNames +=labItemDAO.getLabItem(labSamTestVo.getItemId()).getName();
						itemNames += ",";
						itemIds += labSamTestVo.getItemId();
						itemIds += ",";
					}
					key = true;
					String hql = "SELECT id FROM LabSamTest WHERE isDel='"+Constants_Business.N+"' " +
						"AND isSued='"+Constants_Business.Y+"'  AND isTask<>'"+Constants_Business.Y+"' " +
					    "AND taskId='" + taskId+ "' ";
						hql+=" AND orgId IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
					List list = labSamTestDAO.find(hql);
					if (list != null && list.size() > 0) {
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(taskId,labSamTestBeatch.getId(),
								Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务分配："+itemSet.toString(),
								LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_SUB,testSet.toArray(new String[testSet.size()]),LabWfConstant.OPER_TYPE_USER);
						if (ins == null) {
							throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
						}else{
							labSamTestBeatch.setProcessIns(ins);
						}
					}else{
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(taskId,labSamTestBeatch.getId(),
								Constants_Business.WF_CODE_BUS_RW,son.getFunId(), "任务分配："+itemSet.toString(),
								LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_ALL,testSet.toArray(new String[testSet.size()]),LabWfConstant.OPER_TYPE_USER);
						if (ins == null) {
							throw new GlobalException("LabSamTaskServiceImpl...流程实例化出错！");
						}else{
							labSamTestBeatch.setProcessIns(ins);
						}
					}
					if (itemIds.trim().length() > 0) {
						labSamTestBeatch.setItemIds(itemIds.substring(0, itemIds.length() - 1));
						labSamTestBeatch.setItemNames(itemNames.substring(0, itemNames.length() - 1));
						labSamTestBeatchDAO.updateLabLabSamTestBeatch(labSamTestBeatch);
					}
				}
			}
		}
		return key;
	}

	@Override
	public List<LabSamTestVo> getLabSamTeskVo4AllotBeatch(String[] busIds) throws GlobalException {
		List<LabSamTestVo> listLabSamTestVo=new ArrayList<LabSamTestVo>();
		if(busIds!=null&&busIds.length>0){
			for(String busId:busIds){
				if(!StrUtils.isBlankOrNull(busId)){
					LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(busId);
					List<LabSamTestVo> list=this.getLabSamTeskVo4Allot(busId);
					if(list!=null&&list.size()>0){
						LabSamTestVo vo=new LabSamTestVo();
						vo.setSpanRow(list.size());
						vo.setListLabSamTest(list);
						vo.setTaskNo(labSampRegister.getNo());
						listLabSamTestVo.add(vo);
					}
				}
			}
		}
		return listLabSamTestVo;
	}

}
