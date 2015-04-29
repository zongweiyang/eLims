package cn.labsoft.labos.framework.common.taglib;

import cn.labsoft.labos.framework.common.interceptor.Action;

/**
 * 
 * <strong>Title : BuildHtml </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 1:12:28 PM  </strong>. <br>
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
public class BuildHtml {
	private static final String SIGN = "\"";
	
	public static String start(){
		return "<";
	}
	public static String close(){
		return "</";
	}
	
	public static String endClose(){
		return "/>";
	}
	public static String end(){
		return ">";
	}
	
	public static String id(String id){
		return attr("id",id);
	}
	
	public static String value(String type ,String value){
		if(null!=type&&!"".equals(type.trim())){
			return start()+type+end()+value(value)+close()+type+end();
		}else{
			return value;
		}
	}
	
	public static String value(String value){
		return value;
	}
	
	public static String title(String title){
		return attr("title",title);
	}
	
	public static String html(String html){
		return html;
	}
	public static String disabled(boolean disabled){
		if(disabled){
			return attr("disabled",disabled+"");
		}else{
			return "";
		}
	}
	public static String jsFunction(String event,String function){
		return attr(event,function);
		//return event+"="+appendSign(function);
	}
	public static String onclick(String onclick,String uri){
			String action=uri;
			if(uri.indexOf("/")!=-1){
				action=uri.substring(uri.lastIndexOf("/")+1);
			}
			if(null!=onclick&&!"".equals(onclick.trim())){
				
			}else if(Action.isFormAction(action)){
				onclick = BuildJs.jsPost(uri);
			}else if(Action.isListPage(action)){
				onclick = BuildJs.jsSubmitAction();
			}else{
				onclick = BuildJs.jsGet(uri);
			}
		return  attr("onclick", onclick+BuildJs.jsReturn(onclick));
	}
	public static String img(String img,String alt){
		if(null==img) return "";
		StringBuffer imgHtml = new StringBuffer(start());
		imgHtml.append("img");
		if(null!=img&&!"".equals(img.trim())){
			imgHtml.append(" src="+SIGN+img+SIGN);
		}
		imgHtml.append(attr("alt",alt));
		imgHtml.append(endClose());
		return imgHtml.toString();
	}
	public static String cssClass(String cssClass){
		return attr("class",cssClass);
	}
	public static String cssStyle(String cssStyle){
		return attr("style",cssStyle);
	}
	public static String key(String key){
		return attr("key",key);
	}
	public static String tooltips(String tooltips){
		return attr("tooltips",tooltips);
	}
	public static String target(String target){
		return attr("target",target);
	}
	public static String href(String href){
		/*if(null!=href&&!"".equals(href.trim())){
			href = "javascript:;return false;";
		}*/
		if(null!=href&&"#".equals(href.trim())){
			href = "javascript:;"+BuildJs.jsReturn();
		}
		return attr("href",href);
	}
	
	public static String attr(String attr,String attrValue){
		String html = "";
		if(null!=attr&&!"".equals(attr.trim())){
			if(null!=attrValue&&!"".equals(attrValue.trim())){
				html += " ";
				html += attr +"="+appendSign(attrValue);
			}
		}
		return html;
	}
	public static String appendSign(String val){
		return SIGN+val+SIGN;
	}
}
