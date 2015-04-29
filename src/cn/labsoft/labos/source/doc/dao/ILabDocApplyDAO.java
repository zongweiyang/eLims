package cn.labsoft.labos.source.doc.dao;

import java.util.List;

import cn.labsoft.labos.source.doc.entity.LabDocApply;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
	/**
	 * 
	 * <strong>Title : ILabDocApplyDAO </strong>. <br>
	 * <strong>Description : TODO�</strong> <br>
	 * <strong>Create on : Sep 3, 2014 4:59:17 PM  </strong>. <br>
	 * <p>
	 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
	 * </p>
	 *
	 * @author danlee Li <br>
	 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
	 *          <br>
	 *          <strong>修改历史: .</strong> <br>
	 *          修改人 修改日期 修改描述<br>
	 *          -------------------------------------------<br>
	 *          <br>
	 *          <br>
	 */
public interface ILabDocApplyDAO extends IBaseDAO {
	/**
	 * 新增文档申请
	 * <p>addLabDocApply 新增文档申请<br>
	 * 需要传入LabDocApply labDocApply
	 * @param LabDocApply labDocApply
	 * @return LabDocApply
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocApply addLabDocApply(LabDocApply labDocApply)
			throws GlobalException;
	/**
	 * 修改文档申请
	 * <p>updateLabDocApply 修改文档申请<br>
	 * 需要传入LabDocApply labDocApply
	 * @param LabDocApply labDocApply
	 * @return LabDocApply
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocApply updateLabDocApply(LabDocApply labDocApply)
			throws GlobalException;
	/**
	 * 删除文档申请
	 * <p>deleteLabDocApply 删除文档申请<br>
	 * 需要传入LabDocApply labDocApply
	 * @param LabDocApply labDocApply
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabDocApply(LabDocApply labDocApply)
			throws GlobalException;
	/**
	 * 获取文档申请
	 * <p>getLabDocApplyById 获取文档申请<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabDocApply
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocApply getLabDocApplyById(String id) throws GlobalException;
	/**
	 * 获取文档申请
	 * <p>getLabDocApplyListByHql 获取文档申请<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabDocApply>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDocApply> getLabDocApplyListByHql(String hql)
			throws GlobalException;
	/**
	 * 获取文档申请分页信息
	 * <p>getLabDocApplyPageResult 获取文档申请分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabDocApplyPageResult(String hql, PageResult pageResult) throws GlobalException;

}
