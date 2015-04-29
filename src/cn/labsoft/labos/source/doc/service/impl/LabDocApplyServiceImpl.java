package cn.labsoft.labos.source.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.source.doc.dao.ILabDocApplyDAO;
import cn.labsoft.labos.source.doc.dao.ILabDocDAO;
import cn.labsoft.labos.source.doc.entity.LabDoc;
import cn.labsoft.labos.source.doc.entity.LabDocApply;
import cn.labsoft.labos.source.doc.service.ILabDocApplyService;
import cn.labsoft.labos.source.doc.vo.LabDocApplyVo;
import cn.labsoft.labos.source.doc.vo.LabDocVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
@Service("labDocApplyService")
public class LabDocApplyServiceImpl implements ILabDocApplyService {

	private ILabDocDAO labDocDAO;
	private ILabDocApplyDAO labDocApplyDAO;
	private ILabUploadDAO labUploadDAO;
	
	@Resource
	public void setLabDocDAO(ILabDocDAO labDocDAO) {
		this.labDocDAO = labDocDAO;
	}
	@Resource
	public void setLabDocApplyDAO(ILabDocApplyDAO labDocApplyDAO) {
		this.labDocApplyDAO = labDocApplyDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@Override
	public void addLabDocApply(LabDocVo labDocVo) throws GlobalException {
		LabDoc labDoc = labDocDAO.getLabDocById(labDocVo.getId());
		
		LabDocApply labDocApply = new LabDocApply();
		labDocApply.setLabDoc(labDoc);
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labDocApply.setApplyId(sessionContainer.getUserId());
		labDocApply.setApplyName(sessionContainer.getUserName());
		labDocApply.setApplyTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labDocApply.setStatus("1");// 申请
		labDocApplyDAO.addLabDocApply(labDocApply);
	}

	@Override
	public void addLabDocApplyBatch(LabDocVo labDocVo) throws GlobalException {
     for(int i=0;i<labDocVo.getIds().length;i++){
    	 LabDoc labDoc = labDocDAO.getLabDocById(labDocVo.getIds()[i]); 
    	 LabDocApply labDocApply = new LabDocApply();
 		 labDocApply.setLabDoc(labDoc);
 		 SessionContainer sessionContainer = (SessionContainer) ServletActionContext
 				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
 		 labDocApply.setApplyId(sessionContainer.getUserId());
 		 labDocApply.setApplyName(sessionContainer.getUserName());
 		 labDocApply.setApplyTime(DateUtils.format(new Date(),
 				DateUtils.formatStr_yyyyMMdd));
 		 labDocApply.setStatus("1");// 申请
 		 labDocApplyDAO.addLabDocApply(labDocApply);
     }
	}

	@Override
	public List<LabDocApplyVo> getLabDocApplyList() throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql = "FROM LabDocApply WHERE 1=1 AND isDel='N' AND status !=0 AND applyId='"
				+ sessionContainer.getUserId() + "'";
		hql += " ORDER BY applyTime ASC ";
		List<LabDocApplyVo> voList = new ArrayList<LabDocApplyVo>();
		List<LabDocApply> applyList = labDocApplyDAO
				.getLabDocApplyListByHql(hql);
		if (null != applyList && applyList.size() > 0) {
			for (LabDocApply po : applyList) {
				LabDocApplyVo vo = new LabDocApplyVo();
				BeanUtils.copyProperties(po, vo, new String[] { "labDoc" });
				if (null != po.getLabDoc()) {
					LabDocVo labDocVo = new LabDocVo();
					BeanUtils.copyProperties(po.getLabDoc(), labDocVo,
							new String[] { "labDoc", "ids" });
					vo.setLabDocVo(labDocVo);
					List<LabUpload> uploadList = labUploadDAO.getLabUploadList(
							labDocVo.getId(), "doc");
					if (null != uploadList && uploadList.size() > 0) {
						vo.setExt1(uploadList.get(0).getPath());
					}
				}
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public void cancelLabDocApply(LabDocApplyVo labDocApplyVo)
			throws GlobalException {
		LabDocApply labDocApply = labDocApplyDAO
				.getLabDocApplyById(labDocApplyVo.getId());
		labDocApply.setIsDel(Constants_Source.Y);
		labDocApply.setStatus("0");
		labDocApplyDAO.updateLabDocApply(labDocApply);
	}

	@Override
	public void updateLabDocApply(LabDocApplyVo labDocApplyVo)
			throws GlobalException {
		LabDocApply labDocApply = labDocApplyDAO
				.getLabDocApplyById(labDocApplyVo.getId());
		labDocApply.setStatus(labDocApplyVo.getFlag());
		labDocApplyDAO.updateLabDocApply(labDocApply);
	}

	@Override
	public void updateLabDocApplyBatch(LabDocVo labDocVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
		.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		 for(int i=0;i<labDocVo.getIds().length;i++){
			 String hql="FROM LabDocApply where 1=1 AND isDel='N'  AND labDoc.id='"+labDocVo.getIds()[i]+"' AND applyId='"+sessionContainer.getUserId()+"'";
				List<LabDocApply> applyList=labDocApplyDAO.getLabDocApplyListByHql(hql);
				if(null!=applyList&&applyList.size()>0){
					applyList.get(0).setStatus("0");
					applyList.get(0).setIsDel(Constants_Source.Y);
				
				labDocApplyDAO.updateLabDocApply(applyList.get(0)); 
				}
		 }

	}

	@Override
	public void updateLabDocApplyByLabDocId(LabDocVo labDocVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
		.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql="FROM LabDocApply where 1=1 AND isDel='N' AND labDoc.id='"+labDocVo.getId()+"' AND applyId='"+sessionContainer.getUserId()+"'";
		List<LabDocApply> applyList=labDocApplyDAO.getLabDocApplyListByHql(hql);
		if(null!=applyList&&applyList.size()>0){
			applyList.get(0).setStatus("0");
			applyList.get(0).setIsDel(Constants_Source.Y);
		
		labDocApplyDAO.updateLabDocApply(applyList.get(0));
		}
	}

	@Override
	public LabDocApplyVo getLabDocApplyById(String id) throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
		.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql="FROM LabDocApply where 1=1 AND isDel='N' AND labDoc.id='"+id+"'AND applyId='"+sessionContainer.getUserId()+"'";
		List<LabDocApply> polist=labDocApplyDAO.getLabDocApplyListByHql(hql);
		LabDocApplyVo vo=new LabDocApplyVo();
		if(null!=polist&&polist.size()>0){
		BeanUtils.copyProperties(polist.get(0), vo, new String[]{"labDoc","ids"});
		return vo;
		}
		return null;
	}
	@Override
	public LabDocApplyVo getLabDocApplyByLabDocId(String id) throws GlobalException {
		String hql="FROM LabDocApply where 1=1 AND isDel='N'  AND id='"+id+"'";
		List<LabDocApply> polist=labDocApplyDAO.getLabDocApplyListByHql(hql);
		LabDocApplyVo vo=new LabDocApplyVo();
		if(null!=polist&&polist.size()>0){
		BeanUtils.copyProperties(polist.get(0), vo, new String[]{"labDoc","ids"});
		return vo;
		}
		return null;
	}
	@Override
	public List<LabDocApplyVo> getLabDocOterApplyList(LabDocVo labvo) throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
		.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		List<LabDocApplyVo> voList=new ArrayList<LabDocApplyVo>();
		String hql="FROM LabDocApply where 1=1 AND isDel='N' AND labDoc.creatorId='"+sessionContainer.getUserId()+"'";
		if(null!=labvo.getOrgId()&&!"".equals(labvo.getOrgId())&&!"-1".equals(labvo.getOrgId())){
			hql+=" AND labDoc.orgId="+labvo.getOrgId()+"";
		}
		if(null!=labvo.getFileName()&&!"".equals(labvo.getFileName())){
			hql+=" AND labDoc.fileName LIKE '%"+labvo.getFileName()+"%'";
		}
		if(null!=labvo.getApplyName()&&!"".equals(labvo.getApplyName())){
			hql+=" AND applyName LIKE '%"+labvo.getApplyName()+"%'";
		}
		if(null!=labvo.getFlag()&&!"".equals(labvo.getFlag())&&!"-1".equals(labvo.getFlag())){
			if("1".equals(labvo.getFlag())){
				hql+=" AND status !=2";
			}else{
			hql+=" AND status='"+labvo.getFlag()+"'";
			}
		}
		List<LabDocApply> polist=labDocApplyDAO.getLabDocApplyListByHql(hql);
		if(null!=polist&&polist.size()>0){
			for(int i=0;i<polist.size();i++){
				LabDocApplyVo vo=new LabDocApplyVo();
			BeanUtils.copyProperties(polist.get(i), vo, new String[]{"labDoc","ids"});
			if(null!=polist.get(i).getLabDoc()){
				LabDocVo labDocVo=new LabDocVo();
				BeanUtils.copyProperties(polist.get(i).getLabDoc(), labDocVo, new String[]{"labDoc","ids"});
				vo.setLabDocVo(labDocVo);
			}
			voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public void updateLabDocApplyById(LabDocApplyVo labDocApplyVo)
			throws GlobalException {
			SessionContainer sessionContainer = (SessionContainer) ServletActionContext
			.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
			String hql="FROM LabDocApply where 1=1 AND isDel='N' AND id='"+labDocApplyVo.getId()+"' AND labDoc.creatorId='"+sessionContainer.getUserId()+"'";
			List<LabDocApply> applyList=labDocApplyDAO.getLabDocApplyListByHql(hql);
			if(null!=applyList&&applyList.size()>0){
				applyList.get(0).setStatus(labDocApplyVo.getFlag());
			
			labDocApplyDAO.updateLabDocApply(applyList.get(0));
			}
		
	}

	@Override
	public void updateLabDocOtherApplyBatch(LabDocApplyVo labDocApplyVo)
			throws GlobalException {
		 for(int i=0;i<labDocApplyVo.getIds().length;i++){
			 String hql="FROM LabDocApply where 1=1 AND isDel='N' AND id='"+labDocApplyVo.getIds()[i]+"'";
				List<LabDocApply> applyList=labDocApplyDAO.getLabDocApplyListByHql(hql);
				if(null!=applyList&&applyList.size()>0){
					applyList.get(0).setStatus(labDocApplyVo.getFlag());
				labDocApplyDAO.updateLabDocApply(applyList.get(0)); 
				}
		 }
	}

	@Override
	public List<LabDocApplyVo> getLabDocApplyVoById(String id)
			throws GlobalException {
		String hql="FROM LabDocApply where 1=1 AND isDel='N' AND labDoc.id='"+id+"'";
		List<LabDocApply> polist=labDocApplyDAO.getLabDocApplyListByHql(hql);
		LabDocApplyVo vo=new LabDocApplyVo();
		List<LabDocApplyVo> volist=new ArrayList<LabDocApplyVo>();
		if(null!=polist&&polist.size()>0){
			for (int i = 0; i < polist.size(); i++) {
				BeanUtils.copyProperties(polist.get(i), vo, new String[]{"labDoc","ids"});	
				volist.add(vo);
			}
		
		return volist;
		}
		return null;
	}

}
