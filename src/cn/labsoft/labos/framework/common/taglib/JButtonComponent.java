package cn.labsoft.labos.framework.common.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.DefaultTextProvider;
import com.opensymphony.xwork2.util.ValueStack;

public class JButtonComponent extends Component {

	private String btFunctionUrl;
	private String btFunctionValue;
	private String btFunctionType;
	private String btJsFunctions;
	private String btAttribute;
	private boolean btIsDeafultImg;
	private String btImgSrc;
	protected DefaultTextProvider textProvider;

	public JButtonComponent(ValueStack stack) {
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
		//判断权限.只控制增删改查
		if("add".equals(btFunctionType)
				||"update".equals(btFunctionType)
						||"show".equals(btFunctionType)
								||"delete".equals(btFunctionType)){
			isAUSD = true;
		}
		if(true == isAUSD){
			//是否有增删改查的权限
			if(btFunctionType.equals("add"))btFunctionType="Add";
			if(btFunctionType.equals("update"))btFunctionType="Update";
			if(btFunctionType.equals("show"))btFunctionType="Show";
			if(btFunctionType.equals("delete"))btFunctionType="Delete";
			if(true==JFunction.isHaveingThisFunction(btFunctionUrl,btFunctionType)){
				writer.write(toHTML());
			}
		}else{
			writer.write(toHTML());
		}		
		
	}
	
	private String toHTML() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		StringBuffer html = new StringBuffer("<a ");
		html = setAttribute(basePath, html,btAttribute);	
		html = setAttribute(basePath, html,btJsFunctions);	
		html.append(">");
		html.append("<img");
		html = setImageSrc(basePath, html);	
		html.append("/>");
		html.append("<b>"+btFunctionValue+"</b>");
		html.append("</a>");
		return html.toString();
	}

	private StringBuffer setImageSrc(String basePath, StringBuffer html) {
		if(true==btIsDeafultImg){
			if("add".equals(btFunctionType))
				html.append(" src=\""+basePath+"img/add.gif\" ");
			else if("update".equals(btFunctionType))
				html.append(" src=\""+basePath+"img/update.gif\" ");
			else if("show".equals(btFunctionType))
				html.append(" src=\""+basePath+"img/show.gif\" ");
			else if("delete".equals(btFunctionType))
				html.append(" src=\""+basePath+"img/delete.gif\" ");	
		}else{
			html.append(" src=\""+basePath+""+btImgSrc+"\"");	
		}
		return html;
	}
	
	private StringBuffer setAttribute(String basePath, StringBuffer html,String inputString) {
		if(null!=inputString&&inputString.trim().length()>0){
			String[] objects = inputString.replace("，", ",").split(",");
			for(int i=0;i<objects.length;i++){
				if(null!=objects[i]&&objects[i].trim().length()>0){
					String[] object = objects[i].replace("：", ":").split(":");
					if(!"SRC".equals(object[0].toUpperCase())){
						if("HREF".equals(object[0].toUpperCase())){
							if(null!=object[1]){
								if(object[1].toUpperCase().startsWith("JAVASCRIPT")){
									html.append(" "+object[0]+"=\"javascript:;\" ");
								}else if(object[1].toUpperCase().startsWith("#")){
									html.append(" "+object[0]+"=\"javascript:;\" ");
								}else{
									html.append(" "+object[0]+"=\""+ basePath +object[1]+"\" ");
								}
							}else{
								html.append(" "+object[0]+"=\"javascript:;\" ");
							}
						}else{
							html.append(" "+object[0]+"=\""+object[1]+"\" ");
						}
					}
						
				}
			}
		}
		return html;
	}


	public String getBtJsFunctions() {
		return btJsFunctions;
	}

	public void setBtJsFunctions(String btJsFunctions) {
		this.btJsFunctions = btJsFunctions;
	}

	public String getBtAttribute() {
		return btAttribute;
	}

	public void setBtAttribute(String btAttribute) {
		this.btAttribute = btAttribute;
	}

	public String getBtFunctionValue() {
		return btFunctionValue;
	}

	public void setBtFunctionValue(String btFunctionValue) {
		//i18n
		this.btFunctionValue = getText(btFunctionValue);
	}

	public boolean isBtIsDeafultImg() {
		return btIsDeafultImg;
	}

	public void setBtIsDeafultImg(boolean btIsDeafultImg) {
		this.btIsDeafultImg = btIsDeafultImg;
	}

	public String getBtImgSrc() {
		return btImgSrc;
	}

	public void setBtImgSrc(String btImgSrc) {
		this.btImgSrc = btImgSrc;
	}

	public String getBtFunctionUrl() {
		return btFunctionUrl;
	}

	public void setBtFunctionUrl(String btFunctionUrl) {
		this.btFunctionUrl = btFunctionUrl;
	}

	public String getBtFunctionType() {
		return btFunctionType;
	}

	public void setBtFunctionType(String btFunctionType) {
		this.btFunctionType = btFunctionType;
	}
	public String getText(String key){
		String value = key;
		if(this.textProvider != null){
			value = this.textProvider.getText(key);
		}
		if(value==null)value=key;
		return value;
	}
}
