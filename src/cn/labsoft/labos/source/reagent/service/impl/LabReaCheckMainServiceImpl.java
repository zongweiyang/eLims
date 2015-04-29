package cn.labsoft.labos.source.reagent.service.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.reagent.dao.ILabReaCheckDetailDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReaCheckMainDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckDetail;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckMain;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
import cn.labsoft.labos.source.reagent.service.ILabReaCheckMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Service("labReaCheckMainService")
public class LabReaCheckMainServiceImpl extends BaseService implements
		ILabReaCheckMainService {

	private ILabReaCheckMainDAO labReaCheckMainDAO;
	private ILabReaCheckDetailDAO labReaCheckDetailDAO;
	private ILabReagentDAO labReagentDAO;
	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabReaCheckMainDAO(ILabReaCheckMainDAO labReaCheckMainDAO) {
		this.labReaCheckMainDAO = labReaCheckMainDAO;
	}
	@Resource
	public void setLabReaCheckDetailDAO(ILabReaCheckDetailDAO labReaCheckDetailDAO) {
		this.labReaCheckDetailDAO = labReaCheckDetailDAO;
	}
	@Resource
	public void setLabReagentDAO(ILabReagentDAO labReagentDAO) {
		this.labReagentDAO = labReagentDAO;
	}

	@Override
	public LabReaCheckMainVo addLabReaCheckMain(
			LabReaCheckMainVo labReaCheckMainVo) throws GlobalException {
		LabReaCheckMain labReaCheckMain = VoToPo(labReaCheckMainVo);
		labReaCheckMain.setIsDel(Constants_Source.N);
		try {
			labReaCheckMain.setChecktime(DateUtils.parse(labReaCheckMainVo
					.getChecktime(), DateUtils.formatStr_yyyyMMdd));
		} catch (ParseException e) {
			log.error("时间转换异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		labReaCheckMainDAO.addLabReaCheckMain(labReaCheckMain);
		List<LabReaCheckDetailVo> labReaCheackDetailVoList = labReaCheckMainVo
				.getLabReaCheckDetailVoList();
		if (null != labReaCheackDetailVoList
				&& labReaCheackDetailVoList.size() > 0) {
			for (LabReaCheckDetailVo vo : labReaCheackDetailVoList) {
				LabReaCheckDetail labReaCheckDetail = new LabReaCheckDetail();
				LabReagent labReagent = (LabReagent) labReagentDAO.findById(
						LabReagent.class, vo.getLabReagentId());
				labReagent.setAmount(Double.valueOf(vo.getAmount()));
				labReagentDAO.updateLabReagent(labReagent);
				labReaCheckDetail.setLabReagent(labReagent);
				labReaCheckDetail.setLabReaCheckMain(labReaCheckMain);
				labReaCheckDetail.setAmount(Double.valueOf(vo.getAmount()));
				labReaCheckDetail.setRemark(vo.getRemark());
				labReaCheckDetail.setIsDel(Constants_Source.N);
				labReaCheckDetail.setLastAmount(Double.valueOf(vo
						.getLastAmount()));
				labReaCheckDetail.setThisInAmount(Double.valueOf(vo
						.getThisInAmount()));
				labReaCheckDetail.setThisOutAmount(Double.valueOf(vo
						.getThisOutAmount()));
				labReaCheckDetailDAO.addLabReaCheckDetail(labReaCheckDetail);
			}
		}
		return labReaCheckMainVo;
	}

	@Override
	public LabReaCheckMainVo getLabReaCheckMain(
			LabReaCheckMainVo labReaCheckMainVo) throws GlobalException {
		LabReaCheckMain labReaCheckMain = (LabReaCheckMain) labReaCheckMainDAO
				.findById(LabReaCheckMain.class, labReaCheckMainVo.getId());
		labReaCheckMainVo = PoToVo(labReaCheckMain);
		List<LabReaCheckDetail> labReaCheckDetaiList = labReaCheckDetailDAO
				.find("FROM LabReaCheckDetail WHERE isDel='" + Constants_Source.N
						+ "' AND labReaCheckMain.id='"
						+ labReaCheckMainVo.getId() + "'");
		List<LabReaCheckDetailVo> labReaCheckDetailVoList = new ArrayList<LabReaCheckDetailVo>();
		if (null != labReaCheckDetaiList && labReaCheckDetaiList.size() > 0) {
			for (LabReaCheckDetail po : labReaCheckDetaiList) {
				LabReaCheckDetailVo vo = new LabReaCheckDetailVo();
				vo.setLabReaCheckMainId(po.getLabReaCheckMain().getId());
				vo.setLabReaCheckMainName(po.getLabReaCheckMain().getName());
				vo.setLabReagentId(po.getLabReagent().getId());
				LabReagent labReagent = (LabReagent) labReagentDAO.findById(
						LabReagent.class, po.getLabReagent().getId());
				vo.setLabReagentName(labReagent.getName());
				vo.setReagentSize(labReagent.getSize());
				vo.setReagentpurity(labReagent.getPurity());
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
				labReaCheckDetailVoList.add(vo);
			}
		}

		labReaCheckMainVo.setLabReaCheckDetailVoList(labReaCheckDetailVoList);
		return labReaCheckMainVo;
	}

	@Override
	public PageResult getLabReaCheckMainPR(LabReaCheckMainVo labReaCheckMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReaCheckMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labReaCheckMainVo.getChecker()
				&& !"".equals(labReaCheckMainVo.getChecker())) {
			hql += " AND checker LIKE '%" + labReaCheckMainVo.getChecker().trim()
					+ "%'";
		}
		if (null != labReaCheckMainVo.getName()
				&& !"".equals(labReaCheckMainVo.getName())) {
			hql += " AND name LIKE '%" + labReaCheckMainVo.getName().trim() + "'";
		}
		if (null != labReaCheckMainVo.getCheckno()
				&& !"".equals(labReaCheckMainVo.getCheckno())) {
			hql += " AND checkno LIKE '%" + labReaCheckMainVo.getCheckno().trim()
					+ "'";
		}
		// hql += " order by checktime desc";
		pageResult = labReaCheckMainDAO.getPageResult(hql, pageResult);
		List<LabReaCheckMainVo> labReaChekMainVoList = new ArrayList<LabReaCheckMainVo>();
		List<LabReaCheckMain> labReaCheckMainList = pageResult.getResultList();
		if (labReaCheckMainList.size() > 0) {
			for (LabReaCheckMain po : labReaCheckMainList) {
				LabReaCheckMainVo vo = PoToVo(po);
				labReaChekMainVoList.add(vo);
			}
		}
		return pageResult;
	}

	@Override
	public LabReaCheckMainVo getNewLabReaCheckMainVo() throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabReaCheckMainVo labReaCheckMainVo = new LabReaCheckMainVo();
		labReaCheckMainVo.setChecktime(DateUtils.format(new Date(),DateUtils.formatStr_yyyyMMdd));
		String checkno = getReaCheckstoreMainMaxCheckNo();
		labReaCheckMainVo.setCheckno(checkno);
		return labReaCheckMainVo;
	}

	public LabReaCheckMainVo PoToVo(LabReaCheckMain po) throws GlobalException {
		LabReaCheckMainVo vo = new LabReaCheckMainVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "checktime" });
		} catch (Exception e) {
			log.error("po转vo失败----" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		if (null != po.getChecktime() && !"".equals(po.getChecktime())) {
			vo.setChecktime(DateUtils.format(po.getChecktime(),
					DateUtils.formatStr_yyyyMMdd));
		}
		return vo;
	}

	public LabReaCheckMain VoToPo(LabReaCheckMainVo vo) throws GlobalException {
		LabReaCheckMain po = new LabReaCheckMain();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "checktime" });
		} catch (Exception e) {
			log.error("Vo转Po失败----" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		if (null != vo.getChecktime() && !"".equals(vo.getChecktime())) {
			try {
				po.setChecktime(DateUtils.parse(vo.getChecktime(),
						DateUtils.formatStr_yyyyMMdd));
			} catch (ParseException e) {
				log.error("时间类型转换异常---" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return po;
	}

	@Override
	public String getReaCheckstoreMainMaxCheckNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabReaCheckMain  WHERE 1=1 AND isDel='N' AND checkno LIKE '"
				+ year_month_day + "%'";

		List<LabReaCheckMain> reaCheckstoreMainList = labReaCheckMainDAO
				.find(hql);
		String billId = year_month_day;
		if (reaCheckstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabReaCheckMain main : reaCheckstoreMainList) {
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
	public List<LabReaCheckDetailVo> getAllLabReaCheckDetailVoList() throws GlobalException {
		List<LabReaCheckDetailVo> labReaCheckDetailVoList = new ArrayList();
		List tempList = new ArrayList();
		LabReaCheckMain labReaCheckMain = new LabReaCheckMain();
		try {
			// 得到所有试剂
			String hql = "";
			String amount = "";
			hql = "FROM LabReagent WHERE 1=1 AND isDel='"+ Constants_Source.N + "'";
			List<LabReagent> reagentList = labReaCheckDetailDAO.find(hql);
			
			// 得到上次盘库时间
			Date maxTime = new Date();
				// 得到上次盘库对象
				hql = "FROM LabReaCheckMain  WHERE 1=1 AND isDel='"+ Constants_Source.N + "' ORDER BY checktime DESC LIMIT 1";
				tempList = labReaCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					labReaCheckMain = (LabReaCheckMain) tempList.get(0);
				}
				if(null!=labReaCheckMain){
					maxTime=labReaCheckMain.getChecktime();
				}
			for (LabReagent reaReagent : reagentList) {
				// 创建一条盘点详情
				LabReaCheckDetailVo labReaCheckDetailVo = new LabReaCheckDetailVo();
				labReaCheckDetailVo.setLabReagentId(reaReagent.getId());
				labReaCheckDetailVo.setLabReagentName(reaReagent.getName());
				labReaCheckDetailVo.setReagentSize(reaReagent.getSize());
				labReaCheckDetailVo.setReagentpurity(reaReagent.getPurity());
				labReaCheckDetailVo.setThisAmount(reaReagent.getAmount()
						.toString());
				
				// 得到一个试剂上次盘点后入库数目
				hql = "SELECT SUM(num) FROM LabReaPurDetail WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND main.inTime>='"
						+ maxTime
						+ "' AND reagent.id='"
						+ reaReagent.getId()
						+ "' AND main.processIns.status='"
						+ LabWfConstant.BUS_PROCESS_END + "'";
				tempList = labReaCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				
				labReaCheckDetailVo.setThisInAmount(amount);
				// 得到一个试剂上次盘点后出库数目
				hql = "SELECT SUM(amount) FROM LabReaOutDetail  WHERE 1=1 AND isDel='"+ Constants_Source.N+ "'";
				if(!StrUtils.isBlankOrNull(DateUtils.format(maxTime))){
					hql +=" AND outDate>= '"+maxTime+"'";
				}
				if(!StrUtils.isBlankOrNull(reaReagent.getId())){
					hql +=" AND reagent.id='" + reaReagent.getId() + "'";
				}
				tempList = labReaCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labReaCheckDetailVo.setThisOutAmount(amount);
				// 得到一个试剂上次盘点的数目
				hql = "SELECT SUM(amount) FROM LabReaCheckDetail  WHERE 1=1 AND isDel='"+ Constants_Source.N+"'";
				if(!StrUtils.isBlankOrNull(labReaCheckMain.getId())){
					hql +=" AND labReaCheckMain.id='"+labReaCheckMain.getId()+"'";
				}
				if(!StrUtils.isBlankOrNull(reaReagent.getId())){
					hql +=" AND labReagent.id='"+reaReagent.getId()+"'";
				}
				tempList = labReaCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if (StrUtils.isBlankOrNull(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labReaCheckDetailVo.setLastAmount(amount);
				// 计算一个试剂现在库中应该的数目
				labReaCheckDetailVo.setAmount(String.valueOf((Double
						.valueOf(labReaCheckDetailVo.getLastAmount())
						* 100
						+ Double.valueOf(labReaCheckDetailVo.getThisInAmount())
						* 100 - Double.valueOf(labReaCheckDetailVo
						.getThisOutAmount()) * 100) / 100));
				labReaCheckDetailVoList.add(labReaCheckDetailVo);
			}
		} catch (Exception e) {
			log.error("盘库失败---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labReaCheckDetailVoList;
	}

}
