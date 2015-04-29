package cn.labsoft.labos.common.number.dao.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.number.dao.ILabNumberRecordDAO;
import cn.labsoft.labos.common.number.entity.LabNumber;
import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.common.number.entity.LabNumberRecord;
import cn.labsoft.labos.common.query.vo.DemoVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Repository(value="labNumberRecordDAO")
public class LabNumberRecordDAOImpl extends BaseDAO implements ILabNumberRecordDAO {
	
	@Override
	public LabNumberRecord addLabNumberRecord(LabNumberRecord labNumberRecord) throws GlobalException {
		try {
			labNumberRecord.setIsDel(Constants_Base.N);
			labNumberRecord.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labNumberRecord);
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labNumberRecord;
	}

	@Override
	public boolean deleteLabNumberRecord(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabNumberRecord(id));
			}
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabNumberRecord getLabNumberRecord(String id) throws GlobalException {
		try {
			LabNumberRecord labNumberRecord = (LabNumberRecord) super.findById(LabNumberRecord.class, id);
			return labNumberRecord;
		} catch (Exception ex) {
			Log4J.error("labNumberRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabNumberRecord(LabNumberRecord labNumberRecord) throws GlobalException {
		try {
			super.update(labNumberRecord);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabNumberRecord> getLabNumberRecordList(String hql) throws GlobalException {
	try {
		List<LabNumberRecord> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabNumberRecordPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public void deleteAll(List<LabNumberRecord> listLabNumberRecord) {
		// TODO Auto-generated method stub
		super.deleteAll(listLabNumberRecord);
	}
	@SuppressWarnings("unchecked")
	public DemoVo createLabNumberNo(String type, String[] names,String status) throws GlobalException{
		String returnValue="";
		String splitName="";
		DemoVo demo=new DemoVo();
		int sort=0;
		if(!StrUtils.isBlankOrNull(type)){
			String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"' AND type='"+type+"'";
			List<LabNumber> listLabNumber=super.find(hql);
			if(listLabNumber!=null&&listLabNumber.size()>0){
				hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+listLabNumber.get(0).getId()+"' ORDER BY sort";
				List<LabNumberPar> listLabNumberPar=super.find(hql);
				
				if(!StrUtils.isBlankOrNull(listLabNumber.get(0).getConnector()))splitName=listLabNumber.get(0).getConnector();
				if(listLabNumberPar!=null&&listLabNumberPar.size()>0){
					for(LabNumberPar labNumberPar:listLabNumberPar){
						returnValue+=formartExpression(labNumberPar.getType(),labNumberPar.getExpression(),sort);
						returnValue+=splitName;
					}
					if(!StrUtils.isBlankOrNull(returnValue)){
						if(returnValue.indexOf(splitName)>-1&&!StrUtils.isBlankOrNull(splitName)){
							returnValue=returnValue.substring(0,returnValue.length()-1);
						}
					
						if(returnValue.indexOf("&")>-1){
							if(names!=null&&names.length>0){
								for(String name:names){
									if(!StrUtils.isBlankOrNull(name))
									returnValue=returnValue.replaceFirst("&", name);
								}
							}
						}
					}
				}
				try{
					
				}catch(Exception e){
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		if(!StrUtils.isBlankOrNull(returnValue))
		demo.setName(returnValue);
		demo.setSort(sort);
		return  demo;
		
	}
	public static String formartExpression(String type,String value,int sort){
		String returnExpre="";
		if(type.equals("0")){
			if(!StrUtils.isBlankOrNull(value)){
				returnExpre+=value;
			}else{
				returnExpre+="&";
			}
		}else if(type.equals("1")){
			 SimpleDateFormat dateformat1=new SimpleDateFormat(value);
			  returnExpre+=dateformat1.format(new Date());
		}else{
			returnExpre+=new DecimalFormat(value).format(sort+1);
		}
		
		return returnExpre;
	}
	
}
