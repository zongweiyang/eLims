package cn.labsoft.labos.utils.tree;

import java.util.Collection;
import java.util.Iterator;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class TreeMaker {

	StringBuffer outHTML;
	boolean parent;

	public TreeMaker() {
		outHTML = new StringBuffer();
		parent = true;
	}

	public StringBuffer TreeRootInit(String rootName) {
		return TreeRootInit("", rootName, "", "");
	}

	public StringBuffer TreeRootInit(String rootId, String rootName) {
		return TreeRootInit(rootId, rootName, "", "");
	}

	public StringBuffer TreeRootInit(String rootName, String Icon,
			String openIcon) {
		return TreeRootInit("", rootName, Icon, openIcon);
	}

	public StringBuffer TreeRootInit(String rootId, String rootName,
			String Icon, String openIcon) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node" + rootId + " = new WebFXTree('" + rootId
				+ "','" + rootName + "','','','" + Icon + "','" + openIcon
				+ "');\n");
		outHTML.append("node" + rootId + ".setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree('" + rootId + "','" + rootName
				+ "','" + url + "\" target=\"" + target + "\"','','" + Icon
				+ "','" + openIcon + "');\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit2(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node = new WebFXTree2(\"" + rootId + "\",\""
				+ rootName + "\",\"" + url + "\",\"\",\"" + Icon + "\",\""
				+ openIcon + "\");\n");
		outHTML.append("node.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit21(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node0 = new WebFXTree2(\"" + rootId + "\",\""
				+ rootName + "\",\"" + url + "\",\"\",\"" + Icon + "\",\""
				+ openIcon + "\");\n");
		outHTML.append("node0.setBehavior('classic');\n");
		return outHTML;
	}

	public StringBuffer TreeRootInit3(String rootId, String rootName,
			String Icon, String openIcon, String url, String target) {
		outHTML = new StringBuffer();
		parent = true;
		outHTML.append("var node"+rootId+" = new WebFXTree3('" + rootId + "','"
				+ rootName + "','" + url + "\" target=\"" + target + "\"','','"
				+ Icon + "','" + openIcon + "');\n");
		outHTML.append("node"+rootId+".setBehavior('classic');\n");
		return outHTML;
	}

	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp(Collection tree) {
		return TreeListApp(tree, "", "", "", "", "");
	}
	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp(Collection tree, String multiple) {
		return TreeListApp(tree, multiple, "", "", "", "");
	}
	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp(Collection tree, String Icon,
			String openIcon) {
		return TreeListApp(tree, "", Icon, openIcon, "", "");
	}
	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp(Collection tree, String multiple,
			String Icon, String openIcon) {
		return TreeListApp(tree, multiple, Icon, openIcon, "", "");
	}
	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp(Collection tree, String Icon,
			String openIcon, String action, String target) {
		return TreeListApp(tree, "", Icon, openIcon, action, target);
	}
	@SuppressWarnings("unchecked")
	public StringBuffer TreeListApp2(Collection tree, String Icon,
			String openIcon, String action, String target) {
		return TreeListApp2(tree, "", Icon, openIcon, action, target);
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public StringBuffer TreeListApp(Collection tree, String multiple,
			String Icon, String openIcon, String action, String target) {
		try {
			String multipleStr = "";
			multipleStr = getClassName(multiple);
			Iterator it = (tree == null ? null : tree.iterator());
			TreeNode treeNode = null;
			while (it != null && it.hasNext()) {
				treeNode = (TreeNode) it.next();
				// url
				String tempUrl = "";
				if (treeNode != null) {
					if (isEmpty(action)) {
						tempUrl = treeNode.getTreeNodeURL() == null ? ""
								: treeNode.getTreeNodeURL() + "\" target=\""
										+ target + "\"";
					} else {
						tempUrl = ((action.substring(action.length() - 1,
								action.length()).equals("=")) ? action
								+ treeNode.getTreeNodeID() : action)
								+ "\" target=\"" + target + "\"";
					}
					if (outHTML == null || outHTML.length() < 1) {
						// TreeRootInit(treeNode.getTreeNodeID(),treeNode.getTreeNodeName(),Icon,openIcon);
						TreeRootInit3(treeNode.getTreeNodeID(), treeNode
								.getTreeNodeName(), Icon, openIcon, tempUrl,
								target);
					} else {
						if (isEmpty(multiple)) {
							multipleStr = getClassName(treeNode.getMultiple());
						}
						if (isEmpty(treeNode.getParentTreeNodeID())) {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "('"
											+ treeNode.getTreeNodeID()
											+ "','"
											+ treeNode.getTreeNodeName()
											+ "','"
											+ tempUrl
											+ "',"
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isFolder() + ","))
											+ "'"
											+ treeNode.getParentTreeNodeID()
											+ "','"
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "','"
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "');\n");
							outHTML.append("node.add(node"
									+ treeNode.getTreeNodeID() + ");\n");
						} else {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "('"
											+ treeNode.getTreeNodeID()
											+ "','"
											+ treeNode.getTreeNodeName()
											+ "','"
											+ tempUrl
											+ "',"
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isFolder() + ","))
											+ "'"
											+ treeNode.getParentTreeNodeID()
											+ "','"
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "','"
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "');\n");
							outHTML.append("node"
									+ treeNode.getParentTreeNodeID()
									+ ".add(node" + treeNode.getTreeNodeID()
									+ ");\n");
						}
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());

		} finally {
			return outHTML;
		}
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public StringBuffer TreeListApp2(Collection tree, String multiple,
			String Icon, String openIcon, String action, String target) {
		try {
			String multipleStr = "";
			//String tempmul = "";
			multipleStr = getClassName(multiple);
			Iterator it = (tree == null ? null : tree.iterator());
			TreeNode treeNode = null;
			while (it != null && it.hasNext()) {
				treeNode = (TreeNode) it.next();
				// url
				String tempUrl = "";
				if (treeNode != null) {
					if (isEmpty(action)) {
						tempUrl = treeNode.getTreeNodeURL() == null ? ""
								: treeNode.getTreeNodeURL();
					} else {
						tempUrl = ((action.substring(action.length() - 1,
								action.length()).equals("=")) ? action
								+ treeNode.getTreeNodeID() : action)
								+ "\" target=\"" + target + "\"";
					}
					if (outHTML == null || outHTML.length() < 1) {
						TreeRootInit(treeNode.getTreeNodeID(), treeNode
								.getTreeNodeName(), Icon, openIcon);
					} else {
						if (isEmpty(multiple)) {
							multipleStr = getClassName(treeNode.getMultiple());
						}
						if (isEmpty(treeNode.getParentTreeNodeID())) {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "(\""
											+ treeNode.getTreeNodeID()
											+ "\",\""
											+ treeNode.getTreeNodeName()
											+ "\",\""
											+ tempUrl
											+ "\","
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isFolder() + ","))
											+ "\""
											+ treeNode.getParentTreeNodeID()
											+ "\",\""
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "\",\""
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "\");\n");
							outHTML.append("node.add(node"
									+ treeNode.getTreeNodeID() + ");\n");
						} else {
							outHTML
									.append("var node"
											+ treeNode.getTreeNodeID()
											+ "= new "
											+ multipleStr
											+ "(\""
											+ treeNode.getTreeNodeID()
											+ "\",\""
											+ treeNode.getTreeNodeName()
											+ "\",\""
											+ tempUrl
											+ "\","
											+ (multipleStr
													.equals("WebFXTreeItem") ? ""
													: (treeNode.isFolder() + ","))
											+ "\""
											+ treeNode.getParentTreeNodeID()
											+ "\",\""
											+ (isEmpty(treeNode.getIcon()) ? Icon
													: treeNode.getIcon())
											+ "\",\""
											+ (isEmpty(treeNode.getOpenIcon()) ? openIcon
													: treeNode.getOpenIcon())
											+ "\");\n");
							outHTML.append("node"
									+ treeNode.getParentTreeNodeID()
									+ ".add(node" + treeNode.getTreeNodeID()
									+ ");\n");
						}
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());

		} finally {
			return outHTML;
		}
	}

	public String getClassName(String multiple) {
		String strClassName = null;
		if (multiple != null && multiple.equalsIgnoreCase("c")) {
			// ��ѡ��
			strClassName = "WebFXCheckBoxTreeItem";
		} else if (multiple != null && multiple.equalsIgnoreCase("r")) {
			// ��ѡ��
			strClassName = "WebFXRadioTreeItem";
		} else {
			strClassName = "WebFXTreeItem";
		}
		return strClassName;
	}

	public boolean isEmpty(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}
}
