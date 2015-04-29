package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefCheckMain;

public interface ILabRefCheckMainDAO extends IBaseDAO {
	/**
	 * 新增标准品盘点
	 * <p>addLabRefCheckMain  新增标准品盘点<br>
	 * 需要传入LabRefCheckMain labRefCheckMain
	 * @param LabRefCheckMain labRefCheckMain
	 * @return LabRefCheckMain
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefCheckMain addLabRefCheckMain(LabRefCheckMain labRefCheckMain) throws GlobalException;

}
