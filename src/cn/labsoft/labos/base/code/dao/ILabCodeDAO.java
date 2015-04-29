package cn.labsoft.labos.base.code.dao;

import java.util.List;

import cn.labsoft.labos.base.code.entity.LabCode;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * @author  作者 bill 
 * @version LABOS V1.0
 */
public interface ILabCodeDAO extends IBaseDAO {
	public List<LabCode> getLabCodeByTypeId(String id) throws GlobalException;

	public List<LabCode> getLabCodeByTypeCode(String code) throws GlobalException;

	public LabCode addLabCode(LabCode labCode);

	public LabCode getLabCode(String id);

	public LabCode updateLabCode(LabCode labCode);

	public LabCode deleteLabCode(LabCode labCode);

	public String getLcztMapping(String flag, String code, String value) throws GlobalException;// flag:0-getName,1-getCode

}
