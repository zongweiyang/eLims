package cn.labsoft.labos.common.template.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.configs.dao.ILabConfigDAO;
import cn.labsoft.labos.base.configs.entity.LabConfig;
import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.common.template.dao.ILabTemplateDAO;
import cn.labsoft.labos.common.template.entity.LabTemplate;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.common.template.vo.LabTemplateVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 页面管理Service实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Service(value = "labTemplateService")
public class LabTemplateServiceImpl extends BaseService implements ILabTemplateService {
	private ILabTemplateDAO labTemplateDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabConfigDAO labConfigDAO;

	/**
	 * 模板管理DAO注入
	 * 
	 * @param labTemplateDAO
	 */
	@Resource
	public void setLabTemplateDAO(ILabTemplateDAO labTemplateDAO) {
		this.labTemplateDAO = labTemplateDAO;
	}

	/**
	 * 功能管理DAO注入
	 * 
	 * @param labFunctionDAO
	 */
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}

	/**
	 * 配置管理DAO注入
	 * 
	 * @param labConfigDAO
	 */
	@Resource
	public void setLabConfigDAO(ILabConfigDAO labConfigDAO) {
		this.labConfigDAO = labConfigDAO;
	}

	/**
	 * @Title: 增加模板
	 * @param labTemplateVo
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	@Override
	public LabTemplateVo addLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException {
		LabTemplate labTemplate = new LabTemplate();
		BeanUtils.copyProperties(labTemplateVo, labTemplate, new String[] {});
		labTemplate.setIsDel(Constants_Base.N);
		labTemplateDAO.addLabTemplate(labTemplate);
		return labTemplateVo;
	}

	@Override
	public LabTemplateVo getLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException {
		LabTemplate labTemplate = (LabTemplate) labTemplateDAO.findById(LabTemplate.class, labTemplateVo.getId());
		BeanUtils.copyProperties(labTemplate, labTemplateVo, new String[] {});
		return labTemplateVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabTemplatePR(PageResult pageResult, LabTemplateVo labTemplateVo) throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabTemplate WHERE 1=1 AND isDel = '" + Constants_Base.N + "'");

		// modify by danlee begin
		if (!StrUtils.isBlankOrNull(labTemplateVo.getName())) {
			hql.append(" AND name LIKE '%" + labTemplateVo.getName() + "%'");
		}
		// modify by danlee end
		pageResult = labTemplateDAO.getPageResult(hql.toString(), pageResult);
		List<LabTemplateVo> voList = new ArrayList<LabTemplateVo>();
		List<LabTemplate> poList = pageResult.getResultList();
		if (null != poList && poList.size() > 0) {
			for (LabTemplate po : poList) {
				LabTemplateVo vo = new LabTemplateVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				if (null != po.getBusId()) {
					LabFunction labFunction = labFunctionDAO.getLabFunction(po.getBusId());
					vo.setBusName(labFunction.getName());
				}
				voList.add(vo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	/**
	 * @Title: 删除模板
	 * @param labTemplateVo
	 * @return boolean
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabTemplate4Del(LabTemplateVo labTemplateVo) throws GlobalException {
		List<LabTemplate> listLabTemplate = labTemplateDAO.findByIds(LabTemplate.class, labTemplateVo.getIds());
		try {
			if (null != listLabTemplate && listLabTemplate.size() > 0) {
				for (LabTemplate labTemplate : listLabTemplate) {
					labTemplate.setIsDel(Constants_Base.Y);
					labTemplateDAO.updateLabTemplate(labTemplate);
				}
			}
			return true;
		} catch (Exception e) {
			Log4J.err("删除模板出错...LabTemplateServiceImpl...");
			throw new GlobalException("" + e.getMessage());
		}
	}

	/**
	 * @Title: 修改模板
	 * @param labTemplateVo
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	@Override
	public LabTemplateVo updateLabTemplate(LabTemplateVo labTemplateVo) throws GlobalException {
		LabTemplate labTemplate = (LabTemplate) labTemplateDAO.findById(LabTemplate.class, labTemplateVo.getId());
		BeanUtils.copyProperties(labTemplateVo, labTemplate, new String[] { "isDel" });
		labTemplateDAO.updateLabTemplate(labTemplate);
		return labTemplateVo;
	}

	/**
	 * @Title: 修改OFFICE模板内容
	 * @param labTemplateVo
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	@Override
	public LabTemplateVo updateLabTemplate4Edit(LabTemplateVo labTemplateVo) throws GlobalException {
		LabTemplate labTemplate = (LabTemplate) labTemplateDAO.findById(LabTemplate.class, labTemplateVo.getId());
		String path = "";
		if (labTemplate != null) {
			LabConfig labConfig = labConfigDAO.getLabConfigByCode("exportTemplate");
			if (null != labConfig && null != labConfig && null != labConfig.getValue()) {
				path = labConfig.getValue() + File.separator + labTemplate.getId() + ".xls";
			}
		}
		path = path.replace("\\", "/");
		labTemplate.setPath(path);
		labTemplateDAO.updateLabTemplate(labTemplate);
		return labTemplateVo;
	}

	/**
	 * @Title: 获取模板文件信息
	 * @param labTemplateVo
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public LabTemplateVo getLabTemplate4File(LabTemplateVo labTemplateVo) throws GlobalException {
		LabTemplate labTemplate = (LabTemplate) labTemplateDAO.findById(LabTemplate.class, labTemplateVo.getId());
		BeanUtils.copyProperties(labTemplate, labTemplateVo, new String[] {});
		if (labTemplate.getType() != null && labTemplate.getType().equals("excel")) {
			LabConfig labConfig = labConfigDAO.getLabConfigByCode("exportTemplate");
			String startPath = "";
			String savePath = "";
			if (null != labConfig && null != labConfig && null != labConfig.getValue()) {
				startPath = ServletActionContext.getRequest().getRealPath("/") + labConfig.getValue().replace("/", File.separator);
				savePath = labConfig.getValue() + File.separator + labTemplate.getId() + ".xls";
			}
			String path = startPath + File.separator + labTemplate.getId() + ".xls";
			File file = new File(path);
			if (!file.exists()) {
				String templatePath = startPath + File.separator + "template.xls";
				File template = new File(templatePath);
				if (template.exists()) {
					try {
						FileInputStream fin = new FileInputStream(template);
						BufferedInputStream bin = new BufferedInputStream(fin);
						PrintStream pout = new PrintStream(path);
						BufferedOutputStream bout = new BufferedOutputStream(pout);
						while (bin.available() != 0) {
							int c = bin.read(); //从输入流中读一个字节
							bout.write((char) c); //将字节（字符）写到输出流中     
						}
						bout.close();
						pout.close();
						bin.close();
						fin.close();
					} catch (FileNotFoundException e) {
						Log4J.err("LabReportServiceImpl.." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					} catch (IOException e) {
						Log4J.err("LabReportServiceImpl.." + e.getMessage());
						throw new GlobalException("" + e.getMessage());
					}
				}
			}
			savePath = savePath.replace("\\", "\\\\");
			labTemplateVo.setPath(savePath);
		}
		return labTemplateVo;
	}

	/**
	 * @Title: 根据业务ID获取模板信息
	 * @param labTemplateVo
	 * @return LabTemplateVo
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LabTemplateVo getLabTemplateByBusId(LabTemplateVo labTemplateVo) throws GlobalException {
		String hql = "FROM LabTemplate WHERE 1=1 AND busId = '" + labTemplateVo.getBusId() + "' AND isDel = '" + Constants_Base.N + "'";
		List<LabTemplate> list = labTemplateDAO.find(hql);
		LabTemplate labTemplate = new LabTemplate();
		if (null != list && list.size() > 0) {
			labTemplate = list.get(0);
		}
		BeanUtils.copyProperties(labTemplate, labTemplateVo, new String[] {});
		return labTemplateVo;
	}

	/**
	 * @Title: 根据功能id获取功能下的模版路径
	 * @param labTemplateVo
	 * @return String
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getLabTemplateByBusId(String busId) throws GlobalException {
		String hql = "select path FROM LabTemplate WHERE 1=1 AND busId = '" + busId + "' AND isDel = '" + Constants_Base.N + "'";
		List<String> list = labTemplateDAO.find(hql);
		if (null != list && list.size() > 0) {
			String path = list.get(0);
			if (null != path && path.trim().length() > 0) {
				return path;
			}
		}
		return null;
	}
}