package cn.labsoft.labos.source.klg.dao;


import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabStandardType;

public interface ILabStandardTypeDAO extends IBaseDAO {
	
	/**
	 * 添加标准分类
	 * @param LabStandardType 标准分类对象
	 * @return 返回 boolean 类型
	 * @author amy
	 */
	public boolean addLabStandardType(LabStandardType labStandardType);

	/**
	 * 修改标准分类
	 * @param LabStandardType 标准分类对象
	 * @return 返回 boolean 类型
	 * @author amy
	 */
	public boolean updateLabStandardType(LabStandardType labStandardType);

	/**
	 * 删除标准分类
	 * @param LabStandardType 标准分类对象
	 * @return 返回 boolean 类型
	 * @author amy
	 */
	public boolean deleteLabStandardType(LabStandardType labStandardType);

	/**
	 * 根据parentId获取标准分类列表
	 * @param parentId 标准分类父Id
	 * @return 返回 标准分类对象集合(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardType> getStandTypeListByParentId(String parentId) throws GlobalException;
}
