package cn.labsoft.labos.base.org.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.FileInfoVo;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

/**
 * 
 * <strong>Title : SysOrgAction </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Jan 28, 2014 5:15:54 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 * 
 * @author Stone Tang <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Controller
@Scope("prototype")
public class FileUploadAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private FileInfoVo fileInfoVo;
	private String redirectURL;
	
	public String upload(){
		if(fileInfoVo == null){
			fileInfoVo = new FileInfoVo();
		}
		
		this.redirectURL = "/utils/upload/labUpLoad.jsp?busId="+fileInfoVo.getBusId()+
				"&fileTypes="+ fileInfoVo.getFileTypes() +
				"&busType="+ fileInfoVo.getBusType()+"&showType="+fileInfoVo.getShowType();
		getRequest().setAttribute("busId", fileInfoVo.getBusId());
		getRequest().setAttribute("fileTypes", fileInfoVo.getFileTypes());
		getRequest().setAttribute("busType", fileInfoVo.getBusType());
		getRequest().setAttribute("showType",fileInfoVo.getShowType());
		
		return "tojsp";
	}
	
	public FileInfoVo getFileInfoVo() {
		return fileInfoVo;
	}
	public void setFileInfoVo(FileInfoVo fileInfoVo) {
		this.fileInfoVo = fileInfoVo;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
}