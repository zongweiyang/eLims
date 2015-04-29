package cn.labsoft.labos.framework.common.interceptor;

import cn.labsoft.labos.base.function.service.ILabPowerService;
import cn.labsoft.labos.base.function.vo.LabPowerVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;

/**
 * 
 * <strong>Title : Power </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 12:34:20 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class Power {
	public boolean isPower(String uri) throws GlobalException {
		ILabPowerService labPowerService=(ILabPowerService)SystemInstance
		.getInstance().getBean(ILabPowerService.class);
		return labPowerService.hasPower(uri);
	}
	
	public LabPowerVo getPowerInfo(String uri)  {
		ILabPowerService labPowerService=(ILabPowerService)SystemInstance
		.getInstance().getBean(ILabPowerService.class);
		return labPowerService.getPowerInfo(uri);
	}
}
