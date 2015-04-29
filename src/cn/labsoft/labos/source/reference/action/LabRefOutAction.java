package cn.labsoft.labos.source.reference.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.service.ILabRefOutMainService;
import cn.labsoft.labos.source.reference.vo.LabRefOutDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;
import cn.labsoft.labos.utils.DateUtils;

@Controller
@Scope("prototype")
public class LabRefOutAction extends BaseAction {

	
	private ILabRefOutMainService labRefOutMainService;
	private ILabConfigService labConfigService;
	
	private LabRefOutMainVo labRefOutMainVo;
	private String referenceIds;
	private String fileName;
	private InputStream excelStream;

	@Resource
	public void setLabRefOutMainService(ILabRefOutMainService labRefOutMainService) {
		this.labRefOutMainService = labRefOutMainService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getReferenceIds() {
		return referenceIds;
	}

	public void setReferenceIds(String referenceIds) {
		this.referenceIds = referenceIds;
	}

	public LabRefOutMainVo getLabRefOutMainVo() {
		return labRefOutMainVo;
	}

	public void setLabRefOutMainVo(LabRefOutMainVo labRefOutMainVo) {
		this.labRefOutMainVo = labRefOutMainVo;
	}

	private void initBean() {
		if (null == labRefOutMainVo) {
			labRefOutMainVo = new LabRefOutMainVo();
		}
	}

	public String listLabRefOutMain() throws GlobalException {
		if (null == labRefOutMainVo) {
			labRefOutMainVo = new LabRefOutMainVo();
		}
		pageResult = labRefOutMainService.getLabRefOutMainPR(labRefOutMainVo,
				pageResult);
		return LIST;
	}

	public String preAddLabRefOutMain() throws GlobalException {
		if (null == labRefOutMainVo) {
			labRefOutMainVo = new LabRefOutMainVo();
		}
		labRefOutMainVo.setOutstoreDate(DateUtils.format(new Date(),
				"yyyy-MM-dd"));
		labRefOutMainVo.setOutstorer(getSessionContainer().getUserName());
		String receiptno = labRefOutMainService.getMaxLabRefOutMainReceiptno();
		labRefOutMainVo.setReceiptno(receiptno);
			List<LabRefOutDetailVo> labRefOutDetailVoList = labRefOutMainService.getLabRefOutDetailListByReferenceId(referenceIds);
			labRefOutMainVo.setLabRefOutDetailVoList(labRefOutDetailVoList);
		return PREADD;
	}

	public String addLabRefOutMain() throws GlobalException {
		if (null == labRefOutMainVo) {
			labRefOutMainVo = new LabRefOutMainVo();
		}
		labRefOutMainService.addLabRefOutMain(labRefOutMainVo);
		return ADD;
	}

	public String showLabRefOutMain() throws GlobalException {
		if (null == labRefOutMainVo) {
			labRefOutMainVo = new LabRefOutMainVo();
		}
		labRefOutMainVo = labRefOutMainService
				.getLabRefOutMainById(labRefOutMainVo.getId());
		return SHOW;
	}

	public String exportLabRefOutMain() throws GlobalException {
		if (null == labRefOutMainVo)
			labRefOutMainVo = new LabRefOutMainVo();

		labRefOutMainVo = labRefOutMainService
				.getLabRefOutMainById(labRefOutMainVo.getId());
		String path = getRequest().getParameter("path");
		Map<String, LabRefOutMainVo> beans = new HashMap<String, LabRefOutMainVo>();
		beans.put("data", labRefOutMainVo);

		try {
			fileName = new String(("标准品出库信息目录").getBytes(), "ISO8859_1")
					+ ".xls";
		} catch (UnsupportedEncodingException e) {
			Log4J.error("格式转换错误" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		String templateFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ path.replace("\\", File.separator);
		String destFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ "templates"
				+ File.separator
				+ "temp"
				+ File.separator
				+ System.currentTimeMillis() + "." + "xls";
		String realPath = ServletActionContext.getRequest().getRealPath("/")
				+ "templates" + File.separator + "temp" + File.separator;
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFileName, beans, destFileName);
			File targetFile = new File(destFileName);
			excelStream = new BufferedInputStream(new FileInputStream(
					targetFile), 16 * 1024);
			path = targetFile.getPath();
			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return EXPORT;
	}
}
