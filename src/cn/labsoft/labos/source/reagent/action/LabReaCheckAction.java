package cn.labsoft.labos.source.reagent.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reagent.service.ILabReaCheckMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckMainVo;
@Controller
@Scope("prototype")
public class LabReaCheckAction extends BaseAction {

	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private ILabReaCheckMainService labReaCheckMainService;
	private LabReaCheckMainVo labReaCheckMainVo;
    
	@Resource
	public void setLabReaCheckMainService(
			ILabReaCheckMainService labReaCheckMainService) {
		this.labReaCheckMainService = labReaCheckMainService;
	}

	public LabReaCheckMainVo getLabReaCheckMainVo() {
		return labReaCheckMainVo;
	}

	public void setLabReaCheckMainVo(LabReaCheckMainVo labReaCheckMainVo) {
		this.labReaCheckMainVo = labReaCheckMainVo;
	}

	private void initBean() throws GlobalException {
		if (null == labReaCheckMainVo) {
			labReaCheckMainVo = new LabReaCheckMainVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("checktime");
		}
	}

	public String listLabReaCheckMain() throws GlobalException {
		initBean();
		pageResult = labReaCheckMainService.getLabReaCheckMainPR(
				labReaCheckMainVo, pageResult);
		return LIST;
	}

	public String preAddLabReaCheckMain() throws GlobalException {
		initBean();
		labReaCheckMainVo = labReaCheckMainService.getNewLabReaCheckMainVo();
		List<LabReaCheckDetailVo> labReaCheckDetailVoList = labReaCheckMainService.getAllLabReaCheckDetailVoList();
		labReaCheckMainVo.setLabReaCheckDetailVoList(labReaCheckDetailVoList);
		return PREADD;
	}

	public String addLabReaCheckMain() throws GlobalException {
		try {
			labReaCheckMainVo = labReaCheckMainService
					.addLabReaCheckMain(labReaCheckMainVo);
		} catch (Exception e) {
			log.error("保存盘点库存异常--" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String showLabReaCheckMain() throws GlobalException {
		labReaCheckMainVo = labReaCheckMainService
				.getLabReaCheckMain(labReaCheckMainVo);
		return SHOW;
	}

}
