package cn.labsoft.labos.source.appara.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.appara.dao.ILabApparaTypeDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaType;
import cn.labsoft.labos.source.appara.service.ILabApparaTypeService;
import cn.labsoft.labos.source.appara.vo.LabApparaTypeVo;
@Service("labApparaTypeService")
public class LabApparaTypeServiceImpl extends BaseService implements ILabApparaTypeService {
	private ILabApparaTypeDAO labApparaTypeDAO;
	@Resource
	public void setLabApparaTypeDAO(ILabApparaTypeDAO labApparaTypeDAO) {
		this.labApparaTypeDAO = labApparaTypeDAO;
	}


	@Override
	public LabApparaTypeVo addLabApparaType(LabApparaTypeVo vo) throws GlobalException {
		LabApparaType po = new LabApparaType();
		po.setAppName(vo.getAppName());
		LabApparaType type = new LabApparaType();
		type.setId(vo.getLabAppTypeId());
		po.setType(type);
		po.setIsDel(Constants_Source.N);
		try {
			labApparaTypeDAO.addLabApparaType(po);
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return poToVo(po);
	}

	@Override
	public boolean deleteLabApparaType(String[] ids) throws GlobalException {
		if(!"".equals(ids)&&0<ids.length){
			for (String id : ids) {
				LabApparaType po = (LabApparaType) labApparaTypeDAO.findById(LabApparaType.class,
						id);
				po.setIsDel(Constants_Source.Y);
				labApparaTypeDAO.updateLabApparaType(po);
			}
		}
		return true;
	}

	@Override
	public LabApparaTypeVo getLabApparaType(String id) throws GlobalException {
		LabApparaType labApparaType = (LabApparaType) labApparaTypeDAO.findById(LabApparaType.class, id);
		return poToVo(labApparaType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabApparaTypeVo> getLabApparaTypeByPid(String pid) throws GlobalException {
		String hql = " FROM LabApparaType WHERE 1=1 AND isDel='" + Constants_Source.N+"'";
		if(null!=pid&&!"".equals(pid)){
			hql +=" AND type.id = '"+pid+"' ";	
		}else{
			hql +=" AND type.id IS NULL ";	
		}
		List<LabApparaType> poList = labApparaTypeDAO.find(hql);
		List<LabApparaTypeVo> voList = new ArrayList<LabApparaTypeVo>();
		for (LabApparaType po : poList) {
			voList.add(poToVo(po));
		}
		return voList;
	}

	@Override
	public LabApparaTypeVo updateLabApparaType(LabApparaTypeVo vo) throws GlobalException {
		LabApparaType po = (LabApparaType) labApparaTypeDAO.findById(LabApparaType.class, vo.getId());
		BeanUtils.copyProperties(vo, po,new String[] { "isDel" });
		labApparaTypeDAO.updateLabApparaType(po);
		return vo;
	}

	private static LabApparaTypeVo poToVo(LabApparaType po) throws GlobalException {
		LabApparaTypeVo vo = new LabApparaTypeVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "type" });
			if (null != po.getType()) {
				vo.setLabAppTypeId(po.getType().getId());
				vo.setLabAppTypeName(po.getType().getAppName());
			}
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

}
