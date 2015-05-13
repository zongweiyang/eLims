package DBstep;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.datasource.DataSourceUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.FileUtils;

public class WebOffice {
	private String saveType; // 文件存储类型 file为本地，data为数据库（保存和调用时必须参数，没有时默认为data）
	private int mFileSize; // 文件大小
	private byte[] mFileBody;
	private String mFileName;// 文件名称
	private String mFileType;// 文件类型
	private String mFileDate;
	private String mFileID; // 文件id
	private String mRecordID;
	private String mTemplate;
	private String mDateTime;
	private String mOption;
	private String mMarkName;
	private String mPassword;
	private String mMarkList;
	private String mBookmark;
	private String mDescript;
	private String mHostName;
	private String mMarkGuid;
	private String mCommand;
	private String mContent;
	private String mHtmlName;
	private String mDirectory;
	private String mFilePath;// 文件路径
	private String mUserName;
	private int mColumns;
	private int mCells;
	// private String mLocalFile;
	private String mBusId;
	private String mBusType;
	private String mRemoteFile;
	private String mLabelName;
	private String mImageName;
	private String mTableContent;
	private String mTempType;
	// 打印控制
	private String mOfficePrints;
	private int mCopies;
	// 自定义信息传递
	private String mInfo;
	private DBstep.iMsgServer2000 MsgObj;
	private DBstep.iDBManager2000 DbaObj;

	private static String SERVERPATH = "";// 服务器相对路径

