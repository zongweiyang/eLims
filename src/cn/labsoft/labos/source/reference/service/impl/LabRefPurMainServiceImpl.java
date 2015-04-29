package cn.labsoft.labos.source.reference.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.reference.dao.ILabRefPurDetailDAO;
import cn.labsoft.labos.source.reference.dao.ILabRefPurMainDAO;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabRefPurDetail;
import cn.labsoft.labos.source.reference.entity.LabRefPurMain;
import cn.labsoft.labos.source.reference.entity.LabReference;
import cn.labsoft.labos.source.reference.service.ILabRefPurMainService;
import cn.labsoft.labos.source.reference.vo.LabRefPurDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefPurMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labRefPurMainService")
public class LabRefPurMainServiceImpl extends BaseService implements
		ILabRefPurMainService {
	private ILabRefPurMainDAO labRefPurMainDAO;
	private ILabRefPurDetailDAO labRefPurDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabReferenceDAO labReferenceDAO;

	@Resource
	public void setLabRefPurMainDAO(ILabRefPurMainDAO labRefPurMainDAO) {
		this.labRefPurMainDAO = labRefPurMainDAO;
	}
	@Resource
	public void setLabRefPurDetailDAO(ILabRefPurDetailDAO labRefPurDetailDAO) {
		this.labRefPurDetailDAO = labRefPurDetailDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabReferenceDAO(ILabReferenceDAO labReferenceDAO) {
		this.labReferenceDAO = labReferenceDAO;
	}

	@Override
	public LabRefPurMainVo addLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabRefPurMain labRefPurMain = new LabRefPurMain();
		BeanUtils.copyProperties(labRefPurMainVo, labRefPurMain,
				new String[] { "labRefPurDetailVoList" });
		labRefPurMain.setApplicant(labRefPurMainVo.getApplicant());
		labRefPurMain.setReceiptno(labRefPurMainVo.getReceiptno());
		labRefPurMain.setRemark(labRefPurMainVo.getRemark());
		labRefPurMain.setCreateUserId(sessionContainer.getUserId());
		labRefPurMain.setIsDel(Constants_Source.N);
		labRefPurMain.setType("0");
		labRefPurMainDAO.addLabRefPur(labRefPurMain);

		List<LabRefPurDetailVo> labRefPurDetailVoList = labRefPurMainVo
				.getLabRefPurDetailVoList();
		if (null != labRefPurDetailVoList && labRefPurDetailVoList.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : labRefPurDetailVoList) {
				if (labRefPurDetailVo == null
						|| labRefPurDetailVo.getReferenceId() == null)
					continue;
				LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
				labRefPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labRefPurDetailVo.getNum())) {
					labRefPurDetail.setNum(labRefPurDetailVo.getNum());
				} else {
					labRefPurDetail.setNum(Double.valueOf(0));
				}
				LabReference labReference = (LabReference) labRefPurMainDAO
						.findById(LabReference.class, labRefPurDetailVo
								.getReferenceId());
				labRefPurDetail.setReference(labReference);
				labRefPurDetail.setMain(labRefPurMain);
				if (null != labRefPurDetailVo.getRemark()
						&& !"".equals(labRefPurDetailVo.getRemark())) {
					labRefPurDetail.setRemark(labRefPurDetailVo.getRemark());
				}
				labRefPurDetailDAO.addLabRefPurDetail(labRefPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labRefPurMain
				.getId(),Constants_Source.WF_CODE_REF, getSessionContainer().getFunId(),
				labRefPurMainVo.getAuditMessage(), labRefPurMainVo.getAuditResult());
		if (null != ins) {
			labRefPurMain.setProcessIns(ins);
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		} else {
			throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
		}
		String audit = labRefPurMainVo.getAuditResult();
		BeanUtils.copyProperties(labRefPurMain, labRefPurMainVo, new String []{""});
		labRefPurMainVo.setAuditResult(audit);
		return labRefPurMainVo;
	}

	@Override
	public LabRefPurMainVo getLabRefPurMainById(String mainId)
			throws GlobalException {
		LabRefPurMainVo vo = new LabRefPurMainVo();
		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, mainId);
		BeanUtils.copyProperties(labRefPurMain, vo,
				new String[] { "labRefPurchaseDetail" });
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabRefPurMainPR(LabRefPurMainVo labRefPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabRefPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labRefPurMainVo.getApplicant()
				&& !"".equals(labRefPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labRefPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labRefPurMainVo.getReceiptno()
				&& !"".equals(labRefPurMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labRefPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labRefPurMainVo.getStartDate()
				&& !"".equals(labRefPurMainVo.getStartDate())) {
			hql += " AND  createTime >= '" + labRefPurMainVo.getStartDate().trim()
					+ "'";
		}
		if (null != labRefPurMainVo.getEndDate()
				&& !"".equals(labRefPurMainVo.getEndDate())) {
			hql += " AND  createTime <= '" + labRefPurMainVo.getEndDate().trim() + "'";
		}
		if (null != labRefPurMainVo.getStatus()
				&& !"".equals(labRefPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_REF+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labRefPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		if (null != labRefPurMainVo.getStatus()
				&& !"".equals(labRefPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_REF+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labRefPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labRefPurMainDAO.getPageResult(hql, pageResult);
		List<LabRefPurMain> poList = pageResult.getResultList();
		List<LabRefPurMainVo> voList = new ArrayList<LabRefPurMainVo>();
		if (poList.size() > 0) {
			for (LabRefPurMain po : poList) {
				LabRefPurMainVo vo = new LabRefPurMainVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labRefPurchaseDetail" });
				if (po.getProcessIns() != null) {
					String status = po.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							vo.setIsOper("Y");
						}
						String str = labWfProcessInsDAO
								.getLabWfStepStrByInsId(po.getProcessIns()
										.getId());
						vo.setStatus(str);
					}
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLabRefPurMainMaxReceiptNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM  LabRefPurMain WHERE isDel='" + Constants_Source.N
				+ "' AND  receiptno LIKE '" + year_month_day + "%'";
		List<LabRefPurMain> labRefPurMainList = labRefPurMainDAO.find(hql);
		String billId = year_month_day;
		if (labRefPurMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabRefPurMain main : labRefPurMainList) {
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

	@SuppressWarnings("unchecked")
	@Override
	public void deleteLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {

		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());

		String hql = " FROM LabRefPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labRefPurMain.getId() + "'";
		List<LabRefPurDetail> polist = labRefPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labRefPurDetailDAO.updateLabRefPurDetail(polist.get(i));
			}
		}
		labRefPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labRefPurMainVo
				.getId());
		try {
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabRefPurMainVo updateLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());
		labRefPurMain.setApplicant(labRefPurMainVo.getApplicant());
		labRefPurMain.setCreateTime(labRefPurMainVo.getCreateTime());
		labRefPurMain.setReceiptno(labRefPurMainVo.getReceiptno());
		labRefPurMain.setRemark(labRefPurMainVo.getRemark());
		labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		// 删除
		List<LabRefPurDetailVo> list = getLabRefPurDetailByMainId(labRefPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : list) {
				deleteLabRefPurById(labRefPurDetailVo.getId());
			}
		}
		// 添加
		List<LabRefPurDetailVo> labRefPurDetailVoList = labRefPurMainVo
				.getLabRefPurDetailVoList();
		if (null != labRefPurDetailVoList && labRefPurDetailVoList.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : labRefPurDetailVoList) {
				if (labRefPurDetailVo == null
						|| labRefPurDetailVo.getReferenceId() == null)
					continue;
				LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
				if (!StrUtils.isNull(labRefPurDetailVo.getNum())) {
					labRefPurDetail.setNum(labRefPurDetailVo.getNum());
				} else {
					labRefPurDetail.setNum(Double.valueOf(0));
				}
				LabReference labReference = (LabReference) labRefPurMainDAO
						.findById(LabReference.class, labRefPurDetailVo
								.getReferenceId());
				labRefPurDetail.setReference(labReference);
				labRefPurDetail.setMain(labRefPurMain);
				if (null != labRefPurDetailVo.getRemark()
						&& !"".equals(labRefPurDetailVo.getRemark())) {
					labRefPurDetail.setRemark(labRefPurDetailVo.getRemark());
				}
				labRefPurDetailDAO.addLabRefPurDetail(labRefPurDetail);
			}
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labRefPurMainVo.getId(),Constants_Source.WF_CODE_REF, getSessionContainer()
							.getFunId(), labRefPurMainVo.getAuditMessage(),
					labRefPurMainVo.getAuditResult());
			if (ins == null) {
				throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
			}
		}
		return labRefPurMainVo;
	}

	@Override
	public List<LabRefPurDetailVo> getLabRefPurDetailList(
			LabRefPurMainVo labRefPurMainVo) throws GlobalException {
 		List<LabRefPurDetailVo> volist = new ArrayList<LabRefPurDetailVo>();
		if (null != labRefPurMainVo.getReferenceId()
				&& !"".equals(labRefPurMainVo.getReferenceId())) {
			String[] ids = labRefPurMainVo.getReferenceId().split(",");
			HashSet<String> hashset = new HashSet<String>();
			for (String id : ids) {
				hashset.add(id);
			}
			Iterator<String> iterator=hashset.iterator();
			while(iterator.hasNext()) {
					LabRefPurDetailVo vo = new LabRefPurDetailVo();
					LabReference labReference = (LabReference) labRefPurMainDAO
							.findById(LabReference.class, iterator.next());
					if (labReference != null) {
						vo.setReferenceId(labReference.getId());
						vo.setReferenceName(labReference.getName());
						vo.setSize(labReference.getSize());
						vo.setUnit(labReference.getUnit());
						vo.setAmount(labReference.getAmount());
						volist.add(vo);
					}
			}
		}
		return volist;
	}

	@Override
	public void addLabRefPurDetail(
			List<LabRefPurDetailVo> labRefPurDetailVoList, String id)
			throws GlobalException {
		LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
		for (int i = 0; i < labRefPurDetailVoList.size(); i++) {
			BeanUtils.copyProperties(labRefPurDetailVoList.get(i),
					labRefPurDetail, new String[] { "" });
			if (null != labRefPurDetailVoList.get(i).getNum()
					&& !"".equals(labRefPurDetailVoList.get(i).getNum())) {
				labRefPurDetail.setNum(labRefPurDetailVoList.get(i).getNum());
			}
			if (null != id && !"".equals(id)) {
				LabRefPurMain main = (LabRefPurMain) labRefPurDetailDAO
						.findById(LabRefPurMain.class, id);
				labRefPurDetail.setMain(main);
			}
			if (null != labRefPurDetailVoList.get(i).getReferenceId()
					&& !""
							.equals(labRefPurDetailVoList.get(i)
									.getReferenceId())) {
				LabReference reference = (LabReference) labRefPurDetailDAO
						.findById(LabReference.class, labRefPurDetailVoList
								.get(i).getReferenceId());
				labRefPurDetail.setReference(reference);
			}
			if (null != labRefPurDetailVoList.get(i).getRemark()
					&& !"".equals(labRefPurDetailVoList.get(i).getRemark())) {
				labRefPurDetail.setRemark(labRefPurDetailVoList.get(i)
						.getRemark());
			}
			try {
				labRefPurDetailDAO.addLabRefPurDetail(labRefPurDetail);
			} catch (RuntimeException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRefPurDetailVo> getLabRefPurDetailByMainId(String Id)
			throws GlobalException {
		String hql = " FROM LabRefPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + Id + "'";
		List<LabRefPurDetail> polist = labRefPurDetailDAO.find(hql);
		List<LabRefPurDetailVo> volist = new ArrayList<LabRefPurDetailVo>();
		if (polist.size() > 0) {
			for (LabRefPurDetail po : polist) {
				LabRefPurDetailVo vo = new LabRefPurDetailVo();
				BeanUtils.copyProperties(po, vo, new String[] { "main",
						"reference" });
				if (null != po.getReference() && !"".equals(po.getReference())) {
					vo.setReferenceName(po.getReference().getName());
					vo.setReferenceId(po.getReference().getId());
					vo.setUnit(po.getReference().getUnit());
					vo.setSize(po.getReference().getSize());
					vo.setAmount(po.getReference().getAmount());
					vo.setRemark(po.getRemark());
					vo.setSpecifications(po.getReference().getSize());
					vo.setPurity(po.getReference().getPurity());
					vo.setNum(po.getNum());
				}
				volist.add(vo);
			}
		}

		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRefPurDetailVo> getLabRefPurDetailList()
			throws GlobalException {
		String hql = "FROM LabReference WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND amount<=safeAmount";
		List<LabReference> poList = labRefPurDetailDAO.find(hql);
		List<LabRefPurDetailVo> voList = new ArrayList<LabRefPurDetailVo>();
		if (poList.size() > 0) {
			for (LabReference po : poList) {
				LabRefPurDetailVo vo = new LabRefPurDetailVo();
				vo.setReferenceName(po.getName());
				vo.setReferenceId(po.getId());
				vo.setUnit(po.getUnit());
				vo.setSize(po.getSize());
				vo.setAmount(po.getAmount());
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public boolean deleteLabRefPurById(String id) throws GlobalException {
		LabRefPurDetail labRefPurDetail = (LabRefPurDetail) labRefPurDetailDAO
				.findById(LabRefPurDetail.class, id);
		labRefPurDetail.setIsDel(Constants_Source.Y);
		boolean flag = labRefPurDetailDAO
				.updateLabRefPurDetail(labRefPurDetail);
		return flag;
	}

	@Override
	public boolean updateLabRefPur4Audit(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());
		try {
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labRefPurMainVo.getId(),Constants_Source.WF_CODE_REF, getSessionContainer()
							.getFunId(), labRefPurMainVo.getAuditMessage(),
					labRefPurMainVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabRefPur4Approve(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());
		try {
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labRefPurMainVo.getId(),Constants_Source.WF_CODE_REF, getSessionContainer()
							.getFunId(), labRefPurMainVo.getAuditMessage(),
					labRefPurMainVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabRefPurDetailVo addLabRefPurDetail(
			LabRefPurDetailVo labRefPurDetailVo, String id)
			throws GlobalException {
		LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
		BeanUtils.copyProperties(labRefPurDetailVo, labRefPurDetail,
				new String[] { "" });
		if (null != labRefPurDetailVo.getNum()
				&& !"".equals(labRefPurDetailVo.getNum())) {
			labRefPurDetail.setNum(labRefPurDetailVo.getNum());
		}
		if (null != id && !"".equals(id)) {
			LabRefPurMain main = (LabRefPurMain) labRefPurDetailDAO.findById(
					LabRefPurMain.class, id);
			labRefPurDetail.setMain(main);
		}
		if (null != labRefPurDetailVo.getReferenceId()
				&& !"".equals(labRefPurDetailVo.getReferenceId())) {
			LabReference reference = (LabReference) labRefPurDetailDAO
					.findById(LabReference.class, labRefPurDetailVo
							.getReferenceId());
			labRefPurDetail.setReference(reference);
		}
		if (null != labRefPurDetailVo.getRemark()
				&& !"".equals(labRefPurDetailVo.getRemark())) {
			labRefPurDetail.setRemark(labRefPurDetailVo.getRemark());
		}
		try {
			labRefPurDetail = labRefPurDetailDAO
					.addLabRefPurDetail(labRefPurDetail);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		BeanUtils.copyProperties(labRefPurDetail, labRefPurDetailVo,
				new String[] { "" });
		if (null != labRefPurDetail.getReference()) {
			labRefPurDetailVo.setReferenceId(labRefPurDetail.getReference()
					.getId());
			labRefPurDetailVo.setReferenceName(labRefPurDetail.getReference()
					.getName());
		}
		return labRefPurDetailVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabRefPurInsPR(LabRefPurMainVo labRefPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabRefPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labRefPurMainVo.getApplicant()
				&& !"".equals(labRefPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labRefPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labRefPurMainVo.getReceiptno()
				&& !"".equals(labRefPurMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labRefPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labRefPurMainVo.getStartDate()
				&& !"".equals(labRefPurMainVo.getStartDate())) {
			hql += " AND  inTime >= '" + labRefPurMainVo.getStartDate().trim() + "'";
		}
		if (null != labRefPurMainVo.getEndDate()
				&& !"".equals(labRefPurMainVo.getEndDate())) {
			hql += " AND  inTime <= '" + labRefPurMainVo.getEndDate().trim() + "'";
		}
		if (null != labRefPurMainVo.getCreateTime()
				&& !"".equals(labRefPurMainVo.getCreateTime().trim())) {
			hql += " AND  crefteTime <= '" + labRefPurMainVo.getCreateTime().trim()
					+ "'";
		}
		if (null != labRefPurMainVo.getStatus()
				&& !"".equals(labRefPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_REF+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labRefPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labRefPurMainDAO.getPageResult(hql, pageResult);
		List<LabRefPurMain> poList = pageResult.getResultList();
		List<LabRefPurMainVo> voList = new ArrayList<LabRefPurMainVo>();
		if (poList.size() > 0) {
			for (LabRefPurMain po : poList) {
				LabRefPurMainVo vo = new LabRefPurMainVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labRefPurchaseDetail" });
				if (po.getProcessIns() != null) {
					String status = po.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							vo.setIsOper("Y");
						}
						String str = labWfProcessInsDAO
								.getLabWfStepStrByInsId(po.getProcessIns()
										.getId());
						vo.setStatus(str);
					}
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public void updateLabRefPur4Ins(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);

		if (null == labRefPurMainVo.getId()
				|| "".equals(labRefPurMainVo.getId())) {
			labRefPurMainVo.setInPerson(sessionContainer.getUserName());
			labRefPurMainVo.setInTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			labRefPurMainVo = this.addLabRefPurIns(labRefPurMainVo);
		}
		labRefPurMainVo.setInPerson(sessionContainer.getUserName());
		labRefPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		this.updateLabRefInMain(labRefPurMainVo);
		List<LabRefPurDetailVo> list = getLabRefPurDetailByMainId(labRefPurMainVo
				.getId());
		if (null != list && list.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : list) {
				LabRefPurDetail po = (LabRefPurDetail) labRefPurMainDAO
						.findById(LabRefPurDetail.class, labRefPurDetailVo
								.getId());
				LabReference labReference = (LabReference) labRefPurMainDAO
						.findById(LabReference.class, po.getReference().getId());
				labReference.setAmount(labReference.getAmount() + po.getNum());
				labReferenceDAO.updateLabReference(labReference);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
				labRefPurMainVo.getId(),Constants_Source.WF_CODE_REF, getSessionContainer()
						.getFunId(), labRefPurMainVo.getAuditMessage(),
				labRefPurMainVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
		}
	}

	@Override
	public LabRefPurMainVo addLabRefPurIns(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabRefPurMain labRefPurMain = new LabRefPurMain();
		BeanUtils.copyProperties(labRefPurMainVo, labRefPurMain,
				new String[] { "labRefPurDetailVoList" });
		labRefPurMain.setReceiptno(labRefPurMainVo.getReceiptno());

		labRefPurMain.setCreateUserId(sessionContainer.getUserId());
		labRefPurMain.setCreateTime(labRefPurMainVo.getCreateTime());
		if (null != labRefPurMainVo.getInPerson()
				&& !"".equals(labRefPurMainVo.getInPerson())) {
			labRefPurMain.setInPerson(labRefPurMainVo.getInPerson());
		}
		if (null != labRefPurMainVo.getInTime()
				&& !"".equals(labRefPurMainVo.getInTime())) {
			labRefPurMain.setInTime(labRefPurMainVo.getInTime());
		}
		labRefPurMain.setIsDel(Constants_Source.N);
		labRefPurMain.setType("1");
		labRefPurMainDAO.addLabRefPur(labRefPurMain);
		labRefPurMainVo.setId(labRefPurMain.getId());

		List<LabRefPurDetailVo> labRefPurDetailVoList = labRefPurMainVo
				.getLabRefPurDetailVoList();
		if (null != labRefPurDetailVoList && labRefPurDetailVoList.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : labRefPurDetailVoList) {
				if (labRefPurDetailVo == null
						|| labRefPurDetailVo.getReferenceId() == null)
					continue;
				LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
				labRefPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labRefPurDetailVo.getNum())) {
					labRefPurDetail.setNum(labRefPurDetailVo.getNum());
				} else {
					labRefPurDetail.setNum(Double.valueOf(0));
				}
				LabReference labReference = (LabReference) labRefPurMainDAO
						.findById(LabReference.class, labRefPurDetailVo
								.getReferenceId());
				labRefPurDetail.setReference(labReference);
				labRefPurDetail.setMain(labRefPurMain);
				if (null != labRefPurDetailVo.getRemark()
						&& !"".equals(labRefPurDetailVo.getRemark())) {
					labRefPurDetail.setRemark(labRefPurDetailVo.getRemark());
				}
				labRefPurDetailDAO.addLabRefPurDetail(labRefPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labRefPurMain
				.getId(),Constants_Source.WF_CODE_REF, getSessionContainer().getFunId(),
				labRefPurMainVo.getAuditMessage(), labRefPurMainVo.getAuditResult());
		if (ins != null) {
			labRefPurMain.setProcessIns(ins);
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		} else {
			throw new GlobalException("LabRefPurMainServiceImpl...流程实例化出错！");
		}
		return labRefPurMainVo;
	}

	@Override
	public LabRefPurMainVo updateLabRefInMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {
		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labRefPurMain.setCreateUserId(sessionContainer.getUserId());
		labRefPurMain.setCreateTime(labRefPurMainVo.getCreateTime());
		labRefPurMain.setReceiptno(labRefPurMainVo.getReceiptno());
		if (null != labRefPurMainVo.getInPerson()
				&& !"".equals(labRefPurMainVo.getInPerson())) {
			labRefPurMain.setInPerson(labRefPurMainVo.getInPerson());
		}
		if (null != labRefPurMainVo.getInTime()
				&& !"".equals(labRefPurMainVo.getInTime())) {
			labRefPurMain.setInTime(labRefPurMainVo.getInTime());
		}
		labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		// 删除
		List<LabRefPurDetailVo> list = getLabRefPurDetailByMainId(labRefPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : list) {
				deleteLabRefPurById(labRefPurDetailVo.getId());
			}
		}
		// 添加
		List<LabRefPurDetailVo> labRefPurDetailVoList = labRefPurMainVo
				.getLabRefPurDetailVoList();
		if (null != labRefPurDetailVoList && labRefPurDetailVoList.size() > 0) {
			for (LabRefPurDetailVo labRefPurDetailVo : labRefPurDetailVoList) {
				if (labRefPurDetailVo == null
						|| labRefPurDetailVo.getReferenceId() == null)
					continue;
				LabRefPurDetail labRefPurDetail = new LabRefPurDetail();
				if (!StrUtils.isNull(labRefPurDetailVo.getNum())) {
					labRefPurDetail.setNum(labRefPurDetailVo.getNum());
				} else {
					labRefPurDetail.setNum(Double.valueOf(0));
				}
				LabReference labReference = (LabReference) labRefPurMainDAO
						.findById(LabReference.class, labRefPurDetailVo
								.getReferenceId());
				labRefPurDetail.setReference(labReference);
				labRefPurDetail.setMain(labRefPurMain);
				if (null != labRefPurDetailVo.getRemark()
						&& !"".equals(labRefPurDetailVo.getRemark())) {
					labRefPurDetail.setRemark(labRefPurDetailVo.getRemark());
				}
				labRefPurDetailDAO.addLabRefPurDetail(labRefPurDetail);
			}
		}
		return labRefPurMainVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteLabRefPurIns(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException {

		LabRefPurMain labRefPurMain = (LabRefPurMain) labRefPurMainDAO
				.findById(LabRefPurMain.class, labRefPurMainVo.getId());

		String hql = " FROM LabRefPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labRefPurMain.getId() + "'";
		List<LabRefPurDetail> polist = labRefPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labRefPurDetailDAO.updateLabRefPurDetail(polist.get(i));
			}
		}
		labRefPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labRefPurMainVo.getId());
		try {
			labRefPurMainDAO.updateLabRefPur(labRefPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

}
