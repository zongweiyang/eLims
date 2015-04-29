package cn.labsoft.labos.source.consumables.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.entity.LabConPurDetail;

public interface ILabConPurDetailDAO extends IBaseDAO {
	/**
	 * 新增耗材入库
	 * <p>addLabConPurDetail 新增耗材入库<br>
	 * 需要传入LabConPurDetail labConPurDetail
	 * @param LabConPurDetail labConPurDetail
	 * @return LabConPurDetail
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurDetail addLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException;
	/**
	 * 修改耗材入库
	 * <p>updateLabConPurDetail 修改耗材入库<br>
	 * 需要传入LabConPurDetail labConPurDetail
	 * @param LabConPurDetail labConPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException;
	/**
	 * 删除耗材入库
	 * <p>deleteLabConPurDetail 删除耗材入库<br>
	 * 需要传入LabConPurDetail labConPurDetail
	 * @param LabConPurDetail labConPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException;
	/**
	 * 通过hql 获取耗材入库
	 * <p>getLabConPurDetailBySql 通过hql 获取耗材入库<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabConPurDetail>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConPurDetail> getLabConPurDetailBySql(String hql)
			throws GlobalException;

}
