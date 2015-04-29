package cn.labsoft.labos.source.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.supplier.dao.ILabSupEvaluateDAO;
import cn.labsoft.labos.source.supplier.entity.LabSupEvaluate;
import cn.labsoft.labos.source.supplier.entity.LabSupplier;
import cn.labsoft.labos.source.supplier.service.ILabSupEvaluateService;
import cn.labsoft.labos.source.supplier.vo.LabSupEvaluateVo;

@Service("labSupEvaluateService")
public class LabSupEvaluateServiceImpl implements ILabSupEvaluateService {
	private ILabSupEvaluateDAO labSupEvaluateDAO;

	@Resource
	public void setLabSupEvaluateDAO(ILabSupEvaluateDAO labSupEvaluateDAO) {
		this.labSupEvaluateDAO = labSupEvaluateDAO;
	}

	@Override
	public LabSupEvaluateVo addLabSupEvaluate(LabSupEvaluateVo labSupEvaluateVo)
			throws GlobalException {

		LabSupEvaluate labSupEvaluate = new LabSupEvaluate();
		try {
			labSupEvaluate = this.vo2Po(labSupEvaluateVo, labSupEvaluate);
			labSupEvaluateDAO.addLabSupEvaluate(labSupEvaluate);
			labSupEvaluateVo.setId(labSupEvaluate.getId());
		} catch (Exception e) {
			Log4J.error("LabSupEvaluateServiceImpl addLabSupEvaluate  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labSupEvaluateVo;
	}

	@Override
	public boolean deleteLabSupEvaluate(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labSupEvaluateDAO.deleteLabSupEvaluate(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error(
					"LabSupEvaluateServiceImpl deleteLabSupEvaluate  error..."
							+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabSupEvaluateVo getLabSupEvaluate(String id) throws GlobalException {
		LabSupEvaluateVo labSupEvaluateVo = new LabSupEvaluateVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabSupEvaluate labSupEvaluate = labSupEvaluateDAO
						.getLabSupEvaluate(id);
				labSupEvaluateVo = this.po2Vo(labSupEvaluate, labSupEvaluateVo);
			} catch (Exception e) {
				Log4J.error(
						"LabSupEvaluateServiceImpl getLabSupEvaluate  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labSupEvaluateVo;
	}

	@Override
	public List<LabSupEvaluateVo> getLabSupEvaluateList(
			LabSupEvaluateVo labSupEvaluateVo) throws GlobalException {
		String wereHql = "";
		return this.getLabSupEvaluateVoListByWhere(wereHql);
	}

	@Override
	public PageResult getLabSupEvaluatePR(LabSupEvaluateVo labSupEvaluateVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabSupEvaluate WHERE isDel='" + Constants_Source.N
				+ "' and labSupplier.id='"
				+ labSupEvaluateVo.getLabSupplierId() + "'";
		if (!StrUtils.isBlankOrNull(labSupEvaluateVo.getPerson())) {
			hql += " and person like '%" + labSupEvaluateVo.getPerson().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSupEvaluateVo.getStartDate())) {
			hql += " and evaluateDate >='" + labSupEvaluateVo.getStartDate().trim()
					+ "'";
		}
		if (!StrUtils.isBlankOrNull(labSupEvaluateVo.getEndDate())) {
			hql += " and evaluateDate <='" + labSupEvaluateVo.getEndDate().trim()
					+ "'";
		}
		pageResult = labSupEvaluateDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null
				&& pageResult.getResultList().size() > 0) {
			List<LabSupEvaluateVo> labSupEvaluateVoList = new ArrayList<LabSupEvaluateVo>();
			List<LabSupEvaluate> listLabSupEvaluate = new ArrayList<LabSupEvaluate>();
			listLabSupEvaluate = pageResult.getResultList();
			for (LabSupEvaluate labSupEvaluate : listLabSupEvaluate) {
				LabSupEvaluateVo vo = new LabSupEvaluateVo();
				vo = this.po2Vo(labSupEvaluate, vo);
				labSupEvaluateVoList.add(vo);
			}
			pageResult.setResultList(labSupEvaluateVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabSupEvaluate(LabSupEvaluateVo labSupEvaluateVo)
			throws GlobalException {
		LabSupEvaluate labSupEvaluate = new LabSupEvaluate();
		boolean key = true;
		try {
			labSupEvaluate = this.vo2Po(labSupEvaluateVo, labSupEvaluate);
			labSupEvaluateDAO.updateLabSupEvaluate(labSupEvaluate);
		} catch (Exception e) {
			key = false;
			Log4J.error(
					"LabSupEvaluateServiceImpl updateLabSupEvaluate  error..."
							+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabSupEvaluate4Del(String[] ids)
			throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabSupEvaluate labSupEvaluate = labSupEvaluateDAO
							.getLabSupEvaluate(id);
					labSupEvaluate.setIsDel(Constants_Source.Y);
					labSupEvaluateDAO.updateLabSupEvaluate(labSupEvaluate);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error(
						"LabSupEvaluateServiceImpl updateLabSupEvaluate4Del  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	public List<LabSupEvaluateVo> getLabSupEvaluateVoListByWhere(String wereHql) throws GlobalException {
		List<LabSupEvaluateVo> labSupEvaluateVoList = new ArrayList<LabSupEvaluateVo>();
		String hql = "FROM LabSupEvaluate WHERE isDel='" + Constants_Source.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabSupEvaluate> labSupEvaluateList = labSupEvaluateDAO.find(hql);
		if (labSupEvaluateList != null && labSupEvaluateList.size() > 0) {
			for (LabSupEvaluate labSupEvaluate : labSupEvaluateList) {
				LabSupEvaluateVo labSupEvaluateVo = new LabSupEvaluateVo();
				labSupEvaluateVo = this.po2Vo(labSupEvaluate, labSupEvaluateVo);
				labSupEvaluateVoList.add(labSupEvaluateVo);
			}
		}
		return labSupEvaluateVoList;
	}

	public LabSupEvaluate vo2Po(LabSupEvaluateVo labSupEvaluateVo,
			LabSupEvaluate labSupEvaluate) {
		BeanUtils.copyProperties(labSupEvaluateVo, labSupEvaluate,
				new String[] { "isDel" });
		if (!StrUtils.isBlankOrNull(labSupEvaluateVo.getLabSupplierId())) {
			LabSupplier labSupplier = (LabSupplier) labSupEvaluateDAO.findById(
					LabSupplier.class, labSupEvaluateVo.getLabSupplierId());
			labSupEvaluate.setLabSupplier(labSupplier);
		}
		return labSupEvaluate;
	}

	public LabSupEvaluateVo po2Vo(LabSupEvaluate labSupEvaluate,
			LabSupEvaluateVo labSupEvaluateVo) {
		BeanUtils.copyProperties(labSupEvaluate, labSupEvaluateVo);
		if (null != labSupEvaluate.getLabSupplier()) {
			labSupEvaluateVo.setLabSupplierId(labSupEvaluate.getLabSupplier()
					.getId());
			labSupEvaluateVo.setLabSupplierName(labSupEvaluate.getLabSupplier()
					.getName());
		}
		return labSupEvaluateVo;
	}
}
