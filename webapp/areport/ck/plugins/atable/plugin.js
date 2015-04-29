/*
	循环表格
	2014-7-9
	Aaron
*/
(function(){
	CKEDITOR.plugins.add( 'atable', {   
		requires : 'richcombo',
		init : function( editor ) {
		    var config = editor.config,
		       lang = editor.lang.format;
		    
			editor.ui.addRichCombo( 'atable', {
				label : "数据表格",
				title :"数据表格",
				className : 'cke_format',
				toolbar:"styles,20",
				panel : {
				   css : [ CKEDITOR.skin.getPath("editor") ].concat(config.contentsCss),
				   multiSelect:!1,
				   attributes:{"aria-label":lang.panelVoiceLabel}
				},
				
				init : function() {
					this.startGroup( "数据表格" );
					for (var i in TABLEDATA){
						//格式：code type tags
						this.add(TABLEDATA[i].code+'*^'+TABLEDATA[i].type+'*^'+i, TABLEDATA[i].title);
					}
				},
				
				onClick : function( value ) {
					value = value.split('*^');
					var code = value[0],
						type = value[1],
						tags = TABLEDATA[value[2]].tags;
					
					var _th = '',
						_td = '';
					for (var i = 0; i < tags.length; i++) {
						_th += '<th style="border:1px solid #000;">'+tags[i].title+'</th>';
						_td += '<td code="'+tags[i].code.replace('${','').replace('}','')+'"><span class="are-page-data1 are-page-data-css are-page-data-event">'+tags[i].title+'</span></td>';
					}
					_th = '<tr>'+_th+'</tr>';
					_td = '<tr>'+_td+'</tr>';
					var table = '<table class="areport-each-table" code="'+code.replace('${','').replace('}','')+'">'+
									'<thead>'+_th+'</thead>'+
									'<tbody>'+_td+'</tbody>'+
								'</table>';
					editor.focus();
					editor.fire( 'saveSnapshot' );
					editor.insertHtml(table);
					
					editor.fire( 'saveSnapshot' );
					$('body').focus();
					
					$('.are-page-data-event').click(function() {
						$('body').focus();
					}).focus(function() {
						$('body').focus();
					}).removeClass('are-page-data-event');
				}
			});
		}
	});
})();