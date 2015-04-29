package cn.labsoft.labos.base.code.dao;

import cn.labsoft.labos.base.code.entity.LabType;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * @author  作者 bill 
 * @version LABOS V1.0
 */
public interface ILabTypeDAO extends IBaseDAO {
	public LabType getLabTypeByTypeCode(String typeCode) throws GlobalException;

	public LabType getLabType(String id);

	public LabType addLabType(LabType labType);

	public LabType updateLabType(LabType labType);

	public boolean deleteLabType(LabType labType) throws GlobalException;
}
