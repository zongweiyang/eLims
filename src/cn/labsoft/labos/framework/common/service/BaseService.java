package cn.labsoft.labos.framework.common.service;

import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.StrUtils;


public abstract class BaseService{
	
	public boolean hasThisRole(String roleName){
		SessionContainer s = getSessionContainer();
		boolean key = false;
		String roleNames[]= StrUtils.split(s.getRoleName(), ",");
		for(String str:roleNames){
			if(null!=roleName&&roleName.equals(str)){
				key = true;
				break;
			}
		}
		return key;
	} 
	
	public SessionContainer getSessionContainer() {
		return SessionContainer.getSessionContainer();
	}

}