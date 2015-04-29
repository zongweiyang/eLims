package DBstep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class Signature {
	private boolean mResult;
	private String mMarkType;
	private int mMarkSize;
	private byte[] mMarkBody;

	public void ExecuteRun(HttpServletRequest request, HttpServletResponse resp) throws IOException, GlobalException {
		DBstep.iFileUpLoad2000 FileObj = new DBstep.iFileUpLoad2000(request);
		DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
		String mUserName = FileObj.Request("UserName");
		String mUserId = FileObj.Request("UserId");
		String mOrgId = FileObj.Request("OrgId");
		String mPassword = FileObj.Request("Password");
		String mMarkName = FileObj.Request("MarkName");
		if (FileObj.FileName("MarkFile").equalsIgnoreCase("")) {
			mMarkSize = 0;
		} else {
			mMarkSize = FileObj.FileSize("MarkFile");
			mMarkType = FileObj.ExtName("MarkFile");
			mMarkBody = FileObj.FileBody("MarkFile");
		}

		if (null != DbaObj.Conn) {
			String mSql = "select MarkName from Signature where MarkName='" + mMarkName + "' AND UserId = '" + mUserId + "'";
			try {
				ResultSet result = DbaObj.ExecuteQuery(mSql);
				if (result.next()) {
					mResult = false;
				} else {
					mSql = "Insert Into Signature (UserName,Password,MarkName,MarkSize,MarkType,MarkBody,UserId) values (?,?,?,?,?,?,?)";
					mResult = true;
				}
				result.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				mResult = false;
				throw new GlobalException("" + e.getMessage());
			}

			if (mResult) {
				java.sql.PreparedStatement prestmt = null;
				try {

					prestmt = DbaObj.prepareStatement(mSql);
					prestmt.setString(1, mUserName);
					prestmt.setString(2, mPassword);
					prestmt.setString(3, mMarkName);
					prestmt.setInt(4, mMarkSize);
					prestmt.setString(5, mMarkType);
					prestmt.setBytes(6, mMarkBody);
					prestmt.setString(7, mUserId);
					//DbaObj.Conn.setAutoCommit(true) ;
					prestmt.execute();
					//DbaObj.Conn.commit();
					prestmt.close();
					mMarkBody = null;
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.toString());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				} finally {
					DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
				}
			}
		}

		if (mResult) {

			resp.sendRedirect("/iWebOffice/Signature/SignatureList.jsp");
		} else {
			request.getSession().setAttribute("info", "保存失败，数据库中已存在相同的印签〖" + mMarkName + "〗");
			resp.sendRedirect("/iWebOffice/Signature/SignatureAddFrm.jsp");
		}
	}

	public String getPhoto(InputStream inputStream, String imgName) throws GlobalException {
		try {
			String targetDirectory = ServletActionContext.getServletContext().getRealPath("img/stamp");
			File target = new File(targetDirectory, imgName + ".gif");
			FileOutputStream out = new FileOutputStream(target);
			byte[] buf = new byte[1024];
			int len;
			while ((len = inputStream.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			inputStream.close();
			out.close();
			return "img/stamp/" + imgName + ".gif";
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

}
