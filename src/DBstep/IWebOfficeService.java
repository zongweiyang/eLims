package DBstep;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.PropertiesTools;

public class IWebOfficeService {
	public List<IWebOfficeVo> getTempList(String tempType) throws GlobalException {
		List<IWebOfficeVo> voList = new ArrayList<IWebOfficeVo>();
		String sqlString = "SELECT RecordID,FileName FROM OFFICE_TEMPLATE_FILE where tempType = '" + tempType + "' ORDER BY FileName ASC";
		iDBManager2000 manager2000 = new iDBManager2000();
		try {
			ResultSet result = manager2000.ExecuteQuery(sqlString);
			while (result.next()) {
				IWebOfficeVo vo = new IWebOfficeVo();
				vo.setTempId(result.getString(1));
				vo.setTempName(result.getString(2).replace("TS-QC-", ""));
				voList.add(vo);
			}
			result.close();
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		} finally {
			DataSourceUtils.releaseConnection(manager2000.Conn, manager2000.dataSource);
		}

		return voList;
	}

	public IWebOfficeVo getTempInfo(String recordId) throws GlobalException {
		IWebOfficeVo vo = new IWebOfficeVo();
		String sqlString = "SELECT RecordID,FileName,Descript,FileType,FileBody FROM OFFICE_TEMPLATE_FILE where recordID = '" + recordId + "'";
		iDBManager2000 manager2000 = new iDBManager2000();
		try {
			ResultSet result = manager2000.ExecuteQuery(sqlString);
			while (result.next()) {
				vo.setTempId(result.getString(1));
				vo.setTempName(result.getString(2));
				vo.setTempDescript(result.getString(3));
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + PropertiesTools.getPropertiesValueByFileAndKey("resource.properties", "tempPath").replace("/", File.separator);
				File f = new File(path);
				if (!f.exists()) {
					f.mkdirs();
				}
				path = new WebOffice().SaveByteToFile(path, recordId);
				vo.setTempPath(path);
			}
			result.close();
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		} finally {
			DataSourceUtils.releaseConnection(manager2000.Conn, manager2000.dataSource);
		}
		return vo;
	}
}
