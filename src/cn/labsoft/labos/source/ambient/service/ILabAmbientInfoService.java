package cn.labsoft.labos.source.ambient.service;

import java.util.List;

import cn.labsoft.labos.source.ambient.vo.LabAmbientInfoVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabAmbientInfoService {
	@SuppressWarnings("unchecked")
	public LabAmbientInfoVo addLabAmbientInfo(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException;;
	
	public boolean deleteLabAmbientInfo(String[] ids) throws GlobalException;
	
	public boolean updateLabAmbientInfo4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabAmbientInfo(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException;
	
	public LabAmbientInfoVo getLabAmbientInfo(String id) throws GlobalException;
	
	public List<LabAmbientInfoVo> getLabAmbientInfoList(LabAmbientInfoVo labAmbientInfoVo) throws GlobalException;
	
	public PageResult getLabAmbientInfoPR(LabAmbientInfoVo labAmbientInfoVo,PageResult pageResult) throws GlobalException;

	public PageResult getLabOverrunPR(LabAmbientInfoVo labAmbientInfoVo,
			PageResult pageResult) throws GlobalException;
	public List<LabAmbientInfoVo> getLabAmbientInfoName()throws GlobalException;
	
	public LabAmbientInfoVo getLabAmbientInfoInfo(LabAmbientInfoVo labAmbientInfoVo)throws GlobalException;
	
	public LabAmbientInfoVo getLabAmbientInfoByBeatchId(String beatchId) throws GlobalException;
	
     }
