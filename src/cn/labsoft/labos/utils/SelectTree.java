package cn.labsoft.labos.utils;

import java.util.Set;

public interface SelectTree {

	public String getId();

	public String getTreeName();// ���name

	public String getSelectTree();// ����Ժ�� treeName

	public void setSelectTree(String selectTree);

	// ���ú�����
	@SuppressWarnings("unchecked")
	public void setTreeChild(Set treeChild);

	public Set<? extends SelectTree> getTreeChild();

	// ��ø���
	public SelectTree getTreeParent();

}
