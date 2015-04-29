package packSrc;

import java.util.List;

import org.springframework.stereotype.Repository;

import entitySrc;
import iDaoSrc;
import cn.labsoft.labos.constants.Constants;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="mouldDAO")
public class MouldDAOImpl extends BaseDAO implements IMouldDAO {
	
	@Override
	public Mould addMould(Mould mould)  throws GlobalException  {
		mould.setIsDel(Constants.N);
		mould.setCreateTime(DateUtils.getCurrDateTimeStr());
		super.save(mould);
		return mould;
	}

	@Override
	public boolean deleteMould(String ids[]) throws GlobalException{
		for (String id : ids) {
			super.delete(this.getMould(id));
		}
		return true;
	}

	@Override
	public Mould getMould(String id) throws GlobalException {
		Mould mould = (Mould) super.findById(Mould.class, id);
		return mould;
	}

	@Override
	public boolean updateMould(Mould mould) throws GlobalException {
		super.update(mould);
		return true;
	}
	@Override
	public boolean deleteMould(Mould mould)  throws GlobalException {
		super.delete(mould);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mould> getMouldList(String hql)  throws GlobalException {
		List<Mould> userList = super.find(hql);
		return userList;
	}

	@Override
	public PageResult getMouldPR(String hql, PageResult pageResult)  throws GlobalException {
		pageResult = super.getPageResult(hql, pageResult);
		return pageResult;
	}
	
}
