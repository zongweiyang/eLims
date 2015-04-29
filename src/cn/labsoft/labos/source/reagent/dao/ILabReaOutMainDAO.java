package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaOutMain;

public interface ILabReaOutMainDAO extends IBaseDAO {
	/**
	 * 增加出库信息
	 * <p>addLabReaOutMain  增加出库信息<br>
	 * 需要传入LabReaOutMain labReaOutMain
	 * @param LabReaOutMain labReaOutMain
	 * @return LabReaOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaOutMain addLabReaOutMain(LabReaOutMain labReaOutMain) throws GlobalException;
	/**
	 * 修改出库信息
	 * <p>updateLabReaOutMain  修改出库信息<br>
	 * 需要传入LabReaOutMain po
	 * @param LabReaOutMain po
	 * @return LabReaOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	LabReaOutMain updateLabReaOutMain(LabReaOutMain po) throws GlobalException;

}
