package cn.labsoft.labos.source.klg.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.service.ILabStandardService;
import cn.labsoft.labos.source.klg.service.ILabStandardTypeService;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabStandardAction extends BaseAction {
	
	
	private ILabStandardService labStandardService;
	private ILabStandardTypeService labStandardTypeService;
	private ILabUploadService labUploadService;
	private ILabConfigService labConfigService;
	private LabStandardVo labStandardVo;
	
	
	private File upload;// 实际上传文件
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private String type ;
	private List<LabStandardVo> listLabStandardVo;

	@Resource
	public void setLabStandardService(ILabStandardService labStandardService) {
		this.labStandardService = labStandardService;
	}
	@Resource
	public void setLabStandardTypeService(
			ILabStandardTypeService labStandardTypeService) {
		this.labStandardTypeService = labStandardTypeService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	public List<LabStandardVo> getListLabStandardVo() {
		return listLabStandardVo;
	}
	public void setListLabStandardVo(List<LabStandardVo> listLabStandardVo) {
		this.listLabStandardVo = listLabStandardVo;
	}
	public void isRequiredStandsNo() throws GlobalException, IOException {
		String code=getRequest().getParameter("code");
		boolean flag=labStandardService.exist4StandsCode(code);
		
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
	public String listLabStandard() throws GlobalException, IOException {
		if(null==labStandardVo) {
			labStandardVo = new LabStandardVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("name");
		}
		if(StrUtils.isBlankOrNull(labStandardVo.getStandTypeId())){
			labStandardVo.setStandTypeId("0");
			labStandardVo.setStandTypeName(getText("standard.classify"));
		}else{
			LabStandardTypeVo typeVo= labStandardTypeService.getLabStandardType(labStandardVo.getStandTypeId().trim());
			labStandardVo.setStandTypeId(typeVo.getId());
			labStandardVo.setStandTypeName(typeVo.getName());
		}
		pageResult = labStandardService.getLabStandardPR(labStandardVo, pageResult);
		return LIST;
	}
	
	public String deleteLabStandard() throws GlobalException {
		if(null==labStandardVo) labStandardVo = new LabStandardVo();
		labStandardService.update2DelLabStandard(labStandardVo.getIds());
		return DELETE;
	}

	public String preUpdateLabStandard() throws GlobalException {
		if(null==labStandardVo) labStandardVo = new LabStandardVo();
		labStandardVo=labStandardService.getLabStandard(id);
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labStandardVo.getId(), "klg-standard");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}

	public String updateLabStandard() throws GlobalException,Exception {
		if(null==labStandardVo) labStandardVo = new LabStandardVo();
		
		labStandardService.updateLabStandard(labStandardVo);
		return UPDATE;
	}
	
	public String preAddLabStandard() throws GlobalException {
		if (null == labStandardVo) {
			labStandardVo=new LabStandardVo();
		}
		if(StrUtils.isBlankOrNull(labStandardVo.getUuid())){
			labStandardVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labStandardVo.getUuid(), "klg-standard");
		setAttribute("loadList", loadList);
		return PREADD;
	}
	public String addLabStandard() throws Exception{
		if (labStandardVo==null ) labStandardVo=new LabStandardVo();
		labStandardService.addLabStandard(labStandardVo);
		return ADD;
	}
	private String[] uploadFile() throws IOException, GlobalException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext().getRealPath("/uploadDoc/klg-standard");
		String FileType = trueName.substring(trueName.lastIndexOf(".") + 1, trueName.length());
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
	
	public String preImportLabStandard4Excel()throws GlobalException{
		if (labStandardVo==null ) labStandardVo=new LabStandardVo();
		String fileUrl = labConfigService.getLabConfigByCode("standard").getValue()
		+ File.separator + "检测标准.xls";
		setAttribute("fileUrl", fileUrl);
		return PREIMPORT;
	}
	public String importLabStandard4Excel() throws GlobalException,IOException{
		if (labStandardVo==null ) labStandardVo=new LabStandardVo();
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
				if(value[j].length != 0){
					for (int i = 0; i < value[j].length; i++) {
						switch (i) {
						case 0:
							if (null != value[j][0] && !"".equals(value[j][0])) {
								labStandardVo.setCode(value[j][0]);
							}
							break;
						case 1:
							if (null != value[j][1] && !"".equals(value[j][1])) {
								labStandardVo.setName(value[j][1]);
							}
							break;
						case 2:
							if (null != value[j][2] && !"".equals(value[j][2])) {
								String strArr[] = value[j][2].split("/");
								labStandardVo.setReleaseDate(strArr[2] + "-" + strArr[1] + "-" + strArr[0]);
							}
							break;
						case 3:
							if (null != value[j][3] && !"".equals(value[j][3])) {
								String strArr[] = value[j][3].split("/");
								labStandardVo.setMaterialDate(strArr[2] + "-" + strArr[1] + "-" + strArr[0]);
							}
							break;
						case 4:
							if (null != value[j][4] && !"".equals(value[j][4])) {
								labStandardVo.setStandIndex(value[j][4]);
							}
							break;
						case 5:
							if (null != value[j][5] && !"".equals(value[j][5])) {
								value[j][5] = value[j][5].replace("，", ",");
								String replaceNames = "";
								String replaceIds = "";
								List<LabStandardVo> labStandardVoList = labStandardService.getLabStandardByName(value[j][5].trim());
								if(null !=labStandardVoList && labStandardVoList.size() > 0 ){
									for(LabStandardVo labStandardVo : labStandardVoList){
										replaceIds += labStandardVo.getId()+",";
										replaceNames += labStandardVo.getName()+",";
										//更改被替换标准状态
										labStandardVo.setIsUsed(Constants_Source.N);
										labStandardService.updateLabStandard(labStandardVo);
									}
								}else{
									String[] names = value[j][5].split(",");
									int ii = 1;
									if(names.length > 0){
										for(String name : names){
											LabStandardVo labStandardVo1 = new LabStandardVo();
											labStandardVo1.setName(name.trim());
											labStandardVo1.setIsDel(Constants_Source.N);
											labStandardVo1.setStandTypeId(labStandardVo.getStandTypeId());
											labStandardVo1.setCode("BZ"+DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDay()+ii);
											if(!StrUtils.isBlankOrNull(labStandardVo.getStandTypeId())){
												LabStandardTypeVo labStandardTypeVo = labStandardTypeService.getLabStandardType(labStandardVo.getStandTypeId());
												labStandardVo1.setStandTypeName(labStandardTypeVo.getName());
											}
											labStandardService.addLabStandard(labStandardVo1);
											ii++;
										}
									}
									labStandardVoList = labStandardService.getLabStandardByName(value[j][5].trim());
									for(LabStandardVo labStandardVo : labStandardVoList){
										replaceIds += labStandardVo.getId()+",";
										replaceNames += labStandardVo.getName()+",";
									}
								}
								if(replaceIds.split(",").length > 0){
									replaceIds = replaceIds.substring(0, replaceIds.length()-1);
								}
								if(replaceNames.split(",").length > 0){
									replaceNames = replaceNames.substring(0, replaceNames.length()-1);
								}
								labStandardVo.setReplaceIds(replaceIds);
								labStandardVo.setReplaceName(replaceNames);
							}
							break;
						case 6:
							if (null != value[j][6] && !"".equals(value[j][6])) {
								labStandardVo.setRemark(value[j][6]);
							}
							break;
						}
					}
					labStandardVo.setIsUsed(Constants_Source.Y);
					labStandardVo.setIsDel(Constants_Source.N);
					labStandardVo.setStandTypeId(labStandardVo.getStandTypeId());
					if(!StrUtils.isBlankOrNull(labStandardVo.getStandTypeId())){
						LabStandardTypeVo labStandardTypeVo = labStandardTypeService.getLabStandardType(labStandardVo.getStandTypeId());
						labStandardVo.setStandTypeName(labStandardTypeVo.getName());
					}
					boolean faly  = labStandardService.exist4StandsCode(labStandardVo.getCode());
					if(faly == false ){
						labStandardService.addLabStandard(labStandardVo);
					}else if(!StrUtils.isBlankOrNull(labStandardVo.getCode())){
						labStandardVo = labStandardService.getLabStandardVoByCode(labStandardVo.getCode());
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
	public String frameLabStandard() throws GlobalException {
		return FRAME;
	}

	public String preLabStandard4Ztree() {
		return SHOW;
	}
	
	public void ajaxLabStandard4Ztree() throws GlobalException {
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

	public String listLabStandard4Select() throws GlobalException{
		if (labStandardVo==null ) labStandardVo=new LabStandardVo();
		listLabStandardVo=labStandardService.getLabStandardByStatusPR(labStandardVo);
		return LIST;
	}
	public String showLabStandard() throws GlobalException {
		if (labStandardVo==null ) labStandardVo=new LabStandardVo();
		labStandardVo=labStandardService.getLabStandard(id);
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labStandardVo.getId(), "klg-standard");
		setAttribute("loadList", loadList);
		return SHOW;
	}
	public LabStandardVo getLabStandardVo() {
		return labStandardVo;
	}
	public void setLabStandardVo(LabStandardVo labStandardVo) {
		this.labStandardVo = labStandardVo;
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
	
	
}
