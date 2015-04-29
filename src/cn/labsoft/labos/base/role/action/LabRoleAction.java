package cn.labsoft.labos.base.role.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleFunVo;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabRoleAction extends BaseAction {
	
	private ILabRoleService labRoleService ;
	private ILabFunctionService labFunctionService;
	private ILabUserService labUserService ;
	private LabRoleVo labRoleVo;
	private LabRoleFunVo labRoleFunVo;
    private List<LabFunctionVo> labFunctionVoList;
    private ILabCodeService labCodeService;
    @Resource
    public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}

	public LabRoleVo getLabRoleVo() {
		return labRoleVo;
	}

	public void setLabRoleVo(LabRoleVo labRoleVo) {
		this.labRoleVo = labRoleVo;
	}


	public LabRoleFunVo getLabRoleFunVo() {
		return labRoleFunVo;
	}

	public void setLabRoleFunVo(LabRoleFunVo labRoleFunVo) {
		this.labRoleFunVo = labRoleFunVo;
	}

	// 查看下面是否有子类 0可以删除 1 不可以删除
	public String isDeleteInIds() throws GlobalException, IOException {
		String ids = getParameter("ids");
		if (StringUtils.isBlank(ids))
			return NONE;
		String id[] = ids.split(",");
		String flag = labRoleService.checkDeleteBatchRole(id);
		outPutString(flag);
		return NONE;
	}

	// 判断角色名称是否重复
	public String isRequiredRoleName() throws GlobalException, IOException {
		String name = getParameter("roleName");
		if (StringUtils.isBlank(name))
			return NONE;
		String flag = labRoleService.isExist4RoleName(name);
		outPutString(flag);
		return NONE;
	}
	
	// 获取角色列表
	public String listLabRole() throws GlobalException {
		if( null == labRoleVo ){
			labRoleVo = new LabRoleVo();
			pageResult.setOrder(PageResult.ORDER_ASC);
			pageResult.setOrderColumn("sort");
		}
		pageResult = labRoleService.getLabRolePR(labRoleVo, pageResult);

		return LIST;
	}

	// 删除角色信息
	public String deleteLabRole() throws GlobalException {
		String[] ids = new String[50];
		ids = labRoleVo.getIds();
		labRoleService.updateLabRole4Del(ids);
		return DELETE;
	}

	// 跳到修改角色信息页面
	public String preUpdateLabRole() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		labRoleVo = labRoleService.getLabRole(labRoleVo.getId());
		if(StrUtils.isBlankOrNull(String.valueOf(labRoleVo.getSort()))){
			List<LabRoleVo> labRoleVoList = labRoleService.getAllLabRoleList();
			labRoleVo.setSort(labRoleVoList.size()+1);
		}
		List<LabCodeVo> portletList = labCodeService.getLabCodeByTypeCode("HOME");
		setAttribute("portletList", portletList);
		return PREUPDATE;
	}

	// 修改角色信息
	public String updateLabRole() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		labRoleService.updateLabRole(labRoleVo);
		return UPDATE;
	}

	// 跳到增加角色信息
	public String preAddLabRole() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		List<LabRoleVo> labRoleVoList = labRoleService.getAllLabRoleList();
		labRoleVo.setSort(labRoleVoList.size()+1);
		return PREADD;
	}

	// 增加一条角色信息
	public String addLabRole() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		labRoleService.addLabRole(labRoleVo);
		return ADD;
	}

	// 显示角色的详细信息
	public String showLabRole() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		labRoleVo = labRoleService.getLabRole(labRoleVo.getId());
		return SHOW;
	}
	
	public String ajax4ListLabRole() throws GlobalException {
		if( null == labRoleVo ) labRoleVo = new LabRoleVo();
		List<LabRoleVo> roleList= labRoleService.getLabRoleList(labRoleVo);
		setAttribute("roleList", roleList);
		return LIST;
	}
	public String showLabRoleFun4Select() throws GlobalException {
		if( null == labRoleVo )
			labRoleVo = new LabRoleVo();
		getRequest().getSession().setAttribute("roleId", labRoleVo.getId());
		return PRETREE;
	}
	public void treeLabRoleFun()throws GlobalException {
		StringBuffer treeBuffer=labFunctionService.getLabFunctionCheckTree((String) getRequest().getSession().getAttribute("roleId"),getParameter("treeNid"));
		outPrint(getResponse(),treeBuffer);
	}
	
	// 赋权
	public String updateLabRoleTree() throws GlobalException {
		if( null == labRoleVo ) labRoleVo = new LabRoleVo();
		labRoleVo.setId((String)getRequest().getSession().getAttribute("roleId"));
		
		boolean ifsuccess = labRoleService.addLabRoleFuns((String)getRequest().getSession().getAttribute("roleId"), labRoleVo.getFunIds());
		if(!StrUtils.isBlankOrNull(labRoleVo.getIsSynchro())&&labRoleVo.getIsSynchro().equals(Constants_Common.TRUE)){
			labUserService.deleteLabUserFun4Add(labRoleVo);
		}
		if (ifsuccess == true) {
			setAttribute("Msg", "<font color='red'>"+getText("role.config.success")+"</font>");
		} else {
			setAttribute("Msg", "<font color='red'>"+getText("role.config.fail")+"</font>");
		}
		return UPDATE;
	}
	public void ajaxLabRole4Json() throws GlobalException, IOException{
		
		outPutString(labRoleService.getLabRoleList4Json());
		
	}
	public List<LabFunctionVo> getLabFunctionVoList() {
		return labFunctionVoList;
	}

	public void setLabFunctionVoList(List<LabFunctionVo> labFunctionVoList) {
		this.labFunctionVoList = labFunctionVoList;
	}
}
