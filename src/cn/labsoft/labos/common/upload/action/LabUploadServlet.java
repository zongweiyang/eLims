package cn.labsoft.labos.common.upload.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.onlinepdf.ConvertRunnable;

@SuppressWarnings("serial")
@Component
public class LabUploadServlet extends HttpServlet implements Servlet {
	File tmpDir = null;
	File saveDir = null;
	File savePdf=null;
	public ILabUploadService labUploadService;
	public ILabConfigService labConfigService;
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//增加文件用
		String busId = request.getParameter("busId");
		String busType = request.getParameter("busType");
		String userId=request.getParameter("userId");
		String showType=request.getParameter("showType");
		//String saveType=request.getParameter("saveType");//存储类型 data流存储 file 文件存储
		//删除文件方法用
		String delFlag = request.getParameter("delFlag");
		String fileId = request.getParameter("fileId");
		try {
			if (null!=busId&&ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory dff = new DiskFileItemFactory();
				dff.setRepository(this.tmpDir);
				dff.setSizeThreshold(1024000);
				ServletFileUpload sfu = new ServletFileUpload(dff);
				sfu.setFileSizeMax(109999999L);
				sfu.setSizeMax(999999999L);
				FileItemIterator fii = sfu.getItemIterator(request);
				String filePath = request.getRealPath("/");
				LabConfigVo labConfigVo=labConfigService.getLabConfigByCode(busType);
				String tempPath ="";
				if(labConfigVo!=null&&labConfigVo.getValue()!=null){
					tempPath=labConfigVo.getValue();
				}else{
					tempPath="/uploadDoc";
					if (null != busType && !"".equals(busType))
						tempPath += File.separator + busType;
				}
				tempPath=tempPath.replace("/", File.separator);
				
				String savePath = filePath + tempPath;
				this.saveDir = new File(savePath);
				if (!(this.saveDir.isDirectory()))
					this.saveDir.mkdirs();
				 String savePDFPath=filePath+"uploadDoc"+File.separator+"pdf";
				 this.savePdf=new File(savePDFPath);
			     if (!(this.savePdf.isDirectory()))
			          this.savePdf.mkdir();
				String atag="";
				String savePathTemp="";
			     while (fii.hasNext()) {
					FileItemStream fis = fii.next();
					if ((!(fis.isFormField())) && (fis.getName().length() > 0)) {
						LabUploadVo labUploadVo = new LabUploadVo();
						String fileName = fis.getName();
						String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
						String tempFileUrl = String.valueOf(new Date().getTime()) + "." + fileType;
						String fileUrl = savePath + File.separator + tempFileUrl;
						BufferedInputStream in = new BufferedInputStream(fis.openStream());
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileUrl));
//						if(saveType!=null&&saveType.equals("data")){
//							labUploadVo.setSaveType("data");
//							Blob photo=Hibernate.createBlob(in);
//							labUploadVo.setContent(photo);
//						}else{
//							labUploadVo.setSaveType("file");
//						}
						Streams.copy(in, out, true);
						savePathTemp=(tempPath + File.separator + tempFileUrl).replace(File.separator, "/");
						if(judgeDocType(fileType)){
							if(!fileType.equals("pdf")){
								String pdfName=tempFileUrl.substring(0,tempFileUrl.indexOf("."))+".pdf";
								String pdfPath=savePDFPath+File.separator+pdfName;
								try{
									Thread thread = new Thread(new ConvertRunnable(fileUrl,pdfPath));
						        	thread.start();
						        	labUploadVo.setPdfName(pdfName);
						        	labUploadVo.setPdfPath((tempPath + File.separator + pdfName).replace(File.separator, "/"));
								}catch(Exception e){
									throw new GlobalException("" + e.getMessage());
								}
							}
						}
						fileName=fileName.replace("‘", "");
						fileName=fileName.replace("’", "");
						fileName=fileName.replace("“", "");
						fileName=fileName.replace("”", "");
						fileName=fileName.replace("'", "");
						fileName=fileName.replace("\"", "");
						fileName=fileName.replace("\\", "");
						fileName=fileName.replace("/", "");
						
						labUploadVo.setName(fileName);
						labUploadVo.setVname(tempFileUrl);
						labUploadVo.setType(fileType);
						labUploadVo.setPath(savePathTemp);
						labUploadVo.setBusId(busId);
						labUploadVo.setBusType(busType);
						labUploadVo.setCreateUserId(userId);
						labUploadVo=labUploadService.addLabUpload(labUploadVo);
						String basePath = (String)request.getSession().getAttribute("basePath");
						if(!StrUtils.isBlankOrNull(labUploadVo.getId())){
							if(showType!=null&&showType.equals("show")){
								atag+="<img src=\""+basePath+savePathTemp+"\"  width=\"100%\" height=\"100%\" />";
							}else{
								atag+="<span id=\""+labUploadVo.getId()+"\">" +labUploadVo.getName()+
								"<a href=\"javascript:;\" onclick=\"deleteUploadFile(this,'"+labUploadVo.getId()+"')\">" +
								"<img src=\"/img/zhongzhi.gif\" />" +
								"</a></span>";
							}
						}
						in.close();
						out.close();
					}
				}
			    if("kindEditor".equals(busType)){
			    	JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", savePathTemp);
					response.getWriter().println(obj.toJSONString());
			    }else{
			    	response.getWriter().println(atag);
			    }
			} else if (delFlag != null && !"".equals(delFlag) && delFlag.equals("Y")) {
				boolean flag=labUploadService.deleteFileFromUpload(fileId);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out=response.getWriter();
				out.write(flag+"");
			}
		} catch (Exception e) {
			Log4J.error(e.getMessage());
		}
	}
	public boolean judgeDocType(String type){
		boolean key=false;
		if(type.trim().equals("xls")||type.trim().equals("xlsx")||type.trim().equals("doc")||type.trim().equals("docx")){
			key=true;
		}
		return key;
	}

}