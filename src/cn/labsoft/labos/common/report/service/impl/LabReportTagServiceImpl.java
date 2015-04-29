package cn.labsoft.labos.common.report.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.common.page.dao.ILabPageEditorDAO;
import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.common.report.dao.ILabReportDAO;
import cn.labsoft.labos.common.report.dao.ILabReportTagDAO;
import cn.labsoft.labos.common.report.entity.LabReport;
import cn.labsoft.labos.common.report.entity.LabReportTag;
import cn.labsoft.labos.common.report.service.ILabReportTagService;
import cn.labsoft.labos.common.report.vo.LabReportTagVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.interceptor.LogCommonInformation;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;

@Service("labReportTagService")
public class LabReportTagServiceImpl implements ILabReportTagService {
	private ILabReportTagDAO labReportTagDAO;
	private ILabReportDAO labReportDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabPageEditorDAO labPageEditorDAO;

	
	@Resource
	public void setLabReportTagDAO(ILabReportTagDAO labReportTagDAO) {
		this.labReportTagDAO = labReportTagDAO;
	}
	@Resource
	public void setLabReportDAO(ILabReportDAO labReportDAO) {
		this.labReportDAO = labReportDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabPageEditorDAO(ILabPageEditorDAO labPageEditorDAO) {
		this.labPageEditorDAO = labPageEditorDAO;
	}

	@Override
	public LabReportTagVo addLabReportTag(LabReportTagVo labReportTagVo) throws GlobalException {
		LabReportTag labReportTag = new LabReportTag();
		labReportTag = this.vo2Po(labReportTagVo, labReportTag);
		LabReport report = null;
		if (!StrUtils.isBlankOrNull(labReportTagVo.getLabReportId())) {
			report = labReportDAO.getLabReport(labReportTagVo.getLabReportId());
			labReportTag.setLabReport(report);
		}
		labReportTagDAO.addLabReportTag(labReportTag);
		List<LabReportTagVo> contentList = labReportTagVo.getContentList();
		if (contentList != null) {
			for (LabReportTagVo subTagVo : contentList) {
				if (subTagVo == null || StrUtils.isBlankOrNull(subTagVo.getTitle()) || StrUtils.isBlankOrNull(subTagVo.getCode()))
					continue;
				LabReportTag subTag = new LabReportTag();
				subTag.setLabReport(report);
				subTag.setParentTag(labReportTag);
				subTag.setTitle(subTagVo.getTitle());
				subTag.setCode(subTagVo.getCode());
				subTag.setValue(subTagVo.getValue());
				subTag.setIndex(subTagVo.getIndex());
				subTag.setType(subTagVo.getType());
				labReportTagDAO.addLabReportTag(subTag);
				List<LabReportTagVo> twoContentList = subTagVo.getContentList();
				if (twoContentList != null) {
					for (LabReportTagVo twoTagVo : twoContentList) {
						if (twoTagVo == null || StrUtils.isBlankOrNull(twoTagVo.getTitle()) || StrUtils.isBlankOrNull(twoTagVo.getCode()))
							continue;
						LabReportTag twoTag = new LabReportTag();
						twoTag.setLabReport(report);
						twoTag.setParentTag(subTag);
						twoTag.setTitle(twoTagVo.getTitle());
						twoTag.setCode(twoTagVo.getCode());
						twoTag.setValue(twoTagVo.getValue());
						twoTag.setIndex(twoTagVo.getIndex());
						twoTag.setType(twoTagVo.getType());
						labReportTagDAO.addLabReportTag(twoTag);
					}
				}
			}
		}
		return labReportTagVo;
	}

	@Override
	public boolean deleteLabReportTag(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				for (String pid : ids) {
					String hql = " FROM LabReportTag WHERE 1=1 AND (parentTag.id='" + pid + "' or parentTag.parentTag.id='" + pid + "')";
					List<LabReportTag> subList = labReportTagDAO.getLabReportTagList(hql);
					if (subList != null) {
						for (LabReportTag subTag : subList) {
							labReportTagDAO.deleteLabReportTag(subTag);
						}
					}
				}
				key = labReportTagDAO.deleteLabReportTag(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabReportTagServiceImpl deleteLabReportTag  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabReportTagVo getLabReportTag(String id) throws GlobalException {
		LabReportTagVo labReportTagVo = new LabReportTagVo();
		if (!StrUtils.isBlankOrNull(id)) {
			LabReportTag labReportTag = labReportTagDAO.getLabReportTag(id);
			labReportTagVo = this.po2Vo(labReportTag, labReportTagVo);
			if (labReportTag.getLabReport() != null) {
				labReportTagVo.setLabReportId(labReportTag.getLabReport().getId());
				labReportTagVo.setLabReportTitle(labReportTag.getLabReport().getTitle());
			}
			//第一层循环
			String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "'";
			hql += " AND parentTag.id='" + id + "'";
			hql+=" ORDER BY index ASC";
			List<LabReportTag> subList = labReportTagDAO.find(hql);
			List<LabReportTagVo> subVoList = new ArrayList<LabReportTagVo>();
			if (subList != null) {
				for (LabReportTag subTag : subList) {
					LabReportTagVo subTagVo = new LabReportTagVo();
					BeanUtils.copyProperties(subTag, subTagVo, new String[] {});
					//第二层循环
					String twohql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "'";
					twohql += " AND parentTag.id='" + subTag.getId() + "'";
					twohql+=" ORDER BY index ASC";
					List<LabReportTag> twoList = labReportTagDAO.find(twohql);
					List<LabReportTagVo> twoVoList = new ArrayList<LabReportTagVo>();
					if (subList != null) {
						for (LabReportTag twoTag : twoList) {
							LabReportTagVo twoTagVo = new LabReportTagVo();
							BeanUtils.copyProperties(twoTag, twoTagVo, new String[] {});
							twoVoList.add(twoTagVo);
						}
					}
					subTagVo.setContentList(twoVoList);
					subVoList.add(subTagVo);
				}
			}
			labReportTagVo.setContentList(subVoList);
		}
		return labReportTagVo;
	}

	@Override
	public List<LabReportTagVo> getLabReportTagList(LabReportTagVo labReportTagVo) throws GlobalException {
		String wereHql = "";

		return this.getLabReportTagVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabReportTagPR(LabReportTagVo labReportTagVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "'";
		hql += " AND parentTag.id is null";
		if (!StrUtils.isBlankOrNull(labReportTagVo.getLabReportId())) {
			hql += " AND labReport.id='" + labReportTagVo.getLabReportId() + "'";
		}
		pageResult = labReportTagDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabReportTagVo> listLabReportTagVo = new ArrayList<LabReportTagVo>();
			List<LabReportTag> listLabReportTag = new ArrayList<LabReportTag>();
			listLabReportTag = pageResult.getResultList();
			for (LabReportTag labReportTag : listLabReportTag) {
				LabReportTagVo vo = new LabReportTagVo();
				vo = this.po2Vo(labReportTag, vo);
				listLabReportTagVo.add(vo);
			}
			pageResult.setResultList(listLabReportTagVo);
		}
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabReportTag(LabReportTagVo labReportTagVo) throws GlobalException {
		LabReportTag labReportTag = new LabReportTag();
		boolean key = true;
		labReportTag = this.vo2Po(labReportTagVo, labReportTag);
		LabReport report = null;
		if (!StrUtils.isBlankOrNull(labReportTagVo.getLabReportId())) {
			report = labReportDAO.getLabReport(labReportTagVo.getLabReportId());
			labReportTag.setLabReport(report);
		}
		labReportTagDAO.updateLabReportTag(labReportTag);
		String hql = "FROM LabReportTag WHERE 1=1";
		hql += " AND labReport.id='" + report.getId() + "'";
		hql += " AND parentTag.id='"+labReportTag.getId()+"'";
		List<LabReportTag> subList = labReportTagDAO.find(hql);
		if (subList != null) {
			for (LabReportTag subTag : subList) {
				String hqlx = "FROM LabReportTag WHERE 1=1";
				hqlx += " AND labReport.id='" + report.getId() + "'";
				hqlx += " AND parentTag.id='"+subTag.getId()+"'";
				List<LabReportTag> sub2List = labReportTagDAO.find(hqlx);
				if (sub2List != null) {
					for (LabReportTag sub2Tag : sub2List) {
						labReportTagDAO.deleteLabReportTag(sub2Tag);
					}
				}
				labReportTagDAO.deleteLabReportTag(subTag);
			}
		}
		//第一层标签
		List<LabReportTagVo> contentList = labReportTagVo.getContentList();
		if (contentList != null) {
			for (LabReportTagVo subTagVo : contentList) {
				if (subTagVo == null || StrUtils.isBlankOrNull(subTagVo.getTitle()) || StrUtils.isBlankOrNull(subTagVo.getCode()))
					continue;
				LabReportTag subTag = new LabReportTag();
				subTag.setLabReport(report);
				subTag.setParentTag(labReportTag);
				subTag.setTitle(subTagVo.getTitle());
				subTag.setCode(subTagVo.getCode());
				subTag.setValue(subTagVo.getValue());
				subTag.setIndex(subTagVo.getIndex());
				subTag.setType(subTagVo.getType());
				labReportTagDAO.addLabReportTag(subTag);
				//第二层标签
				List<LabReportTagVo> twoContentList = subTagVo.getContentList();
				if (twoContentList != null) {
					for (LabReportTagVo twoTagVo : twoContentList) {
						if (twoTagVo == null || StrUtils.isBlankOrNull(twoTagVo.getTitle()) || StrUtils.isBlankOrNull(twoTagVo.getCode()))
							continue;
						LabReportTag twoTag = new LabReportTag();
						twoTag.setLabReport(report);
						twoTag.setParentTag(subTag);
						twoTag.setTitle(twoTagVo.getTitle());
						twoTag.setCode(twoTagVo.getCode());
						twoTag.setValue(twoTagVo.getValue());
						twoTag.setIndex(twoTagVo.getIndex());
						twoTag.setType(twoTagVo.getType());
						labReportTagDAO.addLabReportTag(twoTag);
					}
				}
			}
		}
		return key;
	}

	@Override
	public boolean updateLabReportTag4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabReportTag labReportTag = labReportTagDAO.getLabReportTag(id);
					labReportTag.setIsDel(Constants_Base.Y);
					labReportTagDAO.updateLabReportTag(labReportTag);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error("LabReportTagServiceImpl updateLabReportTag4Del  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<LabReportTagVo> getLabReportTagVoListByWhere(String wereHql) throws GlobalException {
		List<LabReportTagVo> listLabReportTagVo = new ArrayList<LabReportTagVo>();
		String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabReportTag> listLabReportTag = labReportTagDAO.find(hql);
		if (listLabReportTag != null && listLabReportTag.size() > 0) {
			for (LabReportTag labReportTag : listLabReportTag) {
				LabReportTagVo labReportTagVo = new LabReportTagVo();
				labReportTagVo = this.po2Vo(labReportTag, labReportTagVo);
				listLabReportTagVo.add(labReportTagVo);
			}
		}
		return listLabReportTagVo;
	}

	public LabReportTag vo2Po(LabReportTagVo labReportTagVo, LabReportTag labReportTag) {
		BeanUtils.copyProperties(labReportTagVo, labReportTag, new String[] { "isDel", "createTime" });
		return labReportTag;
	}

	public LabReportTagVo po2Vo(LabReportTag labReportTag, LabReportTagVo labReportTagVo) {
		BeanUtils.copyProperties(labReportTag, labReportTagVo);
		return labReportTagVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getLabReportTagIndex(String LabReportId) throws GlobalException {
		String sql = "SELECT count(*) FROM lab_report_tag WHERE report_id='" + LabReportId + "'";
		List<String> objList = labReportTagDAO.createSqlQuery(sql);
		Integer index;
		try {
			if (objList != null) {
				index = Integer.valueOf(String.valueOf(objList.get(0)));
			} else {
				index = 0;
			}
		} catch (NumberFormatException e) {
			index = 0;
			Log4J.error("LabReportTagServiceImpl...", e);
			throw new GlobalException("" + e.getMessage());
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabReportTagVo> getLabReportTagListByReportId(String reportId) throws GlobalException {
		List<LabReportTagVo> labReportTagList = new ArrayList<LabReportTagVo>();
		String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "' ";
		hql += " AND labReport.id='" + reportId + "'";
		hql += " AND parentTag.id is null";
		hql += " ORDER BY index asc";
		List<LabReportTag> listLabReportTag = labReportTagDAO.find(hql);
		if (listLabReportTag != null && listLabReportTag.size() > 0) {
			for (LabReportTag labReportTag : listLabReportTag) {
				LabReportTagVo labReportTagVo = new LabReportTagVo();
				labReportTagVo = this.po2Vo(labReportTag, labReportTagVo);
				labReportTagList.add(labReportTagVo);
				if (null != labReportTag.getType() && labReportTag.getType().equals("for-each")) {
					//第一层循环
					String onehql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "'";
					onehql += " AND parentTag.id='" + labReportTag.getId() + "'";
					onehql += " ORDER BY index asc";
					List<LabReportTag> subList = labReportTagDAO.find(onehql);
					List<LabReportTagVo> subVoList = new ArrayList<LabReportTagVo>();
					if (subList != null) {
						for (LabReportTag subTag : subList) {
							LabReportTagVo subTagVo = new LabReportTagVo();
							BeanUtils.copyProperties(subTag, subTagVo, new String[] {});
							subTagVo.setTitle("--" + subTagVo.getTitle());
							labReportTagList.add(subTagVo);
							//第二层循环
							String twohql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "'";
							twohql += " AND parentTag.id='" + subTag.getId() + "'";
							twohql += " ORDER BY index asc";
							List<LabReportTag> twoList = labReportTagDAO.find(twohql);
							List<LabReportTagVo> twoVoList = new ArrayList<LabReportTagVo>();
							if (subList != null) {
								for (LabReportTag twoTag : twoList) {
									LabReportTagVo twoTagVo = new LabReportTagVo();
									BeanUtils.copyProperties(twoTag, twoTagVo, new String[] {});
									twoVoList.add(twoTagVo);
									twoTagVo.setTitle("----" + twoTagVo.getTitle());
									labReportTagList.add(twoTagVo);
								}
							}
							subTagVo.setContentList(twoVoList);
							subVoList.add(subTagVo);

						}
					}
					labReportTagVo.setContentList(subVoList);
				}
			}
		}
		return labReportTagList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabReportTag4Init(String reportId) throws GlobalException {
		try {
			LabReport report = labReportDAO.getLabReport(reportId);
			String delhql = " FROM LabReportTag WHERE 1=1 AND labReport.id='" + report.getId() + "' ";
			List<LabReportTag> subList = labReportTagDAO.getLabReportTagList(delhql);
			if (subList != null) {
				for (LabReportTag subTag : subList) {
					labReportTagDAO.deleteLabReportTag(subTag);
				}
			}
			LabFunction fun = labFunctionDAO.getLabFunction(report.getBusId());
			String url = fun.getUrl();
			if (url.contains("/") && url.contains(".")) {
				//获取对象Vo名称
				String entity = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));
				String[] strList = LogCommonInformation.LIST_PAGE_ACTION;
				for (String str : strList) {
					entity = entity.replace(str, "");
					if (entity.contains("4")) {
						entity = entity.substring(0, entity.indexOf("4"));
					}
					if (entity.contains("2")) {
						entity = entity.substring(0, entity.indexOf("2"));
					}
				}
				entity = entity.replace("Lab", "lab") + "Vo";
				//获取对象的路径
				String hql = "FROM LabPageEditor WHERE objName like '" + entity + "' GROUP BY objName";
				LabPageEditor pe = (LabPageEditor) labPageEditorDAO.find(hql, 0);
				if (pe != null) {
					String classPath = pe.getObjUrl();
					Map<String, String> checkboxList = null;
					Class c = null;
					try {
						c = Class.forName(classPath);
						c.newInstance();
						checkboxList = ExportExcel.getAnnotationInfo(c);
					} catch (ClassNotFoundException e) {
						Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					} catch (InstantiationException e) {
						Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					} catch (IllegalAccessException e) {
						Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					}
					if (null != checkboxList && checkboxList.size() > 0) {
						int index = 0;
						for (Map.Entry<String, String> map : checkboxList.entrySet()) {
							LabReportTag data = new LabReportTag();
							data.setTitle(map.getValue());
							data.setIndex(index + "");
							data.setCode("${" + entity + "." + map.getKey() + "}");
							data.setLabReport(report);
							if (map.getKey().endsWith("Vo")) {
								try {
									Field field = c.getDeclaredField(map.getKey());
									Class subclass = field.getType();
									String subPath = field.getType().getName();
									if (subPath.endsWith("Vo") && subPath.startsWith("cn.")) {
										Map<String, String> checkboxList1 = ExportExcel.getAnnotationInfo(subclass);
										if (null != checkboxList1 && checkboxList1.size() > 0) {
											for (Map.Entry<String, String> map1 : checkboxList1.entrySet()) {
												LabReportTag data1 = new LabReportTag();
												data1.setTitle(map1.getValue());
												data1.setCode("${" + entity + "." + map.getKey() + "." + map1.getKey() + "}");
												data1.setIndex(index + "");
												data1.setLabReport(report);
												data1.setType("value-of");
												labReportTagDAO.addLabReportTag(data1);
												index++;
											}
										}
									}
								} catch (SecurityException e) {
									Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
									throw new GlobalException("" + e.getMessage());
								} catch (NoSuchFieldException e) {
									Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
									throw new GlobalException("" + e.getMessage());
								}
							} else if (map.getKey().toUpperCase().endsWith("LIST") || map.getKey().toUpperCase().startsWith("LIST")) {
								data.setType("for-each");
								labReportTagDAO.addLabReportTag(data);
								index++;
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
											int index1 = 0;
											for (Map.Entry<String, String> map1 : checkboxList1.entrySet()) {
												LabReportTag data1 = new LabReportTag();
												data1.setTitle(map1.getValue());
												data1.setCode("${" + map1.getKey() + "}");
												data1.setIndex(index1 + "");
												data1.setLabReport(report);
												data1.setParentTag(data);
												//第一层循环里面继续处理
												if (map1.getKey().endsWith("Vo")) {
													try {
														Field field1 = c.getDeclaredField(map1.getKey());
														Class subclass1 = field1.getType();
														String subPath1 = field1.getType().getName();
														if (subPath1.endsWith("Vo") && subPath1.startsWith("cn.")) {
															Map<String, String> checkboxList2 = ExportExcel.getAnnotationInfo(subclass1);
															if (null != checkboxList2 && checkboxList2.size() > 0) {
																for (Map.Entry<String, String> map2 : checkboxList2.entrySet()) {
																	LabReportTag data2 = new LabReportTag();
																	data2.setTitle(map2.getValue());
																	data2.setCode("${" + map1.getKey() + "." + map2.getKey() + "}");
																	data2.setIndex(index1 + "");
																	data2.setLabReport(report);
																	data2.setParentTag(data);
																	data2.setType("value-of");
																	labReportTagDAO.addLabReportTag(data2);
																	index1++;
																}
															}
														}
													} catch (SecurityException e) {
														Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
														throw new GlobalException("" + e.getMessage());
													} catch (NoSuchFieldException e) {
														Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
														throw new GlobalException("" + e.getMessage());
													}
												} else if (map1.getKey().toUpperCase().endsWith("LIST") || map1.getKey().toUpperCase().startsWith("LIST")) {
													data1.setType("for-each");
													labReportTagDAO.addLabReportTag(data1);
													index1++;
													try {
														Field field1 = c.getDeclaredField(map1.getKey());
														ParameterizedTypeImpl impl1 = (ParameterizedTypeImpl) field1.getGenericType();
														Type[] a1 = impl1.getActualTypeArguments();
														Object objx1 = a1[0];
														Class subclass1 = (Class<T>) objx1;
														String subPath1 = subclass1.getName();
														if (subPath1.endsWith("Vo") && subPath1.startsWith("cn.")) {
															Map<String, String> checkboxList2 = ExportExcel.getAnnotationInfo(subclass1);
															if (null != checkboxList2 && checkboxList2.size() > 0) {
																int index2 = 0;
																for (Map.Entry<String, String> map2 : checkboxList2.entrySet()) {
																	if (!map1.getKey().endsWith("Vo") && !(map.getKey().toUpperCase().endsWith("LIST") || map.getKey().toUpperCase().startsWith("LIST"))) {
																		LabReportTag data2 = new LabReportTag();
																		data2.setTitle(map2.getValue());
																		data2.setCode("${" + map2.getKey() + "}");
																		data2.setIndex(index2 + "");
																		data2.setLabReport(report);
																		data2.setParentTag(data1);
																		data2.setType("value-of");
																		labReportTagDAO.addLabReportTag(data2);
																		index2++;
																	}
																}
															}
														}
													} catch (SecurityException e) {
														Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
														throw new GlobalException("" + e.getMessage());
													} catch (NoSuchFieldException e) {
														Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
														throw new GlobalException("" + e.getMessage());
													}
												} else {
													data1.setType("value-of");
													labReportTagDAO.addLabReportTag(data1);
													index1++;
												}
											}
										}
									}
								} catch (SecurityException e) {
									Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
									throw new GlobalException("" + e.getMessage());
								} catch (NoSuchFieldException e) {
									Log4J.err("LabReportTagServiceImpl..." + e.getMessage());
									throw new GlobalException("" + e.getMessage());
								}
							} else {
								data.setType("value-of");
								labReportTagDAO.addLabReportTag(data);
								index++;
							}
						}
					}
				}
			}
		} catch (RuntimeException e) {
			Log4J.error("LabReportTagServiceImpl" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLabReportTag4JsonByReportId(String reportId,String tagType)
			throws GlobalException {
		StringBuffer sb = new StringBuffer("[");
		String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "' ";
		hql+=" AND labReport.id='"+reportId+"'";
		if(tagType!=null&&tagType.trim().length()>0){
			hql+=" AND type='"+tagType+"'";
		}
		hql += " AND parentTag.id is null";
		hql+=" ORDER BY index ASC";
		List<LabReportTag> listLabReportTag = labReportTagDAO.find(hql);
		if (listLabReportTag != null && listLabReportTag.size() > 0) {
			for (LabReportTag labReportTag : listLabReportTag) {
				if(labReportTag.getType().contains("value")){
					sb.append("{title:'" + labReportTag.getTitle() + "', code:'" + labReportTag.getCode() + "', type:'" + labReportTag.getType() + "'},");
				}else if(labReportTag.getType().contains("each")){
					sb.append("{title:'" + labReportTag.getTitle() + "', code:'" + labReportTag.getCode() + "', type:'" + labReportTag.getType() + "',tags:" + getsubLabReportTagList(reportId, labReportTag.getId()) + "},");
				}
			}
		}
		if (sb.length() > 1) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}
		sb.append("]");
		return sb.toString();
	}
	@SuppressWarnings("unchecked")
	public String getsubLabReportTagList(String reportId,String pId)
		throws GlobalException {
		StringBuffer sb = new StringBuffer("[");
		String hql = "FROM LabReportTag WHERE isDel='" + Constants_Base.N + "' ";
		hql+=" AND labReport.id='"+reportId+"'";
		hql+=" AND parentTag.id='"+pId+"'";
		hql+=" ORDER BY index ASC";
		List<LabReportTag> listLabReportTag = labReportTagDAO.find(hql);
		if (listLabReportTag != null && listLabReportTag.size() > 0) {
			for (LabReportTag labReportTag : listLabReportTag) {
				if(labReportTag.getType().contains("value")){
					sb.append("{title:'" + labReportTag.getTitle() + "', code:'" + labReportTag.getCode() + "', type:'" + labReportTag.getType() + "'},");
				}else if(labReportTag.getType().contains("each")){
					sb.append("{title:'" + labReportTag.getTitle() + "', code:'" + labReportTag.getCode() + "', type:'" + labReportTag.getType() + "',tags:" + getsubLabReportTagList(reportId, labReportTag.getId()) + "},");
				}
			}
		}
		if (sb.length() > 1) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}
		sb.append("]");
		return sb.toString();
	}
}
