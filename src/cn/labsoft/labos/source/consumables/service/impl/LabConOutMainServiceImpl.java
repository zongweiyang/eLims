package cn.labsoft.labos.source.consumables.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.consumables.dao.ILabConOutDetailDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConOutMainDAO;
import cn.labsoft.labos.source.consumables.dao.ILabConsumablesDAO;
import cn.labsoft.labos.source.consumables.entity.LabConOutDetail;
import cn.labsoft.labos.source.consumables.entity.LabConOutMain;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;
import cn.labsoft.labos.source.consumables.service.ILabConOutMainService;
import cn.labsoft.labos.source.consumables.vo.LabConOutDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labConOutMainService")
public class LabConOutMainServiceImpl extends BaseService implements
		ILabConOutMainService {

	private ILabConOutMainDAO labConOutMainDAO;
	private ILabConOutDetailDAO labConOutDetailDAO;
	private ILabConsumablesDAO labConsumablesDAO;

	private static Log log = LogFactory.getLog(SpringInitListener.class);

    @Resource
	public void setLabConsumablesDAO(ILabConsumablesDAO labConsumablesDAO) {
		this.labConsumablesDAO = labConsumablesDAO;
	}
    @Resource
	public void setLabConOutMainDAO(ILabConOutMainDAO labConOutMainDAO) {
		this.labConOutMainDAO = labConOutMainDAO;
	}

	@Override
	public boolean addLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException {
		LabConOutMain labConOutMain = VoToPo(labConOutMainVo);
		labConOutMain.setIsDel(Constants_Source.N);
		labConOutMainDAO.addLabConOutMain(labConOutMain);

		List<LabConOutDetailVo> conOutDetailVoList = labConOutMainVo
				.getLabConOutDetailVoList();
		if (null != conOutDetailVoList && conOutDetailVoList.size() > 0) {
			for (LabConOutDetailVo labConOutDetailVo : conOutDetailVoList) {
				LabConOutDetail labConOutDetail = new LabConOutDetail();
				labConOutDetail.setIsDel(Constants_Source.N);
				labConOutDetail.setAmount(Double.valueOf(labConOutDetailVo
						.getAmount()));

				labConOutDetail.setRemark(labConOutDetailVo.getRemark());
				LabConsumables labConsumables = new LabConsumables();
				labConsumables.setId(labConOutDetailVo.getConsumablesId());
				labConOutDetail.setConsumables(labConsumables);
				labConOutDetail.setMain(labConOutMain);
				labConOutDetail.setOutDate(labConOutMain.getOutstoreDate());
				labConOutDetailDAO.addLabConOutDetail(labConOutDetail);

				// 更新耗材库存总量
				labConsumables = (LabConsumables) labConOutDetailDAO.findById(
						LabConsumables.class, labConsumables.getId());
				labConsumables.setAmount(labConsumables.getAmount()
						- labConOutDetail.getAmount());
				labConsumablesDAO.updateLabConsumables(labConsumables);
			}
		}

		return false;
	}

	@Override
	public boolean deleteLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException {
		LabConOutMain po = (LabConOutMain) labConOutMainDAO.findById(
				LabConOutMain.class, labConOutMainVo.getId());
		po.setIsDel(Constants_Source.N);
		labConOutMainDAO.updateLabConOutMain(po);
		return true;
	}

	@Override
	public LabConOutMainVo getLabConOutMainById(String id)
			throws GlobalException {
		LabConOutMain po = (LabConOutMain) labConOutMainDAO.findById(
				LabConOutMain.class, id);
		return PoToVo(po);
	}

	@Override
	public PageResult getLabConOutMainPR(LabConOutMainVo labConOutMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabConOutMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labConOutMainVo.getOutstorer()
				&& !"".equals(labConOutMainVo.getOutstorer())) {
			hql += " AND outstorer LIKE '%" + labConOutMainVo.getOutstorer().trim()
					+ "%'";
		}
		if (null != labConOutMainVo.getStartDate()
				&& !"".equals(labConOutMainVo.getStartDate())) {
			hql += " AND  outstoreDate >= '" + labConOutMainVo.getStartDate().trim()
					+ "'";
		}
		if (null != labConOutMainVo.getEndDate()
				&& !"".equals(labConOutMainVo.getEndDate())) {
			hql += " AND  outstoreDate <= '" + labConOutMainVo.getEndDate()
					+ "'";
		}
		if (null != labConOutMainVo.getReceiptno()
				&& !"".equals(labConOutMainVo.getReceiptno())) {
			hql += " AND  receiptno like '%" + labConOutMainVo.getReceiptno().trim()
					+ "%'";
		}
		/*
		 * if (null !=
		 * labConOutMainVo.getOutstoreDate()&&!"".equals(labConOutMainVo.getOutstoreDate())) {
		 * hql += " and outstoreDate like '%" +
		 * labConOutMainVo.getOutstoreDate() + "%'"; }
		 */
		// hql += " order by outstoreDate desc";
		pageResult = labConOutMainDAO.getPageResult(hql, pageResult);
		List<LabConOutMain> poList = pageResult.getResultList();
		List<LabConOutMainVo> voList = new ArrayList<LabConOutMainVo>();
		if (poList.size() > 0) {
			for (LabConOutMain po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return pageResult;
	}

	private LabConOutMainVo PoToVo(LabConOutMain po) throws GlobalException {
		LabConOutMainVo vo = new LabConOutMainVo();
		try {
			BeanUtils.copyProperties(po, vo, new String[] { "outstoreDate" });
		} catch (Exception e) {
			log.error("po转vo异常" + e.getMessage());
			throw new GlobalException("po转vo异常" + e.getMessage());
		}
		if (null != po.getOutstoreDate()) {
			vo.setOutstoreDate(DateUtils.format(po.getOutstoreDate(),
					DateUtils.formatStr_yyyyMMdd));
		}
		List<LabConOutDetail> labConOutDetaiList = labConOutDetailDAO
				.find("FROM LabConOutDetail WHERE main.id='" + po.getId()
						+ "' AND isDel='" + Constants_Source.N + "'");
		List<LabConOutDetailVo> labConOutDetailVoList = new ArrayList<LabConOutDetailVo>();
		if (labConOutDetaiList.size() > 0) {
			for (LabConOutDetail labConOutDetail : labConOutDetaiList) {
				LabConOutDetailVo labConOutDetailVo = new LabConOutDetailVo();
				labConOutDetailVo.setAmount(labConOutDetail.getAmount());
				labConOutDetailVo.setRemark(labConOutDetail.getRemark());
				labConOutDetailVo.setConsumablesId(labConOutDetail
						.getConsumables().getId());
				LabConsumables labConsumables = (LabConsumables) labConOutDetailDAO
						.findById(LabConsumables.class, labConOutDetail
								.getConsumables().getId());
				labConOutDetailVo.setConsumablesName(labConsumables.getName());
				labConOutDetailVo.setSize(labConsumables.getSize());
				labConOutDetailVo.setSpecifications(labConsumables.getSize());
				labConOutDetailVoList.add(labConOutDetailVo);
			}
		}
		vo.setLabConOutDetailVoList(labConOutDetailVoList);
		return vo;
	}

	private LabConOutMain VoToPo(LabConOutMainVo vo) throws GlobalException {
		LabConOutMain po = new LabConOutMain();
		try {
			BeanUtils.copyProperties(vo, po, new String[] { "outstoreDate" });
		} catch (Exception e) {
			log.error("vo转po异常" + e.getMessage());
			throw new GlobalException("vo转po异常" + e.getMessage());
		}
		if (null != vo.getOutstoreDate()) {
			try {
				po.setOutstoreDate(DateUtils.parse(vo.getOutstoreDate(),
						DateUtils.formatStr_yyyyMMdd));
			} catch (ParseException e) {
				log.error("日期格式转换异常" + e.getMessage());
				throw new GlobalException("日期格式转换异常" + e.getMessage());
			}
		}
		return po;
	}

	@Override
	public boolean updateLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException {
		try {
			labConOutMainDAO.updateLabConOutMain(VoToPo(labConOutMainVo));
			return true;
		} catch (Exception e) {
			log.error("修改耗材出库信息失败" + e.getMessage());
			throw new GlobalException("修改耗材出库信息失败" + e.getMessage());
		}

	}
    
	@Resource
	public void setLabConOutDetailDAO(ILabConOutDetailDAO labConOutDetailDAO) {
		this.labConOutDetailDAO = labConOutDetailDAO;
	}

	@Override
	public String getMaxLabConOutMainReceiptno() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabConOutMain  WHERE receiptno LIKE '"
				+ year_month_day + "%' ";

		List<LabConOutMain> conOutstoreMainList = labConOutMainDAO.find(hql);
		String billId = year_month_day;
		if (conOutstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabConOutMain main : conOutstoreMainList) {
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
	public List<LabConOutDetailVo> getLabConOutDetailListByConsumablesId(String conOutDetailVoId) throws GlobalException {
		if(!StrUtils.isBlankOrNull(conOutDetailVoId)){
			String[] ids = conOutDetailVoId.split(",");
			String id = "";
			for (int i = 0; i < ids.length; i++) {
				id += "'" + ids[i] + "',";
			}
			if(!StrUtils.isBlankOrNull(id)){
				id += "'" + ids[ids.length - 1] + "'";
				String hql = " FROM LabConsumables WHERE id IN(" + id + ")";
				List<LabConsumables> consumablesList = labConOutDetailDAO.find(hql);
				List<LabConOutDetailVo> labConOutDetailVoList = new ArrayList<LabConOutDetailVo>();
				if (null != consumablesList && consumablesList.size() > 0) {
					for (LabConsumables labConsumables : consumablesList) {
						LabConOutDetailVo labConOutDetailVo = new LabConOutDetailVo();
						labConOutDetailVo.setConsumablesId(labConsumables.getId());
						labConOutDetailVo.setConsumablesName(labConsumables.getName());
						labConOutDetailVo.setSize(labConsumables.getSize());
						labConOutDetailVo.setNum(String.valueOf(labConsumables
								.getAmount()));
						labConOutDetailVoList.add(labConOutDetailVo);
					}
				}
				return labConOutDetailVoList;
			}
		}
		return null;
	}
}
