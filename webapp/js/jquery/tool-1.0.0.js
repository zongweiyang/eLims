/*
 * 工具类 包含checkbox多选,
 * 日期: 2013-2-18
 * 作者: Aaron
 */
(function($){
/*
 *多选
 */
function checkbox( elements, value ) {
	if ( !elements.is($(':checkbox')) ) {
		return;
	}
	var obj = value;
	if ( value == undefined ) {
		obj = $('input:checkbox[name="'+elements.eq(0).attr('key')+'"]');
	}
	else if ( typeof value == 'string' ) {
		var input = $('input:checkbox[name="'+value+'"]');
		( input.length > 0 ) ? obj = input : obj = $('input:checkbox.'+value);
	}
	elements.click(function() {
		if ( !$(this).attr('checked') || $(this).attr('checked') == undefined ) {
			obj.not(':disabled').attr('checked',false);
		}
		else {
			obj.not(':disabled').attr('checked','checked');
		}
	});
}
/*
 * 树形组件
 */
function tree( element ){
	element.addClass('ztree');
	var id = element.attr('id');
	var checkbox = false;
	if(element.attr('checkbox') == 'true' || element.attr('radio') == 'true'){
		checkbox = true;
	}
	var treeClickTime;
	var setting = {
		async : {
			type : 'post',
			enable : true, // 设置 zTree是否开启异步加载模式
			url : element.attr('turl'), // Ajax 获取数据的 URL 地址
			autoParam : [ "treeNid" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
		},
		data:{ // 必须使用data
			simpleData : {
				enable : true,
				rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
			}
		},
		check: {
			enable: checkbox
		},
		// 回调函数
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				// 判断是否父节点
				treeClickTime = setTimeout(function(){
					var curl = element.attr('curl');
					if(curl == undefined) {
						return;
					}
					var trrno=treeNode.treeNid;
				    if(typeof(trrno)=="undefined"){
				    	trrno='0';
				    }
				    var parentID = element.attr('parent');
				    if(parentID == undefined) {
				    	$('#docTreeMain', parent.document).attr({'src':curl+'='+trrno});
				    }
				    else{
				    	$('#'+parentID, parent.document).attr({'src':curl+'='+trrno});
				    } 
				},200)
			},
			beforeExpand: function(){
				
			},
			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
			onAsyncSuccess : function(event, treeId, treeNode, msg){
			$('#loading').remove();
				var zTree = $.fn.zTree.getZTreeObj(id);
				zTree.expandNode(zTree.getNodes()[0], true, false, false);
			},
			onDblClick : function() {
				clearTimeout(treeClickTime);
			}
		}
	};
	if(element.attr('radio') == 'true'){
		setting.check.chkStyle = "radio";
		setting.check.radioType = element.attr('radioType');
	}
	$.fn.zTree.init(element, setting);
}
jQuery.fn.extend({
	checkbox: function( value ) {
		return checkbox( this, value );
	},
	ztree : function() {
		return tree( this );
	}
});
})(jQuery);
/*
 *树的扩展方法
 */
//修改文件名
function modifyNodeName(_v,docId){
	$("#docTree").find('li[key="'+docId+'"]').children('a').attr('title',_v);
	$("#docTree").find('li[key="'+docId+'"]').children('a').children('span').eq(1).html(_v);
}
//删除一个节点
function removeNode(docId){
	$("#docTree").find('li[key="'+docId+'"]').remove();
}
//添加新节点
function addNewNode(_v,docId) {
	var zTree = $.fn.zTree.getZTreeObj("docTree"),
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	zTree.addNodes(treeNode, {treeNid:docId, name:_v});
};
$(function(){
/*
 * 自动添加多选
 */
	var obj = $('#allCheckBox');
	if( obj.length > 0 ){
		obj.checkbox();
	}
/*
 * 自动添加树
 */
	obj = $('#zTree');
	if( obj.length > 0 ){
		obj.ztree();
	}
	obj = null;
/*
 * 自动table隔色
 */
	if( $('.myworkingboxttable').length > 0 ){
		var link = 0;
		$('.myworkingboxttable').find('tr').each(function(link){
			if( link%2 == 0 ) {
				$(this).addClass('color');
			}
			$(this).hover(
				function () {
					$(this).addClass('css_style_class');
				},
				function () {
					$(this).removeClass('css_style_class');
				}
			); 
		});
		link = null;
	}
});