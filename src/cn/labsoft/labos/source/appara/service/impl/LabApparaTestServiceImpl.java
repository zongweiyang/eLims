package cn.labsoft.labos.source.appara.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.appara.dao.ILabApparaTestDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaTest;
import cn.labsoft.labos.source.appara.service.ILabApparaTestService;
import cn.labsoft.labos.source.appara.vo.LabApparaTestVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaTestService")
public class LabApparaTestServiceImpl extends BaseService implements ILabApparaTestService{
	private ILabApparaTestDAO labApparaTestDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	
	@Resource
	public void setLabApparaTestDAO(ILabApparaTestDAO labApparaTestDAO) {
		this.labApparaTestDAO = labApparaTestDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}

	@Override
	public LabApparaTestVo addLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException {
		LabApparaTest po = new LabApparaTest();
		try {
			BeanUtils.copyProperties(labApparaTestVo,po,new String[]{"labAppara","testDate"});
			if(null != labApparaTestVo.getTestDate() && !"".equals(labApparaTestVo.getTestDate())){
				try {
					po.setTestDate(DateUtils.parse(labApparaTestVo.getTestDate(),DateUtils.formatStr_yyyyMMdd));
				} catch (ParseException e) {
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
			if(null != labApparaTestVo.getAppId() && !labApparaTestVo.getAppId().equals("")){
				LabAppara labAppara = (LabAppara) labApparaTestDAO.findById(LabAppara.class, labApparaTestVo.getAppId());
				po.setLabAppara(labAppara);
			}
			po.setIsDel(Constants_Source.N);
			labApparaTestDAO.addLabApparaTest(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_JD, getSessionContainer().getFunId(), labApparaTestVo.getAuditMessage(), labApparaTestVo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaTestDAO.updateLabApparaTest(po);
			}else{
				throw new GlobalException("LabApparaTestServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaTestServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = labApparaTestVo.getAuditResult();
		BeanUtils.copyProperties(po, labApparaTestVo, new String []{"labAppara","testDate"});
		labApparaTestVo.setAuditResult(audit);
		return labApparaTestVo;
	}

	@Override
	public boolean deleteLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException{
		try {
			LabApparaTest po = (LabApparaTest) labApparaTestDAO.findById(LabApparaTest.class, labApparaTestVo.getId());
			labApparaTestDAO.deleteLabApparaTest(po);
			return true;
		} catch (Exception e) {
			Log4J.error("LabApparaTestServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabApparaTestVo getLabApparaTestById(String id) throws GlobalException{
		LabApparaTestVo vo = new LabApparaTestVo();
		try {
			LabApparaTest po = (LabApparaTest) labApparaTestDAO.findById(LabApparaTest.class, id);
			BeanUtils.copyProperties(po,vo,new String[]{"labAppara","testDate"});
			if(null != po.getLabAppara()){
				vo.setAppId(po.getLabAppara().getId());
				vo.setAppName(po.getLabAppara().getName());
				vo.setExt02(po.getLabAppara().getSpec());
				vo.setExt03(po.getLabAppara().getNo());
			}
			if(null != po.getTestDate() && !"".equals(po.getTestDate())){
				vo.setTestDate(DateUtils.format(po.getTestDate(),DateUtils.formatStr_yyyyMMdd));
			}
		} catch (Exception e) {
			Log4J.error("LabApparaTestServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaTestList(LabApparaTestVo labApparaTestVo,PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaTest WHERE 1=1 AND isDel='N'";
			if(!StrUtils.isBlankOrNull(labApparaTestVo.getAppId())){
				hql+=" AND labAppara.id = '"+labApparaTestVo.getAppId()+"'";
			}
			if(!StrUtils.isBlankOrNull(labApparaTestVo.getAppName())){
				hql +=" AND labAppara.name LIKE '%"+labApparaTestVo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(labApparaTestVo.getStartDate())){
				hql +=" AND testDate >= '"+labApparaTestVo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(labApparaTestVo.getEndDate())){
				hql +=" AND testDate <= '"+labApparaTestVo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != labApparaTestVo.getStatus()
					&& !"".equals(labApparaTestVo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_JD+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labApparaTestVo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			
			pageResult = labApparaTestDAO.getPageResult(hql, pageResult);
			List<LabApparaTestVo> voList = new ArrayList<LabApparaTestVo>();
			List<LabApparaTest> poList = pageResult.getResultList();
			if(null != poList && poList.size() > 0){
				for (LabApparaTest po : poList) {
					LabApparaTestVo vo = new LabApparaTestVo();
					BeanUtils.copyProperties(po,vo,new String[]{"labAppara","testDate"});
					if(null != po.getLabAppara()){
						vo.setAppId(po.getLabAppara().getId());
						vo.setAppName(po.getLabAppara().getName());
					}
					if(null != po.getTestDate() && !"".equals(po.getTestDate())){
						vo.setTestDate(DateUtils.format(po.getTestDate(),DateUtils.formatStr_yyyyMMdd));
					}
					String status = po.getProcessIns().getStatus().replaceAll(",","");
					if (status.equals(getSessionContainer().getFunId())) {
						vo.setIsOper("Y");
					} else {
						vo.setIsOper("N");
					} 
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
					vo.setStatus(str);
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			Log4J.error("LabApparaTestServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabApparaTestVo updateLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException{
		try {
			LabApparaTest po = (LabApparaTest) labApparaTestDAO.findById(LabApparaTest.class, labApparaTestVo.getId());
			BeanUtils.copyProperties(labApparaTestVo, po, new String[]{"testDate"});
			if(null != labApparaTestVo.getTestDate() && !"".equals(labApparaTestVo.getTestDate())){
					po.setTestDate(DateUtils.parse(labApparaTestVo.getTestDate(),DateUtils.formatStr_yyyyMMdd));
			}
			if(null != labApparaTestVo.getAppId() && !labApparaTestVo.getAppId().equals("")){
				LabAppara labAppara = (LabAppara) labApparaTestDAO.findById(LabAppara.class, labApparaTestVo.getAppId());
				po.setLabAppara(labAppara);
			}
			po.setIsDel(Constants_Source.N);
			labApparaTestDAO.updateLabApparaTest(po);
			// 流程实例
			LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_JD, getSessionContainer().getFunId(), labApparaTestVo.getAuditMessage(), labApparaTestVo.getAuditResult());
			if(ins==null){
				throw new GlobalException("LabApparaTestServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaTestServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return labApparaTestVo;
	}

	
}
