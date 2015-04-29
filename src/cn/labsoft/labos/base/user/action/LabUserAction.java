package cn.labsoft.labos.base.user.action;

import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.service.ILabUserCredService;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.service.ILabUserTrainService;
import cn.labsoft.labos.base.user.vo.LabUserCredVo;
import cn.labsoft.labos.base.user.vo.LabUserFunVo;
import cn.labsoft.labos.base.user.vo.LabUserTrainRecordVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 用户信息操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabUserAction extends BaseAction {
	
	private static final long serialVersionUID = 8896349528079537224L;
	private ILabQueryService labQueryService;
	private ILabUserService labUserService;
	private ILabOrgService labOrgService;
	private ILabRoleService labRoleService;
	private ILabFunctionService labFunctionService;
	private ILabUploadService labUploadService;
	private ILabCodeService labCodeService;
	private ILabUserCredService labUserCredService;
	private ILabUserTrainService labUserTrainService;
	private LabUserVo labUserVo;
	private LabRoleVo labRoleVo;
	private LabQueryVo labQueryVo;
	private LabUserFunVo labUserFunVo;
	/**
	 * 高级查询Service注入
	 * @param labQueryService
	 */
	@Resource
	public void setLabQueryService(ILabQueryService labQueryService) {
		this.labQueryService = labQueryService;
	}
	/**
	 * 用户管理Service注入
	 * @param labUserService
	 */
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	/**
	 * 组织管理Service注入
	 * @param labOrgService
	 */
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	/**
	 * 角色管理Service注入
	 * @param labRoleService
	 */
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}
	/**
	 *功能管理Service注入
	 * @param labFunctionService
	 */
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	/**
	 * 上传管理Service注入
	 * @param labUploadService
	 */
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	/**
	 * 公共代码Service注入
	 * @param labCodeService
	 */
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	/**
	 * 用户证书Service注入
	 * @param labUserCredService
	 */
	@Resource
	public void setLabUserCredService(ILabUserCredService labUserCredService) {
		this.labUserCredService = labUserCredService;
	}
	/**
	 * 用户培训Service注入
	 * @param labUserTrainService
	 */
	@Resource
	public void setLabUserTrainService(ILabUserTrainService labUserTrainService) {
		this.labUserTrainService = labUserTrainService;
	}
	public LabQueryVo getLabQueryVo() {
		return labQueryVo;	
	}
	public void setLabQueryVo(LabQueryVo labQueryVo) {
			this.labQueryVo = labQueryVo;
	}
	
	public LabUserFunVo getLabUserFunVo() {
		return labUserFunVo;
	}
	public void setLabUserFunVo(LabUserFunVo labUserFunVo) {
		this.labUserFunVo = labUserFunVo;
	}
	/**
	 * 用户查询
	 * @throws GlobalException
	 */
	public String listUserQuery() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("402881ea45a725f60145a72b4e580004");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
		if(labQueryVo.getQueryType().equals(Constants_Common.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	public LabRoleVo getLabRoleVo() {
		return labRoleVo;
	}
	public void setLabRoleVo(LabRoleVo labRoleVo) {
		this.labRoleVo = labRoleVo;
	}

	public LabUserVo getLabUserVo() {
		return labUserVo;
	}

	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}
	/**
	 * 获取用户列表
	 * @throws GlobalException
	 */
	public String listLabUser() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
			pageResult.setOrder(PageResult.ORDER_ASC);
			pageResult.setOrderColumn("sort");
		}
		if (getSessionContainer().getType().equals(Constants_Common.FRONT)) {
			labUserVo.setUserType(getSessionContainer().getType());
		}
		try{
			pageResult = labUserService.getLabUserPR(labUserVo, pageResult);
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		return LIST;
	}

	/**
	 * 资源管理-用户列表
	 * @throws GlobalException
	 */
	public String listLabUser4Org() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		pageResult = labUserService.getLabUserPR4Org(labUserVo, pageResult);
		return LIST;
	}

	/**
	 * 跳到增加用户信息
	 * @throws GlobalException
	 */
	public String preAddLabUser() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
			labUserVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labUserVo.getUuid(), "userLogo");
		if (null != loadList && loadList.size() > 0) {
			labUserVo.setLogo(loadList.get(0).getPath());
		}
		List<LabOrgVo> orgList = labOrgService.getLabOrgTree();
		orgList = labOrgService.getUsedLabOrgList(orgList);
		setAttribute("orgList", orgList);
		List<LabOrgVo> orgList4Back = labOrgService.getLabOrgVoByRank("0");
		setAttribute("orgList4Back", orgList4Back);

		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode("YHGW");
		getRequest().setAttribute("listLabCode", listLabCode);
		if (getSessionContainer().getType().equals(Constants_Common.BACK)) {
			labUserVo.setUserType(Constants_Common.BACK);
			return "preAdd4Admin";
		} else {
			labUserVo.setUserType(Constants_Common.FRONT);
			return PREADD;
		}
	}

	/**
	 * 增加一条用户信息
	 * @throws GlobalException
	 */
	public String addLabUser() throws GlobalException {
		labUserVo = labUserService.addLabUser(labUserVo);
		return ADD;
	}

	// 删除用户的时候先要把与该用户有多对多关系的表中的数据删掉，再删除该用户
	/**
	 * 删除单条用户信息
	 */
	public String deleteLabUser() throws GlobalException {
		labUserService.delLabUser(labUserVo.getIds());
		return DELETE;
	}

	/**
	 * 跳到修改用户信息页面
	 * @throws GlobalException
	 */
	public String preUpdateLabUser() throws GlobalException {
		if (labUserVo == null)
			labUserVo = new LabUserVo();
		labUserVo = labUserService.getLabUser4AllInfo(labUserVo.getId());
		List<LabOrgVo> orgList = labOrgService.getLabOrgTree();
		orgList = labOrgService.getUsedLabOrgList(orgList);
		setAttribute("orgList", orgList);
		List<LabOrgVo> orgList4Back = labOrgService.getLabOrgVoByRank("0");
		setAttribute("orgList4Back", orgList4Back);

		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode("YHGW");
		getRequest().setAttribute("listLabCode", listLabCode);
		// 获取资质证书
		LabUserCredVo labUserCredVo = new LabUserCredVo();
		labUserCredVo.setUserId(labUserVo.getId());
		List<LabUserCredVo> userCredList = labUserCredService.getLabUserCredList(labUserCredVo);
		setAttribute("userCredList", userCredList);
		// 获取培训列表
		LabUserTrainRecordVo recordVo = new LabUserTrainRecordVo();
		recordVo.setLabUserId(labUserVo.getId());
		List<LabUserTrainRecordVo> trainRecordList = labUserTrainService.getLabUserTrainRecordList(recordVo);
		setAttribute("trainRecordList", trainRecordList);

		if (getSessionContainer().getType().equals(Constants_Common.BACK)) {
			if (null == labUserVo.getUserType() && "".equals(labUserVo.getUserType())) {
				labUserVo.setUserType(Constants_Common.BACK);
			}
			return "preUpdate4Admin";
		} else {
			labUserVo.setUserType(Constants_Common.FRONT);
			return PREUPDATE;
		}
	}

	/**
	 * 修改用户信息(管理级别)
	 * @throws GlobalException
	 */
	public String updateLabUser() throws GlobalException {
		labUserService.updateLabUser(labUserVo);
		return UPDATE;
	}

	/**
	 * 修改用户信息(资源管理)
	 * @throws GlobalException
	 */
	public String updateLabUser4Org() throws GlobalException {
		labUserService.updateLabUser4Org(labUserVo);
		return UPDATE;
	}

	/**
	 * 跳到修改权限信息页面
	 * @throws GlobalException
	 */
	public String preUpdateLabUser4fun() throws GlobalException {
		if (labUserVo == null)
			labUserVo = new LabUserVo();
		labUserVo = labUserService.getLabUser4FunByUserId(labUserVo.getId());
		List<LabOrgVo> orgList = labOrgService.getLabOrgTree();
		orgList = labOrgService.getUsedLabOrgList(orgList);
		setAttribute("orgList", orgList);
		return PREUPDATE;
	}

	/**
	 * 修改用户修改权限(管理级别)
	 * @throws GlobalException
	 */
	public String updateLabUser4fun() throws GlobalException {
		labUserService.updateLabUser4Fun(labUserVo);
		return UPDATE;
	}
	/**
	 * 修改权限-删除用户权限(管理级别)
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String deleteLabUserFun() throws GlobalException, IOException {
		if(labUserFunVo==null)labUserFunVo=new LabUserFunVo();
		boolean flag=labUserService.deleteLabUserFun(labUserFunVo.getUserId(),labUserFunVo.getFunctionId());
		outPutString(flag+"");
		return NONE;
	}
	/**
	 * 赋权页面-增加功能
	 * @throws GlobalException
	 */
	public String addLabUserFun() throws GlobalException {
		boolean flag = labUserService.addLabUserFun(labUserVo);
		if (flag) {
			messageInfo = getText("user.add.success");
		} else {
			messageInfo = getText("user.add.fail");
		}
		return ADD;
	}
	/**
	 * 显示所要查看的用户信息
	 * @throws GlobalException
	 */
	public String showLabUser() throws GlobalException {
		if (labUserVo == null)
			labUserVo = new LabUserVo();
		labUserVo = labUserService.getLabUser(labUserVo.getId());
		// 获取资质证书
		LabUserCredVo labUserCredVo = new LabUserCredVo();
		labUserCredVo.setUserId(labUserVo.getId());
		List<LabUserCredVo> userCredList = labUserCredService.getLabUserCredList(labUserCredVo);
		setAttribute("userCredList", userCredList);
		// 获取培训列表
		LabUserTrainRecordVo recordVo = new LabUserTrainRecordVo();
		recordVo.setLabUserId(labUserVo.getId());
		List<LabUserTrainRecordVo> trainRecordList = labUserTrainService.getLabUserTrainRecordList(recordVo);
		setAttribute("trainRecordList", trainRecordList);
		return SHOW;
	}

	/**
	 * 根据登录用户名验证用户Ip信息
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String ajax4IpByLoginName() throws GlobalException, IOException {
		String loginName = getParameter("loginName");
		boolean flag = labUserService.isExist4UserAndIp(loginName, getSessionContainer().getIp());
		outPutString(flag + "");
		return NONE;
	}

	/**
	 * 判断登陆名称是否重复
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String isExistLoginName() throws GlobalException, IOException {
		String LoginName = getParameter("LoginName");
		if (StrUtils.isBlankOrNull(LoginName))
			return NONE;
		boolean flag = labUserService.isExist4LoginName(LoginName);
		outPutString(flag + "");
		return NONE;
	}

	/**
	 * 验证原始密码
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String isTruePassword() throws GlobalException, IOException {
		String password = getParameter("password");
		String id = getParameter("id");
		if (StrUtils.isBlank(password))
			return NONE;
		boolean flag = labUserService.isTrue4Pwd(id, password);
		outPutString(flag + "");
		return NONE;
	}

	/**
	 * 用户角色赋权
	 * @throws GlobalException
	 */
	public String showLabRole4Select() throws GlobalException {
		if (null == labRoleVo)
			labRoleVo = new LabRoleVo();
		List<LabRoleVo> roleList = labRoleService.getLabRoleList(labRoleVo);
		setAttribute("roleList", roleList);
		return LIST;
	}

	/**
	 * 获取用户列表
	 * @throws GlobalException
	 */
	public String showLabUser4Select() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		List<LabUserVo> userList = labUserService.getLabUserList(labUserVo);
		setAttribute("userList", userList);
		return LIST;
	}
	/**
	 * 跳转到获取功能树页面
	 * @throws GlobalException
	 */
	public String preTreeLabFunction() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		labUserVo = labUserService.getLabUser(labUserVo.getId());
		return PRETREE;
	}
	/**
	 * 获取功能树
	 * @throws GlobalException
	 */
	public void treeLabFunction() throws GlobalException {
		StringBuffer treeBuffer = labFunctionService.getLabFunctionListTree(getParameter("treeNid"));
		outPrint(getResponse(), treeBuffer);
	}

	/**
	 * 个人中心-基本修改
	 * @throws GlobalException
	 */
	public String preUpdateLabUser4Center() throws GlobalException {
		labUserVo = labUserService.getLabUser(labUserVo.getId());
		// 风格
		List<LabCodeVo> listLabCode4CDFG = labCodeService.getLabCodeByTypeCode("CDFG");
		getRequest().setAttribute("listLabCode4CDFG", listLabCode4CDFG);
		return PREUPDATE;
	}
	/**
	 * 个人中心-权限查看
	 * @throws GlobalException
	 */
	public String showLabUser4Center1() throws GlobalException {
		labUserVo = labUserService.getLabUser4FunByUserId(labUserVo.getId());
		return SHOW;
	}
	/**
	 * 个人中心-考勤查看
	 * @throws GlobalException
	 */
	public String showLabUser4Center2() throws GlobalException {
		labUserVo = labUserService.getLabUser4FunByUserId(labUserVo.getId());
		return SHOW;
	}
	/**
	 * 个人中心-其他查看
	 * @throws GlobalException
	 */
	public String showLabUser4Center3() throws GlobalException {
		labUserVo = labUserService.getLabUser(labUserVo.getId());
		// 获取资质证书
		LabUserCredVo labUserCredVo = new LabUserCredVo();
		labUserCredVo.setUserId(labUserVo.getId());
		List<LabUserCredVo> userCredList = labUserCredService.getLabUserCredList(labUserCredVo);
		setAttribute("userCredList", userCredList);
		// 获取培训列表
		LabUserTrainRecordVo recordVo = new LabUserTrainRecordVo();
		recordVo.setLabUserId(labUserVo.getId());
		List<LabUserTrainRecordVo> trainRecordList = labUserTrainService.getLabUserTrainRecordList(recordVo);
		setAttribute("trainRecordList", trainRecordList);
		return SHOW;
	}
	/**
	 * 个人中心-修改
	 * @throws GlobalException
	 */
	public String updateLabUser4Center() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		labUserService.updateLabUser4self(labUserVo);
		return UPDATE;
	}
}
