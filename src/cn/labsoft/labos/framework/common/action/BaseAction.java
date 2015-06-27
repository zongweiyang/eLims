package cn.labsoft.labos.framework.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import cn.labsoft.labos.framework.common.error.form.ErrorForm;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.i18n.annotation.Translator;
import cn.labsoft.labos.i18n.annotation.TranslatorType;
import cn.labsoft.labos.i18n.util.TranslateUtil;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.coreextend.action.Theme;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <strong>Title : BaseAction </strong>. <br>
 * <strong>Description :系统中所有Action的父类,继承ActionSupport 并且 加入通用的分页以及错误处理对象
 * </strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware{
	public Map<String, Object> session;
	public static final String ENCODING="UTF-8";
	public   ExecutorService pool = Executors. newSingleThreadExecutor();
	protected ErrorForm errorForm = new ErrorForm();
	protected PageResult pageResult=getPageResult();
	protected Integer pageSizex;//所有页面的分页
	protected Integer currenPagex;//所有页面的分页
	protected String messageInfo;
	protected String funId;
	protected String themeType;
	protected String id; //Entity's PK
	protected String[] ids;//Some Entitys PK
	protected String formName;
	protected String fromPage;
	protected String treeNid;
	protected String sessionName;//获取前台传过来的Session名称
	protected boolean executeStatus = false;//执行状态
	public static final String TREE = "tree";
	public static final String PRETREE = "preTree";
	public static final String LIST = "list";
	public static final String ADD = "add";
	public static final String COPY = "copy";
	public static final String PREADD = "preAdd";
	public static final String UPDATE = "update";
	public static final String PREUPDATE = "preUpdate";
	public static final String DELETE = "delete";
	public static final String DELETEBATCH = "deleteBatch";
	public static final String SHOW = "show";
	public static final String FRAME = "frame";
	public static final String PRECREATE = "preCreate";
	public static final String PREIMPORT = "preImport";
	public static final String IMPORT = "import";
	public static final String PREEXPORT = "preExport";
	public static final String EXPORT = "export";
	public static final String INPUT = "input";
	public static final String SAVE = "save";
	public static final String PRESAVE = "preSave";
	public static final String UPDATE2DEL = "update2del";
	
	public static final String ADDSUCCESS = "添加成功";
	public static final String ADDFAIL = "添加失败";
	public static final String UPDATESUCCESS = "修改成功";
	public static final String UPDATEFAIL = "修改失败";
	public static final String DELETESUCCESS = "删除成功";
	public static final String DELETEFAIL = "删除失败";
	
	/**
	 * 获取session
	 * @Title:  
	 * @Description: TODO
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	protected SessionContainer getSessionContainer() {
		SessionContainer sessionContainer = new SessionContainer();
		try{
			sessionContainer = (SessionContainer) session.get(SessionContainer.Session_Container);
		}catch(Exception e){
			
		}
		return sessionContainer;
	}
	
	protected String getParameter(String name){
		return getRequest().getParameter(name);
	}
	
	protected String[] getParameterValues(String name){
		return getRequest().getParameterValues(name);
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String,String[]> getParametMap(){
		return getRequest().getParameterMap();
	}
	
	protected void setAttribute(String name,Object obj){
		obj = filterTranslator(obj);
		getRequest().setAttribute(name,obj);
	}
	
	protected void setEncoding(String encoding) throws UnsupportedEncodingException{
		if(StrUtils.isBlank(encoding))
			getRequest().setCharacterEncoding(ENCODING);
		else
			getRequest().setCharacterEncoding(encoding);
	}
	
	
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	protected PrintWriter getWriter() throws IOException{
		return getResponse().getWriter();
	}
	/**
	 * 输出JSON基本对象
	 * @Title:  
	 * @Description: TODO
	 * @param @param str   输出基本对象
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	protected void ajax(String str) throws GlobalException {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding(ENCODING);
			response.setContentType("application/text");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			Log4J.error("BaseAction..."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}
	/**
	 * 输出JSON对象
	 * @Title:  
	 * @Description: TODO
	 * @param @param object  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	protected void ajax(Object object) throws GlobalException {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.println(JSONArray.fromObject(object).toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			Log4J.error("BaseAction..."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}
	/**
	 * 输出JSON模式的LIST
	 * @Title:  
	 * @Description: TODO
	 * @param @param list  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	protected void ajax(List list) throws GlobalException {
		StringBuffer jSONResult = new StringBuffer();
		if(null!=list&&list.size()>0){
			try {
				jSONResult.append(JSONArray.fromObject(list));
			} catch (Exception e) {
				Log4J.error("BaseAction..."+e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}else{
			jSONResult.append("{}");
		}
		ajax(jSONResult.toString());
	}
	protected void outPutString(Object[] obj) throws IOException{
		HttpServletResponse response=getResponse();
		response.setCharacterEncoding(ENCODING);
		PrintWriter out=response.getWriter();
		StringBuffer buffer=new StringBuffer();
		int k=0;
		for(Object o:obj){
			if(k==obj.length-1){
				buffer.append(o);
			}else
				buffer.append(o+",");
			k++;
		}
		out.write(buffer.toString());
	}
	
	protected void outPutString(String outOutStr) throws IOException{
		HttpServletResponse response=getResponse();
		response.setCharacterEncoding(ENCODING);
		PrintWriter out=response.getWriter();
		out.write(outOutStr);
	}
	
	@SuppressWarnings("unchecked")
	protected String getParameterNames()   {
        Enumeration en =getRequest().getParameterNames();
        String re = "";
        String parameterName =  null;
        String parameterValue = null;
        while(en.hasMoreElements()){
        	parameterName = (String)en.nextElement();
                if (parameterName != null)   {
                        re+= "&" + parameterName + "=";
                        parameterValue= getParameter(parameterName);
                        if(parameterValue !=  null)
                           re += parameterValue;
                }
        }
       return re;
	}

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}
	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public ErrorForm getErrorForm() {
		return errorForm;
	}

	public void setErrorForm(ErrorForm errorForm) {
		this.errorForm = errorForm;
	}
	public PageResult getPageResult() {
		if(pageResult==null)pageResult=new PageResult();
		
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		if(pageResult==null)pageResult=new PageResult();
		this.pageResult = pageResult;
	}
	public String getImg() {
		return SUCCESS;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public String getThemeType() {
		try {
			String tempThemeType=SessionContainer.getUserProperties("themeType");
			if(null!=tempThemeType) 
				themeType = tempThemeType;
		} catch (Exception e) {
			
		}
		return themeType;
	}

	public void setThemeType(String themeType) {
		
		this.themeType = themeType;
	}

	//输出
	protected static boolean outPrint(HttpServletResponse response,
			StringBuffer sbf) throws GlobalException {
		if (sbf == null)
			return false;
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(sbf.toString());
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public String getTreeNid() {
		return treeNid;
	}
	public void setTreeNid(String treeNid) {
		this.treeNid = treeNid;
	}
	public Integer getPageSizex() {
		pageSizex=pageResult.getPageSize();
		return pageSizex;
	}
	public void setPageSizex(Integer pageSizex) {
		this.pageSizex = pageSizex;
	}

	public Integer getCurrenPagex() throws GlobalException {
		try {
			currenPagex=Integer.valueOf(pageResult.getCurrentPage());
		} catch (NumberFormatException e) {
			currenPagex=1;
			throw new GlobalException("" + e.getMessage());
		}
		return currenPagex;
	}

	public void setCurrenPagex(Integer currenPagex) {
		this.currenPagex = currenPagex;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
	public Object filterTranslator(Object obj){
		Locale locale = (Locale)getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
		if(locale!=null && locale.equals(Locale.US)){
			if(obj instanceof List){
				List list = (List) obj;
				for(int i=0;i<list.size();i++){
					Object value = list.get(i);
					if(value instanceof BaseVo){
						value = filter(value);
						list.set(i, value);
					}
				}
			}else if(obj instanceof BaseVo){
				obj = filter(obj);
			}
		}
		return obj;
	}
	private Object filter(Object entity){
		Field[] fields = entity.getClass().getDeclaredFields();
		try {
			for (Field f : fields) {
				Translator translator = f.getAnnotation(Translator.class);
				if(translator != null ){
					f.setAccessible(true);
					String value = String.valueOf(f.get(entity));
					value = TranslateUtil.get(value);
					if(value !=null) f.set(entity, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
