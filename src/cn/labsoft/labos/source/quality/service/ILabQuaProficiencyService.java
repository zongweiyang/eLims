package cn.labsoft.labos.source.quality.service;

import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyVo;

public interface ILabQuaProficiencyService {
	/**
	 * 添加比对验证记录对象信息
	 * @param labQuaProficiencyVo 比对验证记录对象
	 * @return 返回类型 比对验证记录对象(LabQuaProficiencyVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyVo addLabQuaProficiency(LabQuaProficiencyVo labQuaProficiencyVo)throws GlobalException;
	/**
	 * 修改比对验证记录对象信息
	 * @param labQuaProficiencyVo 比对验证记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaProficiency(LabQuaProficiencyVo labQuaProficiencyVo)throws GlobalException;
	/**
	 * 软删除比对验证记录对象信息
	 * @param ids 比对验证记录对象Id
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaProficiency(String[] ids)throws GlobalException;
	/**
	 * 根据id获取比对验证记录对象信息
	 * @param id 比对验证记录对象Id
	 * @return 返回类型 比对验证记录对象(LabQuaProficiencyVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyVo getLabQuaProficiency(String id)throws GlobalException;
	/**
	 * 获得比对验证记录对象信息列表
	 * @param labQuaProficiencyVo 比对验证记录对象
	 * @return 返回类型 比对验证记录对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaProficiencyVo> getLabQuaProficiencyList(LabQuaProficiencyVo labQuaProficiencyVo)throws GlobalException;
	/**
	 * 根据pageResult获得比对验证记录对象分页信息列表，labQuaProficiencyVo为备用参数
	 * @param labQuaProficiencyVo 比对验证记录对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaProficiencyPR(LabQuaProficiencyVo labQuaProficiencyVo, PageResult pageResult)throws GlobalException;
	
}
