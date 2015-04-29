package cn.labsoft.labos.source.quality.action;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.source.quality.service.ILabQuaControlService;
import cn.labsoft.labos.source.quality.vo.LabQuaControlVo;
import cn.labsoft.labos.utils.StrUtils;


@Controller
@Scope("prototype")
public class LabQuaControlAction extends BaseAction {
	
	private ILabQuaControlService labQuaControlService ;
	private ILabCodeService labCodeService ;
	private ILabOrgService labOrgService ;
	private ILabApparaService labApparaService;

    private List<LabOrgVo>  unitOrgVoList;
    private List<LabOrgVo>  labOrgVoList;
    private List<LabCodeVo> labCodeVoList;
    private LabQuaControlVo labQuaControlVo;
    private LabApparaVo labApparaVo;
    
    @Resource 
    public void setLabQuaControlService(ILabQuaControlService labQuaControlService) {
		this.labQuaControlService = labQuaControlService;
	}
    @Resource 
    public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}

	@Resource 
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
    @Resource 
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	public List<LabOrgVo> getLabOrgVoList() {
		return labOrgVoList;
	}
	public List<LabCodeVo> getLabCodeVoList() {
		return labCodeVoList;
	}
	public void setLabCodeVoList(List<LabCodeVo> labCodeVoList) {
		this.labCodeVoList = labCodeVoList;
	}
	public void setLabOrgVoList(List<LabOrgVo> labOrgVoList) {
		this.labOrgVoList = labOrgVoList;
	}
	public LabQuaControlVo getLabQuaControlVo() {
		return labQuaControlVo;
	}
	public void setLabQuaControlVo(LabQuaControlVo labQuaControlVo) {
		this.labQuaControlVo = labQuaControlVo;
	}
	public List<LabOrgVo> getUnitOrgVoList() {
		return unitOrgVoList;
	}
	public void setUnitOrgVoList(List<LabOrgVo> unitOrgVoList) {
		this.unitOrgVoList = unitOrgVoList;
	}
	
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	public String listLabQuaControl() throws GlobalException{
		if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		pageResult=labQuaControlService.getLabQuaControlPR(labQuaControlVo, pageResult);
		unitOrgVoList=labOrgService.getLabOrgVoByRank("0");
		labOrgVoList = labOrgService.getLabOrgListByPId(labQuaControlVo.getUnitOrgSearch());
		return LIST;
	}
	public String preAddLabQuaControl() throws GlobalException {
		unitOrgVoList=labOrgService.getLabOrgVoByRank("0");
		labOrgVoList = labOrgService.getLabOrgVoByRank("1");
		labCodeVoList=labCodeService.getLabCodeByTypeCode("ZLJDNR");
	    return PREADD;
	}
	public String addLabQuaControl() throws GlobalException {
		if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		labQuaControlService.addLabQuaControl(labQuaControlVo);
		return ADD;
	}
    
	public String preUpdateLabQuaControl() throws GlobalException {
		if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		labQuaControlVo=labQuaControlService.getLabQuaControl(labQuaControlVo.getId());
		unitOrgVoList=labOrgService.getLabOrgVoByRank("0");
		labOrgVoList = labOrgService.getLabOrgListByPId(labQuaControlVo.getUnitOrgId());
	    return PREUPDATE;
	}
	public String updateLabQuaControl() throws GlobalException {
		if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		labQuaControlService.updateLabQuaControl(labQuaControlVo);
		return UPDATE;
	}
    public String deleteLabQuaControl() throws GlobalException {
    	if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
    	labQuaControlService.update2DelLabQuaControl(labQuaControlVo.getIds());
		return DELETE;
	}
    public String showLabQuaControl() throws GlobalException {
    	if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		labQuaControlVo=labQuaControlService.getLabQuaControl(labQuaControlVo.getId());
    	return SHOW;
    }
    public String ajaxLabQuaControl4LabOrg()throws Exception{
    	if(null==labQuaControlVo) labQuaControlVo=new LabQuaControlVo();
		List<LabOrgVo> labOrgVolist= labOrgService.getLabOrgListByPId(labQuaControlVo.getUnitOrgId());
		StringBuffer buffer=new StringBuffer("[");
		if(null!=labOrgVolist&&labOrgVolist.size()>0){
			for(LabOrgVo labOrgVo:labOrgVolist){
				String name=labOrgVo.getName();
				if(!StrUtils.isBlankOrNull(name)){
					name=name.replaceAll("'","‘");
				}
				buffer.append("{'id':'"+labOrgVo.getId()+"' ,'name':'"+name+"'},");
			}
			buffer=new StringBuffer(buffer.substring(0,buffer.length()-1));
		}
		buffer.append("]");
		outPutString(buffer.toString());
		return NONE;
	}
    public String showLabAppara4select() throws GlobalException{
		if (null == labApparaVo)
			labApparaVo = new LabApparaVo();
		labApparaVo.setStatus("0");
		pageResult= labApparaService.getLabApparaList(labApparaVo, pageResult);
		return SHOW;
	}
}
