package cn.labsoft.labos.source.klg.service;


import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;


@SuppressWarnings("unchecked")
public interface ILabItemService {

	/**
	 * 向项目表中插入一条记录
	 * @param labItemVo 封装了项目信息的视图对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabItem(LabItemVo labItemVo) throws GlobalException;
	/**
	 * 向项目表中插入一条记录（非标）
	 * @param labItemVo 封装了项目信息的视图对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabItem(LabItemVo labItemVo,List<LabMethodVo> methodList) throws GlobalException;
	/**
	 * 删除项目信息
	 * @param ids 要删除的项目id组成的数组
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabItem (String[] ids) throws GlobalException;

	/**
	 * 修改项目信息
	 * @param labItemVo 封装了项目信息的视图对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabItem(LabItemVo labItemVo) throws GlobalException;

	/**
	 * 根据项目id获得项目信息
	 * @param id 项目id
	 * @throws GlobalException
	 * @return 返回类型 LabItemVo 返回封装了项目信息的视图对象
	 * @author amy
	 */
	public LabItemVo getLabItem(String id) throws GlobalException;

	/**
	 * 获得项目信息列表
	 * 
	 * @param labItemVo 封装了项目信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 List 返回项目信息的列表
	 * @author amy
	 */
	public List<LabItemVo> getLabItemList(LabItemVo labItemVo) throws GlobalException;
	/**
	 * 获得项目信息树形列表
	 * 
	 * @param labItemVo 封装了项目信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 List 返回项目信息的列表
	 * @author amy
	 */
	public List<LabItemVo> getLabItemTreeList(LabItemVo labItemVo) throws GlobalException;
	/**
	 * 获得项目信息分页列表
	 * @param labItemVo 项目信息对象
	 * @param currentPage 
	 * @param pagerMethod
	 * @param pageSize
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabItemPR(LabItemVo labItemVo, PageResult pageResult)
			throws GlobalException;
	/**
	 * 根据父项目获得项目列表
	 * 
	 * @param parentId 父项目的id
	 * @throws GlobalException
	 * @return 返回类型 List 返回父项目下的项目列表
	 * @author amy
	 */
	public List<LabItemVo> getLabItemListByParentId(String parentId)
			throws GlobalException;

	/**
	 * 获得某一项目下的最大序号值
	 * @param LabItemVo  封装了项目信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 int 返回某一项目下的最大序号值
	 * @author amy
	 */
	public int getMaxSort(String parentId) throws GlobalException;
	/**
	 * 当前名称是否存在
	 * @param name 项目名称
	 * @param parentId 项目父Id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean exist4LabItemName(String name,String parentId) throws GlobalException;
	/**
	 * 是否有子相
	 * @param id 项目Id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean hasChildren(String id) throws GlobalException;
	/**
	 * 修改项目信息
	 * @param listLabItemVo 项目对象
	 * @return 返回类型 boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateListItem(List<LabItemVo> listLabItemVo) throws GlobalException;
	/**
	 * 根据pageResult查询除过大项的检测项目，labStandardItemVo为备用参数
	 * @param pageResult 
	 * @param labStandardItemVo 标准-项目关系对象
	 * @return 返回类型 PageResult
	 * @author amy
	 * @throws GlobalException 
	 */
	public PageResult getMinLabItem(PageResult pageResult,LabStandardItemVo labStandardItemVo) throws GlobalException;
	/**
	 * 根据标准-项目关系对象查询除过大项的检测项目
	 * @param labStandardItemVo  标准-项目关系对象
	 * @return 返回类型 项目对象集合(List)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabItemVo> getMinLabItem(LabStandardItemVo labStandardItemVo) throws GlobalException;
}
