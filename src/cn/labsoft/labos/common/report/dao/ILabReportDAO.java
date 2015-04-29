package cn.labsoft.labos.common.report.dao;

import java.util.List;

import cn.labsoft.labos.common.report.entity.LabReport;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 *报告模版-标签数据访问层接口
 * @author Quinn
 */
public interface ILabReportDAO extends IBaseDAO {
	/**
	 * 增加模版对象
	 * @param labReport  报告模版对象
	 * @return 报告模版对象
	 * @throws GlobalException 
	 */
	public LabReport addLabReport(LabReport labReport) throws GlobalException;
	/**
	 * 删除模版对象
	 * @param ids 对象id
	 * @return  true or false
	 */
	public boolean deleteLabReport(String ids[])throws GlobalException;
	/**
	 * 修改模版对象
	 * @param labReport 报告模版对象
	 * @return true or false
	 */
	public boolean updateLabReport(LabReport labReport)throws GlobalException;
	/**
	 * 查询模版对象
	 * @param id  对象id
	 * @return 报告模版对象
	 */
	public LabReport getLabReport(String id)throws GlobalException;
	/**
	 * 获取模版对象分页集合
	 * @param hql 组合查询语句
	 * @param pageResult 分页对象
	 * @return 报告模版分页集合对象
	 */
	public PageResult getLabReportPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 获取模版对象集合
	 * @param hql 组合查询语句
	 * @return 报告模版对象集合
	 */
	public List<LabReport> getLabReportList(String hql)throws GlobalException;
	
}
