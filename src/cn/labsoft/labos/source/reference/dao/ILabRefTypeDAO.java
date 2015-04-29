package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefType;

public interface ILabRefTypeDAO extends IBaseDAO {
	/**
	 * 新增标准品类别
	 * <p>addLabRefType  新增标准品类别<br>
	 * 需要传入LabRefType labReferenceType
	 * @param LabRefType labReferenceType
	 * @return LabRefType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefType addLabRefType(LabRefType labReferenceType) throws GlobalException;
	/**
	 * 修改标准品类别
	 * <p>addLabRefType  修改标准品类别<br>
	 * 需要传入LabRefType labReferenceType
	 * @param LabRefType labReferenceType
	 * @return LabRefType
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabRefType updateLabReftype(LabRefType labReferenceType) throws GlobalException;

}
