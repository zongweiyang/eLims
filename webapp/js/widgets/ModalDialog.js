/**
 * Copyright (C), 2007-2010, 厦门亿力吉奥信息科技有限公司
 * File Name: ModalDialog.js
 * Encoding UTF-8
 * Version: 1.0
 * Date: Sep 26, 2007
 * History:	
 */

/**
 * 仿模态对话框
 *
 * @author 陈谋坤 (chenmoukun@pdpower.com)
 * @version Revision: 1.00 Date: Sep 26, 2007
 */ 
ModalDialog = Class.create();

ModalDialog.focusId = "__focus_id__";

ModalDialog.generateIframeId = function(index){
	return "__modal_dialog_iframe_id__" + index;
}

ModalDialog.generateDialogId = function(index){
	return "__widget_modal_dialog_id__" + index;
}

ModalDialog.generateDialogBgId = function(index){
	return "__widget_modal_dialog_bg__" + index;
}

ModalDialog.getDialogBgElement = function(namespace, index){
	namespace = namespace || window;
	var bgId = ModalDialog.generateDialogBgId(index);
	return namespace.document.getElementById(bgId);
}

ModalDialog.gernateDivBg = function(namespace, index){
	
	namespace = namespace || window;
	
	var bg = namespace.document.createElement("DIV");
		bg.className = "dialog-background";
		bg.id = ModalDialog.generateDialogBgId(index);
	
	return bg;
}

ModalDialog.ProcessingHint = Class.create();
ModalDialog.ProcessingHint._id = "__widget_modal_dialog_processing_hint__";
ModalDialog.ProcessingHint.showProcessing = function(namespace, index){
	
	namespace = namespace || window;
	
	var bg = ModalDialog.gernateDivBg(namespace, index);
		bg.id = ModalDialog.ProcessingHint._id;
	
	var div = namespace.document.createElement("DIV");
		div.className = "loading-process";
		div.innerHTML = "正在处理您的请求……";
	
	bg.appendChild(div);	
	namespace.document.body.appendChild(bg);
}

ModalDialog.ProcessingHint.close = function(namespace){
	
	namespace = namespace || window;
	
	namespace.document.body.removeChild($(ModalDialog.ProcessingHint._id));
}

/**
 * 移除Iframe
 * 
 * @param namespace window || window.top
 */
ModalDialog.removeIframe = function(namespace, index) {
	
	var iframeEl = namespace.document.getElementById(ModalDialog.generateIframeId(index));
	
	if(iframeEl == null)
		return;
	
	iframeEl.src = "about:blank";
	iframeEl.parentNode.removeChild(iframeEl);
}

ModalDialog._close = function(namespace, index){
	
	if (Utils.isFunction(ModalDialog._callback))
		ModalDialog._callback();
	
	var bgEl = ModalDialog.getDialogBgElement(namespace, index);
	
	if (bgEl){
//		if (Utils.isIE)	
//			Utils.hideShowCovered(bgEl);
		//document.body.removeChild($(Dialog._bgId));
		
		ModalDialog.removeIframe(namespace, index);
		Utils.removeChildren(bgEl);
		bgEl.parentNode.removeChild(bgEl);
	}
	
	ModalDialog._callback = null;
}

