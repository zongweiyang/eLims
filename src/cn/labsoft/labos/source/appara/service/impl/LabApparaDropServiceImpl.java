package cn.labsoft.labos.source.appara.service.impl;

import java.text.SimpleDateFormat;
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
import cn.labsoft.labos.source.appara.action.Constant;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaDropDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaDrop;
import cn.labsoft.labos.source.appara.service.ILabApparaDropService;
import cn.labsoft.labos.source.appara.vo.LabApparaDropVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaDropService")
public class LabApparaDropServiceImpl extends BaseService implements ILabApparaDropService {
	
	
	private ILabApparaDropDAO labApparaDropDAO;
	private ILabApparaDAO labApparaDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	
	@Resource
	public void setLabApparaDropDAO(ILabApparaDropDAO labApparaDropDAO) {
		this.labApparaDropDAO = labApparaDropDAO;
	}
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Override
	public LabApparaDropVo addLabApparaDrop(LabApparaDropVo vo) throws GlobalException{
		LabApparaDrop po=new LabApparaDrop();
		try {
			BeanUtils.copyProperties(vo,po,new String[]{"date1","date2","date3","createDate"});
			if(vo.getDate1()!=null&&!"".equals(vo.getDate1())){
					po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
			}
			if(vo.getDate2()!=null&&!"".equals(vo.getDate2())){
					po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
			}
			if(vo.getDate3()!=null&&!"".equals(vo.getDate3())){
					po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
			}
			labApparaDropDAO.addLabApparaDrop(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_BF, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaDropDAO.updateLabApparaDrop(po);
			}else{
				throw new GlobalException("LabApparaDropServiceImpl...流程实例化出错！");
			}
			String audit = vo.getAuditResult();
			BeanUtils.copyProperties(po, vo, new String []{"date1","date2","date3","createDate"});
			vo.setAuditResult(audit);
		} catch (Exception e) {
			Log4J.error("LabApparaDropServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
			
		}
		return vo;
	}
	@Override
	public LabApparaDropVo getLabApparaDrop(LabApparaDropVo vo) throws GlobalException{
		try {
			if(null!=vo.getId()&&!"".equals(vo.getId())){
				LabApparaDrop po=labApparaDropDAO.getLabApparaDropById(vo.getId());
				BeanUtils.copyProperties(po, vo, new String[]{"date1","date2","date3","createDate"});
				if(po.getDate1()!=null&&!"".equals(po.getDate1())){
					vo.setDate1(DateUtils.format(po.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if(po.getDate2()!=null&&!"".equals(po.getDate2())){
					vo.setDate2(DateUtils.format(po.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if(po.getDate3()!=null&&!"".equals(po.getDate3())){
					vo.setDate3(DateUtils.format(po.getDate3(), DateUtils.YYYY_MM_DD));
				}
				return vo;
			}else{
				return null;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaDropServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	@Override
	public LabApparaDropVo updateLabApparaDrop(LabApparaDropVo vo) throws GlobalException{
		try {
			LabApparaDrop po=labApparaDropDAO.getLabApparaDropById(vo.getId());
			if(po!=null){
				BeanUtils.copyProperties(vo,po,new String[]{"date1","date2","date3","createDate","userId","userName","isDel","status"});
				if(!StrUtils.isBlankOrNull(vo.getDate1())){
					po.setDate1(new SimpleDateFormat("yyyy-MM-dd").parse(vo.getDate1()));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate2())){
					po.setDate2(new SimpleDateFormat("yyyy-MM-dd").parse(vo.getDate2()));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate3())){
					po.setDate3(new SimpleDateFormat("yyyy-MM-dd").parse(vo.getDate3()));
				}
				labApparaDropDAO.updateLabApparaDrop(po);
				// 流程实例
				LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_BF, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
					LabAppara labAppara=labApparaDAO.getLabApparaById(po.getAppId());
					labAppara.setStatus(Constant.APPARA_BF);
					labApparaDAO.updateLabAppara(labAppara);
				}
				if(ins==null){
					throw new GlobalException("LabApparaDropServiceImpl...流程实例化出错！");
				}
				return vo;
			}else{
				return null;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaDropServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	@Override
	public PageResult getLabApparaDropPR(LabApparaDropVo vo,PageResult pageResult) throws GlobalException {
		try {
			String hql ="FROM LabApparaDrop WHERE 1=1";
			if(!StrUtils.isBlankOrNull(vo.getAppId())){
				hql +=" AND appId = '"+vo.getAppId().trim()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql +=" AND appName LIKE '%"+vo.getAppName().trim()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql +=" AND createDate >='"+vo.getStartDate()+" 00:00:00:00"+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql +=" AND createDate <='"+vo.getEndDate()+" 23:59:59:59"+"%'";
			}
			if (null != vo.getStatus()
					&& !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_BF+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult=labApparaDAO.getPageResult(hql, pageResult);
			List<LabApparaDrop> poList = pageResult.getResultList();
			List<LabApparaDropVo> voList = new ArrayList<LabApparaDropVo>();
			if(null!=poList && poList.size()>0){
				for (LabApparaDrop po : poList) {
					LabApparaDropVo vo1 = new LabApparaDropVo();
					BeanUtils.copyProperties(po, vo1,new String[]{"date1","date2","date3","createDate"});
					vo1.setDate1(DateUtils.format(po.getDate1()));
					String status = po.getProcessIns().getStatus().replaceAll(",", "");
					if (status.equals(getSessionContainer().getFunId())) {
						vo1.setIsOper("Y");
					} else {
						vo1.setIsOper("N");
					}
					//获取状态信息
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
					vo1.setStatus(str);
					voList.add(vo1);
				}
			}
			pageResult.setResultList(voList);
		} catch (Exception e) {
			Log4J.error("LabApparaDropServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}

}
