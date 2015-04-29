package cn.labsoft.labos.source.reagent.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaOutDetail;

public interface ILabReaOutDetailDAO extends IBaseDAO {
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
	LabReaOutDetail addLabReaOutDetail(LabReaOutDetail labReaOutDetail) throws GlobalException;

}
