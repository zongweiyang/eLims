package cn.labsoft.labos.source.ambient.service;

import java.util.List;
import cn.labsoft.labos.source.ambient.vo.LabAmbientVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabAmbientService {
	@SuppressWarnings("unchecked")
	public LabAmbientVo addLabAmbient(LabAmbientVo labAmbientVo) throws GlobalException;;
	
	public boolean deleteLabAmbient(String[] ids);
	
	public boolean updateLabAmbient4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabAmbient(LabAmbientVo labAmbientVo) throws GlobalException;
	
	public LabAmbientVo getLabAmbient(String id) throws GlobalException;
	
	public List<LabAmbientVo> getLabAmbientList(LabAmbientVo labAmbientVo) throws GlobalException;
	
	public PageResult getLabAmbientPR(LabAmbientVo labAmbientVo,PageResult pageResult) throws GlobalException;
	
	public String isDelete4LabAmbient(String[] ids)throws GlobalException;
	
     }
