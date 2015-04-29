package cn.labsoft.labos.source.klg.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;


@SuppressWarnings("unchecked")
public interface ILabStandardItemService {
	/**
	 * 增加标准-项目关系对象
	 * @param labStandardItemVo  标准-项目关系对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabStandardItem(LabStandardItemVo labStandardItemVo)throws GlobalException;;
	/**
	 * 根据Id，name获取标准-项目关系对象列表
	 * @param id 标准Id
	 * @param name 项目名称
	 * @return 返回类型 标准-项目关系对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardItemVo> getLabStandardItemVoByStandId(String id,String name)throws GlobalException;
	/**
	 * 增加标准-小项目关系对象
	 * @param labStandardItemVo  标准-项目关系对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabStandardMinItem(LabStandardItemVo labStandardItemVo)throws GlobalException;
	/**
	 * 添加标准-项目-方法关系对象
	 * @param labStandardItemMethodVo 标准-项目-方法关系对象s
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabStandardItemMethod(LabStandardItemMethodVo labStandardItemMethodVo)throws GlobalException;
	/**
	 * 修改标准-项目关系对象
	 * @param listLabStandardItemVo 标准-项目关系对象集合
	 * @return  返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabStandardItem(List<LabStandardItemVo> listLabStandardItemVo)throws GlobalException;
	
	/**
	 * 删除标准-项目关系对象
	 * @param labStandardItemVo 标准-项目关系对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabStandardItem( LabStandardItemVo labStandardItemVo)throws GlobalException;
	/**
	 * 根据standId，itemName，itemId获取标准-项目-方法关系对象列表
	 * @param standId 标准Id
	 * @param itemName 项目名称
	 * @param itemId 项目Id
	 * @return   返回类型 标准-项目关系对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardItemVo> getLabStandardItemVoByStandId(String standId,String itemName,String itemId) throws GlobalException;
	
	/**
	 * 获取标准-项目-方法关系对象全部列表
	 * @return  返回类型 标准-项目-方法关系对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardItemMethodVo> getLabStandardItemMethodList()throws GlobalException;
	/**
	 * 根据standId,itemId获取检测方法对象列表
	 * @param standId 标准Id
	 * @param itemId 项目Id
	 * @return  返回类型 检测方法对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabMethodVo> getLabMethodList(String standId,String itemId)throws GlobalException;
	
	/**
	 * 根据standItemVo，standItemList修改标准-项目关系对象
	 * @param standItemVo 标准-项目关系对象
	 * @param standItemList 标准-项目关系对象集合
	 * @return   返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateImportLabStandardItem(LabStandardItemVo standItemVo, List<LabStandardItemVo> standItemList) throws GlobalException;
	/**
	 * 根据testType获取标准-项目关系对象中的标准编号
	 * @param testType 检测方式
	 * @return  返回 标准-项目关系对象中的标准编号
	 * @throws GlobalException
	 * @author amy
	 */
	public String getLabStandardItemMethodListByTestType(String testType)throws GlobalException;
	/**
	 * 根据testType，itemname获取标准-项目关系对象中的标准编号
	 * @param testType  检测方式
	 * @param itemname 项目名称
	 * @return   返回类型 标准-项目关系对象集合(List)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItemVo> getLabStandardItemByNoType(String testType,String itemname) throws GlobalException;
	
	/**
	 *  根据id，sampTypeId，pageResult获取标准-项目关系对象分页信息
	 * @param id 标准Id
	 * @param name 项目名称
	 * @param sampTypeId 样品分类Id
	 * @param pageResult 
	 * @return  返回类型 PageResult
	 * @author amy
	 * @throws GlobalException 
	 */
	public PageResult getLabStandardItemVoByStandId(String id,String name,String sampTypeId,PageResult pageResult) throws GlobalException;
	
}
