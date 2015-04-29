package cn.labsoft.labos.utils.exportsql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.JDBCTools;

public class ReorgAllTable {

	public static void main(String[] args) throws GlobalException {
		List<String> array1 = ReorgAllTable.getDBClearScript(null);
		List<String> array2 = ReorgAllTable.getDBDropFKScript();
		for (String str : array1) {
			System.out.println(str);
		}
		for (String str : array2) {
			System.out.println(str);
		}
	}

	private static Map<String, List<TableFK>> tableFKListMap = new HashMap<String, List<TableFK>>();

	private static Map<String, TableFK> tableFKMap = new HashMap<String, TableFK>();

	private static String[] tablePrefixArray;

	/**
	 * 或得清库脚本
	 * 
	 * @param tablePrefixArrayInput
	 *            表前缀
	 * @return
	 * @throws GlobalException 
	 */
	public static List<String> getDBClearScript(String[] tablePrefixArrayInput) throws GlobalException {
		tablePrefixArray = tablePrefixArrayInput;
		List<String> returnStrList = new ArrayList<String>();

		String[] array = ReorgAllTable.getSortedTableNameForDrop();
		for (int i = 0; i < array.length; i++) {
			List<TableFK> tableFKList = getTableFKList(array[i]);
			if (null != tableFKList && 0 < tableFKList.size()) {
				for (TableFK tableFK : tableFKList) {
					if (tableFK.table.equals(tableFK.PKTABLE_NAME)) {
						returnStrList.add("UPDATE " + tableFK.table + " SET "
								+ tableFK.FKCOLUMN_NAME + "=null;");
					}
				}
			}
			returnStrList.add("DELETE FROM " + array[i] + ";");
		}
		return returnStrList;
	}

	/**
	 * 删除表外键
	 * 
	 * @return
	 * @throws GlobalException 
	 */
	public static List<String> getDBDropFKScript() throws GlobalException {
		List<String> returnStrList = new ArrayList<String>();

		String[] array = ReorgAllTable.getSortedTableNameForDrop();
		for (int i = 0; i < array.length; i++) {
			List<TableFK> tableFKList = getTableFKList(array[i]);
			if (null != tableFKList && 0 < tableFKList.size()) {
				for (TableFK tableFK : tableFKList) {
					returnStrList.add("ALTER TABLE " + array[i]
							+ " DROP FOREIGN KEY " + tableFK.FK_NAME + ";");
				}
			}
		}
		return returnStrList;
	}

