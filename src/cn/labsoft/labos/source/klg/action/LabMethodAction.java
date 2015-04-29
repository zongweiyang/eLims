package cn.labsoft.labos.source.klg.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.service.ILabMethodService;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabMethodAction extends BaseAction {
	
	private ILabMethodService labMethodService;
	private ILabUploadService labUploadService;
	private LabMethodVo labMethodVo;
	
	@Resource
	public void setLabMethodService(ILabMethodService labMethodService) {
		this.labMethodService = labMethodService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	
	public LabMethodVo getLabMethodVo() {
		return labMethodVo;
	}

	public void setLabMethodVo(LabMethodVo labMethodVo) {
		this.labMethodVo = labMethodVo;
	}

	public void ajaxMethodIsExistName() throws GlobalException, IOException {
		String name=getRequest().getParameter("name").trim();
		Boolean flag=labMethodService.exist4LabMethodName(name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		if(Boolean.TRUE.equals(flag)){
			out = response.getWriter();
			out.write("1");
		}else{
			out = response.getWriter();
			out.write("0");
		}
	}
	
	public void ajaxMethodIsExistCode() throws GlobalException,IOException {
		String code=getRequest().getParameter("code").trim();
		boolean flag=labMethodService.exist4LabMethodCode(code);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		if(flag){
			out = response.getWriter();
			out.write("1");
		}else{
			out = response.getWriter();
			out.write("0");
		}
	}

	public String listLabMethod() throws GlobalException, IOException {
		if(null==labMethodVo) {
			labMethodVo = new LabMethodVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("name");
		}
		pageResult = labMethodService.getLabMethodPR(labMethodVo, pageResult);
		
		return LIST;
	}

	public String preUpdateLabMethod() throws GlobalException {
		labMethodVo=labMethodService.getLabMethod(id);
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labMethodVo.getId(), "klg-method");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}

	public String updateLabMethod() throws GlobalException {
		if(null==labMethodVo) labMethodVo = new LabMethodVo();
		labMethodService.updateLabMethod(labMethodVo);
		return UPDATE;
	}
	public String preAddLabMethod() throws GlobalException {
		if(null==labMethodVo) 
			labMethodVo = new LabMethodVo();
		if(StrUtils.isBlankOrNull(labMethodVo.getUuid())){
			labMethodVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labMethodVo.getUuid(), "klg-method");
		setAttribute("loadList", loadList);
		return PREADD;
	}
	public String addLabMethod() throws GlobalException {
		if(null==labMethodVo) labMethodVo = new LabMethodVo();
		labMethodService.addLabMethod(labMethodVo);
		return ADD;
	}
	
	public String deleteLabMethod() throws GlobalException {
		if(null==labMethodVo) labMethodVo = new LabMethodVo();
		labMethodService.update2DelLabMethod(labMethodVo.getIds());
		return DELETE;
	}
	
	public String showLabMethod() throws GlobalException {
		if(null==labMethodVo) labMethodVo = new LabMethodVo();
		labMethodVo=labMethodService.getLabMethod (id);
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labMethodVo.getId(), "klg-method");
		setAttribute("loadList", loadList);
		return SHOW;
	}
}
