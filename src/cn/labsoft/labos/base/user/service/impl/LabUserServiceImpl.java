package cn.labsoft.labos.base.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.labsoft.labos.base.desktop.dao.ILabDesktopstyleDAO;
import cn.labsoft.labos.base.desktop.entity.LabDesktopstyle;
import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.dao.ILabRoleDAO;
import cn.labsoft.labos.base.role.dao.ILabRoleFunDAO;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.base.role.entity.LabRoleFun;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.dao.ILabUserFunDAO;
import cn.labsoft.labos.base.user.dao.ILabUserOrgDAO;
import cn.labsoft.labos.base.user.dao.ILabUserRoleDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.base.user.entity.LabUserRole;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserFunVo;
import cn.labsoft.labos.base.user.vo.LabUserOrgVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.MD5Utils;
import cn.labsoft.labos.utils.StrUtils;

/**
 * <strong>Title : SysUserServiceImpl </strong>. <br>
 * <strong>Description : 用户信息服务实现类�</strong> <br>
 * <strong>Create on : Nov 13, 2009 2:15:04 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author HurryXu<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

@Component("labUserService")
public class LabUserServiceImpl implements ILabUserService {

	private ILabUserDAO labUserDAO;
	private ILabUserFunDAO labUserFunDAO;
	private ILabUserOrgDAO labUserOrgDAO;
	private ILabUserRoleDAO labUserRoleDAO;
	private ILabRoleFunDAO labRoleFunDAO;
	private ILabDesktopstyleDAO labDesktopstyleDAO;
	private ILabOrgDAO labOrgDAO;
	private ILabRoleDAO labRoleDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabUploadDAO labUploadDAO;

	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}
	@Resource
	public void setLabUserFunDAO(ILabUserFunDAO labUserFunDAO) {
		this.labUserFunDAO = labUserFunDAO;
	}
	@Resource
	public void setLabUserOrgDAO(ILabUserOrgDAO labUserOrgDAO) {
		this.labUserOrgDAO = labUserOrgDAO;
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
	public void setLabOrgDAO(ILabOrgDAO labOrgDAO) {
		this.labOrgDAO = labOrgDAO;
	}
	@Resource
	public void setLabRoleDAO(ILabRoleDAO labRoleDAO) {
		this.labRoleDAO = labRoleDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabDesktopstyleDAO(ILabDesktopstyleDAO labDesktopstyleDAO) {
		this.labDesktopstyleDAO = labDesktopstyleDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean isExist4LoginName(String loginName) throws GlobalException {
		String hql = "SELECT loginName FROM LabUser WHERE isDel='" + Constants_Common.N + "' ";
		hql += " AND loginName LIKE '" + loginName + "'";
		List<String> lsit = labUserDAO.find(hql);
		if (lsit != null && lsit.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isTrue4Pwd(String userId, String pwd) throws GlobalException {
		String hql = "SELECT loginName FROM LabUser WHERE isDel='" + Constants_Common.N + "' ";
		hql += " AND id = '" + userId + "'";
		hql += " AND pwd = '" + MD5Utils.MD5(pwd) + "'";
		List<String> lsit = labUserDAO.find(hql);
		if (lsit != null && lsit.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabUserVo addLabUser(LabUserVo userVo) throws GlobalException {
		LabUser user = new LabUser();
		BeanUtils.copyProperties(userVo, user, new String[] { "" });
		try {
			List<LabDesktopstyle> list = labDesktopstyleDAO.find("From LabDesktopstyle where name='default'");
			LabDesktopstyle labDesktopstyle = new LabDesktopstyle();
			if (null != list && 0 < list.size()) {
				labDesktopstyle = (LabDesktopstyle) list.get(0);
			}
			user.setDestStyle(labDesktopstyle);
			if (!StrUtils.isBlankOrNull(userVo.getPwd())) {
				user.setPwd(MD5Utils.MD5(userVo.getPwd().toUpperCase()));
			} else {
				user.setPwd(MD5Utils.MD5("111111"));
			}
			labUserDAO.addLabUser(user);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		String tenantId="";
		//修改上传logo的busId
		List<LabUpload> loadList = labUploadDAO.getLabUploadList(userVo.getUuid(), "userLogo");
		if (loadList != null) {
			for (LabUpload labUpload : loadList) {
				labUpload.setBusId(user.getId());
				labUploadDAO.updateLabUpload(labUpload);
			}
		}
		//保存部门和角色关系
		if (userVo.getUserOrgList() != null) {
			for (LabUserOrgVo urVo : userVo.getUserOrgList()) {
				if (urVo == null || StrUtils.isBlankOrNull(urVo.getOrgId()))
					continue;
				LabUserOrg uoPo = new LabUserOrg();
				LabOrg org = labOrgDAO.getLabOrg(urVo.getOrgId());
				uoPo.setOrg(org);
				uoPo.setUser(user);
				tenantId+=org.getTenantId()+",";
				labUserOrgDAO.addLabUserOrg(uoPo);
				if (!StrUtils.isBlankOrNull(urVo.getRoleIds())) {
					String roleids[] = urVo.getRoleIds().split(",");
					for (String roleid : roleids) {
						if (StrUtils.isBlankOrNull(roleid))
							continue;
						LabUserRole urPo = new LabUserRole();
						urPo.setOrg(org);
						urPo.setUser(user);
						LabRole role = labRoleDAO.getLabRole(roleid.trim());
						urPo.setRole(role);
						labUserRoleDAO.addLabUserRole(urPo);
						List<LabRoleFun> roleFunList = labRoleFunDAO.getLabRoleFunByRoleId(roleid);
						if (roleFunList != null) {
							for (LabRoleFun labRoleFun : roleFunList) {
								LabFunction fun = labRoleFun.getFunction();
								if (fun.getId().equals("0"))
									continue;
								LabUserFun userFun = new LabUserFun();
								userFun.setFunction(fun);
								userFun.setOrg(org);
								userFun.setRole(role);
								userFun.setUser(user);
								userFun.setIsAdd("Y");
								userFun.setIsDelete("Y");
								userFun.setIsUpdate("Y");
								userFun.setIsShow("Y");
								//userFun.setTenantStr(org.getTenantId());
								//userFun.setTenantId(org.getTenantId());
								if(null!=fun.getDataStr()&&fun.getDataStr().equals("user")){
									userFun.setTenantId(userFun.getOrg().getTenantId()+"*"+userFun.getUser().getId());
								}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("org")){
									int level=0;
									try {
										level=Integer.valueOf(fun.getValStr()==null?"0":fun.getValStr());
									} catch (NumberFormatException e) {
										level=0;
										throw new GlobalException("" + e.getMessage());
									}
									List<LabOrgVo> orgDataList=getLabOrgList4Tree(userFun.getOrg(),level);
									if(orgDataList!=null&&orgDataList.size()>0){
										userFun.setTenantId(orgDataList.get(0).getTenantId());
									}
								}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("role")){
									userFun.setTenantId(userFun.getOrg().getTenantId()+"*"+fun.getValStr());
								}
								userFun.setTenantStr(fun.getValStr());
								
								labUserFunDAO.addLabUserFun(userFun);
							}
						}
					}
				}
			}
		}
		if(tenantId.endsWith(",")){
			tenantId=tenantId.substring(0, tenantId.length()-1);
		}
		user.setTenantId(tenantId);
		labUserDAO.updateLabUser(user);
		if (user != null)
			userVo.setId(user.getId());
		return userVo;
	}

	@Override
	public boolean delLabUser(String[] userId) throws GlobalException {
		if (userId != null && userId.length > 0) {
			for (String id : userId) {
				if (StrUtils.isBlankOrNull(id))
					continue;
				LabUser user = labUserDAO.getLabUser(id);
				if (StrUtils.isNull(user))
					continue;
				user.setIsDel(Constants_Common.Y);
				labUserDAO.updateLabUser(user);
			}
		}
		return false;
	}
	@Override
	public LabUserVo getLabUser4AllInfo(String userId) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userId);
		if (user != null) {
			LabUserVo userVo = new LabUserVo();
			BeanUtils.copyProperties(user, userVo, new String[] {});
			//用户部门关系列表
			List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByUserId(userId);
			List<LabUserOrgVo> userOrgList = new ArrayList<LabUserOrgVo>();
			String orgId = "";
			String orgName = "";
			if (null != uoList) {
				for (LabUserOrg uoPo : uoList) {
					LabOrg org = uoPo.getOrg();
					LabUserOrgVo uorgVo = new LabUserOrgVo();
					uorgVo.setOrgId(org.getId());
					uorgVo.setOrgName(org.getName());
					List<LabUserRole> urleList = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(userId, org.getId());
					String roleIdStr = "";
					String roleNameStr = "";
					if (urleList != null) {
						for (LabUserRole labUserRole : urleList) {
							roleIdStr += labUserRole.getRole().getId() + ",";
							roleNameStr += labUserRole.getRole().getName() + ",";
						}
					}
					if (roleIdStr.length() > 1) {
						roleIdStr = roleIdStr.substring(0, roleIdStr.length() - 1);
						roleNameStr = roleNameStr.substring(0, roleNameStr.length() - 1);
					}
					uorgVo.setRoleIds(roleIdStr);
					uorgVo.setRoleNames(roleNameStr);
					userOrgList.add(uorgVo);
					orgId += org.getId() + ",";
					orgName += org.getName() + ",";

					userVo.setMainOrgName(uoList.get(0).getOrg().getName());
				}
			}
			if (orgId.length() > 1) {
				orgId = orgId.substring(0, orgId.length() - 1);
				orgName = orgName.substring(0, orgName.length() - 1);
			}
			userVo.setOrgId(orgId);
			userVo.setOrgName(orgName);
			userVo.setUserOrgList(userOrgList);
			List<LabUpload> fileList = labUploadDAO.getLabUploadList(userVo.getId(), "userLogo");
			if (fileList != null && fileList.size() > 0) {
				userVo.setLogo(fileList.get(0).getPath());
			}
			return userVo;
		} else {
			return null;
		}
	}
	@Override
	public LabUserVo getLabUser(String userId) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userId);
		if (user != null) {
			LabUserVo userVo = new LabUserVo();
			BeanUtils.copyProperties(user, userVo, new String[] {});
			//用户部门关系列表
			SessionContainer son =SessionContainer.getSessionContainer();
			List<LabUserRole> urleList = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(userId,son.getOrgId());
			String roleIdStr = "";
			String roleNameStr = "";
			if (urleList != null) {
				for (LabUserRole labUserRole : urleList) {
					roleIdStr += labUserRole.getRole().getId() + ",";
					roleNameStr += labUserRole.getRole().getName() + ",";
				}
				if (roleIdStr.length() > 1) {
					roleIdStr = roleIdStr.substring(0, roleIdStr.length() - 1);
					roleNameStr = roleNameStr.substring(0, roleNameStr.length() - 1);
				}
			}
			userVo.setRoleId(roleIdStr);
			userVo.setRoleName(roleNameStr);
			userVo.setOrgId(son.getOrgId());
			userVo.setOrgName(son.getOrgName());
			List<LabUpload> fileList = labUploadDAO.getLabUploadList(userVo.getId(), "userLogo");
			if (fileList != null && fileList.size() > 0) {
				userVo.setLogo(fileList.get(0).getPath());
			}
			return userVo;
		} else {
			return null;
		}
	}

	@Override
	public LabUserVo getLabUser4FunByUserId(String userId) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userId);
		if (user != null) {
			LabUserVo userVo = new LabUserVo();
			BeanUtils.copyProperties(user, userVo, new String[] {});
			//用户功能关系列表
			List<LabUserFun> ufList = labUserFunDAO.getLabUserFunListByUserId(userId);
			List<LabUserFunVo> userFunList = new ArrayList<LabUserFunVo>();
			if (ufList != null && ufList.size() > 0) {
				for (LabUserFun ufpo : ufList) {
					LabUserFunVo ufVo = new LabUserFunVo();
					ufVo.setFunctionId(ufpo.getFunction().getId());
					ufVo.setFunctionName(ufpo.getFunction().getName());
					if (ufpo.getFunction().getParentFunction() != null) {
						ufVo.setParentFunId(ufpo.getFunction().getParentFunction().getId());
					}
					ufVo.setUserId(ufpo.getUser().getId());
					ufVo.setUserName(ufpo.getUser().getName());
					ufVo.setOrgId(ufpo.getOrg().getId());
					ufVo.setOrgName(ufpo.getOrg().getName());
					if (null != ufpo.getRole()) {
						ufVo.setRoleId(ufpo.getRole().getId());
						ufVo.setRoleName(ufpo.getRole().getName());
					}
					ufVo.setIsAdd(ufpo.getIsAdd());
					ufVo.setIsDelete(ufpo.getIsDelete());
					ufVo.setIsUpdate(ufpo.getIsUpdate());
					ufVo.setIsShow(ufpo.getIsShow());
					ufVo.setDataStr(ufpo.getFunction().getDataStr());
					ufVo.setFunType(ufpo.getFunction().getType());
					ufVo.setTenantStr(ufpo.getTenantStr());
					List<LabOrgVo> orgDataList=new ArrayList<LabOrgVo>();
					//获取当前功能的数据权限
					if(ufVo.getDataStr()!=null&&ufVo.getDataStr().equals("org")){
						int level=0;
						try {
							level=Integer.valueOf(ufpo.getFunction().getValStr());
						} catch (NumberFormatException e) {
							level=0;
							throw new GlobalException("" + e.getMessage());
						}
						orgDataList=getLabOrgList4Tree(ufpo.getOrg(), level);
					}else if(ufVo.getDataStr()!=null&&ufVo.getDataStr().equals("user")){
						ufVo.setTenantStr(ufpo.getUser().getId());
					}else if(ufVo.getDataStr()!=null&&ufVo.getDataStr().equals("role")){
						if(!StrUtils.isBlankOrNull(ufpo.getFunction().getValStr())){
							LabRole role=labRoleDAO.getLabRole(ufpo.getFunction().getValStr());
							if(role!=null){
								ufVo.setDataStr(role.getName());
								ufVo.setTenantStr(role.getId());
							}
						}
					}
					ufVo.setOrgDataList(orgDataList);
					userFunList.add(ufVo);
				}
			} 
			userVo.setUserFunList(userFunList);
			return userVo;
		} else {
			return null;
		}
	}
	//获取某个级别
	@SuppressWarnings("unchecked")
	public List<LabOrgVo> getLabOrgList4Tree(LabOrg LabOrg,int rank) throws GlobalException{
		List<LabOrgVo> orgVoList=new ArrayList<LabOrgVo>();
		if(LabOrg.getRank()>rank){
			orgVoList=getLabOrgList4Parent(LabOrg, rank);
			orgVoList.addAll(getLabOrgList4Sub(LabOrg,""));
		}else if(LabOrg.getRank()==rank){
			LabOrgVo labOrgVo=new LabOrgVo();
			BeanUtils.copyProperties(LabOrg, labOrgVo, new String[]{});
			orgVoList.add(labOrgVo);
			orgVoList.addAll(getLabOrgList4Sub(LabOrg,""));
		}else{
			String hql="FROM LabOrg WHERE rank="+rank+" AND tenantId like '"+LabOrg.getTenantId()+"%'";
			List<LabOrg> orgList = labRoleDAO.find(hql);
			for (LabOrg org : orgList) {
				LabOrgVo labOrgVo=new LabOrgVo();
				BeanUtils.copyProperties(org, labOrgVo, new String[]{});
				orgVoList.add(labOrgVo);
				orgVoList.addAll(getLabOrgList4Sub(LabOrg,""));
			}
		}
		return orgVoList;
	}
	public List<LabOrgVo> getLabOrgList4Parent(LabOrg LabOrg,int rank){
		List<LabOrgVo> orgVoList=new ArrayList<LabOrgVo>();
		if(LabOrg.getRank()>rank){
			orgVoList=getLabOrgList4Parent(LabOrg.getLabOrg(), rank);
		}
		LabOrgVo labOrgVo=new LabOrgVo();
		BeanUtils.copyProperties(LabOrg, labOrgVo, new String[]{});
		orgVoList.add(labOrgVo);
		return orgVoList;
	}
	@SuppressWarnings("unchecked")
	public List<LabOrgVo> getLabOrgList4Sub(LabOrg LabOrg,String str) throws GlobalException{
		String strx=str+"-";
		List<LabOrgVo> orgVoList=new ArrayList<LabOrgVo>();
		String hql="FROM LabOrg WHERE isDel='"+Constants_Common.N+"' AND labOrg.id='"+LabOrg.getId()+"' ORDER BY sort ASC";
		List<LabOrg> orgList=labRoleDAO.find(hql);
		if(orgList!=null&&orgList.size()>0){
			for (LabOrg org : orgList) {
				LabOrgVo labOrgVo=new LabOrgVo();
				BeanUtils.copyProperties(org, labOrgVo, new String[]{});
				labOrgVo.setName(strx+org.getName());
				orgVoList.add(labOrgVo);
				orgVoList.addAll(getLabOrgList4Sub(org, strx));
			}
		}
		return orgVoList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabUserVo getLabUserByLogInNameAndPwd(String logName, String pwd) throws GlobalException {
		String hql = "FROM LabUser WHERE isDel='" + Constants_Common.N + "' and isUsed='" + Constants_Common.Y + "'";
		hql += " AND loginName LIKE '" + logName + "'";
		hql += " AND pwd LIKE '" + MD5Utils.MD5(pwd) + "'";
		List<LabUser> userList = labUserDAO.find(hql);
		if (userList != null && userList.size() == 1) {
			LabUser user = userList.get(0);
			LabUserVo userVo = new LabUserVo();
			BeanUtils.copyProperties(user, userVo, new String[] {});
			if (null != user.getDestStyle()) {
				userVo.setStyleName(user.getDestStyle().getName());
			}
			return userVo;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabUserVo getLabUserByLogInName(String logName) throws GlobalException {
		String hql = "SELECT loginName FROM LabUser WHERE isDel='" + Constants_Common.N + "' and isUsed='" + Constants_Common.Y + "'";
		hql += " AND loginName LIKE '" + logName + "'";
		List<LabUser> userList = labUserDAO.find(hql);
		if (userList != null && userList.size() == 1) {
			LabUser user = userList.get(0);
			LabUserVo userVo = new LabUserVo();
			BeanUtils.copyProperties(user, userVo, new String[] {});
			return userVo;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserVo> getLabUserList(LabUserVo userVo) throws GlobalException {
		String hql = " FROM LabUser WHERE isDel='" + Constants_Common.N + "' and isUsed='" + Constants_Common.Y + "'";
		if (!StrUtils.isBlankOrNull(userVo.getName())) {
			hql += " AND name LIKE '%" + userVo.getName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getLoginName())) {
			hql += " AND loginName LIKE '%" + userVo.getLoginName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getSex())) {
			hql += " AND sex LIKE '" + userVo.getSex() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getNo())) {
			hql += " AND no LIKE '%" + userVo.getNo() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
			hql += " AND isUsed LIKE '" + userVo.getIsUsed() + "'";
		}
		//下边方法放到最后，目的取消自排序
		if (!StrUtils.isBlankOrNull(userVo.getOrgName())) {
			hql += " AND id in (SELECT user.id FROM LabUserOrg WHERE org.name like '%" + userVo.getOrgName() + "%')";
			hql += " ORDER BY sort asc";
		}
		List<LabUser> userList = labUserDAO.find4All(hql);
		List<LabUserVo> userVoList = new ArrayList<LabUserVo>();
		if (userList != null && userList.size() > 0) {
			for (LabUser user : userList) {
				LabUserVo uo = new LabUserVo();
				BeanUtils.copyProperties(user, uo, new String[] {});
				List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByUserId(user.getId());
				String orgName = "";
				if (uoList != null) {
					for (LabUserOrg labUserOrg : uoList) {
						orgName += labUserOrg.getOrg().getName() + "  ";
					}
					if (orgName.length() > 1)
						orgName = orgName.substring(0, orgName.length() - 1);
				}
				uo.setOrgName(orgName);
				List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserId(user.getId());
				String roleName = "";
				if (urList != null) {
					for (LabUserRole labUserRole : urList) {
						roleName += labUserRole.getRole().getName() + ",";
					}
					if (roleName.length() > 1)
						roleName = roleName.substring(0, roleName.length() - 1);
				}
				uo.setRoleName(roleName);
				userVoList.add(uo);
			}
		}
		return userVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabUserPR(LabUserVo userVo, PageResult pageResult) throws GlobalException {
		String hql = " FROM LabUser WHERE isDel='" + Constants_Common.N + "'";
		//hql += " AND id<>'0'";
		if (!StrUtils.isBlankOrNull(userVo.getUserType())) {
			hql += " AND userType = '" + userVo.getUserType() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
			hql += " AND isUsed = '" + userVo.getIsUsed() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getName())) {
			hql += " AND name LIKE '%" + userVo.getName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getLoginName())) {
			hql += " AND loginName LIKE '%" + userVo.getLoginName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getSex())) {
			hql += " AND sex LIKE '" + userVo.getSex() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getNo())) {
			hql += " AND no LIKE '%" + userVo.getNo() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
			hql += " AND isUsed LIKE '" + userVo.getIsUsed() + "'";
		}
		//下边方法放到最后，目的取消自排序
		// modify by danlee begin
		if (!StrUtils.isBlankOrNull(userVo.getOrgName())) {
			hql += " AND id in (SELECT user.id FROM LabUserOrg WHERE org.name like '%" + userVo.getOrgName() + "%')";
		}
		if (!StrUtils.isBlankOrNull(userVo.getRoleName())) {
			hql += " AND id in (SELECT user.id FROM LabUserRole WHERE role.name like '%" + userVo.getRoleName() + "%')";
		}
		//hql += " ORDER BY sort asc";
		// modify by danlee end
		pageResult = labUserDAO.getLabUserPR(hql, pageResult);
		List<LabUser> userList = pageResult.getResultList();
		List<LabUserVo> userVoList = new ArrayList<LabUserVo>();
		if (userList != null) {
			for (LabUser user : userList) {
				LabUserVo uo = new LabUserVo();
				BeanUtils.copyProperties(user, uo, new String[] {});
				List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByUserId(user.getId());
				String orgName = "";
				if (uoList != null) {
					for (LabUserOrg labUserOrg : uoList) {
						orgName += labUserOrg.getOrg().getName() + ",";
					}
					if (orgName.length() > 1)
						orgName = orgName.substring(0, orgName.length() - 1);
				}
				uo.setOrgName(orgName);
				List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserId(user.getId());
				String roleName = "";
				if (urList != null) {
					for (LabUserRole labUserRole : urList) {
						roleName += labUserRole.getRole().getName() + ",";
					}
					if (roleName.length() > 1)
						roleName = roleName.substring(0, roleName.length() - 1);
				}
				uo.setRoleName(roleName);
				userVoList.add(uo);
			}
		}
		pageResult.setResultList(userVoList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabUserPR4Org(LabUserVo userVo, PageResult pageResult) throws GlobalException {
		String hql = " FROM LabUser WHERE isDel='" + Constants_Common.N + "'";
		hql += " AND id<>'0'";
		if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
			hql += " AND isUsed = '" + userVo.getIsUsed() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getName())) {
			hql += " AND name LIKE '%" + userVo.getName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getLoginName())) {
			hql += " AND loginName LIKE '%" + userVo.getLoginName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getSex())) {
			hql += " AND sex LIKE '" + userVo.getSex() + "'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getNo())) {
			hql += " AND no LIKE '%" + userVo.getNo() + "%'";
		}
		if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
			hql += " AND isUsed LIKE '" + userVo.getIsUsed() + "'";
		}
		//下边方法放到最后，目的取消自排序
		if (!StrUtils.isBlankOrNull(userVo.getOrgName())) {
			hql += " AND id in (SELECT user.id FROM LabUserOrg WHERE org.name like '%" + userVo.getOrgName() + "%')";
			hql += " ORDER BY sort asc";
		}
		if (!StrUtils.isBlankOrNull(userVo.getRoleName())) {
			hql += " AND id in (SELECT user.id FROM LabUserRole WHERE role.name like '%" + userVo.getRoleName() + "%')";
			hql += " ORDER BY sort asc";
		}
		pageResult = labUserDAO.getLabUserPR(hql, pageResult);
		List<LabUser> userList = pageResult.getResultList();
		List<LabUserVo> userVoList = new ArrayList<LabUserVo>();
		if (userList != null) {
			for (LabUser user : userList) {
				LabUserVo uo = new LabUserVo();
				BeanUtils.copyProperties(user, uo, new String[] {});
				List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByUserId(user.getId());
				String orgName = "";
				if (uoList != null) {
					for (LabUserOrg labUserOrg : uoList) {
						orgName += labUserOrg.getOrg().getName() + "  ";
					}
					if (orgName.length() > 1)
						orgName = orgName.substring(0, orgName.length() - 1);
				}
				uo.setOrgName(orgName);
				List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserId(user.getId());
				String roleName = "";
				if (urList != null) {
					for (LabUserRole labUserRole : urList) {
						roleName += labUserRole.getRole().getName() + ",";
					}
					if (roleName.length() > 1)
						roleName = roleName.substring(0, roleName.length() - 1);
				}
				uo.setRoleName(roleName);
				userVoList.add(uo);
			}
		}
		pageResult.setResultList(userVoList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isExist4UserAndIp(String loginName, String ip) throws GlobalException {
		String hql = "SELECT loginName FROM LabUser WHERE isDel='" + Constants_Common.N + "' AND isUsed='" + Constants_Common.Y + "'";
		hql += " AND loginName LIKE '" + loginName + "'";
		hql += " AND ipass LIKE '%" + ip + "%'";
		List<String> lsit = labUserDAO.find(hql);
		if (lsit != null && lsit.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isExist4UserAndUksn(String loginName, String uksn) throws GlobalException {
		String hql = "SELECT loginName FROM LabUser WHERE isDel='" + Constants_Common.N + "' AND isUsed='" + Constants_Common.Y + "'";
		hql += " AND loginName LIKE '" + loginName + "'";
		hql += " AND token LIKE '" + uksn + "'";
		List<String> lsit = labUserDAO.find(hql);
		if (lsit != null && lsit.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLabUser(LabUserVo userVo) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userVo.getId());
		if (user != null) {
			if (!StrUtils.isBlankOrNull(userVo.getName())) {
				user.setName(userVo.getName());
			}
			if (!StrUtils.isBlankOrNull(userVo.getPwd())) {
				user.setPwd(MD5Utils.MD5(userVo.getPwd()));
			}
			if (!StrUtils.isBlankOrNull(userVo.getSex())) {
				user.setSex(userVo.getSex());
			}
			if (!StrUtils.isBlankOrNull(userVo.getBirthday())) {
				user.setBirthday(userVo.getBirthday());
			}
			if (!StrUtils.isNull(userVo.getSort())) {
				user.setSort(userVo.getSort());
			}
			if (!StrUtils.isBlankOrNull(userVo.getIp())) {
				user.setIp(userVo.getIp());
			}
			if (!StrUtils.isBlankOrNull(userVo.getToken())) {
				user.setToken(userVo.getToken());
			}
			if (!StrUtils.isBlankOrNull(userVo.getIsUsed())) {
				user.setIsUsed(userVo.getIsUsed());
			}
			if (!StrUtils.isBlankOrNull(userVo.getDuty())) {
				user.setDuty(userVo.getDuty());
			}
			if (!StrUtils.isBlankOrNull(userVo.getUserType())) {
				user.setUserType(userVo.getUserType());
			}
			if (!StrUtils.isBlankOrNull(userVo.getRemark())) {
				user.setRemark(userVo.getRemark());
			}
			labUserDAO.updateLabUser(user);
			String tenantId="";
			//删除部门，角色，功能原数据
			List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByUserId(user.getId());
			if (null != uoList) {
				for (LabUserOrg labUserOrg : uoList) {
					//判断用户部门是否被删除
					boolean isHava=false;
					String roleIds="";
					if(userVo.getUserOrgList()!=null){
						for (LabUserOrgVo urVo: userVo.getUserOrgList()) {
							if(urVo!=null&&labUserOrg.getOrg().getId().equals(urVo.getOrgId())){
								roleIds=urVo.getRoleIds();
								isHava=true;
								break;
							}
						}
					}
					if(!isHava){
						//删除该部门所有功能
						List<LabUserFun> userFunList = labUserFunDAO.getLabUserFunListByUserIdAndOrgId(user.getId(),labUserOrg.getOrg().getId());
						if (userFunList != null) {
							for (LabUserFun labUserFun : userFunList) {
								labUserFunDAO.deleteLabUserFun(labUserFun);
							}
						}
						//删除该部门
						labUserOrgDAO.delLabUserOrg(labUserOrg);
						//删除该部门所有角色
						List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(user.getId(), labUserOrg.getOrg().getId());
						if (null != urList){
							for (LabUserRole labUserRole : urList) {
								labUserRoleDAO.deleteLabUserRole(labUserRole);
							}
						}
					}else{
						tenantId+=labUserOrg.getOrg().getTenantId()+",";
						//判断角色是否被删除
						List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(user.getId(), labUserOrg.getOrg().getId());
						if (null != urList){
							for (LabUserRole labUserRole : urList) {
								String roleids[] = roleIds.split(",");
								//判断该角色是否变动
								isHava=false;
								for (String roleid : roleids) {
									if (StrUtils.isBlankOrNull(roleid))
										continue;
									if(roleid.equals(labUserRole.getRole().getId())){
										isHava=true;
										break;
									}
								}
								if(!isHava){
									//删除该角色所有功能
									List<LabUserFun> userFunList = labUserFunDAO.getLabUserFunList(user.getId(),labUserRole.getRole().getId());
									if (userFunList != null) {
										for (LabUserFun labUserFun : userFunList) {
											labUserFunDAO.deleteLabUserFun(labUserFun);
										}
									}
									//删除该角色
									labUserRoleDAO.deleteLabUserRole(labUserRole);
								}
							}
						}
					}
				}
			}
			if (userVo.getUserOrgList() != null) {
				for (LabUserOrgVo urVo : userVo.getUserOrgList()) {
					if (urVo == null)
						continue;
					boolean isHava=false;
					if(uoList!=null){
						for (LabUserOrg labUserOrg : uoList) {
							if(urVo.getOrgId().trim().equals(labUserOrg.getOrg().getId())){
								if (!StrUtils.isBlankOrNull(urVo.getRoleIds())) {
									String roleids[] = urVo.getRoleIds().split(",");
									List<LabUserRole> urList = labUserRoleDAO.getLabUserRoleListByUserIdAndOrgId(user.getId(), labUserOrg.getOrg().getId());
									for (String roleid : roleids) {
										if (StrUtils.isBlankOrNull(roleid))
											continue;
										boolean isHas=false;
										if (null != urList){
											for (LabUserRole labUserRole : urList) {
												if(roleid.equals(labUserRole.getRole().getId())){
													isHas=true;
													break;
												}
											}
										}
										if(isHas)continue;
										//角色改变
										LabUserRole urPo = new LabUserRole();
										urPo.setOrg(labUserOrg.getOrg());
										urPo.setUser(user);
										LabRole role = labRoleDAO.getLabRole(roleid.trim());
										urPo.setRole(role);
										labUserRoleDAO.addLabUserRole(urPo);
										//增加新部门新角色所附带的权限
										List<LabRoleFun> roleFunList = labRoleFunDAO.getLabRoleFunByRoleId(roleid);
										if (roleFunList != null) {
											for (LabRoleFun labRoleFun : roleFunList) {
												LabFunction fun = labRoleFun.getFunction();
												if(fun==null)continue;
												if (fun.getId().equals("0"))
													continue;
												LabUserFun userFun = new LabUserFun();
												userFun.setFunction(fun);
												userFun.setOrg(labUserOrg.getOrg());
												userFun.setRole(role);
												userFun.setUser(user);
												userFun.setIsAdd("Y");
												userFun.setIsDelete("Y");
												userFun.setIsUpdate("Y");
												userFun.setIsShow("Y");
												//userFun.setTenantStr(labUserOrg.getOrg().getTenantId());
												//userFun.setTenantId(labUserOrg.getOrg().getTenantId());
												if(null!=fun.getDataStr()&&fun.getDataStr().equals("user")){
													userFun.setTenantId(userFun.getOrg().getTenantId()+"*"+userFun.getUser().getId());
												}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("org")){
													int level=0;
													try {
														level=Integer.valueOf(fun.getValStr()==null?"0":fun.getValStr());
													} catch (NumberFormatException e) {
														level=0;
														throw new GlobalException("" + e.getMessage());
													}
													List<LabOrgVo> orgDataList=getLabOrgList4Tree(userFun.getOrg(),level);
													if(orgDataList!=null&&orgDataList.size()>0){
														userFun.setTenantId(orgDataList.get(0).getTenantId());
													}
												}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("role")){
													userFun.setTenantId(userFun.getOrg().getTenantId()+"*"+fun.getValStr());
												}
												userFun.setTenantStr(fun.getValStr());
												labUserFunDAO.addLabUserFun(userFun);
											}
										}
									}
								}
								isHava=true;
								break;
							}
						}
					}
					if(isHava)continue;
					//增加部门
					LabUserOrg uoPo = new LabUserOrg();
					LabOrg org = labOrgDAO.getLabOrg(urVo.getOrgId());
					uoPo.setOrg(org);
					uoPo.setUser(user);
					tenantId+=org.getTenantId()+",";
					labUserOrgDAO.addLabUserOrg(uoPo);
					//保存部门下角色
					if (!StrUtils.isBlankOrNull(urVo.getRoleIds())) {
						String roleids[] = urVo.getRoleIds().split(",");
						for (String roleid : roleids) {
							if (StrUtils.isBlankOrNull(roleid))
								continue;
							LabUserRole urPo = new LabUserRole();
							urPo.setOrg(org);
							urPo.setUser(user);
							LabRole role = labRoleDAO.getLabRole(roleid.trim());
							urPo.setRole(role);
							labUserRoleDAO.addLabUserRole(urPo);
							//增加新部门新角色所附带的权限
							List<LabRoleFun> roleFunList = labRoleFunDAO.getLabRoleFunByRoleId(roleid);
							if (roleFunList != null) {
								for (LabRoleFun labRoleFun : roleFunList) {
									LabFunction fun = labRoleFun.getFunction();
									if(fun==null)continue;
									if (fun.getId().equals("0"))
										continue;
									LabUserFun userFun = new LabUserFun();
									userFun.setFunction(fun);
									userFun.setOrg(org);
									userFun.setRole(role);
									userFun.setUser(user);
									userFun.setIsAdd("Y");
									userFun.setIsDelete("Y");
									userFun.setIsUpdate("Y");
									userFun.setIsShow("Y");
									userFun.setTenantStr(org.getTenantId());
									userFun.setTenantId(org.getTenantId());
									labUserFunDAO.addLabUserFun(userFun);
								}
							}
						}
					}
				}
			}
			if(tenantId.endsWith(",")){
				tenantId=tenantId.substring(0, tenantId.length()-1);
			}
			user.setTenantId(tenantId);
			labUserDAO.updateLabUser(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLabUser4Org(LabUserVo userVo) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userVo.getId());
		if (user != null) {
			user.setName(userVo.getName());
			user.setSex(userVo.getSex());
			user.setMobile(userVo.getMobile());
			user.setEmail(userVo.getEmail());
			user.setTelephone(userVo.getTelephone());
			user.setAddress(userVo.getAddress());
			user.setBirthday(userVo.getBirthday());
			user.setNation(userVo.getNation());
			user.setBirthday(userVo.getBirthday());
			user.setNo(userVo.getNo());
			user.setWorkDate(userVo.getWorkDate());
			user.setProfession(userVo.getProfession());
			user.setDuty(userVo.getDuty());
			user.setTechTitle(userVo.getTechTitle());
			user.setSort(userVo.getSort());
			if (!StrUtils.isBlankOrNull(userVo.getIp())) {
				user.setIp(userVo.getIp());
			}
			user.setToken(userVo.getToken());
			labUserDAO.updateLabUser(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLabUser4Fun(LabUserVo userVo) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userVo.getId());
		if (user != null) {
			//保存功能权限关系
			List<LabUserFun> ufList = labUserFunDAO.getLabUserFunListByUserId(user.getId());
			if (null != ufList) {
				for (LabUserFun labUserFun : ufList) {
					labUserFunDAO.deleteLabUserFun(labUserFun);
				}
			}
			if (userVo.getUserFunList() != null) {
				for (LabUserFunVo ufVo : userVo.getUserFunList()) {
					if (ufVo == null || StrUtils.isBlankOrNull(ufVo.getOrgId()) || StrUtils.isBlankOrNull(ufVo.getFunctionId()))
						continue;
					LabUserFun ufPo = new LabUserFun();
					LabOrg org = labOrgDAO.getLabOrg(ufVo.getOrgId());
					ufPo.setOrg(org);
					ufPo.setUser(user);
					LabFunction fun = labFunctionDAO.getLabFunction(ufVo.getFunctionId());
					ufPo.setFunction(fun);
					if (!StrUtils.isBlankOrNull(ufVo.getRoleId())) {
						LabRole role = labRoleDAO.getLabRole(ufVo.getRoleId());
						ufPo.setRole(role);
					}
					if (!StrUtils.isBlankOrNull(ufVo.getIsAdd())) {
						ufPo.setIsAdd(ufVo.getIsAdd());
					} else {
						ufPo.setIsAdd("N");
					}
					if (!StrUtils.isBlankOrNull(ufVo.getIsDelete())) {
						ufPo.setIsDelete(ufVo.getIsDelete());
					} else {
						ufPo.setIsDelete("N");
					}
					if (!StrUtils.isBlankOrNull(ufVo.getIsUpdate())) {
						ufPo.setIsUpdate(ufVo.getIsUpdate());
					} else {
						ufPo.setIsUpdate("N");
					}
					if (!StrUtils.isBlankOrNull(ufVo.getIsShow())) {
						ufPo.setIsShow(ufVo.getIsShow());
					} else {
						ufPo.setIsShow("N");
					}
					if(null!=fun.getDataStr()&&fun.getDataStr().equals("user")){
						ufPo.setTenantId(ufPo.getOrg().getTenantId()+"*"+ufPo.getUser().getId());
					}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("org")){
						ufPo.setTenantId(ufVo.getTenantStr());
					}else if(null!=fun.getDataStr()&&fun.getDataStr().equals("role")){
						ufPo.setTenantId(ufPo.getOrg().getTenantId()+"*"+ufVo.getTenantStr());
					}
					ufPo.setTenantStr(ufVo.getTenantStr());
					labUserFunDAO.addLabUserFun(ufPo);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLabUser4self(LabUserVo userVo) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userVo.getId());
		if (user != null) {
			if (!StrUtils.isBlankOrNull(userVo.getName())) {
				user.setName(userVo.getName());
			}
			if (!StrUtils.isBlankOrNull(userVo.getPwd())) {
				user.setPwd(MD5Utils.MD5(userVo.getPwd()));
			}
			if (!StrUtils.isBlankOrNull(userVo.getSex())) {
				user.setSex(userVo.getSex());
			}
			if (!StrUtils.isBlankOrNull(userVo.getMobile())) {
				user.setMobile(userVo.getMobile());
			}
			if (!StrUtils.isBlankOrNull(userVo.getEmail())) {
				user.setEmail(userVo.getEmail());
			}
			if (!StrUtils.isBlankOrNull(userVo.getTelephone())) {
				user.setTelephone(userVo.getTelephone());
			}
			if (!StrUtils.isBlankOrNull(userVo.getAddress())) {
				user.setAddress(userVo.getAddress());
			}
			if (!StrUtils.isBlankOrNull(userVo.getBirthday())) {
				user.setBirthday(userVo.getBirthday());
			}
			if (!StrUtils.isBlankOrNull(userVo.getNo())) {
				user.setNo(userVo.getNo());
			}
			if (!StrUtils.isBlankOrNull(userVo.getWorkDate())) {
				user.setWorkDate(userVo.getWorkDate());
			}
			if (!StrUtils.isBlankOrNull(userVo.getProfession())) {
				user.setProfession(userVo.getProfession());
			}
			if (!StrUtils.isBlankOrNull(userVo.getDuty())) {
				user.setDuty(userVo.getDuty());
			}
			if (!StrUtils.isBlankOrNull(userVo.getTechTitle())) {
				user.setTechTitle(userVo.getTechTitle());
			}
			if (!StrUtils.isNull(userVo.getSort())) {
				user.setSort(userVo.getSort());
			}
			if (!StrUtils.isBlankOrNull(userVo.getIp())) {
				user.setIp(userVo.getIp());
			}
			if (!StrUtils.isBlankOrNull(userVo.getToken())) {
				user.setToken(userVo.getToken());
			}
			if (!StrUtils.isBlankOrNull(userVo.getMaritalStatus())) {
				user.setMaritalStatus(userVo.getMaritalStatus());
			}
			if (!StrUtils.isBlankOrNull(userVo.getPersonalHabit())) {
				user.setPersonalHabit(userVo.getPersonalHabit());
			}
			if (!StrUtils.isBlankOrNull(userVo.getEducation())) {
				user.setEducation(userVo.getEducation());
			}
			if (!StrUtils.isBlankOrNull(userVo.getRemark())) {
				user.setRemark(userVo.getRemark());
			}
			if (!StrUtils.isBlankOrNull(String.valueOf(userVo.getMenuType()))) {
				user.setMenuType(userVo.getMenuType());
			}
			labUserDAO.updateLabUser(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateLabUser4Login(String userId, String sessionId, String ip) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userId);
		if (user != null) {
			user.setSessionId(sessionId);
			labUserDAO.updateLabUser(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateLabUser4Logout(String userId) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(userId);
		if (user != null) {
			user.setSessionId(null);
			labUserDAO.updateLabUser(user);
			return true;
		}
		return false;
	}

	@Override
	public List<LabUserVo> getLabUserListByFunId(String funId) throws GlobalException {
		List<LabUserVo> userVoList = new ArrayList<LabUserVo>();
		List<LabUserFun> ufList = labUserFunDAO.getLabUserFunListByFunId(funId);
		if (ufList != null) {
			for (LabUserFun sysUserFun : ufList) {
				LabUser user = sysUserFun.getUser();
				LabUserVo userVo = new LabUserVo();
				userVo.setId(user.getId());
				userVo.setName(user.getName());
				userVoList.add(userVo);
			}
		}
		return userVoList;
	}

	@Override
	public List<LabUserVo> getLabUserListByOrgId(String orgId) throws GlobalException {
		List<LabUserVo> userVoList = new ArrayList<LabUserVo>();
		List<LabUserOrg> uoList = labUserOrgDAO.getLabUserOrgListByOrgId(orgId);
		if (uoList != null) {
			for (LabUserOrg sysUserOrg : uoList) {
				LabUser user = sysUserOrg.getUser();
				LabUserVo userVo = new LabUserVo();
				userVo.setId(user.getId());
				userVo.setName(user.getName());
				userVo.setSex(user.getSex());
				userVo.setDuty(user.getDuty());
				userVo.setTechTitle(user.getTechTitle());
				userVoList.add(userVo);
			}
		}
		return userVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabUserFun(LabUserVo labUserVo) throws GlobalException {
		LabUser user = labUserDAO.getLabUser(labUserVo.getId());
		String orgids = labUserVo.getOrgId();
		String funids = labUserVo.getFunId();
		if (StrUtils.isBlankOrNull(orgids))
			return false;
		if (StrUtils.isBlankOrNull(funids))
			return false;
		try {
			String orgid[] = orgids.split(",");
			String funid[] = funids.split(",");
			for (String id : orgid) {
				if (id.trim() == "")
					continue;
				LabOrg org = labOrgDAO.getLabOrg(id.trim());
				for (String funId : funid) {
					if (funId.trim() == "" || funId.trim() == "0")
						continue;
					String hql = "FROM LabUserFun WHERE function.id='" + funId.trim() + "'";
					hql += " AND org.id='" + org.getId() + "'";
					List<LabUserFun> userFunList = labUserFunDAO.find(hql);
					if (userFunList != null && userFunList.size() > 0)
						continue;
					LabFunction fun = labFunctionDAO.getLabFunction(funId.trim());
					LabUserFun userFun = new LabUserFun();
					userFun.setUser(user);
					userFun.setOrg(org);
					userFun.setFunction(fun);
					userFun.setIsAdd("Y");
					userFun.setIsDelete("Y");
					userFun.setIsUpdate("Y");
					userFun.setIsShow("Y");
					userFun.setTenantStr(org.getTenantId());
					labUserFunDAO.addLabUserFun(userFun);
				}
			}
			return true;
		} catch (RuntimeException e) {
			Log4J.error("LabUserServiceImpl error...." + e.getMessage(), e);
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	@Override
	public boolean deleteLabUserFun4Add(LabRoleVo labRoleVo) throws GlobalException {
		boolean key = true;
		//1 先查看改角色新的功能 2：在UserRole中得到用户，角色 3：干掉userFun数据 重新加
		String funHql = "FROM LabRoleFun WHERE isDel='" + Constants_Common.N + "' AND role.id='" + labRoleVo.getId() + "'";
		List<LabRoleFun> listLabRoleFun = labRoleFunDAO.find(funHql);
		String hql = "FROM LabUserRole WHERE isDel='" + Constants_Common.N + "' AND role.id='" + labRoleVo.getId() + "'";
		List<LabUserRole> listLabUserRole = labUserRoleDAO.find(hql);
		if (listLabUserRole != null && listLabUserRole.size() > 0) {
			hql += " GROUP BY user.id,org.id";
			listLabUserRole = labUserRoleDAO.find(hql);
			String deleteHql = "FROM LabUserFun WHERE isDel='" + Constants_Common.N + "' AND role.id='" + labRoleVo.getId() + "'";
			List<LabUserFun> listLabUserFun = labUserFunDAO.find(deleteHql);
			if (listLabUserFun != null && listLabUserFun.size() > 0) {
				for (LabUserFun labUserFun : listLabUserFun) {
					labUserFunDAO.deleteLabUserFun(labUserFun);
				}
			}
			if (listLabUserRole != null && listLabUserRole.size() > 0) {
				try {
					for (LabUserRole labUserRole : listLabUserRole) {
						for (LabRoleFun labRoleFun : listLabRoleFun) {
							LabUserFun ufPo = labUserFunDAO.getLabUserFunByUserIdOrgIdfunId(labUserRole.getUser().getId(), labRoleFun.getFunction().getId(), labUserRole.getOrg().getId());
							if(ufPo!=null){
								continue;
							}
							LabUserFun labUserFun = new LabUserFun();
							labUserFun.setUser(labUserRole.getUser());
							labUserFun.setOrg(labUserRole.getOrg());
							labUserFun.setRole(labUserRole.getRole());
							labUserFun.setFunction(labRoleFun.getFunction());
							labUserFun.setIsAdd("Y");
							labUserFun.setIsDelete("Y");
							labUserFun.setIsUpdate("Y");
							labUserFun.setIsShow("Y");
							
							//labUserFun.setTenantId(tenantId);
							//labUserFun.setTenantStr(labUserRole.getOrg().getTenantId());
							if(null!=labRoleFun.getFunction().getDataStr()&&labRoleFun.getFunction().getDataStr().equals("user")){
								labUserFun.setTenantId(labUserFun.getOrg().getTenantId()+"*"+labUserFun.getUser().getId());
							}else if(null!=labRoleFun.getFunction().getDataStr()&&labRoleFun.getFunction().getDataStr().equals("org")){
								int level=0;
								try {
									level=Integer.valueOf(labRoleFun.getFunction().getValStr()==null?"0":labRoleFun.getFunction().getValStr());
								} catch (NumberFormatException e) {
									level=0;
									throw new GlobalException("" + e.getMessage());
								}
								List<LabOrgVo> orgDataList=getLabOrgList4Tree(labUserFun.getOrg(),level);
								if(orgDataList!=null&&orgDataList.size()>0){
									labUserFun.setTenantId(orgDataList.get(0).getTenantId());
								}
							}else if(null!=labRoleFun.getFunction().getDataStr()&&labRoleFun.getFunction().getDataStr().equals("role")){
								labUserFun.setTenantId(labUserFun.getOrg().getTenantId()+"*"+labRoleFun.getFunction().getValStr());
							}
							labUserFun.setTenantStr(labRoleFun.getFunction().getValStr());
							labUserFunDAO.addLabUserFun(labUserFun);
						}
					}
				} catch (Exception e) {
					key = false;
				}
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabUserFun(String userId,String funId) throws GlobalException {
		String hql="FROM LabUserFun WHERE user.id='"+userId+"'" +
				" AND( function.id='"+funId+"' or  function.parentFunction.id='"+funId+"' or  function.parentFunction.parentFunction.id='"+funId+"')";
		List<LabUserFun> list=labUserFunDAO.find(hql);
		if(list!=null){
			try {
				for (LabUserFun labUserFun : list) {
					labUserFunDAO.deleteLabUserFun(labUserFun);
				}
			} catch (RuntimeException e) {
				Log4J.error("LabUserServiceImpl"+e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserVo> getLabUserListByRoleName(String roleName) throws GlobalException {
		SessionContainer son = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		List<LabUserVo> listLabUserVo=new ArrayList<LabUserVo>();
		String ids[]=labOrgDAO.getLabOrgList4Sub(son.getOrgId());
		if(!StrUtils.isBlankOrNull(roleName)){
			String hql="FROM LabUserRole WHERE isDel='"+Constants_Common.N+"' AND role.name='"+roleName+"'";
			hql+=" AND org.id IN('"+StrUtils.join(ids, ",").replace(",", "','")+"')";
			hql+=" GROUP BY user.id";
			List<LabUserRole> listLabUserRole=labUserRoleDAO.find(hql);
			if(listLabUserRole!=null&&listLabUserRole.size()>0){
				for(LabUserRole labUserRole:listLabUserRole){
					LabUserVo vo=new LabUserVo();
					BeanUtils.copyProperties(labUserRole.getUser(), vo);
					listLabUserVo.add(vo);
				}
			}
		}
		return listLabUserVo;
	}
	@Override
	public LabUserFunVo getLabUserFun(String userId, String orgId, String funId) throws GlobalException {
		String hql="FROM LabUserFun WHERE user.id='"+userId+"' AND function.id='"+funId+"' AND org.id='"+orgId+"'";
		LabUserFun userfun=(LabUserFun)labUserFunDAO.find4All(hql, 0);
		if(userfun!=null){
			LabUserFunVo ufVo=new LabUserFunVo();
			ufVo.setDataStr(userfun.getFunction().getDataStr());
			ufVo.setTenantId(userfun.getTenantId());
			ufVo.setFunctionId(userfun.getFunction().getId());
			ufVo.setFunctionName(userfun.getFunction().getName());
			ufVo.setUserId(userfun.getUser().getId());
			ufVo.setUserName(userfun.getUser().getName());
			return ufVo;
		}
		return null;
	}
	
}

