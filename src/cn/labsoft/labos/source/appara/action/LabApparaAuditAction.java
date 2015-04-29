package cn.labsoft.labos.source.appara.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.service.ILabApparaTypeService;
import cn.labsoft.labos.source.appara.service.ILabApparaUseService;
import cn.labsoft.labos.source.appara.vo.LabApparaTypeVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
@Controller
@Scope("prototype")
public class LabApparaAuditAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaService labApparaService;
	private ILabApparaUseService labApparaUseService;
	private ILabApparaTypeService labApparaTypeService;
	
	private LabApparaVo labApparaVo;
	private LabApparaTypeVo labApparaTypeVo;
	HttpServletRequest request = ServletActionContext.getRequest();
	
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}

	public LabApparaTypeVo getLabApparaTypeVo() {
		return labApparaTypeVo;
	}

	public void setLabApparaTypeVo(LabApparaTypeVo labApparaTypeVo) {
		this.labApparaTypeVo = labApparaTypeVo;
	}

	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}
	@Resource
	public void setLabApparaUseService(ILabApparaUseService labApparaUseService) {
		this.labApparaUseService = labApparaUseService;
	}
	@Resource
	public void setLabApparaTypeService(ILabApparaTypeService labApparaTypeService) {
		this.labApparaTypeService = labApparaTypeService;
	}
	
	
}
