package cn.labsoft.labos.source.appara.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.encoder.service.ILabEncoderService;
import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.service.ILabApparaTypeService;
import cn.labsoft.labos.source.appara.vo.LabApparaProvVo;
import cn.labsoft.labos.source.appara.vo.LabApparaRepairVo;
import cn.labsoft.labos.source.appara.vo.LabApparaTypeVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;
import cn.labsoft.labos.utils.StrUtils;

/**
 * <strong>Title : LabApparaAction </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : May 4, 2014 1:40:42 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong><br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

@Controller
@Scope("prototype")
public class LabApparaAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabQueryService labQueryService;
	private ILabApparaService labApparaService;
	private ILabUserService labUserService;
	private ILabApparaTypeService labApparaTypeService;
	private ILabEncoderService labEncoderService;
	private ILabTemplateService labTemplateService;
	private ILabUploadService labUploadService;
	private ILabSupplierService labSupplierService;
	private LabApparaVo labApparaVo;
	private LabApparaProvVo labApparaProvVo;
	private LabApparaRepairVo labApparaRepairVo;
	private LabApparaTypeVo labApparaTypeVo;
	private LabSupplierVo labSupplierVo;
	private List<LabUserVo> labUserList;
	//导入清单
	private File upload;
	private String uploadFileName;
	private List<LabApparaVo> labApparaVoList;
	//导出清单
	private String url;
	private String filename;
	private InputStream excelStream;
	private LabEncoderVo labEncoderVo;
	private LabQueryVo labQueryVo;
	private String typeId;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}
	@Resource
	public void setLabSupplierService(ILabSupplierService labSupplierService) {
		this.labSupplierService = labSupplierService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabApparaTypeService(ILabApparaTypeService labApparaTypeService) {
		this.labApparaTypeService = labApparaTypeService;
	}
	@Resource
	public void setLabEncoderService(ILabEncoderService labEncoderService) {
		this.labEncoderService = labEncoderService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	@Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	/**
	 * @Title: 仪器清单 分割页面
	 */
	public String frameLabAppara(){
		if (null == labApparaVo) {
			labApparaVo = new LabApparaVo();
		}
		return FRAME;
	}
	/**
	 * 仪器 分类 树 action
	 * */
	public String preLabApparaZtree(){
		return SHOW;
	}
	/**
	 * 获取 仪器 分类 tree
	 * */
	public void ajaxLabApparaZtree() throws GlobalException {
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
			LabApparaTypeVo labApparaTypeVo = labApparaTypeService.getLabApparaType("0");
			treeBuf.append("{name:'" + labApparaTypeVo.getAppName()
					+ "', treeNid:'" + labApparaTypeVo.getId()
					+ "', isParent:true,open:true,nodes:[" + treeSon("0")
					+ "]}");
			treeBuf.append("]");
			outPrint(response, treeBuf);
		}
	}
	/**
	 * 仪器 tree 新增
	 * */
	public void ajaxLabAppType4Add()throws GlobalException
	{
		if (null == labApparaTypeVo) {
			labApparaTypeVo = new LabApparaTypeVo();
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
				labApparaTypeVo.setLabAppTypeId(parentId);
				if(!StrUtils.isBlankOrNull(id)){
					labApparaTypeVo.setId(id);
					labApparaTypeVo.setAppName(name);
					labApparaTypeService.updateLabApparaType(labApparaTypeVo);
				}else{
					labApparaTypeVo.setAppName(getText("appratus.unknow"));
					labApparaTypeVo=labApparaTypeService.addLabApparaType(labApparaTypeVo);
				}
				out = response.getWriter();
				out.write(labApparaTypeVo.getId());
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
	/**
	 * 仪器 tree 修改
	 * @throws GlobalException 
	 * */
	public void ajaxLabAppType4Update() throws GlobalException{
		if (null == labApparaTypeVo) {
			labApparaTypeVo = new LabApparaTypeVo();
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
				labApparaTypeVo=labApparaTypeService.getLabApparaType(id);
				labApparaTypeVo.setAppName(name);
				labApparaTypeService.updateLabApparaType(labApparaTypeVo);
			
				out = response.getWriter();
				out.write("true");
			} catch (Exception e) {
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
	/**
	 * 仪器  tree  删除
	 * @throws GlobalException 
	 * */
	public void ajaxLabAppType4Delete() throws GlobalException{
		if (null == labApparaTypeVo) {
			labApparaTypeVo = new LabApparaTypeVo();
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
				labApparaTypeService.deleteLabApparaType(ids);
				labApparaService.deleteLabApparaByAppType(id);
				out = response.getWriter();
				out.write("true");
			} catch (Exception e) {
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
	public StringBuffer treeSon(String pid) throws GlobalException {
		StringBuffer firstTree = new StringBuffer();
		List<LabApparaTypeVo> voList = labApparaTypeService.getLabApparaTypeByPid(pid);
		try {
			if (voList != null && voList.size() > 0) {
				for (LabApparaTypeVo vo : voList) {
					String filename = vo.getAppName();
					String id = vo.getId();
					if (labApparaTypeService.getLabApparaTypeByPid(id).size() > 0) {
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
	/**
	 * 得到仪器类型以及它下面的分类
	 * */
	public void ajaxLabApparaTree() throws GlobalException
	{	
		String parentId = getRequest().getParameter("treeNid");
		StringBuffer treeBuf = new StringBuffer();
		// 第一次进来时构造树结构
		StringBuffer firstTree = new StringBuffer();
		// 开始拼接JSON对象字符串
		treeBuf.append("[");
		List<LabApparaTypeVo> typeList = labApparaTypeService.getLabApparaTypeByPid(parentId);
		List<LabApparaVo> list = labApparaService.getApparaListByTypeId(parentId);
		try {
			if(null != typeList && typeList.size() > 0) {
				for(LabApparaTypeVo vo:typeList){
					firstTree.append("{name:'" + vo.getAppName() + "', treeNid:'"
							+ vo.getId() + "', isParent:true},");
					}
			}
			
			if(null!=list&&list.size()>0){
				if(!"[".equals(treeBuf.toString())) treeBuf.append(",");
				for(LabApparaVo vo:list){
					firstTree.append("{name:'" + vo.getName() + "', treeNid:'"
							+ vo.getId() + "', isParent:false},");
					}
			}
			if(firstTree.length()>0)firstTree.replace(firstTree.length()-1, firstTree.length(),"");
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
		outPrint(getResponse(),treeBuf);
	}

	/**
	 * 获取仪器列表
	 * */
	public String listLabAppara() throws GlobalException{
		if(null == labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		pageResult = labApparaService.getLabApparaList(labApparaVo, pageResult);
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labApparaVo.setFilePath(path);
		//获取分类名称
		if(!StrUtils.isBlankOrNull(labApparaVo.getTypeId())){
			LabApparaTypeVo labApparaTypeVo = labApparaTypeService.getLabApparaType(labApparaVo.getTypeId());
			labApparaVo.setApparaTypeName(labApparaTypeVo.getAppName());
		}else{
			LabApparaTypeVo labApparaTypeVo = labApparaTypeService.getLabApparaType("0");
			labApparaVo.setApparaTypeName(labApparaTypeVo.getAppName());
		}
		return LIST;
	}
	/**
	 * 
	 * @Title: 仪器清单 查看 仪器基本信息 
	 * @Description: TODO
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public String showLabAppara() throws GlobalException {
		if (null == labApparaVo) {
			labApparaVo = new LabApparaVo();
		}
		String typeId = labApparaVo.getTypeId();
		labApparaVo = labApparaService.getLabAppara(labApparaVo.getId());
		labApparaVo.setTypeId(typeId);
		//获取 所上传的附件
		//仪器附件
		labApparaVo.setFileList(labApparaService.getLabAppFile(labApparaVo, Constant.APP_TYPE1));
		return SHOW;
	}
	/**
	 * 仪器预添加
	 * */
	public String preAddLabAppara() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
			labApparaVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		//仪器附件
		labApparaVo.setFileList(labApparaService.getLabAppFile(labApparaVo, Constant.APP_TYPE1));
		//仪器管理人员名单
		labUserList = labUserService.getLabUserList(new LabUserVo());
		//仪器类型
		String typeId = getParameter("typeId");
		labApparaVo.setTypeId(typeId);
		return SUCCESS;
	}
	/**
	 * 添加 仪器 保存
	 * */
	public String addLabAppara() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		labApparaService.addLabAppara(labApparaVo);
		return SUCCESS;
	}
	/**
	 * 删除 仪器 附件
	 * */
	public String delLabAppFile() throws Exception{
		String id = getRequest().getParameter("fileId");
		boolean key = labApparaService.delLabAppFile(id);
		outPutString(String.valueOf(key));
		return null;
	}
	/**
	 * 仪器 保管人员 
	 * @throws GlobalException 
	 * */
	public String showUser4Select() throws GlobalException{
		try {
			labUserList = labUserService.getLabUserList(new LabUserVo());
		} catch (GlobalException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return SHOW;
	}
	/**
	 * 仪器供应商 
	 * */
	public String showSupplier4Select()throws GlobalException{
		if(null==labSupplierVo){
			labSupplierVo = new LabSupplierVo();
		}
		labSupplierVo.setGoodsType(getText("appratus.goods.type"));
		pageResult=labSupplierService.getLabSupplierPR(labSupplierVo, pageResult);
		
		return SHOW;
	}
	/**
	 * 仪器预修改
	 * */
	public String preUpdateLabApp() throws GlobalException{
		labApparaVo = getLabApparaValue();
		//仪器管理人员名单
		labUserList = labUserService.getLabUserList(new LabUserVo());
		//仪器附件
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labApparaVo.getId(), "lab-app");
		setAttribute("loadList", loadList);
		return SUCCESS;
	}
	/**
	 * 仪器 修改 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApp() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		labApparaService.updateLabAppara(labApparaVo);
		return UPDATE;
	}
	/**
	 * 删除仪器
	 * @throws GlobalException 
	 * */
	public String delLabApp() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		try {
			for(String id:labApparaVo.getIds())
			  labApparaService.deleteLabAppara(id);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return SHOW;
	}
	/**
	 * 获取 仪器清单导入模板
	 * */
	public String getLabAppExcel(){
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		labApparaVo.setModName(Constant.APP_DOWNEXCEL);
		labApparaVo.setModPath(labApparaService.getLabApparaExcel());
		return LIST;
	}
	/**
	 * 导入 仪器清单模板
	 * @throws GlobalException 
	 * */
	public String importLabAppExcel() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		if(null == upload){
			addActionError(getText("file.path.unnull"));
			return ERROR;
		}else{
			labApparaService.importLabAppExcel(uploadFileName,upload,labApparaVo);
		}
		return LIST;
	}
	
	
	public String showLabApp4printTwoCode() throws GlobalException {
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		labApparaVoList = new ArrayList<LabApparaVo>();
		String[] ids = labApparaVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labApparaVoList.add(labApparaService.getLabAppara(id));
			}
		}
		//二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(getSessionContainer().getFunId(), labApparaVoList,
				LabEncoderVo.EWM_ENCODER);
		return "print";
	}

	public String showLabApp4printBarCode() throws GlobalException {
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		labApparaVoList = new ArrayList<LabApparaVo>();
		String[] ids = labApparaVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labApparaVoList.add(labApparaService.getLabAppara(id));
			}
		}
		//打印条形码
		labEncoderVo = labEncoderService.getLabEncoder4Print(getSessionContainer().getFunId(), labApparaVoList,
				LabEncoderVo.TXM_ENCODER);
		return "print";
	}
	/**
	 * 导出 仪器清单
	 * */
	public String exportLabAppExcel()throws GlobalException {
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		try {
			labApparaVo = labApparaService.getLabApparaVo(labApparaVo);
			filename = "仪器清单.xls";
			filename=new String(filename.getBytes("GB2312"),"ISO8859-1");
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		excelStream=labApparaService.export2Excel(labApparaVo);
		return "exportLabAppara";
	}
	public String labLabAppUserQuery() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("402881ea470e61bb01470e7941f9004a");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
 		if(labQueryVo.getQueryType().equals(Constants_Source.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	public String labLabAppQuery() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("4028818c46ff69af0146ffc430180018");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
 		if(labQueryVo.getQueryType().equals(Constants_Source.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	private LabApparaVo getLabApparaValue() throws GlobalException{
		return labApparaService.getLabAppara(labApparaVo.getId());
	}

	public LabApparaRepairVo getLabApparaRepairVo() {
		return labApparaRepairVo;
	}

	public void setLabApparaRepairVo(LabApparaRepairVo labApparaRepairVo) {
		this.labApparaRepairVo = labApparaRepairVo;
	}

	public LabApparaProvVo getLabApparaProvVo() {
		return labApparaProvVo;
	}

	public void setLabApparaProvVo(LabApparaProvVo labApparaProvVo) {
		this.labApparaProvVo = labApparaProvVo;
	}

	public LabApparaTypeVo getLabApparaTypeVo() {
		return labApparaTypeVo;
	}

	public void setLabApparaTypeVo(LabApparaTypeVo labApparaTypeVo) {
		this.labApparaTypeVo = labApparaTypeVo;
	}

	public List<LabUserVo> getLabUserList() {
		return labUserList;
	}

	public void setSysUserList(List<LabUserVo> labUserList) {
		this.labUserList = labUserList;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<LabApparaVo> getLabApparaVoList() {
		return labApparaVoList;
	}
	public void setLabApparaVoList(List<LabApparaVo> labApparaVoList) {
		this.labApparaVoList = labApparaVoList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public LabEncoderVo getLabEncoderVo() {
		return labEncoderVo;
	}
	public void setLabEncoderVo(LabEncoderVo labEncoderVo) {
		this.labEncoderVo = labEncoderVo;
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	public void setLabUserList(List<LabUserVo> labUserList) {
		this.labUserList = labUserList;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public LabSupplierVo getLabSupplierVo() {
		return labSupplierVo;
	}
	public void setLabSupplierVo(LabSupplierVo labSupplierVo) {
		this.labSupplierVo = labSupplierVo;
	}
	public ILabQueryService getLabQueryService() {
		return labQueryService;
	}
	@Resource
	public void setLabQueryService(ILabQueryService labQueryService) {
		this.labQueryService = labQueryService;
	}
	public LabQueryVo getLabQueryVo() {
		return labQueryVo;
	}
	public void setLabQueryVo(LabQueryVo labQueryVo) {
		this.labQueryVo = labQueryVo;
	}

}
