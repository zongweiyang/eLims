package pageSrc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import IServiceSrc;
import voSrc;

@Controller
@Scope("prototype")
public class MouldAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IMouldService mouldService;
	@Resource
	public void setMouldService(IMouldService mouldService) {
		this.mouldService = mouldService;
	}
	private MouldVo mouldVo;
	public MouldVo getMouldVo() {
		return mouldVo;
	}
	public void setMouldVo(MouldVo mouldVo) {
		this.mouldVo = mouldVo;
	}
	public String listMould() throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		pageResult=mouldService.getMouldPR(mouldVo, pageResult);
		return LIST;
	}
	public String preAddMould(){
		if(null==mouldVo)mouldVo=new MouldVo();
		return PREADD;
	}
	public String addMould() throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		mouldVo=mouldService.addMould(mouldVo);
		return ADD;
	}
	public String preUpdateMould() throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		mouldVo=mouldService.getMould(mouldVo.getId());
		return PREUPDATE;
	}
	public String updateMould() throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		boolean key=mouldService.updateMould(mouldVo);
		return UPDATE;
	}
	public String showMould()  throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		mouldVo=mouldService.getMould(mouldVo.getId());
		return SHOW;
	}
	public String deleteMould() throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		mouldService.deleteMould(mouldVo.getIds());
		return DELETE;
	}
	public String updateMould4Del()throws GlobalException{
		if(null==mouldVo)mouldVo=new MouldVo();
		mouldService.updateMould4Del(mouldVo.getIds());
		return DELETE;
	}
	
}
