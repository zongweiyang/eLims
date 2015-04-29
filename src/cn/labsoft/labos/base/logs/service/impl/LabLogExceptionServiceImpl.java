package cn.labsoft.labos.base.logs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.logs.dao.ILabLogExceptionDAO;
import cn.labsoft.labos.base.logs.entity.LabLogException;
import cn.labsoft.labos.base.logs.entity.LabLogRecord;
import cn.labsoft.labos.base.logs.service.ILabLogExceptionService;
import cn.labsoft.labos.base.logs.vo.LabLogExceptionVo;
import cn.labsoft.labos.base.logs.vo.LabLogRecordVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;

@Service("labLogExceptionService")
public class LabLogExceptionServiceImpl implements ILabLogExceptionService
{
  private ILabLogExceptionDAO labLogExceptionDAO;
  @Resource
  public void setLabLogExceptionDAO(ILabLogExceptionDAO labLogExceptionDAO) {
  	this.labLogExceptionDAO = labLogExceptionDAO;
  }

  
  @SuppressWarnings("unchecked")
  public PageResult getLabLogExceptionPR(LabLogExceptionVo labLogExceptionVo,
  		PageResult pageResult) throws GlobalException {
	  String hql="FROM LabLogException WHERE 1=1";
	  
	  pageResult = labLogExceptionDAO.getPageResult(hql, pageResult);
	  List<LabLogRecord> list = pageResult.getResultList();
      List<LabLogRecordVo> result = new ArrayList<LabLogRecordVo>();
      if ((list != null) && (list.size() > 0)) {
        for (LabLogRecord po : list) {
          LabLogRecordVo vo = new LabLogRecordVo();
          BeanUtils.copyProperties(po, vo, new String[] { "" });
          result.add(vo);
        }
      }
      pageResult.setResultList(result);
	  return pageResult;
  }
  @Override
  public LabLogExceptionVo addLabLogException(LabLogExceptionVo labLogExceptionVo)
  		throws GlobalException {
	  LabLogException le=new LabLogException();
	  try {
		  BeanUtils.copyProperties(labLogExceptionVo, le, new String[]{"createTime","createUserId"});
		  labLogExceptionDAO.addLabLogException(le);
		  labLogExceptionVo.setId(le.getId());
	} catch (BeansException e) {
		Log4J.error("LabLogExceptionServiceImpl..."+e.getMessage());
		throw new GlobalException("" + e.getMessage());
	}
	  return labLogExceptionVo;
  }

}