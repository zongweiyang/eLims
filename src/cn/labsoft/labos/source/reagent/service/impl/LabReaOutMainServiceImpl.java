package cn.labsoft.labos.source.reagent.service.impl;

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
import cn.labsoft.labos.source.reagent.dao.ILabReaOutDetailDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReaOutMainDAO;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaOutDetail;
import cn.labsoft.labos.source.reagent.entity.LabReaOutMain;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
import cn.labsoft.labos.source.reagent.service.ILabReaOutMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaOutDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labReaOutMainService")
public class LabReaOutMainServiceImpl extends BaseService implements
		ILabReaOutMainService {

	private ILabReaOutMainDAO labReaOutMainDAO;
	private ILabReaOutDetailDAO labReaOutDetailDAO;
	private ILabReagentDAO labReagentDAO;

	private static Log log = LogFactory.getLog(SpringInitListener.class);

	@Resource
	public void setLabReagentDAO(ILabReagentDAO labReagentDAO) {
		this.labReagentDAO = labReagentDAO;
	}

	@Resource
	public void setLabReaOutMainDAO(ILabReaOutMainDAO labReaOutMainDAO) {
		this.labReaOutMainDAO = labReaOutMainDAO;
	}

	@Override
	public LabReaOutMainVo addLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException {
		LabReaOutMain labReaOutMain = VoToPo(labReaOutMainVo);
		labReaOutMain.setIsDel(Constants_Source.N);
		labReaOutMainDAO.addLabReaOutMain(labReaOutMain);
		labReaOutMainVo.setId(labReaOutMain.getId());
		List<LabReaOutDetailVo> reaOutDetailVoList = labReaOutMainVo
				.getLabReaOutDetailVoList();
		if (null != reaOutDetailVoList && reaOutDetailVoList.size() > 0) {
			for (LabReaOutDetailVo labReaOutDetailVo : reaOutDetailVoList) {
				LabReaOutDetail labReaOutDetail = new LabReaOutDetail();
				labReaOutDetail.setIsDel(Constants_Source.N);
				labReaOutDetail.setAmount(Double.valueOf(labReaOutDetailVo
						.getAmount()));

				labReaOutDetail.setRemark(labReaOutDetailVo.getRemark());
				LabReagent labReagent = new LabReagent();
				labReagent.setId(labReaOutDetailVo.getReagentId());
				labReaOutDetail.setReagent(labReagent);
				labReaOutDetail.setMain(labReaOutMain);
				labReaOutDetail.setOutDate(labReaOutMain.getOutstoreDate());
				labReaOutDetailDAO.addLabReaOutDetail(labReaOutDetail);
				// 更新试剂库存总量
				labReagent = (LabReagent) labReaOutDetailDAO.findById(
						LabReagent.class, labReagent.getId());
				labReagent.setAmount(labReagent.getAmount()
						- labReaOutDetail.getAmount());
				labReagentDAO.updateLabReagent(labReagent);
			}
		}

		return labReaOutMainVo;
	}

	@Override
	public boolean deleteLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException {
		LabReaOutMain po = (LabReaOutMain) labReaOutMainDAO.findById(
				LabReaOutMain.class, labReaOutMainVo.getId());
		po.setIsDel(Constants_Source.N);
		labReaOutMainDAO.updateLabReaOutMain(po);
		return true;
	}

	@Override
	public LabReaOutMainVo getLabReaOutMainById(String id)
			throws GlobalException {
		LabReaOutMain po=null;
		if(!StrUtils.isBlankOrNull(id)){
			 po = (LabReaOutMain) labReaOutMainDAO.findById(
					LabReaOutMain.class, id);
		}
			if(po!=null)return PoToVo(po);
		
		else return null;
	}
	@Override
	public PageResult getLabReaOutMainPR(LabReaOutMainVo labReaOutMainVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReaOutMain WHERE isDel='" + Constants_Source.N + "'";
		if (null != labReaOutMainVo.getOutstorer()
				&& !"".equals(labReaOutMainVo.getOutstorer())) {
			hql += " AND outstorer LIKE '%" + labReaOutMainVo.getOutstorer().trim()
					+ "%'";
		}
		if (null != labReaOutMainVo.getReceiptno()
				&& !"".equals(labReaOutMainVo.getReceiptno())) {
			hql += " AND receiptno LIKE '%" + labReaOutMainVo.getReceiptno().trim()
					+ "%'";
		}
		if (null != labReaOutMainVo.getStartDate()
				&& !"".equals(labReaOutMainVo.getStartDate())) {
			hql += " AND  outstoreDate >= '" + labReaOutMainVo.getStartDate().trim()
					+ "'";
		}
		if (null != labReaOutMainVo.getEndDate()
				&& !"".equals(labReaOutMainVo.getEndDate())) {
			hql += " AND  outstoreDate <= '" + labReaOutMainVo.getEndDate()
					+ "'";
		}
		/*
		 * if (null !=
		 * labReaOutMainVo.getOutstoreDate()&&!"".equals(labReaOutMainVo.getOutstoreDate())) {
		 * hql += " and outstoreDate like '%" +
		 * labReaOutMainVo.getOutstoreDate() + "%'"; }
		 */
		// hql += " order by outstoreDate desc";
		pageResult = labReaOutMainDAO.getPageResult(hql, pageResult);
		List<LabReaOutMain> poList = pageResult.getResultList();
		List<LabReaOutMainVo> voList = new ArrayList<LabReaOutMainVo>();
		if (poList.size() > 0) {
			for (LabReaOutMain po : poList) {
				voList.add(PoToVo(po));
			}
		}
		return pageResult;
	}

	private LabReaOutMainVo PoToVo(LabReaOutMain po) throws GlobalException {
		LabReaOutMainVo vo = new LabReaOutMainVo();
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
		List<LabReaOutDetail> labReaOutDetaiList = labReaOutDetailDAO
				.find("FROM LabReaOutDetail WHERE main.id='" + po.getId()
						+ "' AND isDel='" + Constants_Source.N + "'");
		List<LabReaOutDetailVo> labReaOutDetailVoList = new ArrayList<LabReaOutDetailVo>();
		if (labReaOutDetaiList.size() > 0) {
			for (LabReaOutDetail labReaOutDetail : labReaOutDetaiList) {
				LabReaOutDetailVo labReaOutDetailVo = new LabReaOutDetailVo();
				labReaOutDetailVo.setAmount(labReaOutDetail.getAmount());
				labReaOutDetailVo.setRemark(labReaOutDetail.getRemark());
				labReaOutDetailVo.setReagentId(labReaOutDetail.getReagent()
						.getId());
				LabReagent labReagent = (LabReagent) labReaOutDetailDAO
						.findById(LabReagent.class, labReaOutDetail
								.getReagent().getId());
				labReaOutDetailVo.setReagentName(labReagent.getName());
				labReaOutDetailVo.setPurity(labReagent.getPurity());
				labReaOutDetailVo.setSize(labReagent.getSize());
				labReaOutDetailVo.setSpecifications(labReagent.getSize());
				labReaOutDetailVo.setNum(labReagent.getAmount().toString());
				labReaOutDetailVo.setId(labReaOutDetail.getId());
				labReaOutDetailVoList.add(labReaOutDetailVo);
			}
		}
		vo.setLabReaOutDetailVoList(labReaOutDetailVoList);
		return vo;
	}

	private LabReaOutMain VoToPo(LabReaOutMainVo vo) throws GlobalException {
		LabReaOutMain po = new LabReaOutMain();
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
	public boolean updateLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException {
		try {
			labReaOutMainDAO.updateLabReaOutMain(VoToPo(labReaOutMainVo));
			return true;
		} catch (Exception e) {
			log.error("修改试剂出库信息失败" + e.getMessage());
			throw new GlobalException("修改试剂出库信息失败" + e.getMessage());
		}

	}

   @Resource
	public void setLabReaOutDetailDAO(ILabReaOutDetailDAO labReaOutDetailDAO) {
		this.labReaOutDetailDAO = labReaOutDetailDAO;
	}

	@Override
	public String getMaxLabReaOutMainReceiptno() throws GlobalException {
		String year_month_day = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDay();
		String hql = "FROM LabReaOutMain  WHERE receiptno LIKE '"
				+ year_month_day + "%' ";

		List<LabReaOutMain> reaOutstoreMainList = labReaOutMainDAO.find(hql);
		String billId = year_month_day;
		if (reaOutstoreMainList.size() > 0) {
			int maxRecordId = 0;
			for (LabReaOutMain main : reaOutstoreMainList) {
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
	public List<LabReaOutDetailVo> getLabReaOutDetailListByReagentId(String reaOutDetailVoId) throws GlobalException {
			if(!StrUtils.isBlankOrNull(reaOutDetailVoId)){
				String[] ids = reaOutDetailVoId.split(",");
				String id = "";
				for (int i = 0; i < ids.length; i++) {
					id += "'" + ids[i] + "',";
				}
				if(!StrUtils.isBlankOrNull(id)){
					id += "'" + ids[ids.length - 1] + "'";
					String hql = " FROM LabReagent WHERE id IN(" + id + ")";
					List<LabReagent> reagentList = labReaOutDetailDAO.find(hql);
					List<LabReaOutDetailVo> labReaOutDetailVoList = new ArrayList<LabReaOutDetailVo>();
					if (null != reagentList && reagentList.size() > 0) {
						for (LabReagent labReagent : reagentList) {
							LabReaOutDetailVo labReaOutDetailVo = new LabReaOutDetailVo();
							labReaOutDetailVo.setReagentId(labReagent.getId());
							labReaOutDetailVo.setReagentName(labReagent.getName());
							labReaOutDetailVo.setSize(labReagent.getSize());
							labReaOutDetailVo.setPurity(labReagent.getPurity());
							labReaOutDetailVo.setNum(String.valueOf(labReagent.getAmount()));
							labReaOutDetailVoList.add(labReaOutDetailVo);
						}
					}
					return labReaOutDetailVoList;
				}
			}
		return null;
	}

	@Override
	public LabReaOutMainVo getLabReaOutMainById(String id, String reaIds) throws GlobalException {
		// TODO Auto-generated method stub
		LabReaOutMainVo labReaOutMainVo=new LabReaOutMainVo();
		List<LabReaOutDetailVo> listLabReaOutDetailVo=new ArrayList<LabReaOutDetailVo>();
		if(!StrUtils.isBlankOrNull(id)){
			LabReaOutMain labReaOutMain=  (LabReaOutMain) labReaOutMainDAO.findById(LabReaOutMain.class, id);
			if(labReaOutMain!=null){
				BeanUtils.copyProperties(labReaOutMain, labReaOutMainVo, new String[] {"outstoreDate"});
				labReaOutMainVo.setOutstoreDate(DateUtils.format(labReaOutMain.getOutstoreDate(), DateUtils.formatStr_yyyyMMdd));
				String hql="FROM LabReaOutDetail WHERE main.id='"+labReaOutMain.getId()+"' AND isDel='"+Constants_Source.N+"'";
				List<LabReaOutDetail> listLabReaOutDetail=labReaOutMainDAO.find(hql);
				if(listLabReaOutDetail!=null&&listLabReaOutDetail.size()>0){
					for(LabReaOutDetail labReaOutDetail:listLabReaOutDetail){
						LabReaOutDetailVo labReaOutDetailVo = new LabReaOutDetailVo();
						if(!StrUtils.isBlankOrNull(reaIds)){
							if(reaIds.indexOf(labReaOutDetail.getReagent().getId())>-1)reaIds=reaIds.replace(labReaOutDetail.getReagent().getId(), "");
						}
						BeanUtils.copyProperties(labReaOutDetail, labReaOutDetailVo,new String[]{"amount","reagent"});
						labReaOutDetailVo.setNum(labReaOutDetail.getReagent().getAmount().toString());
						labReaOutDetailVo.setReagentName(labReaOutDetail.getReagent().getName());
						labReaOutDetailVo.setReagentId(labReaOutDetail.getReagent().getId());
						labReaOutDetailVo.setPurity(labReaOutDetail.getReagent().getPurity());
						labReaOutDetailVo.setSize(labReaOutDetail.getReagent().getSize());
						labReaOutDetailVo.setAmount(labReaOutDetail.getAmount());
						listLabReaOutDetailVo.add(labReaOutDetailVo);
					}
				}
				if(!StrUtils.isBlankOrNull(reaIds))
					if(!StrUtils.isBlankOrNull(reaIds.replaceAll(",", "").trim()))
						listLabReaOutDetailVo.addAll(this.getLabReaOutDetailListByReagentId(reaIds));
				if(listLabReaOutDetailVo.size()>0)labReaOutMainVo.setLabReaOutDetailVoList(listLabReaOutDetailVo);
			}
		}
		return labReaOutMainVo;
	}
}
