package cn.labsoft.labos.business.science.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.science.service.ILabSciLearnService;
import cn.labsoft.labos.business.science.vo.LabSciLearnVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabSciLearnAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ILabSciLearnService labSciLearnService;
	private ILabCodeService labCodeService;
	private ILabOrgService labOrgService;
	private ILabUserService labUserService;
	private LabSciLearnVo labSciLearnVo;
	private LabOrgVo labOrgVo;

	public LabSciLearnVo getLabSciLearnVo() {
		return labSciLearnVo;
	}

	public void setLabSciLearnVo(LabSciLearnVo labSciLearnVo) {
		this.labSciLearnVo = labSciLearnVo;
	}

	@Resource
	public void setLabSciLearnService(ILabSciLearnService labSciLearnService) {
		this.labSciLearnService = labSciLearnService;
	}

	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}

	public LabOrgVo getLabOrgVo() {
		return labOrgVo;
	}

	public void setLabOrgVo(LabOrgVo labOrgVo) {
		this.labOrgVo = labOrgVo;
	}

	private void init() throws GlobalException {
		if (null == labSciLearnVo) {
			labSciLearnVo = new LabSciLearnVo();
		}
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "1".equals(labSciLearnVo.getType())) {
			messageInfo = "HYLX";
		} else if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "2".equals(labSciLearnVo.getType())) {
			messageInfo = "JZLX";
		} else if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "3".equals(labSciLearnVo.getType())) {
			messageInfo = "CCLX";
		}
		List<LabCodeVo> codeList = labCodeService
				.getLabCodeByTypeCode(messageInfo);
		setAttribute("typeList", codeList);
		List<LabCodeVo> roomList = labCodeService.getLabCodeByTypeCode("FJ");
		setAttribute("roomList", roomList);
	}

	private String getResult() throws GlobalException {
		if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "1".equals(labSciLearnVo.getType())) {
			return "HY";
		} else if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "2".equals(labSciLearnVo.getType())) {
			return "JZ";
		} else if (!StrUtils.isBlankOrNull(labSciLearnVo.getType())
				&& "3".equals(labSciLearnVo.getType())) {
			return "CC";
		}
		return "";
	}

	public String listLabSciLearn() throws GlobalException {
		init();
		pageResult = labSciLearnService.getLabSciLearnPR(labSciLearnVo,
				pageResult);
		List<LabCodeVo> codeList = labCodeService
				.getLabCodeByTypeCode(messageInfo);
		setAttribute("typeList", codeList);
		return getResult();
	}

	public String preAddLabSciLearn() throws GlobalException {
		init();
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode(messageInfo);
		setAttribute("typeList", codeList);
		return getResult();
	}

	public String addLabSciLearn() throws GlobalException {
		init();
		labSciLearnService.addLabSciLearn(labSciLearnVo);
		return ADD;
	}

	public String preUpdateLabSciLearn() throws GlobalException {
		init();
		labSciLearnVo = labSciLearnService.getLabSciLearn(labSciLearnVo);
		return getResult();
	}

	public String updateLabSciLearn() throws GlobalException {
		init();
		labSciLearnService.updateLabSciLearn(labSciLearnVo);
		return UPDATE;
	}

	public String deleteLabSciLearn() throws GlobalException {
		init();
		labSciLearnService.deleteLabSciLearn(labSciLearnVo.getIds());
		return DELETE;
	}

	public String showLabSciLearn() throws GlobalException {
		init();
		labSciLearnVo = labSciLearnService.getLabSciLearn(labSciLearnVo);
		return getResult();
	}

	public String showLabOrg4Select() throws GlobalException {
		if( null == labOrgVo ) labOrgVo = new LabOrgVo();
		getRequest().getSession().setAttribute("orgId", labOrgVo.getId());
		return PRETREE;
	}
	public void treeLabOrg()throws GlobalException {
		if( null == labOrgVo ) labOrgVo = new LabOrgVo();
		StringBuffer treeBuffer= labOrgService.getLabOrgCheckTree((String) getRequest().getSession().getAttribute("orgId"),getParameter("treeNid"));
		outPrint(getResponse(),treeBuffer);
	}

	public String showLabUser4Select() throws GlobalException {
		String[] ids = labSciLearnVo.getLabOrgId().split(",");
		List<LabUserVo> allUserList = new ArrayList<LabUserVo>();
		if (ids.length > 0) {
			for (String id : ids) {
				List<LabUserVo> userList = labUserService.getLabUserListByOrgId(id);
				allUserList.addAll(userList);
			}
		}
		setAttribute("userList",allUserList);
		return SHOW;
	}

}
