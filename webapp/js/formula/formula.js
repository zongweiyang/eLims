	function formula(showValueId){
	    		var url=basePath+'/formula/labFormulaApply/ajaxFormulaApply.action';
	    			$.ajax({
						url:url,
						type:'POST',
						data:{'labFormulaApplyVo.id':showValueId},
						dataType:'json',
						success:function(data){
							if(data!=null&&data!=""){
								var result="";
								for(var i=0;i<data.parameterId.split(',').length;i++){
									if(data.parameterId.split(',')[i]!=null&&data.parameterId.split(',')[i]!=""){
										result+=$("#"+data.parameterId.split(',')[i]).val();
										result+=",";
									}
								}
								getResult(showValueId,result,data.valueId);
							}
						},
						error:function(){
							alert('网络不通.');
						}
					});	
	    	}
	function getResult(forId,result,showValueId){
		var url=basePath+"/formula/labFormulaApply/ajaxReckonFormula.action";
		$.ajax({
				url:url,
				type:'POST',
				data:{'labFormulaApplyVo.id':forId,'labFormulaApplyVo.result':result},
				dataType:'json',
				success:function(data){
					if(data!=''){
						$("#"+showValueId).val(data);
					}
				},
				error:function(){
					alert('网络不通.');
				}
			});	
	}