	/**
	 * 向库中插入数据时表顺序
	 * 
	 * @return
	 * @throws GlobalException 
	 */
	public static String[] getSortedTableNameForInsert() throws GlobalException {
		try {
			List<SortedTableNameByFK> sortedTableNameByFKList = ReorgAllTable
					.sortTable();
			if (null != sortedTableNameByFKList
					&& 0 < sortedTableNameByFKList.size()) {
				String[] returnTableNameArray = new String[sortedTableNameByFKList
						.size()];
				for (int i = 0; i < sortedTableNameByFKList.size(); i++) {
					SortedTableNameByFK sortedTableNameByFK = sortedTableNameByFKList
							.get(i);
					returnTableNameArray[i] = sortedTableNameByFK
							.getTableNameStr();
				}
				return returnTableNameArray;
			} else {
				return new String[] {};
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	/**
	 * 删除库中数据时表顺序
	 * 
	 * @return
	 * @throws GlobalException 
	 */
	public static String[] getSortedTableNameForDrop() throws GlobalException {
		try {
			List<SortedTableNameByFK> sortedTableNameByFKList = ReorgAllTable
					.sortTable();

			if (null != sortedTableNameByFKList
					&& 0 < sortedTableNameByFKList.size()) {
				String[] returnTableNameArray = new String[sortedTableNameByFKList
						.size()];
				for (int i = 0; i < sortedTableNameByFKList.size(); i++) {
					SortedTableNameByFK sortedTableNameByFK = sortedTableNameByFKList
							.get(i);
					returnTableNameArray[sortedTableNameByFKList.size() - i - 1] = sortedTableNameByFK
							.getTableNameStr();
				}
				return returnTableNameArray;
			} else {
				return new String[] {};
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	private static List<SortedTableNameByFK> sortTable() throws Exception {
		List<SortedTableNameByFK> sortedTableNameByFKList = new ArrayList<SortedTableNameByFK>();
		fetchTableFkListMap();
		Set<String> tableSet = tableFKListMap.keySet();
		String[] tableSetArray = tableSet.toArray(new String[] {});
		if (null != tableSetArray && 0 < tableSetArray.length) {
			for (int i = 0; i < tableSetArray.length; i++) {
				tableSetArray = ReorgAllTable.sortTable(tableSetArray);
			}
			SortedTableNameByFK sortedTableNameByFK = new SortedTableNameByFK();
			int count = 1;
			for (String tableNameStr : tableSetArray) {
				sortedTableNameByFK = new SortedTableNameByFK();
				sortedTableNameByFK.setSort(count);
				sortedTableNameByFK.setTableNameStr(tableNameStr);

				sortedTableNameByFKList.add(sortedTableNameByFK);
			}
		}

		return sortedTableNameByFKList;
	}

	private static String[] sortTable(String[] tableSetArray) {
		String swapString = "";
		if (null != tableSetArray && 0 < tableSetArray.length) {
			for (int i = 0; i < tableSetArray.length - 1; i++) {
				String tableANameStr = tableSetArray[i];
				for (int j = i; j < tableSetArray.length; j++) {
					String tableBNameStr = tableSetArray[j];
					if (isAContainsB(tableANameStr, tableBNameStr)) {
						swapString = tableSetArray[i];
						tableSetArray[i] = tableSetArray[j];
						tableSetArray[j] = swapString;
					}
				}
			}
		}
		return tableSetArray;
	}

	private static boolean isAContainsB(String tableANameStr,
			String tableBNameStr) {
		List<TableFK> tableFKList = getTableFKList(tableANameStr);
		if (tableFKList == null || tableFKList.isEmpty()) {
			return false;
		} else {
			for (TableFK tableFK : tableFKList) {
				if (tableFK.PKTABLE_NAME.equals(tableBNameStr)) {
					return true;
				}
			}
			return false;
		}
	}

	private static Map<String, List<TableFK>> fetchTableFkListMap()
			throws Exception {
		Connection connection = JDBCTools.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		List<String> tableList = fetchTableList(connection, metaData);
		for (String table : tableList) {
			List<TableFK> tableFKList = new ArrayList<TableFK>();
			ResultSet importedKeys = metaData
					.getImportedKeys(null, null, table);
			while (importedKeys.next()) {
				TableFK tableFK = new TableFK();
				tableFK.table = table;
				tableFK.PKTABLE_SCHEM = importedKeys.getString("PKTABLE_SCHEM");// 引用的外键表模式
				tableFK.PKTABLE_NAME = importedKeys.getString("PKTABLE_NAME");// 引用的外键表名
				tableFK.PKCOLUMN_NAME = importedKeys.getString("PKCOLUMN_NAME");// 引用的外键表主键列名
				tableFK.FKTABLE_SCHEM = importedKeys.getString("FKTABLE_SCHEM");// 主表模式
				tableFK.FKTABLE_NAME = importedKeys.getString("FKTABLE_NAME");// 主表名
				tableFK.FKCOLUMN_NAME = importedKeys.getString("FKCOLUMN_NAME");// 主表外键列名
				tableFK.FK_NAME = importedKeys.getString("FK_NAME");// 主表外键名
				tableFK.PK_NAME = importedKeys.getString("PK_NAME");// 引用的外键表主键名
				tableFKList.add(tableFK);
				tableFKMap.put(tableFK.FK_NAME, tableFK);
			}
			importedKeys.close();
			tableFKListMap.put(table, tableFKList);
		}
		return tableFKListMap;
	}

	private static List<String> fetchTableList(Connection connection,
			DatabaseMetaData metaData) throws SQLException, GlobalException {
		List<String> tableList = new ArrayList<String>();
		try {
			DatabaseMetaData dm = connection.getMetaData();
			String[] para = new String[1];
			para[0] = "TABLE";
			ResultSet resultset = dm.getTables(null, null, null, para);
			while (resultset.next()) {
				String temp = resultset.getString(3);
				if (null != tablePrefixArray && 0 < tablePrefixArray.length) {
					for (String tablePrefix : tablePrefixArray) {
						if (temp.startsWith(tablePrefix)) {
							tableList.add(temp);
						}
					}
				} else {
					tableList.add(temp);
				}

			}
			resultset.close();
		} catch (SQLException e) {
			throw new GlobalException("" + e.getMessage());
		}
		return tableList;
	}

	private static List<TableFK> getTableFKList(String table) {
		return tableFKListMap.get(table);
	}

}

class TableFK {
	public String table = "";
	public String PKTABLE_SCHEM = "";// 引用的外键表模式
	public String PKTABLE_NAME = "";// 引用的外键表名
	public String PKCOLUMN_NAME = "";// 引用的外键表主键列名
	public String FKTABLE_SCHEM = "";// 主表模式
	public String FKTABLE_NAME = "";// 主表名
	public String FKCOLUMN_NAME = "";// 主表外键列名
	public String FK_NAME = "";// 主表外键名
	public String PK_NAME = "";// 引用的外键表主键名
}

class SortedTableNameByFK {
	private int sort;
	private String tableNameStr;

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTableNameStr() {
		return tableNameStr;
	}

	public void setTableNameStr(String tableNameStr) {
		this.tableNameStr = tableNameStr;
	}

}