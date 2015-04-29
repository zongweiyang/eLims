package cn.labsoft.labos.common.component.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;

@Controller
@Scope("prototype")
public class LabUserChooseAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private ILabCodeService labCodeService; 
	private ILabUserService labUserService;
	private ILabOrgService labOrgService;
	private ILabRoleService labRoleService;
	
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}
	
	private List<LabOrgVo> labOrgVoList;
	private List<LabRoleVo> labRoleVoList;
	private List<LabUserVo> labUserVoList;
	private LabUserVo labUserVo;
	private LabOrgVo labOrgVo;
	private LabRoleVo labRoleVo;
	
	public List<LabOrgVo> getLabOrgVoList() {
		return labOrgVoList;
	}
	public void setLabOrgVoList(List<LabOrgVo> labOrgVoList) {
		this.labOrgVoList = labOrgVoList;
	}
	public List<LabRoleVo> getLabRoleVoList() {
		return labRoleVoList;
	}
	public void setLabRoleVoList(List<LabRoleVo> labRoleVoList) {
		this.labRoleVoList = labRoleVoList;
	}
	public List<LabUserVo> getLabUserVoList() {
		return labUserVoList;
	}
	public void setLabUserVoList(List<LabUserVo> labUserVoList) {
		this.labUserVoList = labUserVoList;
	}
	public LabUserVo getLabUserVo() {
		return labUserVo;
	}
	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}
	public LabOrgVo getLabOrgVo() {
		return labOrgVo;
	}
	public void setLabOrgVo(LabOrgVo labOrgVo) {
		this.labOrgVo = labOrgVo;
	}
	public LabRoleVo getLabRoleVo() {
		return labRoleVo;
	}
	public void setLabRoleVo(LabRoleVo labRoleVo) {
		this.labRoleVo = labRoleVo;
	}
	
	//取组织树
	public void ajaxLabOrgTree() throws GlobalException,IOException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		labOrgVoList = labOrgService.getLabOrgList(labOrgVo);
		StringBuffer jsonTree = new StringBuffer();
		jsonTree.append("[");
		for (LabOrgVo labOrg : labOrgVoList) {
			jsonTree.append("{\"id\":\""+labOrg.getId()+"\",\"name\":\""+labOrg.getName()+"\",\"pId\":\""+labOrg.getParentId()+"\"},");
		}
		if (jsonTree.length() > 1) {
			jsonTree.replace(jsonTree.length() - 1, jsonTree.length(), "");
		}
		jsonTree.append("]");
		outPrint(getResponse(), jsonTree);
	}
	//取岗位
	public void ajaxJobsTree() throws GlobalException,IOException {
		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode("YHGW");
		StringBuffer jsonTree = new StringBuffer();
		jsonTree.append("[");
		for (LabCodeVo labCodeVo : listLabCode) {
			jsonTree.append("{\"id\":\""+labCodeVo.getId()+"\",\"name\":\""+labCodeVo.getName()+"\",\"pId\":-1},");
		}
		if (jsonTree.length() > 1) {
			jsonTree.replace(jsonTree.length() - 1, jsonTree.length(), "");
		}
		jsonTree.append("]");
		outPrint(getResponse(), jsonTree);
	}
	//取角色
	public void ajaxLabRoleTree() throws GlobalException,Exception {
		if (null == labRoleVo)
			labRoleVo = new LabRoleVo();
		if(null==labRoleVoList){
			labRoleVoList = new ArrayList<LabRoleVo>();
		}
		labRoleVoList = labRoleService.getLabRoleList(labRoleVo);
		StringBuffer jsonTree = new StringBuffer();
		jsonTree.append("[");
		for (LabRoleVo labRole : labRoleVoList) {
			jsonTree.append("{\"id\":\""+labRole.getId()+"\",\"name\":\""+labRole.getName()+"\",\"pId\":-1},");
		}
		if (jsonTree.length() > 1) {
			jsonTree.replace(jsonTree.length() - 1, jsonTree.length(), "");
		}
		jsonTree.append("]");
		outPrint(getResponse(), jsonTree);
	}
	//查询组织下的所有用户
	public void ajaxListLabUser4Org()throws GlobalException{
		labUserVoList = labUserService.getLabUserListByOrgId(labOrgVo.getId());
		ajax(labUserVoList);
	}
	//查询岗位下的所有用户
	public void ajaxListLabUser4Jobs()throws GlobalException{
		labUserVoList = labUserService.getLabUserList(labUserVo);
		ajax(labUserVoList);
	}
	//查询角色下的所有用户
	public void ajaxListLabUser4Role()throws GlobalException{
		labUserVoList = labUserService.getLabUserListByRoleName(labRoleVo.getName());
		ajax(labUserVoList);
	}
	//根据Id查询用户
	public void ajaxLabUser4Select()throws GlobalException{
		if(null==labUserVo)labUserVo=new LabUserVo();
		labUserVoList = labUserService.getLabUserList(labUserVo);
		ajax(labUserVoList);
	}
}
