package cn.labsoft.labos.business.science.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.business.science.dao.ILabSciLearnDAO;
import cn.labsoft.labos.business.science.entity.LabSciLearn;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciLearnService;
import cn.labsoft.labos.business.science.vo.LabSciLearnVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSciLearnService")
public class LabSciLearnServiceImpl extends BaseService implements
		ILabSciLearnService {

	private ILabSciLearnDAO labSciLearnDAO;

	@Resource
	public void setLabSciLearnDAO(ILabSciLearnDAO labSciLearnDAO) {
		this.labSciLearnDAO = labSciLearnDAO;
	}
	
	public LabSciLearnVo poToVo(LabSciLearn labSciLearn) throws GlobalException {
		LabSciLearnVo vo = new LabSciLearnVo();
		BeanUtils.copyProperties(labSciLearn, vo, new String[] {});
		if (null!=labSciLearn.getLabSciScience()) {
			vo.setLabSciScienceId(labSciLearn.getLabSciScience().getId());
			vo.setLabSciScienceName(labSciLearn.getLabSciScience().getName());
		}
		return vo;
	}

	public LabSciLearn voToPo(LabSciLearnVo labSciLearnVo)
			throws GlobalException {
		LabSciLearn labSciLearn;
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getId())) {
			labSciLearn = (LabSciLearn) labSciLearnDAO.findById(
					LabSciLearn.class, labSciLearnVo.getId());
		} else {
			labSciLearn = new LabSciLearn();
		}
		BeanUtils.copyProperties(labSciLearnVo, labSciLearn, new String[]{"isDel","createTime","tenantId","createUserId"});
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getLabSciScienceId())) {
			labSciLearn.setLabSciScience((LabSciScience)labSciLearnDAO.findById(LabSciScience.class, labSciLearnVo.getLabSciScienceId()));
		}
		return labSciLearn;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSciLearnPR(LabSciLearnVo labSciLearnVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabSciLearn WHERE isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())) {
			hql += " AND type='" + labSciLearnVo.getType() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getName())) {
			hql += " AND name LIKE '%" + labSciLearnVo.getName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getLearnType())) {
			hql += " AND learnType ='" + labSciLearnVo.getLearnType() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getStartTime())) {
			hql+=" AND startTime>='"+labSciLearnVo.getStartTime()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getEndTime())) {
			hql+=" AND endTime<='"+labSciLearnVo.getEndTime()+"'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getSpeaker())) {
			hql+=" AND speaker LIKE'%"+labSciLearnVo.getSpeaker()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSciLearnVo.getLabSciScienceCode()))
		{
			hql += " AND labSciScience.code ='"+labSciLearnVo.getLabSciScienceCode()+"'";
		}
		pageResult = labSciLearnDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList().size() > 0) {
			List<LabSciLearnVo> voList = new ArrayList<LabSciLearnVo>();
			List<LabSciLearn> poList = pageResult.getResultList();
			for (LabSciLearn po : poList) {
				voList.add(poToVo(po));
			}
			pageResult.setResultList(voList);
		}
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSciLearnVo> getLabSciLearnList(LabSciLearnVo labSciLearnVo)
			throws GlobalException {
		String hql = "from LabSciLearn where isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())) {
			hql += " and type='" + labSciLearnVo.getType() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getName())) {
			hql += " and name like '%" + labSciLearnVo.getName() + "%'";
		}
		if(!StrUtils.isBlankOrNull(labSciLearnVo.getLabSciScienceCode()))
		{
			hql += " AND labSciScience.code ='"+labSciLearnVo.getLabSciScienceCode()+"'";
		}
		List<LabSciLearn> poList=labSciLearnDAO.find(hql);
		List<LabSciLearnVo> voList = new ArrayList<LabSciLearnVo>();
		if (poList.size() > 0) {
			for (LabSciLearn po : poList) {
				voList.add(poToVo(po));
			}
		}
		return voList;
	}
	
	@Override
	public LabSciLearnVo addLabSciLearn(LabSciLearnVo labSciLearnVo)
			throws GlobalException {
		return poToVo(labSciLearnDAO.addLabSciLearn(voToPo(labSciLearnVo)));
	}

	@Override
	public boolean deleteLabSciLearn(String[] ids) throws GlobalException {
		try {
			for(String id:ids)
				labSciLearnDAO.deleteLabSciLearn(id);
			return true;
		} catch (Exception e) {
			Log4J.error("delete  LabSciLearn error"+e.getMessage());
			return false;
		}
	}


	@Override
	public LabSciLearnVo getLabSciLearn(LabSciLearnVo labSciLearnVo)
			throws GlobalException {
		return poToVo((LabSciLearn)labSciLearnDAO.findById(LabSciLearn.class, labSciLearnVo.getId()));
	}

	@Override
	public boolean updateLabSciLearn(LabSciLearnVo labSciLearnVo)
			throws GlobalException {
		return labSciLearnDAO.updateLabSciLearn(voToPo(labSciLearnVo));
	}



}
