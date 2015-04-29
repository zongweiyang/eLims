package cn.labsoft.labos.common.formula.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.formula.dao.ILabFormulaApplyDAO;
import cn.labsoft.labos.common.formula.dao.ILabFormulaDAO;
import cn.labsoft.labos.common.formula.entity.LabFormula;
import cn.labsoft.labos.common.formula.entity.LabFormulaApply;
import cn.labsoft.labos.common.formula.service.ILabFormulaApplyService;
import cn.labsoft.labos.common.formula.vo.LabFormulaApplyVo;
import cn.labsoft.labos.common.page.dao.ILabPageEditorDAO;
import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.FileOperater;
import cn.labsoft.labos.utils.StrUtils;

@Service("labFormulaApplyService")
public class LabFormulaApplyServiceImpl implements ILabFormulaApplyService {
	private ILabFormulaApplyDAO labFormulaApplyDAO;
	private ILabFormulaDAO labFormulaDAO;
	private ILabPageEditorDAO labPageEditorDAO;

	
	
	@Resource
	public void setLabFormulaApplyDAO(ILabFormulaApplyDAO labFormulaApplyDAO) {
		this.labFormulaApplyDAO = labFormulaApplyDAO;
	}
	@Resource
	public void setLabFormulaDAO(ILabFormulaDAO labFormulaDAO) {
		this.labFormulaDAO = labFormulaDAO;
	}
	@Resource
	public void setLabPageEditorDAO(ILabPageEditorDAO labPageEditorDAO) {
		this.labPageEditorDAO = labPageEditorDAO;
	}

