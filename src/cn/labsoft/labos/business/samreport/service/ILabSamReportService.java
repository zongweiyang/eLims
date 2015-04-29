package cn.labsoft.labos.business.samreport.service;

import java.util.List;
import cn.labsoft.labos.business.samreport.vo.LabSamReportVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 检测报告服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabSamReportService {
	/**
	 * 增加检测报告
	 * @param labSamReportVo 报告对象Vo
	 * @return 报告对象Vo
	 * @throws GlobalException
	 */
	public LabSamReportVo addLabSamReport(LabSamReportVo labSamReportVo) throws GlobalException;;
	/**
	 * 删除报告信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabSamReport (String[] ids) throws GlobalException;
	/**
	 * 假删除报告信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSamReport4Del(String[] ids) throws GlobalException;
	/**
	 * 报告编制 报告生成是修改相关信息
	 * @param labSamReportVo 报告对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSamReport4Report(LabSamReportVo labSamReportVo) throws GlobalException;
	/**
	 * 报告编制方法
	 * @param labSamReportVo 报告对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSamReport(LabSamReportVo labSamReportVo) throws GlobalException;
	/**
	 * 报告审核方法
	 * @param labSamReportVo 报告对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSamReport4Audit(LabSamReportVo labSamReportVo) throws GlobalException;
	/**
	 * 报告发送方法
	 * @param labSamReportVo 报告对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabSamReport4Send(LabSamReportVo labSamReportVo) throws GlobalException;
	/**
	 * 查询检测报告
	 * @param id 对象id
	 * @return 报告对象Vo
	 * @throws GlobalException
	 */
	public LabSamReportVo getLabSamReport(String id) throws GlobalException;
	/**
	 * 查询检测报告集合
	 * @param labSamReportVo 报告对象Vo
	 * @return  检测报告集合
	 * @throws GlobalException
	 */
	public List<LabSamReportVo> getLabSamReportList(LabSamReportVo labSamReportVo) throws GlobalException;
	/**
	 * 报告编制列表页面
	 * @param labSamReportVo 报告对象Vo
	 * @param pageResult 分页信息对象
	 * @return 还有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabSamReportPR(LabSamReportVo labSamReportVo,PageResult pageResult) throws GlobalException;
	/**
	 * 报告审核列表页面
	 * @param labSamReportVo 报告对象Vo
	 * @param pageResult 分页信息对象
	 * @return 还有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabSamReportPR4Audit(LabSamReportVo labSamReportVo,PageResult pageResult) throws GlobalException;
	/**
	 * 报告发送列表页面
	 * @param labSamReportVo 报告对象Vo
	 * @param pageResult 分页信息对象
	 * @return 还有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabSamReportPR4Send(LabSamReportVo labSamReportVo,PageResult pageResult) throws GlobalException;
	/**
	 * 查询检测报告
	 * @param id 对象id
	 * @return 报告对象Vo
	 * @throws GlobalException
	 */
	public LabSamReportVo getLabSamReport4Send(String id) throws GlobalException;
	
}
