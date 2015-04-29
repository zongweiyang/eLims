package cn.labsoft.labos.source.reagent.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reagent.entity.LabReaPurMain;

public interface ILabReaPurMainDAO extends IBaseDAO {
	/**
	 * 新增试剂盘点信息
	 * <p>addLabReaPur  新增试剂盘点信息<br>
	 * 需要传入LabReaPurMain labReaPurMain
	 * @param LabReaPurMain labReaPurMain
	 * @return LabReaPurMain
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabReaPurMain addLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException;
	/**
	 * 修改试剂盘点信息
	 * <p>updateLabReaPur  修改试剂盘点信息<br>
	 * 需要传入LabReaPurMain labReaPurMain
	 * @param LabReaPurMain labReaPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean updateLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException;
	/**
	 * 删除试剂盘点信息
	 * <p>deleteLabReaPur  删除试剂盘点信息<br>
	 * 需要传入LabReaPurMain labReaPurMain
	 * @param LabReaPurMain labReaPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabReaPur(LabReaPurMain labReaPurMain)
			throws GlobalException;
	/**
	 * 通过sql语句 返回查询集合
	 * <p>getLabReaPurBySql  通过sql语句 返回查询集合<br>
	 * 需要传入String sql
	 * @param String sql
	 * @return List<LabReaPurMain>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabReaPurMain> getLabReaPurBySql(String hql)
			throws GlobalException;
	/**
	 * 查询盘点分页信息
	 * <p>getLabReaPurPageResult  查询盘点分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabReaPurPageResult(String hql, PageResult pageResult)
			throws GlobalException;

}
