package cn.labsoft.labos.common.number.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.number.dao.ILabNumberRecordDAO;
import cn.labsoft.labos.common.number.entity.LabNumberRecord;
import cn.labsoft.labos.common.number.service.ILabNumberRecordService;
import cn.labsoft.labos.common.number.vo.LabNumberRecordVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Service("labNumberRecordService")
public class LabNumberRecordServiceImpl implements ILabNumberRecordService {
	private ILabNumberRecordDAO labNumberRecordDAO;

	public ILabNumberRecordDAO getLabNumberRecordDAO() {
		return labNumberRecordDAO;
	}

	public void setLabNumberRecordDAO(ILabNumberRecordDAO labNumberRecordDAO) {
		this.labNumberRecordDAO = labNumberRecordDAO;
	}

	@Override
	public LabNumberRecordVo addLabNumberRecord(
			LabNumberRecordVo labNumberRecordVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabNumberRecord labNumberRecord=new LabNumberRecord();
		BeanUtils.copyProperties(labNumberRecordVo, labNumberRecord);
		labNumberRecordDAO.addLabNumberRecord(labNumberRecord);
		return labNumberRecordVo;
	}

	@Override
	public boolean deleteLabNumberRecord(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
			for(String id:ids){
				if(!StrUtils.isBlankOrNull(id)){
					LabNumberRecord labNumberRecord=labNumberRecordDAO.getLabNumberRecord(id);
					labNumberRecordDAO.updateLabNumberRecord(labNumberRecord);
				}
				
			}
			}catch(Exception e){
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@Override
	public LabNumberRecordVo getLabNumberRecord(String id)
			throws GlobalException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<LabNumberRecordVo> getLabNumberRecordList(
			LabNumberRecordVo labNumberRecordVo) throws GlobalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getLabNumberRecordPR(LabNumberRecordVo labNumberRecordVo,
			PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateLabNumberRecord(LabNumberRecordVo labNumberRecordVo)
			throws GlobalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLabNumberRecord4Del(String[] ids)
			throws GlobalException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean addLabNumberRecord(String name,String type) throws GlobalException{
		boolean key=false;
		if(!StrUtils.isBlankOrNull(name)&&!StrUtils.isBlankOrNull(type)){
			LabNumberRecord labNumberRecord=new LabNumberRecord();
			labNumberRecord.setName(name);
			labNumberRecord.setTypeCode(type);
			try{
				labNumberRecordDAO.addLabNumberRecord(labNumberRecord);
				key=true;
			}catch(Exception e){
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public boolean deleteLabNumberRecordByNo(String name,String type) throws GlobalException{
		boolean key=false;
		String hql="FROM LabNumberRecord WHERE isDel='"+Constants_Base.N+"' AND name='"+name+"' AND typeCode='"+type+"'";
		try{
			List<LabNumberRecord> listLabNumberRecord=labNumberRecordDAO.find(hql);
			if(listLabNumberRecord!=null&&listLabNumberRecord.size()>0){
				labNumberRecordDAO.deleteAll(listLabNumberRecord);
			}
			key=true;
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	

	
}
