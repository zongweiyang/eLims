package cn.labsoft.labos.source.klg.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;

public interface ILabStandardTypeService {
	/**
	 * 添加标准类别对象信息
	 * @param labStandardTypeVo 标准类别对象信息
	 * @return 返回 标准类别对象信息(LabStandardTypeVo) 类型
	 * @throws GlobalException
	 * @author amy
	 */
	public LabStandardTypeVo addLabStandardType(LabStandardTypeVo labStandardTypeVo) throws GlobalException;

	/**
	 * 软删除标准类别对象信息
	 * @param ids 标准类别Id
	 * @return  返回 boolean 类型
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabStandardType(String[] ids) throws GlobalException;

    /**
     * 修改标准类别对象信息
     * @param labStandardTypeVo 标准类别对象信息 
     * @return  返回 标准类别对象信息(LabStandardTypeVo) 类型
     * @throws GlobalException
     * @author amy
     */
	public LabStandardTypeVo updateLabStandardType(LabStandardTypeVo labStandardTypeVo) throws GlobalException;

	/**
	 * 根据id查询标准类别对象信息
	 * @param id 标准Id
	 * @return 返回 标准类别对象信息(LabStandardTypeVo) 类型
	 * @throws GlobalException
	 * @author amy
	 */
	public LabStandardTypeVo getLabStandardType(String id) throws GlobalException;
	
	/** 
	 * 根据pageResult获得标准类别分页信息列表，labStandardTypeVo为备用参数
	 * @param labStandardTypeVo 标准类别对象信息
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabStandardTypePR(LabStandardTypeVo labStandardTypeVo, PageResult pageResult) throws GlobalException;

	/**
	 * 获得标准类别对象信息列表
	 * @param labStandardTypeVo 标准类别对象信息
	 * @throws GlobalException
	 * @return 返回类型 List 返回标准类别对象信息集合
	 * @author amy
	 */
	public List<LabStandardTypeVo> getLabStandardTypeList(LabStandardTypeVo labStandardTypeVo) throws GlobalException;

	/**
	 * 根据pid获得标准类别对象信息列表
	 * @param pid 标准父Id
	 * @return  返回类型 List 返回标准类别对象信息集合
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardTypeVo> getLabStandardTypeByPid(String pid) throws GlobalException;
	
	/**
	 * 根据 id 获得标准类别对象信息列表
	 * @param id 标准类别Id
	 * @return   返回类型 List 返回标准类别对象信息集合
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardTypeVo> getLabStandardTypeList(String id) throws GlobalException ;
}
