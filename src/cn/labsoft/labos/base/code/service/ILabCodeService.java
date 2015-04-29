package cn.labsoft.labos.base.code.service;

import java.io.Serializable;
import java.util.List;

import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.code.vo.LabTypeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 
 * <strong>Title : ILabCodeService.java </strong>. <br>
 * <strong>Description : TODO</strong> <br>
 * <strong>Create on : 2014-9-4 上午11:34:09  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>CORE8</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人 修改日期 修改描述<br>
 *          席飞   2014-09-04 ABCD
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

@SuppressWarnings("unchecked")
public interface ILabCodeService {

	/**
	 * 向公共代码表中插入一条记录  
	 * @param labCodeVo 封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 LabCodeVo 返回封装了公共代码信息的视图对象
	 */
	public LabCodeVo addLabCode(LabCodeVo labCodeVo) throws GlobalException;;

	/**
	 * 删除公共代码信息
	 * @param labCodeId 公共代码id
	 * @throws GlobalException  
	 * @return 返回类型 LabCodeVo 封装了公共代码信息的视图对象
	 */
	public boolean deleteLabCode(Serializable labCodeId) throws GlobalException;

	/**
	 * 修改公共代码信息
	 * @param labCodeVo 封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 LabCodeVo 封装了公共代码信息的视图对象
	 */
	public LabCodeVo updateLabCode(LabCodeVo labCodeVo) throws GlobalException;

	/**
	 * 根据id获得公共代码信息
	 * @param id  公共代码id
	 * @throws GlobalException  
	 * @return 返回类型 LabCodeVo 封装了公共代码信息的视图对象
	 */
	public LabCodeVo getLabCodeById(Serializable id) throws GlobalException;

	/**
	 * 获得公共代码信息列表
	 * @throws GlobalException  
	 * @return 返回类型 List 返回公共代码信息集合
	 */
	public List getLabCodeList() throws GlobalException;

	/**
	 * 获得公共代码信息分页对象
	 * @author Charles Xi
	 * @since 8.0
	 * @param hql 查询语句
	 * @param pageResult 分页对象初始化参数
	 * @return PageResult 分页对象
	 * @throws GlobalException 全局异常
	 */
	public PageResult getLabCodeList(String hql, PageResult pageResult)
			throws GlobalException;

	/** 
	 * 获得公共代码分页信息列表
	 * @param labCodeVo 封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型  PageResult  返回带有分页的公共代码信息列表
	 */
	public PageResult getLabCodePR(LabCodeVo labCodeVo, PageResult pageResult)
			throws GlobalException;

	/**
	 * 根据公共代码类型id获得公共代码信息
	 * @param labCodeVo  封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型  PageResult 返回带有分页的公共代码信息列表
	 */
	public PageResult getLabCodePRByType(LabCodeVo labCodeVo,
			PageResult pageResult) throws GlobalException;

	/**
	 *
	 * 根据公共类型id获得公共代码名称
	 * @param typeCode  公共代码类型的代码(code)值
	 * @throws GlobalException  
	 * @return 返回类型 String[] 返回公共代码id组成的数组
	 */
	public String[] getInstrumentNames(String typeCode) throws GlobalException;

	/** 
	 * 根据id获得公共代码类型
	 * @param id  公共代码类型id
	 * @throws GlobalException  
	 * @return 返回类型 LabTypeVo 封装了公共代码类型信息的视图对象
	 */
	public LabTypeVo getLabTypeById(Serializable id) throws GlobalException;

	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param labCode
	 * @param @param typeCode
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public String getLabCodeName(String labCode, String typeCode)
			throws GlobalException;

	/**
	 * 
	 * 判断公共代码类型下的公共代码名称是否重复 
	 * @param checkStr 需要验证的公共代码名称
	 * @param typeid 公共代码类型id
	 * @param flag  0:按公共代码名称验证,1:按公共代码code值验证
	 * @throws GlobalException  
	 * @return 返回类型 String 返回若为0表示不存在，1表示已存在
	 */
	public String isExsitLabCodeByCode(String checkStr, String typeid,
			String flag) throws GlobalException;

	/**
	 * 
	 * 根据公共代码类型code获得公共代码信息
	 * @param code 公共代码类型code
	 * @throws GlobalException  
	 * @return 返回类型  List  返回公共代码信息集合
	 */
	public List<LabCodeVo> getLabCodeByTypeCode(String code)
			throws GlobalException;

	/**
	 * 
	 * 获得某一公共代码类型下的公共代码序号最大值
	 * @param labCodeVo  封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 int 返回最大的序号值
	 */
	public int getMaxSort(LabCodeVo labCodeVo) throws GlobalException;

	/**
	 * 
	 * 根据公共代码表的名称和类型表的code获得代码信息
	 * @Description: TODO
	 * @param name
	 * @param typeCode  
	 * @return 返回类型 
	 * @throws GlobalException 
	 */
	public List<LabCodeVo> getLabCodeVoListByNameAndTypeCode(String name,
			String typeCode) throws GlobalException;
	
	public List getAllLabCodeList(LabCodeVo labCodeVo)throws GlobalException;
	/**
	 * 跳转到公共代码增加页面
	 * @param labCodeVo 封装了公共代码信息的视图对象
	 * @throws GlobalException  
	 * @return 返回类型 LabCodeVo  返回封装了公共代码信息的视图对象
	 */
	public LabCodeVo preAddLabComCode(LabCodeVo labCodeVo) throws GlobalException;
	
	public LabCodeVo deleteLabCode4Code(Serializable labCodeId)
	throws GlobalException;
	
	/**
	 * 根据公共代码类型code和名称模糊查询公共代码信息
	 * @param code
	 * @param name
	 * @return
	 * @throws GlobalException
	 */
	public List<String> getLabCodeList(String code,String name)
	throws GlobalException;
}
