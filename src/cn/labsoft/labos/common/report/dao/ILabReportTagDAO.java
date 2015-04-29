package cn.labsoft.labos.common.report.dao;

import java.util.List;

import cn.labsoft.labos.common.report.entity.LabReportTag;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 *报告模版-标签 数据访问层接口
 * @author Quinn
 */
public interface ILabReportTagDAO extends IBaseDAO {
	/**
	 * 增加报告标签信息
	 * @param labReportTag 报告标签对象
	 * @return 报告标签对象
	 * @throws GlobalException 
	 */
	public LabReportTag addLabReportTag(LabReportTag labReportTag) throws GlobalException;
	/**
	 * 删除标签信息
	 * @param ids 对象id
	 * @return true or false
	 */
	public boolean deleteLabReportTag(String ids[])throws GlobalException;
	/**
	 * 删除标签信息
	 * @param labReportTag 报告标签对象
	 * @return true or false
	 */
	public boolean deleteLabReportTag(LabReportTag labReportTag)throws GlobalException;
	/**
	 * 修改标签信息
	 * @param labReportTag 报告标签对象
	 * @return true or false
	 */
	public boolean updateLabReportTag(LabReportTag labReportTag)throws GlobalException;
	/**
	 * 查询标签信息
	 * @param id 对象id
	 * @return报告标签对象
	 */
	public LabReportTag getLabReportTag(String id)throws GlobalException;
	/**
	 * 查询标签集合信息
	 * @param hql 组合查询语句
	 * @param pageResult 分页对象
	 * @return 带有查询结果的对象
	 */
	public PageResult getLabReportTagPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 查询标签集合信息
	 * @param hql 组合查询语句
	 * @return 查询结果集合
	 */
	public List<LabReportTag> getLabReportTagList(String hql)throws GlobalException;
	
}
