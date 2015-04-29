package cn.labsoft.labos.common.page.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import cn.labsoft.labos.common.page.dao.ILabPageEditorDAO;
import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.common.page.service.ILabPageEditorService;
import cn.labsoft.labos.common.page.vo.LabPageEditorVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageBean;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;

/**
 * 页面管理Service实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Service("labPageEditorService")
public class LabPageEditorServiceImpl extends BaseService implements ILabPageEditorService {

	private ILabPageEditorDAO labPageEditorDAO;

	/**
	 * 页面管理DAO注入
	 * 
	 * @param labPageEditorDAO
	 */
	@Resource
	public void setLabPageEditorDAO(ILabPageEditorDAO labPageEditorDAO) {
		this.labPageEditorDAO = labPageEditorDAO;
	}

	/**
	 * 分页查询WebRoot路径下的文件
	 * 
	 * @param labPageEditorVo
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	@Override
	public PageResult getLabPageEditorPR(LabPageEditorVo labPageEditorVo, PageResult pageResult) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabPageEditor WHERE 1=1 AND isDel = '" + Constants_Base.N + "'");
		if (null != labPageEditorVo.getName() && !"".equals(labPageEditorVo.getName())) {
			hql.append(" AND name like '%" + labPageEditorVo.getName() + "%'");
		}
		if (null != labPageEditorVo.getRemark() && !"".equals(labPageEditorVo.getRemark())) {
			hql.append(" AND remark like '%" + labPageEditorVo.getRemark() + "%'");
		}
		if (null != labPageEditorVo.getFileName() && !"".equals(labPageEditorVo.getFileName())) {
			hql.append(" AND url like '%" + labPageEditorVo.getFileName() + "%'");
		}
		pageResult = labPageEditorDAO.getLabPageEditorPR(hql.toString(), pageResult);
		List<LabPageEditorVo> voList = new ArrayList<LabPageEditorVo>();
		List<LabPageEditor> poList = pageResult.getResultList();
		if (null != poList && poList.size() > 0) {
			for (LabPageEditor po : poList) {
				LabPageEditorVo vo = new LabPageEditorVo();
				BeanUtils.copyProperties(po, vo, new String[] {});
				if (null != po.getUrl() && !"".equals(po.getUrl())) {
					File file = new File((ServletActionContext.getRequest().getRealPath("/") + po.getUrl()).replace("/", File.separator));
					if (file.exists()) {
						vo.setFileName(file.getName());
					}
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	/**
	 * 查询页面信息
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public LabPageEditorVo getLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException {
		LabPageEditor labPageEditor = (LabPageEditor) labPageEditorDAO.findById(LabPageEditor.class, labPageEditorVo.getId());
		BeanUtils.copyProperties(labPageEditor, labPageEditorVo, new String[] {});

		String path = (ServletActionContext.getRequest().getRealPath("/") + labPageEditor.getUrl()).replace("/", File.separator);
		File file = new File(path);
		if (file.exists()) {
			labPageEditorVo.setFileName(file.getName());
			if (null != labPageEditorVo.getObjName() && labPageEditorVo.getObjName().length() > 0) {
				String temp = labPageEditorVo.getObjName();
				temp = temp.substring(0, 1).toLowerCase() + temp.substring(1, temp.length());
				labPageEditorVo.setObjName(temp);
			}
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line + "\n");
				}
				labPageEditorVo.setFile(replaceStr(sb.toString()));
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labPageEditorVo;
	}

	/**
	 * 处理字符串中的单引号、双引号、尖括号、成为转义符，除过JS之外
	 * 
	 * @param temp
	 * @return String
	 */
	private String replaceStr(String temp) {
		//<script></script>中不进行转义
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
				str2 = str2 + temp.replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;").replace("\"", "&quot;");
				break;
			}
		}
		//${},el表达式中不进行转义
		String str4 = "";
		String str5 = "";
		String str6 = "";
		while (str2.length() > 0) {
			if (str2.indexOf("${") != -1) {
				str4 = str2.substring(str2.indexOf("${"), str2.indexOf("}", str2.indexOf("${")) + 1);
				str5 = str2.substring(0, str2.indexOf(str4));
				str6 = str6 + str5 + str4.replace("&lt;", "<").replace("&gt;", ">").replace("&apos;", "'");
				str2 = str2.substring(str2.indexOf(str4) + str4.length(), str2.length());
			} else {
				str6 = str6 + str2;
				break;
			}
		}
		return str6;
	}

	/**
	 * 修改页面信息
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@Override
	public LabPageEditorVo updateLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException {
		LabPageEditor labPageEditor = (LabPageEditor) labPageEditorDAO.findById(LabPageEditor.class, labPageEditorVo.getId());
		BeanUtils.copyProperties(labPageEditorVo, labPageEditor, new String[] { "isDel" });
		labPageEditorDAO.updateLabPageEditor(labPageEditor);
		return labPageEditorVo;
	}

	/**
	 * 新增页面信息
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@Override
	public LabPageEditorVo addLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException {
		LabPageEditor labPageEditor = new LabPageEditor();
		BeanUtils.copyProperties(labPageEditorVo, labPageEditor, new String[] {});
		labPageEditor.setIsDel(Constants_Base.N);
		labPageEditor.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMdd));
		labPageEditorDAO.addLabPageEditor(labPageEditor);
		return labPageEditorVo;
	}

	/**
	 * 修改页面内容
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public LabPageEditorVo updateLabPageEdit(LabPageEditorVo labPageEditorVo) throws GlobalException {
		LabPageEditor labPageEditor = (LabPageEditor) labPageEditorDAO.findById(LabPageEditor.class, labPageEditorVo.getId());
		String path = (ServletActionContext.getRequest().getRealPath("/") + labPageEditor.getUrl()).replace("/", File.separator);
		File file = new File(path);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			bufferedWriter.write(labPageEditorVo.getFile());
			bufferedWriter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new GlobalException("" + e1.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return labPageEditorVo;
	}

	/**
	 * 删除页面对象信息
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@Override
	public LabPageEditorVo updateLabPage4Del(LabPageEditorVo labPageEditorVo) throws GlobalException {
		if (null != labPageEditorVo.getIds() && labPageEditorVo.getIds().length > 0) {
			for (String id : labPageEditorVo.getIds()) {
				LabPageEditor labPageEditor = (LabPageEditor) labPageEditorDAO.findById(LabPageEditor.class, id.trim());
				labPageEditor.setIsDel(Constants_Base.Y);
				labPageEditorDAO.updateLabPageEditor(labPageEditor);
			}
		}
		return labPageEditorVo;
	}

	/**
	 * 公式编辑器接口方法：修改页面内容(插入一段JS)
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public LabPageEditorVo updateLabPage4Formula(LabPageEditorVo labPageEditorVo) throws GlobalException {
		LabPageEditor labPageEditor = (LabPageEditor) labPageEditorDAO.findById(LabPageEditor.class, labPageEditorVo.getId());
		String path = (ServletActionContext.getRequest().getRealPath("/") + labPageEditor.getUrl()).replace("/", File.separator);
		File file = new File(path);

		if (file.exists()) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line + "\n");
				}
				labPageEditorVo.setFile(replaceStr(sb.toString()));
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			String temp = labPageEditorVo.getFile();
			String str = temp.substring(0, temp.indexOf("<head>") + "<head>".length());
			String str1 = temp.substring(temp.indexOf("<head>") + "<head>".length(), temp.length());
			bufferedWriter.write(str + "" + str1);
			bufferedWriter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new GlobalException("" + e1.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return labPageEditorVo;
	}

	/**
	 * 查询JSP页面分页
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getFilePagePR(LabPageEditorVo labPageEditorVo, PageResult pageResult) throws GlobalException {
		List<LabPageEditorVo> voList = new ArrayList<LabPageEditorVo>();
		if (null != labPageEditorVo.getParentUrl() && !"".equals(labPageEditorVo.getParentUrl())) {
			File rootFile = new File(labPageEditorVo.getParentUrl().replace("/", File.separator).replace("@", ":"));
			if (rootFile.exists()) {
				File fileList[] = rootFile.listFiles();
				if (null != fileList && fileList.length > 0) {
					for (File file : fileList) {
						if (file.exists()) {
							if (file.isDirectory() && ".svn".equals(file.getName()))
								continue;
							LabPageEditorVo vo = new LabPageEditorVo();
							vo.setUrl(file.getPath().replace(":", "@").replace("\\", "/"));
							vo.setFileName(file.getName());
							vo.setShowUrl(file.getPath());
							vo.setType(file.isDirectory() ? "1" : "0");
							if (null != labPageEditorVo.getFileName() && !"".equals(labPageEditorVo.getFileName())) {
								if (file.getName().indexOf(labPageEditorVo.getFileName()) != -1) {
									voList.add(vo);
								} else {
									continue;
								}
							} else {
								voList.add(vo);
							}
						}
					}
				}
			}
		}
		PageBean pager = new PageBean(voList.size(), pageResult.getPageSize());
		if (pageResult.getCurrentPage() != null && !"".equals(pageResult.getCurrentPage()) && !"null".equals(pageResult.getCurrentPage())) {
			try {
				pager.refresh(Integer.parseInt(pageResult.getCurrentPage()));
			} catch (Exception e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		if (pageResult.getPagerMethod() != null && !"".equals(pageResult.getPagerMethod()) && !"null".equals(pageResult.getPagerMethod())) {
			if (pageResult.getPagerMethod().equals("first")) {
				pager.first();
			} else if (pageResult.getPagerMethod().equals("previous")) {
				pager.previous();
			} else if (pageResult.getPagerMethod().equals("next")) {
				pager.next();
			} else if (pageResult.getPagerMethod().equals("last")) {
				pager.last();
			}
		}
		// 设置跳码
		pager.jumpPage();
		List result = new ArrayList();
		int i = 0;
		int size = 0;
		if (null != voList && voList.size() > 0) {
			size = pager.getPageSize() * pager.getCurrentPage();
			if (voList.size() < size) {
				size = voList.size();
			}
			for (i = pager.getStartRow(); i < size; i++) {
				result.add(voList.get(i));
			}
		}
		List columnList = new ArrayList();
		pageResult.setPageBean(pager);
		pageResult.setColumnList(columnList);
		pageResult.setResultList(result);
		return pageResult;
	}

	/**
	 * 递归得到目录下的文件
	 * 
	 * @param directory
	 * @param list
	 * @return List
	 */
	@SuppressWarnings("unused")
	private static List<File> readFiles(File directory, List<File> list) {
		if (list == null) {
			list = new ArrayList<File>();
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory() && file.getName().equals(".svn"))
				continue;
			if (!file.isDirectory()) {
				list.add(file);
			} else {
				readFiles(file, list);
			}
		}
		return list;
	}

	/**
	 * 批量新增页面信息
	 * 
	 * @param labPageEditorVo
	 * @return LabPageEditorVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public LabPageEditorVo addBatchLabPageEditor(LabPageEditorVo labPageEditorVo) throws GlobalException {
		List<LabPageEditor> list = labPageEditorDAO.getLabPageEditorList("FROM LabPageEditor WHERE 1=1 AND isDel = '" + Constants_Base.N + "'");
		if (null != labPageEditorVo.getUrls() && labPageEditorVo.getUrls().length > 0) {
			int length = labPageEditorVo.getUrls().length;
			for (int i = 0; i < length; i++) {
				String temp = labPageEditorVo.getUrls()[i].trim().replace("@", ":").replace("/", File.separator);
				temp = temp.substring(temp.indexOf(File.separator + "jsp" + File.separator), temp.length());
				File file = new File(ServletActionContext.getRequest().getRealPath("/") + temp);
				if (file.isDirectory() && file.getName().equals(".svn"))
					continue;
				if (file.isFile() && !file.isDirectory()) {
					if (!isHaveSameFile(file, list)) {
						LabPageEditor labPageEditor = new LabPageEditor();
						labPageEditor.setUrl(temp.replace(File.separator, "/"));
						labPageEditor.setIsDel(Constants_Base.N);
						labPageEditor.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMdd));
						labPageEditorDAO.addLabPageEditor(labPageEditor);
					}
				} else {
					List<File> listFile = readFiles(file, null);
					if (null != listFile && listFile.size() > 0) {
						for (File file2 : listFile) {
							if (!isHaveSameFile(file2, list)) {
								LabPageEditor labPageEditor = new LabPageEditor();
								labPageEditor.setUrl(file2.getPath().substring(file2.getPath().indexOf(File.separator + "jsp" + File.separator), file2.getPath().length()).replace(File.separator, "/"));
								labPageEditor.setIsDel(Constants_Base.N);
								labPageEditor.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMdd));
								labPageEditorDAO.addLabPageEditor(labPageEditor);
							}
						}
					}
				}
			}
		}

		return labPageEditorVo;
	}

	/**
	 * 判断list中的文件是否和当前文件有重复
	 * 
	 * @param file
	 * @param list
	 * @return boolean
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	private boolean isHaveSameFile(File file, List<LabPageEditor> list) throws GlobalException {
		boolean flag = false;
		if (null != list && list.size() > 0) {
			for (LabPageEditor labPageEditor : list) {
				File thisFile = new File(ServletActionContext.getRequest().getRealPath("/") + File.separator + labPageEditor.getUrl().replace("/", File.separator));
				if (thisFile.exists() && thisFile.getPath().equals(file.getPath())) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 获得VO对象注解的信息
	 * 
	 * @param labPageEditorVo
	 * @return StringBuffer
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public StringBuffer getAnnotationInfo(LabPageEditorVo labPageEditorVo) throws GlobalException {
		Map<String, String> checkboxList = null;
		Class c = null;
		try {
			c = Class.forName(labPageEditorVo.getObjUrl());
			c.newInstance();
			checkboxList = ExportExcel.getAnnotationInfo(c);
		} catch (InstantiationException e) {
			Log4J.error(labPageEditorVo.getObjUrl() + "初始化失败，类名称错误...InstantiationException");
			throw new GlobalException("" + e.getMessage());
		} catch (IllegalAccessException e) {
			Log4J.error(labPageEditorVo.getObjUrl() + "初始化失败，类名称错误...IllegalAccessException");
			throw new GlobalException("" + e.getMessage());
		} catch (ClassNotFoundException e) {
			Log4J.error(labPageEditorVo.getObjUrl() + "初始化失败，类名称错误...ClassNotFoundException");
			throw new GlobalException("" + e.getMessage());
		}

		String obj = "";
		try {
			obj = c.getSimpleName();
		} catch (Exception e) {
			Log4J.error(labPageEditorVo.getObjUrl() + "初始化失败，类名称错误...InvocationTargetException");
		}
		if (obj.length() > 1) {
			obj = obj.substring(0, 1).toLowerCase() + obj.substring(1, obj.length());
		}
		StringBuffer sb = new StringBuffer("[");
		if (null != checkboxList && checkboxList.size() > 0) {
			for (Map.Entry<String, String> map : checkboxList.entrySet()) {
				if (map.getKey().endsWith("Vo")) {
					try {
						Field field = c.getDeclaredField(map.getKey());
						Class subclass = field.getType();
						String subPath = field.getType().getName();
						if (subPath.endsWith("Vo") && subPath.startsWith("cn.")) {
							Map<String, String> checkboxList1 = ExportExcel.getAnnotationInfo(subclass);
							if (null != checkboxList1 && checkboxList1.size() > 0) {
								sb.append("{'id':'','name':'---" + map.getKey() + "对象'},");
								for (Map.Entry<String, String> map1 : checkboxList1.entrySet()) {
									sb.append("{'id':'" + obj + "." + map.getKey() + "." + map1.getKey() + "','name':'---" + map1.getKey() + "：" + map1.getValue() + "'},");
								}
							}
						}
					} catch (SecurityException e) {
						Log4J.err("LabReportServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					} catch (NoSuchFieldException e) {
						Log4J.err("LabReportServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					}
				} else if (map.getKey().toUpperCase().endsWith("LIST") || map.getKey().toUpperCase().startsWith("LIST")) {
					try {
						Field field = c.getDeclaredField(map.getKey());
						ParameterizedTypeImpl impl = (ParameterizedTypeImpl) field.getGenericType();
						Type[] a = impl.getActualTypeArguments();
						Object objx = a[0];
						Class subclass = (Class<T>) objx;
						String subPath = subclass.getName();
						if (subPath.endsWith("Vo") && subPath.startsWith("cn.")) {
							Map<String, String> checkboxList1 = ExportExcel.getAnnotationInfo(subclass);
							if (null != checkboxList1 && checkboxList1.size() > 0) {
								sb.append("{'id':'','name':'---" + map.getKey() + "集合'},");
								for (Map.Entry<String, String> map1 : checkboxList1.entrySet()) {
									sb.append("{'id':'" + map1.getKey() + "','name':'---" + map1.getKey() + "：" + map1.getValue() + "'},");
								}
							}
						}
					} catch (SecurityException e) {
						Log4J.err("LabReportServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					} catch (NoSuchFieldException e) {
						Log4J.err("LabReportServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					}
				} else {
					sb.append("{'id':'" + obj + "." + map.getKey() + "','name':'" + map.getKey() + "：" + map.getValue() + "'},");
				}
			}
		}
		String result = sb.toString();
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		return new StringBuffer(result + "]");
	}
}