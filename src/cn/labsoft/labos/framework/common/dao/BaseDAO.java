package cn.labsoft.labos.framework.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageBean;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.framework.common.po.BasePo;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.utils.StrUtils;
@SuppressWarnings({"unchecked","static-access","unused","deprecation"})
@Component("baseDAO")
public abstract class BaseDAO extends HibernateDaoSupport implements IBaseDAO {
	
	private static final String DATE_NAME="createDate"; 
	
	@Resource(name="sessionFactory")
	private void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	public void save(Object object) {
		object = setTenantIdToCurrentPo(object);
		getHibernateTemplate().save(object);
	}

	public void delete(Object object) {
		object = setTenantIdToCurrentPo(object);
		getHibernateTemplate().delete(object);
	}

	public void delete(String hsql) throws GlobalException {
		if (SystemInstance.isMutiTenant()) {
			hsql = TentantIdSetAdvice.setTenantIdToHSql(hsql);// 删除查询时
		}
		Session session = openSession();
		try {
			session.createQuery(hsql).executeUpdate();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public void deleteAll(Collection coll) {
		getHibernateTemplate().deleteAll(coll);
	}

	public void deleteBySession(Object object) throws GlobalException {
		object = setTenantIdToCurrentPo(object);
		Session session = openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			session.setFlushMode(FlushMode.AUTO);
			tx.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
			throw new GlobalException("" + e.getMessage());
		}
	}

	protected void update(Object object) {
		object = setTenantIdToCurrentPo(object);
		getHibernateTemplate().update(object);
	}

	protected void updateAUTO(Object object) throws GlobalException {
		object = setTenantIdToCurrentPo(object);
		Session session = openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(object);
			session.setFlushMode(FlushMode.AUTO);
			tx.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
			throw new GlobalException("" + e.getMessage());
		}
	}

