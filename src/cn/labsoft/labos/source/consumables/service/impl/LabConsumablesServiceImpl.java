package cn.labsoft.labos.source.consumables.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.consumables.dao.ILabConsumablesDAO;
import cn.labsoft.labos.source.consumables.entity.LabConType;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;
import cn.labsoft.labos.source.consumables.service.ILabConsumablesService;
import cn.labsoft.labos.source.consumables.vo.LabConsumablesVo;
import cn.labsoft.labos.utils.StrUtils;
@Service("labConsumablesService")
public class LabConsumablesServiceImpl extends BaseService implements
		ILabConsumablesService {

	private ILabConsumablesDAO labConsumablesDAO;
	private ILabUploadDAO labUploadDAO;

	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabConsumablesDAO(ILabConsumablesDAO labConsumablesDAO) {
		this.labConsumablesDAO = labConsumablesDAO;
	} 
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Override
	public LabConsumablesVo getLabConsumablesById(String id)
			throws GlobalException {
		LabConsumables labConsumables = (LabConsumables) labConsumablesDAO
				.findById(LabConsumables.class, id);
		return PoToVo(labConsumables);
	}

	@Override
	public PageResult getLabConsumablesPR(LabConsumablesVo labConsumablesVo,
			PageResult pageResult) throws GlobalException {
		String hql = " FROM LabConsumables WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getCode())) {
			hql += " AND code LIKE '%" + labConsumablesVo.getCode().trim()
					+ "%'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getName())) {
			hql += " AND name LIKE '%" + labConsumablesVo.getName().trim()
					+ "%'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getSize())) {
			hql += " AND size='" + labConsumablesVo.getSize().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getMaxSize())) {
			hql += " AND amount<='" + labConsumablesVo.getMaxSize().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getMinSize())) {
			hql += " AND amount>='" + labConsumablesVo.getMinSize().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getConsumablesTypeId())&&!StrUtils.isBlankOrNull(labConsumablesVo.getConsumablesTypeId()
				.trim())) {
			String type = getTypeIdAndSubIdStrById(labConsumablesVo
					.getConsumablesTypeId().trim());
			hql += " AND consumablesType.id IN ('" + type.replace(",", "','")+ "')";
		}
		pageResult = labConsumablesDAO.getPageResult4All(hql, pageResult);
		List<LabConsumables> labConsumablesList = pageResult.getResultList();
		List<LabConsumablesVo> voList = new ArrayList<LabConsumablesVo>();
		if (labConsumablesList.size() > 0) {
			for (LabConsumables po : labConsumablesList) {
				voList.add(PoToVo(po));
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	public String getTypeIdAndSubIdStrById(String id) throws GlobalException {
		String idStr = id + ",";
		List<LabConType> list = labConsumablesDAO
				.find("from LabConType where type.id='" + id.trim()
						+ "' and isDel='" + Constants_Source.N + "'");
		if (null != list && list.size() > 0) {
			for (LabConType labConType : list) {
				idStr += getTypeIdAndSubIdStrById(labConType.getId());
			}
		}
		return idStr;
	}

	private LabConsumablesVo PoToVo(LabConsumables po) throws GlobalException {
		LabConsumablesVo vo = new LabConsumablesVo();
		try {
			BeanUtils
					.copyProperties(po, vo, new String[] { "consumablesType" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getConsumablesType()) {
			vo.setConsumablesTypeId(po.getConsumablesType().getId());
			vo.setConsumablesTypeName(po.getConsumablesType().getName());
		}
		if (null != po.getSaveOrg() && !"".equals(po.getSaveOrg())) {
			LabOrg labOrg = (LabOrg) labConsumablesDAO.findById(LabOrg.class,
					po.getSaveOrg());
			vo.setSaveOrg(labOrg.getId());
			vo.setSaveOrgName(labOrg.getName());
		}
		if (po.getSize() != null) {
			vo.setSpecifications(po.getSize());
		}
		return vo;
	}

	private static LabConsumables VoToPo(LabConsumablesVo vo)
			throws GlobalException {
		LabConsumables po = new LabConsumables();
		try {
			BeanUtils.copyProperties(vo, po, new String[] {
					"consumablesTypeId", "consumablesTypeName" });
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("vo转Po异常--" + e.getMessage());
		}
		if (null != vo.getConsumablesTypeId()) {
			LabConType type = new LabConType();
			type.setId(vo.getConsumablesTypeId().trim());
			po.setConsumablesType(type);
		}
		return po;
	}

	@Override
	public boolean addLabConsumables(LabConsumablesVo labConsumablesVo)
			throws GlobalException {
		try {
			LabConsumables po = VoToPo(labConsumablesVo);
			labConsumablesDAO.addLabConsumables(po);

			List<LabUpload> loadList = labUploadDAO.getLabUploadList(
					labConsumablesVo.getUuid(), "lab-consumables");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("耗材保存异常--" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabConsumables(String id) throws GlobalException {
		try {
			LabConsumables labConsumables = (LabConsumables) labConsumablesDAO
					.findById(LabConsumables.class, id);
			labConsumables.setIsDel(Constants_Source.Y);
			labConsumablesDAO.updateLabConsumables(labConsumables);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("耗材删除异常--" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabConsumables(LabConsumablesVo labConsumablesVo)
			throws GlobalException {
		try {
			LabConsumables po = VoToPo(labConsumablesVo);
			labConsumablesDAO.updateLabConsumables(po);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("耗材更新异常--" + e.getMessage());
		}
	}

	@Override
	public List<LabConsumablesVo> getConsumablesListByTypeId(String pid)
			throws GlobalException {
		String hql = " FROM LabConsumables WHERE consumablesType.id='" + pid
				+ "' AND isDel='" + Constants_Source.N + "'";
		List<LabConsumables> poList = labConsumablesDAO.find(hql);
		List<LabConsumablesVo> voList = new ArrayList<LabConsumablesVo>();
		if (poList.size() > 0) {
			for (LabConsumables po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabConsumablesList(LabConsumablesVo labConsumablesVo,PageResult pageResult) throws GlobalException {
		String hql = " FROM LabConsumables WHERE  isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getConsumablesTypeId())) {
			hql += " AND consumablesType in ('"
					+ labConsumablesVo.getConsumablesTypeId().trim() + "')";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getName())) {
			hql += " AND name='%" + labConsumablesVo.getName().trim()+ "%'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getUnit())) {
			hql += " AND unit='%" + labConsumablesVo.getUnit().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getIsSafe())
				&& labConsumablesVo.getIsSafe().equals("Y")) {
			hql += " AND safeAmount>=amount ";
		}
		if (!StrUtils.isBlankOrNull(labConsumablesVo.getId())) {
			String[] ids = labConsumablesVo.getId().split(",");
			String id = "";

			for (int i = 0; i < ids.length - 1; i++) {
				id = "'" + ids[i] + "',";
			}
			id += "'" + ids[ids.length - 1] + "'";

			hql += " OR (id IN (" + id + ") AND isDel='" + Constants_Source.N + "')";
		}
		pageResult=labConsumablesDAO.getPageResult(hql, pageResult);
		List<LabConsumables> poList = pageResult.getResultList();
		List<LabConsumablesVo> voList = new ArrayList<LabConsumablesVo>();
		if (poList.size() > 0) {
			for (LabConsumables po : poList) {
				voList.add(PoToVo(po));
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public List<LabConsumablesVo> getLabConsumablesListByIds(String ids)
			throws GlobalException {
		String hql = " FROM LabConsumables WHERE isDel='" + Constants_Source.N
				+ "' AND id IN(";
		String[] id = ids.split(",");
		for (int i = 0; i < id.length - 1; i++) {
			hql += "'" + id[i] + "',";
		}
		hql += "'" + id[id.length - 1] + "')";
		List<LabConsumables> labList = labConsumablesDAO.find(hql);
		List<LabConsumablesVo> labConsumablesVoList = new ArrayList<LabConsumablesVo>();
		if (labList.size() > 0) {
			for (LabConsumables labConsumables : labList) {
				labConsumablesVoList.add(PoToVo(labConsumables));
			}
		}
		return labConsumablesVoList;
	}

	@Override
	public LabConsumablesVo updateLabConsumablesMove(
			LabConsumablesVo labConsumablesVo) throws GlobalException {
		LabConsumables labConsumables = (LabConsumables) labConsumablesDAO
				.findById(LabConsumables.class, labConsumablesVo.getId());
		LabConType labConType = new LabConType();
		labConType.setId(labConsumablesVo.getConsumablesTypeId());
		labConsumables.setConsumablesType(labConType);
		try {
			labConsumablesDAO.updateLabConsumables(labConsumables);
		} catch (Exception e) {
			log.error("耗材迁移异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labConsumablesVo;
	}

	@Override
	public boolean addLabComsumables4Import(String[][] value,String typeId)throws GlobalException {
		LabConsumablesVo labConsumablesVo = new LabConsumablesVo();
		if (null != value && value.length > 0) {
			for (int j = 2; j < value.length; j++) {
				labConsumablesVo.setIsDel(Constants_Source.N);
				if (!StrUtils.isBlankOrNull(typeId)) {
						labConsumablesVo.setConsumablesTypeId(typeId);
				}else{
						labConsumablesVo.setConsumablesTypeId("0");
				}

				if (null != value[j][0] && !"".equals(value[j][0])) {
					labConsumablesVo.setCode(value[j][0]);
				}
				if (null != value[j][1] && !"".equals(value[j][1])) {
					labConsumablesVo.setName(value[j][1]);
				}

				if (null != value[j][2] && !"".equals(value[j][2])) {
					labConsumablesVo.setSize(value[j][2]);
				}

				if (null != value[j][3] && !"".equals(value[j][3])) {
					labConsumablesVo.setDangerSize(value[j][3]);
				}

				if (null != value[j][4] && !"".equals(value[j][4])) {
					LabOrg labOrg = (LabOrg) labConsumablesDAO
							.find(
									"from LabOrg where isDel='" + Constants_Source.N
											+ "' and name like '%"
											+ value[j][4] + "%'", 0);
					if (labOrg != null) {
						labConsumablesVo.setSaveOrg(labOrg.getId());
						labConsumablesVo.setSaveOrgName(labOrg.getName());
					}
				}

				try {
					this.addLabConsumables(labConsumablesVo);
				} catch (Exception e) {
					log.error("--批量导入耗材异常----" + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteLabConsumablesByTypeId(String id) throws GlobalException{
		id=getTypeIdAndSubIdStrById(id);
		String hql="from LabConsumables  WHERE isDel='"+Constants_Source.N+"' and consumablesType.id IN ('" + id.replace(",", "','")+ "')";
		List<LabConsumables> list=labConsumablesDAO.find(hql);
		try {
			for(LabConsumables po:list)
			{
				po.setIsDel(Constants_Source.Y);
				labConsumablesDAO.updateLabConsumables(po);
			}
			return true;
		} catch (Exception e) {// TODO: handle exception
			log.error("耗材批量删除异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}
}
