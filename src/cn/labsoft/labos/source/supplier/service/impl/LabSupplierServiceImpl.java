package cn.labsoft.labos.source.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.supplier.dao.ILabSupplierDAO;
import cn.labsoft.labos.source.supplier.entity.LabSupplier;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;

@Service("labSupplierService")
public class LabSupplierServiceImpl implements ILabSupplierService {
	private ILabSupplierDAO labSupplierDAO;

	@Resource
	public void setLabSupplierDAO(ILabSupplierDAO labSupplierDAO) {
		this.labSupplierDAO = labSupplierDAO;
	}

	@Override
	public LabSupplierVo addLabSupplier(LabSupplierVo labSupplierVo)
			throws GlobalException {

		LabSupplier labSupplier = new LabSupplier();
		try {
			labSupplier = this.vo2Po(labSupplierVo, labSupplier);
			labSupplierDAO.addLabSupplier(labSupplier);
			labSupplierVo.setId(labSupplier.getId());
		} catch (Exception e) {
			Log4J.error("LabSupplierServiceImpl addLabSupplier  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labSupplierVo;
	}

	@Override
	public boolean deleteLabSupplier(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labSupplierDAO.deleteLabSupplier(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabSupplierServiceImpl deleteLabSupplier  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabSupplierVo getLabSupplier(String id) throws GlobalException {
		LabSupplierVo labSupplierVo = new LabSupplierVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabSupplier labSupplier = labSupplierDAO.getLabSupplier(id);
				labSupplierVo = this.po2Vo(labSupplier, labSupplierVo);
			} catch (Exception e) {
				Log4J.error("LabSupplierServiceImpl getLabSupplier  error..."
						+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labSupplierVo;
	}

	@Override
	public List<LabSupplierVo> getLabSupplierList(LabSupplierVo labSupplierVo)
			throws GlobalException {
		String wereHql = "";
        if (!StrUtils.isBlankOrNull(labSupplierVo.getGoodsType())) {
			wereHql+=" and goodsType like '%"+labSupplierVo.getGoodsType()+"%'";
		}
		return this.getLabSupplierVoListByWhere(wereHql);
	}

	@Override
	public PageResult getLabSupplierPR(LabSupplierVo labSupplierVo,PageResult pageResult) throws GlobalException {
		String hql = "FROM LabSupplier WHERE isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labSupplierVo.getCode())) {
			hql += " and code like '%" + labSupplierVo.getCode().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSupplierVo.getName())) {
			hql += " and name like '%" + labSupplierVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labSupplierVo.getGoodsType())) {
			hql += " and goodsType ='" + labSupplierVo.getGoodsType().trim() + "'";
		}
		pageResult = labSupplierDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null
				&& pageResult.getResultList().size() > 0) {
			List<LabSupplierVo> labSupplierVoList = new ArrayList<LabSupplierVo>();
			List<LabSupplier> listLabSupplier = new ArrayList<LabSupplier>();
			listLabSupplier = pageResult.getResultList();
			for (LabSupplier labSupplier : listLabSupplier) {
				LabSupplierVo vo = new LabSupplierVo();
				vo = this.po2Vo(labSupplier, vo);
				labSupplierVoList.add(vo);
			}
			pageResult.setResultList(labSupplierVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabSupplier(LabSupplierVo labSupplierVo)
			throws GlobalException {
		LabSupplier labSupplier = new LabSupplier();
		boolean key = true;
		try {
			labSupplier = this.vo2Po(labSupplierVo, labSupplier);
			labSupplierDAO.updateLabSupplier(labSupplier);
		} catch (Exception e) {
			key = false;
			Log4J.error("LabSupplierServiceImpl updateLabSupplier  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabSupplier4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabSupplier labSupplier = labSupplierDAO.getLabSupplier(id);
					labSupplier.setIsDel(Constants_Source.Y);
					labSupplierDAO.updateLabSupplier(labSupplier);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error(
						"LabSupplierServiceImpl updateLabSupplier4Del  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	public List<LabSupplierVo> getLabSupplierVoListByWhere(String wereHql) throws GlobalException {
		List<LabSupplierVo> labSupplierVoList = new ArrayList<LabSupplierVo>();
		String hql = "FROM LabSupplier WHERE isDel='" + Constants_Source.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabSupplier> labSupplierList = labSupplierDAO.find(hql);
		if (labSupplierList != null && labSupplierList.size() > 0) {
			for (LabSupplier labSupplier : labSupplierList) {
				LabSupplierVo labSupplierVo = new LabSupplierVo();
				labSupplierVo = this.po2Vo(labSupplier, labSupplierVo);
				labSupplierVoList.add(labSupplierVo);
			}
		}
		return labSupplierVoList;
	}

	public LabSupplier vo2Po(LabSupplierVo labSupplierVo,
			LabSupplier labSupplier) {
		BeanUtils.copyProperties(labSupplierVo, labSupplier,
				new String[] { "isDel" });
		return labSupplier;
	}

	public LabSupplierVo po2Vo(LabSupplier labSupplier,
			LabSupplierVo labSupplierVo) {
		BeanUtils.copyProperties(labSupplier, labSupplierVo);
		return labSupplierVo;
	}

	@Override
	public List<LabCodeVo> getLabSupEvaluateList(List<LabCodeVo> list,
			String string, String labSupplierId) throws GlobalException {
		for (LabCodeVo vo : list) {
			Long count = labSupplierDAO
					.getCountSize("select count(*) FROM LabSupEvaluate WHERE isDel='"
							+ Constants_Source.N
							+ "' and labSupplier.id='"
							+ labSupplierId
							+ "' and "
							+ string
							+ "='"
							+ vo.getCode() + "'");
			vo.setName(vo.getName() + "---" + count.toString() + "次");
		}
		return list;
	}
}
