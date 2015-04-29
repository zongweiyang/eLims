/**
 * File Name: ExtModalDialog.js
 * Encoding UTF-8
 * Version: 1.0
 * Date: Nov 15, 2007
 * History:	
 */
/**
 * EXT仿模态对话框
 *
 * @author 
 * @version Revision: 1.00 Date: Nov 15, 2007
 */ 
ExtModalDialog = Class.create();
ExtModalDialog.Ext_Win_List = [];     //虽然调用Ext.WindowMgr.getActive()可以取得当前激活窗口(即Ext会帮我们管理所有弹出的window组件),但是有些窗口不是我们封装的ExtModalDialog,所以设置该变量,能够自己管理弹出的ExtModalDialog
ExtModalDialog.Main_Ext_Win = null;   //用来保存第一层弹出窗口对象，之后凡是第一层弹出窗口，都只是修改该窗口iframe的内容
ExtModalDialog.Main_Dialog_Container_Div_Id = "ext-modal-dialog-win";    //显示弹出窗口div的ID
ExtModalDialog.Return_Value = null;    //存储模态弹出窗口的返回值
ExtModalDialog.IS_MASK_TOP_MAP_AREA = false;   //用来标识首页地图区域是否被屏蔽


/**
 * 显示Ext的window(模态弹出窗口)
 * 
 * @param title 窗口的标题名称
 * @param url 窗口显示内容的URL
 * @param winOpener 打开extDialog的源窗口
 * @param callback 当关闭该窗口时的回调函数
 * @param options 控制窗口显示的一些属性
 * @param vArguments 源窗口传递给弹出窗口的参数值
 */
ExtModalDialog.show = function(title, url, winOpener, callback, options, vArguments){
	
	/*
	 * 为了防止页面没有重新加载，而是使用缓存的页面，所以在URL后加上时间搓
	 */
	
	var extDialogObj = {};
		extDialogObj._winOpener = winOpener;         //打开extDialog的源窗口
		extDialogObj._vArguments = vArguments;       //传递给extDialog的初始信息
		extDialogObj._callback = callback;          //关闭(隐藏)Ext的window时执行的回调函数
	var extWin = null;
		
	options = options || {};	
	
	if(ExtModalDialog.Main_Ext_Win == null){  //如果Ext的window从来没有打开过，则创建，并且设置该window关闭的方式是隐藏
		Object.extend(options, {title: title, closeAction: 'hide', el: ExtModalDialog.Main_Dialog_Container_Div_Id});
		
		extWin = ExtModalDialog._create(url, options);  //创建ext的window
		extWin.on('hide', ExtModalDialog._hideAfterHandle); //注册一个隐藏ext的window后的事件
	
		ExtModalDialog.Main_Ext_Win = extWin;
		
	}else{
		
		if(ExtModalDialog.getActiveExtWin() == null){
			
			ExtModalDialog._reloadIframe(ExtModalDialog.Main_Ext_Win.getId(), url);
						
			var width = options["width"] || 600; 
			var height = options["height"] || 400;
				
			ExtModalDialog.Main_Ext_Win.setWidth(width);
			ExtModalDialog.Main_Ext_Win.setHeight(height);
			ExtModalDialog.Main_Ext_Win.setTitle(title);
							
			extWin = ExtModalDialog.Main_Ext_Win;
			
			
		}else{   //这里执行的是第二层及以后弹出窗口执行的内容，并且设置该window关闭的方式是销毁
			Object.extend(options, {title: title, closeAction: 'close'});
			extWin = ExtModalDialog._create(url, options);  //创建ext的window
			
			extWin.on('beforeclose', ExtModalDialog._destroyBeforeHandle); //注册一个关闭ext的window前的事件
			extWin.on('close', ExtModalDialog._destroyAfterHandle); //注册一个关闭ext的window后的事件
		}
	}
	
	
	extWin.show();                             //显示ext的window	
	
	ExtModalDialog._adaptToSize(extWin);       //调整EXT窗口到适当大小
		
	ExtModalDialog._setGpmsExtDialogObj(extWin, extDialogObj); //将GPMS自己的额外的信息附加到EXT的window对象中
	ExtModalDialog._initExtWin(extWin);	      //初始化当前弹出窗口的信息
}

/**
 * 关闭Ext的window
 */
ExtModalDialog.close = function(){
	var extWin = ExtModalDialog.getActiveExtWin();
	
	if(extWin == null)
		return;
	
	if(extWin.closeAction == 'hide')
		extWin.hide();
	else
		extWin.close();
}

