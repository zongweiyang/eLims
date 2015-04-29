package cn.labsoft.labos.base.function.service;

import java.util.List;

import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;

/**
 * 系统功能Service接口
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public interface ILabFunctionService {
	/**
	 * 向功能表中插入一条记录
	 * 
	 * @param labFunctionVo
	 *            封装了功能信息的视图对象
	 * @throws GlobalException
	 */
	public LabFunctionVo addLabFunction(LabFunctionVo labFunctionVo) throws GlobalException;

	/**
	 * 修改功能信息
	 * 
	 * @param labFunctionVo
	 *            封装了功能信息的视图对象
	 * @throws GlobalException
	 */
	public LabFunctionVo updateLabFunction(LabFunctionVo labFunctionVo) throws GlobalException;

	/**
	 * 批量删除功能信息
	 * 
	 * @param ids
	 *            要删除的功能id组成的数组
	 * @throws GlobalException
	 */
	public boolean deleteLabFunction(String ids[]) throws GlobalException;

	/**
	 * 批量删除功能信息
	 * 
	 * @param ids
	 *            要删除的功能id组成的数组
	 * @return boolean
	 * @throws GlobalException
	 */
	public boolean updateLabFunction4Del(String ids[]) throws GlobalException;

	/**
	 * 根据功能id获得功能信息
	 * 
	 * @param id
	 *            功能id
	 * @throws GlobalException
	 * @return 返回类型 LabFunctionVo 返回封装了功能信息的视图对象
	 */
	public LabFunctionVo getLabFunction(String id) throws GlobalException;

	/**
	 * 根据功能URL获得功能信息
	 * 
	 * @param url
	 *            功能url
	 * @throws GlobalException
	 * @return 返回类型 LabFunctionVo 返回封装了功能信息的视图对象
	 */
	public LabFunctionVo getLabFunctionByURL(String url) throws GlobalException;

	/**
	 * 获取功能分页信息
	 * 
	 * @param labFunctionVo
	 *            封装了功能信息的视图对象
	 * @param pageResult
	 * @return PageResult 分页对象
	 * @throws GlobalException
	 */
	public PageResult getLabFunctionPR(LabFunctionVo labFunctionVo, PageResult pageResult) throws GlobalException;

	/**
	 * 获得功能集合信息
	 * 
	 * @param labFunctionVo
	 *            封装了功能信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionList(LabFunctionVo labFunctionVo) throws GlobalException;

	/**
	 * 根据父类得到最大序号
	 * 
	 * @param parentId
	 *            父类功能的ID
	 * @throws GlobalException
	 * @return 返回类型 Integer
	 */
	public Integer getMaxSort(String parentId) throws GlobalException;

	/**
	 * 根据父类ID获得子功能
	 * 
	 * @param parentId
	 *            父类功能的ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByPid(String parentId) throws GlobalException;

	/**
	 * 递归获得父类下所有的功能树
	 * 
	 * @param parentId
	 *            父类功能的ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public StringBuffer getLabFunctionListTree(String parentId) throws GlobalException;

	/**
	 * 根据角色查询功能（可能为多个角色）
	 * 
	 * @param roleIds
	 *            角色ID数组集合
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByRoleIds(String roleIds[]) throws GlobalException;

	/**
	 * 根据角色Id获取功能列表
	 * 
	 * @param roleId
	 *            角色ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByRoleId(String roleId) throws GlobalException;

	/**
	 * 获取用户功能关系列表，roleId为空时表示非角色赋权的功能列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleId
	 *            角色ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionList(String userId, String roleId) throws GlobalException;

	/**
	 * 获取用户功能关系列表，roleId为空时表示非角色赋权的功能列表,orgId 可为空
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleId
	 *            角色ID
	 * @param orgId
	 *            组织ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionList(String userId, String orgId, String roleId) throws GlobalException;

	/**
	 * 根据userId查询用户全部功能关系列表
	 * 
	 * @param userId
	 *            用户ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByUserId(String userId) throws GlobalException;

	/**
	 * 根据userId查询用户全部功能关系列表,orgId可为空
	 * 
	 * @param userId
	 *            用户ID
	 * @param orgId
	 *            组织ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByUserId(String userId, String orgId) throws GlobalException;

	/**
	 * 根据父功能id和角色数组查询功能
	 * 
	 * @param roleIds
	 * @param pid
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByRoleIdsAndPid(String roleIds[], String pid) throws GlobalException;

	/**
	 * 功能是否使用
	 * 
	 * @param id
	 *            ID
	 * @throws GlobalException
	 * @return 返回类型 boolean
	 */
	public boolean isUse4Function(String id) throws GlobalException;

	/**
	 * 根据URL和角色ID数组获得功能信息
	 * 
	 * @param roleids
	 *            角色ID数组集合
	 * @param url
	 *            功能url
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionByURLAndroleIds(String url, String[] roleids) throws GlobalException;

	/**
	 * 根据角色id数组获得功能信息(主界面上用户的目录功能显示列表)
	 * 
	 * @param sysRoleIds
	 *            角色ID数组集合
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getMainmenuByUserIdAndType(String userId, String type, String orgId) throws GlobalException;

	/**
	 * 根据功能ID递归获得子功能树信息
	 * 
	 * @param funId
	 *            功能ID
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public StringBuffer subTrees(String funId) throws GlobalException;

	/**
	 * 获取用户某个类型的功能列表
	 * 
	 * @param userId
	 *            用户id
	 * @param funType
	 *            0目录 1功能
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabFunctionListByUserIdAndType(String userId, String funType) throws GlobalException;

	/**
	 * 获取用户待办事项列表
	 * 
	 * @param userId
	 *            用户id
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public List<LabFunctionVo> getLabWFFunctionListByUserId(String userId, String orgId) throws GlobalException;

	/**
	 * @Title: 根据功能父Id获取用户某个类型的功能列表
	 * @param
	 * @param userId
	 *            用户id
	 * @param
	 * @param funType
	 *            功能类型 可为null
	 * @param
	 * @param pId
	 *            功能父Id 可为null
	 * @param
	 * @return
	 * @param
	 * @throws GlobalException
	 * @return 返回类型
	 * @throws
	 */
	public List<LabFunctionVo> getLabFunctionListByUserIdAndType(String userId, String funType, String pId) throws GlobalException;

	/**
	 * 根据功能名称判断是否有此功能(如果在修改页面中，应该除去原名)
	 * 
	 * @param name
	 * @param formerName
	 * @throws GlobalException
	 * @return 返回类型 boolean
	 */
	public boolean isExist4Name(String name, String parentId) throws GlobalException;

	/**
	 * 根据功能编号判断是否有此功能(如果在修改页面中，应该除去原名)
	 * 
	 * @param name
	 * @param formerName
	 * @throws GlobalException
	 * @return 返回类型 boolean
	 */
	public boolean isExistFunCode(String wfCode, String id) throws GlobalException;

	/**
	 * 递归查询功能树
	 * 
	 * @param parentId
	 * @throws GlobalException
	 * @return 返回类型 StringBuffer
	 */
	public StringBuffer getLabFunctionTreeRecursion(String parentId) throws GlobalException;

	/**
	 * 查询功能是否可删除
	 * 
	 * @param id
	 * @throws GlobalException
	 * @return 返回类型 boolean
	 */
	public boolean isCouldDel(String id) throws GlobalException;

	/**
	 * 根据角色ID 返回功能check树
	 * 
	 * @Title: stone Tang
	 * @Description: TODO
	 * @param
	 * @param roleId
	 * @param
	 * @param parentId
	 * @param
	 * @return
	 * @param
	 * @throws GlobalException
	 * @return 返回类型
	 * @throws
	 */
	public StringBuffer getLabFunctionCheckTree(String roleId, String parentId) throws GlobalException;

	/**
	 * 获得功能列表中为目录的功能的json字符串(除了自己和子目录)
	 * 
	 * @param funId
	 * @param id
	 * @throws GlobalException
	 * @return String
	 */
	public StringBuffer getLabFunctionDirectoryExpectSelfAndSubs(String funId, String id) throws GlobalException;

	/**
	 * 修改父功能ID
	 * 
	 * @throws GlobalException
	 * @return LabFunctionVo
	 */
	public LabFunctionVo updateLabFunctionParent(LabFunctionVo labFunctionVo) throws GlobalException;

	/**
	 * 修改功能的数据权限
	 * 
	 * @param funlist
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabFunction4Data(List<LabFunctionVo> funlist) throws GlobalException;

	/**
	 * 根据功能ID递归获得子功能树信息
	 * 
	 * @param expectId
	 * @param funId
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public StringBuffer subTrees(String funId, String expectId) throws GlobalException;

	/**
	 * @Title: 根据角色id数组获得功能信息(主界面上用户的目录功能显示列表)
	 * @param userId
	 *            用户id
	 * @param menuType
	 *            菜单显示类型 a b
	 * @param type
	 *            菜单类型 FRONT前台 BACK后台
	 * @param
	 * @throws GlobalException
	 * @return 返回类型
	 * @throws
	 */
	public List<LabFunctionVo> getMainmenuByUserIdAndMenuType(String userId, String menuType, String type) throws GlobalException;

	/**
	 * @Title: 根据角色id数组获得功能信息(主界面上用户的目录功能显示列表)
	 * @param userId
	 *            用户id
	 * @param orgId
	 * @param extendPara
	 *            可扩展参数extendPara[0] 功能父级ID
	 * @param
	 * @throws GlobalException
	 * @return 返回类型
	 * @throws
	 */
	public List<LabFunctionVo> getMainmenuByUserIdAndMenuType(String userId, String orgId, String... extendPara) throws GlobalException;

	/**
	 * 根据功能ID获得子功能树信息
	 * 
	 * @param expectId
	 * @param funId
	 * @throws GlobalException
	 * @return 返回类型 List<LabFunctionVo>
	 */
	public LabFunctionVo getLabFunctionUrl(String id) throws GlobalException;
}
