package pageSrc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.constants.Constants;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import IDaoSrc;
import entitySrc;
import IServiceSrc;
import voSrc;

@Service("mouldService")
public class MouldServiceImpl extends BaseService implements IMouldService {
	private IMouldDAO mouldDAO;
	
	@Resource
	public void setMouldDAO(IMouldDAO mouldDAO) {
		this.mouldDAO = mouldDAO;
	}

	@Override
	public MouldVo addMould(MouldVo mouldVo) throws GlobalException {
		
		Mould mould=new Mould();
		mould=this.vo2Po(mouldVo, mould);
		mouldDAO.addMould(mould);
		mouldVo.setId(mould.getId());
		return mouldVo;
	}

	@Override
	public boolean deleteMould(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			key=mouldDAO.deleteMould(ids);
		}
		return key;
	}

	@Override
	public MouldVo getMould(String id) throws GlobalException {
		MouldVo mouldVo=new MouldVo();
		if(!StrUtils.isBlankOrNull(id)){
			Mould mould=mouldDAO.getMould(id);
			mouldVo=this.po2Vo(mould, mouldVo);
		}
		return mouldVo;
	}

	@Override
	public List<MouldVo> getMouldList(MouldVo mouldVo) throws GlobalException {
		String wereHql="";
		
		return this.getMouldVoListByWhere(wereHql);
	}

	@Override
	public PageResult getMouldPR(MouldVo mouldVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM Mould WHERE isDel='"+Constants.N+"'";
		pageResult=mouldDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<MouldVo> mouldVoList=new ArrayList<MouldVo>();
			List<Mould> listMould=new ArrayList<Mould>();
			listMould=pageResult.getResultList();
			for(Mould mould:listMould){
				MouldVo vo=new MouldVo();
				vo=this.po2Vo(mould, vo);
				mouldVoList.add(vo);
			}
			pageResult.setResultList(mouldVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateMould(MouldVo mouldVo) throws GlobalException {
		boolean key=true;
		if(!StrUtils.isBlankOrNull(mouldVo.getId())){
			Mould mould=mouldDAO.getMould(mouldVo.getId());
			if(mould!=null){
				mould=this.vo2Po(mouldVo, mould);
				mouldDAO.updateMould(mould);
			}
		}
		return key;
	}

	@Override
	public boolean updateMould4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
				for(String id:ids){
					Mould mould=mouldDAO.getMould(id);
					mould.setIsDel(Constants.Y);
					mouldDAO.updateMould(mould);
				}
		}
		return key;
	}
	public List<MouldVo> getMouldVoListByWhere(String wereHql) throws GlobalException{
		List<MouldVo> mouldVoList=new ArrayList<MouldVo>();
		String hql="FROM Mould WHERE isDel='"+Constants.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<Mould> mouldList=mouldDAO.find(hql);
		if(mouldList!=null&&mouldList.size()>0){
			for(Mould mould:mouldList){
				MouldVo mouldVo=new MouldVo();
				mouldVo=this.po2Vo(mould, mouldVo);
				mouldVoList.add(mouldVo);
			}
		}
		return mouldVoList;
	}
	public Mould vo2Po(MouldVo mouldVo,Mould mould){
		BeanUtils.copyProperties(mouldVo, mould,new String[]{"isDel","createTime","createUserId","tenantId"});
		return mould;
	}
	public MouldVo po2Vo(Mould mould,MouldVo mouldVo){
		BeanUtils.copyProperties(mould, mouldVo);
		return mouldVo;
	}
}
