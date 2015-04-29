package cn.labsoft.labos.source.appara.action;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.service.ILabApparaOpenService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaOpenVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.FileUtils;
@Controller
@Scope("prototype")
public class LabApparaOpenAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaOpenService labApparaOpenService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaOpenVo labApparaOpenVo;
	private LabApparaVo labApparaVo;
	@Resource
	public void setLabApparaOpenService(ILabApparaOpenService labApparaOpenService) {
		this.labApparaOpenService = labApparaOpenService;
	}
	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	/**
	 * 仪器 启用 清单
	 * @throws GlobalException 
	 * */
	public String listLabApparaOpen4View() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		pageResult=labApparaOpenService.getLabApparaOpenPR(labApparaOpenVo, pageResult);
		return LIST;
	}
	/**
	 * 启用 仪器 列表
	 * */
	public String listLabApparaOpen()throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		if(null==labApparaOpenVo.getStatus()){
			labApparaOpenVo.setStatus(getSessionContainer().getFunId());
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		pageResult=labApparaOpenService.getLabApparaOpenPR(labApparaOpenVo, pageResult);
		return LIST;
	}
	/**
	 * 启用 查看
	 * @throws GlobalException 
	 * */
	public String showLabAppOpen() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		return SHOW;
	}
	/**
	 * 启用 新增
	 * */
	public String preAddLabApparaOpen() throws GlobalException{
		if(labApparaOpenVo==null)labApparaOpenVo=new LabApparaOpenVo();
		return PREADD;
	}
	/**
	 * 启用 新增 保存
	 * */
	public String addLabApparaOpen() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.addLabApparaOpen(labApparaOpenVo);
		if(LabWfConstant.BUS_GO.equals(labApparaOpenVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 启用 修改
	 * */
	public String preUpdateAppOpen() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		return PREUPDATE;
	}
	/**
	 * 启用 修改 保存
	 * */
	public String updateApparaOpen() throws GlobalException{
		if(null==labApparaOpenVo){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.updateLabApparaOpen(labApparaOpenVo);
		if(LabWfConstant.BUS_GO.equals(labApparaOpenVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	
	/**
	 * 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaOpenAudit() throws GlobalException{
		if(null==labApparaOpenVo){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.updateLabApparaOpen(labApparaOpenVo);
		return LIST;
	}
	/**
	 * 启用 审核 列表
	 * */
	public String listLabAppOpenAudit()throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaOpenVo.getStatus()){
			labApparaOpenVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaOpenService.getLabApparaOpenPR(labApparaOpenVo, pageResult);
		return LIST;
	}
	/**
	 * 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateAppOpenAudit() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		labApparaOpenVo.setUser2(getSessionContainer().getUserName());
		labApparaOpenVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 启用 审批 列表
	 * */
	public String listLabAppOpenPaudit()throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaOpenVo.getStatus()){
			labApparaOpenVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaOpenService.getLabApparaOpenPR(labApparaOpenVo, pageResult);
		return LIST;
	}
	/**
	 * 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateAppOpenPaudit() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		labApparaOpenVo.setUser3(getSessionContainer().getUserName());
		labApparaOpenVo.setDate3(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaOpenPaudit() throws GlobalException{
		if(null==labApparaOpenVo){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.updateLabApparaOpen(labApparaOpenVo);
		return UPDATE;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printLabApparaOpen() throws GlobalException{
		if(labApparaOpenVo==null){
			labApparaOpenVo=new LabApparaOpenVo();
		}
		labApparaOpenVo=labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		return "print";
	}
	public String exportApparaOpen() throws GlobalException {
		//将此方法改造成导出excel
		if (null == labApparaOpenVo) {
			labApparaOpenVo = new LabApparaOpenVo();
		}
		labApparaOpenVo = labApparaOpenService.getLabApparaOpen(labApparaOpenVo);
		try {
			exportToExcel(labApparaOpenVo);
		} catch (UnsupportedEncodingException e) {
			addActionMessage(getText("operation.fail"));
			throw new GlobalException("" + e.getMessage());
		}
		
		return "exportExcel";
	}
	private String fileName;
	private InputStream excelStream;
	//jxls导出excel   START
	@SuppressWarnings("deprecation")
	private String exportToExcel(LabApparaOpenVo labApparaOpenVo) throws UnsupportedEncodingException, GlobalException {
		String path="";
		Map<String, LabApparaOpenVo> beans = new HashMap<String, LabApparaOpenVo>();
		beans.put("data", labApparaOpenVo);
    	
    	String tempFilePath="";
		fileName = "仪器设备启用记录表.xls";
		tempFilePath="exportApparatusOpenRecord.xls";
    	
		fileName=new String(fileName.getBytes("GB2312"),"ISO8859-1");
    	String templateFileName = ServletActionContext.getRequest()
				.getRealPath("/")+"jsp"+ File.separator+"apparatus"+ File.separator+"export"+File.separator+ "templates" + File.separator+ tempFilePath;
    	
		String destFileName = ServletActionContext.getRequest()
				.getRealPath("/")+"jsp"+ File.separator+"apparatus"+ File.separator+"export"+File.separator+"templates"+File.separator+"temp" + File.separator
				+ System.currentTimeMillis() + "." + "xls";
		
		String realPath =  ServletActionContext.getRequest().getRealPath("/")+"jsp"+ File.separator+"apparatus"+ File.separator+"export"+File.separator+ "templates"+File.separator+"temp" + File.separator;
		File file  = new File(realPath);
		if(!file.exists()){
			file.mkdir();
		}
		
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFileName, beans, destFileName);
			File targetFile = new File(destFileName);
			excelStream = new BufferedInputStream(new FileInputStream(targetFile), 16 * 1024);
			path=targetFile.getPath();
			targetFile.delete();
			FileUtils.delAllFile(realPath);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
        return path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public LabApparaOpenVo getLabApparaOpenVo() {
		return labApparaOpenVo;
	}

	public void setLabApparaOpenVo(LabApparaOpenVo labApparaOpenVo) {
		this.labApparaOpenVo = labApparaOpenVo;
	}

	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	
}
