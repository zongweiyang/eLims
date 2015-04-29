package cn.labsoft.labos.framework.common.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.util.ValueStack;

public class JHrefComponent extends Component {

	private String hrFunctionUrl;
	private String hrFunctionValue;
	private String hrFunctionType;
	private String hrJsFunctions;
	private String hrAttribute;
	private boolean hrIsInterval;
	protected DefaultTextProvider textProvider;

	public JHrefComponent(ValueStack stack) {
		super(stack);
		for(Object obj : stack.getRoot()){
			if(obj instanceof DefaultTextProvider){
				textProvider = (DefaultTextProvider)obj;
			}
		}
	}

	// 将自己需要输出的逻辑通过writer输出字符串就可以了。
	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			this.outButton(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return result;
	}

	private void outButton(Writer writer) throws IOException {
		boolean isAUSD = false;
		// 判断权限.只控制增删改查
		if ("add".equals(hrFunctionType) || "update".equals(hrFunctionType)
				|| "show".equals(hrFunctionType)
				|| "delete".equals(hrFunctionType)) {
			isAUSD = true;
		}
		if (true == isAUSD) {
			// 是否有增删改查的权限
			if(hrFunctionType.equals("add"))hrFunctionType="Add";
			if(hrFunctionType.equals("update"))hrFunctionType="Update";
			if(hrFunctionType.equals("show"))hrFunctionType="Show";
			if(hrFunctionType.equals("delete"))hrFunctionType="Delete";
			if (true == JFunction.isHaveingThisFunction(hrFunctionUrl, hrFunctionType)) {
				writer.write(toHTML());
			}
		} else {
			writer.write(toHTML());
		}

	}

	private String toHTML() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		StringBuffer html = new StringBuffer("<a ");
		html = setAttribute(basePath, html, hrAttribute);
		html = setAttribute(basePath, html, hrJsFunctions);
		html.append(">");
		html.append(hrFunctionValue);
		html.append("</a>");
		html = setInterval(html);
		return html.toString();
	}

	private StringBuffer setInterval(StringBuffer html) {
		if (true == hrIsInterval) {
			html.append("&nbsp;&nbsp;&nbsp;");
		}
		return html;
	}

	private StringBuffer setAttribute(String basePath, StringBuffer html,
			String inputString) {
		if (null != inputString && inputString.trim().length() > 0) {
			String[] objects = inputString.replace("，", ",").split(",");
			for (int i = 0; i < objects.length; i++) {
				if (null != objects[i] && objects[i].trim().length() > 0) {
					String[] object = objects[i].replace("：", ":").split(":");
					if (!"SRC".equals(object[0].toUpperCase())) {
						if ("HREF".equals(object[0].toUpperCase())) {
							if (null != object[1]) {
								if (object[1].toUpperCase().startsWith(
										"JAVASCRIPT")) {
									html.append(" " + object[0]
											+ "=\"javascript:;\" ");
								} else if (object[1].toUpperCase().startsWith(
										"#")) {
									html.append(" " + object[0] + "=\"javascript:;\" ");
								} else {
									html.append(" " + object[0] + "=\""
											+ basePath + object[1] + "\" ");
								}
							} else {
								html.append(" " + object[0]
										+ "=\"javascript:;\" ");
							}
						} else {
							html.append(" " + object[0] + "=\"" + object[1]
									+ "\" ");
						}
					}

				}
			}
		}
		return html;
	}

	public String getHrFunctionUrl() {
		return hrFunctionUrl;
	}

	public void setHrFunctionUrl(String hrFunctionUrl) {
		this.hrFunctionUrl = hrFunctionUrl;
	}

	public String getHrFunctionValue() {
		return hrFunctionValue;
	}

	public void setHrFunctionValue(String hrFunctionValue) {
		//i18n
		this.hrFunctionValue = getText(hrFunctionValue);
	}

	public String getHrFunctionType() {
		return hrFunctionType;
	}

	public void setHrFunctionType(String hrFunctionType) {
		this.hrFunctionType = hrFunctionType;
	}

	public String getHrJsFunctions() {
		return hrJsFunctions;
	}

	public void setHrJsFunctions(String hrJsFunctions) {
		this.hrJsFunctions = hrJsFunctions;
	}

	public String getHrAttribute() {
		return hrAttribute;
	}

	public void setHrAttribute(String hrAttribute) {
		this.hrAttribute = hrAttribute;
	}

	public boolean isHrIsInterval() {
		return hrIsInterval;
	}

	public void setHrIsInterval(boolean hrIsInterval) {
		this.hrIsInterval = hrIsInterval;
	}

	public static void main(String[] args) {
		Set<String> strSet = new HashSet<String>();

		strSet.add("acc");
		strSet.add("dea");
		strSet.add("add");
		strSet.add("def");
		strSet.add("abc");

		Set<Integer> intSet = new HashSet<Integer>();
		intSet.add(1);
		intSet.add(432);
		intSet.add(5433);
		intSet.add(3);
		intSet.add(7);

		String[] strArray = strSet.toArray(new String[0]);
		Integer[] intArray = intSet.toArray(new Integer[0]);

		for (String str : strArray) {
			System.out.println(str);
		}

		for (Integer intS : intArray) {
			System.out.println(intS);
		}
	}
	/**
	 * For i18n
	 * */
	public String getText(String key){
		String value = key;
		if(this.textProvider != null){
			value = this.textProvider.getText(key);
		}
		if(value==null)value=key;
		return value;
	}
}
