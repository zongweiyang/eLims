package cn.labsoft.labos.base.message.action;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.message.service.ILabMsgDetailService;
import cn.labsoft.labos.base.message.service.ILabMsgMainService;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.message.vo.LabMsgMainVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 消息管理Action
 * <strong>Title : LabMsgInfoAction </strong>. <br>
 * <strong>Description : TODO</strong> <br>
 * <strong>Create on : Sep 4, 2014 1:12:33 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 */
@Controller
@Scope("prototype")
public class LabMsgInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabMsgDetailService labMsgDetailService;
	private ILabMsgMainService labMsgMainService;
	private ILabUploadService labUploadService;
	private ILabUserService labUserService;
	private LabMsgMainVo labMsgMainVo;
	private LabMsgDetailVo labMsgDetailVo;
	private LabUserVo labUserVo;
	private String receivers;
	private String currentPage;
	
	
	@Resource
	public void setLabMsgDetailService(ILabMsgDetailService labMsgDetailService) {
		this.labMsgDetailService = labMsgDetailService;
	}
	@Resource
	public void setLabMsgMainService(ILabMsgMainService labMsgMainService) {
		this.labMsgMainService = labMsgMainService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}

	public String preAddLabMsg() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
			labMsgMainVo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		return PREADD;
	}
	public String addLabMsg() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		labMsgMainService.addLabMsgMain(labMsgMainVo);
		if(labMsgMainVo.getPosition().equals("2")){
			return "add2";
		}else{
			return ADD;
		}
	}
	public String listLabMsg4CaoGao() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		pageResult=labMsgMainService.getLabMsgMainPR4CaoGao(labMsgMainVo, pageResult);
		return "caogao";
	}
	//发件箱
	public String listLabMsg4Sended() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		labMsgMainVo.setPosition("0");
		pageResult=labMsgMainService.getLabMsgMainPR4Sender(labMsgMainVo, pageResult);
		return "sended";
	}
	//发件箱 查看
	public String showLabMsgMain() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo = labMsgMainService.getLabMsgMain(labMsgMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labMsgMainVo.getId(), "msg");
		setAttribute("loadList", loadList);
		return "search";
	}
	//收件箱
	public String listLabMsg4Recive() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setReceiverIds(getSessionContainer().getUserId());
		pageResult=labMsgMainService.getLabMsgMainPR4Recive(labMsgMainVo, pageResult);
		return LIST;
	}
	//收件箱 未读 点击查看
	public String updateLabMsg4Readed() throws GlobalException {
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
		}
		labMsgDetailVo.setReceiveId(getSessionContainer().getUserId());
		labMsgDetailVo=labMsgDetailService.updateLabMsg4Readed(labMsgDetailVo);
		return UPDATE;
	}
	//收件箱 已读 点击查看
	public String showLabMsg() throws GlobalException {
		
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
		}
		if(!StrUtils.isBlankOrNull(labMsgDetailVo.getId())){
			labMsgDetailVo = labMsgDetailService.showLabMsgById(labMsgDetailVo.getId());
		}else if(!StrUtils.isBlankOrNull(labMsgDetailVo.getMainMsgID())){
			labMsgDetailVo = labMsgDetailService.getLabMsgDetailById(labMsgDetailVo);
		}else{
			return ERROR;
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labMsgDetailVo.getMainMsgID(), "msg");
		setAttribute("loadList", loadList);
		return "search";
	}
	
	public String listLabMsg4IsDelete() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		pageResult=labMsgMainService.getLabMsgMainPR4Delete(labMsgMainVo, pageResult);
		return "isdelete";
	}
	
	public String preUpdateLabMsg() throws GlobalException {
		labMsgMainVo=labMsgMainService.getLabMsgMain(labMsgMainVo.getId());
		List<LabUploadVo> uploadList = labUploadService.getLabUploadList(labMsgMainVo.getId(), "msg");
		setAttribute("uploadList", uploadList);
		return PREUPDATE;
	}

	public String showLabMsgAll() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgDetailService.showlabMsgDetailAll(labMsgMainVo.getIds(), Integer.parseInt(labMsgMainVo.getFlag()));
		return UPDATE;
	}
	//首页 未读消息
	public String showReadedLabMsg() throws GlobalException {
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
		}
		labMsgDetailVo = labMsgDetailService.showLabMsgById(labMsgDetailVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labMsgDetailVo.getMainMsgID(), "msg");
		setAttribute("loadList", loadList);
		return "search";
	}
	

	public String showIsDeletedLabMsg() throws GlobalException {
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
		}
		labMsgDetailVo = labMsgDetailService.showLabMsgById(labMsgDetailVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labMsgDetailVo.getMainMsgID(), "msg");
		setAttribute("loadList", loadList);
		return "search";
	}
	public String preAddLabMsg4Forword() throws GlobalException {
		labMsgMainVo = labMsgMainService.getLabMsgMainBydetailId(labMsgDetailVo.getId());
		labMsgMainVo.setReceiverIds("");
		labMsgMainVo.setReceiverNames("");
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labMsgMainVo.getDemo1(), "msg");
		setAttribute("loadList", loadList);
			labMsgMainVo.setUuid(labMsgMainVo.getDemo1());
		return PREADD;
	}
	public String updateLabMsg() throws GlobalException {
		if (null == labMsgMainVo) {
			labMsgMainVo = new LabMsgMainVo();
		}
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		labMsgMainService.updateLabMsgMain(labMsgMainVo);
		return UPDATE;
	}
	//删除  发件箱  
	public String deleteLabMsg4sender() throws GlobalException {
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		labMsgMainService.updateLabMsgMain2Laji(labMsgMainVo);
		return DELETE;  
	}
	//删除  收件箱
	public String deleteLabMsg() throws GlobalException {
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
			labMsgDetailVo.setReceiveId(getSessionContainer().getUserId());
			labMsgDetailVo.setIds(labMsgMainVo.getIds());
		}
		labMsgDetailService.deleteLabMsgDetail(labMsgDetailVo);
		return DELETE;  
	}
	//删除  草稿箱
	public String deleteLabMsg4CaoGao() throws GlobalException {
		labMsgMainVo.setSenderId(getSessionContainer().getUserId());
		labMsgMainService.deleteLabMsgMain4CaoGao(labMsgMainVo);
		return DELETE;  
	}
	//删除  垃圾箱
	public String deleteLabMsg4Real() throws GlobalException {
		labMsgDetailVo.setSenderId(getSessionContainer().getUserId());
		labMsgDetailService.deleteLabMsgDetail4Real(labMsgDetailVo);
		return DELETE;  
	}
	//彻底删除 发件箱
	public String realDeleteLabMsgSend() throws GlobalException {
		labMsgMainService.deleteLabMsgMain(labMsgMainVo);
		return DELETE;
	}
	//彻底删除 收件箱
	public String realDeleteLabMsgReceive() throws GlobalException {
		labMsgDetailService.deleteLabMsgDetail(labMsgMainVo);
		return DELETE;
	}
	//恢复到发件箱
	public String updateLabMsg2Send() throws GlobalException {
		if (null == labMsgDetailVo) {
			labMsgDetailVo = new LabMsgDetailVo();
		}
		labMsgDetailService.updateLabMsgDetail4IsDelete(labMsgDetailVo);
		return UPDATE;
	}

	public String showLabUser4Select() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		List<LabUserVo> userList = labUserService.getLabUserList(labUserVo);
		setAttribute("userList", userList);
		return LIST;
	}
	public LabMsgMainVo getLabMsgMainVo() {
		return labMsgMainVo;
	}

	public void setLabMsgMainVo(LabMsgMainVo labMsgMainVo) {
		this.labMsgMainVo = labMsgMainVo;
	}

	public LabMsgDetailVo getLabMsgDetailVo() {
		return labMsgDetailVo;
	}

	public void setLabMsgDetailVo(LabMsgDetailVo labMsgDetailVo) {
		this.labMsgDetailVo = labMsgDetailVo;
	}
	public LabUserVo getLabUserVo() {
		return labUserVo;
	}
	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
