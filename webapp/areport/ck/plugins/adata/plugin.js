/*
	数据层
	2014-4-4
	Aaron
*/
(function(){
	CKEDITOR.plugins.add( 'adata', {   
		requires : 'richcombo',
		init : function( editor ) {
		    var config = editor.config,
		       lang = editor.lang.format;
		    
			editor.ui.addRichCombo( 'adata', {
				label : "数据",
				title :"数据",
				className : 'cke_format',
				toolbar:"styles,20",
				panel : {
				   css : [ CKEDITOR.skin.getPath("editor") ].concat(config.contentsCss),
				   multiSelect:!1,
				   attributes:{"aria-label":lang.panelVoiceLabel}
				},
				
				init : function() {
				   this.startGroup( "数据" );
				   //this.add('value', 'drop_text', 'drop_label');
				   for (var i in AREDATA){
				      this.add(AREDATA[i].title + '**' + AREDATA[i].code, AREDATA[i].title);
				   }
				   AREDATA = null;
				},
				
				onClick : function( value ) {
				
					value = value.split('**');
					var title = value[0],
						code = value[1],
						html = '<span style="font-size:12px;" id="are-page-data" key="'+code+'" key1="'+code.replace('${','').replace('}','')+'" class="are-page-data are-page-data-css">'+title+'</span>';

					editor.focus();
					editor.fire( 'saveSnapshot' );
					editor.insertHtml(html);
					
					editor.fire( 'saveSnapshot' );
					$('body').focus();
					
					/*绑定事件删除初始化id*/
					$('#are-page-data').click(function() {
						$('body').focus();
					}).focus(function() {
						$('body').focus();
					}).attr('id','');
				}
			});
		}
	});
})();