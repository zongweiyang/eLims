package cn.labsoft.labos.framework.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : BaseDAO </strong>. <br>
 * <strong>Description : 增加 删除 修改 查询 分页信息 接口类 <strong>Create on : Nov 13, 2009
 * 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 * @param <T>
 */

@SuppressWarnings("unchecked")
public interface ITBaseDAO<T> extends IBaseDAO{
	public void save(T object) ;
	public void delete(T object) ;
	public void delete(Serializable id) ;
	public void delete(String hsql) throws GlobalException ;
	public void deleteAll(Collection coll);
	public void deleteBySession(T object) throws GlobalException ;
	public void update(T object) ;
	public void updateAUTO(T object) throws GlobalException ;
	public void saveAUTO(T object) throws GlobalException ;
	
	public List<T> list(T po) throws GlobalException;
	public List<T> find(String[] ids);
	public List<T> finds(String ids);
	public T find(Serializable id);
}
