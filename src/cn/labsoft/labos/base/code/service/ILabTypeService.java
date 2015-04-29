package cn.labsoft.labos.base.code.service;

import java.io.Serializable;
import java.util.List;

import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * @title  公共代码
 * @author Bill
 * @time   2014.02.08
 */

@SuppressWarnings("unchecked")
public interface ILabTypeService {
	/**
	 * 
	 * 向公共代码类型表中插入一条记录
	 * @param labTypeVo  封装了公共代码类型信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 LabTypeVo  封装了公共代码类型信息的视图对象
	 */
	public LabTypeVo addLabType(LabTypeVo labTypeVo) throws GlobalException;

	/**
	 * 删除一条公共代码类型信息
	 * @param LabTypeId  公共代码类型id
	 * @throws GlobalException  
	 */
	public void deleteLabType(String LabTypeId) throws GlobalException;

	/**
	 * 
	 * 修改公共代码类型信息
	 * @param LabTypeVo  封装了公共代码类型信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 LabTypeVo  封装了公共代码类型信息的视图对象
	 */
	public LabTypeVo updateLabType(LabTypeVo LabTypeVo) throws GlobalException;

	/**
	 * 
	 * 根据id获得公共代码类型信息
	 * @param id  公共代码类型id
	 * @throws GlobalException  
	 * @return 返回类型 LabTypeVo  封装了公共代码类型信息的视图对象
	 */
	public LabTypeVo getLabTypeById(Serializable id) throws GlobalException;

	/**
	 * 
	 * 获得公共代码类型信息列表 
	 * @throws GlobalException  
	 * @return 返回类型 List 返回公共代码类型信息集合
	 */
	public List getLabTypeList() throws GlobalException;

	/**
	 * 
	 * 根据hql获得公共代码类型分页信息
	 * @param  hql  查寻公共代码类型信息的hql语句
	 * @param  currentPage 当前页数
	 * @param  pagerMethod 取值为首页(first)、上一页(previous)、下一页(next)、尾页(last)
	 * @param  pageSize 页面显示记录条数
	 * @throws GlobalException  
	 * @return 返回类型 PageResult 返回带有分页的公共代码类型信息列表
	 */
	public PageResult getLabTypeList(String hql, PageResult pageResult)
			throws GlobalException;
	/**
	 * 
	 * 获得公共代码类型信息列表 
	 * @throws GlobalException  
	 * @return 返回类型 List 返回公共代码类型信息集合
	 */
	public List<LabTypeVo> getLabTypesList(LabTypeVo labTypeVo)
	throws GlobalException;

	/**
	 * 
	 * 获得公共代码分页信息 
	 * @param labTypeVo   封装了公共代码类型信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 PageResult 返回带有分页的公共代码类型信息列表
	 */
	public PageResult getLabTypePR(LabTypeVo labTypeVo, PageResult pageResult)
			throws GlobalException;

	/**
	 * 
	 * 判断公共代码类型名称是否重复 
	 * @param checkStr 需要验证的公共代码类型名称
	 * @param flag  0:按公共代码类型名称验证,1:按公共代码类型code值验证
	 * @throws GlobalException  
	 * @return 返回类型 String 返回若为0表示不存在，1表示已存在
	 */
	public String isExsitLabType(String checkStr, String flag)
			throws GlobalException;//flag 0-name;1-code

	/**
	 * 
	 * 获得某一公共代码类型序号最大值
	 * @param labCodeVo  封装了公共代码类型信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 int 返回最大的序号值
	 */
	public int getMaxSort(LabTypeVo labTypeVo) throws GlobalException;
}
