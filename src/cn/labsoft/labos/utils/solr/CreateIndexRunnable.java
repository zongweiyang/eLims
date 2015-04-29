package cn.labsoft.labos.utils.solr;

import java.util.List;

import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

public class CreateIndexRunnable implements Runnable{
	private List<LabUpload> loadList;
	
	public CreateIndexRunnable(List<LabUpload> loadList){
		this.loadList = loadList;
	}
	@Override
	public void run() {
		try {
			SolrClientUtils.getSolrserver();
			SolrClientUtils.addDoc(this.loadList);
		} catch (GlobalException e) {
			Log4J.error("", e);
		}
	}
}
