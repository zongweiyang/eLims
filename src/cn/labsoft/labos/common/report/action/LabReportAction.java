package cn.labsoft.labos.common.report.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.service.ILabReportTagService;
import cn.labsoft.labos.common.report.vo.LabReportTagVo;
import cn.labsoft.labos.common.report.vo.LabReportVo;
/**
 * 报告模版操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabReportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabReportService labReportService;
	private ILabFunctionService labFunctionService;
	private ILabReportTagService labReportTagService;
	
	private List<LabReportVo> listLabReportVo;
	private LabReportVo labReportVo;
	private LabReportTagVo labReportTagVo;
	/**
	 * 报告模版Service注入
	 * @param labReportService
	 */
	@Resource
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	/**
	 * 功能Service注入
	 * @param labFunctionService
	 */
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	/**
	 * 报告标签Service注入
	 * @param labReportTagService
	 */
	@Resource
	public void setLabReportTagService(ILabReportTagService labReportTagService) {
		this.labReportTagService = labReportTagService;
	}
	public List<LabReportVo> getListLabReportVo() {
		return listLabReportVo;
	}
	public void setListLabReportVo(List<LabReportVo> listLabReportVo) {
		this.listLabReportVo = listLabReportVo;
	}
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	
	public LabReportTagVo getLabReportTagVo() {
		return labReportTagVo;
	}
	public void setLabReportTagVo(LabReportTagVo labReportTagVo) {
		this.labReportTagVo = labReportTagVo;
	}
	public void initialise(){
		if(null==labReportVo)labReportVo=new LabReportVo();
	}
	/**
	 * 报告模版-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabReport() throws GlobalException{
		if(null==labReportVo){
			labReportVo=new LabReportVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("createTime");
		}
		pageResult=labReportService.getLabReportPR(labReportVo, pageResult);
		
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsReport("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		return LIST;
	}
	/**
	 * 报告模版-新增页面方法
	 * @throws GlobalException
	 */
	public String preAddLabReport()throws GlobalException{
		initialise();
		
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsReport("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		return PREADD;
	}
	/**
	 * 报告模版-新增方法
	 * @throws GlobalException
	 */
	public String addLabReport() throws GlobalException{
		initialise();
		labReportVo=labReportService.addLabReport(labReportVo);
		return ADD;
	}
	/**
	 * 报告模版-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabReport() throws GlobalException{
		initialise();
		labReportVo=labReportService.getLabReport(labReportVo.getId());
		
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsReport("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		return PREUPDATE;
	}
	/**
	 * 报告模版-修改方法
	 * @throws GlobalException
	 */
	public String updateLabReport() throws GlobalException{
		initialise();
		labReportService.updateLabReport(labReportVo);
		return UPDATE;
	}
	/**
	 * 报告模版-查看页面方法
	 * @throws GlobalException
	 */
	public String showLabReport()  throws GlobalException{
		initialise();
		labReportVo=labReportService.getLabReport(labReportVo.getId());
		return SHOW;
	}
	/**
	 * 报告模版-删除方法
	 * @throws GlobalException 
	 * @throws GlobalException
	 */
	public String deleteLabReport() throws GlobalException{
		initialise();
		labReportService.deleteLabReport(new String[]{labReportVo.getId()});
		return DELETE;
	}
	/**
	 * 报告模版-批量删除方法
	 * @throws GlobalException 
	 * @throws GlobalException
	 */
	public String deleteBatchLabReport() throws GlobalException{
		initialise();
		labReportService.deleteLabReport(labReportVo.getIds());
		return DELETEBATCH;
	}
	/**
	 * 报告模版-模版维护页面方法
	 * @throws GlobalException
	 */
	public String preUpdateReportModel()throws GlobalException{
		initialise();
		labReportVo=labReportService.getLabReportModel(labReportVo.getId());
		List<LabReportTagVo> reportDataList= labReportTagService.getLabReportTagListByReportId(labReportVo.getId());
		setAttribute("reportDataList", reportDataList);
		if(labReportVo.getType().equals("2")){
			return "preUpdateExcel";
		}else{
			labReportVo.setEditType("0");
			return PREUPDATE;
		}
	}
	/**
	 * 报告模版-模版维护方法
	 * @throws GlobalException
	 */
	public String updateReportModel()throws GlobalException{
		initialise();
		labReportVo=labReportService.updateLabReportModel(labReportVo);
		return UPDATE;
	}
	/**
	 * 跳到页面代码编辑状态
	 * @throws GlobalException
	 */
	public String preUpdateReportModel4Code()throws GlobalException{
		initialise();
		labReportVo=labReportService.getLabReportModel4Code(labReportVo.getId());
		return PREUPDATE;
	}
	/**
	 * 跳到页面代码编辑状态
	 * @throws GlobalException
	 */
	public String updateReportModel4Code()throws GlobalException{
		initialise();
		labReportVo=labReportService.updateLabReportModel4Code(labReportVo);
		return UPDATE;
	}
	/**
	 * 查看模版
	 * @throws GlobalException
	 */
	public String showReportModel()throws GlobalException{
		initialise();
		labReportVo=labReportService.getLabReportModel(labReportVo.getId());
		List<LabReportTagVo> reportDataList= labReportTagService.getLabReportTagListByReportId(labReportVo.getId());
		setAttribute("reportDataList", reportDataList);
		return SHOW;
	}
	/**
	 * 复制模版
	 * @throws GlobalException
	 */
	public String addLabReport4Copy()throws GlobalException{
		labReportVo=labReportService.addLabReport4Copy(labReportVo);
		return ADD;
	}
	/**
	 * 查看模版实例
	 * 业务模块调用方法 
	 * @throws GlobalException
	 */
	public String showLabReport4Bus()throws GlobalException{
		if(labReportVo==null)labReportVo=new LabReportVo();
		String funId=getParameter("funId");
		String busId=getParameter("busId");
		if(!StrUtils.isBlankOrNull(funId)){
			labReportVo.setBusId(funId);
		}
		if(!StrUtils.isBlankOrNull(busId)){
			labReportVo.setBusInsId(busId);
		}
		labReportVo=labReportService.getLabReport4Bus(labReportVo);
		List<LabReportVo> reportTempList=labReportService.getLabReportList(labReportVo);
		setAttribute("reportTempList", reportTempList);
		return SHOW;
	}
	/**
	 * 修改模版实例
	 * 业务模块调用方法
	 * @throws GlobalException
	 */
	public String updateLabReport4Bus()throws GlobalException{
		labReportVo=labReportService.updateLabReport4Bus(labReportVo);
		return ADD;
	}
}
