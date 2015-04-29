package cn.labsoft.labos.source.supplier.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.supplier.service.ILabSupEvaluateService;
import cn.labsoft.labos.source.supplier.vo.LabSupEvaluateVo;
import cn.labsoft.labos.utils.DateUtils;

@Controller
@Scope("prototype")
public class LabSupEvaluateAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabSupEvaluateService labSupEvaluateService;
	private ILabCodeService labCodeService;
	private LabSupEvaluateVo labSupEvaluateVo;

	@Resource
	public void setLabSupEvaluateService(
			ILabSupEvaluateService labSupEvaluateService) {
		this.labSupEvaluateService = labSupEvaluateService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	public LabSupEvaluateVo getLabSupEvaluateVo() {
		return labSupEvaluateVo;
	}

	public void setLabSupEvaluateVo(LabSupEvaluateVo labSupEvaluateVo) {
		this.labSupEvaluateVo = labSupEvaluateVo;
	}

	public String listLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		pageResult = labSupEvaluateService.getLabSupEvaluatePR(
				labSupEvaluateVo, pageResult);
		return LIST;
	}

	public String preAddLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		setAttribute("serverList", serverList);
		labSupEvaluateVo.setEvaluateDate(DateUtils.getCurrDateStr());
		labSupEvaluateVo.setPerson(getSessionContainer().getUserName());
		return PREADD;
	}

	public String addLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		labSupEvaluateVo = labSupEvaluateService
				.addLabSupEvaluate(labSupEvaluateVo);
		return ADD;
	}

	public String preUpdateLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		labSupEvaluateVo = labSupEvaluateService
				.getLabSupEvaluate(labSupEvaluateVo.getId());
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		setAttribute("serverList", serverList);
		return PREUPDATE;
	}

	public String updateLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		boolean key = labSupEvaluateService
				.updateLabSupEvaluate(labSupEvaluateVo);
		return UPDATE;
	}

	public String showLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		labSupEvaluateVo = labSupEvaluateService
				.getLabSupEvaluate(labSupEvaluateVo.getId());
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		setAttribute("serverList", serverList);
		return SHOW;
	}

	public String deleteLabSupEvaluate() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		labSupEvaluateService.deleteLabSupEvaluate(labSupEvaluateVo.getIds());
		return DELETE;
	}

	public String updateLabSupEvaluate4Del() throws GlobalException {
		if (null == labSupEvaluateVo)
			labSupEvaluateVo = new LabSupEvaluateVo();
		labSupEvaluateService.updateLabSupEvaluate4Del(labSupEvaluateVo
				.getIds());
		return DELETE;
	}

}
