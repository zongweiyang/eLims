package cn.labsoft.labos.source.charge.service.impl;

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
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.charge.dao.ILabChargeDAO;
import cn.labsoft.labos.source.charge.dao.ILabChargeDetailDAO;
import cn.labsoft.labos.source.charge.entity.LabCharge;
import cn.labsoft.labos.source.charge.entity.LabChargeDetail;
import cn.labsoft.labos.source.charge.service.ILabChargeService;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labChargeService")
public class LabChargeServiceImpl extends BaseService implements
		ILabChargeService {
	public   ExecutorService poolSer = Executors. newSingleThreadExecutor();
	private ILabChargeDAO labChargeDAO;
	private ILabNumberDAO labNumberDAO;
	private ILabChargeDetailDAO labChargeDetailDAO;
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Resource
	public void setLabChargeDetailDAO(ILabChargeDetailDAO labChargeDetailDAO) {
		this.labChargeDetailDAO = labChargeDetailDAO;
	}
	@Resource
	public void setLabChargeDAO(ILabChargeDAO labChargeDAO) {
		this.labChargeDAO = labChargeDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabCharge(LabChargeVo labChargeVo)
			throws GlobalException {
		LabCharge labCharge = new LabCharge();
		BeanUtils.copyProperties(labChargeVo, labCharge, new String[]{""});
		labCharge.setIsDel(Constants_Source.N);
		Double weiMoney = 0.0;
		if(labChargeVo.getPayMoney() != 0.0){
			weiMoney = labChargeVo.getPayMoney() - labChargeVo.getYiMoney();
			labCharge.setWeiMoney(weiMoney);
			if(weiMoney == 0.0 || weiMoney == 0){
				labCharge.setIsEnd(Constants_Source.Y);
			}else{
				labCharge.setIsEnd(Constants_Source.N);
			}
		}else{
			labCharge.setIsEnd(Constants_Source.N);
		}
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_SF,new String[]{""},Constants_Source.CODE_USE_RUN);
		try {
			labCharge.setCode(poolSer.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			Log4J.error("labChargeService"+e.getStackTrace());
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			Log4J.error("labChargeService"+e.getStackTrace());
			throw new GlobalException("" + e.getMessage());
		}
		labChargeDAO.addLabCharge(labCharge);
		return true;
	}
	
	//收费单号
	@SuppressWarnings("unchecked")
	public String chargeCode() throws GlobalException{
			String typeSign = "FYGL";
			String yyyy=DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay();
			String HQL="SELECT MAX(code) FROM LabCharge WHERE isDel='"+Constants_Source.N+"' AND code like '"+typeSign+yyyy+"%'";
			int code=0;
			List<Object> objList=labChargeDAO.find(HQL);
			if(null!=objList&&objList.size()>0){
				Object obj=objList.get(0);
				if(null!=obj){
					try {
						code=Integer.valueOf(String.valueOf(obj).substring(12,15));
					} catch (Exception e) {
						code=0;
					}
				}
			}
			String codeStr="000";
			codeStr=String.valueOf(code+1);
			if(null==codeStr||"".equals(codeStr))
				codeStr="000";
			if(codeStr.length()==1)
				codeStr="00"+codeStr;
			else if(codeStr.length()==2)
				codeStr="0"+codeStr;
			String result="";
			
			result=typeSign+yyyy+codeStr;
			return result;
	}

	@Override
	public boolean update2DelLabCharge(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabCharge labCharge = (LabCharge)labChargeDAO.findById(LabCharge.class, id);
				labCharge.setIsDel(Constants_Source.Y);
				labChargeDAO.updateLabCharge(labCharge);
			}
		}
		return true;
	}

	@Override
	public LabChargeVo getLabCharge(String id) throws GlobalException {
		LabCharge labCharge = (LabCharge)  labChargeDAO.findById(LabCharge.class, id);
		LabChargeVo labChargeVo = new LabChargeVo();
		BeanUtils.copyProperties(labCharge, labChargeVo,new String[]{""});
		String hql=" FROM LabChargeDetail WHERE code='"+labCharge.getCode()+"'";
		List<LabChargeDetail> detailList=labChargeDetailDAO.getLabChargeDetailList(hql);
		String payInfo="";
		if(detailList!=null){
			for (LabChargeDetail detail : detailList) {
				if(detail.getPrice()>0){
					payInfo+=detail.getPayName()+",";
				}
			}
		}
		if(payInfo.endsWith(",")){
			payInfo=payInfo.substring(0, payInfo.length()-1);
		}
		labChargeVo.setPayInfo(payInfo);
		labChargeVo.setId(labCharge.getId());
		return labChargeVo;
	}

	@Override
	public List<LabChargeVo> getLabChargeList(LabChargeVo labChargeVo)
			throws GlobalException {
		String hql = " FROM LabCharge WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labChargeVo.getCode())){
			hql += " AND code LIKE '%"+labChargeVo.getCode()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getPaymentUnit())){
			hql += " AND paymentUnit LIKE '%" +labChargeVo.getPaymentUnit()+ "%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getPayName())){
			hql += " AND payName LIKE '%"+labChargeVo.getPayName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getIsEnd())){
			hql += " AND isEnd = '"+labChargeVo.getIsEnd()+"'";
		}
		List<LabCharge> labChargeList = labChargeDAO.getLabChargeList(hql);
		List<LabChargeVo> labChargeVoList = new ArrayList<LabChargeVo>();
		if( null != labChargeList && labChargeList.size() > 0 ){
			for(LabCharge labCharge : labChargeList ){
				LabChargeVo chargeVo = new LabChargeVo();
				BeanUtils.copyProperties(labCharge, chargeVo, new String [] {""});
				labChargeVoList.add(chargeVo);
			}
		}
		return labChargeVoList;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabChargePR(LabChargeVo labChargeVo,
			PageResult pageResult) throws GlobalException {
		String hql = " FROM LabCharge WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labChargeVo.getCode())){
			hql += " AND code LIKE '%"+labChargeVo.getCode().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getPaymentUnit())){
			hql += " AND paymentUnit LIKE '%" +labChargeVo.getPaymentUnit().trim()+ "%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getPayName())){
			hql += " AND payName LIKE '%"+labChargeVo.getPayName().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labChargeVo.getIsEnd())){
			hql += " AND isEnd = '"+labChargeVo.getIsEnd().trim()+"'";
		}
		pageResult = labChargeDAO.getLabChargePR(hql, pageResult);
		List<LabCharge> labChargeList = pageResult.getResultList();
		List<LabChargeVo> resultList = new ArrayList<LabChargeVo>();
		if( null != labChargeList && labChargeList.size() > 0 ){
			for(LabCharge labCharge : labChargeList){
				LabChargeVo chargeVo = new LabChargeVo();
				BeanUtils.copyProperties(labCharge, chargeVo, new String[]{""});
				resultList.add(chargeVo);
			}
			pageResult.setResultList(resultList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabCharge(LabChargeVo labChargeVo)
			throws GlobalException {
		LabCharge labCharge = (LabCharge) labChargeDAO.findById(LabCharge.class, labChargeVo.getId());
		if(StrUtils.isBlankOrNull(labChargeVo.getCode())){
			labCharge.setCode(labChargeVo.getCode());
		}
		labCharge.setPayName(labChargeVo.getPayName());
		labCharge.setPaymentUnit(labChargeVo.getPaymentUnit());
		labCharge.setInvoiceNo(labChargeVo.getInvoiceNo());
		labCharge.setFastName(labChargeVo.getFastName());
		labCharge.setSendTime(labChargeVo.getSendTime());
		labCharge.setPayType(labChargeVo.getPayType());
		labCharge.setPayInfo(labChargeVo.getPayInfo());
		labCharge.setCollectionUnit(labChargeVo.getCollectionUnit());
		labCharge.setCollectionName(labChargeVo.getCollectionName());
		labCharge.setCollectionTime(labChargeVo.getCollectionTime());
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getDiscount()))){
			labCharge.setDiscount(labChargeVo.getDiscount());
		}else{
			labCharge.setDiscount(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getPreferential()))){
			labCharge.setPreferential(labChargeVo.getPreferential());
		}else{
			labCharge.setPreferential(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getTax()))){
			labCharge.setTax(Double.valueOf(labChargeVo.getTax()));
		}else{
			labCharge.setTax(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getTaxMoney()))){
			labCharge.setTaxMoney(Double.valueOf(labChargeVo.getTaxMoney()));
		}else{
			labCharge.setTaxMoney(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getTotalMoney()))){
			labCharge.setTotalMoney(Double.valueOf(labChargeVo.getTotalMoney()));
		}else{
			labCharge.setTotalMoney(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getYiMoney()))){
			labCharge.setYiMoney(Double.valueOf(labChargeVo.getYiMoney()));
		}else{
			labCharge.setYiMoney(0.0);
		}
		if(!StrUtils.isBlankOrNull(String.valueOf(labChargeVo.getPayMoney()))){
			Double weiMoney = 0.0;
			labCharge.setPayMoney(Double.valueOf(labChargeVo.getPayMoney()));
			weiMoney = Double.valueOf(labChargeVo.getPayMoney()) - Double.valueOf(labChargeVo.getYiMoney());
			labCharge.setWeiMoney(weiMoney);
		}
		Double weiMoney = 0.0;
		if(labChargeVo.getPayMoney() != 0.0){
			weiMoney = Double.valueOf(labChargeVo.getPayMoney()) - Double.valueOf(labChargeVo.getYiMoney());
			if(weiMoney == 0.0 || weiMoney == 0){
				labCharge.setIsEnd(Constants_Source.Y);
			}else{
				labCharge.setIsEnd(Constants_Source.N);
			}
		}else{
			labCharge.setIsEnd(Constants_Source.N);
		}
		labChargeDAO.updateLabCharge(labCharge);
		return true;
	}
	
	@Override
	public boolean saveLabCharge(String paymentUnit, String payInfo,
			String collectionUnit, String collectionName,String busId,String busType)
			throws GlobalException {
		LabCharge labCharge = new LabCharge();
		labCharge.setPaymentUnit(paymentUnit);
		labCharge.setPayInfo(payInfo);
		labCharge.setCollectionUnit(collectionUnit);
		labCharge.setCollectionName(collectionName);
		labCharge.setBusId(busId);
		labCharge.setBusType(busType);
		labCharge.setIsDel(Constants_Source.N);
		labCharge.setIsEnd(Constants_Source.N);
		labCharge.setCode(chargeCode());
		labChargeDAO.addLabCharge(labCharge);
		return true;
	}

	@Override
	public List<LabChargeVo> getLabChargeByBusIdAndBusType(String busId,
			String busType) throws GlobalException {
		String hql = " FROM LabCharge WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(busId)){              
			hql += " AND busId = '"+busId+"'";
		}
		if(!StrUtils.isBlankOrNull(busType)){
			hql += " AND busType = '"+busType+"'";
		}
		List<LabCharge> labChargeList = labChargeDAO.getLabChargeList(hql);
		List<LabChargeVo> labChargeVoList = new ArrayList<LabChargeVo>();
		if( null != labChargeList && labChargeList.size() > 0 ){
			for(LabCharge labCharge : labChargeList ){
				LabChargeVo chargeVo = new LabChargeVo();
				BeanUtils.copyProperties(labCharge, chargeVo, new String [] {""});
				labChargeVoList.add(chargeVo);
			}
		}
		return labChargeVoList;
	}
	
	@Override
	public boolean updateLabCharge(String paymentUnit, String payInfo,
			String collectionUnit, String collectionName, String busId,
			String busType) throws GlobalException {
		List<LabChargeVo> labChargeVoList = getLabChargeByBusIdAndBusType(busId, busType);
		if(null != labChargeVoList && labChargeVoList.size() > 0 ){
			for(LabChargeVo labChargeVo : labChargeVoList){
				LabCharge labCharge = labChargeDAO.getLabCharge(labChargeVo.getId());
				labCharge.setPaymentUnit(paymentUnit);
				labCharge.setPayInfo(payInfo);
				labCharge.setCollectionUnit(collectionUnit);
				labCharge.setCollectionName(collectionName);
				labCharge.setBusId(busId);
				labCharge.setBusType(busType);
				labChargeDAO.updateLabCharge(labCharge);
			}
		}
		return true;
	}
	@Override
	public boolean updateLabCharge4Rport(LabChargeVo labChargeVo)
			throws GlobalException {
		try {
			LabCharge charge=labChargeDAO.getLabCharge(labChargeVo.getId());
			charge.setReportPath(labChargeVo.getReportPath());
			charge.setReportTempId(labChargeVo.getReportTempId());
			labChargeDAO.updateLabCharge(charge);
		} catch (RuntimeException e) {
			Log4J.error("labChargeService..."+e.getStackTrace());
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}
}
