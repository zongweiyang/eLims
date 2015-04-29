package cn.labsoft.labos.portlets.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.coreextend.service.ICoreExtendService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.portlets.vo.AlarmMessageVo;
import cn.labsoft.labos.source.reagent.service.ILabReaPurMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaPurDetailVo;

/**
 * 
 * <strong>Title : PortletsShowAction </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2009-12-30 上午09:58:40 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author CharlesXi<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Controller
@Scope("prototype")
public class PortletsShowAction extends BaseAction {
	
	private ICoreExtendService coreExtendService;
	private ILabReaPurMainService labReaPurMainService;
	private ILabUserService labUserService;
	private ILabFunctionService labFunctionService;
	private List<LabMsgDetailVo> unReadMessageList;
	private List<AlarmMessageVo> alarmMessageList;
	private ILabRoleService labRoleService ;
	
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	@Resource
	public void setCoreExtendService(ICoreExtendService coreExtendService) {
		this.coreExtendService = coreExtendService;
	}
	@Resource
	public void setLabReaPurMainService(ILabReaPurMainService labReaPurMainService) {
		this.labReaPurMainService = labReaPurMainService;
	}
	public List<LabMsgDetailVo> getUnReadMessageList() {
		return unReadMessageList;
	}
	public void setUnReadMessageList(List<LabMsgDetailVo> unReadMessageList) {
		this.unReadMessageList = unReadMessageList;
	}
	public List<AlarmMessageVo> getAlarmMessageList() {
		return alarmMessageList;
	}
	public void setAlarmMessageList(List<AlarmMessageVo> alarmMessageList) {
		this.alarmMessageList = alarmMessageList;
	}
	/**
	 * 
	 * TODO Portlets主方法
	 * @return void
	 * @throws GlobalException
	 * @return String
	 */
	public String showPortlets() throws GlobalException{
 	   //得到未读消息
		getUnReadLabMsg();
		getAlarmMessage();
		getThemeType();
		Set<String> labPortletsSet = labRoleService.getLabPortlets(getSessionContainer().getSessionContainer().getUserId());
		String show = "";
		if(null!=labPortletsSet&&labPortletsSet.size()>0){
			for(String str:labPortletsSet){
				show +="_"+str;
			}
		}
		setAttribute("show", show);
		return LIST;
	}
	/**
	 * 
	 * TODO 得到预警信息列表
	 * @return void
	 * @throws GlobalException
	 * @return String
	 */
	private void getAlarmMessage() throws GlobalException {
		if(alarmMessageList==null)alarmMessageList=new ArrayList<AlarmMessageVo>();
		int index=1;
		List<LabReaPurDetailVo> reaPurDetailList = labReaPurMainService.getLabReaPurDetailList();
		if(reaPurDetailList!=null){
			for (LabReaPurDetailVo labReaPurDetailVo : reaPurDetailList) {
				AlarmMessageVo amVo=new AlarmMessageVo();
				amVo.setIndex(index+"");
				amVo.setType(getText("试剂"));
				amVo.setMessage("【"+labReaPurDetailVo.getReagentName()+"】"+getText("reagent.low.alarm"));
				alarmMessageList.add(amVo);
				index++;
			}
		}
		//可将耗材，标准品，样品，等等警戒信息加于此处
	}
	
	/**
	 * 
	 * TODO 未读消息
	 * @return void
	 * @throws GlobalException
	 * @return String
	 */
	private void getUnReadLabMsg() throws GlobalException {
		SessionContainer sessionContainer = SessionContainer.getSessionContainer();
		String userId = sessionContainer.getUserId();
		LabMsgDetailVo labMsgDetailVo = new LabMsgDetailVo();
		labMsgDetailVo.setSenderId(userId);
		labMsgDetailVo.setReceiveId(userId);
		unReadMessageList = coreExtendService.getUnreadMesgMessageList(
				labMsgDetailVo);
	}
	
	
	
}
