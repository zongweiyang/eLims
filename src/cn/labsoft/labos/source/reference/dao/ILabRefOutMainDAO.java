package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefOutMain;

public interface ILabRefOutMainDAO extends IBaseDAO {
	/**
	 * 新增出库单
	 * <p>addLabRefOutMain  新增出库单<br>
	 * 需要传入LabRefOutMain labRefOutMain
	 * @param LabRefOutMain labRefOutMain
	 * @return LabRefOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefOutMain addLabRefOutMain(LabRefOutMain labRefOutMain) throws GlobalException;
	/**
	 * 修改出库单
	 * <p>updateLabRefOutMain  修改出库单<br>
	 * 需要传入LabRefOutMain po
	 * @param LabRefOutMain po
	 * @return LabRefOutMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefOutMain updateLabRefOutMain(LabRefOutMain po) throws GlobalException;

}
