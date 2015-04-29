package cn.labsoft.labos.framework.common.action;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.framework.common.vo.BaseVo;


/**
 * 
 * <strong>Title : BaseAction </strong>. <br>
 * <strong>Description :系统中所有Action的父类,继承ActionSupport 并且 加入通用的分页以及错误处理对象
 * </strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("serial")
public class TBaseAction<T extends BaseVo,S extends IBaseService> extends BaseAction{
	protected  T vo;
	protected  S service;
	protected  List<T> list;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	private boolean existInitInfo() {
		if(null==vo){
			Log4J.out(" vo is null !~~");
			return false;
		}
		if(null==service){
			Log4J.out("service is null !~~");
			return false;
		}
		return true;
	}
	
	public String preAdd()throws GlobalException{
		return PREADD;
	}
	
	@SuppressWarnings("unchecked")
	public String exist()throws GlobalException{
		if(existInitInfo())
			executeStatus = service.exist(vo);
		return PREADD;
	}
	
	@SuppressWarnings("unchecked")
	public String finds()throws GlobalException{
		if(existInitInfo())
			list = service.finds(vo.getIds());
		return PREADD;
	}
	
	@SuppressWarnings("unchecked")
	public String add()throws GlobalException{
		if(existInitInfo())
			executeStatus = service.add(vo);
		return ADD;
	}
	
	public String preSave()throws GlobalException{
		return PRESAVE;
	}
	
	@SuppressWarnings("unchecked")
	public String save()throws GlobalException{
		if(existInitInfo())
			executeStatus = service.save(vo);
		return SAVE;
	}
	
	@SuppressWarnings("unchecked")
	public String preUpdate()throws GlobalException{
		if(existInitInfo())
			vo = (T)service.find(vo.getId());
		return PREUPDATE;
	}
	
	public String update()throws GlobalException{
		if(existInitInfo())
			executeStatus = service.delete(vo.getId());
		return UPDATE;
	}
	
	@SuppressWarnings("unchecked")
	public String show()throws GlobalException{
		if(existInitInfo())
			vo = (T)service.find(vo.getId());
		return SHOW;
	}
	
	public void ajaxList() throws GlobalException{
		list();
		ajax(pageResult);
	}
	@SuppressWarnings("unchecked")
	public String list() throws GlobalException{
		if(existInitInfo())
			pageResult = service.pageResult(vo, pageResult);
		return LIST;
	}
	
	public String delete() throws GlobalException {
		if(existInitInfo())
			executeStatus = service.delete(vo.getId());
		return DELETE;
	}

	public String update2del() throws GlobalException {
		if(existInitInfo())
			executeStatus = service.update2del(vo.getId());
		return UPDATE2DEL;
	}
}
