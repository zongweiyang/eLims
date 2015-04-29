/**
 * Operation Excel with Java
 *
 */

package cn.labsoft.labos.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

public class OperationExcel implements Serializable {
	// 定义成员变量用于方法之间传递函数
	private String fileName;
	private int numOfSheet;
	private int numOfRow;
	private int numOfCol1;
	private int numOfCol2;

	public OperationExcel() {
		// Debug.print("OperationExcel have created");
	}

	public static String[][] readExcel(String filePath, int sheetNum) throws Exception {
		try {
			InputStream is = new FileInputStream(filePath);
			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);
			// 检查工作表的有效性
			int sheets = rwb.getNumberOfSheets();
			if (sheetNum < 0 || sheetNum >= sheets || sheets == 0)
				throw new Exception("没此工作表");
			// 获取一个工作表Sheet
			Sheet sht = rwb.getSheet(sheetNum);
			// System.err.println("工作表: "+sht.getName()+sht.getRows());
			// 获取行列值
			Cell cell[] = null;
			String value[][] = null;
			value = new String[sht.getRows()][];
			for (int r = 0; r < sht.getRows(); r++) {
				cell = sht.getRow(r);
				value[r] = new String[cell.length];
				for (int c = 0; c < cell.length; c++) {
					value[r][c] = cell[c].getContents();
				}
			}
			if(null!=is){
				is.close();
			}
			return value;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	// 此方法读取双列值
	public String[][] readDoubleCols(String fileName, int numOfSheet, int firNumOfCol, int secdNumOfCol) throws GlobalException// throws IOException
	{
		// 定义一个二维数组用于返回两列值
		String[][] strCols = null;

		try {
			// fileNullOrNot(fileName);

			// 构建Workbook对象，只读WorkBook对象
			// 从输入流创建WorkBook
			// 下一行文件及路径从参数中获取
			InputStream is = new FileInputStream(fileName);

			Workbook rwb = Workbook.getWorkbook(is);

			// 判断工作表的有效性
			if (numOfSheet >= rwb.getNumberOfSheets() || numOfSheet < 0)
				throw new Exception("没有此工作表");

			// 建立一个工作表
			Sheet rs = rwb.getSheet(numOfSheet);// JavaBean中传入工作表值
			strCols = new String[rs.getRows() - 1][2];

			// ***JavaBean中判定此列是否效值
			if (firNumOfCol >= rs.getColumns() || firNumOfCol < 0 || secdNumOfCol >= rs.getColumns() || secdNumOfCol < 0)
				throw new Exception("表格中没有此列");

			// 获取指定列值
			int r2 = 0;
			int r1 = 0;
			Cell[] cellCols1 = rs.getColumn(firNumOfCol);
			Cell[] cellCols2 = rs.getColumn(secdNumOfCol);
			while (r1 < rs.getRows() - 1 && r2 < rs.getRows() - 1) {
				// 取一列值并切转换为字符串

				strCols[r1][0] = cellCols1[r1].getContents();

				// 取另一列值并切转换为字符串

				strCols[r2][1] = cellCols2[r2].getContents();
				r1 = r1 + 1;
				r2 = r2 + 1;
			}
			rwb.close();
		} catch (Exception ex) {
			ex.getMessage();
			throw new GlobalException("" + ex.getMessage());
		}

		// 返回二维数组
		return strCols;
	}

	// 此方法读取整张工作表
	/**
	 * 根据文件路径和 第几个sheet页得到结果数组
	 * @throws GlobalException 
	 */
	public String[][] readOneSheet(String nameFile, int numOfSheet) throws GlobalException// throws IOException
	{
		// 定义一个二维数组用于返回一张工作表
		String[][] strSheet = null;
		try {
			// fileNullOrNot(nameFile);

			// 构建Workbook对象，只读WorkBook对象
			// 从数人流创建WorkBook
			InputStream is = new FileInputStream(nameFile);
			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);
			// 检查工作表的有效性
			if (numOfSheet < 0 || numOfSheet >= rwb.getNumberOfSheets())
				throw new Exception("没此工作表");

			// 获取一个工作表Sheet
			Sheet sht = rwb.getSheet(numOfSheet);
			System.err.println("工作表: " + sht.getName() + sht.getRows());

			// 实例字符串数组
			strSheet = new String[sht.getRows()][sht.getColumns()];

			// 获取行列值
			Cell cell = null;
			for (int r = 0; r < sht.getRows(); r++) {
				for (int c = 0; c < sht.getColumns(); c++) {
					cell = sht.getCell(c, r);
					strSheet[r][c] = cell.getContents();
				}
			}
			rwb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
		return strSheet;
	}

	// 此方法读取工作表的名称
	public String readSheetName(String nameFile, int numOfSheet) throws GlobalException// throws IOException
	{
		// 定义一个二维数组用于返回一张工作表

		String sheetName = "";
		try {
			// fileNullOrNot(nameFile);

			// 构建Workbook对象，只读WorkBook对象
			// 从数人流创建WorkBook
			InputStream is = new FileInputStream(nameFile);

			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);

			// 检查工作表的有效性
			if (numOfSheet < 0 || numOfSheet >= rwb.getNumberOfSheets())
				throw new Exception("没此工作表");

			// 建立一个工作表Sheet
			Sheet sht = rwb.getSheet(numOfSheet);
			sheetName = sht.getName();
			rwb.close();
		} catch (Exception ex) {
			ex.getMessage();
			throw new GlobalException("" + ex.getMessage());
		}
		return sheetName;
	}

