package cn.labsoft.labos.source.appara.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.dao.ILabApparaRepairDAO;
import cn.labsoft.labos.source.appara.service.ILabApparaRepairService;
@Service("labApparaRepairService")
public class LabApparaRepairServiceImpl implements ILabApparaRepairService {
	private ILabApparaRepairDAO labApparaRepairDAO;
	private ILabApparaDAO labApparaDAO;
	@Resource
	public void setLabApparaRepairDAO(ILabApparaRepairDAO labApparaRepairDAO) {
		this.labApparaRepairDAO = labApparaRepairDAO;
	}
	@Resource
	public void setLabApparaDAO(ILabApparaDAO labApparaDAO) {
		this.labApparaDAO = labApparaDAO;
	}
}
