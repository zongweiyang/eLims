package cn.labsoft.labos.business.sam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.business.sam.dao.ILabSamDAO;
import cn.labsoft.labos.business.sam.dao.ILabSamMainDAO;
import cn.labsoft.labos.business.sam.entity.LabSam;
import cn.labsoft.labos.business.sam.entity.LabSamMain;
import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.business.sam.service.ILabSamService;
import cn.labsoft.labos.business.sam.vo.LabSamMainVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labSamService")
public class LabSamServiceImpl extends BaseService implements ILabSamService {

	private ILabSamDAO labSamDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabSamMainDAO labSamMainDAO;
	
	
	@Resource
	public void setLabSamDAO(ILabSamDAO labSamDAO) {
		this.labSamDAO = labSamDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabSamMainDAO(ILabSamMainDAO labSamMainDAO) {
		this.labSamMainDAO = labSamMainDAO;
	}

	@Override
	public LabSamVo getLabSamById(String id)throws GlobalException {
		LabSam labSam=(LabSam)labSamDAO.findById(LabSam.class, id);
		return PoToVo(labSam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamPR(LabSamVo labSamVo,
			PageResult pageResult)
			throws GlobalException {
		String hql=" FROM LabSam WHERE 1=1 AND isDel='"+Constants_Business.N+"'";
		if (!StrUtils.isBlankOrNull(labSamVo.getName())) {
			hql+=" AND name LIKE '%"+labSamVo.getName().trim()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSamVo.getSamTypeId())&&!"-1".equals(labSamVo.getSamTypeId())) {
			hql+=" AND labSamType.id='"+labSamVo.getSamTypeId().trim()+"'";
		}
        pageResult=labSamDAO.getPageResult(hql, pageResult);
        List<LabSam> labSamList=pageResult.getResultList();
        List<LabSamVo> voList=new ArrayList<LabSamVo>();
        for(LabSam po:labSamList)
        {
        	voList.add(PoToVo(po));
        }
        pageResult.setResultList(voList);
		return pageResult;
	}

	private  LabSamVo PoToVo(LabSam po)throws GlobalException
	{
		  LabSamVo vo=new LabSamVo();
		  try {
				BeanUtils.copyProperties(po, vo, new String[]{"samType"});
			} catch (Exception e) {
				throw new GlobalException("Po转Vo异常--"+e.getMessage());
			}
			if (null!=po.getLabSamType()) {
				vo.setSamTypeId(po.getLabSamType().getId());
				vo.setSamTypeName(po.getLabSamType().getName());
			}
			if(null!=po.getSaveOrg()&&!"".equals(po.getSaveOrg()))
			{
				LabOrg labOrg=(LabOrg) labSamDAO.findById(LabOrg.class, po.getSaveOrg());
				vo.setSaveOrg(labOrg.getId());
				vo.setSaveOrgName(labOrg.getName());
			}
	return vo;
	}
	
	private static LabSam  VoToPo(LabSamVo vo)throws GlobalException
	{    
		LabSam po=new LabSam();
			BeanUtils.copyProperties(vo, po, new String[]{"samTypeId","samTypeName"});
		if(null!=vo.getSampDate()){
			po.setSampDate(DateUtils.format(DateUtils.parseToDate(vo.getSampDate()), DateUtils.formatStr_yyyyMMdd));
		}
		if (null!=vo.getSamTypeId()) {
			LabSamType type=new LabSamType();
			type.setId(vo.getSamTypeId().trim());
			po.setLabSamType(type);
		}
		return po;
	}

	@Override
	public boolean addLabSam(LabSamVo labSamVo)
			throws GlobalException {
			LabSam po=VoToPo(labSamVo);
			po= labSamDAO.addLabSam(po);
			if(null!=labSamVo.getUuid()&&!"".equals(labSamVo.getUuid())){
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labSamVo.getUuid(), "sam");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			}
			return true;
	}

	@Override
	public boolean deleteLabSam(String id) throws GlobalException {
		try {
			LabSamMain labSamMain=(LabSamMain)labSamDAO.findById(LabSamMain.class, id);
			labSamMain.setIsDel(Constants_Business.Y);
			labSamMainDAO.updateLabSamMain(labSamMain);
			return true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("样品删除异常--"+e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamMain(LabSamMainVo labSamMainVo)
			throws GlobalException {
		try {
			LabSamMain po=(LabSamMain) labSamDAO.findById(LabSamMain.class, labSamMainVo.getId());
			po.setIsDivision(labSamMainVo.getIsDivision());
			po.setNum(labSamMainVo.getNum());
			po.setCustomer(labSamMainVo.getCustomer());
			po.setContacts(labSamMainVo.getContacts());
			po.setContactPhone(labSamMainVo.getContactPhone());
			po.setSampSource(labSamMainVo.getSampSource());
			po.setSaveMode(labSamMainVo.getSaveMode());
			
			List<LabSam> listLabSam=labSamDAO.find("FROM LabSam WHERE isDel='N' AND main.id='"+labSamMainVo.getId()+"'" );
			if(null!=listLabSam && listLabSam.size()>0){
				for(LabSam labSam:listLabSam){
					labSamDAO.deleteLabSam(labSam);
				 }
			}
			String isDes="N";
			if(null!=labSamMainVo.getLabSamVoList() && labSamMainVo.getLabSamVoList().size()>0){
				for(LabSamVo vo:labSamMainVo.getLabSamVoList()){
					if(vo==null)continue;
					LabSam labSam=VoToPo(vo);
					labSam.setMain(po);
					labSam.setSampCode(vo.getSampCode());
					if(vo.getIsDestory().equals("Y")){
						isDes="Y";
					}
					labSamDAO.addLabSam(labSam);
				}
			}	
			po.setIsDivision(isDes);
			labSamMainDAO.updateLabSamMain(po);
			return true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("样品更新异常--"+e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamVo> getLabSamList(LabSamVo labSamVo)
			throws GlobalException {
		String hql=" FROM LabSam WHERE  isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSamVo.getSamTypeId())){
			hql+=" AND labSamType in ('"+labSamVo.getSamTypeId().trim()+"')";
		}
		if(!StrUtils.isBlankOrNull(labSamVo.getName())){
			hql+=" AND name='%"+labSamVo.getName()+"%'";
		}
		if (null!=labSamVo.getIds()&&labSamVo.getIds().length>0) {
			String[] ids=labSamVo.getIds();
			String id="";
		
				for(int i=0;i<ids.length-1;i++)
					{
					   id+="'"+ids[i]+"',";
					}
				id+="'"+ids[ids.length-1]+"'";
		
			hql+=" AND (id IN ("+id+") AND isDel='"+Constants_Business.N+"')";
		}
		List<LabSam> poList=labSamDAO.find(hql);
		List<LabSamVo> voList=new ArrayList<LabSamVo>();
		for(LabSam po:poList)
		{
			voList.add(PoToVo(po));
		}
		return voList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamVo> getLabSamList(LabSamMainVo labSamMainVo)
			throws GlobalException {
		String hql=" FROM LabSam WHERE  isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSamMainVo.getLabSamTypeId())){
			hql+=" AND labSamType in ('"+labSamMainVo.getLabSamTypeId().trim()+"')";
		}
		if (null!=labSamMainVo.getId()&&labSamMainVo.getId().length()>0) {
			String[] ids=labSamMainVo.getId().split(",");
			String id="";
		
				for(int i=0;i<ids.length-1;i++)
					{
					   id+="'"+ids[i]+"',";
					}
				id+="'"+ids[ids.length-1]+"'";
		
			hql+=" AND (main.id IN ("+id+") AND isDel='"+Constants_Business.N+"')";
		}
		List<LabSam> poList=labSamDAO.find(hql);
		List<LabSamVo> voList=new ArrayList<LabSamVo>();
		for(LabSam po:poList)
		{
			voList.add(PoToVo(po));
		}
		return voList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamVo> getLabSamListByIds(String  ids) throws GlobalException {
		String hql=" FROM LabSam WHERE isDel='"+Constants_Business.N+"'  AND id IN(";
	    String[] id=ids.split(",");
	    for(int i=0;i<id.length-1;i++)
	    {
	    	hql+="'"+id[i]+"',";
	    }
	    hql+="'"+id[id.length-1]+"')";
	    List<LabSam>  labList=labSamDAO.find(hql);
	    List<LabSamVo> labSamVoList=new ArrayList<LabSamVo>();
	    for(LabSam labSam:labList)
	    {
	    	labSamVoList.add(PoToVo(labSam));
	    }
		return labSamVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamMainPR(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException {
		String hql=" FROM LabSamMain WHERE 1=1 AND isDel='"+Constants_Business.N+"'";
        pageResult=labSamMainDAO.getPageResult(hql, pageResult);
        List<LabSamMain> labSamMainList=pageResult.getResultList();
        List<LabSamMainVo> voList=new ArrayList<LabSamMainVo>();
        for(LabSamMain po:labSamMainList)
        {
        	LabSamMainVo vo=new LabSamMainVo();
        	BeanUtils.copyProperties(po, vo, new String[]{""});
        	String where=this.getLabSamName(po);
        	vo.setSampName(where);
        	voList.add(vo);
        }
        pageResult.setResultList(voList);
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	public String getLabSamName(LabSamMain po)throws GlobalException {
		String name="";
				String hql=" FROM LabSam WHERE main.id='"+po.getId()+"' AND isDel='"+Constants_Business.N+"'";
					List<LabSam> listLabSam=labSamDAO.find(hql);
					for(LabSam labSam:listLabSam){
						if(!name.contains(labSam.getName())){
							if(name.length()>0){
								name+=","+labSam.getName();	
							}else{
								name=labSam.getName();
							}
							
						}
					}
		return name;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamMainPR4Division(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException {
		String hql=" FROM LabSamMain WHERE 1=1 AND isDel='"+Constants_Business.N+"' AND isDivision = 'Y'";
		if (!StrUtils.isBlankOrNull(labSamMainVo.getIsDiv())) {
			hql+=" AND isDiv='"+labSamMainVo.getIsDiv()+"'";
		}
		if (null != labSamMainVo.getStartDate()
				&& !"".equals(labSamMainVo.getStartDate())) {
			hql += " AND  registDate >= '" + labSamMainVo.getStartDate()
					+ "'";
		}
		if (null != labSamMainVo.getEndDate()
				&& !"".equals(labSamMainVo.getEndDate())) {
			hql += " AND  registDate <= '" + labSamMainVo.getEndDate() + "'";
		}
        pageResult=labSamMainDAO.getPageResult(hql, pageResult);
        List<LabSamMain> labSamMainList=pageResult.getResultList();
        List<LabSamMainVo> voList=new ArrayList<LabSamMainVo>();
        for(LabSamMain po:labSamMainList)
        {
        	LabSamMainVo vo=new LabSamMainVo();
        	BeanUtils.copyProperties(po, vo, new String[]{""});
        	String where=this.getLabSamName(po);
        	vo.setSampName(where);
        	voList.add(vo);
        }
        pageResult.setResultList(voList);
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamMainPR4Destory(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException {
		String hql=" FROM LabSamMain WHERE 1=1 AND isDel='"+Constants_Business.N+"' AND isDivision = 'Y'";
		if (null != labSamMainVo.getStartDate()
				&& !"".equals(labSamMainVo.getStartDate())) {
			hql += " AND  registDate >= '" + labSamMainVo.getStartDate()
					+ "'";
		}
		if (null != labSamMainVo.getEndDate()
				&& !"".equals(labSamMainVo.getEndDate())) {
			hql += " AND  registDate <= '" + labSamMainVo.getEndDate() + "'";
		}
        pageResult=labSamMainDAO.getPageResult(hql, pageResult);
        List<LabSamMain> labSamMainList=pageResult.getResultList();
        List<LabSamMainVo> voList=new ArrayList<LabSamMainVo>();
        for(LabSamMain po:labSamMainList)
        {
        	List<LabSam> labSamList=labSamDAO.find("FROM LabSam where isDel='"+Constants_Business.N+"' AND isDestory ='Y' AND main.id='"+po.getId()+"'");
        	if(null!=labSamList&&labSamList.size()>0){
	        	LabSamMainVo vo=new LabSamMainVo();
	        	BeanUtils.copyProperties(po, vo, new String[]{""});
	        	String where=this.getLabSamName(po);
	        	vo.setSampName(where);
	        	voList.add(vo);
        	}
        }
        pageResult.setResultList(voList);
		return pageResult;
	}
	
	@Override
	public LabSamMain addLabSamMain(LabSamMainVo labSamMainVo) throws GlobalException {
		LabSamMain labSamMain=new LabSamMain();
		BeanUtils.copyProperties(labSamMainVo, labSamMain, new String[]{"labSamVoList"});
		labSamMain.setIsDiv(Constants_Business.N);
		labSamMain.setRegistDate(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMddHHmmss));
		labSamMain=labSamMainDAO.addLabSamMain(labSamMain);
		if(null!=labSamMainVo.getUuid()&&!"".equals(labSamMainVo.getUuid())){
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labSamMainVo.getUuid(), "sam");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(labSamMain.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
		}
		if(null !=labSamMainVo.getLabSamVoList()&&labSamMainVo.getLabSamVoList().size()>0){
			for(int i=0;i<labSamMainVo.getLabSamVoList().size();i++){
				LabSamVo labSamVo=labSamMainVo.getLabSamVoList().get(i);
				LabSam po=VoToPo(labSamVo);
				po.setMain(labSamMain);
				po.setIsDestory(Constants_Business.N);
				po.setSampCode(labSamMainVo.getListCode().get(i));
				po= labSamDAO.addLabSam(po);
			}
		}
			return labSamMain;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabSamMainVo getLabSamMainById(String id) throws GlobalException {
		LabSamMain labSamMain=(LabSamMain)labSamMainDAO.findById(LabSamMain.class, id);
		LabSamMainVo vo=new LabSamMainVo();
		BeanUtils.copyProperties(labSamMain, vo, new String[]{""});
		List<LabSam> listLabSam=labSamMainDAO.find("FROM LabSam WHERE isDel='N' AND main.id='"+labSamMain.getId()+"'" );
		List<LabSamVo> listLabSamVo=new ArrayList<LabSamVo>();
		if(null!=listLabSam && listLabSam.size()>0){
			for(LabSam labSam:listLabSam){
				LabSamVo labSamVo=new LabSamVo();
				labSamVo=PoToVo(labSam);
				listLabSamVo.add(labSamVo);
			 }
			vo.setLabSamVoList(listLabSamVo);
		}	
		return vo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabSamMainVo getLabSamMain4Destory(String id) throws GlobalException {
		LabSamMain labSamMain=(LabSamMain)labSamMainDAO.findById(LabSamMain.class, id);
		LabSamMainVo vo=new LabSamMainVo();
		BeanUtils.copyProperties(labSamMain, vo, new String[]{""});
		List<LabSam> listLabSam=labSamMainDAO.find("FROM LabSam WHERE isDel='N'  AND isDestory ='Y' AND main.id='"+labSamMain.getId()+"'" );
		List<LabSamVo> listLabSamVo=new ArrayList<LabSamVo>();
		if(null!=listLabSam && listLabSam.size()>0){
			for(LabSam labSam:listLabSam){
				LabSamVo labSamVo=new LabSamVo();
				labSamVo=PoToVo(labSam);
				listLabSamVo.add(labSamVo);
			 }
			vo.setLabSamVoList(listLabSamVo);
		}	
		return vo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabSamMainVo getLabSamMain4Out(String id) throws GlobalException {
		LabSamMain labSamMain=(LabSamMain)labSamMainDAO.findById(LabSamMain.class, id);
		LabSamMainVo vo=new LabSamMainVo();
		BeanUtils.copyProperties(labSamMain, vo, new String[]{""});
		List<LabSam> listLabSam=labSamMainDAO.find("FROM LabSam WHERE isDel='N'  AND isDestory ='N' AND main.id='"+labSamMain.getId()+"'" );
		List<LabSamVo> listLabSamVo=new ArrayList<LabSamVo>();
		if(null!=listLabSam && listLabSam.size()>0){
			for(LabSam labSam:listLabSam){
				LabSamVo labSamVo=new LabSamVo();
				labSamVo=PoToVo(labSam);
				listLabSamVo.add(labSamVo);
			 }
			vo.setLabSamVoList(listLabSamVo);
		}	
		return vo;
	}
	
	@Override
	public boolean updateLabSamMain4Destory(LabSamMainVo labSamMainVo) throws GlobalException {
		LabSamMain labSamMain=(LabSamMain)labSamMainDAO.findById(LabSamMain.class, labSamMainVo.getId());
		try {
			if(!StrUtils.isBlankOrNull(labSamMainVo.getReason())){
				labSamMain.setReason(labSamMainVo.getReason());
			}
			if(!StrUtils.isBlankOrNull(labSamMainVo.getHandle())){
				labSamMain.setHandle(labSamMainVo.getHandle());
			}
			if(!StrUtils.isBlankOrNull(labSamMainVo.getRemark())){
				labSamMain.setRemark(labSamMainVo.getRemark());
			}
			labSamMain.setIsHandle(Constants_Business.Y);
			labSamMainDAO.updateLabSamMain(labSamMain);
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabSamMain4Division(LabSamMainVo labSamMainVo) throws GlobalException {
		LabSamMain labSamMain=(LabSamMain)labSamMainDAO.findById(LabSamMain.class, labSamMainVo.getId());
		labSamMain.setIsDiv(Constants_Business.Y);
		labSamMainDAO.updateLabSamMain(labSamMain);
		int i=0;
			try {
				List<LabSam> listLabSam=labSamDAO.find("FROM LabSam WHERE isDel='N' AND main.id='"+labSamMainVo.getId()+"'" );
				if(null!=listLabSam && listLabSam.size()>0){
					for(LabSam labSam:listLabSam){
						labSamDAO.deleteLabSam(labSam);
					 }
				}
				if(null!=labSamMainVo.getLabSamVoList()&&labSamMainVo.getLabSamVoList().size()>0){
					for(LabSamVo vo:labSamMainVo.getLabSamVoList()){
						if(null==vo||StrUtils.isBlankOrNull(vo.getId())){
							continue;
						}
						LabSam po=new LabSam();
						LabSam labSam=new LabSam();
						if(!StrUtils.isBlankOrNull(vo.getId())){
							po=(LabSam) labSamDAO.findById(LabSam.class, vo.getId());
						}
						BeanUtils.copyProperties(po, labSam, new String[]{"id","labSamType"});
						if (null!=po.getLabSamType().getId()) {
							LabSamType type=new LabSamType();
							type.setId(po.getLabSamType().getId());
							labSam.setLabSamType(type);
						}
						labSam.setIsDestory(vo.getIsDestory());
						labSam.setSampCode(this.getLabSamMaxReceiptNo());
						labSam.setMain(labSamMain);
						if(!StrUtils.isBlankOrNull(vo.getName())){
							labSam.setName(vo.getName());
						}
						if(!StrUtils.isBlankOrNull(vo.getSpecifications())){
							labSam.setSpecifications(vo.getSpecifications());
						}
						if(!StrUtils.isBlankOrNull(vo.getSeqNum())){
							labSam.setSeqNum(vo.getSeqNum());
						}
						if(!StrUtils.isBlankOrNull(vo.getRemark())){
							labSam.setRemark(vo.getRemark());
						}
						labSamDAO.addLabSam(labSam);
						i++;
					}
				}
				labSamMain.setIsDiv(Constants_Business.Y);
				labSamMain.setNum(String.valueOf(i));
				labSamMainDAO.updateLabSamMain(labSamMain);
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
	@Override
	public boolean updateLabSamMain4Out(LabSamMainVo labSamMainVo) throws GlobalException {
			if(null!=labSamMainVo.getLabSamVoList()&&labSamMainVo.getLabSamVoList().size()>0){
				for(LabSamVo vo:labSamMainVo.getLabSamVoList()){
					if(null==vo){
						continue;
					}
					LabSam po=new LabSam();
					if(!StrUtils.isBlankOrNull(vo.getId())){
						po=(LabSam) labSamDAO.findById(LabSam.class, vo.getId());
					}
					if(!StrUtils.isBlankOrNull(vo.getIsDraw())&&vo.getIsDraw().equals("on")){
						po.setIsDraw(Constants_Business.Y);
						if(!StrUtils.isBlankOrNull(labSamMainVo.getReceivePerson())){
							po.setDrawPerson(labSamMainVo.getReceivePerson());
							po.setDrawTime(DateUtils.getCurrDateTimeStr());
						}
					}
					
					labSamDAO.updateLabSam(po);
				}
			}
			return false;
	}

	
	@SuppressWarnings("unchecked")
	public String getLabSamMaxReceiptNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM  LabSam WHERE isDel='" + Constants_Business.N
				+ "' AND  sampCode LIKE '" + year_month_day + "%'";
		List<LabSam> labSamList = labSamDAO.find(hql);
		String billId = year_month_day;
		if (labSamList.size() > 0) {
			int maxRecordId = 0;
			for (LabSam po : labSamList) {
				String sampCode = po.getSampCode();
				int back4 = Integer.valueOf(sampCode.substring(sampCode
						.length() - 4, sampCode.length()));
				if (back4 > maxRecordId) {
					maxRecordId = back4;
				}
			}
			String tempRecorddId = String.valueOf(maxRecordId + 1);
			switch (tempRecorddId.length()) {
			case 1:
				billId = billId + "000" + tempRecorddId;
				break;
			case 2:
				billId = billId + "00" + tempRecorddId;
				break;
			case 3:
				billId = billId + "0" + tempRecorddId;
				break;
			case 4:
				billId = billId + tempRecorddId;
				break;
			default:
				billId = "";
				break;
			}
		} else {
			billId = billId + "0001";
		}
		return billId;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamMainPR4Out(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException {
		String hql=" FROM LabSamMain WHERE 1=1 AND isDel='"+Constants_Business.N+"'";
		if (null != labSamMainVo.getStartDate()
				&& !"".equals(labSamMainVo.getStartDate())) {
			hql += " AND  registDate >= '" + labSamMainVo.getStartDate()
					+ "'";
		}
		if (null != labSamMainVo.getEndDate()
				&& !"".equals(labSamMainVo.getEndDate())) {
			hql += " AND  registDate <= '" + labSamMainVo.getEndDate() + "'";
		}
        pageResult=labSamMainDAO.getPageResult(hql, pageResult);
        List<LabSamMain> labSamMainList=pageResult.getResultList();
        List<LabSamMainVo> voList=new ArrayList<LabSamMainVo>();
        for(LabSamMain po:labSamMainList)
        {
        	LabSamMainVo vo=new LabSamMainVo();
        	BeanUtils.copyProperties(po, vo, new String[]{""});
        	vo.setSampName(this.getLabSamName(po));
        	List<LabSam> list=labSamDAO.find("FROM LabSam where isDel='N' AND isDraw IS NULL AND isDestory ='N' AND main.id='"+po.getId()+"'");
        	if(list.size()>0){
        		vo.setStatus("Y");
        	}
        	voList.add(vo);
        }
        pageResult.setResultList(voList);
		return pageResult;
	}
	@Override
	public LabSamMainVo getLabSam4Other(LabSamMainVo labSamMainVo)
			throws GlobalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean saveOrUpdateLabSam4Other(LabSamMainVo labSamMainVo)
			throws GlobalException {
		// TODO Auto-generated method stub
		return true;
	}
}
