package cn.labsoft.labos.source.reference.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.reference.dao.ILabRefTypeDAO;
import cn.labsoft.labos.source.reference.entity.LabRefType;
import cn.labsoft.labos.source.reference.service.ILabRefTypeService;
import cn.labsoft.labos.source.reference.vo.LabRefTypeVo;

@Service("labRefTypeService")
public class LabRefTypeServiceImpl extends BaseService implements
		ILabRefTypeService {

	private ILabRefTypeDAO labRefTypeDAO;

	@Resource
	public void setLabRefTypeDAO(ILabRefTypeDAO labRefTypeDAO) {
		this.labRefTypeDAO = labRefTypeDAO;
	}

	@Override
	public LabRefTypeVo addLabRefType(LabRefTypeVo labRefTypeVo)
			throws GlobalException {
		LabRefType labReferenceType = new LabRefType();
		labReferenceType.setName(labRefTypeVo.getName());
		LabRefType type = new LabRefType();
		type.setId(labRefTypeVo.getLabRefReferenceTypeId());
		labReferenceType.setType(type);
		labReferenceType.setIsDel(Constants_Source.N);
		try {
			labRefTypeDAO.addLabRefType(labReferenceType);
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("添加标准品类型异常--" + e.getMessage());
		}
		return poToVo(labReferenceType);
	}

	@Override
	public boolean deleteLabRefType(String[] ids) throws GlobalException {
		if (null != ids && 0 < ids.length) {
			for (String id : ids) {
				LabRefType labReferenceType = (LabRefType) labRefTypeDAO
						.findById(LabRefType.class, id);
				labReferenceType.setIsDel(Constants_Source.Y);
				labRefTypeDAO.updateLabReftype(labReferenceType);
			}
		}
		return true;
	}

	@Override
	public LabRefTypeVo getLabRefType(String id) throws GlobalException {
		LabRefType labReferenceType = (LabRefType) labRefTypeDAO.findById(
				LabRefType.class, id);
		return poToVo(labReferenceType);
	}

	@Override
	public List<LabRefTypeVo> getLabRefTypeByPid(String pid)
			throws GlobalException {
		String hql = " FROM LabRefType WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (null != pid && !"".equals(pid)) {
			hql += " AND type.id = '" + pid.trim() + "' ";
		} else {
			hql += " AND type.id IS NULL ";
		}
		List<LabRefType> labReferenceTypeList = labRefTypeDAO.find(hql);
		List<LabRefTypeVo> labReferenceVoList = new ArrayList<LabRefTypeVo>();
		if (labReferenceTypeList.size() > 0) {
			for (LabRefType po : labReferenceTypeList) {
				labReferenceVoList.add(poToVo(po));
			}
		}
		return labReferenceVoList;
	}

	@Override
	public List<LabRefTypeVo> getLabRefTypeList(LabRefTypeVo labRefTypeVo)
			throws GlobalException {
		String hql = " FROM LabRefType WHERE 1=1 AND isDel ='" + Constants_Source.N
				+ "'";
		List<LabRefType> labReferenceTypeList = labRefTypeDAO.find(hql);
		List<LabRefTypeVo> labReferenceVoList = new ArrayList<LabRefTypeVo>();
		if (labReferenceTypeList.size() > 0) {
			for (LabRefType po : labReferenceTypeList) {
				labReferenceVoList.add(poToVo(po));
			}
		}
		return labReferenceVoList;
	}

	@Override
	public LabRefTypeVo updateLabRefType(LabRefTypeVo labRefTypeVo)
			throws GlobalException {
		LabRefType LabRefType = (LabRefType) labRefTypeDAO.findById(
				LabRefType.class, labRefTypeVo.getId());
		BeanUtils.copyProperties(labRefTypeVo, LabRefType,
				new String[] { "isDel" });
		labRefTypeDAO.updateLabReftype(LabRefType);
		return labRefTypeVo;
	}

	private static LabRefTypeVo poToVo(LabRefType po) throws GlobalException {
		LabRefTypeVo vo = new LabRefTypeVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "type" });
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getType()) {
			vo.setLabRefReferenceTypeId(po.getType().getId());
			vo.setLabRefReferenceTypeName(po.getType().getName());
		}
		return vo;
	}

	@Override
	public LabRefTypeVo getLabRefTypeByVo(LabRefTypeVo labRefTypeVo)
			throws GlobalException {
		String hql = "from LabRefType where isDel='" + Constants_Source.N + "'";
		if (null != labRefTypeVo.getId()) {
			hql += " and id='" + labRefTypeVo.getId().trim() + "'";
		}
		if (null != labRefTypeVo.getName()) {
			hql += "and name='" + labRefTypeVo.getName().trim() + "'";
		}
		LabRefType labReferenceType = (LabRefType) labRefTypeDAO.find(hql, 0);
		if (null != labReferenceType) {
			return poToVo(labReferenceType);
		} else {
			return null;
		}

	}

	@Override
	public List<LabRefTypeVo> getLabRefTypeList(String id, int x)
			throws GlobalException {
		List<LabRefTypeVo> list=new ArrayList<LabRefTypeVo>();
		LabRefTypeVo labRefTypeVo=this.getLabRefType(id);
		list.add(labRefTypeVo);
		x+=1;
		List<LabRefType> sysList=labRefTypeDAO.find("FROM LabRefType WHERE 1=1 AND isDel='"+ Constants_Source.N+ "' and type.id='"+id+"'");
		if (sysList.size()>0) {
			for(LabRefType po:sysList)
			{
				String str="";
				for(int i=0;i<x;i++)
					str+="　　　";
				po.setName(str+po.getName());
				LabRefTypeVo vo=poToVo(po);
                list.addAll(getLabRefTypeList(vo.getId(),x));
			}
		}
		return list;
	}
}
