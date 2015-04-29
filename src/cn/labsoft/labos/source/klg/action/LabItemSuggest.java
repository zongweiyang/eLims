package cn.labsoft.labos.source.klg.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import net.sf.json.JSONArray;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
@Component
public class LabItemSuggest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UNCODE="UTF-8";
	private ILabItemDAO labItemDAO;
	
	
	@Resource
	public void setLabItemDAO(ILabItemDAO labItemDAO) {
		this.labItemDAO = labItemDAO;
	}

	@Override
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(UNCODE);
		response.setContentType("text/html");
		response.setCharacterEncoding(UNCODE);
		
		String param=request.getParameter("q");
		int n=0;
		boolean bb=false; 
		for(int i=0; i<param.length(); i++) {
			n = param.charAt(i);
			if((19968 <= n && n <40623)) {//判断是否是中午
				bb=true;
				break;
			}
		}
		if(!bb){//若不是中午，换个方法获取参数
			param=new String(request.getParameter("q").getBytes("ISO-8859-1"),UNCODE);
		}
		@SuppressWarnings("unused")
		List<LabItem> itemList=null;
		
		PageResult pageResult=new PageResult();
		pageResult.setPageSize(12);
		
		List<String> searchBy=new ArrayList<String>();
		pageResult.setSearchBy(searchBy);
		String hql="FROM LabItem WHERE isDel = '"+Constants_Source.N+"'";
		hql+=" AND name LIKE '%"+param+"%'";
		pageResult=labItemDAO.getPageResult(hql,pageResult);
		itemList=pageResult.getResultList();
		//这里处理productList中的单位产地。在表中查询。
		String newList1 = "[";
		for(LabItem po:itemList){
			newList1 += "{'id':'"+po.getId()+"','name':'"+po.getName()+"','unit':'"+po.getUnit()+"'},";
		}
		if(newList1.length()>1){
			newList1=newList1.substring(0, newList1.length()-1)+"]";
		}else{
			newList1="]";
		}
		JSONArray jsonArray = JSONArray.fromObject(newList1);
		PrintWriter out = response.getWriter();
		out.write(jsonArray.toString());
		out.flush();
		out.close();
	}

	@Override
	public void init() throws ServletException {
	}

}
