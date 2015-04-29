package cn.labsoft.labos.framework.common.taglib;

import java.io.IOException;
import java.io.Writer;

import cn.labsoft.labos.base.function.vo.LabPowerVo;
import cn.labsoft.labos.framework.common.interceptor.Power;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 自定义标签
 * <strong>Title : LabComponent </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 11:14:35 AM  </strong>. <br>
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
public class AComponent extends BaseComponent {
	private A labATag;
	private final String BACK = "back";//返回
	private final String EXPORT = "export";//导出
	private final String IMPORT = "import";//导入
	private final String BUTTON = "button";//按钮
	private final String HTML = "html";//文本
	public void setLabTag(A g) {
		this.labATag = g;
	}
	public AComponent(ValueStack stack) {
		super(stack);
	}
	
	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		StringBuffer html = getHtml();
		try {
			writer.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	private StringBuffer getHtml() {
		StringBuffer html = new StringBuffer();
		if(HTML.equals(labATag.getUri().trim())){
			return new StringBuffer(labATag.getHtml());
		}
		
		if(null!=labATag.getUri()){
			boolean hasPower = false;
			String cssClass = "zPushBtn";
			String disabledClass = "zPushBtnDisabled";
			
			if(EXPORT.equals(labATag.getUri().trim())){
				hasPower = true;
			}else if(IMPORT.equals(labATag.getUri().trim())){
				hasPower = true;
			}else if(BUTTON.equals(labATag.getUri().trim())){
				hasPower = true;
			}else if(BACK.equalsIgnoreCase(labATag.getUri().trim())){
				labATag.setImg(basePath+"img/fanhui.gif");
				labATag.setOnclick(BuildJs.backList());
				hasPower = true;
			}else{
				LabPowerVo uriLabPowerVo = new Power().getPowerInfo(labATag.getUri());
				//是否有权限
				if(uriLabPowerVo.getHasPower()){
					if(null==labATag.getImg()||"".equals(labATag.getImg()))
						labATag.setImg(uriLabPowerVo.getImg());
					hasPower = true;
				}
			}
			
			if(hasPower){
				//if(!Theme.BTN_SHOW_IMG)labATag.setImg(null);
				if(labATag.getDisabled()){
					labATag.setCssClass(disabledClass);
					labATag.setTitle("不可操作");
				}else{
					labATag.setCssClass(cssClass);
				}
				html = build(html);
			}
		}
		return html;
	}
	private StringBuffer build(StringBuffer html) {
		boolean isHref = null != labATag.getHref();
		if (isHref) {
			if(labATag.getDisabled()){
				labATag.setCssClass("undo");
			}else{
				labATag.setCssClass(null);
			}
			labATag.setCssClass(null);
		}

		html.append(BuildHtml.start());
		html.append(A);
		html.append(BuildHtml.id(labATag.getId()));
		if (labATag.getDisabled()) {
			html.append(BuildHtml.cssClass(labATag.getCssClass()));
			html.append(BuildHtml.disabled(labATag.getDisabled()));
		} else {
			html.append(BuildHtml.onclick(labATag.getOnclick(),labATag.getUri()));
			html.append(BuildHtml.cssClass(labATag.getCssClass()));
		}
		html.append(BuildHtml.cssStyle(labATag.getCssStyle()));
		html.append(BuildHtml.key(labATag.getKey()));
		html.append(BuildHtml.title(labATag.getTitle()));
		html.append(BuildHtml.target(labATag.getTarget()));
		if (isHref) {
			html.append(BuildHtml.href(labATag.getHref()));
			html.append(BuildHtml.end());
			html.append(BuildHtml.value(labATag.getValue()));
		} else {
			html.append(BuildHtml.end());
			String img = labATag.getImg();
			if(null!=img){
				if(!img.startsWith(basePath)){
					if(img.startsWith("/")){
						img = img.substring(1);
					}
					img = basePath + img;
				}
			}
			html.append(BuildHtml.img(img, labATag.getAlt()));
			html.append(BuildHtml.value(B, labATag.getValue()));
		}
		html.append(BuildHtml.start());
		html.append(A);
		html.append(BuildHtml.end());
		return html;
	}
}