	// ==============================↓文档、模板管理代码【开始】↓==============================
	// 调出文档，将文档内容保存在mFileBody里，以便进行打包
	private boolean LoadFile() throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT FileBody,FileSize FROM Doc_File WHERE 1=1 ";
		if (null != mRecordID && !"".equals(mRecordID)
				&& !"null".equals(mRecordID))
			Sql += " AND RecordID = '" + mRecordID + "'";
		if (null != mBusType && !"".equals(mBusType)
				&& !"null".equals(mBusType))
			Sql += " AND BusType = '" + mBusType + "'";
		if (null != mBusId && !"".equals(mBusId) && !"null".equals(mBusId))
			Sql += " AND BusId = '" + mBusId + "'";
		Sql += " ORDER BY FileDate DESC";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("FileBody");
							if (result.wasNull()) {
								mFileBody = null;
							}
							mResult = true;
						} catch (Exception ex) {
							Log4J.error("WebOffice...." + ex.getMessage());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
				} catch (SQLException e) {
					Log4J.error("WebOffice...." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 保存文档，如果文档存在，则覆盖，不存在，则添加
	private boolean SaveFile() throws GlobalException {
		boolean mResult = false;
		// int iFileId = -1;
		String Sql = "SELECT * FROM Doc_File WHERE BusId = '" + mBusId
				+ "' AND BusType='" + mBusType + "' AND  RecordID='"
				+ mRecordID + "'";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						Sql = "update Doc_File set RecordID=?,FileName=?,FileType=?,FileSize=?,FileDate=?,FileBody=?,FilePath=?,UserName=?,Descript=?,BusId=?,BusType=? WHERE RecordID='"
								+ mRecordID + "'";
					} else {
						Sql = "insert into Doc_File (RecordID,FileName,FileType,FileSize,FileDate,FileBody,FilePath,UserName,Descript,BusId,BusType) values (?,?,?,?,?,?,?,?,?,?,? )";
					}
					result.close();
				} catch (SQLException e) {
					Log4J.error("WebOffice..." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
				java.sql.PreparedStatement prestmt = null;
				try {
					prestmt = DbaObj.prepareStatement(Sql);
					prestmt.setString(1, mRecordID);
					prestmt.setString(2, mFileName);
					prestmt.setString(3, mFileType);
					prestmt.setInt(4, mFileSize);
					prestmt.setString(5, mFileDate);
					prestmt.setBytes(6, mFileBody);
					prestmt.setString(7, mFilePath.replace(SERVERPATH, ""));
					prestmt.setString(8, mUserName);
					prestmt.setString(9, mDescript); // "通用版本"
					prestmt.setString(10, mBusId);
					prestmt.setString(11, mBusType);
					prestmt.execute();
					prestmt.close();
					mResult = true;
				} catch (SQLException e) {
					Log4J.error("WebOffice..." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 调出模板文档，将模板文档内容保存在mFileBody里，以便进行打包
	private boolean LoadTemplate() throws GlobalException {
		if ("last".equals(mTempType)) {
			return getLastFile();
		} else {
			return getTempFile();
		}
	}

	private boolean getLastFile() throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT FileBody,FileSize,RecordID FROM Doc_file WHERE RecordID = (select RecordID from lab_office_doc where Template ='"
				+ mTemplate + "' order by FileDate desc limit 0,1) ";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("FileBody");
							if (result.wasNull()) {
								mFileBody = null;
							}
							mResult = true;
						} catch (Exception ex) {
							Log4J.error("WebOffice..." + ex.getMessage());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
				} catch (SQLException e) {
					Log4J.error("WebOffice..." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	public String SaveByteToFile(String filePath, String tempId)
			throws GlobalException {
		// boolean mResult = false;
		String Sql = "SELECT FileBody,FileSize,fileType FROM Temp_File WHERE RecordID='"
				+ tempId + "'";
		try {
			DbaObj = new iDBManager2000();
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("FileBody");
							// 创建outputFile对象 输出到服务器产生文件副本
							File pathFile = new File(filePath);
							if (!pathFile.exists())
								pathFile.mkdirs();
							filePath += File.separator + tempId
									+ result.getString("fileType");
							FileUtils.getFileFromBytes(mFileBody, filePath);
							// 使用excel模板写入文件数据
							if (result.wasNull()) {
								mFileBody = null;
							}
							// mResult = true;
						} catch (Exception ex) {
							ex.printStackTrace();
							Log4J.error("WebOffice..." + ex.getMessage());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
				} catch (SQLException e) {
					Log4J.error("WebOffice..." + e.getMessage());
					throw new GlobalException("" + e.getMessage());
					// mResult = false;
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return filePath;
	}

	private boolean getTempFile() throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT FileBody,FileSize FROM Temp_File WHERE RecordID='"
				+ mTemplate + "'";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("FileBody");
							if (result.wasNull()) {
								mFileBody = null;
							}
							mResult = true;
						} catch (Exception ex) {
							ex.printStackTrace();
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());

						}
					}
					result.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 保存模板文档，如果模板文档存在，则覆盖，不存在，则添加
	private boolean SaveTemplate() throws GlobalException {
		boolean mResult = false;
		// int iFileId = -1;
		String Sql = "SELECT * FROM Temp_File WHERE RecordID='" + mTemplate
				+ "'";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						Sql = "update Temp_File set RecordID=?,FileName=?,FileType=?,FileSize=?,FileDate=?,FileBody=?,FilePath=?,UserName=?,Descript=? WHERE RecordID='"
								+ mTemplate + "'";
					} else {
						Sql = "insert into Template_File (RecordID,FileName,FileType,FileSize,FileDate,FileBody,FilePath,UserName,Descript) values (?,?,?,?,?,?,?,?,? )";
					}
					result.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
					mResult = false;
				}
				java.sql.PreparedStatement prestmt = null;
				try {
					prestmt = DbaObj.prepareStatement(Sql);
					prestmt.setString(1, mTemplate);
					prestmt.setString(2, mFileName);
					prestmt.setString(3, mFileType);
					prestmt.setInt(4, mFileSize);
					prestmt.setString(5, mFileDate);
					prestmt.setBytes(6, mFileBody);
					prestmt.setString(7, mFilePath);
					prestmt.setString(8, mUserName);
					prestmt.setString(9, mDescript); // "通用版本"
					prestmt.execute();
					prestmt.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.toString());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// ==============================↑文档、模板管理代码【结束】↑==============================

	// ==============================↓版本管理代码【开始】↓==============================
	// 列出所有版本信息
	private boolean ListVersion() throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT FileID,FileDate,UserName,Descript FROM Vsion_File WHERE RecordID='"
				+ mRecordID + "'";
		mFileID = "\r\n";
		mDateTime = "保存时间\r\n";
		mUserName = "用户名\r\n";
		mDescript = "版本说明\r\n";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					while (result.next()) {
						try {
							mFileID += String.valueOf(result.getInt("FileID"))
									+ "\r\n"; // 文件号列表
							mDateTime += result.getString("FileDate") + "\r\n"; // 日期列表
							mUserName += result.getString("UserName") + "\r\n"; // 用户名列表
							mDescript += result.getString("Descript") + "\r\n"; // 如果说明信息里有回车，则将回车变成>符号
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 调入选中版本，通过文件号调用mFileID,并把文件放入mFileBody里，以便进行打包
	private boolean LoadVersion(String mFileID) throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT FileBody,FileSize FROM Vsion_File WHERE RecordID='"
				+ mRecordID + "' and  FileID=" + mFileID;
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("FileBody");
							if (result.wasNull()) {
								mFileBody = null;
							}
							mResult = true;
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 保存版本，将该版本文件存盘，并将说明信息也保存起来
	private boolean SaveVersion() throws GlobalException {
		boolean mResult = false;
		// int iFileId = -1;
		String Sql = "insert into Version_File (RecordID,FileName,FileType,FileSize,FileDate,FileBody,FilePath,UserName,Descript) values (?,?,?,?,?,?,?,?,? )";
		try {
			if (null != DbaObj.Conn) {
				java.sql.PreparedStatement prestmt = null;
				try {
					prestmt = DbaObj.prepareStatement(Sql);
					prestmt.setString(1, mRecordID);
					prestmt.setString(2, mFileName);
					prestmt.setString(3, mFileType);
					prestmt.setInt(4, mFileSize);
					prestmt.setString(5, mFileDate);
					prestmt.setBytes(6, mFileBody);
					prestmt.setString(7, mFilePath);
					prestmt.setString(8, mUserName);
					prestmt.setString(9, mDescript); // "通用版本"
					prestmt.execute();
					prestmt.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.toString());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// ==============================↑版本管理代码【结束】↑==============================

	// ==============================↓标签管理代码【开始】↓==============================
	// 取得书签列表
	private boolean ListBookmarks() throws GlobalException {
		boolean mResult = false;
		String Sql = "SELECT * FROM Bookmarks ";
		mBookmark = "";
		mDescript = "";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					while (result.next()) {
						try {
							mBookmark += result.getString("BookMarkName")
									+ "\r\n"; // 用户名列表
							mDescript += result.getString("BookMarkDesc")
									+ "\r\n"; // 如果说明信息里有回车，则将回车变成>符号
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 装入书签
	private boolean LoadBookMarks() throws GlobalException {
		boolean mResult = false;
		String Sql = " select b.BookMarkName,b.BookMarkText from Temp_BookMarks a,BookMarks b where a.BookMarkname=b.BookMarkName and a.RecordID='"
				+ mTemplate + "'";
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					while (result.next()) {
						try {
							// 说明：我们测试程序把SQL语句直接写到替换标签内容
							// 实际使用中，这个标签内容是通过Sql语句得到的。
							// 生成SQL查询语句 result.getString("BookMarkText") & "条件"
							// 当前纪录号位 mRecordID
							// BookMarkValue=生成SQL运行结果
							String mBookMarkName = result
									.getString("BookMarkName");
							String mBookMarkValue = result
									.getString("BookMarkText");
							MsgObj.SetMsgByName(mBookMarkName, mBookMarkValue);
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 保存书签
	private boolean SaveBookMarks() throws GlobalException {
		boolean mResult = false;
		String mBookMarkName;
		int mIndex;
		try {
			if (null != DbaObj.Conn) {
				try {
					java.sql.PreparedStatement prestmt = null;
					String Sql = "DELETE FROM Temp_BookMarks Where RecordID='"
							+ mTemplate + "'";
					prestmt = DbaObj.prepareStatement(Sql);

					prestmt.execute();

					prestmt.close();
					for (mIndex = 7; mIndex <= MsgObj.GetFieldCount() - 1; mIndex++) {
						java.sql.PreparedStatement prestmtx = null;
						mBookMarkName = MsgObj.GetFieldName(mIndex);
						Sql = "insert into Temp_BookMarks (RecordId,BookMarkName) values ('"
								+ mTemplate + "','" + mBookMarkName + "')";
						prestmtx = DbaObj.prepareStatement(Sql);

						prestmtx.execute();

						prestmtx.close();
					}
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.toString());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// ==============================↑标签管理代码【结束】↑==============================

	// ==============================↓签章管理代码【开始】↓==============================
	// 取得签名列表
	private boolean LoadMarkList(String userId) throws GlobalException {
		String Sql = "SELECT MarkName FROM Signature where UserId = '" + userId
				+ "'";
		mMarkList = "";
		boolean mResult = false;
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					while (result.next()) {
						try {
							mMarkList += result.getString("MarkName") + "\r\n";
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
					mResult = true;
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 调入签名纪录
	private boolean LoadMarkImage(String vMarkName, String vPassWord)
			throws GlobalException {
		String Sql = "SELECT MarkBody,MarkType FROM Signature WHERE MarkName='"
				+ vMarkName + "' and PassWord='" + vPassWord + "'";
		boolean mResult = false;
		try {
			if (null != DbaObj.Conn) {
				try {
					ResultSet result = DbaObj.ExecuteQuery(Sql);
					if (result.next()) {
						try {
							mFileBody = result.getBytes("MarkBody");
							mFileType = result.getString("MarkType");
							mResult = true;
						} catch (Exception ex) {
							System.out.println(ex.toString());
							throw new GlobalException("" + ex.getMessage());
						}
					}
					result.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					mResult = false;
					throw new GlobalException("" + e.getMessage());
				}
			}
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
		return (mResult);
	}

	// 保存签名
	private boolean SaveSignature() throws GlobalException {
		boolean mResult = false;
		String Sql = "insert into Doc_Signature (RecordID,MarkName,UserName,DateTime,HostName,MarkGuid) values (?,?,?,?,?,? ) ";
		if (null != DbaObj.Conn) {
			java.sql.PreparedStatement prestmt = null;
			try {
				prestmt = DbaObj.prepareStatement(Sql);
				prestmt.setString(1, mRecordID);
				prestmt.setString(2, mMarkName);
				prestmt.setString(3, mUserName);
				prestmt.setString(4, mDateTime);
				prestmt.setString(5, mHostName);
				prestmt.setString(6, mMarkGuid);
				prestmt.execute();
				prestmt.close();
				mResult = true;
			} catch (SQLException e) {
				System.out.println(e.toString() + Sql);
				mResult = false;
				throw new GlobalException("" + e.getMessage());
			} finally {
				DataSourceUtils.releaseConnection(DbaObj.Conn,
						DbaObj.dataSource);
			}
		}
		return (mResult);
	}

	// 列出所有签名
	private boolean LoadSignature() throws GlobalException {
		boolean mResult = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Sql = "SELECT MarkName,UserName,DateTime,HostName,MarkGuid FROM Doc_Signature WHERE RecordID='"
				+ mRecordID + "'";
		mMarkName = "印章名称\r\n";
		mUserName = "签名人\r\n";
		mDateTime = "签章时间\r\n";
		mHostName = "客户端IP\r\n";
		mMarkGuid = "序列号\r\n";
		if (null != DbaObj.Conn) {
			try {
				mResult = true;
				ResultSet result = DbaObj.ExecuteQuery(Sql);
				while (result.next()) {
					mMarkName += result.getString("MarkName") + "\r\n"; // 文件号列表
					mUserName += result.getString("UserName") + "\r\n"; // 日期列表
					mDateTime += formatter.format(result
							.getTimestamp("DateTime")) + "\r\n";
					mHostName += result.getString("HostName") + "\r\n";
					mMarkGuid += result.getString("MarkGuid") + "\r\n";
				}
				result.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				mResult = false;
				throw new GlobalException("" + e.getMessage());
			} finally {
				DataSourceUtils.releaseConnection(DbaObj.Conn,
						DbaObj.dataSource);
			}
		}
		return (mResult);
	}

	// ==============================↑签章管理代码【结束】↑==============================

	// ==============================↓扩展功能代码【开始】↓==============================
	// 调出所对应的文本
	private boolean LoadContent() {
		boolean mResult = false;
		// 打开数据库
		// 根据 mRecordID 或 mFileName 等信息
		// 提取文本信息付给mContent即可。
		// 本演示假设取得的文本信息如下：
		mContent = "";
		mContent += "本文的纪录号：" + mRecordID + "\n";
		mContent += "本文的文件名：" + mFileName + "\n";
		mContent += "    这个部分请自己加入，和你们的数据库结合起来就可以了\n";
		mResult = true;
		return (mResult);
	}

	// 保存所对应的文本
	private boolean SaveContent() {
		boolean mResult = false;
		// 打开数据库
		// 根据 mRecordID 或 mFileName 等信息
		// 插入文本信息 mContent里的文本到数据库中即可。
		mResult = true;
		return (mResult);
	}

	// 增加行并填充表格内容
	private boolean GetWordTable() throws GlobalException {
		int i, n;
		// String strI, strN;
		boolean mResult;
		mColumns = 3;
		mCells = 8;
		MsgObj.MsgTextClear();
		MsgObj.SetMsgByName("COLUMNS", String.valueOf(mColumns)); // 设置表格行
		MsgObj.SetMsgByName("CELLS", String.valueOf(mCells)); // 设置表格列
		// 该部分内容可以从数据库中读取
		try {
			for (i = 1; i <= mColumns; i++) {
				// strI = String.valueOf(i);
				for (n = 1; n <= mCells; n++) {
					MsgObj.SetMsgByName(String.valueOf(i) + String.valueOf(n),
							"内容" + DbaObj.GetDateTime());
				}
			}
			mResult = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			mResult = false;
			throw new GlobalException("" + e.getMessage());
		}
		return (mResult);
	}

	// 更新打印份数
	private boolean UpdataCopies(int mLeftCopies) {
		boolean mResult = true;
		// 该函数可以把打印减少的次数记录到数据库
		// 根据自己的系统进行扩展该功能
		return mResult;
	}

	// ==============================↑扩展功能代码【结束】↑==============================

	// ==============================↓接收流、写回流代码【开始】↓==============================
	// 取得客户端发来的数据包
	@SuppressWarnings("unused")
	private byte[] ReadPackage(HttpServletRequest request)
			throws GlobalException {
		byte mStream[] = null;
		int totalRead = 0;
		int readBytes = 0;
		int totalBytes = 0;
		try {
			totalBytes = request.getContentLength();
			mStream = new byte[totalBytes];
			while (totalRead < totalBytes) {
				request.getInputStream();
				readBytes = request.getInputStream().read(mStream, totalRead,
						totalBytes - totalRead);
				totalRead += readBytes;
				continue;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new GlobalException("" + e.getMessage());
		}
		return (mStream);
	}

	// 发送处理后的数据包
	@SuppressWarnings("unused")
	private void SendPackage(HttpServletResponse response)
			throws GlobalException {
		try {
			ServletOutputStream OutBinarry = response.getOutputStream();
			OutBinarry.write(MsgObj.MsgVariant());
			OutBinarry.flush();
			OutBinarry.close();
		} catch (IOException e) {
			System.out.println(e.toString());
			throw new GlobalException("" + e.getMessage());
		}
	}

	// ==============================↑接收流、写回流代码【结束】↑==============================

	public void ExecuteRun(HttpServletRequest request,
			HttpServletResponse response) throws GlobalException {
		DbaObj = new DBstep.iDBManager2000(); // 创建数据库对象
		MsgObj = new DBstep.iMsgServer2000(); // 创建信息包对象
		mOption = "";
		mRecordID = "";
		mTemplate = "";
		mFileBody = null;
		mFileName = "";
		mFileType = "";
		mFileSize = 0;
		mFileID = "";
		mDateTime = "";
		mMarkName = "";
		mPassword = "";
		mMarkList = "";
		mBookmark = "";
		mMarkGuid = "";
		mDescript = "";
		mCommand = "";
		mContent = "";
		mLabelName = "";
		mImageName = "";
		mTableContent = "";
		mBusId = "";
		mBusType = "";
		mOfficePrints = "0";
		mFilePath = request.getSession().getServletContext().getRealPath(""); // 取得服务器路径
		SERVERPATH = mFilePath;
		try {
			if (request.getMethod().equalsIgnoreCase("POST")) {
				MsgObj.Load(request); // 8.1.0.2版后台类新增解析接口，可支持UTF-8编码自适应功能
				if (MsgObj.GetMsgByName("DBSTEP").equalsIgnoreCase("DBSTEP")) { // 判断是否是合法的信息包，或者数据包信息是否完整
					mOption = MsgObj.GetMsgByName("OPTION"); // 取得操作信息
					mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得系统用户
					// 下面的代码为打开服务器的文件
					if (mOption.equalsIgnoreCase("LOADFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						saveType = MsgObj.GetMsgByName("EXTPARAM");
						MsgObj.MsgTextClear(); // 清除文本信息
						if (null != saveType && saveType.equals("file")) { // 打开文件
							if (MsgObj.MsgFileLoad(mFilePath + mFileName)) {
								MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("打开失败!"); // 设置错误信息
							}
						} else if (LoadFile()) {// 从数据库调入文档
							MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
							MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("打开失败!"); // 设置错误信息
						}
					}
					// 下面的代码为保存文件在服务器的数据库里
					else if (mOption.equalsIgnoreCase("SAVEFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						saveType = MsgObj.GetMsgByName("EXTPARAM");
						mDescript = "通用版本"; // 版本说明
						mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
						mFileDate = DbaObj.GetDateTime(); // 取得文档时间
						mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
						MsgObj.MsgTextClear(); // 清除文本信息
						if (null != saveType && saveType.equals("file")) {
							if (MsgObj.MsgFileSave(mFilePath + mFileName)) { // 保存文档到文件夹中
								MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("保存失败!"); // 设置错误信息
							}
						} else if (SaveFile()) {
							MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear(); // 清除文档内容
					}
					// 下面的代码为打开服务器数据库里的模板文件
					else if (mOption.equalsIgnoreCase("LOADTEMPLATE")) {
						mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板文档类型
						// 本段处理是否调用文档时打开模版，还是套用模版时打开模版。
						mCommand = MsgObj.GetMsgByName("COMMAND"); // 取得客户端定义的变量COMMAND值
						if (mCommand.equalsIgnoreCase("INSERTFILE")) {
							if (MsgObj.MsgFileLoad(mFilePath + "\\Document\\"
									+ mTemplate)) { // 从服务器文件夹中调入模板文档
								MsgObj.SetMsgByName("STATUS", "打开模板成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("打开模板失败!"); // 设置错误信息
							}
						} else {
							MsgObj.MsgTextClear(); // 清除文本信息
							if (LoadTemplate()) { // 调入模板文档
								MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
								MsgObj.SetMsgByName("STATUS", "打开模板成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("打开模板失败!"); // 设置错误信息
							}
						}
					}
					// 下面的代码为保存模板文件在服务器的数据库里
					else if (mOption.equalsIgnoreCase("SAVETEMPLATE")) {
						mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						mDescript = "通用模板"; // 版本说明
						mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
						mFileDate = DbaObj.GetDateTime(); // 取得文档时间
						mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
						MsgObj.MsgTextClear();
						if (SaveTemplate()) { // 保存模板文档内容
							MsgObj.SetMsgByName("STATUS", "保存模板成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存模板失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear();
					}
					// 下面的代码为打开版本列表
					else if (mOption.equalsIgnoreCase("LISTVERSION")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						MsgObj.MsgTextClear();
						if (ListVersion()) { // 生成版本列表
							MsgObj.SetMsgByName("FILEID", mFileID); // 将文档号列表打包
							MsgObj.SetMsgByName("DATETIME", mDateTime); // 将日期时间列表打包
							MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
							MsgObj.SetMsgByName("DESCRIPT", mDescript); // 将说明信息列表打包
							MsgObj.SetMsgByName("STATUS", "版本列表成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("版本列表失败!"); // 设置错误信息
						}
					}
					// 下面的代码为打开版本文档
					else if (mOption.equalsIgnoreCase("LOADVERSION")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileID = MsgObj.GetMsgByName("FILEID"); // 取得版本文档号
						MsgObj.MsgTextClear();
						if (LoadVersion(mFileID)) { // 调入该版本文档
							MsgObj.MsgFileBody(mFileBody); // 将文档信息打包
							MsgObj.SetMsgByName("STATUS", "打开版本成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("打开版本失败!"); // 设置错误信息
						}
					}
					// 下面的代码为保存版本文档
					else if (mOption.equalsIgnoreCase("SAVEVERSION")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileID = MsgObj.GetMsgByName("FILEID"); // 取得版本文档号
																	// 如:WebSaveVersionByFileID，则FileID值存在
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						mDescript = MsgObj.GetMsgByName("DESCRIPT"); // 取得说明信息
						mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
						mFileDate = DbaObj.GetDateTime(); // 取得文档时间
						mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
						MsgObj.MsgTextClear();
						if (SaveVersion()) { // 保存版本文档
							MsgObj.SetMsgByName("STATUS", "保存版本成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存版本失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear(); // 清除文档内容
					}
					// 下面的代码为取得文档标签
					else if (mOption.equalsIgnoreCase("LOADBOOKMARKS")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						MsgObj.MsgTextClear();
						if (LoadBookMarks()) {
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("装入标签信息失败!"); // 设置错误信息
						}
					}
					// 下面的代码为取得标签文档内容
					else if (mOption.equalsIgnoreCase("SAVEBOOKMARKS")) {
						mTemplate = MsgObj.GetMsgByName("TEMPLATE"); // 取得模板编号
						if (SaveBookMarks()) {
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存标签信息失败!"); // 设置错误信息
						}
						MsgObj.MsgTextClear(); // 清除文本信息
					}
					// 下面的代码为显示标签列表
					else if (mOption.equalsIgnoreCase("LISTBOOKMARKS")) {
						MsgObj.MsgTextClear(); // 清除文本信息
						if (ListBookmarks()) {
							MsgObj.SetMsgByName("BOOKMARK", mBookmark); // 将用户名列表打包
							MsgObj.SetMsgByName("DESCRIPT", mDescript); // 将说明信息列表打包
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("调入标签失败!"); // 设置错误信息
						}
					}
					// 下面的代码为创建印章列表
					else if (mOption.equalsIgnoreCase("LOADMARKLIST")) {
						MsgObj.MsgTextClear();
						// 清除文本信息
						if (LoadMarkList(String.valueOf(request.getSession()
								.getAttribute("thisUserId")))) {
							MsgObj.SetMsgByName("MARKLIST", mMarkList); // 显示签章列表
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("创建印章列表失败!"); // 设置错误信息
						}
					}
					// 下面的代码为打开印章文件
					else if (mOption.equalsIgnoreCase("LOADMARKIMAGE")) {
						mMarkName = MsgObj.GetMsgByName("IMAGENAME"); // 取得签名名称
						mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
						mPassword = MsgObj.GetMsgByName("PASSWORD"); // 取得用户密码
						MsgObj.MsgTextClear(); // 清除文本信息
						if (LoadMarkImage(mMarkName, mPassword)) { // 调入签名信息
							MsgObj.SetMsgByName("IMAGETYPE", mFileType); // 设置签名类型
							MsgObj.MsgFileBody(mFileBody); // 将签名信息打包
							MsgObj.SetMsgByName("POSITION", "Manager"); // 插入位置
																		// 在文档中标签"Manager"
							MsgObj.SetMsgByName("ZORDER", "5"); // 4:在文字上方
																// 5:在文字下方
							MsgObj.SetMsgByName("STATUS", "打开成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("签名或密码错误!"); // 设置错误信息
						}
					}
					// 下面的代码为保存签章基本信息
					else if (mOption.equalsIgnoreCase("SAVESIGNATURE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文件名称
						mMarkName = MsgObj.GetMsgByName("MARKNAME"); // 取得签名名称
						mUserName = MsgObj.GetMsgByName("USERNAME"); // 取得用户名称
						mDateTime = MsgObj.GetMsgByName("DATETIME"); // 取得签名时间
						mHostName = request.getRemoteAddr(); // 取得用户IP
						mMarkGuid = MsgObj.GetMsgByName("MARKGUID"); // 取得唯一编号
						MsgObj.MsgTextClear(); // 清除文本信息
						if (SaveSignature()) { // 保存签章
							MsgObj.SetMsgByName("STATUS", "保存印章成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存印章失败!"); // 设置错误信息
						}
					}
					// 下面的代码为调出签章基本信息
					else if (mOption.equalsIgnoreCase("LOADSIGNATURE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						MsgObj.MsgTextClear(); // 清除文本信息
						if (LoadSignature()) { // 调出签章
							MsgObj.SetMsgByName("MARKNAME", mMarkName); // 将签名名称列表打包
							MsgObj.SetMsgByName("USERNAME", mUserName); // 将用户名列表打包
							MsgObj.SetMsgByName("DATETIME", mDateTime); // 将时间列表打包
							MsgObj.SetMsgByName("HOSTNAME", mHostName); // 将盖章IP地址列表打包
							MsgObj.SetMsgByName("MARKGUID", mMarkGuid); // 将唯一编号列表打包
							MsgObj.SetMsgByName("STATUS", "调入印章成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("调入印章失败!"); // 设置错误信息
						}
					}
					// 下面的代码为保存PDF文件
					else if (mOption.equalsIgnoreCase("SAVEPDF")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						MsgObj.MsgTextClear(); // 清除文本信息
						if (MsgObj.MsgFileSave(mFilePath + "\\Document\\"
								+ mRecordID + ".pdf")) { // 保存文档到文件夹中
							MsgObj.SetMsgByName("STATUS", "保存成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear(); // 清除文档内容
					}
					// 下面的代码为将OFFICE存为HTML页面
					else if (mOption.equalsIgnoreCase("SAVEASHTML")) {
						mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
						mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
						MsgObj.MsgTextClear();
						if (mDirectory.trim().equalsIgnoreCase("")) {
							mFilePath = mFilePath + "\\HTML";
						} else {
							mFilePath = mFilePath + "\\HTML\\" + mDirectory;
						}
						MsgObj.MakeDirectory(mFilePath); // 创建路径
						if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
							MsgObj.MsgError(""); // 清除错误信息
							MsgObj.SetMsgByName("STATUS", "保存HTML成功!"); // 设置状态信息
						} else {
							MsgObj.MsgError("保存HTML失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear();
					}
					// 下面的代码为将OFFICE存为HTML图片页面
					else if (mOption.equalsIgnoreCase("SAVEIMAGE")) {
						mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
						mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
						MsgObj.MsgTextClear();
						if (mDirectory.trim().equalsIgnoreCase("")) {
							mFilePath = mFilePath + "\\HTMLIMAGE";
						} else {
							mFilePath = mFilePath + "\\HTMLIMAGE\\"
									+ mDirectory;
						}
						MsgObj.MakeDirectory(mFilePath); // 创建路径
						if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
							MsgObj.MsgError(""); // 清除错误信息
							MsgObj.SetMsgByName("STATUS", "保存HTML图片成功!"); // 设置状态信息
						} else {
							MsgObj.MsgError("保存HTML图片失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear();
					}
					// 下面的代码为将手写批注存为HTML图片页面
					else if (mOption.equalsIgnoreCase("SAVEASPAGE")) {
						mHtmlName = MsgObj.GetMsgByName("HTMLNAME"); // 取得文件名称
						mDirectory = MsgObj.GetMsgByName("DIRECTORY"); // 取得目录名称
						MsgObj.MsgTextClear();
						if (mDirectory.trim().equalsIgnoreCase("")) {
							mFilePath = mFilePath + "\\HTML";
						} else {
							mFilePath = mFilePath + "\\HTML\\" + mDirectory;
						}
						MsgObj.MakeDirectory(mFilePath); // 创建路径
						if (MsgObj.MsgFileSave(mFilePath + "\\" + mHtmlName)) { // 保存HTML文件
							MsgObj.MsgError(""); // 清除错误信息
							MsgObj.SetMsgByName("STATUS", "保存批注HTML图片成功!"); // 设置状态信息
						} else {
							MsgObj.MsgError("保存批注HTML图片失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear();
					}
					// 下面的代码为插入文件
					else if (mOption.equalsIgnoreCase("INSERTFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						MsgObj.MsgTextClear();
						if (LoadFile()) { // 调入文档
							MsgObj.MsgFileBody(mFileBody); // 将文件信息打包
							MsgObj.SetMsgByName("POSITION", "Content"); // 设置插入的位置[书签]
							MsgObj.SetMsgByName("STATUS", "插入文件成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("插入文件成功!"); // 设置错误信息
						}
					}
					// 下面的代码为更新保存文件
					else if (mOption.equalsIgnoreCase("UPDATEFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						mDescript = "定稿版本"; // 版本说明
						mFileSize = MsgObj.MsgFileSize(); // 取得文档大小
						mFileDate = DbaObj.GetDateTime(); // 取得文档时间
						mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
						MsgObj.MsgTextClear();
						if (SaveVersion()) { // 保存文档内容
							MsgObj.SetMsgByName("STATUS", "保存定稿版本成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存定稿版本失败!"); // 设置错误信息
						}
						MsgObj.MsgFileClear();
					}
					// 下面的代码为插入服务器图片
					else if (mOption.equalsIgnoreCase("INSERTIMAGE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mLabelName = MsgObj.GetMsgByName("LABELNAME"); // 标签名
						mImageName = MsgObj.GetMsgByName("IMAGENAME"); // 图片名
						mFilePath = mFilePath + "\\Document\\" + mImageName; // 图片在服务器的完整路径
						mFileType = mImageName.substring(
								mImageName.length() - 4).toLowerCase(); // 取得文件的类型
						MsgObj.MsgTextClear();
						if (MsgObj.MsgFileLoad(mFilePath)) { // 调入图片
							MsgObj.SetMsgByName("IMAGETYPE", mFileType); // 指定图片的类型
							MsgObj.SetMsgByName("POSITION", mLabelName); // 设置插入的位置[书签对象名]
							MsgObj.SetMsgByName("STATUS", "插入图片成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("插入图片失败!"); // 设置错误信息
						}
					}
					// 下面的代码为请求上传文件操作
					else if (mOption.equalsIgnoreCase("PUTFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileBody = MsgObj.MsgFileBody(); // 取得文档内容
						// mLocalFile = MsgObj.GetMsgByName("LOCALFILE");
						// //取得本地文件名称
						mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE"); // 取得远程文件名称
						MsgObj.MsgTextClear(); // 清除文本信息
						mFilePath = mFilePath + "\\Document\\" + mRemoteFile;
						if (MsgObj.MsgFileSave(mFilePath)) { // 调入文档
							MsgObj.SetMsgByName("STATUS", "保存上传文件成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("上传文件失败!"); // 设置错误信息
						}
					}
					// 下面的代码为请求下载文件操作
					else if (mOption.equalsIgnoreCase("GETFILE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						// mLocalFile = MsgObj.GetMsgByName("LOCALFILE");
						// //取得本地文件名称
						mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE"); // 取得远程文件名称
						MsgObj.MsgTextClear(); // 清除文本信息
						mFilePath = mFilePath + "\\Document\\" + mRemoteFile;
						if (MsgObj.MsgFileLoad(mFilePath)) { // 调入文档内容
							MsgObj.SetMsgByName("STATUS", "保存下载文件成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("下载文件失败!"); // 设置错误信息
						}
					}
					// 下面的代码为请求取得服务器时间
					else if (mOption.equalsIgnoreCase("DATETIME")) {
						MsgObj.MsgTextClear(); // 清除文本信息
						MsgObj.SetMsgByName("DATETIME", DbaObj.GetDateTime()); // 标准日期格式字串，如
																				// 2005-8-16
																				// 10:20:35
					}
					// 下面的代码为Web页面请求信息[扩展接口]
					else if (mOption.equalsIgnoreCase("SENDMESSAGE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						mFileName = MsgObj.GetMsgByName("FILENAME"); // 取得文档名称
						mFileType = MsgObj.GetMsgByName("FILETYPE"); // 取得文档类型
						mCommand = MsgObj.GetMsgByName("COMMAND"); // 取得自定义的操作类型
						mContent = MsgObj.GetMsgByName("CONTENT"); // 取得文本信息
																	// Content
						mOfficePrints = MsgObj.GetMsgByName("OFFICEPRINTS"); // 取得Office文档的打印次数
						mInfo = MsgObj.GetMsgByName("TESTINFO"); // 取得客户端传来的自定义信息
						MsgObj.MsgTextClear();
						MsgObj.MsgFileClear();
						if (mCommand.equalsIgnoreCase("INPORTTEXT")) { // 导入文本内容功能
							if (LoadContent()) {
								MsgObj.SetMsgByName("CONTENT", mContent);
								MsgObj.SetMsgByName("STATUS", "导入成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("导入失败!"); // 设置错误信息
							}
						} else if (mCommand.equalsIgnoreCase("EXPORTTEXT")) { // 导出文本内容功能
							if (SaveContent()) {
								MsgObj.SetMsgByName("STATUS", "导出成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("导出失败!"); // 设置错误信
							}
						} else if (mCommand.equalsIgnoreCase("WORDTABLE")) { // 插入远程表格功能
							if (GetWordTable()) {
								MsgObj.SetMsgByName("COLUMNS",
										String.valueOf(mColumns)); // 列
								MsgObj.SetMsgByName("CELLS",
										String.valueOf(mCells)); // 行
								MsgObj.SetMsgByName("WORDCONTENT",
										mTableContent); // 表格内容
								MsgObj.SetMsgByName("STATUS", "增加和填充成功成功!"); // 设置状态信息
								MsgObj.MsgError(""); // 清除错误信息
							} else {
								MsgObj.MsgError("增加表格行失败!"); // 设置错误信息
							}
						} else if (mCommand.equalsIgnoreCase("COPIES")) { // 打印份数控制功能
							mCopies = Integer.parseInt(mOfficePrints); // 获得客户需要打印的份数
							if (mCopies <= 2) { // 比较打印份数，拟定该文档允许打印的总数为2份，注：可以在数据库中设置好文档允许打印的份数
								if (UpdataCopies(2 - mCopies)) { // 更新打印份数
									MsgObj.SetMsgByName("STATUS", "1"); // 设置状态信息，允许打印
									MsgObj.MsgError(""); // 清除错误信息
								}
							} else {
								MsgObj.SetMsgByName("STATUS", "0"); // 不允许打印
								MsgObj.MsgError("超过打印限度不允许打印!"); // 设置错误信息
							}
						} else if (mCommand.equalsIgnoreCase("SELFINFO")) {
							mInfo = "服务器端收到客户端传来的信息：“" + mInfo + "” | ";
							mInfo = mInfo + "当前服务器时间：" + DbaObj.GetDateTime(); // 组合返回给客户端的信息
							MsgObj.SetMsgByName("RETURNINFO", mInfo); // 将返回的信息设置到信息包中
						} else {
							MsgObj.MsgError("客户端Web发送数据包命令没有合适的处理函数!["
									+ mCommand + "]");
							MsgObj.MsgTextClear();
							MsgObj.MsgFileClear();
						}
					}
					// 下面的代码为保存为全文批注格式文件
					else if (mOption.equalsIgnoreCase("SAVEPAGE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						MsgObj.MsgTextClear(); // 清除文本信息
						mFilePath = mFilePath + "\\Document\\" + mRecordID
								+ ".pgf"; // 全文批注文件的完整路径
						if (MsgObj.MsgFileSave(mFilePath)) { // 保存全文批注文件
							MsgObj.SetMsgByName("STATUS", "保存全文批注成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("保存全文批注失败!"); // 设置错误信息
						}
					}
					// 下面的代码为调入全文批注格式文件
					else if (mOption.equalsIgnoreCase("LOADPAGE")) {
						mRecordID = MsgObj.GetMsgByName("RECORDID"); // 取得文档编号
						MsgObj.MsgTextClear(); // 清除文本信息
						mFilePath = mFilePath + "\\Document\\" + mRecordID
								+ ".pgf"; // 全文批注文件的完整路径
						if (MsgObj.MsgFileLoad(mFilePath)) { // 调入文档内容
							MsgObj.SetMsgByName("STATUS", "打开全文批注成功!"); // 设置状态信息
							MsgObj.MsgError(""); // 清除错误信息
						} else {
							MsgObj.MsgError("打开全文批注失败!"); // 设置错误信息
						}
					}
				} else {
					MsgObj.MsgError("客户端发送数据包错误!");
					MsgObj.MsgTextClear();
					MsgObj.MsgFileClear();
				}
			} else {
				MsgObj.MsgError("请使用Post方法");
				MsgObj.MsgTextClear();
				MsgObj.MsgFileClear();
			}
			MsgObj.Send(response); // 8.1.0.2新版后台类新增的功能接口，返回信息包数据
		} catch (Exception e) {
			Log4J.err("WebOffice" + e.toString());
			throw new GlobalException("" + e.getMessage());
		}
	}

	public String getMBusId() {
		return mBusId;
	}

	public void setMBusId(String busId) {
		mBusId = busId;
	}

	public String getMBusType() {
		return mBusType;
	}

	public void setMBusType(String busType) {
		mBusType = busType;
	}

	public String getMTempType() {
		return mTempType;
	}

	public void setMTempType(String tempType) {
		mTempType = tempType;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
}
