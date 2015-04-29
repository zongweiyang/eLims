package cn.labsoft.labos.business.samtest.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.samtest.service.ILabSamTaskService;
import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * stone 检测管理--任务管理（任务下达，任务分配）
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class LabSamTaskAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private List<LabSamTestVo> listLabSamTestVo;
	private LabSamTestVo labSamTestVo;
	private LabSamTestBeatchVo labSamTestBeaVo;
	@Resource
	private ILabSamTaskService labSamTeskService;
	@Resource
	private ILabOrgService labOrgService;
	@Resource
	private ILabUserService labUserService;
	@Resource
	private ILabWfProcessInsService labWfProcessInsService;
	
	
	public List<LabSamTestVo> getListLabSamTestVo() {
		return listLabSamTestVo;
	}

	public void setListLabSamTestVo(List<LabSamTestVo> listLabSamTestVo) {
		this.listLabSamTestVo = listLabSamTestVo;
	}

	public LabSamTestVo getLabSamTestVo() {
		return labSamTestVo;
	}

	public void setLabSamTestVo(LabSamTestVo labSamTestVo) {
		this.labSamTestVo = labSamTestVo;
	}

	public LabSamTestBeatchVo getLabSamTestBeaVo() {
		return labSamTestBeaVo;
	}

	public void setLabSamTestBeaVo(LabSamTestBeatchVo labSamTestBeaVo) {
		this.labSamTestBeaVo = labSamTestBeaVo;
	}
	/**
	 * 任务下达 LIST
	 * @return
	 * @throws GlobalException
	 */
	public String listLabSamTask4Issued() throws GlobalException{
		if(labSamTestVo==null){
			labSamTestVo=new LabSamTestVo();
			labSamTestVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamTeskService.getLabSamTeskIssuedPR(labSamTestVo,pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 任务下达 PREUPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateLabSamTask4Issued() throws GlobalException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
		listLabSamTestVo=labSamTeskService.getLabSamTeskVo4Issued(labSamTestVo.getTaskId());
		List<LabOrgVo> listOrgVo=labOrgService.getLabOrgVoByWhere(" AND type='"+Constants_Business.LABORATORY+"'");
		setAttribute("listOrgVo", listOrgVo);
		return PREUPDATE;
	}
	/**
	 * 任务下达 处理 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTask4Issued() throws GlobalException{
		if(listLabSamTestVo==null)listLabSamTestVo=new ArrayList<LabSamTestVo>();
		labSamTeskService.updateLabSamTeskVo4Issued(listLabSamTestVo);
		return UPDATE;
	}
	/**
	 * 批量下达 PREUPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateSamTask4IssuedBeach() throws GlobalException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
		listLabSamTestVo=labSamTeskService.getLabSamTeskVoBatch4Issued(labSamTestVo.getIds());
		List<LabOrgVo> listOrgVo=labOrgService.getLabOrgVoByWhere(" AND type='"+Constants_Business.LABORATORY+"'");
		setAttribute("listOrgVo", listOrgVo);
		return PREUPDATE;
	}
	/**
	 * 批量下达 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateSamTask4IssuedBeach()throws GlobalException{
		if(listLabSamTestVo==null)listLabSamTestVo=new ArrayList<LabSamTestVo>();
		labSamTeskService.updateLabSamTeskVo4IssuedBeach(listLabSamTestVo);
		return UPDATE;
	}
	/**
	 *  任务分配 LIST
	 * @return
	 * @throws GlobalException
	 */
	public String listLabSamTask4Allot() throws GlobalException{
		if(labSamTestVo==null){
			labSamTestVo=new LabSamTestVo();
			labSamTestVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamTeskService.getLabSamTesk4AllotPR(labSamTestVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 任务分配 PREUPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateLabSamTask4Allot()throws GlobalException{
		if(labSamTestVo==null){
			labSamTestVo=new LabSamTestVo();
		}
		listLabSamTestVo=labSamTeskService.getLabSamTeskVo4Allot(labSamTestVo.getTaskId());
		if(listLabSamTestVo.size()==0){
			return LIST;
		}
		List<LabUserVo> listLabUserTest=labUserService.getLabUserListByRoleName(Constants_Business.TESTROLE);
		setAttribute("listLabUserTest", listLabUserTest);
		List<LabUserVo> listLabUserCheck=labUserService.getLabUserListByRoleName(Constants_Business.CHECKROLE);
		setAttribute("listLabUserCheck", listLabUserCheck);
		return PREUPDATE;
	}
	public String showlabSamTaskAllot4Select() throws GlobalException, UnsupportedEncodingException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
			listLabSamTestVo=labSamTeskService.getLabSamTeskVoAllotShow(labSamTestVo);
		return SHOW;
	}
	/**
	 * 任务分配 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTask4Allot()throws GlobalException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
		if(labSamTestBeaVo==null)labSamTestBeaVo=new LabSamTestBeatchVo();
		labSamTeskService.updateLabSamTeskVo4Allot(labSamTestBeaVo,listLabSamTestVo);
		return UPDATE;
	}
	/**
	 * 批量任务分配  PREUPDATRE
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateSamTask4AllotBeach() throws GlobalException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
		listLabSamTestVo=labSamTeskService.getLabSamTeskVo4AllotBeatch(labSamTestVo.getIds());
		List<LabUserVo> listLabUserTest=labUserService.getLabUserListByRoleName(Constants_Business.TESTROLE);
		setAttribute("listLabUserTest", listLabUserTest);
		List<LabUserVo> listLabUserCheck=labUserService.getLabUserListByRoleName(Constants_Business.CHECKROLE);
		setAttribute("listLabUserCheck", listLabUserCheck);
		if(listLabSamTestVo.size()>0)
			return PREUPDATE;
		else
			return LIST;
	}
	public String updateSamTask4AllotBeach() throws GlobalException{
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
		if(labSamTestBeaVo==null)labSamTestBeaVo=new LabSamTestBeatchVo();
		labSamTeskService.updateLabSamTeskVo4Allot(labSamTestBeaVo,listLabSamTestVo);
		return UPDATE;
	}
	
}
