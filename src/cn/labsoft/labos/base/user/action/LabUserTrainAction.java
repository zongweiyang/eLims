package cn.labsoft.labos.base.user.action;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.service.ILabUserTrainService;
import cn.labsoft.labos.base.user.vo.LabUserTrainVo;
/**
 * 用户培训信息操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabUserTrainAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabUserTrainService labUserTrainService;
	private ILabOrgService labOrgService;
	private List<LabUserTrainVo> listLabUserTrainVo;
	private LabUserTrainVo labUserTrainVo;
	/**
	 * 用户培训Service注入
	 * @param labUserTrainService
	 */
	@Resource
	public void setLabUserTrainService(ILabUserTrainService labUserTrainService) {
		this.labUserTrainService = labUserTrainService;
	}
	/**
	 * 组织Service注入
	 * @param labOrgService
	 */
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	public List<LabUserTrainVo> getListLabUserTrainVo() {
		return listLabUserTrainVo;
	}
	public void setListLabUserTrainVo(List<LabUserTrainVo> listLabUserTrainVo) {
		this.listLabUserTrainVo = listLabUserTrainVo;
	}
	public LabUserTrainVo getLabUserTrainVo() {
		return labUserTrainVo;
	}
	public void setLabUserTrainVo(LabUserTrainVo labUserTrainVo) {
		this.labUserTrainVo = labUserTrainVo;
	}
	/**
	 * 用户培训-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo){
			labUserTrainVo=new LabUserTrainVo();
			pageResult.setOrderColumn("createTime");
			pageResult.setOrder(PageResult.ORDER_DESC);
		}
		pageResult=labUserTrainService.getLabUserTrainPR(labUserTrainVo, pageResult);
		return LIST;
	}
	/**
	 * 用户培训-新增页面方法
	 * @throws GlobalException
	 */
	public String preAddLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		List<LabOrgVo> orgList=labOrgService.getLabOrgTree();
		orgList=labOrgService.getUsedLabOrgList(orgList);
		setAttribute("orgList", orgList);
		return PREADD;
	}
	/**
	 * 用户培训-新增方法
	 * @throws GlobalException
	 */
	public String addLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainVo=labUserTrainService.addLabUserTrain(labUserTrainVo);
		return ADD;
	}
	/**
	 * 用户培训-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainVo=labUserTrainService.getLabUserTrain(labUserTrainVo.getId());
		
		List<LabOrgVo> orgList=labOrgService.getLabOrgTree();
		orgList=labOrgService.getUsedLabOrgList(orgList);
		setAttribute("orgList", orgList);
		return PREUPDATE;
	}
	/**
	 * 用户培训-修改方法
	 * @throws GlobalException
	 */
	public String updateLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainService.updateLabUserTrain(labUserTrainVo);
		return UPDATE;
	}
	/**
	 * 用户培训-查看方法
	 * @throws GlobalException
	 */
	public String showLabUserTrain()  throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainVo=labUserTrainService.getLabUserTrain(labUserTrainVo.getId());
		return SHOW;
	}
	/**
	 * 用户培训-删除方法
	 * @throws GlobalException
	 */
	public String deleteLabUserTrain() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainService.deleteLabUserTrain(labUserTrainVo.getIds());
		return DELETE;
	}
	/**
	 * 用户培训-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabUserTrain4Del()throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainService.updateLabUserTrain4Del(labUserTrainVo.getIds());
		return DELETE;
	}
	/**
	 * 用户培训记录-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabUserTrain4Record() throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainVo=labUserTrainService.getLabUserTrain(labUserTrainVo.getId());
		return PREUPDATE;
	}
	/**
	 * 用户培训记录-修改方法
	 * @throws GlobalException
	 */
	public String updateLabUserTrain4Record()throws GlobalException{
		if(null==labUserTrainVo)labUserTrainVo=new LabUserTrainVo();
		labUserTrainService.updateLabUserTrain4Record(labUserTrainVo);
		return UPDATE;
	}
}
