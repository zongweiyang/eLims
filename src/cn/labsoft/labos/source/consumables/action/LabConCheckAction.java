package cn.labsoft.labos.source.consumables.action;

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
import cn.labsoft.labos.source.consumables.service.ILabConCheckMainService;
import cn.labsoft.labos.source.consumables.vo.LabConCheckDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConCheckMainVo;
@Controller
@Scope("prototype")
public class LabConCheckAction extends BaseAction {

	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private ILabConCheckMainService labConCheckMainService;
	private LabConCheckMainVo labConCheckMainVo;

	@Resource
	public void setLabConCheckMainService(
			ILabConCheckMainService labConCheckMainService) {
		this.labConCheckMainService = labConCheckMainService;
	}

	public LabConCheckMainVo getLabConCheckMainVo() {
		return labConCheckMainVo;
	}

	public void setLabConCheckMainVo(LabConCheckMainVo labConCheckMainVo) {
		this.labConCheckMainVo = labConCheckMainVo;
	}

	private void initBean() throws GlobalException {
		if (null == labConCheckMainVo) {
			labConCheckMainVo = new LabConCheckMainVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("checktime");
		}
	}

	public String listLabConCheckMain() throws GlobalException {
		initBean();
		pageResult = labConCheckMainService.getLabConCheckMainPR(
				labConCheckMainVo, pageResult);
		return LIST;
	}

	public String preAddLabConCheckMain() throws GlobalException {
		initBean();
		labConCheckMainVo = labConCheckMainService.getNewLabConCheckMainVo();
		List<LabConCheckDetailVo> labConCheckDetailVoList = labConCheckMainService
				.getAllLabConCheckDetailVoList();
		labConCheckMainVo.setLabConCheckDetailVoList(labConCheckDetailVoList);
		return PREADD;
	}

	public String addLabConCheckMain() throws GlobalException {
		try {
			labConCheckMainVo = labConCheckMainService
					.addLabConCheckMain(labConCheckMainVo);
		} catch (Exception e) {
			log.error("保存盘点库存异常--" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String showLabConCheckMain() throws GlobalException {
		labConCheckMainVo = labConCheckMainService
				.getLabConCheckMain(labConCheckMainVo);
		return SHOW;
	}

}
