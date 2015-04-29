package cn.labsoft.labos.source.ambient.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.ambient.dao.ILabAmbientDAO;
import cn.labsoft.labos.source.ambient.dao.ILabAmbientInfoDAO;
import cn.labsoft.labos.source.ambient.entity.LabAmbient;
import cn.labsoft.labos.source.ambient.entity.LabAmbientInfo;
import cn.labsoft.labos.source.ambient.service.ILabAmbientInfoService;
import cn.labsoft.labos.source.ambient.vo.LabAmbientInfoVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labAmbientInfoService")
public class LabAmbientInfoServiceImpl implements ILabAmbientInfoService {
	private ILabAmbientInfoDAO labAmbientInfoDAO;
	private ILabAmbientDAO labAmbientDAO;

	@Override
	public LabAmbientInfoVo addLabAmbientInfo(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException {
		// TODO Auto-generated method stub

		LabAmbientInfo labAmbientInfo = new LabAmbientInfo();
		labAmbientInfo = this.vo2Po(labAmbientInfoVo, labAmbientInfo);
		try {
			if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getSamBeatchId())) {
				String hql = "FROM LabAmbientInfo WHERE isDel='" + Constants_Source.N + "' AND samBeatchId='" + labAmbientInfoVo.getSamBeatchId() + "'";
				List<LabAmbientInfo> listLabAmbientInfo = labAmbientInfoDAO.find(hql);
				labAmbientInfoDAO.deleteAllLabAmbientInfo(listLabAmbientInfo);
			}
			labAmbientInfoDAO.addLabAmbientInfo(labAmbientInfo);
			labAmbientInfoVo.setId(labAmbientInfo.getId());
		} catch (Exception e) {
			Log4J.error("LabAmbientInfoServiceImpl addLabAmbientInfo  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labAmbientInfoVo;
	}

	@Override
	public boolean deleteLabAmbientInfo(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					LabAmbientInfo labAmbientInfo = labAmbientInfoDAO.getLabAmbientInfo(id);
					labAmbientInfo.setIsDel(Constants_Source.Y);
					labAmbientInfoDAO.updateLabAmbientInfo(labAmbientInfo);
				}
				//				key=labAmbientInfoDAO.deleteLabAmbientInfo(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabAmbientInfoServiceImpl deleteLabAmbientInfo  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabAmbientInfoVo getLabAmbientInfo(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbientInfoVo labAmbientInfoVo = new LabAmbientInfoVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabAmbientInfo labAmbientInfo = labAmbientInfoDAO.getLabAmbientInfo(id);
				labAmbientInfoVo = this.po2Vo(labAmbientInfo, labAmbientInfoVo);
			} catch (Exception e) {
				Log4J.error("LabAmbientInfoServiceImpl getLabAmbientInfo  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labAmbientInfoVo;
	}

	@Override
	public List<LabAmbientInfoVo> getLabAmbientInfoList(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException {
		// TODO Auto-generated method stub
		String wereHql = "";

		return this.getLabAmbientInfoVoListByWhere(wereHql);
	}

	@Override
	public PageResult getLabAmbientInfoPR(LabAmbientInfoVo labAmbientInfoVo, PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabAmbientInfo WHERE isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getLabAmbientName()))
			hql += " AND labAmbient.name LIKE '%" + labAmbientInfoVo.getLabAmbientName() + "%'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getStartTime()))
			hql += " AND time>='" + labAmbientInfoVo.getStartTime() + "'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getEndTime()))
			hql += " AND time<='" + labAmbientInfoVo.getEndTime() + "'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getLabAmbientId()))
			hql += " AND labAmbient.id='" + labAmbientInfoVo.getLabAmbientId() + "'";
		pageResult = labAmbientInfoDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabAmbientInfoVo> listLabAmbientInfoVo = new ArrayList<LabAmbientInfoVo>();
			List<LabAmbientInfo> listLabAmbientInfo = new ArrayList<LabAmbientInfo>();
			listLabAmbientInfo = pageResult.getResultList();
			for (LabAmbientInfo labAmbientInfo : listLabAmbientInfo) {
				LabAmbientInfoVo vo = new LabAmbientInfoVo();
				vo = this.po2Vo(labAmbientInfo, vo);
				listLabAmbientInfoVo.add(vo);
			}
			pageResult.setResultList(listLabAmbientInfoVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabAmbientInfo(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbientInfo labAmbientInfo = new LabAmbientInfo();
		boolean key = true;
		try {
			labAmbientInfo = this.vo2Po(labAmbientInfoVo, labAmbientInfo);
			labAmbientInfoDAO.updateLabAmbientInfo(labAmbientInfo);
		} catch (Exception e) {
			key = false;
			Log4J.error("LabAmbientInfoServiceImpl updateLabAmbientInfo  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabAmbientInfo4Del(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabAmbientInfo labAmbientInfo = labAmbientInfoDAO.getLabAmbientInfo(id);
					labAmbientInfo.setIsDel(Constants_Source.Y);
					labAmbientInfoDAO.updateLabAmbientInfo(labAmbientInfo);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error("LabAmbientInfoServiceImpl updateLabAmbientInfo4Del  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	public List<LabAmbientInfoVo> getLabAmbientInfoVoListByWhere(String wereHql) throws GlobalException {
		List<LabAmbientInfoVo> listLabAmbientInfoVo = new ArrayList<LabAmbientInfoVo>();
		String hql = "FROM LabAmbientInfo WHERE isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabAmbientInfo> listLabAmbientInfo = labAmbientInfoDAO.find(hql);
		if (listLabAmbientInfo != null && listLabAmbientInfo.size() > 0) {
			for (LabAmbientInfo labAmbientInfo : listLabAmbientInfo) {
				LabAmbientInfoVo labAmbientInfoVo = new LabAmbientInfoVo();
				labAmbientInfoVo = this.po2Vo(labAmbientInfo, labAmbientInfoVo);
				listLabAmbientInfoVo.add(labAmbientInfoVo);
			}
		}
		return listLabAmbientInfoVo;
	}

	public LabAmbientInfo vo2Po(LabAmbientInfoVo labAmbientInfoVo, LabAmbientInfo labAmbientInfo) {
		BeanUtils.copyProperties(labAmbientInfoVo, labAmbientInfo, new String[] { "isDel", "labAmbientId" });
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getLabAmbientId())) {
			LabAmbient labAmbient = (LabAmbient) labAmbientDAO.findById(LabAmbient.class, labAmbientInfoVo.getLabAmbientId());
			if (labAmbient != null)
				labAmbientInfo.setLabAmbient(labAmbient);
		}
		return labAmbientInfo;
	}

	public LabAmbientInfoVo po2Vo(LabAmbientInfo labAmbientInfo, LabAmbientInfoVo labAmbientInfoVo) {
		BeanUtils.copyProperties(labAmbientInfo, labAmbientInfoVo, new String[] { "labAmbient" });
		if (labAmbientInfo.getLabAmbient() != null) {
			labAmbientInfoVo.setLabAmbientName(labAmbientInfo.getLabAmbient().getName());
			labAmbientInfoVo.setLabAmbientUnit(labAmbientInfo.getLabAmbient().getUnit());
			labAmbientInfoVo.setLabAmbientId(labAmbientInfo.getLabAmbient().getId());
		}
		return labAmbientInfoVo;
	}

	@Override
	public PageResult getLabOverrunPR(LabAmbientInfoVo labAmbientInfoVo, PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabAmbientInfo WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND (value<labAmbient.downValue OR value>labAmbient.upValue)";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getStartTime()))
			hql += " AND time>='" + labAmbientInfoVo.getStartTime() + "'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getEndTime()))
			hql += " AND time<='" + labAmbientInfoVo.getEndTime() + "'";
		pageResult = labAmbientInfoDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabAmbientInfoVo> listLabAmbientInfoVo = new ArrayList<LabAmbientInfoVo>();
			List<LabAmbientInfo> listLabAmbientInfo = new ArrayList<LabAmbientInfo>();
			listLabAmbientInfo = pageResult.getResultList();
			for (LabAmbientInfo labAmbientInfo : listLabAmbientInfo) {
				LabAmbientInfoVo vo = new LabAmbientInfoVo();
				vo = this.po2Vo(labAmbientInfo, vo);
				listLabAmbientInfoVo.add(vo);
			}
			pageResult.setResultList(listLabAmbientInfoVo);
		}
		return pageResult;
	}

	@Override
	public List<LabAmbientInfoVo> getLabAmbientInfoName() throws GlobalException {
		// TODO Auto-generated method stub
		String wereHql = " AND 1=1 group by labAmbient.id";
		List<LabAmbientInfoVo> listLabAmbientInfoVo = this.getLabAmbientInfoVoListByWhere(wereHql);
		return listLabAmbientInfoVo;
	}

	@Override
	public LabAmbientInfoVo getLabAmbientInfoInfo(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbient labAmbient = (LabAmbient) labAmbientDAO.findById(LabAmbient.class, labAmbientInfoVo.getLabAmbientId());
		String whereHql = " AND labAmbient.id='" + labAmbientInfoVo.getLabAmbientId() + "' ";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getStartTime()))
			whereHql += " AND time>='" + labAmbientInfoVo.getStartTime() + "'";
		if (!StrUtils.isBlankOrNull(labAmbientInfoVo.getEndTime()))
			whereHql += " AND time<='" + labAmbientInfoVo.getEndTime() + "'";
		whereHql += "  ORDER BY TIME ";
		String time = "";
		String values = "";
		String upValues = "";
		String downValues = "";
		List<LabAmbientInfoVo> listLabAmbientInfoVo = this.getLabAmbientInfoVoListByWhere(whereHql);
		if (listLabAmbientInfoVo != null && listLabAmbientInfoVo.size() > 0) {
			for (LabAmbientInfoVo vo : listLabAmbientInfoVo) {
				time += vo.getTime();
				values += vo.getValue();
				upValues += labAmbient.getUpValue();
				downValues += labAmbient.getDownValue();
				time += ",";
				values += ",";
				upValues += ",";
				downValues += ",";
			}
			if (!StrUtils.isBlankOrNull(time)) {
				time = time.substring(0, time.length() - 1);
				values = values.substring(0, values.length() - 1);
				upValues = upValues.substring(0, upValues.length() - 1);
				downValues = downValues.substring(0, downValues.length() - 1);
				labAmbientInfoVo.setUpValues(upValues);
				labAmbientInfoVo.setDownValues(downValues);
				labAmbientInfoVo.setTimes(time);
				labAmbientInfoVo.setValues(values);
				labAmbientInfoVo.setLabAmbientName(labAmbient.getName());
				labAmbientInfoVo.setLabAmbientUnit(labAmbient.getUnit());
			}

		}

		return labAmbientInfoVo;
	}

