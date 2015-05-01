<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href='<%=basePath %>workflowskins/default/css/flow.css'>
<link rel="stylesheet" type="text/css" href="<%=basePath %>workflowskins/default/css/widget.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/ext/css/ext-all.css">
<STYLE>v\:*{behavior:url(#default#VML);}</STYLE>
<style>
.Start_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/Start.cur);
}
.End_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/End.cur);
}
.AndFork_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/AndFork.cur);
}
.OrFork_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/OrFork.cur);
}
.XorFork_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/XorFork.cur);
}
.AndJoin_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/AndJoin.cur);
}
.OrJoin_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/OrJoin.cur);
}
.XorJoin_cur
{
	cursor:url(skins/default/images/workflow/cur/XorJoin.cur);
}
.Link_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/Link.cur);
}
.Node_cur
{
	cursor:url(<%=basePath %>workflowskins/default/images/workflow/cur/Node.cur);
}
.move_cur
{
	cursor:default;
}
</style>


<script language="javascript" src="<%=basePath %>js/ext/ext-base.js"></script>
<script language="javascript" src="<%=basePath %>js/ext/ext-all.js"></script>
<script language="javascript" src="<%=basePath %>js/lib/prototype.js"></script>
<script language="javascript" src='<%=basePath %>js/lib/Utils.js'></script>
<script language="javascript" src='<%=basePath %>js/widgets/ProcessingHint.js'></script>
<script language="javascript" src="<%=basePath %>js/widgets/ExtModalDialog.js"></script>
<script>
	function save()
	{
		File.save();
	}
    function loadDefault()
    {
        File.getSaveXML();
    }
    function copyEdition(editionId)
    {
        File.copySaveXml(editionId);
    }
    function autoRun()
    {
        File.autoRun();
    }
</script>
<script>
	var ImageRoot='<%=basePath %>workflowskins/default/images';
</script>
<body onload="loadDefault();" style="height:100%">
	<div id="toolbardiv"  style="width:50px;float:left;height:100%;background-color:#efeeee">
		<div>
			<input id="tools" name="tools" nodetype='move' type="button" title='<s:property value="getText('moved')"/>' value=""/><br />
		    <input id="tools" name="tools" nodetype='Link' type="button" title='<s:property value="getText('fun.transfer')"/>' value=""/>
		</div>
	</div>
	<div style="width:100%;height:100%;position:relative"  >
		<div id="canvas" class="canvas"  style="width:100%;height:100%;"  >
		</div>
	</div>
	<div id="ext-modal-dialog-win" class="x-hidden">
	    <div class="x-window-header" id="ext-modal-dialog-win-header"></div>
	    <div id="ext-modal-dialog-win-content"></div>
	</div>
</body>
<script>
//global.js
 /**
 * <pre>
 * 	组件类型 
 * </pre>
 */
 var NodeType={
 	Node:'Node',Start:'Start',End:'End',AndFork:'AndFork',AndJoin:'AndJoin',OrFork:'OrFork',OrJoin:'OrJoin',
 	XorFork:'XorFork',XorJoin:'XorJoin',Link:'Link',ChildWorkFlow:'ChildWorkFlow',WaitingNode:'WaitingNode'
 }
  /**
 * <pre>
 * 	画布（工作区）大小和位置 
 * </pre>
 */
 

 var CanvasSize={
 	LEFT:$('toolbardiv').clientWidth,TOP:0,getwidth:getCanvasWidth,getheight:getCanvasHeight
 }
 function getCanvasWidth()
 {
 	//document.debug.document.write(parseInt($('canvas').clientWidth/18)*18+"<br />");
 	return parseInt($('canvas').clientWidth/18)*18;
 }
 function getCanvasHeight()
 {

 	return parseInt($('canvas').clientHeight/18)*18;
 }
