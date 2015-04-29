package cn.labsoft.labos.common.formula.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.formula.dao.ILabFormulaDAO;
import cn.labsoft.labos.common.formula.entity.LabFormula;
import cn.labsoft.labos.common.formula.service.ILabFormulaService;
import cn.labsoft.labos.common.formula.vo.LabFormulaVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Service("labFormulaService")
public class LabFormulaServiceImpl implements ILabFormulaService {
	@Resource
	private ILabFormulaDAO labFormulaDAO;
	@Override
	public LabFormulaVo addLabFormula(LabFormulaVo labFormulaVo) throws GlobalException {
		
		LabFormula labFormula=new LabFormula();
		try{
			labFormula=this.vo2Po(labFormulaVo, labFormula);
			labFormulaDAO.addLabFormula(labFormula);
			labFormulaVo.setId(labFormula.getId());
		}catch(Exception e){
			//e.printStackTrace();
			Log4J.error("LabFormulaServiceImpl addLabFormula  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labFormulaVo;
	}

	@Override
	public boolean deleteLabFormula(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labFormulaDAO.deleteLabFormula(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabFormulaServiceImpl deleteLabFormula  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabFormulaVo getLabFormula(String id) throws GlobalException {
		LabFormulaVo labFormulaVo=new LabFormulaVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabFormula labFormula=labFormulaDAO.getLabFormula(id);
				labFormulaVo=this.po2Vo(labFormula, labFormulaVo);
			}catch(Exception e){
				Log4J.error("LabFormulaServiceImpl getLabFormula  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labFormulaVo;
	}

	@Override
	public List<LabFormulaVo> getLabFormulaList(LabFormulaVo labFormulaVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabFormulaVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabFormulaPR(LabFormulaVo labFormulaVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabFormula WHERE isDel='"+Constants_Base.N+"'";
		if(!StrUtils.isBlankOrNull(labFormulaVo.getName())){
			hql += " AND name LIKE '%"+labFormulaVo.getName()+"%'";
		}
		pageResult=labFormulaDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabFormulaVo> listLabFormulaVo=new ArrayList<LabFormulaVo>();
			List<LabFormula> listLabFormula=new ArrayList<LabFormula>();
			listLabFormula=pageResult.getResultList();
			for(LabFormula labFormula:listLabFormula){
				LabFormulaVo vo=new LabFormulaVo();
				vo=this.po2Vo(labFormula, vo);
				listLabFormulaVo.add(vo);
			}
			pageResult.setResultList(listLabFormulaVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabFormula(LabFormulaVo labFormulaVo) throws GlobalException {
		LabFormula labFormula=new LabFormula();
		boolean key=true;
		try{
			labFormula=this.vo2Po(labFormulaVo, labFormula);
			labFormulaDAO.updateLabFormula(labFormula);
		}catch(Exception e){
			key=false;
			Log4J.error("LabFormulaServiceImpl updateLabFormula  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabFormula4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabFormula labFormula=labFormulaDAO.getLabFormula(id);
					labFormula.setIsDel(Constants_Base.Y);
					labFormulaDAO.updateLabFormula(labFormula);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabFormulaServiceImpl updateLabFormula4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabFormulaVo> getLabFormulaVoListByWhere(String wereHql) throws GlobalException{
		List<LabFormulaVo> listLabFormulaVo=new ArrayList<LabFormulaVo>();
		String hql="FROM LabFormula WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabFormula> listLabFormula=labFormulaDAO.find(hql);
		if(listLabFormula!=null&&listLabFormula.size()>0){
			for(LabFormula labFormula:listLabFormula){
				LabFormulaVo labFormulaVo=new LabFormulaVo();
				labFormulaVo=this.po2Vo(labFormula, labFormulaVo);
				listLabFormulaVo.add(labFormulaVo);
			}
		}
		return listLabFormulaVo;
	}
	public LabFormula vo2Po(LabFormulaVo labFormulaVo,LabFormula labFormula){
		BeanUtils.copyProperties(labFormulaVo, labFormula,new String[]{"isDel"});
		String parameter="";
		if(!StrUtils.isBlankOrNull(labFormulaVo.getEditor())){
			for(String editor:labFormulaVo.getEditor().split("#")){
				if(!StrUtils.isBlankOrNull(editor)&&editor.indexOf("{")!=-1){
					String name=editor.substring(1,editor.indexOf("}"));
					if(parameter.indexOf(name)==-1){
						parameter+=name;
						parameter+=",";
					}
				}
			}
		}
		labFormula.setParameter(parameter);
		return labFormula;
	}
	public LabFormulaVo po2Vo(LabFormula labFormula,LabFormulaVo labFormulaVo){
		BeanUtils.copyProperties(labFormula, labFormulaVo);
		return labFormulaVo;
	}
}
