package cn.labsoft.labos.source.doc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.doc.service.ILabDocApplyService;
import cn.labsoft.labos.source.doc.service.ILabDocService;
import cn.labsoft.labos.source.doc.vo.LabDocApplyVo;
import cn.labsoft.labos.source.doc.vo.LabDocVo;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.solr.SolrClientUtils;

@Controller
@Scope("prototype")
public class LabDocAction extends BaseAction{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private ILabDocService labDocService;
	private ILabDocApplyService labDocApplyService;
	private ILabUploadService labUploadService;
	private ILabOrgService labOrgService;
	private ILabCodeService labCodeService;
	private LabDocVo labDocVo;
	private List<LabDocVo> labDocVoList;
	private List<LabDocVo> labDocVoFileList;
	private List<LabDocApplyVo> labDocApplyVoList;
	private List<LabCodeVo> docTypeList;
	private LabDocApplyVo labDocApplyVo;
	private LabUpload labUpload;
	private String path="";
	private String literatureJSONList;
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabDocService(ILabDocService labDocService) {
		this.labDocService = labDocService;
	}
	@Resource
	public void setLabDocApplyService(ILabDocApplyService labDocApplyService) {
		this.labDocApplyService = labDocApplyService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	public List<LabDocApplyVo> getLabDocApplyVoList() {
		return labDocApplyVoList;
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
	public List<LabDocVo> getLabDocVoList() {
		return labDocVoList;
	}
	public void setLabDocVoList(List<LabDocVo> labDocVoList) {
		this.labDocVoList = labDocVoList;
	}
	
	public List<LabDocVo> getLabDocVoFileList() {
		return labDocVoFileList;
	}
	public void setLabDocVoFileList(List<LabDocVo> labDocVoFileList) {
		this.labDocVoFileList = labDocVoFileList;
	}
	public String listLabDoc() throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
			labDocVo.setId("1");
		}
		//文件夹列表
		labDocVoList= labDocService.getLabDocListByPid(labDocVo.getId(),Constants_Source.Y);
		setAttribute("labDocVoList", labDocVoList);
		//文件列表
		List<LabDocVo> labDocVoFileList=labDocService.getLabDocListByPid(labDocVo.getId(), Constants_Source.N);
		setAttribute("labDocVoFileList", labDocVoFileList);
		//我的申请列表
		labDocApplyVoList = labDocApplyService.getLabDocApplyList();
		setAttribute("labDocApplyVoList", labDocApplyVoList);
		//我的审核列表
		List<LabDocApplyVo> labDocApplyVoOtherList=labDocApplyService.getLabDocOterApplyList(labDocVo);
		setAttribute("labDocApplyVoOtherList", labDocApplyVoOtherList);
		labDocVo=labDocService.getLabDocById(labDocVo.getId());
		path=labDocVo.getPath();
		if(labDocVo.getPid()==null){
			return "list1";
		}else{
			return LIST;
		}
	}
	
//	public String listLabDoc4Sub()throws GlobalException{
//		if(null ==labDocVo){
//			labDocVo= new LabDocVo();
//			labDocVo.setPid("1");
//		}
//		labDocVoList=labDocService.getLabDocListByPid(labDocVo.getPid(),Constants_Source.Y);
//		setAttribute("labDocVoList", labDocVoList);
//		labDocVoFileList=labDocService.getLabDocListByPid(labDocVo.getPid(),Constants_Source.N);
//		setAttribute("labDocVoList", labDocVoList);
//		return LIST;
//	}
	
	public String showLabDocFolder4Select()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
			labDocVo.setPid("1");
		}
		return PREADD;
	}
	
	public String addLabDocFolder()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo = labDocService.addLabDocForFolder(labDocVo);
		labDocVo.setId(labDocVo.getPid());
		labDocVo.setDocId(labDocVo.getId());
		return ADD;
	}
	public String showLabDocFolder4Update()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo = labDocService.getLabDocById(labDocVo.getId());
		return PREUPDATE;
	}
	public String updateLabDocFolder()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo = labDocService.updateLabDocForFolder(labDocVo);
		labDocVo.setId(labDocVo.getPid());
		labDocVo.setDocId(labDocVo.getId());
		return ADD;
	}
	public String deleteLabDocFolder()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocService.deleteLabDoc(labDocVo.getId());
		labDocVo.setDocId(labDocVo.getId());
		labDocVo.setId(labDocVo.getPid());
		return DELETE;
	}
	public String preAddLabDocFile()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
			labDocVo.setPid("1");
		}
		if(StrUtils.isBlankOrNull(labDocVo.getUuid())){
			labDocVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labDocVo.getUuid(), "doc");
		if(null!=loadList&&loadList.size()>0){
			labDocVo.setFileName(loadList.get(0).getName().substring(0, loadList.get(0).getName().lastIndexOf(".")));
		}
		setAttribute("loadList", loadList);
		
		LabDocVo plabDocVo=labDocService.getLabDocById(labDocVo.getPid());
		path=plabDocVo.getPath();
		//文件类型
		docTypeList = labCodeService.getLabCodeByTypeCode("WJLB");
		return PREADD;
	}
	public String addLabDocFile()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo=labDocService.addLabDoc(labDocVo);
		labDocVo.setId(labDocVo.getPid());
		
		
		return ADD;
	}
