package cn.labsoft.labos.source.reference.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.reference.dao.ILabRefOutDetailDAO;
import cn.labsoft.labos.source.reference.dao.ILabRefOutMainDAO;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabRefOutDetail;
import cn.labsoft.labos.source.reference.entity.LabRefOutMain;
import cn.labsoft.labos.source.reference.entity.LabReference;
import cn.labsoft.labos.source.reference.service.ILabRefOutMainService;
import cn.labsoft.labos.source.reference.vo.LabRefOutDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labRefOutMainService")
public class LabRefOutMainServiceImpl extends BaseService implements
		ILabRefOutMainService {

	private ILabRefOutMainDAO labRefOutMainDAO;
	private ILabRefOutDetailDAO labRefOutDetailDAO;
	private ILabReferenceDAO labReferenceDAO;
	@Resource
	public void setLabRefOutMainDAO(ILabRefOutMainDAO labRefOutMainDAO) {
		this.labRefOutMainDAO = labRefOutMainDAO;
	}
	@Resource
	public void setLabReferenceDAO(ILabReferenceDAO labReferenceDAO) {
		this.labReferenceDAO = labReferenceDAO;
	}
	@Resource
	public void setLabRefOutDetailDAO(ILabRefOutDetailDAO labRefOutDetailDAO) {
		this.labRefOutDetailDAO = labRefOutDetailDAO;
	}
	@Override
	public boolean addLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException {
		LabRefOutMain labRefOutMain = VoToPo(labRefOutMainVo);
		labRefOutMain.setIsDel(Constants_Source.N);
		labRefOutMainDAO.addLabRefOutMain(labRefOutMain);

		List<LabRefOutDetailVo> refOutDetailVoList = labRefOutMainVo
				.getLabRefOutDetailVoList();
		if (null != refOutDetailVoList && refOutDetailVoList.size() > 0) {
			for (LabRefOutDetailVo labRefOutDetailVo : refOutDetailVoList) {
				LabRefOutDetail labRefOutDetail = new LabRefOutDetail();
				labRefOutDetail.setIsDel(Constants_Source.N);
				labRefOutDetail.setAmount(Double.valueOf(labRefOutDetailVo
						.getAmount()));

				labRefOutDetail.setRemark(labRefOutDetailVo.getRemark());
				LabReference labReference = new LabReference();
				labReference.setId(labRefOutDetailVo.getReferenceId());
				labRefOutDetail.setReference(labReference);
				labRefOutDetail.setMain(labRefOutMain);
				labRefOutDetail.setOutDate(labRefOutMain.getOutstoreDate());
				labRefOutDetailDAO.addLabRefOutDetail(labRefOutDetail);

				// 更新标准品库存总量
				labReference = (LabReference) labRefOutDetailDAO.findById(
						LabReference.class, labReference.getId());
				labReference.setAmount(labReference.getAmount()
						- labRefOutDetail.getAmount());
				labReferenceDAO.updateLabReference(labReference);
			}
		}

		return false;
	}

	@Override
	public boolean deleteLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException {
		LabRefOutMain po = (LabRefOutMain) labRefOutMainDAO.findById(
				LabRefOutMain.class, labRefOutMainVo.getId());
		po.setIsDel(Constants_Source.N);
		labRefOutMainDAO.updateLabRefOutMain(po);
		return true;
	}

	@Override
	public LabRefOutMainVo getLabRefOutMainById(String id)
			throws GlobalException {
		if(!StrUtils.isBlankOrNull(id)){
			LabRefOutMain po = (LabRefOutMain) labRefOutMainDAO.findById(
					LabRefOutMain.class, id);
			return PoToVo(po);
		}else{
			
			return new LabRefOutMainVo();
		}
		
	}

	@Override
	public PageResult getLabRefOutMainPR(LabRefOutMainVo labRefOutMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabRefOutMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labRefOutMainVo.getOutstorer()
				&& !"".equals(labRefOutMainVo.getOutstorer())) {
			hql += " AND outstorer LIKE '%" + labRefOutMainVo.getOutstorer().trim()
					+ "%'";
		}
		if (null != labRefOutMainVo.getReceiptno()
				&& !"".equals(labRefOutMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labRefOutMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labRefOutMainVo.getStartDate()
				&& !"".equals(labRefOutMainVo.getStartDate())) {
			hql += " AND  outstoreDate >= '" + labRefOutMainVo.getStartDate().trim()
					+ "'";
		}
		if (null != labRefOutMainVo.getEndDate()
				&& !"".equals(labRefOutMainVo.getEndDate())) {
			hql += " AND  outstoreDate <= '" + labRefOutMainVo.getEndDate().trim()
					+ "'";
		}
		/*
		 * if (null !=
		 * labRefOutMainVo.getOutstoreDate()&&!"".equals(labRefOutMainVo.getOutstoreDate())) {
		 * hql += " and outstoreDate like '%" +
		 * labRefOutMainVo.getOutstoreDate() + "%'"; }
		 */
		// hql += " order by outstoreDate desc";
		pageResult = labRefOutMainDAO.getPageResult(hql, pageResult);
		List<LabRefOutMain> poList = pageResult.getResultList();
		List<LabRefOutMainVo> voList = new ArrayList<LabRefOutMainVo>();
		if (poList.size() > 0) {
			for (LabRefOutMain po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return pageResult;
	}

	private LabRefOutMainVo PoToVo(LabRefOutMain po) throws GlobalException {
		LabRefOutMainVo vo = new LabRefOutMainVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "outstoreDate" });
		} catch (Exception e) {
			Log4J.error("po转vo异常" + e.getMessage());
			throw new GlobalException("po转vo异常" + e.getMessage());
		}
		if (null != po.getOutstoreDate()) {
			vo.setOutstoreDate(DateUtils.format(po.getOutstoreDate(),
					DateUtils.formatStr_yyyyMMdd));
		}
		List<LabRefOutDetail> labRefOutDetaiList = labRefOutDetailDAO
				.find("FROM LabRefOutDetail WHERE main.id='" + po.getId()
						+ "' AND isDel='" + Constants_Source.N + "'");
		List<LabRefOutDetailVo> labRefOutDetailVoList = new ArrayList<LabRefOutDetailVo>();
		if (labRefOutDetaiList.size() > 0) {
			for (LabRefOutDetail labRefOutDetail : labRefOutDetaiList) {
				LabRefOutDetailVo labRefOutDetailVo = new LabRefOutDetailVo();
				labRefOutDetailVo.setAmount(labRefOutDetail.getAmount());
				labRefOutDetailVo.setRemark(labRefOutDetail.getRemark());
				labRefOutDetailVo.setReferenceId(labRefOutDetail.getReference()
						.getId());
				LabReference labReference = (LabReference) labRefOutDetailDAO
						.findById(LabReference.class, labRefOutDetail
								.getReference().getId());
				labRefOutDetailVo.setReferenceName(labReference.getName());
				labRefOutDetailVo.setPurity(labReference.getPurity());
				labRefOutDetailVo.setSize(labReference.getSize());
				labRefOutDetailVo.setSpecifications(labReference.getSize());
				labRefOutDetailVoList.add(labRefOutDetailVo);
			}
		}
		vo.setLabRefOutDetailVoList(labRefOutDetailVoList);
		return vo;
	}

	private LabRefOutMain VoToPo(LabRefOutMainVo vo) throws GlobalException {
		LabRefOutMain po = new LabRefOutMain();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "outstoreDate" });
		} catch (Exception e) {
			Log4J.error("vo转po异常" + e.getMessage());
			throw new GlobalException("vo转po异常" + e.getMessage());
		}
		if (null != vo.getOutstoreDate()) {
			try {
				po.setOutstoreDate(DateUtils.parse(vo.getOutstoreDate(),
						DateUtils.formatStr_yyyyMMdd));
			} catch (ParseException e) {
				Log4J.error("日期格式转换异常" + e.getMessage());
				throw new GlobalException("日期格式转换异常" + e.getMessage());
			}
		}
		return po;
	}

	@Override
	public boolean updateLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException {
		try {
			labRefOutMainDAO.updateLabRefOutMain(VoToPo(labRefOutMainVo));
			return true;
		} catch (Exception e) {
			Log4J.error("修改标准品出库信息失败" + e.getMessage());
			throw new GlobalException("修改标准品出库信息失败" + e.getMessage());
		}

	}
	@Override
	public String getMaxLabRefOutMainReceiptno() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabRefOutMain  WHERE receiptno LIKE '"
				+ year_month_day + "%' ";

		List<LabRefOutMain> refOutstoreMainList = labRefOutMainDAO.find(hql);
		String billId = year_month_day;
		if (refOutstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabRefOutMain main : refOutstoreMainList) {
				String recordId = main.getReceiptno();
				int back4 = Integer.valueOf(recordId.substring(recordId
						.length() - 4, recordId.length()));
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

	@Override
	public List<LabRefOutDetailVo> getLabRefOutDetailListByReferenceId(String refOutDetailVoId) throws GlobalException {
		if(!StrUtils.isBlankOrNull(refOutDetailVoId)){
			String[] ids = refOutDetailVoId.split(",");
			String id = "";
			for (int i = 0; i < ids.length; i++) {
				id += "'" + ids[i] + "',";
			}
			if(id.trim().length()>0){
				id=id.substring(0,id.length()-1);
			}
			if(!StrUtils.isBlankOrNull(id)){
				String hql = " FROM LabReference WHERE id IN(" + id + ")";
				List<LabReference> referenceList = labRefOutDetailDAO.find(hql);
				List<LabRefOutDetailVo> labRefOutDetailVoList = new ArrayList<LabRefOutDetailVo>();
				if (null != referenceList && referenceList.size() > 0) {
					for (LabReference labReference : referenceList) {
						LabRefOutDetailVo labRefOutDetailVo = new LabRefOutDetailVo();
						labRefOutDetailVo.setReferenceId(labReference.getId());
						labRefOutDetailVo.setReferenceName(labReference.getName());
						labRefOutDetailVo.setSize(labReference.getSize());
						labRefOutDetailVo.setPurity(labReference.getPurity());
						labRefOutDetailVo.setNum(String.valueOf(labReference
								.getAmount()));
						labRefOutDetailVoList.add(labRefOutDetailVo);
					}
				}
				return labRefOutDetailVoList;
			}
		}
		return null;
	}
	@Override
	public LabRefOutMainVo getLabRefOutMainById(String id, String refIds) throws GlobalException {
		// TODO Auto-generated method stub
		LabRefOutMainVo labRefOutMainVo=new LabRefOutMainVo();
		if(!StrUtils.isBlankOrNull(id)){
			LabRefOutMain labRefOutMain=(LabRefOutMain) labReferenceDAO.findById(LabRefOutMain.class, id);
			if(labRefOutMain!=null){
				BeanUtils.copyProperties(labRefOutMain, labRefOutMainVo,new String[]{"outstoreDate"});
				labRefOutMainVo.setOutstoreDate(DateUtils.format(labRefOutMain.getOutstoreDate(), DateUtils.formatStr_yyyyMMdd));
				String hql="FROM LabRefOutDetail WHERE isDel='"+Constants_Source.N+"' AND main.id='"+labRefOutMain.getId()+"'";
				List<LabRefOutDetail> listLabRefOutDetail=labReferenceDAO.find(hql);
				List<LabRefOutDetailVo> listLabRefOutDetailVo=new ArrayList<LabRefOutDetailVo>();
				if(listLabRefOutDetail!=null&&listLabRefOutDetail.size()>0){
					for(LabRefOutDetail labRefOutDetail:listLabRefOutDetail){
						if(refIds!=null&&refIds.indexOf(labRefOutDetail.getReference().getId())>-1){
							refIds=refIds.replaceAll(labRefOutDetail.getReference().getId(), "");
						}
						LabRefOutDetailVo labRefOutDetailVo = new LabRefOutDetailVo();
						labRefOutDetailVo.setReferenceId(labRefOutDetail.getReference().getId());
						labRefOutDetailVo.setReferenceName(labRefOutDetail.getReference().getName());
						labRefOutDetailVo.setSize(labRefOutDetail.getReference().getSize());
						labRefOutDetailVo.setPurity(labRefOutDetail.getReference().getPurity());
						labRefOutDetailVo.setNum(String.valueOf(labRefOutDetail.getReference().getAmount()));
						labRefOutDetailVo.setId(labRefOutDetail.getId());
						labRefOutDetailVo.setAmount(labRefOutDetail.getAmount());
						labRefOutDetailVo.setRemark(labRefOutDetail.getRemark());
						listLabRefOutDetailVo.add(labRefOutDetailVo);
					}
				}
				if(refIds!=null&&!StrUtils.isBlankOrNull(refIds.replaceAll(",","").trim())) 
					listLabRefOutDetailVo.addAll(this.getLabRefOutDetailListByReferenceId(refIds));
				if(listLabRefOutDetailVo.size()>0)
					labRefOutMainVo.setLabRefOutDetailVoList(listLabRefOutDetailVo);
			}
			
		}
		return labRefOutMainVo;
	}
}
