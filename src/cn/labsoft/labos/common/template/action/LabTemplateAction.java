package cn.labsoft.labos.common.template.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.common.page.service.ILabPageEditorService;
import cn.labsoft.labos.common.page.vo.LabPageEditorVo;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.common.template.vo.LabTemplateVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 页面管理Action类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Controller
@Scope("prototype")
public class LabTemplateAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1285194228097675623L;
	private ILabTemplateService labTemplateService;
	private ILabPageEditorService labPageEditorService;
	private ILabFunctionService labFunctionService;
	private ILabConfigService labConfigService;
	private LabTemplateVo labTemplateVo;
	private LabPageEditorVo labPageEditorVo;
	private List<LabFunctionVo> listLabFunction;

	/**
	 * 模板管理Service注入
	 * 
	 * @param labTemplateService
	 */
	@Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}

	/**
	 * 页面编辑管理Service注入
	 * 
	 * @param labPageEditorService
	 */
	@Resource
	public void setLabPageEditorService(ILabPageEditorService labPageEditorService) {
		this.labPageEditorService = labPageEditorService;
	}

	/**
	 * 功能管理Service注入
	 * 
	 * @param labFunctionService
	 */
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}

	/**
	 * 配置管理Service注入
	 * 
	 * @param labConfigService
	 */
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public List<LabFunctionVo> getListLabFunction() {
		return listLabFunction;
	}

	public void setListLabFunction(List<LabFunctionVo> listLabFunction) {
		this.listLabFunction = listLabFunction;
	}

	public LabPageEditorVo getLabPageEditorVo() {
		return labPageEditorVo;
	}

	public void setLabPageEditorVo(LabPageEditorVo labPageEditorVo) {
		this.labPageEditorVo = labPageEditorVo;
	}

	public LabTemplateVo getLabTemplateVo() {
		return labTemplateVo;
	}

	public void setLabTemplateVo(LabTemplateVo labTemplateVo) {
		this.labTemplateVo = labTemplateVo;
	}

	/**
	 * 模板分页信息
	 * 
	 * @throws GlobalException
	 */
	public String listLabTemplate() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		pageResult = labTemplateService.getLabTemplatePR(pageResult, labTemplateVo);
		return LIST;
	}

	/**
	 * 模板新增跳转
	 * 
	 * @throws GlobalException
	 */
	public String preAddLabTemplate() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		labFunctionVo.setIsTemplate(Constants_Base.Y);
		labFunctionVo.setType("1");
		listLabFunction = labFunctionService.getLabFunctionList(labFunctionVo);
		return PREADD;
	}

	/**
	 * 模板新增
	 * 
	 * @throws GlobalException
	 */
	public String addLabTemplate() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		labTemplateService.addLabTemplate(labTemplateVo);
		return ADD;
	}

	/**
	 * 模板修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabTemplate() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		labTemplateVo = labTemplateService.getLabTemplate(labTemplateVo);
		LabFunctionVo labFunctionVo = new LabFunctionVo();
		labFunctionVo.setIsTemplate(Constants_Base.Y);
		labFunctionVo.setType("1");
		listLabFunction = labFunctionService.getLabFunctionList(labFunctionVo);
		return PREUPDATE;
	}

	/**
	 * 模板修改
	 * 
	 * @throws GlobalException
	 */
	public String updateLabTemplate() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		labTemplateService.updateLabTemplate(labTemplateVo);
		return UPDATE;
	}

	/**
	 * 模板编辑修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String updateLabTemplate4Edit() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		labTemplateService.updateLabTemplate4Edit(labTemplateVo);
		return UPDATE;
	}

	/**
	 * 模板删除
	 * 
	 * @throws GlobalException
	 */
	public String updateLabTemplate4Del() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		labTemplateService.updateLabTemplate4Del(labTemplateVo);
		return DELETE;
	}

	/**
	 * 模板编辑修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabTemplate4Edit() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}

		labTemplateVo = labTemplateService.getLabTemplate(labTemplateVo);
		if (null != labTemplateVo.getObjUrl() && !"".equals(labTemplateVo.getObjUrl())) {
			labPageEditorVo.setObjUrl(labTemplateVo.getObjUrl());
		}

		StringBuffer sb = labPageEditorService.getAnnotationInfo(labPageEditorVo);
		labTemplateVo = labTemplateService.getLabTemplate4File(labTemplateVo);
		labTemplateVo.setData(sb.toString());
		return PREUPDATE;
	}

	/**
	 * 获得模板导出路径
	 * 
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	public String ajaxVerification4Export() throws GlobalException {
		if (null == labTemplateVo) {
			labTemplateVo = new LabTemplateVo();
		}
		if (null != labTemplateVo.getBusId() && !"".equals(labTemplateVo.getBusId())) {

		}
		LabConfigVo labConfigVo = labConfigService.getLabConfigByCode("exportTemplate");
		labTemplateVo = labTemplateService.getLabTemplateByBusId(labTemplateVo);
		if (null != labTemplateVo && null != labTemplateVo.getId()) {
			String path = "";
			if (null != labConfigVo && null != labConfigVo.getValue()) {
				path = labConfigVo.getValue() + File.separator + labTemplateVo.getId() + ".xls";
			}
			File file = new File(ServletActionContext.getRequest().getRealPath("/") + path.replace("/", File.separator).replace("\\", File.separator));
			if (file.exists()) {
				try {
					outPutString(path);
				} catch (IOException e) {
					throw new GlobalException("" + e.getMessage());
				}
			} else {
				try {
					outPutString("false");
				} catch (IOException e) {
					throw new GlobalException("" + e.getMessage());
				}
			}
		} else {
			try {
				outPutString("false");
			} catch (IOException e) {
				throw new GlobalException("" + e.getMessage());
			}
		}
		return NONE;
	}
}
