package cn.labsoft.labos.business.science.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.code.entity.LabCode;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.science.dao.ILabSciAchievementDAO;
import cn.labsoft.labos.business.science.entity.LabSciAchievement;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciAchievementService;
import cn.labsoft.labos.business.science.vo.LabSciAchievementVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labSciAchievementService")
public class LabSciAchievementServiceImpl extends BaseService implements
		ILabSciAchievementService {

	private ILabSciAchievementDAO labSciAchievementDAO;

	@Resource
	public void setLabSciAchievementDAO(
			ILabSciAchievementDAO labSciAchievementDAO) {
		this.labSciAchievementDAO = labSciAchievementDAO;
	}

	public LabSciAchievementVo poToVo(LabSciAchievement labSciAchievement)
			throws GlobalException {
		LabSciAchievementVo labSciAchievementVo = new LabSciAchievementVo();
		BeanUtils.copyProperties(labSciAchievement, labSciAchievementVo,
				new String[] { "labSciScience" });
		if (null != labSciAchievement.getLabSciScience()) {
			labSciAchievementVo.setLabSciScienceId(labSciAchievement
					.getLabSciScience().getId());
			labSciAchievementVo.setLabSciScienceName(labSciAchievement
					.getLabSciScience().getName());
		}
		return labSciAchievementVo;
	}

	public LabSciAchievement voToPo(LabSciAchievementVo labSciAchievementVo)
			throws GlobalException {
		LabSciAchievement labSciAchievement;
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getId())) {
			labSciAchievement = (LabSciAchievement) labSciAchievementDAO
					.findById(LabSciAchievement.class, labSciAchievementVo
							.getId());
		} else {
			labSciAchievement = new LabSciAchievement();
		}
		BeanUtils.copyProperties(labSciAchievementVo, labSciAchievement,
				new String[] { "isDel","createTime","tenantId","createUserId" });
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getLabSciScienceId())) {
			LabSciScience labSciScience = (LabSciScience)labSciAchievementDAO.findById(LabSciScience.class, labSciAchievementVo.getLabSciScienceId());
			labSciAchievement.setLabSciScience(labSciScience);
		}
		return labSciAchievement;
	}

	public String getQueryHql(LabSciAchievementVo labSciAchievementVo)
			throws GlobalException {
		String hql = "FROM LabSciAchievement WHERE isDel='" + Constants_Business.N + "'";
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getAchievementType())) {
			hql += " AND achievementType ='"
					+ labSciAchievementVo.getAchievementType().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getName())) {
			hql += " AND name LIKE '%" + labSciAchievementVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getLabSciScienceId())) {
			hql += " AND labSciScience.id = '" + labSciAchievementVo.getLabSciScienceId() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getType())) {
			hql += " AND type ='" + labSciAchievementVo.getType().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getStartDate())) {
			hql += " AND date >='" + labSciAchievementVo.getStartDate().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getEndDate())) {
			hql += " AND date <='" + labSciAchievementVo.getEndDate().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getFristAuthorName())) {
			hql += " AND fristAuthorName LIKE '%"
					+ labSciAchievementVo.getFristAuthorName().trim() + "%'";
		 }
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getOtherAuthorName())) {
			hql += " AND otherAuthorName LIKE '%"
				+ labSciAchievementVo.getOtherAuthorName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getLabSciScienceNum())) {
			hql += " AND labSciScience.code ='"
				+ labSciAchievementVo.getLabSciScienceNum().trim()+ "'";
		}
		return hql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSciAchievementPR(PageResult pageResult,
			LabSciAchievementVo labSciAchievementVo) throws GlobalException {
		pageResult = labSciAchievementDAO.getPageResult(
				getQueryHql(labSciAchievementVo), pageResult);
		List<LabSciAchievementVo> voList = new ArrayList<LabSciAchievementVo>();
		List<LabSciAchievement> poList = pageResult.getResultList();
		if (poList.size() > 0) {
			for (LabSciAchievement po : poList)
				voList.add(poToVo(po));
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSciAchievementVo> getLabSciAchievementList(
			LabSciAchievementVo labSciAchievementVo) throws GlobalException {
		List<LabSciAchievementVo> voList = new ArrayList<LabSciAchievementVo>();
		List<LabSciAchievement> poList = labSciAchievementDAO
				.find(getQueryHql(labSciAchievementVo));
		if (poList.size() > 0) {
			for (LabSciAchievement po : poList)
				voList.add(poToVo(po));
		}
		return voList;
	}

	@Override
	public LabSciAchievementVo addLabSciAchievement(
			LabSciAchievementVo labSciAchievementVo) throws GlobalException {
		return poToVo(labSciAchievementDAO
				.addLabSciAchievement(voToPo(labSciAchievementVo)));
	}

	@Override
	public boolean deleteLabSciAchievement(String[] ids) throws GlobalException {
		try {
			for (String id : ids)
				labSciAchievementDAO.deleteLabSciAchievement(id);
			return true;
		} catch (Exception e) {
			Log4J.error("deleteLabSciAchievement " + e.getMessage());
			return false;
		}
	}

	@Override
	public LabSciAchievementVo getLabSciAchievementVo(
			LabSciAchievementVo labSciAchievementVo) throws GlobalException {
		return poToVo((LabSciAchievement) labSciAchievementDAO.findById(
				LabSciAchievement.class, labSciAchievementVo.getId()));
	}

	@Override
	public boolean updateLabSciAchievement(
			LabSciAchievementVo labSciAchievementVo) throws GlobalException {
		return labSciAchievementDAO
				.updateLabSciAchievement(voToPo(labSciAchievementVo));
	}

	@Override
	public String[] getLabSciAchievementShowByType(String labSciScienceId)
			throws GlobalException {
		String[] str={"","","",""};
		String hql="SELECT type,count(type) FROM LabSciAchievement  WHERE isDel='"+Constants_Business.N+"'";
		if (!StrUtils.isBlankOrNull(labSciScienceId)) {
			hql+="and labSciScience.id='"+labSciScienceId+"'";  
		}
		hql+="GROUP BY type ";
	    List<Object[]> list=labSciAchievementDAO.find(hql);
	    if (null!=list&&list.size()>0) {
	    	String names="";
	    	String money="";
			for(int i=0;i<list.size()-1;i++)
			{
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("PAPER")) {
					names+="'论文',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("BOOK")) {
					names+="'著作',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("PATENT")) {
					names+="'发明',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("AWARD")) {
					names+="'获奖',";
				}
				money+=list.get(i)[1].toString()+",";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("PAPER")) {
				names+="'论文'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("BOOK")) {
				names+="'著作'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("PATENT")) {
				names+="'发明'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("AWARD")) {
				names+="'获奖'";
			}
			money+=list.get(list.size()-1)[1].toString();
			str[0]=names;
			str[1]=money;
		}
		return str;
	}

	@Override
	public String[] getLabSciAchievementShowByUser(String userId)
			throws GlobalException {
		String[] str={"","","",""};
		String hql="SELECT type,count(type) FROM LabSciAchievement  WHERE isDel='"+Constants_Business.N+"' and (fristAuthorId='"+userId+"' or otherAuthorId like '%"+userId+"%')  GROUP BY achievementType ";
	    List<Object[]> list=labSciAchievementDAO.find(hql);
	    if (null!=list&&list.size()>0) {
	    	String names="";
	    	String money="";
			for(int i=0;i<list.size()-1;i++)
			{
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("PAPER")) {
					names+="'论文',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("BOOK")) {
					names+="'著作',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("PATENT")) {
					names+="'发明',";
				}
				if(!StrUtils.isBlankOrNull(list.get(i)[0].toString())&&list.get(i)[0].equals("AWARD")) {
					names+="'获奖',";
				}
				money+=list.get(i)[1].toString()+",";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("PAPER")) {
				names+="'论文'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("BOOK")) {
				names+="'著作'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("PATENT")) {
				names+="'发明'";
			}
			if(!StrUtils.isBlankOrNull(list.get(list.size()-1)[0].toString())&&list.get(list.size()-1)[0].equals("AWARD")) {
				names+="'获奖'";
			}
			money+=list.get(list.size()-1)[1].toString();
			str[0]=names;
			str[1]=money;
		}
		return str;
	}

	@Override
	public String[] getLabSciAchievementShowByPaperType(List<LabUserVo> labuseList,List<LabCodeVo>  labcodeList)
			throws GlobalException {
		String[] strings={"","","",""};
		if(null!=labcodeList&&labcodeList.size()>0)
		{
			for(int i=0;i<labcodeList.size();i++)
			{
				String num="";
				LabCodeVo codeVo=labcodeList.get(i);
				if (null!=labuseList&&labuseList.size()>0) {
					for(LabUserVo userVo:labuseList)
					{
					    double no=labSciAchievementDAO.getCountSize("select count(*) from LabSciAchievement  WHERE isDel='"+Constants_Business.N+"' and (fristAuthorId='"+userVo.getId()+"' or otherAuthorId like '%"+userVo.getId()+"%') and type='PAPER' and organization='"+codeVo.getName()+"'");
						num+=String.valueOf(no)+",";
					}
				}
				num=num.substring(0, num.length()-1);
				strings[i]=num;
			}
		}
		return strings;
	}

}
