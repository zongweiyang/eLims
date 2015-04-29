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
import cn.labsoft.labos.source.appara.service.ILabApparaDropService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaDropVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.FileUtils;
@Controller
@Scope("prototype")
public class LabApparaDropAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaDropService labApparaDropService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaDropVo labApparaDropVo;
	private LabApparaVo labApparaVo;
	private List<LabApparaVo> labAppList;

	public List<LabApparaVo> getLabAppList() {
		return labAppList;
	}

	public void setLabAppList(List<LabApparaVo> labAppList) {
		this.labAppList = labAppList;
	}

	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	@Resource
	public void setLabApparaDropService(ILabApparaDropService labApparaDropService) {
		this.labApparaDropService = labApparaDropService;
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
	 * 仪器清单 查看 记录
	 * @throws GlobalException 
	 * */
	public String listLabApparaDrop4View() throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		pageResult=labApparaDropService.getLabApparaDropPR(labApparaDropVo, pageResult);
		return LIST;
	}
	/**
	 * 获取所有仪器报废信息
	 * */
	public String listLabApparaDrop()throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaDropVo.getStatus()){
			labApparaDropVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaDropService.getLabApparaDropPR(labApparaDropVo, pageResult);
		return LIST;
	}
	/**
	 * 获取所有仪器信息
	 * @throws GlobalException 
	 * */
	public String showApp4Select() throws GlobalException{
		if(null==labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		pageResult = labApparaService.getLabApparaList(labApparaVo,pageResult);
		
		return SHOW;
	}
	/**
	 * 报废 查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		labApparaDropVo=labApparaDropService.getLabApparaDrop(labApparaDropVo);
		return SHOW;
	}
	/**
	 * 报废预新增
	 * */
	public String preAddLabApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		return PREADD;
	}
	/**
	 * 报废 新增 保存
	 * */
	public String addLabApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		labApparaDropVo=labApparaDropService.addLabApparaDrop(labApparaDropVo);
		if(LabWfConstant.BUS_GO.equals(labApparaDropVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 报废预修改
	 * */
	public String preUpdateLabApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		labApparaDropVo=labApparaDropService.getLabApparaDrop(labApparaDropVo);
		return PREUPDATE;
	}
	/**
	 * 报废 修改 保存
	 * */
	public String upDateLabApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		labApparaDropVo=labApparaDropService.updateLabApparaDrop(labApparaDropVo);
		if(LabWfConstant.BUS_GO.equals(labApparaDropVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 报废 审核 列表
	 * */
	public String listLabApparaDropAudit()throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaDropVo.getStatus()){
			labApparaDropVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaDropService.getLabApparaDropPR(labApparaDropVo, pageResult);
		return LIST;
	}
	/**
	 * 报废 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppDropAudit() throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		labApparaDropVo = labApparaDropService.getLabApparaDrop(labApparaDropVo);
		labApparaDropVo.setUser2(getSessionContainer().getUserName());
		labApparaDropVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 报废 审批 列表
	 * */
	public String listLabApparaDropPaudit()throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaDropVo.getStatus()){
			labApparaDropVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaDropService.getLabApparaDropPR(labApparaDropVo, pageResult);
		return LIST;
	}
	
	/**
	 * 报废 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppDropPaudit() throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		labApparaDropVo = labApparaDropService.getLabApparaDrop(labApparaDropVo);
		labApparaDropVo.setDate3(DateUtils.getCurrDateStr());
		labApparaDropVo.setUser3(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	public String showApparaDrop() throws GlobalException{
		if(labApparaDropVo==null){
			labApparaDropVo=new LabApparaDropVo();
		}
		labApparaDropVo=labApparaDropService.getLabApparaDrop(labApparaDropVo);
		labApparaVo=labApparaService.getLabAppara(labApparaDropVo.getAppId());
		return SHOW;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printApparaDrop() throws GlobalException{
		if(null==labApparaDropVo){
			labApparaDropVo = new LabApparaDropVo();
		}
		labApparaDropVo = labApparaDropService.getLabApparaDrop(labApparaDropVo);
		return "print";
	}

	public String exportApparaDrop() throws GlobalException {
		//将此方法改造成导出excel
		if (null == labApparaDropVo) {
			labApparaDropVo = new LabApparaDropVo();
		}
		labApparaDropVo = labApparaDropService.getLabApparaDrop(labApparaDropVo);
		try {
			exportToExcel(labApparaDropVo);
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
	private String exportToExcel(LabApparaDropVo labApparaDropVo) throws UnsupportedEncodingException, GlobalException {
		String path="";
		Map<String, LabApparaDropVo> beans = new HashMap<String, LabApparaDropVo>();
		beans.put("data", labApparaDropVo);
    	
    	String tempFilePath="";
		fileName = "仪器设备报废记录表.xls";
		tempFilePath="exportApparatusDropRecord.xls";
    	
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
	public LabApparaDropVo getLabApparaDropVo() {
		return labApparaDropVo;
	}
	public void setLabApparaDropVo(LabApparaDropVo labApparaDropVo) {
		this.labApparaDropVo = labApparaDropVo;
	}
	
}
