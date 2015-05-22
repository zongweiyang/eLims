package cn.labsoft.labos.framework.common.taglib;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;

import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.FileUtils;
import cn.labsoft.labos.utils.StrUtils;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 导出
 * <strong>Title : Tag </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 10:36:55 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class Export extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private static final String EXCEL_TEMP = "export/temp"; //导出临时数据
	private String uri;//数据来源
	private String source;//模板路径
	private String target;//导出路径
	private String params;//数据
	private String fileName;//导出数据文件名称
	private boolean reload;//是否重新加载数据
	

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		AComponent jhref = (AComponent) component;
		String exportPath = getExportPath();
		if(null==fileName)
			fileName = target;
		onclick = BuildJs.jsDown(fileName, exportPath);
		img = "/img/export.gif";
		uri = "export";
		A labTag = new A( id,  type,  uri,  value, title,  
			disabled,   onclick,  img,  cssClass, 
			cssStyle, key,  tooltips,  alt, null,null,html);
		labTag.setValue(jhref.getText(value));
		jhref.setLabTag(labTag);
	}
	
	public Export(){
		
	}

	private String getExportPath() {
		Map<String, Object> beansMaps = new HashMap<String, Object>();
		beansMaps.put("title", StrUtils.split(target, ".")[0]);
		if(null!=params&&params.length()>0){
			if("CSV".equalsIgnoreCase(type)){
				beansMaps.put("data", findValue(params));
				beansMaps.put("property",property);
			}else{
				for(String param : StrUtils.split(params, ',')){
					param = param.trim();
					beansMaps.put(param, findValue(param));
				}
			}
		}
		String realPath = ServletActionContext.getRequest().getRealPath("/");
		String thisDate = DateUtils.getCurrDateStr();
		String exportPath = EXCEL_TEMP+"/"+thisDate+"/"+SessionContainer.getSessionContainer().getUserId()+"/"+type+"/";
		delFiles(realPath, thisDate); 
		exportPath +=target;
		ExportUtil export = new ExportUtil(); 
		export.setBeansMaps(beansMaps);
		export.setTarget((realPath+exportPath).replace("/", File.separator));
		export.setType(type);
		export.setSource((realPath+source).replace("/", File.separator));
		
		Thread exportThread = new Thread(export);
		exportThread.run();
		return exportPath;
	}
	
	private void delFiles(String realPath, String thisDate) {
		try {
			File f = new File(realPath + EXCEL_TEMP);
			for(File file:f.listFiles()){
				if(!thisDate.equals(file.getName()))
					FileUtils.delAllFile(file.getPath());
			}
			
		} catch (Exception e) {
			Log4J.info("删除临时文件出错", e);
		}
	}
	
	public Export(String id, String type, String value,
			String title, boolean disabled, 
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String source,String target,boolean reload,String params,String property) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
		this.title = title;
		this.disabled = disabled;
		this.onclick = onclick;
		this.img = img;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.key = key;
		this.tooltips = tooltips;
		this.alt = alt;
		this.source = source;
		this.target = target;
		this.reload = reload;
		this.params = params;
		this.property = property;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}