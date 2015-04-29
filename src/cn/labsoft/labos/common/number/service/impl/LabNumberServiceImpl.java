package cn.labsoft.labos.common.number.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.number.dao.ILabNumberParDAO;
import cn.labsoft.labos.common.number.dao.ILabNumberRecordDAO;
import cn.labsoft.labos.common.number.entity.LabNumber;
import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.number.vo.LabNumberParVo;
import cn.labsoft.labos.common.number.vo.LabNumberVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DBUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labNumberService")
public class LabNumberServiceImpl implements ILabNumberService {
	private ILabNumberDAO labNumberDAO;
	private ILabNumberRecordDAO labNumberRecordDAO;
	private ILabNumberParDAO labNumberParDAO;
	@Override
	public LabNumberVo addLabNumber(LabNumberVo labNumberVo) throws GlobalException {
		// TODO Auto-generated method stub
		
		LabNumber labNumber=new LabNumber();
		try{
			labNumber=this.vo2Po(labNumberVo, labNumber);
			labNumberDAO.addLabNumber(labNumber);
			if(labNumberVo.getListLabNumberParVo()!=null&&labNumberVo.getListLabNumberParVo().size()>0){
				for(LabNumberParVo labNumberParVo:labNumberVo.getListLabNumberParVo()){
					LabNumberPar labNumberPar=new LabNumberPar();
					BeanUtils.copyProperties(labNumberParVo, labNumberPar,new String[]{"labNumberId"});
					labNumberPar.setIsDel(Constants_Base.N);
					labNumberPar.setLabNumber(labNumber);
					labNumberParDAO.addLabNumberPar(labNumberPar);
				}
			}
			String no=this.getLabNumberByNo(labNumber.getType());
			if(!StrUtils.isBlankOrNull(no)){
				labNumber.setExample(no);
				labNumberDAO.updateLabNumber(labNumber);
			}
			labNumberVo.setId(labNumber.getId());
		   labNumberDAO.updateLabNumber(labNumber);
		}catch(Exception e){
			Log4J.error("LabNumberServiceImpl addLabNumber  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labNumberVo;
	}

	@Override
	public boolean deleteLabNumber(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labNumberDAO.deleteLabNumber(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabNumberServiceImpl deleteLabNumber  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabNumberVo getLabNumber(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabNumberVo labNumberVo=new LabNumberVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabNumber labNumber=labNumberDAO.getLabNumber(id);
				labNumberVo=this.po2Vo(labNumber, labNumberVo);
				String hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+labNumber.getId()+"'";
				List<LabNumberPar> listLabNumberPar=labNumberDAO.find(hql);
				if(listLabNumberPar!=null&&listLabNumberPar.size()>0){
					List<LabNumberParVo> listLabNumberParVo=new ArrayList<LabNumberParVo>();
					for(LabNumberPar labNumberPar:listLabNumberPar){
						LabNumberParVo labNumberParVo=new LabNumberParVo();
						BeanUtils.copyProperties(labNumberPar, labNumberParVo,new String[]{"labNumber"});
						labNumberParVo.setLabNumberId(labNumberPar.getLabNumber().getId());
						listLabNumberParVo.add(labNumberParVo);
					}
					labNumberVo.setListLabNumberParVo(listLabNumberParVo);
				}
			}catch(Exception e){
				Log4J.error("LabNumberServiceImpl getLabNumber  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labNumberVo;
	}

	@Override
	public List<LabNumberVo> getLabNumberList(LabNumberVo labNumberVo) throws GlobalException {
		// TODO Auto-generated method stub
		String wereHql="";
		
		return this.getLabNumberVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabNumberPR(LabNumberVo labNumberVo, PageResult pageResult)
			throws GlobalException {
		// TODO Auto-generated method stub
		String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"'";
		if(!StrUtils.isBlankOrNull(labNumberVo.getName()))
			hql+=" AND name LIKE '%"+labNumberVo.getName()+"%'";
		pageResult=labNumberDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabNumberVo> listLabNumberVo=new ArrayList<LabNumberVo>();
			List<LabNumber> listLabNumber=new ArrayList<LabNumber>();
			listLabNumber=pageResult.getResultList();
			for(LabNumber labNumber:listLabNumber){
				LabNumberVo vo=new LabNumberVo();
				vo=this.po2Vo(labNumber, vo);
				listLabNumberVo.add(vo);
			}
			pageResult.setResultList(listLabNumberVo);
		}
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabNumber(LabNumberVo labNumberVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabNumber labNumber=new LabNumber();
		boolean key=true;
		try{
			labNumber=this.vo2Po(labNumberVo, labNumber);
			labNumberDAO.updateLabNumber(labNumber);
			String hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+labNumber.getId()+"'";
			List<LabNumberPar> listLabNumberPar=labNumberDAO.find(hql);
			if(listLabNumberPar!=null&&listLabNumberPar.size()>0){
				labNumberDAO.deleteAll(listLabNumberPar);
			}
			if(labNumberVo.getListLabNumberParVo()!=null&&labNumberVo.getListLabNumberParVo().size()>0){
				for(LabNumberParVo labNumberParVo:labNumberVo.getListLabNumberParVo()){
					LabNumberPar labNumberPar=new LabNumberPar();
					BeanUtils.copyProperties(labNumberParVo, labNumberPar,new String[]{"labNumberId"});
					labNumberPar.setIsDel(Constants_Base.N);
					labNumberPar.setLabNumber(labNumber);
					labNumberParDAO.addLabNumberPar(labNumberPar);
				}
			}
			String no=this.getLabNumberByNo(labNumber.getType());
			if(!StrUtils.isBlankOrNull(no)){
				labNumber.setExample(no);
				labNumberDAO.updateLabNumber(labNumber);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabNumberServiceImpl updateLabNumber  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabNumber4Del(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabNumber labNumber=labNumberDAO.getLabNumber(id);
					labNumber.setIsDel(Constants_Base.Y);
					labNumberDAO.updateLabNumber(labNumber);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabNumberServiceImpl updateLabNumber4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabNumberVo> getLabNumberVoListByWhere(String wereHql) throws GlobalException{
		List<LabNumberVo> listLabNumberVo=new ArrayList<LabNumberVo>();
		String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabNumber> listLabNumber=labNumberDAO.find(hql);
		if(listLabNumber!=null&&listLabNumber.size()>0){
			for(LabNumber labNumber:listLabNumber){
				LabNumberVo labNumberVo=new LabNumberVo();
				labNumberVo=this.po2Vo(labNumber, labNumberVo);
				listLabNumberVo.add(labNumberVo);
			}
		}
		return listLabNumberVo;
	}
	public LabNumber vo2Po(LabNumberVo labNumberVo,LabNumber labNumber){
		BeanUtils.copyProperties(labNumberVo, labNumber,new String[]{"isDel"});
		if(labNumberVo.getSort()==null){
			labNumber.setSort(0);
		}
		return labNumber;
	}
	public LabNumberVo po2Vo(LabNumber labNumber,LabNumberVo labNumberVo){
		BeanUtils.copyProperties(labNumber, labNumberVo);
		return labNumberVo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getLabNumberByNo(String type) throws GlobalException{
		String returnNo="";
		String splitName="";
		if(!StrUtils.isBlankOrNull(type)){
			String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"' AND type='"+type+"'";
			List<LabNumber> listLabNumber=labNumberDAO.find(hql);
			if(listLabNumber!=null&&listLabNumber.size()>0){
				hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+listLabNumber.get(0).getId()+"' ORDER BY sort";
				List<LabNumberPar> listLabNumberPar=labNumberDAO.find(hql);
				if(!StrUtils.isBlankOrNull(listLabNumber.get(0).getConnector()))splitName=listLabNumber.get(0).getConnector();
				if(listLabNumberPar!=null&&listLabNumberPar.size()>0){
					for(LabNumberPar labNumberPar:listLabNumberPar){
						returnNo+=formartExpression(labNumberPar.getType(),labNumberPar.getExpression(),0);
						returnNo+=splitName;
					}
					if(!StrUtils.isBlankOrNull(returnNo)){
						if(returnNo.indexOf(splitName)>-1&&!StrUtils.isBlankOrNull(splitName)){
							returnNo=returnNo.substring(0,returnNo.length()-1);
						}
					}
				}
			}
		}
		
		
		return returnNo;
	}
	@Override
	public  String getLabNumberByNo(String type, String[] names,String status) throws GlobalException {
		// TODO Auto-generated method stub
			List<String> list=createLabNuberByNo(type,names,status,1);
		return list.get(0);
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
			if(Integer.valueOf(value)>0){
				int result=Integer.valueOf(value)+sort+1;
				returnExpre=String.valueOf(result);
			}else
			returnExpre+=new DecimalFormat(value).format(sort+1);
		}
		
		return returnExpre;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> createLabNuberByNo(String type, String[] names,String status,int count) throws GlobalException{
		String splitName="";
		List<String> listCode=new ArrayList<String>();
		int sort=0;
		if(!StrUtils.isBlankOrNull(type)){
			String hql="FROM LabNumber WHERE isDel='"+Constants_Base.N+"' AND type='"+type+"'";
			List<LabNumber> listLabNumber=labNumberDAO.find(hql);
			if(listLabNumber!=null&&listLabNumber.size()>0){
				hql="FROM LabNumberPar WHERE isDel='"+Constants_Base.N+"' AND labNumber.id='"+listLabNumber.get(0).getId()+"' ORDER BY sort";
				List<LabNumberPar> listLabNumberPar=labNumberDAO.find(hql);
				   hql="SELECT MAX(sort) FROM LabNumberRecord WHERE typeCode='"+type+"' AND isDel='"+Constants_Base.N+"' AND status='"+Constants_Base.Y+"'";
				   List<Integer> listMax= labNumberRecordDAO.find(hql);
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
	@Override
	public List<String> getLabNumberBatchNo( int count,
			String typeCode,String[] name,String status) throws GlobalException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		list=this.createLabNuberByNo(typeCode, name, status, count);
		return list;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Resource
	public void setLabNumberRecordDAO(ILabNumberRecordDAO labNumberRecordDAO) {
		this.labNumberRecordDAO = labNumberRecordDAO;
	}
	@Resource
	public void setLabNumberParDAO(ILabNumberParDAO labNumberParDAO) {
		this.labNumberParDAO = labNumberParDAO;
	}
	

}
