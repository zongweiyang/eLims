package cn.labsoft.labos.common.query.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import DBstep.iDBManager2000;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.common.query.dao.ILabParameterDAO;
import cn.labsoft.labos.common.query.dao.ILabQueryDAO;
import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.common.query.entity.LabQuery;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.DemoVo;
import cn.labsoft.labos.common.query.vo.LabParameterVo;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.common.query.vo.PageVo;
import cn.labsoft.labos.common.query.vo.QueryVo;
import cn.labsoft.labos.common.query.vo.SqlVo;
import cn.labsoft.labos.common.query.vo.TitleVo;
import cn.labsoft.labos.common.query.vo.ValueVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.FileOperater;
import cn.labsoft.labos.utils.StrUtils;

import com.mysql.jdbc.ResultSetMetaData;

@Service("labQueryService")
public class LabQueryServiceImpl implements ILabQueryService {
	private ILabQueryDAO labQueryDAO;
	private ILabParameterDAO labParameterDAO;

	@Override
	public LabQueryVo addLabQuery(LabQueryVo labQueryVo) throws GlobalException {
		// TODO Auto-generated method stub

		LabQuery labQuery = new LabQuery();
		try {
			labQuery = this.vo2Po(labQueryVo, labQuery);
			labQuery.setName(labQuery.getName().replace("&gt;", ">"));
			labQuery.setName(labQuery.getName().replace("&lt;", "<"));
			labQueryDAO.addLabQuery(labQuery);
			labQueryVo.setId(labQuery.getId());
			List<LabParameterVo> listLabParameter = labQueryVo.getListLabParameter();
			if (listLabParameter != null && listLabParameter.size() > 0) {
				for (int i = 0; i < listLabParameter.size(); i++) {
					LabParameterVo labParameterVo = listLabParameter.get(i);
					if (labParameterVo == null || StrUtils.isBlankOrNull(labParameterVo.getName()))
						continue;
					LabParameter labParameter = new LabParameter();
					BeanUtils.copyProperties(labParameterVo, labParameter);
					if (!StrUtils.isBlankOrNull(labParameter.getComboxValue())) {
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&gt;", ">"));
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&lt;", "<"));
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&#x73;", "s"));
					}
					labParameter.setLabQuery(labQuery);
					labParameter.setIsDel(Constants_Base.N);
					labParameter.setQueryIndex(i);
					labParameterDAO.addLabParameter(labParameter);
				}
			}
			List<LabParameterVo> listParameterCountVo = labQueryVo.getListLabParameterCountVo();
			if (listParameterCountVo != null && listParameterCountVo.size() > 0) {
				for (int i = 0; i < listParameterCountVo.size(); i++) {
					LabParameterVo labParameterVo = listParameterCountVo.get(i);
					if (labParameterVo == null || StrUtils.isBlankOrNull(labParameterVo.getName()))
						continue;
					LabParameter labParameter = new LabParameter();
					BeanUtils.copyProperties(labParameterVo, labParameter);
					labParameter.setLabQuery(labQuery);
					labParameterDAO.addLabParameter(labParameter);
				}
			}
			if (!StrUtils.isBlankOrNull(labQuery.getFunction().getUrl()) && !labQuery.getFunction().getUrl().equals(File.separator)) {
				writeCode(labQuery.getFunction().getId(), labQuery.getId());
			}
		} catch (Exception e) {
			Log4J.error("LabQueryServiceImpl addLabQuery  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labQueryVo;
	}

	@Override
	public boolean deleteLabQuery(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labQueryDAO.deleteLabQuery(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabQueryServiceImpl deleteLabQuery  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabQueryVo getLabQuery(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabQueryVo labQueryVo = new LabQueryVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabQuery labQuery = labQueryDAO.getLabQuery(id);
				labQueryVo = this.po2Vo(labQuery, labQueryVo);
			} catch (Exception e) {
				Log4J.error("LabQueryServiceImpl getLabQuery  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labQueryVo;
	}

	@Override
	public List<LabQueryVo> getLabQueryList(LabQueryVo labQueryVo) throws GlobalException {
		// TODO Auto-generated method stub
		String wereHql = "";

		return this.getLabQueryVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabQueryPR(LabQueryVo labQueryVo, PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuery WHERE isDel='" + Constants_Base.N + "'";
		if (!StrUtils.isBlankOrNull(labQueryVo.getFunName()))
			hql += " AND funName='" + labQueryVo.getFunName() + "'";
		pageResult = labQueryDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null && pageResult.getResultList().size() > 0) {
			List<LabQueryVo> listLabQueryVo = new ArrayList<LabQueryVo>();
			List<LabQuery> listLabQuery = new ArrayList<LabQuery>();
			listLabQuery = pageResult.getResultList();
			for (LabQuery labQuery : listLabQuery) {
				LabQueryVo vo = new LabQueryVo();
				vo = this.po2Vo(labQuery, vo);
				listLabQueryVo.add(vo);
			}
			pageResult.setResultList(listLabQueryVo);
		}
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabQuery(LabQueryVo labQueryVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabQuery labQuery = new LabQuery();
		boolean key = true;
		try {
			labQuery = this.vo2Po(labQueryVo, labQuery);
			labQuery.setName(labQuery.getName().replace("&gt;", ">"));
			labQuery.setName(labQuery.getName().replace("&lt;", "<"));
			labQueryDAO.updateLabQuery(labQuery);
			String delHql = "FROM LabParameter WHERE labQuery.id='" + labQuery.getId() + "'";
			List<LabParameter> listLabParameter = labQueryDAO.find(delHql);
			if (listLabParameter != null && listLabParameter.size() > 0) {
				labQueryDAO.deleteAll(listLabParameter);
			}
			List<LabParameterVo> vo = labQueryVo.getListLabParameter();
			if (vo != null && vo.size() > 0) {
				for (int i = 0; i < vo.size(); i++) {
					LabParameterVo labParameterVo = vo.get(i);
					LabParameter labParameter = new LabParameter();
					BeanUtils.copyProperties(labParameterVo, labParameter);
					labParameter.setLabQuery(labQuery);
					labParameter.setIsDel(Constants_Base.N);
					labParameter.setQueryIndex(i);
					if (!StrUtils.isBlankOrNull(labParameter.getComboxValue())) {
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&gt;", ">"));
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&lt;", "<"));
						labParameter.setComboxValue(labParameter.getComboxValue().replace("&#x73;", "s"));
					}
					labParameterDAO.addLabParameter(labParameter);
				}
			}
			/**
			 * 统计类的sql
			 */
			List<LabParameterVo> voCount = labQueryVo.getListLabParameterCountVo();
			if (voCount != null && voCount.size() > 0) {
				for (LabParameterVo labParameterVo : voCount) {
					LabParameter labParameter = new LabParameter();
					BeanUtils.copyProperties(labParameterVo, labParameter);
					labParameter.setLabQuery(labQuery);
					labParameterDAO.addLabParameter(labParameter);
				}
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabQueryServiceImpl updateLabQuery  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabQuery4Del(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabQuery labQuery = labQueryDAO.getLabQuery(id);
					labQuery.setIsDel(Constants_Base.Y);
					labQueryDAO.updateLabQuery(labQuery);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error("LabQueryServiceImpl updateLabQuery4Del  error..." + e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<LabQueryVo> getLabQueryVoListByWhere(String wereHql) throws GlobalException {
		List<LabQueryVo> listLabQueryVo = new ArrayList<LabQueryVo>();
		String hql = "FROM LabQuery WHERE isDel='" + Constants_Base.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabQuery> listLabQuery = labQueryDAO.find(hql);
		if (listLabQuery != null && listLabQuery.size() > 0) {
			for (LabQuery labQuery : listLabQuery) {
				LabQueryVo labQueryVo = new LabQueryVo();
				labQueryVo = this.po2Vo(labQuery, labQueryVo);
				listLabQueryVo.add(labQueryVo);
			}
		}
		return listLabQueryVo;
	}

	public LabQuery vo2Po(LabQueryVo labQueryVo, LabQuery labQuery) {
		BeanUtils.copyProperties(labQueryVo, labQuery, new String[] { "isDel", "funId" });
		if (!StrUtils.isBlankOrNull(labQueryVo.getFunId())) {
			LabFunction funcation = (LabFunction) labQueryDAO.findById(LabFunction.class, labQueryVo.getFunId());
			labQuery.setFunction(funcation);
		}
		return labQuery;
	}

	@SuppressWarnings("unchecked")
	public LabQueryVo po2Vo(LabQuery labQuery, LabQueryVo labQueryVo) throws GlobalException {
		BeanUtils.copyProperties(labQuery, labQueryVo, new String[] { "function" });
		if (!StrUtils.isBlankOrNull(labQuery.getId())) {
			List<LabParameterVo> listLabParameterVo = new ArrayList<LabParameterVo>();
			String hql = "FROM LabParameter WHERE isDel='N' AND labQuery.id='" + labQuery.getId() + "' AND selectSql IS NULL ORDER BY sort";
			List<LabParameter> listLabParameter = labQueryDAO.find(hql);
			if (listLabParameter != null && listLabParameter.size() > 0) {
				for (LabParameter labParameter : listLabParameter) {
					LabParameterVo labParameterVo = new LabParameterVo();
					BeanUtils.copyProperties(labParameter, labParameterVo, new String[] { "labQuery" });
					if (labParameter.getLabQuery() != null)
						labParameterVo.setLabQueryId(labParameter.getLabQuery().getId());
					listLabParameterVo.add(labParameterVo);
				}
			}
			labQueryVo.setListLabParameter(listLabParameterVo);
			List<LabParameterVo> listLabParameterCountVo = new ArrayList<LabParameterVo>();
			hql = "FROM LabParameter WHERE isDel='N' AND labQuery.id='" + labQuery.getId() + "' AND selectSql='1' ORDER BY sort";
			List<LabParameter> listLabParameterCount = labQueryDAO.find(hql);
			if (listLabParameterCount != null && listLabParameterCount.size() > 0) {
				for (LabParameter labParameter : listLabParameterCount) {
					LabParameterVo labParameterVo = new LabParameterVo();
					BeanUtils.copyProperties(labParameter, labParameterVo, new String[] { "labQuery" });
					if (labParameter.getLabQuery() != null)
						labParameterVo.setLabQueryId(labParameter.getLabQuery().getId());
					listLabParameterCountVo.add(labParameterVo);
				}
			}
			labQueryVo.setListLabParameterCountVo(listLabParameterCountVo);
		}
		if (labQuery.getFunction() != null) {
			labQueryVo.setFunId(labQuery.getFunction().getId());
		}
		return labQueryVo;
	}

	@Override
	// TODO Auto-generated method stub
	public List<LabParameterVo> getProperty(LabQueryVo labQueryVo) throws GlobalException {
		//		List<String> listColum=new ArrayList<String>();
		List<LabParameterVo> listParameterVo = new ArrayList<LabParameterVo>();
		String[] whereSqls = null;
		String sql = labQueryVo.getName();
		sql = sql.replace("&gt;", ">"); //   
		sql = sql.replace("&lt;", "<");
		String whereSql = "";
		//		String tableName="";
		//		String[] tableNames=null;
		if (!StrUtils.isBlankOrNull(sql) && (labQueryVo.getSelectSql() == null)) {
			int whereStart = 0;
			if (labQueryVo.getSqlType().equals(Constants_Base.TRUE)) {
				if (sql.indexOf("WHERE") > -1) {
					whereStart = sql.lastIndexOf("WHERE");
				} else
					whereStart = sql.length();
				try {
					whereSql = sql.substring(whereStart + 5, sql.length());
				} catch (Exception e) {
					whereSql = "";
					throw new GlobalException("" + e.getMessage());
				}
			} else {
				whereSql = sql.substring(sql.lastIndexOf("WHERE") + 5, sql.length());
				//						tableName=sql.substring(sql.lastIndexOf("FROM")+4,sql.lastIndexOf("WHERE"));
			}
			whereSql = whereSql.replaceAll(" AND ", "&");
			whereSql = whereSql.replaceAll(" OR ", "&");
			whereSqls = whereSql.split("&");
			for (String name : whereSqls) {
				name = name.trim();
				if (name.indexOf("?") > -1) {
					LabParameterVo labParameterVo = new LabParameterVo();
					if (name.indexOf("^") > -1) {
						labParameterVo.setIsVague("checked=\"checked\"");
					}
					name = name.substring(0,name.indexOf("?"));
						name = name.replace(">", "");
						name = name.replace("<", "");
						name = name.replace("=", "");
					labParameterVo.setName(name);
					listParameterVo.add(labParameterVo);
				}
			}

		} else if (!StrUtils.isBlankOrNull(sql) && labQueryVo.getSelectSql().equals("1")) {
			String showSql = sql.substring(sql.indexOf("SELECT") + 6, sql.indexOf("FROM"));
			if (!StrUtils.isBlankOrNull(showSql)) {
				for (String name : showSql.split(",")) {
					LabParameterVo labParameterVo = new LabParameterVo();
					labParameterVo.setName(name);
					listParameterVo.add(labParameterVo);
				}
			}
		}
		return listParameterVo;
	}

	@Override
	public LabQueryVo runLabQueryJsp(LabQueryVo labQueryVo) throws GlobalException {
		String[] sql = new String[2];
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		iDBManager2000 db = new iDBManager2000();
		Connection con = db.Conn;
		Statement stmt;
		try {
			if (isSqlParamterNull(labQueryVo) == false) {
				ArrayList<QueryVo> num2 = new ArrayList<QueryVo>(labQueryVo.getListQuery());//
				labQueryVo.setListQuerySenior(num2);
			}
			stmt = (Statement) con.createStatement();
			sql = getSql(labQueryVo);
			getLabQueryVoParameter(labQueryVo);
			String pageWhere = "";
			if (labQueryVo.getPageVo() != null && labQueryVo.getPageVo().getPages() != 0) {
				pageWhere += "LIMIT " + labQueryVo.getPageVo().getPages() * 10 + ",10";
			} else {
				pageWhere += "LIMIT 0,10";
				PageVo pageVo = new PageVo();
				pageVo.setPages(0);
				labQueryVo.setPageVo(pageVo);
			}
			sql[0] += " " + pageWhere;
			rs = (ResultSet) stmt.executeQuery(sql[0]);
			rsmd = (ResultSetMetaData) rs.getMetaData();
			List<TitleVo> listTitleVo = new ArrayList<TitleVo>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				TitleVo titleVo = new TitleVo();
				titleVo.setSort(i);
				titleVo.setName(rsmd.getColumnName(i));
				listTitleVo.add(titleVo);
			}
			List<ValueVo> listValueVo = new ArrayList<ValueVo>();
			while (rs.next()) {
				ValueVo valueVo = new ValueVo();
				List<ValueVo> listVa = new ArrayList<ValueVo>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					ValueVo valueSonVo = new ValueVo();
					valueSonVo.setName(rs.getString(i));
					listVa.add(valueSonVo);
				}
				valueVo.setListValue(listVa);
				listValueVo.add(valueVo);
			}
			rs = (ResultSet) stmt.executeQuery(sql[1]);
			if (rs.next()) {
				int countPage = rs.getInt(1) % 10 != 0 ? (rs.getInt(1) / 10 + 1) : rs.getInt(1) / 10;
				labQueryVo.getPageVo().setCountPages(countPage);
			}
			labQueryVo.setListTitle(listTitleVo);
			labQueryVo.setListValue(listValueVo);
			/**
			 * 汇总sql
			 */
			labQueryVo = getCountNumber(labQueryVo, sql[0], stmt, rs);
		} catch (SQLException e) {
			Log4J.error("labQueryService..." + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		} finally {
			DataSourceUtils.releaseConnection(con, db.dataSource);
		}
		return labQueryVo;
	}

	//处理页面查询值得更新
	public LabQueryVo getLabQueryVoParameter(LabQueryVo labQueryVo) throws GlobalException {
		if ((labQueryVo.getListQuerySenior() == null || labQueryVo.getListQuerySenior().size() == 0)) {
			List<QueryVo> listQuery = new ArrayList<QueryVo>();
			if (labQueryVo.getListLabParameter() != null && labQueryVo.getListLabParameter().size() > 0) {
				for (LabParameterVo labParameterVo : labQueryVo.getListLabParameter()) {
					QueryVo queryVo = new QueryVo();
					queryVo.setName(labParameterVo.getName());
					queryVo.setDemo(labParameterVo.getNameChin());
					queryVo.setIsVague(labParameterVo.getIsVague());
					queryVo.setIsSort(labParameterVo.getIsSort());
					queryVo.setParameterId(labParameterVo.getId());
					queryVo.setShowType(labParameterVo.getShowType());
					queryVo.setComboxValue(labParameterVo.getComboxValue());
					queryVo.setQueryIndex(labParameterVo.getQueryIndex());
					queryVo.setDiaLogColum(labParameterVo.getDiaLogColum());
					queryVo.setDialogJson(labParameterVo.getDialogJson());
					listQuery.add(queryVo);
				}
				labQueryVo.setListQuery(listQuery);
			}
		} else if (labQueryVo.getQueryType().equals(Constants_Base.TRUE)) {
			List<QueryVo> listQuery1 = new ArrayList<QueryVo>();
			for (LabParameterVo labParameterVo : labQueryVo.getListLabParameter()) {
				QueryVo queryVo = new QueryVo();
				queryVo.setName(labParameterVo.getName());
				queryVo.setDemo(labParameterVo.getNameChin());
				queryVo.setIsVague(labParameterVo.getIsVague());
				queryVo.setIsSort(labParameterVo.getIsSort());
				queryVo.setParameterId(labParameterVo.getId());
				queryVo.setShowType(labParameterVo.getShowType());
				queryVo.setComboxValue(labParameterVo.getComboxValue());
				queryVo.setQueryIndex(labParameterVo.getQueryIndex());
				listQuery1.add(queryVo);
			}
			labQueryVo.setListQuery(listQuery1);
			List<QueryVo> listQuery = new ArrayList<QueryVo>();
			for (LabParameterVo labParameterVo : labQueryVo.getListLabParameter()) {
				QueryVo queryVo = new QueryVo();
				queryVo.setName(labParameterVo.getName());
				queryVo.setDemo(labParameterVo.getNameChin());
				queryVo.setIsVague(labParameterVo.getIsVague());
				queryVo.setIsSort(labParameterVo.getIsSort());
				queryVo.setParameterId(labParameterVo.getId());
				queryVo.setShowType(labParameterVo.getShowType());
				queryVo.setComboxValue(labParameterVo.getComboxValue());
				queryVo.setQueryIndex(labParameterVo.getQueryIndex());
				queryVo.setDialogJson(labParameterVo.getDialogJson());
				queryVo.setDiaLogColum(labParameterVo.getDiaLogColum());
				for (QueryVo vo : labQueryVo.getListQuerySenior()) {
					if (null != vo && !StrUtils.isBlankOrNull(vo.getParameterId())) {
						if (vo.getParameterId().equals(labParameterVo.getId())) {
							queryVo.setValue(vo.getValue());
							queryVo.setQueryType(vo.getQueryType());
							queryVo.setIsSortValue(vo.getIsSortValue());
							queryVo.setId(labParameterVo.getId());
							if ((!StrUtils.isBlankOrNull(labParameterVo.getComboxValue())) && (!StrUtils.isBlankOrNull(labParameterVo.getComboxValue().trim()))) {
								String selectHtml = labParameterVo.getComboxValue().substring(labParameterVo.getComboxValue().indexOf("<option"), labParameterVo.getComboxValue().length());
								selectHtml = "<select name=\"labQueryVo.listQuery[" + listQuery.size() + "].value\" id=\"select" + labParameterVo.getQueryIndex() + "\">" + selectHtml;
								queryVo.setComboxValue(selectHtml);
							}
							listQuery.add(queryVo);
							break;
						}
					}
				}

			}
			try {
				labQueryVo.setListQuerySenior(updateSelected(listQuery));
			} catch (GlobalException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		} else {
			try {
				labQueryVo.setListQuery(updateSelected(labQueryVo.getListQuerySenior()));
			} catch (GlobalException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

		return labQueryVo;
	}

	public List<QueryVo> updateSelected(List<QueryVo> listQueryVo) throws GlobalException {
		List<QueryVo> listQuerySelect = new ArrayList<QueryVo>();
		if (listQueryVo != null && listQueryVo.size() > 0) {
			for (QueryVo queryVo : listQueryVo) {
				if (!StrUtils.isBlankOrNull(queryVo.getComboxValue()) && !StrUtils.isBlankOrNull(queryVo.getComboxValue().trim())) {
					String nn = queryVo.getComboxValue();
					nn = nn.replace("&gt;", ">"); //   
					nn = nn.replace("&lt;", "<");
					nn = nn.replace("&#x73;", "s");
					String selected = nn.substring(nn.indexOf(".val("), nn.length());
					int num = selected.indexOf("</script>");
					if (num > 0) {
						String repalce = nn.substring(nn.indexOf(".val("), nn.indexOf(".val(") + num);
						if (!StrUtils.isBlankOrNull(queryVo.getValue()))
							nn = nn.replace(repalce, ".val('" + queryVo.getValue() + "')");
						else
							nn = nn.replace(repalce, ".val('')");
					}
					queryVo.setComboxValue(nn);

				}
				listQuerySelect.add(queryVo);
			}
		}
		return listQuerySelect;
	}

	//	public String getWhereColum(String sql){
	//		String whereSql="";
	//		String[] whereSqls=null;
	//		if(whereSql.toUpperCase().indexOf("JOIN")>-1){
	//			whereSqls=whereSql.toUpperCase().split("JOIN");
	//		}
	//		return whereSql;
	//	}

	public String[] getSql(LabQueryVo labQueryVo) {
		String[] sql = new String[2];
		if (labQueryVo.getListLabParameter() != null && labQueryVo.getListLabParameter().size() > 0) {
		SqlVo sqlVo = getSqlVo(labQueryVo.getName());
		if (isSqlParamterNull(labQueryVo))
			labQueryVo.setIsNull(Constants_Base.TRUE);
		else
			labQueryVo.setIsNull(Constants_Base.FALSE);
		sql = getSqlByWhere(sqlVo, labQueryVo);
		}else{
			sql[1] = "SELECT COUNT(1) "+labQueryVo.getName().substring(labQueryVo.getName().indexOf("FROM"));
			sql[0] = labQueryVo.getName();
		}
		return sql;
	}

	public boolean isSqlParamterNull(LabQueryVo labQueryVo) {
		boolean key = true;
		if (labQueryVo.getListQuery() != null && labQueryVo.getListQuery().size() > 0) {
			key = false;
		}
		return key;
	}

	public String[] getSqlByWhere(SqlVo sqlVo, LabQueryVo labQueryVo) {

		String[] sql = new String[] { "", "" };
		String orderSql = "";
	
			if (labQueryVo.getIsNull().equals(Constants_Base.TRUE)) {
				sql[0] = getSqlInitialise(sqlVo, labQueryVo);
			} else {
				sql[0] = getSqlByParameter(sqlVo, labQueryVo);
				//order BY 处理
				for (int i = 0; i < labQueryVo.getListQuerySenior().size(); i++) {
					QueryVo queryVo = labQueryVo.getListQuerySenior().get(i);
					if (queryVo != null && !StrUtils.isBlankOrNull(queryVo.getIsSort()) && queryVo.getIsSort().equals("1")) {
						if (!StrUtils.isBlankOrNull(queryVo.getIsSortValue()) && !queryVo.getIsSortValue().equals("1")) {
							if (!StrUtils.isBlankOrNull(orderSql)) {
								orderSql += ",";
							}
							orderSql += "" + queryVo.getName() + " " + queryVo.getIsSortValue() + "";
						}
					}
				}
				if (!StrUtils.isBlankOrNull(orderSql)) {
					orderSql = "ORDER BY " + orderSql;
				}
			}
			sql[1] = "SELECT COUNT(1) FROM " + sqlVo.getTableName() + " WHERE " + sql[0];
			sql[0] = "SELECT " + sqlVo.getColumn() + " FROM " + sqlVo.getTableName() + " WHERE " + sql[0] + " " + orderSql;
		
		return sql;
	}

	public String getSqlInitialise(SqlVo sqlVo, LabQueryVo labQueryVo) {
		String returnSql = "";
		if (sqlVo.getWhereSqls() != null && sqlVo.getWhereSqls().length > 0) {
			for (int i = 0; i < sqlVo.getWhereSqls().length; i++) {
				String whereSql = sqlVo.getWhereSqls()[i];
				whereSql=whereSql.replace("PX@", "ORDER ");
				boolean key = true;
				if (labQueryVo.getListLabParameter() != null && labQueryVo.getListLabParameter().size() > 0) {
					for (LabParameterVo labParameterVo : labQueryVo.getListLabParameter()) {
						if (whereSql.indexOf(labParameterVo.getName().trim()) > -1) {
							key = false;
							break;
						}
					}
				}
				if (key == true)
					returnSql += whereSql;
				if (i != sqlVo.getWhereSqls().length - 1){
					returnSql += " 1=1 ";
					if(sqlVo.getWhereSqls()[i+1].indexOf("PL@")>0||sqlVo.getWhereSqls()[i+1].indexOf("GROUP")>-1){
						
					}else returnSql+=" AND";
				}else if (key == false){
					if((whereSql.trim().replace(" ", "").indexOf("ORDER")>-1)||(whereSql.trim().replace(" ", "").indexOf("GROUP")>-1)){
						returnSql+=whereSql.substring(whereSql.indexOf("?")+1,whereSql.length());
					}else
					returnSql += " 1=1";
				}

			}
		}
		return returnSql;
	}

	public String getSqlByParameter(SqlVo sqlVo, LabQueryVo labQueryVo) {
		String returnSql = "";
		int sort = 0;//解决相同属性  经行匹配
		for (int i = 0; i < sqlVo.getWhereSqls().length; i++) {
			String whereSql = sqlVo.getWhereSqls()[i];
			whereSql=whereSql.replace("PX@", "ORDER ");
			if (whereSql.indexOf("?") == -1) {
				returnSql += whereSql;
				continue;
			}
			boolean key = false;
			for (int k = 0; k < labQueryVo.getListQuery().size(); k++) {
				QueryVo queryVo = labQueryVo.getListQuery().get(k);
				if (queryVo != null && !StrUtils.isBlankOrNull(queryVo.getName()) && whereSql.indexOf(queryVo.getName().trim()) > -1) {
					if (!StrUtils.isBlankOrNull(queryVo.getValue())) {
						if (whereSql.indexOf("^") > -1) {
							String whereOrSql = "";
							for (int j = k + 1; j < labQueryVo.getListQuery().size(); j++) {
								QueryVo queryVo1 = labQueryVo.getListQuery().get(j);
								if (whereSql.indexOf(queryVo1.getName().trim()) > -1) {
									whereOrSql += whereSql;
									if (!StrUtils.isBlankOrNull(queryVo1.getValue())) {
										if (whereOrSql.indexOf("^") > -1) {
											whereOrSql = whereOrSql.replace("^", "  " + queryVo1.getIsVagueValue() + "");
											if (queryVo1.getIsVagueValue().trim().toUpperCase().equals("LIKE")) {
												whereOrSql = whereOrSql.replace("?", "'%" + queryVo1.getValue() + "%'");
											} else {
												whereOrSql = whereOrSql.replace("?", "'" + queryVo1.getValue() + "'");
											}
										}
									}
									labQueryVo.getListQuery().remove(queryVo1);
								}

							}
							whereSql = whereSql.replace("^", " " + queryVo.getIsVagueValue() + "");
							if (queryVo.getIsVagueValue().trim().toUpperCase().equals("LIKE")) {
								whereSql = whereSql.replace("?", "'%" + queryVo.getValue() + "%'");
							} else {
								whereSql = whereSql.replace("?", "'" + queryVo.getValue() + "'");
							}
							if (!StrUtils.isBlankOrNull(whereOrSql)) {
								if (i != sqlVo.getWhereSqls().length - 1)
									whereSql = whereSql.replace("AND", "OR");
								StringBuffer sb = new StringBuffer(whereOrSql);
								if (whereOrSql.indexOf("AND") > -1 || whereOrSql.indexOf("OR") > -1) {
									if (whereOrSql.indexOf("AND") > -1) {
										sb.insert(whereOrSql.indexOf("AND"), ") ");
									}
									if (whereOrSql.indexOf("OR") > -1) {
										sb.insert(whereOrSql.indexOf("OR"), ") ");
									}

								}
								whereSql = "(" + whereSql + sb.toString();
							}
						} else {
							if (queryVo.getQueryIndex() != sort)
								continue;
							if (queryVo.getValue().indexOf(",") > -1 && queryVo.getValue().split(",").length >= 2) {
								String sqlSon = "(";
								for (int j = 0; j < queryVo.getValue().split(",").length; j++) {
									String name = queryVo.getValue().split(",")[j];
									sqlSon += whereSql.substring(0, whereSql.indexOf("?") + 1).replace("?", "'" + name + "'");
									if (j != queryVo.getValue().split(",").length - 1) {
										sqlSon += " OR ";
									}
								}
								sqlSon += ")";
								whereSql = sqlSon + whereSql.substring(whereSql.indexOf("?") + 1, whereSql.length());
							} else
								whereSql = whereSql.replace("?", "'" + queryVo.getValue() + "'");

						}

					} else {
						if (i != sqlVo.getWhereSqls().length - 1)
							whereSql = " 1=1 AND ";
						else
							whereSql = " 1=1";
					}
					returnSql += whereSql;
					key = true;
					labQueryVo.getListQuery().remove(queryVo);
					break;
				}
			}
			if (whereSql.indexOf("?") == -1 && whereSql.indexOf("^") == -1) {
				if (key == false)
					returnSql += whereSql;
			} else {
				if (i != sqlVo.getWhereSqls().length - 1)
					returnSql += " 1=1 AND ";
				else
					returnSql += " 1=1";
			}
			sort++;
		}
		return returnSql;
	}

	public String getSqlByParameterAndOrder() {
		return null;
	}

	public static SqlVo getSqlVo(String sql) {
		SqlVo sqlVo = new SqlVo();
		sqlVo.setColumn(sql.substring(sql.indexOf("SELECT") + 6, sql.lastIndexOf("FROM")));
		sqlVo.setTableName(sql.substring(sql.lastIndexOf("FROM") + 4, sql.lastIndexOf("WHERE")));
		sqlVo.setWhere(sql.substring(sql.lastIndexOf("WHERE") + 5, sql.length()));
		String whereSql = sqlVo.getWhere().replaceAll("AND", "AND&");
		whereSql=whereSql.replace("ORDER", "PX@");
		whereSql = whereSql.replaceAll(" OR ", "OR&");
		sqlVo.setWhereSqls(whereSql.split("&"));
		return sqlVo;
	}

	@SuppressWarnings("deprecation")
	public boolean writeCode(String funId, String id) throws GlobalException {
		boolean key = false;
				LabFunction labFunction=(LabFunction) labQueryDAO.findById(LabFunction.class, funId);
				String path=ServletActionContext.getRequest().getRealPath("/").replace("\\WebRoot\\", "")+File.separator+"src"+File.separator+"strutsconfigs";
				String[] fileNames=splitFileName(labFunction.getUrl(),path,id,labFunction.getUrl());
		return key;
	}

	public static String[] splitFileName(String filePath, String absPath, String id, String funPath) throws GlobalException {
		String[] fileNames = new String[5];
		if (filePath.charAt(0) == '/') {
			filePath = filePath.substring(1, filePath.length());
		}
		fileNames = filePath.split("/");
		File file = new File(absPath);
		boolean endStatus = false;
		String mateName = fileNames[0] + ".xml";
		String fileAbsPath = "";
		for (File excel : file.listFiles()) {
			if (excel.listFiles() == null) {
				if (excel.getName().equals(mateName)) {
					fileAbsPath = excel.getAbsolutePath();
					endStatus = true;
				}
			} else {
				boolean key = true;
				while (key) {
					for (File excel1 : excel.listFiles()) {
						if (excel1.listFiles() == null) {
							if (excel1.getName().equals(mateName)) {
								fileAbsPath = excel1.getAbsolutePath();
								key = false;
								endStatus = true;
							}
						}
					}
					key = false;
				}
			}
			if (endStatus == true)
				break;
		}
		List<String> listLine = FileOperater.readFileByLines(fileAbsPath);
		String nn = fileNames[0] + "." + (fileNames[1].substring(0, 1).toLowerCase() + fileNames[1].substring(1, fileNames[1].length()));
		boolean key = true;
		boolean findPack = true;
		List<String> writeLine = new ArrayList<String>();
		List<String> listLineXml = readFile(fileAbsPath, fileNames, absPath);
		for (int j = 0; j < listLine.size(); j++) {
			String line = listLine.get(j);
			if (line.trim().startsWith("<package name=\"" + nn + "\"")) {
				writeLine.add(line);
				for (int jj = j + 1; jj < listLine.size(); jj++) {
					String lineEnd = listLine.get(jj);
					if (lineEnd.trim().startsWith("</package>") && findPack) {
						writeLine.addAll(listLineXml);
						findPack = false;
					}
					writeLine.add(lineEnd);
				}
				key = false;
			} else
				writeLine.add(line);
			if (key == false)
				break;
		}
		try {
			FileOperater.writeFileByLines(fileAbsPath, writeLine);
			writeDoc(absPath, fileNames, fileAbsPath, funPath);
			writeAction(absPath, fileAbsPath, fileNames, id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return fileNames;
	}

	public static List<String> readFile(String fileAbsPath, String[] fileNames, String path) throws GlobalException {
		List<String> readLineList = new ArrayList<String>();
		List<String> returnLineList = new ArrayList<String>();
		String action = "cn.labsoft.labos." + fileAbsPath.substring(fileAbsPath.indexOf("strutsconfigs") + 13, fileAbsPath.indexOf(fileNames[0])).replace(File.separator, "") + "." + fileNames[0] + ".action." + fileNames[1].substring(0, 1).toUpperCase() + fileNames[1].substring(1, fileNames[1].length()) + "Action";
		String jsp = "/jsp" + fileAbsPath.substring(fileAbsPath.indexOf("strutsconfigs") + 13, fileAbsPath.indexOf(fileNames[0])) + fileNames[0];
		jsp = jsp.replace(File.separator, "/");
		String readPath = path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + File.separator + "common" + File.separator + "query" + File.separator + "copyXml.xml";
		readLineList = FileOperater.readFileByLines(readPath);
		if (readLineList.size() > 0) {
			for (String line : readLineList) {
				if (!StrUtils.isBlankOrNull(line)) {
					if (line.indexOf("actionName") > -1) {
						line = line.replace("actionName", fileNames[2].substring(0, fileNames[2].indexOf(".")));
					}
					if (line.indexOf("actionMethod") > -1) {
						line = line.replace("actionMethod", fileNames[2].substring(0, fileNames[2].indexOf(".")));
					}
					if (line.indexOf("actionURL") > -1) {
						line = line.replace("actionURL", action);
					}
					if (line.indexOf("JSPURL") > -1) {
						line = line.replace("JSPURL", jsp);
					}
				}
				returnLineList.add(line);
			}
		}
		return returnLineList;
	}

	public static boolean writeDoc(String path, String[] fileNames, String fileAbsPath, String funPath) throws IOException, GlobalException {
		boolean key = true;
		String copyJsp = path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + File.separator + "common" + File.separator + "query" + File.separator + "copyQueryList.jsp";
		String copyJsp2 = path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + File.separator + "common" + File.separator + "query" + File.separator + "copyQuery2List.jsp";
		String jspUrl = path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + fileAbsPath.substring(fileAbsPath.indexOf("strutsconfigs") + 13, fileAbsPath.indexOf(fileNames[0])) + fileNames[0] + File.separator + "queryList.jsp";
		String jspUrl2 = path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + fileAbsPath.substring(fileAbsPath.indexOf("strutsconfigs") + 13, fileAbsPath.indexOf(fileNames[0])) + fileNames[0] + File.separator + "query2List.jsp";
		List<String> listCopyJsp = FileOperater.readFileByLines(copyJsp);
		List<String> listJsp = new ArrayList<String>();
		if (listCopyJsp != null && listCopyJsp.size() > 0) {
			for (String jspLine : listCopyJsp) {
				if (jspLine.indexOf("ACTIONPATH") > -1) {
					jspLine = jspLine.replace("ACTIONPATH", funPath);
				}
				listJsp.add(jspLine);
			}
		}
		FileOperater.writeFileByLines(jspUrl, listJsp);
		List<String> listCopyJsp2 = FileOperater.readFileByLines(copyJsp2);
		List<String> listJsp2 = new ArrayList<String>();
		if (listCopyJsp2 != null && listCopyJsp2.size() > 0) {
			for (String jspLine : listCopyJsp2) {
				if (jspLine.indexOf("ACTIONPATH") > -1) {
					jspLine = jspLine.replace("ACTIONPATH", funPath);
				}
				listJsp2.add(jspLine);
			}
		}
		FileOperater.writeFileByLines(jspUrl2, listJsp2);
		return key;
	}

	public static boolean writeAction(String path, String fileAbsPath, String[] fileNames, String id) throws GlobalException {
		boolean key = true;
		String actionUrl = path.substring(0, path.indexOf("strutsconfigs")) + File.separator + "cn.labsoft.labos." + fileAbsPath.substring(fileAbsPath.indexOf("strutsconfigs") + 13, fileAbsPath.indexOf(fileNames[0])).replace(File.separator, "") + "." + fileNames[0] + ".action." + fileNames[1].substring(0, 1).toUpperCase() + fileNames[1].substring(1, fileNames[1].length()) + "Action";
		actionUrl = actionUrl.replace(".", File.separator) + ".java";
		List<String> listWriteLine = new ArrayList<String>();
		List<String> listLine = FileOperater.readFileByLines(actionUrl);
		List<String> copyActionList = FileOperater.readFileByLines((path.substring(0, path.indexOf("src")) + "WebRoot" + File.separator + "jsp" + File.separator + "common" + File.separator + "query" + File.separator + "copyAction.java"));
		List<String> writeCopyList = new ArrayList<String>();
		if (copyActionList != null && copyActionList.size() > 0) {
			for (String line : copyActionList) {
				if (line.indexOf("queryId") > -1) {
					line = line.replace("queryId", id);
				}
				if (line.indexOf("methodName") > -1) {
					line = line.replace("methodName", fileNames[2].substring(0, fileNames[2].indexOf(".")));
				}
				writeCopyList.add(line);
			}
		}
		if (listLine != null && listLine.size() > 0) {
			boolean keyImport = true;
			boolean keyService = true;
			boolean keyMehtod = true;
			for (int i = 0; i < listLine.size(); i++) {
				String line = listLine.get(i);
				if (line.trim().startsWith("import") && keyImport) {
					keyImport = false;
					listWriteLine.add("import cn.labsoft.labos.constants.Constants;");
					listWriteLine.add("import cn.labsoft.labos.common.query.service.ILabQueryService;");
					listWriteLine.add("import cn.labsoft.labos.common.query.vo.LabQueryVo;");
				}
				if (i >= 1 && listLine.get(i - 1).trim().startsWith("public class") && keyService) {
					keyService = false;
					listWriteLine.add("	private ILabQueryService labQueryService = (ILabQueryService) SystemInstance.getInstance().getBean(ILabQueryService.class);");
					listWriteLine.add("	private LabQueryVo labQueryVo;");
					listWriteLine.add("	public LabQueryVo getLabQueryVo() {");
					listWriteLine.add("		return labQueryVo;	");
					listWriteLine.add("	}");
					listWriteLine.add("	public void setLabQueryVo(LabQueryVo labQueryVo) {");
					listWriteLine.add("			this.labQueryVo = labQueryVo;");
					listWriteLine.add("}");
				}
				if (keyMehtod && keyService == false && line.trim().startsWith("public")) {
					keyMehtod = false;
					listWriteLine.addAll(writeCopyList);
				}
				listWriteLine.add(line);
			}
		}
		if (listWriteLine.size() > 0) {
			FileOperater.writeFileByLines(actionUrl, listWriteLine);
		}
		return key;
	}

	@Resource
	public void setLabQueryDAO(ILabQueryDAO labQueryDAO) {
		this.labQueryDAO = labQueryDAO;
	}

	@Resource
	public void setLabParameterDAO(ILabParameterDAO labParameterDAO) {
		this.labParameterDAO = labParameterDAO;
	}

	public LabQueryVo getCountNumber(LabQueryVo labQueryVo, String sql, Statement stmt, ResultSet rs) throws GlobalException {
		if (!StrUtils.isBlankOrNull(labQueryVo.getId())) {
			String showSql = sql.substring(sql.indexOf("SELECT") + 6, sql.indexOf("FROM"));
			String hql = "FROM LabParameter WHERE isDel='" + Constants_Base.N + "' AND labQuery.id='" + labQueryVo.getId() + "' AND selectSql='1'";
			List<LabParameter> listLabParameter = labParameterDAO.find(hql);
			if (listLabParameter != null && listLabParameter.size() > 0) {
				List<DemoVo> listDemoVoValue = new ArrayList<DemoVo>();
				String countSql = "";
				for (LabParameter labParameter : listLabParameter) {
					DemoVo demoVo = new DemoVo();
					demoVo.setName(labParameter.getNameChin());
					listDemoVoValue.add(demoVo);
					countSql += labParameter.getName();
					countSql += ",";
				}
				if (countSql.length() > 0)
					countSql = countSql.substring(0, countSql.length() - 1);
				sql = sql.replace(showSql, " " + countSql + " ");
				if (sql.indexOf("LIMIT") > 0)
					sql = sql.substring(0, sql.indexOf("LIMIT"));
				try {
					rs = (ResultSet) stmt.executeQuery(sql);

					if (rs != null && rs.next()) {
						for (int i = 0; i < listDemoVoValue.size(); i++) {
							String value = rs.getString(i + 1);
							listDemoVoValue.get(i).setValue(value);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
				labQueryVo.setListCountValue(listDemoVoValue);
			}
			showSql = sql.substring(sql.indexOf("SELECT") + 6, sql.indexOf("FROM"));
			LabQuery labQuery=(LabQuery) labParameterDAO.findById(LabQuery.class,(labQueryVo.getId()));
			if(!StrUtils.isBlankOrNull(labQuery.getIsCartogram())&&labQuery.getIsCartogram().equals("1")){
				sql = sql.replace(showSql, " " + labQuery.getCartogramSql().split(";")[0] + " ");
				if(sql.indexOf("LIMIT")>1){
					sql=sql.substring(0,sql.indexOf("LIMIT"));
				}
				sql+=labQuery.getCartogramSql().split(";")[1];
				
				List<DemoVo> listDemoVoValue = new ArrayList<DemoVo>();
				try {
					rs = (ResultSet) stmt.executeQuery(sql);
					if (rs != null) {
						while(rs.next()){
							String value = rs.getString(1);
							String date=rs.getString(2);
							String newDate="";
							if(!StrUtils.isBlankOrNull(date)){
								if(date.indexOf("-")>-1){
									String month=String.valueOf((Integer.valueOf(date.split("-")[1])-1));
									newDate=date.split("-")[0]+","+month+",";
									if((date.split("-")[2].length()>=3)){
										newDate+=date.split("-")[2].substring(0,3);
									}else newDate+=date.split("-")[2];
								}else  newDate=date;
							}
							DemoVo demoVo=new DemoVo();
							demoVo.setName(newDate);
							demoVo.setValue(value);
							listDemoVoValue.add(demoVo);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
				if(listDemoVoValue!=null&&listDemoVoValue.size()>0){
					labQueryVo.setListCartogram(listDemoVoValue);
				}
			}
		}
		return labQueryVo;
	}
}
