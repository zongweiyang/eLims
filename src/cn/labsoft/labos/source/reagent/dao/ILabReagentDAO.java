package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReagent;

public interface ILabReagentDAO extends IBaseDAO {
	/**
	 * 修改试剂信息
	 * <p>updateLabReagent  修改试剂信息<br>
	 * 需要传入LabReagent labReagent
	 * @param LabReagent labReagent
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	boolean updateLabReagent(LabReagent labReagent) throws GlobalException;
	/**
	 * 新增试剂信息
	 * <p>addLabReagent  新增试剂信息<br>
	 * 需要传入LabReagent po
	 * @param LabReagent po
	 * @return LabReagent
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReagent addLabReagent(LabReagent po) throws GlobalException;

}
