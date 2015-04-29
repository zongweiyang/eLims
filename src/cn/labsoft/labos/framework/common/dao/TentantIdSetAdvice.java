package cn.labsoft.labos.framework.common.dao;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.framework.common.po.BasePo;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;

public class TentantIdSetAdvice {
	
	public static <T extends BasePo> T setTenantIdToObject(T object) {
		/* Quinn*/
		SessionContainer son=SessionContainer.getSessionContainer();
		if(son!=null){
			String tenantId_ =SessionContainer.getSessionContainer().getTenantId();
			if (null == object.getTenantId()){
				if (null != tenantId_ && !"".equals(tenantId_)) {
					object.setTenantId(tenantId_);
				}
			}
			if(object.getCreateTime()==null||object.getCreateTime().equals("")){
				object.setCreateTime(DateUtils.getCurrDateTimeStr());
			}
			if(object.getCreateUserId()==null||object.getCreateUserId().equals("")){
				object.setCreateUserId(son.getUserId());
			}
		}
		return object;
	}

	public static <T extends AbstractBasePo> T setTenantIdToObject(T object) {
		/* Quinn*/
		SessionContainer son=SessionContainer.getSessionContainer();
		if(son!=null){
			String tenantId_ =SessionContainer.getSessionContainer().getTenantId();
			if (null == object.getTenantId()){
				if (null != tenantId_ && !"".equals(tenantId_)) {
					object.setTenantId(tenantId_);
				}
			}
			if(object.getCreateTime()==null||object.getCreateTime().equals("")){
				object.setCreateTime(DateUtils.getCurrDateTimeStr());
			}
			if(object.getCreateUserId()==null||object.getCreateUserId().equals("")){
				object.setCreateUserId(son.getUserId());
			}
		}
		return object;
	}

	public static final String setTenantIdToHSql(String hsql) {
		String tenantId =SessionContainer.getSessionContainer().getFunDataStr();
		if (!hsql.contains("tenantId") && !hsql.contains("tenantid")&& !hsql.contains("TENANTID")
				&&tenantId!=null&&tenantId.trim().length()>0) {
			int whereIndex = hsql.indexOf(" where");
			int WhereIndex = hsql.indexOf(" Where");
			int WHEREIndex = hsql.indexOf(" WHERE");
			if (whereIndex > 0 || WhereIndex > 0 || WHEREIndex > 0) {
				int tempIndex = 0;
				if (whereIndex > 0) {
					tempIndex = whereIndex;
				} else if (WhereIndex > 0) {
					tempIndex = WhereIndex;
				} else {
					tempIndex = WHEREIndex;
				}
				String tempHsql = hsql.substring(0, tempIndex);
				hsql = tempHsql + " WHERE  1=1 And tenantId LIKE '" + tenantId
						+ "%' AND" + hsql.substring(tempIndex + 6, hsql.length());
			} else {
				int orderIndex = hsql.indexOf(" order");
				int OrderIndex = hsql.indexOf(" Order");
				int ORDERIndex = hsql.indexOf(" ORDER");
				if (orderIndex > 0 || OrderIndex > 0 || ORDERIndex > 0) {
					int tempIndex = 0;
					if (orderIndex > 0) {
						tempIndex = orderIndex;
					} else if (OrderIndex > 0) {
						tempIndex = OrderIndex;
					} else {
						tempIndex = ORDERIndex;
					}
					String tempHsql = hsql.substring(0, tempIndex);
					hsql = tempHsql + " WHERE 1=1 And tenantId LIKE '" + tenantId
							+ "%' " + hsql.substring(tempIndex, hsql.length());
				} else {
					hsql += " WHERE 1=1 AND tenantId LIKE '" + tenantId + "%'";
				}
			}
		}
		return hsql;
	}
	public static final String setTenantId2HSql4All(String hsql) {
		String tenantId =SessionContainer.getSessionContainer().getFunDataStr();
		if (!hsql.contains("tenantId") && !hsql.contains("tenantid")&& !hsql.contains("TENANTID")
				&&tenantId!=null&&tenantId.trim().length()>0) {
			if(tenantId.contains(",")){
				if(tenantId.contains("-")){
					String sub1=tenantId.substring(0,tenantId.indexOf(","));
					String sub2=tenantId.substring(tenantId.indexOf(",")+1);
					tenantId=sub1.substring(0,sub1.indexOf("-"))+sub2.substring(0,sub2.indexOf("-"));
				}else if(tenantId.contains("_")){
					String sub1=tenantId.substring(0,tenantId.indexOf(","));
					String sub2=tenantId.substring(tenantId.indexOf(",")+1);
					tenantId=sub1.substring(0,sub1.indexOf("_"))+sub2.substring(0,sub2.indexOf("_"));
				}else if(tenantId.contains("*")){
					String sub1=tenantId.substring(0,tenantId.indexOf(","));
					String sub2=tenantId.substring(tenantId.indexOf(",")+1);
					tenantId=sub1.substring(0,sub1.indexOf("*"))+sub2.substring(0,sub2.indexOf("*"));
				}
			}else if(tenantId.contains("-")){
				tenantId=tenantId.substring(0,tenantId.indexOf("-"));
			}else if(tenantId.contains("_")){
				tenantId=tenantId.substring(0,tenantId.indexOf("_"));
			}else if(tenantId.contains("*")){
				tenantId=tenantId.substring(0,tenantId.indexOf("*"));
			}
			int whereIndex = hsql.indexOf(" where");
			int WhereIndex = hsql.indexOf(" Where");
			int WHEREIndex = hsql.indexOf(" WHERE");
			if (whereIndex > 0 || WhereIndex > 0 || WHEREIndex > 0) {
				int tempIndex = 0;
				if (whereIndex > 0) {
					tempIndex = whereIndex;
				} else if (WhereIndex > 0) {
					tempIndex = WhereIndex;
				} else {
					tempIndex = WHEREIndex;
				}
				String tempHsql = hsql.substring(0, tempIndex);
				hsql = tempHsql + " WHERE  1=1 And tenantId LIKE '" + tenantId
						+ "%' AND" + hsql.substring(tempIndex + 6, hsql.length());
			} else {
				int orderIndex = hsql.indexOf(" order");
				int OrderIndex = hsql.indexOf(" Order");
				int ORDERIndex = hsql.indexOf(" ORDER");
				if (orderIndex > 0 || OrderIndex > 0 || ORDERIndex > 0) {
					int tempIndex = 0;
					if (orderIndex > 0) {
						tempIndex = orderIndex;
					} else if (OrderIndex > 0) {
						tempIndex = OrderIndex;
					} else {
						tempIndex = ORDERIndex;
					}
					String tempHsql = hsql.substring(0, tempIndex);
					hsql = tempHsql + " WHERE 1=1 And tenantId LIKE '" + tenantId
							+ "%' " + hsql.substring(tempIndex, hsql.length());
				} else {
					hsql += " WHERE 1=1 AND tenantId LIKE '" + tenantId + "%'";
				}
			}
		}
		return hsql;
	}
	public static String setHQLForTenantId(String asName) {
		return "";
	}
}
