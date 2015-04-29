package cn.labsoft.labos.framework.common.taglib;

import java.io.IOException;
import java.io.Writer;

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
public class ComboxComponent extends BaseComponent {
	private Combox labComboxTag;
	
	public void setLabComboxTag(Combox labComboxTag) {
		this.labComboxTag = labComboxTag;
	}
	
	public ComboxComponent(ValueStack stack) {
		super(stack);
	}
	
	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		StringBuffer html = build(new StringBuffer(""));
		try {
			writer.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private StringBuffer build(StringBuffer html) {
		html.append("<div class=\"box\">");
		html.append("<input");
		html.append(BuildHtml.attr("name",labComboxTag.getName()));
		html.append(BuildHtml.attr("data", labComboxTag.getData()));
		html.append(BuildHtml.attr("class", "combox"));
		html.append(BuildHtml.attr("type", "text"));
		html.append(BuildHtml.attr("style", "width: 200px"));
		html.append(BuildHtml.attr("key", labComboxTag.getKey()));
		html.append(BuildHtml.title("可选可输"));
		html.append(BuildHtml.attr("value",labComboxTag.getValue()));
		html.append(BuildHtml.id(labComboxTag.getId()));
		html.append(BuildHtml.endClose());
		html.append("</div>");
		return html;
	}
}