/**
 * 重新调整弹出窗口的大小
 * 
 * @param {} width
 * @param {} height
 */
ExtModalDialog.resizeExtWin = function(width, height){
	var extWin = ExtModalDialog.getActiveExtWin();
	
	if(extWin == null)
		return;
	
	extWin.setHeight(height);
	extWin.setWidth(width);
	extWin.center();                           //弹出窗口居中显示
}

/**
 * 最大化弹出窗口
 */
ExtModalDialog.maxExtWin = function(){
	
	var extWin = ExtModalDialog.getActiveExtWin();
	
	if(extWin == null)
		return;
		
	extWin.maximize();
}

/**
 * 如果Ext的window界面已经向服务器提交了内容，就需要调用该函数
 * 主要用于执行当关闭(隐藏)Ext的window时，是否需要执行回调函数
 */
ExtModalDialog.markUpdated = function(){
	var activeExtWin = ExtModalDialog.getActiveExtWin();
	if(activeExtWin == null)
		return;
	activeExtWin._isExcecuteCallBack = true;
}

/**
 * 设置模态窗口的返回值
 */
ExtModalDialog.setReturnValue = function(returnValue){
	ExtModalDialog.Return_Value = returnValue;
}

/**
 * 标识是否屏蔽首页的地图区
 */
ExtModalDialog.setMaskTopMapArea = function(isMasked){
	ExtModalDialog.IS_MASK_TOP_MAP_AREA = isMasked;
}

/**
 * 取得当前显示(激活)的Ext的window
 */
ExtModalDialog.getActiveExtWin = function(){
	return ExtModalDialog.Ext_Win_List[ExtModalDialog.Ext_Win_List.length-1];
}

/**
 * 取得当前窗口的window对象
 * 
 * @return {}
 */
ExtModalDialog.getCurrentWindow = function(){
	var extWin = ExtModalDialog.getActiveExtWin();
	if(extWin == null)
		return;	
	var parentDocument = extWin.body.dom.ownerDocument;
	var iframeId = ExtModalDialog._generateIframeId(extWin.getId());
	
	return parentDocument.frames(iframeId);
}

/**
 * 取得打开当前显示(激活)的Ext的window的窗口对象
 */
ExtModalDialog.getWinOpener = function(){
	var extWin = ExtModalDialog.getActiveExtWin();
	if(extWin == null)
		return;
	return ExtModalDialog._getGpmsExtDialogObj(extWin)._winOpener;
}

/**
 * 取得源窗口传递给弹出窗口的参数值
 */
ExtModalDialog.getArguments = function(){
	var extWin = ExtModalDialog.getActiveExtWin();
	if(extWin == null)
		return;
	return ExtModalDialog._getGpmsExtDialogObj(extWin)._vArguments;
}

/**
 * 取得模态窗口的返回值
 */
ExtModalDialog.getReturnValue = function(){
	return ExtModalDialog.Return_Value;
}

/**
 * 初始化弹出窗口
 */
ExtModalDialog._initExtWin = function(extWin){
	
	ExtModalDialog._addExtWin(extWin);     //添加一个extWin弹出窗口

	ExtModalDialog._resetUpdated(extWin);    //将extWin的更新标识重置为初始状态
	ExtModalDialog.setReturnValue(null);   //清空弹出窗口的返回值
}

/**
 * 添加一个extWin弹出窗口
 */
ExtModalDialog._addExtWin = function(extWin){
	ExtModalDialog.Ext_Win_List.push(extWin);
}

/**
 * 移除一个extWin弹出窗口
 */
ExtModalDialog._removeExtWin = function(){
	ExtModalDialog.Ext_Win_List.pop();
}

/**
 * 创建Ext的window对象
 */
ExtModalDialog._create = function(url, options){
	
	var defaultOptions = {
			width:600,
	        height:400,
	        collapsible:'true',
	        closeAction: 'close',  //当关闭ext的window时，销毁ext的window
	        modal:true,         //模态对话框
	        resizable: true,   //不可调整大小
	        maximizable :true   //允许最大化
		};
		
	Object.extend(defaultOptions, (options || {}));
	var result = new Ext.Window(defaultOptions);
		result.html = ExtModalDialog._generateIframe(result.getId(),url);
	
	return result;
}

/**
 * 重置执行回调函数的刷新标识
 */
ExtModalDialog._resetUpdated = function(extWin){
	extWin._isExcecuteCallBack = false;
}

/**
 * 取得执行回调函数的刷新标识
 */
