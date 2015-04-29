package cn.labsoft.labos.base.user.service;

import java.util.List;

import cn.labsoft.labos.base.user.vo.LabUserTrainRecordVo;
import cn.labsoft.labos.base.user.vo.LabUserTrainVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
/**
 * 用户培训服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabUserTrainService {
	/**
	 * 增加用户培训
	 * @param labUserTrainVo 用户培训信息对象
	 * @return 用户培训信息对象
	 * @throws GlobalException
	 */
	public LabUserTrainVo addLabUserTrain(LabUserTrainVo labUserTrainVo) throws GlobalException;;
	/**
	 * 删除培训计划
	 * @param ids 对象ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabUserTrain (String[] ids) throws GlobalException;
	/**
	 * 假删除培训计划
	 * @param ids 对象ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUserTrain4Del(String[] ids) throws GlobalException;
	/**
	 * 修改培训记录
	 * @param labUserTrainVo 用户培训信息对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUserTrain4Record(LabUserTrainVo labUserTrainVo) throws GlobalException;
	/**
	 * 查询用户培训计划信息
	 * @param labUserTrainVo 用户培训信息对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUserTrain(LabUserTrainVo labUserTrainVo) throws GlobalException;
	/**
	 * 查询用户培训计划信息
	 * @param id 对象id
	 * @return 用户培训计划对象
	 * @throws GlobalException
	 */
	public LabUserTrainVo getLabUserTrain(String id) throws GlobalException;
	/**
	 * 获取用于培训计划的集合信息
	 * @param labUserTrainVo 用户培训信息对象
	 * @return 用户培训计划集合
	 * @throws GlobalException
	 */
	public List<LabUserTrainVo> getLabUserTrainList(LabUserTrainVo labUserTrainVo) throws GlobalException;
	/**
	 * 获取用于培训计划的集合信息
	 * @param labUserTrainRecordVo 用户培训记录信息对象
	 * @return 用户培训计划集合
	 * @throws GlobalException
	 */
	public List<LabUserTrainRecordVo> getLabUserTrainRecordList(LabUserTrainRecordVo labUserTrainRecordVo) throws GlobalException;
	/**
	 * 获取用于培训计划的分页信息对象
	 * @param labUserTrainVo 用户培训信息对象
	 * @param pageResult 分页信息对象
	 * @return  有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabUserTrainPR(LabUserTrainVo labUserTrainVo,PageResult pageResult) throws GlobalException;
	
     }
