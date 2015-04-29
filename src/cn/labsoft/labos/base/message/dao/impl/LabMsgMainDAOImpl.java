package cn.labsoft.labos.base.message.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.message.dao.ILabMsgMainDAO;
import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.base.message.entity.LabMsgMain;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labMsgMainDAO")
public class LabMsgMainDAOImpl extends BaseDAO implements ILabMsgMainDAO {


	@Override
	public LabMsgMain addLabMsgMain(LabMsgMain labMsgMain) throws GlobalException {
		try {
		super.save(labMsgMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMsgMain;
	}

	@Override
	public boolean deleteLabMsgMain(LabMsgMain labMsgMain) throws GlobalException {
		try {
		super.delete(labMsgMain);
		return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabMsgMain getLabMsgMain(String id) throws GlobalException {
		LabMsgMain main=null;
		try {
			main=(LabMsgMain)super.findById(LabMsgMain.class,id);
		} catch (Exception ex) {
			Log4J.error("LabMsgMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return main;
	}

	@Override
	public LabMsgMain updateLabMsgMain(LabMsgMain labMsgMain) throws GlobalException {
		try {
		super.update(labMsgMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMsgMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMsgMain;
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
			super.save(po);
			// 存入消息从表(发给收件人)
			LabMsgDetail detail = new LabMsgDetail();
			LabUser sender1 = (LabUser) super.findById(LabUser.class, senderId);
			LabUser receiver1 = (LabUser) super.findById(LabUser.class,
					receiverId);
			detail.setReceiver(receiver1);
			detail.setMsgMain(po);
			detail.setSender(sender1);
			detail.setPosition((short) 1);
			detail.setReadFlag((short) 0);
			super.save(detail);
			// 给自己发一份(已发邮件)
			LabMsgDetail detail2 = new LabMsgDetail();
			LabUser sender2 = (LabUser) super.findById(LabUser.class, senderId);
			detail2.setReceiver(sender2);
			detail2.setMsgMain(po);
			detail2.setSender(sender2);
			detail2.setPosition((short) 0);
			detail2.setReadFlag((short) 1);
			super.save(detail2);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabMsgMainDAOImpl error...." + ex.getMessage(), ex);
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
	}

	/*@Override
	public boolean sendLabMsgToStation(String senderId, String orgId,
			String stationName, String messageTitle, String messageContent) {
		try {
			List<LabUser> userList;

			StringBuffer userSQL = new StringBuffer();
			userSQL.append(" FROM SysUser s WHERE 1=1 ");
			userSQL
					.append(" AND s.id in (select ss.sysUser.id FROM SysUserStation ss WHERE 1=1 AND ss.sysStation.id in( select st.id FROM SysStation st WHERE 1=1 AND st.stationname ='"
							+ stationName
							+ "' ) )  AND s.sysOrg.id ='"
							+ orgId
							+ "' ");

			userList = super.find(userSQL.toString());
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
			super.save(po);
			// 存入消息从表(发给收件人)
			for (LabUser user : userList) {
				LabMsgDetail detail = new LabMsgDetail();
				LabUser sender1 = (LabUser) super.findById(LabUser.class,
						senderId);

				detail.setReceiver(user);
				detail.setDemo2(user.getId());// 存放收件人的id
				detail.setMsgMain(po);
				detail.setSender(sender1);
				detail.setPosition((short) 1);
				detail.setReadFlag((short) 0);
				super.save(detail);
				// 给自己发一份(已发邮件)
				LabMsgDetail detail2 = new LabMsgDetail();
				LabUser sender2 = (LabUser) super.findById(LabUser.class,
						senderId);
				detail2.setReceiver(sender2);
				detail2.setDemo2(senderId);// 存放收件人的id
				detail2.setMsgMain(po);
				detail2.setSender(sender2);
				detail2.setPosition((short) 0);
				detail2.setReadFlag((short) 1);
				super.save(detail2);
			}
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}*/
}
