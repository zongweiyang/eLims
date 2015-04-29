package cn.labsoft.labos.business.science.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.business.science.service.ILabSciFundsService;
import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabSciFundsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private LabSciFundsVo labSciFundsVo;
	private LabQueryVo labQueryVo;
	
	
	private ILabSciFundsService labSciFundsService;
	private ILabUploadService labUploadService;
	private ILabQueryService labQueryService;

	public LabSciFundsVo getLabSciFundsVo() {
		return labSciFundsVo;
	}

	public void setLabSciFundsVo(LabSciFundsVo labSciFundsVo) {
		this.labSciFundsVo = labSciFundsVo;
	}

	@Resource
	public void setLabSciFundsService(ILabSciFundsService labSciFundsService) {
		this.labSciFundsService = labSciFundsService;
	}
	
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}

	@Resource
	public void setLabQueryService(ILabQueryService labQueryService) {
		this.labQueryService = labQueryService;
	}

	private void init()
	{
		if (null==labSciFundsVo) {
			labSciFundsVo=new LabSciFundsVo();
		}
	}

	public String listLabSciFunds()throws GlobalException
	{ 
		init();
		pageResult=labSciFundsService.getLabSciFundsPR(labSciFundsVo, pageResult);
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"2".equals(labSciFundsVo.getType())) {
			return "list1";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"0".equals(labSciFundsVo.getType())) {
			return "list2";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"1".equals(labSciFundsVo.getType())) {
			return "list3";
		}
		return "";
	}
	
	public String listLabSciFundsQuery() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("402881fa47008407014700993475000f");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
		if(labQueryVo.getQueryType().equals(Constants_Business.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	
	public String preAddLabSciFunds()throws GlobalException
	{
		init();
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"0".equals(labSciFundsVo.getType())) {
			return "preAdd2";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"1".equals(labSciFundsVo.getType())) {
			return "preAdd3";
		}
		return "";
	}
	
	public String addLabSciFunds()throws GlobalException
	{
		init();
		labSciFundsVo=labSciFundsService.addLabSciFunds(labSciFundsVo);
		return ADD;
	}
	
	public String preUpdateLabSciFunds()throws GlobalException
	{
		init();
		labSciFundsVo=labSciFundsService.getLabSciFundsVoById(labSciFundsVo.getId());
		List<LabUploadVo> uplodeList=labUploadService.getLabUploadList(labSciFundsVo.getId(), "lab-scifunds");
		setAttribute("uplodeList", uplodeList);
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"0".equals(labSciFundsVo.getType())) {
			return "preUpdate2";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"1".equals(labSciFundsVo.getType())) {
			return "preUpdate3";
		}
		return "";
	}
	
	public String updateLabSciFunds()throws GlobalException
	{
		init();
		labSciFundsService.updateLabSciFunds(labSciFundsVo);
		return UPDATE;
	}
	
	public String showLabSciFunds()throws GlobalException
	{
		init();
		List<LabUploadVo> uplodeList=labUploadService.getLabUploadList(labSciFundsVo.getId(), "lab-scifunds");
		setAttribute("uplodeList", uplodeList);
		labSciFundsVo=labSciFundsService.getLabSciFundsVoById(labSciFundsVo.getId());
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"2".equals(labSciFundsVo.getType())) {
			return "show1";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"0".equals(labSciFundsVo.getType())) {
			return "show2";
		}
		if (!StrUtils.isBlankOrNull(labSciFundsVo.getType())&&"1".equals(labSciFundsVo.getType())) {
			return "show3";
		}
		return SHOW;
	}
	
	public String deleteLabSciFunds()throws GlobalException
	{
		init();
		labSciFundsService.deleteLabSciFunds(labSciFundsVo.getIds());
		return DELETE;
	}

	public LabQueryVo getLabQueryVo() {
		return labQueryVo;
	}

	public void setLabQueryVo(LabQueryVo labQueryVo) {
		this.labQueryVo = labQueryVo;
	}
}
