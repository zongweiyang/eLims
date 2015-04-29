package cn.labsoft.labos.base.message.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.message.dao.ILabMsgDetailDAO;
import cn.labsoft.labos.base.message.dao.ILabMsgMainDAO;
import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.base.message.entity.LabMsgMain;
import cn.labsoft.labos.base.message.service.ILabMsgDetailService;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.message.vo.LabMsgMainVo;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.dao.ILabUserOrgDAO;
import cn.labsoft.labos.base.user.dao.ILabUserRoleDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.base.user.entity.LabUserRole;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Service("labMsgDetailService")
public class LabMsgDetailServiceImpl implements ILabMsgDetailService {
	private ILabMsgMainDAO labMsgMainDAO;
	private ILabMsgDetailDAO labMsgDetailDAO;
	private ILabUserDAO labUserDAO;
	private ILabUserOrgDAO labUserOrgDAO;
	private ILabUserRoleDAO labUserRoleDAO;
	private ILabUploadDAO labUploadDAO;
	
	
	@Resource
	public void setLabMsgMainDAO(ILabMsgMainDAO labMsgMainDAO) {
		this.labMsgMainDAO = labMsgMainDAO;
	}
	@Resource
	public void setLabMsgDetailDAO(ILabMsgDetailDAO labMsgDetailDAO) {
		this.labMsgDetailDAO = labMsgDetailDAO;
	}
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}
	@Resource
	public void setLabUserOrgDAO(ILabUserOrgDAO labUserOrgDAO) {
		this.labUserOrgDAO = labUserOrgDAO;
	}
	@Resource
	public void setLabUserRoleDAO(ILabUserRoleDAO labUserRoleDAO) {
		this.labUserRoleDAO = labUserRoleDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabMsgDetailList(LabMsgDetailVo labMsgDetailVo,PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabMsgDetail where receiver='" + labMsgDetailVo.getReceiveId() + "'");
			hql.append(" AND position='1'");// 1为收到的消息 2为发送的消息 3为删除的消息 4为草稿
		if (labMsgDetailVo.getFlag() == 1) // n=0查所有消息，n=1查已读消息，n=2查未读消息
			hql.append(" AND readFlag='0'");// 0为未读消息
		if (labMsgDetailVo.getFlag() == 2)
			hql.append(" AND readFlag='1'");// 1为已读消息
		if(labMsgDetailVo.getSubject()!=null&&!"".equals(labMsgDetailVo.getSubject())){
			hql.append(" AND msgMain.subject LIKE '%"+labMsgDetailVo.getSubject().trim()+"%'");
		}
		if(labMsgDetailVo.getSenderName()!=null&&!"".equals(labMsgDetailVo.getSenderName())){
			hql.append(" AND sender.name LIKE '%"+labMsgDetailVo.getSenderName().trim()+"%'");
		}
		if(null != labMsgDetailVo.getStartDate()&& !"".equals(labMsgDetailVo.getStartDate()))
		{
			hql.append(" AND  msgMain.createTime >= '"+labMsgDetailVo.getStartDate()+"'");
		}
		if(null != labMsgDetailVo.getEndDate()&& !"".equals(labMsgDetailVo.getEndDate()))
		{
			hql.append(" AND  msgMain.createTime <= '"+labMsgDetailVo.getEndDate()+"'");
		}
		hql.append(" ORDER BY readFlag asc");
		pageResult = labMsgDetailDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgDetail> list = pageResult.getResultList();
		List<LabMsgDetailVo> li = new ArrayList<LabMsgDetailVo>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabMsgDetail pod = new LabMsgDetail();
				pod = list.get(i);
				LabMsgDetailVo vo = new LabMsgDetailVo();
				if (null != pod.getId() && !"".equals(pod.getId()))
					vo.setId(pod.getId());
				if (null != pod.getSender().getName()
						&& !"".equals(pod.getSender().getName()))
					vo.setSenderName(pod.getSender().getName());
				if(null!=pod.getMsgMain()){
				if (null != pod.getMsgMain().getSubject()
						&& !"".equals(pod.getMsgMain().getSubject()))
					vo.setSubject(pod.getMsgMain().getSubject());
				if (null != pod.getMsgMain().getCreateTime().toString()
						&& !"".equals(pod.getMsgMain().getCreateTime()
								.toString()))
					vo.setCreateTime(pod.getMsgMain().getCreateTime());
				}
				if (!"".equals(pod.getReadFlag()))
					vo.setReadFlag((short) pod.getReadFlag());
				li.add(vo);
			}
		}
		pageResult.setResultList(li);
		return pageResult;
	}

	@Override
	public LabMsgDetailVo showLabMsgById(String id) throws GlobalException {
		LabMsgDetail po = new LabMsgDetail();
	    po = (LabMsgDetail) labMsgDetailDAO.findById(LabMsgDetail.class, id);
		po.setReadFlag(1);
		labMsgDetailDAO.updateLabMsgDetail(po);
		LabMsgDetailVo vo = new LabMsgDetailVo();
		if (null != po.getId() && !"".equals(po.getId()))
			vo.setId(po.getId());
		if (null != po.getSender().getName()
				&& !"".equals(po.getSender().getName()))
			vo.setSenderName(po.getSender().getName());
		if (null != po.getMsgMain().getSubject()
				&& !"".equals(po.getMsgMain().getSubject()))
			vo.setSubject(po.getMsgMain().getSubject());
		if (null != po.getMsgMain().getContent()
				&& !"".equals(po.getMsgMain().getContent()))
			vo.setContent(po.getMsgMain().getContent());
		if (null != po.getMsgMain().getAttachName()
				&& !"".equals(po.getMsgMain().getAttachName()))
			vo.setAttachName(po.getMsgMain().getAttachName());
		if (null != po.getMsgMain().getId()
				&& !"".equals(po.getMsgMain().getId()))
			vo.setMainMsgID(po.getMsgMain().getId());
		if (null != po.getReceiveName()
				&& !"".equals(po.getReceiveName())) {
			 vo.setReceiveId(po.getReceiveName());
		}
		if (null != po.getReceiver().getName()
				&& !"".equals(po.getReceiver().getName())) {
			 vo.setReceiveName(po.getReceiver().getName());
		}
		return vo;
	}

	public boolean deleteLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		String[] ids = labMsgDetailVo.getIds();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			LabMsgDetail po = new LabMsgDetail();
			po = (LabMsgDetail) labMsgDetailDAO.findById(LabMsgDetail.class, id);
			po.setPosition((short) 3);
			labMsgDetailDAO.updateLabMsgDetail(po);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public String getReceiverNameById(String id) throws GlobalException {
		StringBuffer receiver = new StringBuffer();
		String hql = "FROM LabMsgDetail where msgMain.id='" + id
				+ "' and position='1'";
		List<LabMsgDetail> list = labMsgDetailDAO.find(hql);
		LabMsgDetail po = new LabMsgDetail();
		for (int i = 0; i < list.size(); i++) {
			po = list.get(i);
			String name = po.getReceiver().getName();
			if (i > 0)
				receiver.append("," + name);
			else {
				receiver.append(name);
			}
		}
		String sreceiver = receiver.toString();
		return sreceiver;
	}

	@SuppressWarnings("unchecked")
	public PageResult isDeleteLabMsgList(LabMsgDetailVo labMsgDetailVo, PageResult pageResult)
			throws GlobalException {
		StringBuffer hql = new StringBuffer(
				"FROM LabMsgDetail where (receiver='" + labMsgDetailVo.getSenderId() + "' or sender='"
						+ labMsgDetailVo.getSenderId() + "')");
		hql.append(" AND position='3'");
		if(labMsgDetailVo.getSubject()!=null&&!"".equals(labMsgDetailVo.getSubject())){
			hql.append(" AND msgMain.subject LIKE '%"+labMsgDetailVo.getSubject().trim()+"%'");
		}
		if(labMsgDetailVo.getSenderName()!=null&&!"".equals(labMsgDetailVo.getSenderName())){
			hql.append(" AND sender.name LIKE '%"+labMsgDetailVo.getSenderName().trim()+"%'");
		}
		if(null != labMsgDetailVo.getStartDate()&& !"".equals(labMsgDetailVo.getStartDate()))
		{
			hql.append(" AND  msgMain.createTime >= '"+labMsgDetailVo.getStartDate()+"'");
		}
		if(null != labMsgDetailVo.getEndDate()&& !"".equals(labMsgDetailVo.getEndDate()))
		{
			hql.append(" AND  msgMain.createTime <= '"+labMsgDetailVo.getEndDate()+"'");
		}
		pageResult = labMsgDetailDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgDetail> list = pageResult.getResultList();
		LabMsgDetail po = new LabMsgDetail();
		LabMsgMain pom = new LabMsgMain();
		List<LabMsgDetailVo> li = new ArrayList<LabMsgDetailVo>();
		if (null != list && !"".equals(list)) {
			for (int i = 0; i < list.size(); i++) {
				po = list.get(i);
				LabMsgDetailVo vo = new LabMsgDetailVo();
				vo.setId(po.getId());
				pom = (LabMsgMain) labMsgMainDAO.findById(LabMsgMain.class, po
						.getMsgMain().getId());
				String s = pom.getId();
				vo.setReceivers(getReceiverNameById(s));
				if (null != po.getSender().getName()&& !"".equals(po.getSender().getName()))
					vo.setSenderName(po.getSender().getName());
				if (null != po.getMsgMain().getSubject()
						&& !"".equals(po.getMsgMain().getSubject()))
					vo.setSubject(po.getMsgMain().getSubject());
				if (null != po.getMsgMain().getCreateTime()
						&& !"".equals(po.getMsgMain().getCreateTime()))
					vo.setCreateTime(po.getMsgMain().getCreateTime());
				li.add(vo);
			}
			pageResult.setResultList(li);
		}
		return pageResult;
	}

	@Override
	public void saveLabCaoGao(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		LabUser sender = (LabUser) labUserDAO.getLabUser(labMsgDetailVo.getSenderId());
		if(sender!=null){
			LabMsgMain po = new LabMsgMain();
			if (null != labMsgDetailVo.getSubject()&& !"".equals(labMsgDetailVo.getSubject())){
				po.setSubject(labMsgDetailVo.getSubject());
			}else{
				po.setSubject("无主题");
			}
			if (null != labMsgDetailVo.getContent()
					&& !"".equals(labMsgDetailVo.getContent()))
				po.setContent(labMsgDetailVo.getContent());
			if (null != labMsgDetailVo.getDemo2()
					&& !"".equals(labMsgDetailVo.getDemo2()))
				po.setAttachName(labMsgDetailVo.getDemo2());
			if (null != labMsgDetailVo.getType()
					&& !"".equals(labMsgDetailVo.getType()))
				po.setType(Short.parseShort(labMsgDetailVo.getType()));
			po.setSender(sender);
			po.setSenderName(sender.getName());
			po.setReceiverIds(labMsgDetailVo.getReceiveId());
			po.setReceiverNames(labMsgDetailVo.getReceiveName());
			po.setPosition("2");
			labMsgMainDAO.addLabMsgMain(po);// 将消息主题部分加入lab_msg_main表
			// 修改上传logo的busId
			List<LabUpload> loadList = labUploadDAO.getLabUploadList(labMsgDetailVo
					.getUuid(), "msg");
			if (loadList != null) {
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(po.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String findIdByName(String name) throws GlobalException {
		String hql = "FROM LabUser WHERE name='" + name + "'";
		List<LabUser> list = labUserDAO.find(hql);
		String id ="";
		if(list.size()>0){
		LabUser sysUser = list.get(0);
		id = sysUser.getId();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public LabUser findIdById(String id) throws GlobalException {
		String hql = "FROM LabUser WHERE id='" + id + "'";
		List<LabUser> list = labUserDAO.find(hql);
		LabUser sysUser = list.get(0);
		return sysUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabCaoGaoList(LabMsgDetailVo labMsgDetailVo,PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabMsgMain where sender.id='"
				+ labMsgDetailVo.getSenderId() + "' and position='2'");
		
		if(labMsgDetailVo.getSubject()!=null&&!"".equals(labMsgDetailVo.getSubject())){
			hql.append(" AND subject LIKE '%"+labMsgDetailVo.getSubject().trim()+"%'");
		}
		if(null != labMsgDetailVo.getStartDate()&& !"".equals(labMsgDetailVo.getStartDate()))
		{
			hql.append(" AND createTime >= '"+labMsgDetailVo.getStartDate()+"'");
		}
		if(null != labMsgDetailVo.getEndDate()&& !"".equals(labMsgDetailVo.getEndDate()))
		{
			hql.append(" AND createTime <= '"+labMsgDetailVo.getEndDate()+"'");
		}
		pageResult = labMsgDetailDAO.getPageResult(hql.toString(), pageResult);
		List<LabMsgMainVo> li = new ArrayList<LabMsgMainVo>();
		List<LabMsgMain> list = pageResult.getResultList();
		if (null != list) {
			for (LabMsgMain labMsgMain : list) {
				LabMsgMainVo pom = new LabMsgMainVo();
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
	public String findNameByid(String id) throws GlobalException {
		String hql = "FROM LabUser WHERE id='" + id + "'";
		List<LabUser> list = labUserDAO.find(hql);
		if (null != list && list.size() > 0) {
			String name = list.get(0).getName();
			return name;
		} else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUnreadLabMsg(String id) throws GlobalException {
		String hql = "FROM LabMsgDetail WHERE receiver='" + id
				+ "' and position='1' and readFlag='0'";
		List<LabMsgDetail> list = labMsgDetailDAO.find(hql);
		int count = list.size();
		return count;
	}

	@Override
	public long getUnreadLabMsgMessage(String loginname) throws GlobalException {
		String hql = "SELECT COUNT(*) FROM LabMsgDetail mes where mes.position='"
				+ 1
				+ "'"
				+ "  and mes.receiver.loginName='"
				+ loginname
				+ "'"
				+ " and mes.readflag=0";
		return labMsgDetailDAO.getCountSize(hql);
	}

	@Override
	public boolean sendLabMsgToPerson(String senderId, String receiverId,
			String messageTitle, String messageContent) throws GlobalException {
		try {
			// 存入消息主表
			LabMsgMain po = new LabMsgMain();
			Date date = DateUtils.parseToDate(DateUtils.getCurrDateTimeStr());
			Timestamp ts = new Timestamp(date.getTime());
			po.setCreateTime(ts.toString());
			if (!"".equals(messageTitle)) {
				po.setSubject(messageTitle);
			} else {
				po.setSubject("无主题");
			}
			po.setType(Short.parseShort("0"));
			po.setContent(messageContent);
			po.setAttachName("<p>&nbsp;</p>");
			labMsgMainDAO.addLabMsgMain(po);
			// 存入消息从表(发给收件人)
			LabMsgDetail detail = new LabMsgDetail();
			LabUser sender1 = (LabUser) labUserDAO.findById(LabUser.class,
					senderId);
			LabUser receiver1 = (LabUser) labUserDAO.findById(LabUser.class,
					receiverId);
			detail.setReceiver(receiver1);
			detail.setMsgMain(po);
			detail.setSender(sender1);
			detail.setPosition((short) 1);
			detail.setReadFlag((short) 0);
			labMsgDetailDAO.addLabMsgDetail(detail);
			// 给自己发一份(已发邮件)
			LabMsgDetail detail2 = new LabMsgDetail();
			LabUser sender2 = (LabUser) labUserDAO.findById(LabUser.class,
					senderId);
			detail2.setReceiver(sender2);
			detail2.setMsgMain(po);
			detail2.setSender(sender2);
			detail2.setPosition((short) 0);
			detail2.setReadFlag((short) 1);
			labMsgDetailDAO.addLabMsgDetail(detail2);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean sendOLabMsgToRole(String senderId, String roleId,
			String messageTitle, String messageContent) throws GlobalException {
		try {
			List<LabUserRole> userRoleList = labUserRoleDAO
					.getLabUserRoleListByRoleId(roleId);
			// 存入消息主表
			LabMsgMain po = new LabMsgMain();
			Date date = DateUtils.parseToDate(DateUtils.getCurrDateTimeStr());
			Timestamp ts = new Timestamp(date.getTime());
			po.setCreateTime(ts.toString());
			if (!"".equals(messageTitle)) {
				po.setSubject(messageTitle);
			} else {
				po.setSubject("无主题");
			}
			po.setType(Short.parseShort("0"));
			po.setContent(messageContent);
			po.setAttachName("<p>&nbsp;</p>");
			labMsgMainDAO.addLabMsgMain(po);
			// 存入消息从表(发给收件人)
			for (LabUserRole ur : userRoleList) {
				LabUser user = ur.getUser();
				LabMsgDetail detail = new LabMsgDetail();
				LabUser sender1 = (LabUser) labUserDAO.findById(LabUser.class,
						senderId);

				detail.setReceiver(user);
				detail.setMsgMain(po);
				detail.setSender(sender1);
				detail.setPosition((short) 1);
				detail.setReadFlag((short) 0);
				labMsgDetailDAO.addLabMsgDetail(detail);
				// 给自己发一份(已发邮件)
				LabMsgDetail detail2 = new LabMsgDetail();
				LabUser sender2 = (LabUser) labUserDAO.findById(LabUser.class,
						senderId);
				detail2.setReceiver(sender2);
				detail2.setMsgMain(po);
				detail2.setSender(sender2);
				detail2.setPosition((short) 0);
				detail2.setReadFlag((short) 1);
				labMsgDetailDAO.addLabMsgDetail(detail2);
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean sendLabMsgToOrg(String senderId, String orgId,
			String messageTitle, String messageContent) throws GlobalException {
		try {
			List<LabUserOrg> userorgList = labUserOrgDAO
					.getLabUserOrgListByOrgId(orgId);
			// 存入消息主表
			LabMsgMain po = new LabMsgMain();
			Date date = DateUtils.parseToDate(DateUtils.getCurrDateTimeStr());
			Timestamp ts = new Timestamp(date.getTime());
			po.setCreateTime(ts.toString());
			if (!"".equals(messageTitle)) {
				po.setSubject(messageTitle);
			} else {
				po.setSubject("无主题");
			}
			po.setType(Short.parseShort("0"));
			po.setContent(messageContent);
			po.setAttachName("<p>&nbsp;</p>");
			labMsgMainDAO.addLabMsgMain(po);
			// 存入消息从表(发给收件人)
			for (LabUserOrg uo : userorgList) {
				LabUser user = uo.getUser();
				LabMsgDetail detail = new LabMsgDetail();
				LabUser sender1 = (LabUser) labUserDAO.findById(LabUser.class,
						senderId);

				detail.setReceiver(user);
				detail.setMsgMain(po);
				detail.setSender(sender1);
				detail.setPosition((short) 1);
				detail.setReadFlag((short) 0);
				labMsgDetailDAO.addLabMsgDetail(detail);
				// 给自己发一份(已发邮件)
				LabMsgDetail detail2 = new LabMsgDetail();
				LabUser sender2 = (LabUser) labUserDAO.findById(LabUser.class,
						senderId);
				detail2.setReceiver(sender2);
				detail2.setMsgMain(po);
				detail2.setSender(sender2);
				detail2.setPosition((short) 0);
				detail2.setReadFlag((short) 1);
				labMsgDetailDAO.addLabMsgDetail(detail2);
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public long getCountLabMsgMessageDetail(LabUserVo vo)
			throws GlobalException {
		try {

			String hql = "select count(*) from LabMsgDetail where receiver.id='"
					+ vo.getId()
					+ "' and "
					+ "position='"
					+ 1
					+ "'"
					+ " and readFlag=0";
			return labMsgDetailDAO.getCountSize(hql);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据条件获得消息记录条数出错！" + e.getMessage());
		}

	}

	@Override
	public void updateLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		LabMsgMain po = (LabMsgMain) labMsgMainDAO.findById(LabMsgMain.class,
				labMsgDetailVo.getMainMsgID());
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		if (null != labMsgDetailVo.getSubject()&& !"".equals(labMsgDetailVo.getSubject())){
			po.setSubject(labMsgDetailVo.getSubject());
		}else{
			po.setSubject("无主题");
		}
		if (null != labMsgDetailVo.getContent()
				&& !"".equals(labMsgDetailVo.getContent()))
			po.setContent(labMsgDetailVo.getContent());
		if (null != labMsgDetailVo.getDemo2()
				&& !"".equals(labMsgDetailVo.getDemo2()))
			po.setAttachName(labMsgDetailVo.getDemo2());
		if (null != labMsgDetailVo.getType()
				&& !"".equals(labMsgDetailVo.getType()))
			po.setType(Short.parseShort(labMsgDetailVo.getType()));
		po.setCreateTime(ts.toString());
		po = (LabMsgMain) labMsgMainDAO.updateLabMsgMain(po);// 将消息主题部分加入lab_msg_main表
		LabMsgDetail pode = (LabMsgDetail) labMsgMainDAO.findById(LabMsgDetail.class, labMsgDetailVo.getId());
		LabUser pos = new LabUser();
		pos = (LabUser) labUserDAO.findById(LabUser.class, labMsgDetailVo
				.getSenderId());
		if (null != pos)
		    pode.setReceiver(pos);
		if (null != labMsgDetailVo.getReceiveName()
				&& !"".equals(labMsgDetailVo.getReceiveName())) {
			String[] s = labMsgDetailVo.getReceiveName().split(",");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				s[i] = findIdByName(s[i]);
				if (i > 0)
					sb.append("," + s[i]);
				else
					sb.append(s[i]);
			}
			pode.setReceiveName(sb.toString());
		}
		pode.setSender(pos);
		pode.setMsgMain(po);
		pode.setPosition((short) 2);
		try {
			labMsgDetailDAO.updateLabMsgDetail(pode);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}// 将消息发给自己
	}

	@Override
	public LabMsgDetailVo getLabMsgDetailById(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		try {
			LabMsgDetailVo vo = new LabMsgDetailVo();
			LabMsgDetail po = (LabMsgDetail) this.labMsgDetailDAO.findById(
					LabMsgDetail.class, labMsgDetailVo.getId());
				
			if (null != po.getMsgMain()) {
				vo.setMainMsgID(po.getId());
				vo.setSubject(po.getMsgMain().getSubject());
				vo.setContent(po.getMsgMain().getContent());
			}
			vo.setId(po.getId());
			vo.setSenderName(po.getSender().getName());
			vo.setReceiveName(po.getReceiveName());
			vo.setPosition(po.getPosition());
			return vo;
		} catch (Exception e) {
			throw new GlobalException(" 根据ID得到消息从表信息出错！" + e.getMessage());
		}
	}

	@Override
	public boolean showlabMsgDetailAll(String[] ids, int flag)
			throws GlobalException {
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					LabMsgDetail po = (LabMsgDetail) labMsgDetailDAO.findById(
							LabMsgDetail.class, id);
					po.setReadFlag(flag);
					labMsgDetailDAO.updateLabMsgDetail(po);
				}
			}
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCaogaoLabMsg(String id) throws GlobalException {
		String hql = "FROM LabMsgDetail where sender='" + id
				+ "' and position='2'";
		List<LabMsgDetail> list = labMsgDetailDAO.find(hql);
		int count = list.size();
		return count;
	}

	@Override
	public long getCaogaoLabMsgMessage(String loginName) throws GlobalException {
		String hql = "SELECT COUNT(*) FROM LabMsgDetail mes where mes.position='"
				+ 2 + "'" + "  and mes.receiver.loginName='" + loginName + "'";
		return labMsgDetailDAO.getCountSize(hql);
	}

	@Override
	public long getCaogaoLabMsgMessageDetail(LabUserVo vo)
			throws GlobalException {
		try {

			String hql = "select count(*) from LabMsgDetail where receiver.id='"
					+ vo.getId() + "' and " + "position='" + 2 + "'";
			return labMsgDetailDAO.getCountSize(hql);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据条件获得消息记录条数出错！" + e.getMessage());
		}

	}

	@Override
	public long getSendLabMsgMessageDetail(LabUserVo vo) throws GlobalException {
		try {

			String hql = "select count(*) from LabMsgDetail where receiver.id='"
					+ vo.getId() + "' and " + "position='" + 0 + "'";
			return labMsgDetailDAO.getCountSize(hql);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据条件获得消息记录条数出错！" + e.getMessage());
		}

	}

	@Override
	public long getIsDeleteLabMsgMessageDetail(LabUserVo vo)
			throws GlobalException {
		try {

			String hql = "SELECT count(*) FROM LabMsgDetail where (receiver.id='"
					+ vo.getId()
					+ "'OR sender.id='"
					+ vo.getId()
					+ " ') AND "
					+ "position='" + 3 + "'";
			return labMsgDetailDAO.getCountSize(hql);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据条件获得消息记录条数出错！" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendCaoGaoLabMsgDetail(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		LabMsgMain po = (LabMsgMain) labMsgMainDAO.findById(LabMsgMain.class,
				labMsgDetailVo.getMainMsgID());
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		if (null != labMsgDetailVo.getSubject()
				&& !"".equals(labMsgDetailVo.getSubject()))
			po.setSubject(labMsgDetailVo.getSubject());
		if (null != labMsgDetailVo.getContent()
				&& !"".equals(labMsgDetailVo.getContent()))
			po.setContent(labMsgDetailVo.getContent());
		if (null != labMsgDetailVo.getDemo2()
				&& !"".equals(labMsgDetailVo.getDemo2()))
			po.setAttachName(labMsgDetailVo.getDemo2());
		if (null != labMsgDetailVo.getType()
				&& !"".equals(labMsgDetailVo.getType()))
			po.setType(Short.parseShort(labMsgDetailVo.getType()));
		po.setCreateTime(ts.toString());
		po = (LabMsgMain) labMsgMainDAO.updateLabMsgMain(po);// 将消息主题部分加入lab_msg_main表
		
		
		String names="";
		LabUser sender = (LabUser) labUserDAO.getLabUser(labMsgDetailVo.getSenderId());
		if (labMsgDetailVo.getType().equals("0")&&sender!=null) {// 0为消息，1为公告
			String[] s = labMsgDetailVo.getReceiveId().split(",");
			for (int i = 0; i < s.length; i++) {
				if(s[i]==null||s[i].trim().length()==0)
					continue;
				LabUser receiver = labUserDAO.getLabUser(s[i].trim());
				if (null != receiver) continue;
				LabMsgDetail pod = new LabMsgDetail();
				pod.setMsgMain(po);
				pod.setReceiver(receiver);
				pod.setReceiveName(receiver.getName());
				pod.setSender(sender);
				pod.setSenderName(sender.getName());
				pod.setReadFlag(0);
				pod.setPosition((short) 1);
				labMsgDetailDAO.addLabMsgDetail(pod);// 将消息发送给指定的所有人
				names+=receiver.getName()+",";
			}
			if(names.endsWith(",")){
				names=names.substring(0, names.length()-1);
			}
		}
		
		po.setReceiverNames(names);
		labMsgMainDAO.updateLabMsgMain(po);
		
		// 复制一份到发件箱
		LabMsgDetail pode = (LabMsgDetail) labMsgMainDAO.findById(LabMsgDetail.class, labMsgDetailVo.getId());
		pode.setReceiver(sender);
		pode.setReceiveName(sender.getName());
		pode.setSender(sender);
		pode.setSenderName(sender.getName());
		pode.setMsgMain(po);
		pode.setPosition((short) 0);
		labMsgDetailDAO.updateLabMsgDetail(pode);
		
	}
	@Override
	public void updateLabMsgDetail4IsDelete(LabMsgDetailVo labMsgDetailVo)throws GlobalException {
		String[] ids = labMsgDetailVo.getIds();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			LabMsgDetail po = new LabMsgDetail();
			po = (LabMsgDetail) labMsgDetailDAO.findById(LabMsgDetail.class, id);
			po.setPosition((short)1);
			labMsgDetailDAO.updateLabMsgDetail(po);
		}
	}
	@Override
	public LabMsgDetailVo updateLabMsg4Readed(LabMsgDetailVo labMsgDetailVo)
			throws GlobalException {
		String hql="FROM LabMsgDetail WHERE id='"+labMsgDetailVo.getId()+"'";
		hql+=" AND receiver.id='"+labMsgDetailVo.getReceiveId()+"'";
		hql+=" AND readFlag='0'";
		LabMsgDetail obj=(LabMsgDetail)labMsgDetailDAO.find(hql,0);
		if(obj!=null){
			obj.setReadFlag(1);
			labMsgDetailDAO.updateLabMsgDetail(obj);
		}
		return labMsgDetailVo;
	}
	@Override
	public boolean deleteLabMsgDetail4Real(LabMsgDetailVo labMsgDetailVo) throws GlobalException {
		String[] ids = labMsgDetailVo.getIds();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			LabMsgDetail po = new LabMsgDetail();
			po = (LabMsgDetail) labMsgDetailDAO.findById(LabMsgDetail.class, id);
			labMsgDetailDAO.deleteLabMsgDetail(po);
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabMsgDetail(LabMsgMainVo labMsgMainVo)
			throws GlobalException {
		if(labMsgMainVo==null) return false;
		String[] ids = labMsgMainVo.getIds();
		if(ids!=null&&ids.length>0){
			for (String id : ids) {
				if(id==null||id.trim().length()==0)
					continue;
				String hql="FROM LabMsgDetail WHERE id='"+id.trim()+"'";
				List<LabMsgDetail> poList =labMsgDetailDAO.find(hql);
				if(poList!=null&&poList.size()>0){
					for (LabMsgDetail labMsgDetail : poList) {
						labMsgDetailDAO.deleteLabMsgDetail(labMsgDetail);
					}
				}
			}
			return true;
		}
		return false;
	}
}

