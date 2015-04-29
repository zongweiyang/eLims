<%@ page language="java" import="java.util.*,cn.labsoft.labos.framework.common.sesseionutils.*" pageEncoding="UTF-8"%>
<%@page import="cn.labsoft.labos.utils.DateUtils"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld" %>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="bt" uri="/WEB-INF/tld/labsoft/button.tld"%>
<%@ taglib prefix="hr" uri="/WEB-INF/tld/labsoft/href.tld"%>
<%@ taglib prefix="l" uri="/WEB-INF/tld/labsoft/lab.tld"%>
<%@ include file="/jsp/include/jquery.jsp"%>
<script type="text/javascript">
var basePath = "${basePath}"; 
</script>
<%-- <link rel=stylesheet href="${basePath}/js/skins/default.css" media="all" type="text/css" /> --%>
<!-- theme需要修改成可配置的， 为将来有多种结构考虑 -->
<s:if test="${session.SessionContainer.type!='BACK'}">
	<link rel=stylesheet href="${basePath}theme/${themeType}/skin/${session.SessionContainer.styleName}/css/common.css" type="text/css">
</s:if>
<s:else>
	<link rel=stylesheet href="${basePath}admin/skin/css/common.css" type="text/css">
</s:else>
<link rel=stylesheet href="${basePath}style/global.css" type="text/css">
<link rel=stylesheet href="${basePath}js/spechare/css.css" type="text/css">
<script type="text/javascript" src="${basePath}js/form/check.js"></script>
<script type="text/javascript" src="${basePath}utils/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}utils/dialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${basePath}utils/dialog/lhgdialog.min.js?skin=blue"></script>
<script type="text/javascript" src="<%=basePath%>js/front/shadowdiv.js"></script>
<script type="text/javascript" src="<%=basePath%>js/spechare/insertSpeChare.js"></script>
<script type="text/javascript" src="<%=basePath%>js/formula/formula.js"></script>
<script type="text/javascript" src="<%=basePath%>utils/validate/validate-1.0.js"/></script>
<link rel=stylesheet href="${basePath}utils/combox/combox.css" type="text/css">
<script type="text/javascript" src="${basePath}utils/combox/combox.js"/></script>
<!-- 页面序号元素 -->
<s:if test="${pageResult.pageBean.currentPage>=1}">
<s:set name="currenPagex" value="${pageResult.pageBean.currentPage-1 }" />
</s:if>
<s:else>
<s:set name="currenPagex" value="${pageResult.pageBean.currentPage }" />
</s:else>
<s:set name="pageSizex" value="${pageSize}" />	
			
<script language="javascript">
<s:set name="msg" value="actionErrors"/>
<s:iterator value="#msg">
var msx="${msg}";
msx=msx.replace('"','\"');
alert(msx);
</s:iterator>

<s:set name="msg" value="actionMessages"/>
<s:iterator value="#msg">
var msx="${msg}";
msx=msx.replace('"','\"');
alert(msx);
</s:iterator>

function backList() {
	window.location.href='${SessionContainer.lastUrl}';
}

function nextUrl(url) {
	window.location.href= basePath+url;
}

function down(filename,fileurl) {
	var url = '/utils/upload/down.jsp?fileName='+filename+'&fileUrl='+fileurl;
	nextUrl(basePath+url);
}

function nextUri(uri) {
	nextUrl(basePath+uri);
}

function checkAll(n) {
	$('input[name="'+n+'"]').attr({'checked':'checked'});
}
function clearAll(n) {
	$('input[name="'+n+'"]').removeAttr('checked');
}

var obj=window.parent.frames['leftFrame'];
if(typeof(obj)!='undefined' && obj!=null && '${SessionContainer.type}' == 'FRONT'){
	$.post('${basePath}coreextend/extend/getWaitAuditCount.action',function (temp){
		$(window.parent.frames['leftFrame'].document.body).find('#ul1').html(temp);
	},'text');
}
$(function (){
	$('.myworkingboxttable tr,.FormtableCon tr').each(function(link){
		if(link%2==0){
			$(this).addClass('color');
		}
		$(this).hover(
		  function () {
		    $(this).addClass('css_style_class');
		  },
		  function () {
		    $(this).removeClass('css_style_class');
		  }
		).click(function(){
			//$(this).closest('table').find(':radio,:checkbox').prop('checked',false);
			//$(this).find(':radio,:checkbox').prop('checked',true);
		}); 
	});
});
function goAction (url){  //post
	
	$('form').attr('action',basePath+url);
	$('form').submit();
}
function showProcess(id){
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath%>/jsp/common/workflow/ins/processFrame.jsp?busId='+id,
		title:'<s:property value="getText('task.progress')"/>',
		opacity:0.4,
		width:1100, 
		height:500,
		lock:true,
		max:false,
		min:false
	});
}
</script>	
<script >
	function selectAccident(otherId,type){
	var url='<%=basePath%>quality/accident/preAddLabQuaAccident4Other.action?labQuaAccidentVo.otherId='+otherId+'&labQuaAccidentVo.accType='+type;
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('accident.info')"/>',
			opacity:0.4,
			width:850,
			height:550,
			lock:true,
			max:false,
			min:false,
			close:function(){
				thisFlush();
			}
		 });
	}
	function validationCheckbox(name){
		var length=$('input[name="'+name+'"]:checked').length;
		if(length>0){
			return true;
		}else{
		return false;
		}
		
	}
	function showDialog(url,title){
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:title,
			opacity:0.4,
			width:850,
			height:550,
			lock:true
		 });
	}
	function check(name){
				var el = document.getElementsByTagName('input');     
				var len = el.length; 
				var m = 0;    
				for(var i=0; i<len; i++)
				{         
					if((el[i].type=="checkbox") && (el[i].name==name))         
					{             
						if(el[i].checked == true){
					    	m = m + 1;
					 	}      
					}     
				}  
				if(m<1){
					alert('<s:property value="getText('deleted.record')"/>');
					return false;
				}else{
					return true;
				}
			}
</script>
<style>
	.color td {
		background:#f7f7f7;
	}
	.css_style_class td{
		background:#EAF7FF;
	}
	th[property]{
		cursor:pointer;
	}
	th[property]:hover{
		background-color:#FCDB9A;
	}
	th[property] .orderIcon{
		margin-right:0px;
	}
</style>