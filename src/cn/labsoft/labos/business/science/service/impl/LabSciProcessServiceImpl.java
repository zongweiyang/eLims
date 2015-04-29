package cn.labsoft.labos.business.science.service.impl;

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
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.common.upload.sevice.impl.LabUploadServiceImpl;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.customer.dao.ILabCustomerDAO;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.business.sample.dao.ILabSampCustomerDAO;
import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.science.dao.ILabSciProcessDAO;
import cn.labsoft.labos.business.science.entity.LabSciProcess;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciProcessService;
import cn.labsoft.labos.business.science.vo.LabSciProcessVo;

@Service("labSciProcessService")
public class LabSciProcessServiceImpl extends BaseService implements ILabSciProcessService {
	public  ExecutorService poolSer = Executors.newSingleThreadExecutor();
	private ILabSciProcessDAO labSciProcessDAO;
	private ILabSampRegisterDAO labSampRegisterDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabSampCustomerDAO labSampCustomerDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabNumberDAO labNumberDAO;
	
	
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabSampCustomerDAO(ILabSampCustomerDAO labSampCustomerDAO) {
		this.labSampCustomerDAO = labSampCustomerDAO;
	}
	@Resource
	public void setLabSciProcessDAO(ILabSciProcessDAO labSciProcessDAO) {
		this.labSciProcessDAO = labSciProcessDAO;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}

