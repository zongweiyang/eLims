package cn.labsoft.labos.framework.common.page;

/**
 * 
 * <strong>Title : PageVo </strong>. <br>
 * <strong>Description : 分页信息对象</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM  </strong>. <br>
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
public class PageVo {
	protected String where = "";  
	//保存分页结果
	protected PageResult pageResult;
    //当前页

	protected String currentPage;
	//分页方法
	protected String pagerMethod;
	

	public String getWhere()
	{
		return where;
	}

	public void setWhere(String where)
	{
		this.where = where;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}
}
