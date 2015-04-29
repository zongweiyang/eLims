package cn.labsoft.labos.source.appara.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.action.Constant;
import cn.labsoft.labos.source.appara.dao.ILabApparaAcceptDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
import cn.labsoft.labos.source.appara.entity.LabApparaAccept;
import cn.labsoft.labos.source.appara.entity.LabApparaType;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.exportexcel.OperationExcel;
@Service("labApparaService")
public class LabApparaServiceImpl implements ILabApparaService {
	
	private ILabApparaDAO labApparaDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabApparaAcceptDAO labApparaAcceptDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
	@Resource
	public void setLabApparaAcceptDAO(ILabApparaAcceptDAO labApparaAcceptDAO) {
		this.labApparaAcceptDAO = labApparaAcceptDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Override
	public void addLabAppara(LabApparaVo vo) throws GlobalException{
			LabAppara po = new LabAppara();
			try {
				BeanUtils.copyProperties(vo, po, new String[]{"apparaType"});
				if(Constant.APP_CHECK_STAT.equals(vo.getIsCheck())){//如需验收改变状态
					//需验收
					po.setIsCheck(Constant.APP_CHECK_STAT);
				}else{
					//无需验收
					po.setIsCheck(Constant.APP_CHECK_END);
				}
				//仪器类型
				if(!StrUtils.isBlankOrNull(vo.getTypeId())){
					LabApparaType labApparaType = new LabApparaType();
					labApparaType.setId(vo.getTypeId());
					po.setApparaType(labApparaType);
				}
				po.setIsCheck(vo.getIsCheck());
				po.setPrice(vo.getPrice());
				//下次检测时间
				//下次运行检查时间
				
				po.setExt01(vo.getExt01());
				po.setExt03(vo.getExt03());
				po.setExt04(vo.getExt04());
				po.setIsDel(Constants_Source.N);
				po.setTestProperty(vo.getTestProperty());
				labApparaDAO.addLabAppara(po);
				
				//修改上传的busId
				List<LabUpload> loadList = labUploadDAO.getLabUploadList(vo.getUuid(), "lab-app");
				if (loadList != null) {
					for (LabUpload labUpload : loadList) {
						labUpload.setBusId(po.getId());
						labUploadDAO.updateLabUpload(labUpload);
					}
				}
				//如果需要验收，添加到验收记录表
				if(Constant.APP_CHECK_STAT.equals(vo.getIsCheck())){
					LabApparaAccept labApparaAccept = new LabApparaAccept();
					labApparaAccept.setAppId(po.getId());
					labApparaAccept.setAppName(po.getName());
					labApparaAccept.setSpec(po.getSpec());
					labApparaAccept.setAppNo(po.getNo());
					labApparaAccept.setPrice(po.getPrice());
					labApparaAccept.setManufact(po.getProducer());
					labApparaAcceptDAO.addLabApparaAccept(labApparaAccept);
					// 流程实例
					LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labApparaAccept.getId(),Constants_Source.WF_CODE_APP_YS, "4028818c4477e360014477f253c40007", vo.getAuditMessage(), vo.getAuditResult());
					if (ins != null) {
						labApparaAccept.setProcessIns(ins);
						labApparaAcceptDAO.updateLabApparaAccept(labApparaAccept);
					}else{
						throw new GlobalException("LabApparaAcceptServiceImpl...流程实例化出错！");
					}
					
				}
			} catch (Exception e) {
				Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
				throw new GlobalException("" + e.getMessage());
				
			}
	}

