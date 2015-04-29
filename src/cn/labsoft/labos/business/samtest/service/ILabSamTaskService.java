package cn.labsoft.labos.business.samtest.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * stone
 * @author Administrator
 * 检测管理---任务管理（任务下达 任务分配）
 */
public interface ILabSamTaskService {
	@SuppressWarnings("unchecked")
	public PageResult getLabSamTeskIssuedPR(LabSamTestVo labSamTestVo,PageResult pageResult)throws GlobalException;
	/**
	 * 根据任务号，获取项目以及所检测项目数量
	 * @param busId
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTeskVo4Issued(String busId) throws GlobalException;
	/**
	 * 根据客户选择的科室，进行项目的下达
	 * @param listLabSamTestVo
	 * @param labSampRegisterVo   ID任务号
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTeskVo4Issued(List<LabSamTestVo> listLabSamTestVo) throws GlobalException;
	

	/**
	 * 根据d多任务号，分配此次样品中的项目
	 * @param busIds
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTeskVoBatch4Issued(String[] busIds) throws GlobalException;
	/**
	 * 批量修改
	 * @param listLabSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTeskVo4IssuedBeach(List<LabSamTestVo> listLabSamTestVo)throws GlobalException;
	/**
	 * 任务分配 LIST
	 * @param labSamTestVo
	 * @param pageResult
	 * @return
	 */
	public PageResult getLabSamTesk4AllotPR(LabSamTestVo labSamTestVo,PageResult pageResult)throws GlobalException;
	
	/**
	 * 任务分配
	 * @param busId
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTeskVo4Allot(String busId)throws GlobalException;
	/**
	 * 显示分配信息
	 * @param labSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTeskVoAllotShow(LabSamTestVo labSamTestVo)throws GlobalException,UnsupportedEncodingException;
	/**
	 * 任务分配  UPDATE 提交数据处理
	 * @param listLabSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTeskVo4Allot(LabSamTestBeatchVo labSamTestBeatchVo,List<LabSamTestVo> listLabSamTestVo) throws GlobalException;
	/**
	 * 任务分配 PREUPDATE
	 * @param busIds
	 * @return
	 * @throws GlobalException
	 */
	
	public List<LabSamTestVo> getLabSamTeskVo4AllotBeatch(String[] busIds)throws GlobalException;
    }
