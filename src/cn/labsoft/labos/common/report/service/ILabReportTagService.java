package cn.labsoft.labos.common.report.service;

import java.util.List;
import cn.labsoft.labos.common.report.vo.LabReportTagVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 报告模版标签逻辑处理层
 * @author quinn
 * 
 */
public interface ILabReportTagService {
	/**
	 * 增加标签
	 * @param labReportTagVo  模版标签对象
	 * @return 返回类型 
	 * @throws GlobalException  
	 */
	public LabReportTagVo addLabReportTag(LabReportTagVo labReportTagVo) throws GlobalException;
	/**
	 * 初始化标签
	 * @param reportId 模版id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean addLabReportTag4Init(String reportId) throws GlobalException;;
	/**
	 * 删除标签
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabReportTag (String[] ids) throws GlobalException;
	/**
	 * 假删除标签
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabReportTag4Del(String[] ids) throws GlobalException;
	/**
	 * 修改标签
	 * @param labReportTagVo  模版标签对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabReportTag(LabReportTagVo labReportTagVo) throws GlobalException;
	/**
	 * 查询标签
	 * @param id 对象id
	 * @return 标签对象
	 * @throws GlobalException
	 */
	public LabReportTagVo getLabReportTag(String id) throws GlobalException;
	/**
	 * 查询模版标签顺序号
	 * @param LabReportId 模版id
	 * @return 模版标签顺序号
	 * @throws GlobalException
	 */
	public Integer getLabReportTagIndex(String LabReportId) throws GlobalException;
	/**
	 * 
	 * @param labReportTagVo  模版标签对象
	 * @return
	 * @throws GlobalException
	 */
	public List<LabReportTagVo> getLabReportTagList(LabReportTagVo labReportTagVo) throws GlobalException;
	/**
	 * 根据报告id获取其定义的所有标签（若有子标签会循环显示）
	 * @param eportId
	 * @return 返回类型 
	 * @throws GlobalException 
	 */
	public List<LabReportTagVo> getLabReportTagListByReportId(String reportId) throws GlobalException;
	/**
	 * 根据报告id获取其定义的所有标签（若有子标签会循环显示）
	 * @param eportId
	 * @return 返回类型 
	 * @throws GlobalException 
	 */
	public String getLabReportTag4JsonByReportId(String reportId,String tagType) throws GlobalException;
	/**
	 * 获取标签分页信息
	 * @param labReportTagVo 模版标签对象
	 * @param pageResult
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public PageResult getLabReportTagPR(LabReportTagVo labReportTagVo,PageResult pageResult) throws GlobalException;
	
}
