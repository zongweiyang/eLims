package cn.labsoft.labos.source.reagent.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.reagent.dao.ILabReaTypeDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaType;
import cn.labsoft.labos.source.reagent.service.ILabReaTypeService;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;
@Service("labReaTypeService")
public class LabReaTypeServiceImpl extends BaseService implements
		ILabReaTypeService {

	private ILabReaTypeDAO labReaTypeDAO;
	private static Log log = LogFactory.getLog(SpringInitListener.class);
    @Resource
	public void setLabReaTypeDAO(ILabReaTypeDAO labReaTypeDAO) {
		this.labReaTypeDAO = labReaTypeDAO;
	}

	@Override
	public LabReaTypeVo addLabReaType(LabReaTypeVo labReaTypeVo)
			throws GlobalException {
		LabReaType labReagentType = new LabReaType();
		labReagentType.setName(labReaTypeVo.getName());
		LabReaType type = new LabReaType();
		type.setId(labReaTypeVo.getLabReaReagentTypeId());
		labReagentType.setType(type);
		labReagentType.setIsDel(Constants_Source.N);
		try {
			labReaTypeDAO.addLabReaType(labReagentType);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("添加试剂类型异常--" + e.getMessage());
		}
		return poToVo(labReagentType);
	}

	@Override
	public boolean deleteLabReaType(String[] ids) throws GlobalException {
		if (null != ids && 0 < ids.length) {
			for (String id : ids) {
				LabReaType labReagentType = (LabReaType) labReaTypeDAO
						.findById(LabReaType.class, id);
				labReagentType.setIsDel(Constants_Source.Y);
				labReaTypeDAO.updateLabReaType(labReagentType);
			}
		}
		return true;
	}

	@Override
	public LabReaTypeVo getLabReaType(String id) throws GlobalException {
		LabReaType labReagentType = (LabReaType) labReaTypeDAO.findById(
				LabReaType.class, id);
		return poToVo(labReagentType);
	}

	@Override
	public List<LabReaTypeVo> getLabReaTypeByPid(String pid)
			throws GlobalException {
		String hql = " FROM LabReaType WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (null != pid && !"".equals(pid)) {
			hql += " AND type.id = '" + pid + "' ";
		} else {
			hql += " AND type.id IS NULL ";
		}
		List<LabReaType> labReagentTypeList = labReaTypeDAO.find(hql);
		List<LabReaTypeVo> labReagentVoList = new ArrayList<LabReaTypeVo>();
		if (labReagentTypeList.size() > 0) {
			for (LabReaType po : labReagentTypeList) {
				labReagentVoList.add(poToVo(po));
			}
		}
		return labReagentVoList;
	}

	@Override
	public List<LabReaTypeVo> getLabReaTypeList(LabReaTypeVo labReaTypeVo)
			throws GlobalException {
		String hql = " FROM LabReaType WHERE 1=1 AND isDel ='" + Constants_Source.N
				+ "'";
		List<LabReaType> labReagentTypeList = labReaTypeDAO.find(hql);
		List<LabReaTypeVo> labReagentVoList = new ArrayList<LabReaTypeVo>();
		if (labReagentTypeList.size() > 0) {
			for (LabReaType po : labReagentTypeList) {
				labReagentVoList.add(poToVo(po));
			}
		}
		return labReagentVoList;
	}
	
	@Override
	public List<LabReaTypeVo> getLabReaTypeList(String id,int x) throws GlobalException {
		List<LabReaTypeVo> list=new ArrayList<LabReaTypeVo>();
		LabReaTypeVo labReaTypeVo=this.getLabReaType(id);
		list.add(labReaTypeVo);
		x+=1;
		List<LabReaType> sysList=labReaTypeDAO.find("FROM LabReaType WHERE 1=1 AND isDel='"+ Constants_Source.N+ "' and type.id='"+id+"'");
		if (sysList.size()>0) {
			for(LabReaType po:sysList)
			{
				String str="";
				for(int i=0;i<x;i++)
					str+="　　　";
				po.setName(str+po.getName());
				LabReaTypeVo vo=poToVo(po);
                list.addAll(getLabReaTypeList(vo.getId(),x));
			}
		}
		return list;
	}

	@Override
	public LabReaTypeVo updateLabReaType(LabReaTypeVo labReaTypeVo)
			throws GlobalException {
		LabReaType LabReaType = (LabReaType) labReaTypeDAO.findById(
				LabReaType.class, labReaTypeVo.getId());
		BeanUtils.copyProperties(labReaTypeVo, LabReaType,
				new String[] { "isDel" });
		labReaTypeDAO.updateLabReaType(LabReaType);
		return labReaTypeVo;
	}

	private static LabReaTypeVo poToVo(LabReaType po) throws GlobalException {
		LabReaTypeVo vo = new LabReaTypeVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "type" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getType()) {
			vo.setLabReaReagentTypeId(po.getType().getId());
			vo.setLabReaReagentTypeName(po.getType().getName());
		}
		return vo;
	}

	@Override
	public LabReaTypeVo getLabReaTypeByVo(LabReaTypeVo labReaTypeVo)
			throws GlobalException {
		String hql = "from LabReaType where isDel='" + Constants_Source.N + "'";
		if (null != labReaTypeVo.getId()) {
			hql += " and id='" + labReaTypeVo.getId().trim() + "'";
		}
		if (null != labReaTypeVo.getName()) {
			hql += "and name='" + labReaTypeVo.getName().trim() + "'";
		}
		LabReaType labReagentType = (LabReaType) labReaTypeDAO.find(hql, 0);
		if (null != labReagentType) {
			return poToVo(labReagentType);
		}
		return null;
	}

}
