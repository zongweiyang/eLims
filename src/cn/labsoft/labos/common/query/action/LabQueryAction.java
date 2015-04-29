package cn.labsoft.labos.common.query.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.common.query.service.ILabParameterService;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.LabParameterVo;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.common.query.vo.TitleVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;

@Controller
@Scope("prototype")
public class LabQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabQueryService labQueryService ;
	private ILabFunctionService labFunctionService;
	private ILabParameterService labParameterService;
	private List<LabQueryVo> listLabQueryVo;
	private LabQueryVo labQueryVo;
	private TitleVo demo;
	private LabParameterVo labParameterVo;
	public LabParameterVo getLabParameterVo() {
		return labParameterVo;
	}
	public void setLabParameterVo(LabParameterVo labParameterVo) {
		this.labParameterVo = labParameterVo;
	}
	public List<LabQueryVo> getListLabQueryVo() {
		return listLabQueryVo;
	}
	public void setListLabQueryVo(List<LabQueryVo> listLabQueryVo) {
		this.listLabQueryVo = listLabQueryVo;
	}
	public LabQueryVo getLabQueryVo() {
		return labQueryVo;
	}
	public void setLabQueryVo(LabQueryVo labQueryVo) {
		this.labQueryVo = labQueryVo;
	}
	public void initialise(){
		if(null==labQueryVo)labQueryVo=new LabQueryVo();
	}
	public String listLabQuery() throws GlobalException{
		initialise();
		pageResult=labQueryService.getLabQueryPR(labQueryVo, getPageResult());
		return LIST;
	}
	public String preAddLabQuery() throws GlobalException{
		initialise();
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsQuery("Y");
		List<LabFunctionVo> listLabFunctionVo=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("listLabFunction", listLabFunctionVo);
		labQueryVo.setType("0");
		return PREADD;
	}
	public String addLabQuery() throws GlobalException{
		initialise();
		labQueryVo=labQueryService.addLabQuery(labQueryVo);
		return ADD;
	}
	public String preUpdateLabQuery() throws GlobalException{
		initialise();
		labQueryVo=labQueryService.getLabQuery(labQueryVo.getId());
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsQuery("Y");
		List<LabFunctionVo> listLabFunctionVo=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("listLabFunction", listLabFunctionVo);
		return PREUPDATE;
	}
	public String updateLabQuery() throws GlobalException{
		initialise();
		labQueryService.updateLabQuery(labQueryVo);
		return UPDATE;
	}
	public String showLabQuery()  throws GlobalException{
		initialise();
		labQueryVo=labQueryService.getLabQuery(labQueryVo.getId());
		return SHOW;
	}
	public String deleteLabQuery() throws GlobalException{
		initialise();
		labQueryService.deleteLabQuery(new String[]{labQueryVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabQuery() throws GlobalException{
		initialise();
		labQueryService.updateLabQuery4Del(labQueryVo.getIds());
		return DELETEBATCH;
	}
	public String showLabQueryDiaLog4Select(){
		initialise();
		return SHOW;
	}
	public void ajaxColumProperty() throws GlobalException{
		initialise();
		List<LabParameterVo> listParameter=labQueryService.getProperty(labQueryVo);
		try {
			outPutString(JSONSerializer.toJSON(listParameter).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		
	}
	public void ajaxWhere() throws GlobalException{
		if(labParameterVo==null)labParameterVo=new LabParameterVo();
		labParameterVo=labParameterService.getLabParameter(labParameterVo.getId());
		try {
			outPutString(JSONSerializer.toJSON(labParameterVo).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
	public String getQuery() throws GlobalException{
		initialise();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("05c0bca5456eb2f401456ee99fed0003");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
		if(labQueryVo.getQueryType().equals(Constants_Base.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	public void ajaxTestDiaLog() throws IOException, GlobalException{
		String json="{\"tableValue\":[{\"4\":\"回访短信\",\"3\":\"邮件短信\",\"aa\":\"测试短信\"}]}";
		try {
			outPutString(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
	public String preDiaLog4Select(){
		
		return PREUPDATE;
	}
	public TitleVo getDemo() {
		return demo;
	}
	public void setDemo(TitleVo demo) {
		this.demo = demo;
	}
	@Resource
	public void setLabQueryService(ILabQueryService labQueryService) {
		this.labQueryService = labQueryService;
	}
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	@Resource
	public void setLabParameterService(ILabParameterService labParameterService) {
		this.labParameterService = labParameterService;
	}
	
}