//	public String preAddLabDocFileDetail()throws GlobalException{
//		if(null ==labDocVo){
//			labDocVo= new LabDocVo();
//			labDocVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
//		}
//		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labDocVo.getUuid(), "doc");
//		setAttribute("loadList", loadList);
//		return PREADD;
//	}

	public String deleteLabDocFile() throws GlobalException{
		if(null == labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocService.deleteLabDoc(labDocVo.getId());
		labDocVo.setId(labDocVo.getPid());
		return DELETE;
	}
	public String preUpdateLabDocFile()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo =labDocService.getLabDocById(labDocVo.getId()); 
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labDocVo.getId(), "doc");
		setAttribute("loadList", loadList);
		LabDocVo plabDocVo=labDocService.getLabDocById(labDocVo.getPid());
		path=plabDocVo.getPath();
		return PREUPDATE;
	}
	public String updateLabDocFile()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
			labDocVo.setPid("1");
		}
		 labDocService.updateLabDoc(labDocVo);
		 labDocVo.setId(labDocVo.getPid());
		return UPDATE;
	}
	public String deleteUploadFile()throws GlobalException{
		if(null==labDocVo){
			labDocVo = new LabDocVo();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			String result="0";
			Boolean key=labUploadService.deleteFileFromUpload(labDocVo.getFileId());
			if(key){
				result="1";
			}
			out.write(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return null;
	}
	public String showLabDoc4Select()throws GlobalException{
		if(null == labDocVo){
			labDocVo= new LabDocVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
		.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labDocVo.setUserId(sessionContainer.getUserId());
		labDocVoList=labDocService.getLabDocAllOtherList(labDocVo);
		setAttribute("labDocVoList", labDocVoList);
		List<LabOrgVo> orgList=labOrgService.getLabOrgList(new LabOrgVo());
		setAttribute("orgList", orgList);
		return LIST;
	}
	public String showLabDocFile4Select()throws GlobalException{
		if(null ==labDocVo){
			labDocVo= new LabDocVo();
		}
		labDocVo =labDocService.getLabDocById(labDocVo.getId()); 
		List<LabUploadVo> loadList=labUploadService.getLabUploadList(labDocVo.getId(), "doc");
		setAttribute("loadList", loadList);
		LabDocVo plabDocVo=labDocService.getLabDocById(labDocVo.getPid());
		path=plabDocVo.getPath();
		return SHOW;
	}
	public String ajaxCheckLabDocApply() throws GlobalException{
		if(null == labDocVo){
			labDocVo= new LabDocVo();
		}
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("ID");
		String result="0";
		List<LabDocApplyVo> volist= labDocApplyService.getLabDocApplyVoById(id);
		if(null!=volist){
			if(volist.size()>0){
				result="1";
			}
		}
		try {
			outPutString(result);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}
	/**
	 * 文献检索
	 * @throws GlobalException 
	 * */
	public String listLabDocSolr() throws GlobalException{
		if(null == labUpload){
			labUpload= new LabUpload();
		}
		if(null==labDocVo){
			labDocVo = new LabDocVo();
		}
		try {
			if(!StrUtils.isBlankOrNull(labUpload.getContent())){
				pageResult = SolrClientUtils.queryAll(labUpload, getPageResult());
				labUpload.setContent(labUpload.getContent().trim());
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		
		return LIST;
	}
	/**
	 * 高级查询
	 * */
	public String listLabDocSolr2db() throws GlobalException{
		if(null==labDocVo){
			labDocVo = new LabDocVo();
		}
		if(!StrUtils.isBlankOrNull(labDocVo.getLiterature())){
			literatureJSONList = JSONArray.fromObject(labDocVo.getLiterature().split(",")).toString();
		}else{
			literatureJSONList = JSONArray.fromObject(new String[]{"null"}).toString();
		}
		if(!StrUtils.isBlankOrNull(labDocVo.getExt2())){
			labDocVoList = new ArrayList();
			String []ext2=labDocVo.getExt2().split(",");
			String []ext3=labDocVo.getExt3().split(",");
			String []ext4=labDocVo.getExt4().split(",");
			String []ext5=labDocVo.getExt5().split(",");
			for (int i =0;i<ext2.length;i++) {
				LabDocVo vo = new LabDocVo();
				vo.setExt2(ext2[i].trim());
				vo.setExt3(ext3[i].trim());
				vo.setExt4(ext4[i].trim());
				vo.setExt5(ext5[i].trim());
				labDocVoList.add(vo);
			}
		}		
		pageResult=labDocService.getLabDocSolrPR(labDocVo, getPageResult());
		//文件类型
		docTypeList = labCodeService.getLabCodeByTypeCode("WJLB");
		return LIST;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public LabDocApplyVo getLabDocApplyVo() {
		return labDocApplyVo;
	}
	public void setLabDocApplyVo(LabDocApplyVo labDocApplyVo) {
		this.labDocApplyVo = labDocApplyVo;
	}
	public LabUpload getLabUpload() {
		return labUpload;
	}
	public void setLabUpload(LabUpload labUpload) {
		this.labUpload = labUpload;
	}
	public List<LabCodeVo> getDocTypeList() {
		return docTypeList;
	}
	public void setDocTypeList(List<LabCodeVo> docTypeList) {
		this.docTypeList = docTypeList;
	}
	public String getLiteratureJSONList() {
		return literatureJSONList;
	}
	public void setLiteratureJSONList(String literatureJSONList) {
		this.literatureJSONList = literatureJSONList;
	}
	
	
}
