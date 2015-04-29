package cn.labsoft.labos.source.reference.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.service.ILabRefTypeService;
import cn.labsoft.labos.source.reference.service.ILabReferenceService;
import cn.labsoft.labos.source.reference.vo.LabRefTypeVo;
import cn.labsoft.labos.source.reference.vo.LabReferenceVo;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabRefTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabRefTypeService labRefTypeService;
	private ILabReferenceService labReferenceService;
	
	private LabRefTypeVo labRefTypeVo;
	private LabReferenceVo labReferenceVo;
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	public void setLabRefTypeService(ILabRefTypeService labRefTypeService) {
		this.labRefTypeService = labRefTypeService;
	}
	@Resource
	public void setLabReferenceService(ILabReferenceService labReferenceService) {
		this.labReferenceService = labReferenceService;
	}

	public LabReferenceVo getLabReferenceVo() {
		return labReferenceVo;
	}

	public void setLabReferenceVo(LabReferenceVo labReferenceVo) {
		this.labReferenceVo = labReferenceVo;
	}

	public LabRefTypeVo getLabRefTypeVo() {
		return labRefTypeVo;
	}

	public void setLabRefTypeVo(LabRefTypeVo labRefTypeVo) {
		this.labRefTypeVo = labRefTypeVo;
	}

	public String frameLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		return FRAME;
	}

	public String preLabReferenceZtree() throws GlobalException {
		return PRETREE;
	}

	public void ajaxNode4Add() throws GlobalException {
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
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
				labRefTypeVo.setLabRefReferenceTypeId(parentId);
				if (!StrUtils.isBlankOrNull(id)) {
					labRefTypeVo.setId(id);
					labRefTypeVo.setName(name);
					labRefTypeService.updateLabRefType(labRefTypeVo);
				} else {
					labRefTypeVo.setName( getText("appratus.unknow") );
					labRefTypeVo = labRefTypeService
							.addLabRefType(labRefTypeVo);
				}
				out = response.getWriter();
				out.write(labRefTypeVo.getId());
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
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
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
				labReferenceService.deleteLabReferenceByTypeId(id);
				labRefTypeService.deleteLabRefType(ids);
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
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
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
				labRefTypeVo = labRefTypeService.getLabRefType(id);
				labRefTypeVo.setName(name);
				labRefTypeService.updateLabRefType(labRefTypeVo);

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

	// 只得到标准品类型tree
	public void ajaxLabReferenceZtree() throws GlobalException {
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
			LabRefTypeVo labRefTypeVo = labRefTypeService.getLabRefType("0");
			treeBuf.append("{name:'" + labRefTypeVo.getName()
					+ "', treeNid:'" + labRefTypeVo.getId()
					+ "', isParent:true,open:true,nodes:[" + treeSon("0")
					+ "]}");
			treeBuf.append("]");
			outPrint(response, treeBuf);
		}
	}

	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabRefTypeVo> listrefTypeVo = labRefTypeService
				.getLabRefTypeByPid(pid);
		try {
			if (null != listrefTypeVo && listrefTypeVo.size() > 0) {
				for (LabRefTypeVo vo : listrefTypeVo) {
					String filename = vo.getName();
					String id = vo.getId();
					if (labRefTypeService.getLabRefTypeByPid(id).size() > 0) {
						firstTree.append("{name:'" + filename
								+ "', treeNid:'" + id + "', isParent:true},");
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

	// 得到标准品类型以及它下面的标准品
	public void ajaxLabReferenceTree() throws GlobalException {
		String parentId = getRequest().getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		// 第一次进来时构造树结构
		StringBuffer firstTree = new StringBuffer();
		// 开始拼接JSON对象字符串
		treeBuf.append("[");
		List<LabRefTypeVo> typeList = labRefTypeService
				.getLabRefTypeByPid(parentId);
		List<LabReferenceVo> list = labReferenceService
				.getReferenceListByTypeId(parentId);
		try {
			if (null != typeList && typeList.size() > 0) {
				for (LabRefTypeVo vo : typeList) {
					firstTree.append("{name:'" + vo.getName()
							+ "', treeNid:'" + vo.getId()
							+ "', isParent:true},");
				}
			}

			if (null != list && list.size() > 0) {
				if (!"[".equals(treeBuf.toString()))
					treeBuf.append(",");
				for (LabReferenceVo po : list) {
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

	public String showLabReferenceType() throws GlobalException {
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
		}
		labRefTypeVo = labRefTypeService.getLabRefTypeByVo(labRefTypeVo);
		return SHOW;
	}

}
