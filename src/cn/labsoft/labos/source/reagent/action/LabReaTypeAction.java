package cn.labsoft.labos.source.reagent.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;
import cn.labsoft.labos.source.reagent.vo.LabReagentVo;
import cn.labsoft.labos.source.reagent.service.ILabReagentService;
import cn.labsoft.labos.source.reagent.service.ILabReaTypeService;
import cn.labsoft.labos.utils.StrUtils;

/**
 * @Title 试剂类型
 * @author Bill
 * 
 */
@Controller
@Scope("prototype")
public class LabReaTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabReaTypeService labReaTypeService;
	private ILabReagentService labReagentService;
	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private LabReaTypeVo labReaTypeVo;
	private LabReagentVo labReagentVo;
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	public void setLabReaTypeService(ILabReaTypeService labReaTypeService) {
		this.labReaTypeService = labReaTypeService;
	}
    @Resource
	public void setLabReagentService(ILabReagentService labReagentService) {
		this.labReagentService = labReagentService;
	}

	public LabReagentVo getLabReagentVo() {
		return labReagentVo;
	}

	public void setLabReagentVo(LabReagentVo labReagentVo) {
		this.labReagentVo = labReagentVo;
	}

	public LabReaTypeVo getLabReaTypeVo() {
		return labReaTypeVo;
	}

	public void setLabReaTypeVo(LabReaTypeVo labReaTypeVo) {
		this.labReaTypeVo = labReaTypeVo;
	}

	public String frameLabReagent() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		return FRAME;
	}

	public String preLabReagentZtree() throws GlobalException {
		return PRETREE;
	}

	public void ajaxNode4Add() throws GlobalException {
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
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
		if (!StrUtils.isBlankOrNull(parentId)) {
			try {
				labReaTypeVo.setLabReaReagentTypeId(parentId);
				if (!StrUtils.isBlankOrNull(id)) {
					labReaTypeVo.setId(id);
					labReaTypeVo.setName(name);
					labReaTypeService.updateLabReaType(labReaTypeVo);
				} else {
					labReaTypeVo.setName(getText("appratus.unknow"));
					labReaTypeVo = labReaTypeService
							.addLabReaType(labReaTypeVo);
				}
				out = response.getWriter();
				out.write(labReaTypeVo.getId());
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		} else {
			try {
				out = response.getWriter();
				out.write("");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}

	public void ajaxNode4Delete() throws GlobalException {
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
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
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				String[] ids = id.split(",");
				labReagentService.deleteLabReagentByTypeId(id);
				labReaTypeService.deleteLabReaType(ids);
				out = response.getWriter();
				out.write("true");
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		} else {
			try {
				out = response.getWriter();
				out.write("false");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	public void ajaxNode4Update() throws GlobalException {
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
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
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				labReaTypeVo = labReaTypeService.getLabReaType(id);
				labReaTypeVo.setName(name);
				labReaTypeService.updateLabReaType(labReaTypeVo);

				out = response.getWriter();
				out.write("true");
			} catch (IOException e) {
				out.write("");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		} else {
			try {
				out = response.getWriter();
				out.write("false");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}

	// 只得到试剂类型tree
	public void ajaxLabReagentZtree() throws GlobalException {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 最终输出字符串对象
		String treeNid = request.getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		treeBuf.append("[");
		if (treeNid != null) {
			treeBuf.append(treeSon(treeNid));
			treeBuf.append("]");
			outPrint(response, treeBuf);
		} else {
			LabReaTypeVo labReaTypeVo = labReaTypeService.getLabReaType("0");
			treeBuf
					.append("{name:'" + labReaTypeVo.getName()
							+ "', treeNid:'" + labReaTypeVo.getId()
							+ "',isParent:true,open:true,nodes:["
							+ treeSon("0") + "]}");
			treeBuf.append("]");

			outPrint(response, treeBuf);
		}
	}

	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabReaTypeVo> listreaTypeVo = labReaTypeService
				.getLabReaTypeByPid(pid);
		try {
			if (listreaTypeVo != null && listreaTypeVo.size() > 0) {
				for (LabReaTypeVo vo : listreaTypeVo) {
					String filename = vo.getName();
					String id = vo.getId();
					if (labReaTypeService.getLabReaTypeByPid(id).size() > 0) {
						firstTree.append("{name:'" + filename + "',treeNid:'"
								+ id + "', isParent:true},");
					} else {
						firstTree
								.append("{name:'" + filename + "', treeNid:'"
										+ id + "', isParent:false},");
					}
				}
				firstTree.replace(firstTree.length() - 1, firstTree.length(),
						"");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return firstTree;
	}

	// 得到试剂类型以及它下面的试剂
	public void ajaxLabReagentTree() throws GlobalException {
		String parentId = getRequest().getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		// 第一次进来时构造树结构
		StringBuffer firstTree = new StringBuffer();
		// 开始拼接JSON对象字符串
		treeBuf.append("[");
		List<LabReaTypeVo> typeList = labReaTypeService
				.getLabReaTypeByPid(parentId);
		List<LabReagentVo> list = labReagentService
				.getReagentListByTypeId(parentId);
		try {
			if (null != typeList && typeList.size() > 0) {
				for (LabReaTypeVo vo : typeList) {
					firstTree.append("{name:'" + vo.getName()
							+ "', treeNid:'" + vo.getId()
							+ "', isParent:true},");
				}
			}

			if (null != list && list.size() > 0) {
				if (!"[".equals(treeBuf.toString()))
					treeBuf.append(",");
				for (LabReagentVo po : list) {
					firstTree.append("{name:'" + po.getName()
							+ "', treeNid:'" + po.getId()
							+ "', isParent:false},");
				}
			}
			if (firstTree.length() > 0)
				firstTree.replace(firstTree.length() - 1, firstTree.length(),
						"");
			treeBuf.append(firstTree);
			treeBuf.append("]");
			treeBuf.replace(treeBuf.length() - 1, treeBuf.length(), "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		// 拼接结尾字符串
		treeBuf.append("]");
		outPrint(getResponse(), treeBuf);
	}

	public String showLabReagentType() throws GlobalException {
		if (null != labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
		}
		labReaTypeVo = labReaTypeService.getLabReaTypeByVo(labReaTypeVo);
		return SHOW;
	}

}
