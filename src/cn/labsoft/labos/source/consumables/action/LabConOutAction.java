package cn.labsoft.labos.source.consumables.action;

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
import cn.labsoft.labos.source.consumables.service.ILabConOutMainService;
import cn.labsoft.labos.source.consumables.vo.LabConOutDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabConOutAction extends BaseAction {

	private LabConOutMainVo labConOutMainVo;
	private ILabConOutMainService labConOutMainService;
	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private String congentIds;
	private ILabConfigService labConfigService;
	private String fileName;
	private InputStream excelStream;

	@Resource
	public void setLabConOutMainService(ILabConOutMainService labConOutMainService) {
		this.labConOutMainService = labConOutMainService;
	}

	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public String getCongentIds() {
		return congentIds;
	}

	public void setCongentIds(String congentIds) {
		this.congentIds = congentIds;
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

	public String getConsumablesIds() {
		return congentIds;
	}

	public void setConsumablesIds(String congentIds) {
		this.congentIds = congentIds;
	}

	public LabConOutMainVo getLabConOutMainVo() {
		return labConOutMainVo;
	}

	public void setLabConOutMainVo(LabConOutMainVo labConOutMainVo) {
		this.labConOutMainVo = labConOutMainVo;
	}

	private void initBean() {
		if (null == labConOutMainVo) {
			labConOutMainVo = new LabConOutMainVo();
		}
	}

	public String listLabConOutMain() throws GlobalException {
		if (null == labConOutMainVo) {
			labConOutMainVo = new LabConOutMainVo();
		}
		pageResult = labConOutMainService.getLabConOutMainPR(labConOutMainVo,
				pageResult);
		return LIST;
	}

	public String preAddLabConOutMain() throws GlobalException {
		if (null == labConOutMainVo) {
			labConOutMainVo = new LabConOutMainVo();
		}
		labConOutMainVo.setOutstoreDate(DateUtils.format(new Date(),
				"yyyy-MM-dd"));
		labConOutMainVo.setOutstorer(getSessionContainer().getUserName());
		String receiptno = labConOutMainService.getMaxLabConOutMainReceiptno();
		labConOutMainVo.setReceiptno(receiptno);
			List<LabConOutDetailVo> labConOutDetailVoList = labConOutMainService.getLabConOutDetailListByConsumablesId(congentIds);
			labConOutMainVo.setLabConOutDetailVoList(labConOutDetailVoList);
		return PREADD;
	}

	public String addLabConOutMain() throws GlobalException {
		if (null == labConOutMainVo) {
			labConOutMainVo = new LabConOutMainVo();
		}
		labConOutMainService.addLabConOutMain(labConOutMainVo);
		return ADD;
	}

	public String showLabConOutMain() throws GlobalException {
		if (null == labConOutMainVo) {
			labConOutMainVo = new LabConOutMainVo();
		}
		labConOutMainVo = labConOutMainService
				.getLabConOutMainById(labConOutMainVo.getId());
		return SHOW;
	}

	@SuppressWarnings("deprecation")
	public String exportLabConOutMain() throws GlobalException {
		if (null == labConOutMainVo)
			labConOutMainVo = new LabConOutMainVo();

		labConOutMainVo = labConOutMainService
				.getLabConOutMainById(labConOutMainVo.getId());
		String path = getRequest().getParameter("path");
		Map<String, LabConOutMainVo> beans = new HashMap<String, LabConOutMainVo>();
		beans.put("data", labConOutMainVo);

		try {
			fileName = new String(("耗材出库信息目录").getBytes(), "ISO8859_1")
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
