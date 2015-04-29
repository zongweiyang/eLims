package cn.labsoft.labos.utils.solr;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.struts2.ServletActionContext;

import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageBean;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.vo.CommonVo;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.TikaUtils;

import com.opensymphony.xwork2.ActionContext;

public class SolrClientUtils {
	private static HttpSolrServer solr = null;
	public static HttpSolrServer getSolrserver() throws GlobalException {
		ActionContext ac = ActionContext.getContext();
		HttpServletRequest request =(HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
		String key = Integer.toString(request.getServerPort());
		key="http://localhost:"+key+"/collection1";
		if(null==solr){
			try {
				solr = new HttpSolrServer(key);//
				solr.setConnectionTimeout(50000);// 连接solr服务器超时时间 单位毫秒
				solr.setSoTimeout(10000); // socket read timeout
				solr.setDefaultMaxConnectionsPerHost(1000);
				solr.setMaxTotalConnections(1000);
				solr.setFollowRedirects(false); // 是否允许跟踪重定向 默认false
				solr.setAllowCompression(true); // 是否允许压缩
			} catch (Exception e) {
				System.out.println("请检查tomcat服务器或端口是否开启!");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return solr;
	}

	/**
	 * 添加索引
	 * 
	 * @param List
	 *            <CommonVo> 需要拼接好的List集合
	 * @throws GlobalException 
	 * @throws
	 * @return 返回类型 boolean
	 */
	public static boolean saveOrUpdateIndex(List<CommonVo> keyValeList) throws GlobalException {
		try {
			SolrInputDocument doc = new SolrInputDocument();
			for (CommonVo commonVo : keyValeList) {
				doc.addField(commonVo.getId(), commonVo.getLable());
				solr.add(doc);
				//对索引进行优化
				solr.optimize();
				solr.commit();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		
		return true;
	}

	/**
	 * 整合DocFileVo 并添加索引
	 * 
	 * @param List<DocFileVo>
	 * @throws GlobalException 
	 * @throws
	 * @return 返回类型 boolean
	 */
	public static boolean addDoc(List<LabUpload> loadList) throws GlobalException {
		getSolrserver();//初始化
		try {
			if (null != loadList && 0 < loadList.size()) {
				for (LabUpload docFileVo : loadList) {
					List<CommonVo> cvList = new ArrayList<CommonVo>();
					try {
						
						cvList = getNameValue(docFileVo);
						if (!StrUtils.isBlankOrNull(docFileVo.getPath())) {
							// 抽取所上传文件内容
							String content = TikaUtils.parseFile(new File(docFileVo.getPath()));
							cvList.add(new CommonVo("content", content));
						}
						saveOrUpdateIndex(cvList);
						System.out.println(docFileVo.getName()+" Create Index Successfully.");
					} catch (Exception e) {
						//e.printStackTrace();
						throw new GlobalException("" + e.getMessage());
					}

				}
			}
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	/**
	 * 通过反射获取属性名称与值不为空的CommonVo
	 * @param DocFileVo
	 * @throws GlobalException 
	 * @throws
	 * @return 返回类型 List<CommonVo>
	 */
	public static List<CommonVo> getNameValue(LabUpload docFileVo) throws GlobalException {
		List<CommonVo> cvList = new ArrayList<CommonVo>();
		cvList.add(new CommonVo("id", docFileVo.getId()));
		try {
			List<String> nameList = new ArrayList<String>();
			Class cls = Class.forName(LabUpload.class.getName());
			Field[] fieldlist = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				nameList.add(fld.getName());
			}
			// 通过反射获取属性值
			for (String name : nameList) {
				String sx = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
				String value = "";
				value = String.valueOf(docFileVo.getClass().getMethod("get" + sx, new Class[] {}).invoke(docFileVo, new Object[] {}));
				if (value == null || "null".equals(value) || "".equals(value)) {
					continue;
				}
				cvList.add(new CommonVo(name, value));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return cvList;
	}

	/**
	 * 全范围查询
	 * @param DocFileVo
	 * @throws
	 * @return 返回类型 PageResult
	 */
	public static PageResult queryAll(LabUpload vo, PageResult pageResult) throws Exception{
		getSolrserver();//初始化
		List<LabUpload> resultList = new ArrayList();
		List<CommonVo> cvList = new ArrayList<CommonVo>();
		if (null != vo) {
			cvList = getNameValue(vo);
		}
		String qu = "";
		if (cvList.size() > 0) {//拼接solr查询条件,拼接高亮查询条件
			for (CommonVo commonVo : cvList) {
				qu += commonVo.getLable() + ",";
			}
		}
		qu = qu.substring(0, qu.length() - 1);
//		qu = "content："+qu+" OR "+"name:"+qu+" OR "+"title:"+qu;
		PageBean pager = null;
		int totalRows = 0;
		SolrQuery paramsTotal = new SolrQuery();//创建solr查询对象
		paramsTotal.setQuery(qu);
		QueryResponse response = solr.query(paramsTotal);
		
		SolrDocumentList list = response.getResults();
		if (null != list && 0 < list.size()) {
			totalRows = (int) list.getNumFound();
		}
//		System.out.println("总计：" + list.getNumFound() + "条，本批次:" + list.size() + "条");

		SolrQuery params = new SolrQuery();//创建solr查询对象
		params.setQuery(qu);//高亮查询字段
		params.setHighlight(true);//开启高亮功能
		params.setParam("hl.fl", "name,content,author");//高亮字段
		params.setHighlightSimplePre("<font color='red'>");//渲染标签
		params.setHighlightSimplePost("</font>");//渲染标签 
	   
		
		pager = new PageBean(totalRows, pageResult.getPageSize());
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
		//设置跳码
		pager.jumpPage();
		String orderColumn = pageResult.getOrderColumn();
		if (null != pageResult.getOrderColumn() && !"".equals(pageResult.getOrderColumn())) {
			try {
				params.setSort(orderColumn, pageResult.getOrder().equals("ASC") ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc);
			} catch (Exception e) {
				throw new GlobalException("" + e.getMessage());
			}
		}

		params.setStart(pager.getStartRow());
		params.setRows(pager.getPageSize());
		try {
			response = solr.query(params);
			Map<String,Map<String,List<String>>> hl = response.getHighlighting(); //获取高亮集合 
			
			SolrDocumentList list1 = response.getResults();
			//获取所有字段属性
			List<String> nameList = new ArrayList<String>();
			Class cls = Class.forName(LabUpload.class.getName());
			Field[] fieldlist = cls.getDeclaredFields();
			for (int ix = 0; ix < fieldlist.length; ix++) {
				Field fld = fieldlist[ix];
				if(fld.getType().toString().equals(String.class.toString())){
					nameList.add(fld.getName());
				}
			}
			String temp="";//高亮字段,通过循环设定高亮字段
			for(SolrDocument sd : list1){ 
				for (String name : nameList) {
					if(hl.get(sd.getFieldValue("id")).get(name) != null){  
						temp = hl.get(sd.getFieldValue("id")).get(name).get(0);  
						sd.setField(name, temp);
					} 
				}
			}  
			
			for (int i = 0; i < list1.size(); i++) {
				LabUpload docFileVo = new LabUpload();
				//通过反射放入对应值
				SolrDocument doc = list1.get(i);
				if(null!=nameList&&0<nameList.size()){
					for(String key : nameList){
						Object value = doc.get(key);
						String sx = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
						try {
							docFileVo.getClass().getMethod("set" + sx, new Class[] {String.class}).invoke(docFileVo, new Object[] {value});
						} catch (Exception e) {
							throw new GlobalException("" + e.getMessage());
						}
					}
				}
				//重配置地址
				docFileVo.setPath(docFileVo.getPath().substring(docFileVo.getPath().indexOf("/")+1, docFileVo.getPath().length()));
				//重配置时间
				docFileVo.setCreateTime(docFileVo.getCreateTime().substring(0, 10));
				resultList.add(docFileVo);
			}
		} catch (SolrServerException e) {
			//e.printStackTrace();
		}
		List columnList = new ArrayList();
		pageResult.setPageBean(pager);
		pageResult.setResultList(resultList);
		pageResult.setColumnList(columnList);
		return pageResult;
	}
//
//
//	/**
//	 * 精确查询
//	 * @param DocFileVo
//	 * @throws
//	 * @return 返回类型 PageResult
//	 */
//	public static PageResult query(DocFileVo vo, PageResult pageResult) throws Exception {
//		List<DocFileVo> resultList = new ArrayList();
//		List<CommonVo> cvList = new ArrayList<CommonVo>();
//		if (null != vo) {
//			cvList = getNameValue(vo);
//		}
//		String qu = "";
//		if (cvList.size() > 0) {//拼接solr查询条件
//			for (CommonVo commonVo : cvList) {
//				qu += commonVo.getId() + ":" + commonVo.getLable() + " AND ";
//			}
//		}
//		qu = qu.substring(0, qu.length() - 5);
//
//		PageBean pager = null;
//		int totalRows = 0;
//
//		SolrQuery paramsTotal = new SolrQuery();//创建solr查询对象
//		paramsTotal.setQuery(qu);
//		QueryResponse response = solr.query(paramsTotal);
//		SolrDocumentList list = response.getResults();
//		if (null != list && 0 < list.size()) {
//			totalRows = list.size();
//		}
//		System.out.println("总计：" + list.getNumFound() + "条，本批次:" + list.size() + "条");
//
//		SolrQuery params = new SolrQuery();//创建solr查询对象
//		
//		pager = new PageBean(totalRows, pageResult.getPageSize());
//		if (pageResult.getCurrentPage() != null && !"".equals(pageResult.getCurrentPage()) && !"null".equals(pageResult.getCurrentPage())) {
//			try {
//				pager.refresh(Integer.parseInt(pageResult.getCurrentPage()));
//			} catch (Exception e) {
//				//e.printStackTrace();
//			}
//		}
//
//		if (pageResult.getPagerMethod() != null && !"".equals(pageResult.getPagerMethod()) && !"null".equals(pageResult.getPagerMethod())) {
//			if (pageResult.getPagerMethod().equals("first")) {
//				pager.first();
//			} else if (pageResult.getPagerMethod().equals("previous")) {
//				pager.previous();
//			} else if (pageResult.getPagerMethod().equals("next")) {
//				pager.next();
//			} else if (pageResult.getPagerMethod().equals("last")) {
//				pager.last();
//			}
//		}
//		//设置跳码
//		pager.jumpPage();
//		String orderColumn = pageResult.getOrderColumn();
//		if (null != pageResult.getOrderColumn() && !"".equals(pageResult.getOrderColumn())) {
//			try {
//				params.setSort(orderColumn, pageResult.getOrder().equals("ASC") ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc);
//			} catch (Exception e) {
//			}
//		}
//		params.setStart(pager.getStartRow());
//		params.setRows(pager.getPageSize());
//		params.setQuery(qu);
//		try {
//			response = solr.query(params);
//			SolrDocumentList list1 = response.getResults();
//			List<String> nameList = new ArrayList<String>();
//			Class cls = Class.forName(DocFileVo.class.getName());
//			Field[] fieldlist = cls.getDeclaredFields();
//			for (int ix = 0; ix < fieldlist.length; ix++) {
//				Field fld = fieldlist[ix];
//				if(fld.getType().toString().equals(String.class.toString())){
//					nameList.add(fld.getName());
//				}
//			}
//			
//			for (int i = 0; i < list1.size(); i++) {
//				DocFileVo docFileVo = new DocFileVo();
//
//				SolrDocument doc = list1.get(i);
//				if(null!=nameList&&0<nameList.size()){
//					for(String key : nameList){
//						Object value = doc.get(key);
//						String sx = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
//						try {
//							docFileVo.getClass().getMethod("set" + sx, new Class[] {String.class}).invoke(docFileVo, new Object[] {value});
//						} catch (Exception e) {
//						}
//					}
//				}
//				resultList.add(docFileVo);
//
//			}
//		} catch (SolrServerException e) {
//			//e.printStackTrace();
//		}
//		List columnList = new ArrayList();
//		pageResult.setPageBean(pager);
//		pageResult.setResultList(resultList);
//		pageResult.setColumnList(columnList);
//		return pageResult;
//	}

	/**
	 * 索引删除
	 * 
	 * @param List<String> id集合
	 * @throws GlobalException 
	 * @throws
	 * @return 返回类型 boolean true删除成功,false删除失败
	 */
	public static boolean delIndex(String[] ids) throws GlobalException {
		try {
			for (String id : ids) {
				solr.deleteById(id);
				solr.commit();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	/**
	 * 全部索引删除
	 * @param 
	 * @throws GlobalException 
	 * @throws
	 * @return 返回类型 boolean true删除成功,false删除失败
	 * */
	public static boolean delIndexAll() throws GlobalException {
		try {
			solr.deleteByQuery("*:*");
			solr.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		getSolrserver();
//		List<LabUpload> loadList = new ArrayList();
//		LabUpload po= new LabUpload();
//		po.setId("40288126458d2ab701458d479c240010");
//		po.setName("开发计划.doc");
//		po.setVname("1398234651643.doc");
//		po.setPath("E:\\4.xlsx");
//		loadList.add(po);
//		addDoc(loadList);
//		SolrInputDocument doc = new SolrInputDocument();
//		doc.addField("id", "40288126458d2ab701458d479c240010");
//		doc.addField("name", "开发计划.doc");
//		doc.addField("path", "E:\\4.xlsx");
//		doc.addField("content", "1111111");
//		solr.add(doc);
//		//对索引进行优化
//		solr.optimize();
//		solr.commit();
		delIndexAll();
		
	}

}
