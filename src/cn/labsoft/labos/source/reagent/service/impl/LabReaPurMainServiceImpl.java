package cn.labsoft.labos.source.reagent.service.impl;

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
import cn.labsoft.labos.source.reagent.dao.ILabReaPurDetailDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReaPurMainDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaPurDetail;
import cn.labsoft.labos.source.reagent.entity.LabReaPurMain;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
import cn.labsoft.labos.source.reagent.service.ILabReaPurMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaPurDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaPurMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labReaPurMainService")
public class LabReaPurMainServiceImpl extends BaseService implements
		ILabReaPurMainService {
	private ILabReaPurMainDAO labReaPurMainDAO;
	private ILabReaPurDetailDAO labReaPurDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabReagentDAO labReagentDAO;

    @Resource
	public void setLabReagentDAO(ILabReagentDAO labReagentDAO) {
		this.labReagentDAO = labReagentDAO;
	}
    @Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
    @Resource
	public void setLabReaPurDetailDAO(ILabReaPurDetailDAO labReaPurDetailDAO) {
		this.labReaPurDetailDAO = labReaPurDetailDAO;
	}
    @Resource
	public void setLabReaPurMainDAO(ILabReaPurMainDAO labReaPurMainDAO) {
		this.labReaPurMainDAO = labReaPurMainDAO;
	}

	@Override
	public LabReaPurMainVo addLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabReaPurMain labReaPurMain = new LabReaPurMain();
		BeanUtils.copyProperties(labReaPurMainVo, labReaPurMain,
				new String[] { "labReaPurDetailVoList" });
		labReaPurMain.setApplicant(labReaPurMainVo.getApplicant());
		labReaPurMain.setReceiptno(labReaPurMainVo.getReceiptno());
		labReaPurMain.setRemark(labReaPurMainVo.getRemark());
		labReaPurMain.setCreateUserId(sessionContainer.getUserId());
		labReaPurMain.setIsDel(Constants_Source.N);
		labReaPurMain.setType("0");
		labReaPurMainDAO.addLabReaPur(labReaPurMain);

		List<LabReaPurDetailVo> labReaPurDetailVoList = labReaPurMainVo
				.getLabReaPurDetailVoList();
		if (null != labReaPurDetailVoList && labReaPurDetailVoList.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : labReaPurDetailVoList) {
				if (null == labReaPurDetailVo
						|| labReaPurDetailVo.getReagentId() == null)
					continue;
				LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
				labReaPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labReaPurDetailVo.getNum())) {
					labReaPurDetail.setNum(labReaPurDetailVo.getNum());
				} else {
					labReaPurDetail.setNum(Double.valueOf(0));
				}
				LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(LabReagent.class, labReaPurDetailVo.getReagentId());
				labReaPurDetail.setReagent(labReagent);
				labReaPurDetail.setMain(labReaPurMain);
				labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
				if (null != labReaPurDetailVo.getRemark()
						&& !"".equals(labReaPurDetailVo.getRemark())) {
					labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
				}
				labReaPurDetail=labReaPurDetailDAO.addLabReaPurDetail(labReaPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labReaPurMain.getId(),Constants_Source.WF_CODE_REA, getSessionContainer().getFunId(),
				labReaPurMainVo.getAuditMessage(), labReaPurMainVo.getAuditResult());
		if (ins != null) {
			labReaPurMain.setProcessIns(ins);
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		} else {
			throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
		}
		String audit = labReaPurMainVo.getAuditResult();
		BeanUtils.copyProperties(labReaPurMain, labReaPurMainVo, new String []{""});
		labReaPurMainVo.setAuditResult(audit);
		return labReaPurMainVo;
	}

	@Override
	public LabReaPurMainVo getLabReaPurMainById(String mainId)
			throws GlobalException {
		LabReaPurMainVo vo = new LabReaPurMainVo();
		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, mainId);
		BeanUtils.copyProperties(labReaPurMain, vo,
				new String[] { "labReaPurchaseDetail" });
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabReaPurMainPR(LabReaPurMainVo labReaPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReaPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labReaPurMainVo.getApplicant()
				&& !"".equals(labReaPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labReaPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labReaPurMainVo.getStartDate()
				&& !"".equals(labReaPurMainVo.getStartDate())) {
			hql += " AND  createTime >= '" + labReaPurMainVo.getStartDate()
					+ "'";
		}
		if (null != labReaPurMainVo.getEndDate()
				&& !"".equals(labReaPurMainVo.getEndDate())) {
			hql += " AND  createTime <= '" + labReaPurMainVo.getEndDate() + "'";
		}
		if (null != labReaPurMainVo.getReceiptno()
				&& !"".equals(labReaPurMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labReaPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labReaPurMainVo.getStatus()
				&& !"".equals(labReaPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_REA+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labReaPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labReaPurMainDAO.getPageResult(hql, pageResult);
		List<LabReaPurMain> poList = pageResult.getResultList();
		List<LabReaPurMainVo> voList = new ArrayList<LabReaPurMainVo>();
		if (poList.size() > 0) {
			for (LabReaPurMain po : poList) {
				LabReaPurMainVo vo = new LabReaPurMainVo();
				BeanUtils.copyProperties(po, vo,new String[] { "labReaPurchaseDetail" });
				if (po.getProcessIns() != null) {
					String status = po.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							vo.setIsOper("Y");
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
						vo.setStatus(str);
					}
				}
				labWfProcessInsDAO.getLabWfStepStrByInsId(po.getProcessIns().getId());
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLabReaPurMainMaxReceiptNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM  LabReaPurMain WHERE isDel='" + Constants_Source.N
				+ "' AND  receiptno LIKE '" + year_month_day + "%'";
		List<LabReaPurMain> labReaPurMainList = labReaPurMainDAO.find(hql);
		String billId = year_month_day;
		if (labReaPurMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabReaPurMain main : labReaPurMainList) {
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
	public void deleteLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {

		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());

		String hql = " FROM LabReaPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labReaPurMain.getId() + "'";
		List<LabReaPurDetail> polist = labReaPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labReaPurDetailDAO.updateLabReaPurDetail(polist.get(i));
			}
		}
		labReaPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labReaPurMainVo
				.getId());
		try {
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabReaPurMainVo updateLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());
		labReaPurMain.setApplicant(labReaPurMainVo.getApplicant());
		labReaPurMain.setCreateTime(labReaPurMainVo.getCreateTime());
		labReaPurMain.setReceiptno(labReaPurMainVo.getReceiptno());
		labReaPurMain.setRemark(labReaPurMainVo.getRemark());
		labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		// 删除
		List<LabReaPurDetailVo> list = getLabReaPurDetailByMainId(labReaPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : list) {
				deleteLabReaPurById(labReaPurDetailVo.getId());
			}
		}
		// 添加
		List<LabReaPurDetailVo> labReaPurDetailVoList = labReaPurMainVo
				.getLabReaPurDetailVoList();
		if (null != labReaPurDetailVoList && labReaPurDetailVoList.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : labReaPurDetailVoList) {
				if (labReaPurDetailVo == null
						|| labReaPurDetailVo.getReagentId() == null)
					continue;
				LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
				if (!StrUtils.isNull(labReaPurDetailVo.getNum())) {
					labReaPurDetail.setNum(labReaPurDetailVo.getNum());
				} else {
					labReaPurDetail.setNum(Double.valueOf(0));
				}
				LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(
						LabReagent.class, labReaPurDetailVo.getReagentId());
				labReaPurDetail.setReagent(labReagent);
				labReaPurDetail.setMain(labReaPurMain);
				if (null != labReaPurDetailVo.getRemark()
						&& !"".equals(labReaPurDetailVo.getRemark())) {
					labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
				}
				labReaPurDetailDAO.addLabReaPurDetail(labReaPurDetail);
			}
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labReaPurMain.getId(),Constants_Source.WF_CODE_REA, getSessionContainer()
							.getFunId(), labReaPurMainVo.getAuditMessage(),
					labReaPurMainVo.getAuditResult());
			if (ins == null) {
				throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
			}
		}
		return labReaPurMainVo;
	}

	@Override
	public List<LabReaPurDetailVo> getLabReaPurDetailList(
			LabReaPurMainVo labReaPurMainVo) throws GlobalException {
		List<LabReaPurDetailVo> volist = new ArrayList<LabReaPurDetailVo>();
		if (null != labReaPurMainVo.getReagentId()
				&& !"".equals(labReaPurMainVo.getReagentId())) {
			String[] ids = labReaPurMainVo.getReagentId().split(",");
			//出去重复项
			HashSet<String> hs = new HashSet<String>(); 
			for (String id : ids) {
				hs.add(id);
			}
			Iterator<String> iterator = hs.iterator();
			while(iterator.hasNext()) {
					LabReaPurDetailVo vo = new LabReaPurDetailVo();
					LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(LabReagent.class, iterator.next());
					if (labReagent != null) {
						vo.setReagentId(labReagent.getId());
						vo.setReagentName(labReagent.getName());
						vo.setSize(labReagent.getSize());
						vo.setAmount(labReagent.getAmount());
						volist.add(vo);
					}
			}
		}
		return volist;
	}

	@Override
	public void addLabReaPurDetail(
			List<LabReaPurDetailVo> labReaPurDetailVoList, String id)
			throws GlobalException {
		LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
		for (int i = 0; i < labReaPurDetailVoList.size(); i++) {
			BeanUtils.copyProperties(labReaPurDetailVoList.get(i),
					labReaPurDetail, new String[] { "" });
			if (null != labReaPurDetailVoList.get(i).getNum()
					&& !"".equals(labReaPurDetailVoList.get(i).getNum())) {
				labReaPurDetail.setNum(labReaPurDetailVoList.get(i).getNum());
			}
			if (null != id && !"".equals(id)) {
				LabReaPurMain main = (LabReaPurMain) labReaPurDetailDAO
						.findById(LabReaPurMain.class, id);
				labReaPurDetail.setMain(main);
			}
			if (null != labReaPurDetailVoList.get(i).getReagentId()
					&& !"".equals(labReaPurDetailVoList.get(i).getReagentId())) {
				LabReagent reagent = (LabReagent) labReaPurDetailDAO.findById(
						LabReagent.class, labReaPurDetailVoList.get(i)
								.getReagentId());
				labReaPurDetail.setReagent(reagent);
			}
			if (null != labReaPurDetailVoList.get(i).getRemark()
					&& !"".equals(labReaPurDetailVoList.get(i).getRemark())) {
				labReaPurDetail.setRemark(labReaPurDetailVoList.get(i)
						.getRemark());
			}
			try {
				labReaPurDetailDAO.addLabReaPurDetail(labReaPurDetail);
			} catch (RuntimeException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabReaPurDetailVo> getLabReaPurDetailByMainId(String Id)
			throws GlobalException {
		String hql = " FROM LabReaPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + Id + "'";
		List<LabReaPurDetail> polist = labReaPurDetailDAO.find(hql);
		List<LabReaPurDetailVo> volist = new ArrayList<LabReaPurDetailVo>();
		if (polist.size() > 0) {
			for (LabReaPurDetail po : polist) {
				LabReaPurDetailVo vo = new LabReaPurDetailVo();
				BeanUtils.copyProperties(po, vo, new String[] { "main",
						"reagent" });
				if (null != po.getReagent() && !"".equals(po.getReagent())) {
					vo.setReagentName(po.getReagent().getName());
					vo.setReagentId(po.getReagent().getId());
					vo.setSize(po.getReagent().getSize());
					vo.setAmount(po.getReagent().getAmount());
					vo.setRemark(po.getRemark());
					vo.setSpecifications(po.getReagent().getSize());
					vo.setPurity(po.getReagent().getPurity());
					vo.setNum(po.getNum());
				}
				volist.add(vo);
			}
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabReaPurDetailVo> getLabReaPurDetailList()
			throws GlobalException {
		String hql = "FROM LabReagent WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND amount<=safeAmount";
		List<LabReagent> poList = labReaPurDetailDAO.find(hql);
		List<LabReaPurDetailVo> voList = new ArrayList<LabReaPurDetailVo>();
		if (poList.size() > 0) {
			for (LabReagent po : poList) {
				LabReaPurDetailVo vo = new LabReaPurDetailVo();
				vo.setReagentName(po.getName());
				vo.setReagentId(po.getId());
				vo.setSize(po.getSize());
				vo.setAmount(po.getAmount());
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public boolean deleteLabReaPurById(String id) throws GlobalException {
		LabReaPurDetail labReaPurDetail = (LabReaPurDetail) labReaPurDetailDAO
				.findById(LabReaPurDetail.class, id);
		labReaPurDetail.setIsDel(Constants_Source.Y);
		boolean flag = labReaPurDetailDAO
				.updateLabReaPurDetail(labReaPurDetail);
		return flag;
	}

	@Override
	public boolean updateLabReaPur4Audit(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());
		try {
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labReaPurMain.getId(),Constants_Source.WF_CODE_REA, getSessionContainer().getFunId(), labReaPurMainVo.getAuditMessage(),
					labReaPurMainVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabReaPur4Approve(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());
		try {
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labReaPurMain.getId(),Constants_Source.WF_CODE_REA, getSessionContainer()
							.getFunId(), labReaPurMainVo.getAuditMessage(),
					labReaPurMainVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabReaPurDetailVo addLabReaPurDetail(
			LabReaPurDetailVo labReaPurDetailVo, String id)
			throws GlobalException {
		LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
		BeanUtils.copyProperties(labReaPurDetailVo, labReaPurDetail,
				new String[] { "" });
		if (null != labReaPurDetailVo.getNum()
				&& !"".equals(labReaPurDetailVo.getNum())) {
			labReaPurDetail.setNum(labReaPurDetailVo.getNum());
		}
		if (null != id && !"".equals(id)) {
			LabReaPurMain main = (LabReaPurMain) labReaPurDetailDAO.findById(
					LabReaPurMain.class, id);
			labReaPurDetail.setMain(main);
		}
		if (null != labReaPurDetailVo.getReagentId()
				&& !"".equals(labReaPurDetailVo.getReagentId())) {
			LabReagent reagent = (LabReagent) labReaPurDetailDAO.findById(
					LabReagent.class, labReaPurDetailVo.getReagentId());
			labReaPurDetail.setReagent(reagent);
		}
		if (null != labReaPurDetailVo.getRemark()
				&& !"".equals(labReaPurDetailVo.getRemark())) {
			labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
		}
		try {
			labReaPurDetail = labReaPurDetailDAO
					.addLabReaPurDetail(labReaPurDetail);
		} catch (RuntimeException e) {
			throw new GlobalException("" + e.getMessage());
		}
		BeanUtils.copyProperties(labReaPurDetail, labReaPurDetailVo,
				new String[] { "" });
		if (null != labReaPurDetail.getReagent()) {
			labReaPurDetailVo
					.setReagentId(labReaPurDetail.getReagent().getId());
			labReaPurDetailVo.setReagentName(labReaPurDetail.getReagent()
					.getName());
		}
		return labReaPurDetailVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabReaPurInsPR(LabReaPurMainVo labReaPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReaPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labReaPurMainVo.getApplicant()
				&& !"".equals(labReaPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labReaPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labReaPurMainVo.getStartDate()
				&& !"".equals(labReaPurMainVo.getStartDate())) {
			hql += " AND  inTime >= '" + labReaPurMainVo.getStartDate() + "'";
		}
		if (null != labReaPurMainVo.getEndDate()
				&& !"".equals(labReaPurMainVo.getEndDate())) {
			hql += " AND  inTime <= '" + labReaPurMainVo.getEndDate() + "'";
		}
		if (null != labReaPurMainVo.getCreateTime()
				&& !"".equals(labReaPurMainVo.getCreateTime().trim())) {
			hql += " AND  createTime <= '" + labReaPurMainVo.getCreateTime().trim()
					+ "'";
		}
		if (null != labReaPurMainVo.getReceiptno()
				&& !"".equals(labReaPurMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labReaPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labReaPurMainVo.getStatus()
				&& !"".equals(labReaPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_REA+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labReaPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labReaPurMainDAO.getPageResult(hql, pageResult);
		List<LabReaPurMain> poList = pageResult.getResultList();
		List<LabReaPurMainVo> voList = new ArrayList<LabReaPurMainVo>();
		if (poList.size() > 0) {
			for (LabReaPurMain po : poList) {
				LabReaPurMainVo vo = new LabReaPurMainVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labReaPurchaseDetail" });
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
	public void updateLabReaPur4Ins(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);

		if (null == labReaPurMainVo.getId()
				|| "".equals(labReaPurMainVo.getId())) {
			labReaPurMainVo.setInPerson(sessionContainer.getUserName());
			labReaPurMainVo.setInTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			labReaPurMainVo = this.addLabReaPurIns(labReaPurMainVo);
		}
		labReaPurMainVo.setInPerson(sessionContainer.getUserName());
		labReaPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		this.updateLabReaInMain(labReaPurMainVo);
		List<LabReaPurDetailVo> list = getLabReaPurDetailByMainId(labReaPurMainVo
				.getId());
		if (null != list && list.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : list) {
				LabReaPurDetail po = (LabReaPurDetail) labReaPurMainDAO
						.findById(LabReaPurDetail.class, labReaPurDetailVo
								.getId());
				LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(
						LabReagent.class, po.getReagent().getId());
				labReagent.setAmount(labReagent.getAmount() + po.getNum());
				labReagentDAO.updateLabReagent(labReagent);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
				labReaPurMainVo.getId(),Constants_Source.WF_CODE_REA, getSessionContainer()
						.getFunId(), labReaPurMainVo.getAuditMessage(),
				labReaPurMainVo.getAuditResult());
		if (ins == null) {
			throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
		}
	}

	@Override
	public LabReaPurMainVo addLabReaPurIns(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabReaPurMain labReaPurMain = new LabReaPurMain();
		BeanUtils.copyProperties(labReaPurMainVo, labReaPurMain,
				new String[] { "labReaPurDetailVoList" });
		labReaPurMain.setReceiptno(labReaPurMainVo.getReceiptno());

		labReaPurMain.setCreateUserId(sessionContainer.getUserId());
		labReaPurMain.setCreateTime(labReaPurMainVo.getCreateTime());
		if (null != labReaPurMainVo.getInPerson()
				&& !"".equals(labReaPurMainVo.getInPerson())) {
			labReaPurMain.setInPerson(labReaPurMainVo.getInPerson());
		}
		if (null != labReaPurMainVo.getInTime()
				&& !"".equals(labReaPurMainVo.getInTime())) {
			labReaPurMain.setInTime(labReaPurMainVo.getInTime());
		}
		labReaPurMain.setIsDel(Constants_Source.N);
		labReaPurMain.setType("1");
		labReaPurMainDAO.addLabReaPur(labReaPurMain);
		labReaPurMainVo.setId(labReaPurMain.getId());

		List<LabReaPurDetailVo> labReaPurDetailVoList = labReaPurMainVo
				.getLabReaPurDetailVoList();
		if (null != labReaPurDetailVoList && labReaPurDetailVoList.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : labReaPurDetailVoList) {
				if (null == labReaPurDetailVo
						|| labReaPurDetailVo.getReagentId() == null)
					continue;
				LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
				labReaPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labReaPurDetailVo.getNum())) {
					labReaPurDetail.setNum(labReaPurDetailVo.getNum());
				} else {
					labReaPurDetail.setNum(Double.valueOf(0));
				}
				LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(
						LabReagent.class, labReaPurDetailVo.getReagentId());
				labReaPurDetail.setReagent(labReagent);
				labReaPurDetail.setMain(labReaPurMain);
				if (null != labReaPurDetailVo.getRemark()
						&& !"".equals(labReaPurDetailVo.getRemark())) {
					labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
				}
				labReaPurDetailDAO.addLabReaPurDetail(labReaPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labReaPurMain
				.getId(),Constants_Source.WF_CODE_REA, getSessionContainer().getFunId(),
				labReaPurMainVo.getAuditMessage(), labReaPurMainVo
						.getAuditResult());
		if (null != ins) {
			labReaPurMain.setProcessIns(ins);
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		} else {
			throw new GlobalException("LabReaPurMainServiceImpl...流程实例化出错！");
		}
		return labReaPurMainVo;
	}

	@Override
	public LabReaPurMainVo updateLabReaInMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {
		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labReaPurMain.setCreateUserId(sessionContainer.getUserId());
		labReaPurMain.setCreateTime(labReaPurMainVo.getCreateTime());
		labReaPurMain.setReceiptno(labReaPurMainVo.getReceiptno());
		if (null != labReaPurMainVo.getInPerson()
				&& !"".equals(labReaPurMainVo.getInPerson())) {
			labReaPurMain.setInPerson(labReaPurMainVo.getInPerson());
		}
		if (null != labReaPurMainVo.getInTime()
				&& !"".equals(labReaPurMainVo.getInTime())) {
			labReaPurMain.setInTime(labReaPurMainVo.getInTime());
		}
		labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		// 删除
		List<LabReaPurDetailVo> list = getLabReaPurDetailByMainId(labReaPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : list) {
				deleteLabReaPurById(labReaPurDetailVo.getId());
			}
		}
		// 添加
		List<LabReaPurDetailVo> labReaPurDetailVoList = labReaPurMainVo
				.getLabReaPurDetailVoList();
		if (null != labReaPurDetailVoList && labReaPurDetailVoList.size() > 0) {
			for (LabReaPurDetailVo labReaPurDetailVo : labReaPurDetailVoList) {
				if (labReaPurDetailVo == null
						|| labReaPurDetailVo.getReagentId() == null)
					continue;
				LabReaPurDetail labReaPurDetail = new LabReaPurDetail();
				if (!StrUtils.isNull(labReaPurDetailVo.getNum())) {
					labReaPurDetail.setNum(labReaPurDetailVo.getNum());
				} else {
					labReaPurDetail.setNum(Double.valueOf(0));
				}
				LabReagent labReagent = (LabReagent) labReaPurMainDAO.findById(
						LabReagent.class, labReaPurDetailVo.getReagentId());
				labReaPurDetail.setReagent(labReagent);
				labReaPurDetail.setMain(labReaPurMain);
				if (null != labReaPurDetailVo.getRemark()
						&& !"".equals(labReaPurDetailVo.getRemark())) {
					labReaPurDetail.setRemark(labReaPurDetailVo.getRemark());
				}
				labReaPurDetailDAO.addLabReaPurDetail(labReaPurDetail);
			}
		}
		return labReaPurMainVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteLabReaPurIns(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException {

		LabReaPurMain labReaPurMain = (LabReaPurMain) labReaPurMainDAO
				.findById(LabReaPurMain.class, labReaPurMainVo.getId());

		String hql = " FROM LabReaPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labReaPurMain.getId() + "'";
		List<LabReaPurDetail> polist = labReaPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labReaPurDetailDAO.updateLabReaPurDetail(polist.get(i));
			}
		}
		labReaPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labReaPurMainVo.getId());
		try {
			labReaPurMainDAO.updateLabReaPur(labReaPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

}
