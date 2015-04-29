package cn.labsoft.labos.common.template.dao;

import java.util.List;

import cn.labsoft.labos.common.template.entity.LabTemplate;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 页面管理DAO接口
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public interface ILabTemplateDAO extends IBaseDAO {
	/**
	 * 增加模板对象信息
	 * 
	 * @param labTemplate
	 *            封装了模板信息的对象
	 * @return LabTemplate
	 */
	public LabTemplate addLabTemplate(LabTemplate labTemplate) throws GlobalException;

	/**
	 * 修改模板对象信息
	 * 
	 * @param labTemplate
	 *            封装了模板信息的对象
	 * @return LabTemplate
	 */
	public LabTemplate updateLabTemplate(LabTemplate labTemplate) throws GlobalException;

	/**
	 * 删除模板对象信息
	 * 
	 * @param ids
	 *            模板ID数组集合
	 * @return boolean
	 */
	public boolean deleteLabTemplate(String ids[]) throws GlobalException;

	/**
	 * 根据Id查询模板对象信息
	 * 
	 * @param id
	 *            模板ID
	 * @return LabTemplate
	 */
	public LabTemplate getLabTemplate(String id) throws GlobalException;

	/**
	 * 根据hql语句获取模板对象分页列表信息
	 * 
	 * @param hql
	 * @param pageResult
	 *            模板分页对象
	 * @return PageResult
	 */
	public PageResult getLabTemplatePR(String hql, PageResult pageResult) throws GlobalException;

	/**
	 * 根据hql语句获取模板列表
	 * 
	 * @param hql
	 * @return List<LabTemplate>
	 */
	public List<LabTemplate> getLabTemplateList(String hql) throws GlobalException;
}