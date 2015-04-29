package cn.labsoft.labos.framework.common.service;

import java.io.Serializable;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 
 * <strong>Title : IBaseService </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Nov 6, 2014 1:37:52 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("unchecked")
public interface IBaseService <T> {
	/**
	 * 新增
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean add(T vo)throws GlobalException;
	/**
	 * 新增
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean save(T vo)throws GlobalException;
	/**
	 * 修改
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean update(T vo)throws GlobalException;
	/**
	 * 假删除
	 * @Title:  
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean update2del(Serializable id)throws GlobalException;
	/**
	 * 真删
	 * @Title:  
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean delete(Serializable id)throws GlobalException;
	/**
	 * 是否存在
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public boolean exist(T vo)throws GlobalException;
	/**
	 * 获得分页列表
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @param pageResult
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public PageResult pageResult(T vo,PageResult pageResult) throws GlobalException;
	/**
	 * 获得一组数据
	 * @Title:  
	 * @Description: TODO
	 * @param @param vo
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public List<T> list(T vo) throws GlobalException;
	/**
	 * 获得一组数据
	 * @Title:  
	 * @Description: TODO
	 * @param @param ids 数组
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public List<T> find(Serializable[] ids);
	/**
	 * 获得一组数据
	 * @Title:  
	 * @Description: TODO
	 * @param @param ids "id1,id2,id3"
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public List<T> finds(Serializable ids);
	/**
	 * 获得对象
	 * @Title:  
	 * @Description: TODO
	 * @param @param id
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public T find(Serializable id);
	
}
