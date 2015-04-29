package cn.labsoft.labos.base.configs.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.configs.dao.ILabConfigDAO;
import cn.labsoft.labos.base.configs.entity.LabConfig;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.StrUtils;
@Service("labConfigService")
public class LabConfigServiceImpl implements ILabConfigService{
	private ILabConfigDAO labConfigDAO;
	
	@Resource
	public void setLabConfigDAO(ILabConfigDAO labConfigDAO) {
		this.labConfigDAO = labConfigDAO;
	}

	@Override
	public boolean addLabConfig(LabConfigVo labConfigVo) throws GlobalException {
		boolean key = false;
		try{
			LabConfig newPo = new LabConfig();
			newPo.setValue(labConfigVo.getValue().trim());
			newPo.setInitValue(labConfigVo.getInitValue().trim());
			newPo.setCategory(labConfigVo.getCategory().trim());
			newPo.setIsUsed("Y");
			newPo.setCode(labConfigVo.getCode().trim());
			newPo.setName(labConfigVo.getName().trim());
			newPo.setRemark(labConfigVo.getRemark());
			newPo.setDesc(labConfigVo.getDesc());
			newPo.setIsDel(Constants_Common.N);
			labConfigDAO.addLabConfig(newPo);
			key = true;
		}catch(Exception e){
			Log4J.error("添加系统配置出错！" + e.getMessage());
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabConfigVo> getLabConfigListAll() throws GlobalException {
		List<LabConfigVo> result = new ArrayList<LabConfigVo>();
		String hql = "FROM LabConfig WHERE 1=1 AND isDel='"+Constants_Common.N+"' ORDER BY category";
		List<LabConfig>  resultPo = labConfigDAO.find(hql);
		if(resultPo!=null){
			for (LabConfig labConfig : resultPo) {
				LabConfigVo labConfigVo=new LabConfigVo();
				BeanUtils.copyProperties(labConfig, labConfigVo);
				result.add(labConfigVo);
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LabConfigVo getLabConfigByCode(String code)throws GlobalException{
		LabConfigVo vo=new LabConfigVo();
		String sql="FROM LabConfig WHERE 1=1 AND code='"+code+"'";
		List<LabConfig> list=labConfigDAO.find(sql);
		if(null!=list&&list.size()>0){
			LabConfig po=list.get(0);
			BeanUtils.copyProperties(po, vo, new String[]{});
		}
		return vo;
	}
	public List<LabConfigVo> getLabConfigByCategory(String category)throws GlobalException{
		LabConfigVo labConfigVo=new LabConfigVo();
		String sql="FROM LabConfig WHERE 1=1 AND category='"+category+"'";
		List<LabConfig> list=labConfigDAO.find(sql);
		List<LabConfigVo> voList=new ArrayList<LabConfigVo>();
		if(null!=list&&list.size()>0){
			for(LabConfig labConfig :list){
				labConfigVo.setCategory(labConfig.getCategory());
				labConfigVo.setCode(labConfig.getCode());
				labConfigVo.setValue(labConfig.getValue());
				voList.add(labConfigVo);
			}
		}
		return voList;
	}
	@Override
	public String getLabConfigValue(String code)throws GlobalException{
		return getLabConfigByCode(code).getValue();
	}

	@Override
	public boolean updateLabConfig(LabConfigVo labConfigVo)
			throws GlobalException {
		try {
			LabConfig newPo = (LabConfig)labConfigDAO.findById(LabConfig.class, labConfigVo.getId());
			newPo.setValue(labConfigVo.getValue().trim());
			newPo.setInitValue(labConfigVo.getInitValue().trim());
			newPo.setCategory(labConfigVo.getCategory().trim());
			newPo.setCode(labConfigVo.getCode().trim());
			newPo.setName(labConfigVo.getName().trim());
			newPo.setRemark(labConfigVo.getRemark());
			newPo.setDesc(labConfigVo.getDesc());
			labConfigDAO.updateLabConfig(newPo);
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLabConfig4Del(String id) throws GlobalException {
		try {
			LabConfig newPo = (LabConfig)labConfigDAO.findById(LabConfig.class,id);
			newPo.setIsDel(Constants_Common.Y);
			labConfigDAO.updateLabConfig(newPo);
			return true;
		} catch (RuntimeException e) {
			//e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean addLabConfig4InitSystem() throws GlobalException {
		String filepath=ServletActionContext.getRequest().getRealPath("/sql/systemInit.sql");
		BufferedReader bufferedReader = null;
		List<String> lineStrList = new ArrayList<String>();
		List<String> returnLineStrList = new ArrayList<String>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(filepath)), "UTF-8"));

			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {
				lineStrList.add(tempString);
			}

			StringBuffer newLineStrBuffer = new StringBuffer();
			for (String str : lineStrList) {
				if (null != str && !str.startsWith("/*") && !str.startsWith("--")) {
					if (str.endsWith(";")) {
						newLineStrBuffer.append(str.trim() + "\n");
						returnLineStrList.add(newLineStrBuffer.toString());
						newLineStrBuffer = new StringBuffer();
					} else {
						newLineStrBuffer.append(str.trim() + " ");
					}
				}
			}
//			Connection conn = JDBCTools.getConnection();
//			Statement st = conn.createStatement();
//			for (String lineSqlStr : returnLineStrList) {
//				try {
//					st.execute(lineSqlStr.substring(0,
//							lineSqlStr.length() - 1));
//				} catch (Exception x) {
//					System.out.println("LINESQLSTR:" + lineSqlStr);
//					x.printStackTrace();
//				}
//			}
//			if (null != st) {
//				st.close();
//			}
		} catch(Exception e){
			//e.printStackTrace();
			throw new GlobalException("读取系统初始sql脚本出错！" + e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean hasLabConfig4Code(String code,String id)throws GlobalException{
		String sql="FROM LabConfig WHERE 1=1 AND code='"+code+"'";
		if(!StrUtils.isBlankOrNull(id)){
			sql+=" AND id!='"+id+"'";
		}
		List<LabConfig> list=labConfigDAO.find(sql);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean initLabConfig4Value(LabConfigVo labConfigVo)throws GlobalException{
		boolean flag = false;
		List<LabConfigVo> result = new ArrayList<LabConfigVo>();
		String hql = "FROM LabConfig WHERE 1=1 AND isDel='"+Constants_Base.N+"'";
		List<LabConfig>  configList = labConfigDAO.find(hql);
		if(configList!=null){
			for (LabConfig labConfig : configList) {
				labConfig.setValue(labConfig.getInitValue());
				labConfigDAO.updateLabConfig(labConfig);
			}
			flag=true;
		}
		return flag;
	}
	
}
