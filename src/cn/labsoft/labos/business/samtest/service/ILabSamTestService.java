package cn.labsoft.labos.business.samtest.service;

import java.util.List;

import jxl.write.WriteException;

import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;

/**
 * stone
 * @author Administrator
 * 检测管理---数据管理（数据录入 数据校验，数据汇总）
 */
public interface ILabSamTestService {
	/**
	 * 数据录入 LIST页面 
	 * @param labSamTestBeatchVo
	 * @param pageResult
	 * @return
	 */
	public PageResult getLabSamTestPR(LabSamTestBeatchVo labSamTestBeatchVo,PageResult pageResult)throws GlobalException;
	/**
	 * 根据批次ID  获取次检测人的检测样品
	 * @param labSamTestBeatchVo
	 * @return
	 */
	public List<LabSamTestVo> getLabSamTestBeatchVo(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	/**
	 * 对批次中的检测项目 检测标准 维护
	 * @param labSamTestBeatchVo
	 * @param listLabSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTest4Standard(LabSamTestBeatchVo labSamTestBeatchVo,List<LabSamTestVo> listLabSamTestVo)throws GlobalException;
	/**
	 * 数据录入 样品信息  pre
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTestVo4Date(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	/**
	 * 数据录入  样品信息录入
	 * @param listLabSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTest4Date(LabSamTestBeatchVo labSamTestBeatchVo,List<LabSamTestVo> listLabSamTestVo)throws GlobalException;
	/**
	 * 数据录入 试剂信息先删后加
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException 
	 */
	public boolean updateLabSamTest4Reagent(LabSamTestBeatchVo labSamTestBeatchVo,LabReaOutMainVo labReaOutMainVo) throws GlobalException;
	/**
	 * 模板
	 * @param listLabSamTestVo
	 * @return
	 * @throws WriteException
	 */
	public String writeExcel(List<LabSamTestVo> listLabSamTestVo) throws WriteException;
	/**
	 * 
	 * @param labSamTestBeatchVo
	 * @param fileName
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTest4ImportDate(LabSamTestBeatchVo labSamTestBeatchVo,String fileName)throws GlobalException;
	/**
	 * 模板导入 验证
	 * @param labSamTestBeatchVo
	 * @param fileName
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTest4Validate(LabSamTestBeatchVo labSamTestBeatchVo,String fileName)throws GlobalException;
	/**
	 * 数据录入  所使用仪器
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTestVo4Appara(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	/**
	 * 数据录入  仪器修改。
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTestVo4Appara(LabSamTestBeatchVo labSamTestBeatchVo,List<LabApparaUseVo> listLabApparaUseVo)throws GlobalException;
	
	/**
	 * 数据录入  标准品修改
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException 
	 */
	public boolean updateLabSamTestVo4Reference(LabSamTestBeatchVo labSamTestBeatchVo,LabRefOutMainVo labRefOutMainVo) throws GlobalException;
	/**
	 * 数据检验 LIST
	 * @param labSamTestBeatchVo
	 * @param pageResult
	 * @return
	 * @throws GlobalException
	 */
	public PageResult getLabSamTest4CheckPR(LabSamTestBeatchVo labSamTestBeatchVo,PageResult pageResult)throws GlobalException;
	/**
	 * 数据校验 PREUPDATE
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTestVo4Check(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	/**
	 * 数据校验 UPDATE
	 * @param listLabSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTestVo4Check(LabSamTestVo labSamTestVo)throws GlobalException;
	/**
	 * 数据汇总 LIST
	 * @param labSamTestVo
	 * @param pageResult
	 * @return
	 * @throws GlobalException
	 */
	public PageResult getLabSamTest4SummaryPR(LabSamTestVo labSamTestVo,PageResult pageResult)throws GlobalException;
	/**
	 * 数据汇总 PREUPDATE
	 * @param labSamTestVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabSamTestVo> getLabSamTest4Summary(LabSamTestVo labSamTestVo)throws GlobalException;
	/**
	 * 数据汇总 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTest4Summary(LabSamTestVo labSamTestVo,List<LabSamTestVo> listLabSamTestVo)throws GlobalException;
	
	/**
	 * 获取批次类信息
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public LabSamTestBeatchVo getLabSamTestBeatchById(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	
	/**
	 * 修改批次信息
	 * @param labSamTestBeatchVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTestBeatch(LabSamTestBeatchVo labSamTestBeatchVo)throws GlobalException;
	
}
