package cn.labsoft.labos.common.page.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.page.dao.ILabPageEditorDAO;
import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 页面管理DAO实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Repository(value = "labPageEditorDAO")
public class LabPageEditorDAOImpl extends BaseDAO implements ILabPageEditorDAO {
	private static Log log = LogFactory.getLog(LabPageEditorDAOImpl.class);

	/**
	 * 增加页面对象信息
	 * 
	 * @param labPageEditor
	 * @return LabPageEditor
	 */
	@Override
	public LabPageEditor addLabPageEditor(LabPageEditor labPageEditor) throws GlobalException {
		super.save(labPageEditor);
		return labPageEditor;
	}

	/**
	 * 修改页面对象信息
	 * 
	 * @param labPageEditor
	 * @return boolean
	 */
	@Override
	public boolean deleteLabPageEditor(String[] ids) throws GlobalException {
		try {
			super.deleteAll(super.findByIds(LabPageEditor.class, ids));
		} catch (Exception e) {
			log.error("删除页面对象出错");
			throw new GlobalException("" + e.getMessage());
		}
		return false;
	}

	/**
	 * 删除功能对象信息
	 * 
	 * @param ids
	 * @return LabPageEditor
	 */
	@Override
	public LabPageEditor getLabPageEditor(String id) throws GlobalException {
		LabPageEditor labPageEditor = (LabPageEditor) super.findById(LabPageEditor.class, id);
		return labPageEditor;
	}

	/**
	 * 根据Id查询页面对象信息
	 * 
	 * @param id
	 * @return List<LabPageEditor>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabPageEditor> getLabPageEditorList(String hql) throws GlobalException {
		List<LabPageEditor> list = super.find(hql);
		return list;
	}

	/**
	 * 根据hql语句获取页面对象分页列表信息
	 * 
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 */
	@Override
	public PageResult getLabPageEditorPR(String hql, PageResult pageResult) throws GlobalException {
		pageResult = super.getPageResult(hql, pageResult);
		return pageResult;
	}

	/**
	 * 根据hql语句获取页面列表
	 * 
	 * @param hql
	 * @return LabPageEditor
	 */
	@Override
	public LabPageEditor updateLabPageEditor(LabPageEditor labPageEditor) throws GlobalException {
		super.update(labPageEditor);
		return labPageEditor;
	}
}
