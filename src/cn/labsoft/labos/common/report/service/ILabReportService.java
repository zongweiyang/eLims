package cn.labsoft.labos.common.report.service;

import java.util.List;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
/**
 * 报告模版逻辑处理层
 * @author quinn
 */
public interface ILabReportService {
	/**
	 * 增加模版对象
	 * @param labReportVo 模版对象
	 * @return 模版对象
	 * @throws GlobalException
	 */
	public LabReportVo addLabReport(LabReportVo labReportVo) throws GlobalException;
	/**
	 * copy模版对象
	 * @param labReportVo 模版对象
	 * @return 模版对象
	 * @throws GlobalException
	 */
	public LabReportVo addLabReport4Copy(LabReportVo labReportVo) throws GlobalException;;
	/**
	 * 删除模版对象
	 * @param ids 对象id
	 * @return  true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabReport(String[] ids) throws GlobalException;
	/**
	 * 假删除模版对象
	 * @param ids 对象id
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean updateLabReport4Del(String[] ids) throws GlobalException;
	/**
	 * 修改模版对象
	 * @param labReportVo 模版对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabReport(LabReportVo labReportVo) throws GlobalException;
	/**
	 * 查询模版对象
	 * @param id 对象id
	 * @return 报告模版对象
	 * @throws GlobalException
	 */
	public LabReportVo getLabReport(String id) throws GlobalException;
	/**
	 * 获取模版对象集合
	 * @param labReportVo 报告对象
	 * @return 查询结果集合
	 * @throws GlobalException
	 */
	public List<LabReportVo> getLabReportList(LabReportVo labReportVo) throws GlobalException;
	/**
	 * 获取模版对象分页集合
	 * @param labReportVo
	 * @param pageResult 分页查询对象
	 * @return 查询结果对象
	 * @throws GlobalException
	 */
	public PageResult getLabReportPR(LabReportVo labReportVo,PageResult pageResult) throws GlobalException;
	/**
	 * 查询模版设计对象
	 * @param id 对象id
	 * @return 报告模版对象
	 * @throws GlobalException
	 */
	public LabReportVo getLabReportModel(String id) throws GlobalException;
	/**
	 * 查询模版设计对象(代码编辑)
	 * @param id 对象id
	 * @return 报告模版对象
	 * @throws GlobalException
	 */
	public LabReportVo getLabReportModel4Code(String id) throws GlobalException;
	/**
	 * 修改模版设计
	 * @param labReportVo
	 * @return 报告模版对象
	 * @throws GlobalException
	 */
	public LabReportVo updateLabReportModel(LabReportVo labReportVo) throws GlobalException;
	/**
	 * 修改模版设计(代码编辑)
	 * @param labReportVo
	 * @return 报告模版对象
	 * @throws GlobalException
	 */
	public LabReportVo updateLabReportModel4Code(LabReportVo labReportVo) throws GlobalException;
//	/**
//	 * 根据功能id获取功能的主对象属性集合
//	 * @param funId
//	 * @return
//	 * @throws GlobalException
//	 */
	//public List<LabReportTagVo> getLabObjectFieldListByFunId(String funId) throws GlobalException;

	/**
	 * 业务模块编辑完报告后保存
	 * 根据功能id和业务id生成页面报告
	 * @param funId 当前功能id
	 * @param busId 当前功能下的业务id
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public LabReportVo updateLabReport4Bus(LabReportVo labReportVo) throws GlobalException;
	/**
	 * 报告生成
	 * 业务模块展示模版
	 * @param funId
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public LabReportVo getLabReport4Bus(LabReportVo labReportVo) throws GlobalException;
	/**
	 * 
	 * 报告生成
	 * 业务模块展示模版
	 * @param funId
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public LabReportVo addLabReport4Bus(LabReportVo labReportVo) throws GlobalException;
}
