package cn.labsoft.labos.source.reference.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reference.entity.LabRefPurMain;

public interface ILabRefPurMainDAO extends IBaseDAO {
	/**
	 * 新增采购单
	 * <p>addLabRefPur  新增采购单<br>
	 * 需要传入LabRefPurMain labRefPurMain
	 * @param LabRefPurMain labRefPurMain
	 * @return LabRefPurMain
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMain addLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException;
	/**
	 * 修改采购单
	 * <p>updateLabRefPur  修改采购单<br>
	 * 需要传入LabRefPurMain labRefPurMain
	 * @param LabRefPurMain labRefPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException;
	/**
	 * 删除采购单
	 * <p>deleteLabRefPur  删除采购单<br>
	 * 需要传入LabRefPurMain labRefPurMain
	 * @param LabRefPurMain labRefPurMain
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException;
	/**
	 *  通过sql语句获取采购单
	 * <p>getLabRefPurBySql  通过sql语句获取采购单<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabRefPurMain>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefPurMain> getLabRefPurBySql(String hql)
			throws GlobalException;
	/**
	 *  获取采购单分页信息
	 * <p>getLabRefPurPageResult  获取采购单分页信息<br>
	 * 需要传入String hql, PageResult pageResult
	 * @param String hql, PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabRefPurPageResult(String hql, PageResult pageResult)
			throws GlobalException;

}
