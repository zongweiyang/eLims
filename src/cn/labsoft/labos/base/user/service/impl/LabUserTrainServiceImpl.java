package cn.labsoft.labos.base.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.message.dao.ILabMsgMainDAO;
import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.dao.ILabUserTrainDAO;
import cn.labsoft.labos.base.user.dao.ILabUserTrainRecordDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.base.user.entity.LabUserTrain;
import cn.labsoft.labos.base.user.entity.LabUserTrainRecord;
import cn.labsoft.labos.base.user.service.ILabUserTrainService;
import cn.labsoft.labos.base.user.vo.LabUserTrainRecordVo;
import cn.labsoft.labos.base.user.vo.LabUserTrainVo;

@Service("labUserTrainService")
public class LabUserTrainServiceImpl extends BaseService implements ILabUserTrainService {
	private ILabUserTrainDAO labUserTrainDAO;
	private ILabUserTrainRecordDAO labUserTrainRecordDAO;
	private ILabOrgDAO labOrgDAO;
	private ILabUserDAO labUserDAO;
	private ILabMsgMainDAO labMsgMainDAO;
	
	
	@Resource
	public void setLabMsgMainDAO(ILabMsgMainDAO labMsgMainDAO) {
		this.labMsgMainDAO = labMsgMainDAO;
	}
	@Resource
	public void setLabUserTrainDAO(ILabUserTrainDAO labUserTrainDAO) {
		this.labUserTrainDAO = labUserTrainDAO;
	}
	@Resource
	public void setLabUserTrainRecordDAO(ILabUserTrainRecordDAO labUserTrainRecordDAO) {
		this.labUserTrainRecordDAO = labUserTrainRecordDAO;
	}
	@Resource
	public void setLabOrgDAO(ILabOrgDAO labOrgDAO) {
		this.labOrgDAO = labOrgDAO;
	}
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}

	@Override
	public LabUserTrainVo addLabUserTrain(LabUserTrainVo labUserTrainVo) throws GlobalException {
		LabUserTrain labUserTrain=new LabUserTrain();
		labUserTrain=this.vo2Po(labUserTrainVo, labUserTrain);
		if(labUserTrainVo.getOrgId()!=null&&!labUserTrainVo.getOrgId().equals("")){
			LabOrg org=labOrgDAO.getLabOrg(labUserTrainVo.getOrgId().trim());
			if(org!=null){
				labUserTrain.setOrgId(org.getId());
				labUserTrain.setOrgName(org.getName());
			}
		}
		try{
			labUserTrain.setCreateUserId(getSessionContainer().getUserId());
			labUserTrain.setStatus("0");
			labUserTrainDAO.addLabUserTrain(labUserTrain);
			labUserTrainVo.setId(labUserTrain.getId());
		}catch(Exception e){
			Log4J.error("LabUserTrainServiceImpl addLabUserTrain  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		if(labUserTrainVo.getStudentIds()!=null&&!labUserTrainVo.getStudentIds().equals("")){
			String[] userIds=labUserTrainVo.getStudentIds().split(",");
			for (String userId : userIds) {
				if(userId==null&&userId.trim().length()==0)continue;
				LabUser user=labUserDAO.getLabUser(userId.trim());
				LabUserTrainRecord labUserTrainRecord=new LabUserTrainRecord();
				labUserTrainRecord.setLabUser(user);
				labUserTrainRecord.setLabUserTrain(labUserTrain);
				labUserTrainRecord.setAddress(labUserTrain.getAddress());
				labUserTrainRecord.setTdate(labUserTrain.getTdate());
				labUserTrainRecord.setContent(labUserTrain.getContent());
				labUserTrainRecord.setTeacher(labUserTrain.getTeacher());
				labUserTrainRecord.setStation(labUserTrain.getStation());
				labUserTrainRecord.setOrgId(labUserTrain.getOrgId());
				labUserTrainRecord.setOrgName(labUserTrain.getOrgName());
				labUserTrainRecordDAO.addLabUserTrainRecord(labUserTrainRecord);
				//站内信
				if(labUserTrainVo.getIsMsg()!=null&&labUserTrainVo.getIsMsg().equals(Constants_Common.Y)){
					labMsgMainDAO.sendLabMsgToPerson(getSessionContainer().getUserId(), user.getId(), labUserTrain.getTitle(),"<p>日期："
							+labUserTrain.getTdate()+"；</p><p>地点："+labUserTrain.getAddress()+"；</p><p>内容："+labUserTrain.getContent()+"</p><p>备注："+labUserTrain.getRemark()+"</p>");
				}
				//短信
				if(labUserTrainVo.getIsSms()!=null&&labUserTrainVo.getIsSms().equals(Constants_Common.Y)){
				}
			}
		}
		return labUserTrainVo;
	}

	@Override
	public boolean deleteLabUserTrain(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labUserTrainDAO.deleteLabUserTrain(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabUserTrainServiceImpl deleteLabUserTrain  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabUserTrainVo getLabUserTrain(String id) throws GlobalException {
		LabUserTrainVo labUserTrainVo=new LabUserTrainVo();
		if(!StrUtils.isBlankOrNull(id)){
			LabUserTrain labUserTrain=labUserTrainDAO.getLabUserTrain(id);
			labUserTrainVo=this.po2Vo(labUserTrain, labUserTrainVo);
		
			String hql="FROM LabUserTrainRecord WHERE 1=1";
			hql+=" AND labUserTrain.id='"+labUserTrain.getId()+"'";
			List<LabUserTrainRecord> list=labUserTrainRecordDAO.getLabUserTrainRecordList(hql);
			List<LabUserTrainRecordVo> recordList=new ArrayList<LabUserTrainRecordVo>();
			String userId="";
			String userName="";
			if(list!=null){
				for (LabUserTrainRecord record : list) {
					LabUserTrainRecordVo recordVo=new LabUserTrainRecordVo();
					BeanUtils.copyProperties(record, recordVo, new String[]{});
					if(record.getLabUser()!=null){
						recordVo.setLabUserId(record.getLabUser().getId());
						recordVo.setLabUserName(record.getLabUser().getName());
						userId+=recordVo.getLabUserId()+",";
						userName+=recordVo.getLabUserName()+",";
					}
					if(record.getLabUserTrain()!=null){
						recordVo.setLabUserTrainId(record.getLabUserTrain().getId());
						recordVo.setTitle(record.getLabUserTrain().getTitle());
					}
					if(null!=recordVo.getStatus()&&recordVo.getStatus().equals("2")
							&&StrUtils.isBlankOrNull(recordVo.getResult())){
						recordVo.setResult("未填写");
					}
					recordList.add(recordVo);
				}
				if(userId.endsWith(",")){
					userId=userId.substring(0, userId.length()-1);
					userName=userName.substring(0, userName.length()-1);
				}
			}
			labUserTrainVo.setStudentIds(userId);
			labUserTrainVo.setStudentNames(userName);
			labUserTrainVo.setRecordList(recordList);
		}
		return labUserTrainVo;
	}

	@Override
	public List<LabUserTrainVo> getLabUserTrainList(LabUserTrainVo labUserTrainVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabUserTrainVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabUserTrainPR(LabUserTrainVo labUserTrainVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabUserTrain WHERE isDel='"+Constants_Common.N+"'";
		if(!StrUtils.isBlankOrNull(labUserTrainVo.getTitle())){
			hql+=" AND title like '%"+labUserTrainVo.getTitle()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labUserTrainVo.getStation())){
			hql+=" AND station like '%"+labUserTrainVo.getStation()+"%'";
		}
		pageResult=labUserTrainDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabUserTrainVo> listLabUserTrainVo=new ArrayList<LabUserTrainVo>();
			List<LabUserTrain> listLabUserTrain=new ArrayList<LabUserTrain>();
			listLabUserTrain=pageResult.getResultList();
			
			for(LabUserTrain labUserTrain:listLabUserTrain){
				LabUserTrainVo vo=new LabUserTrainVo();
				vo=this.po2Vo(labUserTrain, vo);
				listLabUserTrainVo.add(vo);
			}
			pageResult.setResultList(listLabUserTrainVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabUserTrain(LabUserTrainVo labUserTrainVo) throws GlobalException {
		LabUserTrain labUserTrain=new LabUserTrain();
		labUserTrain=this.vo2Po(labUserTrainVo, labUserTrain);
		if(labUserTrainVo.getOrgId()!=null&&!labUserTrainVo.getOrgId().equals("")){
			LabOrg org=labOrgDAO.getLabOrg(labUserTrainVo.getOrgId().trim());
			if(org!=null){
				labUserTrain.setOrgId(org.getId());
				labUserTrain.setOrgName(org.getName());
			}
		}
		boolean key=true;
		try{
			labUserTrain.setStatus("0");
			labUserTrainDAO.updateLabUserTrain(labUserTrain);
		}catch(Exception e){
			key=false;
			Log4J.error("LabUserTrainServiceImpl updateLabUserTrain  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		//重新初始化被培训人
		String hql="FROM LabUserTrainRecord WHERE 1=1";
		hql+=" AND labUserTrain.id='"+labUserTrain.getId()+"'";
		List<LabUserTrainRecord> list=labUserTrainRecordDAO.getLabUserTrainRecordList(hql);
		if(list!=null){
			for (LabUserTrainRecord record : list) {
				labUserTrainRecordDAO.deleteLabUserTrainRecord(record);
			}
		}
		if(labUserTrainVo.getStudentIds()!=null&&!labUserTrainVo.getStudentIds().equals("")){
			String[] userIds=labUserTrainVo.getStudentIds().split(",");
			for (String userId : userIds) {
				if(userId==null&&userId.trim().length()==0)continue;
				LabUser user=labUserDAO.getLabUser(userId.trim());
				LabUserTrainRecord labUserTrainRecord=new LabUserTrainRecord();
				labUserTrainRecord.setLabUser(user);
				labUserTrainRecord.setLabUserTrain(labUserTrain);
				labUserTrainRecord.setAddress(labUserTrain.getAddress());
				labUserTrainRecord.setTdate(labUserTrain.getTdate());
				labUserTrainRecord.setContent(labUserTrain.getContent());
				labUserTrainRecord.setTeacher(labUserTrain.getTeacher());
				labUserTrainRecord.setStation(labUserTrain.getStation());
				labUserTrainRecord.setOrgId(labUserTrain.getOrgId());
				labUserTrainRecord.setOrgName(labUserTrain.getOrgName());
				labUserTrainRecordDAO.addLabUserTrainRecord(labUserTrainRecord);
				//站内信
				if(labUserTrainVo.getIsMsg()!=null&&labUserTrainVo.getIsMsg().equals(Constants_Common.Y)){
					labMsgMainDAO.sendLabMsgToPerson(getSessionContainer().getUserId(), user.getId(), labUserTrain.getTitle(),"<p>日期："
							+labUserTrain.getTdate()+"；</p><p>地点："+labUserTrain.getAddress()+"；</p><p>内容："+labUserTrain.getContent()+"</p><p>备注："+labUserTrain.getRemark()+"</p>");
				}
				//短信
				if(labUserTrainVo.getIsSms()!=null&&labUserTrainVo.getIsSms().equals(Constants_Common.Y)){
				}
			}
		}
		return key;
	}

	@Override
	public boolean updateLabUserTrain4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabUserTrain labUserTrain=labUserTrainDAO.getLabUserTrain(id);
					labUserTrain.setIsDel(Constants_Common.Y);
					labUserTrainDAO.updateLabUserTrain(labUserTrain);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabUserTrainServiceImpl updateLabUserTrain4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabUserTrainVo> getLabUserTrainVoListByWhere(String wereHql) throws GlobalException{
		List<LabUserTrainVo> listLabUserTrainVo=new ArrayList<LabUserTrainVo>();
		String hql="FROM LabUserTrain WHERE isDel='"+Constants_Common.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabUserTrain> listLabUserTrain=labUserTrainDAO.find(hql);
		if(listLabUserTrain!=null&&listLabUserTrain.size()>0){
			for(LabUserTrain labUserTrain:listLabUserTrain){
				LabUserTrainVo labUserTrainVo=new LabUserTrainVo();
				labUserTrainVo=this.po2Vo(labUserTrain, labUserTrainVo);
				listLabUserTrainVo.add(labUserTrainVo);
			}
		}
		return listLabUserTrainVo;
	}
	public LabUserTrain vo2Po(LabUserTrainVo labUserTrainVo,LabUserTrain labUserTrain){
		BeanUtils.copyProperties(labUserTrainVo, labUserTrain,new String[]{"isDel","createUserId","createTime","tenantId"});
		return labUserTrain;
	}
	public LabUserTrainVo po2Vo(LabUserTrain labUserTrain,LabUserTrainVo labUserTrainVo){
		BeanUtils.copyProperties(labUserTrain, labUserTrainVo);
		if(StrUtils.isBlankOrNull(labUserTrain.getOrgName())){
			labUserTrainVo.setOrgName("全部");
		}
		return labUserTrainVo;
	}

	@Override
	public boolean updateLabUserTrain4Record(LabUserTrainVo labUserTrainVo)
			throws GlobalException {
		if(!StrUtils.isBlankOrNull(labUserTrainVo.getId())){
			LabUserTrain labUserTrain= labUserTrainDAO.getLabUserTrain(labUserTrainVo.getId());
		
			//重新初始化被培训人
			String hql="FROM LabUserTrainRecord WHERE 1=1";
			hql+=" AND labUserTrain.id='"+labUserTrain.getId()+"'";
			List<LabUserTrainRecord> list=labUserTrainRecordDAO.getLabUserTrainRecordList(hql);
			if(list!=null){
				for (LabUserTrainRecord record : list) {
					labUserTrainRecordDAO.deleteLabUserTrainRecord(record);
				}
			}
			boolean isALl=true;
			List<LabUserTrainRecordVo> recordList=labUserTrainVo.getRecordList();
			if(recordList!=null){
				for (LabUserTrainRecordVo recordVo : recordList) {
					if(recordVo.getLabUserId()!=null&&!"".equals(recordVo.getLabUserId())){
						LabUser user=labUserDAO.getLabUser(recordVo.getLabUserId());
						LabUserTrainRecord labUserTrainRecord=new LabUserTrainRecord();
						labUserTrainRecord.setLabUser(user);
						labUserTrainRecord.setLabUserTrain(labUserTrain);
						labUserTrainRecord.setAddress(labUserTrain.getAddress());
						labUserTrainRecord.setContent(labUserTrain.getContent());
						labUserTrainRecord.setTeacher(labUserTrain.getTeacher());
						labUserTrainRecord.setStation(labUserTrain.getStation());
						labUserTrainRecord.setOrgId(labUserTrain.getOrgId());
						labUserTrainRecord.setOrgName(labUserTrain.getOrgName());
						labUserTrainRecord.setResult(recordVo.getResult());
						labUserTrainRecord.setRemark(recordVo.getRemark());
						labUserTrainRecord.setTdate(recordVo.getTdate());
						labUserTrainRecordDAO.addLabUserTrainRecord(labUserTrainRecord);
						if(StrUtils.isBlankOrNull(recordVo.getResult())){
							isALl=false;
						}
					}
				}
			}
			if(isALl){
				labUserTrain.setStatus("2");
			}else{
				labUserTrain.setStatus("1");
			}
			labUserTrainDAO.updateLabUserTrain(labUserTrain);
		}
		return true;
	}

	@Override
	public List<LabUserTrainRecordVo> getLabUserTrainRecordList(
			LabUserTrainRecordVo labUserTrainRecordVo) throws GlobalException {
		String hql="FROM LabUserTrainRecord WHERE 1=1";
		hql+=" AND labUser.id='"+labUserTrainRecordVo.getLabUserId()+"'";
		hql+=" AND (result<>'' or result is not null) ";
		List<LabUserTrainRecord> list=labUserTrainRecordDAO.getLabUserTrainRecordList(hql);
		List<LabUserTrainRecordVo> recordList=new ArrayList<LabUserTrainRecordVo>();
		if(list!=null){
			for (LabUserTrainRecord record : list) {
				LabUserTrainRecordVo recordVo=new LabUserTrainRecordVo();
				BeanUtils.copyProperties(record, recordVo);
				if(record.getLabUser()!=null){
					recordVo.setLabUserId(record.getLabUser().getId());
					recordVo.setLabUserName(record.getLabUser().getName());
				}
				if(record.getLabUserTrain()!=null){
					recordVo.setLabUserTrainId(record.getLabUserTrain().getId());
					recordVo.setTitle(record.getLabUserTrain().getTitle());
				}
				recordList.add(recordVo);
			}
		}
		return recordList;
	}
}
