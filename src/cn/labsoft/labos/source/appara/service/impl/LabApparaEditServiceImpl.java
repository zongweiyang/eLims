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
import cn.labsoft.labos.source.appara.dao.ILabApparaEditDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaEdit;
import cn.labsoft.labos.source.appara.service.ILabApparaEditService;
import cn.labsoft.labos.source.appara.vo.LabApparaEditVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaEditService")
public class LabApparaEditServiceImpl extends BaseService implements ILabApparaEditService {
	private ILabApparaEditDAO labApparaEditDAO;
	private ILabApparaDAO labApparaDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	@Resource
	public void setLabApparaEditDAO(ILabApparaEditDAO labApparaEditDAO) {
		this.labApparaEditDAO = labApparaEditDAO;
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
	public LabApparaEditVo addLabApparaEdit(LabApparaEditVo vo) throws GlobalException {
		LabApparaEdit po = new LabApparaEdit();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "labApparaId", "verDate", "checkDate", "auditDate" });
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				po.setLabAppara((LabAppara) labApparaDAO.findById(LabAppara.class, vo.getAppId()));
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
			labApparaEditDAO.addLabApparaEdit(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_WX, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaEditDAO.updateLabApparaEdit(po);
			}else{
				throw new GlobalException("LabApparaEditServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaEditServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = vo.getAuditResult();
		BeanUtils.copyProperties(po, vo, new String []{"labApparaId", "verDate", "checkDate", "auditDate" });
		vo.setAuditResult(audit);
		return vo;
	}

	@Override
	public LabApparaEditVo getLabApparaEdit(LabApparaEditVo vo) throws GlobalException {
		try {
			LabApparaEdit po = labApparaEditDAO.getLabApparaEditById(vo.getId());
			BeanUtils.copyProperties(po, vo, new String[] { "verDate", "checkDate", "auditDate" });
			if (null != po.getVerDate() && !"".equals(po.getVerDate())) {
				vo.setVerDate(po.getVerDate().toString().substring(0, 10));
			}
			if (null != po.getCheckDate() && !"".equals(po.getCheckDate())) {
				vo.setVerDate(po.getCheckDate().toString().substring(0, 10));
			}
			if (null != po.getAuditDate() && !"".equals(po.getAuditDate())) {
				vo.setVerDate(po.getAuditDate().toString().substring(0, 10));
			}
		} catch (Exception e) {
			Log4J.error("LabApparaEditServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

	@Override
	public void updateLabApparaEdit(LabApparaEditVo vo) throws GlobalException {
		try {
			LabApparaEdit po = labApparaEditDAO.getLabApparaEditById(vo.getId());
			BeanUtils.copyProperties(vo, po, new String []{"verDate","checkDate","auditDate"});
			if (null != vo.getAppId() && !"".equals(vo.getAppId()))
				po.setLabAppara((LabAppara) labApparaDAO.findById(LabAppara.class, vo.getAppId()));
			if (null != vo.getVerDate() && !"".equals(vo.getVerDate()))
				po.setVerDate(DateUtils.parseToDate(vo.getVerDate()));
			if (null != vo.getCheckDate() && !"".equals(vo.getCheckDate()))
				po.setCheckDate(DateUtils.parseToDate(vo.getCheckDate()));
			if (null != vo.getAuditDate() && !"".equals(vo.getAuditDate()))
				po.setAuditDate(DateUtils.parseToDate(vo.getAuditDate()));
			po.setIsDel(Constants_Source.N);
			labApparaEditDAO.updateLabApparaEdit(po);
			// 流程实例
			LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_WX, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
				LabAppara labAppara=labApparaDAO.getLabApparaById(po.getLabAppara().getId());
				labAppara.setStatus(Constant.APPARA_WX);
				labApparaDAO.updateLabAppara(labAppara);
			}
			
			if(ins==null){
				throw new GlobalException("LabApparaEditServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaEditServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaEditPR(LabApparaEditVo vo, PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaEdit WHERE 1=1 and isDel='N' ";
			if(!StrUtils.isBlankOrNull(vo.getAppId())){
				hql +=" AND labAppara.id = '"+vo.getAppId()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppSpec())){
				hql +=" AND appSpec LIKE '%"+vo.getAppSpec()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getCheckName())){
				hql +=" AND checkName LIKE '%"+vo.getCheckName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql +=" AND appName LIKE '%"+vo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql +=" AND verDate >='"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql +=" AND verDate <='"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus()
					&& !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_WX+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult = labApparaEditDAO.getPageResult(hql, pageResult);
			List<LabApparaEdit> vfList = pageResult.getResultList();
			List<LabApparaEditVo> vfVoList = new ArrayList<LabApparaEditVo>();
			if (null != vfList && vfList.size() > 0) {
				for (LabApparaEdit po : vfList) {
					LabApparaEditVo apparaEditVo = new LabApparaEditVo();
					BeanUtils.copyProperties(po, apparaEditVo, new String[] { "labAppara", "verDate", "checkDate", "auditDate" });
					if (null != po.getLabAppara()) {
						apparaEditVo.setAppId(po.getLabAppara().getId());
					}
					if (null != po.getVerDate() && !"".equals(po.getVerDate())) {
						apparaEditVo.setVerDate(po.getVerDate().toString().substring(0, 10));
					}
					if (null != po.getCheckDate() && !"".equals(po.getCheckDate()))
						apparaEditVo.setCheckDate(po.getCheckDate().toString().substring(0, 10));
					if (null != po.getAuditDate() && !"".equals(po.getAuditDate())) {
						apparaEditVo.setAuditDate(po.getAuditDate().toString().substring(0, 10));
					}
					String status = po.getProcessIns().getStatus().replaceAll(",", "");
					if (status.equals(getSessionContainer().getFunId())) {
						apparaEditVo.setIsOper("Y");
					} else {
						apparaEditVo.setIsOper("N");
					} 
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
					apparaEditVo.setStatus(str);
					vfVoList.add(apparaEditVo);
				}
			}
			pageResult.setResultList(vfVoList);
		} catch (Exception e) {
			Log4J.error("LabApparaEditServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

		return pageResult;
	}

	@Override
	public LabApparaVo getLabApparaById(LabApparaEditVo vo) throws GlobalException {
		LabApparaVo infoVo = new LabApparaVo();
		try {
			LabAppara po = (LabAppara) labApparaDAO.findById(LabAppara.class, vo.getAppId());
			infoVo.setExt01(po.getExt01());
			infoVo.setExt03(po.getExt03());
			infoVo.setExt04(po.getExt04());
			infoVo.setId(po.getId());
			if (null != po.getName() && !"".equals(po.getName()))
				infoVo.setName(po.getName());
			if (null != po.getModel() && !"".equals(po.getModel()))
				infoVo.setModel(po.getModel());
			if (null != po.getNo() && !"".equals(po.getNo()))
				infoVo.setNo(po.getNo());
			if (null != po.getVerLastTime() && !"".equals(po.getVerLastTime()))
				infoVo.setVerLastTime(po.getVerLastTime().toString().substring(0, 11));
			if (null != po.getVerNextTime() && !"".equals(po.getVerNextTime()))
				infoVo.setVerNextTime(po.getVerNextTime().toString().substring(0, 11));
		} catch (Exception e) {
			Log4J.error("LabApparaEditServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return infoVo;
	}

}
