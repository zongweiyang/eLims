package cn.labsoft.labos.source.ambient.dao;

import java.util.List;

import cn.labsoft.labos.source.ambient.entity.LabAmbientInfo;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabAmbientInfoDAO extends IBaseDAO {
	
	public LabAmbientInfo addLabAmbientInfo(LabAmbientInfo labAmbientInfo) throws GlobalException;
	
	public boolean deleteLabAmbientInfo(String ids[])throws GlobalException;
	
	public boolean updateLabAmbientInfo(LabAmbientInfo labAmbientInfo)throws GlobalException;
	
	public LabAmbientInfo getLabAmbientInfo(String id)throws GlobalException;
	
	public PageResult getLabAmbientInfoPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabAmbientInfo> getLabAmbientInfoList(String hql)throws GlobalException;

	public boolean deleteAllLabAmbientInfo(List<LabAmbientInfo> listLabAmbientInfo)throws GlobalException;

}
