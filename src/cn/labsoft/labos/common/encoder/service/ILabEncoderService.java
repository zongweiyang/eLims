package cn.labsoft.labos.common.encoder.service;

import java.util.List;

import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 条码管理DAO
 * @author danlee Li
 *
 */
public interface ILabEncoderService {
	/**
	 * 添加条码定义信息
	 * @param labEncoderVo	封装条码视图对象
	 * @return LabEncoderVo	封装条码视图对象
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public LabEncoderVo addLabEncoder(LabEncoderVo labEncoderVo)
			throws GlobalException;;
	/**
	 * 根据ids删除条码信息
	 * @param ids
	 * @return boolean
	 */
	public boolean deleteLabEncoder(String[] ids) throws GlobalException;
	
	/**
	 * 删除条码-假删除
	 * @param ids
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabEncoder4Del(String[] ids) throws GlobalException;

	/**
	 * 修改条码
	 * @param labEncoderVo 封装条码视图对象
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabEncoder(LabEncoderVo labEncoderVo)
			throws GlobalException;
	/**
	 * 根据ID获得条码信息
	 * @param id
	 * @return LabEncoderVo 封装条码视图对象
	 * @throws GlobalException
	 */
	public LabEncoderVo getLabEncoder(String id) throws GlobalException;
	
	/**
	 * 查找条码list列表
	 * @param labEncoderVo 封装了条码视图对象
	 * @return List<LabEncoderVo>
	 * @throws GlobalException
	 */
	public List<LabEncoderVo> getLabEncoderList(LabEncoderVo labEncoderVo)
			throws GlobalException;
	/**
	 * 查找条码分页列表
	 * @param labEncoderVo 封装了条码对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabEncoderPR(LabEncoderVo labEncoderVo,
			PageResult pageResult) throws GlobalException;
	/**
	 * 查看条码定义信息
	 * @param busId 业务ID
	 * @param busType 业务类型
	 * @return LabEncoderVo	封装条码对象
	 * @throws GlobalException
	 */
	public LabEncoderVo showLabEncoder(String busId, String busType)
			throws GlobalException;
	/**
	 * 添加二维码
	 * @param labEncoderVo
	 * 					封装条码对象
	 * @return LabEncoderVo 
	 * 					封装条码对象
	 * @throws GlobalException
	 */
	public LabEncoderVo addLabEncoder4Two(LabEncoderVo labEncoderVo)
			throws GlobalException;

	/**
	 * 获得条码打印信息
	 * @param busId 业务ID
	 * @param busList 
	 * @param barCodeType 条码类型
	 * @return	LabEncoderVo 封装了条码视图对象
	 * @throws GlobalException
	 */
	public LabEncoderVo getLabEncoder4Print(String busId, List<?> busList,
			String barCodeType)throws GlobalException;
	/**
	 * 获得功能ID获得当前对象
	 * @param funId 功能ID
	 * @return String 返回当前对象属性的键值对 key 属性名 value 属性名所对应的中文名称
	 * @throws GlobalException
	 */
	public String getLabObjectByFunId(String funId)throws GlobalException;
	/**
	 * 获得条码信息
	 * @param labEncoderVo 封装了条码视图对象
	 * @param barCodeType	条码类型
	 * @return LabEncoderVo 封装条码视图对象
	 * @throws GlobalException
	 */
	public LabEncoderVo getLabEncoder4Show( LabEncoderVo labEncoderVo,String barCodeType)throws GlobalException;
	/**
	 * 根据业务ID查找该业务定义条码数，判断该业务ID是否已经定义条码
	 * @param busId 业务ID
	 * @return String	返回条码数
	 * @throws GlobalException
	 */
	public String  getLabEncoder4BusId(String busId) throws GlobalException;
}
