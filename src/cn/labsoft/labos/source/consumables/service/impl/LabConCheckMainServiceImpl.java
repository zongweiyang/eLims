package cn.labsoft.labos.source.consumables.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.consumables.dao.ILabConCheckDetailDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConCheckMainDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConsumablesDAO;
import cn.labsoft.labos.source.consumables.entity.LabConCheckDetail;
import cn.labsoft.labos.source.consumables.entity.LabConCheckMain;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;
import cn.labsoft.labos.source.consumables.service.ILabConCheckMainService;
import cn.labsoft.labos.source.consumables.vo.LabConCheckDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConCheckMainVo;
import cn.labsoft.labos.utils.DateUtils;
@Service("labConCheckMainService")
public class LabConCheckMainServiceImpl extends BaseService implements
		ILabConCheckMainService {

	private ILabConCheckMainDAO labConCheckMainDAO;
	private ILabConCheckDetailDAO labConCheckDetailDAO;
	private ILabConsumablesDAO labConsumablesDAO;
	
	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabConCheckMainDAO(ILabConCheckMainDAO labConCheckMainDAO) {
		this.labConCheckMainDAO = labConCheckMainDAO;
	}
    @Resource
	public void setLabConCheckDetailDAO(
			ILabConCheckDetailDAO labConCheckDetailDAO) {
		this.labConCheckDetailDAO = labConCheckDetailDAO;
	}
    @Resource
	public void setLabConsumablesDAO(ILabConsumablesDAO labConsumablesDAO) {
		this.labConsumablesDAO = labConsumablesDAO;
	}

	@Override
	public LabConCheckMainVo addLabConCheckMain(
			LabConCheckMainVo labConCheckMainVo) throws GlobalException {
		LabConCheckMain labConCheckMain = VoToPo(labConCheckMainVo);
		labConCheckMain.setIsDel(Constants_Source.N);
		try {
			labConCheckMain.setChecktime(DateUtils.parse(labConCheckMainVo
					.getChecktime(), DateUtils.formatStr_yyyyMMdd));
		} catch (ParseException e) {
			log.error("时间转换异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		labConCheckMainDAO.addLabConCheckMain(labConCheckMain);
		List<LabConCheckDetailVo> labConCheackDetailVoList = labConCheckMainVo
				.getLabConCheckDetailVoList();
		if (labConCheackDetailVoList.size() > 0) {
			for (LabConCheckDetailVo vo : labConCheackDetailVoList) {
				LabConCheckDetail labConCheckDetail = new LabConCheckDetail();
				LabConsumables labConsumables = (LabConsumables) labConsumablesDAO
						.findById(LabConsumables.class, vo
								.getLabConsumablesId());
				labConsumables.setAmount(Double.valueOf(vo.getAmount()));
				labConsumablesDAO.updateLabConsumables(labConsumables);
				labConCheckDetail.setLabConsumables(labConsumables);
				labConCheckDetail.setLabConCheckMain(labConCheckMain);
				labConCheckDetail.setAmount(Double.valueOf(vo.getAmount()));
				labConCheckDetail.setRemark(vo.getRemark());
				labConCheckDetail.setIsDel(Constants_Source.N);
				labConCheckDetail.setLastAmount(Double.valueOf(vo
						.getLastAmount()));
				labConCheckDetail.setThisInAmount(Double.valueOf(vo
						.getThisInAmount()));
				labConCheckDetail.setThisOutAmount(Double.valueOf(vo
						.getThisOutAmount()));
				labConCheckDetailDAO.addLabConCheckDetail(labConCheckDetail);
			}
		}
		return labConCheckMainVo;
	}

	@Override
	public LabConCheckMainVo getLabConCheckMain(
			LabConCheckMainVo labConCheckMainVo) throws GlobalException {
		LabConCheckMain labConCheckMain = (LabConCheckMain) labConCheckMainDAO
				.findById(LabConCheckMain.class, labConCheckMainVo.getId());
		labConCheckMainVo = PoToVo(labConCheckMain);
		List<LabConCheckDetail> labConCheckDetaiList = labConCheckDetailDAO
				.find("FROM LabConCheckDetail WHERE isDel='" + Constants_Source.N
						+ "' AND labConCheckMain.id='"
						+ labConCheckMainVo.getId() + "'");
		List<LabConCheckDetailVo> labConCheckDetailVoList = new ArrayList<LabConCheckDetailVo>();
		if (labConCheckDetaiList.size() > 0) {
			for (LabConCheckDetail po : labConCheckDetaiList) {
				LabConCheckDetailVo vo = new LabConCheckDetailVo();
				vo.setLabConCheckMainId(po.getLabConCheckMain().getId());
				vo.setLabConCheckMainName(po.getLabConCheckMain().getName());
				vo.setLabConsumablesId(po.getLabConsumables().getId());
				LabConsumables labConsumables = (LabConsumables) labConsumablesDAO
						.findById(LabConsumables.class, po.getLabConsumables()
								.getId());
				vo.setLabConsumablesName(labConsumables.getName());
				vo.setConsumablesSize(labConsumables.getSize());
				vo.setConsumablesUnit(labConsumables.getUnit());
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
				labConCheckDetailVoList.add(vo);
			}
		}

		labConCheckMainVo.setLabConCheckDetailVoList(labConCheckDetailVoList);
		return labConCheckMainVo;
	}

	@Override
	public PageResult getLabConCheckMainPR(LabConCheckMainVo labConCheckMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabConCheckMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labConCheckMainVo.getChecker()
				&& !"".equals(labConCheckMainVo.getChecker())) {
			hql += " AND checker LIKE '%" + labConCheckMainVo.getChecker().trim()
					+ "%'";
		}
		if (null != labConCheckMainVo.getName()
				&& !"".equals(labConCheckMainVo.getName())) {
			hql += " AND name LIKE '%" + labConCheckMainVo.getName().trim() + "%'";
		}
		if (null != labConCheckMainVo.getCheckno()
				&& !"".equals(labConCheckMainVo.getCheckno())) {
			hql += " AND checkno LIKE '%" + labConCheckMainVo.getCheckno().trim()
					+ "%'";
		}
		// hql += " order by checktime desc";
		pageResult = labConCheckMainDAO.getPageResult(hql, pageResult);
		List<LabConCheckMainVo> labConChekMainVoList = new ArrayList<LabConCheckMainVo>();
		List<LabConCheckMain> labConCheckMainList = pageResult.getResultList();
		if (labConCheckMainList.size() > 0) {
			for (LabConCheckMain po : labConCheckMainList) {
				LabConCheckMainVo vo = PoToVo(po);
				labConChekMainVoList.add(vo);
			}
		}
		return pageResult;
	}

	@Override
	public LabConCheckMainVo getNewLabConCheckMainVo() throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabConCheckMainVo labConCheckMainVo = new LabConCheckMainVo();
		labConCheckMainVo.setChecktime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		String checkno = getConCheckstoreMainMaxCheckNo();
		labConCheckMainVo.setCheckno(checkno);
		// labConCheckMainVo.setChecker(sessionContainer.getUserName());
		return labConCheckMainVo;
	}

	public LabConCheckMainVo PoToVo(LabConCheckMain po) throws GlobalException {
		LabConCheckMainVo vo = new LabConCheckMainVo();
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

	public LabConCheckMain VoToPo(LabConCheckMainVo vo) throws GlobalException {
		LabConCheckMain po = new LabConCheckMain();
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
	public String getConCheckstoreMainMaxCheckNo() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabConCheckMain  WHERE 1=1 AND isDel='N' AND checkno LIKE '"
				+ year_month_day + "%'";

		List<LabConCheckMain> conCheckstoreMainList = labConCheckMainDAO
				.find(hql);
		String billId = year_month_day;
		if (conCheckstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabConCheckMain main : conCheckstoreMainList) {
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
	public List<LabConCheckDetailVo> getAllLabConCheckDetailVoList() throws GlobalException {
		List<LabConCheckDetailVo> labConCheckDetailVoList = new ArrayList();
		List tempList = new ArrayList();
		LabConCheckMain labConCheckMain = new LabConCheckMain();
		try {
			// 得到所有耗材
			String hql = "";
			String amount = "";
			hql = "FROM LabConsumables WHERE isDel='" + Constants_Source.N + "'";
			List<LabConsumables> consumablesList = labConCheckDetailDAO
					.find(hql);

			// 得到上次盘库时间
			Date date = new Date();
			try {
				date = DateUtils.parse("2000-01-01 00:00:00",
						DateUtils.formatStr_yyyyMMddHHmmss);
			} catch (ParseException e) {
				log.error("时间格式化错误---" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
			Date maxTime = new Date(date.getTime());
			hql = "SELECT MAX(checktime) FROM LabConCheckMain WHERE  1=1 and isDel='"
					+ Constants_Source.N + "'";
			tempList = labConCheckDetailDAO.find(hql);
			if (null != tempList && !"[null]".equals(tempList)
					&& 0 < tempList.size() && null != tempList.get(0)) {
				maxTime = (Date) tempList.get(0);

				// 得到上次盘库对象
				hql = "FROM LabConCheckMain  WHERE 1=1 AND isDel='"
						+ Constants_Source.N + "' AND checktime='" + maxTime + "'";
				tempList = labConCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					labConCheckMain = (LabConCheckMain) tempList.get(0);
				}
			}
			for (LabConsumables conConsumables : consumablesList) {
				// 创建一条盘点详情
				LabConCheckDetailVo labConCheckDetailVo = new LabConCheckDetailVo();
				labConCheckDetailVo.setLabConsumablesId(conConsumables.getId());
				labConCheckDetailVo.setLabConsumablesName(conConsumables
						.getName());
				labConCheckDetailVo
						.setConsumablesSize(conConsumables.getSize());
				labConCheckDetailVo
						.setConsumablesUnit(conConsumables.getUnit());
				labConCheckDetailVo.setThisAmount(conConsumables.getAmount().toString());
				// 得到一个耗材上次盘点后入库数目
				hql = "SELECT SUM(num) FROM LabConPurDetail WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND main.inTime>='"
						+ maxTime
						+ "' AND consumables.id='"
						+ conConsumables.getId()
						+ "' AND main.processIns.status='"
						+ LabWfConstant.BUS_PROCESS_END + "'";
				tempList = labConCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labConCheckDetailVo.setThisInAmount(amount);
				// 得到一个耗材上次盘点后出库数目
				hql = "SELECT SUM(amount) FROM LabConOutDetail  WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND outDate>='"
						+ maxTime
						+ "' AND consumables.id='"
						+ conConsumables.getId()
						+ "'";
				tempList = labConCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labConCheckDetailVo.setThisOutAmount(amount);
				// 得到一个耗材上次盘点的数目
				hql = "SELECT amount FROM LabConCheckDetail  WHERE 1=1 AND isDel='"
						+ Constants_Source.N
						+ "' AND labConCheckMain.id='"
						+ labConCheckMain.getId()
						+ "' AND labConsumables.id='"
						+ conConsumables.getId() + "'";
				tempList = labConCheckDetailDAO.find(hql);
				if (null != tempList && 0 < tempList.size()) {
					amount = String.valueOf(tempList.get(0));
					if ("null".equals(amount)) {
						amount = "0";
					}
				} else {
					amount = "0";
				}
				labConCheckDetailVo.setLastAmount(amount);

				// 计算一个耗材现在库中应该的数目
				labConCheckDetailVo.setAmount(String.valueOf((Double
						.valueOf(labConCheckDetailVo.getLastAmount())
						* 100
						+ Double.valueOf(labConCheckDetailVo.getThisInAmount())
						* 100 - Double.valueOf(labConCheckDetailVo
						.getThisOutAmount()) * 100) / 100));
				labConCheckDetailVoList.add(labConCheckDetailVo);
			}
		} catch (Exception e) {
			log.error("盘库失败---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return labConCheckDetailVoList;
	}

}
