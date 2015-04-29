package cn.labsoft.labos.framework.common.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * <strong>Title : PageBean </strong>. <br>
 * <strong>Description : 分页信息类</strong> <br>
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
public class PageBean
{
	private int totalRows;// 记录总行数
	private int pageSize;// 设置一页显示的条数
	private int currentPage;// 当前页面
	private int totalPages;// 总页数
	private int startRow;// 当前页码开始的行数
	private Integer []pageItem;

	public Integer[] getPageItem() {
		return pageItem;
	}


	public void setPageItem(Integer[] pageItem) {
		this.pageItem = pageItem;
	}


	/**
	 * 构造函数 进行初始化
	 * @param totalRows
	 * @param pageSize
	 */
	public PageBean(int totalRows,int pageSize)
	{
		this.pageSize=pageSize;
		this.totalRows = totalRows;
		totalPages = totalRows / pageSize;
		if (totalRows % pageSize > 0)
		{
			totalPages++;
		}
		currentPage = 1;
		startRow = 0;
	}
	

	// 首页
	public void first()
	{
		currentPage = 1;
		startRow = 0;
	}

	// 上一页
	public void previous()
	{
		if (currentPage == 1)
		{
			startRow = 0;
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
		
	}

	// 下一页
	public void next()
	{
		
		if (currentPage < totalPages)
		{
			currentPage++;
		
		}
		startRow = (currentPage - 1) * pageSize;
		
	}

	// 尾页
	public void last()
	{
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}
	//全部
	public void all()
	{
		
		startRow = 0;
		pageSize=this.totalRows;
		
	}
	
	//刷新page对象
	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			//last();
			currentPage = totalPages;
		}
		if(currentPage<1)
		{
			//first();
			currentPage=0;
		}
		if(currentPage==0)
			currentPage=1;
		startRow = (currentPage - 1) * pageSize;
//		if(_currentPage>totalPages){
//			currentPage = totalPages;
//		}else
//			if(_currentPage<=0){
//				_currentPage=1;
//				currentPage = _currentPage;
//			}else
//				currentPage = _currentPage;
//			
//		
//		startRow = (currentPage - 1) * pageSize;
	}
	/**
	 * setter & getter methods
	 * 
	 * @return
	 */
	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}


	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getStartRow()
	{
		return startRow;
	}

	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public void jumpPage(){
		if(pageItem==null){
			pageItem=new Integer[10];
		}
		
		if(getTotalPages()>10){
			List<Integer> list=new ArrayList<Integer>();
			
			if(currentPage<=5){
					if(currentPage==1){
					}else if(currentPage==2){
						list.add(1);
					}
					else if(currentPage==3){
						list.add(1);
						list.add(2);
					}
					else if(currentPage==4){
						list.add(1);
						list.add(2);
						list.add(3);
					}
					else if(currentPage==5){
						list.add(1);
						list.add(2);
						list.add(3);
						list.add(4);
					}
			}else{
				for(int i=0;i<5;i++){
					list.add(currentPage-i-1);
				}
			}
			
			list.add(currentPage);
			if(getTotalPages()-currentPage<4){
				for(int i=0;i<getTotalPages()-currentPage;i++){
					list.add(currentPage+i+1);
				}
			}else{
				for(int i=0;i<4;i++){
					list.add(currentPage+i+1);
				}
			}
			Collections.sort(list);
			pageItem=list.toArray(new Integer[0]);
		}else{
			pageItem=new Integer[getTotalPages()];
			for(int i=0;i<getTotalPages();i++){
				pageItem[i]=i+1;
			}
		}
		setPageItem(pageItem);
}
}
