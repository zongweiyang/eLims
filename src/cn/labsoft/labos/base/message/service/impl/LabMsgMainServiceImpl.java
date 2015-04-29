/**
 * <strong>Title : MesgMessageMainServiceImpl.java </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2010-3-9 下午01:25:05  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author Molly Hao<br>
 * @version <strong>LabOSM1 v </strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
package cn.labsoft.labos.base.message.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.message.dao.ILabMsgDetailDAO;
import cn.labsoft.labos.base.message.dao.ILabMsgMainDAO;
import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.base.message.entity.LabMsgMain;
import cn.labsoft.labos.base.message.service.ILabMsgMainService;
import cn.labsoft.labos.base.message.vo.LabMsgMainVo;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.PropertiesTools;
import cn.labsoft.labos.utils.StrUtils;

/**
 * <strong>Title : MesgMessageMainServiceImpl </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2010-3-9 下午01:25:05 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author Molly Hao<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人Molly Hao 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("unchecked")
@Service("labMsgMainService")
public class LabMsgMainServiceImpl implements ILabMsgMainService {
	private ILabMsgMainDAO labMsgMainDAO;
	private ILabMsgDetailDAO labMsgDetailDAO;
	private ILabUserDAO labUserDAO;
	private ILabUploadDAO labUploadDAO;
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}
	@Resource
	public void setLabMsgMainDAO(ILabMsgMainDAO labMsgMainDAO) {
		this.labMsgMainDAO = labMsgMainDAO;
	}
	@Resource
	public void setLabMsgDetailDAO(ILabMsgDetailDAO labMsgDetailDAO) {
		this.labMsgDetailDAO = labMsgDetailDAO;
	}

	@Override
	public LabMsgMainVo addLabMsgMain(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		try {
			LabMsgMain po = new LabMsgMain();
			if (!"".equals(labMsgMainVo.getSubject())) {
				po.setSubject(labMsgMainVo.getSubject());
			} else {
				po.setSubject("无主题");
			}
			po.setType(labMsgMainVo.getType());
			po.setContent(labMsgMainVo.getContent());
			LabUser sender=labUserDAO.getLabUser(labMsgMainVo.getSenderId());
			po.setSender(sender);
			po.setSenderName(sender.getName());
			po.setReceiverIds(labMsgMainVo.getReceiverIds());
			po.setReceiverNames(labMsgMainVo.getReceiverNames());
			po.setPosition(labMsgMainVo.getPosition());
			labMsgMainDAO.addLabMsgMain(po);
			if(null!=labMsgMainVo.getPosition()&&labMsgMainVo.getPosition().equals("0")){
				String[] s = labMsgMainVo.getReceiverIds().split(",");
				for (int i = 0; i < s.length; i++) {
					if(s[i]==null||s[i].trim().length()==0) continue;
					LabUser receiver = labUserDAO.getLabUser(s[i].trim());
					if (null == receiver) continue;
					LabMsgDetail pod = new LabMsgDetail();
					pod.setMsgMain(po);
					pod.setReceiver(receiver);
					pod.setReceiveName(receiver.getName());
					pod.setSender(sender);
					pod.setSenderName(sender.getName());
					pod.setReadFlag(0);
					pod.setPosition((short)1);
					labMsgDetailDAO.addLabMsgDetail(pod);// 将消息发送给指定的所有人
				}
				// 复制一份到发件箱
				LabMsgDetail pode = new LabMsgDetail();
				pode.setReceiver(sender);
				pode.setReceiveName(sender.getName());
				pode.setSender(sender);
				pode.setSenderName(sender.getName());
				pode.setMsgMain(po);
				pode.setPosition((short)0);
				labMsgDetailDAO.addLabMsgDetail(pode);
			}
			labMsgMainVo.setId(po.getId());
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labMsgMainVo.getUuid(), "msg");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
			return labMsgMainVo;
		} catch (Exception e) {
			Log4J.error("向消息主表中插入记录出错！" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabMsgMainVo addLabMsgDetailbyName(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		try {

			LabMsgMain po = new LabMsgMain();
			String createTime = labMsgMainVo.getCreateTime();
			Date date = DateUtils.parse(createTime,
					DateUtils.formatStr_yyyyMMddHHmmss);
			Timestamp ts = new Timestamp(date.getTime());
			if (!"".equals(labMsgMainVo.getSubject())) {
				po.setSubject(labMsgMainVo.getSubject());
			} else {
				po.setSubject("无主题");
			}
			po.setType(labMsgMainVo.getType());
			po.setContent(labMsgMainVo.getContent());
			po.setCreateTime(ts.toString());
			labMsgMainDAO.addLabMsgMain(po);
			LabMsgMainVo vo = new LabMsgMainVo();
			BeanUtils.copyProperties(po, vo, new String[] { "" });
			return vo;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据用户名发送消息出错！" + e.getMessage());
		}

	}

	@Override
	public Map getLabMsgMainVoByType(String[] column, String[] value,
			String type) throws GlobalException {
		Map resultMap = new HashMap();
		resultMap.put("Column", value);
		String sel = "";
		if ((column != null) && (column.length > 0)) {
			for (int k = 0; k < column.length; ++k) {
				if ((column[k] != null) && (!("".equals(column[k])))) {
					sel = sel + column[k] + ",";
				}
			}
			String sell = sel.substring(0, sel.length() - 1);
			StringBuffer hql = new StringBuffer();
			if ("org.hibernate.dialect.SQLServerDialect".equals(PropertiesTools
					.getPropertiesValueByFileAndKey(
							"resources/jdbc.properties", "hibernate.dialect"))) {
				hql.append("SELECT TOP 10 " + sell
						+ " FROM LAB_MSG_DETAIL WHERE 1=1 ");
				hql.append(" AND type=1 ");
				hql.append(" ORDER BY createtime DESC  ");
			} else if ("org.hibernate.dialect.Oracle9Dialect"
					.equals(PropertiesTools.getPropertiesValueByFileAndKey(
							"resources/jdbc.properties", "hibernate.dialect"))) {
				hql.append("SELECT " + sell + " FROM LAB_MSG_MAIN WHERE 1=1 ");
				hql.append(" AND type=1 ");
				hql.append(" AND ROWNUM <= 10  ");
				hql.append(" ORDER BY createtime DESC  ");
			} else {
				hql.append("SELECT " + sell + " FROM LAB_MSG_MAIN WHERE 1=1 ");
				hql.append(" AND type=1 ");
				hql.append(" ORDER BY createtime DESC limit 10  ");
			}
			String[][] resultValue = null;

			List list = labMsgMainDAO.createSqlQuery(hql.toString());
			if ((list != null) && (list.size() > 0)) {
				resultValue = new String[list.size()][column.length];
				for (int i = 0; i < list.size(); ++i) {
					if (column.length > 1) {
						Object[] obj = (Object[]) list.get(i);
						for (int j = 0; j < column.length; ++j) {
							String temp = column[j];

							if (("ID".equals(temp)) || ("id".equals(temp)))
								if (obj[j] != null) {
									List detailList = labMsgDetailDAO
											.createSqlQuery("SELECT ID FROM LAB_MSG_DETAIL WHERE MAINID='"
													+ obj[j] + "'");
									if ((detailList != null)
											&& (detailList.size() > 0)) {
										Object id = detailList.get(0);
										if (id != null)
											resultValue[i][j] = String
													.valueOf(id);
										else
											resultValue[i][j] = "";
									}
								} else {
									resultValue[i][j] = "";
								}
							else if (("createtime".equals(temp))
									|| ("CREATETIME".equals(temp))) {
								if (obj[j] != null)
									resultValue[i][j] = DateUtils.format(
											(Timestamp) obj[j],
											DateUtils.formatStr_yyyyMMddHHmmss);
								else
									resultValue[i][j] = "";
							} else if (obj[j] != null)
								resultValue[i][j] = String.valueOf(obj[j]);
							else
								resultValue[i][j] = "";
						}
					} else {
						Object obj = list.get(i);
						String temp = column[0];

						if (("createtime".equals(temp))
								|| ("CREATETIME".equals(temp))) {
							if (obj != null)
								resultValue[i][0] = DateUtils.format(
										(Timestamp) obj,
										DateUtils.formatStr_yyyyMMddHHmmss);
							else {
								resultValue[i][0] = "";
							}
						} else if (obj != null)
							resultValue[i][0] = String.valueOf(obj);
						else {
							resultValue[i][0] = "";
						}
					}
				}
				resultMap.put("Value", resultValue);
			}
		}
		return resultMap;
	}
	@Override
	public LabMsgMainVo getLabMsgMain(String id) throws GlobalException {
		LabMsgMainVo mainVo=null;
		LabMsgMain main=labMsgMainDAO.getLabMsgMain(id);
		if(main!=null){
			mainVo=new LabMsgMainVo();
			mainVo.setId(main.getId());
			mainVo.setSubject(main.getSubject());
			mainVo.setContent(main.getContent());
			mainVo.setCreateTime(main.getCreateTime());
			mainVo.setReceiverIds(main.getReceiverIds());
			mainVo.setReceiverNames(main.getReceiverNames());
			if(main.getSender()!=null){
				mainVo.setSenderId(main.getSender().getId());
				mainVo.setSenderName(main.getSender().getName());
			}
		}
		return mainVo;
	}
	@Override
	public boolean updateLabMsgMain2Laji(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		if(labMsgMainVo==null) return false;
		String[] ids = labMsgMainVo.getIds();
		if(ids!=null&&ids.length>0){
			for (String id : ids) {
				if(id==null||id.trim().length()==0)
					continue;
				String hql="FROM LabMsgMain WHERE id='"+id.trim()+"'";
				if(!StrUtils.isBlankOrNull(labMsgMainVo.getReceiverIds())){
					hql+=" AND receiver.id='"+labMsgMainVo.getReceiverIds()+"'";
				}else if(!StrUtils.isBlankOrNull(labMsgMainVo.getSenderId())){
					hql+=" AND sender.id='"+labMsgMainVo.getSenderId()+"'";
				}
				List<LabMsgMain> poList =labMsgDetailDAO.find(hql);
				if(poList!=null&&poList.size()>0){
					for (LabMsgMain labMsgMain : poList) {
						labMsgMain.setPosition("3");
						labMsgMainDAO.updateLabMsgMain(labMsgMain);
					}
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteLabMsgMain(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		if(labMsgMainVo==null) return false;
		String[] ids = labMsgMainVo.getIds();
		if(ids!=null&&ids.length>0){
			for (String id : ids) {
				if(id==null||id.trim().length()==0)
					continue;
				String hql="FROM LabMsgMain WHERE id='"+id.trim()+"'";
				List<LabMsgMain> poList =labMsgMainDAO.find(hql);
				if(poList!=null&&poList.size()>0){
					for (LabMsgMain labMsgMain : poList) {
						labMsgMain.setIsDel(Constants_Common.Y);
						labMsgMainDAO.updateLabMsgMain(labMsgMain);
					}
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public PageResult getLabMsgMainPR4Sender(LabMsgMainVo labMsgMainVo,
			PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabMsgMain where 1=1 AND isDel='N'");
		
		if(labMsgMainVo.getSenderId()!=null&&!"".equals(labMsgMainVo.getSenderId())){
			hql.append(" AND sender.id = '"+labMsgMainVo.getSenderId()+"'");
		}
		if(labMsgMainVo.getPosition()!=null&&!"".equals(labMsgMainVo.getPosition())){
			hql.append(" AND position = '"+labMsgMainVo.getPosition()+"'");
		}
		if(labMsgMainVo.getSubject()!=null&&!"".equals(labMsgMainVo.getSubject())){
			hql.append(" AND subject LIKE '%"+labMsgMainVo.getSubject().trim()+"%'");
		}
		if(null != labMsgMainVo.getStartDate()&& !"".equals(labMsgMainVo.getStartDate()))
		{
			hql.append(" AND createTime >= '"+labMsgMainVo.getStartDate()+"'");
		}
		if(null != labMsgMainVo.getEndDate()&& !"".equals(labMsgMainVo.getEndDate()))
		{
			hql.append(" AND createTime <= '"+labMsgMainVo.getEndDate()+"'");
		}
		pageResult = labMsgMainDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgMainVo> li = new ArrayList<LabMsgMainVo>();
		List<LabMsgMain> list = pageResult.getResultList();
		if (null != list) {
			for (LabMsgMain labMsgMain : list) {
				LabMsgMainVo pom = new LabMsgMainVo();
				BeanUtils.copyProperties(labMsgMain, pom, new String[]{"sender"});
				li.add(pom);
			}
		} 
		pageResult.setResultList(li);
		return pageResult;
	}
	@Override
	public PageResult getLabMsgMainPR4CaoGao(LabMsgMainVo labMsgMainVo,
			PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabMsgMain where sender.id='"
				+ labMsgMainVo.getSenderId() + "' and position='2'");
		if(labMsgMainVo.getSubject()!=null&&!"".equals(labMsgMainVo.getSubject())){
			hql.append(" AND subject LIKE '%"+labMsgMainVo.getSubject().trim()+"%'");
		}
		if(null != labMsgMainVo.getStartDate()&& !"".equals(labMsgMainVo.getStartDate()))
		{
			hql.append(" AND createTime >= '"+labMsgMainVo.getStartDate()+"'");
		}
		if(null != labMsgMainVo.getEndDate()&& !"".equals(labMsgMainVo.getEndDate()))
		{
			hql.append(" AND createTime <= '"+labMsgMainVo.getEndDate()+"'");
		}
		pageResult = labMsgMainDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgMainVo> li = new ArrayList<LabMsgMainVo>();
		List<LabMsgMain> list = pageResult.getResultList();
		if (null != list) {
			for (LabMsgMain labMsgMain : list) {
				LabMsgMainVo pom = new LabMsgMainVo();
				pom.setId(labMsgMain.getId());
				pom.setSubject(labMsgMain.getSubject());
				pom.setCreateTime(labMsgMain.getCreateTime());
				pom.setReceiverNames(labMsgMain.getReceiverNames());
				li.add(pom);
			}
		} 
		pageResult.setResultList(li);
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabMsgMainPR4Recive(LabMsgMainVo labMsgMainVo,PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabMsgDetail where receiver.id='" + labMsgMainVo.getReceiverIds() + "'");
			hql.append(" AND position='1'");
		if (!StrUtils.isBlankOrNull(labMsgMainVo.getFlag())) // n=0查所有消息，n=1查已读消息，n=2查未读消息
			hql.append(" AND readFlag="+labMsgMainVo.getFlag()+"");// 0为未读消息
		
		if(labMsgMainVo.getSubject()!=null&&!"".equals(labMsgMainVo.getSubject())){
			hql.append(" AND msgMain.subject LIKE '%"+labMsgMainVo.getSubject().trim()+"%'");
		}
		if(labMsgMainVo.getSenderName()!=null&&!"".equals(labMsgMainVo.getSenderName())){
			hql.append(" AND sender.name LIKE '%"+labMsgMainVo.getSenderName().trim()+"%'");
		}
		if(null != labMsgMainVo.getStartDate()&& !"".equals(labMsgMainVo.getStartDate()))
		{
			hql.append(" AND  msgMain.createTime >= '"+labMsgMainVo.getStartDate()+"'");
		}
		if(null != labMsgMainVo.getEndDate()&& !"".equals(labMsgMainVo.getEndDate()))
		{
			hql.append(" AND  msgMain.createTime <= '"+labMsgMainVo.getEndDate()+"'");
		}
		hql.append(" ORDER BY readFlag asc");
		pageResult = labMsgMainDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgDetail> list = pageResult.getResultList();
		List<LabMsgMainVo> li = new ArrayList<LabMsgMainVo>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabMsgDetail pod = list.get(i);
				LabMsgMainVo vo = new LabMsgMainVo();
				if (null != pod.getSender()){
					vo.setSenderName(pod.getSender().getName());
					vo.setSenderId(pod.getSender().getId());
				}
				if(null!=pod.getMsgMain()){
					vo.setSubject(pod.getMsgMain().getSubject());
					vo.setCreateTime(pod.getMsgMain().getCreateTime());
					vo.setId(pod.getMsgMain().getId());
					vo.setDemo1(pod.getId());
				}
				vo.setFlag(pod.getReadFlag()+"");
				li.add(vo);
			}
		}
		pageResult.setResultList(li);
		return pageResult;
	}
	@Override
	public PageResult getLabMsgMainPR4Delete(LabMsgMainVo labMsgMainVo,
			PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabMsgDetail where 1=1");
		hql.append(" AND position='3'");
		hql.append(" AND (sender.id = '"+labMsgMainVo.getSenderId()+"' or receiver.id='" + labMsgMainVo.getSenderId() + "')");
		
		if(labMsgMainVo.getSubject()!=null&&!"".equals(labMsgMainVo.getSubject())){
			hql.append(" AND msgMain.subject LIKE '%"+labMsgMainVo.getSubject().trim()+"%'");
		}
		if(labMsgMainVo.getSenderName()!=null&&!"".equals(labMsgMainVo.getSenderName())){
			hql.append(" AND sender.name LIKE '%"+labMsgMainVo.getSenderName().trim()+"%'");
		}
		if(null != labMsgMainVo.getStartDate()&& !"".equals(labMsgMainVo.getStartDate()))
		{
			hql.append(" AND  msgMain.createTime >= '"+labMsgMainVo.getStartDate()+"'");
		}
		if(null != labMsgMainVo.getEndDate()&& !"".equals(labMsgMainVo.getEndDate()))
		{
			hql.append(" AND  msgMain.createTime <= '"+labMsgMainVo.getEndDate()+"'");
		}
		hql.append(" ORDER BY readFlag asc");
		pageResult = labMsgMainDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgDetail> list = pageResult.getResultList();
		List<LabMsgMainVo> li = new ArrayList<LabMsgMainVo>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabMsgDetail pod = list.get(i);
				LabMsgMainVo vo = new LabMsgMainVo();
				if (null != pod.getSender()){
					vo.setSenderId(pod.getSender().getId());
					vo.setSenderName(pod.getSenderName());
				}
				if(null!=pod.getMsgMain()){
					vo.setSubject(pod.getMsgMain().getSubject());
					vo.setCreateTime(pod.getMsgMain().getCreateTime());
					vo.setId(pod.getMsgMain().getId());
					vo.setDemo1(pod.getId());
				}
				li.add(vo);
			}
		}
		pageResult.setResultList(li);
		return pageResult;
	}
	
	@Override
	public LabMsgMainVo updateLabMsgMain(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		LabMsgMain po = (LabMsgMain) labMsgMainDAO.findById(LabMsgMain.class,
				labMsgMainVo.getId());
		if (null != labMsgMainVo.getSubject()&& !"".equals(labMsgMainVo.getSubject())){
			po.setSubject(labMsgMainVo.getSubject());
		}else{
			po.setSubject("无主题");
		}
		if (null != labMsgMainVo.getContent()&& !"".equals(labMsgMainVo.getContent()))
			po.setContent(labMsgMainVo.getContent());
		LabUser sender=labUserDAO.getLabUser(labMsgMainVo.getSenderId());
		po.setSender(sender);
		po.setReceiverIds(labMsgMainVo.getReceiverIds());
		po.setReceiverNames(labMsgMainVo.getReceiverNames());
		po.setPosition("2");
		po = (LabMsgMain) labMsgMainDAO.updateLabMsgMain(po);// 将消息主题部分加入lab_msg_main表
		if(null!=labMsgMainVo.getPosition()&&labMsgMainVo.getPosition().equals("0")){
			po.setPosition("0");
			po = (LabMsgMain) labMsgMainDAO.updateLabMsgMain(po);
			String[] s = labMsgMainVo.getReceiverIds().split(",");
			for (int i = 0; i < s.length; i++) {
				if(s[i]==null||s[i].trim().length()==0) continue;
				LabUser receiver = labUserDAO.getLabUser(s[i].trim());
				if (null == receiver) continue;
				LabMsgDetail pod = new LabMsgDetail();
				pod.setMsgMain(po);
				pod.setReceiver(receiver);
				pod.setReceiveName(receiver.getName());
				pod.setSender(sender);
				pod.setSenderName(sender.getName());
				pod.setReadFlag(0);
				pod.setPosition((short)1);
				labMsgDetailDAO.addLabMsgDetail(pod);// 将消息发送给指定的所有人
			}
			// 复制一份到发件箱
			LabMsgDetail pode = new LabMsgDetail();
			pode.setReceiver(sender);
			pode.setReceiveName(sender.getName());
			pode.setSender(sender);
			pode.setSenderName(sender.getName());
			pode.setMsgMain(po);
			pode.setPosition((short)0);
			labMsgDetailDAO.addLabMsgDetail(pode);
		}
		BeanUtils.copyProperties(po, labMsgMainVo, new String[]{""});
		return labMsgMainVo;
		}
	@Override
	public int getLabMsgMessage4CaoGaoCount(String userId)
			throws GlobalException {
		String hql="SELECT COUNT(*) FROM LabMsgMain where sender.id='"
				+ userId + "' and position='2'";
		List<Integer> n=labMsgMainDAO.find(hql);
		if(n!=null){
			int count=Integer.valueOf(n.get(0)+"");
			return count;
		}else{
			return 0;
		}
	}
	@Override
	public int getLabMsgMessage4DeleteCount(String userId)
			throws GlobalException {
		String hql="SELECT COUNT(*) FROM LabMsgDetail where position='3'";
		hql+=" AND (sender.id='" + userId+ "' or receiver.id ='" +userId+ "')";
		List<Integer> n=labMsgMainDAO.find(hql);
		if(n!=null){
			int count=Integer.valueOf(n.get(0)+"");
			return count;
		}else{
			return 0;
		}
	}
	@Override
	public int getLabMsgMessage4SenderCount(String userId)
			throws GlobalException {
		String hql="SELECT COUNT(*) FROM LabMsgMain where isDel='N' AND sender.id='"
			+ userId+ "' and position='0'";
		List<Integer> n=labMsgMainDAO.find(hql);
		if(n!=null){
			int count=Integer.valueOf(n.get(0)+"");
			return count;
		}else{
			return 0;
		}
	}
	@Override
	public int getLabMsgMessage4UnreadCount(String userId)
			throws GlobalException {
		String hql="SELECT COUNT(*) FROM LabMsgDetail where position='1' AND receiver.id='"
			+userId + "' and readFlag='0'";
		List n=labMsgMainDAO.find(hql);
		if(n!=null){
			int count=Integer.valueOf(n.get(0)+"");
			return count;
		}else{
			return 0;
		}
	}
	@Override
	public boolean deleteLabMsgMain4CaoGao(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		boolean flag=false;
		if(!StrUtils.isBlankOrNull(labMsgMainVo.getId())){
			for(String id:labMsgMainVo.getId().split(",")){
				LabMsgMain main=labMsgMainDAO.getLabMsgMain(id);
				labMsgMainDAO.deleteLabMsgMain(main);
				flag=true;
			}
		}
		
		return flag;
	}
	@Override
	public LabMsgMainVo getLabMsgMainBydetailId(String id) throws GlobalException {
		LabMsgMainVo mainVo=new LabMsgMainVo();
		LabMsgDetail labMsgDetail=(LabMsgDetail) labMsgDetailDAO.findById(LabMsgDetail.class, id);
		if(labMsgDetail!=null){
			mainVo.setId(labMsgDetail.getId());
			mainVo.setSubject(labMsgDetail.getMsgMain().getSubject());
			mainVo.setContent(labMsgDetail.getMsgMain().getContent());
			mainVo.setCreateTime(labMsgDetail.getCreateTime());
			mainVo.setReceiverIds(labMsgDetail.getMsgMain().getReceiverIds());
			mainVo.setReceiverNames(labMsgDetail.getMsgMain().getReceiverNames());
			mainVo.setDemo1(labMsgDetail.getMsgMain().getId());
			if(labMsgDetail.getSender()!=null){
				mainVo.setSenderId(labMsgDetail.getSender().getId());
				mainVo.setSenderName(labMsgDetail.getSender().getName());
			}
		}
		return mainVo;
	}
}
