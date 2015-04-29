package cn.labsoft.labos.business.sample.service;

import java.util.List;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;
/**
 * 检测任务服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabSampRegisterService {
	/**
	 * 增加或修改任务tab1
	 * @param labSampRegisterVo 任务对象
	 * @return 任务对象
	 * @throws GlobalException
	 */
	public LabSampRegisterVo saveLabSampRegister4Tab1(LabSampRegisterVo labSampRegisterVo) throws GlobalException;;
	/**
	 * 增加或修改任务tab2
	 * @param labSampRegisterVo 任务对象
	 * @return 任务对象
	 * @throws GlobalException
	 */
	public LabSampRegisterVo saveLabSampRegister4Tab2(LabSampRegisterVo labSampRegisterVo,List<LabSamVo> sampList) throws GlobalException;
	/**
	 * 增加或修改任务tab3
	 * @param labSampRegisterVo 任务对象
	 * @return 任务对象
	 * @throws GlobalException
	 */
	public LabSampRegisterVo saveLabSampRegister4Tab3(LabSampRegisterVo labSampRegisterVo,LabChargeVo labChargeVo) throws GlobalException;
	/**
	 * 删除任务
	 * @param ids 对象ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabSampRegister (String[] ids) throws GlobalException;
	/**
	 * 修改任务
	 * @param labSampRegisterVo 任务对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSampRegister(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 查看任务
	 * @param id 对象id
	 * @return 任务对象
	 * @throws GlobalException
	 */
	public LabSampRegisterVo getLabSampRegister(String id) throws GlobalException;
	/**
	 * 获取任务列表
	 * @param labSampRegisterVo 任务对象
	 * @return 任务对象集合
	 * @throws GlobalException
	 */
	public List<LabSampRegisterVo> getLabSampRegisterList(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 获取任务分页信息
	 * @param labSampRegisterVo 任务对象
	 * @param pageResult 分页信息的对象
	 * @return 带查询结果的分页信息的对象
	 * @throws GlobalException
	 */
	public PageResult getLabSampRegisterPR(LabSampRegisterVo labSampRegisterVo,PageResult pageResult) throws GlobalException;
	/**
	 * 获取任务分页信息
	 * @param labSampRegisterVo 任务对象
	 * @param pageResult 分页信息的对象
	 * @return 带查询结果的分页信息的对象
	 * @throws GlobalException
	 */
	public PageResult getLabSampRegisterPR4Audit(LabSampRegisterVo labSampRegisterVo,PageResult pageResult) throws GlobalException;
	/**
	 * 查看费用信息
	 * @param labSampRegisterVo 任务对象
	 * @return 费用对象
	 * @throws GlobalException
	 */
	public LabChargeVo getLabCharge(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 查看样品信息
	 * @param labSampRegisterVo 任务对象
	 * @return 样品对象集合
	 * @throws GlobalException
	 */
	public List<LabSamVo> getLabSamList(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 假删除任务信息
	 * @param ids 对象ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSampRegister4Del(String[] ids) throws GlobalException;
	/**
	 * 任务审核
	 * @param labSampRegisterVo 任务对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSampRegister4Audit(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 任务复核
	 * @param labSampRegisterVo 任务对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSampRegister4Check(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 任务审批
	 * @param labSampRegisterVo 任务对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSampRegister4Approve(LabSampRegisterVo labSampRegisterVo) throws GlobalException;
	/**
	 * 验证是否填写检测项目
	 * @param labSampRegisterVo 任务对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean ajaxLabSampItem4Exsit(String id) throws GlobalException;
	/**
	 * 
	 * @param labSampRegisterVo 任务对象
	 * @param sampList
	 * @return 任务对象
	 * @throws GlobalException
	 */
	public LabSampRegisterVo saveLabSampRegister4Other(LabSampRegisterVo labSampRegisterVo,List<LabSamVo> sampList) throws GlobalException ;
}
