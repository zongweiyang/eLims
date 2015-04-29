package cn.labsoft.labos.common.number.service;

import java.util.List;

import cn.labsoft.labos.common.number.vo.LabNumberRecordVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabNumberRecordService {
	@SuppressWarnings("unchecked")
	public LabNumberRecordVo addLabNumberRecord(LabNumberRecordVo labNumberRecordVo) throws GlobalException;;
	
	public boolean deleteLabNumberRecord (String[] ids) throws GlobalException;
	
	public boolean updateLabNumberRecord4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabNumberRecord (LabNumberRecordVo labNumberRecordVo) throws GlobalException;
	
	public LabNumberRecordVo getLabNumberRecord(String id) throws GlobalException;
	
	public List<LabNumberRecordVo> getLabNumberRecordList(LabNumberRecordVo labNumberRecordVo) throws GlobalException;
	
	public PageResult getLabNumberRecordPR(LabNumberRecordVo labNumberRecordVo,PageResult pageResult) throws GlobalException;
     }
