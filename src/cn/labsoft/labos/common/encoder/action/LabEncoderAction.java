package cn.labsoft.labos.common.encoder.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.common.encoder.service.ILabEncoderService;
import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
@Controller
@Scope("prototype")
public class LabEncoderAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabEncoderService labEncoderService;
	private ILabFunctionService labFunctionService;
	private ILabCodeService labCodeService;

	private List<LabEncoderVo> listLabEncoderVo;
	private LabEncoderVo labEncoderVo;

	@Resource
	public void setLabEncoderService(ILabEncoderService labEncoderService) {
		this.labEncoderService = labEncoderService;
	}
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	public List<LabEncoderVo> getListLabEncoderVo() {
		return listLabEncoderVo;
	}

	public void setListLabEncoderVo(List<LabEncoderVo> listLabEncoderVo) {
		this.listLabEncoderVo = listLabEncoderVo;
	}

	public LabEncoderVo getLabEncoderVo() {
		return labEncoderVo;
	}

	public void setLabEncoderVo(LabEncoderVo labEncoderVo) {
		this.labEncoderVo = labEncoderVo;
	}

	public void initialise() {
		if (null == labEncoderVo)
			labEncoderVo = new LabEncoderVo();
	}

	public String listLabEncoder() throws GlobalException {
		initialise();
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		labFunctionVo.setIsBarCode("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList = labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		pageResult = labEncoderService.getLabEncoderPR(labEncoderVo, getPageResult());
		return LIST;
	}

	@SuppressWarnings("unchecked")
	public String preAddLabEncoder() throws GlobalException {
		initialise();
		// 获取流程源列表
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		labFunctionVo.setIsBarCode("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList = labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		List<LabCodeVo> sizeList = labCodeService.getLabCodeByTypeCode("EWMS");
		setAttribute("sizeList", sizeList);
		List<LabCodeVo> faultList = labCodeService.getLabCodeByTypeCode("EWMF");
		setAttribute("faultList", faultList);
		return PREADD;
	}

	public String addLabEncoder() throws GlobalException{
		initialise();
			labEncoderVo = labEncoderService.addLabEncoder(labEncoderVo);
		return ADD;
	}

	@SuppressWarnings("unchecked")
	public String preUpdateLabEncoder() throws GlobalException {
		initialise();
		// 获取流程源列表
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		labFunctionVo.setIsBarCode("Y");
		labFunctionVo.setType("1");
		List<LabFunctionVo> funcList = labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		List<LabCodeVo> sizeList = labCodeService.getLabCodeByTypeCode("EWMS");
		setAttribute("sizeList", sizeList);
		List<LabCodeVo> faultList = labCodeService.getLabCodeByTypeCode("EWMF");
		setAttribute("faultList", faultList);
		labEncoderVo = labEncoderService.getLabEncoder(labEncoderVo.getId());
		return PREUPDATE;
	}

	public String updateLabEncoder() throws GlobalException {
		initialise();
		labEncoderService.updateLabEncoder(labEncoderVo);
		return UPDATE;
	}

	public String showLabEncoder() throws GlobalException {
		initialise();
		labEncoderVo = labEncoderService.getLabEncoder(labEncoderVo.getId());
		return SHOW;
	}

	public String deleteLabEncoder() throws GlobalException {
		initialise();
		labEncoderService.deleteLabEncoder(new String[] { labEncoderVo.getId() });
		return DELETE;
	}

	public String deleteBatchLabEncoder() throws GlobalException {
		initialise();
		labEncoderService.deleteLabEncoder(labEncoderVo.getIds());
		return DELETEBATCH;
	}

	@SuppressWarnings("unchecked")
	public void ajax2Content4Select() throws GlobalException {
		initialise();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String funId = request.getParameter("funId");
		String result = labEncoderService.getLabObjectByFunId(funId);
		outPrint(response, new StringBuffer(result));
	}

	public String showLabEncoder4BarCode() throws GlobalException{
			initialise();
			labEncoderVo = labEncoderService.getLabEncoder4Show(labEncoderVo, LabEncoderVo.TXM_ENCODER);
		return "print";
	}

	public String showLabEncoder4TwoCode() throws GlobalException{
			initialise();
			labEncoderVo = labEncoderService.getLabEncoder4Show(labEncoderVo, LabEncoderVo.EWM_ENCODER);
		return "print";
	}

	public String ajax4CheckBusId() throws GlobalException{
		initialise();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			String busId = request.getParameter("funId");
			String result = labEncoderService.getLabEncoder4BusId(busId);
			outPrint(response, new StringBuffer(result));
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}
}
