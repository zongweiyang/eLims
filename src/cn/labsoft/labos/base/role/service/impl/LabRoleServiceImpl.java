package cn.labsoft.labos.base.role.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.role.dao.ILabRoleDAO;
import cn.labsoft.labos.base.role.dao.ILabRoleFunDAO;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.base.role.entity.LabRoleFun;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.dao.ILabUserRoleDAO;
import cn.labsoft.labos.base.user.entity.LabUserRole;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Service("labRoleService")
public class LabRoleServiceImpl implements ILabRoleService {
	private ILabRoleDAO labRoleDAO;
	private ILabUserRoleDAO labUserRoleDAO;
	private ILabRoleFunDAO labRoleFunDAO;
	private ILabFunctionDAO labFunctionDAO;

	@Resource
	public void setLabRoleDAO(ILabRoleDAO labRoleDAO) {
		this.labRoleDAO = labRoleDAO;
	}

	@Resource
	public void setLabUserRoleDAO(ILabUserRoleDAO labUserRoleDAO) {
		this.labUserRoleDAO = labUserRoleDAO;
	}

	@Resource
	public void setLabRoleFunDAO(ILabRoleFunDAO labRoleFunDAO) {
		this.labRoleFunDAO = labRoleFunDAO;
	}

	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}

	@Override
	public LabRoleVo addLabRole(LabRoleVo labRoleVo) throws GlobalException {
		LabRole po = new LabRole();
		BeanUtils.copyProperties(labRoleVo, po, new String[] { "isUsed", "isDefault" });
		if (StrUtils.isNull(labRoleVo.getSort())) {
			po.setSort(0);
		}
		labRoleDAO.addLabRole(po);
		if (po != null)
			labRoleVo.setId(po.getId());
		return labRoleVo;
	}

	@Override
	public LabRoleVo getLabRole(Serializable id) throws GlobalException {
		LabRoleVo vo = new LabRoleVo();
		LabRole po = (LabRole) labRoleDAO.findById(LabRole.class, id);
		BeanUtils.copyProperties(po, vo, new String[] { "isUsed", "isDefault" });
		vo.setSort(po.getSort());
		return vo;
	}

	@Override
	public boolean updateLabRole4Del(String ids[]) throws GlobalException {
		if (ids != null && ids.length > 0) {
			for (String roleId : ids) {
				LabRole labRole = (LabRole) labRoleDAO.findById(LabRole.class, roleId);
				labRole.setIsDel(Constants_Common.Y);
				labRoleDAO.updateLabRole(labRole);
			}
		}
		return false;
	}

	@Override
	public List<LabRoleVo> getLabRoleList(LabRoleVo labRoleVo) throws GlobalException {
		String hql = "FROM LabRole WHERE isDel='" + Constants_Common.N + "'";
		if (!StrUtils.isBlankOrNull(labRoleVo.getShow())) {
			hql += " AND show='" + labRoleVo.getShow() + "'";
		}
		//此处可以添加条件
		hql += " ORDER BY sort ASC";
		List<LabRole> roleList = labRoleDAO.find(hql);
		List<LabRoleVo> roleVoList = new ArrayList<LabRoleVo>();
		if (roleList != null) {
			for (LabRole role : roleList) {
				LabRoleVo vo = new LabRoleVo();
				BeanUtils.copyProperties(role, vo, new String[] {});
				List<LabRoleFun> roleFunList = labRoleFunDAO.getLabRoleFunByRoleId(role.getId());
				List<LabFunctionVo> funList = new ArrayList<LabFunctionVo>();
				String funNames = "";
				if (null != roleFunList) {
					for (LabRoleFun po : roleFunList) {
						if (po.getFunction() != null && !po.getFunction().getId().equals("0")) {
							LabFunctionVo funVo = new LabFunctionVo();
							BeanUtils.copyProperties(po.getFunction(), funVo, new String[] {});
							funList.add(funVo);
							funNames += po.getFunction().getName() + ",";
						}
					}
				}
				vo.setFunList(funList);
				vo.setFunNames(funNames);
				roleVoList.add(vo);
			}
		}
		return roleVoList;
	}

	@Override
	public PageResult getLabRolePR(LabRoleVo labRoleVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabRole WHERE isDel='" + Constants_Common.N + "'";
		if (!StrUtils.isBlankOrNull(labRoleVo.getName())) {
			hql += " AND name LIKE '%" + labRoleVo.getName() + "%'";
		}
		//hql+=" ORDER BY sort ASC";
		pageResult = labRoleDAO.getPageResult(hql, pageResult);
		List<LabRole> roleList = pageResult.getResultList();
		List<LabRoleVo> resultList = new ArrayList<LabRoleVo>();
		if (null != roleList && roleList.size() > 0) {
			for (LabRole role : roleList) {
				LabRoleVo vo = new LabRoleVo();
				BeanUtils.copyProperties(role, vo, new String[] {});
				String userNames = "";
				if (!StrUtils.isBlankOrNull(role.getId())) {
					List<LabUserRole> labUserRoleList = labUserRoleDAO.getLabUserRoleListByRoleId(role.getId());
					if (null != labUserRoleList && labUserRoleList.size() > 0) {
						for (LabUserRole po : labUserRoleList) {
							if (!StrUtils.isBlankOrNull(po.getUser().getName())) {
								userNames += po.getUser().getName() + ",";
							}
						}
					}
				}
				if (userNames.length() > 0) {
					userNames = userNames.substring(0, userNames.length() - 1);
				}
				vo.setUserNames(userNames);
				resultList.add(vo);
			}
		}
		pageResult.setResultList(resultList);
		return pageResult;
	}

	@Override
	public boolean updateLabRole(LabRoleVo labRoleVo) throws GlobalException {
		LabRole labRole = (LabRole) labRoleDAO.findById(LabRole.class, labRoleVo.getId());
		if (labRole != null) {
			String createTime = labRole.getCreateTime();
			BeanUtils.copyProperties(labRoleVo, labRole, new String[] { "isUsed", "isDefault","isDel","createTime","tenantId","createUserId" });
			labRole.setIsDel(Constants_Common.N);
			labRole.setIsUsed(Constants_Common.Y);
			labRole.setCreateTime(createTime);
			labRoleDAO.updateLabRole(labRole);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRoleVo> getAllLabRoleList() throws GlobalException {
		String hql = "FROM LabRole WHERE isUsed ='" + Constants_Common.Y + "' AND name !='SA' AND isDel='" + Constants_Common.N + "' ORDER BY SORT ASC";
		List<LabRole> roleList = labRoleDAO.find(hql);
		List<LabRoleVo> list = new ArrayList<LabRoleVo>();
		for (int i = 0; i < roleList.size(); i++) {
			LabRole role = roleList.get(i);
			LabRoleVo roleVo=new LabRoleVo();
			BeanUtils.copyProperties(role, roleVo, new String[]{});
			list.add(roleVo);
		}
		return list;
	}

	@Override
	public String isExist4RoleName(String name) throws GlobalException {
		List roleName = labRoleDAO.find("FROM LabRole WHERE isDel='" + Constants_Common.N + "' AND name='" + name + "'");
		if (roleName == null) {
			return "0";
		} else {
			if (roleName.size() == 0) {
				return "0";
			} else {
				return "1";
			}
		}
	}

	@Override
	public List<LabRoleVo> getLabRoleListByUserId(String userId) throws GlobalException {
		LabRole po = new LabRole();
		List roleVoList = new ArrayList();
		if (userId == null) {
			userId = "0";
		}
		List<LabUserRole> urist = labUserRoleDAO.getLabUserRoleListByUserId(userId);
		for (int i = 0; i < urist.size(); i++) {
			LabUserRole labUserRole = urist.get(i);
			po = labUserRole.getRole();
			roleVoList.add(po);
		}
		return roleVoList;
	}

	@Override
	public String checkDeleteBatchRole(String[] ids) throws GlobalException {
		String s = "1";
		for (int i = 0; i < ids.length; i++) {
			if (null != ids[i]) {
				List role = labRoleDAO.find("FROM LabUserRole WHERE role.id='" + ids[i] + "'");
				if (role == null) {
					s = "0";
				} else {
					if (role.size() == 0) {
						s = "0";
					} else {
						s = "1";
						break;
					}
				}
			}
		}
		return s;
	}

	@Override
	public String[] getLabRoleIdsArrayByUserId(String userId,String orgId) throws GlobalException {
		List<LabUserRole> urist = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(userId,orgId);
		String[] rolesIds = new String[urist.size()];
		int i=0;
		for (LabUserRole o : urist) {
			rolesIds[i] = o.getRole().getId();
			i++;
		}
		return rolesIds;
	}

	@Override
	public String[] getLabRoleNamesArrayByUserId(String userId,String orgId) throws GlobalException {
		List<LabUserRole> urist = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(userId,orgId);
		String[] rolesNames = new String[urist.size()];
		int i = 0;
		for (LabUserRole o : urist) {
			rolesNames[i] = o.getRole().getName();
			i++;
		}
		return rolesNames;
	}

	@Override
	public boolean addLabRoleFuns(String roleId, String funIds) throws GlobalException {
		List tempList = labRoleDAO.find("FROM LabRoleFun WHERE role.id = '" + roleId + "'");
		for (Object o : tempList) {
			if (o != null) {
				labRoleFunDAO.delLabRoleFun((LabRoleFun) o);
			}
		}
		String[] funId = null;
		LabRole role = (LabRole) labRoleDAO.findById(LabRole.class, roleId);
		if (funIds != null && !funIds.equals("")) {
			funId = StrUtils.split(funIds, ',');
			for (int i = 0; funId != null && i < funId.length; i++) {
				LabFunction fun = new LabFunction();
				fun = (LabFunction) labFunctionDAO.findById(LabFunction.class, funId[i].trim());
				if(fun==null)continue;
				LabRoleFun po = new LabRoleFun();
				po.setFunction(fun);
				po.setRole(role);
				labRoleFunDAO.addLabRoleFun(po);
			}
		}
		return true;
	}

	@Override
	public String getLabRoleList4Json() throws GlobalException {
		String json = "{tableValue:[";
		String hql = "FROM LabRole WHERE isDel='" + Constants_Common.N + "'";
		List<LabRole> listLabRole = labRoleDAO.find(hql);
		if (listLabRole != null && listLabRole.size() > 0) {
			for (LabRole labRole : listLabRole) {
				json += "{";
				json += "角色名称:";
				json += "\"" + labRole.getName() + "\",";
				json += "显示:";
				if (!StrUtils.isBlankOrNull(labRole.getShow()) && labRole.getShow().equals("0"))
					json += "\"前台\",";
				else
					json += "\"后台\",";
				json += "备注:";
				json += "\"" + labRole.getRemark() + "\"},";
			}
			json = json.substring(0, json.length() - 1);
			json += "]}";
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRoleVo> getLabRoleList(String userId, String orgId) throws GlobalException {
		String hql = "FROM LabUserRole WHERE 1=1";
		hql += " AND user.id='" + userId+ "'";
		hql += " AND org.id='" + orgId+ "'";
		hql += " ORDER BY role.sort ASC";
		List<LabUserRole> roleList = labRoleDAO.find(hql);
		List<LabRoleVo> roleVoList = new ArrayList<LabRoleVo>();
		if (roleList != null) {
			for (LabUserRole ur : roleList) {
				LabRole role=ur.getRole();
				LabRoleVo vo = new LabRoleVo();
				BeanUtils.copyProperties(role, vo, new String[] {});
				roleVoList.add(vo);
			}
		}
		return roleVoList;
	}

	public Set getLabPortlets(String userId)throws GlobalException{
		Set<String> set = new HashSet<String>();
		try {
			String hql = "From LabUserRole po where po.user.id = '" + userId+ "'";
			List<LabUserRole> list = labRoleDAO.find(hql);
			for (LabUserRole lsbUserRole:list) {
				if(null!=lsbUserRole.getRole().getPortlet()){
					String[] strArrays = StrUtils.split(lsbUserRole.getRole().getPortlet().replaceAll(" ", ""), ",");
					for(String str:strArrays){
						set.add(str);
					}
				}
			}
			return set;
		} catch (Exception e) {
			throw new GlobalException("根据用户的ID查询角色名称出错！" + e.getMessage());
		}
	}
}