	@Override
	public LabFormulaApplyVo addLabFormulaApply(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException {
		
		LabFormulaApply labFormulaApply=new LabFormulaApply();
		try{
			labFormulaApply=this.vo2Po(labFormulaApplyVo, labFormulaApply);
			labFormulaApplyDAO.addLabFormulaApply(labFormulaApply);
			labFormulaApplyVo.setId(labFormulaApply.getId());
			writeFile(labFormulaApply,LabFormulaApply.WRITEINPUT);
		}catch(Exception e){
			//e.printStackTrace();
			Log4J.error("LabFormulaApplyServiceImpl addLabFormulaApply  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labFormulaApplyVo;
	}

	@Override
	public boolean deleteLabFormulaApply(String[] ids) {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				deleteJspLabFormula(ids);
				key=labFormulaApplyDAO.deleteLabFormulaApply(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabFormulaApplyServiceImpl deleteLabFormulaApply  error..."+e.getMessage(), e);
		}
		return key;
	}

	@Override
	public LabFormulaApplyVo getLabFormulaApply(String id) throws GlobalException {
		LabFormulaApplyVo labFormulaApplyVo=new LabFormulaApplyVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabFormulaApply labFormulaApply=labFormulaApplyDAO.getLabFormulaApply(id);
				labFormulaApplyVo=this.po2Vo(labFormulaApply, labFormulaApplyVo);
			}catch(Exception e){
				Log4J.error("LabFormulaApplyServiceImpl getLabFormulaApply  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labFormulaApplyVo;
	}

	@Override
	public List<LabFormulaApplyVo> getLabFormulaApplyList(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabFormulaApplyVoListByWhere(wereHql);
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabFormulaApplyPR(LabFormulaApplyVo labFormulaApplyVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabFormulaApply WHERE isDel='"+Constants_Base.N+"'";
		if(!StrUtils.isBlankOrNull(labFormulaApplyVo.getLabFormulaName()))
			hql+=" AND labFormula.name LIKE '%"+labFormulaApplyVo.getLabFormulaName()+"%'";
		pageResult=labFormulaApplyDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabFormulaApplyVo> listLabFormulaApplyVo=new ArrayList<LabFormulaApplyVo>();
			List<LabFormulaApply> listLabFormulaApply=new ArrayList<LabFormulaApply>();
			listLabFormulaApply=pageResult.getResultList();
			for(LabFormulaApply labFormulaApply:listLabFormulaApply){
				LabFormulaApplyVo vo=new LabFormulaApplyVo();
				vo=this.po2Vo(labFormulaApply, vo);
				listLabFormulaApplyVo.add(vo);
			}
			pageResult.setResultList(listLabFormulaApplyVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabFormulaApply(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException {
		boolean key=true;
		try{
			this.deleteLabFormulaApply(new String[]{labFormulaApplyVo.getId()});
			this.addLabFormulaApply(labFormulaApplyVo);
		}catch(Exception e){
			key=false;
			Log4J.error("LabFormulaApplyServiceImpl updateLabFormulaApply  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabFormulaApply4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabFormulaApply labFormulaApply=labFormulaApplyDAO.getLabFormulaApply(id);
					labFormulaApply.setIsDel(Constants_Base.Y);
					labFormulaApplyDAO.updateLabFormulaApply(labFormulaApply);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabFormulaApplyServiceImpl updateLabFormulaApply4Del  error..."+e.getMessage(), e);
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabFormulaApplyVo> getLabFormulaApplyVoListByWhere(String wereHql) throws GlobalException{
		List<LabFormulaApplyVo> listLabFormulaApplyVo=new ArrayList<LabFormulaApplyVo>();
		String hql="FROM LabFormulaApply WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabFormulaApply> listLabFormulaApply=labFormulaApplyDAO.find(hql);
		if(listLabFormulaApply!=null&&listLabFormulaApply.size()>0){
			for(LabFormulaApply labFormulaApply:listLabFormulaApply){
				LabFormulaApplyVo labFormulaApplyVo=new LabFormulaApplyVo();
				labFormulaApplyVo=this.po2Vo(labFormulaApply, labFormulaApplyVo);
				listLabFormulaApplyVo.add(labFormulaApplyVo);
			}
		}
		return listLabFormulaApplyVo;
	}
		public LabFormulaApply vo2Po(LabFormulaApplyVo labFormulaApplyVo,LabFormulaApply labFormulaApply) throws GlobalException{
			BeanUtils.copyProperties(labFormulaApplyVo, labFormulaApply,new String[]{"isDel","labFormulaId","labPageEditorId"});
		if(!StrUtils.isBlankOrNull(labFormulaApplyVo.getLabFormulaId())){
			LabFormula labFormula=labFormulaDAO.getLabFormula(labFormulaApplyVo.getLabFormulaId());
			labFormulaApply.setLabFormula(labFormula);
		}
		if((labFormulaApplyVo.getNames()!=null)&&(labFormulaApplyVo.getNames().length>0)){
			String parmaterNames="";
			for(String name:labFormulaApplyVo.getNames()){
				parmaterNames+=name;
				parmaterNames+=",";
			}
			labFormulaApply.setParameterId(parmaterNames);
		}
		if(!StrUtils.isBlankOrNull(labFormulaApplyVo.getLabPageEditorId())){
			LabPageEditor labPageEditor=labPageEditorDAO.getLabPageEditor(labFormulaApplyVo.getLabPageEditorId());
			labFormulaApply.setLabPageEditor(labPageEditor);
		}
		return labFormulaApply;
	}
	public LabFormulaApplyVo po2Vo(LabFormulaApply labFormulaApply,LabFormulaApplyVo labFormulaApplyVo){
		BeanUtils.copyProperties(labFormulaApply, labFormulaApplyVo,new String[]{"labFormula","labPageEditor"});
		if(labFormulaApply.getLabFormula()!=null){
			labFormulaApplyVo.setLabFormulaId(labFormulaApply.getLabFormula().getId());
			labFormulaApplyVo.setLabFormulaName(labFormulaApply.getLabFormula().getName());
			labFormulaApplyVo.setParameter(labFormulaApply.getLabFormula().getParameter());
			labFormulaApplyVo.setLabFormulaEditor(labFormulaApply.getLabFormula().getEditor());
		}
		if(labFormulaApply.getLabPageEditor()!=null){
			labFormulaApplyVo.setLabPageEditorId(labFormulaApply.getLabPageEditor().getId());
			labFormulaApplyVo.setJspUrl(labFormulaApply.getLabPageEditor().getUrl());
		}
		
		return labFormulaApplyVo;
	}

	@Override
	public String getResult(String result,String forAppId)
			throws GlobalException{
		LabFormulaApply labFormulaApply=labFormulaApplyDAO.getLabFormulaApply(forAppId);
		String[] parmater=null;
		String returnValue="";
		if(!StrUtils.isBlankOrNull(labFormulaApply.getLabFormula().getParameter())){
			parmater=labFormulaApply.getLabFormula().getParameter().split(",");
		}
		Evaluator eva = new Evaluator();
		if(parmater!=null&&parmater.length>0&&result!=null&&result.split(",").length>0){
			for(int i=0;i<parmater.length;i++){
				if(!StrUtils.isBlankOrNull(parmater[i]))
					if(result.split(",").length == parmater.length){
						eva.putVariable(parmater[i], String.valueOf(result.split(",")[i]));
					}
			}
			try {
				returnValue=eva.evaluate(labFormulaApply.getLabFormula().getEditor());
			} catch (EvaluationException e) {
				returnValue="";
				throw new GlobalException("" + e.getMessage());
			}
		}
		return returnValue;
	}
	@SuppressWarnings("unchecked")
	public List<LabFormulaApplyVo> getLabFormulaApplyListByWhere(String whereHql)  throws GlobalException{
		List<LabFormulaApplyVo> listLabFormulaApplyVo=new ArrayList<LabFormulaApplyVo>();
		String hql="FROM LabFormulaApply WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(whereHql))
			hql+=whereHql;
		List<LabFormulaApply> listLabFormulaApply=labFormulaApplyDAO.find(hql);
		if(listLabFormulaApply!=null&&listLabFormulaApply.size()>0){
			for(LabFormulaApply labFormulaApply:listLabFormulaApply){
				LabFormulaApplyVo labFormulaApplyVo=new LabFormulaApplyVo();
				this.po2Vo(labFormulaApply, labFormulaApplyVo);
				listLabFormulaApplyVo.add(labFormulaApplyVo);
			}
		}
		return listLabFormulaApplyVo;
	}
	@SuppressWarnings("deprecation")
	public  void writeFile(LabFormulaApply labFormulaApply,String type) throws GlobalException{
		String path = (ServletActionContext.getRequest().getRealPath("/") + labFormulaApply.getLabPageEditor().getUrl()).replace("/", File.separator);
		List<String> listLine=FileOperater.readFileByLines(path);
		List<String> wirteLine=new ArrayList<String>();
		if(listLine!=null&&listLine.size()>0){
			for(String line:listLine){
				if(type.equals(LabFormulaApply.WRITEINPUT)){
					if(line.trim().indexOf("<script")>-1){
						StringBuffer writeString=new StringBuffer();
						writeString.append("	$(function(){");
						writeString.append("$('#"+labFormulaApply.getValueId()+"').click(function(){");
						writeString.append("formula('"+labFormulaApply.getId()+"');");
						writeString.append("});");
						writeString.append("});");
						wirteLine.add(line);
						wirteLine.add(writeString.toString());
					}else
						wirteLine.add(line);
				}else if(type.equals(LabFormulaApply.WRITEOUTPUT)){
					if(line.trim().indexOf(labFormulaApply.getId())>-1)
						continue;
					else
						wirteLine.add(line);
				}
			}
		}
		FileOperater.writeFileByLines(path, wirteLine);
	}
	public void writeJavaScrip(String type,String url,List<String> listLine){
		if(type.equals(LabFormulaApply.WRITEINPUT)){
			if(listLine!=null&&listLine.size()>0){
				@SuppressWarnings("unused")
				boolean key=false;
				for(String line:listLine){
					if(line.indexOf("<script")>-1){
						key=true;
					}
				}
			}
			
		}
		
	}
	public boolean deleteJspLabFormula(String[] ids) throws GlobalException{
		boolean key=true;
			if(ids!=null&&ids.length>0){
				try{
				for(String id:ids){
					LabFormulaApply labFormulaApply=labFormulaApplyDAO.getLabFormulaApply(id);
					writeFile(labFormulaApply,LabFormulaApply.WRITEOUTPUT);
				}
				}catch(Exception e){
					key=false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		return key;
	}
//	public static void main(String[] args) {
//		String ids="one,two,three";
//		String jspUrl="E:\\workspace\\LABOS-CORE\\WebRoot\\jsp\\common\\jevel\\jevelMain.jsp";
//		String writeUrl="E:\\workspace\\LABOS-CORE\\WebRoot\\jsp\\common\\jevel\\stone.jsp";
		
//	}


}
