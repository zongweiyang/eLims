package cn.labsoft.labos.common.upload.sevice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@SuppressWarnings("unchecked")
@Service("labUploadService")
public class LabUploadServiceImpl implements ILabUploadService {
	private ILabUploadDAO labUploadDAO;
	private ILabUserDAO labUserDAO;
	
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabUserDAO(ILabUserDAO labUserDAO) {
		this.labUserDAO = labUserDAO;
	}

	@Override
	public LabUploadVo addLabUpload(LabUploadVo uploadFileVo) throws GlobalException {
		LabUpload uploadFile = new LabUpload();
		BeanUtils.copyProperties(uploadFileVo, uploadFile, new String[] { "createTime" });
		if(!StrUtils.isBlankOrNull(uploadFileVo.getCreateUserId())){
			LabUser user=labUserDAO.getLabUser(uploadFileVo.getCreateUserId());
			if(null!=user){
				uploadFile.setCreateUserId(user.getId());
				uploadFile.setCreater(user.getName());
			}
		}
		uploadFile.setCreateTime(DateUtils.getCurrDateTimeStr());
		uploadFile.setIsDel(Constants_Base.N);
			try {
				labUploadDAO.addLabUpload(uploadFile);
				uploadFileVo.setId(uploadFile.getId());
			} catch (RuntimeException e) {
				e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		return uploadFileVo;
	}

	@Override
	public boolean deleteFileFromUpload(String sampId) throws GlobalException {
		LabUpload uploadFile = new LabUpload();
		uploadFile = labUploadDAO.findById(sampId);
		uploadFile.setIsDel("Y");
		labUploadDAO.updateLabUpload(uploadFile);
		return true;
	}

	@Override
	public LabUploadVo getLabUpload(String id) throws GlobalException {
		LabUploadVo vo = new LabUploadVo();
		BeanUtils.copyProperties(labUploadDAO.findById(id), vo, new String[] { "" });
		return vo;
	}

	@Override
	public List<LabUploadVo> getLabUploadList(String busId, String busType) throws GlobalException {
		List<LabUpload> poList = labUploadDAO.getLabUploadList(busId, busType);
		List<LabUploadVo> voList = new ArrayList<LabUploadVo>();
		if (null != poList && poList.size() > 0) {
			for (LabUpload po : poList) {
				LabUploadVo vo = new LabUploadVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				voList.add(vo);
			}
		}
		return voList;
	}
}
