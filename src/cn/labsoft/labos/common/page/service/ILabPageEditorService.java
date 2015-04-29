package cn.labsoft.labos.common.page.service;

import cn.labsoft.labos.common.page.vo.LabPageEditorVo;
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
public interface ILabPageEditorService{
	/**
	 * 分页查询WebRoot路径下的文件
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @param pageResult
	 *            分页信息
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabPageEditorPR(LabPageEditorVo labPageEditorVo, PageResult pageResult) throws GlobalException;

	/**
	 * 查询页面信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo getLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 修改页面信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo updateLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 新增页面信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo addLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 修改页面内容
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo updateLabPageEdit(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 删除页面对象信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo updateLabPage4Del(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 公式编辑器接口方法：修改页面内容(插入一段JS)
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo updateLabPage4Formula(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 查询JSP页面分页
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public PageResult getFilePagePR(LabPageEditorVo labPageEditorVo, PageResult pageResult) throws GlobalException;

	/**
	 * 批量新增页面信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	public LabPageEditorVo addBatchLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException;

	/**
	 * 获得VO对象注解的信息
	 * 
	 * @param labPageEditorVo
	 *            封装了页面信息的视图对象
	 * @return StringBuffer
	 * @throws GlobalException
	 */
	public StringBuffer getAnnotationInfo(LabPageEditorVo labPageEditorVo) throws GlobalException;
}