package cn.labsoft.labos.source.appara.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;

public interface ILabApparaService {
	/**
	 *  新增 仪器信息
	 * <p>addLabAppara 新增 仪器信息<br>
	 * 需要传入LabApparaVo vo
	 * @param LabApparaVo 传入LabApparaVo vo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void addLabAppara(LabApparaVo vo)throws GlobalException;
	/**
	 *  修改 仪器信息
	 * <p>updateLabAppara 修改 仪器信息<br>
	 * 需要传入LabApparaVo vo
	 * @param LabApparaVo 传入LabApparaVo vo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabAppara(LabApparaVo vo)throws GlobalException;
	/**
	 *  获取 仪器信息通过ID
	 * <p>getLabAppara 获取 仪器信息通过ID<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabApparaVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaVo getLabAppara(String id)throws GlobalException;
	/**
	 *  获取 仪器信息分页信息
	 * <p>getLabApparaList 获取 仪器信息分页信息<br>
	 * 需要传入LabApparaVo vo,PageResult pageResult
	 * @param LabApparaVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaList(LabApparaVo vo,PageResult pageResult)throws GlobalException;
	/**
	 * 获取 仪器信息List集合
	 * <p>getLabApparaList 获取 仪器信息List集合<br>
	 * 需要传入LabApparaVo vo
	 * @param LabApparaVo vo
	 * @return List<LabApparaVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabApparaVo> getLabApparaList(LabApparaVo vo)throws GlobalException;
	/**
	 * 删除 仪器信息通过ID
	 * <p>deleteLabAppara 删除 仪器信息通过ID<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabAppara(String id)throws GlobalException;
	/**
	 * 获取仪器下所上传的附件
	 * <p>getLabAppFile 获取仪器下所上传的附件<br>
	 * 需要传入LabApparaVo vo,String type
	 * @param LabApparaVo vo,String type
	 * @return List<LabUploadVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabUploadVo> getLabAppFile(LabApparaVo vo,String type)throws GlobalException;
	/**
	 * 删除仪器所上传的附件
	 * <p>delLabAppFile 删除仪器所上传的附件<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean delLabAppFile(String id)throws GlobalException;
	/**
	 * 根据类型id 获取 仪器
	 * <p>getApparaListByTypeId 根据类型id 获取 仪器<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabApparaVo> getApparaListByTypeId(String id) throws GlobalException;
	
	/**
	 * 根据name 获取 仪器
	 * <p>getLabApparaByName 根据name 获取 仪器<br>
	 * 需要传入String name
	 * @param String name
	 * @return List<LabApparaVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabApparaVo> getLabApparaByName(String name)throws GlobalException;
	/**
	 * 获取 仪器 模板地址
	 * @return String
	 * */
	public String getLabApparaExcel();
	/**
	 * 导入Excel操作组
	 * <p>uploadFile 导入Excel操作组<br>
	 * 需要传入String uploadFileName , File upload
	 * @param String uploadFileName , File upload
	 * @return String[][]
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public boolean importLabAppExcel(String uploadFileName , File upload ,LabApparaVo labApparaVo) throws GlobalException;
	public String[][] uploadFile(String uploadFileName , File upload)throws GlobalException;
	/**
	 * 导出Excel
	 * <p>export2Excel 导出Excel<br>
	 * 需要传入LabApparaVo labApparaVo
	 * @param LabApparaVo labApparaVo
	 * @return InputStream
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public InputStream export2Excel(LabApparaVo labApparaVo)throws GlobalException;
	/**
	 * 获取Volist放入vo对象中
	 * <p>getLabApparaVo 获取Volist放入vo对象中<br>
	 * 需要传入LabApparaVo labApparaVo
	 * @param LabApparaVo labApparaVo
	 * @return LabApparaVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaVo getLabApparaVo(LabApparaVo labApparaVo)throws GlobalException;
	
	/**
	 * 根据仪器类型删除它下面的所有仪器
	 * <p>deleteLabApparaByAppType 根据仪器类型删除它下面的所有仪器<br>
	 * 需要传入String typeId
	 * @param String typeId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabApparaByAppType(String typeId)throws GlobalException;
}
