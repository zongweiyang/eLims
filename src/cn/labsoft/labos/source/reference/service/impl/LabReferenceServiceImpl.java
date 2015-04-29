package cn.labsoft.labos.source.reference.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabRefType;
import cn.labsoft.labos.source.reference.entity.LabReference;
import cn.labsoft.labos.source.reference.service.ILabReferenceService;
import cn.labsoft.labos.source.reference.vo.LabReferenceVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labReferenceService")
public class LabReferenceServiceImpl extends BaseService implements
		ILabReferenceService {

	private ILabReferenceDAO labReferenceDAO;
	private ILabUploadDAO labUploadDAO;
	@Resource
	public void setLabReferenceDAO(ILabReferenceDAO labReferenceDAO) {
		this.labReferenceDAO = labReferenceDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Override
	public LabReferenceVo getLabReferenceById(String id) throws GlobalException {
		LabReference labReference = (LabReference) labReferenceDAO.findById(
				LabReference.class, id);
		return PoToVo(labReference);
	}

	@Override
	public PageResult getLabReferencePR(LabReferenceVo labReferenceVo,
			PageResult pageResult) throws GlobalException {
		String hql = " FROM LabReference WHERE 1=1 AND isDel='" + Constants_Source.N
				+ "'";
		if (!StrUtils.isBlankOrNull(labReferenceVo.getCode())) {
			hql += " AND code LIKE '%" + labReferenceVo.getCode().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getName())) {
			hql += " AND name LIKE '%" + labReferenceVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getSize())) {
			hql += " AND size like '%" + labReferenceVo.getSize().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getMaxSize())) {
			hql += " AND amount <= '" + labReferenceVo.getMaxSize().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getMinSize())) {
			hql += " AND amount >= '" + labReferenceVo.getMinSize().trim() + "'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getReferenceTypeId())&&!StrUtils.isBlankOrNull(labReferenceVo.getReferenceTypeId().trim())) {
			String type = getTypeIdAndSubIdStrById(labReferenceVo
					.getReferenceTypeId().trim());
			hql += " AND referenceType.id IN ('" + type.replace(",", "','")
					+ "')";
		}
		pageResult = labReferenceDAO.getPageResult4All(hql, pageResult);
		List<LabReference> labReferenceList = pageResult.getResultList();
		List<LabReferenceVo> voList = new ArrayList<LabReferenceVo>();
		if (labReferenceList.size() > 0) {
			for (LabReference po : labReferenceList) {
				voList.add(PoToVo(po));
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	public String getTypeIdAndSubIdStrById(String id) throws GlobalException {
		String idStr = id + ",";
		List<LabRefType> list = labReferenceDAO
				.find("from LabRefType where type.id='" + id.trim()
						+ "' and isDel='" + Constants_Source.N + "'");
		if (null != list && list.size() > 0) {
			for (LabRefType labRefType : list) {
				idStr += getTypeIdAndSubIdStrById(labRefType.getId());
			}
		}
		return idStr;
	}

	private LabReferenceVo PoToVo(LabReference po) throws GlobalException {
		LabReferenceVo vo = new LabReferenceVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "referenceType" });
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("Po转Vo异常--" + e.getMessage());
		}
		if (null != po.getReferenceType()) {
			vo.setReferenceTypeId(po.getReferenceType().getId());
			vo.setReferenceTypeName(po.getReferenceType().getName());
		}
		if (null != po.getSaveOrg() && !"".equals(po.getSaveOrg())) {
			LabOrg labOrg = (LabOrg) labReferenceDAO.findById(LabOrg.class, po
					.getSaveOrg());
			vo.setSaveOrg(labOrg.getId());
			vo.setSaveOrgName(labOrg.getName());
		}
		if (po.getSize() != null) {
			vo.setSpecifications(po.getSize());
		}
		return vo;
	}

	private static LabReference VoToPo(LabReferenceVo vo)
			throws GlobalException {
		LabReference po = new LabReference();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "referenceTypeId",
					"referenceTypeName" });
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("vo转Po异常--" + e.getMessage());
		}
		if (null != vo.getReferenceTypeId()) {
			LabRefType type = new LabRefType();
			type.setId(vo.getReferenceTypeId().trim());
			po.setReferenceType(type);
		}
		return po;
	}

	@Override
	public boolean addLabReference(LabReferenceVo labReferenceVo)
			throws GlobalException {
		try {
			LabReference po = VoToPo(labReferenceVo);
			labReferenceDAO.addLabReference(po);

			List<LabUpload> loadList = labUploadDAO.getLabUploadList(
					labReferenceVo.getUuid(), "lab-reference");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			return true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("标准品保存异常--" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabReference(String id) throws GlobalException {
		try {
			LabReference labReference = (LabReference) labReferenceDAO
					.findById(LabReference.class, id);
			labReference.setIsDel(Constants_Source.Y);
			labReferenceDAO.updateLabReference(labReference);
			return true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("标准品删除异常--" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabReference(LabReferenceVo labReferenceVo)
			throws GlobalException {
		try {
			LabReference po = VoToPo(labReferenceVo);
			labReferenceDAO.updateLabReference(po);
			return true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("标准品更新异常--" + e.getMessage());
		}
	}

	@Override
	public List<LabReferenceVo> getReferenceListByTypeId(String pid)
			throws GlobalException {
		String hql = " FROM LabReference WHERE referenceType.id='" + pid
				+ "' AND isDel='" + Constants_Source.N + "'";
		List<LabReference> poList = labReferenceDAO.find(hql);
		List<LabReferenceVo> voList = new ArrayList<LabReferenceVo>();
		for (LabReference po : poList) {
			voList.add(PoToVo(po));
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabReferenceVo> getLabReferenceList(
			LabReferenceVo labReferenceVo) throws GlobalException {
		String hql = " FROM LabReference WHERE  isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labReferenceVo.getReferenceTypeId())) {
			hql += " AND referenceType in ('"
					+ labReferenceVo.getReferenceTypeId().trim() + "')";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getName())) {
			hql += " AND name='%" + labReferenceVo.getName().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getUnit())) {
			hql += " AND unit='%" + labReferenceVo.getUnit().trim() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getIsSafe())
				&& labReferenceVo.getIsSafe().equals("Y")) {
			hql += " AND safeAmount>=amount ";
		}
		if (!StrUtils.isBlankOrNull(labReferenceVo.getId())) {
			String[] ids = labReferenceVo.getId().split(",");
			String id = "";

			for (int i = 0; i < ids.length - 1; i++) {
				id = "'" + ids[i] + "',";
			}
			id += "'" + ids[ids.length - 1] + "'";

			hql += " OR (id IN (" + id + ") AND isDel='" + Constants_Source.N + "')";
		}
		List<LabReference> poList = labReferenceDAO.find(hql);
		List<LabReferenceVo> voList = new ArrayList<LabReferenceVo>();
		if (poList.size() > 0) {
			for (LabReference po : poList) {
				voList.add(PoToVo(po));
			}
		}

		return voList;
	}

	@Override
	public List<LabReferenceVo> getLabReferenceListByIds(String ids)
			throws GlobalException {
		String hql = " FROM LabReference WHERE isDel='" + Constants_Source.N
				+ "' AND id IN(";
		String[] id = ids.split(",");
		for (int i = 0; i < id.length - 1; i++) {
			hql += "'" + id[i] + "',";
		}
		hql += "'" + id[id.length - 1] + "')";
		List<LabReference> labList = labReferenceDAO.find(hql);
		List<LabReferenceVo> labReferenceVoList = new ArrayList<LabReferenceVo>();
		if (labList.size() > 0) {
			for (LabReference labReference : labList) {
				labReferenceVoList.add(PoToVo(labReference));
			}
		}
		return labReferenceVoList;
	}

	@Override
	public LabReferenceVo updateLabReferenceMove(LabReferenceVo labReferenceVo) throws GlobalException {
		LabReference labReference = (LabReference) labReferenceDAO.findById(
				LabReference.class, labReferenceVo.getId());
		LabRefType labRefType = new LabRefType();
		labRefType=(LabRefType) labReferenceDAO.findById(LabRefType.class, labReferenceVo.getReferenceTypeId());
		labReference.setReferenceType(labRefType);
		try {
			labReferenceDAO.updateLabReference(labReference);
		} catch (Exception e) {
			Log4J.error("标准品迁移异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labReferenceVo;
	}

	@Override
	public boolean addLabReference4Import(String[][] value,String typeId) throws GlobalException {
		LabReferenceVo labReferenceVo = new LabReferenceVo();
		if (null != value && value.length > 0) {
			for (int j = 2; j < value.length; j++) {
				labReferenceVo.setIsDel(Constants_Source.N);
				if (!StrUtils.isBlankOrNull(typeId)) {
						labReferenceVo.setReferenceTypeId(typeId);
					} else {
						labReferenceVo.setReferenceTypeId("0");
				}
				if (null != value[j][0] && !"".equals(value[j][0])) {
					labReferenceVo.setCode(value[j][0]);
				}
				if (null != value[j][1] && !"".equals(value[j][1])) {
					labReferenceVo.setName(value[j][1]);
				}

				if (null != value[j][2] && !"".equals(value[j][2])) {
					labReferenceVo.setSize(value[j][2]);
				}

				if (null != value[j][3] && !"".equals(value[j][3])) {
					labReferenceVo.setPurity(value[j][3]);
				}

				if (null != value[j][4] && !"".equals(value[j][4])) {
					labReferenceVo.setDangerSize(value[j][4]);
				}
				if (null != value[j][5] && !"".equals(value[j][5])) {
					LabOrg labOrg = (LabOrg) labReferenceDAO
							.find(
									"from LabOrg where isDel='" + Constants_Source.N
											+ "' and name like '%"
											+ value[j][5] + "%'", 0);
					if (labOrg != null) {
						labReferenceVo.setSaveOrg(labOrg.getId());
						labReferenceVo.setSaveOrgName(labOrg.getName());
					}
				}
				try {
					this.addLabReference(labReferenceVo);
				} catch (Exception e) {
					Log4J.error("标准品---" + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteLabReferenceByTypeId(String id) throws GlobalException {
		id=getTypeIdAndSubIdStrById(id);
		String hql="from LabReference  WHERE isDel='"+Constants_Source.N+"' and referenceType.id in ('" + id.replace(",", "','")+ "')";
		List<LabReference> list=labReferenceDAO.find(hql);
		try {
			for(LabReference po:list)
			{
				po.setIsDel(Constants_Source.Y);
				labReferenceDAO.updateLabReference(po);
			}
			return true;
		} catch (Exception e) {// TODO: handle exception
			Log4J.error("耗材批量删除异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}
}
