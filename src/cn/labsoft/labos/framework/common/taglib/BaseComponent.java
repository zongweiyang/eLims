package cn.labsoft.labos.framework.common.taglib;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.DefaultTextProvider;
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
public class BaseComponent extends Component {
	protected String basePath = "/";
	private String path;
	protected String realPath;
	protected final String A = "a";
	protected final String B = "b";
	protected DefaultTextProvider textProvider;
	public BaseComponent(ValueStack stack) {
		super(stack);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		realPath = request.getRealPath("/");
		path = request.getContextPath();
		for(Object obj : stack.getRoot()){
			if(obj instanceof DefaultTextProvider){
				textProvider = (DefaultTextProvider)obj;
			}
		}
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	}
	
	@Override
	public boolean start(Writer writer) {
		return super.start(writer);
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
