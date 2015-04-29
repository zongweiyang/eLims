package cn.labsoft.labos.base.user.service;

import java.util.List;
import cn.labsoft.labos.base.user.vo.LabUserCredVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户证书服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabUserCredService {
	/**
	 * 增加用户证书
	 * @param labUserCredVo 封装了用户证书信息Vo
	 * @return 用户证书信息
	 * @throws GlobalException
	 */
	public LabUserCredVo addLabUserCred(LabUserCredVo labUserCredVo) throws GlobalException;;
	/**
	 * 删除用户证书信息
	 * @param ids 证书ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabUserCred (String[] ids) throws GlobalException;
	/**
	 * 假删除用户证书信息
	 * @param ids 证书ids
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUserCred4Del(String[] ids) throws GlobalException;
	/**
	 * 修改用户证书信息
	 * @param labUserCredVo 封装了用户证书信息Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUserCred(LabUserCredVo labUserCredVo) throws GlobalException;
	/**
	 * 查询用户证书信息
	 * @param id对象id
	 * @return 用户证书信息Vo
	 * @throws GlobalException
	 */
	public LabUserCredVo getLabUserCred(String id) throws GlobalException;
	/**
	 * 获取用户证书信息集合
	 * @param labUserCredVo 封装了用户证书信息Vo
	 * @return 用户证书信息对象集合
	 * @throws GlobalException
	 */
	public List<LabUserCredVo> getLabUserCredList(LabUserCredVo labUserCredVo) throws GlobalException;
	/**
	 * 获取用户证书分页信息对象
	 * @param labUserCredVo 封装了用户证书信息Vo
	 * @param pageResult
	 * @return 用户证书信息集合对象
	 * @throws GlobalException
	 */
	public PageResult getLabUserCredPR(LabUserCredVo labUserCredVo,PageResult pageResult) throws GlobalException;
	
     }
