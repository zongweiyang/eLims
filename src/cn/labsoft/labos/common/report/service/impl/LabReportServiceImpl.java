package cn.labsoft.labos.common.report.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.configs.dao.ILabConfigDAO;
import cn.labsoft.labos.base.configs.entity.LabConfig;
import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.common.report.dao.ILabReportDAO;
import cn.labsoft.labos.common.report.entity.LabReport;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;

@Service("labReportService")
public class LabReportServiceImpl implements ILabReportService {
	private ILabReportDAO labReportDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabConfigDAO labConfigDAO;
	
	@Resource
	public void setLabReportDAO(ILabReportDAO labReportDAO) {
		this.labReportDAO = labReportDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabConfigDAO(ILabConfigDAO labConfigDAO) {
		this.labConfigDAO = labConfigDAO;
	}

	@Override
	public LabReportVo addLabReport(LabReportVo labReportVo) throws GlobalException {
		LabReport labReport = new LabReport();
		try {
			labReport = this.vo2Po(labReportVo, labReport);
			if (null != labReportVo.getBusId() && !labReportVo.getBusId().equals("")) {
				LabFunction fun = labFunctionDAO.getLabFunction(labReportVo.getBusId());
				labReport.setBusId(fun.getId());
				labReport.setBusName(fun.getName());
			}
			labReportDAO.addLabReport(labReport);
			labReportVo.setId(labReport.getId());
		} catch (Exception e) {
			Log4J.error("LabReportServiceImpl addLabReport  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labReportVo;
	}

	@Override
	public boolean deleteLabReport(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				//key=labReportDAO.deleteLabReport(ids);
				for (String id : ids) {
					if (id == null || id.trim().length() == 0)
						continue;
					LabReport report = labReportDAO.getLabReport(id);
					report.setIsDel(Constants_Base.Y);
					labReportDAO.updateLabReport(report);
				}
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabReportServiceImpl deleteLabReport  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabReportVo getLabReport(String id) throws GlobalException {
		LabReportVo labReportVo = new LabReportVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabReport labReport = labReportDAO.getLabReport(id);
				labReportVo = this.po2Vo(labReport, labReportVo);
			} catch (Exception e) {
				Log4J.error("LabReportServiceImpl getLabReport  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labReportVo;
	}

	@Override
	public List<LabReportVo> getLabReportList(LabReportVo labReportVo) throws GlobalException {
		String wereHql = "";
		if(!StrUtils.isBlankOrNull(labReportVo.getBusId())){
			wereHql+=" AND busId='"+labReportVo.getBusId()+"'";
		}
		return this.getLabReportVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabReportPR(LabReportVo labReportVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReport WHERE isDel='" + Constants_Base.N + "'";
		// modify by danlee begin
		if(!StrUtils.isBlankOrNull(labReportVo.getType())){
			hql+=" AND type = '"+labReportVo.getType()+"'";
		}
		if(!StrUtils.isBlankOrNull(labReportVo.getTitle())){
			hql+=" AND title LIKE '%"+labReportVo.getTitle()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labReportVo.getBusId())){
			hql+=" AND busId = '"+labReportVo.getBusId()+"'";
		}
		// modify by danlee end
		pageResult = labReportDAO.getPageResult(hql, pageResult);
		List<LabReportVo> listLabReportVo = new ArrayList<LabReportVo>();
		List<LabReport> listLabReport = new ArrayList<LabReport>();
		listLabReport = pageResult.getResultList();
		for (LabReport labReport : listLabReport) {
			LabReportVo vo = new LabReportVo();
			vo = this.po2Vo(labReport, vo);
			listLabReportVo.add(vo);
		}
		pageResult.setResultList(listLabReportVo);
		return pageResult;
	}

	@Override
	public boolean updateLabReport(LabReportVo labReportVo) throws GlobalException {
		LabReport labReport = new LabReport();
		boolean key = true;
		try {
			labReport = this.vo2Po(labReportVo, labReport);
			if (null != labReportVo.getBusId() && !labReportVo.getBusId().equals("")) {
				LabFunction fun = labFunctionDAO.getLabFunction(labReportVo.getBusId());
				labReport.setBusId(fun.getId());
				labReport.setBusName(fun.getName());
			}
			labReportDAO.updateLabReport(labReport);
		} catch (Exception e) {
			key = false;
			Log4J.error("LabReportServiceImpl updateLabReport  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabReport4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabReport labReport = labReportDAO.getLabReport(id);
					labReport.setIsDel(Constants_Base.Y);
					labReportDAO.updateLabReport(labReport);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error("LabReportServiceImpl updateLabReport4Del  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<LabReportVo> getLabReportVoListByWhere(String wereHql) throws GlobalException {
		List<LabReportVo> listLabReportVo = new ArrayList<LabReportVo>();
		String hql = "FROM LabReport WHERE isDel='" + Constants_Base.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabReport> listLabReport = labReportDAO.find(hql);
		if (listLabReport != null && listLabReport.size() > 0) {
			for (LabReport labReport : listLabReport) {
				LabReportVo labReportVo = new LabReportVo();
				labReportVo = this.po2Vo(labReport, labReportVo);
				listLabReportVo.add(labReportVo);
			}
		}
		return listLabReportVo;
	}

	public LabReport vo2Po(LabReportVo labReportVo, LabReport labReport) {
		BeanUtils.copyProperties(labReportVo, labReport, new String[] { "isDel" });
		return labReport;
	}

	public LabReportVo po2Vo(LabReport labReport, LabReportVo labReportVo) {
		BeanUtils.copyProperties(labReport, labReportVo);
		return labReportVo;
	}

	@SuppressWarnings( { "deprecation", "unchecked" })
	@Override
	public LabReportVo getLabReportModel(String id) throws GlobalException {
		LabReportVo labReportVo = new LabReportVo();
		if (!StrUtils.isBlankOrNull(id)) {
			LabReport labReport = labReportDAO.getLabReport(id);
			labReportVo = this.po2Vo(labReport, labReportVo);
			String type = labReport.getType();
			LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
			String path = config.getValue();
			if (type != null && type.equals("1")) {
				path += File.separator + "jspModel" + File.separator + id + ".jsp";
				path = ServletActionContext.getRequest().getRealPath(path);
				File file = new File(path);
				if (!file.exists()) {
					String basejspPath = config.getValue()+File.separator + "base" + File.separator + "report.jsp";
					basejspPath = ServletActionContext.getRequest().getRealPath(basejspPath);
					try {
						FileUtils.copyFile(new File(basejspPath), new File(path));
					} catch (IOException e) {
						Log4J.error("LabReportServiceImpl", e);
						throw new GlobalException("" + e.getMessage());
					}
				}
				try {
					String oldhtmlContentStr = FileUtils.readFileToString(file, "UTF-8");
					labReportVo.setFile(oldhtmlContentStr);
				} catch (FileNotFoundException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				} catch (IOException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			} 
			else if (type != null && type.equals("2")) {
				String savePath = "";
				path += File.separator + "excelModel" + File.separator + id + ".xls";
				savePath = path;
				path = ServletActionContext.getRequest().getRealPath(path);
				File file = new File(path);
				
				if (!file.exists()) {
					String baseXlsPath = config.getValue()+File.separator + "base" + File.separator + "report.xls";
					baseXlsPath = ServletActionContext.getRequest().getRealPath(baseXlsPath);
					try {
						FileUtils.copyFile(new File(baseXlsPath), new File(path));
					} catch (IOException e) {
						Log4J.error("LabReportServiceImpl", e);
						throw new GlobalException("" + e.getMessage());
					}
				}
				savePath = savePath.replace("/", File.separator);
				savePath = savePath.replace("\\", File.separator);
				savePath = savePath.replace("\\", "\\\\");//用iweboffice打开excel必须
				labReportVo.setPath(savePath);
			}
		}
		return labReportVo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo updateLabReportModel(LabReportVo labReportVo) throws GlobalException {
		LabReport labReport = labReportDAO.getLabReport(labReportVo.getId());
		String type = labReport.getType();
		LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
		String path = config.getValue();
		String savePath = "";
		if (type != null && type.equals("1")) {
			path += File.separator + "jspModel" + File.separator + labReport.getId() + ".jsp";
			savePath = path;
			path = ServletActionContext.getRequest().getRealPath(path);
			File file = new File(path);
			try {
				FileUtils.writeStringToFile(file, labReportVo.getFile(), "UTF-8");
			} catch (FileNotFoundException e1) {
				Log4J.err("LabReportServiceImpl.." + e1.getMessage());
				throw new GlobalException("" + e1.getMessage());
			} catch (IOException e) {
				Log4J.err("LabReportServiceImpl.." + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		else if (type != null && type.equals("2")) {
			path += File.separator + "excelModel" + File.separator + labReport.getId() + ".xls";
			savePath = path;
		}
		savePath = savePath.replace("\\", "/");
		labReport.setPath(savePath);
		labReportDAO.updateLabReport(labReport);
		labReportVo.setType(type);
		return labReportVo;
	}

	/**
	 * 处理字符串中的单引号、双引号、尖括号、成为转义符，除过JS之外
	 * 
	 * @param temp
	 * @return String
	 */
	private String replaceStr(String temp) {
		String str = "";
		String str2 = "";
		String str3 = "";
		while (temp.length() > 0) {
			if (temp.indexOf("<script") != -1) {
				str3 = temp.substring(temp.indexOf("<script"), temp.indexOf(">", temp.indexOf("<script")) + 1);
				str = temp.substring(0, temp.indexOf(str3));
				str = str.replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;").replace("\"", "&quot;");
				str2 = str2 + str + (str3.replace("<", "&lt;").replace(">", "&gt;")) + temp.substring(temp.indexOf(str3) + str3.length(), temp.indexOf("</script>")) + "&lt;/script&gt;";
				temp = temp.substring(temp.indexOf("</script>") + "</script>".length(), temp.length());
			} else {
				str2 = str2 + temp;
				break;
			}
		}
		return str2;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo addLabReport4Copy(LabReportVo labReportVo) throws GlobalException {
		if (!StrUtils.isBlankOrNull(labReportVo.getId())) {
			LabReport report = labReportDAO.getLabReport(labReportVo.getId().trim());
			LabReport reportCopy = new LabReport();
			BeanUtils.copyProperties(report, reportCopy, new String[] {});
			reportCopy.setId(null);
			reportCopy.setTitle(report.getTitle() + "复制");
			labReportDAO.addLabReport(reportCopy);
			//copy model
			LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
			String path = config.getValue();
			String savePath = "";
			if (!StrUtils.isBlankOrNull(report.getPath())&& reportCopy.getType().equals("1")) {
				path += File.separator + "jspModel" + File.separator + reportCopy.getId() + ".jsp";
				savePath = path;
				String oldPath = ServletActionContext.getRequest().getRealPath(report.getPath());
				File oldfile = new File(oldPath);
				if (oldfile.exists()) {
					path = ServletActionContext.getRequest().getRealPath(path);
					try {
						FileUtils.copyFile(new File(oldPath), new File(path));
					} catch (IOException e) {
						Log4J.error("LabReportServiceImpl", e);
						throw new GlobalException("" + e.getMessage());
					}
				}
			} else if (!StrUtils.isBlankOrNull(report.getPath())&& reportCopy.getType().equals("2")) {
				path += File.separator + "excelModel" + File.separator + reportCopy.getId() + ".xls";
				savePath = path;
				String oldPath = ServletActionContext.getRequest().getRealPath(report.getPath());
				File oldfile = new File(oldPath);
				if (oldfile.exists()) {
					path = ServletActionContext.getRequest().getRealPath(path);
					try {
						FileUtils.copyFile(new File(oldPath), new File(path));
					} catch (IOException e) {
						Log4J.error("LabReportServiceImpl", e);
						throw new GlobalException("" + e.getMessage());
					}
				}
			}
			savePath = savePath.replace("\\", "/");
			reportCopy.setPath(savePath);
			labReportDAO.updateLabReport(reportCopy);
		}
		return labReportVo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo addLabReport4Bus(LabReportVo labReportVo)
			throws GlobalException {
		String filePath=labReportVo.getPath();
		
		String hql="FROM LabReport WHERE isDel='"+Constants_Base.N+"'";
		hql+=" AND id='"+labReportVo.getId()+"'";
		List<LabReport> poList= labReportDAO.getLabReportList(hql);
		if(poList!=null&&poList.size()>0){
			LabReport po=poList.get(0);
			BeanUtils.copyProperties(po, labReportVo, new String[]{"editType"});
			
			String type=po.getType();
			LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
			String path = config.getValue();
			
			if (type != null && type.equals("1")) {
				File file =null;
				//重新生成文件
				if(null!=labReportVo.getIsNew()&&labReportVo.getIsNew().equals("1")){
					String filePathDir=path+File.separator +"reportFile"+File.separator +po.getBusCode();
					path=filePathDir+File.separator +labReportVo.getBusInsId()+".jsp";
					labReportVo.setPath(path);
					filePathDir = ServletActionContext.getRequest().getRealPath(filePathDir);
					path=ServletActionContext.getRequest().getRealPath(path);
					File fileDir = new File(filePathDir);
					if(!fileDir.exists()&&!fileDir.isDirectory()){
						fileDir.mkdirs();
					}
					String basejspPath = ServletActionContext.getRequest().getRealPath(po.getPath());
					File fileTemp = new File(basejspPath);
					if(fileTemp.exists()&&fileTemp.isFile()){
						try {
							String jspContentStr = FileUtils.readFileToString(fileTemp,"UTF-8");
							jspContentStr="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"
								+"<%@ taglib prefix=\"s\" uri=\"/WEB-INF/tld/struts/struts-tags.tld\" %>"+jspContentStr;
							file=new File(path);
							FileUtils.writeStringToFile(file, jspContentStr, "UTF-8");
						} catch (IOException e) {
							Log4J.error("LabReportServiceImpl", e);
							throw new GlobalException("" + e.getMessage());
						}
					}
				}else if(!StrUtils.isBlankOrNull(filePath)){//取出已有文件
					labReportVo.setPath(filePath);
					String filePathx=ServletActionContext.getRequest().getRealPath(filePath);
					file=new File(filePathx);
				}
				if(file==null||!file.exists()){
					String filePathDir=path+File.separator +"reportFile"+File.separator +po.getBusCode();
					path=filePathDir+File.separator +labReportVo.getBusInsId()+".jsp";
					labReportVo.setPath(path);
					filePathDir = ServletActionContext.getRequest().getRealPath(filePathDir);
					File fileDir = new File(filePathDir);
					if(!fileDir.exists()&&!fileDir.isDirectory()){
						fileDir.mkdirs();
					}
					path = ServletActionContext.getRequest().getRealPath(path);
					file = new File(path);
				}
				//若文件不存在，怎根据母版重新生成文件
				if (file==null||!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e2) {
						Log4J.error("LabReportServiceImpl", e2);
						throw new GlobalException("" + e2.getMessage());
					}
					String basejspPath = ServletActionContext.getRequest().getRealPath(po.getPath());
					File fileTemp = new File(basejspPath);
					if(fileTemp.exists()&&fileTemp.isFile()){
						try {
							String jspContentStr = FileUtils.readFileToString(fileTemp,"UTF-8");
							jspContentStr="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"
								+"<%@ taglib prefix=\"s\" uri=\"/WEB-INF/tld/struts/struts-tags.tld\" %>"+jspContentStr;
							FileUtils.writeStringToFile(file, jspContentStr, "UTF-8");
						} catch (IOException e) {
							Log4J.error("LabReportServiceImpl", e);
							throw new GlobalException("" + e.getMessage());
						}
					}
				}
				try {
					String jspContentStr = FileUtils.readFileToString(file,"UTF-8");
					if(!jspContentStr.contains("import=\"java.util.*")){
						jspContentStr="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"
							+"<%@ taglib prefix=\"s\" uri=\"/WEB-INF/tld/struts/struts-tags.tld\" %>"+jspContentStr;
					}
					labReportVo.setFile(jspContentStr);
				} catch (IOException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			} else if (type != null && type.equals("2")) {
				File file =null;
				if(null!=labReportVo.getIsNew()&&labReportVo.getIsNew().equals("1")){
					String filePathDir=path+File.separator +"reportFile"+File.separator +po.getBusCode();
					path=filePathDir+File.separator +labReportVo.getBusInsId()+".xls";
					labReportVo.setPath(path);
					filePathDir = ServletActionContext.getRequest().getRealPath(filePathDir);
					File fileDir = new File(filePathDir);
					if(!fileDir.exists()&&!fileDir.isDirectory()){
						fileDir.mkdirs();
					}
					path = ServletActionContext.getRequest().getRealPath(path);
					file = new File(path);
					if(file==null||!file.exists()){
						try {
							file.createNewFile();
						} catch (IOException e2) {
							Log4J.error("LabReportServiceImpl", e2);
							throw new GlobalException("" + e2.getMessage());
						}
					}
					String basejspPath = ServletActionContext.getRequest().getRealPath(po.getPath());
					File fileTemp = new File(basejspPath);
					if(fileTemp.exists()&&fileTemp.isFile()){
						try {
							FileUtils.copyFile(fileTemp,file);
						} catch (IOException e) {
							Log4J.error("LabReportServiceImpl", e);
							throw new GlobalException("" + e.getMessage());
						}
					}
				}else if(!StrUtils.isBlankOrNull(filePath)){
					labReportVo.setPath(filePath);
					String filePathx=ServletActionContext.getRequest().getRealPath(filePath);
					file=new File(filePathx);
				}
				if(file==null||!file.exists()){
					String filePathDir=path.trim()+File.separator +"reportFile"+File.separator +po.getBusCode().trim();
					path=filePathDir+File.separator +labReportVo.getBusInsId().trim()+".xls";
					labReportVo.setPath(path);
					filePathDir = ServletActionContext.getRequest().getRealPath(filePathDir);
					File fileDir = new File(filePathDir);
					if(!fileDir.exists()&&!fileDir.isDirectory()){
						fileDir.mkdirs();
					}
					path = ServletActionContext.getRequest().getRealPath(path);
					file = new File(path);
				}
				if (file==null||!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e2) {
						Log4J.error("LabReportServiceImpl", e2);
						throw new GlobalException("" + e2.getMessage());
					}
					String basejspPath = ServletActionContext.getRequest().getRealPath(po.getPath());
					File fileTemp = new File(basejspPath);
					if(fileTemp.exists()&&fileTemp.isFile()){
						try {
							FileUtils.copyFile(fileTemp,file);
						} catch (IOException e) {
							Log4J.error("LabReportServiceImpl", e);
							throw new GlobalException("" + e.getMessage());
						}
					}
				}
			}
		}
		return labReportVo;
	}
	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo getLabReport4Bus(LabReportVo labReportVo)
			throws GlobalException {
		String filePath=labReportVo.getPath();
		String hql="FROM LabReport WHERE isDel='"+Constants_Base.N+"'";
		hql+=" AND id='"+labReportVo.getId()+"'";
		List<LabReport> poList= labReportDAO.getLabReportList(hql);
		if(poList!=null&&poList.size()>0){
			LabReport po=poList.get(0);
			BeanUtils.copyProperties(po, labReportVo, new String[]{"editType"});
			String type=po.getType();
			if (type != null && type.equals("1")) {
				File file =null;
				if(!StrUtils.isBlankOrNull(filePath)){
					labReportVo.setPath(filePath);
					String filePathx=ServletActionContext.getRequest().getRealPath(filePath);
					file=new File(filePathx);
				}
				if (file!=null&&file.exists()&&file.isFile()) {
					try {
						String jspContentStr = FileUtils.readFileToString(file,"UTF-8");
						if(!jspContentStr.contains("import=\"java.util.*")){
							jspContentStr="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"
								+"<%@ taglib prefix=\"s\" uri=\"/WEB-INF/tld/struts/struts-tags.tld\" %>"+jspContentStr;
						}
						labReportVo.setFile(jspContentStr);
					} catch (IOException e) {
						Log4J.err("LabReportServiceImpl.." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					}
				}
			} else if (type != null && type.equals("2")) {
				File file =null;
				if(!StrUtils.isBlankOrNull(filePath)){
					labReportVo.setPath(filePath);
					String filePathx=ServletActionContext.getRequest().getRealPath(filePath);
					file=new File(filePathx);
					if (file==null&&file.exists()&&file.isFile()) {
						labReportVo.setPath(filePathx);
					}
				}
			}
		}
		return labReportVo;
	}
	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo updateLabReport4Bus(LabReportVo labReportVo)
			throws GlobalException {
			String path = labReportVo.getPath();
			path = ServletActionContext.getRequest().getRealPath(path);
			File file=new File(path);
			if (file.exists()) {
				try {
					String jspContentStr =labReportVo.getFile();
					if(!jspContentStr.contains("pageEncoding=\"UTF-8\"")){
						jspContentStr="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"
							+"<%@ taglib prefix=\"s\" uri=\"/WEB-INF/tld/struts/struts-tags.tld\" %>"+jspContentStr;
					}
					FileUtils.writeStringToFile(file, jspContentStr, "UTF-8");
				} catch (FileNotFoundException e1) {
					Log4J.err("LabReportServiceImpl.." + e1.getMessage());
					throw new GlobalException("" + e1.getMessage());
				} catch (IOException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		return labReportVo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo getLabReportModel4Code(String id) throws GlobalException {
		LabReportVo labReportVo = new LabReportVo();
		if (!StrUtils.isBlankOrNull(id)) {
			LabReport labReport = labReportDAO.getLabReport(id);
			labReportVo = this.po2Vo(labReport, labReportVo);
			String type = labReport.getType();
			LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
			String path = config.getValue();
			if (type != null && type.equals("1")) {
				path += File.separator + "jspModel" + File.separator + id + ".jsp";
				path = ServletActionContext.getRequest().getRealPath(path);
				File file = new File(path);
				if (!file.exists()) {
					String basejspPath = config.getValue()+File.separator + "base" + File.separator + "report.jsp";
					basejspPath = ServletActionContext.getRequest().getRealPath(basejspPath);
					try {
						FileUtils.copyFile(new File(basejspPath), new File(path));
					} catch (IOException e) {
						Log4J.error("LabReportServiceImpl", e);
						throw new GlobalException("" + e.getMessage());
					}
				}
				try {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					StringBuffer sb = new StringBuffer();
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line + "\n");
					}
					labReportVo.setFile(replaceStr(sb.toString()));
					bufferedReader.close();
				} catch (FileNotFoundException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				} catch (IOException e) {
					Log4J.err("LabReportServiceImpl.." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			} 
		}
		return labReportVo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabReportVo updateLabReportModel4Code(LabReportVo labReportVo)
			throws GlobalException {
		LabReport labReport = labReportDAO.getLabReport(labReportVo.getId());
		String type = labReport.getType();
		LabConfig config = labConfigDAO.getLabConfigByCode("reportModel");
		String path = config.getValue();
		String savePath = "";
		if (type != null && type.equals("1")) {
			path += File.separator + "jspModel" + File.separator + labReport.getId() + ".jsp";
			savePath = path;
			path = ServletActionContext.getRequest().getRealPath(path);
			File file = new File(path);
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				bufferedWriter.write(labReportVo.getFile());
				bufferedWriter.close();
			} catch (FileNotFoundException e1) {
				Log4J.err("LabReportServiceImpl.." + e1.getMessage());
				throw new GlobalException("" + e1.getMessage());
			} catch (IOException e) {
				Log4J.err("LabReportServiceImpl.." + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		savePath = savePath.replace("\\", "/");
		labReport.setPath(savePath);
		labReportDAO.updateLabReport(labReport);
		labReportVo.setType(type);
		return labReportVo;
	}

}
