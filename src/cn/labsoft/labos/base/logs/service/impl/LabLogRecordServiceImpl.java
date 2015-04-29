package cn.labsoft.labos.base.logs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.logs.dao.ILabLogRecordDAO;
import cn.labsoft.labos.base.logs.entity.LabLogRecord;
import cn.labsoft.labos.base.logs.service.ILabLogRecordService;
import cn.labsoft.labos.base.logs.vo.LabLogRecordVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labLogRecordService")
public class LabLogRecordServiceImpl implements ILabLogRecordService {
	private ILabLogRecordDAO labLogRecordDAO;

	@Resource
	public void setLabLogRecordDAO(ILabLogRecordDAO labLogRecordDAO) {
		this.labLogRecordDAO = labLogRecordDAO;
	}

	@SuppressWarnings("unchecked")
	public PageResult getlabLogrecordPR(LabLogRecordVo labLogrecordVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabLogRecord WHERE 1=1";
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getIp())) {
			hql += " AND ip like'%" + labLogrecordVo.getIp() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getContent())) {
			hql += " AND content like'%" + labLogrecordVo.getContent() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getModule())) {
			hql += " AND module like'%" + labLogrecordVo.getModule() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getWorkTable())) {
			hql += " AND workTable like'%" + labLogrecordVo.getWorkTable() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getOperator())) {
			hql += " AND operator like'%" + labLogrecordVo.getOperator() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getMethod())) {
			hql += " AND method like'%" + labLogrecordVo.getMethod() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getStartTime())) {
			hql += " AND dateTime >='" + labLogrecordVo.getStartTime() + "'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getEndTime())) {
			hql += " AND dateTime <='" + labLogrecordVo.getEndTime() + "'";
		}
		// hql+=" ORDER BY dateTime DESC";
		pageResult = labLogRecordDAO.getPageResult(hql, pageResult);
		List<LabLogRecord> list = pageResult.getResultList();
		List<LabLogRecordVo> result = new ArrayList<LabLogRecordVo>();
		if ((list != null) && (list.size() > 0)) {
			for (LabLogRecord po : list) {
				LabLogRecordVo vo = new LabLogRecordVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				String content = vo.getContent();
				content = content.replace(",<null>", "");
				vo.setContent(content);
				result.add(vo);
			}
		}
		pageResult.setResultList(result);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabLogRecordVo> getLabLogrecordList(LabLogRecordVo labLogrecordVo) throws GlobalException {
		List<LabLogRecordVo> labLogRecordList = new ArrayList<LabLogRecordVo>();
		String hql = "FROM LabLogRecord WHERE 1=1";
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getIp())) {
			hql += " AND ip like'%" + labLogrecordVo.getIp() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getContent())) {
			hql += " AND content like'%" + labLogrecordVo.getContent() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getModule())) {
			hql += " AND module like'%" + labLogrecordVo.getModule() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getWorkTable())) {
			hql += " AND workTable like'%" + labLogrecordVo.getWorkTable() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getOperator())) {
			hql += " AND operator like'%" + labLogrecordVo.getOperator() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getMethod())) {
			hql += " AND method like'%" + labLogrecordVo.getMethod() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getStartTime())) {
			hql += " AND dateTime >='" + labLogrecordVo.getStartTime() + "'";
		}
		if (!StrUtils.isBlankOrNull(labLogrecordVo.getEndTime())) {
			hql += " AND dateTime <='" + labLogrecordVo.getEndTime() + "'";
		}
		hql += " ORDER BY dateTime DESC";
		List<LabLogRecord> logrecordList = labLogRecordDAO.find(hql);
		if ((logrecordList != null) && (logrecordList.size() > 0)) {
			for (LabLogRecord po : logrecordList) {
				LabLogRecordVo vo = new LabLogRecordVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				String content = vo.getContent();
				content = content.replace(",<null>", "");
				vo.setContent(content);
				vo.setCreateTime(DateUtils.format(po.getDateTime(), DateUtils.formatStr_yyyyMMddHHmmss));
				labLogRecordList.add(vo);
			}
		}
		return labLogRecordList;
	}

	@Override
	public LabLogRecordVo addLabLogrecord4Bus(String content, String busId, String busName, String busType, String operator) throws GlobalException {
		LabLogRecordVo vo = null;
		LabLogRecord labLogrecord = labLogRecordDAO.addLabLogrecord4Bus(content, busId, busName, busType, operator);
		if (labLogrecord != null) {
			vo = new LabLogRecordVo();
			BeanUtils.copyProperties(labLogrecord, vo, new String[] {});
		}
		return vo;
	}

}