	@Resource
	public void setLabSampRegisterDAO(ILabSampRegisterDAO labSampRegisterDAO) {
		this.labSampRegisterDAO = labSampRegisterDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Override
	public LabSciProcessVo addLabSciProcess(LabSciProcessVo labSciProcessVo) throws GlobalException {
		
		LabSciProcess labSciProcess=new LabSciProcess();
		try{
			labSciProcess=this.vo2Po(labSciProcessVo, labSciProcess);
			ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Business.CODE_GC,new String[]{""},"1");
			try {
				labSciProcess.setNo(poolSer.submit(threadNumber).get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
			labSciProcess.setCreateUserId(getSessionContainer().getUserId());
			labSciProcessDAO.addLabSciProcess(labSciProcess);
			labSciProcessVo.setId(labSciProcess.getId());
			
			List<LabUpload> fileList=labUploadDAO.getLabUploadList(labSciProcessVo.getUuid(), "lab-sciProcess");
			if (fileList.size()>0) {
				for(LabUpload labUpload:fileList)
				{
					labUpload.setBusId(labSciProcessVo.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			if(null!=labSciProcessVo.getType()&&labSciProcessVo.getType().equals("科研实验")){
				LabSampRegister labSampRegister=new LabSampRegister();
				
				threadNumber = new ThreadNumber(null,labNumberDAO,
						Constants_Business.CODE_RW, new String[] {},Constants_Business.CODE_USE_RUN);
				try {
					labSampRegister.setNo(poolSer.submit(threadNumber).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
				ThreadNumber threadNumbers = new ThreadNumber(null,labNumberDAO,
						Constants_Business.CODE_LY, new String[] {},Constants_Business.CODE_USE_RUN);
				try {
					labSampRegister.setSampNo(poolSer.submit(threadNumbers).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
				}
				LabSampCustomer labSampCustomer=new LabSampCustomer();
				labSampCustomer.setLabCustomerName(labSciProcessVo.getName());
				labSampCustomer.setUser(labSciProcessVo.getWriteUser());
				labSampRegister.setCreateDate(labSciProcessVo.getStartTime());
				labSampRegister.setFinishDate(labSciProcessVo.getEndTime());
				labSampCustomerDAO.addLabSampCustomer(labSampCustomer);
				labSampRegister.setLabSampCustomer(labSampCustomer);
				labSampRegister.setTaskType("科研实验");
				labSampRegister.setReportType("检测");
				labSampRegister.setReportMake("1");
				labSampRegister.setReportPost("自取");
				labSampRegister.setReportNum("1");
				labSampRegister.setOtherId(labSciProcessVo.getId());
				labSampRegister.setSampNum(labSciProcessVo.getSampRegisterNum());
				labSampRegister.setOtherType(Constants_Business.WF_CODE_QUA_GC);
				labSampRegisterDAO.addLabSampRegister(labSampRegister);
				// 流程实例
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns4Other(labSampRegister.getId(),Constants_Business.WF_CODE_BUS_RW);
				if(ins==null){
					throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
				}else{
					labSampRegister.setProcessIns(ins);
					labSampRegisterDAO.updateLabSampRegister(labSampRegister);
				}
			}
		}catch(Exception e){
			Log4J.error("LabSciProcessServiceImpl addLabSciProcess  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labSciProcessVo;
	}

	@Override
	public boolean deleteLabSciProcess(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				for(String id : ids ){
					LabSciProcess labSciProcess = labSciProcessDAO.getLabSciProcess(id);
					labSciProcess.setIsDel(Constants_Business.Y);
					key=labSciProcessDAO.updateLabSciProcess(labSciProcess);
				}
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabSciProcessServiceImpl deleteLabSciProcess  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabSciProcessVo getLabSciProcess(String id) throws GlobalException {
		LabSciProcessVo labSciProcessVo=new LabSciProcessVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabSciProcess labSciProcess=labSciProcessDAO.getLabSciProcess(id);
				labSciProcessVo=this.po2Vo(labSciProcess, labSciProcessVo);
				if(null!=labSciProcessVo.getType()&&labSciProcessVo.getType().equals("科研实验")){
					String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"' AND otherType='"+Constants_Business.WF_CODE_QUA_GC+"' AND otherId='"+labSciProcessVo.getId()+"'";
					LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
					if(labSampRegister!=null){
						labSciProcessVo.setSampRegisterNo(labSampRegister.getNo());
						LabSampCustomer customer=labSampRegister.getLabSampCustomer();
						labSciProcessVo.setName(customer.getLabCustomerName());
						labSciProcessVo.setWriteUser(customer.getUser());
						labSciProcessVo.setSampRegisterNum(labSampRegister.getSampNum());
						labSciProcessVo.setSampRegisterId(labSampRegister.getId());
						labSciProcessVo.setSampRegisterSampNo(labSampRegister.getSampNo());
					}
				}
			}catch(Exception e){
				Log4J.error("LabSciProcessServiceImpl getLabSciProcess  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labSciProcessVo;
	}

	@Override
	public List<LabSciProcessVo> getLabSciProcessList(LabSciProcessVo labSciProcessVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabSciProcessVoListByWhere(wereHql);
	}

	@Override
	public PageResult getLabSciProcessPR(LabSciProcessVo labSciProcessVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabSciProcess WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getStartTime())&&!StrUtils.isBlankOrNull(labSciProcessVo.getEndTime())){
			hql += " AND createTime BETWEEN '"+labSciProcessVo.getStartTime()+"' AND '"+labSciProcessVo.getEndTime()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getNo())){
			hql += " AND no LIKE '%"+labSciProcessVo.getNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getLabSciScienceId())){
			hql += " AND labSciScience.id = '"+labSciProcessVo.getLabSciScienceId()+"'";
		}
		pageResult=labSciProcessDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSciProcessVo> labSciProcessVoList=new ArrayList<LabSciProcessVo>();
			List<LabSciProcess> listLabSciProcess=new ArrayList<LabSciProcess>();
			listLabSciProcess=pageResult.getResultList();
			for(LabSciProcess labSciProcess:listLabSciProcess){
				LabSciProcessVo vo=new LabSciProcessVo();
				vo=this.po2Vo(labSciProcess, vo);
				labSciProcessVoList.add(vo);
			}
			pageResult.setResultList(labSciProcessVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabSciProcess(LabSciProcessVo labSciProcessVo) throws GlobalException {
		boolean key=true;
		try{
			if(!StrUtils.isBlankOrNull(labSciProcessVo.getId())){
				LabSciProcess labSciProcess=labSciProcessDAO.getLabSciProcess(labSciProcessVo.getId());
				if(labSciProcess!=null){
					labSciProcess=this.vo2Po(labSciProcessVo, labSciProcess);
					if(StrUtils.isBlankOrNull(labSciProcessVo.getId())){
						ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Business.CODE_GC,new String[]{""},"1");
						try {
							labSciProcess.setNo(poolSer.submit(threadNumber).get().toString());
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
					labSciProcessDAO.updateLabSciProcess(labSciProcess);
					if(null!=labSciProcessVo.getType()&&labSciProcessVo.getType().equals("科研实验")){
						String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"' AND otherType='"+Constants_Business.WF_CODE_QUA_GC+"' AND otherId='"+labSciProcessVo.getId()+"'";
						LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
						if(labSampRegister==null){
							labSampRegister = new LabSampRegister();
							ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
									Constants_Business.CODE_RW, new String[] {},Constants_Business.CODE_USE_RUN);
							try {
								labSampRegister.setNo(poolSer.submit(threadNumber).get().toString());
							} catch (Exception e) {
								Log4J.error(e.getMessage());
								throw new GlobalException("" + e.getMessage());
							}
							ThreadNumber threadNumbers = new ThreadNumber(null,labNumberDAO,
									Constants_Business.CODE_LY, new String[] {},Constants_Business.CODE_USE_INIT);
							try {
								labSampRegister.setSampNo(poolSer.submit(threadNumbers).get().toString());
							} catch (Exception e) {
								Log4J.error(e.getMessage());
								throw new GlobalException("" + e.getMessage());
							}
							LabSampCustomer labSampCustomer=new LabSampCustomer();
							labSampCustomer.setLabCustomerName(labSciProcessVo.getName());
							labSampCustomer.setUser(labSciProcessVo.getWriteUser());
							labSampRegister.setCreateDate(labSciProcessVo.getStartTime());
							labSampRegister.setFinishDate(labSciProcessVo.getEndTime());
							labSampRegisterDAO.addLabSampRegister(labSampRegister);
							labSampRegister.setLabSampCustomer(labSampCustomer);
							labSampRegister.setTaskType("科研实验");
							labSampRegister.setReportType("检测");
							labSampRegister.setReportMake("1");
							labSampRegister.setReportPost("自取");
							labSampRegister.setReportNum("1");
							labSampRegister.setOtherId(labSciProcessVo.getId());
							labSampRegister.setSampNum(labSciProcessVo.getSampRegisterNum());
							labSampRegister.setOtherType(Constants_Business.WF_CODE_QUA_GC);
							labSampRegisterDAO.addLabSampRegister(labSampRegister);
							// 流程实例
							LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns4Other(labSampRegister.getId(),Constants_Business.WF_CODE_BUS_RW);
							if(ins==null){
								throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
							}else{
								labSampRegister.setProcessIns(ins);
								labSampRegisterDAO.updateLabSampRegister(labSampRegister);
							}
						}else{
							String hqlx="FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busId='"+labSampRegister.getId()+"'";
							List list=labWfProcessInsDAO.find(hqlx);
							if(list.size()<=1){
								labSampRegister.setSampNum(labSciProcessVo.getSampRegisterNum());
								if(StrUtils.isBlankOrNull(labSciProcessVo.getSampRegisterNo())){
									ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
											Constants_Business.CODE_RW, new String[] {},Constants_Business.CODE_USE_RUN);
									try {
										labSampRegister.setNo(poolSer.submit(threadNumber).get().toString());
									} catch (Exception e) {
										Log4J.error(e.getMessage());
									}
								}
								labSampRegister.setCreateDate(labSciProcessVo.getStartTime());
								labSampRegister.setFinishDate(labSciProcessVo.getEndTime());
								labSampRegisterDAO.updateLabSampRegister(labSampRegister);
								LabSampCustomer labSampCustomer=labSampRegister.getLabSampCustomer();
								labSampCustomer.setLabCustomerName(labSciProcessVo.getName());
								labSampCustomer.setUser(labSciProcessVo.getWriteUser());
								labSampRegisterDAO.updateLabSampRegister(labSampRegister);
							}
						}
					}else{
						String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"' AND otherType='"+Constants_Business.WF_CODE_QUA_GC+"' AND otherId='"+labSciProcessVo.getId()+"'";
						LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
						if(labSampRegister!=null){
							labSampRegister.setIsDel(Constants_Business.Y);
							labSampRegisterDAO.updateLabSampRegister(labSampRegister);
							labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labSampRegister.getId());
						}
					}
				}
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabSciProcessServiceImpl updateLabSciProcess  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabSciProcess4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabSciProcess labSciProcess=labSciProcessDAO.getLabSciProcess(id);
					labSciProcess.setIsDel(Constants_Business.Y);
					labSciProcessDAO.updateLabSciProcess(labSciProcess);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabSciProcessServiceImpl updateLabSciProcess4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	public List<LabSciProcessVo> getLabSciProcessVoListByWhere(String wereHql) throws GlobalException{
		List<LabSciProcessVo> labSciProcessVoList=new ArrayList<LabSciProcessVo>();
		String hql="FROM LabSciProcess WHERE isDel='"+Constants_Business.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabSciProcess> labSciProcessList=labSciProcessDAO.find(hql);
		if(labSciProcessList!=null&&labSciProcessList.size()>0){
			for(LabSciProcess labSciProcess:labSciProcessList){
				LabSciProcessVo labSciProcessVo=new LabSciProcessVo();
				labSciProcessVo=this.po2Vo(labSciProcess, labSciProcessVo);
				labSciProcessVoList.add(labSciProcessVo);
			}
		}
		return labSciProcessVoList;
	}
	public LabSciProcess vo2Po(LabSciProcessVo labSciProcessVo,LabSciProcess labSciProcess){
		BeanUtils.copyProperties(labSciProcessVo, labSciProcess,new String[]{"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getLabSciScienceId())){
			LabSciScience labSciScience = (LabSciScience)labSciProcessDAO.findById(LabSciScience.class, labSciProcessVo.getLabSciScienceId());
			labSciProcess.setLabSciScience(labSciScience);
		}
		return labSciProcess;
	}
	public LabSciProcessVo po2Vo(LabSciProcess labSciProcess,LabSciProcessVo labSciProcessVo){
		BeanUtils.copyProperties(labSciProcess, labSciProcessVo);
		if(null !=  labSciProcess.getLabSciScience()){
			labSciProcessVo.setLabSciScienceId(labSciProcess.getLabSciScience().getId());
			labSciProcessVo.setLabSciScienceName(labSciProcess.getLabSciScience().getName());
		}
		return labSciProcessVo;
	}
}
