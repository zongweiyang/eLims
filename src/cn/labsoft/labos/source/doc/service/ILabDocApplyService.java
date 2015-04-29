package cn.labsoft.labos.source.doc.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.doc.vo.LabDocApplyVo;
import cn.labsoft.labos.source.doc.vo.LabDocVo;

public interface ILabDocApplyService {
	/**
	 * 获取文档申请列表
	 * <p>getLabDocApplyList 获取文档申请列表<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabDocApplyVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabDocApplyVo> getLabDocApplyList() throws GlobalException;
	/**
	 * 新增文档申请
	 * <p>addLabDocApply 新增文档申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void addLabDocApply(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 软删除文档申请
	 * <p>cancelLabDocApply 软删除文档申请<br>
	 * 需要传入LabDocApplyVo labDocApplyVo
	 * @param LabDocApplyVo labDocApplyVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void cancelLabDocApply(LabDocApplyVo labDocApplyVo)
			throws GlobalException;
	/**
	 * 修改文档申请
	 * <p>updateLabDocApply 软删除文档申请<br>
	 * 需要传入LabDocApplyVo labDocApplyVo
	 * @param LabDocApplyVo labDocApplyVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabDocApply(LabDocApplyVo labDocApplyVo)
			throws GlobalException;
	/**
	 * 批量增加文档申请
	 * <p>addLabDocApplyBatch 批量增加文档申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void addLabDocApplyBatch(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 修改文档申请
	 * <p>updateLabDocApplyBatch 修改文档申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabDocApplyBatch(LabDocVo labDocVo)
			throws GlobalException;
	/**
	 * 根据文档ID修改申请
	 * <p>updateLabDocApplyByLabDocId 根据文档ID修改申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabDocApplyByLabDocId(LabDocVo labDocVo)
			throws GlobalException;
	/**
	 * 根据ID获取文档申请
	 * <p>getLabDocApplyById 根据ID获取文档申请<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabDocApplyVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabDocApplyVo getLabDocApplyById(String id) throws GlobalException;
	/**
	 * 获取文档申请
	 * <p>getLabDocOterApplyList 获取文档申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return List<LabDocApplyVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabDocApplyVo> getLabDocOterApplyList(LabDocVo labDocVo) throws GlobalException;
	/**
	 * 修改文档申请
	 * <p>updateLabDocApplyById 修改文档申请<br>
	 * 需要传入LabDocVo labDocVo
	 * @param LabDocVo labDocVo
	 * @return List<LabDocApplyVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabDocApplyById(LabDocApplyVo labDocApplyVo)
			throws GlobalException;
	/**
	 * 获取文档申请
	 * <p>updateLabDocApplyById 获取文档申请<br>
	 * 需要传入String id
	 * @param String id
	 * @return List<LabDocApplyVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabDocApplyVo getLabDocApplyByLabDocId(String id)
			throws GlobalException;
	/**
	 * 获取文档申请
	 * <p>getLabDocApplyVoById 获取文档申请<br>
	 * 需要传入String id
	 * @param String id
	 * @return List<LabDocApplyVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabDocApplyVo> getLabDocApplyVoById(String id)
	throws GlobalException;
	/**
	 * 修改文档申请
	 * <p>updateLabDocOtherApplyBatch 修改文档申请<br>
	 * 需要传入LabDocApplyVo labDocApplyVo
	 * @param LabDocApplyVo labDocApplyVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabDocOtherApplyBatch(LabDocApplyVo labDocApplyVo)
			throws GlobalException;

}