/**
 * <pre>
 * 	节点的大小
 * </pre>
  

 * @version  1.0
 * @since   1.0
 */
 var NodeSize={
 	Node:{w:47,h:35},
 	Start:{w:36,h:36},
 	End:{w:36,h:35},
 	AndFork:{w:30,h:39},
 	AndJoin:{w:30,h:41},
 	OrFork:{w:30,h:39},
 	OrJoin:{w:30,h:41},
 	XorFork:{w:30,h:42},
 	XorJoin:{w:30,h:41},
    ChildWorkFlow:{w:55,h:35},
    WaitingNode:{w:47,h:35}
 }
 /**
 *连接弧选择的小黑点大小
 */
 var blackpointSize=6;
 /**
 * <pre>
 * 	工具函数类
 * </pre>
 */
 var Utils={
 	/**
 	*取18的倍数
 	*/
 	get18Int:function(s)
 	{
 		var t=parseInt(s/18)*18;
 		if(s%18>9)
 		{
 			t+=18;
 		}
 		if(s%18<-9)
 		{
 			t-=18;
 		}
 		return t;
 	},
 	nullToblank:function(src)
 	{
 		return src==null?'':src;
 	}
 }

 /* 功能说明:
 *1)负责生成ID号
 */
 var IdGenarator = Class.create();
 IdGenarator.prototype = {
	/*初始化函数*/
	initialize:function()
	{
		this.currentId=1;
	},
	getNextId:function()
	{
		var d=new Date();
        var time=d.getTime();
        var random=Math.floor(Math.random()*100000);
        return time+""+random;
	}
 }
 
 /*
 说明：用 Javascript 获取滚动条位置等信息
  来源 ：ThickBox 2.1
  整理 ：CodeBit.cn ( http://www.CodeBit.cn )
*/
 function getScroll()
 {
	 var t, l, w, h;
	 if (document.documentElement && document.documentElement.scrollTop)
	 {
		 t = parseInt(document.documentElement.scrollTop);
		 l = parseInt(document.documentElement.scrollLeft);
		 w = parseInt(document.documentElement.scrollWidth);
		 h = parseInt(document.documentElement.scrollHeight);
	 }else if (document.body)
	 {
		 t = parseInt(document.body.scrollTop);
		 l = parseInt(document.body.scrollLeft);
		 w = parseInt(document.body.scrollWidth);
		 h = parseInt(document.body.scrollHeight);
	 }
	 return { t: t, l: l, w: w, h: h };
 }
 
 //扩展Array方法
 Array.prototype.addAll=function()
 {
 	for(var i=0;i<arguments.length;i++)
 	{
 		if (arguments[i].constructor == Array) {

 			for(var j=0;j<arguments[i].length;j++)
 			{
 				this[this.length]=arguments[i][j];
 			}
 		}else
 		{
 			this[this.length]=arguments[i];
 		}
 	}
 }
 /**
 *&quot; " 
&lt; <
&gt; >
&amp; &
&apos; '
 */
 function transXml(str)
 {
 	str=str.replace(/&/g,'&amp;');
 	str=str.replace(/</g,'&lt;');
 	str=str.replace(/>/g,'&gt;');
 	//
 	str=str.replace(/'/g,'&apos;');
 	str=str.replace(/"/g,'&quot;');
 	return str;
 }
</script>
<script>
function refreshDoc()
{
}
/**
*节点属性的基类
*/
var ComponentAtt=Class.create();
ComponentAtt.prototype=
{
	initialize:function()
	{
		this.basic={name:'',desc:''};
		var othis=this;
 		Try.these(function(){othis.afterInit();});
	},
	/**
	*XML格式:
	*<att><basic name='' desc=''></basic></att>
	*
	*/
	toXML:function(doc)
	{
		var att=doc.createElement("ATT");
		var basic=doc.createElement("BASIC");
		basic.setAttribute("NAME",this.basic.name);
		basic.setAttribute("DESC",this.basic.desc);
		att.appendChild(basic);
        if(this.afterXML!=null)
        this.afterXML(doc,att);
		return att;
	},
    initattfromxml:function(nodeObj)
    {
        //alert('begin initfromxml');
        var basic=nodeObj.selectSingleNode("ATT/BASIC");
        //alert(basic);
        this.basic.name=basic.getAttribute("NAME");
        this.basic.desc=basic.getAttribute("DESC");
        try
        {
            this.afterinitattfromxml(nodeObj);
        }catch(e)
        {
            
        }
        //alert('after initfromxml');
    }
}

var StartAtt=Class.create();
Object.extend(StartAtt.prototype,ComponentAtt.prototype);
var EndAtt=Class.create();
Object.extend(EndAtt.prototype,ComponentAtt.prototype);
var XorForkAtt=Class.create();
XorForkAtt.prototype={
	afterInit:function()
	{
		this.basic.conclass='3';
		this.basic.handSelectNextNode=1;
	},
	afterXML:function(doc,att)
	{
		var basic=att.selectSingleNode("BASIC");
		basic.setAttribute("CONCLASS",this.basic.conclass);
		basic.setAttribute("HANDSELECTNEXTNODE",this.basic.handSelectNextNode);
	},
    afterinitattfromxml:function(node)
    {
        var basic=node.selectSingleNode("ATT/BASIC");
        this.basic.conclass=basic.getAttribute("CONCLASS");
        this.basic.handSelectNextNode=basic.getAttribute("HANDSELECTNEXTNODE");
    }
}
Object.extend(XorForkAtt.prototype,ComponentAtt.prototype);

var XorJoinAtt=Class.create();
Object.extend(XorJoinAtt.prototype,ComponentAtt.prototype);
var OrForkAtt=Class.create();
OrForkAtt.prototype={
	afterInit:function()
	{
		this.basic.conclass='3';
		this.basic.handSelectNextNode=1;
	},
	afterXML:function(doc,att)
	{
		var basic=att.selectSingleNode("BASIC");
		basic.setAttribute("CONCLASS",this.basic.conclass);
		basic.setAttribute("HANDSELECTNEXTNODE",this.basic.handSelectNextNode);
	},
    afterinitattfromxml:function(node)
    {
        var basic=node.selectSingleNode("ATT/BASIC");
        this.basic.conclass=basic.getAttribute("CONCLASS");
        this.basic.handSelectNextNode=basic.getAttribute("HANDSELECTNEXTNODE");
    }
}
Object.extend(OrForkAtt.prototype,ComponentAtt.prototype);
var OrJoinAtt=Class.create();
Object.extend(OrJoinAtt.prototype,ComponentAtt.prototype);
var AndForkAtt=Class.create();
Object.extend(AndForkAtt.prototype,ComponentAtt.prototype);
var AndJoinAtt=Class.create();
Object.extend(AndJoinAtt.prototype,ComponentAtt.prototype);
//等待节点属性
var WaitingNodeAtt=Class.create();
Object.extend(WaitingNodeAtt.prototype,ComponentAtt.prototype);

var NodeAtt=Class.create();
NodeAtt.prototype={
	afterInit:function()
	{
		this.basic.businesscode='';
        this.basic.url='';
        //执行者 0 组织机构和参与角色 1 流程启动者 2 任务执行者 3 任务执行者 4 表单选择 6 参与人员
        //formselect:表单选择 selectExcutor 手动选择执行者  excuterole 执行角色  objectmodeExcutor 业务对象执行者列表 formcolumn 表单字段
        // paruserid 参与者ID号 parusername 参与者人员姓名
        //部门:userId,userId,userId,userId
        this.excutor={type:'3',selectExcutor:0,formselect:'',excuterole:'',objectmodeExcutor:'',paruserid:'',parusername:'',formcolumn:''};
		//执行规则 0 独占执行 1 同时执行 2 百分比执行
		//抢占规则 0 第一个处理 1 第一个打开
		this.runrule={runmethod:0,runrob:0,signin:0};
		//回退规则
		//backscheme "0" 上层节点 "1" 第一个节点 "2" 指定任务节点 "3" 运行时指定
        //runtimebacknodeType 运行时指定类型 runtimenodes 运行时指定节点
        this.rollback={isback:1,name:'',desc:'',backscheme:'',backnode:'',runtimenodes:'',runtimebacknodeType:'',runtimebacknodeType:''};
		//objectExtend
		this.objectextend={objectstatus:'',objecttemplate:'',views_type:'',views_str:'',role:'',roletype:'',logo:'',tag:''};
	},
	afterXML:function(doc,att)
	{
		var basic=att.selectSingleNode("BASIC");
		basic.setAttribute("BUSINESSCODE",this.basic.businesscode);
        basic.setAttribute("URL",escape(transXml(this.basic.url)));

        var excutor=doc.createElement("EXCUTOR");
		excutor.setAttribute("TYPE",this.excutor.type);
		excutor.setAttribute("SELECTEXCUTOR",this.excutor.selectExcutor);
		excutor.setAttribute("FORMSELECT",this.excutor.formselect);
        excutor.setAttribute("EXCUTEROLE",this.excutor.excuterole);
        excutor.setAttribute("OBJECTMODEEXCUTOR",this.excutor.objectmodeExcutor);
        if(this.excutor.formcolumn==null)
        {
            this.excutor.formcolumn="";
        }
        excutor.setAttribute("FORMCOLUMN",this.excutor.formcolumn);
        //alert(this.excutor.paruserid);
        if(this.excutor.paruserid==null)
        {
            this.excutor.paruserid="";
        }
        if(this.excutor.parusername==null)
        {
           this.excutor.parusername=""; 
        }
        excutor.setAttribute("PARUSERID",this.excutor.paruserid);
        excutor.setAttribute("PARUSERNAME",this.excutor.parusername);
        att.appendChild(excutor);
		
		var runrule=doc.createElement("RUNRULE");
		runrule.setAttribute("RUNMETHOD",this.runrule.runmethod);
		runrule.setAttribute("RUNROB",this.runrule.runrob);
		runrule.setAttribute("SIGNIN",this.runrule.signin);
		att.appendChild(runrule);
		
		var rollback=doc.createElement("ROLLBACK");
		rollback.setAttribute("ISBACK",this.rollback.isback);
		rollback.setAttribute("NAME",this.rollback.name);
		rollback.setAttribute("DESC",this.rollback.desc);
		rollback.setAttribute("BACKSCHEME",this.rollback.backscheme);
		rollback.setAttribute("BACKNODE",this.rollback.backnode);
        rollback.setAttribute("RUNTIMEBACKNODETYPE",this.rollback.runtimebacknodeType);
        rollback.setAttribute("RUNTIMENODES",this.rollback.runtimenodes);
        att.appendChild(rollback);

		var objectextend=doc.createElement("OBJEXTEND");
		objectextend.setAttribute("OBJSTATUS",this.objectextend.objectstatus);
		objectextend.setAttribute("OBJTEMPLATE",this.objectextend.objecttemplate);
		objectextend.setAttribute("VIEWSTYPE",this.objectextend.views_type);
		objectextend.setAttribute("VIEWSSTR",this.objectextend.views_str);
		objectextend.setAttribute("LOGO",this.objectextend.logo);
		objectextend.setAttribute("TAG",this.objectextend.tag);

		att.appendChild(objectextend);
	},
    afterinitattfromxml:function(node)
    {
        var basicNode=node.selectSingleNode("ATT/BASIC");
        this.basic.businesscode=basicNode.getAttribute("BUSINESSCODE");
        this.basic.url = unescape(basicNode.getAttribute("URL"));

        var excutorNode=node.selectSingleNode("ATT/EXCUTOR");
        this.excutor.type=excutorNode.getAttribute("TYPE");
        this.excutor.selectExcutor=excutorNode.getAttribute("SELECTEXCUTOR");
        this.excutor.formselect=excutorNode.getAttribute("FORMSELECT");
        this.excutor.excuterole=excutorNode.getAttribute("EXCUTEROLE");
        this.excutor.objectmodeExcutor=excutorNode.getAttribute("OBJECTMODEEXCUTOR");
        this.excutor.paruserid=excutorNode.getAttribute("PARUSERID");
        this.excutor.parusername=excutorNode.getAttribute("PARUSERNAME");
        this.excutor.formcolumn=excutorNode.getAttribute("FORMCOLUMN");

        var runruleNode=node.selectSingleNode("ATT/RUNRULE");
        this.runrule.runmethod=runruleNode.getAttribute("RUNMETHOD");
        this.runrule.runrob=runruleNode.getAttribute("RUNROB");
        this.runrule.signin=runruleNode.getAttribute("SIGNIN");


        var rollbackNode=node.selectSingleNode("ATT/ROLLBACK");
        this.rollback.isback=rollbackNode.getAttribute("ISBACK");
        this.rollback.name=rollbackNode.getAttribute("NAME");
        this.rollback.desc=rollbackNode.getAttribute("DESC");
        this.rollback.backscheme=rollbackNode.getAttribute("BACKSCHEME");
        this.rollback.backnode=rollbackNode.getAttribute("BACKNODE");
        this.rollback.runtimebacknodeType=rollbackNode.getAttribute("RUNTIMEBACKNODETYPE");
        this.rollback.runtimenodes=rollbackNode.getAttribute("RUNTIMENODES");

        var objectextendNode=node.selectSingleNode("ATT/OBJEXTEND");
        this.objectextend.objectstatus=objectextendNode.getAttribute("OBJSTATUS");
        this.objectextend.objecttemplate=objectextendNode.getAttribute("OBJTEMPLATE");
        this.objectextend.views_type=objectextendNode.getAttribute("VIEWSTYPE");
        this.objectextend.views_str=objectextendNode.getAttribute("VIEWSSTR");
        this.objectextend.logo=objectextendNode.getAttribute("LOGO");
        this.objectextend.tag=objectextendNode.getAttribute("TAG");

    }
}
Object.extend(NodeAtt.prototype,ComponentAtt.prototype);

var ChildWorkFlowAtt=Class.create();
ChildWorkFlowAtt.prototype={
	afterInit:function()
	{
		this.basic.businesscode='';
        this.basic.url='';
        //执行者 0 组织机构和参与角色 1 流程启动者 2 任务执行者 3 任务执行者 4 表单选择 6 参与人员
        //formselect:表单选择 selectExcutor 手动选择执行者  excuterole 执行角色  objectmodeExcutor 业务对象执行者列表 formcolumn 表单字段
        // paruserid 参与者ID号 parusername 参与者人员姓名
        //部门:userId,userId,userId,userId
        this.excutor={type:'3',selectExcutor:0,formselect:'',excuterole:'',objectmodeExcutor:'',paruserid:'',parusername:'',formcolumn:''};
		//执行规则 0 独占执行 1 同时执行 2 百分比执行
		//抢占规则 0 第一个处理 1 第一个打开
		this.runrule={runmethod:0,runrob:0,signin:0};
		//回退规则
		//backscheme "0" 上层节点 "1" 第一个节点 "2" 指定任务节点 "3" 运行时指定
        //runtimebacknodeType 运行时指定类型 runtimenodes 运行时指定节点
        this.rollback={isback:1,name:'',desc:'',backscheme:'',backnode:'',runtimenodes:'',runtimebacknodeType:'',runtimebacknodeType:''};
		//objectExtend
		this.objectextend={objectstatus:'',objecttemplate:'',views_type:'',views_str:'',role:'',roletype:'',logo:'',tag:''};
	},
	afterXML:function(doc,att)
	{	
		var basic=att.selectSingleNode("BASIC");
		basic.setAttribute("BUSINESSCODE",this.basic.businesscode);
        basic.setAttribute("URL",escape(transXml(this.basic.url)));

        var excutor=doc.createElement("EXCUTOR");
		excutor.setAttribute("TYPE",this.excutor.type);
		excutor.setAttribute("SELECTEXCUTOR",this.excutor.selectExcutor);
		excutor.setAttribute("FORMSELECT",this.excutor.formselect);
        excutor.setAttribute("EXCUTEROLE",this.excutor.excuterole);
        excutor.setAttribute("OBJECTMODEEXCUTOR",this.excutor.objectmodeExcutor);
        if(this.excutor.formcolumn==null)
        {
            this.excutor.formcolumn="";
        }
        excutor.setAttribute("FORMCOLUMN",this.excutor.formcolumn);
        //alert(this.excutor.paruserid);
        if(this.excutor.paruserid==null)
        {
            this.excutor.paruserid="";
        }
        if(this.excutor.parusername==null)
        {
           this.excutor.parusername=""; 
        }
        excutor.setAttribute("PARUSERID",this.excutor.paruserid);
        excutor.setAttribute("PARUSERNAME",this.excutor.parusername);
        att.appendChild(excutor);
		
		var runrule=doc.createElement("RUNRULE");
		runrule.setAttribute("RUNMETHOD",this.runrule.runmethod);
		runrule.setAttribute("RUNROB",this.runrule.runrob);
		runrule.setAttribute("SIGNIN",this.runrule.signin);
		att.appendChild(runrule);
		
		var rollback=doc.createElement("ROLLBACK");
		rollback.setAttribute("ISBACK",this.rollback.isback);
		rollback.setAttribute("NAME",this.rollback.name);
		rollback.setAttribute("DESC",this.rollback.desc);
		rollback.setAttribute("BACKSCHEME",this.rollback.backscheme);
		rollback.setAttribute("BACKNODE",this.rollback.backnode);
        rollback.setAttribute("RUNTIMEBACKNODETYPE",this.rollback.runtimebacknodeType);
        rollback.setAttribute("RUNTIMENODES",this.rollback.runtimenodes);
        att.appendChild(rollback);

		var objectextend=doc.createElement("OBJEXTEND");
		objectextend.setAttribute("OBJSTATUS",this.objectextend.objectstatus);
		objectextend.setAttribute("OBJTEMPLATE",this.objectextend.objecttemplate);
		objectextend.setAttribute("VIEWSTYPE",this.objectextend.views_type);
		objectextend.setAttribute("VIEWSSTR",this.objectextend.views_str);
		objectextend.setAttribute("LOGO",this.objectextend.logo);
		objectextend.setAttribute("TAG",this.objectextend.tag);

		att.appendChild(objectextend);
	},
    afterinitattfromxml:function(node)
    {
        var basicNode=node.selectSingleNode("ATT/BASIC");
        this.basic.businesscode=basicNode.getAttribute("BUSINESSCODE");
        this.basic.url = unescape(basicNode.getAttribute("URL"));

        var excutorNode=node.selectSingleNode("ATT/EXCUTOR");
        this.excutor.type=excutorNode.getAttribute("TYPE");
        this.excutor.selectExcutor=excutorNode.getAttribute("SELECTEXCUTOR");
        this.excutor.formselect=excutorNode.getAttribute("FORMSELECT");
        this.excutor.excuterole=excutorNode.getAttribute("EXCUTEROLE");
        this.excutor.objectmodeExcutor=excutorNode.getAttribute("OBJECTMODEEXCUTOR");
        this.excutor.paruserid=excutorNode.getAttribute("PARUSERID");
        this.excutor.parusername=excutorNode.getAttribute("PARUSERNAME");
        this.excutor.formcolumn=excutorNode.getAttribute("FORMCOLUMN");

        var runruleNode=node.selectSingleNode("ATT/RUNRULE");
        this.runrule.runmethod=runruleNode.getAttribute("RUNMETHOD");
        this.runrule.runrob=runruleNode.getAttribute("RUNROB");
        this.runrule.signin=runruleNode.getAttribute("SIGNIN");


        var rollbackNode=node.selectSingleNode("ATT/ROLLBACK");
        this.rollback.isback=rollbackNode.getAttribute("ISBACK");
        this.rollback.name=rollbackNode.getAttribute("NAME");
        this.rollback.desc=rollbackNode.getAttribute("DESC");
        this.rollback.backscheme=rollbackNode.getAttribute("BACKSCHEME");
        this.rollback.backnode=rollbackNode.getAttribute("BACKNODE");
        this.rollback.runtimebacknodeType=rollbackNode.getAttribute("RUNTIMEBACKNODETYPE");
        this.rollback.runtimenodes=rollbackNode.getAttribute("RUNTIMENODES");

        var objectextendNode=node.selectSingleNode("ATT/OBJEXTEND");
        this.objectextend.objectstatus=objectextendNode.getAttribute("OBJSTATUS");
        this.objectextend.objecttemplate=objectextendNode.getAttribute("OBJTEMPLATE");
        this.objectextend.views_type=objectextendNode.getAttribute("VIEWSTYPE");
        this.objectextend.views_str=objectextendNode.getAttribute("VIEWSSTR");
        this.objectextend.logo=objectextendNode.getAttribute("LOGO");
        this.objectextend.tag=objectextendNode.getAttribute("TAG");
    }
}


Object.extend(ChildWorkFlowAtt.prototype,ComponentAtt.prototype);


var LinkAtt=Class.create();
LinkAtt.prototype={
	afterInit:function()
	{
		this.basic.con='';
	},
	afterXML:function(doc,att)
	{
		var basic=att.selectSingleNode("BASIC");
		basic.setAttribute("CON",escape(transXml(this.basic.con)));
	},
    afterinitattfromxml:function(node)
    {
        var basic=node.selectSingleNode("ATT/BASIC");
        this.basic.con=unescape(basic.getAttribute("CON"));
    }
}
Object.extend(LinkAtt.prototype,ComponentAtt.prototype);

</script>
<script>

//nodejs
/**
*所有组件(节点)的基类
*
*/
var Component=Class.create();
Component.prototype=
{
	initialize:function(position,type,id)
	{
        this.element=document.createElement("DIV");
		//节点类型
		this.type=type;
		//生成ID
		if(id!=null)
		{
			idGen.getNextId();
			this.id=id
		}else
		{
			this.id=idGen.getNextId();
			id=this.id;
		}
		//数据库编号
		this.dbid='';
		//节点位置
		this.element.className="com_off";
		this.element.style.left=position.x;
		this.element.style.top=position.y;
		
		this.element.style.width=NodeSize[type].w;
		this.element.style.height=NodeSize[type].h;

        var imgrc=ImageRoot+"/workflow/big/"+type+".png";
        
        this.element.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+imgrc+"',sizingMethod='scale')";
        this.element.innerHTML="&nbsp;";
        //this.element.innerHTML="<img  src='"+ImageRoot+"/workflow/big/"+type+".png' width='"+NodeSize[type].w+"' height='"+NodeSize[type].h+"' />";
		//this.element.innerHTML+='<input type="text" style="display:none" size="10" name="node_input_'+id+'"  value="'+type+id+'" class="node_input_on" />';
		
		//节点左上角位置
		this.leto=position;
		
		this.links=new Array();

		$('canvas').appendChild(this.element);
		
		var othis=this;
		Try.these(
			function(){othis.afterinit();}
		);
	},
    /**
	*开始移动节点的函数，记录下节点和箭头的起始位置
	*/
	elementStartDrag:function(clientX,clientY)
	{
		Try.these(
		function(){this.beforeStartDrag();}
		);
		this.startX=parseInt(this.element.style.left);
		this.startY=parseInt(this.element.style.top);
		this.mouseX=clientX;
		this.mouseY=clientY;
		var othis=this;
		//纪录下开始箭头的位置
		/*$A(this.links).each(function(value)
 	  		{
 	  			//var t=value.isStartOrEnd.bind(value.line);
 	  			if(value.isStartOrEnd(othis)=='start')
 	  			{
 	  				value.mousedownX=parseInt(value.line.style.left);
 	  				value.mousedownY=parseInt(value.line.style.top);
 	  				
 	  			}
 	  		}
 	  	);*/
		Try.these(
		function(){othis.afterStartDrag();}
		);
	},
	//添加一个连接弧
	addlink:function(link)
	{
		this.links[this.links.length]=link;
	},
	/**
	*节点拖动的函数
	*/
	elementDrag:function(clientX,clientY)
	{
		Try.these(
		function(){this.beforeDrag();}
		);
		var dx=clientX - this.mouseX;
		var dy=clientY - this.mouseY;
		var left = this.startX + dx;
 	  	var top =  this.startY + dy;
 	  	//调整left top让它只能在画布上移动
 	  	left=left<0?0:left;
 	  	top=top<0?0:top;
 	  	left=left+NodeSize[this.type].w>CanvasSize.getwidth()?CanvasSize.getwidth()-NodeSize[this.type].w:left;
 	  	top=top+NodeSize[this.type].h>CanvasSize.getheight()?CanvasSize.getheight()-NodeSize[this.type].h:top;
		dx=left-this.startX;
		dy=top-this.startY;
		
 	  	this.element.style.left=left;
 	  	this.element.style.top=top;
 	  	//刷新左上角的点
 	  	this.leto.x=parseInt(left);
 	  	this.leto.y=parseInt(top);

 	  	var othis=this;
 	  	//同步移动小黑点 和 箭头
 	  	$A(this.links).each(function(value)
 	  		{
 	  			if(value.isStartOrEnd(othis)=='start')
 	  			{
					var black=value.PointBlacks[0];
					black.style.left=value.points[0].x-blackpointSize/2+dx;
					black.style.top=value.points[0].y-blackpointSize/2+dy;
					//移动箭头
					var p=new Position(parseInt(black.style.left)+blackpointSize/2,parseInt(black.style.top)+blackpointSize/2);
					
					var newp=othis.getlineCrossRect(p,value.points[1]);
					
					value.moveline(value.lines[0],newp,value.points[1]);

 	  			}else if(value.isStartOrEnd(othis)=='end')
 	  			{
 	  				var black=value.PointBlacks[value.PointBlacks.length-1];
					black.style.left=value.points[value.points.length-1].x-blackpointSize/2+dx;
					black.style.top=value.points[value.points.length-1].y-blackpointSize/2+dy;
 	  				
					var p=new Position(parseInt(black.style.left)+blackpointSize/2,parseInt(black.style.top)+blackpointSize/2);
					var newp=othis.getlineCrossRect(p,value.points[value.points.length-2]);
					
					value.moveline(value.lines[value.lines.length-1],value.points[value.points.length-2],newp);
				}
 	  		}
 	  	);
 	  	Try.these(
		function(){othis.afterDrag(dx,dy);}
		);
	},
		/**
	*停止拖动的函数
	*/
	elementStopDrag:function(clientX,clientY)
	{
		//调整节点为整格数
		var curX=Utils.get18Int(parseInt(this.element.style.left));
		var dx=curX-parseInt(this.element.style.left);
		var curY=Utils.get18Int(parseInt(this.element.style.top));
		var dy=curY-parseInt(this.element.style.top);
		this.element.style.left=curX;
		this.element.style.top=curY;
		//刷新左上角的点
		this.leto.x=parseInt(this.element.style.left);
		this.leto.y=parseInt(this.element.style.top);
		
		
		var othis=this;
		//同步调整小黑快
 	  	$A(this.links).each(function(value)
 	  		{
 	  			if(value.isStartOrEnd(othis)=='start')
 	  			{
					var black=value.PointBlacks[0];
					black.style.left=parseInt(black.style.left)+dx;
					black.style.top=parseInt(black.style.top)+dy;
					value.points[0].x=parseInt(black.style.left)+blackpointSize/2;
 	  				value.points[0].y=parseInt(black.style.top)+blackpointSize/2;
					//移动箭头
					var p=new Position(parseInt(black.style.left)+blackpointSize/2,parseInt(black.style.top)+blackpointSize/2);
					var newp=othis.getlineCrossRect(p,value.points[1]);
					
					value.moveline(value.lines[0],newp,value.points[1]);

 	  			}else if(value.isStartOrEnd(othis)=='end')
 	  			{
 	  				var black=value.PointBlacks[value.PointBlacks.length-1];
					black.style.left=parseInt(black.style.left)+dx;
					black.style.top=parseInt(black.style.top)+dy;
					value.points[value.points.length-1].x=parseInt(black.style.left)+blackpointSize/2;
 	  				value.points[value.points.length-1].y=parseInt(black.style.top)+blackpointSize/2;
					
					var p=new Position(parseInt(black.style.left)+blackpointSize/2,parseInt(black.style.top)+blackpointSize/2);
					var newp=othis.getlineCrossRect(p,value.points[value.points.length-2]);
					
					value.moveline(value.lines[value.lines.length-1],value.points[value.points.length-2],newp);
				}
 	  			
 	  		}
 	  	);
 	  	Try.these(
		function(){othis.afterStopDrag();}
		);		
	},
	//添加移动事件
	addEvent:function()
	{
		var othis=this;
		this.drag=new Drag(this.element);
		var moveable=false;
		this.element.onmousedown=function(){moveable=true;componentMan.setSelectedObj(othis);othis.drag.startDrag(null,othis.elementStartDrag.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};	
		this.element.onmousemove=function(){if(!moveable) return;othis.drag.drag(null,othis.elementDrag.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		this.element.onmouseup=function(){moveable=false;othis.drag.stopDrag(null,othis.elementStopDrag.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		this.element.ondblclick=function(){othis.dblfun()}
	},
	//删除移动事件
	removeEvent:function()
	{
		var othis=this;
		this.element.onmousedown=null;
		this.element.onmousemove=null;
		this.element.onmouseup=null;
		this.element.ondblclick=null;
	},
	remove:function()
	{
		//先删除迁移
		var othis=this;
		$A(this.links).each(function(value){value.remove(othis)});
		//删除节点
		$('canvas').removeChild(this.element);
		Try.these(
		function(){othis.removeafter()}
		);
	}
	,
	setSelect:function()
	{
		this.element.className="com_on";
		//$('node_input_'+this.id).className="node_input_on";
	},
	noSelect:function()
	{
		
		
		this.element.className="com_off";
		//$('node_input_'+this.id).className="node_input_off";
	},
	/**
	*点是否在这个节点内
	*@param point 相对于画布的位置
	*@return true or false
	*/
	pointInElement:function(point)
	{
		var x=point.x;
		var y=point.y;
		//注意leto中是相对画布的位置
		if(x>=this.leto.x && x<=this.leto.x+NodeSize[this.type].w && y>=this.leto.y && y<=this.leto.y+NodeSize[this.type].h)
		{
			return true;
		}else
		{
			return false;
		}
	},                                                        
	/**
	*调整连接弧的小黑点的位置不要超出框
	*/
	repoistBlackPoint:function(blackpoint)
	{
		var p=new Position(blackpoint.x,blackpoint.y);
		if(blackpoint.x<this.leto.x)
		{
			p.x=this.leto.x;
		}
		if(blackpoint.y<this.leto.y)
		{
			p.y=this.leto.y;
		}
		if((blackpoint.x+blackpointSize)>(this.leto.x+NodeSize[this.type].w))
		{
			p.x=(this.leto.x+NodeSize[this.type].w)-blackpointSize;
		}
		if((blackpoint.y+blackpointSize)>(this.leto.y+NodeSize[this.type].h))
		{
			p.y=(this.leto.y+NodeSize[this.type].h)-blackpointSize;
		}
		return p;
	},
	/**
	*获得两个中心点和节点边框的交点 （这个函数是获得e1边框的交点）
	*/
	getlineCrossRect:function(e1,e2)
	{
		var point=new Position(null,null);
		var e1c=e1;
		var e2c=e2;
		if(this.type=='Node')
		{
			//处理任务下的文本高度
			if(this.text=='')
			{
				h=0;
			}else
			{
				var h=this.textHeight;
				if(h==null)
				h=$('node_text_div'+this.id).offsetHeight;
			}
			var vh=NodeSize[this.type].h+h;
		}else
		{
			var vh=NodeSize[this.type].h;
		}
		//平行线和数值先
		if(e1.x==e2.x)
		{
			point.x=e1.x;
			if(e2.y>e1.y)
			{
				point.y=this.leto.y+vh;
			}else
			{
				point.y=this.leto.y;
			}
			return point;
		}
		if(e1.y==e2.y)
		{
			point.y=e1.y;
			if(e2.x>e1.x)
			{
				point.x=this.leto.x+NodeSize[this.type].w;
			}else
			{
				point.x=this.leto.x;
			}
			return point;
		}

		if(e2.x>e1.x && e2.y>e1.y)
		{
			//在右下角
			var conerx=this.leto.x+NodeSize[this.type].w;
			var conery=this.leto.y+vh;
		}
		if(e2.x>e1.x && e2.y<e1.y)
		{
			//在右上角
			var conerx=this.leto.x+NodeSize[this.type].w;
			var conery=this.leto.y;
		}
		if(e2.x<e1.x && e2.y<e1.y)
		{
			//在左上角
			var conerx=this.leto.x;
			var conery=this.leto.y;
		}
		if(e2.x<e1.x && e2.y>e1.y)
		{
			//在左下角
			var conerx=this.leto.x;
			var conery=this.leto.y+vh;
		}
		//临界角度
		var linjie=Math.abs((conery-e1.y)/(conerx-e1.x));
		var realjiaodu=Math.abs((e2.y-e1.y)/(e2.x-e1.x));
		if(realjiaodu>linjie)
		{
			//点落在长上
			point.y=conery;
			//document.debug.document.write('chang');
			point.x=parseInt(e1.x-(e1.y-conery)*(e1.x-e2.x)/(e1.y-e2.y));
		}else
		{
			//点落在宽上
			point.x=conerx;
			//document.debug.document.write('kuan');
			point.y=parseInt(e1.y-(e1.x-conerx)*(e1.y-e2.y)/(e1.x-e2.x));
		}
		//document.debug.document.write(point.x+":"+point.y+"<br >");
		return point;
	},
	//作为前驱节点的个数
	getBeStartNodeNum:function()
	{
		var num=0;
		var othis=this;
		$A(this.links).each(
		function(value)
		{
			if(value.startNode.id==othis.id)
			{
				num++;
			}
		}
		);
		return num;
	},
	//作为后驱节点的个数
	getBeEndNodeNum:function()
	{
		var num=0;
		var othis=this;
		$A(this.links).each(
		function(value)
		{
			if(value.endNode.id==othis.id)
			{
				num++;
			}
		}
		);
		return num;
	}
	,
	dblfun:function()
	{
		var othis=this;
		ExtModalDialog.show("节点属性","<%=basePath%>/jsp/workflow/temp.jsp",window,refreshDoc.bind(othis),{width:600,height:400},{'node':othis,'man':componentMan});
	},
	/**
	*格式
	*/
	toXML:function(doc)
	{
		var node=doc.createElement("NODE");
		node.setAttribute("ID",this.id);
		node.setAttribute("DBID",this.dbid);
		node.setAttribute("TYPE",this.type);
		node.setAttribute("LEFT",parseInt(this.element.style.left));
		node.setAttribute("TOP",parseInt(this.element.style.top));
		
		node.appendChild(this.attObj.toXML(doc));
		var othis=this;
		Try.these(
		function(){othis.afterToXML(node);}
		);
		return node;
	}
};
/**
*节点类
*/
var Node=Class.create();
Node.prototype={
	afterinit:function()
	{
		//任务定义ID
		this.taskdefId="";
		//文字
		this.text="";
		/*<div style="line-height:18px; height:18px; color:#333333">我猜我才我猜猜猜</div>*/
		var textDiv=document.createElement("div");
		textDiv.className="node_text_div";
		textDiv.id="node_text_div"+this.id;
		textDiv.innerHTML="<input type='text' value='"+this.text+"' size=12 style='font-size:12px;height:12px' name='text"+this.id+"' class='node_text_input'  />";
		textDiv.style.position="absolute";
		textDiv.style.width=104;
		$('canvas').appendChild(textDiv);
		//document.debug.document.write(text.clientWidth);
		var x=this.leto.x+NodeSize['Node'].w/2-textDiv.clientWidth/2;
		var y=this.leto.y+NodeSize['Node'].h;
		textDiv.style.left=x;
		textDiv.style.top=y;

		textDiv.onclick=function(){
			componentMan.setSelectedObj(othis);
			if($('text'+this.id)!=null)
			$('text'+this.id).focus();
		}
		$('text'+this.id).onblur=this.noSelect.bind(this);
		$('text'+this.id).onchange=this.ontextdatachange.bind(this);
		var othis=this;
		this.textDiv=textDiv;
		//初始化属性对象
		this.attObj=new NodeAtt();
        //alert(this.afteraferinit)
        try
        {
           this.afteraferinit();
        }catch(e)
        {
        }
    },
    changePic:function(picname)
    {
        var imgrc=ImageRoot+"/workflow/big/"+picname;
        this.element.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+imgrc+"',sizingMethod='scale')";
    },
    changePicgif:function(picname)
    {
        var imgrc=ImageRoot+"/workflow/big/"+picname;
        this.element.style.filter = "";
        this.element.innerHTML="<img src='"+imgrc+"' width='47' height='35'>";
    }
    ,
    afterToXML:function(node)
	{
		//处理TASKNODEID
		node.setAttribute("TASKDEFID",this.taskdefId);
	}
	,
	ontextdatachange:function()
	{
		this.attObj.basic.name = $('text'+this.id).value;
	},
	//更新节点
	updateText:function(text)
	{
		this.text=text;
		if($('text'+this.id)!=null)
		{
			$('text'+this.id).value=text;
		}else
		{
			$('node_text_div'+this.id).innerHTML=this.text;
			this.textHeight=$("node_text_div"+this.id).offsetHeight;
		}
		this.attObj.basic.name = text;
	},
    //添加节点
    addText:function(text)
    {
        $('node_text_div'+this.id).style.width = 120;
        $('node_text_div'+this.id).innerHTML=$('node_text_div'+this.id).innerHTML+"<br />"+text;
		this.textHeight=$("node_text_div"+this.id).offsetHeight;
        var x=this.leto.x+NodeSize['Node'].w/2-$('node_text_div'+this.id).clientWidth/2;
		var y=this.leto.y+NodeSize['Node'].h;
		$('node_text_div'+this.id).style.left=x;
		$('node_text_div'+this.id).style.top=y;
    }
    ,
	//处理移动事件
	afterStartDrag:function()
	{
		this.inputstartx=parseInt(this.textDiv.style.left);
		this.inputstarty=parseInt(this.textDiv.style.top);
	},
	afterDrag:function(dx,dy)
	{
		this.textDiv.style.left=this.inputstartx+dx;
		this.textDiv.style.top=this.inputstarty+dy;
	},
	afterStopDrag:function()
	{
		var x=this.leto.x+NodeSize['Node'].w/2-this.textDiv.clientWidth/2;
		var y=this.leto.y+NodeSize['Node'].h;
		this.textDiv.style.left=x;
		this.textDiv.style.top=y;
	},
	removeafter:function()
	{
		//删除文本框
		$('canvas').removeChild($('node_text_div'+this.id));
	}
};
Object.extend(Node.prototype,Component.prototype);
Node.prototype.setSelect=function()
{

	
	if($('text'+this.id)==null)
	{
		$('node_text_div'+this.id).innerHTML="<input  type='text' value='"+this.text+"' size=12 style='font-size:12px;height:12px' name='text"+this.id+"' class='node_text_input'  />";
		$('text'+this.id).onblur=this.noSelect.bind(this);
		$('text'+this.id).onchange=this.ontextdatachange.bind(this);
	}
    var othis=this;
    this.textDiv.onclick=function(){
			componentMan.setSelectedObj(othis);
			if($('text'+this.id)!=null)
			$('text'+this.id).focus();
	}
    //document.debug.document.writeln("here");
	var othis=this;
	$('text'+this.id).focus();
};
Node.prototype.noSelect=function()
{
	//$('text'+this.id).blur();
	if($('text'+this.id)!=null)
	{
		this.text=$('text'+this.id).value;
		$('node_text_div'+this.id).innerHTML=this.text;
		this.textHeight=$("node_text_div"+this.id).offsetHeight;
	}
	this.attObj.basic.name = this.text;
	this.textDiv.onclick=null;
};
Node.prototype.dblfun=function()
{
	othis = this;
	var _value = $('node_text_div'+othis.id).childNodes[0].value;
	if(_value == undefined){
	   _value = $('node_text_div'+othis.id).innerHTML;
	}
	var overDate = othis.attObj.objectextend.objectstatus;
	if(overDate == 'null'){
		overDate = '';
	}
	var url = othis.attObj.objectextend.views_str;
	ExtModalDialog.show("节点属性","<%=basePath %>workflow/process/preUpdateLabWfStep.action?labWfStepVo.operType="+othis.attObj.objectextend.objecttemplate+"&labWfStepVo.id="+this.id+"&labWfStepVo.name="+encodeURIComponent(_value)+"&labWfStepVo.ids="+othis.attObj.objectextend.views_type+"&labWfStepVo.overDate="+overDate+"&labWfStepVo.url="+url,window,refreshDoc.bind(othis),{width:600,height:400,maximizable: false,collapsible: false});
}
/*
 *	回调函数设置节点值
 */
function callBackNodeFun(stepName,overDate,type,ids,url) {
	$('node_text_div'+othis.id).innerHTML = stepName;
	othis.text = stepName;
	othis.attObj.basic.name = stepName;
	othis.attObj.objectextend.objectstatus = overDate;
	othis.attObj.objectextend.objecttemplate = type;
	othis.attObj.objectextend.views_type = ids;
	othis.attObj.objectextend.views_str = url;
	othis = null;
}
/*
 *	回调函数设置线路值
 */
function callBackLineFun(pathName,pathstr) {
	othis.attObj.basic.name = pathName;
	othis.attObj.basic.desc = pathstr;
	$('link_text'+othis.id).value = pathName;
	othis = null;
}
/**
*开始
*/
var Start=Class.create();
Start.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new StartAtt();
	}
};
Object.extend(Start.prototype,Component.prototype);
/**
*结束
*/
var End=Class.create();
End.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new EndAtt();
	}
}
Object.extend(End.prototype,Component.prototype);
/**
*AndFork节点
*/
var AndFork=Class.create();
AndFork.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new AndForkAtt();
	}
};
Object.extend(AndFork.prototype,Component.prototype);
/**
*AndJoin
*/
var AndJoin=Class.create();
AndJoin.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new AndJoinAtt();
	}
}
Object.extend(AndJoin.prototype,Component.prototype);
/**
*OrFork
*/
var OrFork=Class.create();
OrFork.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new OrForkAtt();
	}
}
Object.extend(OrFork.prototype,Component.prototype);
OrFork.prototype.dblfun=function()
{
	var othis=this;
	ExtModalDialog.show("节点属性","workflow/attribute.spr?action=orfork_index",window,refreshDoc.bind(othis),{width:600,height:400},{'node':othis,'man':componentMan});
}
/**
*OrJoin
*/
var OrJoin=Class.create();
OrJoin.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new OrJoinAtt();
	}
}
Object.extend(OrJoin.prototype,Component.prototype);
/**
*XorFork
*/
var XorFork=Class.create();
XorFork.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new XorForkAtt();
	}
}
Object.extend(XorFork.prototype,Component.prototype);
XorFork.prototype.dblfun=function()
{
	var othis=this;
	ExtModalDialog.show("节点属性","workflow/attribute.spr?action=orfork_index",window,refreshDoc.bind(othis),{width:600,height:400},{'node':othis,'man':componentMan});
}
/**
*XorJoin
*/
var XorJoin=Class.create();
XorJoin.prototype={
	afterinit:function()
	{
		//初始化属性对象
		this.attObj=new XorJoinAtt();
	}
}
Object.extend(XorJoin.prototype,Component.prototype);

