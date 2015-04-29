package cn.labsoft.labos.base.code.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.code.dao.ILabCodeDAO;
import cn.labsoft.labos.base.code.dao.ILabTypeDAO;
import cn.labsoft.labos.base.code.entity.LabCode;
import cn.labsoft.labos.base.code.entity.LabType;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

/**
 * @title 公共代码
 * @author Bill
 * @time 2014.02.08
 */
@SuppressWarnings("unchecked")
@Service("labCodeService")
public class LabCodeServiceImpl implements ILabCodeService {
	private ILabCodeDAO labCodeDAO;
	private ILabTypeDAO labTypeDAO;
	@Resource
	public void setLabTypeDAO(ILabTypeDAO labTypeDAO) {
		this.labTypeDAO = labTypeDAO;
	}
	

	public LabCodeVo addLabCode(LabCodeVo labCodeVo) throws GlobalException {
		try {
			String typeId = labCodeVo.getTypeid();
			LabCode po = new LabCode();
			BeanUtils.copyProperties(labCodeVo, po, new String[] { "sort" });
			LabType type = (LabType) labTypeDAO.findById(LabType.class,
					labCodeVo.getTypeid());
			po.setLabType(type);
			if (labCodeVo.getSort() == null) {
				po.setSort(0);
			} else {
				po.setSort(labCodeVo.getSort());
			}
            po.setIsDel(Constants_Common.N);
			labCodeDAO.addLabCode(po);
			labCodeVo.setTypeid(typeId);
			return labCodeVo;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("添加公共代码出错！" + e.getMessage());
		}

	}

	public boolean deleteLabCode(Serializable labCodeId) throws GlobalException {
		try {
			LabCode po = labCodeDAO.getLabCode(String.valueOf(labCodeId));
			if (po != null) {
				labCodeDAO.deleteLabCode(po);
			}
			return true;
		} catch (Exception e) {
			throw new GlobalException("删除公共代码出错！" + e.getMessage());
		}
	}

	public LabCodeVo updateLabCode(LabCodeVo labCodeVo) throws GlobalException {
		try {
			String typeId = labCodeVo.getTypeid();
			LabCode po = labCodeDAO.getLabCode(labCodeVo.getId().trim());
			if (!StrUtils.isBlankOrNull(labCodeVo.getCode())) {
				po.setCode(labCodeVo.getCode());
			}
			if (!StrUtils.isBlankOrNull(labCodeVo.getName())) {
				po.setName(labCodeVo.getName());
			}
			if (!StrUtils.isBlankOrNull(labCodeVo.getRemark())) {
				po.setRemark(labCodeVo.getRemark());
			}
			po.setLabType((LabType) labTypeDAO.findById(LabType.class, typeId));
			if (null == labCodeVo.getSort()) {
				po.setSort(0);
			} else {
				po.setSort(labCodeVo.getSort());
			}
			labCodeDAO.updateLabCode(po);
			labCodeVo.setTypeid(typeId);
			return labCodeVo;

		} catch (Exception e) {

			throw new GlobalException("修改公共代码出错！" + e.getMessage());
		}
	}

	public LabCodeVo getLabCodeById(Serializable id) throws GlobalException {
		try {
			LabCodeVo labCodeVo = new LabCodeVo();
			LabCode po = (LabCode) labCodeDAO.findById(LabCode.class, id);
			BeanUtils.copyProperties(po, labCodeVo, new String[] { "" });
			labCodeVo.setTypeid(po.getLabType().getId());
			return labCodeVo;
		} catch (Exception e) {

			throw new GlobalException("根据ID查询公共代码出错！" + e.getMessage());
		}
	}

