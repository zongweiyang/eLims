package cn.labsoft.labos.source.klg.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemMethodDAO;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;
import cn.labsoft.labos.source.klg.service.ILabMethodService;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labMethodService")
public class LabMethodServiceImpl implements ILabMethodService {
	private ILabMethodDAO labMethodDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabStandardItemMethodDAO labStandardItemMethodDAO;
	
	@Resource
	public void setLabMethodDAO(ILabMethodDAO labMethodDAO) {
		this.labMethodDAO = labMethodDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabStandardItemMethodDAO(
			ILabStandardItemMethodDAO labStandardItemMethodDAO) {
		this.labStandardItemMethodDAO = labStandardItemMethodDAO;
	}

	private LabMethodVo labMethodTOLabMethodVo(LabMethod po, LabMethodVo labMethodVo) {
		BeanUtils.copyProperties(po, labMethodVo, new String[] { "appApparatus", "reaReagent", "LabItem" ,"isDel"});
		return labMethodVo;
	}

	private LabMethod labMethodVoTOLabMethod(LabMethodVo labMethodVo, LabMethod po) {
		BeanUtils.copyProperties(labMethodVo, po, new String[] { "appApparatusName", "appApparatusNo", "reaReagentName", "reaReagentNo","isDel","uuid","createTime","tenantId","createUserId" });
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exist4LabMethodCode(String code) throws GlobalException {
		String hql = "FROM LabMethod WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND code  LIKE '" + code + "'";
		List list = labMethodDAO.find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabMethodPR(LabMethodVo labMethodVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabMethod WHERE isDel='" + Constants_Source.N + "'";
		if (!StrUtils.isBlankOrNull(labMethodVo.getName())) {
			hql += " AND name LIKE '%" + labMethodVo.getName() + "%'";
		}
		if (!StrUtils.isBlankOrNull(labMethodVo.getCode())) {
			hql += " AND code LIKE '%" + labMethodVo.getCode() + "%'";
		}
		try {
			pageResult = labMethodDAO.getPageResult(hql, pageResult);
			List<LabMethod> labMethodList = pageResult.getResultList();
			List<LabMethodVo> labMethodVoList = new ArrayList<LabMethodVo>();
			if (labMethodList.size() > 0) {
				for (LabMethod po : labMethodList) {
					LabMethodVo vo = new LabMethodVo();
					this.labMethodTOLabMethodVo(po, vo);
					labMethodVoList.add(vo);
				}
			}
			pageResult.setResultList(labMethodVoList);
		} catch (Exception e) {
			//e.printStackTrace();
			Log4J.error(e.getMessage(), e);
			throw new GlobalException("检测方法分页查询出错！" + e.getMessage());
		}
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabMethodList(LabMethodVo labMethodVo , PageResult pageResult) throws GlobalException {
		String hql = "FROM LabMethod WHERE isDel='" + Constants_Source.N + "' ";
		if (null != labMethodVo.getName() && !"".equals(labMethodVo.getName())) {
			hql += " AND name LIKE '%" + labMethodVo.getName() + "%'";
		}
		if (null != labMethodVo.getCode() && !"".equals(labMethodVo.getCode())) {
			hql += " AND code LIKE '%" + labMethodVo.getCode() + "%'";
		}
		pageResult =  labMethodDAO.getPageResult(hql, pageResult);
		List<LabMethod> labMethodList =pageResult.getResultList();
		List<LabMethodVo> methodVoList = new ArrayList<LabMethodVo>();
		if (labMethodList.size() > 0) {
			for (LabMethod labMethod : labMethodList) {
				LabMethodVo vo = new LabMethodVo();
				this.labMethodTOLabMethodVo(labMethod, vo);
				methodVoList.add(vo);
			}
		}
		pageResult.setResultList(methodVoList);
		return pageResult;
	}

	@Override
	public boolean update2DelLabMethod(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			for (String id : ids) {
					List<LabStandardItemMethod> labStandardItemMethodList = labStandardItemMethodDAO.getListByMethodId(id);
					if( null != labStandardItemMethodList && labStandardItemMethodList.size() > 0){
						for(LabStandardItemMethod labStandardItemMethod: labStandardItemMethodList){
							labStandardItemMethod.setIsDel(Constants_Source.Y);
							labStandardItemMethodDAO.updateLabStandardItemMethod(labStandardItemMethod);
						}
					}
					LabMethod labMethod = (LabMethod) labMethodDAO.findById(LabMethod.class, id);
					labMethod.setIsDel(Constants_Source.Y);
					labMethodDAO.updateLabMethod(labMethod);
			}
		} catch (Exception e) {
			key = false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabMethod(LabMethodVo labMethodVo) throws GlobalException {
		LabMethod labMethod = new LabMethod();
		boolean key = true;
		try {
			this.labMethodVoTOLabMethod(labMethodVo, labMethod);
			labMethodDAO.updateLabMethod(labMethod);
		} catch (Exception e) {
			key = false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabMethodVo getLabMethod(String id) {
		LabMethodVo labMethodVo = new LabMethodVo();
		if (id != null && !id.equals("")) {
			LabMethod LabMethod = (LabMethod) labMethodDAO.findById(LabMethod.class, id);
			this.labMethodTOLabMethodVo(LabMethod, labMethodVo);
		}

		return labMethodVo;
	}

	@Override
	public boolean addLabMethod(LabMethodVo labMethodVo) throws GlobalException {
		boolean key = true;
		try {
			LabMethod labMethod = new LabMethod();
			this.labMethodVoTOLabMethod(labMethodVo, labMethod);
			labMethod.setIsDel(Constants_Source.N);
			labMethodDAO.addLabMethod(labMethod);
			
			List<LabUpload> loadList=labUploadDAO.getLabUploadList(labMethodVo.getUuid(),"klg-method");
			if(loadList!=null){
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(labMethod.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
		} catch (Exception e) {
			key = false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public List<LabMethodVo> getLabMethodByItemsId(String itemsId) throws GlobalException {
		List<LabMethodVo> labMethodVoList = new ArrayList<LabMethodVo>();
		String hql = "FROM LabMethod WHERE isDel='" + Constants_Source.N + "' AND labItem.id='" + itemsId + "'";
		List<LabMethod> poList = labMethodDAO.find(hql);
		for (LabMethod LabMethod : poList) {
			LabMethodVo vo = new LabMethodVo();
			this.labMethodTOLabMethodVo(LabMethod, vo);
			labMethodVoList.add(vo);
		}
		return labMethodVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean exist4LabMethodName(String name) throws GlobalException {
		String hql = new StringBuilder().append("FROM LabMethod WHERE isDel='").append(Constants_Source.N).append("'").append(" AND name='").append(name).append("'").toString();
		List list = labMethodDAO.find(hql);
		if (list != null && list.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	@Override
	public List<LabMethodVo> getLabMethodByName(String name) throws GlobalException {
		List<LabMethodVo> labMethodVoList = new ArrayList<LabMethodVo>();
		String hql = "FROM LabMethod WHERE isDel='" + Constants_Source.N + "' AND name in ('" + name.replace(",", "','") + "')";
		List<LabMethod> poList = labMethodDAO.find(hql);
		for (LabMethod LabMethod : poList) {
			LabMethodVo vo = new LabMethodVo();
			this.labMethodTOLabMethodVo(LabMethod, vo);
			labMethodVoList.add(vo);
		}
		return labMethodVoList;
	}
	@Override
	public List<LabMethodVo> getLabStandardItemMethodByItems(String itemId,String standId) throws GlobalException {
		// TODO Auto-generated method stub
		List<LabMethodVo> listLabMethodVo=new ArrayList<LabMethodVo>();
		if(!StrUtils.isBlankOrNull(itemId)&&!StrUtils.isBlankOrNull(standId)){
			String hql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"' AND item.id='"+itemId+"'";
				   hql+=" AND standard.id='"+standId+"'";
		    List<LabStandardItemMethod> listLabStandardItemMethod=labMethodDAO.find(hql);
		    if(listLabStandardItemMethod!=null&&listLabStandardItemMethod.size()>0){
		    	for(LabStandardItemMethod po:listLabStandardItemMethod){
		    		if(po.getMethod()!=null){
		    			LabMethodVo vo = new LabMethodVo();
		    			listLabMethodVo.add(this.labMethodTOLabMethodVo(po.getMethod(), vo));
		    		}
		    	}
		    }
		}
		return listLabMethodVo;
	}
}
