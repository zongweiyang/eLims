package cn.labsoft.labos.common.template.service;

import cn.labsoft.labos.common.template.vo.LabTemplateVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;

/**
 * 页面管理Service接口
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public interface ILabTemplateService{
	/**
	 * @Title: 获取模板内容分页方法
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @param pageResult
	 *            分页对象
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabTemplatePR(PageResult pageResult, LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 获取模板
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo getLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 增加模板
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo addLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 修改模板
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo updateLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 修改OFFICE模板内容
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo updateLabTemplate4Edit(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 删除模板
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabTemplate4Del(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 获取模板文件信息
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo getLabTemplate4File(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 根据业务ID获取模板信息
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	public LabTemplateVo getLabTemplateByBusId(LabTemplateVo labTemplateVo) throws GlobalException;

	/**
	 * @Title: 根据功能id获取功能下的模版路径
	 * @param labTemplateVo
	 *            封装了模板信息的视图对象
	 * @return String
	 * @throws GlobalException
	 */
	public String getLabTemplateByBusId(String busId) throws GlobalException;
}