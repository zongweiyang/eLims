package cn.labsoft.labos.source.quality.service;

import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckVo;




public interface ILabQuaManageCheckService {
	/**
	 * 添加管理评审记录对象信息
	 * @param labQuaManageCheckVo 管理评审记录对象
	 * @return 返回类型 管理评审记录对象(LabQuaManageCheckVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckVo addLabQuaManageCheck(LabQuaManageCheckVo labQuaManageCheckVo)throws GlobalException;
	/**
	 * 修改管理评审记录对象信息
	 * @param labQuaManageCheckVo 管理评审记录对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaManageCheck(LabQuaManageCheckVo labQuaManageCheckVo)throws GlobalException;
	/**
	 * 软删除管理评审记录对象信息
	 * @param ids 管理评审记录对象Id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaManageCheck(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得管理评审记录对象信息
	 * @param id 管理评审记录对象Id
	 * @return 返回类型 管理评审记录对象(LabQuaManageCheckVo) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckVo getLabQuaManageCheck(String id)throws GlobalException;
	/**
	 * 获得管理评审记录对象信息列表
	 * @param labQuaManageCheckVo 管理评审记录对象
	 * @return 返回类型 管理评审记录对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaManageCheckVo> getLabQuaManageCheckList(LabQuaManageCheckVo labQuaManageCheckVo)throws GlobalException;
	/**
	 * 根据pageResult获得管理评审记录对象分页信息列表，labQuaManageCheckVo为备用参数
	 * @param labQuaManageCheckVo 管理评审记录对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaManageCheckPR(LabQuaManageCheckVo labQuaManageCheckVo,PageResult pageResult )throws GlobalException;
	/**
	 * 修改管理评审报告
	 * @param labQuaManageCheckVo 管理评审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaManageCheck4Report(LabQuaManageCheckVo labQuaManageCheckVo) throws GlobalException;
}
