package cn.labsoft.labos.common.query.dao;

import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
public interface ILabParameterDAO extends IBaseDAO {
	
	public LabParameter getLabParameter(String id) throws GlobalException;
	public LabParameter addLabParameter(LabParameter labParameter) throws GlobalException;
}
