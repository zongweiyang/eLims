package cn.labsoft.labos.source.klg.service;


import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;



@SuppressWarnings("unchecked")
public interface ILabStandardService {

	/**
	 * 向表中插入一条记录
	 * 
	 * @param LabStandardVo
	 *            封装了功能信息的视图对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabStandard(LabStandardVo labStandardVo)
			throws GlobalException;;

	/**
	 * 根据功能id获得该条记录信息并修改信息后存入数据库
	 * 
	 * @param LabStandardVo
	 *            封装了功能信息的视图对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabStandard(LabStandardVo labStandardVo)
			throws GlobalException;

	/**
	 * 根据功能id获取该条功能信息并封装到LabStandardVo返回给页面
	 * 
	 * @param id
	 *            某条功能信息的id
	 * @throws GlobalException
	 * @return 返回类型 LabStandardVo 返回封装了功能信息的视图对象
	 * @author amy
	 */
	public LabStandardVo getLabStandard(String id) throws GlobalException;

	/**
	 * 软删除功能
	 * 
	 * @param LabStandardId
	 *            功能id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabStandard(String[] ids)
			throws GlobalException;
	/**
	 *  根据hql获得检测标准分页信息列表，LabStandardVo为备用参数
	 * @param labStandardVo 检测标准对象
	 * @param pageResult
	 * @return 返回类型 PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabStandardPR(LabStandardVo labStandardVo, PageResult pageResult) 
	throws GlobalException;
	/**
	 *  获取标准信息列表
	 * @param labStandardVo  检测标准对象
	 * @return 返回类型 检测标准对象集合(List)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardVo> getLabStandardList(LabStandardVo labStandardVo) throws GlobalException;
	/**
	 * 标准编号是否存在
	 * @param standsNo 标准编号
	 * @return  返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean exist4StandsCode(String standsNo)throws GlobalException ;
	/**
	 * 获取标准信息列表
	 * @param vo 检测标准对象
	 * @return 返回 检测标准对象(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardVo> getLabStandardVoList(LabStandardVo vo) throws GlobalException;
	/**
	 * 根据 typeId 获取检测标准信息列表
	 * @param typeId 彼岸准类型Id
	 * @return 返回 检测标准对象(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
    public List<LabStandardVo> getLabStandardByType(String typeId) throws GlobalException;
    /**
     * 查询为被替换的标准
     * @param labStandardVo 检测标准对象
     * @return 返回 检测标准对象(List) 类型
     * @author amy
     * @throws GlobalException 
     */
    public List<LabStandardVo> getLabStandardByStatusPR(LabStandardVo labStandardVo) throws GlobalException;
	/**
	 * 修改检测标准对象信息
	 * @param ids 标准Ids
	 * @param type 标准状态
	 * @return 返回 boolean
	 * @throws GlobalException
	 * @author amy
	 */
    public boolean updateLabStandard4Status(String ids,String type)
	throws GlobalException;
    /**
     * 根据name获取检测标准对象信息列表
     * @param name 标准名称
     * @return 返回 检测标准对象(List) 类型
     * @author amy
     * @throws GlobalException 
     */
    public List<LabStandardVo> getLabStandardByName(String name) throws GlobalException;
    /**
     * 根据code获取检测标准对象信息
     * @param code 标准编号
     * @return 返回类型 检测标准对象信息(LabStandardVo)
     * @author amy
     * @throws GlobalException 
     * */
    public LabStandardVo getLabStandardVoByCode(String code) throws GlobalException;
}
