package cn.labsoft.labos.source.appara.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.action.Constant;
import cn.labsoft.labos.source.appara.dao.ILabApparaUseDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaUse;
import cn.labsoft.labos.source.appara.service.ILabApparaUseService;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;
import cn.labsoft.labos.utils.StrUtils;
@Service("labApparaUseService")
public class LabApparaUseServiceImpl implements ILabApparaUseService {
	private ILabApparaUseDAO labApparaUseDAO;
	private ILabUserDAO labUserDAO;

	
	@Resource
	public void setLabApparaUseDAO(ILabApparaUseDAO labApparaUseDAO) {
		this.labApparaUseDAO = labApparaUseDAO;
	}
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}

	@SuppressWarnings("unchecked")
	public boolean addLabApparaUse(LabApparaUseVo vo) throws GlobalException {
		try {
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				String hql = "FROM LabApparaUse WHERE 1=1 AND labAppara.id = '" + vo.getAppId() + "'";
				//预约判断
				if(!StrUtils.isBlankOrNull(vo.getStartDate())){
					hql += " AND beEndDate >= '" + vo.getStartDate() + "'";
				}
				if(!StrUtils.isBlankOrNull(vo.getEndDate())){
					hql += " AND beStartDate <= '" + vo.getBeEndDate() + "'";
				}
				hql +=" AND labAppara.status = '0'";
				List<LabApparaUse> list = labApparaUseDAO.find(hql);
				if (list.size() <= 0) {
					LabApparaUse po = new LabApparaUse();
					LabAppara app = new LabAppara();
					app.setId(vo.getAppId());
					app.setName(vo.getAppName());
					app.setNo(vo.getAppNo());
					BeanUtils.copyProperties(vo, po, new String[] { "" });
					po.setLabAppara(app);
					if (!StrUtils.isBlankOrNull(vo.getUseUserId())) {
						LabUser user = labUserDAO.getLabUser(vo.getUseUserId());
						po.setUseUser(user.getName());
					}
					labApparaUseDAO.addLabApparaUse(po);
				}else{
					return false;
				}
			}
		} catch (Exception e) {
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean updateLabApparaUse(LabApparaUseVo vo) throws GlobalException {
		try {
			//查询时间是否冲突
			String hql ="FROM LabApparaUse WHERE 1=1 AND labAppara.id = '" + vo.getAppId() + "'";
			if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
				hql += " AND beStartDate > '" + vo.getBeStartDate() + "'";
			}
			if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
				hql += " AND beEndDate < '" + vo.getBeEndDate() + "'";
			}
			List<LabApparaUse> list = labApparaUseDAO.find(hql);
			if(list.size()<=0){
				LabApparaUse po = labApparaUseDAO.getLabApparaUseById(vo.getId());
				BeanUtils.copyProperties(vo, po, new String[] { "" });
				String infoId = vo.getAppId();
				if (null != infoId && !"".equals(infoId)) {
					LabAppara info = (LabAppara) labApparaUseDAO.findById(LabAppara.class, infoId);
					if (null != info) {
						po.setLabAppara(info);
					}
				}
				labApparaUseDAO.updateLabApparaUse(po);
			}else{
				return false;
			}
		} catch (Exception e) {
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	public LabApparaUseVo getLabApparaUse(String id) throws GlobalException {
		LabApparaUseVo vo = new LabApparaUseVo();
		try {
			LabApparaUse po = labApparaUseDAO.getLabApparaUseById(id);
			BeanUtils.copyProperties(po, vo, new String[] { "" });
			if (null != po.getLabAppara()) {
				vo.setAppId(po.getLabAppara().getId());
				vo.setAppName(po.getLabAppara().getName());
				vo.setAppNo(po.getLabAppara().getNo());
			}
		} catch (Exception e) {
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	public PageResult getLabApparaUsePR(LabApparaUseVo vo, PageResult pageResult) throws GlobalException {
		try {
			String hql = " FROM LabApparaUse WHERE 1=1";
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				hql += " AND labAppara.id = '" + vo.getAppId() + "'";
			}
			if (!StrUtils.isBlankOrNull(vo.getAppName())) {
				hql += " AND labAppara.name LIKE '%" + vo.getAppName() + "%'";
			}
			pageResult = labApparaUseDAO.getPageResult(hql, pageResult);
			if (null != pageResult) {
				List<LabApparaUse> list = pageResult.getResultList();
				List<LabApparaUseVo> voList = new ArrayList<LabApparaUseVo>();
				if (null != list && list.size() > 0) {
					for (LabApparaUse po : list) {
						LabApparaUseVo apparaUseVo = new LabApparaUseVo();
						apparaUseVo.toVo(po);
						if (null != po.getLabAppara()) {
							apparaUseVo.setAppId(po.getLabAppara().getId());
							apparaUseVo.setAppName(po.getLabAppara().getName());
						}
						voList.add(apparaUseVo);
					}
				}
				pageResult.setResultList(voList);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	public List<LabApparaUseVo> getLabApparaUseList(LabApparaUseVo vo) throws GlobalException {
		try {
			List<LabApparaUseVo> voList = new ArrayList<LabApparaUseVo>();
			String hql = "FROM LabApparaUse WHERE 1=1 AND beStartDate != ''";
			List<LabApparaUse> poList = labApparaUseDAO.find(hql);
			for (LabApparaUse po : poList) {
				LabApparaUseVo useVo = new LabApparaUseVo();
				useVo.toVo(po);
				useVo.setAppName(po.getLabAppara().getName());
				useVo.setAppId(po.getLabAppara().getId());
				voList.add(useVo);
			}
			return voList;
		} catch (Exception e) {
			//e.printStackTrace();
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public void deleteLabApparaUse(LabApparaUseVo vo) throws GlobalException {
		if (!StrUtils.isBlankOrNull(vo.getId())) {
			String[] ids = vo.getId().split(",");
			try {
				labApparaUseDAO.deleteLabApparaUseById(ids);
			} catch (GlobalException e) {
				//e.printStackTrace();
				Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
	}
	@Override
	public boolean addLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(listLabApparaUseVo!=null&&listLabApparaUseVo.size()>0){
			for(LabApparaUseVo vo:listLabApparaUseVo){
				try {
					if (!StrUtils.isBlankOrNull(vo.getAppId())) {
						String hql = "FROM LabApparaUse WHERE 1=1 AND labAppara.id = '" + vo.getAppId() + "'";
						if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
							hql += " AND beEndDate >= '" + vo.getBeStartDate() + "'";
						}
						if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
							hql += " AND beStartDate <= '" + vo.getBeEndDate() + "'";
						}
						List<LabApparaUse> list = labApparaUseDAO.find(hql);
						if (list.size() <= 0) {
							LabApparaUse po = new LabApparaUse();
							LabAppara app =(LabAppara) labApparaUseDAO.findById(LabAppara.class, vo.getAppId());
							BeanUtils.copyProperties(vo, po, new String[] { "" });
							if(app!=null)
							po.setLabAppara(app);
							po.setStauts(Constant.APP_BESPEAK_ST);
							if (!StrUtils.isBlankOrNull(vo.getUseUserId())) {
								LabUser user = labUserDAO.getLabUser(vo.getUseUserId());
								po.setUseUser(user.getName());
							}
							labApparaUseDAO.addLabApparaUse(po);
						}else{
							key=false;
						}
					}
				} catch (Exception e) {
					Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
					key=false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return key;
	}
	
	@Override
	public boolean updateLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo, String samBeatchId) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(!StrUtils.isBlankOrNull(samBeatchId)){
			String hql="FROM LabApparaUse WHERE isDel='"+Constants_Source.N+"' AND samBeatchId='"+samBeatchId+"'";
			List<LabApparaUse> listLabApparaUse=labApparaUseDAO.find(hql);
			if(listLabApparaUse!=null&&listLabApparaUse.size()>0){
				key=labApparaUseDAO.deleteAllLabApparaUse(listLabApparaUse);
			}
		}
		if(key)
		key=this.addLabApparaUseList(listLabApparaUseVo,samBeatchId);
		return key;
	}
	@Override
	public boolean addLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(listLabApparaUseVo!=null&&listLabApparaUseVo.size()>0){
			for(LabApparaUseVo vo:listLabApparaUseVo){
				try {
					if (!StrUtils.isBlankOrNull(vo.getAppId())) {
						String hql = "FROM LabApparaUse WHERE 1=1 AND labAppara.id = '" + vo.getAppId() + "'";
						if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
							hql += " AND beEndDate >= '" + vo.getBeStartDate() + "'";
						}
						if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
							hql += " AND beStartDate <= '" + vo.getBeEndDate() + "'";
						}
						List<LabApparaUse> list = labApparaUseDAO.find(hql);
						if (list.size() <= 0) {
							LabApparaUse po = new LabApparaUse();
							LabAppara app =(LabAppara) labApparaUseDAO.findById(LabAppara.class, vo.getAppId());
							BeanUtils.copyProperties(vo, po, new String[] { "" });
							if(app!=null)
							po.setLabAppara(app);
							po.setStauts(Constant.APP_BESPEAK_ST);
							po.setSamBeatchId(samBeatchId);
							if (!StrUtils.isBlankOrNull(vo.getUseUserId())) {
								LabUser user = labUserDAO.getLabUser(vo.getUseUserId());
								po.setUseUser(user.getName());
							}
							labApparaUseDAO.addLabApparaUse(po);
						}else{
							key=false;
						}
					}
				} catch (Exception e) {
					Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
					key=false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return key;
	}
	@Override
	public boolean addLabApparaBeUse(LabApparaUseVo vo) throws GlobalException {
		try {
			if (!StrUtils.isBlankOrNull(vo.getAppId())) {
				String hql = "FROM LabApparaUse WHERE 1=1 AND labAppara.id = '" + vo.getAppId() + "'";
				//预约判断
				if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
					hql += " AND beEndDate >= '" + vo.getBeStartDate() + "'";
				}
				if(!StrUtils.isBlankOrNull(vo.getBeStartDate())){
					hql += " AND beStartDate <= '" + vo.getBeEndDate() + "'";
				}
				hql +=" AND labAppara.status = '0'";
				List<LabApparaUse> list = labApparaUseDAO.find(hql);
				if (list.size() <= 0) {
					LabApparaUse po = new LabApparaUse();
					LabAppara app = new LabAppara();
					app.setId(vo.getAppId());
					app.setName(vo.getAppName());
					app.setNo(vo.getAppNo());
					BeanUtils.copyProperties(vo, po, new String[] { "" });
					po.setLabAppara(app);
					po.setStauts(Constant.APP_BESPEAK_ST);
					if (!StrUtils.isBlankOrNull(vo.getUseUserId())) {
						LabUser user = labUserDAO.getLabUser(vo.getUseUserId());
						po.setUseUser(user.getName());
					}
					labApparaUseDAO.addLabApparaUse(po);
				}else{
					return false;
				}
			}
		} catch (Exception e) {
			Log4J.error("LabApparaUseServiceImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}
	@Override
	public List<LabApparaUseVo> getLabApparaUseList(String busId) throws GlobalException {
		// TODO Auto-generated method stub
		List<LabApparaUseVo> voList = new ArrayList<LabApparaUseVo>();
		String hql = "FROM LabApparaUse WHERE isDel='"+Constants_Source.N+"' AND samBeatchId='"+busId+"'";
		List<LabApparaUse> poList = labApparaUseDAO.find(hql);
		if(poList!=null&&poList.size()>0){
			for (LabApparaUse po : poList) {
				LabApparaUseVo useVo = new LabApparaUseVo();
				useVo.toVo(po);
				if(po.getLabAppara()!=null){
					useVo.setAppName(po.getLabAppara().getName());
					useVo.setAppId(po.getLabAppara().getId());
				}
				voList.add(useVo);
			}
		}
		return voList;
	}

}
