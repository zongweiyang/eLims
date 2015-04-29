package cn.labsoft.labos.common.report.action;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.service.ILabReportTagService;
import cn.labsoft.labos.common.report.vo.LabReportTagVo;
import cn.labsoft.labos.common.report.vo.LabReportVo;
/**
 * 报告模版-标签操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabReportTagAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabReportTagService labReportTagService;
	private ILabReportService labReportService;
	
	private LabReportTagVo labReportTagVo;
	private LabReportVo labReportVo;
	/**
	 * 报告标签Service注入
	 * @param labReportTagService
	 */
	@Resource
	public void setLabReportTagService(ILabReportTagService labReportTagService) {
		this.labReportTagService = labReportTagService;
	}
	/**
	 * 报告模版Service注入
	 * @param labReportService
	 */
	@Resource
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	public LabReportTagVo getLabReportTagVo() {
		return labReportTagVo;
	}
	public void setLabReportTagVo(LabReportTagVo labReportTagVo) {
		this.labReportTagVo = labReportTagVo;
	}
	
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	/**
	 * 报告模版标签-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabReportTag() throws GlobalException{
		if(null==labReportTagVo){
			labReportTagVo=new LabReportTagVo();
		}
		if(StrUtils.isBlankOrNull(pageResult.getOrderColumn())){
			pageResult.setOrder(PageResult.ORDER_ASC);
			pageResult.setOrderColumn("index");
		}
		pageResult=labReportTagService.getLabReportTagPR(labReportTagVo, pageResult);
		LabReportVo labReport=labReportService.getLabReport(labReportTagVo.getLabReportId());
		labReportTagVo.setLabReportTitle(labReport.getTitle());
		labReportTagVo.setLabReportId(labReport.getId());
		return LIST;
	}
	/**
	 * 报告模版标签-增加页面方法
	 * @throws GlobalException
	 */
	public String preAddLabReportTag() throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		int index=labReportTagService.getLabReportTagIndex(labReportTagVo.getLabReportId());
		labReportTagVo.setIndex(index+"");
		return PREADD;
	}
	/**
	 * 报告模版标签-增加方法
	 * @throws GlobalException
	 */
	public String addLabReportTag() throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		labReportTagVo=labReportTagService.addLabReportTag(labReportTagVo);
		return ADD;
	}
	/**
	 * 报告模版标签-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabReportTag() throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		labReportTagVo=labReportTagService.getLabReportTag(labReportTagVo.getId());
		return PREUPDATE;
	}
	/**
	 * 报告模版标签-修改方法
	 * @throws GlobalException
	 */
	public String updateLabReportTag() throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
	labReportTagService.updateLabReportTag(labReportTagVo);
		return UPDATE;
	}
	/**
	 * 报告模版标签-查看页面方法
	 * @throws GlobalException
	 */
	public String showLabReportTag()  throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		labReportTagVo=labReportTagService.getLabReportTag(labReportTagVo.getId());
		return SHOW;
	}
	/**
	 * 报告模版标签-删除方法
	 * @throws GlobalException
	 */
	public String deleteLabReportTag() throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		labReportTagService.deleteLabReportTag(labReportTagVo.getIds());
		return DELETE;
	}
	/**
	 * 报告模版标签-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabReportTag4Del()throws GlobalException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		labReportTagService.updateLabReportTag4Del(labReportTagVo.getIds());
		return DELETE;
	}
	/**
	 * 报告模版标签-标签初始化方法
	 * @throws GlobalException
	 */
	public String ajaxLabReportTag4Init() throws GlobalException, IOException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		boolean flag=labReportTagService.addLabReportTag4Init(labReportTagVo.getLabReportId());
		outPutString(flag+"");
		return NONE;
	}
	/**
	 * 报告模版标签-标签初始化方法
	 * @throws GlobalException
	 */
	public String ajaxLabReportTag4List() throws GlobalException, IOException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		String tagJson=labReportTagService.getLabReportTag4JsonByReportId(labReportTagVo.getLabReportId(),"value-of");
		outPutString(tagJson);
		return NONE;
	}
	/**
	 * 报告模版标签-标签初始化方法
	 * @throws GlobalException
	 */
	public String ajaxLabReportTag4Table() throws GlobalException, IOException{
		if(null==labReportTagVo)labReportTagVo=new LabReportTagVo();
		String tagJson=labReportTagService.getLabReportTag4JsonByReportId(labReportTagVo.getLabReportId(),"for-each");
		outPutString(tagJson);
		return NONE;
	}
}
