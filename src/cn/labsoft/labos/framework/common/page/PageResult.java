package cn.labsoft.labos.framework.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * <strong>Title : PageResult </strong>. <br>
 * <strong>Description : 分页结果对象</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author <br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("unchecked")
public class PageResult implements Serializable {
	private static final long serialVersionUID = -4500981451969706619L;
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";
	private List<String> searchBy; //查找字段
	private String pagerMethod;
	private String currentPage;
	private String action;
	private Integer pageSize;
	private String order = ORDER_DESC;
	/**
	 * 排序字段
	 * */
	private String orderColumn;
	/**
	 * 分页结果
	 */
	private List resultList = new ArrayList();

	private List columnList = new ArrayList();
	/**
	 * 分页信息
	 */
	private PageBean pageBean;
	
	
	public List getResultList() {
		return resultList;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}

	public String getCurrentPage() {
		if(currentPage==null)currentPage="1";
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		if(pageSize==null)pageSize=10;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getColumnList() {
		return columnList;
	}

	public void setColumnList(List columnList) {
		this.columnList = columnList;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setResultList(List resultList) {
		action = ServletActionContext.getRequest().getRequestURI().replaceAll("//", "/");
		this.resultList = resultList;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<String> getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(List<String> searchBy) {
		this.searchBy = searchBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

	

}
