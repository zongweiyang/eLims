package cn.labsoft.labos.source.doc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.user.dao.ILabUserOrgDAO;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.doc.dao.ILabDocApplyDAO;
import cn.labsoft.labos.source.doc.dao.ILabDocDAO;
import cn.labsoft.labos.source.doc.entity.LabDoc;
import cn.labsoft.labos.source.doc.entity.LabDocApply;
import cn.labsoft.labos.source.doc.service.ILabDocService;
import cn.labsoft.labos.source.doc.vo.LabDocVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.solr.CreateIndexRunnable;
@Service("labDocService")
public class LabDocServiceImpl implements ILabDocService {
	private ILabDocDAO labDocDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabDocApplyDAO labDocApplyDAO;
	private ILabUserOrgDAO labUserOrgDAO;
	
	@Resource
	public void setLabDocDAO(ILabDocDAO labDocDAO) {
		this.labDocDAO = labDocDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabDocApplyDAO(ILabDocApplyDAO labDocApplyDAO) {
		this.labDocApplyDAO = labDocApplyDAO;
	}
	@Resource
	public void setLabUserOrgDAO(ILabUserOrgDAO labUserOrgDAO) {
		this.labUserOrgDAO = labUserOrgDAO;
	}

	@Override
	public LabDocVo addLabDoc(LabDocVo labDocVo) throws GlobalException {
		SessionContainer son =(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabDoc labDoc = new LabDoc();
		List<LabUpload> loadList = labUploadDAO.getLabUploadList(labDocVo.getUuid(), "doc");
		BeanUtils.copyProperties(labDocVo, labDoc, new String[] { "labDoc",
				"ids" });

		if (null == labDocVo.getPid() || "".equals(labDocVo.getPid())) {
			labDoc.setLabDoc((LabDoc) labDocDAO.findById(LabDoc.class, "1"));
		} else {
			labDoc.setLabDoc((LabDoc) labDocDAO.findById(LabDoc.class, labDocVo
					.getPid()));
		}
		labDoc.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labDoc.setCreatorId(son.getUserId());
		labDoc.setCreatorName(son.getUserName());
		String orgIds="";
		String orgNames="";
		List<LabUserOrg> uoList=labUserOrgDAO.getLabUserOrgListByUserId(son.getUserId());
		if(uoList!=null&&uoList.size()>0){
			for(LabUserOrg org : uoList){
				orgIds=" "+org.getOrg().getId();
				orgNames=" "+org.getOrg().getName();
			 }
		}
		labDoc.setOrgId(orgIds);
		labDoc.setOrgName(orgNames);
		labDoc.setIsDir(Constants_Source.N);
		if(null!=loadList&&loadList.size()>0){
		labDoc.setDocType(loadList.get(0).getType());
		}
		try {
			labDocDAO.addLabDoc(labDoc);
			labDocVo.setId(labDoc.getId());
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if (loadList != null) {
			for (LabUpload labUpload : loadList) {
				labUpload.setBusId(labDoc.getId());
				labUploadDAO.updateLabUpload(labUpload);
			}
		}
		//拼接地址
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		if (loadList != null) {
			for (LabUpload labUpload : loadList) {
				path += labUpload.getPath();
				labUpload.setPath(path);
			}
		}
		//添加索引
		Thread t = new Thread(new CreateIndexRunnable(loadList));
		t.start();
		return labDocVo;
	}

	@Override
	public LabDocVo addLabDocForFolder(LabDocVo labDocVo)
			throws GlobalException {
		SessionContainer son =(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		if (null != labDocVo) {
			LabDoc po = new LabDoc();
			BeanUtils.copyProperties(labDocVo, po, new String[] { "labDoc",
					"ids" });
			LabDoc parentPo = labDocDAO.getLabDocById(labDocVo.getPid());
			po.setLabDoc(parentPo);
			po.setCreateTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			po.setCreatorId(son.getUserId());
			po.setCreatorName(son.getUserName());
			String orgIds=son.getOrgId();
			String orgNames=son.getOrgName();
			po.setOrgId(orgIds);
			po.setOrgName(orgNames);
			po.setIsDir(Constants_Source.Y);
			labDocDAO.addLabDoc(po);
		}
		return labDocVo;
	}

	@Override
	public void deleteLabDoc(String id) throws GlobalException {
		LabDoc labDoc = labDocDAO.getLabDocById(id);
		String hql="FROM LabDocApply where 1=1 AND isDel='N' AND labDoc.id='"+id+"'";
		List<LabDocApply> polist=labDocApplyDAO.getLabDocApplyListByHql(hql);
		if(null!=polist&&polist.size()>0){
			for (LabDocApply labDocApply : polist) {
				labDocApply.setIsDel(Constants_Source.Y);
				labDocApply.setStatus("0");
				labDocApplyDAO.updateLabDocApply(labDocApply);
			}
	}
		labDoc.setIsDel(Constants_Source.Y);
		labDocDAO.updateLabDoc(labDoc);
	}
	@Override
	public void deleteLabDocByIds(String ids) throws GlobalException {
		if (null != ids && ids.length() > 0) {
			String[] idsArray = ids.split(",");
			if (null != idsArray && idsArray.length > 0) {
				for (String id : idsArray) {
					LabDoc labDoc = (LabDoc) labDocDAO.findById(LabDoc.class,
							id);
					labDoc.setIsDel(Constants_Source.Y);
					labDocDAO.updateLabDoc(labDoc);
				}
			}
		}
	}

	@Override
	public void deleteLabDoceBatch(String[] ids) throws GlobalException {
		for (int i = 0; i < ids.length; i++) {
			labDocDAO.deleteLabDoc(labDocDAO.getLabDocById(ids[i]));
		}

	}

	@Override
	public LabDocVo getLabDocById(String Id) throws GlobalException {
		LabDocVo labDocVo = new LabDocVo();
		LabDoc labDoc = labDocDAO.getLabDocById(Id);
		if (null != labDoc) {
			BeanUtils.copyProperties(labDoc, labDocVo, new String[] { "labDoc",
					"ids" });
			if (null != labDoc.getLabDoc()) {
				labDocVo.setPid(labDoc.getLabDoc().getId());
				labDocVo.setPname(labDoc.getLabDoc().getFileName());
			}
			labDocVo.setPath(getPath(labDoc,""));
		}
		return labDocVo;
	}
	public String getPath(LabDoc LabDoc,String str){
		if(str.length()>0){
			str=LabDoc.getFileName()+">"+str;
		}else{
			str=LabDoc.getFileName();
		}
		if(LabDoc.getLabDoc()!=null){
			str=getPath(LabDoc.getLabDoc(),str);
		}
		return str;
	}
	@Override
	public List<LabDocVo> getLabDocByName(String fileName)
			throws GlobalException {
		String hql = "FROM　LabDoc where 1=1 AND isDel != '" + Constants_Source.Y
				+ "' AND fileName = " + fileName;
		List<LabDoc> poList;
		List<LabDocVo> voList = new ArrayList<LabDocVo>();
		poList = labDocDAO.getLabDocList(hql);
		if (null != poList && poList.size() > 0) {
			for (LabDoc po : poList) {
				LabDocVo vo = new LabDocVo();
				BeanUtils.copyProperties(po, vo, new String[] { "pid",
						"labDoc", "ids" });
				vo.setPid(po.getLabDoc().getId());
				voList.add(vo);
			}
		}

		return voList;
	}

	@Override
	public List<LabDocVo> getLabDocListByPid(String pid, String flag)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql = "FROM LabDoc d WHERE 1=1 AND  d.isDel='N' "
				+ " AND d.labDoc.id ='" + pid + "'AND d.isDir='" + flag + "'";
		hql += " AND d.creatorId ='" + sessionContainer.getUserId() + "'";
		hql += " ORDER BY d.isDir DESC,d.createTime ASC";
		List<LabDocVo> voList = new ArrayList<LabDocVo>();
		List<LabDoc> poList = new ArrayList<LabDoc>();
		poList = labDocDAO.getLabDocList(hql);
		if (null != poList && poList.size() > 0) {
			for (LabDoc po : poList) {
				LabDocVo vo = new LabDocVo();
				BeanUtils.copyProperties(po, vo, new String[] { "pid", "ids",
						"labDoc" });
				if (null != po.getLabDoc() && !"".equals(po.getLabDoc())) {
					vo.setPid(po.getLabDoc().getId());
				}
				if (flag == Constants_Source.Y && null != po.getId()
						&& !"".equals(po.getId())) {
					hql = "SELECT count(*) FROM LabDoc  WHERE 1=1 AND isDel='N' AND labDoc.id='"
							+ po.getId()
							+ "' AND creatorId='"
							+ sessionContainer.getUserId() + "'";
					int num;
					try {
						num = (int) labDocDAO.getCountSize(hql);
						vo.setNum(String.valueOf(num));
					} catch (RuntimeException e) {
						//e.printStackTrace();
						throw new GlobalException("" + e.getMessage());
					}

				}
				if (flag == Constants_Source.N && null != po.getId()
						&& !"".equals(po.getId())) {
					List<LabUpload> uploadList = labUploadDAO.getLabUploadList(
							po.getId(), "doc");
					if (null != uploadList && uploadList.size() > 0) {
						vo.setPath(uploadList.get(0).getPath());
						vo.setName(uploadList.get(0).getName());
					}

				}
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public List<LabDocVo> getLabDocListBySql(String id, String pid)
			throws GlobalException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabDocPageResult(LabDocVo labDocVo,PageResult pageResult)
			throws GlobalException {
		String sql = "FROM LabDoc d WHERE 1=1 AND d.isDel !='" + Constants_Source.Y
				+ "'";
		if (null == labDocVo.getPid() || "".equals(labDocVo.getPid())) {
			sql += " AND d.labDoc.id is null ";
		} else {
			sql += " AND d.labDoc.id ='" + labDocVo.getPid() + "' ";
		}
		
		sql += " ORDER BY d.isDir DESC, d.createTime ASC";
		 pageResult = labDocDAO.getPageResult(sql,pageResult);

		List<LabDoc> poList = pageResult.getResultList();
		List<LabDocVo> voList = new ArrayList<LabDocVo>();
		if (null != poList && poList.size() > 0) {
			LabDocVo vo = new LabDocVo();
			for (LabDoc po : poList) {
				BeanUtils.copyProperties(po, vo, new String[] { "pid","labDoc", "ids" });
				if(null!=po.getLabDoc()){
					vo.setPid(po.getLabDoc().getId());
				}else{
					vo.setPid("");
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public Boolean updateLabDoc(LabDocVo labDocVo) throws GlobalException {
		Boolean key = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SessionContainer sessionConstants = (SessionContainer) session
				.getAttribute(SessionContainer.Session_Container);
		if (null != labDocVo) {
			LabDoc labDoc = labDocDAO.getLabDocById(labDocVo.getId());

			if (null != labDocVo.getFileName()
					&& !"".equals(labDocVo.getFileName())) {
				labDoc.setFileName(labDocVo.getFileName());
			}
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labDocVo
					.getId(), "doc");
			if(null!=loadList&&loadList.size()>0){
				labDoc.setDocType(loadList.get(0).getType());
				}
			if (null != labDocVo.getRemark()
					&& !"".equals(labDocVo.getRemark())) {
				labDoc.setRemark(labDocVo.getRemark());
			}
			labDoc.setModifier(sessionConstants.getUserName());
			labDoc.setModifier(sessionConstants.getUserId());
			key = labDocDAO.updateLabDocBoolean(labDoc);
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabDocVo> getLabDocAllOtherList(LabDocVo labDocVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		List<LabDoc> poList = new ArrayList<LabDoc>();
		String hql = "FROM LabDoc d WHERE 1=1 AND  d.isDel='N' AND d.isDir='"
				+ Constants_Source.N
				+  "'";
		if ( null != labDocVo.getOrgId() && !"".equals(labDocVo.getOrgId())&&!"-1".equals(labDocVo.getOrgId())) {
			hql += " AND orgId=" + labDocVo.getOrgId() + "";
		}
		if (null != labDocVo.getCreatorName()
				&& !"".equals(labDocVo.getCreatorName())) {
			hql += " AND creatorName LIKE'%" + labDocVo.getCreatorName() + "%'";
		}
		if (null != labDocVo.getFileName()
				&& !"".equals(labDocVo.getFileName())) {
			hql += " AND fileName  LIKE '%" + labDocVo.getFileName() + "%'";
		}
		hql += " ORDER BY d.createTime ASC";
		List<LabDocVo> voList = new ArrayList<LabDocVo>();
		poList = labDocDAO.getLabDocList(hql);
		String sql = "FROM LabDocApply WHERE 1=1 AND isDel='N' AND applyId='"
				+ sessionContainer.getUserId() + "'";
		List<LabDocApply> applyList = labDocDAO.find(sql);
		if (null != poList && poList.size() > 0) {
			for (int i = 0; i < poList.size(); i++) {
				LabDocVo vo = new LabDocVo();
				BeanUtils.copyProperties(poList.get(i), vo, new String[] {
						"pid", "labDoc", "ids" });
				vo.setStatus("0");
					List<LabUpload> uploadList = labUploadDAO.getLabUploadList(
							poList.get(i).getId(), "doc");
					if (null != uploadList && uploadList.size() > 0) {
						vo.setExt01(uploadList.get(0).getPath());
						vo.setName(uploadList.get(0).getName());
					}

				for (int j = 0; j < applyList.size(); j++) {
					if (applyList.get(j).getLabDoc().getId() == poList.get(i)
							.getId()) {
						vo.setStatus(applyList.get(j).getStatus());
					}
				}
				voList.add(vo);
			}
		}
		return voList;
	}
	@Override
	public LabDocVo updateLabDocForFolder(LabDocVo labDocVo) throws GlobalException {
		LabDoc labDoc=labDocDAO.getLabDocById(labDocVo.getId());
		if(!StrUtils.isBlankOrNull(labDocVo.getFileName())){
			labDoc.setFileName(labDocVo.getFileName());
		}
		if(!StrUtils.isBlankOrNull(labDocVo.getDocIcon())){
			labDoc.setDocIcon(labDocVo.getDocIcon());
		}
		if(!StrUtils.isBlankOrNull(labDocVo.getRemark())){
			labDoc.setRemark(labDocVo.getRemark());
		}
		labDoc=labDocDAO.updateLabDoc(labDoc);
		BeanUtils.copyProperties(labDoc, labDocVo, new String[] { "pid",
				"labDoc", "ids" });
		labDocVo.setPid(null == labDoc.getLabDoc().getId() ? "" : labDoc.getLabDoc()
				.getId());
		return labDocVo;
	}
	@Override
	public PageResult getLabDocSolrPR(LabDocVo labDocVo, PageResult pageResult) throws GlobalException {
		String hql="FROM LabDoc WHERE 1=1 ";
		if(!StrUtils.isBlankOrNull(labDocVo.getExt4())){
			String [] ex2 = labDocVo.getExt2().split(",");
			String [] ex3 = labDocVo.getExt3().split(",");
			String [] ex4 = labDocVo.getExt4().split(",");
			String [] ex5 = labDocVo.getExt5().split(",");
			
			for (int i = 0; i < ex2.length; i++) {
				if(!"all".equals(ex2[i].trim())){
					if(i>=1){
						if("or".equals(ex5[i-1].trim()) || "and".equals(ex5[i-1].trim())){
							hql +=" "+ex5[i-1].trim()+" ";
						}else{
							hql +=" AND ";
						}
					}else{
						hql +=" AND ";
					}
					hql += ex2[i].trim()+" ";
					if("模糊".equals(ex3[i].trim())){
						if(i>=1){
							if("<>".equals(ex3[i-1].trim())){
								hql +=" NOT LIKE "+"'%"+ex4[i].trim()+"%'";
							}else{
								hql +=" LIKE "+"'%"+ex4[i].trim()+"%'";
							}
						}else{
							hql +=" LIKE "+"'%"+ex4[i].trim()+"%'";
						}
					}else{
						if(i>=1){
							if("<>".equals(ex5[i-1].trim())){
								hql +=" <> "+"'"+ex4[i].trim()+"'";
							}else{
								hql +=" = "+"'"+ex4[i].trim()+"'";
							}
						}else{
							hql +=" = "+"'"+ex4[i].trim()+"'";
						}
					}
				}
			}
		}
		//添加文献类型
		if(!StrUtils.isBlankOrNull(labDocVo.getLiterature())){
			String[] literature = labDocVo.getLiterature().split(",");
			hql+=" AND literature IN (";
			for (String s : literature) {
				if(!StrUtils.isBlankOrNull(s)){
					hql+="'"+s+"',";
				}
			}
			hql=hql.substring(0, hql.length()-1);
			hql+=")";
		}
		hql+="AND docType <> null";
		pageResult=labDocDAO.getPageResult(hql, pageResult);
		//拼接文件下载路径
		List<LabDocVo> list1 = new ArrayList();
		List<LabDoc> list = pageResult.getResultList();
		for (LabDoc po : list) {
			LabDocVo vo = new LabDocVo();
			BeanUtils.copyProperties(po, vo, new String[] { "pid","labDoc", "ids" });
			vo.setLabUploadList(labUploadDAO.getLabUploadList(po.getId(), null));
			list1.add(vo);
		}
		
		pageResult.setResultList(list1);
		return pageResult;
	}
}
