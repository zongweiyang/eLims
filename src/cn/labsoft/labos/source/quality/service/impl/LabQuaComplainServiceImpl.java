package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.quality.dao.ILabQuaComplainDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaComplain;
import cn.labsoft.labos.source.quality.service.ILabQuaComplainService;
import cn.labsoft.labos.source.quality.vo.LabQuaComplainVo;
import cn.labsoft.labos.utils.StrUtils;




@Service("labQuaComplainService")
public class LabQuaComplainServiceImpl extends BaseService implements ILabQuaComplainService{
	
	public  ExecutorService poolSer = Executors.newSingleThreadExecutor();
	private ILabQuaComplainDAO labQuaComplainDAO;
	private ILabNumberDAO labNumberDAO;
	
	@Resource
	public void setLabQuaComplainDAO(ILabQuaComplainDAO labQuaComplainDAO) {
		this.labQuaComplainDAO = labQuaComplainDAO;
	}
	
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}


	@Override
	public boolean update2DelLabQuaComplain(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaComplain labQuaComplain=labQuaComplainDAO.getLabQuaComplain(id);
				labQuaComplain.setIsDel(Constants_Source.Y);
				labQuaComplainDAO.updateLabQuaComplain(labQuaComplain);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaComplainVo> getLabQuaComplainVoList(
			LabQuaComplainVo labQuaComplainVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaComplain WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaComplainVo.getNameSearch())){
			hql += " AND name like '%"+labQuaComplainVo.getNameSearch()+"%' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaComplainVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaComplainVo.getAccStatus()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaComplainVo.getUnitSearch())){
			hql += " AND labCustomer.name like '%"+labQuaComplainVo.getUnitSearch()+"%'  ";
		} 
		List<LabQuaComplain> labQuaComplainList=labQuaComplainDAO.getLabQuaComplainByHQL(hql);
		List<LabQuaComplainVo> labQuaComplainVoList=new ArrayList<LabQuaComplainVo>();
		if (null!=labQuaComplainList && labQuaComplainList.size()>0) {
			for (LabQuaComplain labQuaComplain : labQuaComplainList) {
				LabQuaComplainVo labQuaComplainVoOne=new LabQuaComplainVo();
				BeanUtils.copyProperties(labQuaComplain, labQuaComplainVoOne, new String[]{"isDel"});
				if(null != labQuaComplain.getLabCustomer()){
					labQuaComplainVoOne.setUnitId(labQuaComplain.getLabCustomer().getId());
					labQuaComplainVoOne.setUnit(labQuaComplain.getLabCustomer().getName());
				}
				labQuaComplainVoList.add(labQuaComplainVoOne);
			} 
		}
		return labQuaComplainVoList;
	}

	@Override
	public PageResult getLabQuaComplainVoPR(LabQuaComplainVo labQuaComplainVo, PageResult pageResult)
			throws GlobalException {
			String hql = "FROM LabQuaComplain WHERE isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaComplainVo.getNameSearch())){
				hql += " AND name like '%"+labQuaComplainVo.getNameSearch()+"%' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaComplainVo.getAccStatus())){
				hql += " AND accStatus = '"+labQuaComplainVo.getAccStatus()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaComplainVo.getNo())){
				hql += " AND no like '%"+labQuaComplainVo.getNo()+"%' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaComplainVo.getUnitSearch())){
				hql += " AND labCustomer.name like '%"+labQuaComplainVo.getUnitSearch()+"%'  ";
			}
			pageResult=labQuaComplainDAO.getLabQuaComplainPR(hql, pageResult);
			List<LabQuaComplain> labQuaComplainList=pageResult.getResultList();
			List<LabQuaComplainVo> labQuaComplainVoList=new ArrayList<LabQuaComplainVo>();
			if (null!=labQuaComplainList && labQuaComplainList.size()>0) {
				for (LabQuaComplain labQuaComplain : labQuaComplainList) {
					LabQuaComplainVo labQuaComplainVoOne=new LabQuaComplainVo();
					BeanUtils.copyProperties(labQuaComplain, labQuaComplainVoOne, new String[]{"isDel"});
					if(null != labQuaComplain.getLabCustomer()){
						labQuaComplainVoOne.setUnitId(labQuaComplain.getLabCustomer().getId());
						labQuaComplainVoOne.setUnit(labQuaComplain.getLabCustomer().getName());
					}
					labQuaComplainVoList.add(labQuaComplainVoOne);
				} 
				pageResult.setResultList(labQuaComplainVoList);
			}
			
			return pageResult;
		
	}
	
	@Override
	public LabQuaComplainVo getLabQuaComplain(String id)
			throws GlobalException {
		LabQuaComplain labQuaComplain=labQuaComplainDAO.getLabQuaComplain(id);
		LabQuaComplainVo labQuaComplainVo=new LabQuaComplainVo();
		BeanUtils.copyProperties(labQuaComplain, labQuaComplainVo, new String[]{"isDel"});
		if(null != labQuaComplain.getLabCustomer()){
			labQuaComplainVo.setUnitId(labQuaComplain.getLabCustomer().getId());
			labQuaComplainVo.setUnit(labQuaComplain.getLabCustomer().getName());
		}
		return labQuaComplainVo;
		
	}

	@Override
	public LabQuaComplainVo addLabQuaComplain(LabQuaComplainVo labQuaComplainVo)
			throws GlobalException {
		LabQuaComplain labQuaComplain=new LabQuaComplain();
		BeanUtils.copyProperties(labQuaComplainVo, labQuaComplain, new String[] {"isDel"});
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_YWTS,new String[]{""},Constants_Source.CODE_USE_RUN);
		try {
			labQuaComplain.setNo(poolSer.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(!StrUtils.isBlankOrNull(labQuaComplainVo.getUnitId())){
			LabCustomer labCustomer = (LabCustomer)labQuaComplainDAO.findById(LabCustomer.class, labQuaComplainVo.getUnitId());
			labQuaComplain.setLabCustomer(labCustomer);
		}
		labQuaComplain.setAccStatus("0");
		labQuaComplainDAO.addLabQuaComplain(labQuaComplain);
		labQuaComplainVo.setId(labQuaComplain.getId());
		return labQuaComplainVo;
	}

	@Override
	public boolean updateLabQuaComplain(LabQuaComplainVo labQuaComplainVo)
			throws GlobalException {
		LabQuaComplain labQuaComplain=labQuaComplainDAO.getLabQuaComplain(labQuaComplainVo.getId());
		BeanUtils.copyProperties(labQuaComplainVo, labQuaComplain, new String[] {"isDel","createTime","tenantId","createUserId","accStatus"});
		if(StrUtils.isBlankOrNull(labQuaComplainVo.getNo())){
			ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_YWTS,new String[]{""},Constants_Source.CODE_USE_RUN);
			try {
				labQuaComplain.setNo(poolSer.submit(threadNumber).get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		if(!StrUtils.isBlankOrNull(labQuaComplainVo.getUnitId())){
			LabCustomer labCustomer = (LabCustomer)labQuaComplainDAO.findById(LabCustomer.class, labQuaComplainVo.getUnitId());
			labQuaComplain.setLabCustomer(labCustomer);
		}
		labQuaComplainDAO.updateLabQuaComplain(labQuaComplain);
		return true;
	}
}
