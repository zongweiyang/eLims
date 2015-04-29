package cn.labsoft.labos.source.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.customer.dao.ILabCustomerDAO;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;

@Service("labCustomerService")
public class LabCustomerServiceImpl implements ILabCustomerService {
	private ILabCustomerDAO labCustomerDAO;

	@Resource
	public void setLabCustomerDAO(ILabCustomerDAO labCustomerDAO) {
		this.labCustomerDAO = labCustomerDAO;
	}

	@Override
	public LabCustomerVo addLabCustomer(LabCustomerVo labCustomerVo)
			throws GlobalException {

		LabCustomer labCustomer = new LabCustomer();
		try {
			labCustomer = this.vo2Po(labCustomerVo, labCustomer);
			labCustomerDAO.addLabCustomer(labCustomer);
			labCustomerVo.setId(labCustomer.getId());
		} catch (Exception e) {
			Log4J.error("LabCustomerServiceImpl addLabCustomer  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labCustomerVo;
	}

	@Override
	public boolean deleteLabCustomer(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labCustomerDAO.deleteLabCustomer(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabCustomerServiceImpl deleteLabCustomer  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabCustomerVo getLabCustomer(String id) throws GlobalException {
		LabCustomerVo labCustomerVo = new LabCustomerVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabCustomer labCustomer = labCustomerDAO.getLabCustomer(id);
				labCustomerVo = this.po2Vo(labCustomer, labCustomerVo);
			} catch (Exception e) {
				Log4J.error("LabCustomerServiceImpl getLabCustomer  error..."
						+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labCustomerVo;
	}

	@Override
	public List<LabCustomerVo> getLabCustomerList(LabCustomerVo labCustomerVo)
			throws GlobalException {
		String hql = "";
		if (!StrUtils.isBlankOrNull(labCustomerVo.getNum())) {
			hql += " and num like'%" + labCustomerVo.getNum().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getName())) {
			hql += " and name like '%" + labCustomerVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getType())) {
			hql += " and type like '%" + labCustomerVo.getType().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getLevel())) {
			hql += " and level like '%" + labCustomerVo.getLevel().trim() + "%'";
		}
		return this.getLabCustomerVoListByWhere(hql);
	}

	@Override
	public PageResult getLabCustomerPR(LabCustomerVo labCustomerVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabCustomer WHERE isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labCustomerVo.getNum())) {
			hql += " and num like'%" + labCustomerVo.getNum().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getName())) {
			hql += " and name like '%" + labCustomerVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getType())) {
			hql += " and type like '%" + labCustomerVo.getType().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labCustomerVo.getLevel())) {
			hql += " and level like '%" + labCustomerVo.getLevel().trim() + "%'";
		}
		pageResult = labCustomerDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null
				&& pageResult.getResultList().size() > 0) {
			List<LabCustomerVo> listLabCustomerVo = new ArrayList<LabCustomerVo>();
			List<LabCustomer> listLabCustomer = new ArrayList<LabCustomer>();
			listLabCustomer = pageResult.getResultList();
			for (LabCustomer labCustomer : listLabCustomer) {
				LabCustomerVo vo = new LabCustomerVo();
				vo = this.po2Vo(labCustomer, vo);
				listLabCustomerVo.add(vo);
			}
			pageResult.setResultList(listLabCustomerVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabCustomer(LabCustomerVo labCustomerVo)
			throws GlobalException {
		LabCustomer labCustomer = new LabCustomer();
		boolean key = true;
		try {
			labCustomer = this.vo2Po(labCustomerVo, labCustomer);
			labCustomerDAO.updateLabCustomer(labCustomer);
		} catch (Exception e) {
			key = false;
			Log4J.error("LabCustomerServiceImpl updateLabCustomer  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabCustomer4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabCustomer labCustomer = labCustomerDAO.getLabCustomer(id);
					labCustomer.setIsDel(Constants_Source.Y);
					labCustomerDAO.updateLabCustomer(labCustomer);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error(
						"LabCustomerServiceImpl updateLabCustomer4Del  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	public List<LabCustomerVo> getLabCustomerVoListByWhere(String wereHql) throws GlobalException {
		List<LabCustomerVo> listLabCustomerVo = new ArrayList<LabCustomerVo>();
		String hql = "FROM LabCustomer WHERE isDel='" + Constants_Source.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabCustomer> listLabCustomer = labCustomerDAO.find(hql);
		if (listLabCustomer != null && listLabCustomer.size() > 0) {
			for (LabCustomer labCustomer : listLabCustomer) {
				LabCustomerVo labCustomerVo = new LabCustomerVo();
				labCustomerVo = this.po2Vo(labCustomer, labCustomerVo);
				listLabCustomerVo.add(labCustomerVo);
			}
		}
		return listLabCustomerVo;
	}

	public LabCustomer vo2Po(LabCustomerVo labCustomerVo,
			LabCustomer labCustomer) {
		BeanUtils.copyProperties(labCustomerVo, labCustomer,
				new String[] { "isDel" });
		return labCustomer;
	}

	public LabCustomerVo po2Vo(LabCustomer labCustomer,
			LabCustomerVo labCustomerVo) {
		BeanUtils.copyProperties(labCustomer, labCustomerVo);
		return labCustomerVo;
	}

	@Override
	public List<String> getEveryMonthProgrammAmount(String id)
			throws GlobalException {
		List<String> amountList=new ArrayList<String>();
		String hql="select count(id) from LabSampCustomer where isDel='"+Constants_Source.N+"' and labCustomer.id='"+id+"'";
		String[] month={"01","02","03","04","05","06","07","08","09","10","11","12"};
		for(int i=0;i<12;i++)
		{
			String hql1=hql+" and createTime like '"+DateUtils.getYear()+"-"+month[i]+"%'";
			String amount=String.valueOf(labCustomerDAO.getCountSize(hql1));
			amountList.add(amount);
		}
		return amountList;
	}
}
