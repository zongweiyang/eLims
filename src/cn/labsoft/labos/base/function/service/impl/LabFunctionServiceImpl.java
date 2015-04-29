package cn.labsoft.labos.base.function.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.role.dao.ILabRoleDAO;
import cn.labsoft.labos.base.role.dao.ILabRoleFunDAO;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.base.role.entity.LabRoleFun;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.dao.ILabUserFunDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.i18n.annotation.Translator;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 系统功能Service实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Service("labFunctionService")
public class LabFunctionServiceImpl extends BaseService implements
		ILabFunctionService {
	private ILabFunctionDAO labFunctionDAO;
	private ILabUserFunDAO labUserFunDAO;
	private ILabRoleFunDAO labRoleFunDAO;
	private ILabRoleDAO labRoleDAO;
	private ILabUserDAO labUserDAO;

	/**
	 * 用户管理DAO注入
	 * 
	 * @param labUserDAO
	 */
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}

	/**
	 * 功能管理DAO注入
	 * 
	 * @param labFunctionDAO
	 */
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}

	/**
	 * 用户功能管理DAO注入
	 * 
	 * @param labUserFunDAO
	 */
	@Resource
	public void setLabUserFunDAO(ILabUserFunDAO labUserFunDAO) {
		this.labUserFunDAO = labUserFunDAO;
	}

	/**
	 * 角色功能管理DAO注入
	 * 
	 * @param labRoleFunDAO
	 */
	@Resource
	public void setLabRoleFunDAO(ILabRoleFunDAO labRoleFunDAO) {
		this.labRoleFunDAO = labRoleFunDAO;
	}

	/**
	 * 角色管理DAO注入
	 * 
	 * @param labRoleDAO
	 */
	@Resource
	public void setLabRoleDAO(ILabRoleDAO labRoleDAO) {
		this.labRoleDAO = labRoleDAO;
	}

	@Override
	public LabFunctionVo addLabFunction(LabFunctionVo labFunctionVo)
			throws GlobalException {
		LabFunction labFunction = new LabFunction();
		labFunction.setIsDel(Constants_Common.N);
		BeanUtils.copyProperties(labFunctionVo, labFunction, new String[] {
				"parentFunction", "subFunctions", "isDel" });
		String code = labFunction.getSort() + "";
		if (code.length() < 2) {
			code = "0" + code;
		}
		if (null != labFunctionVo.getParentId()
				&& !"".equals(labFunctionVo.getParentId())) {
			LabFunction parent = (LabFunction) labFunctionDAO.findById(
					LabFunction.class, labFunctionVo.getParentId());
			labFunction.setParentFunction(parent);
			code = parent.getCode() + "_" + code;
		}
		labFunction.setCode(code);
		labFunction.setDataStr("org");
		labFunction.setValStr("0");
		labFunctionDAO.addLabFunction(labFunction);
		return labFunctionVo;
	}

	@Override
	public boolean deleteLabFunction(String[] ids) throws GlobalException {
		return false;
	}

	@Override
	public LabFunctionVo getLabFunction(String id) throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, id);
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		BeanUtils.copyProperties(labFunction, labFunctionVo, new String[] {
				"parentFunction", "subFunctions" });
		if (null != labFunction.getParentFunction()) {
			labFunctionVo.setParentId(labFunction.getParentFunction().getId());
			labFunctionVo.setParentName(labFunction.getParentFunction()
					.getName());
		}
		return labFunctionVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionByURLAndroleIds(String url,
			String[] roleids) throws GlobalException {
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		List<LabRoleFun> poList = new ArrayList<LabRoleFun>();
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND function.url = '" + url + "'");
		StringBuffer roleidsStr = new StringBuffer();
		if (null != roleids && roleids.length > 0) {
			for (String roleid : roleids) {
				roleidsStr.append("'" + roleid.trim() + "',");
			}
			if (roleidsStr.length() > 0) {
				String temp = roleidsStr.substring(0, roleidsStr.length() - 1);
				hql.append(" AND role.id IN(" + temp + ")");
				hql.append(" ORDER BY function.sort ASC");
				poList = labRoleFunDAO.find(hql.toString());
			}
		}
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(roleFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != roleFun.getFunction().getParentFunction()) {
						vo.setParentId(roleFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(roleFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@Override
	public List<LabFunctionVo> getLabFunctionList(LabFunctionVo labFunctionVo)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N + "'");
		hql.append(" AND id<>'0'");
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsProcess())) {
			hql.append(" AND isProcess='" + labFunctionVo.getIsProcess() + "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getParentId())) {
			hql.append(" AND parentFunction.id='" + labFunctionVo.getParentId()
					+ "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getName())) {
			hql.append(" AND name like '%" + labFunctionVo.getName() + "%'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getType())) {
			hql.append(" AND type='" + labFunctionVo.getType() + "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsReport())) {
			hql.append(" AND isReport='" + labFunctionVo.getIsReport() + "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsBarCode())) {
			hql.append(" AND isBarCode='" + labFunctionVo.getIsBarCode() + "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsQuery())) {
			hql.append(" AND isQuery='" + labFunctionVo.getIsQuery() + "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsTemplate())) {
			hql.append(" AND isTemplate = '" + labFunctionVo.getIsTemplate()
					+ "'");
		}
		if (!StrUtils.isBlankOrNull(labFunctionVo.getIsFront())) {
			hql.append(" AND isFront = '" + labFunctionVo.getIsFront() + "'");
		}
		hql.append(" ORDER BY code ASC");
		List<LabFunction> poList = labFunctionDAO.getLabFunctionList(hql
				.toString());
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		if (null != poList && poList.size() > 0) {
			for (LabFunction po : poList) {
				LabFunctionVo vo = new LabFunctionVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "subFunctions" });
				if (null != po.getParentFunction()) {
					vo.setParentId(po.getParentFunction().getId());
					vo.setParentName(po.getParentFunction().getName());
				}
				if (po.getDataStr() == null) {
					vo.setDataStr("org");
				}
				List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
				if (po.getSubFunctions() != null) {
					for (LabFunction firstPo : po.getSubFunctions()) {
						LabFunctionVo firstVo = new LabFunctionVo();
						BeanUtils.copyProperties(firstPo, firstVo,
								new String[] { "" });
						List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
						if (po.getSubFunctions() != null) {
							for (LabFunction secondPo : firstPo
									.getSubFunctions()) {
								LabFunctionVo secondVo = new LabFunctionVo();
								BeanUtils.copyProperties(secondPo, secondVo,
										new String[] { "" });
								secondList.add(secondVo);
							}
						}
						firstVo.setSubFunctionList(firstList);
						firstList.add(firstVo);
					}
				}
				vo.setSubFunctionList(firstList);
				voList.add(vo);
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionList(String userId, String roleId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabUserFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND user.id = '" + userId
						+ "' AND role.id = '" + roleId + "'");
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY function.sort ASC");
		List<LabUserFun> poList = labUserFunDAO.find(hql.toString());
		if (null != poList && poList.size() > 0) {
			for (LabUserFun userFun : poList) {
				if (null != userFun && null != userFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(userFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != userFun.getFunction().getParentFunction()) {
						vo.setParentId(userFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(userFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionList(String userId, String orgId,
			String roleId) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabUserFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND user.id = '" + userId
						+ "' AND role.id = '" + roleId + "' AND org.id = '"
						+ orgId + "'");
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY function.sort ASC");
		List<LabUserFun> poList = labFunctionDAO.find(hql.toString());
		if (null != poList && poList.size() > 0) {
			for (LabUserFun userFun : poList) {
				if (null != userFun && null != userFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(userFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != userFun.getFunction().getParentFunction()) {
						vo.setParentId(userFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(userFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@Override
	public List<LabFunctionVo> getLabFunctionListByPid(String parentId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N + "'");
		if (null != parentId && !"".equals(parentId)) {
			hql.append(" AND parentFunction.id = '" + parentId + "'");
		} else {
			hql.append(" AND parentFunction.id IS NULL");
		}
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY sort ASC");
		List<LabFunction> poList = labFunctionDAO.getLabFunctionList(hql
				.toString());
		if (null != poList && poList.size() > 0) {
			for (LabFunction po : poList) {
				LabFunctionVo vo = new LabFunctionVo();
				BeanUtils.copyProperties(po, vo, new String[] {
						"parentFunction", "subFunctions" });
				if (null != po.getParentFunction()) {
					vo.setParentId(po.getParentFunction().getId());
					vo.setParentName(po.getParentFunction().getName());
				}
				voList.add(vo);
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionListByRoleId(String roleId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND role.id = '" + roleId + "'");
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY function.sort ASC");
		List<LabRoleFun> poList = labRoleFunDAO.find(hql.toString());
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(roleFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != roleFun.getFunction().getParentFunction()) {
						vo.setParentId(roleFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(roleFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionListByRoleIds(String[] roleIds)
			throws GlobalException {
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		List<LabRoleFun> poList = new ArrayList<LabRoleFun>();
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' ");
		StringBuffer roleidsStr = new StringBuffer();
		if (null != roleIds && roleIds.length > 0) {
			for (String roleid : roleIds) {
				roleidsStr.append("'" + roleid.trim() + "',");
			}
			if (roleidsStr.length() > 0) {
				String temp = roleidsStr.substring(0, roleidsStr.length() - 1);
				hql.append("AND role.id IN(" + temp + ")");
				hql.append(" ORDER BY function.sort ASC");
				poList = labRoleFunDAO.find(hql.toString());
			}
		}
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(roleFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != roleFun.getFunction().getParentFunction()) {
						vo.setParentId(roleFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(roleFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	public String getLabFunctionIdsByRoleIds(String[] roleIds)
			throws GlobalException {
		String ids = "";
		List<LabRoleFun> poList = new ArrayList<LabRoleFun>();
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' ");
		StringBuffer roleidsStr = new StringBuffer();
		if (null != roleIds && roleIds.length > 0) {
			for (String roleid : roleIds) {
				roleidsStr.append("'" + roleid.trim() + "',");
			}
			if (roleidsStr.length() > 0) {
				String temp = roleidsStr.substring(0, roleidsStr.length() - 1);
				hql.append("AND role.id IN(" + temp + ")");
				hql.append(" ORDER BY function.sort ASC");
				poList = labRoleFunDAO.find(hql.toString());
			}
		}
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					ids += roleFun.getFunction().getId();
					ids += ",";
				}
			}
		}
		return ids;
	}

	@SuppressWarnings("unchecked")
	public List<LabFunctionVo> getLabFunctionListByRoleIds(String[] roleIds,
			String funType) throws GlobalException {
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		List<LabRoleFun> poList = new ArrayList<LabRoleFun>();
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND function.type = '" + funType
						+ "'");
		StringBuffer roleidsStr = new StringBuffer();
		if (null != roleIds && roleIds.length > 0) {
			for (String roleid : roleIds) {
				roleidsStr.append("'" + roleid.trim() + "',");
			}
			if (roleidsStr.length() > 0) {
				String temp = roleidsStr.substring(0, roleidsStr.length() - 1);
				hql.append("AND role.id IN(" + temp + ")");
				hql.append(" ORDER BY function.sort ASC");
				poList = labRoleFunDAO.find(hql.toString());
			}
		}
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(roleFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != roleFun.getFunction().getParentFunction()) {
						vo.setParentId(roleFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(roleFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	public List<LabFunctionVo> getLabFunctionListByUserIdAndType(String userId,
			String funType) throws GlobalException {
		List<LabFunctionVo> funVoList = new ArrayList<LabFunctionVo>();
		if (userId.equals("0")) {
			String hql = "FROM LabFunction WHERE isDel='" + Constants_Common.N
					+ "' AND isDisplay='" + Constants_Common.Y + "'";
			if (!StrUtils.isBlankOrNull(funType)) {
				hql += " AND type='" + funType + "' ";
			}
			hql += " ORDER BY sort ASC";
			List<LabFunction> funList = labFunctionDAO.getLabFunctionList(hql);
			if (null != funList) {
				for (LabFunction po : funList) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					funVoList.add(vo);
				}
			}
		} else {
			String hql = "FROM LabUserFun WHERE function.isDel='" + Constants_Common.N
					+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
			if (!StrUtils.isBlankOrNull(userId)) {
				hql += " AND user.id='" + userId + "' ";
			}
			if (!StrUtils.isBlankOrNull(funType)) {
				hql += " AND function.type='" + funType + "' ";
			}
			hql += " GROUP BY function.id";
			hql += " ORDER BY function.sort ASC";
			List<LabUserFun> funList = labFunctionDAO.find(hql);
			if (null != funList) {
				for (LabUserFun userFun : funList) {
					LabFunction po = userFun.getFunction();
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					funVoList.add(vo);
				}
			}
		}
		return funVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionListByRoleIdsAndPid(
			String[] roleIds, String pid) throws GlobalException {
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		List<LabRoleFun> poList = new ArrayList<LabRoleFun>();
		StringBuffer hql = new StringBuffer(
				"FROM LabRoleFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND function.parentFunction.id = '"
						+ pid + "'");
		StringBuffer roleidsStr = new StringBuffer();
		if (null != roleIds && roleIds.length > 0) {
			for (String roleid : roleIds) {
				roleidsStr.append("'" + roleid.trim() + "',");
			}
			if (roleidsStr.length() > 0) {
				String temp = roleidsStr.substring(0, roleidsStr.length() - 1);
				hql.append(" AND role.id IN(" + temp + ")");
				hql.append(" ORDER BY function.sort ASC");
				poList = labRoleFunDAO.find(hql.toString());
			}
		}
		if (null != poList && poList.size() > 0) {
			for (LabRoleFun roleFun : poList) {
				if (null != roleFun && null != roleFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(roleFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != roleFun.getFunction().getParentFunction()) {
						vo.setParentId(roleFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(roleFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionListByUserId(String userId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabUserFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND user.id = '" + userId + "'");
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY function.sort ASC");
		List<LabUserFun> poList = labUserFunDAO.find(hql.toString());
		if (null != poList && poList.size() > 0) {
			for (LabUserFun userFun : poList) {
				if (null != userFun && null != userFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(userFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != userFun.getFunction().getParentFunction()) {
						vo.setParentId(userFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(userFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabFunctionListByUserId(String userId,
			String orgId) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabUserFun WHERE 1=1 AND function.isDel = '"
						+ Constants_Common.N + "' AND user.id = '" + userId
						+ "' AND org.id = '" + orgId + "'");
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		hql.append(" ORDER BY function.sort ASC");
		List<LabUserFun> poList = labUserFunDAO.find(hql.toString());
		if (null != poList && poList.size() > 0) {
			for (LabUserFun userFun : poList) {
				if (null != userFun && null != userFun.getFunction()) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(userFun.getFunction(), vo,
							new String[] { "parentFunction", "subFunctions" });
					if (null != userFun.getFunction().getParentFunction()) {
						vo.setParentId(userFun.getFunction()
								.getParentFunction().getId());
						vo.setParentName(userFun.getFunction()
								.getParentFunction().getName());
					}
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@Override
	public StringBuffer getLabFunctionListTree(String parentId)
			throws GlobalException {
		StringBuffer tree = new StringBuffer();
		if (null != parentId && !"".equals(parentId)) {
			tree = new StringBuffer(subTrees(parentId));
		} else {
			LabFunctionVo root = new LabFunctionVo();
			List<LabFunctionVo> list = this.getLabFunctionListByPid(null);
			if (null != list && list.size() > 0) {
				root = list.get(0);
			}

			tree.append("[{name:'" + root.getName() + "', treeNid:'"
					+ root.getId() + "', isParent:true,open:true,nodes:"
					+ subTrees(root.getId()) + "}]");
		}
		return tree;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabFunctionPR(LabFunctionVo labFunctionVo,
			PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N + "'");
		if(null != labFunctionVo.getParentId()&&"0".equals(labFunctionVo.getParentId())){
			labFunctionVo.setParentId(null);
		}
		if (null != labFunctionVo.getParentId()
				&& !"".equals(labFunctionVo.getParentId())) {
			hql.append(" AND( parentFunction.id = '"
					+ labFunctionVo.getParentId()
					+ "' or  parentFunction.parentFunction.id = '"
					+ labFunctionVo.getParentId() + "')");
		} else {
			hql.append(" AND parentFunction.id = '0'");
		}
		if (null != labFunctionVo.getName()
				&& !"".equals(labFunctionVo.getName())) {
			hql.append(" AND name LIKE '%" + labFunctionVo.getName() + "%'");
		}
		hql.append(" ORDER BY code ASC");
		pageResult = labFunctionDAO
				.getLabFunctionPR(hql.toString(), pageResult);
		List<LabFunction> poList = pageResult.getResultList();
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		if (null != poList && poList.size() > 0) {
			for (LabFunction po : poList) {
				LabFunctionVo vo = new LabFunctionVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "subFunctions" });
				if (null != po.getParentFunction()) {
					vo.setParentId(po.getParentFunction().getId());
					vo.setParentName(po.getParentFunction().getName());
				}
				List<LabFunction> list = labFunctionDAO
						.getLabFunctionList("FROM LabFunction WHERE 1=1 AND isDel = '"
								+ Constants_Common.N
								+ "' AND parentFunction.id = '"
								+ po.getId() + "'");
				if (null == list || list.size() == 0) {
					vo.setIsHaveSub("N");
				} else {
					vo.setIsHaveSub("Y");
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getMainmenuByUserIdAndType(String userId,
			String type, String orgId) throws GlobalException {

		List<LabFunctionVo> funVoList = new ArrayList<LabFunctionVo>();
		if (userId.equals("0")) {
			String hql = "FROM LabFunction WHERE isDel='" + Constants_Common.N
					+ "' AND isDisplay='" + Constants_Common.Y + "'";
			hql += " AND parentFunction.id ='0'";
			if (type.equals(Constants_Common.BACK)) {
				hql += " AND isBack = 'Y'";
			} else if (type.equals(Constants_Common.FRONT)) {
				hql += " AND isFront = 'Y'";
			}
			hql += " ORDER BY sort ASC";
			List<LabFunction> funList = labFunctionDAO.getLabFunctionList(hql);
			if (null != funList) {
				for (LabFunction po : funList) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					String url = vo.getUrl();
					if (null != url) {
						if (!"".equals(url) && !url.startsWith("/")) {
							url = "/" + url;
						}
						if (url.contains("?")) {
							url = url + "&funId=" + vo.getId();
						} else {
							url = url + "?funId=" + vo.getId();
						}
						String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
						if (!url.contains(urlPrefixStr)) {
							url = "/" + urlPrefixStr + url;
						}
						url = url.replace("&", "^");
					}
					vo.setUrl(url);
					List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
					if (po.getSubFunctions() != null) {
						for (LabFunction firstPo : po.getSubFunctions()) {
							if (null == firstPo
									|| firstPo.getIsDel().equals(Constants_Common.Y)
									|| firstPo.getIsDisplay().equals(
											Constants_Common.N)) {
								continue;
							}
							LabFunctionVo firstVo = new LabFunctionVo();
							if (firstPo.getIsDel().equals(Constants_Common.Y)) {
								continue;
							}
							BeanUtils.copyProperties(firstPo, firstVo,
									new String[] { "createUserId" });
							String url1 = firstVo.getUrl();
							if (null != url1) {
								if (!"".equals(url1) && !url1.startsWith("/")) {
									url1 = "/" + url1;
								}
								if (url1.contains("?")) {
									url1 = url1 + "&funId=" + firstVo.getId();
								} else {
									url1 = url1 + "?funId=" + firstVo.getId();
								}
								String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
								if (!url1.contains(urlPrefixStr)) {
									url1 = "/" + urlPrefixStr + url1;
								}
								url1 = url1.replace("&", "^");
							}
							firstVo.setUrl(url1);
							List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
							if (firstPo.getSubFunctions() != null) {
								for (LabFunction secondPo : firstPo
										.getSubFunctions()) {
									if (secondPo.getIsDel().equals(Constants_Common.Y)) {
										continue;
									}
									LabFunctionVo secondVo = new LabFunctionVo();
									BeanUtils.copyProperties(secondPo,
											secondVo,
											new String[] { "createUserId" });
									String url2 = secondVo.getUrl();
									if (null != url2) {
										if (!"".equals(url2)
												&& !url2.startsWith("/")) {
											url2 = "/" + url2;
										}
										if (url2.contains("?")) {
											url2 = url2 + "&funId="
													+ secondVo.getId();
										} else {
											url2 = url2 + "?funId="
													+ secondVo.getId();
										}
										String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
										if (!url2.contains(urlPrefixStr)) {
											url2 = "/" + urlPrefixStr + url2;
										}
										url2 = url2.replace("&", "^");
									}
									secondVo.setUrl(url2);
									secondList.add(secondVo);
								}
							}
							firstVo.setSubFunctionList(secondList);
							firstList.add(firstVo);
						}
					}
					vo.setSubFunctionList(firstList);
					funVoList.add(vo);
				}
			}
		} else {
			String hql = "FROM LabUserFun WHERE function.isDel='" + Constants_Common.N
					+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
			hql += " AND user.id='" + userId + "' ";
			if (type.equals(Constants_Common.BACK)) {
				hql += " AND function.isBack = 'Y'";
			} else if (type.equals(Constants_Common.FRONT)) {
				hql += " AND function.isFront = 'Y'";
			}
			if (orgId != null && !orgId.equals("")) {
				hql += " AND org.id='" + orgId + "' ";
			}
			hql += " AND function.parentFunction.id='0'";
			hql += " GROUP BY function.id ";
			hql += " ORDER BY function.sort ASC";
			List<LabUserFun> funList = labUserFunDAO.find(hql);
			if (null != funList) {
				for (LabUserFun userFunpo : funList) {
					LabFunction po = userFunpo.getFunction();
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					String url = vo.getUrl();
					if (null != url) {
						if (!"".equals(url) && !url.startsWith("/")) {
							url = "/" + url;
						}
						if (url.contains("?")) {
							url = url + "&funId=" + vo.getId();
						} else {
							url = url + "?funId=" + vo.getId();
						}
						String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
						if (!url.contains(urlPrefixStr)) {
							url = "/" + urlPrefixStr + url;
						}
						url = url.replace("&", "^");
					}
					vo.setUrl(url);
					List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
					List<LabUserFun> subUserFunList = labUserFunDAO
							.getLabUserFunListByUserIdAndPfunId(userId, po
									.getId(), orgId);
					if (subUserFunList != null) {
						for (LabUserFun subUserFun : subUserFunList) {
							LabFunction firstPo = subUserFun.getFunction();
							LabFunctionVo firstVo = new LabFunctionVo();
							BeanUtils.copyProperties(firstPo, firstVo,
									new String[] { "createUserId" });
							String url1 = firstVo.getUrl();
							if (null != url1) {
								if (!"".equals(url1) && !url1.startsWith("/")) {
									url1 = "/" + url1;
								}
								if (url1.contains("?")) {
									url1 = url1 + "&funId=" + firstVo.getId();
								} else {
									url1 = url1 + "?funId=" + firstVo.getId();
								}
								String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
								if (!url1.contains(urlPrefixStr)) {
									url1 = "/" + urlPrefixStr + url1;
								}
								url1 = url1.replace("&", "^");
							}
							firstVo.setUrl(url1);
							List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
							List<LabUserFun> secondUserFunList = labUserFunDAO
									.getLabUserFunListByUserIdAndPfunId(userId,
											firstPo.getId(), orgId);
							if (secondUserFunList != null) {
								for (LabUserFun sendUserFun : secondUserFunList) {
									LabFunction secondPo = sendUserFun
											.getFunction();
									LabFunctionVo secondVo = new LabFunctionVo();
									BeanUtils.copyProperties(secondPo,
											secondVo,
											new String[] { "createUserId" });
									String url2 = secondVo.getUrl();
									if (null != url2) {
										if (!"".equals(url2)
												&& !url2.startsWith("/")) {
											url2 = "/" + url2;
										}
										if (url2.contains("?")) {
											url2 = url2 + "&funId="
													+ secondVo.getId();
										} else {
											url2 = url2 + "?funId="
													+ secondVo.getId();
										}
										String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
										if (!url2.contains(urlPrefixStr)) {
											url2 = "/" + urlPrefixStr + url2;
										}
										url2 = url2.replace("&", "^");
									}
									secondVo.setUrl(url2);
									secondList.add(secondVo);
								}
							}
							firstVo.setSubFunctionList(secondList);
							firstList.add(firstVo);
						}
					}
					vo.setSubFunctionList(firstList);
					funVoList.add(vo);
				}
			}
		}
		return funVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getMainmenuByUserIdAndMenuType(String userId,
			String menuType, String type) throws GlobalException {
		List<LabFunctionVo> funVoList = new ArrayList<LabFunctionVo>();
		if (userId.equals("0")) {
			String hql = "FROM LabFunction WHERE isDel='" + Constants_Common.N
					+ "' AND isDisplay='" + Constants_Common.Y + "'";
			hql += " AND parentFunction.id ='0'";

			if (!StrUtils.isBlankOrNull(type)) {
				if (type.equals(Constants_Common.BACK)) {
					hql += " AND function.isBack = 'Y'";
				} else if (type.equals(Constants_Common.FRONT)) {
					hql += " AND function.isFront = 'Y'";
				}
			}
			hql += " ORDER BY sort ASC";
			List<LabFunction> funList = labFunctionDAO.getLabFunctionList(hql);
			if (null != funList) {
				for (LabFunction po : funList) {
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					String url = vo.getUrl();
					List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
					List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
					if (po.getSubFunctions() != null) {
						for (LabFunction firstPo : po.getSubFunctions()) {
							if (null == firstPo
									|| firstPo.getIsDel().equals(Constants_Common.Y)
									|| firstPo.getIsDisplay().equals(
											Constants_Common.N)) {
								continue;
							}
							LabFunctionVo firstVo = new LabFunctionVo();
							if (firstPo.getIsDel().equals(Constants_Common.Y)) {
								continue;
							}
							BeanUtils.copyProperties(firstPo, firstVo,
									new String[] { "" });
							String url1 = firstVo.getUrl();
							if (!StrUtils.isBlankOrNull(url1)) {
								if (!"".equals(url1) && !url1.startsWith("/")) {
									url1 = "/" + url1;
								}
								if (url1.contains("?")) {
									url1 = url1 + "&funId=" + firstVo.getId();
								} else {
									url1 = url1 + "?funId=" + firstVo.getId();
								}
								String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
								if (!url1.contains(urlPrefixStr)) {
									url1 = "/" + urlPrefixStr + url1;
								}
								url1 = url1.replace("&", "^");
							}
							firstVo.setUrl(url1);
							if (firstPo.getSubFunctions() != null) {
								for (LabFunction secondPo : firstPo
										.getSubFunctions()) {
									if (secondPo.getIsDel().equals(Constants_Common.Y)) {
										continue;
									}
									LabFunctionVo secondVo = new LabFunctionVo();
									BeanUtils.copyProperties(secondPo,
											secondVo, new String[] { "" });
									String url2 = secondVo.getUrl();
									if (!StrUtils.isBlankOrNull(url2)) {
										if (!"".equals(url2)
												&& !url2.startsWith("/")) {
											url2 = "/" + url2;
										}
										if (url2.contains("?")) {
											url2 = url2 + "&funId="
													+ secondVo.getId();
										} else {
											url2 = url2 + "?funId="
													+ secondVo.getId();
										}
										String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
										if (!url2.contains(urlPrefixStr)) {
											url2 = "/" + urlPrefixStr + url2;
										}
										url2 = url2.replace("&", "^");
									}
									secondVo.setUrl(url2);
									secondList.add(secondVo);
								}
							}
							String imageUrl = firstVo.getImageUrl();
							if (StrUtils.isBlankOrNull(imageUrl)) {
								imageUrl = "iconPng1.jpg";
							}
							firstVo.setImageUrl(imageUrl);
							firstVo.setSubFunctionList(secondList);
							firstList.add(firstVo);
						}
					}
					if (!StrUtils.isBlankOrNull(url)) {
						if (!"".equals(url) && !url.startsWith("/")) {
							url = "/" + url;
						}
						if (url.contains("?")) {
							url = url + "&funId=" + vo.getId();
						} else {
							url = url + "?funId=" + vo.getId();
						}
						String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
						if (!url.contains(urlPrefixStr)) {
							url = "/" + urlPrefixStr + url;
						}
						url = url.replace("&", "^");
					} else {
						if (!StrUtils.isNull(menuType)
								&& menuType.equals(Constants_Common.MENU_B)) {// 图标菜单形式
							url = "/coreextend/extend/listLabFunction4iconMenu.action?labFunctionVo.parentId="
									+ po.getId()
									+ "&labFunctionVo.type="
									+ po.getType();
						}
					}
					vo.setUrl(url);
					vo.setSubFunctionList(firstList);

					String imageUrl = vo.getImageUrl();
					if (StrUtils.isBlankOrNull(imageUrl)) {
						imageUrl = "iconPng1.jpg";
					}
					vo.setImageUrl(imageUrl);
					funVoList.add(vo);
				}
			}
		} else {
			String hql = "FROM LabUserFun WHERE function.isDel='" + Constants_Common.N
					+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
			hql += " AND user.id='" + userId + "' ";
			hql += " AND function.parentFunction.id='0'";

			if (!StrUtils.isBlankOrNull(type)) {
				if (type.equals(Constants_Common.BACK)) {
					hql += " AND function.isBack = 'Y'";
				} else if (type.equals(Constants_Common.FRONT)) {
					hql += " AND function.isFront = 'Y'";
				}
			}
			hql += " GROUP BY function.id ";
			hql += " ORDER BY function.sort ASC";
			List<LabUserFun> funList = labUserFunDAO.find(hql);
			if (null != funList) {
				for (LabUserFun userFunpo : funList) {
					LabFunction po = userFunpo.getFunction();
					LabFunctionVo vo = new LabFunctionVo();
					BeanUtils.copyProperties(po, vo, new String[] { "tenantId",
							"createUserId", "createTime" });
					vo.setId(po.getId());
					vo.setName(po.getName());
					if (po.getParentFunction() != null) {
						vo.setParentId(po.getParentFunction().getId());
						vo.setParentName(po.getParentFunction().getName());
					}
					String url = vo.getUrl();
					List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
					List<LabUserFun> subUserFunList = labUserFunDAO
							.getLabUserFunListByUserIdAndPfunId(userId, po
									.getId());
					if (subUserFunList != null) {
						for (LabUserFun subUserFun : subUserFunList) {
							LabFunction firstPo = subUserFun.getFunction();
							LabFunctionVo firstVo = new LabFunctionVo();
							BeanUtils.copyProperties(firstPo, firstVo,
									new String[] { "tenantId", "createUserId",
											"createTime" });
							String url1 = firstVo.getUrl();
							if (!StrUtils.isBlankOrNull(url1)) {
								if (!"".equals(url1) && !url1.startsWith("/")) {
									url1 = "/" + url1;
								}
								if (url1.contains("?")) {
									url1 = url1 + "&funId=" + firstVo.getId();
								} else {
									url1 = url1 + "?funId=" + firstVo.getId();
								}
								String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
								if (!url1.contains(urlPrefixStr)) {
									url1 = "/" + urlPrefixStr + url1;
								}
								url1 = url1.replace("&", "^");
							}
							firstVo.setUrl(url1);
							List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
							List<LabUserFun> secondUserFunList = labUserFunDAO
									.getLabUserFunListByUserIdAndPfunId(userId,
											firstPo.getId());
							if (secondUserFunList != null) {
								for (LabUserFun sendUserFun : secondUserFunList) {
									LabFunction secondPo = sendUserFun
											.getFunction();
									LabFunctionVo secondVo = new LabFunctionVo();
									BeanUtils.copyProperties(secondPo,
											secondVo, new String[] {
													"tenantId", "createUserId",
													"createTime" });
									String url2 = secondVo.getUrl();
									if (!StrUtils.isBlankOrNull(url2)) {
										if (!"".equals(url2)
												&& !url2.startsWith("/")) {
											url2 = "/" + url2;
										}
										if (url2.contains("?")) {
											url2 = url2 + "&funId="
													+ secondVo.getId();
										} else {
											url2 = url2 + "?funId="
													+ secondVo.getId();
										}
										String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
										if (!url2.contains(urlPrefixStr)) {
											url2 = "/" + urlPrefixStr + url2;
										}
										url2 = url2.replace("&", "^");
									}
									secondVo.setUrl(url2);
									secondList.add(secondVo);
								}
							}
							String imageUrl = firstVo.getImageUrl();
							if (StrUtils.isBlankOrNull(imageUrl)) {
								imageUrl = "iconJpg.png";
							}
							firstVo.setImageUrl(imageUrl);
							firstVo.setSubFunctionList(secondList);
							firstList.add(firstVo);
						}
					}
					if (!StrUtils.isBlankOrNull(url) && url.trim().length() > 1) {
						if (!"".equals(url) && !url.startsWith("/")) {
							url = "/" + url;
						}
						if (url.contains("?")) {
							url = url + "&funId=" + vo.getId();
						} else {
							url = url + "?funId=" + vo.getId();
						}
						String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
						if (!url.contains(urlPrefixStr)) {
							url = "/" + urlPrefixStr + url;
						}
						url = url.replace("&", "^");
					} else {
						if (!StrUtils.isNull(menuType)
								&& menuType.equals(Constants_Common.MENU_B)) {// 图标菜单形式
							url = "/coreextend/extend/listLabFunction4iconMenu.action?labFunctionVo.parentId="
									+ po.getId()
									+ "&labFunctionVo.type="
									+ po.getType();
						}
					}
					vo.setUrl(url);
					vo.setSubFunctionList(firstList);
					String imageUrl = vo.getImageUrl();
					if (StrUtils.isBlankOrNull(imageUrl)) {
						imageUrl = "iconJpg.png";
					}
					vo.setImageUrl(imageUrl);
					funVoList.add(vo);
				}
			}
		}
		return funVoList;
	}

	@Override
	public Integer getMaxSort(String parentId) throws GlobalException {
		int sort = -1;
		String sql = "SELECT MAX(sort) FROM LabFunction po where po.parentFunction.id='"
				+ parentId + "' and po.isDel='" + Constants_Common.N + "'";
		if (null != labFunctionDAO.getColumnMaxValue(sql)) {
			sort = (Integer) labFunctionDAO.getColumnMaxValue(sql);
		}
		return sort;
	}

	@Override
	public boolean isUse4Function(String id) throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, id.trim());
		if (null != labFunction && null != labFunction.getIsDel()
				&& Constants_Common.N.equals(labFunction.getIsDel())) {
			if (null != labFunction.getIsDisplay()
					&& !"".equals(labFunction.getIsDisplay())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isCouldDel(String id) throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, id.trim());
		if (null != labFunction && null != labFunction.getSubFunctions()
				&& labFunction.getSubFunctions().size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public StringBuffer subTrees(String funId) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N
						+ "' AND parentFunction.id = '" + funId + "'");
		hql.append(" ORDER BY sort ASC");
		List<LabFunction> poList = labFunctionDAO.getLabFunctionList(hql
				.toString());
		StringBuffer sb = new StringBuffer("[");
		if (null != poList && poList.size() > 0) {
			for (LabFunction labFunction : poList) {
				boolean b = false;
				if (labFunction.getType().equals("0")) {
					b = true;
				}
				String url = labFunction.getUrl();
				if (!StrUtils.isBlankOrNull(url)) {
					if (!"".equals(url) && !url.startsWith("/")) {
						url = "/" + url;
					}
					if (url.contains("?")) {
						url = url + "&funId=" + labFunction.getId();
					} else {
						url = url + "?funId=" + labFunction.getId();
					}
					String urlPrefixStr = "/admin/progress_bar.jsp?url=";
					if (!url.contains(urlPrefixStr)) {
						url = urlPrefixStr + url;
					}
					url = url.replace("&", "^");
				}
				sb.append("{name:'" + labFunction.getName() + "', treeNid:'"
						+ labFunction.getId() + "', isParent:" + b + ", curl:'"
						+ url + "'},");
			}
		}
		if (sb.length() > 1) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}
		sb.append("]");
		return sb;
	}

	@Override
	public LabFunctionVo updateLabFunction(LabFunctionVo labFunctionVo)
			throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, labFunctionVo.getId());
		BeanUtils.copyProperties(labFunctionVo, labFunction, new String[] {
				"parentFunction", "subFunctions", "isDel", "tenantId",
				"createUserId", "createTime", "dataStr", "valStr" });
		String code = labFunction.getSort() + "";
		if (code.length() < 2) {
			code = "0" + code;
		}
		if (null != labFunctionVo.getParentId()
				&& !"".equals(labFunctionVo.getParentId())) {
			LabFunction parent = (LabFunction) labFunctionDAO.findById(
					LabFunction.class, labFunctionVo.getParentId());
			labFunction.setParentFunction(parent);
			code = parent.getCode() + "_" + code;
		}
		labFunction.setCode(code);
		labFunctionDAO.updateLabFunction(labFunction);
		this.updateSubFunction(labFunction);
		return labFunctionVo;
	}

	public void updateSubFunction(LabFunction labFunction)
			throws GlobalException {
		String hql = "FROM LabFunction WHERE isDel='" + Constants_Common.N + "'";
		hql += " AND parentFunction.id='" + labFunction.getId() + "'";
		hql += " ORDER BY sort ASC";
		List<LabFunction> funList = labFunctionDAO.getLabFunctionList(hql);
		if (funList != null && funList.size() > 0) {
			for (LabFunction subFun : funList) {
				String code = labFunction.getCode();
				String subCode = subFun.getSort() + "";
				if (subCode.length() < 2) {
					subCode = "0" + subCode;
				}
				code = code + "_" + subCode;
				subFun.setCode(code);
				labFunctionDAO.updateLabFunction(labFunction);
				this.updateSubFunction(subFun);
			}
		}
	}

	@Override
	public boolean updateLabFunction4Del(String[] ids) throws GlobalException {
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					LabFunction labFunction = (LabFunction) labFunctionDAO
							.findById(LabFunction.class, id.trim());
					labFunction.setIsDel("Y");
					labFunctionDAO.updateLabFunction(labFunction);
				}
			}
			return true;
		} catch (Exception e) {
			Log4J.error("删除功能对象出错");
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isExist4Name(String name, String parentId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N
						+ "' AND name = '" + name
						+ "' AND parentFunction.id = '" + parentId + "'");
		List<LabFunction> list = labFunctionDAO.find(hql.toString());
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isExistFunCode(String code, String id)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Common.N
						+ "' AND wfcode = '" + code + "' AND id <> '" + id
						+ "'");
		List<LabFunction> list = labFunctionDAO.find(hql.toString());
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public StringBuffer getLabFunctionTreeRecursion(String parentId)
			throws GlobalException {
		StringBuffer tree = new StringBuffer("[]");
		return tree;
	}

	@Override
	public StringBuffer getLabFunctionCheckTree(String roleId, String parentId)
			throws GlobalException {
		// TODO Auto-generated method stub
		String funIds = getLabFunctionIdsByRoleIds(new String[] { roleId });
		if (StrUtils.isBlankOrNull(parentId))
			parentId = "";
		return treeSon(funIds, parentId, roleId);
	}

	public StringBuffer treeSon(String funIds, String funPid, String roleId)
			throws GlobalException {
		LabRole role = labRoleDAO.getLabRole(roleId);
		StringBuffer treeBuffer = new StringBuffer();
		treeBuffer.append("[");
		List<LabFunctionVo> listLabFunctionVo = getLabFunctionListByPid(funPid);
		if (listLabFunctionVo.size() > 0) {
			for (LabFunctionVo labFunctionVo : listLabFunctionVo) {
				// 后台角色过滤显示后台功能
				if (role.getShow() != null && role.getShow().equals("BACK")
						&& labFunctionVo.getIsBack() != null
						&& labFunctionVo.getIsBack().equals(Constants_Common.Y)) {
					boolean key = getLabFunctionListByPid(labFunctionVo.getId())
							.size() > 0 ? true : false;
					if (getLabFunctionListByPid(labFunctionVo.getId()).size() > 0)
						treeBuffer
								.append("{name:'"
										+ labFunctionVo.getName()
										+ "', treeNid:'"
										+ labFunctionVo.getId()
										+ "', isParent:"
										+ key
										+ ",checked:"
										+ isHave(funIds, labFunctionVo.getId())
										+ ",children:"
										+ treeSon(funIds,
												labFunctionVo.getId(), roleId)
												.toString() + "},");
					else
						treeBuffer.append("{name:'" + labFunctionVo.getName()
								+ "', treeNid:'" + labFunctionVo.getId()
								+ "', isParent:" + key + ",checked:"
								+ isHave(funIds, labFunctionVo.getId()) + "},");
				} else if ((role.getShow() == null || role.getShow().equals(
						"FRONT"))
						&& labFunctionVo.getIsFront() != null
						&& labFunctionVo.getIsFront().equals(Constants_Common.Y)) {
					boolean key = getLabFunctionListByPid(labFunctionVo.getId())
							.size() > 0 ? true : false;
					if (getLabFunctionListByPid(labFunctionVo.getId()).size() > 0)
						treeBuffer
								.append("{name:'"
										+ labFunctionVo.getName()
										+ "', treeNid:'"
										+ labFunctionVo.getId()
										+ "', isParent:"
										+ key
										+ ",checked:"
										+ isHave(funIds, labFunctionVo.getId())
										+ ",children:"
										+ treeSon(funIds,
												labFunctionVo.getId(), roleId)
												.toString() + "},");
					else
						treeBuffer.append("{name:'" + labFunctionVo.getName()
								+ "', treeNid:'" + labFunctionVo.getId()
								+ "', isParent:" + key + ",checked:"
								+ isHave(funIds, labFunctionVo.getId()) + "},");
				}
			}
			if (treeBuffer != null) {
				if (treeBuffer.length() > 0) {
					treeBuffer.replace(treeBuffer.length() - 1, treeBuffer
							.length(), "");
				}
			}
		}
		treeBuffer.append("]");
		return treeBuffer;
	}

	public boolean isHave(String funIds, String funId) {
		boolean key = false;
		if (!StrUtils.isBlankOrNull(funIds)) {
			if (funIds != null && funIds.split(",").length > 0) {
				for (String id : funIds.split(",")) {
					if (id.equals(funId)) {
						key = true;
						break;
					}
				}
			}
		}

		return key;
	}

	@Override
	public StringBuffer subTrees(String funId, String expectId)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabFunction WHERE 1=1 AND id <> '" + expectId
						+ "' AND isDel = '" + Constants_Common.N
						+ "' AND parentFunction.id = '" + funId
						+ "' AND type = '0'");

		hql.append(" ORDER BY sort ASC");
		List<LabFunction> poList = labFunctionDAO.getLabFunctionList(hql
				.toString());
		StringBuffer sb = new StringBuffer("[");
		if (null != poList && poList.size() > 0) {
			for (LabFunction labFunction : poList) {
				boolean b = false;
				if (labFunction.getSubFunctions() != null
						&& labFunction.getSubFunctions().size() > 0) {
					b = true;
				}
				sb.append("{name:'" + labFunction.getName() + "', treeNid:'"
						+ labFunction.getId() + "', isParent:" + b
						+ ",checked:false},");
			}
		}
		if (sb.length() > 1) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}
		sb.append("]");
		return sb;
	}

	@Override
	public StringBuffer getLabFunctionDirectoryExpectSelfAndSubs(String funId,
			String id) throws GlobalException {

		StringBuffer tree = new StringBuffer();
		if (null != funId && !"".equals(funId)) {
			tree = new StringBuffer(subTrees(funId, id));
		} else {
			LabFunctionVo root = new LabFunctionVo();
			List<LabFunctionVo> list = this.getLabFunctionListByPid(null);
			if (null != list && list.size() > 0) {
				root = list.get(0);
			}
			tree.append("[{name:'" + root.getName() + "', treeNid:'"
					+ root.getId() + "', isParent:true," + "checked:false,"
					+ "open:true,nodes:" + subTrees(root.getId(), id) + "}]");
		}
		return tree;
	}

	@Override
	public LabFunctionVo updateLabFunctionParent(LabFunctionVo labFunctionVo)
			throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, labFunctionVo.getId().trim());
		String code = labFunction.getSort() + "";
		if (code.length() < 2) {
			code = "0" + code;
		}
		LabFunction parent = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, labFunctionVo.getParentId().trim());
		labFunction.setParentFunction(parent);
		code = parent.getCode() + "_" + code;
		labFunction.setCode(code);
		labFunctionDAO.updateLabFunction(labFunction);
		this.updateSubFunction(labFunction);
		return labFunctionVo;
	}

	@Override
	public List<LabFunctionVo> getLabFunctionListByUserIdAndType(String userId,
			String funType, String pid) throws GlobalException {
		List<LabUserFun> userFunList = labUserFunDAO.getLabUserFunList(userId,
				funType, pid);
		List<LabFunctionVo> voList = new ArrayList<LabFunctionVo>();
		if (null != userFunList) {
			for (LabUserFun labUserFun : userFunList) {
				LabFunction funpo = labUserFun.getFunction();
				LabFunctionVo funVo = new LabFunctionVo();
				BeanUtils.copyProperties(funpo, funVo, new String[] {});
				voList.add(funVo);
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabFunctionVo getLabFunctionByURL(String url) throws GlobalException {
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		String hql = "FROM LabFunction WHERE isDel = '" + Constants_Common.N
				+ "' AND url like '%" + url + "%'";
		List<LabFunction> labFunctionList = labFunctionDAO.find(hql);
		if (null != labFunctionList && 0 < labFunctionList.size()) {
			BeanUtils.copyProperties(labFunctionList.get(0), labFunctionVo,
					new String[] {});
		}
		return labFunctionVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getLabWFFunctionListByUserId(String userId,
			String orgId) throws GlobalException {
		// 版本1
		// String sql=" SELECT fun.id,fun.name,fun.url,case when ins.parent_id
		// is NULL then count(fun.ID) else count(distinct(ins.group_by_str))
		// end";
		// sql += " from lab_sys_function fun";
		// sql += " left join lab_wf_process_ins ins on ins.`status`=fun.ID
		// where ins.is_del='"+Constants.N+"'";
		// sql += " AND ins.`status`<>'"+LabWfConstant.BUS_PROCESS_END+"' AND
		// ins.`status`<>'"+LabWfConstant.BUS_PROCESS_INIT+"'";
		// sql += " and fun.is_del='"+Constants.N+"' AND
		// fun.is_display='"+Constants.Y+"' AND fun.type='1' AND
		// fun.is_process='"+Constants.Y+"'";
		// sql += " and fun.ID in (select fun_id from lab_sys_user_fun
		// uf,lab_sys_function fun where user_id='"+userId+"')";
		// sql += " GROUP BY fun.ID";
		// 版本2
		// String sql=" SELECT fun.id,fun.name,fun.url,count(fun.ID)";
		// sql += " FROM lab_sys_function fun left join lab_wf_step_ins ins on
		// ins.code=fun.ID ";
		// sql += " WHERE ins.is_del='"+Constants.N+"' AND
		// ins.`status`<>'"+LabWfConstant.BUS_PROCESS_END+"' AND
		// ins.`status`<>'"+LabWfConstant.BUS_PROCESS_INIT+"' ";
		// sql += " AND fun.is_del='"+Constants.N+"' ";
		// sql += " AND fun.is_display='Y' AND fun.type='1' AND
		// fun.is_process='"+Constants.Y+"' and fun.ID in ";
		// sql += " (SELECT uf.fun_id FROM lab_sys_user_fun uf WHERE 1=1 ";
		// if(orgId!=null&&!orgId.equals("")){
		// sql += " AND uf.org_id='"+orgId+"'";
		// }
		// sql += " AND uf.user_id='"+userId+"')";
		// sql += " GROUP BY fun.ID";

		// String sql=" SELECT
		// ins.code,ins.step_name,fun.url,COUNT(DISTINCT(ins.ID))";
		// sql+=" FROM lab_wf_step_ins ins";
		// sql+=" LEFT JOIN lab_sys_function fun ON ins.code=fun.ID AND
		// fun.is_del='"+Constants.N+"' AND fun.is_display='"+Constants.Y+"' AND
		// fun.type='1' AND fun.is_process='"+Constants.Y+"'";
		// sql+=" LEFT JOIN lab_sys_user_fun uf ON uf.fun_id=fun.ID AND
		// uf.fun_id=fun.ID AND uf.user_id='"+userId+"'";
		// if(orgId!=null&&!orgId.equals("")){
		// sql += " AND uf.org_id='"+orgId+"'";
		// }
		// sql+=" WHERE ins.is_del='"+Constants.N+"' AND
		// ins.`status`<>'"+LabWfConstant.BUS_PROCESS_END+"' AND
		// ins.`status`<>'"+LabWfConstant.BUS_PROCESS_INIT+"'";
		// sql+=" AND INSTR(ins.tenantid,uf.tenantid)>0";
		// sql+=" GROUP BY ins.code";
		// sql+=" ORDER BY fun.code ASC";

		// 版本3
		String sql = " SELECT ins.code,ins.step_name,fun.url,COUNT(ins.ID)";
		sql += " FROM  lab_wf_step_ins ins";
		sql += " LEFT JOIN lab_sys_function fun ON ins.code=fun.ID AND fun.is_del='"
				+ Constants_Common.N
				+ "' AND fun.is_display='"
				+ Constants_Common.Y
				+ "' AND fun.type='1' AND fun.is_process='" + Constants_Common.Y + "'";
		sql += " LEFT JOIN lab_sys_user_fun uf ON uf.fun_id=fun.ID AND uf.fun_id=fun.ID AND uf.user_id='"
				+ userId + "'";
		if (orgId != null && !orgId.equals("")) {
			sql += " AND uf.org_id='" + orgId + "'";
		}
		sql += " WHERE ins.is_del='" + Constants_Common.N + "' AND ins.`status`<>'"
				+ LabWfConstant.BUS_PROCESS_END + "' AND ins.`status`<>'"
				+ LabWfConstant.BUS_PROCESS_INIT + "'";
		sql += " AND INSTR(ins.tenantid,uf.tenantid)>0";
		sql += " GROUP BY ins.code";
		sql += " ORDER BY fun.code ASC";
		List<Object[]> StrList = labFunctionDAO.createSqlQuery(sql);
		List<LabFunctionVo> funList = new ArrayList<LabFunctionVo>();
		if (StrList != null && StrList.size() > 0) {
			for (Object[] obj : StrList) {
				LabFunctionVo vo = new LabFunctionVo();
				vo.setId(String.valueOf(obj[0]));
				vo.setName(String.valueOf(obj[1]));
				vo.setUrl(String.valueOf(obj[2]));
				vo.setCount(String.valueOf(obj[3]));
				funList.add(vo);
			}
		}
		return funList;
	}

	@Override
	public LabFunctionVo getLabFunctionUrl(String id) throws GlobalException {
		LabFunction labFunction = (LabFunction) labFunctionDAO.findById(
				LabFunction.class, id);
		if (labFunction.getType().equals("0")) {
			List<LabFunction> list = labFunctionDAO
					.getLabFunctionList("FROM LabFunction WHERE 1=1 AND isDel = '"
							+ Constants_Common.N
							+ "' AND parentFunction.id = '"
							+ id
							+ "' ORDER BY sort ASC");
			if (null != list && list.size() > 0)
				labFunction = list.get(0);
		}
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		BeanUtils.copyProperties(labFunction, labFunctionVo, new String[] {
				"parentFunction", "subFunctions" });
		if (null != labFunction.getParentFunction()) {
			labFunctionVo.setParentId(labFunction.getParentFunction().getId());
			labFunctionVo.setParentName(labFunction.getParentFunction()
					.getName());
		}
		return labFunctionVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabFunction4Data(List<LabFunctionVo> funlist)
			throws GlobalException {
		boolean flag = true;
		if (funlist != null) {
			for (LabFunctionVo labFunctionVo : funlist) {
				LabFunction fun = labFunctionDAO.getLabFunction(labFunctionVo
						.getId().trim());
				fun.setDataStr(labFunctionVo.getDataStr());
				if (labFunctionVo.getDataStr() != null
						&& labFunctionVo.getDataStr().equals("org")) {
					fun.setValStr(labFunctionVo.getValStr());
					int n = 0;
					try {
						n = Integer.valueOf(labFunctionVo.getValStr());
					} catch (NumberFormatException e) {
						n = 0;
					}
					// 更新已经分配的权限
					String hql = "FROM LabUserFun WHERE function.id='"
							+ fun.getId() + "'";
					List<LabUserFun> list = labUserFunDAO.find(hql);
					if (list != null) {
						for (LabUserFun labUserFun : list) {
							String tt = labUserFun.getOrg().getTenantId();
							String[] str = tt.split("-");
							if (str.length <= (n + 1)) {
								labUserFun.setTenantId(labUserFun.getOrg()
										.getTenantId());
							} else {
								String st = "";
								for (int i = 0; i <= n; i++) {
									st += str[i] + "-";
								}
								if (st.endsWith("-"))
									st = st.substring(0, st.length() - 1);
								labUserFun.setTenantId(st);
							}
							labUserFunDAO.updateLabUserFun(labUserFun);
						}
					}
				} else {
					fun.setValStr(null);
					// 更新已经分配的权限
					String hql = "FROM LabUserFun WHERE function.id='"
							+ fun.getId() + "'";
					List<LabUserFun> list = labUserFunDAO.find(hql);
					if (list != null) {
						for (LabUserFun labUserFun : list) {
							labUserFun.setTenantId(labUserFun.getOrg()
									.getTenantId()
									+ "*" + labUserFun.getUser().getId());
							labUserFun.setTenantStr(null);
							labUserFunDAO.updateLabUserFun(labUserFun);
						}
					}
				}
				labFunctionDAO.updateLabFunction(fun);
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunctionVo> getMainmenuByUserIdAndMenuType(String userId,
			String orgId, String... extendPara) throws GlobalException {

		LabUser user = labUserDAO.getLabUser(userId);
		List<LabFunctionVo> funVoList = new ArrayList<LabFunctionVo>();

		String hql = "FROM LabUserFun WHERE function.isDel='" + Constants_Common.N
				+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
		hql += " AND user.id='" + userId + "' ";
		hql += " AND org.id='" + orgId + "' ";
		hql += " AND function.parentFunction.id='" + extendPara[0] + "'";
		if (null != user.getUserType()
				&& user.getUserType().equals(Constants_Common.BACK)) {
			hql += " AND function.isBack = 'Y'";
		} else if (null != user.getUserType()
				&& user.getUserType().equals(Constants_Common.FRONT)) {
			hql += " AND function.isFront = 'Y'";
		}
		hql += " GROUP BY function.id ";
		hql += " ORDER BY function.sort ASC";
		List<LabUserFun> funList = labUserFunDAO.find(hql);
		if (null != funList) {
			for (LabUserFun userFunpo : funList) {
				LabFunction po = userFunpo.getFunction();
				LabFunctionVo vo = new LabFunctionVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				String url = vo.getUrl();
				List<LabFunctionVo> firstList = new ArrayList<LabFunctionVo>();
				List<LabFunctionVo> allList = new ArrayList<LabFunctionVo>();
				
				List<LabUserFun> subUserFunList = labUserFunDAO
						.getLabUserFunListByUserIdAndPfunId(userId, po.getId(),
								orgId);
				if (subUserFunList != null) {
					for (LabUserFun subUserFun : subUserFunList) {
						LabFunction firstPo = subUserFun.getFunction();
						LabFunctionVo firstVo = new LabFunctionVo();
						BeanUtils.copyProperties(firstPo, firstVo,
								new String[] { "" });
						String url1 = firstVo.getUrl();
						if (!StrUtils.isBlankOrNull(url1)) {
							if (!"".equals(url1) && !url1.startsWith("/")) {
								url1 = "/" + url1;
							}
							if (url1.contains("?")) {
								url1 = url1 + "&funId=" + firstVo.getId();
							} else {
								url1 = url1 + "?funId=" + firstVo.getId();
							}
							String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
							if (!url1.contains(urlPrefixStr)) {
								url1 = "/" + urlPrefixStr + url1;
							}
							url1 = url1.replace("&", "^");
							firstVo.setUrl(url1);
						}
						List<LabFunctionVo> secondList = new ArrayList<LabFunctionVo>();
						List<LabUserFun> secondUserFunList = labUserFunDAO
								.getLabUserFunListByUserIdAndPfunId(userId,
										firstPo.getId(), orgId);
						if (secondUserFunList != null&&secondUserFunList.size()>0) {
							for (LabUserFun sendUserFun : secondUserFunList) {
								LabFunction secondPo = sendUserFun
										.getFunction();
								LabFunctionVo secondVo = new LabFunctionVo();
								BeanUtils.copyProperties(secondPo, secondVo,
										new String[] { "" });
								String url2 = secondVo.getUrl();
								if (!StrUtils.isBlankOrNull(url2)) {
									if (!"".equals(url2)
											&& !url2.startsWith("/")) {
										url2 = "/" + url2;
									}
									if (url2.contains("?")) {
										url2 = url2 + "&funId="
												+ secondVo.getId();
									} else {
										url2 = url2 + "?funId="
												+ secondVo.getId();
									}
									String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
									if (!url2.contains(urlPrefixStr)) {
										url2 = "/" + urlPrefixStr + url2;
									}
									url2 = url2.replace("&", "^");
								}
								secondVo.setParentName(secondPo.getParentFunction().getName());
								secondVo.setUrl(url2);
								secondList.add(secondVo);
							}
							allList.addAll(secondList);
						}else{
							allList.add(firstVo);
						} 

						String imageUrl = firstVo.getImageUrl();
						if (StrUtils.isBlankOrNull(imageUrl)) {
							imageUrl = "iconJpg.png";
						}
						firstVo.setImageUrl(imageUrl);
						firstVo.setSubFunctionList(secondList);
						firstList.add(firstVo);
					}
				}
				if (!StrUtils.isBlankOrNull(url) && url.trim().length() > 1) {
					if (!"".equals(url) && !url.startsWith("/")) {
						url = "/" + url;
					}
					if (url.contains("?")) {
						url = url + "&funId=" + vo.getId();
					} else {
						url = url + "?funId=" + vo.getId();
					}
					String urlPrefixStr = "jsp/include/progress_bar.jsp?url=";
					if (!url.contains(urlPrefixStr)) {
						url = "/" + urlPrefixStr + url;
					}
					url = url.replace("&", "^");
				} else {
					if ("themed".equals(extendPara[2])) {// 图标菜单形式
						if (null != allList && allList.size() > 0)
							url = allList.get(0).getUrl();
							firstList = allList;
					} else if ("themea".equals(extendPara[2])) {
						url = "/coreextend/extend/listLabFunction4iconMenu.action?labFunctionVo.parentId="
								+ po.getId()
								+ "&labFunctionVo.type="
								+ po.getType();
						
					}

				}
				
				vo.setUrl(url);
				vo.setSubFunctionList(firstList);
				String imageUrl = vo.getImageUrl();
				if (StrUtils.isBlankOrNull(imageUrl)) {
					imageUrl = "iconJpg.png";
				}
				vo.setImageUrl(imageUrl);
				funVoList.add(vo);
			}
		}
		return funVoList;
	}
}
