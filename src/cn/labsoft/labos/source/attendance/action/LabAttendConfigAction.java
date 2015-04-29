package cn.labsoft.labos.source.attendance.action;


import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.attendance.service.ILabAttendConfigService;
import cn.labsoft.labos.source.attendance.vo.LabAttendConfigVo;
/**
 * 用户考勤配置操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabAttendConfigAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabAttendConfigService labAttendConfigService;
	/**
	 * 考勤配置Service注入
	 * @param labAttendConfigService
	 */
	@Resource
	public void setLabAttendConfigService(ILabAttendConfigService labAttendConfigService) {
		this.labAttendConfigService = labAttendConfigService;
	}
	private LabAttendConfigVo labAttendConfigVo;
	public LabAttendConfigVo getLabAttendConfigVo() {
		return labAttendConfigVo;
	}
	public void setLabAttendConfigVo(LabAttendConfigVo labAttendConfigVo) {
		this.labAttendConfigVo = labAttendConfigVo;
	}
	/**
	 * 考勤配置-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabAttendConfig() throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		pageResult=labAttendConfigService.getLabAttendConfigPR(labAttendConfigVo, pageResult);
		return LIST;
	}
	/**
	 * 考勤配置-新增页面方法
	 * @throws GlobalException
	 */
	public String preAddLabAttendConfig(){
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		return PREADD;
	}
	/**
	 * 考勤配置-新增方法
	 * @throws GlobalException
	 */
	public String addLabAttendConfig() throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigVo=labAttendConfigService.addLabAttendConfig(labAttendConfigVo);
		return ADD;
	}
	/**
	 * 考勤配置-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabAttendConfig() throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigVo=labAttendConfigService.getLabAttendConfig(labAttendConfigVo.getId());
		return PREUPDATE;
	}
	/**
	 * 考勤配置-修改方法
	 * @throws GlobalException
	 */
	public String updateLabAttendConfig() throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigService.updateLabAttendConfig(labAttendConfigVo);
		return UPDATE;
	}
	/**
	 * 考勤配置-查看页面方法
	 * @throws GlobalException
	 */
	public String showLabAttendConfig()  throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigVo=labAttendConfigService.getLabAttendConfig(labAttendConfigVo.getId());
		return SHOW;
	}
	/**
	 * 考勤配置-删除方法
	 * @throws GlobalException
	 */
	public String deleteLabAttendConfig() throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigService.deleteLabAttendConfig(labAttendConfigVo.getIds());
		return DELETE;
	}
	/**
	 * 考勤配置-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabAttendConfig4Del()throws GlobalException{
		if(null==labAttendConfigVo)labAttendConfigVo=new LabAttendConfigVo();
		labAttendConfigService.updateLabAttendConfig4Del(labAttendConfigVo.getIds());
		return DELETE;
	}
	
}
