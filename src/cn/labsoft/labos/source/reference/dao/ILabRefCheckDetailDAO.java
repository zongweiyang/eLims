package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefCheckDetail;

public interface ILabRefCheckDetailDAO extends IBaseDAO {
	/**
	 * 新增标准品盘点
	 * <p>addLabRefCheckDetail  新增标准品盘点<br>
	 * 需要传入LabRefCheckDetail labRefCheckDetail
	 * @param LabRefCheckDetail labRefCheckDetail
	 * @return LabRefCheckDetail
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefCheckDetail addLabRefCheckDetail(LabRefCheckDetail labRefCheckDetail) throws GlobalException;

}
