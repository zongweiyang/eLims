package cn.labsoft.labos.business.samreport.dao;

import java.util.List;

import cn.labsoft.labos.business.samreport.entity.LabSamReportData;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 报告数据信息 数据访问对象接口
 * @author Quinn
 */
public interface ILabSamReportDataDAO extends IBaseDAO {
	/**
	 * 增加报告数据信息
	 * @param labSamReportData 报告数据持久化对象
	 * @return  报告数据对象
	 * @throws GlobalException 
	 */
	public LabSamReportData addLabSamReportData(LabSamReportData labSamReportData) throws GlobalException;
	/**
	 * 删除报告数据信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSamReportData(String ids[]) throws GlobalException;
	/**
	 * 删除报告数据信息
	 * @param labSamReportData 报告数据持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSamReportData(LabSamReportData labSamReportData) throws GlobalException;
	/**
	 * 修改报告数据信息
	 * @param labSamReportData 报告数据持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabSamReportData(LabSamReportData labSamReportData) throws GlobalException;
	/**
	 * 查询报告数据对象
	 * @param id 对象id
	 * @return  报告数据对象
	 * @throws GlobalException 
	 */
	public LabSamReportData getLabSamReportData(String id) throws GlobalException;
	/**
	 * 获取报告数据分页信息
	 * @param hql 组合查询语句
	 * @param pageResult 封装分页信息的对象
	 * @return 带有查询结果的分页信息对象
	 * @throws GlobalException 
	 */
	public PageResult getLabSamReportDataPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 *  获取报告数据对象集合
	 * @param hql 组合查询语句
	 * @return 查询结果对象集合
	 * @throws GlobalException 
	 */
	public List<LabSamReportData> getLabSamReportDataList(String hql) throws GlobalException;
	
}
