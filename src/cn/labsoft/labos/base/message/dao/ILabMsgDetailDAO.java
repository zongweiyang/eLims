package cn.labsoft.labos.base.message.dao;

import cn.labsoft.labos.base.message.entity.LabMsgDetail;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 消息管理DAO
 * <strong>Title : ILabMsgDetailDAO </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Sep 4, 2014 1:13:34 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabMsgDetailDAO extends IBaseDAO {
	/**
	 * 新增消息详细信息
	 * @Description: 新增消息详细信息
	 * @param labMsgDetail
	 * @return LabMsgDetail
	 * @exception GlobalException 
	 */
	public LabMsgDetail addLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException;
	/**
	 * 删除消息详细信息
	 * @Description: 删除消息详细信息
	 * @param labMsgDetail
	 * @return boolean
	 * @exception GlobalException
	 */
	public boolean deleteLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException;
	/**
	 * 修改消息详细信息
	 * @Description: 修改消息详细信息
	 * @param @param labMsgDetail
	 * @return LabMsgDetail
	 * @exception GlobalException
	 */
	public LabMsgDetail updateLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException;
	/**
	 * 获得消息详细信息
	 * @Description: 获得消息详细信息
	 * @param labMsgDetail
	 * @return LabMsgDetail
	 * @exception GlobalException
	 */
	public LabMsgDetail getLabMsgDetail(LabMsgDetail labMsgDetail) throws GlobalException;
}
