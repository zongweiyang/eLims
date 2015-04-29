package cn.labsoft.labos.common.page.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.common.page.service.ILabPageEditorService;
import cn.labsoft.labos.common.page.vo.LabPageEditorVo;
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
public class LabPageEditorAction extends BaseAction {
	private static final long serialVersionUID = -3888237210398096050L;
	private ILabPageEditorService labPageEditorService;
	private Map<String, String> checkboxList;

	private LabPageEditorVo labPageEditorVo;

	/**
	 * 页面管理Service注入
	 * 
	 * @param labPageEditorService
	 */
	@Resource
	public void setLabPageEditorService(ILabPageEditorService labPageEditorService) {
		this.labPageEditorService = labPageEditorService;
	}

	public LabPageEditorVo getLabPageEditorVo() {
		return labPageEditorVo;
	}

	public void setLabPageEditorVo(LabPageEditorVo labPageEditorVo) {
		this.labPageEditorVo = labPageEditorVo;
	}

	public Map<String, String> getCheckboxList() {
		return checkboxList;
	}

	public void setCheckboxList(Map<String, String> checkboxList) {
		this.checkboxList = checkboxList;
	}

	/**
	 * 页面分页方法
	 * 
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	public String listLabPage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		pageResult = labPageEditorService.getLabPageEditorPR(labPageEditorVo, pageResult);
		return LIST;
	}

	/**
	 * 页面新增跳转
	 * 
	 * @throws GlobalException
	 */
	public String preAddLabPage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		return PREADD;
	}

	/**
	 * 页面新增
	 * 
	 * @throws GlobalException
	 */
	public String addLabPage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.addLabPageEditor(labPageEditorVo);
		return ADD;
	}

	/**
	 * 页面修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabPage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.getLabPageEditor(labPageEditorVo);
		return PREUPDATE;
	}

	/**
	 * 页面修改
	 * 
	 * @throws GlobalException
	 */
	public String updateLabPage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.updateLabPageEditor(labPageEditorVo);
		return UPDATE;
	}

	/**
	 * 页面编辑修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabPage4Edit() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.getLabPageEditor(labPageEditorVo);
		return PREUPDATE;
	}

	/**
	 * 页面编辑修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdatePageEdit4Formula() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.getLabPageEditor(labPageEditorVo);
		return PREUPDATE;
	}

	/**
	 * 页面编辑修改
	 * 
	 * @throws GlobalException
	 */
	public String updateLabPage4Edit() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.updateLabPageEdit(labPageEditorVo);
		return UPDATE;
	}

	/**
	 * 页面编辑修改
	 * 
	 * @throws GlobalException
	 */
	public String updateLabPageEdit4Formula() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.updateLabPageEdit(labPageEditorVo);
		return UPDATE;
	}

	/**
	 * 页面删除
	 * 
	 * @throws GlobalException
	 */
	public String updateLabPage4Del() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.updateLabPage4Del(labPageEditorVo);
		return DELETE;
	}

	/**
	 * 判断是否存在页面对象对应文件
	 * 
	 * @throws GlobalException
	 */
	public String ajaxIsFileExist() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.getLabPageEditor(labPageEditorVo);
		if (null == labPageEditorVo.getUrl() || "".equals(labPageEditorVo.getUrl())) {
			try {
				outPutString("false");
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return NONE;
	}

	/**
	 * 页面对象选择
	 * 
	 * @throws GlobalException
	 */
	public String showPageFormula4Select() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		pageResult = labPageEditorService.getLabPageEditorPR(labPageEditorVo, pageResult);
		return LIST;
	}

	/**
	 * 获取页面对象视图参数注释
	 * 
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String ajaxGetParameter() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		StringBuffer annotation = labPageEditorService.getAnnotationInfo(labPageEditorVo);
		try {
			outPutString(annotation.toString());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取页面文件分页信息
	 * 
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	public String listFilePage() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		String jspPath = ServletActionContext.getRequest().getRealPath("/jsp/").replace(":", "@").replace("\\", "/");
		if (null == labPageEditorVo.getParentUrl() || "".equals(labPageEditorVo.getParentUrl())) {
			labPageEditorVo.setParentUrl(jspPath);
		}
		if (!jspPath.equals(labPageEditorVo.getParentUrl())) {
			File file = new File(labPageEditorVo.getParentUrl().replace("@", ":").replace("/", File.separator));
			if (file.exists() && file.getParent() != null) {
				labPageEditorVo.setGrandUrl(file.exists() ? file.getParent().replace(":", "@").replace("\\", "/") : "");
			}
		} else {
			labPageEditorVo.setGrandUrl("");
		}
		pageResult = labPageEditorService.getFilePagePR(labPageEditorVo, getPageResult());
		return LIST;
	}

	/**
	 * 批量增加页面信息
	 * 
	 * @throws GlobalException
	 */
	public String addLabPage4Batch() throws GlobalException {
		if (null == labPageEditorVo) {
			labPageEditorVo = new LabPageEditorVo();
		}
		labPageEditorVo = labPageEditorService.addBatchLabPageEditor(labPageEditorVo);
		try {
			outPutString("success");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labPageEditorVo.setParam("success");
		return ADD;
	}

}