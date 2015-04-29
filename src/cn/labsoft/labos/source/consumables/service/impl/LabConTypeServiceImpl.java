package cn.labsoft.labos.source.consumables.service.impl;

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
import cn.labsoft.labos.source.consumables.dao.ILabConTypeDAO;
import cn.labsoft.labos.source.consumables.entity.LabConType;
import cn.labsoft.labos.source.consumables.service.ILabConTypeService;
import cn.labsoft.labos.source.consumables.vo.LabConTypeVo;
@Service("labConTypeService")
public class LabConTypeServiceImpl extends BaseService implements
		ILabConTypeService {

	private ILabConTypeDAO labConTypeDAO;
	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabConTypeDAO(ILabConTypeDAO labConTypeDAO) {
		this.labConTypeDAO = labConTypeDAO;
	}

	@Override
	public LabConTypeVo addLabConType(LabConTypeVo labConTypeVo)
			throws GlobalException {
		LabConType labConsumablesType = new LabConType();
		labConsumablesType.setName(labConTypeVo.getName());
		LabConType type = new LabConType();
		type.setId(labConTypeVo.getLabConConsumablesTypeId());
		labConsumablesType.setType(type);
		labConsumablesType.setIsDel(Constants_Source.N);
		try {
			labConTypeDAO.addLabConType(labConsumablesType);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("添加耗材类型异常--" + e.getMessage());
		}
		return poToVo(labConsumablesType);
	}

	@Override
	public boolean deleteLabConType(String[] ids) throws GlobalException {
		if (null != ids && 0 < ids.length) {
			for (String id : ids) {
				LabConType labConsumablesType = (LabConType) labConTypeDAO
						.findById(LabConType.class, id);
				labConsumablesType.setIsDel(Constants_Source.Y);
				labConTypeDAO.updateLabConType(labConsumablesType);
			}
		}
		return true;
	}

	@Override
	public LabConTypeVo getLabConType(String id) throws GlobalException {
		LabConType labConsumablesType = (LabConType) labConTypeDAO.findById(
				LabConType.class, id);
		return poToVo(labConsumablesType);
	}

	@Override
	public List<LabConTypeVo> getLabConTypeByPid(String pid)
			throws GlobalException {
		String hql = " FROM LabConType WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (null != pid && !"".equals(pid)) {
			hql += " AND type.id = '" + pid + "' ";
		} else {
			hql += " AND type.id IS NULL ";
		}
		List<LabConType> labConsumablesTypeList = labConTypeDAO.find(hql);
		List<LabConTypeVo> labConsumablesVoList = new ArrayList<LabConTypeVo>();
		if (labConsumablesTypeList.size() > 0) {
			for (LabConType po : labConsumablesTypeList) {
				labConsumablesVoList.add(poToVo(po));
			}
		}
		return labConsumablesVoList;
	}

	@Override
	public List<LabConTypeVo> getLabConTypeList(LabConTypeVo labConTypeVo)
			throws GlobalException {
		String hql = " FROM LabConType WHERE 1=1 AND isDel ='" + Constants_Source.N
				+ "'";
		List<LabConType> labConsumablesTypeList = labConTypeDAO.find(hql);
		List<LabConTypeVo> labConsumablesVoList = new ArrayList<LabConTypeVo>();
		if (labConsumablesTypeList.size() > 0) {
			for (LabConType po : labConsumablesTypeList) {
				labConsumablesVoList.add(poToVo(po));
			}
		}
		return labConsumablesVoList;
	}

	@Override
	public LabConTypeVo updateLabConType(LabConTypeVo labConTypeVo)
			throws GlobalException {
		LabConType LabConType = (LabConType) labConTypeDAO.findById(
				LabConType.class, labConTypeVo.getId());
		BeanUtils.copyProperties(labConTypeVo, LabConType,
				new String[] { "isDel" });
		labConTypeDAO.updateLabConType(LabConType);
		return labConTypeVo;
	}

	private static LabConTypeVo poToVo(LabConType po) throws GlobalException {
		LabConTypeVo vo = new LabConTypeVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "type" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getType()) {
			vo.setLabConConsumablesTypeId(po.getType().getId());
			vo.setLabConConsumablesTypeName(po.getType().getName());
		}
		return vo;
	}

	@Override
	public LabConTypeVo getLabConTypeByVo(LabConTypeVo labConTypeVo)
			throws GlobalException {
		String hql = "from LabConType where isDel='" + Constants_Source.N + "'";
		if (null != labConTypeVo.getId()) {
			hql += " and id='" + labConTypeVo.getId().trim() + "'";
		}
		if (null != labConTypeVo.getName()) {
			hql += "and name='" + labConTypeVo.getName().trim() + "'";
		}
		LabConType labConsumablesType = (LabConType) labConTypeDAO.find(hql, 0);
		return poToVo(labConsumablesType);
	}

	@Override
	public List<LabConTypeVo> getLabConTypeList(String id, int x)
			throws GlobalException {
		List<LabConTypeVo> list=new ArrayList<LabConTypeVo>();
		LabConTypeVo labConTypeVo=this.getLabConType(id);
		list.add(labConTypeVo);
		x+=1;
		List<LabConType> sysList=labConTypeDAO.find("FROM LabConType WHERE 1=1 AND isDel='"+ Constants_Source.N+ "' and type.id='"+id+"'");
		if (sysList.size()>0) {
			for(LabConType po:sysList)
			{
				String str="";
				for(int i=0;i<x;i++)
					str+="　　　";
				po.setName(str+po.getName());
				LabConTypeVo vo=poToVo(po);
                list.addAll(getLabConTypeList(vo.getId(),x));
			}
		}
		return list;
	}
}
