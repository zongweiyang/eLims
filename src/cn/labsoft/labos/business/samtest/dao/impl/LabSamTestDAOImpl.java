package cn.labsoft.labos.business.samtest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.sample.entity.LabSampItems;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.samtest.entity.LabSamTest;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Repository(value="labSamTestDAO")
public class LabSamTestDAOImpl extends BaseDAO implements ILabSamTestDAO {
	
	@Override
		public LabSamTest addLabSamTest(LabSamTest labSamTest) throws GlobalException {
		try {
			labSamTest.setIsDel(Constants_Business.N);
			labSamTest.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSamTest);
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamTest;
	}

	@Override
	public boolean deleteLabSamTest(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSamTest(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSamTest getLabSamTest(String id) throws GlobalException {
		try {
			LabSamTest labSamTest = (LabSamTest) super.findById(LabSamTest.class, id);
			return labSamTest;
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSamTest(LabSamTest labSamTest) throws GlobalException {
		try {
			super.update(labSamTest);
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSamTest(LabSamTest labSamTest) throws GlobalException {
		try {
			super.delete(labSamTest);
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamTest> getLabSamTestList(String hql) throws GlobalException {
	try {
		List<LabSamTest> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSamTestPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSamTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	@Override
	public boolean updateLabSamTest4LabSam(String busId) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=false;
		String hql="";
		if(!StrUtils.isBlankOrNull(busId)){
			LabSampRegister labSampRegister=(LabSampRegister) super.findById(LabSampRegister.class, busId);
			hql="FROM LabSampItems WHERE isDel='"+Constants_Business.N+"' AND busId='"+busId+"'";
			List<LabSampItems> listLabSampItems=super.find(hql);
			if(listLabSampItems!=null&&listLabSampItems.size()>0){
				for(LabSampItems labSampItems:listLabSampItems){
					if(labSampItems!=null){
						LabSamTest labSamTest=new LabSamTest();
						labSamTest.setSampCode(labSampItems.getSamCode());
						labSamTest.setLabSamId(labSampItems.getLabSamId());
						labSamTest.setLabSamName(labSampItems.getLabSamName());
						labSamTest.setLabSamTypeId(labSampItems.getLabSamTypeId());
						labSamTest.setLabSamTypeName(labSampItems.getLabSamTypeName());
						labSamTest.setItemId(labSampItems.getItemId());
						labSamTest.setItemName(labSampItems.getItemName());
						labSamTest.setStandardId(labSampItems.getStandardId());
						labSamTest.setStandardName(labSampItems.getStandardName());
						labSamTest.setMethodId(labSampItems.getMethodId());
						labSamTest.setMethodName(labSampItems.getMethodName());
						labSamTest.setTaskId(busId);
						labSamTest.setTaskNo(labSampRegister.getNo());
						labSamTest.setTaskType(labSampRegister.getTaskType());
						labSamTest.setFinishDate(labSampRegister.getFinishDate());
						this.addLabSamTest(labSamTest);
					}
				}
				key=true;
			}
		}
		return key;
	}
}