	// 此方法读取整张工作表
	public String[][] readOneSheetByRows(String nameFile, int numOfSheet, int start, int end, int col) throws GlobalException// throws IOException
	{
		// 定义一个二维数组用于返回一张工作表
		String[][] strSheet = null;
		try {
			// 构建Workbook对象，只读WorkBook对象
			// 从数人流创建WorkBook
			InputStream is = new FileInputStream(nameFile);
			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);
			// 检查工作表的有效性
			if (numOfSheet < 0 || numOfSheet >= rwb.getNumberOfSheets())
				throw new Exception("没此工作表");

			// 建立一个工作表Sheet
			Sheet sht = rwb.getSheet(numOfSheet);
			// 实例字符串数组
			int bal = end - start + 1;
			strSheet = new String[bal][col];

			// 获取行列值
			Cell cell = null;
			int m = 0;
			for (int r = start - 1; r < end; r++) {
				for (int c = 0; c < col; c++) {
					cell = sht.getCell(c, r);
					strSheet[m][c] = cell.getContents();
				}
				m++;
			}
			rwb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
		return strSheet;
	}

	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param nameFile
	 * @param @param numOfSheet  sheet也所在的数目 第一个是0 ...
	 * @param @param rowNum      从第几行开始 第一行是0
	 * @param @param cellNum     第一个列里面的值   第一行是0
	 * @param @param cellNumSecond  第二个列里面的值  第一行是0
	 * @param @return  Properties
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	public Properties readOneSheetByRowAndCell(String nameFile, int numOfSheet, int rowNum, int cellNum, int cellNumSecond) throws GlobalException// throws IOException
	{
		// 定义一个二维数组用于返回一张工作表
		Properties properties = new Properties();
		String strSheet = "";
		try {
			// fileNullOrNot(nameFile);

			// 构建Workbook对象，只读WorkBook对象
			// 从数人流创建WorkBook
			InputStream is = new FileInputStream(nameFile);
			// 创建一个工作薄Workbook
			Workbook rwb = Workbook.getWorkbook(is);
			// 检查工作表的有效性
			if (numOfSheet < 0 || numOfSheet >= rwb.getNumberOfSheets())
				throw new Exception("没此工作表");

			// 获取一个工作表Sheet
			Sheet sht = rwb.getSheet(numOfSheet);
			// System.err.println("工作表: "+sht.getName()+sht.getRows());

			// 获取行列值
			Cell cell = null;

			for (int r = rowNum; r < sht.getRows(); r++) {
				String no = "";
				String value = "";

				no = sht.getCell(cellNum, r).getContents();

				// if("1".equals(type)){
				NumberCell numberCell = (NumberCell) sht.getCell(cellNumSecond, r);
				String namberValue = numberCell.getContents();
				value = namberValue;
				// cell = sht.getCell(cellNumScender, r);
				// value=cell.getContents();
				// value=String.valueOf(namberValue);
				// }else{
				// cell = sht.getCell(cellNum, r);
				// value=cell.getContents();
				// type="1";
				// }
				if (value != null && !value.equals("")) {
					value = value.trim();
					if ("null".equals(value)) {
						value = "";
					}
				}
				if (null != no) {
					no = no.toUpperCase().trim();
				}
				properties.setProperty(no, value);
			}
			rwb.close();
		} catch (Exception ex) {
			InputStream is;
			try {
				properties = new Properties();
				is = new FileInputStream(nameFile);
				// 创建一个工作薄Workbook
				Workbook rwb = Workbook.getWorkbook(is);
				// 检查工作表的有效性
				if (numOfSheet < 0 || numOfSheet >= rwb.getNumberOfSheets())
					throw new Exception("没此工作表");
				Sheet sht = rwb.getSheet(numOfSheet);
				Cell cell = null;
				for (int r = rowNum; r < sht.getRows(); r++) {
					String no = "";
					String value = "";
					no = sht.getCell(cellNum, r).getContents();
					value = sht.getCell(cellNumSecond, r).getContents();
					if (value != null && !value.equals("")) {
						value = value.trim();
					}
					if (null != no) {
						no = no.toUpperCase().trim();
					}
					properties.setProperty(no, value);
				}
				rwb.close();
			} catch (Exception e) {
				throw new GlobalException("" + e.getMessage());
			}
		}
		return properties;
	}

	public static void main(String[] args) throws GlobalException {
		System.out.println("start--------");
		OperationExcel OE = new OperationExcel();
		String filePath = "C:/总氮.xls";
		// String textResult=OE.readOneSheetByRowAndCell(filePath,0,4,5,"");
		// String textResult2=OE.readOneSheetByRowAndCell(filePath,0,4,0,"");
		// System.out.println(textResult);
		// System.out.println(textResult2);
		Properties proterties = OE.readOneSheetByRowAndCell(filePath, 0, 4, 0, 5);
		if (null != proterties) {
			System.out.println(proterties.get("K100864") + "***");
			System.out.println(proterties.get("K100864") + "***");
			System.out.println(proterties.get("K100865") + "***");
			System.out.println(proterties.get("K100865") + "***");
			System.out.println(proterties.get("K100866") + "***");
			System.out.println(proterties.get("K100866") + "***");
			System.out.println(proterties.get("K100867") + "***");
			System.out.println(proterties.get("K100867") + "***");
			System.out.println(proterties.get("K100868") + "***");
			System.out.println(proterties.get("K100868") + "***");
		}
		//	   
	}

}
