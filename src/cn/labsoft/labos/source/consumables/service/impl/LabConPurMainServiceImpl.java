package cn.labsoft.labos.source.consumables.service.impl;

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
import cn.labsoft.labos.source.consumables.dao.ILabConPurDetailDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConPurMainDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConsumablesDAO;
import cn.labsoft.labos.source.consumables.entity.LabConPurDetail;
import cn.labsoft.labos.source.consumables.entity.LabConPurMain;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;
import cn.labsoft.labos.source.consumables.service.ILabConPurMainService;
import cn.labsoft.labos.source.consumables.vo.LabConPurDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConPurMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labConPurMainService")
public class LabConPurMainServiceImpl extends BaseService implements
		ILabConPurMainService {
	private ILabConPurMainDAO labConPurMainDAO;
	private ILabConPurDetailDAO labConPurDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabConsumablesDAO labConsumablesDAO;
    @Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
    @Resource
	public void setLabConsumablesDAO(ILabConsumablesDAO labConsumablesDAO) {
		this.labConsumablesDAO = labConsumablesDAO;
	}
    @Resource
	public void setLabConPurDetailDAO(ILabConPurDetailDAO labConPurDetailDAO) {
		this.labConPurDetailDAO = labConPurDetailDAO;
	}
    @Resource
	public void setLabConPurMainDAO(ILabConPurMainDAO labConPurMainDAO) {
		this.labConPurMainDAO = labConPurMainDAO;
	}

	@Override
	public LabConPurMainVo addLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabConPurMain labConPurMain = new LabConPurMain();
		BeanUtils.copyProperties(labConPurMainVo, labConPurMain,
				new String[] { "labConPurDetailVoList" });
		labConPurMain.setApplicant(labConPurMainVo.getApplicant());
		labConPurMain.setReceiptno(labConPurMainVo.getReceiptno());
		labConPurMain.setRemark(labConPurMainVo.getRemark());
		labConPurMain.setCreateUserId(sessionContainer.getUserId());
		labConPurMain.setIsDel(Constants_Source.N);
		labConPurMain.setType("0");
		labConPurMainDAO.addLabConPur(labConPurMain);

		List<LabConPurDetailVo> labConPurDetailVoList = labConPurMainVo
				.getLabConPurDetailVoList();
		if (null != labConPurDetailVoList && labConPurDetailVoList.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : labConPurDetailVoList) {
				if (null == labConPurDetailVo
						|| labConPurDetailVo.getConsumablesId() == null)
					continue;
				LabConPurDetail labConPurDetail = new LabConPurDetail();
				labConPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labConPurDetailVo.getNum())) {
					labConPurDetail.setNum(labConPurDetailVo.getNum());
				} else {
					labConPurDetail.setNum(Double.valueOf(0));
				}
				LabConsumables labConsumables = (LabConsumables) labConPurMainDAO
						.findById(LabConsumables.class, labConPurDetailVo
								.getConsumablesId());
				labConPurDetail.setConsumables(labConsumables);
				labConPurDetail.setMain(labConPurMain);
				if (null != labConPurDetailVo.getRemark()
						&& !"".equals(labConPurDetailVo.getRemark())) {
					labConPurDetail.setRemark(labConPurDetailVo.getRemark());
				}
				labConPurDetailDAO.addLabConPurDetail(labConPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labConPurMain
				.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer().getFunId(),
				labConPurMainVo.getAuditMessage(), labConPurMainVo
						.getAuditResult());
		if (null != ins) {
			labConPurMain.setProcessIns(ins);
			labConPurMainDAO.updateLabConPur(labConPurMain);
		} else {
			throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
		}
		String audit = labConPurMainVo.getAuditResult();
		BeanUtils.copyProperties(labConPurMain, labConPurMainVo, new String []{""});
		labConPurMainVo.setAuditResult(audit);
		return labConPurMainVo;
	}

	@Override
	public LabConPurMainVo getLabConPurMainById(String mainId)
			throws GlobalException {
		LabConPurMainVo vo = new LabConPurMainVo();
		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, mainId);
		BeanUtils.copyProperties(labConPurMain, vo,
				new String[] { "labConPurchaseDetail" });
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabConPurMainPR(LabConPurMainVo labConPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabConPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labConPurMainVo.getApplicant()
				&& !"".equals(labConPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labConPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labConPurMainVo.getReceiptno()
				&& !"".equals(labConPurMainVo.getReceiptno())) {
			hql += "AND receiptno LIKE '%" + labConPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labConPurMainVo.getStartDate()
				&& !"".equals(labConPurMainVo.getStartDate())) {
			hql += " AND  createTime >= '" + labConPurMainVo.getStartDate()
					+ "'";
		}
		if (null != labConPurMainVo.getEndDate()
				&& !"".equals(labConPurMainVo.getEndDate())) {
			hql += " AND  createTime <= '" + labConPurMainVo.getEndDate() + "'";
		}
		if (null != labConPurMainVo.getStatus()
				&& !"".equals(labConPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_CONS+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labConPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		if (null != labConPurMainVo.getStatus()
				&& !"".equals(labConPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_CONS+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labConPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		if (null != labConPurMainVo.getReceiptno()
				&& !"".equals(labConPurMainVo.getReceiptno())) {
			hql += " AND receiptno like '%" + labConPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labConPurMainDAO.getPageResult(hql, pageResult);
		List<LabConPurMain> poList = pageResult.getResultList();
		List<LabConPurMainVo> voList = new ArrayList<LabConPurMainVo>();
		if (poList.size() > 0) {
			for (LabConPurMain po : poList) {
				LabConPurMainVo vo = new LabConPurMainVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labConPurchaseDetail" });
				if (null != po.getProcessIns()) {
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
	public String getLabConPurMainMaxReceiptNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM  LabConPurMain WHERE isDel='" + Constants_Source.N
				+ "' AND  receiptno LIKE '" + year_month_day + "%'";
		List<LabConPurMain> labConPurMainList = labConPurMainDAO.find(hql);
		String billId = year_month_day;
		if (labConPurMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabConPurMain main : labConPurMainList) {
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
	public void deleteLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException {

		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());

		String hql = " FROM LabConPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labConPurMain.getId() + "'";
		List<LabConPurDetail> polist = labConPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labConPurDetailDAO.updateLabConPurDetail(polist.get(i));
			}
		}
		labConPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labConPurMainVo
				.getId());
		try {
			labConPurMainDAO.updateLabConPur(labConPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabConPurMainVo updateLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());
		labConPurMain.setApplicant(labConPurMainVo.getApplicant());
		labConPurMain.setCreateTime(labConPurMainVo.getCreateTime());
		labConPurMain.setReceiptno(labConPurMainVo.getReceiptno());
		labConPurMain.setRemark(labConPurMainVo.getRemark());
		labConPurMainDAO.updateLabConPur(labConPurMain);
		// 删除
		List<LabConPurDetailVo> list = getLabConPurDetailByMainId(labConPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : list) {
				deleteLabConPurById(labConPurDetailVo.getId());
			}
		}
		// 添加
		List<LabConPurDetailVo> labConPurDetailVoList = labConPurMainVo
				.getLabConPurDetailVoList();
		if (null != labConPurDetailVoList && labConPurDetailVoList.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : labConPurDetailVoList) {
				if (labConPurDetailVo == null
						|| labConPurDetailVo.getConsumablesId() == null)
					continue;
				LabConPurDetail labConPurDetail = new LabConPurDetail();
				if (!StrUtils.isNull(labConPurDetailVo.getNum())) {
					labConPurDetail.setNum(labConPurDetailVo.getNum());
				} else {
					labConPurDetail.setNum(Double.valueOf(0));
				}
				LabConsumables labConsumables = (LabConsumables) labConPurMainDAO
						.findById(LabConsumables.class, labConPurDetailVo
								.getConsumablesId());
				labConPurDetail.setConsumables(labConsumables);
				labConPurDetail.setMain(labConPurMain);
				if (null != labConPurDetailVo.getRemark()
						&& !"".equals(labConPurDetailVo.getRemark())) {
					labConPurDetail.setRemark(labConPurDetailVo.getRemark());
				}
				labConPurDetailDAO.addLabConPurDetail(labConPurDetail);
			}
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labConPurMain.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer()
							.getFunId(), labConPurMainVo.getAuditMessage(),
					labConPurMainVo.getAuditResult());
			if (ins == null) {
				throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
			}
		}
		return labConPurMainVo;
	}

	@Override
	public List<LabConPurDetailVo> getLabConPurDetailList(
			LabConPurMainVo labConPurMainVo) throws GlobalException {
		List<LabConPurDetailVo> volist = new ArrayList<LabConPurDetailVo>();
		if (null != labConPurMainVo.getConsumablesId()
				&& !"".equals(labConPurMainVo.getConsumablesId())) {
			String[] ids = labConPurMainVo.getConsumablesId().split(",");
			HashSet<String> hashset = new HashSet<String>();
			for (String id : ids) {
				if(!StrUtils.isBlankOrNull(id)){
					hashset.add(id);
				}
			}
			Iterator<String> iterator=hashset.iterator();
			while(iterator.hasNext()) {
					LabConPurDetailVo vo = new LabConPurDetailVo();
					LabConsumables labConsumables = (LabConsumables) labConPurMainDAO.findById(LabConsumables.class, iterator.next());
					if (labConsumables != null) {
						vo.setConsumablesId(labConsumables.getId());
						vo.setConsumablesName(labConsumables.getName());
						vo.setSize(labConsumables.getSize());
						vo.setUnit(labConsumables.getUnit());
						vo.setAmount(labConsumables.getAmount());
						volist.add(vo);
					}
			}
		}
		return volist;
	}

	@Override
	public void addLabConPurDetail(
			List<LabConPurDetailVo> labConPurDetailVoList, String id)
			throws GlobalException {
		LabConPurDetail labConPurDetail = new LabConPurDetail();
		for (int i = 0; i < labConPurDetailVoList.size(); i++) {
			BeanUtils.copyProperties(labConPurDetailVoList.get(i),
					labConPurDetail, new String[] { "" });
			if (null != labConPurDetailVoList.get(i).getNum()
					&& !"".equals(labConPurDetailVoList.get(i).getNum())) {
				labConPurDetail.setNum(labConPurDetailVoList.get(i).getNum());
			}
			if (null != id && !"".equals(id)) {
				LabConPurMain main = (LabConPurMain) labConPurDetailDAO
						.findById(LabConPurMain.class, id);
				labConPurDetail.setMain(main);
			}
			if (null != labConPurDetailVoList.get(i).getConsumablesId()
					&& !"".equals(labConPurDetailVoList.get(i)
							.getConsumablesId())) {
				LabConsumables consumables = (LabConsumables) labConPurDetailDAO
						.findById(LabConsumables.class, labConPurDetailVoList
								.get(i).getConsumablesId());
				labConPurDetail.setConsumables(consumables);
			}
			if (null != labConPurDetailVoList.get(i).getRemark()
					&& !"".equals(labConPurDetailVoList.get(i).getRemark())) {
				labConPurDetail.setRemark(labConPurDetailVoList.get(i)
						.getRemark());
			}
			try {
				labConPurDetailDAO.addLabConPurDetail(labConPurDetail);
			} catch (RuntimeException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabConPurDetailVo> getLabConPurDetailByMainId(String Id)
			throws GlobalException {
		String hql = " FROM LabConPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + Id + "'";
		List<LabConPurDetail> polist = labConPurDetailDAO.find(hql);
		List<LabConPurDetailVo> volist = new ArrayList<LabConPurDetailVo>();
		if (polist.size() > 0) {
			for (LabConPurDetail po : polist) {
				LabConPurDetailVo vo = new LabConPurDetailVo();
				BeanUtils.copyProperties(po, vo, new String[] { "main",
						"consumables" });
				if (null != po.getConsumables()
						&& !"".equals(po.getConsumables())) {
					vo.setConsumablesName(po.getConsumables().getName());
					vo.setConsumablesId(po.getConsumables().getId());
					vo.setUnit(po.getConsumables().getUnit());
					vo.setSize(po.getConsumables().getSize());
					vo.setAmount(po.getConsumables().getAmount());
					vo.setRemark(po.getRemark());
					vo.setSpecifications(po.getConsumables().getSize());
					vo.setNum(po.getNum());
				}
				volist.add(vo);
			}
		}
		return volist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabConPurDetailVo> getLabConPurDetailList()
			throws GlobalException {
		String hql = "FROM LabConsumables WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND amount<=safeAmount";
		List<LabConsumables> poList = labConPurDetailDAO.find(hql);
		List<LabConPurDetailVo> voList = new ArrayList<LabConPurDetailVo>();
		if (poList.size() > 0) {
			for (LabConsumables po : poList) {
				LabConPurDetailVo vo = new LabConPurDetailVo();
				vo.setConsumablesName(po.getName());
				vo.setConsumablesId(po.getId());
				vo.setUnit(po.getUnit());
				vo.setSize(po.getSize());
				vo.setAmount(po.getAmount());
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public boolean deleteLabConPurById(String id) throws GlobalException {
		LabConPurDetail labConPurDetail = (LabConPurDetail) labConPurDetailDAO
				.findById(LabConPurDetail.class, id);
		labConPurDetail.setIsDel(Constants_Source.Y);
		boolean flag = labConPurDetailDAO
				.updateLabConPurDetail(labConPurDetail);
		return flag;
	}

	@Override
	public boolean updateLabConPur4Audit(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());
		try {
			labConPurMainDAO.updateLabConPur(labConPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labConPurMain.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer()
							.getFunId(), labConPurMainVo.getAuditMessage(),
					labConPurMainVo.getAuditResult());
			if (ins == null) {
				throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean updateLabConPur4Approve(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());
		try {
			labConPurMainDAO.updateLabConPur(labConPurMain);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
					labConPurMain.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer()
							.getFunId(), labConPurMainVo.getAuditMessage(),
					labConPurMainVo.getAuditResult());
			if (null == ins) {
				throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
			}
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabConPurDetailVo addLabConPurDetail(
			LabConPurDetailVo labConPurDetailVo, String id)
			throws GlobalException {
		LabConPurDetail labConPurDetail = new LabConPurDetail();
		BeanUtils.copyProperties(labConPurDetailVo, labConPurDetail,
				new String[] { "" });
		if (null != labConPurDetailVo.getNum()
				&& !"".equals(labConPurDetailVo.getNum())) {
			labConPurDetail.setNum(labConPurDetailVo.getNum());
		}
		if (null != id && !"".equals(id)) {
			LabConPurMain main = (LabConPurMain) labConPurDetailDAO.findById(
					LabConPurMain.class, id);
			labConPurDetail.setMain(main);
		}
		if (null != labConPurDetailVo.getConsumablesId()
				&& !"".equals(labConPurDetailVo.getConsumablesId())) {
			LabConsumables consumables = (LabConsumables) labConPurDetailDAO
					.findById(LabConsumables.class, labConPurDetailVo
							.getConsumablesId());
			labConPurDetail.setConsumables(consumables);
		}
		if (null != labConPurDetailVo.getRemark()
				&& !"".equals(labConPurDetailVo.getRemark())) {
			labConPurDetail.setRemark(labConPurDetailVo.getRemark());
		}
		try {
			labConPurDetail = labConPurDetailDAO
					.addLabConPurDetail(labConPurDetail);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		BeanUtils.copyProperties(labConPurDetail, labConPurDetailVo,
				new String[] { "" });
		if (null != labConPurDetail.getConsumables()) {
			labConPurDetailVo.setConsumablesId(labConPurDetail.getConsumables()
					.getId());
			labConPurDetailVo.setConsumablesName(labConPurDetail
					.getConsumables().getName());
		}
		return labConPurDetailVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabConPurInsPR(LabConPurMainVo labConPurMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabConPurMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labConPurMainVo.getApplicant()
				&& !"".equals(labConPurMainVo.getApplicant())) {
			hql += "AND applicant LIKE '%" + labConPurMainVo.getApplicant().trim()
					+ "%'";
		}
		if (null != labConPurMainVo.getReceiptno()
				&& !"".equals(labConPurMainVo.getReceiptno())) {
			hql += "AND receiptno LIKE '%" + labConPurMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labConPurMainVo.getStartDate()
				&& !"".equals(labConPurMainVo.getStartDate())) {
			hql += " AND  inTime >= '" + labConPurMainVo.getStartDate() + "'";
		}
		if (null != labConPurMainVo.getEndDate()
				&& !"".equals(labConPurMainVo.getEndDate())) {
			hql += " AND  inTime <= '" + labConPurMainVo.getEndDate() + "'";
		}
		if (null != labConPurMainVo.getCreateTime()
				&& !"".equals(labConPurMainVo.getCreateTime().trim())) {
			hql += " AND  cconteTime <= '" + labConPurMainVo.getCreateTime().trim()
					+ "'";
		}
		if (null != labConPurMainVo.getStatus()
				&& !"".equals(labConPurMainVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busType='"+Constants_Source.WF_CODE_CONS+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labConPurMainVo.getStatus()+"'";
			hql+=" AND id in(" + subhql+ ")";
		}
		hql += " ORDER BY processIns.num ASC";
		pageResult = labConPurMainDAO.getPageResult(hql, pageResult);
		List<LabConPurMain> poList = pageResult.getResultList();
		List<LabConPurMainVo> voList = new ArrayList<LabConPurMainVo>();
		if (poList.size() > 0) {
			for (LabConPurMain po : poList) {
				LabConPurMainVo vo = new LabConPurMainVo();
				BeanUtils.copyProperties(po, vo,
						new String[] { "labConPurchaseDetail" });
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
	public void updateLabConPur4Ins(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);

		if (null == labConPurMainVo.getId()
				|| "".equals(labConPurMainVo.getId())) {
			labConPurMainVo.setInPerson(sessionContainer.getUserName());
			labConPurMainVo.setInTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			labConPurMainVo = this.addLabConPurIns(labConPurMainVo);
		}
		labConPurMainVo.setInPerson(sessionContainer.getUserName());
		labConPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		this.updateLabConInMain(labConPurMainVo);
		List<LabConPurDetailVo> list = getLabConPurDetailByMainId(labConPurMainVo
				.getId());
		if (null != list && list.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : list) {
				LabConPurDetail po = (LabConPurDetail) labConPurMainDAO
						.findById(LabConPurDetail.class, labConPurDetailVo
								.getId());
				LabConsumables labConsumables = (LabConsumables) labConPurMainDAO
						.findById(LabConsumables.class, po.getConsumables()
								.getId());
				labConsumables.setAmount(labConsumables.getAmount()
						+ po.getNum());
				labConsumablesDAO.updateLabConsumables(labConsumables);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
				labConPurMainVo.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer()
						.getFunId(), labConPurMainVo.getAuditMessage(),
				labConPurMainVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
		}
	}

	@Override
	public LabConPurMainVo addLabConPurIns(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabConPurMain labConPurMain = new LabConPurMain();
		BeanUtils.copyProperties(labConPurMainVo, labConPurMain,
				new String[] { "labConPurDetailVoList" });
		labConPurMain.setReceiptno(labConPurMainVo.getReceiptno());

		labConPurMain.setCreateUserId(sessionContainer.getUserId());
		labConPurMain.setCreateTime(labConPurMainVo.getCreateTime());
		if (null != labConPurMainVo.getInPerson()
				&& !"".equals(labConPurMainVo.getInPerson())) {
			labConPurMain.setInPerson(labConPurMainVo.getInPerson());
		}
		if (null != labConPurMainVo.getInTime()
				&& !"".equals(labConPurMainVo.getInTime())) {
			labConPurMain.setInTime(labConPurMainVo.getInTime());
		}
		labConPurMain.setIsDel(Constants_Source.N);
		labConPurMain.setType("1");
		labConPurMainDAO.addLabConPur(labConPurMain);
		labConPurMainVo.setId(labConPurMain.getId());

		List<LabConPurDetailVo> labConPurDetailVoList = labConPurMainVo
				.getLabConPurDetailVoList();
		if (null != labConPurDetailVoList && labConPurDetailVoList.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : labConPurDetailVoList) {
				if (labConPurDetailVo == null
						|| labConPurDetailVo.getConsumablesId() == null)
					continue;
				LabConPurDetail labConPurDetail = new LabConPurDetail();
				labConPurDetail.setIsDel(Constants_Source.N);
				if (!StrUtils.isNull(labConPurDetailVo.getNum())) {
					labConPurDetail.setNum(labConPurDetailVo.getNum());
				} else {
					labConPurDetail.setNum(Double.valueOf(0));
				}
				LabConsumables labConsumables = (LabConsumables) labConPurMainDAO
						.findById(LabConsumables.class, labConPurDetailVo
								.getConsumablesId());
				labConPurDetail.setConsumables(labConsumables);
				labConPurDetail.setMain(labConPurMain);
				if (null != labConPurDetailVo.getRemark()
						&& !"".equals(labConPurDetailVo.getRemark())) {
					labConPurDetail.setRemark(labConPurDetailVo.getRemark());
				}
				labConPurDetailDAO.addLabConPurDetail(labConPurDetail);
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labConPurMain
				.getId(),Constants_Source.WF_CODE_CONS, getSessionContainer().getFunId(),
				labConPurMainVo.getAuditMessage(), labConPurMainVo
						.getAuditResult());
		if (null != ins) {
			labConPurMain.setProcessIns(ins);
			labConPurMainDAO.updateLabConPur(labConPurMain);
		} else {
			throw new GlobalException("LabConPurMainServiceImpl...流程实例化出错！");
		}
		return labConPurMainVo;
	}

	@Override
	public LabConPurMainVo updateLabConInMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException {
		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labConPurMain.setCreateUserId(sessionContainer.getUserId());
		labConPurMain.setCreateTime(labConPurMainVo.getCreateTime());
		labConPurMain.setReceiptno(labConPurMainVo.getReceiptno());
		if (null != labConPurMainVo.getInPerson()
				&& !"".equals(labConPurMainVo.getInPerson())) {
			labConPurMain.setInPerson(labConPurMainVo.getInPerson());
		}
		if (null != labConPurMainVo.getInTime()
				&& !"".equals(labConPurMainVo.getInTime())) {
			labConPurMain.setInTime(labConPurMainVo.getInTime());
		}
		labConPurMainDAO.updateLabConPur(labConPurMain);
		// 删除
		List<LabConPurDetailVo> list = getLabConPurDetailByMainId(labConPurMain
				.getId());
		if (null != list && list.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : list) {
				deleteLabConPurById(labConPurDetailVo.getId());
			}
		}
		// 添加
		List<LabConPurDetailVo> labConPurDetailVoList = labConPurMainVo
				.getLabConPurDetailVoList();
		if (null != labConPurDetailVoList && labConPurDetailVoList.size() > 0) {
			for (LabConPurDetailVo labConPurDetailVo : labConPurDetailVoList) {
				if (labConPurDetailVo == null
						|| labConPurDetailVo.getConsumablesId() == null)
					continue;
				LabConPurDetail labConPurDetail = new LabConPurDetail();
				if (!StrUtils.isNull(labConPurDetailVo.getNum())) {
					labConPurDetail.setNum(labConPurDetailVo.getNum());
				} else {
					labConPurDetail.setNum(Double.valueOf(0));
				}
				LabConsumables labConsumables = (LabConsumables) labConPurMainDAO
						.findById(LabConsumables.class, labConPurDetailVo
								.getConsumablesId());
				labConPurDetail.setConsumables(labConsumables);
				labConPurDetail.setMain(labConPurMain);
				if (null != labConPurDetailVo.getRemark()
						&& !"".equals(labConPurDetailVo.getRemark())) {
					labConPurDetail.setRemark(labConPurDetailVo.getRemark());
				}
				labConPurDetailDAO.addLabConPurDetail(labConPurDetail);
			}
		}
		return labConPurMainVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteLabConPurIns(LabConPurMainVo labConPurMainVo)
			throws GlobalException {

		LabConPurMain labConPurMain = (LabConPurMain) labConPurMainDAO
				.findById(LabConPurMain.class, labConPurMainVo.getId());

		String hql = " FROM LabConPurDetail WHERE isDel='" + Constants_Source.N
				+ "' AND main.id='" + labConPurMain.getId() + "'";
		List<LabConPurDetail> polist = labConPurDetailDAO.find(hql);
		if (null != polist && polist.size() > 0) {
			for (int i = 0; i < polist.size(); i++) {
				polist.get(i).setIsDel(Constants_Source.Y);
				labConPurDetailDAO.updateLabConPurDetail(polist.get(i));
			}
		}
		labConPurMain.setIsDel(Constants_Source.Y);
		labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labConPurMainVo.getId());
		try {
			labConPurMainDAO.updateLabConPur(labConPurMain);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

}
