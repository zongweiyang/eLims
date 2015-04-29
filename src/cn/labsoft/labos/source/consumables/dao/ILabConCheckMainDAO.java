package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConCheckMain;

public interface ILabConCheckMainDAO extends IBaseDAO {
	/**
	 * 新增耗材出库单
	 * <p>addLabConCheckMain 新增耗材出库单<br>
	 * 需要传入LabConCheckMain labConCheckMain
	 * @param LabConCheckMain labConCheckMain
	 * @return LabConCheckMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConCheckMain addLabConCheckMain(LabConCheckMain labConCheckMain) throws GlobalException;

}
