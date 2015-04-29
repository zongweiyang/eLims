package cn.labsoft.labos.source.quality.action;

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

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;
import cn.labsoft.labos.source.quality.service.ILabQuaAccidentService;
import cn.labsoft.labos.source.quality.vo.LabQuaAccidentVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.FileUtils;

@Controller
@Scope("prototype")
public class LabQuaAccidentAction extends BaseAction {
	
	private ILabQuaAccidentService labQuaAccidentService;
	private ILabCustomerService labCustomerService;
	private ILabSampRegisterService labSampRegisterService;
	private ILabConfigService labConfigService;
	private ILabCodeService labCodeService;
	
	
    private LabQuaAccidentVo labQuaAccidentVo;
    private LabCustomerVo labCustomerVo;
    private List<LabCustomerVo> labCustomerVoList;
    private LabSampRegisterVo labSampRegisterVo;
    private List<LabSampRegisterVo> labSampRegisterVoList;
    private List<LabCodeVo> labCodeVoList;
    private String fileName;
	private InputStream excelStream;
	private boolean demo1;
    
	@Resource 
	public void setLabSampRegisterService(
			ILabSampRegisterService labSampRegisterService) {
		this.labSampRegisterService = labSampRegisterService;
	}
	@Resource 
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource 
    public void setLabQuaAccidentService(
			ILabQuaAccidentService labQuaAccidentService) {
		this.labQuaAccidentService = labQuaAccidentService;
	}
    @Resource 
    public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
    @Resource 
    public void setLabCustomerService(ILabCustomerService labCustomerService) {
    	this.labCustomerService = labCustomerService;
    }
    
	public LabSampRegisterVo getLabSampRegisterVo() {
		return labSampRegisterVo;
	}
	public void setLabSampRegisterVo(LabSampRegisterVo labSampRegisterVo) {
		this.labSampRegisterVo = labSampRegisterVo;
	}
	public List<LabSampRegisterVo> getLabSampRegisterVoList() {
		return labSampRegisterVoList;
	}
	public void setLabSampRegisterVoList(
			List<LabSampRegisterVo> labSampRegisterVoList) {
		this.labSampRegisterVoList = labSampRegisterVoList;
	}
	public List<LabCodeVo> getLabCodeVoList() {
		return labCodeVoList;
	}
	public void setLabCodeVoList(List<LabCodeVo> labCodeVoList) {
		this.labCodeVoList = labCodeVoList;
	}
	public LabCustomerVo getLabCustomerVo() {
		return labCustomerVo;
	}
	public void setLabCustomerVo(LabCustomerVo labCustomerVo) {
		this.labCustomerVo = labCustomerVo;
	}
	public LabQuaAccidentVo getLabQuaAccidentVo() {
    	return labQuaAccidentVo;
    }
	public void setLabQuaAccidentVo(LabQuaAccidentVo labQuaAccidentVo) {
		this.labQuaAccidentVo = labQuaAccidentVo;
	}
	public List<LabCustomerVo> getLabCustomerVoList() {
		return labCustomerVoList;
	}
	public void setLabCustomerVoList(List<LabCustomerVo> labCustomerVoList) {
		this.labCustomerVoList = labCustomerVoList;
	}
	
