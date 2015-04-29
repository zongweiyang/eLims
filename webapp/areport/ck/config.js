/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
CKEDITOR.editorConfig = function( config ) {
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;雅黑/雅黑;'+ config.font_names ;
	config.allowedContent  = true;
	var _toolbar;
	if (EDITTYPE == 0) {
		_toolbar = ['Maximize', 'ShowBlocks', '-', 'Undo', 'Redo'];
		config.extraPlugins = 'submit,adata,ahtml,atable';
	}
	else {
		_toolbar = ['Maximize', 'Undo', 'Redo'];
		config.extraPlugins = 'submit,aprint';
	}
	config.toolbar =
    [
       ['NewPage','submit','aprint'],
       //['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],
       ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
       ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent'],
       ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
       ['Table', 'HorizontalRule', 'SpecialChar'],
       ['Styles'],
       ['Format'],
       ['Font'],
       ['FontSize'],
       ['adata'],
       ['ahtml'],
       ['atable'],
       ['TextColor', 'BGColor'],
       _toolbar
    ];
};
