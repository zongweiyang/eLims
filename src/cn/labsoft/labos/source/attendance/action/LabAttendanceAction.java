package cn.labsoft.labos.source.attendance.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.attendance.service.ILabAttendanceService;
import cn.labsoft.labos.source.attendance.vo.LabAttendanceVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
/**
 * 用户考勤记录操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabAttendanceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabAttendanceService labAttendanceService;
	private ILabOrgService labOrgService;
	/**
	 * 组织Service注入
	 * @param labOrgService
	 */
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	/**
	 * 考勤记录Service注入
	 * @param labAttendanceService
	 */
	@Resource
	public void setLabAttendanceService(ILabAttendanceService labAttendanceService) {
		this.labAttendanceService = labAttendanceService;
	}
	
	private LabAttendanceVo labAttendanceVo;
	private List<LabAttendanceVo> attendList;
	private Integer[] countList;//个人天数统计
	private List<String[]> countAllList;//所有人到勤统计
	
	public List<String[]> getCountAllList() {
		return countAllList;
	}
	public void setCountAllList(List<String[]> countAllList) {
		this.countAllList = countAllList;
	}
	public Integer[] getCountList() {
		return countList;
	}
	public void setCountList(Integer[] countList) {
		this.countList = countList;
	}
	public List<LabAttendanceVo> getAttendList() {
		return attendList;
	}
	public void setAttendList(List<LabAttendanceVo> attendList) {
		this.attendList = attendList;
	}
	public LabAttendanceVo getLabAttendanceVo() {
		return labAttendanceVo;
	}
	public void setLabAttendanceVo(LabAttendanceVo labAttendanceVo) {
		this.labAttendanceVo = labAttendanceVo;
	}
	/**
	 * 考勤记录-列表方法（统计）
	 * @throws GlobalException
	 */
	public String listLabAttendance4All() throws GlobalException{
		if(null==labAttendanceVo){
			labAttendanceVo=new LabAttendanceVo();
			labAttendanceVo.setWorkMonth(DateUtils.getYear()+"-"+DateUtils.getMonth());
			labAttendanceVo.setOrgId(getSessionContainer().getOrgId());
		}
		countList=labAttendanceService.getLabAttendanceList4User(labAttendanceVo);
		countAllList=labAttendanceService.getLabAttendanceList4Org(labAttendanceVo);
		List<LabOrgVo> orgList=labOrgService.getLabOrgTree();
		setAttribute("orgList", orgList);
		
		return LIST;
	}
	/**
	 * 考勤记录-查询方法
	 * @throws GlobalException
	 */
	public String showLabAttendance4User()  throws GlobalException{
		if(null==labAttendanceVo){
			labAttendanceVo=new LabAttendanceVo();
			labAttendanceVo.setWorkMonth(DateUtils.getYear()+"-"+DateUtils.getMonth());
			labAttendanceVo.setUserId(getSessionContainer().getUserId());
		}
		attendList=labAttendanceService.getLabAttendanceList(labAttendanceVo);
		countList=labAttendanceService.getLabAttendanceList4User(labAttendanceVo);
		return SHOW;
	}
	/**
	 * 考勤记录-列表方法
	 * @throws GlobalException
	 */
	public String listLabAttendance() throws GlobalException{
		if(null==labAttendanceVo){
			labAttendanceVo=new LabAttendanceVo();
			labAttendanceVo.setWorkMonth(DateUtils.getYear()+"-"+DateUtils.getMonth());
			labAttendanceVo.setUserId(getSessionContainer().getUserId());
		}
		attendList=labAttendanceService.getLabAttendanceList(labAttendanceVo);
		countList=labAttendanceService.getLabAttendanceList4User(labAttendanceVo);
		return LIST;
	}
	/**
	 * 考勤记录-签到方法
	 * @throws GlobalException
	 */
	public String addLabAttendance() throws GlobalException{
		if(null==labAttendanceVo)
			labAttendanceVo=new LabAttendanceVo();
		labAttendanceVo=labAttendanceService.addLabAttendance(labAttendanceVo);
		return SHOW;
	}
//	public String preUpdateLabAttendance() throws GlobalException{
//		if(null==labAttendanceVo)labAttendanceVo=new LabAttendanceVo();
//		labAttendanceVo=labAttendanceService.getLabAttendance(labAttendanceVo.getId());
//		return PREUPDATE;
//	}
	/**
	 * 考勤记录-签退方法
	 * @throws GlobalException
	 */
	public String updateLabAttendance() throws GlobalException{
		if(null==labAttendanceVo)labAttendanceVo=new LabAttendanceVo();
		labAttendanceService.updateLabAttendance(labAttendanceVo);
		return SHOW;
	}
	/**
	 * 考勤记录-补签方法
	 * @throws GlobalException
	 */
	public String preAddLabAttendance4History() throws GlobalException{
		if(null==labAttendanceVo)
			labAttendanceVo=new LabAttendanceVo();
		if(!StrUtils.isBlankOrNull(labAttendanceVo.getWorkDate())){
			labAttendanceVo=labAttendanceService.getLabAttendance(getSessionContainer().getUserId(),labAttendanceVo.getWorkDate());
		}else{
			labAttendanceVo.setUserId(getSessionContainer().getUserId());
			labAttendanceVo.setUserName(getSessionContainer().getUserName());
		}
		return PREADD;
	}
	/**
	 * 考勤记录-补签方法
	 * @throws GlobalException
	 */
	public String addLabAttendance4History() throws GlobalException{
		labAttendanceVo=labAttendanceService.addLabAttendance4History(labAttendanceVo);
		return SHOW;
	}
	/**
	 * 考勤记录-查看方法
	 * @throws GlobalException
	 */
	public String showLabAttendance()  throws GlobalException{
		if(null==labAttendanceVo)labAttendanceVo=new LabAttendanceVo();
		labAttendanceVo=labAttendanceService.getLabAttendance(labAttendanceVo.getId());
		return SHOW;
	}
	/**
	 * 考勤记录-删除方法
	 * @throws GlobalException
	 */
	public String deleteLabAttendance() throws GlobalException{
		if(null==labAttendanceVo)labAttendanceVo=new LabAttendanceVo();
		labAttendanceService.deleteLabAttendance(labAttendanceVo.getIds());
		return DELETE;
	}
	/**
	 * 考勤记录-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabAttendance4Del()throws GlobalException{
		if(null==labAttendanceVo)labAttendanceVo=new LabAttendanceVo();
		labAttendanceService.updateLabAttendance4Del(labAttendanceVo.getIds());
		return DELETE;
	}
	
}
