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
public class LabOrgAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabOrgService labOrgService;
	private ILabCodeService labCodeService;
	private ILabUploadService labUploadService;
	private ILabConfigService labConfigService;
	private List<LabOrgVo> listLabOrgVo;
	private File upload;// 实际上传文件
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private LabOrgVo labOrgVo;

	public void initialise() {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
	}

	public String frameLabOrg() {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		return FRAME;
	}

	public String preTreeLabOrg() {
		return PRETREE;
	}

	public void treeLabOrg() throws GlobalException {
		StringBuffer stirngBuffer = new StringBuffer();
		stirngBuffer = labOrgService.getZtreeByPId(getParameter("treeNid"));
		outPrint(getResponse(), stirngBuffer);
	}

	public String listLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		pageResult = labOrgService.getLabOrgPR(labOrgVo, pageResult);
		LabConfigVo config = labConfigService.getLabConfigByCode("nodeNum");
		int num = 0;
		try {
			num = Integer.valueOf(config.getValue());
		} catch (NumberFormatException e) {
			num = 10;
		}
		if (pageResult.getPageBean().getTotalRows() >= num) {
			labOrgVo.setIsOper("N");
		}
		return LIST;
	}

	public String showLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		try {
			labOrgVo = labOrgService.getLabOrg(labOrgVo.getId());
			List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode(LabOrg.LABORG_KIND);
			getRequest().setAttribute("listLabCode", listLabCode);
		} catch (Exception e) {
			Log4J.error(" showLabOrg查询组织报错", e);
			throw new GlobalException("" + e.getMessage());
		}
		return SHOW;
	}

	public String preAddLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		labOrgVo.setSort(labOrgService.getMaxSort(labOrgVo) + 1);
		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode(LabOrg.LABORG_KIND);
		getRequest().setAttribute("listLabCode", listLabCode);
//		TestWordStone.show();
		return PREADD;
	}

	public String addLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		try {
			labOrgVo = labOrgService.addLabOrg(labOrgVo);
		} catch (Exception e) {
			Log4J.error(" addLabOrg添加组织报错", e);
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String preUpdateLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		labOrgVo = labOrgService.getLabOrg(labOrgVo.getId());
		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode(LabOrg.LABORG_KIND);
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREUPDATE;
	}

	public String updateLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		try {
			boolean key = labOrgService.updateLabOrg(labOrgVo);
		} catch (Exception e) {
			Log4J.error(" updateLabOrg修改组织报错", e);
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String deleteLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		boolean key = labOrgService.updateLabOrg4Del(new String[] { labOrgVo.getId() });
		return DELETE;
	}

	public String deleteBatchLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		boolean key = labOrgService.updateLabOrg4Del(labOrgVo.getIds());
		return DELETEBATCH;
	}

	public void isRequiredName() throws GlobalException, IOException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		String flag = labOrgService.testLabOrgName(labOrgVo);
		outPutString(flag);
	}

	public String preRadioTreeLabOrg() {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		return PRETREE;
	}

	public void treeRadioLabOrg() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		StringBuffer stirngBuffer = new StringBuffer();
		stirngBuffer = labOrgService.getLabOrgContentsZtree(labOrgVo.getParentId(), labOrgVo.getId());
		outPrint(getResponse(), stirngBuffer);
	}

	/**
	 * 迁移组织
	 * 
	 * @Title:
	 * @Description: TODO
	 * @param
	 * @return
	 * @return 返回类型
	 * @throws GlobalException 
	 * @throws
	 */
	public String updateLabOrgMove() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		try {
			boolean key = labOrgService.updateLabOrgPid(labOrgVo);
		} catch (Exception e) {
			Log4J.error(" updateLabOrgMove,迁移组织报错", e);
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ILabOrgService getlabOrgService() {
		return labOrgService;
	}

	public void setlabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	public List<LabOrgVo> getListLabOrgVo() {
		return listLabOrgVo;
	}

	public void setListLabOrgVo(List<LabOrgVo> listLabOrgVo) {
		this.listLabOrgVo = listLabOrgVo;
	}

	public LabOrgVo getLabOrgVo() {
		return labOrgVo;
	}

	public void setLabOrgVo(LabOrgVo labOrgVo) {
		this.labOrgVo = labOrgVo;
	}

	public String getLabUnit() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		labOrgVo = labOrgService.getLabOrg("0");
		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode("DWLX");
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREUPDATE;
	}

	public String updateLabUnit() throws GlobalException {
		if (null == labOrgVo)
			labOrgVo = new LabOrgVo();
		try {
			boolean key = labOrgService.updateLabOrg(labOrgVo);
		} catch (Exception e) {
			Log4J.error(" updateLabOrg修改组织报错", e);
			throw new GlobalException("" + e.getMessage());
		}
		List<LabCodeVo> listLabCode = labCodeService.getLabCodeByTypeCode("DWLX");
		getRequest().setAttribute("listLabCode", listLabCode);
		List<LabUploadVo> labUploadVoList = labUploadService.getLabUploadList(labOrgVo.getId(), "logo");
		if (null != labUploadVoList && labUploadVoList.size() > 0) {
			labOrgVo.setLogo(labUploadVoList.get(0).getVname());
			labOrgService.updateLabOrg(labOrgVo);
		}
		setAttribute("loadList", labUploadVoList);
		return UPDATE;
	}

	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}

	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
}