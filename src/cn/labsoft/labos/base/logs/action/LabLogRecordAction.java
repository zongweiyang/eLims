package cn.labsoft.labos.base.logs.action;

import java.io.InputStream;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.logs.service.ILabLogRecordService;
import cn.labsoft.labos.base.logs.vo.LabLogRecordVo;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 系统日志操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabLogRecordAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabLogRecordService labLogRecordService;
	private ILabTemplateService labTemplateService;

	private LabLogRecordVo labLogRecordVo;
	private String fileName;
	private InputStream excelStream;
	/**
	 * 系统日志Service注入
	 * @param labLogRecordService
	 */
	@Resource
	public void setLabLogRecordService(ILabLogRecordService labLogRecordService) {
		this.labLogRecordService = labLogRecordService;
	}
	/**
	 * 数据模版Service注入
	 * @param labTemplateService
	 */
	@Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	
	public LabLogRecordVo getLabLogRecordVo() {
		return labLogRecordVo;
	}
	public void setLabLogRecordVo(LabLogRecordVo labLogRecordVo) {
		this.labLogRecordVo = labLogRecordVo;
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
	/**
	 * 系统日志-列表页面
	 * @throws GlobalException
	 */
	public String listlabLogrecord() throws GlobalException {
		if (labLogRecordVo == null) {
			labLogRecordVo = new LabLogRecordVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("dateTime");
		}
		pageResult = labLogRecordService.getlabLogrecordPR(labLogRecordVo, pageResult);
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labLogRecordVo.setFilePath(path);
		return SUCCESS;
	}

//	@SuppressWarnings("deprecation")
//	public String exportLabLogrecordList() throws GlobalException {
//		if (null == labLogrecordVo)
//			labLogrecordVo = new LabLogRecordVo();
//
//		List<LabLogRecordVo> alertListForExcel = labLogRecordService.getLabLogrecordList(labLogrecordVo);
//		labLogrecordVo.setLabLogrecordVoList(alertListForExcel);
//		String path = getRequest().getParameter("path");
//		Map<String, LabLogRecordVo> beans = new HashMap<String, LabLogRecordVo>();
//		beans.put("data", labLogrecordVo);
//
//		try {
//			fileName = new String(("日志信息目录").getBytes(), "ISO8859_1") + ".xls";
//		} catch (UnsupportedEncodingException e) {
//			Log4J.error("格式转换错误" + e.getMessage());
//		}
//
//		String templateFileName = ServletActionContext.getRequest().getRealPath("/") + path.replace("\\", File.separator);
//		String destFileName = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator + System.currentTimeMillis() + "." + "xls";
//
//		String realPath = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator;
//		File file = new File(realPath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//
//		XLSTransformer transformer = new XLSTransformer();
//		try {
//			transformer.transformXLS(templateFileName, beans, destFileName);
//			File targetFile = new File(destFileName);
//			excelStream = new BufferedInputStream(new FileInputStream(targetFile), 16 * 1024);
//			path = targetFile.getPath();
//			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		return "export";
//	}

	//  public String updateSessionContainerToFile() {
	//    if (sessionContainerList == null) {
	//      sessionContainerList = new ArrayList();
	//    }
	//    SessionContainer sessionContainer = (SessionContainer)getCurrentRequest().getSession().getAttribute(SessionContainer.Session_Container);
	//    if (sessionContainer != null) {
	//      sessionContainer.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMddHHmmssS));
	//    }
	//    sessionContainerList.add(sessionContainer);
	//    return "none";
	//  }

	//  public String getCurrentOnLineUser() {
	//    sessionContainerList = new ArrayList();
	//    sendMessageToScriptSession(getCurrentRequest());
	//    try {
	//      Thread.sleep(3000L);
	//    }
	//    catch (InterruptedException e) {
	//      //e.printStackTrace();
	//    }
	//    return "list";
	//  }
	//
	

	//  public List<SessionContainer> getSessionContainerList() {
	//    return sessionContainerList;
	//  }

	//  public void setSessionContainerList(List<SessionContainer> sessionContainerList) {
	//    sessionContainerList = sessionContainerList;
	//  }

	//  private static void sendMessageToScriptSession(HttpServletRequest request) {
	//    ServletContext sc = request.getSession().getServletContext();
	//    ServerContext sctx = ServerContextFactory.get(sc);
	//    Collection pages = sctx.getScriptSessionsByPage(
	//      request.getContextPath() + 
	//      "/coreextend/extend/leftframe.action");
	//    Iterator it = pages.iterator();
	//    int count = 0;
	//    while (it.hasNext()) {
	//      ++count;
	//      ScriptSession ss = (ScriptSession)it.next();
	//      ss.addScript(updateCurrentUserInfo());
	//    }
	//  }
	//
	//  private static ScriptBuffer updateCurrentUserInfo()
	//  {
	//    ScriptBuffer sb = new ScriptBuffer();
	//    sb.appendScript("updateCurrentUserInfo()");
	//    return sb;
	//  }

}