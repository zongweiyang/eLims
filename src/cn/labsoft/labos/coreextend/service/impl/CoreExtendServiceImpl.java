package cn.labsoft.labos.coreextend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.message.dao.ILabMsgDetailDAO;
import cn.labsoft.labos.base.message.dao.ILabMsgMainDAO;
import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.base.message.entity.LabMsgMain;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.coreextend.service.ICoreExtendService;
import cn.labsoft.labos.framework.common.exception.GlobalException;

@Repository(value = "coreExtendService")
public class CoreExtendServiceImpl implements ICoreExtendService {
	private ILabMsgDetailDAO labMsgDetailDAO;
	private ILabMsgMainDAO labMsgMainDAO;

	@Resource
	public void setLabMsgDetailDAO(ILabMsgDetailDAO labMsgDetailDAO) {
		this.labMsgDetailDAO = labMsgDetailDAO;
	}

	@Resource
	public void setLabMsgMainDAO(ILabMsgMainDAO labMsgMainDAO) {
		this.labMsgMainDAO = labMsgMainDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabMsgDetailVo> getUnreadMesgMessageList(LabMsgDetailVo labMsgDetailVo) throws GlobalException {
		String hql = "FROM LabMsgDetail mes where mes.position='" + 1 + "'" + "  and mes.receiver.id='" + labMsgDetailVo.getReceiveId() + "'" + " and mes.readFlag=0";

		try {
			List list = labMsgDetailDAO.find(hql);
			List<LabMsgDetailVo> reaturnList = new ArrayList<LabMsgDetailVo>();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					LabMsgDetailVo mdVo = new LabMsgDetailVo();
					LabMsgDetail md = (LabMsgDetail) list.get(i);
					LabMsgMain mesgMessageMain = (LabMsgMain) labMsgMainDAO.findById(LabMsgMain.class, md.getMsgMain().getId());
					mdVo.setId(md.getId());
					mdVo.setMainMsgID(mesgMessageMain.getId());
					mdVo.setSubject(mesgMessageMain.getSubject());
					String createTime = "";
					if (mesgMessageMain.getCreateTime().length() > 10) {
						createTime = mesgMessageMain.getCreateTime().substring(0, 10);
					} else {
						createTime = mesgMessageMain.getCreateTime();
					}
					mdVo.setSenderName(md.getSender().getName());
					mdVo.setCreateTime(createTime);
					reaturnList.add(mdVo);
				}
			}
			return reaturnList;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("根据传入参数得到未读消息出错！" + e.getMessage());
			
		}
	}

	@Override
	public void getPushMessageTargetRole(String taskCode, String processCode, String userId) {
		//		HttpServletRequest request = ServletActionContext.getRequest();
		//		String path = request.getContextPath();
		//		String basePath = request.getScheme() + "://" + request.getServerName()
		//				+ ":" + request.getServerPort() + path + "/";
		//		List<LabRoleFun> sysRoleFunList = new ArrayList<LabRoleFun>();
		//		sysRoleFunList = sysRoleDAO.getSysRoleFunListByFunWFCode(taskCode,
		//				processCode);
		//		for (SysRoleFun sysRoleFun : sysRoleFunList) {
		//			String funId = "";
		//			SysFunction function =null;
		//			if (null != sysRoleFun.getSysFunction()){
		//				funId = sysRoleFun.getSysFunction().getId();
		//				function=sysRoleFun.getSysFunction();
		//			}
		//				
		//
		//			String parentId = null;
		//			if (null != sysRoleFun.getSysFunction()
		//					&& null != sysRoleFun.getSysFunction().getSysFunction())
		//				parentId = sysRoleFun.getSysFunction().getSysFunction().getId();
		//			
		//			
		//				if (null != userId && !"".equals(userId)) {
		//					MessagePushUtils.pushMessage(request, "1", userId, sysRoleFun
		//							.getSysFunction().getFunname()
		//							+ "提示", "<li><a onclick=\"selectCurrentMenu('"
		//							+ parentId + "','" + funId + "');\" href='" + basePath
		//							+ sysRoleFun.getSysFunction().getFunurl()
		//							+ "' target='workarea'>您有新的<font color='red'>"
		//							+ sysRoleFun.getSysFunction().getFunname()
		//							+ "</font>需要处理！</a></li>");
		//				} else {
		//					MessagePushUtils.pushMessage(request, "2", sysRoleFun
		//							.getSysRole().getRolename(), sysRoleFun
		//							.getSysFunction().getFunname()
		//							+ "提示", "<li><a onclick=\"selectCurrentMenu('"
		//							+ parentId + "','" + funId + "');\" href='" + basePath
		//							+ sysRoleFun.getSysFunction().getFunurl()
		//							+ "' target='workarea'>您有新的<font color='red'>"
		//							+ sysRoleFun.getSysFunction().getFunname()
		//							+ "</font>需要处理！</a></li>");
		//				}
		//		}
	}

	@Override
	public void getPushMessageTargetRoleIsHaveSunWfCode(String taskCode, String processCode, String sunProcessCode, String userId) {
		//		HttpServletRequest request = ServletActionContext.getRequest();
		//		String path = request.getContextPath();
		//		String basePath = request.getScheme() + "://" + request.getServerName()
		//				+ ":" + request.getServerPort() + path + "/";
		//		List<LabRoleFun> sysRoleFunList = new ArrayList<LabRoleFun>();
		//		sysRoleFunList = sysRoleDAO.getSysRoleFunListByFunWFCodeIsHaveSunWfCode(taskCode, processCode, sunProcessCode);
		//				
		//		for (SysRoleFun sysRoleFun : sysRoleFunList) {
		//			String funId = "";
		//			SysFunction function =null;
		//			if (null != sysRoleFun.getSysFunction()){
		//				funId = sysRoleFun.getSysFunction().getId();
		//				function=sysRoleFun.getSysFunction();
		//			}
		//
		//			String parentId = null;
		//			if (null != sysRoleFun.getSysFunction()
		//					&& null != sysRoleFun.getSysFunction().getSysFunction())
		//				parentId = sysRoleFun.getSysFunction().getSysFunction().getId();
		//				if (null != userId && !"".equals(userId)) {
		//					MessagePushUtils.pushMessage(request, "1", userId, sysRoleFun
		//							.getSysFunction().getFunname()
		//							+ "提示", "<li><a onclick=\"selectCurrentMenu('"
		//							+ parentId + "','" + funId + "');\" href='" + basePath
		//							+ sysRoleFun.getSysFunction().getFunurl()
		//							+ "' target='workarea'>您有新的<font color='red'>"
		//							+ sysRoleFun.getSysFunction().getFunname()
		//							+ "</font>需要处理！</a></li>");
		//				} else {
		//					MessagePushUtils.pushMessage(request, "2", sysRoleFun
		//							.getSysRole().getRolename(), sysRoleFun
		//							.getSysFunction().getFunname()
		//							+ "提示", "<li><a onclick=\"selectCurrentMenu('"
		//							+ parentId + "','" + funId + "');\" href='" + basePath
		//							+ sysRoleFun.getSysFunction().getFunurl()
		//							+ "' target='workarea'>您有新的<font color='red'>"
		//							+ sysRoleFun.getSysFunction().getFunname()
		//							+ "</font>需要处理！</a></li>");
		//				}
		//		}
	}
}