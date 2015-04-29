package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConOutMain;

public interface ILabConOutMainDAO extends IBaseDAO {
	/**
	 * 新增耗材出库
	 * <p>addLabConOutMain 新增耗材出库<br>
	 * 需要传入LabConOutMain labConOutMain
	 * @param LabConOutMain labConOutMain
	 * @return LabConOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConOutMain addLabConOutMain(LabConOutMain labConOutMain) throws GlobalException;
	/**
	 * 修改增耗材出库
	 * <p>updateLabConOutMain 修改增耗材出库<br>
	 * 需要传入LabConOutMain labConOutMain
	 * @param LabConOutMain labConOutMain
	 * @return LabConOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConOutMain updateLabConOutMain(LabConOutMain labConOutMain) throws GlobalException;

}
