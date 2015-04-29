package cn.labsoft.labos.business.sam.service;

import java.util.List;

import cn.labsoft.labos.business.sam.entity.LabSamMain;
import cn.labsoft.labos.business.sam.vo.LabSamMainVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 样品管理Service
 * @author danlee Li
 *
 */
public interface ILabSamService {

	/**
	 * 得到样品列表
	 * @param labSamVo
	 *        封装的样品对象的信息   
	 * @param  pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabSamPR(LabSamVo labSamVo, PageResult pageResult)throws GlobalException;
	/**
	 * 得到样品列表
	 * @param labSamVo
	 *        封装的样品对象的信息 
	 * @param pageResult   
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabSamMainPR(LabSamMainVo labSamMainVo, PageResult pageResult)throws GlobalException;
	/**
	 * 得到一个样品对象
	 * @param labSamVo
	 *          封装了样品对象的信息
	 * @return List<LabSamVo>
	 * @throws GlobalException
	 */
	public List<LabSamVo> getLabSamList(LabSamVo labSamVo)throws GlobalException;
	/**
	 * 根据ids查找样品对象
	 * @param labSamVo
	 * @return List<LabSamVo>
	 * @throws GlobalException
	 */
	public List<LabSamVo> getLabSamListByIds(String ids)throws GlobalException;
	/**
	 * 根据ids查找样品对象
	 * @param labSamVo
	 * @return List<LabSamVo>
	 * @throws GlobalException
	 */
	public List<LabSamVo> getLabSamList(LabSamMainVo labSamMainVo)throws GlobalException;
	/**
	 * 根据ID得到一个样品对象
	 * @param id
	 * @return LabSamVo
	 * @throws GlobalException
	 */
	public LabSamVo getLabSamById(String id)throws GlobalException;

	/**
	 * 给一个类型下添加一个样品
	 * @param labSamVo
	 *           封装了样品对象的信息 
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean addLabSam(LabSamVo labSamVo)throws GlobalException;
	
	/**
	 * 根据id软删除一个样品
	 * @param id
	 *          样品ID
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean deleteLabSam(String id)throws GlobalException ;
	
	/**
	 * 修改一个样品的信息
	 * @param  labSamVo
	 *            封装了样品对象的信息
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabSamMain(LabSamMainVo labSamMainVo)throws GlobalException;
	
	/**
	 * 添加一个样品信息
	 * @param labSamMainVo
	 * 						封装样品主表对象vo
	 * @return LabSamMain 
	 *  					封装样品主表对象
	 * @throws GlobalException
	 */
	public LabSamMain addLabSamMain(LabSamMainVo labSamMainVo)throws GlobalException;
	
	/**
	 *	根据ID得到一个样品主表信息
	 * @param id
	 * @return LabSamMainVo 封装样品主表对象
	 * @throws GlobalException
	 */
	
	public LabSamMainVo getLabSamMainById(String id)throws GlobalException;
	
	
	/**
	 * 接收样品信息
	 * @param  labSamVo
	 *            封装了样品对象的信息
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabSamMain4Destory(LabSamMainVo labSamMainVo)throws GlobalException;
	
	/**
	 * 得到样品分样列表
	 * @param labSamMainVo 封装样品分类主表
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabSamMainPR4Division(LabSamMainVo labSamMainVo, PageResult pageResult)throws GlobalException;
	
	/**
	 *对样品信息进行分样
	 * @param  labSamVo
	 *            封装了样品对象的信息
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabSamMain4Division(LabSamMainVo labSamMainVo)throws GlobalException;
	/**
	 * 得到留样分页列表
	 * @param labSamMainVo
	 * @param pageResult
	 * @throws GlobalException  
	 * @return PageResult
	 */
	public PageResult getLabSamMainPR4Out(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException;
	/**
	 * 根据ID得到留样信息
	 * @param id
	 * @throws GlobalException  
	 * @return LabSamMainVo 
	 */
	public LabSamMainVo getLabSamMain4Destory(String id) throws GlobalException;
	/**
	 * 接收样品
	 * @param labSamMainVo
	 * @throws GlobalException  
	 * @return boolean
	 */
	public boolean updateLabSamMain4Out(LabSamMainVo labSamMainVo) throws GlobalException;
	
	
	
	/**
	 * 得到余样列表
	 * @param labSamMainVo
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	public PageResult getLabSamMainPR4Destory(LabSamMainVo labSamMainVo, PageResult pageResult) throws GlobalException;
	
	/**
	 * 得到留样信息
	 * @param id
	 * @throws GlobalException  
	 * @return LabSamMainVo 
	 */
	public LabSamMainVo getLabSamMain4Out(String id) throws GlobalException;

	/**
	 * 获取样品信息
	 * @Description: 其他模块调用接口
	 * @param labSamVo
	 *          封装了样品对象的信息
	 * @return
	 */
	public LabSamMainVo getLabSam4Other(LabSamMainVo labSamMainVo)throws GlobalException;
	/**
	 * 保存或修改样品信息
	 * @Description: 其他模块调用接口
	 * @param labSamVo
	 *          封装了样品对象的信息
	 * @return
	 */
	public boolean saveOrUpdateLabSam4Other(LabSamMainVo labSamMainVo)throws GlobalException;
}
