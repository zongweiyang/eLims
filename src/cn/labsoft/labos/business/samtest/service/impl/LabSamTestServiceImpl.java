package cn.labsoft.labos.business.samtest.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDAO;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDataDAO;
import cn.labsoft.labos.business.samreport.entity.LabSamReport;
import cn.labsoft.labos.business.samreport.entity.LabSamReportData;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestBeatchDAO;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestDAO;
import cn.labsoft.labos.business.samtest.entity.LabSamTest;
import cn.labsoft.labos.business.samtest.entity.LabSamTestBeatch;
import cn.labsoft.labos.business.samtest.service.ILabSamTestService;
import cn.labsoft.labos.business.samtest.vo.LabDemoVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.reagent.dao.ILabReaOutDetailDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReaOutMainDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaOutDetail;
import cn.labsoft.labos.source.reagent.entity.LabReaOutMain;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
import cn.labsoft.labos.source.reagent.vo.LabReaOutDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;
import cn.labsoft.labos.source.reference.dao.ILabRefOutDetailDAO;
import cn.labsoft.labos.source.reference.dao.ILabRefOutMainDAO;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabRefOutDetail;
import cn.labsoft.labos.source.reference.entity.LabRefOutMain;
import cn.labsoft.labos.source.reference.entity.LabReference;
import cn.labsoft.labos.source.reference.vo.LabRefOutDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSamTestService")
public class LabSamTestServiceImpl implements ILabSamTestService {
	public ExecutorService pool = Executors.newSingleThreadExecutor();

