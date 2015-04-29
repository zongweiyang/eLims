package cn.labsoft.labos.utils.tree;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * <strong>Title : TreeNodes </strong>. <br>
 * <strong>Description : 树型结构的节点集</strong> <br>
 * <strong>Create on : 2009-12-25 上午11:15:01 </strong>. <br>
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
public class TreeNodes implements Serializable {
	private TreeNodes parentTreeNode;

	@SuppressWarnings("unchecked")
	private Set sonTreeNodes = new TreeSet();

	private Object treeNode;

	@SuppressWarnings("unused")
	private String treeNodeURL;

	private boolean folder = false;

	private boolean isChoose = false;

	@SuppressWarnings("unused")
	private boolean isLast = true;

	private boolean isRoot = false;

	public TreeNodes() {
		super();
	}

	public TreeNodes(Object o) {
		this.treeNode = o;
	}

	public TreeNodes getParent() {
		return this.parentTreeNode;
	}

	@SuppressWarnings("unchecked")
	public void setParent(TreeNodes parentTreeNode) {
		this.parentTreeNode = parentTreeNode;
		Set set = this.parentTreeNode.getSons();
		set.add(this);
		this.parentTreeNode.setSons(set);
	}

	@SuppressWarnings("unchecked")
	public Set getSons() {
		return this.sonTreeNodes;
	}
	@SuppressWarnings("unchecked")
	public void setSons(Set sons) {
		this.sonTreeNodes = sons;
	}

	public void setNode(Object node) {
		this.treeNode = node;
	}

	public Object getNode() {
		return this.treeNode;
	}

	public void setIsChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}

	public boolean getIsChoose() {
		return this.isChoose;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean getIsRoot() {
		return this.isRoot;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}
}