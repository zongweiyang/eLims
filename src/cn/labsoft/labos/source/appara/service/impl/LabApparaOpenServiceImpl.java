package cn.labsoft.labos.source.appara.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
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
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.appara.action.Constant;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaOpenDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaOpen;
import cn.labsoft.labos.source.appara.service.ILabApparaOpenService;
import cn.labsoft.labos.source.appara.vo.LabApparaOpenVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaOpenService")
public class LabApparaOpenServiceImpl extends BaseService implements ILabApparaOpenService {
	private ILabApparaDAO labApparaDAO;
	private ILabApparaOpenDAO labApparaOpenDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	
	@Resource
	public void setLabApparaOpenDAO(ILabApparaOpenDAO labApparaOpenDAO) {
		this.labApparaOpenDAO = labApparaOpenDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	@Override
	public LabApparaOpenVo addLabApparaOpen(LabApparaOpenVo vo) throws GlobalException{
		LabApparaOpen po=new LabApparaOpen();
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
			SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
				po.setUserId(son.getUserId());
				po.setUserName(son.getUserName());
				po.setStatus("00");
				labApparaOpenDAO.addLabApparaOpen(po);
				// 流程实例
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_QY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				if (ins != null) {
					po.setProcessIns(ins);
					labApparaOpenDAO.updateLabApparaOpen(po);
				}else{
					throw new GlobalException("LabApparaOpenServiceImpl...流程实例化出错！");
				}
		} catch (Exception e) {
			Log4J.error("LabApparaOpenServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = vo.getAuditResult();
		BeanUtils.copyProperties(po, vo, new String [] {"date1","date2","date3","createDate"});
		vo.setAuditResult(audit);
		return vo;
	}
	@Override
	public LabApparaOpenVo getLabApparaOpen(LabApparaOpenVo vo) throws GlobalException{
		try {
			if(!StrUtils.isBlankOrNull(vo.getId())){
				LabApparaOpen po=labApparaOpenDAO.getLabApparaOpenById(vo.getId());
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
			}
		} catch (Exception e) {
			Log4J.error("LabApparaOpenServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return null;
	}
	@Override
	public LabApparaOpenVo updateLabApparaOpen(LabApparaOpenVo vo) throws GlobalException{
		try {
			LabApparaOpen po=labApparaOpenDAO.getLabApparaOpenById(vo.getId());
			if(po!=null){
				if(!StrUtils.isBlankOrNull(vo.getDate1())){
						po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate2())){
						po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate3())){
						po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
				}
				labApparaOpenDAO.updateLabApparaOpen(po);
				// 流程实例
				LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_QY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
					LabAppara labAppara=labApparaDAO.getLabApparaById(po.getAppId());
					labAppara.setStatus(Constant.APPARA_ZC);
					labApparaDAO.updateLabAppara(labAppara);
				}
				
				if(ins==null){
					throw new GlobalException("LabApparaOpenServiceImpl...流程实例化出错！");
				}
				return vo;
			}else{
				return null;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaOpenServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaOpenPR(LabApparaOpenVo vo,PageResult pageResult) throws GlobalException{
		try {
			String hql = "FROM LabApparaOpen WHERE 1=1 AND isDel='N'";
			if(!StrUtils.isBlankOrNull(vo.getAppId())){
				hql +=" AND appId = '"+vo.getAppId()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql +=" AND appName LIKE '%"+vo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql +=" AND createDate >= '"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql +=" AND createDate <= '"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus()
					&& !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_QY+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult=labApparaOpenDAO.getPageResult(hql, pageResult);
			List<LabApparaOpenVo> voList = new ArrayList<LabApparaOpenVo>();
			List<LabApparaOpen> poList = pageResult.getResultList();
			for (LabApparaOpen po : poList) {
				LabApparaOpenVo vo1 = new LabApparaOpenVo();
				BeanUtils.copyProperties(po, vo1, new String []{"date1","date2","date3","createDate"});
				vo1.setDate1(DateUtils.format(po.getDate1()));
				String status = po.getProcessIns().getStatus().replaceAll(",", "");
				if (status.equals(getSessionContainer().getFunId())) {
					vo1.setIsOper("Y");
				} else {
					vo1.setIsOper("N");
				} 
				String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
				vo1.setStatus(str);
				voList.add(vo1);
			}
			pageResult.setResultList(voList);
		} catch (Exception e) {
			Log4J.error("LabApparaOpenServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}
	
}
