/*判断文件名是否重复*/
	function tarveFileNamePresence(val,obj,aHtml,id){
		var type=true;
		$('span[name="fileName"]').each(function(){
			if(val==$(this).html()){
				alert('无法重命名，与现有文件重名！');
				$('#modifyInput').focus();
				type=false;
				return false;
			}
		});
		if(type){
			$('#modifyInput').remove();
			$(obj).parent().next().html(aHtml);
			$(obj).parent().next().children().children('span').html('<img width="16" height="16" src="'+basePath+'/img/loading_16x16.gif"></img>');
			$.ajax({
	    		url:basePath+'document/manager/json/updateFileName.action',
	    		type:'post',
	    		dataType:'text',
	    		data:{'docFileVo.id':id,'docFileVo.name':val},
	    		error: function(){
	    			$(obj).parent().next().html(aHtml);
					alert('请检查您的网络!');
	    		},
	    		success: function(json) {
	    			if(json=='success'){
	    				$(obj).parent().next().children().children('span').html(val);
	    			}
	    			else{
	    				$(obj).parent().next().html(aHtml);
	    				alert('文件名修改失败!');
	    			}
	    		}
	    	});
			
		}
	}
	/*判断文件夹名是否重复*/
	function tarveFolderNamePresence(val,obj,aHtml){
		var type=true;
		$('span[name="fileName"]').each(function(){
			if(val==$(this).html()){
				alert('文件夹重名！请重新命名！');
				$('#modifyInput').focus();
				type=false;
				return false;
			}
		});
		if(type){
			$('#modifyInput').remove();
			$(obj).parent().next().html(aHtml);
			$(obj).parent().next().children().children('span').html('<img width="16" height="16" src="'+basePath+'/img/loading_16x16.gif"></img>');
			var parentID=$('#parentID').val();
			$.ajax({
	    		url:basePath+'document/manager/json/addFolder.action',
	    		type:'post',
	    		dataType:'text',
	    		data:{'docFileVo.name':val,'docFileVo.pid':parentID},
	    		error: function(){
	    			$(obj).parent().parent().remove();
	    			$('#addNewFolder').bind('click',traverseFolderName);
					alert('文件夹上传失败!error');
	    		},
	    		success: function(json) {
	    			if(json==''||json==null||json==undefined||json=='undefined'){
	    				$(obj).parent().parent().remove();
	    				alert('文件夹新增失败!');
	    			}
	    			else{
	    				//alert(json)
	    				$(obj).parent().parent().children().eq(0).children().val(json);
	    				$(obj).parent().next().children().children('span').html(val);
	    			}
	    			$('#addNewFolder').bind('click',traverseFolderName);
	    		}
	    	});
			
		}
	}
	/*删除文件*/
	function removeFile(obj){
		var id =$(obj).parent().prev().children('input').val();
		$.ajax({
	   		url:basePath+'document/manager/json/deleteFile.action',
	   		type:'post',
	   		dataType:'text',
	   		data:{"docFileVo.ids":id},
	   		error: function(){
				alert('文件删除失败!error');
	   		},
	   		success: function(json) {
	   			if(json=='success'){
	   				$(obj).parent().parent('tr').remove();
	   			}
	   			else{
	   				alert('文件删除失败!');
	   			}
	   		}
	   	});
	}
	function batchDeleteClick(){
		if($('input[name="sysUserVo.ids"]:checked').length<=0){
			if(!$('#batchDeleteFile').hasClass('zPushBtnDisabled')){
				$('#batchDeleteFile').addClass('zPushBtnDisabled').unbind('click',batchDeleteFile);
			}
		}
		else{
			$('#batchDeleteFile').removeClass('zPushBtnDisabled').bind('click',batchDeleteFile);
		}
	}
	$(function(){
		$('input[name="sysUserVo.ids"]').click(function(){
			batchDeleteClick();
		});
		$('#addNewFolder').click(function(){
			traverseFolderName();
		});
	});
	/*批量删除文件*/
	function batchDeleteFile(){
		var id="";
		$('#batchDeleteFile').addClass('zPushBtnDisabled').unbind('click',batchDeleteFile);
		$('input[name="sysUserVo.ids"]:checked').each(function(){
			id+=$(this).val()+'^';
		});
		id = id.substring(0,id.length-1);
		$.ajax({
	   		url:basePath+'document/manager/json/deleteFile.action',
	   		type:'post',
	   		dataType:'text',
	   		data:{"docFileVo.ids":id},
	   		error: function(){
				alert('文件删除失败!error');
	   		},
	   		success: function(json) {
	   			if(json=='success'){
	   				$('input[name="sysUserVo.ids"]:checked').each(function(){
						$(this).parent().parent().remove();
					});
	   			}
	   			else{
	   				$('#batchDeleteFile').addClass('zPushBtnDisabled').unbind('click',batchDeleteFile);
	   				alert('文件删除失败!');
	   				$('input[name="sysUserVo.ids"]').attr({'checked':false});
	   			}
	   		}
	   	});
	}
	var folderIndex=1;
	/*遍历文件名是否重复*/
	function traverseFolderName(){
		var type=true;
		var value = '';
		if(folderIndex==1){
			value='新建文件夹'
		}
		else{
			value = '新建文件夹 ('+folderIndex+')';
		}
		$('span[name="fileName"]').each(function(){
			if(value==$(this).html()){
				folderIndex++;
				traverseFolderName();
				type=false;
				return false;
			}
		});
		if(type){
			folderIndex=1;
			addNewFolder(value);
		}
	}
	/*新增文件夹*/
	function addNewFolder(value){
		$('#addNewFolder').unbind('click');
		var obj = $('<tr>').append(
						$('<td class="td_cb c">').html('<input lang="1" type="checkbox" onclick="batchDeleteClick()" name="sysUserVo.ids" id="ids"/>')
					).append(
						$('<td class="w150 c">').html('<a onclick="modifyFileName(this)" href="javascript:;">修改</a>&nbsp;|&nbsp;'+
						'<a onclick="removeFile(this);" href="javascript:;">删除</a>')
					).append(
						$('<td>').html('<a class="file_link" href="javascript:;"><img style="vertical-align:middle" src="'+basePath+'zTreeStyle/img/folder_Close.gif"/><span name="fileName" style="margin-left:3px;">'+value+'</span></a>')
					).append(
						$('<td align="right">')
					).append(
						$('<td align="right">')
					).append(
						$('<td align="center">')
					);
		if($('input[name="sysUserVo.ids"][lang="1"]').length==0){
			$('table.myworkingboxttable3 tbody').append(obj);
		}
		else {
			obj.insertAfter(
				$('input[name="sysUserVo.ids"][lang="1"]').last().parent().parent()
			);
		}
		obj = obj.children().eq(1).children().eq(0);
		addNewFolderName(obj);
	}
	/*新增文件夹修改名称*/
	function addNewFolderName(obj){
		var id = $(obj).parent().prev().children('input').val();
		var aObj = $(obj).parent().next();
		var aHtml = aObj.html();
		aObj.html(aObj.children().html());
		var span = aObj.children('span');
		var spanVal = span.html();
		span.parent().append(
			$('<input id="modifyInput" autocomplete= "off" style="height:18px;font-size:12px;margin-top:1px;width:'+(span.width()+10)+'px;" type="text"/>').keydown(function(e){
				if(e.keyCode==13){
					event.keyCode=9;
					if(!document.all){
						var modifiedVal = $(this).val();
						modifiedVal = modifiedVal.replace(/(^\s*)|(\s*$)/g, '');
						tarveFolderNamePresence($(this).val(),obj,aHtml);
					}
				}
			}).keyup(function(){
				$('body').append(
					$('<span>').attr({'id':'modify_Span'}).css({'display':'none'}).html($(this).val())
				);
				$(this).width($('#modify_Span').width()+10);
				$('#modify_Span').remove();
			}).blur(function(){
				var modifiedVal = $(this).val();
				modifiedVal = modifiedVal.replace(/(^\s*)|(\s*$)/g, '');
				tarveFolderNamePresence($(this).val(),obj,aHtml);
			})
		);
		$('#modifyInput').focus().focus().val(spanVal,aHtml).select();
		span.remove();
		return null;
	}
	/*修改文件名*/
	function modifyFileName(obj){
		var id = $(obj).parent().prev().children('input').val();
		var aObj = $(obj).parent().next();
		var aHtml = aObj.html();
		aObj.html(aObj.children().html());
		var span = aObj.children('span');
		var spanVal = span.html();
		span.hide();
		span.parent().append(
			$('<input id="modifyInput" autocomplete= "off" style="height:18px;font-size:12px;margin-top:1px;width:'+(span.width()+10)+'px;" type="text"/>').keydown(function(e){
				if(e.keyCode==13){
					event.keyCode=9;
					if(!document.all){
						var modifiedVal = $(this).val();
						modifiedVal = modifiedVal.replace(/(^\s*)|(\s*$)/g, '');
						if(modifiedVal== ''){
							modifiedVal=spanVal;
						}
						if(modifiedVal==spanVal){
							$(this).remove();
							aObj.html(aHtml);
							aObj.children('span').html(modifiedVal);
						}
						else {
							tarveFileNamePresence($(this).val(),obj,aHtml,id);
						}
					}
				}
			}).keyup(function(){
				$('body').append(
					$('<span>').attr({'id':'modify_Span'}).css({'display':'none'}).html($(this).val())
				);
				$(this).width($('#modify_Span').width()+10);
				$('#modify_Span').remove();
			}).blur(function(){
				var modifiedVal = $(this).val();
				modifiedVal = modifiedVal.replace(/(^\s*)|(\s*$)/g, '');
				if(modifiedVal== ''){
					modifiedVal=spanVal;
				}
				if(modifiedVal==spanVal){
					$(this).remove();
					aObj.html(aHtml);
					aObj.children('span').html(modifiedVal);
				}
				else {
					tarveFileNamePresence($(this).val(),obj,aHtml,id);
				}
			})
		);
		$('#modifyInput').focus().focus().val(spanVal).select();
		span.remove();
		return null;
	}
	
	function modifyFileNameForNew(obj){
		var treeNid = $(obj).parent().prev().children('input').val();
		showDialog(treeNid);
	}
	
	
	function showDialog(treeNid){
		var pageUrl=basePath+'/document/manager/preUpdateDoc.action?treeNid='+treeNid;
		var dg=new J.dialog({page:pageUrl,title:'更新文档：', rang: true,drag: true,resize: true,bgcolor:'#000',opacity:0.4,iconTitle:false,width:500, btnBar:false,height:400,cover:true, autoPos:{left:'center',top:'center'}});
		dg.ShowDialog();
	}
	