package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;

public interface ILabConsumablesDAO extends IBaseDAO {
	/**
	 * 修改耗材
	 * <p>updateLabConsumables  修改耗材<br>
	 * 需要传入LabConType labConsumablesType
	 * @param LabConType labConsumablesType
	 * @return LabConType
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabConsumables updateLabConsumables(LabConsumables labConsumables);
	/**
	 * 新增耗材
	 * <p>addLabConsumables 新增耗材<br>
	 * 需要传入LabConsumables po
	 * @param LabConsumables po
	 * @return LabConsumables
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConsumables addLabConsumables(LabConsumables po) throws GlobalException;

}
