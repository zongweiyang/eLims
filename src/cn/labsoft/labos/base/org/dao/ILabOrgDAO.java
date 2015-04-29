package cn.labsoft.labos.base.org.dao;

import java.util.List;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabOrgDAO extends IBaseDAO {
	/**
	 * 添加组织
	 * @param sysOrgVo
	 * @return
	 */
	public LabOrg addLabOrg(LabOrg labOrg) throws GlobalException;
	/**
	 * 修改组织信息
	 * @param sysOrgVo
	 * @return
	 */
	public boolean updateLabOrg(LabOrg labOrg) throws GlobalException;
	/**
	 * 删除组织信息
	 * @param sysOrgVo
	 * @return
	 */
	public boolean delLabOrg(String[] ids) throws GlobalException;
	/**
	 * 根据ID查询组织信息
	 * @param sysOrgId
	 * @return
	 */
	public LabOrg getLabOrg(String id)  throws GlobalException;
	
	/**
	 * 根据HQL查询
	 * @param hql
	 * @return
	 */
	public List<LabOrg> getLabOrgList(String hql) throws GlobalException;
	/**
	 * 获取组织与其子集的id集合
	 * @param hql
	 * @return
	 */
	public String[] getLabOrgList4Sub(String pid) throws GlobalException;
	/**
	 * 分页信息
	 * @param hql
	 * @param pageReuslt
	 * @return
	 * @throws GlobalException 
	 */
	public PageResult getLabOrgPR(String  hql,PageResult  pageReuslt ) throws GlobalException;
}
