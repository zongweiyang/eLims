package cn.labsoft.labos.source.reference.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.entity.LabRefPurDetail;

public interface ILabRefPurDetailDAO extends IBaseDAO {
	/**
	 * 新增采购单
	 * <p>addLabRefPur  新增采购单<br>
	 * 需要传入LabRefPurMain labRefPurMain
	 * @param LabRefPurMain labRefPurMain
	 * @return LabRefPurMain
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurDetail addLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException;
	/**
	 * 修改采购单
	 * <p>updateLabRefPurDetail  修改采购单<br>
	 * 需要传入LabRefPurDetail labRefPurDetail
	 * @param LabRefPurDetail labRefPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException;
	/**
	 * 删除采购单
	 * <p>deleteLabRefPurDetail  删除采购单<br>
	 * 需要传入LabRefPurDetail labRefPurDetail
	 * @param LabRefPurDetail labRefPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException;
	/**
	 * 获取采购单集合通过sql
	 * <p>getLabRefPurDetailBySql  获取采购单集合通过sql<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabRefPurDetail>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefPurDetail> getLabRefPurDetailBySql(String hql)
			throws GlobalException;

}
