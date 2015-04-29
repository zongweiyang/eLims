package cn.labsoft.labos.utils.tree;

import java.io.Serializable;

/**
 * 
 * <strong>Title : Tree </strong>. <br>
 * <strong>Description : 树型结构对象</strong> <br>
 * <strong>Create on : 2009-12-25 上午11:16:53 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author CharlesXi<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("serial")
public class Tree implements Serializable {

	private String multiple;

	private String icon;

	private String openIcon;

	private String url;

	private String target;

	private Object treeNodes;

	public Tree() {
	}

	public Tree(Object treeNodes) {
		this(treeNodes, "", "", "", "", "");
	}

	public Tree(Object treeNodes, String multiple) {
		this(treeNodes, multiple, "", "", "", "");
	}

	public Tree(Object treeNodes, String icon, String openIcon) {
		this(treeNodes, "", icon, openIcon, "", "");
	}

	public Tree(Object treeNodes, String multiple, String icon, String openIcon) {
		this(treeNodes, multiple, icon, openIcon, "", "");
	}

	public Tree(Object treeNodes, String multiple, String icon,
			String openIcon, String url, String target) {
		this.treeNodes = treeNodes;
		this.multiple = multiple;
		this.icon = icon;
		this.openIcon = openIcon;
		this.url = url;
		this.target = target;
	}

	/**
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return
	 */
	public String getMultiple() {
		return multiple;
	}

	/**
	 * @return
	 */
	public String getOpenIcon() {
		return openIcon;
	}

	/**
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return
	 */
	public Object getTreeNodes() {
		return treeNodes;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param string
	 */
	public void setIcon(String string) {
		icon = string;
	}

	/**
	 * @param string
	 */
	public void setMultiple(String string) {
		multiple = string;
	}

	/**
	 * @param string
	 */
	public void setOpenIcon(String string) {
		openIcon = string;
	}

	/**
	 * @param string
	 */
	public void setTarget(String string) {
		target = string;
	}

	/**
	 * @param object
	 */
	public void setTreeNodes(Object object) {
		treeNodes = object;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

}
