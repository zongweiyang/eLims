package cn.labsoft.labos.source.doc.vo;

import java.util.List;

import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.source.doc.entity.LabDoc;

public class LabDocVo extends BaseVo {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String[] ids;
	private LabDoc labDoc;
	private String fileName;// 文件名称
	private String creatorName;// 文件创建者Name
	private String creatorId;// 文件创建者ID
	private String isDir;// 0-文件夹 1-文档
	private String createTime;
	private String author;//作者
	private String docType;// 文件类型
	private String docSize;// 文件大小
	private String versionNum;// 版本号
	private String modifyTime;// 修改日期
	private String modifier;// 修改人
	private String orgName;// 科室名称
	private String orgId;// 科室ID
	private String title;
	private String isDel;
	private String keyWord;//关键字
	private String summary;//摘要
	private String literature;//文件类型
	private String pid;
	private String pname;
	private String docIcon;// 文件图标
	private String docDepict;// 文档描述
	private String remark;// 备注
	private String status;//状态 0表示无权限 1审核中 2已赋权 3已拒绝
	private String applyName;
	private String flag;
	private String num;//文件夹下的文件数量
	private String userId;
	private String power;//权限 ALL指所有，ONLY 仅自己
	private String path;
	private String name;
	private String fileId;
	private String docId;
	private String content;
	private String ext01;
	private String ext02;
	private String ext2;//条件字段1
	private String ext3;//条件字段2
	private String ext4;//条件字段3
	private String ext5;//条件字段4
	private List<LabUpload> labUploadList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String[] getIds() {
		return ids;
	}
	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public LabDoc getLabDoc() {
		return labDoc;
	}
	public void setLabDoc(LabDoc labDoc) {
		this.labDoc = labDoc;
	}
	@Override
	public String getFileName() {
		return fileName;
	}
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getIsDir() {
		return isDir;
	}
	public void setIsDir(String isDir) {
		this.isDir = isDir;
	}
	@Override
	public String getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocSize() {
		return docSize;
	}
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getDocIcon() {
		return docIcon;
	}
	public void setDocIcon(String docIcon) {
		this.docIcon = docIcon;
	}
	public String getDocDepict() {
		return docDepict;
	}
	public void setDocDepict(String docDepict) {
		this.docDepict = docDepict;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExt01() {
		return ext01;
	}
	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}
	public String getExt02() {
		return ext02;
	}
	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getLiterature() {
		return literature;
	}
	public void setLiterature(String literature) {
		this.literature = literature;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public List<LabUpload> getLabUploadList() {
		return labUploadList;
	}
	public void setLabUploadList(List<LabUpload> labUploadList) {
		this.labUploadList = labUploadList;
	}
	
}
