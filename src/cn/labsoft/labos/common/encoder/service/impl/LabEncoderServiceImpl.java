package cn.labsoft.labos.common.encoder.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.common.encoder.dao.ILabEncoderDAO;
import cn.labsoft.labos.common.encoder.entity.LabEncoder;
import cn.labsoft.labos.common.encoder.service.ILabEncoderService;
import cn.labsoft.labos.common.encoder.vo.BarCode;
import cn.labsoft.labos.common.encoder.vo.BarCodeContent;
import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.common.page.dao.ILabPageEditorDAO;
import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.interceptor.LogCommonInformation;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.EWMTools;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;
@Service("labEncoderService")
public class LabEncoderServiceImpl implements ILabEncoderService {
	private ILabEncoderDAO labEncoderDAO;
	private ILabFunctionDAO labFunctionDAO;
	private ILabPageEditorDAO labPageEditorDAO;
	
	
	@Resource
	public void setLabEncoderDAO(ILabEncoderDAO labEncoderDAO) {
		this.labEncoderDAO = labEncoderDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabPageEditorDAO(ILabPageEditorDAO labPageEditorDAO) {
		this.labPageEditorDAO = labPageEditorDAO;
	}

	@Override
	public LabEncoderVo addLabEncoder(LabEncoderVo labEncoderVo)
			throws GlobalException {
		LabEncoder labEncoder = new LabEncoder();
		try {
			labEncoder = this.vo2Po(labEncoderVo, labEncoder);
			labEncoderDAO.addLabEncoder(labEncoder);
			labEncoderVo.setId(labEncoder.getId());
		} catch (Exception e) {
			Log4J.error("LabEncoderServiceImpl addLabEncoder  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labEncoderVo;
	}

	@Override
	public boolean deleteLabEncoder(String[] ids) throws GlobalException {
		boolean key = true;
		try {
			if (ids != null && ids.length > 0) {
				key = labEncoderDAO.deleteLabEncoder(ids);
			}
		} catch (Exception e) {
			key = false;
			Log4J.error("LabEncoderServiceImpl deleteLabEncoder  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabEncoderVo getLabEncoder(String id) throws GlobalException {
		LabEncoderVo labEncoderVo = new LabEncoderVo();
		if (!StrUtils.isBlankOrNull(id)) {
			try {
				LabEncoder labEncoder = labEncoderDAO.getLabEncoder(id);
				labEncoderVo = this.po2Vo(labEncoder, labEncoderVo);
			} catch (Exception e) {
				Log4J.error("LabEncoderServiceImpl getLabEncoder  error..."
						+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labEncoderVo;
	}

	@Override
	public List<LabEncoderVo> getLabEncoderList(LabEncoderVo labEncoderVo)
			throws GlobalException {
		String wereHql = "";

		return this.getLabEncoderVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabEncoderPR(LabEncoderVo labEncoderVo,
			PageResult pageResult) throws GlobalException {
		String hql = "FROM LabEncoder WHERE isDel='" + Constants_Base.N + "'";
		if(!StrUtils.isBlankOrNull(labEncoderVo.getBusId())){
		hql+=" AND busId= '"+labEncoderVo.getBusId()+"'";
		}
		pageResult = labEncoderDAO.getPageResult(hql, pageResult);
		if (pageResult.getResultList() != null
				&& pageResult.getResultList().size() > 0) {
			List<LabEncoderVo> listLabEncoderVo = new ArrayList<LabEncoderVo>();
			List<LabEncoder> listLabEncoder = new ArrayList<LabEncoder>();
			listLabEncoder = pageResult.getResultList();
			for (LabEncoder labEncoder : listLabEncoder) {
				LabEncoderVo vo = new LabEncoderVo();
				vo = this.po2Vo(labEncoder, vo);
				LabFunction labFunction = (LabFunction) labEncoderDAO.findById(
						LabFunction.class, labEncoder.getBusId());
				vo.setBusName(labFunction.getName());
				if (vo.getBusType() != null) {
					String str = "";
					if (vo.getBusType().contains("1")) {
						str = "条形码";
					}
					if (vo.getBusType().contains("2")) {
						if (str.length() == 0) {
							str = "二维码";
						} else {
							str += ",二维码";
						}
					}
					vo.setBusType(str);
				}
				List<BarCodeContent> content4OneList = vo.getContent4One();
				String strCode = "";
				if(null!=content4OneList&&content4OneList.size()>0){
				for (BarCodeContent bar : content4OneList) {
					if (bar.getIsDisplay() != null
							&& bar.getIsDisplay().equals("on")) {
						if (strCode.length() == 0) {
							strCode = bar.getKey().replace("&nbsp;","" );
						} else {
							strCode += "," + bar.getKey().replace("&nbsp;","" );
						}
					}
				}
				}
				vo.setCodeInfo4One(strCode);
				List<BarCodeContent> content4TwoList = vo.getContent4Two();
				String strCodeTwo = "";
				if(null!=content4TwoList&&content4TwoList.size()>0){
				for (BarCodeContent bar : content4TwoList) {
					if (bar.getIsDisplay() != null
							&& bar.getIsDisplay().equals("on")) {
						if (strCodeTwo.length() == 0) {
							strCodeTwo = bar.getKey().replace("&nbsp;","" );
						} else {
							strCodeTwo += "," + bar.getKey().replace("&nbsp;","" );
						}
					}
				}
				}
				vo.setCodeInfo4Two(strCodeTwo);
				listLabEncoderVo.add(vo);
			}
			pageResult.setResultList(listLabEncoderVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabEncoder(LabEncoderVo labEncoderVo)
			throws GlobalException {
		LabEncoder labEncoder = new LabEncoder();
		boolean key = true;
		try {
			labEncoder = this.vo2Po(labEncoderVo, labEncoder);
			labEncoderDAO.updateLabEncoder(labEncoder);
		} catch (Exception e) {
			key = false;
			Log4J.error("LabEncoderServiceImpl updateLabEncoder  error..."
					+ e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabEncoder4Del(String[] ids) throws GlobalException {
		boolean key = true;
		if (ids != null && ids.length > 0) {
			try {
				for (String id : ids) {
					LabEncoder labEncoder = labEncoderDAO.getLabEncoder(id);
					labEncoder.setIsDel(Constants_Base.Y);
					labEncoderDAO.updateLabEncoder(labEncoder);
				}
			} catch (Exception e) {
				key = false;
				Log4J.error(
						"LabEncoderServiceImpl updateLabEncoder4Del  error..."
								+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	public List<LabEncoderVo> getLabEncoderVoListByWhere(String wereHql) throws GlobalException {
		List<LabEncoderVo> listLabEncoderVo = new ArrayList<LabEncoderVo>();
		String hql = "FROM LabEncoder WHERE isDel='" + Constants_Base.N + "' ";
		if (!StrUtils.isBlankOrNull(wereHql))
			hql += wereHql;
		List<LabEncoder> listLabEncoder = labEncoderDAO.find(hql);
		if (listLabEncoder != null && listLabEncoder.size() > 0) {
			for (LabEncoder labEncoder : listLabEncoder) {
				LabEncoderVo labEncoderVo = new LabEncoderVo();
				labEncoderVo = this.po2Vo(labEncoder, labEncoderVo);
				listLabEncoderVo.add(labEncoderVo);
			}
		}
		return listLabEncoderVo;
	}

	public LabEncoder vo2Po(LabEncoderVo labEncoderVo, LabEncoder labEncoder) throws GlobalException {
		BeanUtils.copyProperties(labEncoderVo, labEncoder,
				new String[] { "isDel" });
			for(BarCodeContent bcc : labEncoderVo.getContent4One() ){
				if(bcc.getKey()!=null){
					bcc.setKey(bcc.getKey().replace(" ", "&nbsp;"));
				}
			}
		List<BarCodeContent> content4OneList = labEncoderVo.getContent4One();
		labEncoder.setContent4OneByte(StrUtils
				.objectToByteArray(serializableBCC(content4OneList)));
		for(BarCodeContent bct : labEncoderVo.getContent4Two() ){
			if(bct.getKey()!=null){
				bct.setKey(bct.getKey().replace(" ", "&nbsp;"));
			}
		}
		List<BarCodeContent> content4TwoList = new ArrayList<BarCodeContent>();
		content4TwoList = labEncoderVo.getContent4Two();
		labEncoder.setContent4TwoByte(StrUtils
				.objectToByteArray(serializableBCC(content4TwoList)));

		return labEncoder;
	}

	private List<BarCodeContent> serializableBCC(
			List<BarCodeContent> content4OneList) {
		List<BarCodeContent> content4OneListTemp = new ArrayList<BarCodeContent>();
		if (null != content4OneList && 0 < content4OneList.size()) {
			for (BarCodeContent bcc : content4OneList) {
				if (null != bcc) {
					BarCodeContent bccT = new BarCodeContent();
					BeanUtils.copyProperties(bcc, bccT);
					content4OneListTemp.add(bccT);
				}
			}
		}
		return content4OneListTemp;
	}

	@SuppressWarnings("unchecked")
	public LabEncoderVo po2Vo(LabEncoder labEncoder, LabEncoderVo labEncoderVo) throws GlobalException {
		BeanUtils.copyProperties(labEncoder, labEncoderVo);
		List<BarCodeContent> list4OneEncoder = new ArrayList<BarCodeContent>();
		byte[] bytes4One = labEncoder.getContent4OneByte();
		if (null != bytes4One) {
			list4OneEncoder = (List<BarCodeContent>) StrUtils
					.byteArrayToObject(bytes4One);
			labEncoderVo.setContent4One(list4OneEncoder);
		}
		List<BarCodeContent> list4TwoEncoder = new ArrayList<BarCodeContent>();
		byte[] bytes4Two = labEncoder.getContent4TwoByte();
		if (null != bytes4Two) {
			list4TwoEncoder = (List<BarCodeContent>) StrUtils
					.byteArrayToObject(bytes4Two);
			labEncoderVo.setContent4Two(list4TwoEncoder);
		}
		return labEncoderVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabEncoderVo showLabEncoder(String busId, String busType)
			throws GlobalException {
		String hql = "FROM LabEncoder WHERE isDel='" + Constants_Base.N
				+ "' AND busId='" + busId + "' AND busType='" + busType + "'";
		List<LabEncoder> listLabEncoder = labEncoderDAO.find(hql);
		LabEncoderVo labEncoderVo = new LabEncoderVo();
		if (listLabEncoder != null && listLabEncoder.size() > 0) {
			labEncoderVo = this.po2Vo(listLabEncoder.get(0), labEncoderVo);
		}
		return labEncoderVo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LabEncoderVo addLabEncoder4Two(LabEncoderVo labEncoderVo)
			throws GlobalException {
		File saveDir = null;
		LabEncoder labEncoder = new LabEncoder();
		labEncoder = this.vo2Po(labEncoderVo, labEncoder);
		String filePath = ServletActionContext.getRequest().getRealPath("/")
				+ "";
		String tempPath = "";
		tempPath = "/uploadDoc";
		tempPath = tempPath.replace("/", File.separator);
		if (null != labEncoderVo.getBusType()
				&& !"".equals(labEncoderVo.getBusType())) {
			tempPath += File.separator + labEncoderVo.getBusType();
		}
		String savePath = filePath + tempPath;
		saveDir = new File(savePath);
		if (!(saveDir.isDirectory())) {
			saveDir.mkdirs();
		}
		labEncoderDAO.addLabEncoder(labEncoder);
		return labEncoderVo;
	}

	@SuppressWarnings("deprecation")
	public LabEncoderVo getLabEncoder4Print(String busId, List<?> busList,
			String barCodeType) throws GlobalException {
		LabEncoderVo labEncoderVo = new LabEncoderVo();
		List<BarCode> barCodeList = new ArrayList<BarCode>();
		if (!StrUtils.isBlankOrNull(busId)) {
			String hql = "FROM LabEncoder le WHERE 1=1 AND le.busId='" + busId
					+ "' ORDER BY le.createTime DESC";
			try {
				List<LabEncoder> labEncoderList = labEncoderDAO
						.getLabEncoderList(hql);
				if (null != labEncoderList && 0 < labEncoderList.size()) {
					labEncoderVo = this.po2Vo(labEncoderList.get(0),
							labEncoderVo);

					List<BarCodeContent> barCodeContentList = new ArrayList<BarCodeContent>();

					if (LabEncoderVo.EWM_ENCODER.equals(barCodeType)) {
						barCodeContentList = labEncoderVo.getContent4Two();
					} else if (LabEncoderVo.TXM_ENCODER.equals(barCodeType)) {
						barCodeContentList = labEncoderVo.getContent4One();
					}
					if (null != barCodeContentList
							&& 0 < barCodeContentList.size()&&null!=busList&&0<busList.size()) {
						for(Object barCodeValue : busList){
							BarCode barCode = new BarCode();
							BarCodeContent[] bccArray = new BarCodeContent[0];
							String barCodeInfoStr = "";
							List<BarCodeContent> bccAndValueList = new ArrayList<BarCodeContent>();
							for (BarCodeContent bccType : barCodeContentList) {
								BarCodeContent bccAndValue = new BarCodeContent();
								if (null != bccType
										&& null != bccType.getIsDisplay()
										&& "on".equals(bccType.getIsDisplay())) {
									String sx = bccType.getValue();
									sx = sx.substring(0,1).toUpperCase()+sx.substring(1,sx.length());
									String value = String.valueOf(barCodeValue.getClass().getMethod("get"+sx, new Class[] {}).invoke(
											barCodeValue, new Object[] {}));
									if(value==null||value.equals("null")){
										value="";
									}
									bccAndValue.setKey(bccType.getKey());
									bccAndValue.setValue(value);
									barCodeInfoStr+=bccType.getKey().replace("&nbsp;", "")+":"+value+"\n";
									bccAndValueList.add(bccAndValue);
								}
							}
							bccArray = bccAndValueList.toArray(new BarCodeContent[0]);
							barCode.setBccArray(bccArray);
							if(LabEncoderVo.EWM_ENCODER.equals(barCodeType)){
								if(!"".equals(barCodeInfoStr)){
									String id = (String) barCodeValue.getClass().getMethod("getId", new Class[] {}).invoke(
											barCodeValue, new Object[] {});
									String filePath = ServletActionContext.getRequest().getRealPath("/")
									+ "";
									String barCodePath = filePath+"/uploadDoc/barcode";
									File barCodeDir = new File(barCodePath);
									barCodeDir.mkdirs();
									File barCodeFile = new File(barCodePath+"/"+id+".png");
									if(barCodeFile.exists()){
										barCodeFile.delete();
									}
									if(null==labEncoderVo.getRc4Two()||"".equals(labEncoderVo.getRc4Two())){
										labEncoderVo.setRc4Two("M");
									}
									if(null==labEncoderVo.getSize4Two()||"".equals(labEncoderVo.getSize4Two())){
										labEncoderVo.setSize4Two("0");
									}
									try{
										EWMTools.generateEWM(barCodeFile, barCodeInfoStr, labEncoderVo.getRc4Two().charAt(0), Integer.valueOf(labEncoderVo.getSize4Two()), labEncoderVo.getFrontColor4Two(), labEncoderVo.getBackColor4Two());
									}catch(Exception ex){
										Log4J.err(ex);
										throw new GlobalException("" + ex.getMessage());
									}
									
										
									barCode.setId(id);
								}else{
									barCode.setId("0");
								}
							}else if(LabEncoderVo.TXM_ENCODER.equals(barCodeType)){
								if(null!=labEncoderVo.getNum4One()&&!"".equals(labEncoderVo.getNum4One())){
									String sx = labEncoderVo.getNum4One();
									sx = sx.substring(0,1).toUpperCase()+sx.substring(1,sx.length());
									String id = (String) barCodeValue.getClass().getMethod("get"+sx, new Class[] {}).invoke(
											barCodeValue, new Object[] {});
									barCode.setId(id);
								}else{
									barCode.setId("0");
								}
							}
							barCodeList.add(barCode);
						}
						labEncoderVo.setBarCodeList(barCodeList);
					}
				}
			} catch (Exception e) {
				Log4J.error("LabEncoderServiceImpl getLabEncoder  error..."
						+ e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}

		return labEncoderVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLabObjectByFunId(String funId)
			throws GlobalException {
		LabFunction fun=labFunctionDAO.getLabFunction(funId);
		String url=fun.getUrl();
		Map<String, String> checkboxList =null;
		StringBuffer sb = new StringBuffer("[");
		String result = "";
		if(url.contains("/")&&url.contains(".")){
			//获取对象Vo名称
			String entity=url.substring(url.lastIndexOf("/")+1,url.indexOf("."));
			String[] strList=LogCommonInformation.LIST_PAGE_ACTION;
			for (String str : strList) {
				entity=entity.replace(str,"");
				if(entity.contains("4")){
					entity=entity.substring(0,entity.indexOf("4"));
				}
				if(entity.contains("2")){
					entity=entity.substring(0,entity.indexOf("2"));
				}
			}
			entity=entity.replace("Lab", "lab")+"Vo";
			//获取对象的路径
			String hql="FROM LabPageEditor WHERE objName like '"+entity+"' GROUP BY objName";
			LabPageEditor pe=(LabPageEditor)labPageEditorDAO.find(hql, 0);
			if(pe!=null){
				try {
					String classPath=pe.getObjUrl();
					Class c = Class.forName(classPath);
					c.newInstance();
					checkboxList = ExportExcel.getAnnotationInfo(c);
				} catch (ClassNotFoundException e) {
					Log4J.err("LabReportServiceImpl..."+e.getMessage());
					throw new GlobalException("" + e.getMessage());
				} catch (InstantiationException e) {
					Log4J.err("LabReportServiceImpl..."+e.getMessage());
					throw new GlobalException("" + e.getMessage());
				} catch (IllegalAccessException e) {
					Log4J.err("LabReportServiceImpl..."+e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
			}
			if (null != checkboxList && checkboxList.size() > 0) {
				for (Map.Entry<String, String> map : checkboxList.entrySet()) {
					sb.append("{'key':'" + map.getValue() + "','value':'"
							+ map.getKey() + "'},");
				}
				result = sb.toString();
				if (result.endsWith(",")) {
					result = result.substring(0, result.length() - 1);
				}
				result = result + "]";
			}
		}
		return result;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public LabEncoderVo getLabEncoder4Show(LabEncoderVo labEncoderVo,String barCodeType) throws GlobalException{
				//List<BarCodeContent> barCodeContentList=new ArrayList<BarCodeContent>();
				//List<BarCode> barCodeList = new ArrayList<BarCode>();
					BarCodeContent bar= new BarCodeContent();
					String barCodeKey = "";
					String barCodeValue = "";
					String barCodeInfoStr ="";
					if (null != labEncoderVo.getBarcodeKey() && labEncoderVo.getBarcodeKey().length()>0) {
								barCodeKey=labEncoderVo.getBarcodeKey();
								barCodeValue=labEncoderVo.getBarcodeValue();
								barCodeInfoStr+=labEncoderVo.getBarcodeKey()+"\n";
							if(!"".equals(barCodeKey)){
								barCodeKey=barCodeKey.substring(0, barCodeKey.length()-1);
								barCodeValue=barCodeValue.substring(0, barCodeValue.length()-1);
							}
							
							
						bar.setKey(barCodeKey);
						bar.setValue(barCodeValue);
						if(LabEncoderVo.EWM_ENCODER.equals(barCodeType)){
								if(!"".equals(barCodeInfoStr)){
									String id = labEncoderVo.getBusId();
									String filePath = ServletActionContext.getRequest().getRealPath("/")
									+ "";
									String barCodePath = filePath+"/uploadDoc/barcode";
									File barCodeDir = new File(barCodePath);
									barCodeDir.mkdirs();
									File barCodeFile = new File(barCodePath+"/"+id+".png");
									if(barCodeFile.exists()){
										barCodeFile.delete();
									}
									if(null==labEncoderVo.getRc4Two()||"".equals(labEncoderVo.getRc4Two())){
										labEncoderVo.setRc4Two("M");
									}
									if(null==labEncoderVo.getSize4Two()||"".equals(labEncoderVo.getSize4Two())){
										labEncoderVo.setSize4Two("0");
									}
									try{
										EWMTools.generateEWM(barCodeFile, barCodeInfoStr, labEncoderVo.getRc4Two().charAt(0), Integer.valueOf(labEncoderVo.getSize4Two()), labEncoderVo.getFrontColor4Two(), labEncoderVo.getBackColor4Two());
									}catch(Exception ex){
										Log4J.err(ex);
										throw new GlobalException("" + ex.getMessage());
									}
									List<BarCodeContent> barcodeList=new ArrayList<BarCodeContent>();
									barcodeList.add(bar);
									labEncoderVo.setContent4One(barcodeList);
									labEncoderVo.setId(id);
								}
							}
						if(LabEncoderVo.TXM_ENCODER.equals(barCodeType)){
								if(null!=labEncoderVo.getNum4One()&&!"".equals(labEncoderVo.getNum4One())){
									String sx = labEncoderVo.getNum4One();
									labEncoderVo.setId(sx);
									}else{
										labEncoderVo.setId("0");
									}
								 	List<BarCodeContent> barcodeList=new ArrayList<BarCodeContent>();
								 	barcodeList.add(bar);
								 	labEncoderVo.setContent4One(barcodeList);
									
								}
					}
					return labEncoderVo;
		}
	@Override
	public String  getLabEncoder4BusId(String busId) throws GlobalException{
		int i=0; 
		if (!StrUtils.isBlankOrNull(busId)) {
			String hql = "FROM LabEncoder le WHERE 1=1 AND le.busId='" + busId
					+ "' ORDER BY le.createTime DESC";
				List<LabEncoder> labEncoderList = labEncoderDAO
						.getLabEncoderList(hql);
				i=labEncoderList.size();
		}
		return String.valueOf(i);
	}
	
	}