	@SuppressWarnings("unused")
	private void prepareDate(LabApparaVo vo, LabAppara po) throws GlobalException{
		try {
			Date purchaseTime = DateUtils.parse(vo.getPurTime(), DateUtils.formatStr_yyyyMMdd);
			Date effectiveDate = DateUtils.parse(vo.getEffectDate(), DateUtils.formatStr_yyyyMMdd);
			Date verificationDate = DateUtils.parse(vo.getVerDate(), DateUtils.formatStr_yyyyMMdd);
			po.setPurTime(purchaseTime.toString());
			po.setEffectDate(effectiveDate.toString());
			Calendar c = Calendar.getInstance();
			c.setTime(verificationDate);
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}


	@Override
	public LabApparaVo getLabAppara(String id) throws GlobalException{
			LabApparaVo vo = new LabApparaVo();
			try {
				LabAppara po = labApparaDAO.getLabApparaById(id);
				BeanUtils.copyProperties(po, vo, new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate"});
				if(!StrUtils.isBlankOrNull(po.getPurTime())){
					vo.setPurTime(po.getPurTime().toString());
				}
				if(!StrUtils.isBlankOrNull(po.getVerLastTime())){
					vo.setVerLastTime(po.getVerLastTime().toString());
				}
				if(!StrUtils.isBlankOrNull(po.getVerNextTime())){
					vo.setVerNextTime(po.getVerNextTime().toString().substring(0,10));
				}
				if(!StrUtils.isBlankOrNull(po.getRunLastTime())){
					vo.setRunLastTime(po.getRunLastTime().toString());
				}
				if(!StrUtils.isBlankOrNull(po.getRunNextTime())){
					vo.setRunNextTime(po.getRunNextTime().toString());
				}
				if(!StrUtils.isBlankOrNull(po.getEffectDate())){
					vo.setEffectDate(po.getEffectDate().toString());
				}
				if(!StrUtils.isNull(po.getApparaType())){
					vo.setTypeId(po.getApparaType().getId());
					vo.setTypeName(po.getApparaType().getAppName());
				}
				vo.setUuid(null);
			} catch (Exception e) {
				Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
				throw new GlobalException("" + e.getMessage());
			}
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabApparaList(LabApparaVo vo,PageResult page) throws GlobalException{
		try {
				StringBuffer hql = new StringBuffer("FROM LabAppara WHERE 1=1 AND isDel='"+Constants_Source.N+"' ");
				if(!StrUtils.isBlankOrNull(vo.getName())){
					hql.append(" AND name LIKE '%"+vo.getName()+"%'");
				}
				if(!StrUtils.isBlankOrNull(vo.getNo())){
					hql.append(" AND no LIKE '%"+vo.getNo()+"%'");
				}
				if(!StrUtils.isBlankOrNull(vo.getIsCheck())){//仪器验收是否结束
					hql.append(" AND isCheck = '"+vo.getIsCheck()+"'");
				}
				if(!StrUtils.isBlankOrNull(vo.getStatus())){
					hql.append(" AND status = '"+vo.getStatus()+"'");
				}
				//添加 仪器 分类 条件
				if(!StrUtils.isBlankOrNull(vo.getTypeId())&&!vo.getTypeId().equals("0")){
					hql.append(" AND (apparaType.id = '"+vo.getTypeId().trim()+"' OR apparaType.type = '"+vo.getTypeId().trim()+"')");
				}
				PageResult pageResult = labApparaDAO.getPageResult(hql.toString(), page);
				List<LabApparaVo> voList = new ArrayList();
				List<LabAppara> poList = pageResult.getResultList();
				if(null!=pageResult.getResultList()&&pageResult.getResultList().size()>0){
					for(LabAppara po : poList){
						LabApparaVo infoVo = new LabApparaVo();
						BeanUtils.copyProperties(po,infoVo,new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate"});
						if(!StrUtils.isBlankOrNull(po.getPurTime())){
							infoVo.setPurTime(po.getPurTime().toString());
						}
						if(!StrUtils.isBlankOrNull(po.getVerLastTime())){
							infoVo.setVerLastTime(po.getVerLastTime().toString());
						}
						if(!StrUtils.isBlankOrNull(po.getVerNextTime())){
							infoVo.setVerNextTime(po.getVerNextTime().toString());
						}
						if(!StrUtils.isBlankOrNull(po.getRunLastTime())){
							infoVo.setRunLastTime(po.getRunLastTime().toString());
						}
						if(!StrUtils.isBlankOrNull(po.getRunNextTime())){
							infoVo.setRunNextTime(po.getRunNextTime().toString());
						}
						if(!StrUtils.isBlankOrNull(po.getEffectDate())){
							infoVo.setEffectDate(po.getEffectDate().toString());
						}
						voList.add(infoVo);
					}
				}
				pageResult.setResultList(voList);
				return pageResult;
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private List<LabApparaVo> checkEffectiveDate(List<LabAppara> list){
		List<LabApparaVo> result = new ArrayList<LabApparaVo>();
		if(null == list) return result;
		Calendar currentDate = Calendar.getInstance();
		Calendar effectiveDate = Calendar.getInstance();
		for(LabAppara po : list){
			LabApparaVo vo = new LabApparaVo();
			BeanUtils.copyProperties(po, vo, new String[]{"purTime","effectDate","verDate"});
			if(null != po.getEffectDate()){
				effectiveDate.add(Calendar.MONTH, -1);
				if(currentDate.after(effectiveDate)){
					vo.setAlarm("1");
				}else{
					vo.setAlarm("0");
				}
			}else{
				vo.setAlarm("0");
			}
			result.add(vo);
		}
		return result;
	}
	
	
	@Override
	public void updateLabAppara(LabApparaVo vo) throws GlobalException{
			LabAppara po = new LabAppara();
			try {
				if(!StrUtils.isBlankOrNull(vo.getId())){
					po = labApparaDAO.getLabApparaById(vo.getId());
				}
				BeanUtils.copyProperties(vo,po,new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate","isDel",""});
				if(!StrUtils.isBlankOrNull(vo.getPurTime())){
					po.setPurTime(vo.getPurTime());
				}
				if(!StrUtils.isBlankOrNull(vo.getVerLastTime())){
					po.setVerLastTime(vo.getVerLastTime());	
				}
				if(!StrUtils.isBlankOrNull(vo.getVerNextTime())){
					po.setVerNextTime(vo.getVerNextTime());
				}
				if(!StrUtils.isBlankOrNull(vo.getRunLastTime())){
					po.setRunLastTime(vo.getRunLastTime());
				}
				if(!StrUtils.isBlankOrNull(vo.getRunNextTime())){
					po.setRunNextTime(vo.getRunNextTime());
				}
				if(!StrUtils.isBlankOrNull(vo.getEffectDate())){
					po.setEffectDate(vo.getEffectDate());
				}
				if(!StrUtils.isBlankOrNull(vo.getSupplier())){
					po.setSupplier(vo.getSupplier());
				}
				
				po.setExt01(vo.getExt01());
				po.setExt03(vo.getExt03());
				po.setExt04(vo.getExt04());
				po.setTestProperty(vo.getTestProperty());
				po.setStatus(vo.getStatus());
				labApparaDAO.updateLabAppara(po);
				
				
			} catch (Exception e) {
				Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
				throw new GlobalException("" + e.getMessage());
			}
	}
	
	
	

	@SuppressWarnings({ "unchecked", "unused" })
	private List<LabAppara> getApparatusInfoList(String status) throws GlobalException {
		try{
			String hql = "FROM LabAppara WHERE 1=1 and isDel='"+Constants_Source.N+"' and 1=1 AND status = '"+ status+"'";
			return labApparaDAO.find(hql);
		}catch(Exception e){
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabApparaVo> getLabApparaList(LabApparaVo vo) throws GlobalException{
		try {
			List<LabApparaVo> resultList = new ArrayList<LabApparaVo>();
			StringBuffer hql = new StringBuffer("FROM LabAppara WHERE 1=1 and isDel='"+Constants_Source.N+"' and 1=1");
			if(!StrUtils.isEmpty(vo.getName())){
				hql.append(" AND  name like '%"+vo.getName()+"%'");
			}
			if(!StrUtils.isEmpty(vo.getStatus())){
				hql.append(" AND status = '"+ vo.getStatus() +"'");
			}
			hql.append(" ORDER BY price desc");
			List<LabAppara> poList = new ArrayList<LabAppara>();
			poList = labApparaDAO.find(hql.toString());
			
			if(null!=poList&&poList.size()>0){
				for(LabAppara po : poList){
					LabApparaVo infoVo = new LabApparaVo();
					BeanUtils.copyProperties(po,infoVo,new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate"});
					if(null!=po.getPurTime()&&!"".equals(po.getPurTime())&&po.getPurTime().length()>10)
						infoVo.setPurTime(po.getPurTime().toString());
					if(null!=po.getVerLastTime()&&!"".equals(po.getVerLastTime())&&po.getVerLastTime().length()>9)
						infoVo.setVerLastTime(po.getVerLastTime().toString());
					if(null!=po.getVerNextTime()&&!"".equals(po.getVerNextTime())&&po.getVerNextTime().length()>9)
						infoVo.setVerNextTime(po.getVerNextTime().toString());
					if(null!=po.getRunLastTime()&&!"".equals(po.getRunLastTime())&&po.getRunLastTime().length()>9)
						infoVo.setRunLastTime(po.getRunLastTime().toString());
					if(null!=po.getRunNextTime()&&!"".equals(po.getRunNextTime())&&po.getRunNextTime().length()>9)
						infoVo.setRunNextTime(po.getRunNextTime().toString());
					if(null!=po.getEffectDate()&&!"".equals(po.getEffectDate())&&po.getEffectDate().length()>9)
						infoVo.setEffectDate(po.getEffectDate().toString());
					resultList.add(infoVo);
				}
			}
			return resultList;
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabAppara(String id) throws GlobalException{
		try {
			LabAppara labAppara = (LabAppara)labApparaDAO.findById(LabAppara.class,id);
			if(null != labAppara){
				labAppara.setIsDel(Constants_Source.Y);
				labApparaDAO.updateLabAppara(labAppara);
			}
			return true;
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public List<LabUploadVo> getLabAppFile(LabApparaVo vo,String type) throws GlobalException {
		List<LabUploadVo> Volist = new ArrayList<LabUploadVo>();
		try {
			if(!StrUtils.isBlankOrNull(vo.getUuid())){
				List<LabUpload> Polist = new ArrayList<LabUpload>();
				Polist=labUploadDAO.getLabUploadList(vo.getUuid(), type);
				for(int i=0;i<Polist.size();i++){
					LabUploadVo labUploadVo= new LabUploadVo();
					BeanUtils.copyProperties(Polist.get(i), labUploadVo);
					String path=ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/uploadDoc")+File.separator
					+Polist.get(i).getBusType()+File.separator+Polist.get(i).getVname();
					labUploadVo.setPath(path);
					Volist.add(labUploadVo);
				}
			}else if(!StrUtils.isBlankOrNull(vo.getId())){
				List<LabUpload> Polist = new ArrayList<LabUpload>();
				Polist=labUploadDAO.getLabUploadList(vo.getId(), type);
				for(int i=0;i<Polist.size();i++){
					LabUploadVo labUploadVo= new LabUploadVo();
					BeanUtils.copyProperties(Polist.get(i), labUploadVo);
					String path=ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/uploadDoc")+File.separator
					+Polist.get(i).getBusType()+File.separator+Polist.get(i).getVname();
					labUploadVo.setPath(path);
					Volist.add(labUploadVo);
				}
			}
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return Volist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean delLabAppFile(String id) throws GlobalException {
		boolean key=true;
		try {
			if(!StrUtils.isBlankOrNull(id)){
				String hql= "FROM LabUpload WHERE id = '"+id+"'";
				List<LabUpload> fileList=labUploadDAO.find(hql);
				if(fileList!=null){
					for (LabUpload labUpload : fileList) {
						labUploadDAO.deleteLabUpload(labUpload);
					}
				}
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabApparaVo> getApparaListByTypeId(String id) throws GlobalException {
		String hql ="FROM LabAppara WHERE 1=1 ";
		if(!StrUtils.isBlankOrNull(id)){
			hql+=" AND apparaType.id = '"+id+"'";
		}
		List<LabApparaVo> voList = new ArrayList<LabApparaVo>();
		List<LabAppara> poList=labApparaDAO.find(hql);
		if(poList.size()>0){
			for (LabAppara po : poList) {
				LabApparaVo vo = new LabApparaVo();
				BeanUtils.copyProperties(po, vo,new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate"});
				voList.add(vo);
			}
		}
		return voList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabApparaVo> getLabApparaByName(String name)throws GlobalException {
		String hql ="FROM LabAppara WHERE 1=1 ";
		if(!StrUtils.isBlankOrNull(name)){
			hql+=" AND name = '"+name+"'";
		}
		List<LabApparaVo> voList = new ArrayList<LabApparaVo>();
		List<LabAppara> poList=labApparaDAO.find(hql);
		if(poList.size()>0){
			for (LabAppara po : poList) {
				LabApparaVo vo = new LabApparaVo();
				BeanUtils.copyProperties(po, vo,new String[]{"purTime","verLastTime","verNextTime","runLastTime","runNextTime","effectDate"});
				voList.add(vo);
			}
		}
		return voList;
	}

	@Override
	public boolean importLabAppExcel(String uploadFileName , File upload ,LabApparaVo labApparaVo) throws GlobalException {
				String[][] value=uploadFile(uploadFileName,upload);
			try {
				for (int i = 1; i < value.length; i++) {
					LabApparaVo vo = new LabApparaVo();
					LabAppara po = new LabAppara();
					if(!StrUtils.isBlankOrNull(value[i][0])){
						vo.setNo(value[i][0]);
					}
					if(!StrUtils.isBlankOrNull(value[i][1])){
						vo.setName(value[i][1]);
					}
					if(!StrUtils.isBlankOrNull(value[i][2])){
						vo.setCode(value[i][2]);
					}
					if(!StrUtils.isBlankOrNull(value[i][3])){
						vo.setSpec(value[i][3]);
					}
					BeanUtils.copyProperties(vo, po);
					//载入仪器类型
					LabApparaType labApparaType=new LabApparaType();
					if(!StrUtils.isBlankOrNull(labApparaVo.getTypeId())){
						labApparaType.setId(labApparaVo.getTypeId());
						po.setApparaType(labApparaType);
					}else{
						labApparaType.setId("0");
						po.setApparaType(labApparaType);
					}
					//启用仪器
					po.setIsDel(Constants_Source.N);
					po.setStatus(Constant.APPARA_ZC);
					labApparaDAO.addLabAppara(po);
					
				}
				
			} catch (GlobalException e) {
				Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
				throw new GlobalException("" + e.getMessage());
			}
			return true;
	}

	@Override
	public String[][] uploadFile(String uploadFileName, File upload) throws GlobalException {
		String result[] = new String[3];
		String value[][] =null;
		try {
			String trueName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1, uploadFileName.length());
			String uploadDirectory = ServletActionContext.getServletContext().getRealPath("/uploadDoc/appFile");
			String FileType = trueName.substring(trueName.lastIndexOf(".") + 1,trueName.length());
			String temp = Long.toString(new Date().getTime());
			String targetFileName = temp + "." + FileType;
			File file = new File(uploadDirectory, targetFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.copyFile(upload, file);
			result[0] = trueName;//真实名称
			result[1] = uploadDirectory;//存放路径
			result[2] = targetFileName;//上传后名称
			//获取Excel中的值
			String fileUrl = result[1] + "\\" + result[2];
			fileUrl = fileUrl.replace('\\', '/');
			File target = new File(result[1], result[2]);
			value= OperationExcel.readExcel(fileUrl, 0);
			//删除所上传的文件
			target.getAbsoluteFile().delete();
		} catch (Exception e) {
			Log4J.error("LabApparaServiceImpl error...."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return value;
	}

	@Override
	public String getLabApparaExcel() {
		String path="";
		path = File.separator+"/templates"
		+File.separator
		+"appara"
		+File.separator
		+"labAppara.xls";
		return path;
	}

	@Override
	public InputStream export2Excel(LabApparaVo labApparaVo) throws GlobalException {
		@SuppressWarnings("unused")
		String path="";
		InputStream excelStream=null;
		Map<String, LabApparaVo> beans = new HashMap<String, LabApparaVo>();
		beans.put("data", labApparaVo);
    	String tempFilePath="";
		tempFilePath="exportApparaExcel.xls";
		try {
			String templateFileName = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
			+"templates"+ File.separator+"appara" + File.separator+ tempFilePath;
			String destFileName = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
			+"uploadDoc"+ File.separator+"appFile" + File.separator+ System.currentTimeMillis() + "." + "xls";
			String realPath =  ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
			+"uploadDoc"+ File.separator+"appFile"+ File.separator;
			File file  = new File(realPath);
			if(!file.exists()){
				file.mkdir();
			}
				XLSTransformer transformer = new XLSTransformer();
				transformer.transformXLS(templateFileName, beans, destFileName);
				File targetFile = new File(destFileName);
				excelStream = new BufferedInputStream(new FileInputStream(targetFile), 16 * 1024);
				path=targetFile.getPath();
		} catch (Exception e1) {
			Log4J.error("LabApparaServiceImpl error...."+e1.getMessage(),e1);
			throw new GlobalException("" + e1.getMessage());
		}
        return excelStream;
	}

	@Override
	public LabApparaVo getLabApparaVo(LabApparaVo labApparaVo) throws GlobalException {
		List<LabApparaVo> list = new ArrayList<LabApparaVo>();
		String [] ids = labApparaVo.getIds();
		if (null!=ids) {
			for (String id : ids) {
				LabApparaVo vo =new LabApparaVo();
				vo = getLabAppara(id);
				if(Constant.APPARA_ZC.equals(vo.getStatus())){
					vo.setStatus("正常");
				}else if(Constant.APPARA_WX.equals(vo.getStatus())){
					vo.setStatus("维修");
				}else if(Constant.APPARA_TY.equals(vo.getStatus())){
					vo.setStatus("停用");
				}else if(Constant.APPARA_BF.equals(vo.getStatus())){
					vo.setStatus("报废");
				}
				list.add(vo);
			}
		}
		labApparaVo.setLabApparaVoList(list);
		return labApparaVo;
	}

	@SuppressWarnings("unchecked")
	public String getTypeIdAndSubIdStrById(String id) throws GlobalException {
		String idStr = id + ",";
		List<LabApparaType> list = labApparaDAO
				.find("from LabApparaType where type.id='" + id.trim()
						+ "' and isDel='" + Constants_Source.N + "'");
		if (null != list && list.size() > 0) {
			for (LabApparaType labAppType : list) {
				idStr += getTypeIdAndSubIdStrById(labAppType.getId());
			}
		}
		return idStr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabApparaByAppType(String typeId)
			throws GlobalException {
		typeId=getTypeIdAndSubIdStrById(typeId);
		String hql="from LabAppara  WHERE isDel='"+Constants_Source.N+"' and apparaType.id in ('" + typeId.replace(",", "','")+ "')";
		List<LabAppara> list=labApparaDAO.find(hql);
		try {
			for(LabAppara po:list)
			{
				po.setIsDel(Constants_Source.Y);
				labApparaDAO.updateLabAppara(po);
			}
			return true;
		} catch (Exception e) {
			Log4J.error("仪器批量删除异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}

}
