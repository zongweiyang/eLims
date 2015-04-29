package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckMain;

public interface ILabReaCheckMainDAO extends IBaseDAO {
	/**
	 * 新增试剂盘点信息
	 * <p>addLabReaCheckMain  新增试剂盘点信息<br>
	 * 需要传入LabReaCheckMain labReaCheckMain
	 * @param LabReaCheckMain labReaCheckMain
	 * @return LabReaCheckMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaCheckMain addLabReaCheckMain(LabReaCheckMain labReaCheckMain) throws GlobalException;

}
