package cn.labsoft.labos.framework.common.taglib;

import java.util.List;

import cn.labsoft.labos.base.role.dao.ILabRoleFunDAO;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;

import com.opensymphony.xwork2.ActionContext;

public class JFunction {
	@SuppressWarnings("unchecked")
	static boolean isHaveingThisFunction(String url,String type){
		ILabRoleFunDAO labRoleFunDAO = (ILabRoleFunDAO) SystemInstance
			.getInstance().getBean(ILabRoleFunDAO.class);
		boolean key = false;
		try {
			SessionContainer sc = (SessionContainer) ActionContext.getContext().getSession().get(
				SessionContainer.Session_Container);
			String userId = sc.getUserId();
			if(userId.equals("0"))return true;//超级管理员拥有所有权限
			String[] strs = url.split("/");
			String nameSpace="";
			for(int i=0;i<strs.length-1;i++){
				nameSpace +=strs[i]+"/";
			}
			StringBuffer hql = new StringBuffer("FROM LabUserFun WHERE 1=1 ");
			hql.append(" AND is"+type+" ='Y'");
			hql.append(" AND user.id = '"+userId+"'");
			hql.append(" AND function.url like '%"+nameSpace+"%'  ");
			hql.append(" AND function.isDel = 'N' ");
			List<LabUserFun> li = labRoleFunDAO.find(hql.toString());
			if(null!=li&&li.size()>0){
				key = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}
}
