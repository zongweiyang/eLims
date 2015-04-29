package cn.labsoft.labos.business.science.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.business.science.dao.ILabSciAuthorDAO;
import cn.labsoft.labos.business.science.dao.ILabSciFundsDAO;
import cn.labsoft.labos.business.science.dao.ILabSciScienceDAO;
import cn.labsoft.labos.business.science.entity.LabSciAuthor;
import cn.labsoft.labos.business.science.entity.LabSciFunds;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciScienceService;
import cn.labsoft.labos.business.science.vo.LabSciAuthorVo;
import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.business.science.vo.LabSciScienceVo;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSciScienceService")
public class LabSciScienceServiceImpl extends BaseService implements
		ILabSciScienceService {

	private ILabSciScienceDAO labSciScienceDAO;
	private ILabSciAuthorDAO labSciAuthorDAO;
	private ILabSciFundsDAO labSciFundsDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;

	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}

	@Resource
	public void setLabSciFundsDAO(ILabSciFundsDAO labSciFundsDAO) {
		this.labSciFundsDAO = labSciFundsDAO;
	}

	@Resource
	public void setLabSciAuthorDAO(ILabSciAuthorDAO labSciAuthorDAO) {
		this.labSciAuthorDAO = labSciAuthorDAO;
	}

	@Resource
	public void setLabSciScienceDAO(ILabSciScienceDAO labSciScienceDAO) {
		this.labSciScienceDAO = labSciScienceDAO;
	}

	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	public LabSciScience VoToPo(LabSciScienceVo labSciScienceVo)
			throws GlobalException {
		LabSciScience labSciScience ;
		if (StrUtils.isBlankOrNull(labSciScienceVo.getId())) {
			labSciScience=new LabSciScience();
		}else {
			labSciScience=(LabSciScience) labSciScienceDAO.findById(LabSciScience.class, labSciScienceVo.getId());
		}
		BeanUtils.copyProperties(labSciScienceVo, labSciScience,
				new String[] { "applyFunds","isDel","createTime","tenantId","createUserId" });
		if(labSciScienceVo.getApplyFunds() != 0){
			if(labSciScienceVo.getApplyFunds() != labSciScience.getApplyFunds()){
				labSciScience.setApplyFunds(labSciScienceVo.getApplyFunds());
			}
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getLabOrgId())) {
			LabOrg labOrg = (LabOrg) labSciScienceDAO.findById(LabOrg.class,
					labSciScienceVo.getLabOrgId());
			labSciScience.setLabOrg(labOrg);
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getMasterId())) {
			LabUser labUser = (LabUser) labSciScienceDAO.findById(
					LabUser.class, labSciScienceVo.getMasterId());
			labSciScience.setMaster(labUser);
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getMasterId())) {
			LabUser labUser = (LabUser) labSciScienceDAO.findById(
					LabUser.class, labSciScienceVo.getMasterId());
			labSciScience.setMaster(labUser);
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getProcessInsId())) {
			LabWfProcessIns labWfProcessIns = (LabWfProcessIns) labSciScienceDAO
					.findById(LabWfProcessIns.class, labSciScienceVo
							.getProcessInsId());
			labSciScience.setProcessIns(labWfProcessIns);
		}
		
		return labSciScience;
	}

	@SuppressWarnings("unchecked")
	public LabSciScienceVo PoToVo(LabSciScience labSciScience)
			throws GlobalException {
		LabSciScienceVo labSciScienceVo = new LabSciScienceVo();
		BeanUtils.copyProperties(labSciScience, labSciScienceVo, new String[] {
				"labOrg", "master" });
		if (null != labSciScience.getLabOrg()) {
			labSciScienceVo.setLabOrgId(labSciScience.getLabOrg().getId());
			labSciScienceVo.setLabOrgName(labSciScience.getLabOrg().getName());
		}
		if (null != labSciScience.getMaster()) {
			labSciScienceVo.setMasterId(labSciScience.getMaster().getId());
			labSciScienceVo.setMasterName(labSciScience.getMaster().getName());
			labSciScienceVo.setDuty(labSciScience.getMaster().getDuty());
			labSciScienceVo.setTechTitle(labSciScience.getMaster()
					.getTechTitle());
			labSciScienceVo.setEmail(labSciScience.getMaster().getEmail());
			labSciScienceVo.setTelephone(labSciScience.getMaster()
					.getTelephone());
			labSciScienceVo.setSex(labSciScience.getMaster().getSex());
		}
		List<LabSciFunds> labSciFundsList = labSciFundsDAO
				.find("from LabSciFunds where isDel='" + Constants_Business.N
						+ "' and labSciScience.id='" + labSciScience.getId()
						+ "' AND type = '2'" );
		List<LabSciFundsVo> labsciFundsList2 = new ArrayList<LabSciFundsVo>();
		if (null != labSciFundsList && labSciFundsList.size() > 0) {
			for (LabSciFunds po : labSciFundsList) {
				LabSciFundsVo vo = new LabSciFundsVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labSciScience" });
				if (null != po.getLabSciScience()) {
					vo.setLabSciScienceId(po.getLabSciScience().getId());
					vo.setLabSciScienceName(po.getLabSciScience().getName());
				}
				labsciFundsList2.add(vo);
			}
			labSciScienceVo.setLabSciFundsList(labsciFundsList2);
		}
		List<LabSciAuthor> labSciAuthorList = labSciAuthorDAO
				.find("from LabSciAuthor where isDel='" + Constants_Business.N
						+ "' and labSciScience.id='" + labSciScience.getId()
						+ "'");
		List<LabSciAuthorVo> authorList = new ArrayList<LabSciAuthorVo>();
		if (null != labSciAuthorList && labSciAuthorList.size() > 0) {
			for (LabSciAuthor po : labSciAuthorList) {
				LabSciAuthorVo vo = new LabSciAuthorVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labSciScience" });
				if (null != po.getLabSciScience()) {
					vo.setLabSciScienceId(po.getLabSciScience().getId());
				}
				vo.setContributionRate(po.getContributionRate());
				vo.setDuty(po.getDuty());
				vo.setLearn(po.getLearn());
				vo.setNo(po.getNo());
				vo.setRemark(po.getRemark());
				vo.setTechTitle(po.getTechTitle());
				vo.setType(po.getType());
				vo.setUserId(po.getUserId());
				vo.setUserName(po.getUserName());
				vo.setId(po.getId());
				authorList.add(vo);
			}
			labSciScienceVo.setAuthorList(authorList);
		}
		if (null != labSciScience.getProcessIns()) {
			labSciScienceVo.setProcessInsId(labSciScience.getProcessIns()
					.getId());
			String status = labSciScience.getProcessIns().getStatus();
			if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
				labSciScienceVo.setStatus("已完结");
			} else {
				if (status.contains(getSessionContainer().getFunId())) {
					labSciScienceVo.setIsOper("Y");
				}
				String str = labWfProcessInsDAO
						.getLabWfStepStrByInsId(labSciScience.getProcessIns()
								.getId());
				labSciScienceVo.setStatus(str);
			}
		}
		labSciScienceVo.setId(labSciScience.getId());
		return labSciScienceVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSciSciencePR(PageResult pageResult,
			LabSciScienceVo labSciScienceVo) throws GlobalException {
		StringBuilder hql = new StringBuilder();
		hql.append("from LabSciScience where isDel='" + Constants_Business.N + "'");
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getCode())) {
			hql.append(" and code like '%" + labSciScienceVo.getCode().trim()
					+ "%'");
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getName())) {
			hql.append(" and name like '%" + labSciScienceVo.getName().trim()
					+ "%'");
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getMasterName())) {
			hql.append(" and master.name like '%"
					+ labSciScienceVo.getMasterName().trim() + "%'");
		}
		if (null != labSciScienceVo.getStatus()
				&& !"".equals(labSciScienceVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_SCIE+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labSciScienceVo.getStatus()+"'";
			hql.append(" AND id in(" + subhql+ ")");
		}
		pageResult = labSciScienceDAO.getPageResult(hql.toString(), pageResult);
		List<LabSciScience> list = pageResult.getResultList();
		List<LabSciScienceVo> voList = new ArrayList<LabSciScienceVo>();
		for (LabSciScience labSciScience : list) {
			voList.add(PoToVo(labSciScience));
		}
		pageResult.setResultList(voList);
		return pageResult;
	}
	@Override
	public PageResult getLabSciSciencePR4IsApply(PageResult pageResult,
			LabSciScienceVo labSciScienceVo) throws GlobalException {
		StringBuilder hql = new StringBuilder();
		hql.append("from LabSciScience where isDel='" + Constants_Business.N + "'");
		hql.append(" and isApply = '" + labSciScienceVo.getIsApply().trim()+ "'");
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getCode())) {
			hql.append(" and code like '%" + labSciScienceVo.getCode().trim()
					+ "%'");
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getName())) {
			hql.append(" and name like '%" + labSciScienceVo.getName().trim()
					+ "%'");
		}
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getMasterName())) {
			hql.append(" and master.name like '%"
					+ labSciScienceVo.getMasterName().trim() + "%'");
		}
		if (null != labSciScienceVo.getStatus()
				&& !"".equals(labSciScienceVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_SCIE+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labSciScienceVo.getStatus()+"'";
			hql.append(" AND id in(" + subhql+ ")");
		}
		pageResult = labSciScienceDAO.getPageResult(hql.toString(), pageResult);
		List<LabSciScience> list = pageResult.getResultList();
		List<LabSciScienceVo> voList = new ArrayList<LabSciScienceVo>();
		for (LabSciScience labSciScience : list) {
			voList.add(PoToVo(labSciScience));
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public LabSciScienceVo addLabSciScience(LabSciScienceVo labSciScienceVo)
			throws GlobalException {
		LabSciScience labSciScience = labSciScienceDAO.addLabSciScience(VoToPo(labSciScienceVo));
		List<LabUpload> list = labUploadDAO.getLabUploadList(labSciScienceVo
				.getUuid(), "lab-science");
		if (null != list && list.size() > 0) {
			for (LabUpload labUpload : list) {
				labUpload.setBusId(labSciScience.getId());
				labUploadDAO.updateLabUpload(labUpload);
			}
		}
		if (null != labSciScienceVo.getAuthorList()
				&& labSciScienceVo.getAuthorList().size() > 0) {
			for (LabSciAuthorVo labSciAuthorVo : labSciScienceVo
					.getAuthorList()) {
				LabSciAuthor labSciAuthor = new LabSciAuthor();
				BeanUtils.copyProperties(labSciAuthorVo, labSciAuthor,
						new String[] { "labSciScience" });
				labSciAuthor.setLabSciScience(labSciScience);
				labSciAuthorDAO.addLabSciAuthor(labSciAuthor);
			}
		}
		if (null != labSciScienceVo.getLabSciFundsList()
				&& labSciScienceVo.getLabSciFundsList().size() > 0) {
			for (LabSciFundsVo labSciFundsVo : labSciScienceVo
					.getLabSciFundsList()) {
				LabSciFunds labSciFunds = new LabSciFunds();
				labSciFunds.setType("2");
				BeanUtils.copyProperties(labSciFundsVo, labSciFunds,
						new String[] { "labSciScience" });
				labSciFunds.setLabSciScience(labSciScience);
				labSciFundsDAO.addLabSciFunds(labSciFunds);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSciScience
				.getId(),Constants_Business.WF_CODE_SCIE, getSessionContainer().getFunId(),
				labSciScienceVo.getAuditMessage(), labSciScienceVo.getAuditResult());
		if (ins != null) {
			labSciScience.setProcessIns(ins);
			labSciScienceDAO.updateLabSciScience(labSciScience);
		} else {
			throw new GlobalException("LabSciScienceServiceImpl...流程实例化出错！");
		}
		labSciScienceVo.setId(labSciScience.getId());
		
		List<LabUpload> loadList = labUploadDAO.getLabUploadList(labSciScienceVo.getUuid(), "lab-science");
		if (loadList != null) {
			for (LabUpload labUpload : loadList) {
				labUpload.setBusId(labSciScience.getId());
				labUploadDAO.updateLabUpload(labUpload);
			}
		}
		return PoToVo(labSciScience);
	}

	@Override
	public boolean deleteLabSciScience(String[] ids) throws GlobalException {
		boolean result = false;
		for (String id : ids) {
			try {
				labSciScienceDAO.deleteLabSciScience(id);
				result = true;
			} catch (Exception e) {
				result = false;
				throw new GlobalException("" + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public boolean updateLabSciScience(LabSciScienceVo labSciScienceVo)
			throws GlobalException {
		try {
			LabSciScience labSciScience = VoToPo(labSciScienceVo);
			labSciScienceDAO.updateLabSciScience(labSciScience);
			
			if (null!=labSciScienceVo.getAuthorList()&&labSciScienceVo.getAuthorList().size()>0) {
		           for(LabSciAuthorVo vo:labSciScienceVo.getAuthorList())
		           {
		        	   LabSciAuthor po=(LabSciAuthor) labSciAuthorDAO.findById(LabSciAuthor.class, vo.getId());
		        	   po.setContributionRate(vo.getContributionRate());
		        	   labSciAuthorDAO.updateLabSciAuthor(po);
		           }
				}
			if (null != labSciScienceVo.getLabSciFundsList() && labSciScienceVo.getLabSciFundsList().size() > 0) {
				for (LabSciFundsVo labSciFundsVo : labSciScienceVo
						.getLabSciFundsList()) {
					LabSciFunds labSciFunds = new LabSciFunds();
					if(!StrUtils.isBlankOrNull(labSciFundsVo.getId())){
						labSciFunds.setType("2");
						labSciFunds = (LabSciFunds)labSciFundsDAO.findById(LabSciFunds.class, labSciFundsVo.getId());
						BeanUtils.copyProperties(labSciFundsVo, labSciFunds,
								new String[] { "labSciScience" });
						labSciFundsDAO.updateLabSciFunds(labSciFunds);
					}else{
						labSciFunds.setType("2");
						BeanUtils.copyProperties(labSciFundsVo, labSciFunds,
								new String[] { "labSciScience" });
						labSciFunds.setLabSciScience(labSciScience);
						labSciFundsDAO.addLabSciFunds(labSciFunds);
					}
					
				}
			}
			// 流程实例
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSciScience
					.getId(),Constants_Business.WF_CODE_SCIE, getSessionContainer().getFunId(),
					labSciScienceVo.getAuditMessage(), labSciScienceVo.getAuditResult());
			if (ins == null) {
				throw new GlobalException("LabSciScienceServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSciScienceVo> getLabSciScienceList(
			LabSciScience labSciScienceVo) throws GlobalException {
		String hql = "from LabSciScience where isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciScienceVo.getCode())) {
			hql += " and code like '%" + labSciScienceVo.getCode() + "%'";
		}
		List<LabSciScience> list = labSciScienceDAO.find(hql);
		List<LabSciScienceVo> voList = new ArrayList<LabSciScienceVo>();
		for (LabSciScience labSciScience : list) {
			voList.add(PoToVo(labSciScience));
		}
		return voList;
	}

	@Override
	public LabSciScienceVo getLabSciScienceVoById(String id)
			throws GlobalException {
		LabSciScience labSciScience = (LabSciScience) labSciScienceDAO
				.findById(LabSciScience.class, id);
		return PoToVo(labSciScience);
	}

	@Override
	public boolean updateLabSciScience4Audit(LabSciScienceVo labSciScienceVo) throws GlobalException {
		try {
			// 流程实例
			LabSciScience labSciScience = (LabSciScience)labSciScienceDAO.findById(LabSciScience.class, labSciScienceVo.getId()); 
			labSciScience.setIsSeized(Constants_Business.N);
			labSciScienceDAO.updateLabSciScience(labSciScience);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSciScienceVo
					.getId(),Constants_Business.WF_CODE_SCIE, getSessionContainer().getFunId(),
					labSciScienceVo.getAuditMessage(), labSciScienceVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabSciScienceServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
}
