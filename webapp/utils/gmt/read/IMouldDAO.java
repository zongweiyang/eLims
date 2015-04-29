package packSrc;

import java.util.List;

import entitySrc;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface IMouldDAO extends IBaseDAO {
	
	public Mould addMould(Mould mould) throws GlobalException;
	
	public boolean deleteMould(String ids[]) throws GlobalException;
	
	public boolean deleteMould(Mould mould) throws GlobalException;
	
	public boolean updateMould(Mould mould) throws GlobalException;
	
	public Mould getMould(String id) throws GlobalException;
	
	public PageResult getMouldPR(String hql, PageResult pageResult) throws GlobalException;
	
	public List<Mould> getMouldList(String hql) throws GlobalException;
	
}
