package cn.labsoft.labos.base.function.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.function.service.ILabPowerService;
import cn.labsoft.labos.base.function.vo.LabPowerVo;
import cn.labsoft.labos.base.user.dao.ILabUserFunDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.interceptor.Action;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

/**
 * 系统功能用户权限Service实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */

@SuppressWarnings("unchecked")
@Service("labPowerService")
public class LabPowerServiceImpl implements ILabPowerService {
	private ILabUserFunDAO labUserFunDAO;

	/**
	 * 用户功能管理DAO注入
	 * 
	 * @param labUserFunDAO
	 */
	@Resource
	public void setLabUserFunDAO(ILabUserFunDAO labUserFunDAO) {
		this.labUserFunDAO = labUserFunDAO;
	}

	/**
	 * @Title: 根据uri 判断是否拥有权限
	 * @Description: TODO
	 * @param uri
	 * @return boolean
	 */
	@Override
	public boolean hasPower(String uri) {
		SessionContainer sessionContainer = SessionContainer.getSessionContainer();
		if (null == sessionContainer) {
			return false;
		} else {
			//是否为例外的namespace
			if (Action.extNameSpace(getNamespace(uri))) {
				return true;
			} else {
				try {
					boolean flag = labUserFunDAO.getLabUserFunHavePower(sessionContainer.getUserId(), sessionContainer.getFunId(), getAction(uri));
					if (flag) {
						return true;
					}
				} catch (GlobalException e) {
					Log4J.error("", e);
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: 根据uri返回权限信息
	 * @Description: TODO
	 * @param uri
	 * @return LabPowerVo
	 */
	@Override
	public LabPowerVo getPowerInfo(String uri) {
		LabPowerVo labPowerVo = new LabPowerVo();
		labPowerVo.setHasPower(hasPower(uri));
		if (labPowerVo.getHasPower()) {
			String basePath = getBasePath();
			labPowerVo.setUrl(basePath + uri);
			labPowerVo.setAction(getAction(uri));
			labPowerVo.setImg(basePath + "img/" + labPowerVo.getAction() + ".gif");
		}
		return labPowerVo;
	}

	private String getBasePath() {
		return "/";
	}

	public String getAction(String uri) {
		return Action.getAction(getActionName(uri));
	}

	public String getActionName(String uri) {
		String[] arrays = uri.split("\\/");
		return arrays[arrays.length - 1].split("\\.")[0];
	}

	public String getNamespace(String uri) {
		//String basePath = getBasePath();
		//String info = uri.replace(basePath, "").toUpperCase().split(".ACTION")[0].replace(getActionName(uri).toUpperCase(), "");
		//quinn update
		String info = uri.toUpperCase().split(".ACTION")[0].replace(getActionName(uri).toUpperCase(), "");
		String[] array = info.split("/");
		String nameSpace = "";
		for (String str : array) {
			if (null != str && !"".equals(str))
				nameSpace += "/" + str;
		}
		return nameSpace;
	}
}
