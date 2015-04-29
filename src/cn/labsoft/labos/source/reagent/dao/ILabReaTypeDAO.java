package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaType;

public interface ILabReaTypeDAO extends IBaseDAO {
	/**
	 * 修改试剂类别信息
	 * <p>updateLabReaType  修改试剂类别信息<br>
	 * 需要传入LabReaType labReaType
	 * @param LabReaType labReaType
	 * @return LabReaType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaType updateLabReaType(LabReaType labReaType) throws GlobalException;
	/**
	 * 新增试剂类别信息
	 * <p>addLabReaType  新增试剂类别信息<br>
	 * 需要传入LabReaType labReaType
	 * @param LabReaType labReaType
	 * @return LabReaType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaType addLabReaType(LabReaType labReagentType) throws GlobalException;

}
