/*
	模块层
	2014-5-6
	Aaron
*/
(function(){
	CKEDITOR.plugins.add( 'ahtml', {   
		requires : 'richcombo',
		init : function( editor ) {
		    var config = editor.config,
		       lang = editor.lang.format;
		    
			editor.ui.addRichCombo( 'ahtml', {
				label : "模块",
				title :"模块",
				className : 'cke_format',
				toolbar:"styles,20",
				panel : {
				   css : [ CKEDITOR.skin.getPath("editor") ].concat(config.contentsCss),
				   multiSelect:!1,
				   attributes:{"aria-label":lang.panelVoiceLabel}
				},
				
				init : function() {
				   this.startGroup( "模块" );
				   //this.add('value', 'drop_text', 'drop_label');
				   /*json数据name：下拉框的名称, code：调用方法名*/
				   var data = [
				   				{'name':'双标签','code':'code4tag2'},
				   				{'name':'标题标签(1)','code':'code4label1'},
				   				{'name':'标题标签(2)','code':'code4label2'},
				   				{'name':'标题标签(3)','code':'code4label3'}
				   			  ];
				   for (var i in data){
				      this.add(data[i].code, data[i].name);
				   }
				},
				
				onClick : function( value ) {
					var fun = eval(value);
					fun(editor);
				}
			});
		}
	});
})();