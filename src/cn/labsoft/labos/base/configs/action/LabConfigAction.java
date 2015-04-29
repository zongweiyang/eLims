package cn.labsoft.labos.base.configs.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
/**
 * 系统配置操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabConfigAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ILabConfigService labConfigService;
	private LabConfigVo labConfigVo;
	private List<LabConfigVo> configList;
	
	/**
	 * 系统配置Service注入
	 * @param labConfigService
	 */
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	/**
	 * 系统配置-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabConfig()throws GlobalException{
		if(labConfigVo==null){
			labConfigVo=new LabConfigVo();
		}
		configList=labConfigService.getLabConfigListAll();
		return LIST;
	}
	/**
	 * 系统配置-新增方法
	 * @throws GlobalException
	 */
	public String addLabConfig()throws GlobalException{
		labConfigService.addLabConfig(labConfigVo);
		return ADD;
	}
	/**
	 * 系统配置-系统初始化配置
	 * @throws GlobalException
	 * @throws IOException
	 */
	/*public String addLabConfig4InitSystem()throws GlobalException, IOException{
		boolean flag=labConfigService.addLabConfig4InitSystem();
		outPutString(flag+"");
		return NONE;
	}*/
	public String initLabConfig4Value() throws GlobalException{
		boolean flag = labConfigService.initLabConfig4Value(labConfigVo);
		try {
			outPutString(flag+"");
		} catch (IOException e) {
			Log4J.error("LabConfigAction.."+e.getStackTrace());
		}
		return UPDATE;
	}
	/**
	 * 系统配置-修改方法
	 * @throws GlobalException
	 */
	public String updateLabConfig()throws GlobalException{
		labConfigService.updateLabConfig(labConfigVo);
		return UPDATE;
	}
	/**
	 * 系统配置-删除方法
	 * @throws GlobalException
	 */
	public String delLabConfig()throws GlobalException{
		labConfigService.updateLabConfig4Del(id);
		return DELETE;
	}
	/**
	 * 验证编码不能重复
	 * @throws GlobalException
	 */
	public String ajaxLabConfig4Code()throws GlobalException{
		String code=getParameter("code");
		String id=getParameter("id");
		try {
			if(labConfigService.hasLabConfig4Code(code,id)){
				outPutString(true + "");
			}else{
				outPutString(false + "");
			}
		} catch (IOException e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException(getText("code.error") + e.getMessage());
		}
		return NONE;
	}
	public LabConfigVo getLabConfigVo() {
		return labConfigVo;
	}

	public void setLabConfigVo(LabConfigVo labConfigVo) {
		this.labConfigVo = labConfigVo;
	}

	public List<LabConfigVo> getConfigList() {
		return configList;
	}

	public void setConfigList(List<LabConfigVo> configList) {
		this.configList = configList;
	}

}