	public List<LabCodeVo> getLabCodeList() throws GlobalException {
		try {
			String hql = "FROM LabCode po where isDel='"+Constants_Common.N+"'";
			List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
			List<LabCode> poList = labCodeDAO.find(hql);
			if (poList.size() > 0) {
				for (LabCode po : poList) {
					LabCodeVo vo = new LabCodeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			return voList;
		} catch (Exception e) {
			throw new GlobalException("查询公共代码列表出错！" + e.getMessage());
		}
	}

	public PageResult getLabCodeList(String hql, PageResult pageResult)
			throws GlobalException {
		try {
			pageResult = labCodeDAO.getPageResult(hql, pageResult);
			List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
			List<LabCode> poList = pageResult.getResultList();
			if (poList.size() > 0) {
				for (LabCode po : poList) {
					LabCodeVo vo = new LabCodeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			throw new GlobalException("公共代码分页查询出错！" + e.getMessage());
		}
	}

	@Override
	public PageResult getLabCodePR(LabCodeVo labCodeVo, PageResult pageResult)
			throws GlobalException {
		try {
			String hql = "FROM LabCode po WHERE isDel='"+Constants_Common.N+"' and po.labType.id='"
					+ labCodeVo.getTypeid() + "' order by sort";
			pageResult = labCodeDAO.getPageResult(hql, pageResult
					.getCurrentPage(), pageResult.getPagerMethod(), pageResult
					.getPageSize());
			List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
			List<LabCode> poList = pageResult.getResultList();
			if (poList.size() > 0) {
				for (LabCode po : poList) {
					LabCodeVo vo = new LabCodeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			throw new GlobalException("样品登记分页查询出错！" + e.getMessage());
		}
	}

	@Resource
	public void setLabCodeDAO(ILabCodeDAO labCodeDAO) {
		this.labCodeDAO = labCodeDAO;
	}

	public int getMaxSort(LabCodeVo labCodeVo) throws GlobalException {
		int sort = -1;
		String sql = "SELECT MAX(sort)  FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.id='"
				+ labCodeVo.getTypeid() + "'";
		if (null != labCodeDAO.getColumnMaxValue(sql)) {
			sort = (Integer) labCodeDAO.getColumnMaxValue(sql);
		}
		return sort;
	}

	@Override
	public String[] getInstrumentNames(String typeCode) throws GlobalException {
		LabType typePo = labTypeDAO.getLabTypeByTypeCode(typeCode);
		List<LabCode> codeList = labCodeDAO.getLabCodeByTypeId(typePo.getId());
		String[] instrumentNames = new String[codeList.size()];
		int i = 0;
		if (codeList.size() > 0) {
			for (LabCode po : codeList) {
				instrumentNames[i] = po.getName();
				i++;
			}
		}
		return instrumentNames;
	}

	@Override
	public PageResult getLabCodePRByType(LabCodeVo labCodeVo,
			PageResult pageResult) throws GlobalException {
		try {
			String hql = "FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.id='"
					+ labCodeVo.getTypeid() + "' ORDER BY po.sort";
			pageResult = labCodeDAO.getPageResult(hql, pageResult);
			List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
			List<LabCode> poList = pageResult.getResultList();
			if (poList.size() > 0) {
				for (LabCode po : poList) {
					LabCodeVo vo = new LabCodeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {

			throw new GlobalException("样品登记分页查询出错！" + e.getMessage());
		}
	}

	@Override
	public LabTypeVo getLabTypeById(Serializable id) throws GlobalException {
		try {

			LabTypeVo rslabLabmonTypeVo = new LabTypeVo();
			LabType po = (LabType) labTypeDAO.findById(LabType.class, id);
			BeanUtils
					.copyProperties(po, rslabLabmonTypeVo, new String[] { "" });

			return rslabLabmonTypeVo;
		} catch (Exception e) {

			throw new GlobalException("根据公共代码类型ID查询出错！" + e.getMessage());
		}
	}

	@Override
	public String isExsitLabCodeByCode(String checkStr, String typeid,
			String flag) throws GlobalException {
		if (flag.equals("0")) {
			List<LabCode> list = new ArrayList<LabCode>();
			list = labCodeDAO.find("FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.id='"
					+ typeid.trim() + "' AND po.name='" + checkStr.trim()
					+ "' ORDER BY po.sort");
			if (list.size() > 0) {
				return "1";
			} else {
				return "0";
			}

		} else if (flag.equals("1")) {
			List<LabCode> list = new ArrayList<LabCode>();
			list = labCodeDAO.find("FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.id='"
					+ typeid.trim() + "' AND po.code='" + checkStr.trim()
					+ "' ORDER BY po.sort");
			if (list.size() > 0) {
				return "1";
			} else {
				return "0";
			}
		} else {
			return "1";
		}
	}

	public List<LabCodeVo> getLabCodeByTypeCode(String code)
			throws GlobalException {
		List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
		try {
			String hql2 = "FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.code='" + code.trim()
					+ "' ORDER BY po.sort";
			List<LabCode> poList = labCodeDAO.find(hql2);
			if (poList.size() > 0) {
				for (LabCode po : poList) {
					LabCodeVo vo = new LabCodeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			throw new GlobalException("根据公共类型名查找公共代码列表出错！" + e.getMessage());
		}
		return voList;
	}
	@Override
	public List<LabCodeVo> getLabCodeVoListByNameAndTypeCode(String name,
			String typeCode) throws GlobalException {
		List<LabCodeVo> codeList = new ArrayList<LabCodeVo>();
		StringBuffer SQL = new StringBuffer();
		SQL.append(" FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' ");
		SQL.append(" AND po.name like '%" + name.trim() + "%' ");
		SQL.append(" AND po.labType.code ='" + typeCode.trim()+ "' ");
		SQL.append(" ORDER BY po.sort ");
		List<LabCode> list = labCodeDAO.find(SQL.toString());
		if (null != list && list.size() > 0) {
			for (LabCode po : list) {
				LabCodeVo vo = new LabCodeVo();
				vo.setName(po.getName());
				vo.setCode(po.getCode());
				vo.setId(po.getId());
				codeList.add(vo);
			}
		}
		return codeList;
	}

	@Override
	public String getLabCodeName(String labCode, String typeCode)
			throws GlobalException {
		String hql = "FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and  AND po.code = '" + labCode.trim()
				+ "' AND labType.code  = '" + typeCode.trim() + "'";
		List<LabCode> list = labCodeDAO.find(hql);
		if (null != list && list.size() > 0)
			return list.get(0).getName();
		return "";
	}
	
	public List getAllLabCodeList(LabCodeVo labCodeVo)throws GlobalException{
		//获得无分页数据
		String hql = "FROM LabCode po WHERE 1=1";
		if(!"".equals(labCodeVo.getTypeid()) && null!=labCodeVo.getTypeid()){
			hql+="AND po.labType = '"+labCodeVo.getTypeid()+"'";
		}
			hql+=" ORDER BY po.sort";
		List labLabCodelist=labCodeDAO.find(hql);
		return labLabCodelist;
	}
	
	public LabCodeVo preAddLabComCode(LabCodeVo labCodeVo) throws GlobalException {
		// comCodeVo.setSort(getMaxSort(comCodeVo));
		return labCodeVo;
	}
	
	public LabCodeVo deleteLabCode4Code(Serializable labCodeId)
			throws GlobalException {
		try {
			LabCodeVo vo = new LabCodeVo();
			LabCode po = new LabCode();
			po = (LabCode) labCodeDAO.findById(LabCode.class, labCodeId);
			if(null!=po){
				BeanUtils.copyProperties(po, vo, new String[] { "" });
			}
			
			vo.setTypeid(po.getLabType().getId());
			labCodeDAO.deleteLabCode((LabCode)labCodeDAO.findById(LabCode.class, labCodeId));
			return vo;
		} catch (Exception e) {

			throw new GlobalException("删除公共代码出错！" + e.getMessage());
		}
	}

	@Override
	public List<String> getLabCodeList(String code, String name) throws GlobalException {
		List<String> list = null;
		try {
			String hql = "SELECT DISTINCT name FROM LabCode po WHERE po.isDel='"+Constants_Common.N+"' and po.labType.code='" + code.trim()+"'";
			if(null!=name&&!"".equals(name))
				hql +=" AND name like '"+name+"%' ";
			hql +=" ORDER BY po.sort ";
			list = labCodeDAO.find(hql);
		} catch (Exception e) {
			Log4J.error("根据公共类型名查找公共代码列表出错！" + e.getMessage());
		}
		return list;
	}

}
