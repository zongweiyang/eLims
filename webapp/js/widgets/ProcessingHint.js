/**
 * Copyright (C), 2007-2010, 厦门亿力吉奥信息科技有限公司
 * File Name: ProcessingHint.js
 * Encoding UTF-8
 * Version: 1.0
 * Date: Oct 30, 2007
 * History:	
 */

/**
 * 处理提醒
 *
 * @author 陈谋坤 (chenmoukun@pdpower.com)
 * @version Revision: 1.00 Date: Oct 30, 2007
 */ 
ProcessingHint = {
	_loadingMaskId : "__widget_processing_loading_mask__",
	_loadingId : "__widget_processing_loading__",
	
	showProcessing : function(){
		document.write('<div id="' + this._loadingMaskId + '" class="process-loading-mask" style=""></div>' +
			'		<div id="' + this._loadingId +'" class="process-loading">' +
			'			<div class="process-loading-indicator">' +
			'				Loading...' +
			'		    </div>' +
			'	   </div>');
	},
	
	close : function(){
		var loading = document.getElementById(this._loadingId);
		var loadingMask = document.getElementById(this._loadingMaskId);
		document.body.removeChild(loading);
		document.body.removeChild(loadingMask);
	}
}//ended ProcessingHint

/*------------------- DomReadyUtil ------------------------------*/

DomReadyUtil = {
	docReadyState : false,
	docReadyProcId : null,
	
	fireDocReady : function(){

		if(this.docReadyState)
			return;
	
        this.docReadyState = true;
        
        ProcessingHint.close();
        
        if(this.docReadyProcId){        
        	clearInterval(this.docReadyProcId);
        }
        
        if(Utils.isGecko || Utils.isOpera) {
        	document.removeEventListener("DOMContentLoaded", this.fireDocReady, false);
        }
        
        if(Utils.isIE){
        	var defer = document.getElementById("ie-dom-ready-deferred-loader");
        	if(defer){
        		defer.onreadystatechange = null;
        		defer.parentNode.removeChild(defer);
            }
        }
    },
	
	/**
	 * 初始化document文档
	 */
	initDocReady : function(){
		
		this.docReadyState =false;
		this.docReadyProcId = null;
		
		ProcessingHint.showProcessing();
		
        if(Utils.isGecko || Utils.isOpera) {
            document.addEventListener("DOMContentLoaded", this.fireDocReady, false);
        }else if(Utils.isIE){
            document.write("<s"+'cript id="ie-dom-ready-deferred-loader" defer="defer" src="/'+'/:"></s'+"cript>");
            var defer = document.getElementById("ie-dom-ready-deferred-loader");
            defer.onreadystatechange = function(){
                if(this.readyState == "complete"){
                    DomReadyUtil.fireDocReady();
                }
            };
        }else if(Utils.isSafari){
            docReadyProcId = setInterval(function(){
                var rs = document.readyState;
                if(rs == "complete") {
                    DomReadyUtil.fireDocReady();
                 }
            }, 10);
        }
    }
}//ended DomReadyUtil


//执行初始化页面:即显示加载提示
DomReadyUtil.initDocReady();