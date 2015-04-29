package cn.labsoft.labos.source.reagent.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.entity.LabReaPurDetail;

public interface ILabReaPurDetailDAO extends IBaseDAO {
	/**
	 * 增加采购信息
	 * <p>addLabReaPurDetail  增加采购信息<br>
	 * 需要传入LabReaPurDetail labReaPurDetail
	 * @param LabReaPurDetail labReaPurDetail
	 * @return LabReaPurDetail
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabReaPurDetail addLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException;
	/**
	 * 修改采购信息
	 * <p>updateLabReaPurDetail  修改采购信息<br>
	 * 需要传入LabReaPurDetail labReaPurDetail
	 * @param LabReaPurDetail labReaPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean updateLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException;
	/**
	 * 删除采购信息
	 * <p>deleteLabReaPurDetail  删除采购信息<br>
	 * 需要传入LabReaPurDetail labReaPurDetail
	 * @param LabReaPurDetail labReaPurDetail
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException;
	/**
	 * 通过sql语句获取采购信息
	 * <p>getLabReaPurDetailBySql  通过sql语句获取采购信息<br>
	 * 需要传入String hql
	 * @param String hql
	 * @return List<LabReaPurDetail>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabReaPurDetail> getLabReaPurDetailBySql(String hql)
			throws GlobalException;

}
