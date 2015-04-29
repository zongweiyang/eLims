package cn.labsoft.labos.source.reference.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.reference.dao.ILabRefCheckDetailDAO;
import cn.labsoft.labos.source.reference.dao.ILabRefCheckMainDAO;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabRefCheckDetail;
import cn.labsoft.labos.source.reference.entity.LabRefCheckMain;
import cn.labsoft.labos.source.reference.entity.LabReference;
import cn.labsoft.labos.source.reference.service.ILabRefCheckMainService;
import cn.labsoft.labos.source.reference.vo.LabRefCheckDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefCheckMainVo;
import cn.labsoft.labos.utils.DateUtils;

@Service("labRefCheckMainService")
public class LabRefCheckMainServiceImpl extends BaseService implements
		ILabRefCheckMainService {

	private ILabRefCheckMainDAO labRefCheckMainDAO;
	private ILabRefCheckDetailDAO labRefCheckDetailDAO;
	private ILabReferenceDAO labReferenceDAO;
	
	@Resource
	public void setLabRefCheckMainDAO(ILabRefCheckMainDAO labRefCheckMainDAO) {
		this.labRefCheckMainDAO = labRefCheckMainDAO;
	}
	@Resource
	public void setLabRefCheckDetailDAO(ILabRefCheckDetailDAO labRefCheckDetailDAO) {
		this.labRefCheckDetailDAO = labRefCheckDetailDAO;
	}
	@Resource
	public void setLabReferenceDAO(ILabReferenceDAO labReferenceDAO) {
		this.labReferenceDAO = labReferenceDAO;
	}

	@Override
	public LabRefCheckMainVo addLabRefCheckMain(
			LabRefCheckMainVo labRefCheckMainVo) throws GlobalException {
		LabRefCheckMain labRefCheckMain = VoToPo(labRefCheckMainVo);
		labRefCheckMain.setIsDel(Constants_Source.N);
		try {
			labRefCheckMain.setChecktime(DateUtils.parse(labRefCheckMainVo
					.getChecktime(), DateUtils.formatStr_yyyyMMdd));
		} catch (ParseException e) {
			Log4J.error("时间转换异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		labRefCheckMainDAO.addLabRefCheckMain(labRefCheckMain);
		List<LabRefCheckDetailVo> labRefCheackDetailVoList = labRefCheckMainVo
				.getLabRefCheckDetailVoList();
		if (labRefCheackDetailVoList.size() > 0) {
			for (LabRefCheckDetailVo vo : labRefCheackDetailVoList) {
				LabRefCheckDetail labRefCheckDetail = new LabRefCheckDetail();
				LabReference labReference = (LabReference) labReferenceDAO
						.findById(LabReference.class, vo.getLabReferenceId());
				labReference.setAmount(Double.valueOf(vo.getAmount()));
				labReferenceDAO.updateLabReference(labReference);
				labRefCheckDetail.setLabReference(labReference);
				labRefCheckDetail.setLabRefCheckMain(labRefCheckMain);
				labRefCheckDetail.setAmount(Double.valueOf(vo.getAmount()));
				labRefCheckDetail.setRemark(vo.getRemark());
				labRefCheckDetail.setIsDel(Constants_Source.N);
				labRefCheckDetail.setLastAmount(Double.valueOf(vo
						.getLastAmount()));
				labRefCheckDetail.setThisInAmount(Double.valueOf(vo
						.getThisInAmount()));
				labRefCheckDetail.setThisOutAmount(Double.valueOf(vo
						.getThisOutAmount()));
				labRefCheckDetailDAO.addLabRefCheckDetail(labRefCheckDetail);
			}
		}
		return labRefCheckMainVo;
	}

	@Override
	public LabRefCheckMainVo getLabRefCheckMain(
			LabRefCheckMainVo labRefCheckMainVo) throws GlobalException {
		LabRefCheckMain labRefCheckMain = (LabRefCheckMain) labRefCheckMainDAO
				.findById(LabRefCheckMain.class, labRefCheckMainVo.getId());
		labRefCheckMainVo = PoToVo(labRefCheckMain);
		List<LabRefCheckDetail> labRefCheckDetaiList = labRefCheckDetailDAO
				.find("FROM LabRefCheckDetail WHERE isDel='" + Constants_Source.N
						+ "' AND labRefCheckMain.id='"
						+ labRefCheckMainVo.getId() + "'");
		List<LabRefCheckDetailVo> labRefCheckDetailVoList = new ArrayList<LabRefCheckDetailVo>();
		if (labRefCheckDetaiList.size() > 0) {
			for (LabRefCheckDetail po : labRefCheckDetaiList) {
				LabRefCheckDetailVo vo = new LabRefCheckDetailVo();
				vo.setLabRefCheckMainId(po.getLabRefCheckMain().getId());
				vo.setLabRefCheckMainName(po.getLabRefCheckMain().getName());
				vo.setLabReferenceId(po.getLabReference().getId());
				LabReference labReference = (LabReference) labReferenceDAO
						.findById(LabReference.class, po.getLabReference()
								.getId());
				vo.setLabReferenceName(labReference.getName());
				vo.setReferenceSize(labReference.getSize());
				vo.setReferencepurity(labReference.getPurity());
				vo.setReferenceUnit(labReference.getUnit());
				vo.setRemark(po.getRemark());
				if (null == po.getAmount()) {
					vo.setAmount("0");
				} else {
					vo.setAmount(String.valueOf(po.getAmount()));
				}
				if (null == po.getLastAmount()) {
					vo.setLastAmount("0");
				} else {
					vo.setLastAmount(String.valueOf(po.getLastAmount()));
				}
				if (null == po.getThisInAmount()) {
					vo.setThisInAmount("0");
				} else {
					vo.setThisInAmount(po.getThisInAmount().toString());
				}
				if (null == po.getThisOutAmount()) {
					vo.setThisOutAmount("0");
				} else {
					vo.setThisOutAmount(po.getThisOutAmount().toString());
				}
				labRefCheckDetailVoList.add(vo);
			}
		}
		labRefCheckMainVo.setLabRefCheckDetailVoList(labRefCheckDetailVoList);
		return labRefCheckMainVo;
	}

	@Override
	public PageResult getLabRefCheckMainPR(LabRefCheckMainVo labRefCheckMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabRefCheckMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labRefCheckMainVo.getChecker()
				&& !"".equals(labRefCheckMainVo.getChecker())) {
			hql += " AND checker LIKE '%" + labRefCheckMainVo.getChecker().trim()
					+ "%'";
		}
		if (null != labRefCheckMainVo.getName()
				&& !"".equals(labRefCheckMainVo.getName())) {
			hql += " AND name LIKE '%" + labRefCheckMainVo.getName().trim() + "%'";
		}
		if (null != labRefCheckMainVo.getCheckno()
				&& !"".equals(labRefCheckMainVo.getCheckno())) {
			hql += " AND checkno LIKE '%" + labRefCheckMainVo.getCheckno().trim()
					+ "%'";
		}
		// hql += " order by checktime desc";
		pageResult = labRefCheckMainDAO.getPageResult(hql, pageResult);
		List<LabRefCheckMainVo> labRefChekMainVoList = new ArrayList<LabRefCheckMainVo>();
		List<LabRefCheckMain> labRefCheckMainList = pageResult.getResultList();
		if (labRefCheckMainList.size() > 0) {
			for (LabRefCheckMain po : labRefCheckMainList) {
				LabRefCheckMainVo vo = PoToVo(po);
				labRefChekMainVoList.add(vo);
			}
		}
		return pageResult;
	}

	@Override
	public LabRefCheckMainVo getNewLabRefCheckMainVo() throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabRefCheckMainVo labRefCheckMainVo = new LabRefCheckMainVo();
		labRefCheckMainVo.setChecktime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		String checkno = getRefCheckstoreMainMaxCheckNo();
		labRefCheckMainVo.setCheckno(checkno);
		// labRefCheckMainVo.setChecker(sessionContainer.getUserName());
		return labRefCheckMainVo;
	}

	public LabRefCheckMainVo PoToVo(LabRefCheckMain po) throws GlobalException {
		LabRefCheckMainVo vo = new LabRefCheckMainVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "checktime" });
		} catch (Exception e) {
			Log4J.error("po转vo失败----" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		if (null != po.getChecktime() && !"".equals(po.getChecktime())) {
			vo.setChecktime(DateUtils.format(po.getChecktime(),
					DateUtils.formatStr_yyyyMMdd));
		}
		return vo;
	}

	public LabRefCheckMain VoToPo(LabRefCheckMainVo vo) throws GlobalException {
		LabRefCheckMain po = new LabRefCheckMain();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "checktime" });
		} catch (Exception e) {
			Log4J.error("Vo转Po失败----" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		if (null != vo.getChecktime() && !"".equals(vo.getChecktime())) {
			try {
				po.setChecktime(DateUtils.parse(vo.getChecktime(),
						DateUtils.formatStr_yyyyMMdd));
			} catch (ParseException e) {
				Log4J.error("时间类型转换异常---" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return po;
	}

	@Override
	public String getRefCheckstoreMainMaxCheckNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabRefCheckMain  WHERE 1=1 AND isDel='N' AND checkno LIKE '"
				+ year_month_day + "%'";

		List<LabRefCheckMain> refCheckstoreMainList = labRefCheckMainDAO
				.find(hql);
		String billId = year_month_day;
		if (refCheckstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabRefCheckMain main : refCheckstoreMainList) {
				String recordId = main.getCheckno();
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
	public List<LabRefCheckDetailVo> getAllLabRefCheckDetailVoList() throws GlobalException {
		List<LabRefCheckDetailVo> labRefCheckDetailVoList = new ArrayList();
		List tempList = new ArrayList();
		LabRefCheckMain labRefCheckMain = new LabRefCheckMain();
		try {
			// 得到所有标准品
			String hql = "";
			String amount = "";
			hql = "FROM LabReference WHERE isDel='" + Constants_Source.N + "'";
			List<LabReference> referenceList = labRefCheckDetailDAO.find(hql);

			// 得到上次盘库时间
			Date date = new Date();
			try {
				date = DateUtils.parse("2000-01-01 00:00:00",
						DateUtils.formatStr_yyyyMMddHHmmss);
			} catch (ParseException e) {
				Log4J.error("时间格式化错误---" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
			Date maxTime = new Date(date.getTime());
			hql = "SELECT MAX(checktime) FROM LabRefCheckMain WHERE  1=1 and isDel='"
					+ Constants_Source.N + "'";
			tempList = labRefCheckDetailDAO.find(hql);
			if (null != tempList && !"[null]".equals(tempList)
					&& 0 < tempList.size() && null != tempList.get(0)) {
				maxTime = (Date) tempList.get(0);

				// 得到上次盘库对象
				hql = "FROM LabRefCheckMain  WHERE 1=1 AND isDel='"
						+ Constants_Source.N + "' AND checktime='" + maxTime + "'";
				tempList = labRefCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					labRefCheckMain = (LabRefCheckMain) tempList.get(0);
				}
			}

			for (LabReference refReference : referenceList) {
				// 创建一条盘点详情
				LabRefCheckDetailVo labRefCheckDetailVo = new LabRefCheckDetailVo();
				labRefCheckDetailVo.setLabReferenceId(refReference.getId());
				labRefCheckDetailVo.setLabReferenceName(refReference.getName());
				labRefCheckDetailVo.setReferenceSize(refReference.getSize());
				labRefCheckDetailVo.setReferenceUnit(refReference.getUnit());
				labRefCheckDetailVo
						.setReferencepurity(refReference.getPurity());
				labRefCheckDetailVo.setThisAmount(refReference.getAmount()
						.toString());
				// 得到一个标准品上次盘点后入库数目
				hql = "SELECT SUM(num) FROM LabRefPurDetail WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND main.inTime>='"
						+ maxTime
						+ "' AND reference.id='"
						+ refReference.getId()
						+ "' AND main.processIns.status='"
						+ LabWfConstant.BUS_PROCESS_END + "'";
				tempList = labRefCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labRefCheckDetailVo.setThisInAmount(amount);
				// 得到一个标准品上次盘点后出库数目
				hql = "SELECT SUM(amount) FROM LabRefOutDetail  WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND outDate>='"
						+ maxTime
						+ "' AND reference.id='" + refReference.getId() + "'";
				tempList = labRefCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labRefCheckDetailVo.setThisOutAmount(amount);
				// 得到一个标准品上次盘点的数目
				hql = "SELECT amount FROM LabRefCheckDetail  WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND labRefCheckMain.id='"
						+ labRefCheckMain.getId()
						+ "' AND labReference.id='"
						+ refReference.getId() + "'";
				tempList = labRefCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labRefCheckDetailVo.setLastAmount(amount);

				// 计算一个标准品现在库中应该的数目
				labRefCheckDetailVo.setAmount(String.valueOf((Double
						.valueOf(labRefCheckDetailVo.getLastAmount())
						* 100
						+ Double.valueOf(labRefCheckDetailVo.getThisInAmount())
						* 100 - Double.valueOf(labRefCheckDetailVo
						.getThisOutAmount()) * 100) / 100));
				labRefCheckDetailVoList.add(labRefCheckDetailVo);
			}
		} catch (Exception e) {
			Log4J.error("盘库失败---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labRefCheckDetailVoList;
	}

}
