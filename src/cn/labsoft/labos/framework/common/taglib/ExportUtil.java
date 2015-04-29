package cn.labsoft.labos.framework.common.taglib;

import java.util.Map;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.csv.ExportCSV;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;
import cn.labsoft.labos.utils.word.ExportWord;

/**
 * 导出
 * <strong>Title : Export </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 30, 2014 2:01:35 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class ExportUtil implements Runnable {
	private final String EXCEL = "EXCEL";
	private final String PDF = "PDF";
	private final String WORD = "WORD";
	private final String CSV = "CSV";
	private String source;
	private String type;
	private String target;
	private Map<String, Object> beansMaps;
	

	public void setType(String type) {
		this.type = type;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setBeansMaps(Map<String, Object> beansMaps) {
		this.beansMaps = beansMaps;
	}

	public ExportUtil() {
	}
	
	@Override
	public void run() {
		try {
			if(EXCEL.equalsIgnoreCase(type)){
				ExportExcel.exportExcel(source, target, beansMaps);
			}else if(WORD.equalsIgnoreCase(type)){
				ExportWord.exportWord(source, target, beansMaps);
			}else if(CSV.equalsIgnoreCase(type)){
				ExportCSV.exportCSV(source, target, beansMaps);
			}else if(PDF.equalsIgnoreCase(type)){
				
			}
		} catch (GlobalException e) {
			//e.printStackTrace();
		}
	}



}

