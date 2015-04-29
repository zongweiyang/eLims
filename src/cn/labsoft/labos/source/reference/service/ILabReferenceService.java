package cn.labsoft.labos.source.reference.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reference.vo.LabReferenceVo;

public interface ILabReferenceService {

	/**
	 * 得到标准品列表
	 * <p>getLabReferencePR  得到标准品列表<br>
	 * 需要传入LabReferenceVo labReferenceVo,PageResult pageResult
	 * @param LabReferenceVo labReferenceVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabReferencePR(LabReferenceVo labReferenceVo,PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个标准品对象
	 * <p>getLabReferenceList  得到一个标准品对象<br>
	 * 需要传入LabReferenceVo labReferenceVo
	 * @param LabReferenceVo labReferenceVo
	 * @return List<LabReferenceVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReferenceVo> getLabReferenceList(
			LabReferenceVo labReferenceVo) throws GlobalException;

	/**
	 * 得到一个标准品对象
	 * <p>getLabReferenceById  得到一个标准品对象<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReferenceVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReferenceVo getLabReferenceById(String id) throws GlobalException;

	/**
	 * 给一个类型下添加一个标准品
	 * <p>addLabReference  给一个类型下添加一个标准品<br>
	 * 需要传入LabReferenceVo labReferenceVo
	 * @param LabReferenceVo labReferenceVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabReference(LabReferenceVo labReferenceVo)
			throws GlobalException;

	/**
	 * 根据id软删除一个标准品
	 * <p>deleteLabReference  根据id软删除一个标准品<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReference(String id) throws GlobalException;

	/**
	 * 修改一个标准品的信息
	 * <p>updateLabReference  修改一个标准品的信息<br>
	 * 需要传入LabReferenceVo labReferenceVo
	 * @param LabReferenceVo labReferenceVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabReference(LabReferenceVo labReferenceVo)
			throws GlobalException;

	/**
	 * 根据标准品类型得到该类型下的所有标准品
	 * <p>getReferenceListByTypeId  根据标准品类型得到该类型下的所有标准品<br>
	 * 需要传入String typeId
	 * @param String typeId
	 * @return List<LabReferenceVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReferenceVo> getReferenceListByTypeId(String typeId)
			throws GlobalException;

	/**
	 * 根据ids查找标准品对象
	 * <p>getLabReferenceListByIds  根据ids查找标准品对象<br>
	 * 需要传入String ids
	 * @param String ids
	 * @return List<LabReferenceVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReferenceVo> getLabReferenceListByIds(String ids)
			throws GlobalException;

	/**
	 * 修改一个标准品的类型信息
	 * <p>updateLabReferenceMove  修改一个标准品的类型信息<br>
	 * 需要传入LabReferenceVo labReferenceVo
	 * @param LabReferenceVo labReferenceVo
	 * @return LabReferenceVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	public LabReferenceVo updateLabReferenceMove(LabReferenceVo labReferenceVo) throws GlobalException;

	/**
	 * 新增导出标准品
	 * <p>addLabReference4Import  修改一个标准品的类型信息<br>
	 * 需要传入String[][] strings,String typeId
	 * @param String[][] strings,String typeId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabReference4Import(String[][] strings,String typeId)throws GlobalException;
	/**
	 * 删除标准品信息通过类型Id
	 * <p>deleteLabReferenceByTypeId  删除标准品信息通过类型Id<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReferenceByTypeId(String id)throws GlobalException;
}
