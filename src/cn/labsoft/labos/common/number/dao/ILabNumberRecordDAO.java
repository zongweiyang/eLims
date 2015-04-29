package cn.labsoft.labos.common.number.dao;

import java.util.List;

import cn.labsoft.labos.common.number.entity.LabNumberRecord;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabNumberRecordDAO extends IBaseDAO {
	
	public LabNumberRecord addLabNumberRecord(LabNumberRecord labNumberRecord) throws GlobalException;
	
	public boolean deleteLabNumberRecord(String ids[])throws GlobalException;
	
	public boolean updateLabNumberRecord(LabNumberRecord labNumberRecord)throws GlobalException;
	
	public LabNumberRecord getLabNumberRecord(String id)throws GlobalException;
	
	public PageResult getLabNumberRecordPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabNumberRecord> getLabNumberRecordList(String hql)throws GlobalException;

	public void deleteAll(List<LabNumberRecord> listLabNumberRecord)throws GlobalException;
	
}
