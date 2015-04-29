package cn.labsoft.labos.base.org.service;

import java.util.List;

import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 
 * <strong>Title : ILabOrgService </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Jan 28, 2014 5:16:26 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Stone Tang <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("unchecked")
public interface ILabOrgService {
	
	/**
	 * 向组织表中插入一条记录
	 * 
	 * @param sysOrgVo
	 *            封装了组织信息的视图对象
	 * @throws GlobalException
	 */
	public LabOrgVo addLabOrg(LabOrgVo labOrgVo) throws GlobalException;;

	/**
	 * 批量删除组织信息
	 * 
	 * @param ids
	 *            要删除的组织id组成的数组
	 * @throws GlobalException
	 */
	public boolean updateLabOrg4Del(String[] ids) throws GlobalException;

	/**
	 * 修改组织信息
	 * 
	 * @param sysOrgVo
	 *            封装了组织信息的视图对象
	 * @throws GlobalException
	 */
	public boolean updateLabOrg(LabOrgVo labOrgVo) throws GlobalException;

	/**
	 * 根据组织id获得组织信息
	 * 
	 * @param id
	 *            组织id
	 * @throws GlobalException
	 * @return 返回类型 SysOrgVo 返回封装了组织信息的视图对象
	 */
	public LabOrgVo getLabOrg(String id) throws GlobalException;

	/**
	 * 获得组织信息列表
	 * 
	 * @param sysOrgVo
	 *            封装了组织信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 List 返回组织信息的列表
	 */
	public List<LabOrgVo> getLabOrgList(LabOrgVo labOrgVo) throws GlobalException;

	/**
	 * 获取组织分页信息
	 * @param sysOrgVo
	 * @param pageResult
	 * @return
	 * @throws GlobalException
	 */
	public PageResult getLabOrgPR(LabOrgVo labOrgVo,PageResult pageResult) throws GlobalException;


	/**
	 * 根据父组织获得组织列表
	 * 
	 * @param parentId
	 *            父组织的id
	 * @throws GlobalException
	 * @return 返回类型 List 返回父组织下的组织列表
	 */
	public List<LabOrgVo> getLabOrgListByPId(String pId)
			throws GlobalException;
	/**
	 * 根据父组织名称获得组织列表
	 * @param parentName
	 * @return
	 * @throws GlobalException
	 */
	public List<LabOrgVo> getLabOrgListByPName(String pName)
	throws GlobalException;


	/**
	 * 获得最高组织(单位)的信息
	 * 
	 * @throws GlobalException
	 * @return 返回类型 SysOrgVo 封装了组织信息的视图对象
	 */
	public LabOrgVo getLabOrgUnit() throws GlobalException;
	/**
	 * 判断组织名称中是否已经存在此名称  1 可以  0 
	 * @Title:  
	 * @Description: TODO
	 * @param @param name
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public String testLabOrgName(LabOrgVo labOrgVo)throws GlobalException;

	/**
	 * 获得某一组织下的最大序号值
	 * 
	 * @param sysOrgVo
	 *            封装了组织信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 int 返回某一组织下的最大序号值
	 */
	public int getMaxSort(LabOrgVo labOrgVo) throws GlobalException;

	/**
	 * 根据等级名称 查询组织信息
	 * @param rank
	 * @return
	 * @throws GlobalException
	 */
	
	public List<LabOrgVo> getLabOrgVoByRank(String rank)throws GlobalException;
	/**
	 * 根据父节点获取子节点
	 * @Title:  
	 * @Description: TODO
	 * @param @param parentId
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public StringBuffer getZtreeByPId(String parentId)throws GlobalException;
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public List<LabOrgVo> getLabOrgByUserId(String userId)throws GlobalException;
	/**
	 * 获取除过id以为的组织目录
	 * @Title:  
	 * @Description: TODO
	 * @param @param parentId
	 * @param @param id
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public StringBuffer getLabOrgContentsZtree(String parentId,String id)throws GlobalException;
	/**
	 * \修改组织继承的父组织
	 * @Title:  
	 * @Description: TODO
	 * @param @param labOrgVo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean updateLabOrgPid(LabOrgVo labOrgVo) throws GlobalException;

	public List<LabOrgVo> getLabOrgTree() throws GlobalException;

	/**
	 * 根据组织对象查找组织
	 * @param orgVo
	 * @return
	 * @throws GlobalException
	 */
	public LabOrgVo getLabOrgByVo(LabOrgVo labOrgVo)throws GlobalException;
	/**
	 * 过滤可用的部门列表
	 * @param labOrgVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabOrgVo> getUsedLabOrgList(List<LabOrgVo> labOrgVo)throws GlobalException;

	public List<LabOrgVo> getLabOrgVoByWhere(String whereHql) throws GlobalException;
	/**
	 * 获取某个组织下的级别集合
	 * @param id 组织id
	 * @return
	 * @throws GlobalException
	 */
	public List<Integer> getLabOrgLevelList(String id) throws GlobalException;
	
	/**
	 * 
	 * @param orgId
	 * @param parentId
	 * @return
	 * @throws GlobalException
	 */
	public StringBuffer getLabOrgCheckTree(String orgId, String parentId) throws GlobalException;
	
}
