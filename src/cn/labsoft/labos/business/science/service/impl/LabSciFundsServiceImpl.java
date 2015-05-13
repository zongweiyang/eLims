package cn.labsoft.labos.business.science.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.business.science.dao.ILabSciFundsDAO;
import cn.labsoft.labos.business.science.dao.ILabSciScienceDAO;
import cn.labsoft.labos.business.science.entity.LabSciFunds;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciFundsService;
import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSciFundsService")
public class LabSciFundsServiceImpl extends BaseService implements
		ILabSciFundsService {

	@SuppressWarnings("unused")
	private ILabSciFundsDAO labSciFundsDAO;
    private ILabSciScienceDAO labSciScienceDAO;
    private ILabUploadDAO labUploadDAO;
    
   @Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Resource
	public void setLabSciFundsDAO(ILabSciFundsDAO labSciFundsDAO) {
		this.labSciFundsDAO = labSciFundsDAO;
	}
	
	@Resource
	public void setLabSciScienceDAO(ILabSciScienceDAO labSciScienceDAO) {
		this.labSciScienceDAO = labSciScienceDAO;
	}


	@SuppressWarnings("unused")
	private LabSciFundsVo PoToVo(LabSciFunds labSciFunds)
			throws GlobalException {
		LabSciFundsVo labSciFundsVo = new LabSciFundsVo();
		BeanUtils.copyProperties(labSciFunds, labSciFundsVo,
				new String[] { "labSciScience" });
		labSciFundsVo.setOldMoney(labSciFunds.getMoney());
		if (null != labSciFunds.getLabSciScience()) {
			LabSciScience labSciScience=(LabSciScience) labSciFundsDAO.findById(LabSciScience.class, labSciFunds.getLabSciScience().getId());
			labSciFundsVo.setLabSciScienceId(labSciScience.getId());
     		labSciFundsVo.setLabSciScienceName(labSciScience.getName());
			labSciFundsVo.setSciCode(labSciScience.getCode());
			labSciFundsVo.setSciMastor(labSciScience.getMaster().getName());
		}
		return labSciFundsVo;
	}

	@SuppressWarnings("unused")
	private LabSciFunds VoToPo(LabSciFundsVo labSciFundsVo)
			throws GlobalException {
		LabSciFunds labSciFunds;
		if (StrUtils.isBlankOrNull(labSciFundsVo.getId())) {
			labSciFunds=new LabSciFunds();
		}else {
			labSciFunds=(LabSciFunds) labSciFundsDAO.findById(LabSciFunds.class, labSciFundsVo.getId());
		}
		BeanUtils.copyProperties(labSciFundsVo, labSciFunds,
				new String[] { "isDel","createTime","tenantId","createUserId" });
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getLabSciScienceId())) {
			LabSciScience labSciScience = new LabSciScience();
			labSciScience.setId(labSciFundsVo.getLabSciScienceId());
			labSciFunds.setLabSciScience(labSciScience);
		}
		labSciFunds.setIsDel(Constants_Business.N);
		return labSciFunds;
	}

	@Override
	public LabSciFundsVo addLabSciFunds(LabSciFundsVo labSciFundsVo)
			throws GlobalException {
		LabSciFundsVo labSciFundsVo2 = PoToVo(labSciFundsDAO
				.addLabSciFunds(VoToPo(labSciFundsVo)));
		LabSciScience labSciScience=(LabSciScience) labSciFundsDAO.findById(LabSciScience.class, labSciFundsVo2.getLabSciScienceId());
		if (!StrUtils.isBlankOrNull(labSciFundsVo2.getType())&&"0".equals(labSciFundsVo2.getType())) {
			labSciScience.setInFunds(labSciScience.getInFunds()+labSciFundsVo2.getMoney());
			labSciScience.setPaperFunds(labSciScience.getPaperFunds()+labSciFundsVo2.getMoney());
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo2.getType())&&"1".equals(labSciFundsVo2.getType())) {
			labSciScience.setOutFunds(labSciScience.getOutFunds()+labSciFundsVo2.getMoney());
			labSciScience.setPaperFunds(labSciScience.getPaperFunds()-labSciFundsVo2.getMoney());
		}
		labSciScienceDAO.updateLabSciScience(labSciScience);
		List<LabUpload> fileList=labUploadDAO.getLabUploadList(labSciFundsVo.getUuid(), "lab-scifunds");
		if(fileList.size()>0)
		{
			for(LabUpload labUpload:fileList)
			{
				labUpload.setBusId(labSciFundsVo.getId());
				labUploadDAO.updateLabUpload(labUpload);
			}
		}
		return labSciFundsVo;
	}

	@Override
	public boolean deleteLabSciFunds(String[] ids) throws GlobalException {
		@SuppressWarnings("unused")
		boolean result = false;
		for (String id : ids) {
			try {
				labSciFundsDAO.deleteLabSciFunds(id);
			} catch (Exception e) {
				Log4J.error("deleteLabSciFunds error---" + e.getMessage());
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSciFundsVo> getLabSciFundsList(LabSciFundsVo labSciFundsVo)
			throws GlobalException {
		String hql = "from LabSciFunds where isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getLabSciScienceId())) {
			hql+=" and labSciScience.id='"+labSciFundsVo.getLabSciScienceId()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())) {
		    hql+=" and type ='"+labSciFundsVo.getType()+"'";
		}
		hql+=" order by money desc";
		List<LabSciFunds> poList = labSciFundsDAO.find(hql);
		if(poList.size()>5)
			poList=poList.subList(0, 5);
		List<LabSciFundsVo> voList = new ArrayList<LabSciFundsVo>();
		for (LabSciFunds po : poList) {
			@SuppressWarnings("unused")
			LabSciFundsVo vo = new LabSciFundsVo();
			voList.add(PoToVo(po));
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSciFundsPR(LabSciFundsVo labSciFundsVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabSciFunds WHERE isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())) {
			hql+=" AND type='"+labSciFundsVo.getType()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getLabSciScienceId())) {
			hql+=" AND labSciScience.id='"+labSciFundsVo.getLabSciScienceId()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getName())) {
			hql+=" AND name like'%"+labSciFundsVo.getName()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getUser())) {
			hql+=" AND user like'%"+labSciFundsVo.getUser()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getStartDate())) {
			hql+=" AND useDate >='"+labSciFundsVo.getStartDate()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getEndDate())) {
			hql+=" AND useDate <='"+labSciFundsVo.getEndDate()+"'";
		}
		pageResult = labSciFundsDAO.getPageResult(hql, pageResult);
		List<LabSciFunds> poList = pageResult.getResultList();
		List<LabSciFundsVo> voList = new ArrayList<LabSciFundsVo>();
		for (LabSciFunds po : poList) {
			@SuppressWarnings("unused")
			LabSciFundsVo vo = new LabSciFundsVo();
			voList.add(PoToVo(po));
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public LabSciFundsVo getLabSciFundsVoById(String id) throws GlobalException {
		return PoToVo((LabSciFunds) labSciFundsDAO.findById(LabSciFunds.class,
				id));
	}

	@Override
	public boolean updateLabSciFunds(LabSciFundsVo labSciFundsVo)
			throws GlobalException {
		LabSciScience labSciScience=(LabSciScience) labSciFundsDAO.findById(LabSciScience.class, labSciFundsVo.getLabSciScienceId());
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"0".equals(labSciFundsVo.getType())) {
			labSciScience.setInFunds(labSciScience.getInFunds()+labSciFundsVo.getMoney()-labSciFundsVo.getOldMoney());
			labSciScience.setPaperFunds(labSciScience.getPaperFunds()+labSciFundsVo.getMoney()-labSciFundsVo.getOldMoney());
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"1".equals(labSciFundsVo.getType())) {
			labSciScience.setOutFunds(labSciScience.getOutFunds()+labSciFundsVo.getMoney()-labSciFundsVo.getOldMoney());
			labSciScience.setPaperFunds(labSciScience.getPaperFunds()-labSciFundsVo.getMoney()+labSciFundsVo.getOldMoney());
		}
		labSciScienceDAO.updateLabSciScience(labSciScience);
		return labSciFundsDAO.updateLabSciFunds(VoToPo(labSciFundsVo));
	}

	@Override
	public String[] getQueryLabSciFunds(String labSicScienceId)
			throws GlobalException {
		String[] str={""};
		String hql="SELECT name,SUM(money)FROM LabSciFunds  WHERE isDel='"+Constants_Business.N+"' and labSciScience.id='"+labSicScienceId+"' and type='1' GROUP BY NAME ";
	    List<Object[]> list=labSciFundsDAO.find(hql);
	    if (null!=list&&list.size()>0) {
	    	String names="";
			for(int i=0;i<list.size()-1;i++)
			{
				names+="['"+list.get(i)[0].toString()+"',"+list.get(i)[1].toString()+"],";
			}
			names+="['"+list.get(list.size()-1)[0].toString()+"',"+list.get(list.size()-1)[1].toString()+"]";
			str[0]=names;
		}
		return str;
	}

	
}
