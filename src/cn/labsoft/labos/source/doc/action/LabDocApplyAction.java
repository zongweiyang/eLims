package cn.labsoft.labos.source.doc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.source.doc.service.ILabDocApplyService;
import cn.labsoft.labos.source.doc.vo.LabDocApplyVo;
import cn.labsoft.labos.source.doc.vo.LabDocVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
@Controller
@Scope("prototype")
public class LabDocApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabDocApplyService labDocApplyService;
	private ILabOrgService labOrgService;

	private LabDocApplyVo labDocApplyVo;
	private List<LabDocApplyVo> labDocApplyVoList;
	private LabDocVo labDocVo;
	@Resource
	public void setLabDocApplyService(ILabDocApplyService labDocApplyService) {
		this.labDocApplyService = labDocApplyService;
	}
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	public void setLabDocApplyVoList(List<LabDocApplyVo> labDocApplyVoList) {
		this.labDocApplyVoList = labDocApplyVoList;
	}

	public LabDocVo getLabDocVo() {
		return labDocVo;
	}

	public void setLabDocVo(LabDocVo labDocVo) {
		this.labDocVo = labDocVo;
	}

	public LabDocApplyVo getLabDocApplyVo() {
		return labDocApplyVo;
	}

	public void setLabDocApplyVo(LabDocApplyVo labDocApplyVo) {
		this.labDocApplyVo = labDocApplyVo;
	}

	public String addLabDocApply() throws GlobalException {
		if (null == labDocVo) {
			labDocVo = new LabDocVo();
		}
		labDocApplyService.addLabDocApply(labDocVo);
		return ADD;
	}

	public String lisLabDocApply() throws GlobalException {
		labDocApplyVoList = labDocApplyService.getLabDocApplyList();
		setAttribute("labDocApplyVoList", labDocApplyVoList);
		return LIST;
	}

	public String updateLabDocApply4cancel() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.cancelLabDocApply(labDocApplyVo);
		return "cancel";
	}

	public String updateLabDocApply() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.updateLabDocApply(labDocApplyVo);
		return UPDATE;
	}

	public String updateLabDocApplyByLabDocId() throws GlobalException {
		if (null == labDocVo) {
			labDocVo = new LabDocVo();
		}
		labDocApplyService.updateLabDocApplyByLabDocId(labDocVo);
		return UPDATE;
	}

	public String addLabDocApplyBatch() throws GlobalException {
		if (null == labDocVo) {
			labDocVo = new LabDocVo();
		}
		labDocApplyService.addLabDocApplyBatch(labDocVo);
		return ADD;
	}

	public String updateLabDocApplyBatch() throws GlobalException {
		if (null == labDocVo) {
			labDocVo = new LabDocVo();
		}
		labDocApplyService.updateLabDocApplyBatch(labDocVo);
		return UPDATE;
	}

	public String updateLabDocOtherApplyBatch() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.updateLabDocOtherApplyBatch(labDocApplyVo);
		return UPDATE;
	}

	public String ajaxCheckApply() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("ID");
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;

		LabDocApplyVo vo = labDocApplyService.getLabDocApplyById(id);
		try {
			out = response.getWriter();
			String result = "0";
			if(null!=vo){
				if (vo.getStatus().equals("1") || vo.getStatus().equals("2")) {
					result = "1";
				}
			}
			
			out.write(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return null;

	}

	public String ajaxCheckCancel() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("ID");
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;

		LabDocApplyVo vo = labDocApplyService.getLabDocApplyById(id);
		try {
			out = response.getWriter();
			String result = "0";
			if (null == vo) {
				result = "1";
			} else {
				if (vo.getStatus().equals("0")) {
					result = "1";
				}
			}
			out.write(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return null;

	}

	public String showLabDocApply4Select() throws GlobalException {
		if (null == labDocVo) {
			labDocVo = new LabDocVo();
		}
		labDocApplyVoList = labDocApplyService.getLabDocOterApplyList(labDocVo);
		setAttribute("labDocApplyVoList", labDocApplyVoList);
		List<LabOrgVo> orgList=labOrgService.getLabOrgList(new LabOrgVo());
		setAttribute("orgList", orgList);
		return LIST;
	}

	public String updateLabDocApplyById() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.updateLabDocApplyById(labDocApplyVo);
		return UPDATE;
	}

	public String updateLabDocApplyRefuse() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.updateLabDocApplyById(labDocApplyVo);
		return UPDATE;
	}

	public String updateLabDocApplyfByFlag() throws GlobalException {
		if (null == labDocApplyVo) {
			labDocApplyVo = new LabDocApplyVo();
		}
		labDocApplyService.updateLabDocApplyById(labDocApplyVo);
		return UPDATE;
	}

	public String ajaxCheckRefuse() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("ID");
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;

		LabDocApplyVo vo = labDocApplyService.getLabDocApplyByLabDocId(id);
		try {
			out = response.getWriter();
			String result = "0";
			if (vo.getStatus().equals("0")) {
				result = "1";
			}
			out.write(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return null;

	}

	public String ajaxCheckPass() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("ID");
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;

		LabDocApplyVo vo = labDocApplyService.getLabDocApplyByLabDocId(id);
		try {
			out = response.getWriter();
			String result = "0";
			if (null == vo) {
				result = "1";
			} else {
				if (vo.getStatus().equals("2")) {
					result = "1";
				}
			}
			out.write(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return null;

	}
	

}
