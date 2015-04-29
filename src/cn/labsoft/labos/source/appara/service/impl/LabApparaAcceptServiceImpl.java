package cn.labsoft.labos.source.appara.service.impl;

import java.io.Serializable;
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
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.source.appara.action.Constant;
import cn.labsoft.labos.source.appara.dao.ILabApparaAcceptDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaAccept;
import cn.labsoft.labos.source.appara.service.ILabApparaAcceptService;
import cn.labsoft.labos.source.appara.vo.LabApparaAcceptVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaAcceptService")
public class LabApparaAcceptServiceImpl extends BaseService implements ILabApparaAcceptService {

	private ILabApparaAcceptDAO labApparaAcceptDAO;
	private ILabApparaDAO labApparaDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabApparaAcceptDAO(ILabApparaAcceptDAO labApparaAcceptDAO) {
		this.labApparaAcceptDAO = labApparaAcceptDAO;
	}
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	

	@Override
	public void addLabApparaAccept(LabApparaAcceptVo vo) throws GlobalException {
		try {
			LabApparaAccept po = new LabApparaAccept();
			BeanUtils.copyProperties(vo, po, new String[] { "date1", "date2", "date3", "createDate" });
			if (!StrUtils.isBlankOrNull(vo.getDate1())) {
				po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
			}
			if (!StrUtils.isBlankOrNull(vo.getDate2())) {
				po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
			}
			if (StrUtils.isBlankOrNull(vo.getDate3())) {
				po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
			}
			SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
			po.setUserId(son.getUserId());
			po.setUserName(son.getUserName());
			po.setStatus("00");
			po.setIsDel("0");
			labApparaAcceptDAO.addLabApparaAccept(po);
			LabAppara app = labApparaDAO.getLabApparaById(po.getAppId());
			labApparaDAO.updateLabAppara(app);
		} catch (Exception e) {
			Log4J.error("LabApparaAcceptServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public LabApparaAcceptVo getLabApparaAccept(LabApparaAcceptVo vo) throws GlobalException {
		try {
			if (!StrUtils.isBlankOrNull(vo.getId())) {
				LabApparaAccept po = labApparaAcceptDAO.getLabApparaAcceptById(vo.getId());
				BeanUtils.copyProperties(po, vo, new String[] { "date1", "date2", "date3", "createDate" });
				if (null != po.getDate1()) {
					vo.setDate1(po.getDate1().toString().substring(0, 10));
				}
				if (null != po.getDate2()) {
					vo.setDate2(po.getDate2().toString().substring(0, 10));
				}
				if (null != po.getDate3()) {
					vo.setDate3(po.getDate3().toString().substring(0, 10));
				}
				return vo;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaAcceptServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return null;
	}

	@Override
	public LabApparaAcceptVo updateLabApparaAccept(LabApparaAcceptVo vo) throws GlobalException {
		try {
			LabApparaAccept po = labApparaAcceptDAO.getLabApparaAcceptById(vo.getId());
			if (po != null && po.getId() != null) {
				BeanUtils.copyProperties(vo, po, new String[] { "date1", "date2", "date3", "createDate", "userId", "userName", "isDel", "status" });
				if (!StrUtils.isBlankOrNull(vo.getDate1())) {
					po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getDate2())) {
					po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getDate3())) {
					po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getStatus())) {
					po.setStatus(vo.getStatus());
				}
				labApparaAcceptDAO.updateLabApparaAccept(po);
				// 流程实例
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_YS, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				if (ins != null) {
					po.setProcessIns(ins);
					labApparaAcceptDAO.updateLabApparaAccept(po);
				}else{
					throw new GlobalException("LabApparaAcceptServiceImpl...流程实例化出错！");
				}
				return vo;
			} 
		} catch (Exception e) {
			Log4J.error("LabApparaAcceptServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

	@Override
	public PageResult getLabApparaAcceptPR(LabApparaAcceptVo vo, PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaAccept WHERE 1=1 ";
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				hql += " AND appId = '" + vo.getAppId() + "'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql +=" AND appName LIKE '%"+vo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppNo())){
				hql +=" AND appNo LIKE '%"+vo.getAppNo()+"%'";
			}
			if (null != vo.getStatus() && !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_YS+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult = labApparaAcceptDAO.getPageResult(hql, pageResult);
			List<LabApparaAccept> poList = pageResult.getResultList();
			List<LabApparaAcceptVo> voList = new ArrayList<LabApparaAcceptVo>();
			for (int i = 0; i < poList.size(); i++) {
				LabApparaAcceptVo vo1 =new LabApparaAcceptVo();
				BeanUtils.copyProperties(poList.get(i), vo1 , new String[]{"date1","date2","date3","createDate"});
				if(null!=poList.get(i).getProcessIns()){
					String status = poList.get(i).getProcessIns().getStatus().replaceAll(",", "");
					if(!StrUtils.isBlankOrNull(status)){
						if (!status.equals(getSessionContainer().getFunId())) {
							vo1.setIsOper("N");
						} else {
							vo1.setIsOper("Y");
						} 
					}else{
						vo1.setIsOper("Y");
					}
				}else{
					vo1.setIsOper("Y");
				}
				//获取状态信息
				String str = labWfProcessInsDAO.getLabWfStepStrByInsId(poList.get(i).getProcessIns().getId());
				vo1.setStatus(str);
				voList.add(vo1);
			}
			pageResult.setResultList(voList);
		} catch (Exception e) {
			Log4J.error("LabApparaAcceptServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}

	@Override
	public LabApparaAcceptVo updateLabAppAccept(LabApparaAcceptVo vo) throws GlobalException {
		try {
			LabApparaAccept po = labApparaAcceptDAO.getLabApparaAcceptById(vo.getId());
			if (po != null && po.getId() != null) {
				BeanUtils.copyProperties(vo, po, new String[] { "date1", "date2", "date3", "createDate", "userId", "userName", "isDel", "status" });
				if (!StrUtils.isBlankOrNull(vo.getDate1())) {
					po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getDate2())) {
					po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getDate3())) {
					po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
				}
				if (!StrUtils.isBlankOrNull(vo.getStatus())) {
					po.setStatus(vo.getStatus());
				}
				labApparaAcceptDAO.updateLabApparaAccept(po);
				// 流程实例
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_YS, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				
				if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
					 LabAppara labAppara=labApparaDAO.getLabApparaById(po.getAppId());
					 labAppara.setStatus(Constant.APPARA_ZC);
					 labApparaDAO.updateLabAppara(labAppara);
				 }
				return vo;
			} 
		} catch (Exception e) {
			Log4J.error("LabApparaAcceptServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}


}
