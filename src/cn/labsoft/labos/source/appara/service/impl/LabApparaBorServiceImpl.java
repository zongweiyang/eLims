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
import cn.labsoft.labos.source.appara.dao.ILabApparaBorDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaBor;
import cn.labsoft.labos.source.appara.service.ILabApparaBorService;
import cn.labsoft.labos.source.appara.vo.LabApparaBorVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaBorService")
public class LabApparaBorServiceImpl extends BaseService implements ILabApparaBorService {
	private ILabApparaDAO labApparaDAO;
	private ILabApparaBorDAO labApparaBorDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;

	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	@Resource
	public void setLabApparaBorDAO(ILabApparaBorDAO labApparaBorDAO) {
		this.labApparaBorDAO = labApparaBorDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Override
	public LabApparaBorVo addLabApparaBor(LabApparaBorVo vo) throws GlobalException{
		LabApparaBor po=new LabApparaBor();
		try {
			BeanUtils.copyProperties(vo,po,new String[]{"date1","date2","date3","createDate"});
			if(!StrUtils.isBlankOrNull(vo.getDate1())){
					po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
			}
			if(!StrUtils.isBlankOrNull(vo.getDate2())){
					po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
			}
			if(!StrUtils.isBlankOrNull(vo.getDate3())){
					po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
			}
			SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
			po.setUserId(son.getUserId());
			po.setUserName(son.getUserName());
			po.setStatus("00");
			labApparaBorDAO.addLabApparaBor(po);
			// 实例化流程
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_JY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaBorDAO.updateLabApparaBor(po);
			}else{
				throw new GlobalException("LabApparaBorServiceImpl...流程实例化出错！");
			}
		} catch (Exception e){
			Log4J.error("LabApparaBorServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = vo.getAuditResult();
		BeanUtils.copyProperties(po, vo, new String []{"date1","date2","date3","createDate"});
		vo.setAuditResult(audit);
		return vo;
	}
	@Override
	public LabApparaBorVo getLabApparaBor(LabApparaBorVo vo) throws GlobalException{
		try {
			if(!StrUtils.isBlankOrNull(vo.getId())){
				LabApparaBor po=labApparaBorDAO.getLabApparaBorById(vo.getId());
				BeanUtils.copyProperties(po, vo, new String[]{"date1","date2","date3","createDate"});
				if(null!=po.getDate1()){
					vo.setDate1(po.getDate1().toString().substring(0, 10));
				}
				if(null!=po.getDate2()){
					vo.setDate2(po.getDate2().toString().substring(0, 10));
				}
				if(null!=po.getDate3()){
					vo.setDate3(po.getDate3().toString().substring(0, 10));
				}
				return vo;
			}else{
				return null;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaBorServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	@Override
	public LabApparaBorVo updateLabApparaBor(LabApparaBorVo vo) throws GlobalException{
		try {
			LabApparaBor po=labApparaBorDAO.getLabApparaBorById(vo.getId());
			if(null!=po){
				BeanUtils.copyProperties(vo,po,new String[]{"date1","date2","date3","createDate","userId","userName","isDel","status"});
				if(!StrUtils.isBlankOrNull(vo.getDate1())){
						po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate2())){
						po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if(!StrUtils.isBlankOrNull(vo.getDate3())){
						po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
				}
				labApparaBorDAO.updateLabApparaBor(po);
				// 所有ADD,update方法步骤
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_JY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				
				if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
					LabAppara labAppara=labApparaDAO.getLabApparaById(po.getAppId());
					labAppara.setStatus(Constant.APPARA_JY);
					labApparaDAO.updateLabAppara(labAppara);
				}
				if(ins==null){
					throw new GlobalException("LabApparaBorServiceImpl...流程实例化出错！");
				}
				return vo;
			}else{
				return null;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaBorServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaBorPR(LabApparaBorVo vo,
			PageResult pageResult) throws GlobalException{
		try {
 			String hql = "FROM LabApparaBor WHERE 1=1 ";
			if(!StrUtils.isBlankOrNull(vo.getAppId())){
				hql+=" AND appId ='"+vo.getAppId()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql+=" AND appName LIKE '%"+vo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql+=" AND createDate >='"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql+=" AND createDate <='"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus()
					&& !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_JY+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult=labApparaBorDAO.getPageResult(hql, pageResult);
			List<LabApparaBorVo> voList = new ArrayList<LabApparaBorVo>();
			List<LabApparaBor> poList = pageResult.getResultList();
			for (LabApparaBor po : poList) {
				LabApparaBorVo labApparaBorVo = new LabApparaBorVo();
				BeanUtils.copyProperties(po, labApparaBorVo, new String []{"date1","date2","date3","createDate"});
				labApparaBorVo.setDate1(DateUtils.format(po.getDate1()));
				String status = po.getProcessIns().getStatus().replaceAll(",", "");
				if (status.equals(getSessionContainer().getFunId())) {
					labApparaBorVo.setIsOper("Y");
				} else {
					labApparaBorVo.setIsOper("N");
				}
				//添加状态信息
				String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
				labApparaBorVo.setStatus(str);
				voList.add(labApparaBorVo);
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			Log4J.error("LabApparaBorServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	
}
