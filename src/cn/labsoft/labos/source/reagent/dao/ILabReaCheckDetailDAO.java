package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckDetail;

public interface ILabReaCheckDetailDAO extends IBaseDAO {
	/**
	 * 新增试剂盘点信息
	 * <p>addLabReaCheckDetail  新增试剂盘点信息<br>
	 * 需要传入LabReaCheckDetail labReaCheckDetail
	 * @param LabReaCheckDetail labReaCheckDetail
	 * @return LabReaCheckDetail
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaCheckDetail addLabReaCheckDetail(LabReaCheckDetail labReaCheckDetail) throws GlobalException;

}
