package cn.labsoft.labos.business.science.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.science.service.ILabSciAchievementService;
import cn.labsoft.labos.business.science.vo.LabSciAchievementVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabSciAchievementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ILabSciAchievementService labSciAchievementService;
	private ILabCodeService labCodeService;
	private ILabUserService labUserService;
	private LabSciAchievementVo labSciAchievementVo;
	private LabUserVo labUserVo;

	@Resource
	public void setLabSciAchievementService(
			ILabSciAchievementService labSciAchievementService) {
		this.labSciAchievementService = labSciAchievementService;
	}
	
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	

	public LabSciAchievementVo getLabSciAchievementVo() {
		return labSciAchievementVo;
	}

	public void setLabSciAchievementVo(LabSciAchievementVo labSciAchievementVo) {
		this.labSciAchievementVo = labSciAchievementVo;
	}
	
	public LabUserVo getLabUserVo() {
		return labUserVo;
	}

	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}

	private void init() throws GlobalException {
		if (null == labSciAchievementVo) {
			labSciAchievementVo = new LabSciAchievementVo();
		}
	}
	
	private String getSkipResult()throws GlobalException
	{
		List<LabCodeVo> typeList=labCodeService.getLabCodeByTypeCode(labSciAchievementVo.getType());
		setAttribute("typeList", typeList);
		List<LabCodeVo> searchList=labCodeService.getLabCodeByTypeCode("YJXK");
		setAttribute("searchList", searchList);
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getType())&&Constants_Business.PAPER.equals(labSciAchievementVo.getType())) {
			List<LabCodeVo> qkList=labCodeService.getLabCodeByTypeCode("FBQK");
			setAttribute("qkList", qkList);
			return Constants_Business.PAPER;
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getType())&&Constants_Business.BOOK.equals(labSciAchievementVo.getType())) {
			List<LabCodeVo> qkList=labCodeService.getLabCodeByTypeCode("CBS");
			setAttribute("qkList", qkList);
			return Constants_Business.BOOK;
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getType())&&Constants_Business.AWARD.equals(labSciAchievementVo.getType())) {
			List<LabCodeVo> qkList=labCodeService.getLabCodeByTypeCode("FJZZ");
			setAttribute("qkList", qkList);
			List<LabCodeVo> hjdjList=labCodeService.getLabCodeByTypeCode("HJDJ");
			setAttribute("hjdjList", hjdjList);
			return Constants_Business.AWARD;
		}
		if (!StrUtils.isBlankOrNull(labSciAchievementVo.getType())&&Constants_Business.PATENT.equals(labSciAchievementVo.getType())) {
			return Constants_Business.PATENT;
		}
		return "";
	}
	
	public String listLabSciAchievement()throws GlobalException
	{
		init();
		pageResult=labSciAchievementService.getLabSciAchievementPR(pageResult, labSciAchievementVo);
		return getSkipResult();
	}
	
	public String preAddLabSciAchievement()throws GlobalException
	{
		init();
		return getSkipResult();
	}
	
	public String addLabSciAchievement()throws GlobalException
	{
		init();
		labSciAchievementService.addLabSciAchievement(labSciAchievementVo);
		return ADD;
	}
	
	public String preUpdateLabSciAchievement()throws GlobalException
	{
		init();
		labSciAchievementVo=labSciAchievementService.getLabSciAchievementVo(labSciAchievementVo);
		return getSkipResult();
	}
	
	public String updateLabSciAchievement()throws GlobalException
	{
		init();
		labSciAchievementService.updateLabSciAchievement(labSciAchievementVo);
		return UPDATE;
	}
	
	public String deleteLabSciAchievement()throws GlobalException
	{
		init();
		labSciAchievementService.deleteLabSciAchievement(labSciAchievementVo.getIds());
		return DELETE;
	}
	
	public String showLabSciAchievement()throws GlobalException
	{
		init();
		labSciAchievementVo=labSciAchievementService.getLabSciAchievementVo(labSciAchievementVo);
		return getSkipResult();
	}
	
	public String showLabUser4Select()throws GlobalException
	{
		if (null==labUserVo) {
			labUserVo=new LabUserVo();
		}
		List<LabUserVo> userList=labUserService.getLabUserList(labUserVo);
		setAttribute("userList", userList);
		return SHOW;
	}

	


}
