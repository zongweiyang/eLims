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
import cn.labsoft.labos.source.appara.dao.ILabApparaCheckDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaCheck;
import cn.labsoft.labos.source.appara.service.ILabApparaCheckService;
import cn.labsoft.labos.source.appara.vo.LabApparaCheckVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaCheckService")
public class LabApparaCheckServiceImpl extends BaseService implements ILabApparaCheckService {
	private ILabApparaCheckDAO labApparaCheckDAO;
	private ILabApparaDAO labApparaDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;

	@Resource
	public void setLabApparaCheckDAO(ILabApparaCheckDAO labApparaCheckDAO) {
		this.labApparaCheckDAO = labApparaCheckDAO;
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
	public LabApparaCheckVo addLabApparaCheck(LabApparaCheckVo vo) throws GlobalException {
		LabApparaCheck po = new LabApparaCheck();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "appInfoId", "verDate", "checkDate", "auditDate","labAppara" });
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
			labApparaCheckDAO.addLabApparaCheck(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_HC, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaCheckDAO.updateLabApparaCheck(po);
			}else{
				throw new GlobalException("LabApparaCheckServiceImpl...流程实例化出错！");
			}
			String audit = vo.getAuditResult();
			BeanUtils.copyProperties(po, vo, new String []{"appInfoId", "verDate", "checkDate", "auditDate","labAppara" });
			vo.setAuditResult(audit);
			return vo;
		} catch (Exception e) {
			Log4J.error("LabApparaCheckServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabApparaCheckVo getLabApparaCheck(LabApparaCheckVo vo) throws GlobalException {
		try {
			LabApparaCheck po = labApparaCheckDAO.getLabApparaCheckById(vo.getId());
			BeanUtils.copyProperties(po, vo , new String[]{"processIns","verDate","checkDate","auditDate"});
			if(null!=po.getCheckDate()){
				vo.setCheckDate(po.getCheckDate().toString().substring(0, 10)); 
			}
			if(null!=po.getVerDate()){
				vo.setVerDate(po.getVerDate().toString().substring(0, 10));
			}
			return vo;
		} catch (Exception e) {
			Log4J.error("LabApparaCheckServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public void updateLabApparaCheck(LabApparaCheckVo vo) throws GlobalException {
		try {
			LabApparaCheck po = labApparaCheckDAO.getLabApparaCheckById(vo.getId());
			BeanUtils.copyProperties(vo, po, new String[] {"verDate", "checkDate", "auditDate" });
			if(!StrUtils.isBlankOrNull(vo.getUser2())){
				po.setUser2(vo.getUser2());
			}
			if(!StrUtils.isBlankOrNull(vo.getUser3())){
				po.setUser2(vo.getUser3());
			}
			if(!StrUtils.isBlankOrNull(vo.getDate2())){
				po.setDate2(vo.getDate2());
			}
			if(!StrUtils.isBlankOrNull(vo.getDate3())){
				po.setDate2(vo.getDate3());
			}
			if (!StrUtils.isBlankOrNull(vo.getVerDate())){
				po.setVerDate(DateUtils.parseToDate(vo.getVerDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getCheckDate())){
				po.setCheckDate(DateUtils.parseToDate(vo.getCheckDate()));
			}
			if (!StrUtils.isBlankOrNull(vo.getAuditDate())){
				po.setAuditDate(DateUtils.parseToDate(vo.getAuditDate()));
			}
			labApparaCheckDAO.updateLabApparaCheck(po);
			// 所有ADD,update方法步骤
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_HC, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if(ins==null){
				throw new GlobalException("LabApparaCheckServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaCheckServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

	}

	@Override
	public PageResult getLabApparaCheckPR(LabApparaCheckVo vo, PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaCheck WHERE 1=1 ";
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				hql += " AND labAppara.id='" + vo.getAppId() + "'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql += " AND appName LIKE '"+vo.getAppName()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql += " AND verDate >= '"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql += " AND verDate <= '"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus() && !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_HC+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in("+subhql+")";
			}
			pageResult = labApparaCheckDAO.getPageResult(hql, pageResult);
			List<LabApparaCheck> vfList = pageResult.getResultList();
			List<LabApparaCheckVo> vfVoList = new ArrayList();
			if (null != vfList && vfList.size() > 0) {
				for (LabApparaCheck po : vfList) {
					LabApparaCheckVo labApparaCheckVo = new LabApparaCheckVo();
					BeanUtils.copyProperties(po, labApparaCheckVo, new String[] {"verDate", "checkDate", "auditDate" });
					if (null != po.getLabAppara()) {
						labApparaCheckVo.setAppId(po.getLabAppara().getId());
					}
					if (null != po.getVerDate()) {
						labApparaCheckVo.setVerDate(po.getVerDate().toString().substring(0, 10));
					}
					if (null != po.getCheckDate()) {
						labApparaCheckVo.setCheckDate(po.getCheckDate().toString().substring(0, 10));
					}
					if (null != po.getAuditDate()) {
						labApparaCheckVo.setAuditDate(po.getAuditDate().toString().substring(0, 10));
					}
					labApparaCheckVo.setId(po.getId());
					String status = po.getProcessIns().getStatus().replaceAll(",", "");
					if (status.equals(getSessionContainer().getFunId())) {
						labApparaCheckVo.setIsOper("Y");
					} else {
						labApparaCheckVo.setIsOper("N");
					}
					//添加状态信息
					String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
					labApparaCheckVo.setStatus(str);
					vfVoList.add(labApparaCheckVo);
				}
			}
			pageResult.setResultList(vfVoList);

		} catch (Exception e) {
			Log4J.error("LabApparaCheckServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}

}