	protected void saveAUTO(Object object) throws GlobalException {
		object = setTenantIdToCurrentPo(object);
		Session session = openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			session.setFlushMode(FlushMode.AUTO);
			tx.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			tx.rollback();
			throw new GlobalException("" + e.getMessage());
		}
	}
	public List find(String hql) throws GlobalException {
		hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		List list = new ArrayList();
		try{
			list = getHibernateTemplate().find(hql);
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		return list;
	}
	public List find4All(String hql) throws GlobalException {
		List list = new ArrayList();
		try{
			list = getHibernateTemplate().find(hql);
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		return list;
	}
	public Object find(String hql, int i) throws GlobalException {
		Object obj = null;
		List l = find(hql);
		if (null != l && l.size() > 0) {
			if (l.size() < i)
				i = 0;
			obj = l.get(i);
		}
		return obj;
	}
	public Object find4All(String hql, int i) throws GlobalException {
		Object obj = null;
		List l = find4All(hql);
		if (null != l && l.size() > 0) {
			if (l.size() < i)
				i = 0;
			obj = l.get(i);
		}
		return obj;
	}
	public Object find0(String hql) throws GlobalException {
		if (SystemInstance.isMutiTenant()) {
			hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		}
		Object obj = null;
		List l = find(hql);
		return null!=l&&l.size()>0?l.get(0):null;
	}
	
	public List findByCache(String hql) {
		hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		List list = new ArrayList();
		if ((hql.indexOf("From") >= 0) || (hql.indexOf("from") >= 0)
				|| (hql.indexOf("FROM") >= 0)) {
			int startPosition = (hql.indexOf("From") >= 0) ? hql
					.indexOf("From") : hql.indexOf("from");
			String tempStr = "";

			if (startPosition < 0) {
				startPosition = hql.indexOf("FROM");
			}
			tempStr = hql.substring(startPosition, hql.length());
			StringTokenizer st = new StringTokenizer(tempStr, " ");
			String[] tempStrArray = new String[st.countTokens()];
			tempStrArray = StrUtils.split(tempStr, " ");
			if (tempStrArray.length > 0) {
				String objectName = tempStrArray[1];
				Session session = openSession();
				Query query = session.createQuery(hql);
				query.setCacheable(true);
				query.setCacheRegion(objectName + ".bean." + objectName
						+ "Instance");
				list = query.list();
				return list;
			}
			list = getHibernateTemplate().find(hql);
			return list;
		}

		list = getHibernateTemplate().find(hql);
		return list;
	}

	public Object findById(Class cla, Serializable id) {
		return getHibernateTemplate().get(cla, id);
	}

	protected void saveOrUpdate(Object object) {
		object = setTenantIdToCurrentPo(object);
		getHibernateTemplate().saveOrUpdate(object);
	}

	public Session openSession() {
		return SessionFactoryUtils.getSession(getSessionFactory(), true);
	}

	public PageResult getPageResult(String where, String currentPage,
			String pagerMethod, int pageSize) throws GlobalException {
		where = TentantIdSetAdvice.setTenantIdToHSql(where);// 查询时
		PageBean pager = null;
		int totalRows = 0;
		Session session = openSession();
		Query query = session.createQuery(where);
		Query query2 = session.createQuery("SELECT COUNT(*) " + where);
		List list2 = query2.list();
		if (null != list2&&0<list2.size()) {
			totalRows = Integer.valueOf(list2.get(0).toString());
		}
		int pageSizeTemp=pageSize;
		if(0==pageSizeTemp){
			if(0==totalRows)
				pageSize=10;
			pageSize=totalRows;
		}
		pager = new PageBean(totalRows, pageSize);
		
		if (currentPage != null && !"".equals(currentPage)
				&& !"null".equals(currentPage)) {
			try {
				pager.refresh(Integer.parseInt(currentPage));
			} catch (Exception e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
			
		}

		if (pagerMethod != null && !"".equals(pagerMethod)
				&& !"null".equals(pagerMethod)) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		//设置跳码
		pager.jumpPage();
		
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		List resultList = query.list();

		PageResult pagerResult = new PageResult();
		List columnList = new ArrayList();
		if(pageSizeTemp==0)
			pager.setPageSize(pageSizeTemp);
		pagerResult.setPageBean(pager);
		pagerResult.setResultList(resultList);
		pagerResult.setColumnList(columnList);
		
		return pagerResult;
	}
	public PageResult getPageResult(String hql,PageResult pageResult) {
		hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		PageBean pager = null;
		int totalRows = 0;
		Session session = openSession();
		String orderColumn = pageResult.getOrderColumn();
		if(null!=pageResult.getOrderColumn()&&!"".equals(pageResult.getOrderColumn())){
			if(hql.toUpperCase().contains("ORDER")){
				hql =hql.trim()+","+orderColumn.replaceAll("Vo.", ".")+" "+pageResult.getOrder();
			}else{
				hql +=" ORDER BY "+orderColumn.replaceAll("Vo.", ".")+" "+pageResult.getOrder();
			}
		}
		Query query = session.createQuery(hql);
		Query query2 = session.createQuery("SELECT COUNT(*) " + hql);
		List list2 = query2.list();
		if (null != list2&&0<list2.size()) {
			totalRows = Integer.valueOf(list2.get(0).toString());
		}
		int pageSize=pageResult.getPageSize();
		if(0==pageSize){
			if(0==totalRows)
				pageResult.setPageSize(10);
			pageResult.setPageSize(totalRows);
		}
		pager = new PageBean(totalRows, pageResult.getPageSize());
		if (pageResult.getCurrentPage() != null && !"".equals(pageResult.getCurrentPage())
				&& !"null".equals(pageResult.getCurrentPage())) {
			try {
				pager.refresh(Integer.parseInt(pageResult.getCurrentPage()));
			} catch (Exception e) {
				//e.printStackTrace();
				Log4J.error("", e);
			}
		}

		if (pageResult.getPagerMethod() != null && !"".equals(pageResult.getPagerMethod())
				&& !"null".equals(pageResult.getPagerMethod())) {
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
		
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		List resultList = query.list();

		List columnList = new ArrayList();
		pageResult.setPageBean(pager);
		pageResult.setResultList(resultList);
		pageResult.setColumnList(columnList);
		if(0==pageSize){
			pageResult.setPageSize(pageSize);
		}
		return pageResult;
	}
	public PageResult getPageResult4All(String hql,PageResult pageResult) throws GlobalException {
		hql = TentantIdSetAdvice.setTenantId2HSql4All(hql);
		PageBean pager = null;
		int totalRows = 0;
		Session session = openSession();
		String orderColumn = pageResult.getOrderColumn();
		if(null!=pageResult.getOrderColumn()&&!"".equals(pageResult.getOrderColumn())){
			if(hql.toUpperCase().contains("ORDER")){
				hql =hql.trim()+","+orderColumn.replaceAll("Vo.", ".")+" "+pageResult.getOrder();
			}else{
				hql +=" ORDER BY "+orderColumn.replaceAll("Vo.", ".")+" "+pageResult.getOrder();
			}
		}
		Query query = session.createQuery(hql);
		Query query2 = session.createQuery("SELECT COUNT(*) " + hql);
		List list2 = query2.list();
		if (null != list2&&0<list2.size()) {
			totalRows = Integer.valueOf(list2.get(0).toString());
		}
		int pageSize=pageResult.getPageSize();
		if(0==pageSize){
			if(0==totalRows)
				pageResult.setPageSize(10);
			pageResult.setPageSize(totalRows);
		}
		pager = new PageBean(totalRows, pageResult.getPageSize());
		if (pageResult.getCurrentPage() != null && !"".equals(pageResult.getCurrentPage())
				&& !"null".equals(pageResult.getCurrentPage())) {
			try {
				pager.refresh(Integer.parseInt(pageResult.getCurrentPage()));
			} catch (Exception e) {
				//e.printStackTrace();	
				throw new GlobalException("" + e.getMessage());
			}
		}

		if (pageResult.getPagerMethod() != null && !"".equals(pageResult.getPagerMethod())
				&& !"null".equals(pageResult.getPagerMethod())) {
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
		
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		List resultList = query.list();

		List columnList = new ArrayList();
		pageResult.setPageBean(pager);
		pageResult.setResultList(resultList);
		pageResult.setColumnList(columnList);
		if(0==pageSize){
			pageResult.setPageSize(pageSize);
		}
		return pageResult;
	}
	public PageResult getPageResultBySQL(String sql,PageResult pageResult) {
		sql = TentantIdSetAdvice.setTenantIdToHSql(sql);// 查询时
		PageBean pager = null;
		Session session = openSession();
		Query query = session.createSQLQuery(sql);
		int totalRows = 0;
		List resultList = query.list();
		if (null != resultList && resultList.size() > 0) {
			totalRows = resultList.size();
		}
		int pageSize=pageResult.getPageSize();
		if(0==pageSize){
			if(0==totalRows)
				pageResult.setPageSize(10);
			pageResult.setPageSize(totalRows);
		}
		pager = new PageBean(totalRows, pageResult.getPageSize());

		if (pageResult.getCurrentPage() != null && !"".equals(pageResult.getCurrentPage())
				&& !"null".equals(pageResult.getCurrentPage())) {
			pager.refresh(Integer.parseInt(pageResult.getCurrentPage()));
		}
		if (pageResult.getPagerMethod() != null && !"".equals(pageResult.getPagerMethod())
				&& !"null".equals(pageResult.getPagerMethod())) {
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
		query.setFirstResult(pager.getStartRow());

		query.setMaxResults(pager.getPageSize());
		if (null != query && null != query.list() && query.list().size() > 0) {
			resultList = query.list();
		}
		PageResult pagerResult = new PageResult();
		List columnList = new ArrayList();
		pagerResult.setPageBean(pager);
		pagerResult.setResultList(resultList);
		pagerResult.setColumnList(columnList);
		if(0==pageSize){
			pageResult.setPageSize(pageSize);
		}
		return pagerResult;
	}


	public double getCount(String hql) {
		if (SystemInstance.isMutiTenant()) {
			hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		}
		Iterator iter = getHibernateTemplate().iterate(hql);
		if ((iter != null) && (iter.hasNext())) {
			Object ob = iter.next();
			if (ob != null) {
				return ((Double) ob).doubleValue();
			}

			return 0.0D;
		}

		return 0.0D;
	}

	public Object getColumnMaxValue(String hql) {
		if (SystemInstance.isMutiTenant()) {
			hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		}
		Iterator iter = getHibernateTemplate().iterate(hql);
		if ((iter != null) && (iter.hasNext())) {
			Object ob = iter.next();
			if (ob != null) {
				return ob;
			}
			return null;
		}

		return null;
	}

	public long getCountSize(String hql) {
		if (SystemInstance.isMutiTenant()) {
			hql = TentantIdSetAdvice.setTenantIdToHSql(hql);// 查询时
		}
		Iterator iter = getHibernateTemplate().iterate(hql);
		if ((iter != null) && (iter.hasNext())) {
			Object ob = iter.next();
			if (ob != null) {
				return ((Long) ob).longValue();
			}

			return 0L;
		}

		return 0L;
	}

	public List createSqlQuery(String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		if (SystemInstance.isMutiTenant()) {
			sql = TentantIdSetAdvice.setTenantIdToHSql(sql);// 查询时
		}
		Query query = session.createSQLQuery(sql);

		List buData = query.list();

		return buData;
	}

	/**
	 * @param
	 * @return
	 * @throws GlobalException
	 * @throws @
	 *         description 鑾峰彇瀵硅薄
	 */
	
	@Override
	public void executeSQL(String sql) throws Exception {
		Transaction trans = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().beginTransaction();
		Connection conn = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().connection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		// stmt.execute();

	}

	@Override
	public List findByIds(Class className, String[] ids) {
		Criteria criteria = getSession().createCriteria(className);
		criteria.add(Restrictions.in("id", ids));
		/*List resultList = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			Object o = new Object();
			o = this.findById(className, ids[i]);
			if(null!=o)resultList.add(o);
		}
		return resultList;
		 */
		return criteria.list();
	}

	@Override
	public List findByIds(Class className, String ids) {
		List l = null;
		if(null!=ids&&ids.length()>0){
			ids = ids.replaceAll(ids, "");
			l = findByIds(className, StrUtils.split(ids, ","));
		}
		return l;

	}
	private Object setTenantIdToCurrentPo(Object object){
		if(object instanceof BasePo){
			object = TentantIdSetAdvice.setTenantIdToObject((BasePo) object);// 新增时
		}else if(object instanceof AbstractBasePo){
			object = TentantIdSetAdvice.setTenantIdToObject((AbstractBasePo) object);// 新增时
		}
		return object;
	}

	
}