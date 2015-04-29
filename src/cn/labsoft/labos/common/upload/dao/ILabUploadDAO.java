package cn.labsoft.labos.common.upload.dao;

import java.util.List;

import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * @author Administrator
 *
 */
public interface ILabUploadDAO extends IBaseDAO {

	/**
	 * 文件上传
	 * @param UploadFileVo
	 * @return
	 * @throws GlobalException
	 */
	public LabUpload addLabUpload(LabUpload labUpload) throws GlobalException;
	/**
	 * 文件上传
	 * @param UploadFileVo
	 * @return
	 * @throws GlobalException
	 */
	public LabUpload updateLabUpload(LabUpload labUpload) throws GlobalException;
	/**
	 * 文件上传
	 * @param UploadFileVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean deleteLabUpload(LabUpload labUpload) throws GlobalException;
	
	/**
	 * 详情
	 * @param LabUpload
	 * @return
	 * @throws GlobalException
	 */
	public LabUpload findById(String id) throws GlobalException;
	
	/**
	 * 
	 * @param busId
	 * @param type
	 * @return
	 * @throws GlobalException
	 */
	public List<LabUpload> getLabUploadList(String busId,String type)
			throws GlobalException;
	
	

}
