package cn.labsoft.labos.source.supplier.action;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;

@Controller
@Scope("prototype")
public class LabSupplierAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	public ExecutorService pool = Executors.newSingleThreadExecutor();
	private ILabNumberService labNumberService;
	private ILabSupplierService labSupplierService;
	private ILabCodeService labCodeService;
	private LabSupplierVo labSupplierVo;
	
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	@Resource
	public void setLabSupplierService(ILabSupplierService labSupplierService) {
		this.labSupplierService = labSupplierService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	public LabSupplierVo getLabSupplierVo() {
		return labSupplierVo;
	}

	public void setLabSupplierVo(LabSupplierVo labSupplierVo) {
		this.labSupplierVo = labSupplierVo;
	}

	public String listLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		pageResult = labSupplierService.getLabSupplierPR(labSupplierVo,
				pageResult);
		List typeList = labCodeService.getLabCodeByTypeCode("CPLX");
		setAttribute("typeList", typeList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		setAttribute("qualityList", qualityList);
		return LIST;
	}

	public String preAddLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_GYS, new String[] {},Constants_Source.CODE_USE_INIT);
		try {
			labSupplierVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			Log4J.err("LabSupplier preAddLabSupplier getCode error------"
					+ e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		List companyList = labCodeService.getLabCodeByTypeCode("DWLX");
		setAttribute("companyList", companyList);
		List typeList = labCodeService.getLabCodeByTypeCode("CPLX");
		setAttribute("typeList", typeList);
		List bankList = labCodeService.getLabCodeByTypeCode("KHH");
		setAttribute("bankList", bankList);
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		setAttribute("serverList", serverList);
		return PREADD;
	}

	public String addLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_GYS, new String[] {},Constants_Source.CODE_USE_RUN);
		try {
			labSupplierVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			Log4J.err("LabSupplier addLabSupplier getCode error------"
					+ e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		labSupplierVo = labSupplierService.addLabSupplier(labSupplierVo);
		return ADD;
	}

	public String preUpdateLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		labSupplierVo = labSupplierService
				.getLabSupplier(labSupplierVo.getId());
		List companyList = labCodeService.getLabCodeByTypeCode("DWLX");
		setAttribute("companyList", companyList);
		List typeList = labCodeService.getLabCodeByTypeCode("CPLX");
		setAttribute("typeList", typeList);
		List bankList = labCodeService.getLabCodeByTypeCode("KHH");
		setAttribute("bankList", bankList);
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		timeList = labSupplierService.getLabSupEvaluateList(timeList,
				"delivery", labSupplierVo.getId());
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		priceList = labSupplierService.getLabSupEvaluateList(priceList,
				"price", labSupplierVo.getId());
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		qualityList = labSupplierService.getLabSupEvaluateList(qualityList,
				"quality", labSupplierVo.getId());
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		serverList = labSupplierService.getLabSupEvaluateList(serverList,
				"server", labSupplierVo.getId());
		setAttribute("serverList", serverList);
		return PREUPDATE;
	}

	public String updateLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		boolean key = labSupplierService.updateLabSupplier(labSupplierVo);
		return UPDATE;
	}

	public String showLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		labSupplierVo = labSupplierService
				.getLabSupplier(labSupplierVo.getId());
		List companyList = labCodeService.getLabCodeByTypeCode("DWLX");
		setAttribute("companyList", companyList);
		List typeList = labCodeService.getLabCodeByTypeCode("CPLX");
		setAttribute("typeList", typeList);
		List bankList = labCodeService.getLabCodeByTypeCode("KHH");
		setAttribute("bankList", bankList);
		List timeList = labCodeService.getLabCodeByTypeCode("JHQ");
		timeList = labSupplierService.getLabSupEvaluateList(timeList,
				"delivery", labSupplierVo.getId());
		setAttribute("timeList", timeList);
		List priceList = labCodeService.getLabCodeByTypeCode("price");
		priceList = labSupplierService.getLabSupEvaluateList(priceList,
				"price", labSupplierVo.getId());
		setAttribute("priceList", priceList);
		List qualityList = labCodeService.getLabCodeByTypeCode("CPZL");
		qualityList = labSupplierService.getLabSupEvaluateList(qualityList,
				"quality", labSupplierVo.getId());
		setAttribute("qualityList", qualityList);
		List serverList = labCodeService.getLabCodeByTypeCode("server");
		serverList = labSupplierService.getLabSupEvaluateList(serverList,
				"server", labSupplierVo.getId());
		setAttribute("serverList", serverList);
		return SHOW;
	}

	public String deleteLabSupplier() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		labSupplierService.deleteLabSupplier(labSupplierVo.getIds());
		return DELETE;
	}

	public String updateLabSupplier4Del() throws GlobalException {
		if (null == labSupplierVo)
			labSupplierVo = new LabSupplierVo();
		labSupplierService.updateLabSupplier4Del(labSupplierVo.getIds());
		return DELETE;
	}

}
