package cn.labsoft.labos.source.reagent.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaType;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
import cn.labsoft.labos.source.reagent.service.ILabReagentService;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;
import cn.labsoft.labos.source.reagent.vo.LabReagentVo;
import cn.labsoft.labos.utils.StrUtils;
@Service("labReagentService")
public class LabReagentServiceImpl extends BaseService implements
		ILabReagentService {

	private ILabReagentDAO labReagentDAO;
	private ILabUploadDAO labUploadDAO;

	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabReagentDAO(ILabReagentDAO labReagentDAO) {
		this.labReagentDAO = labReagentDAO;
	}

	@Override
	public LabReagentVo getLabReagentById(String id) throws GlobalException {
		LabReagent labReagent = (LabReagent) labReagentDAO.findById(
				LabReagent.class, id);
		return PoToVo(labReagent);
	}

	@Override
	public PageResult getLabReagentPR(LabReagentVo labReagentVo,
			PageResult pageResult) throws GlobalException {
		String hql = " FROM LabReagent WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (!StrUtils.isBlankOrNull(labReagentVo.getCode())) {
			hql += " AND code LIKE '%" + labReagentVo.getCode().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getName())) {
			hql += " AND name LIKE '%" + labReagentVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getSize())) {
			hql += " AND size LIKE '%" + labReagentVo.getSize().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getMaxSize())) {
			hql += " AND amount<='" + Double.valueOf(labReagentVo.getMaxSize())
					+ "'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getMinSize())) {
			hql += " AND amount>='" + Double.valueOf(labReagentVo.getMinSize())
					+ "'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getReagentTypeId())&&!StrUtils.isBlankOrNull(labReagentVo.getReagentTypeId().trim())) {
			String type = getTypeIdAndSubIdStrById(labReagentVo
					.getReagentTypeId().trim());
			hql += " AND reagentType.id IN ('" + type.replace(",", "','")
					+ "')";
		}
		pageResult = labReagentDAO.getPageResult4All(hql, pageResult);
		List<LabReagent> labReagentList = pageResult.getResultList();
		List<LabReagentVo> voList = new ArrayList<LabReagentVo>();
		if (labReagentList.size() > 0) {
			for (LabReagent po : labReagentList) {
				voList.add(PoToVo(po));
			}
		}

		pageResult.setResultList(voList);
		return pageResult;
	}

	public String getTypeIdAndSubIdStrById(String id) throws GlobalException {
		String idStr = id + ",";
		List<LabReaType> list = labReagentDAO
				.find("from LabReaType where type.id='" + id.trim()
						+ "' and isDel='" + Constants_Source.N + "'");
		if (null != list && list.size() > 0) {
			for (LabReaType labReaType : list) {
				idStr += getTypeIdAndSubIdStrById(labReaType.getId());
			}
		}
		return idStr;
	}

	private LabReagentVo PoToVo(LabReagent po) throws GlobalException {
		LabReagentVo vo = new LabReagentVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "reagentType" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getReagentType()) {
			vo.setReagentTypeId(po.getReagentType().getId());
			vo.setReagentTypeName(po.getReagentType().getName());
		}
		if (null != po.getSaveOrg() && !"".equals(po.getSaveOrg())) {
			LabOrg labOrg = (LabOrg) labReagentDAO.findById(LabOrg.class, po
					.getSaveOrg());
			vo.setSaveOrg(labOrg.getId());
			vo.setSaveOrgName(labOrg.getName());
		}
		if (po.getSize() != null) {
			vo.setSpecifications(po.getSize());
		}
		return vo;
	}

	private static LabReagent VoToPo(LabReagentVo vo) throws GlobalException {
		LabReagent po = new LabReagent();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "reagentTypeId",
					"reagentTypeName" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("vo转Po异常--" + e.getMessage());
		}
		if (null != vo.getReagentTypeId()) {
			LabReaType type = new LabReaType();
			type.setId(vo.getReagentTypeId().trim());
			po.setReagentType(type);
		}
		return po;
	}

	@Override
	public boolean addLabReagent(LabReagentVo labReagentVo)
			throws GlobalException {
		try {
			LabReagent po = VoToPo(labReagentVo);
			labReagentDAO.addLabReagent(po);
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labReagentVo.getUuid(), "lab-reagent");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			return true;
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
			throw new GlobalException("试剂保存异常--" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabReagent(String id) throws GlobalException {
		try {
			LabReagent labReagent = (LabReagent) labReagentDAO.findById(
					LabReagent.class, id);
			labReagent.setIsDel(Constants_Source.Y);
			labReagentDAO.updateLabReagent(labReagent);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("试剂删除异常--" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabReagent(LabReagentVo labReagentVo)
			throws GlobalException {
		try {
			LabReagent po = VoToPo(labReagentVo);
			labReagentDAO.updateLabReagent(po);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("试剂更新异常--" + e.getMessage());
		}
	}

	@Override
	public List<LabReagentVo> getReagentListByTypeId(String pid)
			throws GlobalException {
		String hql = " FROM LabReagent WHERE reagentType.id='" + pid
				+ "' AND isDel='" + Constants_Source.N + "'";
		List<LabReagent> poList = labReagentDAO.find(hql);
		List<LabReagentVo> voList = new ArrayList<LabReagentVo>();
		if (poList.size() > 0) {
			for (LabReagent po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabReagentVo> getLabReagentList(LabReagentVo labReagentVo)
			throws GlobalException {
		String hql = " FROM LabReagent WHERE  isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labReagentVo.getReagentTypeId())) {
			String type = getTypeIdAndSubIdStrById(labReagentVo
					.getReagentTypeId().trim());
			hql += " AND reagentType.id IN ('" + type.replace(",", "','")
					+ "')";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getName())) {
			hql += " AND name like '%" + labReagentVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getUnit())) {
			hql += " AND unit like '%" + labReagentVo.getUnit().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReagentVo.getIsSafe())
				&& labReagentVo.getIsSafe().equals("Y")) {
			hql += " AND safeAmount>=amount";
		}
		List<LabReagent> poList = labReagentDAO.find(hql);
		List<LabReagentVo> voList = new ArrayList<LabReagentVo>();
		if (poList.size() > 0) {
			for (LabReagent po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return voList;
	}

	@Override
	public List<LabReagentVo> getLabReagentListByIds(String ids)
			throws GlobalException {
		String hql = " FROM LabReagent WHERE isDel='" + Constants_Source.N
				+ "' AND id IN(";
		String[] id = ids.split(",");
		for (int i = 0; i < id.length - 1; i++) {
			hql += "'" + id[i] + "',";
		}
		hql += "'" + id[id.length - 1] + "')";
		List<LabReagent> labList = labReagentDAO.find(hql);
		List<LabReagentVo> labReagentVoList = new ArrayList<LabReagentVo>();
		for (LabReagent labReagent : labList) {
			labReagentVoList.add(PoToVo(labReagent));
		}
		return labReagentVoList;
	}

	@Override
	public LabReagentVo updateLabReagentMove(LabReagentVo labReagentVo) throws GlobalException {
		LabReagent labReagent = (LabReagent) labReagentDAO.findById(LabReagent.class, labReagentVo.getId());
		LabReaType labReaType = new LabReaType();
		labReaType.setId(labReagentVo.getReagentTypeId());
		labReagent.setReagentType(labReaType);
		try {
			labReagentDAO.updateLabReagent(labReagent);
		} catch (Exception e) {
			log.error("试剂迁移异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labReagentVo;
	}

	@Override
	public boolean addLabReagent4Import(String[][] string,String typeId) throws GlobalException {
		for (int j = 2; j < string.length; j++) {
			LabReaTypeVo labReaTypeVo = new LabReaTypeVo();
			LabReagentVo labReagentVo = new LabReagentVo();
			labReagentVo.setIsDel(Constants_Source.N);
			if (!StrUtils.isBlankOrNull(typeId)) {
					labReagentVo.setReagentTypeId(typeId);
				} else {
					labReagentVo.setReagentTypeId("0");
			}
			if (null != string[j][0] && !"".equals(string[j][0])) {
				labReagentVo.setCode(string[j][0]);//编号
			}
			if (null != string[j][1] && !"".equals(string[j][1])) {
				labReagentVo.setName(string[j][1]);//名称
			}

			if (null != string[j][2] && !"".equals(string[j][2])) {
				labReagentVo.setSize(string[j][2]);//规格
			}

			if (null != string[j][3] && !"".equals(string[j][3])) {
				labReagentVo.setPurity(string[j][3]);
			}

			if (null != string[j][4] && !"".equals(string[j][4])) {
				labReagentVo.setDangerSize(string[j][4]);
			}

			if (null != string[j][5] && !"".equals(string[j][5])) {
				LabOrgVo orgVo = new LabOrgVo();
				orgVo.setName(string[j][5]);
				LabOrg labOrg = (LabOrg) labReagentDAO
						.find("FROM LabOrg WHERE isDel='" + Constants_Source.N
								+ "' AND name like '%" + string[j][5] + "%'", 0);
				if (labOrg != null) {
					labReagentVo.setSaveOrg(labOrg.getId());
					labReagentVo.setSaveOrgName(labOrg.getName());
				}
			}
			try {
				this.addLabReagent(labReagentVo);
			} catch (Exception e) {
				log.error("导入试剂异常----" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return true;
	}

	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Override
	public void updateLabReagentList(LabReagentVo labReagentVo)
			throws GlobalException {
		String hql="FROM LabReagent WHERE 1=1 AND code is NULL and isDel='"+Constants_Source.N+"'";
		List<LabReagent> list=labReagentDAO.find(hql);
		
	}

	@Override
	public boolean deleteLabReagentByTypeId(String id) throws GlobalException {
		id=getTypeIdAndSubIdStrById(id);
		String hql="from LabReagent  WHERE isDel='"+Constants_Source.N+"' and reagentType.id in ('" + id.replace(",", "','")+ "')";
		List<LabReagent> list=labReagentDAO.find(hql);
		try {
			for(LabReagent po:list)
			{
				po.setIsDel(Constants_Source.Y);
				labReagentDAO.updateLabReagent(po);
			}
			return true;
		} catch (Exception e) {// TODO: handle exception
			log.error("试剂批量删除异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}


}