ModalDialog.prototype = {
	
	/*------------------ initializer --------------*/
	
	initialize : function(title, url, index, options){
		
		this.win = window;
		this.top = window.top;
		this._self = this;
		this._index = index;
		
		this._options = {
			width : "500px",						//对话框宽度
			height : "400px",						//对话框高度
			boxClass : "detail-dialog-box",			//对话框样式
			titleClass : "dialog-title",			//对话框标题样式
			bodyClass : "dialog-body",				//对话框主体样式
			confirmBtnTitle : "确 定",               //对话框中确定按钮的名称
			onComplete : null
		};
		
		this._init(title, url, options);
		this._createDialog();
				
		/*
		 * 焦点定位
		 */
		if ($(ModalDialog.focusId)) {
			try {
				$(ModalDialog.focusId).focus();
			} catch(e) {
				
			}
		}
		
		/*
		 * 加载iframe中的内容
		 */
		 this.top.document.getElementById(ModalDialog.generateIframeId(index)).src = url;
	},
	
	/*------------ public methods ---------------------*/
	
	close : function(){
		
		if (Utils.isFunction(this._callback))
			this._callback();

		var bgEl = ModalDialog.getDialogBgElement(this.top, this._index);

		if (bgEl){
	//		if (Utils.isIE)	
	//			Utils.hideShowCovered(bgEl);
			//document.body.removeChild($(Dialog._bgId));
			
			ModalDialog.removeIframe(this.top, this._index);
			Utils.removeChildren(bgEl);
			bgEl.parentNode.removeChild(bgEl);
		}else{
			return false;
		}
		
		this._callback = null;
		
		return true;
	},
	
	/*------------ private methods ---------------------*/
	
	_init : function(title, url, options){
		if (ModalDialog.getDialogBgElement(this.top, this._index)){
			ModalDialog._close(this.top, this._index);
		}
		
		this._title = title || "&nbsp;";
		this._url = url || "&nbsp;";
		this._setOptions(options);
		
		this._callback = this._options.onComplete;
	},
	
	_setOptions : function(options){
		Object.extend(this._options, (options || {}));
	},
	
	_createDialog : function(){
	
		this._bgElement = ModalDialog.gernateDivBg(this.top, this._index);
		this.top.document.body.appendChild(this._bgElement);		
		this._bgElement.style.position = "absolute";
		this._bgElement.style.zIndex = 20000;
		
		var body = this._createDialogBody();
		this._bgElement.appendChild(body);
		
		//update by chenmk 2007.9.27
//		if (Utils.isIE)	
//			Utils.hideShowCovered(body);
		
	},
	
	_createDialogBody : function(){
		var div = this.top.document.createElement("DIV");
			div.id = ModalDialog.generateDialogId(this._index);
			div.className = this._options.boxClass;
			div.style.left = (this.top.document.body.offsetWidth-Utils.getComputedStyle(this._options.width))/2;
			div.style.top = (this._bgElement.offsetHeight-Utils.getComputedStyle(this._options.height))/2;
			div.style.width = this._options.width;
			div.style.height = this._options.height;
			
		
		
		var header = this._createHeader();
		div.appendChild(header);
		
		var body = this._createBody();
		with(body.style){
			width = Utils.getComputedStyle(this._options.width) - 8;
			height = Utils.getComputedStyle(this._options.height) - 47;
		}
		div.appendChild(body);
		this._appendButtons(div);
		
		var width = Utils.getComputedStyle(this._options.width);
		var x2 = this.top.document.body.offsetWidth - width;
		if (Utils.isIE)
			x2 -= 15;
		else
			x2 -= 5;
				
		Drag.init(header, div, 5, x2, 5);
		Drag.namespace = this.top;
		
		return div;
	},
	
	_createHeader : function(){
		var header = this.top.document.createElement("DIV");
			header.className = this._options.titleClass;
			header.style.paddingTop = "6px"; 
			header.style.paddingBottom = "6px";
			header.style.borderBottom = "1px #B1B1B1 solid";
			header.style.cursor = "move";
		
		
		header.innerHTML = this._title;
		
		return header;
	},
	
	_createBody : function(){
		var body = this.top.document.createElement("DIV");
			body.className = this._options.bodyClass;
		
		var frame = this.top.document.createElement("IFRAME");
			frame.id = ModalDialog.generateIframeId(this._index);
			frame.frameBorder = "0";
			frame.width = "100%";
			frame.height = "100%";
			frame.src = "about:blank";
		
		body.appendChild(frame);
		
		return body;
	},
	
	_appendButtons : function(box){
		box.appendChild(this._createCloseBtn());
	},
	
	_createCloseBtn : function(){
		var _self = this;
		var btn = this.top.document.createElement("INPUT");
			btn.type = "image";
			btn.src = "images/close.gif";
			btn.title = "关闭";
			btn.onclick = function(){			
				_self.close();
			};			
			btn.id = ModalDialog.focusId;
			btn.style.position = "absolute";
			btn.style.right = "8px";
			btn.style.top = "6px";
			btn.style.cursor = "pointer";
		
		return btn;
	}
}