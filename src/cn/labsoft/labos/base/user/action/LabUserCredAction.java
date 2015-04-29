package cn.labsoft.labos.base.user.action;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.base.user.service.ILabUserCredService;
import cn.labsoft.labos.base.user.vo.LabUserCredVo;
/**
 * 用户证书信息操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabUserCredAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private ILabUserCredService labUserCredService;
	private List<LabUserCredVo> listLabUserCredVo;
	private LabUserCredVo labUserCredVo;
	/**
	 * 用户证书Service注入
	 * @param labUserCredService
	 */
	@Resource
	public void setLabUserCredService(ILabUserCredService labUserCredService) {
		this.labUserCredService = labUserCredService;
	}
	public List<LabUserCredVo> getListLabUserCredVo() {
		return listLabUserCredVo;
	}
	public void setListLabUserCredVo(List<LabUserCredVo> listLabUserCredVo) {
		this.listLabUserCredVo = listLabUserCredVo;
	}
	public LabUserCredVo getLabUserCredVo() {
		return labUserCredVo;
	}
	public void setLabUserCredVo(LabUserCredVo labUserCredVo) {
		this.labUserCredVo = labUserCredVo;
	}
	/**
	 * 用户证书-列表页面
	 * @throws GlobalException
	 */
	public String listLabUserCred() throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		pageResult=labUserCredService.getLabUserCredPR(labUserCredVo, getPageResult());
		return LIST;
	}
	/**
	 * 用户证书-调至新增页面方法
	 */
	public String preAddLabUserCred(){
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		return PREADD;
	}
	/**
	 * 用户证书-新增方法
	 * @throws GlobalException
	 */
	public String addLabUserCred() throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredVo=labUserCredService.addLabUserCred(labUserCredVo);
		return ADD;
	}
	/**
	 * 用户证书-调至修改页面方法
	 */
	public String preUpdateLabUserCred() throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredVo=labUserCredService.getLabUserCred(labUserCredVo.getId());
		return PREUPDATE;
	}
	/**
	 * 用户证书-修改方法
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabUserCred() throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredService.updateLabUserCred(labUserCredVo);
		return UPDATE;
	}
	/**
	 * 用户证书-调至查看页面方法
	 */
	public String showLabUserCred()  throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredVo=labUserCredService.getLabUserCred(labUserCredVo.getId());
		return SHOW;
	}
	/**
	 * 用户证书-删除方法
	 */
	public String deleteLabUserCred() throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredService.deleteLabUserCred(labUserCredVo.getIds());
		return DELETE;
	}
	/**
	 * 用户证书-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabUserCred4Del()throws GlobalException{
		if(null==labUserCredVo)labUserCredVo=new LabUserCredVo();
		labUserCredService.updateLabUserCred4Del(labUserCredVo.getIds());
		return DELETE;
	}
	
}
