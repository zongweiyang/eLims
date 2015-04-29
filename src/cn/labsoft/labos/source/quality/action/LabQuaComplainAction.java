package cn.labsoft.labos.source.quality.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;
import cn.labsoft.labos.source.quality.service.ILabQuaComplainService;
import cn.labsoft.labos.source.quality.vo.LabQuaComplainVo;

@Controller
@Scope("prototype")
public class LabQuaComplainAction extends BaseAction {
	
	private ILabQuaComplainService labQuaComplainService;
	private ILabCustomerService labCustomerService;
	
	private LabCustomerVo labCustomerVo;
    private List<LabCustomerVo> labCustomerVoList;
    private LabQuaComplainVo labQuaComplainVo;
    
    @Resource 
    public void setLabQuaComplainService(
			ILabQuaComplainService labQuaComplainService) {
		this.labQuaComplainService = labQuaComplainService;
	}
    @Resource 
    public void setLabCustomerService(ILabCustomerService labCustomerService) {
		this.labCustomerService = labCustomerService;
	}
	public LabCustomerVo getLabCustomerVo() {
		return labCustomerVo;
	}


	public void setLabCustomerVo(LabCustomerVo labCustomerVo) {
		this.labCustomerVo = labCustomerVo;
	}


	public List<LabCustomerVo> getLabCustomerVoList() {
		return labCustomerVoList;
	}


	public void setLabCustomerVoList(List<LabCustomerVo> labCustomerVoList) {
		this.labCustomerVoList = labCustomerVoList;
	}

	public LabQuaComplainVo getLabQuaComplainVo() {
    	return labQuaComplainVo;
    }
	public void setLabQuaComplainVo(LabQuaComplainVo labQuaComplainVo) {
		this.labQuaComplainVo = labQuaComplainVo;
	}
    public String listLabQuaComplain() throws GlobalException{
		if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
		pageResult=labQuaComplainService.getLabQuaComplainVoPR(labQuaComplainVo, pageResult);
		return LIST;
	}
	public String preAddLabQuaComplain() throws GlobalException {
		return PREADD;
	}
	public String addLabQuaComplain() throws GlobalException {
		if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
		labQuaComplainService.addLabQuaComplain(labQuaComplainVo);
		return ADD;
	}
	public String preUpdateLabQuaComplain() throws GlobalException {
		if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
		labQuaComplainVo=labQuaComplainService.getLabQuaComplain(labQuaComplainVo.getId());
		return PREUPDATE;
	}
	public String showLabQuaComplain() throws GlobalException {
		if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
		labQuaComplainVo=labQuaComplainService.getLabQuaComplain(labQuaComplainVo.getId());
	    return SHOW;
	}
	public String updateLabQuaComplain() throws GlobalException {
		if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
		labQuaComplainService.updateLabQuaComplain(labQuaComplainVo);
		return UPDATE;
	}
    public String deleteLabQuaComplain() throws GlobalException {
    	if(null==labQuaComplainVo) labQuaComplainVo=new LabQuaComplainVo();
    	labQuaComplainService.update2DelLabQuaComplain(labQuaComplainVo.getIds());
		return DELETE;
	}
    public String showLabCustomer4select() throws GlobalException{
		if( null == labCustomerVo) labCustomerVo = new LabCustomerVo();
		labCustomerVoList = labCustomerService.getLabCustomerList(labCustomerVo);
		return LIST;
	}
    
}
