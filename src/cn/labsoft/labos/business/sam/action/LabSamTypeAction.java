package cn.labsoft.labos.business.sam.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.service.ILabItemService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
@Controller
@Scope("prototype")
public class LabSamTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabSamTypeService labSamTypeService;
	private ILabItemService labItemService;
	private LabSamTypeVo labSamTypeVo;
	private List<LabSamTypeVo> labSamTypeVoList;
	private LabItemVo labItemVo;

	
	@Resource
	public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
	}
	@Resource
	public void setLabItemService(ILabItemService labItemService) {
		this.labItemService = labItemService;
	}

	private void headerInit() throws GlobalException {
		if (null == labSamTypeVo)
			labSamTypeVo = new LabSamTypeVo();
	}

	public LabItemVo getLabItemVo() {
		return labItemVo;
	}

	public void setLabItemVo(LabItemVo labItemVo) {
		this.labItemVo = labItemVo;
	}

	public String listLabSamType() throws GlobalException {
		if (null == labSamTypeVo) {
			labSamTypeVo = new LabSamTypeVo();
		} 
		labSamTypeVo.setPid("0");
		labSamTypeVoList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		if (null == labSamTypeVoList) {
			labSamTypeVoList = new ArrayList<LabSamTypeVo>();
		}

		labSamTypeVo.setLabSamTypeVoList(labSamTypeVoList);
		pageResult = labSamTypeService.getLabSamTypePR(labSamTypeVo, pageResult);
		return LIST;
	}

	public String addLabSamType() throws GlobalException {
		headerInit();
		labSamTypeVo = labSamTypeService.addLabSamType(labSamTypeVo);
		return ADD;
	}

	public String updateLabSamType() throws GlobalException {
		labSamTypeService.updateLabSamType(labSamTypeVo);
		return UPDATE;
	}

	public String deleteLabSamType() throws GlobalException {
		headerInit();
		labSamTypeService.updateLabSamType4Del(labSamTypeVo.getId());
		return DELETE;
	}

	public String showLabSamType() throws GlobalException {
		headerInit();
		labSamTypeVo = labSamTypeService.getLabSamTypeById(labSamTypeVo.getId());
		return SHOW;
	}

	public String deleteLabSamType4Batch() throws GlobalException {
		headerInit();
		labSamTypeService.deleteLabSamType4Batch(labSamTypeVo.getIds());
		return DELETE;
	}

	public String ajaxCheckChildren() throws Exception {

		if (labSamTypeVo != null) {
			boolean key = labSamTypeService.getLabSamTypeByPid(labSamTypeVo);
			if (key) {
				outPutString("1");
			} else {
				outPutString("0");
			}
		}
		return NONE;
	}
	public String showLabItem4Select() throws GlobalException{
		if(null==labItemVo){
			labItemVo=new LabItemVo();
			}
		List<LabItemVo> labItemList = labItemService.getLabItemList(labItemVo);
		setAttribute("labItemList", labItemList);
		return SHOW;
	}
	public String preAddLabSamType() throws GlobalException {
		
		if(labSamTypeVo!=null){
			labSamTypeVo=labSamTypeService.getLabSamTypeItem(labSamTypeVo);
		}else{
			labSamTypeVo=new LabSamTypeVo();
		}
		return PREADD;
	}
	public String addLabSamTypeItem() throws GlobalException {
		headerInit();
		labSamTypeService.addLabSamTypeItem(labSamTypeVo);
		return ADD;
	}
	public LabSamTypeVo getLabSamTypeVo() {
		return labSamTypeVo;
	}

	public void setLabSamTypeVo(LabSamTypeVo labSamTypeVo) {
		this.labSamTypeVo = labSamTypeVo;
	}

	public List<LabSamTypeVo> getLabSamTypeVoList() {
		return labSamTypeVoList;
	}

	public void setLabSamTypeVoList(List<LabSamTypeVo> labSamTypeVoList) {
		this.labSamTypeVoList = labSamTypeVoList;
	}

}
