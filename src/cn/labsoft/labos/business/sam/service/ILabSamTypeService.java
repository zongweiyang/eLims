package cn.labsoft.labos.business.sam.service;

import java.util.List;

import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 样品分类Service
 * @author danlee Li
 *
 */
public interface ILabSamTypeService {
	/**
	 * 判断该样品名称是否存在
	 * @param name
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean ajaxIsExistName(String name) throws GlobalException;
	/**
	 * 增加样品分类
	 * @param labSamTypeVo
	 * 					封装了样品分类对象信息
	 * @return LabSamTypeVo
	 * 					封装了样品分类对象信息
	 * @throws GlobalException
	 */
	public LabSamTypeVo addLabSamType(LabSamTypeVo labSamTypeVo)throws GlobalException;
	/**
	 * 修改样品分类 
	 * @param labSamTypeVo
	 * 					封装了样品分类对象信息
	 * @throws GlobalException  
	 * @return boolean 
	 */
	
	public boolean updateLabSamType(LabSamTypeVo labSamTypeVo)throws GlobalException;
	/**
	 * 删除用户信息-假删除
	 * @param id
	 * @return boolean 
	 * @throws GlobalException
	 */
	public boolean updateLabSamType4Del(String id)throws GlobalException;
	/**
	 * 删除用户信息-批量删除
	 * @param ids
	 * @return boolean 
	 * @throws GlobalException
	 */
	public boolean deleteLabSamType4Batch(String[] ids)throws GlobalException;
	/**
	 * 删除用户信息
	 * @param id
	 * @return boolean
	 * @throws GlobalException  
	 */
	public boolean deleteLabSamType(String id)throws GlobalException;
	/**
	 * 获取样品分类列表
	 * @param vo
	 * @throws GlobalException  
	 * @return List<LabSamTypeVo>
	 */
	public List<LabSamTypeVo> getLabSamTypeList(LabSamTypeVo vo)throws GlobalException;
	
	/**
	 * 获得样品分类分页信息
	 * @param pageResult
	 * @throws GlobalException  
	 * @return PageResult 
	 */
	public PageResult getLabSamTypePR(LabSamTypeVo vo,PageResult pageResult)throws GlobalException;
	
	/**
	 * 根据ID获得样品分类
	 * @param id
	 * @throws GlobalException  
	 * @return LabSamTypeVo
	 */
	public LabSamTypeVo getLabSamTypeById(String id) throws GlobalException ;
	
	/**
	 *  验证是否有子集
	 * @param labSamTypeVo
	 * @throws GlobalException  
	 * @return boolean
	 */
	public boolean getLabSamTypeByPid(LabSamTypeVo labSamTypeVo)throws GlobalException;
	
	/**
	 * 获得样品分类信息
	 * @param labSamTypeVo
	 * @return LabSamTypeVo
	 * @throws GlobalException
	 */
	public LabSamTypeVo getLabSamTypeByVo(LabSamTypeVo labSamTypeVo) throws GlobalException;
	/**
	 * 给指定样品分类关联项目
	 * @param labSamTypeVo
	 * @throws GlobalException
	 */
	public void addLabSamTypeItem(LabSamTypeVo labSamTypeVo)throws GlobalException;
	/**
	 * 获得指定样品分类下的项目
	 * @param labSamTypeVo
	 * @return LabSamTypeVo
	 * @throws GlobalException
	 */
	public LabSamTypeVo getLabSamTypeItem(LabSamTypeVo labSamTypeVo)throws GlobalException;
}
