package cn.labsoft.labos.business.science.dao;

import cn.labsoft.labos.business.science.entity.LabSciAuthor;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabSciAuthorDAO extends IBaseDAO {
	
	public LabSciAuthor addLabSciAuthor(LabSciAuthor labSciAuthor)throws GlobalException;
	
	public boolean updateLabSciAuthor(LabSciAuthor labSciAuthor)throws GlobalException;
	
	public boolean deleteLabSciAuthor(String id)throws GlobalException;
	
	
}
