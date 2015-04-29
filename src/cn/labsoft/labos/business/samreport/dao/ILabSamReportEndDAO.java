package cn.labsoft.labos.business.samreport.dao;

import java.util.List;

import cn.labsoft.labos.business.samreport.entity.LabSamReportEnd;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 报告归档信息 数据访问对象接口
 * @author Quinn
 */
public interface ILabSamReportEndDAO extends IBaseDAO {
	/**
	 * 增加报告归档信息
	 * @param labSamReportEnd 报告归档持久化对象
	 * @return
	 * @throws GlobalException 
	 */
	public LabSamReportEnd addLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException;
	/**
	 * 删除报告归档信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSamReportEnd(String ids[]) throws GlobalException;
	/**
	 *  删除报告归档信息
	 * @param labSamReportEnd 报告归档持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException;
	/**
	 * 修改报告归档信息
	 * @param labSamReportEnd 报告归档持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException;
	/**
	 * 查询报告归档信息
	 * @param id 对象id
	 * @return 报告归档对象
	 * @throws GlobalException 
	 */
	public LabSamReportEnd getLabSamReportEnd(String id) throws GlobalException;
	/**
	 * 获取报告归档分页信息
	 * @param hql 组合查询语句
	 * @param pageResult 封装分页信息的对象
	 * @return 带有查询结果的分页信息对象
	 * @throws GlobalException 
	 */
	public PageResult getLabSamReportEndPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取报告归档集合
	 * @param hql 组合查询语句
	 * @return 查询结果对象集合
	 * @throws GlobalException 
	 */
	public List<LabSamReportEnd> getLabSamReportEndList(String hql) throws GlobalException;
	
}
