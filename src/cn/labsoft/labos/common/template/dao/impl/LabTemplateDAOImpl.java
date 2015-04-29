package cn.labsoft.labos.common.template.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.template.dao.ILabTemplateDAO;
import cn.labsoft.labos.common.template.entity.LabTemplate;
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
@Repository(value = "labTemplateDAO")
public class LabTemplateDAOImpl extends BaseDAO implements ILabTemplateDAO {
	/**
	 * 增加模板对象信息
	 * 
	 * @param labTemplate
	 * @return LabTemplate
	 */
	@Override
	public LabTemplate addLabTemplate(LabTemplate labTemplate) throws GlobalException {
		super.save(labTemplate);
		return labTemplate;
	}

	/**
	 * 删除模板对象信息
	 * 
	 * @param ids
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabTemplate(String ids[]) throws GlobalException {
		List<LabTemplate> list = super.findByIds(LabTemplate.class, ids);
		try {
			super.deleteAll(list);
			return true;
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
	}

	/**
	 * 根据Id查询模板对象信息
	 * 
	 * @param id
	 * @return LabTemplate
	 */
	@Override
	public LabTemplate getLabTemplate(String id) throws GlobalException {
		LabTemplate sysFunction = (LabTemplate) super.findById(LabTemplate.class, id);
		return sysFunction;
	}

	/**
	 * 根据hql语句获取模板列表
	 * 
	 * @param hql
	 * @return List<LabTemplate>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabTemplate> getLabTemplateList(String hql) throws GlobalException {
		List<LabTemplate> list = super.find(hql);
		return list;
	}

	/**
	 * 根据hql语句获取模板对象分页列表信息
	 * 
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 */
	@Override
	public PageResult getLabTemplatePR(String hql, PageResult pageResult) throws GlobalException {
		return super.getPageResult(hql, pageResult);
	}

	/**
	 * 修改模板对象信息
	 * 
	 * @param labTemplate
	 * @return LabTemplate
	 */
	@Override
	public LabTemplate updateLabTemplate(LabTemplate labTemplate) throws GlobalException {
		super.update(labTemplate);
		return labTemplate;
	}
}
