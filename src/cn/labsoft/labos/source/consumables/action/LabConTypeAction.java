package cn.labsoft.labos.source.consumables.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.consumables.vo.LabConTypeVo;
import cn.labsoft.labos.source.consumables.vo.LabConsumablesVo;
import cn.labsoft.labos.source.consumables.service.ILabConsumablesService;
import cn.labsoft.labos.source.consumables.service.ILabConTypeService;
import cn.labsoft.labos.utils.StrUtils;

/**
 * @Title 耗材类型
 * @author Bill
 * 
 */
@Controller
@Scope("prototype")
public class LabConTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabConTypeService labConTypeService;
	private ILabConsumablesService labConsumablesService;
	private ILabConfigService labConfigService;
	private LabConTypeVo labConTypeVo;
	private LabConsumablesVo labConsumablesVo;
	HttpServletRequest request = ServletActionContext.getRequest();
    @Resource
	public void setLabConTypeService(ILabConTypeService labConTypeService) {
		this.labConTypeService = labConTypeService;
	}
    @Resource
	public void setLabConsumablesService(
			ILabConsumablesService labConsumablesService) {
		this.labConsumablesService = labConsumablesService;
	}
    @Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public LabConsumablesVo getLabConsumablesVo() {
		return labConsumablesVo;
	}

	public void setLabConsumablesVo(LabConsumablesVo labConsumablesVo) {
		this.labConsumablesVo = labConsumablesVo;
	}

	public LabConTypeVo getLabConTypeVo() {
		return labConTypeVo;
	}

	public void setLabConTypeVo(LabConTypeVo labConTypeVo) {
		this.labConTypeVo = labConTypeVo;
	}

	public String frameLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		return FRAME;
	}

	public String preLabConsumablesZtree() throws GlobalException {
		return PRETREE;
	}

	public void ajaxNode4Add() throws GlobalException {
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
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
				labConTypeVo.setLabConConsumablesTypeId(parentId);
				if (!StrUtils.isBlankOrNull(id)) {
					labConTypeVo.setId(id);
					labConTypeVo.setName(name);
					labConTypeService.updateLabConType(labConTypeVo);
				} else {
					List<LabConTypeVo> maxSize=labConTypeService.getLabConTypeByPid(parentId);
					LabConfigVo config=labConfigService.getLabConfigByCode("nodeNum");
					int num=0;
					try {
						num=Integer.valueOf(config.getValue());
					} catch (NumberFormatException e) {
						num=10;
						throw new GlobalException("" + e.getMessage());
					}
					if(maxSize.size()<num){
						labConTypeVo.setName("未命名");
						labConTypeVo = labConTypeService
								.addLabConType(labConTypeVo);
						out = response.getWriter();
						out.write(labConTypeVo.getId());
					}else{
						out = response.getWriter();
						out.write(getText("file.max.size"));
					}
				}
			} catch (IOException e) {
				out.write("");
				Log4J.error(e.getStackTrace());
				throw new GlobalException("" + e.getMessage());
			}
		} else {
			try {
				out = response.getWriter();
				out.write("");
			} catch (IOException e) {
				Log4J.error(e.getStackTrace());
				throw new GlobalException("" + e.getMessage());
			}
		}

	}

	public void ajaxNode4Delete() throws GlobalException {
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
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
				labConsumablesService.deleteLabConsumablesByTypeId(id);
				labConTypeService.deleteLabConType(ids);
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
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
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
				labConTypeVo = labConTypeService.getLabConType(id);
				labConTypeVo.setName(name);
				labConTypeService.updateLabConType(labConTypeVo);

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

	// 只得到耗材类型tree
	public void ajaxLabConsumablesZtree() throws GlobalException {
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
			LabConTypeVo labConTypeVo = labConTypeService.getLabConType("0");
			treeBuf.append("{name:'" + labConTypeVo.getName()
					+ "', treeNid:'" + labConTypeVo.getId()
					+ "', isParent:true,open:true,nodes:[" + treeSon("0")
					+ "]}");
			treeBuf.append("]");
			outPrint(response, treeBuf);
		}
	}

	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabConTypeVo> listconTypeVo = labConTypeService
				.getLabConTypeByPid(pid);
		try {
			if (null != listconTypeVo && listconTypeVo.size() > 0) {
				for (LabConTypeVo vo : listconTypeVo) {
					String filename = vo.getName();
					String id = vo.getId();
					if (labConTypeService.getLabConTypeByPid(id).size() > 0) {
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

	// 得到耗材类型以及它下面的耗材
	public void ajaxLabConsumablesTree() throws GlobalException {
		String parentId = getRequest().getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		// 第一次进来时构造树结构
		StringBuffer firstTree = new StringBuffer();
		// 开始拼接JSON对象字符串
		treeBuf.append("[");
		List<LabConTypeVo> typeList = labConTypeService
				.getLabConTypeByPid(parentId);
		List<LabConsumablesVo> list = labConsumablesService
				.getConsumablesListByTypeId(parentId);
		try {
			if (null != typeList && typeList.size() > 0) {
				for (LabConTypeVo vo : typeList) {
					firstTree.append("{name:'" + vo.getName()
							+ "', treeNid:'" + vo.getId()
							+ "', isParent:true},");
				}
			}

			if (null != list && list.size() > 0) {
				if (!"[".equals(treeBuf.toString()))
					treeBuf.append(",");
				for (LabConsumablesVo po : list) {
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

	public String showLabConsumablesType() throws GlobalException {
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
		}
		labConTypeVo = labConTypeService.getLabConTypeByVo(labConTypeVo);
		return SHOW;
	}

}
