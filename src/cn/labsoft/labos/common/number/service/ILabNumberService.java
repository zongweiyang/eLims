package cn.labsoft.labos.common.number.service;

import java.util.List;
import cn.labsoft.labos.common.number.vo.LabNumberVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabNumberService {
	@SuppressWarnings("unchecked")
	public LabNumberVo addLabNumber(LabNumberVo labNumberVo) throws GlobalException;;
	
	public boolean deleteLabNumber (String[] ids) throws GlobalException;
	
	public boolean updateLabNumber4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabNumber(LabNumberVo labNumberVo) throws GlobalException;
	
	public LabNumberVo getLabNumber(String id) throws GlobalException;
	
	public List<LabNumberVo> getLabNumberList(LabNumberVo labNumberVo) throws GlobalException;
	
	public PageResult getLabNumberPR(LabNumberVo labNumberVo,PageResult pageResult) throws GlobalException;
	/**
	 * 先虚后实 （先生成编号 页面保存后确定编号）
	 * 取编号的调此方法 
	 * @param type 编号类型  定义是选择的类型 如 收费编号（SFBH）
	 * @param names 编号中包含动态的字符 用，分开
	 * @return
	 * @throws GlobalException
	 */
	public String getLabNumberByNo(String type,String[] names,String status)throws GlobalException;
	
	/**
	 * 取N个编号
	 * @param count
	 * @param typeCode 编号类型  定义是选择的类型 如 收费编号（SFBH）
	 * @param name 编号中包含动态的字符 用，分开
	 * @return
	 * @throws GlobalException 
	 */
	public List<String> getLabNumberBatchNo(int count,String typeCode,String[] name,String status) throws GlobalException;
	/**
	 * 此方法取初始化编号 只供预览
	 * @param type
	 * @return
	 * @throws GlobalException 
	 */
	public String getLabNumberByNo(String type) throws GlobalException;
     }
