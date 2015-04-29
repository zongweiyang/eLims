package cn.labsoft.labos.common.makecode.service;

import java.util.List;
import cn.labsoft.labos.common.makecode.vo.LabMakeCodeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabMakeCodeService {
	
	/**
	 * 新增代码生成信息
	 * @param labMakeCodeVo
	 * @return
	 * @throws GlobalException
	 */
	public LabMakeCodeVo addLabMakeCode(LabMakeCodeVo labMakeCodeVo) throws GlobalException;;
	/**
	 * 真删除代码生成信息
	 * @param ids
	 * @return true:删除成功,false:删除失败
	 */
	public boolean deleteLabMakeCode(String[] ids) throws GlobalException;
	/**
	 * 假删除对象信息（单删除/批量删除）
	 * @param ids
	 * @return true：删除成功，false:删除失败
	 */
	public boolean updateLabMakeCode4Del(String[] ids) throws GlobalException;
	/**
	 * 修改生成代码信息
	 * @param labMakeCodeVo
	 * @return true:修改成功false：修改失败
	 * @throws GlobalException
	 */
	public boolean updateLabMakeCode(LabMakeCodeVo labMakeCodeVo) throws GlobalException;
	/**
	 * 根据Id获取生成代码信息
	 * @param id
	 * @return 生成代码对象信息
	 * @throws GlobalException
	 */
	public LabMakeCodeVo getLabMakeCode(String id) throws GlobalException;
	
	/**
	 * 根据条件获取生成代码对象集合信息
	 * @param labMakeCodeVo
	 * @return 生成代码集合信息
	 * @throws GlobalException
	 */
	public List<LabMakeCodeVo> getLabMakeCodeList(LabMakeCodeVo labMakeCodeVo) throws GlobalException;
	/**
	 * 获取生成代码分页集合信息
	 * @param labMakeCodeVo
	 * @param pageResult
	 * @return
	 * @throws GlobalException
	 */
	public PageResult getLabMakeCodePR(LabMakeCodeVo labMakeCodeVo,PageResult pageResult) throws GlobalException;
	
     }