	@Resource
	private ILabSamTestBeatchDAO labSamTestBeatchDAO;
	@Resource
	private ILabSamTestDAO labSamTestDAO;
	@Resource
	private ILabStandardItemDAO labStandardItemDAO;
	@Resource
	private ILabReagentDAO labReagentDAO;
	@Resource
	private ILabRefOutDetailDAO labRefOutDetailDAO;
	@Resource
	private ILabReaOutMainDAO labReaOutMainDAO;
	@Resource
	private ILabReaOutDetailDAO labReaOutDetailDAO;
	@Resource
	private ILabRefOutMainDAO labRefOutMainDAO;
	@Resource
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	@Resource
	private ILabSampRegisterDAO labSampRegisterDAO;
	@Resource
	private ILabSamReportDAO labSamReportDAO;
	@Resource
	private ILabSamReportDataDAO labSamReportDataDAO;
	@Resource
	private ILabNumberDAO labNumberDAO;
	@Resource
	private ILabReferenceDAO labReferenceDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTestPR(LabSamTestBeatchVo labSamTestBeatchVo, PageResult pageResult) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		List<LabSamTestBeatchVo> listSamTestBeatchVo = new ArrayList<LabSamTestBeatchVo>();
		String hql = "FROM LabSamTestBeatch WHERE isDel='" + Constants_Business.N + "' ";
		if (null != labSamTestBeatchVo.getStatus() && !"".equals(labSamTestBeatchVo.getStatus())) {
			String subhql = "SELECT stepBusId FROM LabWfStepIns WHERE isDel='" + Constants_Business.N + "' AND busType='" + Constants_Business.WF_CODE_BUS_RW + "' AND status<>'" + LabWfConstant.BUS_PROCESS_END + "' AND code='" + labSamTestBeatchVo.getStatus() + "'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql + ")";
		}
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getName()))
			hql += " AND name LIKE '%" + labSamTestBeatchVo.getName().trim() + "%'";

		pageResult = labSamTestBeatchDAO.getPageResult(hql, pageResult);
		List<LabSamTestBeatch> listLabSamTestBeatch = pageResult.getResultList();
		if (listLabSamTestBeatch != null && listLabSamTestBeatch.size() > 0) {
			for (LabSamTestBeatch labSamTestBeatch : listLabSamTestBeatch) {
				LabSamTestBeatchVo vo = new LabSamTestBeatchVo();
				BeanUtils.copyProperties(labSamTestBeatch, vo);
				String sonHql = "select COUNT(distinct(sam_Id)) FROM lab_sam_test WHERE is_Del='" + Constants_Business.N + "' AND beatch_id='" + labSamTestBeatch.getId() + "'";
				List<Object[]> listObj = labSamTestBeatchDAO.createSqlQuery(sonHql);
				if (listObj != null && listObj.size() > 0) {
					Object oo = listObj.get(0);
					vo.setSampNum(Integer.valueOf(String.valueOf(oo)));
				}
				LabWfProcessIns ins = labSamTestBeatch.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag = labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSamTestBeatch.getTaskIds(), labSamTestBeatch.getId(), son.getFunId());
							if (flag) {
								vo.setIsBack(Constants_Business.Y);
							} else {
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
						vo.setStatus(str);
					}
				}
				listSamTestBeatchVo.add(vo);
			}
			pageResult.setResultList(listSamTestBeatchVo);
		}
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTestVo> getLabSamTestBeatchVo(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		SessionContainer son=SessionContainer.getSessionContainer();
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())) {
			String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
			hql += " AND isTest!='" + Constants_Business.Y + "'";
		//	hql += " AND testManId='" +son.getUserId()+ "'";
			hql += " GROUP BY itemId";
			List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
			if (listLabSamTest != null && listLabSamTest.size() > 0) {
				for (LabSamTest labSamTest : listLabSamTest) {
					LabSamTestVo labSamTestVo = new LabSamTestVo();
					BeanUtils.copyProperties(labSamTest, labSamTestVo, new String[] { "processIns", "labSamTestBeatch" });
						hql="FROM LabStandardItem WHERE isDel='"+Constants_Business.N+"' AND item.id='"+labSamTest.getItemId()+"' AND standard.id='"+labSamTest.getStandardId()+"'";
						String describe="";
						String norm="";
						LabStandardItem labStandardItem=(LabStandardItem) labSamTestBeatchDAO.find(hql,0);
						if(labStandardItem!=null&&!StrUtils.isBlankOrNull(labStandardItem.getMaxKey())){
							describe+=showDescribe(labStandardItem.getMaxKey());
							describe+=labStandardItem.getMaxValue();
							describe+="("+labStandardItem.getItem().getUnit()+")";
							describe+=",";
							describe+=showDescribe(labStandardItem.getMinKey());
							describe+=labStandardItem.getMinValue();
							describe+="("+labStandardItem.getItem().getUnit()+")";
							describe+=",";
							describe+="保留"+labStandardItem.getDecimalCount()+"位小数";
							describe+=",";
							norm+=labStandardItem.getMaxKey();
							norm+=":";
							norm+=labStandardItem.getMaxValue();
							norm+=",";
						}
						if(describe.length()>0)describe=describe.substring(0,describe.length()-1);
						if(norm.length()>0)norm=norm.substring(0,norm.length()-1);
						labSamTestVo.setDescribeWri(norm);
						labSamTestVo.setDescribeFormula(describe);
					listLabSamTestVo.add(labSamTestVo);
				}
			}
		}
		return listLabSamTestVo;
	}
	public String showDescribe(String mark){
		String describe="";
		if(mark.trim().equals("<"))describe="小于";
		else if(mark.trim().equals("<="))describe="小于等于";
		else if(mark.trim().equals(">"))describe="大于";
		else if(mark.trim().equals(">="))describe="大于等于";
		else if(mark.trim().equals("="))describe="等于";
		return describe;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTest4Standard(LabSamTestBeatchVo labSamTestBeatchVo, List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		boolean key = false;
		String hql = "";
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId()) && listLabSamTestVo != null && listLabSamTestVo.size() > 0) {
			for (LabSamTestVo labSamTestVo : listLabSamTestVo) {
				hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
				hql += " AND itemId='" + labSamTestVo.getItemId() + "'";
				List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
				if (listLabSamTest != null && listLabSamTest.size() > 0) {
					for (LabSamTest labSamTest : listLabSamTest) {
						try {
							labSamTest.setDescribeWri(labSamTestVo.getDescribeWri());
							labSamTest.setTemperature(labSamTestVo.getTemperature());
							labSamTest.setHumidity(labSamTestVo.getHumidity());
							labSamTest.setDescribeFormula(formate(labSamTestVo.getDescribeFormula()));
							labSamTestDAO.updateLabSamTest(labSamTest);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
					key = true;
				}
			}
		}
		return key;
	}

	public String formate(String formula) {
		formula = formula.replace("&lt;", "<");
		formula = formula.replace("&gt;", ">");
		return formula;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTestVo> getLabSamTestVo4Date(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		SessionContainer son=SessionContainer.getSessionContainer();
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		List<LabDemoVo> listLabDemoVo = new ArrayList<LabDemoVo>();
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())) {
			String hql = "SELECT  itemId,itemName,checkManName FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
			hql += " AND testManId='" +son.getUserId()+ "'";
			hql += " AND isTest<>'" + Constants_Business.Y + "'";
			hql += " GROUP BY itemId ";
			List<Object[]> listTitle = labSamTestDAO.find(hql);
			if (listTitle != null && listTitle.size() > 0) {
				for (Object[] o : listTitle) {
					LabDemoVo labDemoVo = new LabDemoVo();
					labDemoVo.setDemo(o[0].toString());
					labDemoVo.setDemo1(o[1].toString());
					labDemoVo.setDemo2(o[2].toString());
					listLabDemoVo.add(labDemoVo);
				}
			}
			if (listLabDemoVo.size() > 0) {
				hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
				hql += " AND testManId='" +son.getUserId()+ "'";
				hql += " AND isTest<>'" + Constants_Business.Y + "'";
				hql += "GROUP BY labSamId";
				List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
				if (listLabSamTest != null && listLabSamTest.size() > 0) {
					for (LabSamTest labSamTest : listLabSamTest) {
						LabSamTestVo labSamTestVo = new LabSamTestVo();
						BeanUtils.copyProperties(labSamTest, labSamTestVo, new String[] { "processIns", "labSamTestBeatch" });
						List<LabDemoVo> listLabDemo = new ArrayList<LabDemoVo>();
						for (LabDemoVo demoVo : listLabDemoVo) {
							LabDemoVo labDemoVo = new LabDemoVo();
							hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamId='" + labSamTest.getLabSamId() + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "' AND itemId='" + demoVo.getDemo() + "'";
							hql += " AND testManId='" +son.getUserId()+ "'";
							hql += " AND isTest<>'" + Constants_Business.Y + "'";
							List<LabSamTest> sonLabSamTest = labSamTestDAO.find(hql);
							if (sonLabSamTest != null && sonLabSamTest.size() > 0) {
								labDemoVo.setDemo(Constants_Business.N);
								labDemoVo.setDemo1(demoVo.getDemo());
								labDemoVo.setDemo2(sonLabSamTest.get(0).getValue());
								labDemoVo.setDemo3(sonLabSamTest.get(0).getDescribeFormula());//指标要求描述
								labDemoVo.setDemo4(sonLabSamTest.get(0).getDescribeWri());//指标要求公式
							}
							listLabDemo.add(labDemoVo);
						}
						labSamTestVo.setListLabDemoVo(listLabDemo);
						listLabSamTestVo.add(labSamTestVo);
					}
				}
				if(listLabSamTestVo.size()>0)
				listLabSamTestVo.get(0).setListTitle(listLabDemoVo);
			}
		}
		return listLabSamTestVo;
	}

	@Override
	public boolean updateLabSamTest4Date(LabSamTestBeatchVo labSamTestBeatchVo, List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		Set<String> nextUserId=new HashSet<String>();
		Set<String> itemSet=new HashSet<String>();
		boolean key = true;
		if (listLabSamTestVo.size() > 0) {
			for (LabSamTestVo labSamTestVo : listLabSamTestVo) {
				if (!StrUtils.isBlankOrNull(labSamTestVo.getLabSamId())) {
					if (labSamTestVo.getListLabSamTest() != null && labSamTestVo.getListLabSamTest().size() > 0) {
						for (LabSamTestVo vo : labSamTestVo.getListLabSamTest()) {
							if (vo == null)
								continue;
							if (!StrUtils.isBlankOrNull(vo.getItemId())) {
								String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamId='" + labSamTestVo.getLabSamId() + "' AND itemId='" + vo.getItemId() + "'";
								LabSamTest labSamTest = (LabSamTest) labSamTestDAO.find(hql, 0);
								labSamTest.setValue(vo.getValue());
								if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getAuditResult()) && labSamTestBeatchVo.getAuditResult().equals(Constants_Business.TRUE)){
									labSamTest.setIsTest("Y");
									nextUserId.add(labSamTest.getCheckManId());
									itemSet.add(labSamTest.getItemName());
								}
								labSamTest.setTestTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMdd));
								labSamTestDAO.updateLabSamTest(labSamTest);
								
								//组装校验人及对应的项目
								
							}

						}
					}
				}
			}
			if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getAuditResult())) {
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamTestBeatchVo.getId(), labSamTestBeatchVo.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据录入：" +itemSet.toArray(new String[itemSet.size()]), labSamTestBeatchVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,nextUserId.toArray(new String[nextUserId.size()]),LabWfConstant.OPER_TYPE_USER);
				if (null == ins) {
					throw new GlobalException("labSamTestService...流程实例化出错！");
				}
			}
		} else
			key = false;
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTestVo> getLabSamTestVo4Appara(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		SessionContainer son=SessionContainer.getSessionContainer();
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())) {
			String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
			hql += " AND testManId='" +son.getUserId()+ "'";
			hql += " GROUP BY itemId";
			List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
			if (listLabSamTest != null && listLabSamTest.size() > 0) {
				for (LabSamTest labSamTest : listLabSamTest) {
					LabSamTestVo labSamTestVo = new LabSamTestVo();
					BeanUtils.copyProperties(labSamTest, labSamTestVo, new String[] { "labSamTestBeatch" });
					if (!StrUtils.isBlankOrNull(labSamTest.getMethodId())) {
						LabMethod labMethod = (LabMethod) labSamTestDAO.findById(LabMethod.class, labSamTest.getMethodId());
						labSamTestVo.setApparaId(labMethod.getLabApparaId());
						labSamTestVo.setApparaName(labMethod.getLabApparaName());
					}
					listLabSamTestVo.add(labSamTestVo);
				}
			}
		}
		return listLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTest4CheckPR(LabSamTestBeatchVo labSamTestBeatchVo, PageResult pageResult) throws GlobalException {
		SessionContainer son = SessionContainer.getSessionContainer();
		List<LabSamTestBeatchVo> listSamTestBeatchVo = new ArrayList<LabSamTestBeatchVo>();
		String hql = "FROM LabSamTestBeatch WHERE isDel='" + Constants_Business.N + "' ";
		if (null != labSamTestBeatchVo.getStatus() && !"".equals(labSamTestBeatchVo.getStatus())) {
			String subhql = "SELECT stepBusId FROM LabWfStepIns WHERE isDel='" + Constants_Business.N + "' AND busType='" + Constants_Business.WF_CODE_BUS_RW + "' AND status<>'" + LabWfConstant.BUS_PROCESS_END + "' AND code='" + labSamTestBeatchVo.getStatus() + "'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql + ")";
		}
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getName()))
			hql += " AND name LIKE '%" + labSamTestBeatchVo.getName().trim() + "%'";
		
		pageResult = labSamTestBeatchDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabSamTestBeatch> listLabSamTestBeatch = pageResult.getResultList();
			if (listLabSamTestBeatch != null && listLabSamTestBeatch.size() > 0) {
				for (LabSamTestBeatch labSamTestBeatch : listLabSamTestBeatch) {
					LabSamTestBeatchVo vo = new LabSamTestBeatchVo();
					BeanUtils.copyProperties(labSamTestBeatch, vo);
					String sonHql = "SELECT COUNT(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatch.getId() + "'";
					sonHql += " AND checkManId='" +son.getUserId()+ "'";
					List<Object> listObj = labSamTestBeatchDAO.find(sonHql);
					if (listObj != null && listObj.size() > 0) {
						Object oo = listObj.get(0);
						vo.setSampNum(Integer.valueOf(String.valueOf(oo)));
					}
					sonHql = "SELECT itemName,itemId FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatch.getId() + "'";
					sonHql += " AND checkManId='" +son.getUserId()+ "'";
					sonHql += " GROUP BY itemId";
					List<Object[]> listObjx = labSamTestBeatchDAO.find(sonHql);
					String itemName="";
					String itemId="";
					if (listObjx != null && listObjx.size() > 0) {
						for (Object[] objects : listObjx) {
							itemName+=objects[0]+",";
							itemId+=objects[1]+",";
						}
						if(itemId.endsWith(",")){
							itemId=itemId.substring(0, itemId.length()-1);
							itemName=itemName.substring(0, itemName.length()-1);
						}
					}
					vo.setItemIds(itemId);
					vo.setItemNames(itemName);
					LabWfProcessIns ins = labSamTestBeatch.getProcessIns();
					if (ins != null) {
						String status = ins.getStatus();
						if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
							vo.setStatus("已完结");
						} else {
							if (status.contains(son.getFunId())) {
								vo.setIsOper(Constants_Business.Y);
								boolean flag = labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSamTestBeatch.getTaskIds(), labSamTestBeatch.getId(), son.getFunId());
								if (flag) {
									vo.setIsBack(Constants_Business.Y);
								} else {
									vo.setIsBack(Constants_Business.N);
								}
							}
							String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
							vo.setStatus(str);
						}
					}
					listSamTestBeatchVo.add(vo);
				}
				pageResult.setResultList(listSamTestBeatchVo);
			}
		}
		return pageResult;
	}

	@Override
	public List<LabSamTestVo> getLabSamTestVo4Check(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		SessionContainer son = SessionContainer.getSessionContainer();
		
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		int sort = 0;
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId()) && !StrUtils.isBlankOrNull(labSamTestBeatchVo.getItemIds())) {
			List<LabDemoVo> listTitle = new ArrayList<LabDemoVo>();
			for (String itemId : labSamTestBeatchVo.getItemIds().split(",")) {
				if (!StrUtils.isBlankOrNull(itemId.trim())) {
					LabDemoVo labDemoVo = new LabDemoVo();
					LabItem labItem = (LabItem) labSamTestDAO.findById(LabItem.class, itemId.trim());
					labDemoVo.setDemo(labItem.getId());
					labDemoVo.setDemo1(labItem.getName());
					listTitle.add(labDemoVo);
				}
			}
			if (listTitle != null && listTitle.size() > 0) {
				String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
				hql += " AND isTest='" + Constants_Business.Y + "'";
				hql += " AND isCheck<>'" + Constants_Business.Y + "'";
				hql+=" AND checkManId='"+son.getUserId()+"'";
				hql += " GROUP BY labSamId";
				List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
				if (listLabSamTest != null && listLabSamTest.size() > 0) {
					for (LabSamTest labSamTest : listLabSamTest) {
						LabSamTestVo labSamTestVo = new LabSamTestVo();
						BeanUtils.copyProperties(labSamTest, labSamTestVo, new String[] { "processIns", "labSamTestBeatch" });
						List<LabDemoVo> listLabDemoVo = new ArrayList<LabDemoVo>();
						for (LabDemoVo demoVo : listTitle) {
							hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamId='" + labSamTest.getLabSamId() + "' AND itemId='" + demoVo.getDemo() + "' AND labSamTestBeatch.id='" + labSamTestBeatchVo.getId() + "'";
							hql+=" AND checkManId='"+son.getUserId()+"'";
							//hql+=" AND (isTest='" + Constants.Y + "' OR (isTest='" + Constants.N + "' AND isBack='"+Constants.Y+"'))";
							List<LabSamTest> listSamTestPo = labSamTestDAO.find(hql);
							LabDemoVo labDemoVo = new LabDemoVo();
							if (listSamTestPo != null && listSamTestPo.size() > 0) {
								labDemoVo.setDemo(Constants_Business.N);
								labDemoVo.setDemo1(listSamTestPo.get(0).getValue());
								labDemoVo.setDemo2(listSamTestPo.get(0).getId());
								labDemoVo.setDemo3(listSamTestPo.get(0).getIsTest());
								labDemoVo.setDemo4(listSamTestPo.get(0).getIsBack());
								labDemoVo.setDemo5(sort);
								listLabDemoVo.add(labDemoVo);
							} else {
								labDemoVo.setDemo(Constants_Business.Y);
								listLabDemoVo.add(labDemoVo);
							}
							sort++;
						}
						labSamTestVo.setListLabDemoVo(listLabDemoVo);
						listLabSamTestVo.add(labSamTestVo);
					}
				}
			}
			if (listLabSamTestVo.size() > 0)
				listLabSamTestVo.get(0).setListTitle(listTitle);
		}
		return listLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTestVo4Check(LabSamTestVo labSamTestVo) throws GlobalException {
		SessionContainer son = SessionContainer.getSessionContainer();
		boolean key = true;
		String busId="";
		LabSamTestBeatch samTestBeatch = (LabSamTestBeatch) labSamTestBeatchDAO.findById(LabSamTestBeatch.class, labSamTestVo.getBeatchId());
		if (labSamTestVo.getAuditResult().equals(LabWfConstant.BUS_BACK)) {
			Set<String> testSet=new HashSet<String>();
			Set<String> itemSet=new HashSet<String>();
			boolean submitAll = true;//判断是否全部提交
			if (labSamTestVo.getListLabSamTest() != null && labSamTestVo.getListLabSamTest().size() > 0) {
				for (LabSamTestVo samVo : labSamTestVo.getListLabSamTest()) {
					if(samVo==null||StrUtils.isBlankOrNull(samVo.getId()))continue;
					if (!StrUtils.isNull(samVo.getIsBack())&& samVo.getIsBack().equals("Y")) {
						LabSamTest labSamTest = (LabSamTest) labSamTestDAO.getLabSamTest(samVo.getId());
						busId=labSamTest.getTaskId();
						labSamTest.setIsBack(Constants_Business.Y);
						labSamTest.setIsTest(Constants_Business.N);
						labSamTestDAO.updateLabSamTest(labSamTest);
						//组装退回数据
						testSet.add(labSamTest.getTestManId());
						itemSet.add(labSamTest.getItemName());
					} else {
						submitAll = false;
					}
				}
			}
			if (submitAll) {//退回全部
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(samTestBeatch.getId(), samTestBeatch.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据检验：项目退回：" + itemSet.toString() + "；" + "备注：" + labSamTestVo.getAuditMessage(), labSamTestVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,testSet.toArray(new String[testSet.size()]),LabWfConstant.OPER_TYPE_USER);
				if (null == ins) {
					throw new GlobalException("labSamTestService...流程实例化出错！");
				}
			} else {//退回部分
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(samTestBeatch.getId(), samTestBeatch.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(),"数据检验：项目退回：" + itemSet.toString() + "；备注：" + labSamTestVo.getAuditMessage(), labSamTestVo.getAuditResult(), LabWfConstant.BUS_STEP_SUB,testSet.toArray(new String[testSet.size()]),LabWfConstant.OPER_TYPE_USER);
				if (null == ins) {
					throw new GlobalException("labSamTestService...流程实例化出错！");
				}
			}
		} else {//通过提交所有
			String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamTestBeatch.id='" + samTestBeatch.getId() + "' ";
			hql+=" AND checkManId='"+son.getUserId()+"'";
			List<LabSamTest> testlist = labSamTestDAO.find(hql);
			if (testlist != null) {
				for (LabSamTest labSamTest : testlist) {
					labSamTest.setIsCheck(Constants_Business.Y);
					busId=labSamTest.getTaskId();
					labSamTestDAO.updateLabSamTest(labSamTest);
				}
			}
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(samTestBeatch.getId(),busId, Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), labSamTestVo.getAuditMessage(), labSamTestVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,null,null);
			if (null == ins) {
				throw new GlobalException("labSamTestService...流程实例化出错！");
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTest4SummaryPR(LabSamTestVo labSamTestVo, PageResult pageResult) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql = "FROM LabSampRegister WHERE isDel='" + Constants_Business.N + "'";
		if(!StrUtils.isBlankOrNull(labSamTestVo.getTaskNo())){
			hql+=" AND no LIKE '%"+labSamTestVo.getTaskNo().trim()+"%'";
		}
		if (null != labSamTestVo.getStatus() && !"".equals(labSamTestVo.getStatus())) {
			String subhql = "SELECT busId FROM LabWfStepIns WHERE isDel='" + Constants_Business.N + "' AND busType='" + Constants_Business.WF_CODE_BUS_RW + "' AND status<>'" + LabWfConstant.BUS_PROCESS_END + "' AND code='" + labSamTestVo.getStatus() + "'";
			hql += " AND id in(" + subhql + ")";
		}
		pageResult = labSamTestDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabSampRegister> listLabSamTest = pageResult.getResultList();
			List<LabSampRegisterVo> listLabSamTestVo = new ArrayList<LabSampRegisterVo>();
			for (LabSampRegister labSampRegister : listLabSamTest) {
				LabSampRegisterVo vo = new LabSampRegisterVo();
				BeanUtils.copyProperties(labSampRegister, vo, new String[] { "processIns", "itemName" });
				String sonHql = "select COUNT(distinct(sam_Id)),GROUP_CONCAT(distinct(item_name)) FROM lab_sam_test WHERE is_Del='N' AND task_id='" + labSampRegister.getId() + "'";
				List<Object[]> listObj = labSamTestBeatchDAO.createSqlQuery(sonHql);
				if (listObj != null && listObj.size() > 0) {
					Object[] oo = listObj.get(0);
					vo.setSampNum(String.valueOf(oo[0]));
					vo.setItemNames(String.valueOf(oo[1]));
				}
				LabWfProcessIns ins = labSampRegister.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag = labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSampRegister.getId(), labSampRegister.getId(), son.getFunId());
							if (flag) {
								vo.setIsBack(Constants_Business.Y);
							} else {
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(labSampRegister.getProcessIns().getId());
						vo.setStatus(str);
					}
				}
				listLabSamTestVo.add(vo);
			}
			pageResult.setResultList(listLabSamTestVo);
		}
		return pageResult;
	}

	@Override
	public List<LabSamTestVo> getLabSamTest4Summary(LabSamTestVo labSamTestVo) throws GlobalException {
		List<LabSamTestVo> listLabSamTestVo = new ArrayList<LabSamTestVo>();
		List<LabDemoVo> listLabDemoVo = new ArrayList<LabDemoVo>();
		if (!StrUtils.isBlankOrNull(labSamTestVo.getTaskId())) {
			String hql = "SELECT  itemId,itemName FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSamTestVo.getTaskId() + "' ";
			hql += " GROUP BY itemId ";
			List<Object[]> listTitle = labSamTestDAO.find(hql);
			if (listTitle != null && listTitle.size() > 0) {
				for (Object[] o : listTitle) {
					LabDemoVo labDemoVo = new LabDemoVo();
					labDemoVo.setDemo(o[0].toString());
					labDemoVo.setDemo1(o[1].toString());
					listLabDemoVo.add(labDemoVo);
				}
			}
			if (listLabDemoVo.size() > 0) {
				hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSamTestVo.getTaskId() + "'";
				//hql+="";流程状态
				hql += "GROUP BY labSamId";
				List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
				if (listLabSamTest != null && listLabSamTest.size() > 0) {
					for (LabSamTest labSamTest : listLabSamTest) {
						LabSamTestVo vo = new LabSamTestVo();
						BeanUtils.copyProperties(labSamTest, vo, new String[] { "processIns", "labSamTestBeatch" });
						List<LabDemoVo> listLabDemo = new ArrayList<LabDemoVo>();
						for (LabDemoVo demoVo : listLabDemoVo) {
							LabDemoVo labDemoVo = new LabDemoVo();
							hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND labSamId='" + labSamTest.getLabSamId() + "'  AND taskId='" + labSamTestVo.getTaskId() + "' AND itemId='" + demoVo.getDemo() + "'";
							hql += "  AND isTest!='" + Constants_Business.N + "'";
							hql += "  AND isCheck='" + Constants_Business.Y + "'";
							List<LabSamTest> sonLabSamTest = labSamTestDAO.find(hql);
							if (sonLabSamTest != null && sonLabSamTest.size() > 0) {
								labDemoVo.setDemo(Constants_Business.N);
								labDemoVo.setDemo1(demoVo.getDemo());
								labDemoVo.setDemo2(sonLabSamTest.get(0).getValue());
							} else {
								labDemoVo.setDemo(Constants_Business.Y);
							}
							listLabDemo.add(labDemoVo);
						}
						vo.setListLabDemoVo(listLabDemo);
						listLabSamTestVo.add(vo);
					}
				}
				listLabSamTestVo.get(0).setListTitle(listLabDemoVo);
			}
		}
		return listLabSamTestVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTest4Summary(LabSamTestVo labSamTestVo, List<LabSamTestVo> listLabSamTestVo) throws GlobalException {
		boolean key = true;
		if (listLabSamTestVo != null && listLabSamTestVo.size() > 0 && !StrUtils.isBlankOrNull(labSamTestVo.getTaskId())) {
			Map<String,Set[]> backMap=new HashMap<String,Set[]>();
			if (labSamTestVo.getAuditResult().equals(LabWfConstant.BUS_BACK)) {
				for (LabSamTestVo vo : listLabSamTestVo) {
					String hql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSamTestVo.getTaskId() + "' AND itemId='" + vo.getItemId() + "'";
					List<LabSamTest> listLabSamTest = labSamTestDAO.find(hql);
					if (listLabSamTest != null && listLabSamTest.size() > 0) {
						for (LabSamTest labSamTest : listLabSamTest) {
							labSamTest.setIsBack(Constants_Business.Y);
							labSamTest.setIsTest(Constants_Business.N);
							labSamTest.setIsCheck(Constants_Business.N);
							labSamTestDAO.updateLabSamTest(labSamTest);
							Set[] sets=backMap.get(labSamTest.getLabSamTestBeatch().getId());
							if(sets==null){
								sets=new Set[2];
								sets[0]=new HashSet<String>();
								sets[1]=new HashSet<String>();
							}
							sets[0].add(labSamTest.getTestManId());
							sets[1].add(labSamTest.getItemName());
							backMap.put(labSamTest.getLabSamTestBeatch().getId(), sets);
						}
					}
				}
				String hql = "SELECT distinct(itemId) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND isCheck='" + Constants_Business.Y + "' AND taskId='" + labSamTestVo.getTaskId() + "'";
				List<String> objList = labSamTestDAO.find(hql);
				Set<String> keys=backMap.keySet();
				if (objList.size() >0) {//退回部分
					for (String keyx : keys) {
						Set[] setx=backMap.get(keyx);
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamTestVo.getTaskId(),keyx, Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据汇总：项目退回："+setx[1].toString()+"；备注：" + labSamTestVo.getAuditMessage(), labSamTestVo.getAuditResult(), LabWfConstant.BUS_STEP_SUB,(String[]) setx[0].toArray(new String[setx[0].size()]),LabWfConstant.OPER_TYPE_USER);
						if (null == ins) {
							throw new GlobalException("labSamTestService...流程实例化出错！");
						}
					}
				} else {//退回全部
					for (String keyx : keys) {
						Set[] setx=backMap.get(keyx);
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamTestVo.getTaskId(),keyx, Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据汇总：项目退回："+setx[1].toString()+"；备注：" + labSamTestVo.getAuditMessage(), labSamTestVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,(String[]) setx[0].toArray(new String[setx[0].size()]),LabWfConstant.OPER_TYPE_USER);
						if (null == ins) {
							throw new GlobalException("labSamTestService...流程实例化出错！");
						}
					}
				}
			} else {//通过所有
				LabSampRegister rigster = labSampRegisterDAO.getLabSampRegister(labSamTestVo.getTaskId());
				this.initLabSamReport(rigster);
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public void initLabSamReport(LabSampRegister labSampRegister) throws GlobalException {
		if (null != labSampRegister.getReportMake() && labSampRegister.getReportMake().equals("1")) {
			LabSamReport report = new LabSamReport();
			report.setBusId(labSampRegister.getId());
			report.setBusNo(labSampRegister.getNo());
			report.setFinishDate(labSampRegister.getFinishDate());
			report.setReportNum(labSampRegister.getReportNum());
			report.setReportPost(labSampRegister.getReportPost());
			report.setReportType(labSampRegister.getReportType());
			LabSampCustomer customer = labSampRegister.getLabSampCustomer();
			report.setAddress(customer.getAddress());
			report.setEmail(customer.getEmail());
			report.setFax(customer.getFax());
			report.setLabCustomerName(customer.getLabCustomerName());
			report.setUser(customer.getUser());
			report.setZipCode(customer.getZipCode());
			report.setTelephone(customer.getTelephone());

			ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
					Constants_Business.CODE_BG, new String[] {},Constants_Business.CODE_USE_RUN);
			try {
				report.setReportNo(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error("生成编号出错" + e.getMessage());
			}
			labSamReportDAO.addLabSamReport(report);
			String sampType = "";
			String strhql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND isCheck='" + Constants_Business.Y + "' AND taskId='" + labSampRegister.getId() + "'";
			List<LabSamTest> sampTypelist = labSamTestDAO.find(strhql);

			if (sampTypelist != null) {
				for (LabSamTest sampItems : sampTypelist) {
					if (!sampType.contains(sampItems.getLabSamTypeName())) {
						sampType += sampItems.getLabSamTypeName() + ",";
					}
					LabSamReportData reportdate = new LabSamReportData();
					reportdate.setBusId(labSampRegister.getId());
					reportdate.setBusNo(labSampRegister.getNo());
					reportdate.setReportId(report.getId());
					reportdate.setReportNo(report.getReportNo());
					reportdate.setSampCode(sampItems.getSampCode());
					reportdate.setLabSamId(sampItems.getLabSamId());
					reportdate.setLabSamName(sampItems.getLabSamName());
					reportdate.setLabSamTypeId(sampItems.getLabSamTypeId());
					reportdate.setLabSamTypeName(sampItems.getLabSamTypeName());
					reportdate.setItemId(sampItems.getItemId());
					reportdate.setItemName(sampItems.getItemName());
					reportdate.setMethodId(sampItems.getMethodId());
					reportdate.setMethodName(sampItems.getMethodName());
					reportdate.setStandardId(sampItems.getStandardId());
					reportdate.setStandardName(sampItems.getStandardName());
					reportdate.setResult(sampItems.getResult());//判定结果
					reportdate.setTestResult(sampItems.getValue());//检测结果
					reportdate.setTestUserId(sampItems.getTestManId());
					reportdate.setTestUserName(sampItems.getTestManName());
					reportdate.setTestTime(sampItems.getTestTime());
					labSamReportDataDAO.addLabSamReportData(reportdate);
				}
			}
			if (sampType.endsWith(",")) {
				sampType = sampType.substring(0, sampType.length() - 1);
			}
			report.setSampType(sampType);

			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegister.getId(), report.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据汇总完成", LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_ALL,null,null);
			if (null == ins) {
				throw new GlobalException("labSamTestService...流程实例化出错！");
			}
			report.setProcessIns(ins);
			labSamReportDAO.updateLabSamReport(report);
		} else if (null != labSampRegister.getReportMake() && labSampRegister.getReportMake().equals("2")) {
			String strhql = "select distinct(labSamName) FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND taskId='" + labSampRegister.getId() + "'";
			List<String> sampTypelist = labSamTestDAO.find(strhql);
			if (sampTypelist != null) {
				int n = 1;
				for (String sampType : sampTypelist) {
					LabSamReport report = new LabSamReport();
					report.setBusId(labSampRegister.getId());
					report.setBusNo(labSampRegister.getNo());
					report.setFinishDate(labSampRegister.getFinishDate());
					report.setReportNum(labSampRegister.getReportNum());
					report.setReportPost(labSampRegister.getReportPost());
					report.setReportType(labSampRegister.getReportType());
					LabSampCustomer customer = labSampRegister.getLabSampCustomer();
					report.setAddress(customer.getAddress());
					report.setEmail(customer.getEmail());
					report.setFax(customer.getFax());
					report.setLabCustomerName(customer.getLabCustomerName());
					report.setUser(customer.getUser());
					report.setZipCode(customer.getZipCode());
					report.setTelephone(customer.getTelephone());
					report.setSampType(sampType);
					ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
							Constants_Business.CODE_BG, new String[] {},Constants_Business.CODE_USE_RUN);
					try {
						report.setReportNo(pool.submit(threadNumber).get().toString());
					} catch (Exception e) {
						Log4J.error("生成编号出错" + e.getMessage());
					}
					labSamReportDAO.addLabSamReport(report);

					String subhql = "FROM LabSamTest WHERE isDel='" + Constants_Business.N + "' AND isCheck='" + Constants_Business.Y + "' AND taskId='" + labSampRegister.getId() + "'";
					subhql += " AND labSamName like '" + sampType + "'";
					List<LabSamTest> itemlist = labSamTestDAO.find(subhql);
					if (itemlist != null) {
						for (LabSamTest sampItems : itemlist) {
							LabSamReportData reportdate = new LabSamReportData();
							reportdate.setBusId(labSampRegister.getId());
							reportdate.setBusNo(labSampRegister.getNo());
							reportdate.setReportId(report.getId());
							reportdate.setReportNo(report.getReportNo());
							reportdate.setSampCode(sampItems.getSampCode());
							reportdate.setLabSamId(sampItems.getLabSamId());
							reportdate.setLabSamName(sampItems.getLabSamName());
							reportdate.setLabSamTypeId(sampItems.getLabSamTypeId());
							reportdate.setLabSamTypeName(sampItems.getLabSamTypeName());
							reportdate.setItemId(sampItems.getItemId());
							reportdate.setItemName(sampItems.getItemName());
							reportdate.setMethodId(sampItems.getMethodId());
							reportdate.setMethodName(sampItems.getMethodName());
							reportdate.setStandardId(sampItems.getStandardId());
							reportdate.setStandardName(sampItems.getStandardName());
							reportdate.setResult(sampItems.getResult());//判定结果
							reportdate.setTestResult(sampItems.getValue());//检测结果
							reportdate.setTestUserId(sampItems.getTestManId());
							reportdate.setTestUserName(sampItems.getTestManName());
							reportdate.setTestTime(sampItems.getTestTime());
							labSamReportDataDAO.addLabSamReportData(reportdate);
						}
					}
					if (sampTypelist.size() == n) {
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegister.getId(), report.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据汇总完成", LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_ALL,null,null);
						if (null == ins) {
							throw new GlobalException("labSamTestService...流程实例化出错！");
						}
						report.setProcessIns(ins);
					} else {
						LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegister.getId(), report.getId(), Constants_Business.WF_CODE_BUS_RW, SessionContainer.getSessionContainer().getFunId(), "数据汇总完成", LabWfConstant.BUS_GO, LabWfConstant.BUS_STEP_SUB,null,null);
						if (null == ins) {
							throw new GlobalException("labSamTestService...流程实例化出错！");
						}
						report.setProcessIns(ins);
					}
					labSamReportDAO.updateLabSamReport(report);
					n++;
				}
			}
		}
	}
	@Override
	public String writeExcel(List<LabSamTestVo> listLabSamTestVo) throws WriteException {
		String filePath = ServletActionContext.getRequest().getRealPath("/uploadDoc")+File.separator+"dataTemplate"+File.separator;
		String fileName =UUID.randomUUID().toString().replace("-", "") + ".xls";
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(new File(filePath+fileName));
		} catch (IOException e1) {
			return null;
		}
		if (wwb != null) {
			WritableSheet ws = wwb.createSheet("导入检测值", 0);
			WritableFont fontTitle = new WritableFont(WritableFont.TIMES, 9, WritableFont.NO_BOLD);
			WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);
			formatTitle.setWrap(true);
			if (listLabSamTestVo != null && listLabSamTestVo.size() > 0) {
				if (listLabSamTestVo.get(0).getListTitle() != null && listLabSamTestVo.get(0).getListTitle().size() > 0) {
					for (int i = 0; i <= listLabSamTestVo.get(0).getListTitle().size(); i++) {
						ws.setColumnView(i, 15);
						ws.setRowView(i, 400);
						Label labelC = null;
						if (i == 0) {
							labelC = new Label(i, 0, "样品编号", formatTitle);
						} else {
							LabDemoVo demo=listLabSamTestVo.get(0).getListTitle().get(i-1);
							labelC = new Label(i, 0, demo.getDemo1(), formatTitle);
						}
						ws.addCell(labelC);
					}
					for (int j = 1; j <= listLabSamTestVo.size(); j++) {
						ws.setColumnView(j, 15);
						ws.setRowView(j, 400);
						LabSamTestVo samTestVo = listLabSamTestVo.get((j - 1));
						Label labelC = new Label(0, j, samTestVo.getSampCode(), formatTitle);
						ws.addCell(labelC);
					}
				}
			}
			try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				return null;
			}
		}
		fileName= ServletActionContext.getRequest().getRealPath("/uploadDoc")+File.separator+"dataTemplate"+File.separator+""+fileName;
		return fileName;
	}

	@Override
	public boolean updateLabSamTest4ImportDate(LabSamTestBeatchVo labSamTestBeatchVo, String fileName) throws GlobalException {
		// TODO Auto-generated method stub
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet sht = rwb.getSheet(0);
			int cellCount=Integer.valueOf(labSamTestBeatchVo.getItemNum().trim());
			String[] samp=new String[cellCount];
			for(int c=1;c<=cellCount;c++){
				samp[c-1]= sht.getCell(c, 0).getContents();
			}
			for (int r =1; r < sht.getRows();r++) {
				String samCode=sht.getCell(0, r).getContents();
				if(!StrUtils.isBlankOrNull(samCode)){
					for(int c=1;c<=cellCount;c++){
						String value = sht.getCell(c, r).getContents();
						String hql="FROM LabSamTest WHERE isDel='"+Constants_Business.N+"' AND sampCode='"+samCode+"'";
						hql+=" AND itemName='"+samp[c-1]+"'";
						hql+= "  AND isTest<>'" + Constants_Business.Y + "'";
						LabSamTest labSamTest=(LabSamTest) labSamTestDAO.find(hql,0);
						if(labSamTest!=null){
							labSamTest.setValue(value);
							labSamTestDAO.updateLabSamTest(labSamTest);
						}
					}
				}
			}
			rwb.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamTestVo4Appara(LabSamTestBeatchVo labSamTestBeatchVo,List<LabApparaUseVo> listLabApparaUseVo) throws GlobalException {
		boolean key=false;
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())){
			if(listLabApparaUseVo!=null&&listLabApparaUseVo.size()>0){
				for(LabApparaUseVo labApparaUseVo:listLabApparaUseVo){
					String hql="FROM LabSamTest WHERE isDel='"+Constants_Business.N+"' AND itemId='"+labApparaUseVo.getExt01()+"'";
						   hql+=" AND labSamTestBeatch.id='"+labSamTestBeatchVo.getId()+"'";
					List<LabSamTest> listLabSamTest=labSamTestDAO.find(hql);
					if(listLabSamTest!=null&&listLabSamTest.size()>0){
						for(LabSamTest labSamTest:listLabSamTest){
							labSamTest.setStartDate(labApparaUseVo.getBeStartDate());
							labSamTest.setEndDate(labApparaUseVo.getBeStartDate());
							labSamTestDAO.updateLabSamTest(labSamTest);
						}
						key=true;
					}
				}
			}
		}
		return key;
	}

	@Override
	public LabSamTestBeatchVo getLabSamTestBeatchById(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabSamTestBeatchVo vo=new LabSamTestBeatchVo();
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())){
			LabSamTestBeatch labSamTestBeatch=(LabSamTestBeatch) labSamTestDAO.findById(LabSamTestBeatch.class, labSamTestBeatchVo.getId());
			BeanUtils.copyProperties(labSamTestBeatch, vo,new String[]{"processIns"});
		}
		return vo;
	}

	@Override
	public boolean updateLabSamTestBeatch(LabSamTestBeatchVo labSamTestBeatchVo) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=false;
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())){
			LabSamTestBeatch labSamTestBeatch=new LabSamTestBeatch();
			labSamTestBeatch=vo2Po(labSamTestBeatchVo,labSamTestBeatch);
			labSamTestBeatchDAO.updateLabLabSamTestBeatch(labSamTestBeatch);
			key=true;
		}
		return key;
	}
	public LabSamTestBeatch vo2Po(LabSamTestBeatchVo labSamTestBeatchVo,LabSamTestBeatch labSamTestBeatch){
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())){
			labSamTestBeatch=(LabSamTestBeatch) labSamTestDAO.findById(LabSamTestBeatch.class, labSamTestBeatchVo.getId());
			if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getReagentOutId()))labSamTestBeatch.setReagentOutId(labSamTestBeatchVo.getReagentOutId());
			if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getReferenceOutId()))labSamTestBeatch.setReferenceOutId(labSamTestBeatchVo.getReferenceOutId());
			if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getAmbientId()))labSamTestBeatch.setAmbientId(labSamTestBeatchVo.getAmbientId());
		}
		return labSamTestBeatch;
	}

	@Override
	public boolean updateLabSamTest4Reagent(LabSamTestBeatchVo labSamTestBeatchVo,LabReaOutMainVo labReaOutMainVo) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		LabReaOutMain labReaOutMain=null;
		LabSamTestBeatch labSamTestBeatch=(LabSamTestBeatch) labSamTestDAO.findById(LabSamTestBeatch.class, labSamTestBeatchVo.getId());
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getId())){
			if(!StrUtils.isBlankOrNull(labSamTestBeatch.getReagentOutId())){
					labReaOutMain=(LabReaOutMain) labSamTestDAO.findById(LabReaOutMain.class, labSamTestBeatch.getReagentOutId());
			}else{
				labReaOutMain =new LabReaOutMain();
				BeanUtils.copyProperties(labReaOutMainVo, labReaOutMain,new String[]{"outstoreDate"});
				labReaOutMain.setOutstoreDate(new Date());
				labReaOutMain.setIsDel(Constants_Business.N);
				labReaOutMainDAO.addLabReaOutMain(labReaOutMain);
				labSamTestBeatch.setReagentOutId(labReaOutMain.getId());
				labSamTestBeatchDAO.updateLabLabSamTestBeatch(labSamTestBeatch);
			}
			List<LabReaOutDetailVo> reaOutDetailVoList = labReaOutMainVo
			.getLabReaOutDetailVoList();
		if (labReaOutMain!=null&&!StrUtils.isBlankOrNull(labReaOutMain.getId())&&null != reaOutDetailVoList && reaOutDetailVoList.size() > 0) {
			for (LabReaOutDetailVo labReaOutDetailVo : reaOutDetailVoList) {
				if(!StrUtils.isBlankOrNull(labReaOutDetailVo.getId()))continue;
				LabReaOutDetail labReaOutDetail = new LabReaOutDetail();
				labReaOutDetail.setIsDel(Constants_Business.N);
				labReaOutDetail.setAmount(Double.valueOf(labReaOutDetailVo
						.getAmount()));
		
				labReaOutDetail.setRemark(labReaOutDetailVo.getRemark());
				LabReagent labReagent = new LabReagent();
				labReagent.setId(labReaOutDetailVo.getReagentId());
				labReaOutDetail.setReagent(labReagent);
				labReaOutDetail.setMain(labReaOutMain);
				labReaOutDetail.setOutDate(labReaOutMain.getOutstoreDate());
				labReaOutDetailDAO.addLabReaOutDetail(labReaOutDetail);
				// 更新试剂库存总量
				labReagent = (LabReagent) labSamTestDAO.findById(
						LabReagent.class, labReagent.getId());
				labReagent.setAmount(labReagent.getAmount()
						- labReaOutDetail.getAmount());
				labReagentDAO.updateLabReagent(labReagent);
			}
		}
	}
		return key;
	}

	@Override
	public boolean updateLabSamTestVo4Reference(LabSamTestBeatchVo labSamTestBeatchVo, LabRefOutMainVo labRefOutMainVo) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		LabSamTestBeatch labSamTestBeatch=(LabSamTestBeatch) labSamTestDAO.findById(LabSamTestBeatch.class, labSamTestBeatchVo.getId());
		LabRefOutMain labRefOutMain=null;
		if(labSamTestBeatch!=null){
			if(!StrUtils.isBlankOrNull(labSamTestBeatch.getReferenceOutId())){
				labRefOutMain=(LabRefOutMain) labSamTestDAO.findById(LabRefOutMain.class, labSamTestBeatch.getReferenceOutId());
			}else{
				labRefOutMain =new LabRefOutMain();
				BeanUtils.copyProperties(labRefOutMainVo, labRefOutMain,new String[]{"outstoreDate"});
				labRefOutMain.setOutstoreDate(new Date());
				labRefOutMain.setIsDel(Constants_Business.N);
				labRefOutMainDAO.addLabRefOutMain(labRefOutMain);
				labSamTestBeatch.setReferenceOutId(labRefOutMain.getId());
				labSamTestBeatchDAO.updateLabLabSamTestBeatch(labSamTestBeatch);
			}
			if(labRefOutMain!=null){
				List<LabRefOutDetailVo> listLabRefOutDetailVo=labRefOutMainVo.getLabRefOutDetailVoList();
				if(listLabRefOutDetailVo!=null){
					for(LabRefOutDetailVo labRefOutDetailVo:listLabRefOutDetailVo){
						if(!StrUtils.isBlankOrNull(labRefOutDetailVo.getId()))continue;
						LabRefOutDetail labRefOutDetail = new LabRefOutDetail();
						labRefOutDetail.setIsDel(Constants_Business.N);
						labRefOutDetail.setAmount(Double.valueOf(labRefOutDetailVo
								.getAmount()));

						labRefOutDetail.setRemark(labRefOutDetailVo.getRemark());
						LabReference labReference = new LabReference();
						labReference.setId(labRefOutDetailVo.getReferenceId());
						labRefOutDetail.setReference(labReference);
						labRefOutDetail.setMain(labRefOutMain);
						labRefOutDetail.setOutDate(labRefOutMain.getOutstoreDate());
						labRefOutDetailDAO.addLabRefOutDetail(labRefOutDetail);

						// 更新标准品库存总量
						labReference = (LabReference) labSamTestDAO.findById(
								LabReference.class, labReference.getId());
						labReference.setAmount(labReference.getAmount()
								- labRefOutDetail.getAmount());
						try{
							labReferenceDAO.updateLabReference(labReference);
						}catch(Exception e){key=false;}
					}
				}
			}
		}
		return key;
	}

	@Override
	public String updateLabSamTest4Validate(LabSamTestBeatchVo labSamTestBeatchVo, String fileName) throws GlobalException {
		// TODO Auto-generated method stub
		String returnValue="";
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet sht = rwb.getSheet(0);
			int cellCount=Integer.valueOf(labSamTestBeatchVo.getItemNum().trim());
			String[] samp=new String[cellCount];
			Map<String,String> itemValue=new HashMap();
			for(int c=1;c<=cellCount;c++){
				String itemName=sht.getCell(c, 0).getContents();
				String hql="FROM LabSamTest WHERE isDel='"+Constants_Business.N+"' AND itemName='"+itemName+"'";
				hql+= "  AND isTest<>'" + Constants_Business.Y + "' AND  labSamTestBeatch.id='"+labSamTestBeatchVo.getId()+"' GROUP BY standardId";
				List<LabSamTest> listLabSamTest=labSamTestDAO.find(hql);
				if(listLabSamTest!=null&&listLabSamTest.size()>0){
					hql="FROM LabStandardItem WHERE isDel='"+Constants_Business.N+"' AND item.id='"+listLabSamTest.get(0).getItemId()+"'";
					hql+=" AND standard.id='"+listLabSamTest.get(0).getStandardId()+"'";
					List<LabStandardItem> listLabStandardItem=labSamTestDAO.find(hql);
					if(listLabStandardItem!=null&&listLabStandardItem.size()>0){
						itemValue.put(String.valueOf((c-1)), listLabStandardItem.get(0).getDecimalCount());
					}
				}
			}
			for (int r =1; r < sht.getRows();r++) {
					for(int c=1;c<=cellCount;c++){
						String items=sht.getCell(c, 0).getContents();
						String value = sht.getCell(c, r).getContents();
						String demo=itemValue.get(String.valueOf((c-1)));
						if(demo!=null&&demo!=""&&demo!="0"){
							if(value.indexOf(".")==-1){
								returnValue=items+"检测项目，检测值保留"+demo+"位";
								break;
							}else{
								value=value.substring(value.indexOf(".")+1,value.length());
								if(value.length()!=Integer.valueOf(demo)){
									returnValue=items+"检测项目，检测值保留"+demo+"位";
									break;
								}
							}
						}
					}
				if(!StrUtils.isBlankOrNull(returnValue)){
					break;
				}	
			}
			rwb.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return returnValue;
	}
}
