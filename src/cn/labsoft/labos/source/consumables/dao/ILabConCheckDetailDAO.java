package cn.labsoft.labos.source.consumables.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConCheckDetail;

public interface ILabConCheckDetailDAO extends IBaseDAO {
	/**
	 * 新增耗材出库单
	 * <p>addLabConCheckDetail 新增耗材出库单<br>
	 * 需要传入LabConCheckDetail labConCheckDetail
	 * @param LabConCheckDetail labConCheckDetail
	 * @return LabConCheckDetail
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabConCheckDetail addLabConCheckDetail(LabConCheckDetail labConCheckDetail) throws GlobalException;

}
