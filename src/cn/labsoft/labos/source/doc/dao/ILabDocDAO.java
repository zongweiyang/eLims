package cn.labsoft.labos.source.doc.dao;

import java.util.List;

import cn.labsoft.labos.source.doc.entity.LabDoc;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 
 * <strong>Title : ILabDocDAO </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Sep 3, 2014 5:09:34 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public interface ILabDocDAO extends IBaseDAO {
	
	/**
	 * 新增文档
	 * <p>addLabDoc 新增文档<br>
	 * 需要传入LabDoc labDoc
	 * @param LabDoc labDoc
	 * @return LabDoc
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDoc addLabDoc(LabDoc labDoc) throws GlobalException;
	/**
	 * 新增文档
	 * <p>updateLabDoc 新增文档<br>
	 * 需要传入LabDoc labDoc
	 * @param LabDoc labDoc
	 * @return LabDoc
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDoc updateLabDoc(LabDoc labDoc) throws GlobalException;
	/**
	 * 删除文档
	 * <p>deleteLabDoc 删除文档<br>
	 * 需要传入LabDoc labDoc
	 * @param LabDoc labDoc
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabDoc(LabDoc labDoc) throws GlobalException;
	/**
	 * 获取文档
	 * <p>getLabDocById 获取文档<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabDoc
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDoc getLabDocById(String id) throws GlobalException;
	/**
	 * 获取文档
	 * <p>getLabDocList 获取文档<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabDoc>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDoc> getLabDocList(String hql) throws GlobalException;
	
	/**
	 * 获取文档分页信息
	 * <p>getLabDocPageResult 获取文档分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabDocPageResult(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 修改文档信息
	 * <p>getLabDocPageResult 获取文档分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public Boolean updateLabDocBoolean(LabDoc labDoc) throws GlobalException;
	/**
	 * 删除文档信息
	 * <p>deleteLabDocBoolean 删除文档信息<br>
	 * 需要传入LabDoc labDoc
	 * @param LabDoc labDoc
	 * @return Boolean 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public Boolean deleteLabDocBoolean(LabDoc labDoc) throws GlobalException;
	/**
	 * 新增文档信息
	 * <p>addLabDocBoolean 新增文档信息<br>
	 * 需要传入LabDoc labDoc
	 * @param LabDoc labDoc
	 * @return Boolean 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public Boolean addLabDocBoolean(LabDoc labDoc) throws GlobalException;
	
}
