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
import cn.labsoft.labos.base.code.service.ILabTypeService;
import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@SuppressWarnings("unchecked")
@Service("labTypeService")
public class LabTypeServiceImpl implements ILabTypeService {
	private ILabTypeDAO labTypeDAO;
	private ILabCodeDAO labCodeDAO;

	@Resource
	public void setLabCodeDAO(ILabCodeDAO labCodeDAO) {
		this.labCodeDAO = labCodeDAO;
	}

	public LabTypeVo addLabType(LabTypeVo labTypeVo) throws GlobalException {
		try {
			LabType po = new LabType();
			BeanUtils.copyProperties(labTypeVo, po, new String[] { "sort" });
			if (labTypeVo.getSort() == null) {
				po.setSort(0);
			} else {
				po.setSort(labTypeVo.getSort());
			}
			po.setIsDel(Constants_Common.N);
			labTypeDAO.addLabType(po);
			return labTypeVo;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("添加公共代码类型出错！" + e.getMessage());
		}
	}

	public void deleteLabType(String labTypeId) throws GlobalException {
		LabType type = labTypeDAO.getLabType(labTypeId);
		if (type != null) {
			try {
				String hql = "FROM LabCode WHERE isDel='"+Constants_Common.N+"' and labType.id='" + type.getId()
						+ "'";
				List<LabCode> list = labCodeDAO.find(hql);
				if (list != null) {
					for (LabCode labCode : list) {
						labCodeDAO.deleteLabCode(labCode);
					}
				}
				labTypeDAO.deleteLabType(type);
			} catch (Exception e) {
				Log4J.error(e.getMessage(), e);
				throw new GlobalException("删除公共代码类型出错！" + e.getMessage());
			}
		}
	}

	public LabTypeVo updateLabType(LabTypeVo labLabTypeVo)
			throws GlobalException {
		try {
			LabType po = labTypeDAO.getLabType(labLabTypeVo.getId());
			if (null == labLabTypeVo.getSort()) {
				po.setSort(0);
			} else {
				po.setSort(labLabTypeVo.getSort());
			}
			if (!StrUtils.isBlankOrNull(labLabTypeVo.getShowType())) {
				po.setShowType(labLabTypeVo.getShowType());
			}
			if (!StrUtils.isBlankOrNull(labLabTypeVo.getName())) {
				po.setName(labLabTypeVo.getName());
			}
			if (!StrUtils.isBlankOrNull(labLabTypeVo.getCode())) {
				po.setCode(labLabTypeVo.getCode());
			}
			if (!StrUtils.isBlankOrNull(labLabTypeVo.getRemark())) {
				po.setRemark(labLabTypeVo.getRemark());
			}
			labTypeDAO.updateLabType(po);
			return labLabTypeVo;

		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("修改公共代码类型出错！" + e.getMessage());
		}
	}

	public LabTypeVo getLabTypeById(Serializable id) throws GlobalException {
		try {
			LabTypeVo rslabLabTypeVo = new LabTypeVo();
			LabType po = (LabType) labTypeDAO.findById(LabType.class, id);
			BeanUtils.copyProperties(po, rslabLabTypeVo, new String[] { "" });

			return rslabLabTypeVo;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("根据公共代码类型ID查询出错！" + e.getMessage());
		}
	}

	public List<LabTypeVo> getLabTypeList() throws GlobalException {
		try {
			String hql = "FROM LabType po where po.isDel='"+Constants_Common.N+"'";
			List<LabType> typeList = labTypeDAO.find(hql);
			List<LabTypeVo> typeVoList = new ArrayList<LabTypeVo>();
			if (typeList.size() > 0) {
				for (LabType po : typeList) {
					LabTypeVo vo = new LabTypeVo();
					BeanUtils.copyProperties(po, vo, new String[] { "" });
					typeVoList.add(vo);
				}
			}
			return typeVoList;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("查询公共代码类型列表出错！" + e.getMessage());
		}
	}

