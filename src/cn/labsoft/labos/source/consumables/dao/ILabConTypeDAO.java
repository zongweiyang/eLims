package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConType;

public interface ILabConTypeDAO extends IBaseDAO {
	/**
	 * 新增耗材类型
	 * <p>addLabConType  新增耗材类型<br>
	 * 需要传入LabConType labConsumablesType
	 * @param LabConType labConsumablesType
	 * @return LabConType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConType addLabConType(LabConType labConsumablesType) throws GlobalException;
	/**
	 * 修改耗材类型
	 * <p>updateLabConType  修改耗材类型<br>
	 * 需要传入LabConType labConsumablesType
	 * @param LabConType labConsumablesType
	 * @return LabConType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConType updateLabConType(LabConType labConsumablesType) throws GlobalException;

}
