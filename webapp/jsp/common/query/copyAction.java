public String methodName() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("queryId");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
		if(labQueryVo.getQueryType().equals(Constants.TRUE)){
			return "list1";
		}else
			return LIST;
	}