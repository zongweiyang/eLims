package cn.labsoft.labos.source.klg.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.service.ILabStandardTypeService;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabStandardTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private ILabStandardTypeService labStandardTypeService;
	
	private LabStandardTypeVo labStandardTypeVo;

	@Resource
	public void setLabStandardTypeService(
			ILabStandardTypeService labStandardTypeService) {
		this.labStandardTypeService = labStandardTypeService;
	}

	public LabStandardTypeVo getLabStandardTypeVo() {
		return labStandardTypeVo;
	}

	public void setLabStandardTypeVo(LabStandardTypeVo labStandardTypeVo) {
		this.labStandardTypeVo = labStandardTypeVo;
	}

	HttpServletRequest request = ServletActionContext.getRequest();

	public void ajaxLabStandardType4Add() throws GlobalException {
		if (null == labStandardTypeVo) {
			labStandardTypeVo = new LabStandardTypeVo();
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
			
		// 最终输出字符串对象
		String parentId = request.getParameter("treeNid");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(!StrUtils.isBlankOrNull(parentId)){
			try {
				labStandardTypeVo.setParentid(parentId);
				if(!StrUtils.isBlankOrNull(id)){
					labStandardTypeVo.setId(id);
					labStandardTypeVo.setName(name);
					labStandardTypeService.updateLabStandardType(labStandardTypeVo);
				}else{
					labStandardTypeVo.setName(getText("appratus.unknow"));
					labStandardTypeVo=labStandardTypeService.addLabStandardType(labStandardTypeVo);
				}
				out = response.getWriter();
				out.write(labStandardTypeVo.getId());
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}else{
			try {
				out = response.getWriter();
				out.write("");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}
	public void ajaxLabStandardType4Delete() throws GlobalException {
		if (null == labStandardTypeVo) {
			labStandardTypeVo = new LabStandardTypeVo();
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
			
		// 最终输出字符串对象
		String id = request.getParameter("id");
		if(!StrUtils.isBlankOrNull(id)){
			try {
				String[] ids = id.split(",");
				labStandardTypeService.deleteLabStandardType(ids);
				out = response.getWriter();
				out.write("true");
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}else{
			try {
				out = response.getWriter();
				out.write("false");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}
	public void ajaxLabStandardType4Update() throws GlobalException {
		if (null == labStandardTypeVo) {
			labStandardTypeVo = new LabStandardTypeVo();
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
			
		// 最终输出字符串对象
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(!StrUtils.isBlankOrNull(id)){
			try {
				labStandardTypeVo=labStandardTypeService.getLabStandardType(id);
				labStandardTypeVo.setName(name);
				labStandardTypeService.updateLabStandardType(labStandardTypeVo);
			
				out = response.getWriter();
				out.write("true");
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}else{
			try {
				out = response.getWriter();
				out.write("false");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}
}
