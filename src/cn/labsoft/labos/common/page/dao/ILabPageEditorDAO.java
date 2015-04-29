package cn.labsoft.labos.common.page.dao;

import java.util.List;

import cn.labsoft.labos.common.page.entity.LabPageEditor;
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
public interface ILabPageEditorDAO extends IBaseDAO {
	/**
	 * 增加页面对象信息
	 * 
	 * @param labPageEditor
	 *            封装了页面信息的对象
	 * @return LabPageEditor
	 */
	public LabPageEditor addLabPageEditor(LabPageEditor labPageEditor) throws GlobalException;

	/**
	 * 修改页面对象信息
	 * 
	 * @param labPageEditor
	 *            封装了页面信息的对象
	 * @return LabPageEditor
	 */
	public LabPageEditor updateLabPageEditor(LabPageEditor labPageEditor) throws GlobalException;

	/**
	 * 删除功能对象信息
	 * 
	 * @param ids
	 *            页面ID数组集合
	 * @return boolean
	 */
	public boolean deleteLabPageEditor(String ids[]) throws GlobalException;

	/**
	 * 根据Id查询页面对象信息
	 * 
	 * @param id
	 *            页面ID
	 * @return LabPageEditor
	 */
	public LabPageEditor getLabPageEditor(String id) throws GlobalException;

	/**
	 * 根据hql语句获取页面对象分页列表信息
	 * 
	 * @param hql
	 * @param pageResult
	 *            分页对象
	 * @return PageResult
	 */
	public PageResult getLabPageEditorPR(String hql, PageResult pageResult) throws GlobalException;

	/**
	 * 根据hql语句获取页面列表
	 * 
	 * @param hql
	 * @return List<LabPageEditor>
	 */
	public List<LabPageEditor> getLabPageEditorList(String hql) throws GlobalException;
}