package cn.labsoft.labos.base.code.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : LabCodeAction.java </strong>. <br>
 * <strong>Description : TODO</strong> <br>
 * <strong>Create on : 2014-9-4 上午10:24:43  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>CORE8</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class LabCodeAction extends BaseAction {
	
	private ILabCodeService labCodeService;
	
	private LabCodeVo labCodeVo;
	private LabTypeVo labTypeVo;
	private List listResult;
	private  int maxSort;
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	public LabCodeVo getLabCodeVo() {
		return labCodeVo;
	}
	/**
	 * 
	 * @author Charles Xi<br>
	 * @since 8.0
	 * @param labCodeVo  
	 * @throws
	 */
	public void setLabCodeVo(LabCodeVo labCodeVo) {
		this.labCodeVo = labCodeVo;
	}
	
	public LabTypeVo getLabTypeVo() {
		return labTypeVo;
	}

	public void setLabTypeVo(LabTypeVo labTypeVo) {
		this.labTypeVo = labTypeVo;
	}

	public List getListResult() {
		return listResult;
	}

	public void setListResult(List listResult) {
		this.listResult = listResult;
	}

	public int getMaxSort() {
		return maxSort;
	}

	public void setMaxSort(int maxSort) {
		this.maxSort = maxSort;
	}

	public void init() throws GlobalException {
		if (labCodeVo == null) {
			labCodeVo = new LabCodeVo();
		}
		if (labTypeVo == null) {
			labTypeVo = new LabTypeVo();
		}
	}

	public String preAddLabCode() throws GlobalException {
		init();
		labCodeVo.setSort(labCodeService.getMaxSort(labCodeVo) + 1);
		return PREADD;
	}

	public String addLabCode() throws GlobalException {
		init();
		labCodeVo = labCodeService.addLabCode(labCodeVo);
		return ADD;
	}

	public String preUpdateLabCode() throws GlobalException {
		init();
		String typeId = labCodeVo.getTypeid();
		labCodeVo = labCodeService.getLabCodeById(labCodeVo.getId());
		labCodeVo.setTypeid(typeId);
		return PREUPDATE;
	}

	public String updateLabCode() throws GlobalException {
		init();
		labCodeService.updateLabCode(labCodeVo);
		return UPDATE;
	}

	public String deleteLabCode() throws GlobalException {
		init();
		if (null != labCodeVo.getIds() && !"".equals(labCodeVo.getIds())) {
			for (String id : labCodeVo.getIds())
				labCodeService.deleteLabCode(id);
		}
		return DELETE;
	}

	public String listLabCode() throws GlobalException {
		init();
		pageResult = labCodeService.getLabCodePR(labCodeVo, pageResult);
		return LIST;
	}
	
	public String ajaxIsExsitLabCode() throws GlobalException, IOException {
		String checkStr = getParameter("checkStr");
		String flag = getParameter("flag");
		String typeid = getParameter("typeid");
		String result = labCodeService.isExsitLabCodeByCode(checkStr, typeid,
				flag);
		outPutString(result + "");
		return NONE;
	}
	public void ajaxLabCodeList() throws GlobalException {
		List<String> labCodeVoList = labCodeService.getLabCodeList(labCodeVo.getCode(),labCodeVo.getName());
		ajax(labCodeVoList);
	}
	/**
	 * 显示公共代码页面
	 * @return
	 * @throws GlobalException
	 */
	public String showListLabCode() throws GlobalException {
		if(null==labCodeVo){
			labCodeVo = new LabCodeVo();
		}
		try {
			labCodeVo.setTypename(labCodeService.getLabTypeById(
					labCodeVo.getTypeid()).getName());
			pageResult = labCodeService.getLabCodePR(labCodeVo,pageResult);
	
	

			if(null==listResult){
				listResult = new ArrayList();
			}
			listResult=labCodeService.getAllLabCodeList(labCodeVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		labCodeService.preAddLabComCode(labCodeVo);
		labCodeVo.setSort(labCodeService.getMaxSort(labCodeVo) + 1);
		maxSort=labCodeService.getMaxSort(labCodeVo) + 1;
		return "showListLabCode";
	}

	/**
	 * 添加公共代码
	 * @return
	 * @throws GlobalException
	 */
	public String addLabCode4Code() throws GlobalException {
		
		init();
		labCodeVo = labCodeService.addLabCode(labCodeVo);
		return "addLabCode";
	}
	/**
	 * 删除公共代码
	 * @return
	 * @throws GlobalException
	 */
	public String delLabCode4Code() throws GlobalException {
		labCodeVo = labCodeService.deleteLabCode4Code(labCodeVo.getId());
		return "delLabCode";
	}
	/**
	 * 修改公共代码
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabCode4Code() throws GlobalException {
		init();
		labCodeService.updateLabCode(labCodeVo);
		labCodeVo.setSort(labCodeService.getMaxSort(labCodeVo) + 1);
		maxSort=labCodeService.getMaxSort(labCodeVo) + 1;
		return "updateLabCode";
	}
	/**
	 * 跳转修改公共代码
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateLabCode4Code() throws GlobalException {
		String typeId = labCodeVo.getTypeid();
		labCodeVo = labCodeService.getLabCodeById(labCodeVo.getId());
		labCodeVo.setTypeid(typeId);
		listResult=labCodeService.getAllLabCodeList(labCodeVo);
		return "listLabCode";
	}
}
