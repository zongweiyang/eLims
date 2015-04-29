package cn.labsoft.labos.source.klg.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.jxls.transformer.XLSTransformer;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.service.ILabItemService;
import cn.labsoft.labos.source.klg.service.ILabMethodService;
import cn.labsoft.labos.source.klg.service.ILabStandardItemService;
import cn.labsoft.labos.source.klg.service.ILabStandardService;
import cn.labsoft.labos.source.klg.service.ILabStandardTypeService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabStandardItemAction extends BaseAction {

	private ILabStandardService labStandardService;
	private ILabStandardTypeService labStandardTypeService;
	private ILabItemService labItemService;
	private ILabMethodService labMethodService;
	private ILabStandardItemService labStandardItemService;
	private ILabConfigService labConfigService;
	private ILabSamTypeService labSamTypeService;
	@Resource
	private ILabCodeService labCodeService;
	
	private LabStandardVo labStandardVo;
	private LabItemVo labItemVo;
	private LabMethodVo labMethodVo;
	private LabStandardItemVo labStandardItemVo;
	private LabStandardTypeVo labStandardTypeVo;
	private LabStandardItemMethodVo labStandardItemMethodVo;
	private List<LabStandardItemVo> standardItemList;
	private List<LabItemVo> listLabItemVo;
	private InputStream excelStream;
	private List<LabMethodVo> listLabMethodVo;
	
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private String type ;
	private File upload;// 实际上传文件
	private String fileName;

	@Resource
	public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
	}
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
	public void setLabItemService(ILabItemService labItemService) {
		this.labItemService = labItemService;
	}
	@Resource
	public void setLabMethodService(ILabMethodService labMethodService) {
		this.labMethodService = labMethodService;
	}
	@Resource
	public void setLabStandardItemService(
			ILabStandardItemService labStandardItemService) {
		this.labStandardItemService = labStandardItemService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	private void initHeader()throws GlobalException{
		if(null==labItemVo) 
			labItemVo = new LabItemVo();
		if(null==labStandardVo) 
			labStandardVo = new LabStandardVo();
		if(null==labMethodVo) 
			labMethodVo = new LabMethodVo();
		if(null==labStandardItemMethodVo) 
			labStandardItemMethodVo = new LabStandardItemMethodVo();
		if(null==listLabItemVo) 
			listLabItemVo = new ArrayList<LabItemVo>();
		if(labStandardItemVo==null)labStandardItemVo=new LabStandardItemVo();
	}
	
	public String listLabStandardItemMethod() throws GlobalException {
		initHeader();
		if (null == labStandardItemVo){
			labStandardItemVo = new LabStandardItemVo();
			labStandardItemVo.setStandardId(labStandardItemVo.getId());
		}
		if(null == labStandardVo){
			labStandardVo = new LabStandardVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("name");
		}
		if(StrUtils.isBlankOrNull(labStandardVo.getStandTypeId())){
			labStandardVo.setStandTypeId("0");
			labStandardVo.setStandTypeName(getText("standard.classify"));
		}else{
			LabStandardTypeVo typeVo= labStandardTypeService.getLabStandardType(labStandardVo.getStandTypeId().trim());
			labStandardVo.setStandTypeName(typeVo.getName());
		}
		pageResult = labStandardService.getLabStandardPR(labStandardVo, pageResult);
		return LIST;
	}
	
	public String preUpdateLabStandardItemMethod() throws GlobalException {
		initHeader();
 		labStandardVo = labStandardService.getLabStandard(id);
 		String currentPage = getParameter("currentPages");
		String pageSize = getParameter("pageSizes");
		String pagerMethod = getParameter("pagerMethods");
		if(!StrUtils.isBlankOrNull(currentPage))pageResult.setCurrentPage(currentPage);
		if(!StrUtils.isBlankOrNull(pageSize))pageResult.setPageSize(Integer.valueOf(pageSize));
		if(!StrUtils.isBlankOrNull(pagerMethod))pageResult.setPagerMethod(pagerMethod);
// 		standardItemList = labStandardItemService.getLabStandardItemVoByStandId(id,labItemVo.getName());
 		pageResult = labStandardItemService.getLabStandardItemVoByStandId(id, labItemVo.getName(), labStandardItemVo.getLabSamTypeId(), pageResult);
 		//样品类型列表
		LabSamTypeVo labSamTypeVo = new LabSamTypeVo();
		labSamTypeVo.setPid("0");
		List<LabSamTypeVo> samTypeList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("samTypeList", samTypeList);
		List<LabCodeVo> jingduList=labCodeService.getLabCodeByTypeCode("SZJD");
		setAttribute("jingduList", jingduList);
		
 		return PREUPDATE;
	}
	
	public String updateLabStandardItemMethod() throws GlobalException {
		initHeader();
		boolean flag = labStandardItemService.updateLabStandardItem(standardItemList);
		return UPDATE;
	}
	
	public String saveLabStandardItemMethod()throws GlobalException {
		initHeader();
		boolean flag = labStandardItemService.updateLabStandardItem(standardItemList);
		labStandardItemMethodVo.setStandardId(labStandardItemMethodVo.getStandardId());
		labStandardItemMethodVo.setMethodIds(labStandardItemMethodVo.getMethodIds());
		labStandardItemMethodVo.setItemId(labStandardItemMethodVo.getItemId());
		labStandardItemMethodVo.setType(labStandardItemMethodVo.getType());
		labItemVo.setName(labItemVo.getName());
		labStandardItemVo.setLabSamTypeId(labStandardItemVo.getLabSamTypeId());
		return UPDATE;
	}
	
	public String showLabMethod4select() throws GlobalException {
		initHeader();
		labStandardVo = labStandardService.getLabStandard(labStandardItemMethodVo.getStandardId());
		labItemVo = labItemService.getLabItem(labStandardItemMethodVo.getItemId());
		labStandardItemMethodVo.setStandardId(labStandardVo.getId());
		labStandardItemMethodVo.setStandardName(labStandardVo.getName());
		labStandardItemMethodVo.setItemId(labItemVo.getId());
		labStandardItemMethodVo.setItemName(labItemVo.getName());
		//List<LabMethodVo> labMethodVoList= labMethodService.getLabMethodList(labMethodVo);
		//setAttribute("labMethodVoList", labMethodVoList);
		pageResult = labMethodService.getLabMethodPR(labMethodVo, pageResult);
		String currentPage = getParameter("currentPages");
		String pageSize = getParameter("pageSizes");
		String pagerMethod = getParameter("pagerMethods");
		setAttribute("currentPages", currentPage);
		setAttribute("pageSizes", pageSize);
		setAttribute("pagerMethods", pagerMethod);
		return PREADD;
	}
	public String addLabStandardItemMethod() throws GlobalException{
		if(labStandardItemMethodVo==null)labStandardItemMethodVo=new LabStandardItemMethodVo();
		labStandardItemService.addLabStandardItemMethod(labStandardItemMethodVo);
		return ADD;
	}
	public String preAddLabMethods() throws GlobalException, UnsupportedEncodingException{
		initHeader();
		labItemVo.setName(labItemVo.getName());
		labStandardItemVo.setLabSamTypeId(labStandardItemVo.getLabSamTypeId());
		return PREADD;
	}
	

	
	public void ajaxIsExistName() throws GlobalException, IOException {
		String name=getRequest().getParameter("name").trim();
		String standId=getRequest().getParameter("standId").trim();
	//	List<LabStandardItemVo> labStandardItemVoList=labStandardItemService.getLabStandardItemVoByStandId(standId,name,"");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
//		if(labStandardItemVoList.size()>0){
//			out = response.getWriter();
//			out.write("1");
//		}else{
//			out = response.getWriter();
//			out.write("0");
//		}
	}
	public String deleteLabStandardItem() throws GlobalException, IOException {
		initHeader();
		boolean key=labStandardItemService.deleteLabStandardItem(labStandardItemVo);
		return DELETE;
	}
	
	public String showLabStandardItem4select() throws GlobalException {
		initHeader();
		String itemId = getRequest().getParameter("itemId");
		List<LabItemVo> labItemVoList= labItemService.getLabItemList(labItemVo);
		setAttribute("itemId", itemId);
		setAttribute("labItemVoList", labItemVoList);
		return PREADD;
	}
	/**
	 * 管理小项
	 * @Title:  
	 * @Description: TODO
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public String showLabStandardMinItem4select() throws GlobalException {
		initHeader();
		//List<LabItemVo> labItemVoList=labItemService.getMinLabItem(labStandardItemVo);
		//setAttribute("labItemVoList", labItemVoList);
		labItemVo.setId(labStandardItemVo.getItemId());
		pageResult = labItemService.getLabItemPR(labItemVo, pageResult);
		return PREADD;
	}
	
	public String addLabStandardItem() throws GlobalException {
		if (null == labStandardItemVo){
			labStandardItemVo = new LabStandardItemVo();
		}
		boolean key = labStandardItemService.addLabStandardItem(labStandardItemVo);
		return ADD;
	}
	public String addLabStandardMinItem() throws GlobalException {
		if (null == labStandardItemVo){
			labStandardItemVo = new LabStandardItemVo();
		}
		boolean key = labStandardItemService.addLabStandardMinItem(labStandardItemVo);
		return ADD;
	}
	public String frameLabStandardItemMethod() throws GlobalException {
		return FRAME;
	}

	public String preLabStandardItemMethod4Ztree() {
		return SHOW;
	}
	public void ajaxLabStandardItemMethod4Ztree() throws GlobalException {
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
			LabStandardTypeVo klgStandardTypeVo = labStandardTypeService
					.getLabStandardType("0");

			treeBuf.append("{name:'" + klgStandardTypeVo.getName()
					+ "', treeNid:'" + klgStandardTypeVo.getId()
					+ "', isParent:true,open:true,nodes:[" + treeSon("0")
					+ "]}");
			treeBuf.append("]");
			outPrint(response, treeBuf);
		}

	}

	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabStandardTypeVo> listLabStandardTypeVo = labStandardTypeService
				.getLabStandardTypeByPid(pid);
		try {
			if (listLabStandardTypeVo != null
					&& listLabStandardTypeVo.size() > 0) {
				for (LabStandardTypeVo vo : listLabStandardTypeVo) {
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
	
	public String preImportLabStandardItemMethod4Excel()throws GlobalException{
		if(null == labStandardItemMethodVo){
			labStandardItemMethodVo = new LabStandardItemMethodVo();
		}
		String fileUrl = labConfigService.getLabConfigByCode("standard").getValue()
		+ File.separator + "标准量化.xls";
		setAttribute("fileUrl", fileUrl);
		return PREIMPORT;
	}
	private String[] uploadFile() throws IOException, GlobalException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext().getRealPath("/uploadDoc/quantify");
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
	
	public String importLabStandardItemMethod4Excel() throws GlobalException,IOException{
		initHeader();
		if (null == labStandardItemVo){
			labStandardItemVo = new LabStandardItemVo();
		}
		if (null == labStandardTypeVo){
			labStandardTypeVo = new LabStandardTypeVo();
		}
		if (null == standardItemList){
			standardItemList = new ArrayList<LabStandardItemVo>();
		}
		LabStandardItemVo labStandardItemVoFrist = new LabStandardItemVo();
		List<LabStandardItemVo> indexSItemList = new ArrayList<LabStandardItemVo>();
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
								labStandardTypeVo.setName(value[j][0].trim());
								List<LabStandardTypeVo> labStandardTypeVoList = labStandardTypeService.getLabStandardTypeList(labStandardTypeVo);
								if(null != labStandardTypeVoList && labStandardTypeVoList.size() > 0 ){
									labStandardVo.setStandTypeId(labStandardTypeVoList.get(0).getId());
									labStandardVo.setStandTypeName(labStandardTypeVoList.get(0).getName());
								}else{
									labStandardTypeVo.setIsDel(Constants_Source.N);
									labStandardTypeService.addLabStandardType(labStandardTypeVo);
									labStandardTypeVoList = labStandardTypeService.getLabStandardTypeList(labStandardTypeVo);
									labStandardVo.setStandTypeId(labStandardTypeVoList.get(0).getId());
									labStandardVo.setStandTypeName(labStandardTypeVoList.get(0).getName());
								}
							}
							break;
						case 1:
							if (null != value[j][1] && !"".equals(value[j][1])) {
								labStandardVo.setCode(value[j][1].trim());
							}
							break;
						case 2:
							if (null != value[j][2] && !"".equals(value[j][2])) {
								List<LabStandardVo> labStandardVoList = labStandardService.getLabStandardList(labStandardVo);
								if(null != labStandardVoList && labStandardVoList.size() > 0 ){
									labStandardItemVoFrist.setStandardId(labStandardVoList.get(0).getId());
								}else{
									labStandardVo.setIsDel(Constants_Source.N);
									labStandardVo.setName(value[j][2].trim());
									labStandardService.addLabStandard(labStandardVo);
									labStandardVoList = labStandardService.getLabStandardList(labStandardVo);
									labStandardItemVoFrist.setStandardId(labStandardVoList.get(0).getId());
								}
							}
							break;
						case 3:
							if (null != value[j][3] && !"".equals(value[j][3])) {
								labItemVo.setName(value[j][3]);
								List<LabItemVo> labItemVoList = labItemService.getLabItemList(labItemVo);
								if(null != labItemVoList && labItemVoList.size() > 0 ){
									String itemsId  = labItemVoList.get(0).getId() + ",";
									String[] itemsIds = labItemVoList.get(0).getId().split(",");
									labStandardItemVoFrist.setItemsIds(itemsIds);
									labStandardItemVoFrist.setItemName(labItemVoList.get(0).getName());
									labStandardItemVo.setItemId(labItemVoList.get(0).getId());
									labStandardItemVo.setItemName(labItemVoList.get(0).getName());
								}else{
									labItemVo.setType("0");
									labItemService.addLabItem(labItemVo);
									labItemVoList = labItemService.getLabItemList(labItemVo);
									String itemsId  = labItemVoList.get(0).getId() + ",";
									String[] itemsIds = labItemVoList.get(0).getId().split(",");
									labStandardItemVoFrist.setItemsIds(itemsIds);
									labStandardItemVoFrist.setItemName(labItemVoList.get(0).getName());
									labStandardItemVo.setItemId(labItemVoList.get(0).getId());
									labStandardItemVo.setItemName(labItemVoList.get(0).getName());
								}
							}
							break;
						case 4:
							if (null != value[j][4] && !"".equals(value[j][4])) {
								try {
									labStandardItemVoFrist.setScores(value[j][4].trim());
								} catch (Exception e) {
									labStandardItemVoFrist.setScores("0.0");
									throw new GlobalException("" + e.getMessage());
								}
							}
							break;
						case 5:
							if (null != value[j][5] && !"".equals(value[j][5])) {
								labStandardItemVoFrist.setMinKey(value[j][5]);
							}
							break;
						case 6:
							if (null != value[j][6] && !"".equals(value[j][6])) {
								labStandardItemVoFrist.setMinValue(value[j][6]);
							}
							break;
						case 7:
							if (null != value[j][6] && !"".equals(value[j][7])) {
								labStandardItemVoFrist.setMaxKey(value[j][7]);
							}
							break;
						case 8:
							if (null != value[j][8] && !"".equals(value[j][8])) {
								labStandardItemVoFrist.setMaxValue(value[j][8]);
							}
							break;
						case 9:
							if (null != value[j][9] && !"".equals(value[j][9])) {
								value[j][9]=value[j][9].replace("，", ",");
								String[] names = value[j][9].split(",");
								List<LabMethodVo> labMethodVoList = labMethodService.getLabMethodByName(value[j][9].trim());
								String methodIds= "";
								if(null != labMethodVoList && labMethodVoList.size()>0 ){
									for(LabMethodVo labMethodVo : labMethodVoList){
										methodIds += labMethodVo.getId()+",";
									}
								}else{
									String code = "";
									if(names.length > 0 ){
										int h = 0 ;
										for(String name : names){
											labMethodVo.setName(name.trim());
											if(h >=10){
												code = "00"+(h+1);
											}else if(h >=100){
												code = "0"+(h+1);
											}else{
												code = String.valueOf(h+1);
											}
											labMethodVo.setCode("BH"+DateUtils.getCurrDateStr()+"-"+code);
											labMethodService.addLabMethod(labMethodVo);
											labMethodVoList = labMethodService.getLabMethodByName(name);
											try{
												methodIds += labMethodVoList.get(h).getId()+",";
											}catch(Exception e){
												throw new GlobalException("" + e.getMessage());
											}
											h++;
										}
									}else{
										labMethodVo.setName(value[j][8].trim());
										labMethodVo.setCode("BH"+DateUtils.getCurrDateStr());
										labMethodService.addLabMethod(labMethodVo);
										labMethodVoList = labMethodService.getLabMethodByName(value[j][8]);
										methodIds = labMethodVoList.get(0).getId()+",";
									}
								}
								if(methodIds.length()>0){
									methodIds = methodIds.substring(0,methodIds.length()-1);
								}
								labStandardItemMethodVo.setMethodIds(methodIds);
								
							}
							break;
						}
					}
					indexSItemList.add(labStandardItemVoFrist);
					labStandardItemVo.setIndexSItemList(indexSItemList);
					labStandardItemVo.setStandardId(labStandardItemVoFrist.getStandardId());
					standardItemList.add(labStandardItemVo);
					List<LabStandardItemVo> labStandardItemVoList = labStandardItemService.getLabStandardItemVoByStandId(labStandardItemVoFrist.getStandardId(), null,labStandardItemVo.getItemId());		
					if(labStandardItemVoList.size() <= 0 ){
						labStandardItemService.addLabStandardItem(labStandardItemVoFrist);
					}
					labStandardItemService.updateImportLabStandardItem(labStandardItemVo,standardItemList); 
					labStandardItemMethodVo.setStandardId(labStandardItemVoFrist.getStandardId());
					labStandardItemMethodVo.setItemId(labStandardItemVo.getItemId());
					labStandardItemService.addLabStandardItemMethod(labStandardItemMethodVo);
					type = "1";
					}
				}
			}
		} catch (Exception e) {
			type = "0";
			//e.printStackTrace();
		}
		target.delete();
		getRequest().setAttribute("type", type);
		return IMPORT;
	}
	public String exportLabStandardItemMethod4Excel() throws GlobalException,UnsupportedEncodingException {
		initHeader();

		List<LabStandardItemMethodVo> labStandardItemMethodVoList = labStandardItemService.getLabStandardItemMethodList();
		String path = "";
		Map<String, List<LabStandardItemMethodVo>> beans = new HashMap<String, List<LabStandardItemMethodVo>>();
		beans.put("labStandardItemMethodVoList", labStandardItemMethodVoList);
		fileName = new String(("标准量化信息目录").getBytes("GB2312"), "ISO8859_1") + ".xls";

		String template = "exportQuantify.xls";
		String templateFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ labConfigService.getLabConfigByCode("standard")
						.getValue() + File.separator + template;

		String destFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ "templates"
				+ File.separator
				+ "temp"
				+ File.separator
				+ System.currentTimeMillis() + "." + "xls";

		String realPath = ServletActionContext.getRequest().getRealPath("/")
				+ "templates" + File.separator + "temp" + File.separator;
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}

		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFileName, beans, destFileName);
			File targetFile = new File(destFileName);
			excelStream = new BufferedInputStream(new FileInputStream(
					targetFile), 16 * 1024);
			path = targetFile.getPath();
			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return EXPORT;
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

	public LabMethodVo getLabMethodVo() {
		return labMethodVo;
	}

	public void setLabMethodVo(LabMethodVo labMethodVo) {
		this.labMethodVo = labMethodVo;
	}

	public LabStandardItemVo getLabStandardItemVo() {
		return labStandardItemVo;
	}

	public void setLabStandardItemVo(LabStandardItemVo labStandardItemVo) {
		this.labStandardItemVo = labStandardItemVo;
	}


	public LabStandardItemMethodVo getLabStandardItemMethodVo() {
		return labStandardItemMethodVo;
	}

	public void setLabStandardItemMethodVo(
			LabStandardItemMethodVo labStandardItemMethodVo) {
		this.labStandardItemMethodVo = labStandardItemMethodVo;
	}

	public List<LabStandardItemVo> getStandardItemList() {
		return standardItemList;
	}

	public void setStandardItemList(List<LabStandardItemVo> standardItemList) {
		this.standardItemList = standardItemList;
	}

	public List<LabItemVo> getListLabItemVo() {
		return listLabItemVo;
	}

	public void setListLabItemVo(List<LabItemVo> listLabItemVo) {
		this.listLabItemVo = listLabItemVo;
	}

	public List<LabMethodVo> getListLabMethodVo() {
		return listLabMethodVo;
	}

	public void setListLabMethodVo(List<LabMethodVo> listLabMethodVo) {
		this.listLabMethodVo = listLabMethodVo;
	}

	public LabStandardTypeVo getLabStandardTypeVo() {
		return labStandardTypeVo;
	}

	public void setLabStandardTypeVo(LabStandardTypeVo labStandardTypeVo) {
		this.labStandardTypeVo = labStandardTypeVo;
	}
	
	
}
