package cn.labsoft.labos.source.reference.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reference.service.ILabRefCheckMainService;
import cn.labsoft.labos.source.reference.vo.LabRefCheckDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefCheckMainVo;

@Controller
@Scope("prototype")
public class LabRefCheckAction extends BaseAction {

	private ILabRefCheckMainService labRefCheckMainService;
	private LabRefCheckMainVo labRefCheckMainVo;

	@Resource
	public void setLabRefCheckMainService(
			ILabRefCheckMainService labRefCheckMainService) {
		this.labRefCheckMainService = labRefCheckMainService;
	}

	public LabRefCheckMainVo getLabRefCheckMainVo() {
		return labRefCheckMainVo;
	}

	public void setLabRefCheckMainVo(LabRefCheckMainVo labRefCheckMainVo) {
		this.labRefCheckMainVo = labRefCheckMainVo;
	}

	private void initBean() throws GlobalException {
		if (null == labRefCheckMainVo) {
			labRefCheckMainVo = new LabRefCheckMainVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("checktime");
		}
	}

	public String listLabRefCheckMain() throws GlobalException {
		initBean();
		pageResult = labRefCheckMainService.getLabRefCheckMainPR(
				labRefCheckMainVo, pageResult);
		return LIST;
	}

	public String preAddLabRefCheckMain() throws GlobalException {
		initBean();
		labRefCheckMainVo = labRefCheckMainService.getNewLabRefCheckMainVo();
		List<LabRefCheckDetailVo> labRefCheckDetailVoList = labRefCheckMainService
				.getAllLabRefCheckDetailVoList();
		labRefCheckMainVo.setLabRefCheckDetailVoList(labRefCheckDetailVoList);
		return PREADD;
	}

	public String addLabRefCheckMain() throws GlobalException {
		try {
			labRefCheckMainVo = labRefCheckMainService
					.addLabRefCheckMain(labRefCheckMainVo);
		} catch (Exception e) {
			Log4J.error("保存盘点库存异常--" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String showLabRefCheckMain() throws GlobalException {
		labRefCheckMainVo = labRefCheckMainService
				.getLabRefCheckMain(labRefCheckMainVo);
		return SHOW;
	}

}
