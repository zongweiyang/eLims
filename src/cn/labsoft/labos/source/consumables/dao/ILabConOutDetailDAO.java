package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConOutDetail;

public interface ILabConOutDetailDAO extends IBaseDAO {
	/**
	 * 新增耗材出库单
	 * <p>addLabConOutDetail 新增耗材出库单<br>
	 * 需要传入LabConOutMain labConOutMain
	 * @param LabConOutMain labConOutMain
	 * @return LabConOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	void addLabConOutDetail(LabConOutDetail labConOutDetail) throws GlobalException;

}
