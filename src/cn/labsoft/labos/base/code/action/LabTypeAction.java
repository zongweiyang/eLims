package cn.labsoft.labos.base.code.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabTypeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 
 * @author bill<br>
 * @version <strong>LABCORE v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LabTypeAction extends BaseAction {

	private ILabTypeService labTypeService;
	private List<LabTypeVo> typeList;
	private LabTypeVo labTypeVo;

	private LabCodeVo labCodeVo;

	public LabCodeVo getLabCodeVo() {
		return labCodeVo;
	}

	public void setLabCodeVo(LabCodeVo labCodeVo) {
		this.labCodeVo = labCodeVo;
	}

	public LabTypeVo getLabTypeVo() {
		return labTypeVo;
	}

	public void setLabTypeVo(LabTypeVo labTypeVo) {
		this.labTypeVo = labTypeVo;
	}

	public List<LabTypeVo> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<LabTypeVo> typeList) {
		this.typeList = typeList;
	}

	private void init() throws GlobalException {
		if (labTypeVo == null) {
			labTypeVo = new LabTypeVo();
			pageResult.setOrderColumn("sort");
			pageResult.setOrder(PageResult.ORDER_ASC);
		}
	}

	

	public String preUpdateLabType() throws GlobalException {
		init();
		labTypeVo = labTypeService.getLabTypeById(labTypeVo.getId());
		return PREUPDATE;
	}

	public String updateLabType() throws GlobalException {
		init();
		labTypeService.updateLabType(labTypeVo);
		return UPDATE;
	}

	public String deleteLabType() throws GlobalException {
		init();
		if (null != labTypeVo.getIds() && !"".equals(labTypeVo.getIds())) {
			for (String id : labTypeVo.getIds())
				labTypeService.deleteLabType(id);
		}
		return DELETE;
	}
	/**
	 * 公共代码类型页面
	 * @return
	 * @throws GlobalException
	 */
	public String listLabType() throws GlobalException {
		init();
		if (getSessionContainer().getType().equals(Constants_Common.FRONT)) {
			labTypeVo.setShowType(Constants_Common.Y);
		}
		List<LabTypeVo> tempList  = labTypeService.getLabTypesList(labTypeVo);
		try {
			int k = 3;//三列
			LabTypeVo[][] objs = new LabTypeVo[k][tempList.size()%k >0 ?tempList.size()/k +1: tempList.size()/k];
			for(int i=0;i<tempList.size();i++){
				objs[i%k][i/k] = tempList.get(i);
			}
			
			typeList = new ArrayList<LabTypeVo>();
			for(int i=0;i<objs.length;i++){
				LabTypeVo vo = new LabTypeVo();
				tempList = new ArrayList<LabTypeVo>();
				for(int j=0;j<objs[i].length;j++){
					if(null!=objs[i][j])
						tempList.add(objs[i][j]);
				}
				
				vo.setList(tempList);
				typeList.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*init();
		if (getSessionContainer().getType().equals(Constants.FRONT)) {
			labTypeVo.setShowType(Constants.Y);
		}
		pageResult = labTypeService.getLabTypePR(labTypeVo, pageResult);*/
		return LIST;
	}
	/**
	 * 跳到新增公共代码类型页面
	 * @return
	 * @throws GlobalException
	 */
	public String preAddLabType4Code() throws GlobalException {
		if (null == labTypeVo) {
			labTypeVo = new LabTypeVo();
		}
		int sort = labTypeService.getMaxSort(labTypeVo);
		labTypeVo.setSort(sort + 1);
		return "preAddLabType";
	}
	/**
	 * 新增公共代码类型
	 * @return
	 * @throws GlobalException
	 */
	public String addLabType4Code() throws GlobalException {
		if(null!=labTypeVo){
			String code1=labTypeVo.getCode().toUpperCase();
			labTypeVo.setCode(code1);
		}
		labTypeService.addLabType(labTypeVo);
		return "addLabType";
	}
	

	/**
	 * ajax 判断公共代码类型是否存在
	 * @return
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String ajaxIsExsitLabType() throws GlobalException, IOException {
		String checkStr = getParameter("checkStr");
		String flag = getParameter("flag");
		String result = labTypeService.isExsitLabType(checkStr, flag);
		outPutString(result + "");
		return NONE;
	}
    @Resource
	public void setLabTypeService(ILabTypeService labTypeService) {
		this.labTypeService = labTypeService;
	}
}
