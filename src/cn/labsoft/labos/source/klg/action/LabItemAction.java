package cn.labsoft.labos.source.klg.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.source.klg.service.ILabItemService;
import cn.labsoft.labos.source.klg.service.ILabStandardTypeService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.OperationExcel;

@Controller
@Scope("prototype")
public class LabItemAction extends BaseAction {

	private ILabStandardTypeService labStandardTypeService;
	private ILabItemService labItemService;
	private ILabConfigService labConfigService;
	private ILabApparaService labApparaService;
	
	private LabStandardVo labStandardVo;
	private LabItemVo labItemVo;
	private LabApparaVo labApparaVo;
	private LabStandardTypeVo labStandardTypeVo;
	
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private String type;
	private File upload;// 实际上传文件

	@Resource
	public void setLabStandardTypeService(
			ILabStandardTypeService labStandardTypeService) {
		this.labStandardTypeService = labStandardTypeService;
	}
	@Resource
	public void setLabItemService(ILabItemService labItemService) {
		this.labItemService = labItemService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}

	public LabStandardTypeVo getLabStandardTypeVo() {
		return labStandardTypeVo;
	}

	public void setLabStandardTypeVo(LabStandardTypeVo labStandardTypeVo) {
		this.labStandardTypeVo = labStandardTypeVo;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void ajaxIsExistName() throws GlobalException, IOException {
		String name = getRequest().getParameter("name");
		boolean flag = labItemService.exist4LabItemName(name, "");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		if (flag) {
			out = response.getWriter();
			out.write("1");
		} else {
			out = response.getWriter();
			out.write("0");
		}
	}

	private void initHeader() throws GlobalException {
		if (null == labItemVo) {
			labItemVo = new LabItemVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("name");
		}
		if (null == labStandardVo)
			labStandardVo = new LabStandardVo();
		if (null == labStandardTypeVo)
			labStandardTypeVo = new LabStandardTypeVo();
	}

	public String listLabItem() throws GlobalException, IOException {
		initHeader();
		pageResult = labItemService.getLabItemPR(labItemVo, pageResult);
		return LIST;
	}

	public String deleteLabItem() throws GlobalException {
		initHeader();
		labItemService.update2DelLabItem(labItemVo.getIds());
		return DELETE;
	}

	public String preUpdateLabItem() throws GlobalException {
		initHeader();
		labItemVo = labItemService.getLabItem(id);
		return PREUPDATE;
	}

	public String updateLabItem() throws GlobalException {
		initHeader();
		boolean key = labItemService.updateLabItem(labItemVo);
		return UPDATE;
	}

	public String preAddLabItem() throws GlobalException {
		initHeader();
		return PREADD;
	}

	public String addLabItem() throws GlobalException {
		initHeader();
		boolean key = labItemService.addLabItem(labItemVo);
		return ADD;
	}

	public String showLabItem() throws GlobalException {
		initHeader();
		if (null == labItemVo) {
			labItemVo = new LabItemVo();
		}
		labItemVo = labItemService.getLabItem(id);
		return SHOW;
	}
	
	public String showLabAppara4select() throws GlobalException{
		if (null == labApparaVo)
			labApparaVo = new LabApparaVo();
		labApparaVo.setStatus("0");
		pageResult= labApparaService.getLabApparaList(labApparaVo, pageResult);
		return SHOW;
	}

	public String frameLabStandItem() throws GlobalException {
		return FRAME;
	}

	public String preLabStandItemZtree() {
		return SHOW;
	}

	public void ajaxLabStandItemZtree() throws GlobalException {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 最终输出字符串对象
		String treeNid = getRequest().getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		StringBuffer firstTree = new StringBuffer();
		treeBuf.append("[");
		if (treeNid != null) {

			treeBuf.append(treeSon(treeNid));
			treeBuf.append("]");
			outPrint(response, treeBuf);
		} else {
			LabStandardTypeVo labStandardTypeVo = labStandardTypeService
					.getLabStandardType("0");

			treeBuf.append("{name:'" + labStandardTypeVo.getName()
					+ "', treeNid:'" + labStandardTypeVo.getId()
					+ "', isParent:true,open:true,nodes:[" + treeSon("0")
					+ "]}");
			treeBuf.append("]");
			outPrint(response, treeBuf);
		}

	}

	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabStandardTypeVo> listlabStandardTypeVo = labStandardTypeService
				.getLabStandardTypeByPid(pid);
		try {
			if (listlabStandardTypeVo != null
					&& listlabStandardTypeVo.size() > 0) {
				for (LabStandardTypeVo vo : listlabStandardTypeVo) {
					String filename = vo.getName();
					String id = vo.getId();
					if (labStandardTypeService.getLabStandardTypeByPid(id)
							.size() > 0) {
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

	public String preImportLabItem4Excel() throws GlobalException, Exception {
		initHeader();
		String fileUrl = labConfigService.getLabConfigByCode(
				"standard").getValue()
				+ File.separator + "检测项目.xls";
		setAttribute("fileUrl", fileUrl);
		return PREIMPORT;
	}

	private String[] uploadFile() throws IOException, GlobalException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName
				.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext()
				.getRealPath("/uploadDoc/klg-item");
		String FileType = trueName.substring(trueName.lastIndexOf(".") + 1,
				trueName.length());
		String temp = Long.toString(new Date().getTime());
		String targetFileName = temp + "." + FileType;
		File file = new File(uploadDirectory, targetFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			org.apache.commons.io.FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;

		return result;
	}

	public String importLabItem4Excel() throws GlobalException, IOException {
		initHeader();
		if (null == labApparaVo)
			labApparaVo = new LabApparaVo();
		// 上传文件
		if (upload == null) {
			addActionError(getText("file.path.unnull"));
			return ERROR;
		}
		PrintWriter out = null;
		String str[] = uploadFile();
		String fileUrl = str[1] + "\\" + str[2];
		fileUrl = fileUrl.replace('\\', '/');
		File target = new File(str[1], str[2]);
		try {
			String value[][] = OperationExcel.readExcel(fileUrl, 0);
			if (null != value && value.length > 0) {
				for (int j = 1; j < value.length; j++) {
					if (j == 1) {
						continue;
					}
					if (value[j].length != 0) {
						for (int i = 0; i < value[j].length; i++) {
							switch (i) {
							case 0:
								if (null != value[j][0]
										&& !"".equals(value[j][0])) {
									labItemVo.setName(value[j][0]);
								}
								break;
							case 1:
								if (null != value[j][1]
										&& !"".equals(value[j][1])) {
									labItemVo.setUnit(value[j][1]);
								}
								break;
							case 2:
								if (null != value[j][2]
										&& !"".equals(value[j][2])) {
									
									List<LabApparaVo> labApparaVoList = labApparaService.getLabApparaByName(value[j][2].trim());
									if(null != labApparaVoList && labApparaVoList.size() > 0 ){
										labItemVo.setAppId(labApparaVoList.get(0).getId());
										labItemVo.setAppName(labApparaVoList.get(0).getName());
									}else{
										labApparaVo.setName(value[j][2]);
										labApparaVo.setCode("YQ"+DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDay());
										labApparaVo.setStatus("0");
										labApparaService.addLabAppara(labApparaVo);
										labApparaVoList = labApparaService.getLabApparaByName(value[j][2].trim());
										labItemVo.setAppId(labApparaVoList.get(0).getId());
										labItemVo.setAppName(labApparaVoList.get(0).getName());
									}
								}
								break;
							case 3:
								if (null != value[j][3]
										&& !"".equals(value[j][3])) {
									labItemVo.setDemo1(value[j][3]);
								}
								break;
							case 4:
								if (null != value[j][4]
										&& !"".equals(value[j][4])) {
									labItemVo.setDemo2(value[j][4]);
								}
								break;
							case 5:
								if (null != value[j][5]
										&& !"".equals(value[j][5])) {
									try {
										labItemVo.setPrice(Double.valueOf(value[j][5].trim()));
									} catch (Exception e) {
										// TODO: handle exception
										labItemVo.setPrice(0.0);
										throw new GlobalException("" + e.getMessage());
									}
								}
								break;
							case 6:
								if (null != value[j][6]
										&& !"".equals(value[j][6])) {
									labItemVo.setRemark(value[j][6]);
								}
								break;
							}
						}
						boolean flag =  labItemService.exist4LabItemName(labItemVo.getName(), "");
						if(flag == false){
							labItemService.addLabItem(labItemVo);
						}else{
							List<LabItemVo> labItemVoList =  labItemService.getLabItemList(labItemVo);
							if(null != labItemVoList && labItemVoList.size() > 0){
								for(LabItemVo labItemVo1 : labItemVoList){
									labItemVo.setId(labItemVo1.getId());
									labItemService.updateLabItem(labItemVo);
								}
							}
						}
						type = "1";
					}
				}
			}
		} catch (Exception e) {
			type = "0";
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		target.delete();
		getRequest().setAttribute("type", type);
		return IMPORT;
	}
	public LabStandardVo getLabStandardVo() {
		return labStandardVo;
	}

	public void setLabStandardVo(LabStandardVo labStandardVo) {
		this.labStandardVo = labStandardVo;
	}

	public LabItemVo getLabItemVo() {
		return labItemVo;
	}

	public void setLabItemVo(LabItemVo labItemVo) {
		this.labItemVo = labItemVo;
	}

	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}


}