	public boolean isDemo1() {
		return demo1;
	}
	public void setDemo1(boolean demo1) {
		this.demo1 = demo1;
	}
	/**
	 * 事故登记
	 * @return
	 * @throws GlobalException
	 */
    public String listLabQuaAccident() throws GlobalException{
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo.setAccStatus("0");
		pageResult=labQuaAccidentService.getLabQuaAccidentVoPR(labQuaAccidentVo, pageResult);
		labCodeVoList=labCodeService.getLabCodeByTypeCode("SGLX");
		return LIST;
	}
	public String preAddLabQuaAccident() throws GlobalException {
		labCodeVoList=labCodeService.getLabCodeByTypeCode("SGLX");
		return PREADD;
	}
	public String addLabQuaAccident() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		demo1=false;
		labQuaAccidentVo=labQuaAccidentService.addLabQuaAccident(labQuaAccidentVo);
		if(null!=labQuaAccidentVo){
			demo1=true;
		}
		return ADD;
	}
	public String preUpdateLabQuaAccident() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo=labQuaAccidentService.getLabQuaAccident(labQuaAccidentVo.getId());
		labCodeVoList=labCodeService.getLabCodeByTypeCode("SGLX");
		return PREUPDATE;
	}
	public String showLabQuaAccident() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo=labQuaAccidentService.getLabQuaAccident(labQuaAccidentVo.getId());
	    return SHOW;
	}
	public String updateLabQuaAccident() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentService.updateLabQuaAccident(labQuaAccidentVo);
		return UPDATE;
	}
    public String deleteLabQuaAccident() throws GlobalException {
    	if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
    	labQuaAccidentService.update2DelLabQuaAccident(labQuaAccidentVo.getIds());
		return DELETE;
	}
    /**
     * 事故处理
     * 
     */
    public String listLabQuaAccident4Handle() throws GlobalException{
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo.setAccStatus("1");
		pageResult=labQuaAccidentService.getLabQuaAccidentVoPR(labQuaAccidentVo, pageResult);
		labCodeVoList=labCodeService.getLabCodeByTypeCode("SGLX");
		return LIST;
	}
	public String preUpdateLabQuaAccident4Handle() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo=labQuaAccidentService.getLabQuaAccident(labQuaAccidentVo.getId());
		labQuaAccidentVo.setResPeople(getSessionContainer().getUserName());
		labQuaAccidentVo.setProcTime(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	public String updateLabQuaAccident4Handle() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentService.updateLabQuaAccident(labQuaAccidentVo);
		return UPDATE;
	}
	/**
	 * 事故查询
	 * 
	 */
	 public String listLabQuaAccident4Query() throws GlobalException{
			if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
			pageResult=labQuaAccidentService.getLabQuaAccidentVoPR(labQuaAccidentVo, pageResult);
			labCodeVoList=labCodeService.getLabCodeByTypeCode("SGLX");
			return LIST;
		}
	 public String showLabQuaAccident4Query() throws GlobalException {
			if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
			labQuaAccidentVo=labQuaAccidentService.getLabQuaAccident(labQuaAccidentVo.getId());
		    return SHOW;
		}
	 
	public String exportLabQuaAccident4Excel() throws GlobalException {
		if(null==labQuaAccidentVo) labQuaAccidentVo=new LabQuaAccidentVo();
		labQuaAccidentVo=labQuaAccidentService.getLabQuaAccident(labQuaAccidentVo.getId());
		 String repTime=labQuaAccidentVo.getRepTime();
		 String cheTime=labQuaAccidentVo.getCheTime();
		 String procTime=labQuaAccidentVo.getProcTime();
		 if(null!=repTime&&repTime.length()>=10){
			 repTime=repTime.substring(0, 4)+getText("year")+repTime.substring(5, 7)+getText("month")+repTime.substring(8, 10)+getText("day");
		 }
		 if(null!=cheTime&&cheTime.length()>=10){
			 cheTime=cheTime.substring(0, 4)+getText("year")+cheTime.substring(5, 7)+getText("month")+cheTime.substring(8, 10)+getText("day");
		 }
		 if(null!=procTime&&procTime.length()>=10){
			 procTime=procTime.substring(0, 4)+getText("year")+procTime.substring(5, 7)+getText("month")+procTime.substring(8, 10)+getText("day");
		 }
		 labQuaAccidentVo.setRepTime(repTime);
		 labQuaAccidentVo.setCheTime(cheTime);
		 labQuaAccidentVo.setProcTime(procTime);
		 try {
			 exportLabQuaAccident(labQuaAccidentVo);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return EXPORT;
	}
	
	//jxls导出excel   START
	private String exportLabQuaAccident(LabQuaAccidentVo labQuaAccidentVo) throws UnsupportedEncodingException, GlobalException {
		String path="";
		Map<String, LabQuaAccidentVo> beans = new HashMap<String, LabQuaAccidentVo>();
		beans.put("data", labQuaAccidentVo);
    	
    	String tempFilePath="";
		fileName = "申诉与投诉登记与处理表.xls";
		tempFilePath="exportQuaAccidentRecord.xls";
		fileName=new String(fileName.getBytes("GB2312"),"ISO8859-1");
		String templateFileName = ServletActionContext.getRequest()
		.getRealPath("/")
		+ labConfigService.getLabConfigByCode("quality")
				.getValue() + File.separator + tempFilePath;
		String destFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ "templates"
				+ File.separator
				+ "temp"
				+ File.separator
				+ System.currentTimeMillis() + "." + "xls";
		
		String realPath = ServletActionContext.getRequest().getRealPath("/")
				+ "templates" + File.separator + "temp" + File.separator;
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
	
	public String showLabCustomer4select() throws GlobalException{
		if( null == labCustomerVo) labCustomerVo = new LabCustomerVo();
		labCustomerVoList = labCustomerService.getLabCustomerList(labCustomerVo);
		return LIST;
	}
	public String showLabSampRegister4select() throws GlobalException{
		if( null == labSampRegisterVo) labSampRegisterVo = new LabSampRegisterVo();
		labSampRegisterVoList = labSampRegisterService.getLabSampRegisterList(labSampRegisterVo);
		return LIST;
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
}