ExtModalDialog._isUpdated = function(extWin){
	return extWin._isExcecuteCallBack;
}

/**
 * 取得自定义的存储在EXT的window对象中的信息
 */
ExtModalDialog._getGpmsExtDialogObj = function(extWin){
	return extWin._gpmsExtDialogObj;
}

/**
 * 设置自定义的信息存储在EXT的window对象中
 */
ExtModalDialog._setGpmsExtDialogObj = function(extWin, customObj){
	extWin._gpmsExtDialogObj = customObj;
}

/**
 * 隐藏ext的window后执行的操作
 */
ExtModalDialog._hideAfterHandle = function(extWin){
	
	ExtModalDialog._closeBeforeHandle(extWin);
	
	ExtModalDialog._closeAfterHandle(extWin);
}

/**
 * 销毁ext的window前执行的操作
 */
ExtModalDialog._destroyBeforeHandle = function(extWin){
	
	ExtModalDialog._renewExtWinMask();
	
	ExtModalDialog._closeBeforeHandle(extWin);
}

/**
 * 销毁ext的window后执行的操作
 */
ExtModalDialog._destroyAfterHandle = function(extWin){

	ExtModalDialog._closeAfterHandle(extWin);

	ExtModalDialog._setGpmsExtDialogObj(extWin, null);
	extWin = null;
} 

/**
 * 关闭ext的window前执行的操作
 */
ExtModalDialog._closeBeforeHandle = function(extWin){
	
	ExtModalDialog._renewExtWinMask();
	
	ExtModalDialog._removeExtWin();  //关闭(隐藏/销毁)当前window窗口前,先从保存extWin列表中移除 

	ExtModalDialog._clearIframeContent(extWin.getId());  //清空iframeContent的内容
}

/**
 * 关闭ext的window后执行的操作
 */
ExtModalDialog._closeAfterHandle = function(extWin){
	
	ExtModalDialog._executeCallBack(extWin);	
}

/**
 * 执行关闭(隐藏)后的回调函数
 */
ExtModalDialog._executeCallBack = function(extWin){
	
	var extDialogObj = ExtModalDialog._getGpmsExtDialogObj(extWin);
	var extDialogCallBack = extDialogObj._callback;
	if(extDialogObj != null && ExtModalDialog._isUpdated(extWin) && Utils.isFunction(extDialogCallBack)){
		extDialogCallBack();
	}
}


/**
 * 恢复Ext窗体的屏障
 */
ExtModalDialog._renewExtWinMask = function(){
	if(ExtModalDialog.IS_MASK_TOP_MAP_AREA && Utils.isFunction(renewGpmsWebGisMap))
		renewGpmsWebGisMap();
		
	ExtModalDialog.IS_MASK_TOP_MAP_AREA = false;
}

/**
 * 生成Iframe的ID
 */
ExtModalDialog._generateIframeId = function(winId){
	return "__gpms_dialog_iframe__" + winId;
}

/**
 * 生成ExtModalDialog中的iframe
 */
ExtModalDialog._generateIframe = function(winId, targetUrl){
	var iframeId = ExtModalDialog._generateIframeId(winId);
	var result = '<iframe id="' + iframeId + '" name="' + iframeId 
				    +'" src="' + targetUrl + '" frameBorder="0" scrolling="auto" width="100%" height="100%"></iframe>';
	return result;
}

/**
 * 清空iframe的内容，避免内存泄露
 */
ExtModalDialog._clearIframeContent = function(winId){

	var iframeEl = $(ExtModalDialog._generateIframeId(winId));
		iframeEl.src = "about:blank";
}

/**
 * 重新加载Iframe的内容
 */
ExtModalDialog._reloadIframe = function(winId, url){
	var iframeEl = $(ExtModalDialog._generateIframeId(winId));
	
	if(iframeEl == null)
		return;
	
	iframeEl.src = url;
	
	return iframeEl;
}

/**
 * 调整EXT窗口到适当大小
 * 
 * @param {} extWin
 */
ExtModalDialog._adaptToSize = function(extWin){	
	extWin.center();                           //弹出窗口居中显示
	if(extWin == null)
		return;
	var extWinEl = extWin.getEl();
	var winTop = extWinEl.getTop();
	var winBottom = extWinEl.getBottom() - 35;
	var winHeight = extWinEl.getHeight();
	if(winTop < 0){
		extWinEl.setTop('0');
		if(winBottom < winHeight)
			extWin.setHeight(winBottom);
	}
}


