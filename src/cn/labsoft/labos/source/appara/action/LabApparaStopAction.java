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
import cn.labsoft.labos.source.appara.service.ILabApparaStopService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaStopVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.FileUtils;
@Controller
@Scope("prototype")
public class LabApparaStopAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabApparaStopService labApparaStopService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaStopVo labApparaStopVo;
	private LabApparaVo labApparaVo;
	
	@Resource
	public void setLabApparaStopService(ILabApparaStopService labApparaStopService) {
		this.labApparaStopService = labApparaStopService;
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
	 * 仪器 停用 清单
	 * @throws GlobalException 
	 * */
	public String listLabLabApparaStop4View() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo = new LabApparaStopVo();
		}
		pageResult=labApparaStopService.getLabApparaStopPR(labApparaStopVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器 停用 列表
	 * */
	public String listLabApparaStop()throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo = new LabApparaStopVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaStopVo.getStatus()){
			labApparaStopVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaStopService.getLabApparaStopPR(labApparaStopVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器 停用 查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaStop() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo = new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.getLabApparaStop(labApparaStopVo);
		return SHOW;
	}
	/**
	 * 停用 新增
	 * */
	public String preAddLabApparaStop() throws GlobalException{
		if(labApparaStopVo==null){
			labApparaStopVo=new LabApparaStopVo();
		}
		return PREADD;
	}
	/**
	 * 停用 新增 保存
	 * */
	public String addLabApparaStop() throws GlobalException{
		if(labApparaStopVo==null){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.addLabApparaStop(labApparaStopVo);
		if(LabWfConstant.BUS_GO.equals(labApparaStopVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 停用 修改
	 * */
	public String preUpdateLabApparaStop() throws GlobalException{
		if(labApparaStopVo==null){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.getLabApparaStop(labApparaStopVo);
		return PREUPDATE;
	}
	/**
	 * 停用 修改 保存
	 * */
	public String updateAppStop() throws GlobalException{
		if(labApparaStopVo==null){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.updateLabApparaStop(labApparaStopVo);
		if(LabWfConstant.BUS_GO.equals(labApparaStopVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 审核 列表
	 * */
	public String listLabAppStopAudit() throws GlobalException{
 		if(null==labApparaStopVo){
			labApparaStopVo=new LabApparaStopVo();
		}
 		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaStopVo.getStatus()){
			labApparaStopVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaStopService.getLabApparaStopPR(labApparaStopVo, pageResult);
		return LIST;
	}
	/**
	 * 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateAppStopAudit() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.getLabApparaStop(labApparaStopVo);
		labApparaStopVo.setDate2(DateUtils.getCurrDateStr());
		labApparaStopVo.setUser2(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	/**
	 * 审批 列表
	 * */
	public String listLabAppStopPaudit() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo=new LabApparaStopVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaStopVo.getStatus()){
			labApparaStopVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaStopService.getLabApparaStopPR(labApparaStopVo, pageResult);
		return LIST;
	}
	/**
	 * 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateAppStopPaudit() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.getLabApparaStop(labApparaStopVo);
		labApparaStopVo.setDate3(DateUtils.getCurrDateStr());
		labApparaStopVo.setUser3(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	/**
	 * 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateAppStopPaudit() throws GlobalException{
		if(labApparaStopVo==null){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.updateLabApparaStop(labApparaStopVo);
		return UPDATE;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printApparaStop() throws GlobalException{
		if(null==labApparaStopVo){
			labApparaStopVo=new LabApparaStopVo();
		}
		labApparaStopVo=labApparaStopService.getLabApparaStop(labApparaStopVo);
		return "print";
	}
	public String exportApparaStop() throws GlobalException {
		//将此方法改造成导出excel
		if (null == labApparaStopVo) {
			labApparaStopVo = new LabApparaStopVo();
		}
		labApparaStopVo = labApparaStopService.getLabApparaStop(labApparaStopVo);
		try {
			exportToExcel(labApparaStopVo);
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
	private String exportToExcel(LabApparaStopVo labApparaStopVo) throws UnsupportedEncodingException, GlobalException {
		String path="";
		Map<String, LabApparaStopVo> beans = new HashMap<String, LabApparaStopVo>();
		beans.put("data", labApparaStopVo);
    	
    	String tempFilePath="";
		fileName = "仪器设备停用记录表.xls";
		tempFilePath="exportApparatusStopRecord.xls";
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

	public LabApparaStopVo getLabApparaStopVo() {
		return labApparaStopVo;
	}
	public void setLabApparaStopVo(LabApparaStopVo labApparaStopVo) {
		this.labApparaStopVo = labApparaStopVo;
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
}