/**
*ChildWorkFlow
*/
var ChildWorkFlow=Class.create();
ChildWorkFlow.prototype={
	afteraferinit:function()
	{
		//初始化属性对象
		this.attObj=new ChildWorkFlowAtt();
	}
}
Object.extend(ChildWorkFlow.prototype,Node.prototype);
ChildWorkFlow.prototype.dblfun=function()
{
	var othis=this;
	top.ExtModalDialog.show("节点属性","workflow/attribute.spr?action=child_workflow",window,refreshDoc.bind(othis),{width:500,height:230},{'node':othis,'man':componentMan});
}
/**
 * waitingNode
 */
var WaitingNode=Class.create();
WaitingNode.prototype={
	afterinit:function()
	{
		//初始化属性对象
        this.attObj=new WaitingNodeAtt();
    }
}
Object.extend(WaitingNode.prototype,Component.prototype);
/**
*位置表示类
*/
var Position=Class.create();
Position.prototype=
{
	initialize:function(x,y)
	{
		this.x=x;
		this.y=y;
	},
	toString:function()
	{
		return this.x+","+this.y;
	}
}
//迁移
var Link=Class.create();
Link.prototype=
{
	initialize:function(startNode,endNode,points,id)
	{
		this.type=NodeType.Link;
		//生成ID
		if(id!=null)
		{
			idGen.getNextId();
			this.id=id
		}else
		{
			this.id=idGen.getNextId();
		}
		this.dbid='';
		//document.debug.document.write("length:"+points.length+"<br />");
		//点位置
		this.points=points;
        var pointIds=new Array();

        for(var i=0;i<points.length;i++)
        {
            pointIds[pointIds.length]=idGen.getNextId();
        }
        this.pointIds=pointIds;
		this.inflexId=null;
		
		
		this.inflextype=null;
		
		this.startNode=startNode;
		this.endNode=endNode;
		

		this.lines=new Array();
		this.PointBlacks=new Array();

		var othis=this;
		this.startNode.addlink(othis);
		this.endNode.addlink(othis);
		//生成直线函数
		var createLine=function(p1,p2,endarrow)
		{
			var line=document.createElement("v:line");
			line.strokecolor="#89ccf3";
			line.strokeweight="2px";
			line.innerHTML="<v:Stroke dashstyle='Solid' endarrow='"+endarrow+"'/>";
			line.style.position="absolute";
			line.style.left=p1.x;
			line.style.top=p1.y;

			var dx=p2.x-p1.x;
			var dy=p2.y-p1.y;
			line.to=dx+","+dy;
			othis.lines[othis.lines.length]=line;
			$('canvas').appendChild(line);
		};
		//document.debug.document.write("inflexId:"+inflexId+"<br />");
		//开始处理拐点
		/*if(inflexId!=null)
		{
			var has=componentMan.hasflex(inflexId);
			if(has)
			{
				//拐点已经存在了就需要一些连线不用创建了
				if(inflextype==1)
				{
					//拐出，拐点前面的线都不用创建了
					var beforeLines=this.getInflexBeforeLines();
					this.lines.addAll(beforeLines);
					start=this.getInflexIndex();
					end=this.points.length;
				}else
				{
					//拐进，之后的线不用创建,应该在创建完这些线，调用添加
					var afterAdd=true;
					start=0;
					end=this.getInflexIndex();
				}
			}else
			{
				start=0;
				end=this.points.length;
			}
		}else
		{
			start=0;
			end=this.points.length;
		}*/
		
		//document.debug.document.write(start+":"+end+"<br />");
		
		
		for(var i=0;i<this.points.length;i++)
		{
			if(i+1<=this.points.length-1)
			{
				if(i+1==this.points.length-1)
				{
					var newpoint=this.endNode.getlineCrossRect(points[i+1],points[i]);
					//如果只有两个点，算两个边界
					if(this.points.length==2)
					{
						var beginpoint=this.startNode.getlineCrossRect(points[i],points[i+1]);
					}else
					{
						beginpoint=points[i];
					}
                    createLine(beginpoint,newpoint,"classic");
				}
				else if(i==0 && i+1!=this.points.length-1)
				{
                    var newpoint=this.startNode.getlineCrossRect(points[i],points[i+1]);
					createLine(newpoint,points[i+1],"");
				}else
				{
					createLine(points[i],points[i+1],"");
				}
			}
		}
		//拐点后面添加
		/*if(afterAdd!=null)
		{
			var afterLines=this.getInflexAfterLines();
			this.lines.addAll(afterLines);
		}*/
		//alert("停止类吗？");
		
		//创建文本框
		var div=document.createElement("DIV");
		div.className="link_text_div";
        //
        div.innerHTML="<input type='text' name='link_text"+this.id+"' size=10 class='link_text_on'  />";
        //alert("点的长度:"+this.points.length);
        if(this.points.length==2)
		{
			var s=this.points[0];
			var e=this.points[1];
		}else
		{
			var s=this.points[1];
			var e=this.points[2];
		}

        $('canvas').appendChild(div);
        div.style.left=s.x+(e.x-s.x)/2-div.offsetWidth/2;
		div.style.top=s.y+(e.y-s.y)/2-div.offsetHeight-2;
		this.textDiv=div;
        $('link_text'+this.id).onfocus=function(){$('link_text'+othis.id).className="link_text_on";event.cancelBubble=true;event.returnValue=false;};
		$('link_text'+this.id).onblur=function(){$('link_text'+othis.id).className="link_text_off";event.cancelBubble=true;event.returnValue=false;};
		$('link_text'+this.id).onchange=function(){othis.attObj.basic.name=$('link_text'+othis.id).value;}; 
		$("link_text"+this.id).focus();
        //alert(div.outerHTML);
        //alert(div.style.left);
        //alert(div.style.top);
        //新建小黑点
		$A(this.points).each(
			function(value){
				var d=document.createElement("div");
				d.className="link_point";
				d.style.left=value.x-blackpointSize/2;
				d.style.top=value.y-blackpointSize/2;
				d.style.display="none";
				d.innerHTML="&nbsp;";
				othis.PointBlacks[othis.PointBlacks.length]=d;
				$('canvas').appendChild(d);
			}
		);
		//选中状态
		this.setPointBlack();
		this.setSelect();
		//
		this.attObj=new LinkAtt();
	},
	updateText:function(text)
	{
		$('link_text'+this.id).value=text;
	}
	,
	addTextMoveEvent:function()
	{
		//文本框的移动操作
		var textmousedown=function(clientX,clientY){
			this.textmouseX=clientX;
			this.textmouseY=clientY;
			this.textleft=parseInt(this.textDiv.style.left);
			this.texttop=parseInt(this.textDiv.style.top);
			
		};
		var textmousemove=function(clientX,clientY)
		{
			var dx=clientX-this.textmouseX;
			var dy=clientY-this.textmouseY;
			this.textDiv.style.left=this.textleft+dx;
			this.textDiv.style.top=this.texttop+dy;
		}
		var textmouseup=function(clientX,clientY)
		{
		}
		var othis=this;
		var drag=new Drag(this.textDiv);
		this.textDiv.onmousedown=function(){drag.startDrag(null,textmousedown.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};	
		this.textDiv.onmousemove=function(){drag.drag(null,textmousemove.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		this.textDiv.onmouseup=function(){drag.stopDrag(null,textmouseup.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
	}
	,
	/**
	*设置黑点
	*/
	setPointBlack:function()
	{
		var othis=this;
        //alert(this.points);
        $A(this.points).each(
			function(value)
			{
                var index=othis.indexOfPoints(value);
                var d=othis.PointBlacks[index];
				
				//document.debug.document.writeln(d.id+"<br />");
				//移动事件
				var mousedown=function(clientX,clientY)
				{
					this.mouseX=clientX;
					this.mouseY=clientY;
					this.pointLeft=parseInt(d.style.left);
					this.pointTop=parseInt(d.style.top);
					//记下
				}
				var mousedrag=function(clientX,clientY)
				{
					var dx=clientX - this.mouseX;
					var dy=clientY - this.mouseY;
					
					//同步连线
					var index=this.indexOfPoints(value);
					if(index==0)
					{
						//调整开始节点不能操作节点的边框
						var p=this.startNode.repoistBlackPoint(new Position(this.pointLeft+dx,this.pointTop+dy));
						dx=p.x-this.pointLeft;
						dy=p.y-this.pointTop;
						d.style.left=p.x;
						d.style.top=p.y;
						
						//移动开始黑点
						var line=this.lines[0];
						var startpoint=new Position(this.points[0].x,this.points[0].y);
						startpoint.x=startpoint.x+dx;
						startpoint.y=startpoint.y+dy;

						var endpoint=this.points[1];
						//调整开始
						startpoint=this.startNode.getlineCrossRect(startpoint,endpoint);
						this.moveline(line,startpoint,endpoint);
						
					}else if(index==this.points.length-1)
					{
						//调整结束节点
						var p=this.endNode.repoistBlackPoint(new Position(this.pointLeft+dx,this.pointTop+dy));
						dx=p.x-this.pointLeft;
						dy=p.y-this.pointTop;
						d.style.left=p.x;
						d.style.top=p.y;
						//移动结束黑点
						var line=this.lines[this.lines.length-1];
						var zuihoup=this.points[this.points.length-1];
						var lastp=this.points[this.points.length-2];
						
						

						var endp=new Position(zuihoup.x+dx,zuihoup.y+dy);
						//调整结束点位置
						endp=this.endNode.getlineCrossRect(endp,lastp);
						this.moveline(line,lastp,endp);
					}else
					{
						d.style.left=this.pointLeft+dx;
						d.style.top=this.pointTop+dy;
						//其他黑点
						var lastline=this.lines[index-1];
						var nextline=this.lines[index];
						var p=this.points[index];
						var lastp=this.points[index-1];
						var nextp=this.points[index+1];
						var curp=new Position(p.x+dx,p.y+dy);
						this.moveline(lastline,lastp,curp);
						this.moveline(nextline,curp,nextp);
						//document.debug.document.write(fromline.style.left+"<br />");
						//fromline.style.left=parseInt(p.x)+dx;
						//fromline.style.top=parseInt(p.y)+dy;

						
					}
				}
				var mouseup=function(clientX,clientY)
				{
					//更改点的位置
					var index=this.indexOfPoints(value);
					this.points[index].x=parseInt(d.style.left)+blackpointSize/2;
					this.points[index].y=parseInt(d.style.top)+blackpointSize/2;
				}
				var drag=new Drag(d);
				d.onmousedown=function(){drag.startDrag(null,mousedown.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
				d.onmousemove=function(){drag.drag(null,mousedrag.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
				d.onmouseup=function(){drag.stopDrag(null,mouseup.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
			}
		);
	},
	removePointBlackMove:function()
	{
		$A(this.PointBlacks).each(
			function(value)
			{
				value.onmousedown=null;
				value.onmousemove=null;
				value.onmouseup=null;
			}
		);
	}
	,
	moveline:function(line,startpoint,endpoint)
	{
		if(this.isFirstPoint(startpoint))
		{
			startpoint=this.startNode.getlineCrossRect(startpoint,endpoint);
			//document.debug.document.write('start<br>')
		}
		if(this.isEndPoint(endpoint))
		{
			endpoint=this.endNode.getlineCrossRect(endpoint,startpoint);
			//document.debug.document.write('end<br>')
		}
		line.style.left=startpoint.x;
		line.style.top=startpoint.y;
		var dx=endpoint.x-startpoint.x;
		var dy=endpoint.y-startpoint.y;
		line.to=dx+","+dy;
	},
	/**
	*是否第一个点
	*/
	isFirstPoint:function(point)
	{
		var first=this.points[0];
		if(first.x==point.x && first.y==point.y)
		{
			return true;
		}else
		{
			return false;
		}
	},
	/**
	*是否最后一个点
	*/
	isEndPoint:function(point)
	{
		var end=this.points[this.points.length-1];
		if(end.x==point.x && end.y==point.y)
		{
			return true;
		}else
		{
			return false;
		}
	},
	/**
	*点在节点位置
	*/
	indexOfPoints:function(point)
	{
		var points=this.points;
		for(var i=0;i<points.length;i++)
		{
			if(point.x==points[i].x && point.y==points[i].y)
			{
				return i;
			}
		}
		return -1;
	},
	isStartOrEnd:function(com)
	{
		//document.debug.document.write(com.id+':');
		if(com.id==this.startNode.id)
		return 'start';
		if(com.id==this.endNode.id)
		return 'end';
	}
	,
	/**
	*选中这个连接弧
	*/
	setSelect:function()
	{
		$A(this.lines).each(
			function(value)
			{
				value.strokecolor="#ff0000";
			}
		);
		$A(this.PointBlacks).each(
			function(value)
			{
				value.style.display="block";
			}
		);
		//文本框选中
		$('link_text'+this.id).focus();
	},
	/**
	*没选中这个连接弧
	*/
	noSelect:function()
	{
		$A(this.lines).each(
			function(value)
			{
				value.strokecolor="#89ccf3";
			}
		);
		$A(this.PointBlacks).each(
			function(value)
			{
				value.style.display="none";
			}
		);
		//文本框选中
		$('link_text'+this.id).blur();
	},
	addEvent:function()
	{
		var othis=this;
		$A(this.lines).each(
			function(value)
			{
				value.onclick=function()
				{
					componentMan.setSelectedObj(othis);
				}
				//添加双击时间
				value.ondblclick=othis.dblfun.bind(othis);
			}
		);
		this.addTextMoveEvent();
		this.setPointBlack();
	},
	remove:function(fromNode)
	{
		//删除小黑点
		$A(this.PointBlacks).each(function(value){$('canvas').removeChild(value)});
		//删除连接弧
		$A(this.lines).each(function(value){$('canvas').removeChild(value)});
		//清楚文本框
		$('canvas').removeChild(this.textDiv);
		//清楚节点的link引用
		var othis=this;
		function removeNodeLink(node)
		{
			if(node==null)
			return;
			var links=node.links;
			for(var i=0;i<links.length;i++)
			{
				if(links[i].id==othis.id)
				{
					break;
				}
			}
			links[i]=links[links.length-1];
			links.pop();
		}
		if(fromNode==null)
		{
			removeNodeLink(this.startNode);
			removeNodeLink(this.endNode);
		}else if(fromNode.id==this.startNode.id)
		{
			//从开始节点过来删除
			removeNodeLink(this.endNode);
		}else if(fromNode.id==this.endNode.id)
		{
			//从结束节点删除
			removeNodeLink(this.startNode);
		}		
		//清空内存对象
		this.PointBlacks=null;
		this.lines=null;
	}
	,
	removeEvent:function()
	{
		var othis=this;
		$A(this.lines).each(
			function(value)
			{
				value.onclick=null;
				value.onDblClick=null;
			}
			
		);
		this.removePointBlackMove();
	},
	dblfun:function()
	{
		othis=this;
		var _value = $('link_text'+this.id).value;
		var busId='${labWfProcessVo.id}';
		var _name=othis.attObj.basic.name;
		ExtModalDialog.show("线路属性","preUpdateLabWfPath.action?labWfPathVo.processId="+busId+"&labWfPathVo.name="+encodeURIComponent(_name)+"&labWfPathVo.ids="+encodeURIComponent(othis.attObj.basic.desc),window,refreshDoc.bind(this),{width:600,height:400,maximizable: false,collapsible: false},othis);
	},
	//获得拐点之前的直线
	getInflexBeforeLines:function()
	{
		var beforelines=new Array();
		if(this.inflexIndex!=null)
		{
			for(var i=0;i<this.inflexIndex;i++)
			{
				beforelines[beforelines.length]=this.lines[i];
			}
		}
		return beforelines;
	},
	//获得拐点之后的直线
	getInflexAfterLines:function()
	{
		var afterlines=new Array();
		if(this.inflexIndex!=null)
		{
			var j=0;
			for(var i=this.inflexIndex;i<this.lines.length;i++)
			{
				afterlines[j++]=this.lines[i];
			}
		}
		return afterlines;
	},
	//算出flex之前的点
	getBeforeFlexpoint:function(inflexId)
	{
		//包括inflex点
		for(var i=0;i<this.pointIds.length;i++)
		{
			if(this.pointIds[i]==inflexId)
			{
				index=i;
				break;
			}	
		}
		
		var result=new Array();
		for(var i=0;i<index;i++)
		{
			result[i]=new Position(this.points[i].x,this.points[i].y);
		}
		return result;
	},
	getAfterFlexpoint:function(inflexId)
	{
		//不包括inflex点
		for(var i=0;i<this.pointIds.length;i++)
		{
			if(this.pointIds[i]==inflexId)
			{
				index=i;
				break;
			}	
		}
		//document.debug.document.write("index:"+index);
		var result=new Array();
		var j=0
		for(var i=index;i<this.points.length;i++)
		{
			result[j++]=new Position(this.points[i].x,this.points[i].y);
			
		}
		return result;
	}
	,
	getPointByPointId:function(pointId)
	{

		for(var i=0;i<this.pointIds.length;i++)
		{
			
			if(this.pointIds[i]==pointId)
			{
				index=i;
				break;
			}	
		}
		return new Position(this.points[index].x,this.points[index].y);
	},
	getInflexIndex:function()
	{
		for(var i=0;i<this.pointIds.length;i++)
		{
			if(this.pointIds[i]==this.inflexId)
			{
				return i;
			}	
		} 
	},
	/**
	*格式
	*/
	toXML:function(doc)
	{
		var node=doc.createElement("LINK");
		node.setAttribute("ID",this.id);
		node.setAttribute("STARTID",this.startNode.id);
		node.setAttribute("ENDID",this.endNode.id);
		node.setAttribute("DBID",this.dbid);
		var pointBlacks=this.PointBlacks;
		var poses=new Array();
		for(var i=0;i<pointBlacks.length;i++)
		{
			var pos=new Position();
			pos.x=parseInt(pointBlacks[i].style.left)+blackpointSize/2
			pos.y=parseInt(pointBlacks[i].style.top)+blackpointSize/2
			poses[poses.length]=pos;
		}
		var s=poses.join("-");
		node.setAttribute("POINTS",s);

        //pointIds
        var linktext=$("link_text"+this.id);
        var textdiv=(linktext.parentNode);
        var p=parseInt(textdiv.style.left)+","+parseInt(textdiv.style.top);
        node.setAttribute("TEXTPOS",p);

        node.appendChild(this.attObj.toXML(doc));
		return node;
	}
}
//拐点
var inflexion=Class.create();
inflexion.prototype={
	initialize:function(type,link,inflexId)
	{
		//类型1 拐出 类型2 拐入
		this.type=type;
		this.links=new Array();
		//将初始化的连接弧放入到拐点
		this.links[this.links.length]=link;
		//点的ID号
		this.inflexId=inflexId;
	},
	//添加一条link
	addlink:function(link)
	{
		var has=false;
		for(var i=0;i<this.links.length;i++)
		{
			if(link==this.links[i])
			{
				has=true;
				break;
			}
		}
		if(!has)
		{
			this.links[this.links.length]=link;
		}
	}
}

</script>
<script>
//tagmanager.js
/**
 * <pre>
 * 	工厂类负责创建各个元件  
 * </pre>
 */
var Factory = {
	/**
	*x y左上角的位置
	*/
	createComponent:function(x,y,type,id,rootObj)
	{
		var pos=new Position(parseInt(x),parseInt(y));
	
        switch(type)
			{
				case NodeType.Node:
				var com=new Node(pos,type,id);
				//componentMan.addNode(start);
				//Tree.addNode(start.id,start.name);
				if(rootObj!=''&&typeof(rootObj)!='undefined'&&rootObj.title!=''&&typeof(rootObj.title)!='undefined'){
					com.updateText(rootObj.title);
				}
				break;
				case NodeType.Start:
				var num=componentMan.getComponentNum(NodeType.Start);
				//开始节点只能有一个
				if(num<1)
				{
					var com=new Start(pos,type,id);
				}else
				{
					var com=null;
				}
				break;
				case NodeType.End:
				//结束节点只能有一个
				var num=componentMan.getComponentNum(NodeType.End);
				if(num<1)
				{
					var com=new End(pos,type,id);
				}else
				{
					var com=null;
				}
				break;
				case NodeType.AndFork:
				var com=new AndFork(pos,type,id);
				break;
				case NodeType.AndJoin:
				var com=new AndJoin(pos,type,id);
				break;
				case NodeType.OrFork:
				var com=new OrFork(pos,type,id);
				break;
				case NodeType.OrJoin:
				var com=new OrJoin(pos,type,id);
				break;
				case NodeType.XorFork:
				var com=new XorFork(pos,type,id);
				break;
				case NodeType.XorJoin:
				var com=new XorJoin(pos,type,id);
				break;
                case NodeType.ChildWorkFlow:
                var com=new ChildWorkFlow(pos,type,id);
                break;
                case NodeType.WaitingNode:
                var com=new WaitingNode(pos,type,id);
                break;
            }
			
			if(com!=null)
			componentMan.addNode(com);
			return com;
	},
	createLink:function(startNode,endNode,points,id)
	{
		var startType=startNode.type;
		var endType=endNode.type;

        var creatable=true;
		//link的结束类型不能是开始节点
		if(endType==NodeType.Start)
		{
			return;
		}
		//link的开始类型不能是结束节点
		if(startType==NodeType.End)
		{
			return;
		}
		if(startType==NodeType.Start)
		{
			//开始节点后续节点不能为汇聚节点
			if(endType==NodeType.XorJoin || endType==NodeType.OrJoin || endType==NodeType.AndJoin)
			{
				return;
			}
			//开始节点必须只能有一个后续节点
			if(componentMan.hasStartNodeLink(NodeType.Start))
			{
				return;
			}
		}
		if(endType==NodeType.End)
		{
			//结束节点允许的前趋节点只能为汇聚节点、任务节点和开始节点
			if(startType==NodeType.OrFork || startType==NodeType.AndFork)
			{
				return;
			}
		}
		//发散节点
		if(startType==NodeType.AndFork || startType==NodeType.OrFork ||startType==NodeType.XorFork)
		{
			//后续节点不允许为汇聚节点
            if(endType==NodeType.XorJoin || endType==NodeType.OrJoin || endType==NodeType.AndJoin)
			{
				return;
			}
		}
		//汇聚节点
		if(startType==NodeType.AndJoin || startType==NodeType.OrJoin || startType==NodeType.XorJoin)
		{
			//只能有一个后续节点
			var num=startNode.getBeStartNodeNum();
			if(num>=1)
			return;
		}
		if(creatable)
		{
			/*var pointIds=new Array();

			for(var i=0;i<points.length;i++)
			{
				pointIds[pointIds.length]=idGen.getNextId();
			}*/
            var link=new Link(startNode,endNode,points,id);
            //alert("addscuess")
            //link.attObj.basic.name=link.id;
            componentMan.addLink(link);
            return link;
        }
	}
}
/**
 * <pre>
 * 	元件管理器，元件对象，保存，恢复  
 * </pre>
*/
var ComponentMan = Class.create();
ComponentMan.prototype= {
	initialize:function()
	{
		this.nodeList=new Array();
		this.linkList=new Array();
		//拐点的集合
		this.inflexions=new Array();
		//选中的节点
		this.selectedObj=null;
		//名字
		this.name=null;
	},
	getObjById:function(id)
	{
		for(var i=0;i<this.nodeList.length;i++)
		{
			if(this.nodeList[i].id==id)
			{
				return this.nodeList[i];
			}
		}
		for(var i=0;i<this.linkList.length;i++)
		{
			if(this.linkList[i].id==id)
			{
				return this.linkList[i];
			}
		}
		return null;
	}

    ,
    /**
	*添加一个节点
	*@param node 节点对象
	*/
	addNode:function(node)
	{
		
		this.nodeList[this.nodeList.length]=node;
		//alert("addNode:function"+node.type);
		this.setSelectedObj(node);
		
	},
	addLink:function(link)
	{
		this.linkList[this.linkList.length]=link;
		this.setSelectedObj(link);
	},
	deleteSelectObj:function()
	{
		if(this.selectedObj==null)
		{
			return;
		}
		//删除画布
		this.selectedObj.remove();
		if(this.selectedObj.type==NodeType.Link)
		{
			//删除迁移
			this.removeLink(this.selectedObj);
		}else
		{
			//删除节点
			this.removeNode(this.selectedObj);
		}
		//清空选中项
		this.selectedObj=null;
	}
	,
	removeLink:function(obj)
	{
		var select=null;
		for(var i=0;i<this.linkList.length;i++)
		{
			if(this.linkList[i].id==obj.id)
			{
				select=this.linkList[i];
				break;
			}
		}
		if(select!=null)
		{
			var t=this.linkList[this.linkList.length-1];
			this.linkList[this.linkList.length-1]=select;
			this.linkList[i]=t;
			this.linkList.pop();
		}
	},
	removeNode:function(obj)
	{
		//清空节点下迁移
		var othis=this;
		$A(obj.links).each(function(value){othis.removeLink(value);});
		//清空节点
		var select=null;
		for(var i=0;i<this.nodeList.length;i++)
		{
			if(this.nodeList[i].id==obj.id)
			{
				select=this.nodeList[i];
				break;
			}
		}
		if(select!=null)
		{
			var t=this.nodeList[this.nodeList.length-1];
			this.nodeList[this.nodeList.length-1]=select;
			this.nodeList[i]=t;
			this.nodeList.pop();
		}
	}
	,
	addInflexion:function(inflexion)
	{
		if(!this.hasflex(inflexion.inflexId))
		this.inflexions[this.inflexions.length]=inflexion;
	}
	,
	setSelectedObj:function(obj)
	{
		
		
		
		if(this.selectedObj==obj)
		{
			obj.setSelect();

			
			return;
		}
		
		if(this.selectedObj!=null)
		{
			
						this.selectedObj.noSelect();
	
		}

		this.selectedObj=obj;
		if(obj!=null)
		obj.setSelect();
		
	},
	getSelectObj:function()
	{
		if(this.selectedObj!=null)
		{
			return this.selectedObj;
		}
		return null;
	}
	,
	/**
	*返回包含着个点的节点，如果没有返回null
	*/
	getNodeByPoint:function(point)
	{
		return $A(this.nodeList).find(function (value){return value.pointInElement(point);});
	},
	/**
	*返回节点个数
	*/
	getComponentNum:function(type)
	{
		var num=0;
		$A(this.nodeList).each(
			function(value)
			{
				if(value.type==type)
				{
					num++;
				}
			}
		);
		return num
	},
	hasStartNodeLink:function(type)
	{
		//开始节点是否有连接弧
		var has=false;
		$A(this.linkList).each(
			function(value)
			{
				if(value.startNode.type==type)
				{
					has=true;				
				}
			}
		);
		return has;
	},
	getflex:function(flexid)
	{
		for(var i=0;i<this.inflexions.length;i++)
		{
			if(this.inflexions[i].inflexId==flexid)
			{
				return this.inflexions[i];
			}
		}
		return null;
	}
	,
	hasflex:function(flexid)
	{
		var has=false;
		for(var i=0;i<this.inflexions.length;i++)
		{
			if(this.inflexions[i].inflexId==flexid)
			{
				has=true;
				break;
			}
		}
		return has;
	}
	,
	//获得小黑点
	getBlackPoint:function(mousepoint)
	{

		var x=mousepoint.x;
		var y=mousepoint.y;
		//document.debug.document.write(x+":"+y+"<br />")
		var blackpoint=null;
		var link=null;
		var pointId=null;
		outerloop:
		for(var i=0;i<this.linkList.length;i++)
		{
			var pointBlacks=this.linkList[i].PointBlacks;
			
			for(var j=0;j<pointBlacks.length;j++)
			{
				var top=parseInt(pointBlacks[j].style.top);
				var left=parseInt(pointBlacks[j].style.left);
				//document.debug.document.write("point:"+top+":"+left+"<br />")
				if(x>=left && x<=left+blackpointSize && y>=top && y<=top+blackpointSize)
				{
					blackpoint=pointBlacks[j];
					link=this.linkList[i];
					pointId=link.pointIds[j];
					break outerloop;
				}
			}
		}
		if(blackpoint!=null)
		{
			return {'blackpoint':blackpoint,'link':link,'pointId':pointId};
		}else
		{
			return null;
		}		
	},
	saveAsXML:function()
	{
		var doc = new ActiveXObject("Microsoft.XMLDOM");

		var workflow=doc.createElement("WORKFLOW");
		workflow.setAttribute("BPDID","0001");
		var nodes=doc.createElement("NODES");
		for(var i=0;i<this.nodeList.length;i++)
		{
			//alert(this.nodeList[i].id+":::"+this.nodeList[i].dbid);
            nodes.appendChild(this.nodeList[i].toXML(doc));
		}
		workflow.appendChild(nodes);
		var links=doc.createElement("LINKS");
		for(var i=0;i<this.linkList.length;i++)
		{
			links.appendChild(this.linkList[i].toXML(doc));
		}
		workflow.appendChild(links);
		doc.appendChild(workflow);
		return doc.xml;
	},
	//从xml取到DBID赋值
	initDBID:function(doc)
	{
		var nodes=doc.getElementsByTagName("NODE");
		for(var i=0;i<nodes.length;i++)
		{
			var id=nodes.item(i).getAttribute("ID");
			var dbid=nodes.item(i).getAttribute("DBID");
            //alert("XML:"+id+"::::"+dbid);
            var component=this.getObjById(id);
            //alert("aferXML:"+component.id);
            if(component!=null)
			{
                component.dbid=dbid;
				var type=nodes.item(i).getAttribute("TYPE");
				if(type==NodeType.Node)
				{
					//节点设置taskdefID
					component.taskdefId=nodes.item(i).getAttribute("TASKDEFID");
				}
			}
            //alert("aferXML2:"+component.id+"::::"+component.dbid);
        }
		var links=doc.getElementsByTagName("LINK");
		for(var i=0;i<links.length;i++)
		{
			var id=links.item(i).getAttribute("ID");
			var	dbid=links.item(i).getAttribute("DBID");
			var component=this.getObjById(id);
            //alert(id+":::"+component.type);
            if(component!=null)
			{
				component.dbid=dbid;
			}
		}
	},
    getObjByDBID:function(dbid)
    {
        for(var i=0;i<this.nodeList.length;i++)
		{
			if(this.nodeList[i].dbid==dbid)
			{
				return this.nodeList[i];
			}
		}
		for(var i=0;i<this.linkList.length;i++)
		{
			if(this.linkList[i].dbid==dbid)
			{
				return this.linkList[i];
			}
		}
		return null;
    }
    ,
    //从XML中加载
    initFromXML:function(doc)
    {
        var documentroot = doc.documentElement;
		
        var nodes = documentroot.selectSingleNode("NODES");
			
			
        var nodeList = nodes.childNodes;
		
        for (var i = 0; i < nodeList.length; i++)
        {
            //处理每个节点
            var node = nodeList.item(i);
            var id = node.getAttribute("ID");
            var left = parseInt(node.getAttribute("LEFT"));
            var top = parseInt(node.getAttribute("TOP"));
            var type = node.getAttribute("TYPE");
            var n = Factory.createComponent(left, top, type, id);
            n.attObj.initattfromxml(node);
            if(type==NodeType.Node)
            {
                //处理文本框
                n.updateText(n.attObj.basic.name);
            }
            if(type==NodeType.ChildWorkFlow)
            {
                //处理文本框
                n.updateText(n.attObj.basic.name);
            }
        }
		
        var links = documentroot.selectSingleNode("LINKS");
		
        var linkList = links.childNodes;
		
        //alert("连接弧的长度:"+linkList.length);
        for(var i=0;i<linkList.length;i++)
        {
            var link = linkList.item(i);
            var id = link.getAttribute("ID");
            var startId=link.getAttribute("STARTID");
            var endId=link.getAttribute("ENDID");
            var pointstr=link.getAttribute("POINTS");

            var textstyle=link.getAttribute("TEXTPOS");
            var textleft=textstyle.split(',')[0];
            var texttop=textstyle.split(',')[1];

            var pointarr=new Array();
            var temp=pointstr.split("-");
            for(var j=0;j<temp.length;j++)
            {
                var p=temp[j].split(",");
                var po=new Position();
                po.x = parseInt(p[0]);
                po.y = parseInt(p[1]);
                pointarr[pointarr.length]=po;
            }
            var startNode=this.getObjById(startId);
            var endNode=this.getObjById(endId);
            var l=Factory.createLink(startNode,endNode,pointarr,id);
            l.attObj.initattfromxml(link);

            //处理text的位置
            $("link_text"+id).parentNode.style.left=parseInt(textleft);
            $("link_text"+id).parentNode.style.top=parseInt(texttop);
            l.updateText(l.attObj.basic.name);
        }
        var tools = document.all.tools;

        tools[0].fireEvent("onclick");
	
        this.initDBID(doc);
	
    }
}


</script>
<script>
/**
 * 功能说明:
 *多个组件的拖动
 */
 
/**
 * <pre>
 * 	多个组件的拖动类
 * </pre>
 */
var MultiMove=Class.create();
MultiMove.prototype={
	//初始化函数
	initialize:function()
	{
		//选中的节点
		this.selectObjs=[];
		//没有移动之前的初始坐标
		this.beDragPoits=new col.JSMap();
		//选中节点之间的连接弧
		this.betweenSelLinks=null;
		//开始节点的连接弧
		this.startSelLink=null;
		//结束节点的连接弧
		this.endSelLink=null;
		//所有的连接弧
		this.alllink=null;
		//这个选中所有节点框框的左上角的坐标
		this.leto=new Point(10000,10000);
		this.rido=new Point(0,0);
		//用于标志选中节点是否移动，在mouseup后标志为true,用来标志移动后的onclick事件
		this.moveable=false;
	},
	clean:function()
	{
		$A(this.selectObjs).each(
			function (value)
			{
				value.notSelected();
				value.addDragEvent();
			}
		);
		//选中的节点
		this.selectObjs=[];
		//没有移动之前的初始坐标
		this.beDragPoits=new col.JSMap();
		//选中节点之间的连接弧
		this.betweenSelLinks=null;
		//开始节点的连接弧
		this.startSelLink=null;
		//结束节点的连接弧
		this.endSelLink=null;
		//所有的连接弧
		this.alllink=null;
		//这个选中所有节点框框的左上角的坐标
		this.leto=new Point(10000,10000);
		this.rido=new Point(0,0);
	}
	,
	/**
	*获得选中节点之间的连接弧
	*获得选中节点开始节点的连接弧
	*获得选中节点结束节点的连接弧
	*/
	initLinks:function()
	{
		var othis=this;
		this.betweenSelLinks=new col.JSSet();
		//alert(this.id+"  this.id");
		this.startSelLink=new col.JSSet();
		this.endSelLink=new col.JSSet();
		this.alllink=new col.JSSet();
		$A(this.selectObjs).each(function(value)
		{
			var links=value.links;
			var oothis=othis;
			$A(links).each(function(link)
			{
				var startNode=link.startNode;
				var endNode=link.endNode;
				var t1=othis.nodeInArray(startNode);
				var t2=othis.nodeInArray(endNode);
				if(t1==true && t2==true)
				{
					oothis.betweenSelLinks.add(link);
				}else if(t1==true && t2==false)
				{
					oothis.startSelLink.add(link);
				}else if(t1==false && t2==true)
				{
					oothis.endSelLink.add(link);
				}
				oothis.alllink.add(link);
			});
		});
		
	},
	/**
	*获取选中图元的框
	*/
	initRect:function()
	{
		var othis=this;
		this.leto=new Point(10000,10000);
		this.rido=new Point(0,0);
		$A(this.selectObjs).each(function(value)
		{
		//初始化选中框左上角和右下角坐标
			if(value.leto.x<othis.leto.x)
			{
				othis.leto.x=value.leto.x;
			}
			if(value.leto.y<othis.leto.y)
			{
				othis.leto.y=value.leto.y;
			}
			if(value.leto.x+NodeSize.WIDTH>othis.rido.x)
			{
				othis.rido.x=value.leto.x+NodeSize.WIDTH;
			}
			if(value.leto.y+NodeSize.HEIGHT>othis.rido.y)
			{
				othis.rido.y=value.leto.y+NodeSize.HEIGHT;
			}
		});
	}
	,
	/**
	*节点是否在选中的数组里
	*/
	nodeInArray:function(node)
	{
		var t=$A(this.selectObjs).find(function(value){return value.id==node.id});
		if(t==null)
		{
			return false
		}else
		{
			return true;
		}
	}
	,
	/**
	*添加多选事件
	*/
	addEvent:function()
	{
		var othis=this;
		$A(this.selectObjs).each(function (value)
		{
			var t=othis.addOneObjEvent.bind(othis);
			t(value);
		}
		);
	},
	addOneObjEvent:function(value)
	{
		var othis=this;
		var drag=new Drag(value.element);
		value.element.onmousedown=function(){drag.startDrag(null,othis.objClick.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		value.element.onmousemove=function(){drag.drag(null,othis.objDrag.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		value.element.onmouseup=function(){drag.stopDrag(null,othis.objUp.bind(othis),null);event.cancelBubble=true;event.returnValue=false;};
		
	}
	,
	addselectObj:function(obj)
	{
		if(!this.nodeInArray(obj))
		this.selectObjs[this.selectObjs.length]=obj;
		multiMove.addOneObjEvent(obj);
	}
	,
	/**
	*开始移动时操作
	*/
	objClick:function(clientX,clientY)
	{
		this.mouseX=parseInt(clientX);
		this.mouseY=parseInt(clientY);
		//alert('aaa');
		var othis=this;
		$A(this.selectObjs).each(function(value)
		{
			var startX=parseInt(value.element.style.left);
			var startY=parseInt(value.element.style.top);
			var point=new Point(startX,startY);
			othis.beDragPoits.put(value.id,point);
		}
		);
		//记录下开始箭头的位置
		//var t=multiMove.initLinks.bind(this);
		//t();
		multiMove.initLinks();
		this.initRect();
		//alert("leto:"+this.leto.x+","+this.leto.y+":::::"+this.rido.x+","+this.rido.y)
		$A(this.startSelLink.arr).each(
			function(link)
			{
				link.mousedownX=parseInt(value.line.style.left);
				link.mousedownY=parseInt(value.line.style.top);
			}
		);
		//alert("leto:"+this.leto.x+","+this.leto.y+"::::rido:"+this.rido.x+","+this.rido.y);
		//File.test("click leto:"+this.leto.x+","+this.leto.y+"::::rido:"+this.rido.x+","+this.rido.y);
	},
	/**
	*移动时的操作
	*/
	objDrag:function(clientX,clientY)
	{
		var dx=clientX - this.mouseX;
		var dy=clientY - this.mouseY;
		var isKang=false;
		var othis=this;
		var dx=clientX - othis.mouseX;
		var dy=clientY - othis.mouseY;
		for(var i=0;i<this.selectObjs.length;i++)
		{
			
			var startX=othis.beDragPoits.get(this.selectObjs[i].id).x;
			var startY=othis.beDragPoits.get(this.selectObjs[i].id).y;
			
			var left = startX + dx;
 	  		var top =  startY + dy;
 	  		//调整left top让它只能在画布上移动
 	  		left=this.leto.x+dx<0?startX-this.leto.x:left;
 	  		top=this.leto.y+dy<0?startY-this.leto.y:top;
 	  		left=this.rido.x+dx>CanvasSize.WIDTH?startX+CanvasSize.WIDTH-this.rido.x:left;
 	  		top=this.rido.y+dy>CanvasSize.HEIGHT?startY+CanvasSize.HEIGHT-this.rido.y:top;

			
			//alert(left+"=="+top);
			this.selectObjs[i].element.style.left=left;
			this.selectObjs[i].element.style.top=top;
			//刷新左上角的点
 	  		this.selectObjs[i].leto.x=parseInt(left);
 	  		this.selectObjs[i].leto.y=parseInt(top);
		}
		//File.test("leto:之间"+this.betweenSelLinks.arr.length+",开始"+this.startSelLink.arr.length+"::::结束"+this.endSelLink.arr.length);
		//移动节点之间的连接弧
		//this.betweenSelLinks.arr
 	  	$A(this.betweenSelLinks.arr).each(
 	  		function(value)
 	  		{
 	  			//刷新开始点和结束点
	 	  		var ddx=dx;
	 	  		var ddy=dy;
	 	  		ddx=othis.leto.x+ddx<0?0-(othis.leto.x):ddx;
 	  			ddy=othis.leto.y+ddy<0?0-othis.leto.y:ddy;
 	  			ddx=othis.rido.x+ddx>CanvasSize.WIDTH?CanvasSize.WIDTH-othis.rido.x:ddx;
 	  			ddy=othis.rido.y+ddy>CanvasSize.HEIGHT?CanvasSize.HEIGHT-othis.rido.y:ddy;
	 	  		value.line.style.left= parseInt(value.startPoit.x)+ ddx;
 	  			value.line.style.top=parseInt(value.startPoit.y) + ddy;
	 	  		//var tdx=parseInt(value.endNode.element.leto.x)+NodeSize.WIDTH/2-parseInt(value.line.style.left);
 	  			//var tdy=parseInt(value.endNode.element.leto.y)+NodeSize.HEIGHT/2-parseInt(value.line.style.top);
 	  			//value.line.to=tdx+','+tdy;
 	  		}
 	  	);
 	  	
 	  	//移动开始节点的连接弧
 	  	//this.startSelLink.arr
 	  	$(this.startSelLink.arr).each(
 	  		function(value)
 	  		{
 	  			//alert(othis.mousedownX+"");
 	  			dx=othis.leto.x+dx<0?0-(othis.leto.x):dx;
 	  			dy=othis.leto.y+dy<0?0-othis.leto.y:dy;
 	  			dx=othis.rido.x+dx>CanvasSize.WIDTH?CanvasSize.WIDTH-othis.rido.x:dx;
 	  			dy=othis.rido.y+dy>CanvasSize.HEIGHT?CanvasSize.HEIGHT-othis.rido.y:dy;
 	  			value.line.style.left= parseInt(value.startPoit.x)+ dx;
 	  			value.line.style.top=parseInt(value.startPoit.y) + dy;
 	  			var tdx=value.endPoit.x-parseInt(value.line.style.left);
 	  			var tdy=value.endPoit.y-parseInt(value.line.style.top);
 	  			value.line.to=tdx+','+tdy;
 	  		}
 	  	);
 	  	//移动结束节点的连接弧
 	  	
 	  	$(this.endSelLink.arr).each(
 	  		function(value)
 	  		{
 	  			dx=othis.leto.x+dx<0?0-(othis.leto.x):dx;
 	  			dy=othis.leto.y+dy<0?0-othis.leto.y:dy;
 	  			dx=othis.rido.x+dx>CanvasSize.WIDTH?CanvasSize.WIDTH-othis.rido.x:dx;
 	  			dy=othis.rido.y+dy>CanvasSize.HEIGHT?CanvasSize.HEIGHT-othis.rido.y:dy;
 	  			var tdx=value.endPoit.x-value.startPoit.x+dx;
 	  			var tdy=value.endPoit.y-value.startPoit.y+dy;
 	  			value.line.to=tdx+','+tdy;
 	  		}
 	  	);
 	  	//File.test("changge moveable true");
		this.moveable=true;
	}
}

</script>
<script>
//tagtoolbarjs
/**W
 *
 * 功能说明:
 *1)组件栏的操作，移动，最小化
 */
var ToolBar = Class.create();
ToolBar.prototype = {
	/**
	*组件栏的初始化
	*/
	initialize:function()
	{
		//Canvas.addComponentEvent(NodeType.Node);
		var tools=document.all.tools;
		this.click=null;
		var othis=this;
		$A(tools).each(
			function(value)
			{
				value.className="tool "+value.nodetype;
				value.onmouseover=function (){value.className="tool "+value.nodetype+"_over"};
				value.onmouseout=function (){value.className="tool "+value.nodetype};

				var toolmouseover=function(){this.className="tool "+this.nodetype+"_over"}
				var toolmouseout=function(){this.className="tool "+this.nodetype};
				if(value.nodetype=="move")
				{
					
					value.onclick=function()
					{
						value.blur();
						//处理点击的css变化
						if(value!=othis.click)
						{
							value.className="tool "+value.nodetype+"_on";
							value.onmouseover=null;
							value.onmouseout=null;
							
							if(othis.click!=null)
							{
								othis.click.className="tool "+othis.click.nodetype;
								othis.click.onmouseover=toolmouseover.bind(othis.click);
								othis.click.onmouseout=toolmouseout.bind(othis.click);
							}
							othis.click=value;
						}
						Canvas.addMoveEvent();
					}
				}else if(value.nodetype=="Link")
				{
					value.onclick=function()
					{
						value.blur();
						if(value!=othis.click)
						{
							value.className="tool "+value.nodetype+"_on";
							value.onmouseover=null;
							value.onmouseout=null;
							if(othis.click!=null)
							{
								othis.click.className="tool "+othis.click.nodetype;
								othis.click.onmouseover=toolmouseover.bind(othis.click);
								othis.click.onmouseout=toolmouseout.bind(othis.click);
							}
							othis.click=value;
						}
						Canvas.addLinkEvent();
						
					}
				}else
				{
					value.onclick=function()
					{
						value.blur();
						if(value!=othis.click)
						{
							value.className="tool "+value.nodetype+"_on";
							value.onmouseover=null;
							value.onmouseout=null;
							if(othis.click!=null)
							{
								//document.debug.document.write("tool "+othis.click.nodetype+"_over"+",");
								othis.click.className="tool "+othis.click.nodetype;
								othis.click.onmouseover=toolmouseover.bind(othis.click);
								othis.click.onmouseout=toolmouseout.bind(othis.click);
							}
							othis.click=value;
						}
						Canvas.addComponentEvent(value.nodetype,value);
						
					}
				}
			}
		);
	}
}

var Canvas={
	/**
	*添加元件，onclick触发
	*/
	addComponentEvent:function(type,rootObj)
	{
		/*把其他事件禁用*/
		$('canvas').onmousedown=null;
		$('canvas').onmouseover=null;
		$('canvas').onmouseup=null;

		//首先需要把节点的鼠标事件停掉
		$A(componentMan.nodeList).each(
			function(value)
			{
				value.removeEvent();
			}
		);
		$A(componentMan.linkList).each(
			function(value)
			{
				value.removeEvent();
			}
		);
		//定义鼠标的形状
		$('canvas').className="canvas "+type+"_cur";
		//点击画布的操作
		$('canvas').onclick=function()
		{
			//滚动条的位置
			
			var scroll=getScroll();
		  
			var x=Utils.get18Int(event.clientX-NodeSize[type].w/2-CanvasSize.LEFT+scroll.l);
			var y=Utils.get18Int(event.clientY-NodeSize[type].h/2-CanvasSize.TOP+scroll.t);
			//处理超过画布定位到边上 不能超过
			x=x<0?0:x;
			y=y<0?0:y;
			x=x+NodeSize[type].w>CanvasSize.WIDTH?CanvasSize.WIDTH-NodeSize[type].w:x;
			y=y+NodeSize[type].h>CanvasSize.HEIGHT?CanvasSize.HEIGHT-NodeSize[type].h:y;
			//alert(x+"===="+y);
			var leto=new Position(x,y);
			Factory.createComponent(x,y,type,null,rootObj);
			//变成选择状态
			$('tools').fireEvent("onclick");
			
		};
	},
	addLinkEvent:function()
	{
		//首先需要把节点的鼠标事件停掉
		$A(componentMan.nodeList).each(
			function(value)
			{
				value.removeEvent();
			}
		);
		$A(componentMan.linkList).each(
			function(value)
			{
				value.removeEvent();
			}
		);
		$('canvas').onclick=null;

		$('canvas').className="canvas Link_cur";
		var startNode=null;
		var endNode=null;
		var lines=new Array();
		var link_points=new Array();
		var moveable=false;
		
		
		var blackpoint=null;
		
		//通用的函数
		//清空虚线，并初始化虚线的全局变量
		var clearDash=function()
		{
			startNode=null;
			endNode=null;
			link_points=new Array();
			$A(lines).each(
				function(value)
				{
					$('canvas').removeChild(value);
				}
			);
			lines=new Array();
			blackpoint=null;
		};
		var createDash=function(startx,starty,inflexpos)
		{
			if(inflexpos==null)
			pos=new Position(startx,starty);
			else
			pos=inflexpos;
			link_points[link_points.length]=pos;
			line=document.createElement("v:line");
			//line.strokecolor="#";
			line.style.position="absolute";
			line.style.left=startx;
			line.style.top=starty;
			line.style.zIndex='10';
			line.from="0,0";
			line.innerHTML="<v:stroke dashstyle=\"Dash\"/>";
			$('canvas').appendChild(line);
			lines[lines.length]=line;
			moveable=true;
		};
		
		$('canvas').onmousedown=function()
		{
			startx=event.clientX;
			starty=event.clientY;
			//alert("x:"+startx+"y:"+starty);
			var scroll=getScroll();
			startx=startx-CanvasSize.LEFT+scroll.l;
			starty=starty-CanvasSize.TOP+scroll.t;

			var pos=new Position(startx,starty);

			var node=componentMan.getNodeByPoint(pos);

			if(node==null)
			{
				if(link_points.length!=0)
				{
					//在中间出现折线
					createDash(startx,starty);
				}
				//判断拐点
				bp=componentMan.getBlackPoint(pos);
				//document.debug.document.write(blcakpoint);
				if(bp!=null && blackpoint==null)
				{
					//blackpoint
					blackpoint=bp;
					//第一次表是流出
					if(link_points.length==0)
					{
						var beforePoints =  blackpoint.link.getBeforeFlexpoint(blackpoint.pointId);
						link_points.addAll(beforePoints);
						startNode=blackpoint.link.startNode;
						createDash(startx,starty,blackpoint.link.getPointByPointId(blackpoint.pointId));
					}
					
				}
			}else
			{
				//点击到开始节点
				if(startNode==null)
				{
					startNode=node;
				}
				createDash(startx,starty);
			}
			event.cancelBubble=true;
			event.returnValue=false;
		};
		$('canvas').onmousemove=function()
		{
			if(lines.length!=0 && moveable)
			{
				var scroll=getScroll();
				var x=event.clientX;
				var y=event.clientY;
				//注意相对位置
				x=x-CanvasSize.LEFT+scroll.l;
				y=y-CanvasSize.TOP+scroll.t;
				var dx=x-startx;
				var dy=y-starty;
				//alert(lines.length);
				lines[lines.length-1].to=dx+","+dy;	
			}
			event.cancelBubble=true;
			event.returnValue=false;
		};
		$('canvas').onmouseup=function()
		{
			//moveable=false;
			startx=event.clientX;
			starty=event.clientY;
			//alert("x:"+startx+"y:"+starty);
			var scroll=getScroll();
			startx=startx-CanvasSize.LEFT+scroll.l;
			starty=starty-CanvasSize.TOP+scroll.t;

			var pos=new Position(startx,starty);

			var node=componentMan.getNodeByPoint(pos);
			
			if(node==null && lines.length==1)
			{
				//第一次的松手事件，需要创建一个新的折线
				bp2=componentMan.getBlackPoint(pos);
				if(bp2!=null && blackpoint==null)
				{
					var beforePoints =  bp2.link.getAfterFlexpoint(bp2.pointId);
					link_points.addAll(beforePoints);
					endNode=bp2.link.endNode;
					//document.debug.document.write("节点个数:"+link_points.length);
					Factory.createLink(startNode,endNode,link_points);
					clearDash();
					//变成选择状态
					$('tools').fireEvent("onclick");
					return;
				}
				
				createDash(startx,starty);
			}else
			{
				if(node!=null)
				{
					//到达结束节点
					endNode=node;
					if(startNode==null)
					{
						clearDash();
						return;
					}
					if(endNode.id==startNode.id)
					{
						//开始节点和结束节点重复
						clearDash();
						return;
					}else
					{
						if(lines.length==1)
						{
							//处理特殊情况，就是没有折线
							link_points[link_points.length]=pos;
						}
						if(blackpoint!=null)
						{
							//拐点
							//document.debug.document.write("blackpoint.pointId:"+blackpoint.pointId+"<br />");
							if(!componentMan.hasflex(blackpoint.pointId))
							{
								var inflex=new inflexion(1,blackpoint.link,blackpoint.pointId);
								componentMan.addInflexion(inflex);
							}else
							{
								var inflex=componentMan.getflex(blackpoint.pointId);
								inflex.addlink(inflex);
							} 
							Factory.createLink(startNode,endNode,link_points,blackpoint.pointId,1);
						}else
						{
							Factory.createLink(startNode,endNode,link_points);
						}
						clearDash();
						//变成选择状态
						$('tools').fireEvent("onclick");
						return;
					}
				}else
				{

					if(startNode==null)
					{
						clearDash();
						return;
					}
					bp2=componentMan.getBlackPoint(pos);
					if(bp2!=null && blackpoint.pointId==bp2.pointId)
					{
						var beforePoints =  bp2.link.getAfterFlexpoint(bp2.pointId);
						link_points.pop();
						link_points.addAll(beforePoints);
						endNode=bp2.link.endNode;
						//document.debug.document.write("节点个数:"+link_points.length);
						Factory.createLink(startNode,endNode,link_points);
						clearDash();
						//变成选择状态
						$('tools').fireEvent("onclick");
						return;
					}
				}
			}
		}
		//双击清空虚线
		$('canvas').ondblclick=function()
		{
			clearDash();
		}

	},
	addMoveEvent:function()
	{
		//把其他事件禁用
		$('canvas').onmousedown=null;
		$('canvas').onmouseover=null;
		$('canvas').onmouseup=null;
		$('canvas').onclick=null;
		//定义鼠标的形状
		$('canvas').className="canvas move_cur";
		//alert(""+componentMan.nodeList.length);
		$A(componentMan.nodeList).each(
			function(value)
			{
				value.addEvent();
			}
		);
		$A(componentMan.linkList).each(
			function(value)
			{
				value.addEvent();
			}
		);
	}
}
</script>

<script>

//tag dragjs


/**
 * 功能说明:
 *1)负责组件的移动
 */
 var Drag=Class.create();
 Drag.prototype={
 	//初始化函数
	initialize:function(clickDiv)
	{
		this.div=clickDiv;
		this.moveable=false;
	},
	/**
	*开始移动
	*@param beforeStartDrag 函数引用 在开始移动之前的操作
	*@param startDrag  函数引用 开始移动的操作
	*@param afterStartDrag 函数引用 在开始移动之后的操作
	*/
 	startDrag:function(beforeStartDrag,sdrag,afterStartDrag)
 	{
 		Try.these(
 			function (){beforeStartDrag(event.clientX,event.clientY);}
 		);
 		if(event.button==1)
 		{
 			this.div.setCapture();
 			this.moveable=true;
 			sdrag(event.clientX,event.clientY);
 			//鼠标的位置
 			/*Try.these(
 				
 				function (){sdrag(event.clientX,event.clientY);}
 			);*/
 		}
 		Try.these(
 			function(){afterStartDrag(event.clientX,event.clientY);}
 		);
 	},
 	/**
	*移动
	*@param beforeDrag 函数引用 在移动之前的操作
	*@param dr  函数引用 移动的操作
	*@param afterDrag 函数引用 在移动之后的操作
	*/
 	drag:function(beforeDrag,dr,afterDrag)
 	{
 		var x=event.clientX;
 		var y=event.clientY;
 		Try.these(
 		function (){beforeDrag(event.clientX,event.clientY);}
 		);
 		
 		if(this.moveable)
 		{
 		
 			Try.these(
 			function(){
 				dr(x,y);
 			}
 			);
 		}
 		
 		Try.these(
 		function(){afterDrag(x,y);}
 		);
 	},
 	/**
	*移动
	*@param bforeStopDrag 函数引用 在终止移动之前的操作
	*@param sDrag  函数引用 终止移动的操作
	*@param afterStopDrag 函数引用 在终止移动之后的操作
	*/
 	stopDrag:function(bforeStopDrag,sDrag,afterStopDrag)
 	{
 		var x=event.clientX;
 		var y=event.clientY;
		Try.these(
 			function(){bforeStopDrag();}
 		);
 		if(this.moveable)
 		{
	 		this.div.releaseCapture();
	 		
	 		Try.these
	 		(
	 			function(){sDrag(x,y);}
	 		);
 			this.moveable=false;
 		}
 		Try.these
 		(
 			function(){afterStopDrag();}
 		);
 	}
 }
 
 
</script>

<script>

//tag initjs
 /* 功能说明:
 *1)负责生成ID号
 */
 var IdGenarator = Class.create();
 IdGenarator.prototype = {
	/*初始化函数*/
	initialize:function()
	{
		this.currentId=1;
	},
	getNextId:function()
	{
		var d=new Date();
       	var time=d.getTime();
        var random=Math.floor(Math.random()*100000);
        var prefix = time+""+random;
        var idQZ='<%=UUID.randomUUID().toString().replace("-","")%>';
        idQZ=idQZ.substring(0,14)+prefix;
        return idQZ;
	}
 }
var idGen=null;
var toolBar=null;
var componentMan=null;
function initWindow()
{
	idGen=new IdGenarator();
	componentMan=new ComponentMan();
	toolBar=new ToolBar();
    document.onkeydown=del;
    //File.loadXML('default');
	//多个选择移动
	//multiMove=new MultiMove();
}
initWindow();
/**
*删除动作
*/
function del()
{
	var t=componentMan;
	var obj=t.getSelectObj();
	if(event.keyCode==46)
	{
		if(obj!=null)
		{
			if(confirm('<s:property value="getText('fimrtodel')"/>'))
			t.deleteSelectObj();
		}
	}
}
</script>
<!-- 获取xml内容测试按钮 -->
<!-- <button onclick="getXml222()">ccc</button> -->
<script>
/*function getXml222(){
	xml=componentMan.saveAsXML();
}*/
var File={
	save:function()
	{
		var url="<%=basePath%>workflow/process/updateLabWfProcess4Flow.action";
		xml=componentMan.saveAsXML();
		
		var busId='${labWfProcessVo.id}';
        var pars="labWfProcessVo.flowXml="+xml+"&labWfProcessVo.id="+busId;
		new Ajax.Request(  
		url,
		{
			method:'post',
			parameters:pars,
			onComplete: function(rq)
			{
				var doc = new ActiveXObject("Microsoft.XMLDOM");
				doc.loadXML(rq.responseText);
				componentMan.initDBID(doc);
			}
		});
	},
	getSaveXML:function()
	{
		  var doc = new ActiveXObject("Microsoft.XMLDOM");
		  if('${labWfProcessVo.flowXml}'!=''){
		  	doc.loadXML('${labWfProcessVo.flowXml}');
		  }else{
			doc.loadXML('');  
		  }
		  componentMan.initFromXML(doc);
		  try{
              afterinitload();
          }catch(e){

          }
	}
}
</script>







