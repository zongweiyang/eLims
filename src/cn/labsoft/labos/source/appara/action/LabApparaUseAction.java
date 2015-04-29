package cn.labsoft.labos.source.appara.action;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.service.ILabApparaUseService;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
@Controller
@Scope("prototype")
public class LabApparaUseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaUseService labApparaUseService;
	private ILabApparaService labApparaService;
	private ILabUserService labUserService;
	private LabApparaVo labApparaVo;
	private LabApparaUseVo labApparaUseVo;
	private LabUserVo labUserVo;
	private List<LabUserVo> labUserList;
	private List<LabApparaVo> labApparaList;
	private List<LabApparaUseVo> labApparaUseVoList;
	private String labApparaUseListJSON;
	@Resource
	public void setLabApparaUseService(ILabApparaUseService labApparaUseService) {
		this.labApparaUseService = labApparaUseService;
	}
	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	/**
	 * 仪器 使用 清单
	 * @throws GlobalException 
	 * */
	public String listLabAppUse4View() throws GlobalException{
		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		pageResult = labApparaUseService.getLabApparaUsePR(labApparaUseVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器 使用 列表
	 * */
	public String listLabAppUse() throws GlobalException{
 		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		pageResult = labApparaUseService.getLabApparaUsePR(labApparaUseVo, pageResult);
		return LIST; 
	}
	/**
	 * 仪器使用查看
	 * */
	public String showLabAppUse()throws GlobalException{
		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseVo = labApparaUseService.getLabApparaUse(labApparaUseVo.getId());
		return SHOW;
	}
	/**
	 * 仪器记录
	 * */
	public String listLabAppRecord() throws GlobalException{
		if(null == labApparaVo){
			labApparaVo = new LabApparaVo();
		}
		pageResult = labApparaService.getLabApparaList(labApparaVo, pageResult);
		return LIST;
	}
	/**
	 * 使用 新增
	 * */
	public String preAddLabAppUse() throws GlobalException{
		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		//labApparaVo=labApparaService.getLabAppara(labApparaUseVo.getAppId());
		return SUCCESS;
	}
	/**
	 *  新增记录 保存
	 * */
	public String addLabAppUse() throws GlobalException{
		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseService.addLabApparaUse(labApparaUseVo);
		return SUCCESS;
	}
	/**
	 * 使用 修改
	 * */
	public String preUpdateLabAppUse() throws GlobalException{
		if(null == labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseVo = labApparaUseService.getLabApparaUse(labApparaUseVo.getId());
		return SUCCESS;
	}
	/**
	 * 仪器修改保存
	 * */
	public String updateLabAppUse() throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseService.updateLabApparaUse(labApparaUseVo);
		return SUCCESS;
	}
	/**
	 * 查看机时
	 * */
	public String listLabAppUse4Bespek() throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseVoList = labApparaUseService.getLabApparaUseList(labApparaUseVo);
		labApparaUseListJSON = JSONArray.fromObject(labApparaUseVoList).toString();
		return LIST;
	}
	/**
	 * 新增预约
	 * */
	public String preAddLabAppUse4bespek()throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		if (null == labApparaVo) {
			labApparaVo = new LabApparaVo();
		}
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		//仪器管理人员名单
		labUserList = labUserService.getLabUserList(labUserVo);
		labApparaList = labApparaService.getLabApparaList(labApparaVo);
		return PREADD;
	}
	/**
	 * 新增 保存
	 * */
	public String addLabAppUse4bespek()throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		boolean rs=labApparaUseService.addLabApparaBeUse(labApparaUseVo);
		if(!rs){
			String message = getText("add.fail.time.rush");
			getRequest().getSession().setAttribute("message", message);
		}
		return ADD;
	}
	/**
	 * 修改 预约
	 * */
	public String preUpdateLabAppUse4bespek()throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		if (null == labApparaVo) {
			labApparaVo = new LabApparaVo();
		}
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		labApparaUseVo = labApparaUseService.getLabApparaUse(labApparaUseVo.getId());
		//仪器管理人员名单
		labUserList = labUserService.getLabUserList(labUserVo);
		labApparaList = labApparaService.getLabApparaList(labApparaVo);
		return PREUPDATE;
	}
	/**
	 * 修改 保存
	 * */
	public String updateLabAppUse4bespek()throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseService.updateLabApparaUse(labApparaUseVo);
		return UPDATE;
	}
	/**
	 * 删除预约
	 * */
	public String deleteLabAppUsebespek()throws GlobalException{
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		labApparaUseService.deleteLabApparaUse(labApparaUseVo);
		return DELETE;
	}
	/**
	 * 拖动修改时间
	 * */
	public void ajaxLabAppUsebes4update()throws GlobalException{
		String date="";
		if(null==labApparaUseVo){
			labApparaUseVo = new LabApparaUseVo();
		}
		boolean key=labApparaUseService.updateLabApparaUse(labApparaUseVo);
		try {
			if(!key){
				date="false";
				outPutString(date);
			}else{
				date="true";
				outPutString(date);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}

	public LabApparaUseVo getLabApparaUseVo() {
		return labApparaUseVo;
	}

	public void setLabApparaUseVo(LabApparaUseVo labApparaUseVo) {
		this.labApparaUseVo = labApparaUseVo;
	}
	public List<LabUserVo> getLabUserList() {
		return labUserList;
	}
	public void setLabUserList(List<LabUserVo> labUserList) {
		this.labUserList = labUserList;
	}
	public List<LabApparaVo> getLabApparaList() {
		return labApparaList;
	}
	public void setLabApparaList(List<LabApparaVo> labApparaList) {
		this.labApparaList = labApparaList;
	}
	public List<LabApparaUseVo> getLabApparaUseVoList() {
		return labApparaUseVoList;
	}
	public void setLabApparaUseVoList(List<LabApparaUseVo> labApparaUseVoList) {
		this.labApparaUseVoList = labApparaUseVoList;
	}
	public String getLabApparaUseListJSON() {
		return labApparaUseListJSON;
	}
	public void setLabApparaUseListJSON(String labApparaUseListJSON) {
		this.labApparaUseListJSON = labApparaUseListJSON;
	}
	public LabUserVo getLabUserVo() {
		return labUserVo;
	}
	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}
}
