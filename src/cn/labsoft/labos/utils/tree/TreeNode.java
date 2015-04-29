package cn.labsoft.labos.utils.tree;

import java.io.Serializable;

/**
 * 
 * <strong>Title : TreeNode </strong>. <br>
 * <strong>Description : 树型结构的节点</strong> <br>
 * <strong>Create on : 2009-12-25 上午11:15:39 </strong>. <br>
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
public class TreeNode implements Serializable {

	private String treeNodeName;

	private String parentTreeNodeID;

	private String treeNodeKey;

	private String treeNodeID;

	private String treeNodeURL;

	private String treeNodeType;

	private String icon;

	private String openIcon;

	private String multiple;

	private boolean folder = false;

	public TreeNode() {
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String multiple) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.multiple = multiple;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String icon, String openIcon) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.icon = icon;
		this.openIcon = openIcon;
	}

	public TreeNode(String treeNodeID, String treeNodeName,
			String parentTreeNodeID, String multiple, boolean isFolder) {
		this.treeNodeID = treeNodeID;
		this.treeNodeName = treeNodeName;
		this.parentTreeNodeID = parentTreeNodeID;
		this.multiple = multiple;
		this.folder = isFolder;
	}

	public String getParentTreeNodeID() {
		return parentTreeNodeID;
	}

	public String getTreeNodeKey() {
		return treeNodeKey;
	}

	public String getTreeNodeID() {
		return treeNodeID;
	}

	public String getTreeNodeName() {
		return treeNodeName;
	}

	public void setTreeNodeName(String treeNodeName) {
		this.treeNodeName = treeNodeName;
	}

	public void setTreeNodeKey(String treeNodeKey) {
		this.treeNodeKey = treeNodeKey;
	}

	public void setTreeNodeID(String treeNodeID) {
		this.treeNodeID = treeNodeID;
	}

	public void setParentTreeNodeID(String parentTreeNodeID) {
		this.parentTreeNodeID = parentTreeNodeID;
	}

	public String getTreeNodeURL() {
		return treeNodeURL;
	}

	public void setTreeNodeURL(String treeNodeURL) {
		this.treeNodeURL = treeNodeURL;
	}

	public String getTreeNodeType() {
		return treeNodeType;
	}

	public void setTreeNodeType(String treeNodeType) {
		this.treeNodeType = treeNodeType;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	/**
	 * @return
	 */
	public String getMultiple() {
		return multiple;
	}

	/**
	 * @param string
	 */
	public void setMultiple(String string) {
		multiple = string;
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
	public String getOpenIcon() {
		return openIcon;
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
	public void setOpenIcon(String string) {
		openIcon = string;
	}

}
