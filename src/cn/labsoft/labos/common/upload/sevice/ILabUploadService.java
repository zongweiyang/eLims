package cn.labsoft.labos.common.upload.sevice;

import java.util.List;

import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;



@SuppressWarnings("unchecked")
public interface ILabUploadService {
	/**
	 * 文件上传
	 * @param LabUploadVo
	 * @return
	 * @throws GlobalException
	 */
	public LabUploadVo addLabUpload(LabUploadVo labUploadVo) throws GlobalException;
	
	/**
	 * 详情
	 * @param LabUploadVo
	 * @return
	 * @throws GlobalException
	 */
	public LabUploadVo getLabUpload(String id)
			throws GlobalException;
	
	/**
	 * 详情
	 * @param LabUploadVo
	 * @return
	 * @throws GlobalException
	 */
	public List<LabUploadVo> getLabUploadList(String busId,String bustype)
			throws GlobalException;
	/**
	 * 
	 * @param fileId
	 * @return 
	 * @throws GlobalException
	 */
	public boolean deleteFileFromUpload(String busId)throws GlobalException ;
}
