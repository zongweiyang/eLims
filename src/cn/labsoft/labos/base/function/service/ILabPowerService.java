package cn.labsoft.labos.base.function.service;

import cn.labsoft.labos.base.function.vo.LabPowerVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 系统功能用户权限Service接口
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public interface ILabPowerService {
	/**
	 * 
	 * @Title: 根据uri返回权限信息
	 * @Description: TODO
	 * @param uri
	 * @return LabPowerVo
	 * @throws GlobalException 
	 */
	public LabPowerVo getPowerInfo(String uri) ;

	/**
	 * 
	 * @Title: 根据uri 判断是否拥有权限
	 * @Description: TODO
	 * @param uri
	 * @return boolean
	 * @throws GlobalException 
	 */
	public boolean hasPower(String uri) throws GlobalException;

}