	public PageResult getLabTypeList(String hql, PageResult pageResult)
			throws GlobalException {
		try {
			pageResult = labTypeDAO.getPageResult(hql, pageResult);
			List<LabType> poList = pageResult.getResultList();
			List<LabTypeVo> voList = new ArrayList<LabTypeVo>();
			if (poList.size() > 0) {
				for (LabType po : poList) {
					LabTypeVo vo = new LabTypeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("公共代码类型分页查询出错！" + e.getMessage());
		}
	}

	@Override
	public PageResult getLabTypePR(LabTypeVo labTypeVo, PageResult pageResult)
			throws GlobalException {
		try {
			String hql = "FROM LabType po  WHERE po.isDel='"+Constants_Common.N+"'";
			if (labTypeVo.getName() != null && !"".equals(labTypeVo.getName())) {
				hql += " AND name like '%" + labTypeVo.getName().trim() + "%'";
			}
			if (labTypeVo.getCode() != null && !"".equals(labTypeVo.getCode())) {
				hql += " AND code like '%" + labTypeVo.getCode().trim() + "%'";
			}
			if (labTypeVo.getShowType() != null
					&& !"".equals(labTypeVo.getShowType())) {
				hql += " AND showType='" + labTypeVo.getShowType().trim() + "'";
			}
			pageResult = labTypeDAO.getPageResult(hql, pageResult);
			List<LabType> poList = pageResult.getResultList();
			List<LabTypeVo> voList = new ArrayList<LabTypeVo>();
			if (poList.size() > 0) {
				for (LabType po : poList) {
					LabTypeVo vo = new LabTypeVo();
					BeanUtils.copyProperties(po, vo, new String[] {});
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("样品登记分页查询出错！" + e.getMessage());
		}
	}

	@Resource
	public void setLabTypeDAO(ILabTypeDAO labTypeDAO) {
		this.labTypeDAO = labTypeDAO;
	}

	@Override
	public String isExsitLabType(String checkStr, String flag)
			throws GlobalException {
		if (flag.equals("0")) {
			List<LabType> list = new ArrayList<LabType>();
			list = labTypeDAO.find("FROM LabType po WHERE po.isDel='"+Constants_Common.N+"' and po.name='"
					+ checkStr.trim() + "'");
			if (list.size() > 0) {
				return "1";
			} else {
				return "0";
			}

		} else if (flag.equals("1")) {
			List<LabType> list = new ArrayList<LabType>();
			list = labTypeDAO.find("FROM LabType po WHERE po.isDel='"+Constants_Common.N+"' and po.code='"
					+ checkStr.trim() + "'");
			if (list.size() > 0) {
				return "1";
			} else {
				return "0";
			}
		} else {
			return "1";
		}
	}

	public int getMaxSort(LabTypeVo labTypeVo) throws GlobalException {
		int sort = -1;
		String sql = "SELECT max(sort) FROM LabType po where po.isDel='"+Constants_Common.N+"'";
		if (null != labTypeDAO.getColumnMaxValue(sql)) {
			sort = (Integer) labTypeDAO.getColumnMaxValue(sql);
		}
		return sort;
	}
	
	
	public List<LabTypeVo> getLabTypesList(LabTypeVo labTypeVo)
	throws GlobalException {
		List<LabTypeVo> voList = null;
		String hql = "From LabType po WHERE 1=1 ";
		if (labTypeVo.getShowType() != null
				&& !"".equals(labTypeVo.getShowType())) {
			hql += " AND showType='" + labTypeVo.getShowType().trim() + "'";
		}
		hql +=" ORDER BY po.type,id ";
		List<LabType> poList = labTypeDAO.find(hql);
		if(null!=poList&poList.size()>0){
			voList = new ArrayList<LabTypeVo>(); 
			String preTypeName = ""; 
			for(int i=poList.size()-1;i>=0;i--){
				LabType po = poList.get(i);
				LabTypeVo vo = new LabTypeVo();
				BeanUtils.copyProperties(po, vo, new String[]{});
				if(null!=po && !"".equals(po.getType()) && null!=po.getType()){
					if(!preTypeName.equals(po.getType())){
						preTypeName = vo.getType();
						voList.add(vo);
					}
				}
			}
		}
		
		for(LabTypeVo tempLabTypeVo:voList){
			List<LabTypeVo> tList = new ArrayList<LabTypeVo>();
			for(LabType po:poList){
				if(tempLabTypeVo.getType().equals(po.getType())){
					LabTypeVo tempVo = new LabTypeVo();
					BeanUtils.copyProperties(po, tempVo, new String[]{});
					tempVo.setCodeList(labCodeDAO.getLabCodeByTypeCode(po.getCode()));
					tList.add(tempVo);
				}
			}
			tempLabTypeVo.setList(tList);
		}
		
	return voList;
}

}
