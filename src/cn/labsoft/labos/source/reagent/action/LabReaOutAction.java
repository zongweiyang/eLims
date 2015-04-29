package cn.labsoft.labos.source.reagent.action;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.source.reagent.service.ILabReaOutMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaOutDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabReaOutAction extends BaseAction {

	private LabReaOutMainVo labReaOutMainVo;
	private ILabReaOutMainService labReaOutMainService;
	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private String reagentIds;
	private ILabConfigService labConfigService;
	private String fileName;
	private InputStream excelStream;

	@Resource
	public void setLabReaOutMainService(ILabReaOutMainService labReaOutMainService) {
		this.labReaOutMainService = labReaOutMainService;
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

	public String getReagentIds() {
		return reagentIds;
	}

	public void setReagentIds(String reagentIds) {
		this.reagentIds = reagentIds;
	}

	public LabReaOutMainVo getLabReaOutMainVo() {
		return labReaOutMainVo;
	}

	public void setLabReaOutMainVo(LabReaOutMainVo labReaOutMainVo) {
		this.labReaOutMainVo = labReaOutMainVo;
	}

	private void initBean() {
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
	}

	public String listLabReaOutMain() throws GlobalException {
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
		pageResult = labReaOutMainService.getLabReaOutMainPR(labReaOutMainVo,
				pageResult);
		return LIST;
	}

	public String preAddLabReaOutMain() throws GlobalException {
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
		labReaOutMainVo.setOutstoreDate(DateUtils.format(new Date(),
				"yyyy-MM-dd"));
		labReaOutMainVo.setOutstorer(getSessionContainer().getUserName());
		String receiptno = labReaOutMainService.getMaxLabReaOutMainReceiptno();
		labReaOutMainVo.setReceiptno(receiptno);
		List<LabReaOutDetailVo> labReaOutDetailVoList = labReaOutMainService.getLabReaOutDetailListByReagentId(reagentIds);
		labReaOutMainVo.setLabReaOutDetailVoList(labReaOutDetailVoList);
		return PREADD;
	}

	public String addLabReaOutMain() throws GlobalException {
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
		labReaOutMainService.addLabReaOutMain(labReaOutMainVo);
		return ADD;
	}

	public String showLabReaOutMain() throws GlobalException {
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
		labReaOutMainVo = labReaOutMainService
				.getLabReaOutMainById(labReaOutMainVo.getId());
		return SHOW;
	}

	public String exportLabReaOutMain() throws GlobalException {
		if (null == labReaOutMainVo)
			labReaOutMainVo = new LabReaOutMainVo();

		labReaOutMainVo = labReaOutMainService
				.getLabReaOutMainById(labReaOutMainVo.getId());
		String path = getRequest().getParameter("path");
		Map<String, LabReaOutMainVo> beans = new HashMap<String, LabReaOutMainVo>();
		beans.put("data", labReaOutMainVo);

		try {
			fileName = new String(("试剂出库信息目录").getBytes(), "ISO8859_1")
					+ ".xls";
		} catch (UnsupportedEncodingException e) {
			log.error("格式转换错误" + e.getMessage());
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
