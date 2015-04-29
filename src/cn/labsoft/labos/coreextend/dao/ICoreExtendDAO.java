package cn.labsoft.labos.coreextend.dao;

import java.util.List;

import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;


public interface ICoreExtendDAO   extends IBaseDAO{
	public List<LabUser> getSysUserListByStationName(String stationName);
	
	public List<LabUser> getSysUserListByStationNameOrgId(String stationName, String orgId);
}
