package cn.labsoft.labos.source.doc.service;

import java.util.List;
import cn.labsoft.labos.source.doc.vo.LabDocVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 文件管理Service
 * @author danlee Li
 *
 */
public interface ILabDocService {
	/**
	 * 获得文档信息分页
	 * <p>getLabDocPageResult 获得文档信息分页<br>
	 * 需要传入LabDocVo labDocVo,PageResult pageResult
	 * @param LabDocVo labDocVo,PageResult pageResult
	 * @return PageResult 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabDocPageResult(LabDocVo labDocVo,PageResult pageResult)
			throws GlobalException;
	/**
	 * 获得文档信息
	 * <p>getLabDocListBySql 获得文档信息<br>
	 * 需要传入String id, String pid
	 * @param String id, String pid
	 * @return List<LabDocVo> 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDocVo> getLabDocListBySql(String id, String pid)throws GlobalException;
	/**
	 * 获得文档信息
	 * <p>getLabDocListByPid 获得文档信息<br>
	 * 需要传入String pid,String flag
	 * @param String pid,String flag
	 * @return List<LabDocVo> 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDocVo> getLabDocListByPid(String pid,String flag) throws GlobalException;
	/**
	 * 获得文档信息
	 * <p>getLabDocById 获得文档信息<br>
	 * 需要传入String Id
	 * @param String Id
	 * @return LabDocVo 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocVo getLabDocById(String Id) throws GlobalException;
	/**
	 * 新增文档信息
	 * <p>addLabDoc 新增文档信息<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return LabDocVo 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocVo addLabDoc(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 删除文档信息
	 * <p>deleteLabDoc 删除文档信息<br>
	 * 需要传入String id
	 * @param String id
	 * @return null 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabDoc(String id) throws GlobalException;
	/**
	 * 批量删除文档信息
	 * <p>deleteLabDoceBatch 批量删除文档信息<br>
	 * 需要传入String ids[]
	 * @param String ids[]
	 * @return null 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabDoceBatch(String ids[]) throws GlobalException;
	/**
	 * 批量删除文档信息
	 * <p>deleteLabDoceBatch 批量删除文档信息<br>
	 * 需要传入String ids[]
	 * @param String ids[]
	 * @return null 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public Boolean updateLabDoc(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 获取文档信息通过名称
	 * <p>getLabDocByName 获取文档信息通过名称<br>
	 * 需要传入String fileName
	 * @param String fileName
	 * @return List<LabDocVo> 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDocVo> getLabDocByName(String fileName) throws GlobalException;
	/**
	 * 根据ids删除文件
	 * @param ids 数组类型文件ID
	 * @throws GlobalException
	 */
	public void deleteLabDocByIds(String ids)throws GlobalException;
	/**
	 * 新增文件夹
	 * <p>addLabDocForFolder 新增文件夹<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return LabDocVo 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocVo addLabDocForFolder(LabDocVo labDocVo)throws GlobalException;
	/**
	 * 修改文件夹
	 * <p>updateLabDocForFolder 修改文件夹<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return LabDocVo 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabDocVo updateLabDocForFolder(LabDocVo labDocVo)throws GlobalException;
	/**
	 * 获取文件夹
	 * <p>getLabDocAllOtherList 获取文件夹<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return List<LabDocVo> 
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabDocVo> getLabDocAllOtherList(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 高级检索查询
	 * <p>getLabDocSolrPR 高级检索查询<br>
	 * 需要传入LabDocVo labDocVo,PageResult pageResult
	 * @param LabDocVo labDocVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabDocSolrPR(LabDocVo labDocVo,PageResult pageResult) throws GlobalException;

}
