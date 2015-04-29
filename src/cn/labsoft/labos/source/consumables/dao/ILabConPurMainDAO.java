package cn.labsoft.labos.source.consumables.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.consumables.entity.LabConPurMain;

public interface ILabConPurMainDAO extends IBaseDAO {
	/**
	 * 新增耗材入库
	 * <p>addLabConPur 新增耗材入库<br>
	 * 需要传入LabConPurMain labConPurMain
	 * @param LabConPurMain labConPurMain
	 * @return LabConPurMain
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurMain addLabConPur(LabConPurMain labConPurMain)
			throws GlobalException;
	/**
	 * 修改耗材入库
	 * <p>updateLabConPur 修改耗材入库<br>
	 * 需要传入LabConPurMain labConPurMain
	 * @param LabConPurMain labConPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConPur(LabConPurMain labConPurMain)
			throws GlobalException;
	/**
	 * 删除耗材入库
	 * <p>deleteLabConPur 删除耗材入库<br>
	 * 需要传入LabConPurMain labConPurMain
	 * @param LabConPurMain labConPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConPur(LabConPurMain labConPurMain)
			throws GlobalException;
	/**
	 * 通过hql 获取耗材入库
	 * <p>getLabConPurBySql 通过hql 获取耗材入库<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabConPurMain>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConPurMain> getLabConPurBySql(String hql)
			throws GlobalException;
	/**
	 * 通过hql 获取耗材入库分页信息
	 * <p>getLabConPurPageResult 通过hql 获取耗材入库分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConPurPageResult(String hql, PageResult pageResult)
			throws GlobalException;

}
