package cn.labsoft.labos.utils;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/** 带遮罩的网页对话框自定义标签类 */
public class MsgDialogTag extends SimpleTagSupport {

	String title = "温馨提示"; //对话框标题文字
	String height="100%";	//遮罩的高度,默认为屏幕的高度,即100%
	String top="100px";		//对话框的顶部距离,默认为100px
	String boxwidth="500px";//对话框的宽度,默认为500px
	String basepath =""; 	//基本URL
	String tmpid = null;	//临时ID后缀,避免ID冲突,默认为系统时间的毫秒数
	
	/** 标签体处理 */
    @Override
	public void doTag() throws JspException, IOException{
    	//规范属性值
    	if (!height.endsWith("%"))height=height+"px";
    	if (!top.endsWith("px"))top=top+"px";
    	if (!boxwidth.endsWith("px"))boxwidth=boxwidth+"px";
    	int titlewidth = Integer.valueOf(boxwidth.replaceAll("px", "")).intValue()-42;
    	if (tmpid==null)tmpid = String.valueOf(System.currentTimeMillis());//临时ID后缀,避免ID冲突
    	
    	//取得现有标签体的内容
    	JspFragment body = this.getJspBody();
    	StringWriter writer = new StringWriter();
    	//StringBuffer buff = writer.getBuffer();
    	body.invoke(writer);

    	//构造带遮罩的网页对话框
    	StringBuffer sb = new StringBuffer();
    	sb.append("<style>\n");
    	sb.append("#msgbox"+tmpid+"{width:"+boxwidth+";background: #D6D3CE; border:1px #848284 solid;padding:0px;margin:0 auto;}\n");
    	sb.append(".msgicon{float:left;background-image:url("+basepath+"/img/msgDialog/msgtitle_icon.jpg); background-repeat:no-repeat; height:20px;width:20px;}\n");
    	sb.append(".msgtilte{float:left; text-align:left;background-image:url("+basepath+"/img/msgDialog/msgtitle_back.jpg); background-repeat:repeat-x;line-height:20px;letter-spacing:2px; height:20px; width:"+titlewidth+"px;}\n");
    	sb.append(".msgclose{float:left;background-image:url("+basepath+"/img/msgDialog/btn_close.jpg); background-repeat:no-repeat; height:20px; width:20px; cursor:pointer;}\n");
    	sb.append(".msgmainbox{clear:both; border-top:1px #848284 solid;text-align:left;padding:20px;overflow: auto;}\n");
    	sb.append("</style>\n");
    	sb.append("<div id='mask"+tmpid+"' style='clear:both;top:0;left:0;position:absolute;z-index:999;filter:alpha(opacity=70);background:#000;opacity: 0.7;-moz-opacity: 0.7;height:"+height+";width:100%;'></div>\n");
    	sb.append("<div id='msgprompt"+tmpid+"' style='clear:both;top:"+top+";left:0;position: absolute; z-index: 1000; width:100%;text-align:center'>\n");
    	sb.append("<div id='msgbox"+tmpid+"'>\n");
    	sb.append("<div class='msgicon'></div>\n");
    	sb.append("<div class='msgtilte'>"+title+"</div>\n");
    	sb.append("<div class='msgclose' onClick='closemask"+tmpid+"()'></div>\n");
    	sb.append("<div class='msgmainbox'>\n");
    	//插入标签体中的提示信息内容
    	sb.append(writer.toString().trim()+"\n");
    	sb.append("</div>\n");
    	sb.append("</div>\n");
    	sb.append("</div>\n");
    	sb.append("<script language='javascript'>\n");
    	sb.append("function closemask"+tmpid+"(){\n");
    	sb.append("document.getElementById('mask"+tmpid+"').style.display='none';\n");    	
    	sb.append("document.getElementById('msgprompt"+tmpid+"').style.display='none';\n");
    	sb.append("}\n");
    	sb.append("</script>\n");
    	sb.append("</div>\n");
    	//输出处理结果到页面上
    	getJspContext().getOut().println(sb);    	
    }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBoxwidth() {
		return boxwidth;
	}

	public void setBoxwidth(String boxwidth) {
		this.boxwidth = boxwidth;
	}

	public String getTmpid() {
		return tmpid;
	}

	public void setTmpid(String tmpid) {
		this.tmpid = tmpid;
	}
}
