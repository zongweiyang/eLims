package cn.labsoft.labos.utils.tree;

import java.sql.ResultSet;
import java.util.StringTokenizer;

/**
 * 
 * <strong>Title : activeTree </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2009-12-25 上午11:12:31 </strong>. <br>
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

public class activeTree {
	String home = "images/home.gif";
	String plus = "images/plus.gif";
	String minus = "images/minus.gif";
	String vertline = "images/vline.gif";
	String node = "images/node.gif";
	String lastnode = "images/lastnode.gif";
	// \u5B9A\u4E49\u53D8\u91CF
	String treeBC = "";
	String treeFC = "darkblue";
	String itemFC = "black";
	String itemABC = "rgb(158,172,196)";
	String itemAFC = "red";
	String itemfont = "\u5B8B\u4F53";
	String itemfontsize = "10pt";
	String itemfontweight = "normal";
	String indent = "20";
	String marginleft = "5";
	String treeTarget = "ctl";

	public activeTree() {
	}

	public void setHome(String s) {
		home = s;
	}

	public void setPlus(String s) {
		plus = s;
	}

	public void setMinus(String s) {
		minus = s;
	}

	public void setVLine(String s) {
		vertline = s;
	}

	public void setNode(String s) {
		node = s;
	}

	public void setLastNode(String s) {
		lastnode = s;
	}

	public void setBackcolor(String s) {
		treeBC = s;
	}

	public void setForecolor(String s) {
		treeFC = s;
	}

	public void setItemForecolor(String s) {
		itemFC = s;
	}

	public void setItemActiveBackcolor(String s) {
		itemABC = s;
	}

	public void setItemActiveForecolor(String s) {
		itemAFC = s;
	}

	public void setItemFont(String s) {
		itemfont = s;
	}

	public void setItemFontSize(String s) {
		itemfontsize = s;
	}

	public void setItemFontWeight(String s) {
		itemfontweight = s;
	}

	public void setIndent(String s) {
		indent = s;
	}

	public void setMarginLeft(String s) {
		marginleft = s;
	}

	public void setTarget(String s) {
		treeTarget = s;
	}

	public String getHome() {
		return home;
	}

	public String getPlus() {
		return plus;
	}

	public String getMinus() {
		return minus;
	}

	public String getVLine() {
		return vertline;
	}

	public String getNode() {
		return node;
	}

	public String getLastNode() {
		return lastnode;
	}

	public String getBackcolor() {
		return treeBC;
	}

	public String getForecolor() {
		return treeFC;
	}

	public String getItemForecolor() {
		return itemFC;
	}

	public String getItemActiveBackcolor() {
		return itemABC;
	}

	public String getItemActiveForecolor() {
		return itemAFC;
	}

	public String getItemFont() {
		return itemfont;
	}

	public String getItemFontSize() {
		return itemfontsize;
	}

	public String getItemFontWeight() {
		return itemfontweight;
	}

	public String getIndent() {
		return indent;
	}

	public String getMarginLeft() {
		return marginleft;
	}

	public String getTarget() {
		return treeTarget;
	}

	public String getTree(ResultSet rs, String ml, String micon) {

		StringBuffer sd = new StringBuffer();
		// \u5B9A\u4E49\u53D8\u91CF
		sd.append("<script language=\"javascript\">");
		sd.append("var treeBC = \"" + treeBC + "\"\n");
		sd.append("var treeFC = \"" + treeFC + "\"\n");
		sd.append("var itemFC = \"" + itemFC + "\"\n");
		sd.append("var itemABC = \"" + itemABC + "\"\n");
		sd.append("var itemAFC = \"" + itemAFC + "\"\n");
		sd.append("var itemfont = \"" + itemfont + "\"\n");
		sd.append("var itemfontsize = \"" + itemfontsize + "\"\n");
		sd.append("var itemfontweight = \"" + itemfontweight + "\"\n");
		sd.append("var indent = \"" + indent + "\"\n");
		sd.append("var marginleft = \"" + marginleft + "\"\n");
		sd.append("var treeTarget = \"" + treeTarget + "\"\n");
		sd.append("var minus = \"" + minus + "\"\n");
		sd.append("var plus = \"" + plus + "\"\n");
		sd.append("function limg_onclick(li, d, limg)\n");
		sd
				.append("{\n if(d.style.display == \"none\") \n { \n   d.style.display = \"block\" \n      limg.src = minus\n }\n else \n {\n            d.style.display = \"none\" \n               limg.src = plus\n        } \n        window.event.cancelBubble = true \n} ");
		sd
				.append("\nfunction li_onclick(li, d, limg) \n{\n        if(d == null) \n        { \n                alert(\"You click \" + window.event.srcElement.id + \"!\") \n        } \n        else \n        { \n              if(d.style.display == \"none\") \n            { \n                       d.style.display = \"block\" \n                    limg.src = minus\n                } \n           else \n                { \n                        d.style.display = \"none\" \n                        limg.src = plus\n                } \n        } \n        window.event.cancelBubble = true \n} ");
		sd
				.append("\nfunction li_onmouseover(li)\n{ \n        li.style.color = itemAFC \n        li.style.backgroundColor = itemABC \n        window.event.cancelBubble = true \n} \n");
		sd
				.append("\nfunction li_onmouseout(li) \n{\n        li.style.color = itemFC \n        li.style.backgroundColor = treeBC \n        window.event.cancelBubble = true \n}");
		sd.append("</script>");

		int lev = 0;
		String tmp = "";
		String icon = "";
		String suffix = "";
		int lev_next = 0;
		int count_next = 0;
		String s = "";
		String str0 = "";
		String str1 = "";
		try {
			if (rs.next()) {
				tmp = rs.getString("ml");
				StringTokenizer st = new StringTokenizer(tmp, ";");
				//boolean b = false;
				str0 = st.nextToken();
				if (st.hasMoreTokens()) {

					str1 = st.nextToken();
					tmp = "<A target=\"" + treeTarget + "\" HREF=\"" + str1
							+ "\">" + str0 + "</A>";
					//b = true;
				} else {
					tmp = str0;
				}
				sd.append("<DIV noWrap>");
				sd
						.append("<UL style=\"BACKGROUND-COLOR: ; font-family: \u5B8B\u4F53; font-size: 10pt; font-weight: normal; COLOR: darkblue; MARGIN-LEFT: 5\">");
				suffix = String.valueOf(rs.getRow());
				tmp = "<IMG id=limg"
						+ suffix
						+ " SRC=\""
						+ home
						+ "\">&nbsp;<SPAN id=li"
						+ suffix
						+ " style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript  onmouseover=\"return li_onmouseover(li"
						+ suffix + ")\" onmouseout=\"return li_onmouseout(li"
						+ suffix + ")\">" + tmp + "</SPAN><BR>";
				sd.append(tmp);
				tmp = "<DIV id=d" + suffix + " style=\"display: block\">";
				sd.append(tmp);
				tmp = "<UL id=u" + suffix + " style=\"MARGIN-LEFT: 0\">";
				sd.append(tmp);
			}
			while (rs.next()) {
				lev = rs.getInt("lev");
				tmp = rs.getString("ml");
				icon = rs.getString(micon);
				StringTokenizer st = new StringTokenizer(tmp, ";");
				//boolean b = false;
				str0 = st.nextToken();
				if (st.hasMoreTokens()) {

					str1 = st.nextToken();
					tmp = "<A target=\"" + treeTarget + "\" HREF=\"" + str1
							+ "\">" + str0 + "</A>";
					//b = true;
				} else {
					tmp = str0;
				}
				suffix = String.valueOf(rs.getRow());
				lev_next = lev;
				if (rs.next()) {
					for (int n = 0; n < lev - 2; n++) {
						sd.append("<IMG SRC=\"" + vertline + "\">");
					}
					lev_next = rs.getInt("lev");
					if (lev_next == lev) {
						tmp = "<IMG id=limg"
								+ suffix
								+ " SRC=\""
								+ node
								+ "\">&nbsp;<SPAN id=li"
								+ suffix
								+ " style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript  onmouseover=\"return li_onmouseover(li"
								+ suffix
								+ ")\" onmouseout=\"return li_onmouseout(li"
								+ suffix + ")\"><IMG SRC=\"" + icon + "\">"
								+ tmp + "</SPAN><BR>";
						sd.append(tmp);
					}
					if (lev_next > lev) {
						tmp = "<IMG id=limg"
								+ suffix
								+ " SRC=\""
								+ minus
								+ "\" style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript onclick =\"return limg_onclick(li"
								+ suffix
								+ ", d"
								+ suffix
								+ ", limg"
								+ suffix
								+ ")\">&nbsp;<SPAN id=li"
								+ suffix
								+ " style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript onclick =\"return li_onclick(li"
								+ suffix + ", d" + suffix + ", limg" + suffix
								+ ")\" onmouseover=\"return li_onmouseover(li"
								+ suffix
								+ ")\" onmouseout=\"return li_onmouseout(li"
								+ suffix + ")\"><IMG SRC=\"" + icon + "\">"
								+ tmp + "</SPAN><BR>";
						sd.append(tmp);
						tmp = "<DIV id=d" + suffix
								+ " style=\"display: block\">";
						sd.append(tmp);
						tmp = "<UL id=u" + suffix
								+ " style=\"MARGIN-LEFT: 0\">";
						sd.append(tmp);
						count_next++;
					}
					if (lev_next < lev) {
						tmp = "<IMG id=limg"
								+ suffix
								+ " SRC=\""
								+ lastnode
								+ "\">&nbsp;<SPAN id=li"
								+ suffix
								+ " style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript  onmouseover=\"return li_onmouseover(li"
								+ suffix
								+ ")\" onmouseout=\"return li_onmouseout(li"
								+ suffix + ")\"><IMG SRC=\"" + icon + "\">"
								+ tmp + "</SPAN><BR>";
						sd.append(tmp);
						for (int n = 0; n < lev - lev_next; n++) {
							sd.append("</UL></DIV>");
							count_next--;
						}
					}
					rs.previous();
				} else {
					for (int n = 0; n < lev - 2; n++) {
						sd.append("<IMG SRC=\"" + vertline + "\">");
					}
					tmp = "<IMG id=limg"
							+ suffix
							+ " SRC=\""
							+ lastnode
							+ "\">&nbsp;<SPAN id=li"
							+ suffix
							+ " style=\"COLOR: black; CURSOR: hand;\" LANGUAGE=javascript  onmouseover=\"return li_onmouseover(li"
							+ suffix
							+ ")\" onmouseout=\"return li_onmouseout(li"
							+ suffix + ")\"><IMG SRC=\"" + icon + "\">" + tmp
							+ "</SPAN><BR>";
					sd.append(tmp);
					for (int n = 0; n < count_next; n++) {
						sd.append("</UL></DIV>");
					}
				}
			}
			rs.close();
			sd.append("</UL></DIV>");
			s = sd.toString();
		} catch (java.sql.SQLException e) {
			sd.append("var=\"\" " + e.toString());
		}
		return s;
	}

	public static void main(String arg[]) throws Exception {

	}

	public String getNewTree(ResultSet rs, String ml, String TreeName,
			String HomeIcon, String NodeOpenIcon, String NodeCloseIcon,
			String ItemIcon, String Target, boolean NodeLinkFlag) {

		StringBuffer sd = new StringBuffer();
		// \u5B9A\u4E49\u53D8\u91CF
		sd.append("<script language=\"javascript\">\n");
		sd.append("var treeBC = \"" + treeBC + "\"\n");
		sd.append("var treeFC = \"" + treeFC + "\"\n");
		sd.append("var itemFC = \"" + itemFC + "\"\n");
		sd.append("var itemABC = \"" + itemABC + "\"\n");
		sd.append("var itemAFC = \"" + itemAFC + "\"\n");
		sd.append("var itemfont = \"" + itemfont + "\"\n");
		sd.append("var itemfontsize = \"" + itemfontsize + "\"\n");
		sd.append("var itemfontweight = \"" + itemfontweight + "\"\n");
		sd.append("var indent = \"" + indent + "\"\n\n");

		sd.append("function NodeOnClick(DivID, ImgName)\n" + "{\n"
				+ "	  if (DivID.style.display == \"none\")\n" + "	     {\n"
				+ "           DivID.style.display = \"block\"\n"
				+ "           ImgName.src = \"" + NodeOpenIcon + "\"\n"
				+ "      }\n" + "	  else\n" + "	     {\n"
				+ "           DivID.style.display = \"none\"\n"
				+ "           ImgName.src = \"" + NodeCloseIcon + "\"\n"
				+ "      }\n" + "}\n\n");
		sd.append("</script>\n\n");

		int lev = 0, lev_next = 0, count_next = 0, Num = 1;
		String tmp = "", s = "", str0 = "", str1 = "";

		try {
			if (TreeName.equals("")) {
				if (rs.next()) {
					tmp = rs.getString("ML");
					int pos = tmp.indexOf(";");
					if (pos != -1) // ��ML����з����Ŀ¼��Ϣ
						tmp = tmp.substring(0, pos);

					sd
							.append("<DIV noWrap><UL style=\"MARGIN-LEFT:0\"><IMG align=\"absmiddle\" SRC=\""
									+ HomeIcon
									+ "\"><SPAN><font color=red>&nbsp;&nbsp;"
									+ tmp + "</font></SPAN>\n");
				}
			} else
				sd
						.append("<DIV noWrap><UL style=\"MARGIN-LEFT:0\"><IMG align=\"absmiddle\" SRC=\""
								+ HomeIcon
								+ "\"><SPAN><font color=red>&nbsp;&nbsp;"
								+ TreeName + "</font></SPAN>\n");

			sd.append("    <DIV id=d0 style=\"display:block\">\n");
			sd.append("      <UL id=u0 style=\"MARGIN-LEFT:20\">");

			while (rs.next()) {
				lev = rs.getInt("lev");
				Num = rs.getRow();

				tmp = rs.getString("ml");
				StringTokenizer st = new StringTokenizer(tmp, ";");
				// boolean b=false;
				str0 = st.nextToken();
				if (st.hasMoreTokens()) // �ж�ML�ֶ��Ƿ���t����Ϣ
				{
					str1 = st.nextToken();
					tmp = "<A target=\"" + Target + "\" HREF=\"" + str1
							+ "\"> " + str0 + "</A>";
					// b=true;
				} else
					tmp = str0;

				if (rs.next()) {
					lev_next = rs.getInt("lev");
					if (lev_next == lev) // ���յ�Ҷ�ӽڵ�
						sd.append("<Img id=limg" + Num
								+ " align='absmiddle' Src=\"" + ItemIcon
								+ "\"><SPAN id=li" + Num
								+ " style=\"CURSOR: hand;\">  " + tmp
								+ "</SPAN><br>");

					if (lev_next > lev) // �ڵ�
					{
						sd
								.append("<Img id=limg"
										+ Num
										+ " align='absmiddle' Src=\""
										+ NodeCloseIcon
										+ "\" Style=\"cursor:hand\" OnClick=\"NodeOnClick(d"
										+ (Num + 1)
										+ ", limg"
										+ Num
										+ ")\"><SPAN id=li"
										+ (Num + 1)
										+ " style=\"CURSOR: hand;\" OnClick=\"NodeOnClick(d"
										+ (Num + 1) + ", limg" + Num + ")\">  "
										+ (NodeLinkFlag == true ? tmp : str0)
										+ "</SPAN><br>\n");
						sd.append("<DIV id=d" + (Num + 1)
								+ " style=\"display:none\">");
						sd.append("  <UL id=u" + (Num + 1)
								+ " style=\"MARGIN-LEFT: " + 16 + "\">");
						count_next++;
					}
					// *
					if (lev_next < lev) // ��ǰ�ڵ��Ǳ������͵����һ��ڵ�
					{
						sd.append("<Img align='absmiddle' Src=\"" + ItemIcon
								+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
								+ "</SPAN><br>\n");

						for (int n = 0; n < lev - lev_next; n++) {
							sd.append("    </UL>\n  </DIV>\n");
							count_next--;
						}
					}

					rs.previous();
				} else // ���һ��Ҷ�ӣ��4����</UL></DIV>��ǩ
				{
					sd.append("<Img align='absmiddle' Src=\"" + ItemIcon
							+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
							+ "</SPAN><br>\n");
					for (int n = 0; n < count_next; n++)
						sd.append("      </UL>\n  </DIV>\n");
				}
			} // End While(rs.next()) case

			// rs.close();
			sd.append("  </UL>\n</DIV>\n");
			s = sd.toString();
		} catch (java.sql.SQLException e) {
			sd.append("var=\"\" " + e.toString());
		}
		return s;
	}

	public String getOnlineTree(ResultSet rs, String ml, String TreeName,
			String HomeIcon, String NodeOpenIcon, String NodeCloseIcon,
			String ItemIconOn, String ItemIconOff, String Target,
			boolean NodeLinkFlag) {

		StringBuffer sd = new StringBuffer();
		// \u5B9A\u4E49\u53D8\u91CF
		sd.append("<script language=\"javascript\">\n");
		sd.append("var treeBC = \"" + treeBC + "\"\n");
		sd.append("var treeFC = \"" + treeFC + "\"\n");
		sd.append("var itemFC = \"" + itemFC + "\"\n");
		sd.append("var itemABC = \"" + itemABC + "\"\n");
		sd.append("var itemAFC = \"" + itemAFC + "\"\n");
		sd.append("var itemfont = \"" + itemfont + "\"\n");
		sd.append("var itemfontsize = \"" + itemfontsize + "\"\n");
		sd.append("var itemfontweight = \"" + itemfontweight + "\"\n");
		sd.append("var indent = \"" + indent + "\"\n\n");

		sd.append("function NodeOnClick(DivID, ImgName)\n" + "{\n"
				+ "	  if (DivID.style.display == \"none\")\n" + "	     {\n"
				+ "           DivID.style.display = \"block\"\n"
				+ "           ImgName.src = \"" + NodeOpenIcon + "\"\n"
				+ "      }\n" + "	  else\n" + "	     {\n"
				+ "           DivID.style.display = \"none\"\n"
				+ "           ImgName.src = \"" + NodeCloseIcon + "\"\n"
				+ "      }\n" + "}\n\n");
		sd.append("</script>\n\n");

		@SuppressWarnings("unused")
		int lev = 0, lev_next = 0, count_next = 0, Num = 1, ifpost_next = 0, ifpost = 0;
		String tmp = "", s = "", str0 = "", str1 = "";

		try {
			if (TreeName.equals("")) {
				if (rs.next()) {
					tmp = rs.getString("ML");
					int pos = tmp.indexOf(";");
					if (pos != -1) // ��ML����з����Ŀ¼��Ϣ
						tmp = tmp.substring(0, pos);

					sd
							.append("<DIV noWrap><UL style=\"MARGIN-LEFT:0\"><IMG align=\"absmiddle\" SRC=\""
									+ HomeIcon
									+ "\"><SPAN><font color=red>&nbsp;&nbsp;"
									+ tmp + "</font></SPAN>\n");
				}
			} else
				sd
						.append("<DIV noWrap><UL style=\"MARGIN-LEFT:0\"><IMG align=\"absmiddle\" SRC=\""
								+ HomeIcon
								+ "\"><SPAN><font color=red>&nbsp;&nbsp;"
								+ TreeName + "</font></SPAN>\n");

			sd.append("    <DIV id=d0 style=\"display:block\">\n");
			sd.append("      <UL id=u0 style=\"MARGIN-LEFT:20\">");

			while (rs.next()) {
				lev = rs.getInt("lev");
				Num = rs.getRow();
				ifpost = rs.getInt("ifpost");
				tmp = rs.getString("ml");
				StringTokenizer st = new StringTokenizer(tmp, ";");
				// boolean b=false;
				str0 = st.nextToken();
				if (st.hasMoreTokens()) // �ж�ML�ֶ��Ƿ���t����Ϣ
				{
					str1 = st.nextToken();
					tmp = "<A target=\"" + Target + "\" HREF=\"" + str1
							+ "\"> " + str0 + "</A>";
					// b=true;
				} else
					tmp = str0;

				if (rs.next()) {
					lev_next = rs.getInt("lev");
					ifpost_next = rs.getInt("ifpost");
					if (lev_next == lev) {
						if (ifpost == 1) // ���յ�Ҷ�ӽڵ�
						{
							sd.append("<Img id=limg" + Num
									+ " align='absmiddle' Src=\"" + ItemIconOn
									+ "\"><SPAN id=li" + Num
									+ " style=\"CURSOR: hand;\">  " + tmp
									+ "</SPAN><br>");
						} else {
							sd.append("<Img id=limg" + Num
									+ " align='absmiddle' Src=\"" + ItemIconOff
									+ "\"><SPAN id=li" + Num
									+ " style=\"CURSOR: hand;\">  " + tmp
									+ "</SPAN><br>");
						}
					}
					if (lev_next > lev) // �ڵ�
					{
						sd
								.append("<Img id=limg"
										+ Num
										+ " align='absmiddle' Src=\""
										+ NodeCloseIcon
										+ "\" Style=\"cursor:hand\" OnClick=\"NodeOnClick(d"
										+ (Num + 1)
										+ ", limg"
										+ Num
										+ ")\"><SPAN id=li"
										+ (Num + 1)
										+ " style=\"CURSOR: hand;\" OnClick=\"NodeOnClick(d"
										+ (Num + 1) + ", limg" + Num + ")\">  "
										+ (NodeLinkFlag == true ? tmp : str0)
										+ "</SPAN><br>\n");
						sd.append("<DIV id=d" + (Num + 1)
								+ " style=\"display:none\">");
						sd.append("  <UL id=u" + (Num + 1)
								+ " style=\"MARGIN-LEFT: " + 16 + "\">");
						count_next++;
					}
					// *
					if (lev_next < lev) // ��ǰ�ڵ��Ǳ������͵����һ��ڵ�
					{
						if (ifpost == 1) {
							sd.append("<Img align='absmiddle' Src=\""
									+ ItemIconOn
									+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
									+ "</SPAN><br>\n");
						} else {
							sd.append("<Img align='absmiddle' Src=\""
									+ ItemIconOff
									+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
									+ "</SPAN><br>\n");

						}

						for (int n = 0; n < lev - lev_next; n++) {
							sd.append("    </UL>\n  </DIV>\n");
							count_next--;
						}
					}

					rs.previous();
				} else // ���һ��Ҷ�ӣ��4����</UL></DIV>��ǩ
				{
					if (ifpost == 1) {
						sd.append("<Img align='absmiddle' Src=\"" + ItemIconOn
								+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
								+ "</SPAN><br>\n");
					} else {
						sd.append("<Img align='absmiddle' Src=\"" + ItemIconOff
								+ "\"><SPAN style=\"CURSOR: hand;\">" + tmp
								+ "</SPAN><br>\n");

					}
					for (int n = 0; n < count_next; n++)
						sd.append("      </UL>\n  </DIV>\n");
				}
			} // End While(rs.next()) case

			// rs.close();
			sd.append("  </UL>\n</DIV>\n");
			s = sd.toString();
		} catch (java.sql.SQLException e) {
			sd.append("var=\"\" " + e.toString());
		}

		return s;
	}

}