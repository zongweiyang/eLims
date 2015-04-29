package cn.labsoft.labos.source.klg.dao;


import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabStandard;

public interface ILabStandardDAO extends IBaseDAO {
	/**
	 * 根据Id查询检测标准对象
	 * @param id 标准Id
	 * @return  返回 检测标准对象(LabStandard) 类型
	 * @author amy
	 */
	public LabStandard getLabStandard(String id);
	/**
	 * 根据name查询检测标准对象
	 * @param name 标准名称
	 * @return 返回 检测标准对象(LabStandard) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabStandard getLabStandardByName(String name) throws GlobalException;
	/**
	 * 根据code查询检测标准对象
	 * @param code 标准编号
	 * @return 返回 检测标准对象(LabStandard) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabStandard getLabStandardByCode(String code) throws GlobalException;
	/**
	 * 添加检测标准
	 * @param labStandard 标准对象
	 * @return 返回 检测标准对象(LabStandard) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabStandard addLabStandard(LabStandard labStandard) throws GlobalException;
	/**
	 * 修改检测标准
	 * @param labStandard 标准对象
	 * @return 返回 boolean 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabStandard(LabStandard labStandard) throws GlobalException;
}
