package cn.labsoft.labos.common.number.dao.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.number.entity.LabNumber;
import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DBUtils;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Repository(value="labNumberDAO")
public class LabNumberDAOImpl extends BaseDAO implements ILabNumberDAO {
	@Override
	public LabNumber addLabNumber(LabNumber labNumber) throws GlobalException {
		try {
			labNumber.setIsDel(Constants_Base.N);
			labNumber.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labNumber);
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labNumber;
	}

	@Override
	public boolean deleteLabNumber(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabNumber(id));
			}
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabNumber getLabNumber(String id) throws GlobalException {
		try {
			LabNumber labNumber = (LabNumber) super.findById(LabNumber.class, id);
			return labNumber;
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabNumber(LabNumber labNumber) throws GlobalException {
		try {
			super.update(labNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabNumber> getLabNumberList(String hql) throws GlobalException {
	try {
		List<LabNumber> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabNumberPR(String hql, PageResult pageResult) throws GlobalException {
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
	public void deleteAll(List<LabNumberPar> listLabNumberPar) {
		// TODO Auto-generated method stub
		super.deleteAll(listLabNumberPar);
	}
	@Override
	public List<String> getLabNumberBatchNo( int count,
			String typeCode,String[] name,String status) throws GlobalException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		list=this.createLabNuberByNo(typeCode, name, status, count);
		return list;
	}
	@Override
	public  String getLabNumberByNo(String type, String[] names,String status) throws GlobalException {
		// TODO Auto-generated method stub
			List<String> list=createLabNuberByNo(type,names,status,1);
		return list.get(0);
	}
	@SuppressWarnings("unchecked")
	public List<String> createLabNuberByNo(String type, String[] names,String status,int count) throws GlobalException{
		String splitName="";
		List<String> listCode=new ArrayList<String>();
		int sort=0;
		if(!StrUtils.isBlankOrNull(type)){
			String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"' AND type='"+type+"'";
			List<LabNumber> listLabNumber=this.find(hql);
			if(listLabNumber!=null&&listLabNumber.size()>0){
				hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+listLabNumber.get(0).getId()+"' ORDER BY sort";
				List<LabNumberPar> listLabNumberPar=super.find(hql);
				   hql="SELECT MAX(sort) FROM LabNumberRecord WHERE typeCode='"+type+"' AND isDel='"+Constants_Base.N+"' AND status='"+Constants_Base.Y+"'";
				   List<Integer> listMax= super.find(hql);
					if(listMax!=null&&listMax.size()>0&&listMax.get(0)!=null){
						sort=listMax.get(0);
					}else
						sort=0;
				if(!StrUtils.isBlankOrNull(listLabNumber.get(0).getConnector()))splitName=listLabNumber.get(0).getConnector();
				if(listLabNumberPar!=null&&listLabNumberPar.size()>0){
					for(int i=0;i<count;i++){
						String returnValue="";
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
							
							listCode.add(returnValue);
						}
						sort++;
						if(status.equals("1")){
							String insertSql="INSERT INTO lab_number_record(id,name,typeCode,status,is_del,sort) values('"+UUID.randomUUID().toString().replace("-", "")+"','"+returnValue+"','"+type+"','Y','N',"+sort+")";
							DBUtils.executSql(insertSql);
						}
					}
				}
			}
		}
		
		return  listCode;
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
