package cn.labsoft.labos.source.reference.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabReference;

public interface ILabReferenceDAO extends IBaseDAO {
	/**
	 * 修改标准品
	 * <p>updateLabReference  修改标准品<br>
	 * 需要传入LabReference labReference
	 * @param LabReference labReference
	 * @return null
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabReference updateLabReference(LabReference labReference) throws GlobalException;
	/**
	 * 新增标准品
	 * <p>addLabReference  新增标准品<br>
	 * 需要传入LabReference po
	 * @param LabReference po
	 * @return null
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	LabReference addLabReference(LabReference po) throws GlobalException;

}
