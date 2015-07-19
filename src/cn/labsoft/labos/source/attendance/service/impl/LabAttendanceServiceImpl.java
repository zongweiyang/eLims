package cn.labsoft.labos.source.attendance.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.attendance.dao.ILabAttendConfigDAO;
import cn.labsoft.labos.source.attendance.dao.ILabAttendanceDAO;
import cn.labsoft.labos.source.attendance.entity.LabAttendConfig;
import cn.labsoft.labos.source.attendance.entity.LabAttendance;
import cn.labsoft.labos.source.attendance.service.ILabAttendanceService;
import cn.labsoft.labos.source.attendance.vo.LabAttendanceVo;

@Service("labAttendanceService")
public class LabAttendanceServiceImpl extends BaseService implements ILabAttendanceService {
	private ILabAttendanceDAO labAttendanceDAO;
	private ILabAttendConfigDAO labAttendConfigDAO;

	@Resource
	public void setLabAttendConfigDAO(ILabAttendConfigDAO labAttendConfigDAO) {
		this.labAttendConfigDAO = labAttendConfigDAO;
	}

	@Resource
	public void setLabAttendanceDAO(ILabAttendanceDAO labAttendanceDAO) {
		this.labAttendanceDAO = labAttendanceDAO;
	}

	@Override
	public LabAttendanceVo addLabAttendance(LabAttendanceVo labAttendanceVo) throws GlobalException {
		SessionContainer son = getSessionContainer();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND workDate = '" + DateUtils.getCurrDateStr() + "'";
		LabAttendance labAttendance = (LabAttendance) labAttendanceDAO.find(hql, 0);
		String stantTime = "";
		if (labAttendance == null) {
			labAttendance = new LabAttendance();
			labAttendance.setOrgId(son.getOrgId());
			labAttendance.setOrgName(son.getOrgName());
			labAttendance.setUserId(son.getUserId());
			labAttendance.setUserName(son.getUserName());
			labAttendance.setWorkDate(DateUtils.getCurrDateStr());
			labAttendance.setStartTime(DateUtils.getCurrTimeStr().substring(0, 5));
			LabAttendConfig config = labAttendConfigDAO.getLabAttendConfig4Cur();
			stantTime = config.getStartTime();
			String stanTime = DateUtils.getCurrDateStr() + " " + config.getStartTime() + ":00";
			String nowTime = DateUtils.getCurrDateTimeStr();
			if (DateUtils.getIntevalMinutes(stanTime, nowTime) > 0) {
				labAttendance.setStartFlag(Constants_Source.Y);
				labAttendance.setStartMin(DateUtils.getIntevalMinutes(stanTime, nowTime));
			} else {
				labAttendance.setStartFlag(Constants_Source.N);
				labAttendance.setStartMin(0);
			}
			labAttendance.setAfterFlag(Constants_Source.N);
			labAttendanceDAO.addLabAttendance(labAttendance);
		} else {
			LabAttendConfig config = labAttendConfigDAO.getLabAttendConfig4Cur();
			stantTime = config.getStartTime();
		}
		try {
			labAttendanceVo = this.po2Vo(labAttendance, labAttendanceVo);
			labAttendanceVo.setStandTimeAM(stantTime);
		} catch (Exception e) {
			Log4J.error("LabAttendanceServiceImpl addLabAttendance  error.." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labAttendanceVo;
	}

	@Override
	public boolean deleteLabAttendance(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labAttendanceDAO.deleteLabAttendance(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabAttendanceServiceImpl deleteLabAttendance  error.." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabAttendanceVo getLabAttendance(String id) throws GlobalException {
		LabAttendanceVo labAttendanceVo = new LabAttendanceVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabAttendance labAttendance = labAttendanceDAO.getLabAttendance(id);
				labAttendanceVo = this.po2Vo(labAttendance, labAttendanceVo);
			} catch (Exception e) {
				Log4J.error("LabAttendanceServiceImpl getLabAttendance  error.." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labAttendanceVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabAttendanceVo> getLabAttendanceList(LabAttendanceVo labAttendanceVo) throws GlobalException {
		List<LabAttendanceVo> labAttendanceVoList = new ArrayList<LabAttendanceVo>();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "' ";
		hql += " AND workDate LIKE '" + labAttendanceVo.getWorkMonth() + "%'";
		hql += " AND userId = '" + labAttendanceVo.getUserId() + "'";
		hql += " ORDER BY workDate ASC";
		List<LabAttendance> labAttendanceList = labAttendanceDAO.find(hql);
		// 总天数
		String nextDay = DateUtils.getNextMonth(labAttendanceVo.getWorkMonth()) + "01";
		nextDay = nextDay.substring(0, 4) + "-" + nextDay.substring(4, 6) + "-" + nextDay.substring(6, 8);
		int totleDay = (int) DateUtils.getIntevalDays(labAttendanceVo.getWorkMonth() + "-01", nextDay);
		int weeks = 1;
		for (int i = 1; i <= totleDay; i++) {
			String day = "";
			if (i < 10) {
				day = labAttendanceVo.getWorkMonth() + "-0" + i;
			} else {
				day = labAttendanceVo.getWorkMonth() + "-" + i;
			}
			LabAttendanceVo attendanceVo = new LabAttendanceVo();
			attendanceVo.setDay(i);
			attendanceVo.setWorkDate(day);

			int yearx = Integer.valueOf(day.substring(0, 4));
			int monthx = Integer.parseInt(day.substring(5, 7));
			int dayx = Integer.parseInt(day.substring(8, 10));
			int num = DateUtils.getDayOfWeek(yearx, monthx, dayx);
			attendanceVo.setWeek(num);// 周几
			if (i == 1 && num != 1) {
				weeks = num;
				for (int x = 1; x < num; x++) {
					LabAttendanceVo vo = new LabAttendanceVo();
					labAttendanceVoList.add(vo);
				}
			}
			boolean flag = false;
			for (LabAttendance po : labAttendanceList) {
				if (day.equals(po.getWorkDate())) {
					attendanceVo.setStartTime(po.getStartTime());
					attendanceVo.setStartFlag(po.getStartFlag());
					attendanceVo.setEndTime(po.getEndTime());
					attendanceVo.setEndFlag(po.getEndFlag());
					attendanceVo.setAfterFlag(po.getAfterFlag());
					attendanceVo.setIsOk(po.getIsOk());
					attendanceVo.setUserId(po.getUserId());
					attendanceVo.setUserName(po.getUserName());
					attendanceVo.setOrgId(po.getOrgId());
					attendanceVo.setOrgName(po.getOrgName());
					attendanceVo.setRemark(po.getRemark());
					flag = true;
					break;
				}
			}
			if (DateUtils.getIntevalDays(day, DateUtils.getCurrDateStr()) > 0) {
				if (!flag && checkThisDay4work(day)) {
					attendanceVo.setNoWork(Constants_Source.Y);// 旷工
				} else if (flag && !checkThisDay4work(day)) {
					attendanceVo.setIsGood(Constants_Source.Y);// 加班
				}
			}
			labAttendanceVoList.add(attendanceVo);
		}
		if ((totleDay + weeks - 1) % 7 != 0) {
			for (int x = 0; x < 7 - (totleDay + weeks - 1) % 7; x++) {
				LabAttendanceVo vo = new LabAttendanceVo();
				labAttendanceVoList.add(vo);
			}
		}
		return labAttendanceVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabAttendancePR(LabAttendanceVo labAttendanceVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "'";
		pageResult = labAttendanceDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabAttendanceVo> labAttendanceVoList = new ArrayList<LabAttendanceVo>();
			List<LabAttendance> listLabAttendance = new ArrayList<LabAttendance>();
			listLabAttendance = pageResult.getResultList();
			for (LabAttendance labAttendance : listLabAttendance) {
				LabAttendanceVo vo = new LabAttendanceVo();
				vo = this.po2Vo(labAttendance, vo);
				labAttendanceVoList.add(vo);
			}
			pageResult.setResultList(labAttendanceVoList);
		}
		return pageResult;
	}

	@Override
	public LabAttendanceVo updateLabAttendance(LabAttendanceVo labAttendanceVo) throws GlobalException {
		SessionContainer son = getSessionContainer();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "'";
		hql += " AND workDate = '" + DateUtils.getCurrDateStr() + "'";
		LabAttendance labAttendance = (LabAttendance) labAttendanceDAO.find(hql, 0);
		String stantTime = "";
		if (labAttendance != null) {
			labAttendance.setEndTime(DateUtils.getCurrTimeStr().substring(0, 5));
			LabAttendConfig config = labAttendConfigDAO.getLabAttendConfig4Cur();
			stantTime = config.getEndTime();
			String standTime = DateUtils.getCurrDateStr() + " " + config.getEndTime() + ":00";
			String nowTime = DateUtils.getCurrDateTimeStr();
			if (DateUtils.getIntevalMinutes(standTime, nowTime) < 0) {
				labAttendance.setEndFlag(Constants_Source.Y);
				labAttendance.setEndMin(DateUtils.getIntevalMinutes(nowTime, standTime));
			} else {
				labAttendance.setEndFlag(Constants_Source.N);
				labAttendance.setEndMin(0);
			}
			if(null!=labAttendance.getStartFlag()&&labAttendance.getStartFlag().equals(Constants_Source.N)
					&&null!=labAttendance.getEndFlag()&&labAttendance.getEndFlag().equals(Constants_Source.N)){
				labAttendance.setIsOk(Constants_Source.Y);
			}else{
				labAttendance.setIsOk(Constants_Source.N);
			}
			labAttendanceDAO.updateLabAttendance(labAttendance);
		} else {
			labAttendance = new LabAttendance();
			labAttendance.setOrgId(son.getOrgId());
			labAttendance.setOrgName(son.getOrgName());
			labAttendance.setUserId(son.getUserId());
			labAttendance.setUserName(son.getUserName());
			labAttendance.setWorkDate(DateUtils.getCurrDateStr());
			labAttendance.setStartTime(null);
			labAttendance.setEndTime(DateUtils.getCurrTimeStr().substring(0, 5));

			LabAttendConfig config = labAttendConfigDAO.getLabAttendConfig4Cur();
			stantTime = config.getEndTime();

			String startTime = DateUtils.getCurrDateStr() + " " + config.getStartTime() + ":00";
			String nowTime = DateUtils.getCurrDateStr() + " 12:00:00";
			labAttendance.setStartMin(DateUtils.getIntevalMinutes(startTime, nowTime));
			labAttendance.setStartFlag(Constants_Source.Y);

			String endTime = DateUtils.getCurrDateStr() + " " + config.getEndTime() + ":00";
			String nowTimex = DateUtils.getCurrDateTimeStr();
			if (DateUtils.getIntevalMinutes(endTime, nowTimex) < 0) {
				labAttendance.setEndFlag(Constants_Source.Y);
				labAttendance.setEndMin(DateUtils.getIntevalMinutes(nowTimex, endTime));
			} else {
				labAttendance.setEndFlag(Constants_Source.N);
				labAttendance.setEndMin(0);
			}
			labAttendance.setIsOk(Constants_Source.N);
			labAttendanceDAO.addLabAttendance(labAttendance);
		}
		labAttendanceVo = this.po2Vo(labAttendance, labAttendanceVo);
		labAttendanceVo.setStandTimePM(stantTime);
		return labAttendanceVo;
	}

	@Override
	public boolean updateLabAttendance4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabAttendance labAttendance = labAttendanceDAO.getLabAttendance(id);
					labAttendance.setIsDel(Constants_Source.Y);
					labAttendanceDAO.updateLabAttendance(labAttendance);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error("LabAttendanceServiceImpl updateLabAttendance4Del  error.." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<LabAttendanceVo> getLabAttendanceVoListByWhere(String wereHql) throws GlobalException {
		List<LabAttendanceVo> labAttendanceVoList = new ArrayList<LabAttendanceVo>();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		hql += " ORDER BY workDate ASC";
		List<LabAttendance> labAttendanceList = labAttendanceDAO.find(hql);
		if (labAttendanceList != null && labAttendanceList.size() > 0) {
			for (LabAttendance labAttendance : labAttendanceList) {
				LabAttendanceVo labAttendanceVo = new LabAttendanceVo();
				labAttendanceVo = this.po2Vo(labAttendance, labAttendanceVo);
				labAttendanceVoList.add(labAttendanceVo);
			}
		}
		return labAttendanceVoList;
	}

	public LabAttendance vo2Po(LabAttendanceVo labAttendanceVo, LabAttendance labAttendance) {
		BeanUtils.copyProperties(labAttendanceVo, labAttendance, new String[] { "isDel" });
		return labAttendance;
	}

	public LabAttendanceVo po2Vo(LabAttendance labAttendance, LabAttendanceVo labAttendanceVo) {
		BeanUtils.copyProperties(labAttendance, labAttendanceVo);
		return labAttendanceVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getLabAttendanceList4Org(LabAttendanceVo labAttendanceVo) throws GlobalException {
		String sqlTem = "";
		if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkMonth())) {
			sqlTem += "work_date LIKE '" + labAttendanceVo.getWorkMonth() + "%'";
		} else if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkYear())) {
			sqlTem += "work_date LIKE '" + labAttendanceVo.getWorkYear() + "%'";
		}
		int n = getWorkDateNum4Month(labAttendanceVo.getWorkMonth());
		String sql = "select us.id,us.name as user,org.name as orgName,count(att.id),";
		sql += " (select count(*) from lab_attendance a where att.user_id=a.user_id and a.start_flag='" + Constants_Source.Y + "' AND a." + sqlTem.trim() + "),";
		sql += " (select count(*) from lab_attendance b where att.user_id=b.user_id and b.end_flag='" + Constants_Source.Y + "' AND b." + sqlTem.trim() + "),";
		sql += " (select count(*) from lab_attendance c where att.user_id=c.user_id and c.after_flag='" + Constants_Source.Y + "' AND c." + sqlTem.trim() + ")";
		sql += " FROM lab_sys_user us";
		sql += " left join lab_sys_user_org uo on uo.user_id=us.ID";
		sql += " left join lab_sys_org org on uo.org_id=org.ID";
		sql += " left join lab_attendance att on att.user_id=us.ID AND att." + sqlTem.trim();
		sql += " where us.is_del='" + Constants_Source.N + "' and us.ID in(select user_id from lab_sys_user_org where org_id='" + labAttendanceVo.getOrgId() + "') and org.id='" + labAttendanceVo.getOrgId() + "'";
		sql += " group by us.id ORDER BY us.sort ASC";
		List<Object[]> objctList = labAttendanceDAO.createSqlQuery(sql);
		List<String[]> allList=new ArrayList<String[]>();
		if (objctList != null) {
			for (Object[] obj : objctList) {
				String[] str=new String[8];
				str[0]=String.valueOf(obj[0]);
				str[1]=String.valueOf(obj[1]);
				str[2]=String.valueOf(obj[2]);
				str[3]=String.valueOf(obj[3]);
				str[4]=String.valueOf(obj[4]);
				str[5]=String.valueOf(obj[5]);
				str[6]=String.valueOf(obj[6]);
				str[7] = (n - Integer.valueOf(String.valueOf(obj[3]))) + "";
				allList.add(str);
			}
		}

		return allList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer[] getLabAttendanceList4User(LabAttendanceVo labAttendanceVo) throws GlobalException {
		Integer[] totlList = new Integer[5];
		// 统计个人
		if (!StrUtils.isBlankOrNull(labAttendanceVo.getUserId())) {
			String hql = "SELECT count(id) FROM LabAttendance WHERE isDel='" + Constants_Source.N + "'";
			hql += " AND userId='" + labAttendanceVo.getUserId() + "'";

			if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkMonth())) {
				hql += " AND workDate LIKE '" + labAttendanceVo.getWorkMonth() + "%'";
			} else if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkYear())) {
				hql += " AND workDate LIKE '" + labAttendanceVo.getWorkYear() + "%'";
			}
			if (!StrUtils.isBlankOrNull(labAttendanceVo.getOrgId())) {
				hql += " AND orgId='" + labAttendanceVo.getOrgId() + "'";
			}
			// 实到
			Object obj1 = labAttendanceDAO.find(hql, 0);
			if (obj1 == null) {
				totlList[0] = 0;
				totlList[4] = 0;
			} else {
				totlList[0] = Integer.valueOf(String.valueOf(obj1));
				// 缺卡
				int n = getWorkDateNum4Month(labAttendanceVo.getWorkMonth());
				totlList[4] = n - Integer.valueOf(String.valueOf(obj1));
			}
			// 迟到
			Object obj2 = labAttendanceDAO.find(hql + " AND startFlag='" + Constants_Source.Y + "'", 0);
			if (obj2 == null) {
				totlList[1] = 0;
			} else {
				totlList[1] = Integer.valueOf(String.valueOf(obj2));
			}
			// 早退
			Object obj3 = labAttendanceDAO.find(hql + " AND endFlag='" + Constants_Source.Y + "'", 0);
			if (obj3 == null) {
				totlList[2] = 0;
			} else {
				totlList[2] = Integer.valueOf(String.valueOf(obj3));
			}
			// 补卡
			Object obj4 = labAttendanceDAO.find(hql + " AND afterFlag='" + Constants_Source.Y + "'", 0);
			if (obj4 == null) {
				totlList[3] = 0;
			} else {
				totlList[3] = Integer.valueOf(String.valueOf(obj4));
			}

		} else {// 统计集体
			String hql = "SELECT count(id) FROM LabAttendance WHERE isDel='" + Constants_Source.N + "'";
			if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkMonth())) {
				hql += " AND workDate LIKE '" + labAttendanceVo.getWorkMonth() + "%'";
			} else if (!StrUtils.isBlankOrNull(labAttendanceVo.getWorkYear())) {
				hql += " AND workDate LIKE '" + labAttendanceVo.getWorkYear() + "%'";
			}
			String suerHql = "select distinct(user.id) FROM LabUserOrg WHERE user.isDel='" + Constants_Source.N + "'";
			if (!StrUtils.isBlankOrNull(labAttendanceVo.getOrgId())) {
				hql += " AND orgId='" + labAttendanceVo.getOrgId() + "'";
				suerHql += " AND org.id='" + labAttendanceVo.getOrgId() + "'";
			}
			List<Object> userList = labAttendanceDAO.find(suerHql);
			int n = getWorkDateNum4Month(labAttendanceVo.getWorkMonth());
			totlList[0] = n;
			// 实到
			Object obj1 = labAttendanceDAO.find(hql, 0);
			if (obj1 == null) {
				totlList[4] = userList.size() * n;
			} else {
				// 缺卡
				totlList[4] = userList.size() * n - Integer.valueOf(String.valueOf(obj1));
			}
			// 迟到
			Object obj2 = labAttendanceDAO.find(hql + " AND startFlag='" + Constants_Source.Y + "'", 0);
			if (obj2 == null) {
				totlList[1] = 0;
			} else {
				totlList[1] = Integer.valueOf(String.valueOf(obj2));
			}
			// 早退
			Object obj3 = labAttendanceDAO.find(hql + " AND endFlag='" + Constants_Source.Y + "'", 0);
			if (obj3 == null) {
				totlList[2] = 0;
			} else {
				totlList[2] = Integer.valueOf(String.valueOf(obj3));
			}
			// 补卡
			Object obj4 = labAttendanceDAO.find(hql + " AND afterFlag='" + Constants_Source.Y + "'", 0);
			if (obj4 == null) {
				totlList[3] = 0;
			} else {
				totlList[3] = Integer.valueOf(String.valueOf(obj4));
			}
		}
		return totlList;
	}

	// 根据配置获取当前月的工作时间
	public Integer getWorkDateNum4Month(String month) throws GlobalException {
		int count = 0;
		LabAttendConfig cfg = labAttendConfigDAO.getLabAttendConfig4Month(month);
		int totleDay = 0;
		if(cfg==null)return 0;
		if (DateUtils.getCurrDateStr().contains(month)) {
			String standTime = DateUtils.getCurrDateStr() + " " + cfg.getStartTime() + ":00";
			if (DateUtils.getIntevalMinutes(standTime, DateUtils.getCurrDateTimeStr()) > 0) {
				totleDay = (int) DateUtils.getIntevalDays(month + "-01", DateUtils.getCurrDateStr()) + 1;
			} else {
				totleDay = (int) DateUtils.getIntevalDays(month + "-01", DateUtils.getCurrDateStr());
			}
		} else {
			String nextDay = DateUtils.getNextMonth(month) + "01";
			nextDay = nextDay.substring(0, 4) + "-" + nextDay.substring(4, 6) + "-" + nextDay.substring(6, 8);

			totleDay = (int) DateUtils.getIntevalDays(month + "-01", nextDay);
		}
		int yearx = Integer.valueOf(month.substring(0, 4));
		int monthx = Integer.parseInt(month.substring(5, 7));
		for (int i = 1; i <= totleDay; i++) {
			int day = DateUtils.getDayOfWeek(yearx, monthx, i);
			if (cfg.getWorkDay().contains("" + day)) {
				count++;
			}
		}
		return count;
	}

	//根据配置获取当前月的工作时间
	public boolean checkThisDay4work(String day) throws GlobalException {
		if (day.length() >= 10) {
			if (DateUtils.getIntevalDays(DateUtils.getCurrDateStr(), day) >= 0) {
				return false;
			}
			LabAttendConfig cfg = labAttendConfigDAO.getLabAttendConfig4Month(day.substring(0, 7));
			int yearx = Integer.valueOf(day.substring(0, 4));
			int monthx = Integer.parseInt(day.substring(5, 7));
			int dayx = Integer.parseInt(day.substring(8, 10));
			int num = DateUtils.getDayOfWeek(yearx, monthx, dayx);
			if (cfg!=null && cfg.getWorkDay().contains("" + num)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public LabAttendanceVo addLabAttendance4History(LabAttendanceVo labAttendanceVo) throws GlobalException {
		SessionContainer son=SessionContainer.getSessionContainer();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "' ";
		hql += " AND workDate ='" + labAttendanceVo.getWorkDate()+ "'";
		hql += " AND userId = '" +labAttendanceVo.getUserId() + "'";
		LabAttendance attendance = (LabAttendance)labAttendanceDAO.find(hql,0);
		LabAttendConfig cfg = labAttendConfigDAO.getLabAttendConfig4Month(labAttendanceVo.getWorkDate().substring(0,7));
		if(attendance!=null){
			attendance.setAfterFlag(Constants_Source.Y);
			if(!StrUtils.isBlankOrNull(labAttendanceVo.getStartTime())){
				attendance.setStartTime(labAttendanceVo.getStartTime());
				String stanTime =labAttendanceVo.getWorkDate()+" "+cfg.getStartTime() + ":00";
				String nowTime =labAttendanceVo.getWorkDate()+" "+labAttendanceVo.getStartTime() + ":00";
				if (DateUtils.getIntevalMinutes(stanTime, nowTime) > 0) {
					attendance.setStartFlag(Constants_Source.Y);
					attendance.setStartMin(DateUtils.getIntevalMinutes(stanTime, nowTime));
				} else {
					attendance.setStartFlag(Constants_Source.N);
					attendance.setStartMin(0);
				}
			}
			if(!StrUtils.isBlankOrNull(labAttendanceVo.getEndTime())){
				attendance.setEndTime(labAttendanceVo.getEndTime());
				String stanTime =labAttendanceVo.getWorkDate()+" "+cfg.getEndTime() + ":00";
				String nowTime =labAttendanceVo.getWorkDate()+" "+labAttendanceVo.getEndTime() + ":00";
				if (DateUtils.getIntevalMinutes(nowTime,stanTime) > 0) {
					attendance.setEndFlag(Constants_Source.Y);
					attendance.setEndMin(DateUtils.getIntevalMinutes(nowTime,stanTime));
				} else {
					attendance.setEndFlag(Constants_Source.N);
					attendance.setEndMin(0);
				}
			}
			if(null!=attendance.getStartFlag()&&attendance.getStartFlag().equals(Constants_Source.N)
					&&null!=attendance.getEndFlag()&&attendance.getEndFlag().equals(Constants_Source.N)){
				attendance.setIsOk(Constants_Source.Y);
			}else{
				attendance.setIsOk(Constants_Source.N);
			}
			attendance.setRemark(labAttendanceVo.getRemark());
			labAttendanceDAO.updateLabAttendance(attendance);
			labAttendanceVo = this.po2Vo(attendance, labAttendanceVo);
		}else{
			attendance=new LabAttendance();
			attendance.setAfterFlag(Constants_Source.Y);
			attendance.setOrgId(son.getOrgId());
			attendance.setOrgName(son.getOrgName());
			attendance.setUserId(son.getUserId());
			attendance.setUserName(son.getUserName());
			attendance.setWorkDate(labAttendanceVo.getWorkDate());
			if(!StrUtils.isBlankOrNull(labAttendanceVo.getStartTime())){
				attendance.setStartTime(labAttendanceVo.getStartTime());
				String stanTime =labAttendanceVo.getWorkDate()+" "+cfg.getStartTime() + ":00";
				String nowTime =labAttendanceVo.getWorkDate()+" "+labAttendanceVo.getStartTime() + ":00";
				if (DateUtils.getIntevalMinutes(stanTime, nowTime) > 0) {
					attendance.setStartFlag(Constants_Source.Y);
					attendance.setStartMin(DateUtils.getIntevalMinutes(stanTime, nowTime));
				} else {
					attendance.setStartFlag(Constants_Source.N);
					attendance.setStartMin(0);
				}
			}
			if(!StrUtils.isBlankOrNull(labAttendanceVo.getEndTime())){
				attendance.setEndTime(labAttendanceVo.getEndTime());
				String stanTime =labAttendanceVo.getWorkDate()+" "+cfg.getEndTime() + ":00";
				String nowTime =labAttendanceVo.getWorkDate()+" "+labAttendanceVo.getEndTime() + ":00";
				if (DateUtils.getIntevalMinutes(nowTime,stanTime) > 0) {
					attendance.setEndFlag(Constants_Source.Y);
					attendance.setEndMin(DateUtils.getIntevalMinutes(nowTime,stanTime));
				} else {
					attendance.setEndFlag(Constants_Source.N);
					attendance.setEndMin(0);
				}
			}
			if(null!=attendance.getStartFlag()&&attendance.getStartFlag().equals(Constants_Source.N)
					&&null!=attendance.getEndFlag()&&attendance.getEndFlag().equals(Constants_Source.N)){
				attendance.setIsOk(Constants_Source.Y);
			}else{
				attendance.setIsOk(Constants_Source.N);
			}
			attendance.setRemark(labAttendanceVo.getRemark());
			labAttendanceDAO.addLabAttendance(attendance);
			labAttendanceVo = this.po2Vo(attendance, labAttendanceVo);
		}
		labAttendanceVo.setAuditResult(Constants_Source.Y);
		return labAttendanceVo;
	}

	@Override
	public LabAttendanceVo getLabAttendance(String userId, String workData) throws GlobalException {
		SessionContainer son = getSessionContainer();
		LabAttendanceVo vo=new LabAttendanceVo();
		String hql = "FROM LabAttendance WHERE isDel='" + Constants_Source.N + "' ";
		hql += " AND workDate ='" + workData+ "'";
		hql += " AND userId = '" +userId + "'";
		LabAttendance attendance = (LabAttendance)labAttendanceDAO.find(hql,0);
		LabAttendConfig cfg = labAttendConfigDAO.getLabAttendConfig4Month(workData.substring(0,7));
		if(attendance!=null){
			BeanUtils.copyProperties(attendance, vo, new String[]{});
			vo.setStandTimeAM(cfg.getStartTime());
			vo.setStandTimePM(cfg.getEndTime());
		}else{
			vo.setWorkDate(workData);
			vo.setUserId(son.getUserId());
			vo.setUserName(son.getUserName());
			vo.setStandTimeAM(cfg.getStartTime());
			vo.setStandTimePM(cfg.getEndTime());
		}
		return vo;
	}

}
