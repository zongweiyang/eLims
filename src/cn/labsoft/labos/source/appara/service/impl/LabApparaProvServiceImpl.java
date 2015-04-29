package cn.labsoft.labos.source.appara.service.impl;

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
import cn.labsoft.labos.source.appara.dao.ILabApparaProvDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaProv;
import cn.labsoft.labos.source.appara.service.ILabApparaProvService;
import cn.labsoft.labos.source.appara.vo.LabApparaProvVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaProvService")
public class LabApparaProvServiceImpl extends BaseService implements ILabApparaProvService {

	private ILabApparaProvDAO labApparaProvDAO;
	private ILabApparaDAO labApparaDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	
	@Resource
	public void setLabApparaProvDAO(ILabApparaProvDAO labApparaProvDAO) {
		this.labApparaProvDAO = labApparaProvDAO;
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
	public LabApparaProvVo addLabApparaProv(LabApparaProvVo vo) throws GlobalException {
		LabApparaProv po = new LabApparaProv();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "appInfoId", "appInfoName", "verDate", "checkDate", "auditDate" });
			if (!StrUtils.isBlankOrNull(vo.getLabAppId())) {
				po.setLabAppara((LabAppara) labApparaDAO.findById(LabAppara.class, vo.getLabAppId()));
			}
			if (!StrUtils.isBlankOrNull(vo.getVerDate())) {
				po.setVerDate(DateUtils.parseToDate(vo.getVerDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getLabAppName())) {
				po.setLabAppName(vo.getLabAppName());
			}
			if (!StrUtils.isBlankOrNull(vo.getCheckDate())) {
				po.setCheckDate(DateUtils.parseToDate(vo.getCheckDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getAuditDate())) {
				po.setAuditDate(DateUtils.parseToDate(vo.getAuditDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getSpec())) {
				po.setSpec(vo.getSpec());
			}
			labApparaProvDAO.addLabApparaProv(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_GZ, getSessionContainer().getFunId(), 
					vo.getAuditMessage(), vo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaProvDAO.updateLabApparaProv(po);
			}else{
				throw new GlobalException("LabApparaProvServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaProvServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = vo.getAuditResult();
		BeanUtils.copyProperties(po, vo, new String [] {"appInfoId", "appInfoName", "verDate", "checkDate", "auditDate" });
		vo.setAuditResult(audit);
		return vo;
	}

	@Override
	public LabApparaProvVo getLabApparaProv(LabApparaProvVo vo) throws GlobalException {
		try {
			LabApparaProv po = labApparaProvDAO.getLabApparaProvById(vo.getId());
			vo.setLabAppId(po.getLabAppara().getId());
			vo.setLabAppName(po.getLabAppara().getName());
			vo.setLabAppNo(po.getLabAppNo());
			vo.setSpec(po.getLabAppara().getSpec());
			vo.setPersonId(po.getPersonId());
			vo.setPersonName(po.getPersonName());
			vo.setVerDate(DateUtils.format(po.getVerDate(), DateUtils.formatStr_yyyyMMdd));
			vo.setCheckId(po.getCheckId());
			vo.setCheckName(po.getCheckName());
			vo.setCheckDate(DateUtils.format(po.getCheckDate(), DateUtils.formatStr_yyyyMMdd));
			vo.setAuditId(po.getAuditId());
			vo.setAuditName(po.getAuditName());
			vo.setAuditDate(DateUtils.format(po.getAuditDate(), DateUtils.formatStr_yyyyMMdd));
			vo.setVerInfo(po.getVerInfo());
			vo.setComment(po.getComment());
			vo.setPauditMessage(po.getPauditMessage());
			vo.setVerCon(po.getVerCon());
			vo.setAuditMessage(po.getAuditMessage());
			vo.setExt01(po.getExt01());
			vo.setExt02(po.getExt02());
			vo.setExt03(po.getExt03());
			vo.setExt04(po.getExt04());
			vo.setExt05(po.getExt05());
			vo.setUser2(po.getUser2());
			vo.setDate2(po.getDate2());
			vo.setUser3(po.getUser3());
			vo.setDate3(po.getDate3());
		} catch (Exception e) {
			Log4J.error("LabApparaProvServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

	@Override
	public void updateLabApparaProv(LabApparaProvVo vo) throws GlobalException {
		try {
			LabApparaProv po = labApparaProvDAO.getLabApparaProvById(vo.getId());
			BeanUtils.copyProperties(vo, po, new String[] { "appInfoId", "verDate", "checkDate", "auditDate" });
			if (!StrUtils.isBlankOrNull(vo.getLabAppId())) {
				po.setLabAppara((LabAppara) labApparaDAO.findById(LabAppara.class, vo.getLabAppId()));
			}
			if (!StrUtils.isBlankOrNull(vo.getVerDate())) {
				po.setVerDate(DateUtils.parseToDate(vo.getVerDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getCheckDate())) {
				po.setCheckDate(DateUtils.parseToDate(vo.getCheckDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getAuditDate())) {
				po.setAuditDate(DateUtils.parseToDate(vo.getAuditDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getSpec())) {
				po.setSpec(vo.getSpec());
			}
			if (!StrUtils.isBlankOrNull(vo.getAuditMessage())) {
				po.setAuditMessage(vo.getAuditMessage());
			}
			po.setIsDel(Constants_Source.N);
			labApparaProvDAO.updateLabApparaProv(po);
			// 流程实例
			LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_GZ, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
				LabAppara labAppara=labApparaDAO.getLabApparaById(po.getLabAppara().getId());
				labAppara.setStatus(Constant.APPARA_GZ);
				labApparaDAO.updateLabAppara(labAppara);
			}
			
			if(ins==null){
				throw new GlobalException("LabApparaProvServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaProvServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaProvPR(LabApparaProvVo vo, PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaProv WHERE 1=1 and isDel='N' ";
			if (!StrUtils.isBlankOrNull(vo.getLabAppId())) {
				hql += " AND labAppara.id = '" + vo.getLabAppId() + "'";
			}
			if(!StrUtils.isBlankOrNull(vo.getLabAppName())){
				hql +=" AND labAppName LIKE '%"+vo.getLabAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql +=" AND verDate >='"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql +=" AND verDate <='"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus() && !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_GZ+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult = labApparaProvDAO.getPageResult(hql, pageResult);
			List<LabApparaProv> vfList = pageResult.getResultList();
			List<LabApparaProvVo> vfVoList = new ArrayList<LabApparaProvVo>();
			if (null != vfList && vfList.size() > 0) {
				for (LabApparaProv po : vfList) {
					if(po!=null){
					LabApparaProvVo labApparaProvVo = new LabApparaProvVo();
					BeanUtils.copyProperties(po, labApparaProvVo, new String[] { "appInfo", "verDate", "checkDate", "auditDate" });
					if(null!=po.getProcessIns()){
						String status = po.getProcessIns().getStatus().replaceAll(",", "");
						if (status.equals(getSessionContainer().getFunId())) {
							labApparaProvVo.setIsOper("Y");
								} 
							}else {
								labApparaProvVo.setIsOper("N");
							}
					if (null != po.getLabAppara()) {
						labApparaProvVo.setLabAppId(po.getLabAppara().getId());
						if(!StrUtils.isBlankOrNull(po.getLabAppara().getName())){
							labApparaProvVo.setLabAppName(po.getLabAppara().getName());
						}
					}
					if (null != po.getVerDate() && !"".equals(po.getVerDate())) {
						labApparaProvVo.setVerDate(DateUtils.format(po.getVerDate(), DateUtils.formatStr_yyyyMMdd));
					}
					if (null != po.getCheckDate() && !"".equals(po.getCheckDate())) {
						labApparaProvVo.setCheckDate(DateUtils.format(po.getCheckDate(), DateUtils.formatStr_yyyyMMdd));
					}
					if (null != po.getAuditDate() && !"".equals(po.getAuditDate())) {
						labApparaProvVo.setAuditDate(DateUtils.format(po.getAuditDate(), DateUtils.formatStr_yyyyMMdd));
					}
					//获取状态信息
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
					labApparaProvVo.setStatus(str);
					vfVoList.add(labApparaProvVo);
				}
				}
			}
			pageResult.setResultList(vfVoList);
		} catch (Exception e) {
			Log4J.error("LabApparaProvServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

		return pageResult;
	}

	@Override
	public LabApparaVo getLabApparaVoById(LabApparaProvVo vo) throws GlobalException {
		try {
			LabAppara po = (LabAppara) labApparaDAO.findById(LabAppara.class, vo.getLabAppId());
			LabApparaVo infoVo = new LabApparaVo();
			BeanUtils.copyProperties(po, infoVo);
			return infoVo;
		} catch (Exception e) {
			Log4J.error("LabApparaProvServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

}