	@Resource
	public void setLabAmbientInfoDAO(ILabAmbientInfoDAO labAmbientInfoDAO) {
		this.labAmbientInfoDAO = labAmbientInfoDAO;
	}

	@Resource
	public void setLabAmbientDAO(ILabAmbientDAO labAmbientDAO) {
		this.labAmbientDAO = labAmbientDAO;
	}

	@Override
	public LabAmbientInfoVo getLabAmbientInfoByBeatchId(String beatchId) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbientInfoVo labAmbientInfoVo = new LabAmbientInfoVo();
		if (!StrUtils.isBlankOrNull(beatchId)) {
			String hql = "FROM LabAmbientInfo WHERE isDel='" + Constants_Source.N + "' AND samBeatchId='" + beatchId + "'";
			LabAmbientInfo labAmbientInfo = (LabAmbientInfo) labAmbientInfoDAO.find(hql, 0);
			if (labAmbientInfo != null) {
				BeanUtils.copyProperties(labAmbientInfo, labAmbientInfoVo, new String[] { "labAmbient" });
				labAmbientInfoVo.setLabAmbientId(labAmbientInfo.getLabAmbient().getId());
				labAmbientInfoVo.setLabAmbientName(labAmbientInfo.getLabAmbient().getName());
				labAmbientInfoVo.setLabAmbientUnit(labAmbientInfo.getLabAmbient().getUnit());
			}
		}
		return labAmbientInfoVo;
	}

}
