package cn.labsoft.labos.base.org.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.dao.ILabUserOrgDAO;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Service("labOrgService")
public class LabOrgServiceImpl implements ILabOrgService {
	private ILabOrgDAO labOrgDAO;
	private ILabUserOrgDAO labUserOrgDAO;
	private ILabUploadDAO labUploadDAO;

	@Resource
	public void setLabOrgDAO(ILabOrgDAO labOrgDAO) {
		this.labOrgDAO = labOrgDAO;
	}

	@Resource
	public void setLabUserOrgDAO(ILabUserOrgDAO labUserOrgDAO) {
		this.labUserOrgDAO = labUserOrgDAO;
	}

	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Override
	public LabOrgVo addLabOrg(LabOrgVo labOrgVo) throws GlobalException {
		try {
			LabOrg labOrg = new LabOrg();
			LabOrg labOrgP = labOrgDAO.getLabOrg(labOrgVo.getParentId());
			labOrg = this.vo2Po(labOrgVo, labOrg);
			labOrg.setIsDel(Constants_Common.N);
			labOrg.setCode(labOrgP.getCode()
					+ (labOrg.getSort() >= 10 ? labOrg.getSort()
							: ("0" + labOrg.getSort())));
			String hql = "SELECT max(dataStr) FROM LabOrg";
			Object obj = labOrgDAO.find(hql, 0);
			if (obj == null)
				obj = 100;
			labOrg.setDataStr(Integer.valueOf(String.valueOf(obj)) + 1);// 数据标示
			String tenantid = labOrgP.getTenantId();
			tenantid += "-" + labOrg.getDataStr();
			labOrg.setTenantId(tenantid);// 用于数据权限
			labOrg.setRank(labOrgP.getRank() + 1);
			labOrgDAO.addLabOrg(labOrg);
			labOrgVo.setId(labOrg.getId());
		} catch (Exception e) {
			// e.printStackTrace();
			Log4J.error(
					"LabOrgServiceImpl addLabOrg  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labOrgVo;
	}

	@Override
	public LabOrgVo getLabOrg(String id) throws GlobalException {
		LabOrgVo labOrgVo = new LabOrgVo();
		LabOrg labOrg = new LabOrg();
		try {
			if (!StrUtils.isBlankOrNull(id)) {
				labOrg = labOrgDAO.getLabOrg(id);
			}
			labOrgVo = this.po2Vo(labOrg, labOrgVo);
			return labOrgVo;
		} catch (Exception e) {
			Log4J.error(
					"LabOrgServiceImpl getLabOrg error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public int getMaxSort(LabOrgVo sysOrgVo) throws GlobalException {
		int sort = -1;
		String sql = "select max(sort) from LabOrg po where po.labOrg.id='"
				+ sysOrgVo.getParentId() + "' and po.isDel='N'";
		if (null != labOrgDAO.getColumnMaxValue(sql)) {
			sort = (Integer) labOrgDAO.getColumnMaxValue(sql);
		}
		return sort;
	}

	@Override
	public List<LabOrgVo> getLabOrgVoByRank(String rank) throws GlobalException {
		String whereHql = "";
		if (!StrUtils.isBlankOrNull(rank)) {
			whereHql += " AND rank=" + rank;
		}
		return this.getLabOrgVoByWhere(whereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabOrgPR(LabOrgVo labOrgVo, PageResult pageResult)
			throws GlobalException {
		String hql = "FROM LabOrg WHERE isDel='" + Constants_Common.N + "'";
		if (StrUtils.isBlankOrNull(labOrgVo.getParentId()))
			labOrgVo.setParentId("0");
		hql += " AND labOrg.id='" + labOrgVo.getParentId() + "'";
		// hql+="ORDER BY sort ";
		pageResult = labOrgDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null
				&& pageResult.getResultList().size() > 0) {
			List<LabOrgVo> listLabOrgVo = new ArrayList<LabOrgVo>();
			List<LabOrg> listLabOrg = new ArrayList<LabOrg>();
			listLabOrg = pageResult.getResultList();
			for (LabOrg sysOrg : listLabOrg) {
				LabOrgVo vo = new LabOrgVo();
				vo = this.po2Vo(sysOrg, vo);
				listLabOrgVo.add(vo);
			}
			pageResult.setResultList(listLabOrgVo);
		}
		return pageResult;
	}

	@Override
	public List<LabOrgVo> getLabOrgListByPName(String parentName)
			throws GlobalException {
		String whereHql = "";
		if (!StrUtils.isBlankOrNull(parentName)) {
			whereHql += " AND labOrg.name='" + parentName + "'";
		}
		List<LabOrgVo> listLabOrgVo = this.getLabOrgVoByWhere(whereHql);
		return listLabOrgVo;
	}

	@Override
	public List<LabOrgVo> getLabOrgList(LabOrgVo labOrgVo)
			throws GlobalException {
		List<LabOrgVo> listLabOrgVo = new ArrayList<LabOrgVo>();
		try {
			String whereHql = "";
			listLabOrgVo = this.getLabOrgVoByWhere(whereHql);
		} catch (Exception e) {
			Log4J.error(
					"LabOrgServiceImpl getLabOrgList  error..."
							+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return listLabOrgVo;
	}

	@Override
	public List<LabOrgVo> getLabOrgListByPId(String pId) throws GlobalException {
		List<LabOrgVo> listSysOrgVo = new ArrayList<LabOrgVo>();
		if (!StrUtils.isBlankOrNull(pId)) {
			String whereHql = " AND labOrg.id='" + pId + "'";
			try {
				listSysOrgVo = this.getLabOrgVoByWhere(whereHql);
			} catch (Exception e) {
				Log4J.error("LabOrgServiceImpl getLabOrgListByPId  error..."
						+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return listSysOrgVo;
	}

	@Override
	public LabOrgVo getLabOrgUnit() throws GlobalException {
		String whereHql = " AND labOrg is null ";
		List<LabOrgVo> labOrgVoList = this.getLabOrgVoByWhere(whereHql);
		if (labOrgVoList != null && labOrgVoList.size() > 0) {
			return labOrgVoList.get(0);
		}
		return null;
	}

	@Override
	public boolean updateLabOrg(LabOrgVo labOrgVo) throws GlobalException {
		boolean key = true;

		if (labOrgVo != null && !StrUtils.isBlankOrNull(labOrgVo.getId())) {
			LabOrg sysOrg = labOrgDAO.getLabOrg(labOrgVo.getId());
			sysOrg = this.vo2Po(labOrgVo, sysOrg);
			LabOrg labOrgP = sysOrg.getLabOrg();
			try {
				if (null != labOrgP) {
					sysOrg.setCode(labOrgP.getCode()
							+ (sysOrg.getSort() >= 10 ? sysOrg.getSort()
									: ("0" + sysOrg.getSort())));
					String tenantid = labOrgP.getTenantId();
					tenantid += "-" + sysOrg.getDataStr();
					sysOrg.setTenantId(tenantid);// 用于数据权限
				}
				labOrgDAO.updateLabOrg(sysOrg);
			} catch (Exception e) {
				Log4J.error(
						"LabOrgServiceImpl updateLabOrg  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		} else
			key = false;
		return key;
	}

	@Override
	public boolean updateLabOrgPid(LabOrgVo labOrgVo) throws GlobalException {
		boolean key = true;
		if (labOrgVo != null && !StrUtils.isBlankOrNull(labOrgVo.getId())) {
			LabOrg labOrg = labOrgDAO.getLabOrg(labOrgVo.getId());
			LabOrg labOrgP = null;
			if (!StrUtils.isBlankOrNull(labOrgVo.getParentId())) {
				labOrgP = labOrgDAO.getLabOrg(labOrgVo.getParentId());
			}
			if (labOrgP != null) {
				labOrg.setLabOrg(labOrgP);
				int sort = this.getMaxSort(labOrgVo) + 1;
				labOrg.setCode(labOrgP.getCode()
						+ (sort >= 10 ? sort : ("0" + sort)));
				String tenantid = labOrgP.getTenantId();
				tenantid += "-" + labOrg.getDataStr();
				labOrg.setTenantId(tenantid);// 用于数据权限
				labOrg.setRank(labOrgP.getRank() + 1);
			}
			try {

				labOrgDAO.updateLabOrg(labOrg);
			} catch (Exception e) {
				Log4J.error(
						"LabOrgServiceImpl updateLabOrgPid  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		} else
			key = false;
		return key;
	}

	@Override
	public List<LabOrgVo> getLabOrgVoByWhere(String whereHql)
			throws GlobalException {
		List<LabOrgVo> listLabOrgVo = new ArrayList<LabOrgVo>();
		List<LabOrg> listLabOrg = new ArrayList<LabOrg>();
		String hql = "FROM LabOrg WHERE isDel='" + Constants_Common.N + "'";
		if (!StrUtils.isBlankOrNull(whereHql)) {
			hql += whereHql;
		}
		try {
			listLabOrg = labOrgDAO.getLabOrgList(hql);
			if (listLabOrg != null && listLabOrg.size() > 0) {
				for (LabOrg sysOrg : listLabOrg) {
					LabOrgVo sysOrgVo = new LabOrgVo();
					sysOrgVo = this.po2Vo(sysOrg, sysOrgVo);
					listLabOrgVo.add(sysOrgVo);
				}
			}
		} catch (Exception e) {
			Log4J.error(
					"LabOrgServiceImpl getSysOrgVoByWhere error..."
							+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return listLabOrgVo;
	}

	public LabOrgVo po2Vo(LabOrg labOrg, LabOrgVo labOrgVo)
			throws GlobalException {
		if (labOrg != null) {
			BeanUtils.copyProperties(labOrg, labOrgVo,
					new String[] { "labOrg" });
			if (labOrg.getLabOrg() != null) {
				labOrgVo.setParentId(labOrg.getLabOrg().getId());
				labOrgVo.setParentName(labOrg.getLabOrg().getName());
			} else {
				labOrgVo.setParentId("");
			}
			List<LabUpload> fileList = labUploadDAO.getLabUploadList(
					labOrg.getId(), "sysLogo");
			if (fileList != null && fileList.size() > 0) {
				labOrgVo.setLogo(fileList.get(0).getPath());
			}
		}
		return labOrgVo;
	}

	public LabOrg vo2Po(LabOrgVo labOrgVo, LabOrg labOrg)
			throws GlobalException {
		BeanUtils.copyProperties(labOrgVo, labOrg, new String[] { "parentId",
				"code", "rank", "isDel", "tenantId", "dataStr", "createTime",
				"createUserId" });
		labOrg.setCode(labOrgVo.getCode());
		if (!StrUtils.isBlankOrNull(labOrgVo.getParentId())) {
			LabOrg labOrgP = labOrgDAO.getLabOrg(labOrgVo.getParentId());
			labOrg.setLabOrg((labOrgP));
		}
		return labOrg;
	}

	@Override
	public StringBuffer getZtreeByPId(String pId) throws GlobalException {
		StringBuffer treeBuf = new StringBuffer();
		treeBuf.append("[");
		if (pId != null) {
			treeBuf.append(treeSon(pId));
		} else {
			LabOrgVo labOrgVo = this.getLabOrg("0");
			treeBuf.append("{name:'" + labOrgVo.getName() + "', treeNid:'"
					+ labOrgVo.getId() + "', isParent:true,open:true,nodes:["
					+ treeSon("0") + "]}");
		}
		treeBuf.append("]");
		return treeBuf;
	}

	public StringBuffer treeSon(String treeNid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabOrgVo> listlabOrgVo = this.getLabOrgListByPId(treeNid);
		if (null != listlabOrgVo && listlabOrgVo.size() > 0) {
			for (int i = 0; i < listlabOrgVo.size(); i++) {
				String filename = listlabOrgVo.get(i).getName();
				String ids = listlabOrgVo.get(i).getId();
				if (this.getLabOrgListByPId(ids).size() > 0) {
					firstTree.append("{name:'" + filename + "', treeNid:'"
							+ ids + "', isParent:true},");
				} else {
					firstTree.append("{name:'" + filename + "', treeNid:'"
							+ ids + "', isParent:false},");
				}
			}
			firstTree.replace(firstTree.length() - 1, firstTree.length(), "");
		}
		return firstTree;
	}

	@Override
	public boolean updateLabOrg4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabOrg labgOrg = (LabOrg) labOrgDAO.findById(LabOrg.class,
							id);
					labgOrg.setIsDel(Constants_Common.Y);
					labOrgDAO.updateLabOrg(labgOrg);
				}
			} catch (Exception e) {
				Log4J.error(
						"LabOrgServiceImpl updateLabOrg4Del  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		} else
			key = false;
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabOrgVo> getLabOrgByUserId(String userId)
			throws GlobalException {
		List<LabOrgVo> listLabOrgVo = new ArrayList<LabOrgVo>();
		String hql = "FROM LabUserOrg WHERE isDel='" + Constants_Common.N
				+ "' AND user.id='" + userId + "'";
		List<LabUserOrg> listLabUserOrg = labUserOrgDAO.find(hql);
		if (listLabUserOrg != null && listLabUserOrg.size() > 0) {
			try {
				for (LabUserOrg labUserOrg : listLabUserOrg) {
					if (labUserOrg != null && labUserOrg.getOrg() != null) {
						LabOrgVo orgVo = new LabOrgVo();
						this.po2Vo(labUserOrg.getOrg(), orgVo);
						listLabOrgVo.add(orgVo);
					}
				}
			} catch (Exception e) {
				Log4J.error(
						"LabOrgServiceImpl getLabOrgByUserId  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return listLabOrgVo;
	}

	@Override
	public String testLabOrgName(LabOrgVo labOrgVo) throws GlobalException {
		if (!StrUtils.isBlankOrNull(labOrgVo.getName())) {
			String whereHql = " AND name='" + labOrgVo.getName() + "'";
			if (!StrUtils.isBlankOrNull(labOrgVo.getParentId())) {
				whereHql += " AND labOrg.id='" + labOrgVo.getParentId() + "'";
			}
			List<LabOrgVo> listLabOrgVo = this.getLabOrgVoByWhere(whereHql);
			if (listLabOrgVo != null && listLabOrgVo.size() > 0) {
				return Constants_Common.TRUE;
			}
		}
		return Constants_Common.FALSE;
	}

	@Override
	public StringBuffer getLabOrgContentsZtree(String parentId, String id)
			throws GlobalException {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		String whereHql = " AND id!='" + id + "' AND id!='" + parentId
				+ "' AND labOrg.id!='" + id + "'";
		List<LabOrgVo> listOrgVo = this.getLabOrgVoByWhere(whereHql);
		if (listOrgVo != null && listOrgVo.size() > 0) {
			for (LabOrgVo labOrgVo : listOrgVo) {
				sb.append("{name:'" + labOrgVo.getName() + "',treeNid:'"
						+ labOrgVo.getId() + "'},");
			}
		}
		if (sb.length() > 0)
			sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("]");
		return sb;
	}

	@Override
	public List<LabOrgVo> getLabOrgTree() throws GlobalException {
		LabOrgVo labOrgVo = new LabOrgVo();
		labOrgVo.setId("0");
		List<LabOrgVo> labOrgVoList = this.getLabOrgLevelSon(labOrgVo);
		return labOrgVoList;
	}

	public List<LabOrgVo> getLabOrgLevelSon(LabOrgVo labOrgVo)
			throws GlobalException {
		List<LabOrgVo> listLabOrgVo1 = this
				.getLabOrgListByPId(labOrgVo.getId());
		List<LabOrgVo> listLabOrgVo = new ArrayList<LabOrgVo>();
		for (LabOrgVo vo : listLabOrgVo1) {
			boolean key = true;
			int level = 0;
			while (key) {
				if (!StrUtils.isBlankOrNull(vo.getParentId())
						&& !vo.getParentId().equals("0")) {
					level++;
					if (!vo.getParentId().equals("0")) {
						LabOrg labOrg = labOrgDAO.getLabOrg(vo.getParentId());
						vo.setParentId(labOrg.getLabOrg().getId());
					} else
						key = false;
				} else {
					key = false;
				}
			}
			String text = "";
			for (int i = 0; i < level; i++) {
				text += "—";
			}
			vo.setName(text + vo.getName());
			listLabOrgVo.add(vo);
			if (this.getLabOrgListByPId(vo.getId()).size() > 0) {
				listLabOrgVo.addAll(getLabOrgLevelSon(vo));
			}

		}
		return listLabOrgVo;
	}

	public static void main(String[] args) {

	}

	@Override
	public LabOrgVo getLabOrgByVo(LabOrgVo labOrgVo) throws GlobalException {
		String hql = " FROM LabOrg WHERE isDel='" + Constants_Common.N + "'";
		if (labOrgVo.getName() != null) {
			hql += " AND name='" + labOrgVo.getName() + "'";
		}
		LabOrg labOrg = (LabOrg) labOrgDAO.find(hql, 0);
		this.po2Vo(labOrg, labOrgVo);
		return labOrgVo;
	}

	@Override
	public List<LabOrgVo> getUsedLabOrgList(List<LabOrgVo> labOrgVoList)
			throws GlobalException {
		LabOrgVo labOrgVo = new LabOrgVo();
		for (int i = 0; i < labOrgVoList.size(); i++) {
			labOrgVo = labOrgVoList.get(i);
			if (null != labOrgVo) {
				if (null != labOrgVo
						&& !Constants_Common.Y.equals(labOrgVo.getIsUsed())) {
					labOrgVoList.remove(i);
				}
			}
		}
		return labOrgVoList;
	}

	@Override
	public StringBuffer getLabOrgCheckTree(String orgId, String parentId)
			throws GlobalException {
		if (StrUtils.isBlankOrNull(parentId)) {
			parentId = "0";
		}
		return treeSon(parentId, orgId);
	}

	public StringBuffer treeSon(String parentId, String orgId)
			throws GlobalException {
		StringBuffer treeBuffer = new StringBuffer();
		treeBuffer.append("[");
		List<LabOrgVo> listLabOrgVo = getLabOrgListByPId(parentId);
		if (listLabOrgVo.size() > 0) {
			for (LabOrgVo labOrgVo : listLabOrgVo) {
				// 后台角色过滤显示后台功能
				boolean key = getLabOrgListByPId(labOrgVo.getId()).size() > 0 ? true
						: false;
				if (getLabOrgListByPId(labOrgVo.getId()).size() > 0)
					treeBuffer.append("{name:'" + labOrgVo.getName()
							+ "', treeNid:'" + labOrgVo.getId()
							+ "', isParent:" + key + ",checked:"
							+ isHave(orgId, labOrgVo.getId()) + ",children:"
							+ treeSon(orgId, labOrgVo.getId()) + "},");
				else
					treeBuffer.append("{name:'" + labOrgVo.getName()
							+ "', treeNid:'" + labOrgVo.getId()
							+ "', isParent:" + key + ",checked:"
							+ isHave(orgId, labOrgVo.getId()) + "},");
			}
			if (treeBuffer.length() > 0)
				treeBuffer.replace(treeBuffer.length() - 1,
						treeBuffer.length(), "");
		}
		treeBuffer.append("]");
		return treeBuffer;
	}

	public boolean isHave(String orgIds, String orgId) {
		boolean key = false;
		if (!StrUtils.isBlankOrNull(orgIds)) {
			if (orgIds != null && orgIds.split(",").length > 0) {
				for (String id : orgIds.split(",")) {
					if (id.equals(orgId)) {
						key = true;
						break;
					}
				}
			}
		}

		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getLabOrgLevelList(String id) throws GlobalException {
		List<Integer> levelList = new ArrayList<Integer>();
		LabOrg org = labOrgDAO.getLabOrg(id);
		String hql = "FROM LabOrg WHERE isDel='" + Constants_Common.N
				+ "' AND tenantId like '" + org.getTenantId() + "%'";
		List<LabOrg> subList = labOrgDAO.find(hql);
		if (subList != null && subList.size() > 0) {
			String subStr = "";
			for (LabOrg labOrg : subList) {
				if (subStr.length() < labOrg.getTenantId().length()) {
					subStr = labOrg.getTenantId();
				}
			}
			int endLevel = subStr.split("-").length;
			if (endLevel == org.getRank()) {
				levelList.add(org.getRank());
			} else if (endLevel > org.getRank()) {
				for (int i = org.getRank(); i < endLevel; i++) {
					levelList.add(i);
				}
			}
		} else {
			levelList.add(org.getRank());
		}
		return levelList;
	}
}
