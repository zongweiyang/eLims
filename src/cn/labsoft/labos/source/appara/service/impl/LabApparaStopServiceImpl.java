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
import cn.labsoft.labos.source.appara.dao.ILabApparaStopDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaStop;
import cn.labsoft.labos.source.appara.service.ILabApparaStopService;
import cn.labsoft.labos.source.appara.vo.LabApparaStopVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaStopService")
public class LabApparaStopServiceImpl extends BaseService implements ILabApparaStopService {
	
	private ILabApparaStopDAO labApparaStopDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabApparaDAO labApparaDAO;
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	@Resource
	public void setLabApparaStopDAO(ILabApparaStopDAO labApparaStopDAO) {
		this.labApparaStopDAO = labApparaStopDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Override
	public LabApparaStopVo addLabApparaStop(LabApparaStopVo vo) throws GlobalException {
		LabApparaStop po=new LabApparaStop();
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
			labApparaStopDAO.addLabApparaStop(po);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_TY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
			if (ins != null) {
				po.setProcessIns(ins);
				labApparaStopDAO.updateLabApparaStop(po);
			}else{
				throw new GlobalException("LabApparaStopServiceImpl...流程实例化出错！");
			}
		} catch (Exception e) {
			Log4J.error("LabApparaStopServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		String audit = vo.getAuditResult();
		BeanUtils.copyProperties(po, vo, new String []{"date1","date2","date3","createDate"});
		return vo;
	}
	@Override
	public LabApparaStopVo getLabApparaStop(LabApparaStopVo vo) throws GlobalException{
		try {
			if(!StrUtils.isBlankOrNull(vo.getId())){
				LabApparaStop po=labApparaStopDAO.getLabApparaStopById(vo.getId());
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
			Log4J.error("LabApparaStopServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return null;
	}
	@Override
	public LabApparaStopVo updateLabApparaStop(LabApparaStopVo vo) throws GlobalException{
		try {
			LabApparaStop po=labApparaStopDAO.getLabApparaStopById(vo.getId());
			if(po!=null){
				BeanUtils.copyProperties(vo,po,new String[]{"date1","date2","date3","createDate","userId","userName","isDel","status"});
				if(vo.getDate1()!=null&&!"".equals(vo.getDate1())){
						po.setDate1(DateUtils.parse(vo.getDate1(), DateUtils.YYYY_MM_DD));
				}
				if(vo.getDate2()!=null&&!"".equals(vo.getDate2())){
						po.setDate2(DateUtils.parse(vo.getDate2(), DateUtils.YYYY_MM_DD));
				}
				if(vo.getDate3()!=null&&!"".equals(vo.getDate3())){
						po.setDate3(DateUtils.parse(vo.getDate3(), DateUtils.YYYY_MM_DD));
				}
				labApparaStopDAO.updateLabApparaStop(po);
				// 流程实例
				LabWfProcessIns ins=labWfProcessInsDAO.addLabWfStepIns(po.getId(),Constants_Source.WF_CODE_APP_TY, getSessionContainer().getFunId(), vo.getAuditMessage(), vo.getAuditResult());
				if(LabWfConstant.BUS_PROCESS_END.equals(po.getProcessIns().getStatus())){
					LabAppara labAppara=labApparaDAO.getLabApparaById(po.getAppId());
					labAppara.setStatus(Constant.APPARA_TY);
					labApparaDAO.updateLabAppara(labAppara);
				}
				if(ins==null){
					throw new GlobalException("LabApparaStopServiceImpl...流程实例化出错！");
				}
				return vo;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaStopServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaStopPR(LabApparaStopVo vo,PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabApparaStop WHERE 1=1";
			if(!StrUtils.isBlankOrNull(vo.getAppId())){
				hql+=" AND appId = '"+vo.getAppId()+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getAppName())){
				hql += " AND appName LIKE '%"+vo.getAppName()+"%'";
			}
			if(!StrUtils.isBlankOrNull(vo.getStartDate())){
				hql +=" AND createDate >= '"+vo.getStartDate()+" 00:00:00"+"'";
			}
			if(!StrUtils.isBlankOrNull(vo.getEndDate())){
				hql +=" AND createDate <= '"+vo.getEndDate()+" 23:59:59"+"'";
			}
			if (null != vo.getStatus()
					&& !"".equals(vo.getStatus())) {
				String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_APP_TY+"' AND status<>'"
				+LabWfConstant.BUS_PROCESS_END+"' AND code='"+vo.getStatus()+"'";
				hql+=" AND id in(" + subhql+ ")";
			}
			pageResult=labApparaStopDAO.getPageResult(hql, pageResult);
			List<LabApparaStopVo> voList = new ArrayList<LabApparaStopVo>();
			List<LabApparaStop> poList = pageResult.getResultList();
			for (LabApparaStop po : poList) {
				LabApparaStopVo vo1 = new LabApparaStopVo();
				BeanUtils.copyProperties(po, vo1, new String[]{"date1","date2","date3","createDate"});
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
			pageResult.setResultList(voList); 
		} catch (Exception e) {
			//e.printStackTrace();
			Log4J.error("LabApparaStopServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}
}
