package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefOutDetail;

public interface ILabRefOutDetailDAO extends IBaseDAO {
	/**
	 * 新增出库单
	 * <p>addLabRefOutDetail  新增出库单<br>
	 * 需要传入LabRefOutDetail labRefOutDetail
	 * @param LabRefOutDetail labRefOutDetail
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	void addLabRefOutDetail(LabRefOutDetail labRefOutDetail);

}
