package cn.labsoft.labos.business.samreport.dao;

import java.util.List;

import cn.labsoft.labos.business.samreport.entity.LabSamReport;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 报告信息 数据访问对象接口
 * @author Quinn
 */
public interface ILabSamReportDAO extends IBaseDAO {
	/**
	 * 增加报告信息
	 * @param labSamReport 报告信息持久化对象
	 * @return  报告信息对象
	 */
	public LabSamReport addLabSamReport(LabSamReport labSamReport);
	/**
	 * 删除报告
	 * @param ids 对象id
	 * @return true or false
	 */
	public boolean deleteLabSamReport(String ids[]);
	/**
	 * 删除报告
	 * @param labSamReport 报告信息持久化对象
	 * @return true or false
	 */
	public boolean deleteLabSamReport(LabSamReport labSamReport);
	/**
	 * 修改报告
	 * @param labSamReport 报告信息持久化对象
	 * @return true or false
	 */
	public boolean updateLabSamReport(LabSamReport labSamReport);
	/**
	 * 查询报告
	 * @param id 对象id
	 * @return 报告信息对象
	 */
	public LabSamReport getLabSamReport(String id);
	/**
	 *  获取报告分页信息
	 * @param hql 组合查询语句
	 * @param pageResult 封装分页信息的对象
	 * @return 带有查询结果的分页信息对象
	 */
	public PageResult getLabSamReportPR(String hql, PageResult pageResult);
	/**
	 * 获取报告对象集合
	 * @param hql 组合查询语句
	 * @return 查询结果对象集合
	 */
	public List<LabSamReport> getLabSamReportList(String hql);
	
}
