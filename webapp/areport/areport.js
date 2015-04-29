 /*
 *  areport
 *	Date 2014-07-18
 *	Aut: Aaron.Yang
 *	v 2.1.0
 */
var AREDATA = new Array(),
	TABLEDATA = new Array(),
	EDITTYPE;
(function($){
	var par = {
		
	},
	trim = function(str) {
		if (str == undefined) {
			return str;
		}
		return str.replace(/(^\s*)|(\s*$)/g, "");  
	},
	/*编辑页面事件*/
	addEvent = function(obj) {
		obj.focus(function() {
			$('.are-toolbar').css('z-index','5000');
			var index = $(this).closest('.are-page').index();
			$('.are-toolbar:eq('+index+')').css('z-index','6000');
		}).keydown(function(e){
			//var ockeditor = CKEDITOR.instances['abc'];
			//ockeditor.insertHtml('asdfasdf');
			if ( e.keyCode == 37 || e.keyCode == 38 || e.keyCode == 39 || e.keyCode == 40 ) {
				return;
			}
			e.stopPropagation();
			var obj = $(this);
			
			/*判断必须是按了退格键并且此时只存在一个初始p标签以及0个p标签*/
			if ( e.keyCode == 8 && $(this).children('p').length < 2 && $(this).children('table').length < 1 ) {
				removeNowPage(obj);
			}
			else if ( e.keyCode == 8 ) {
				backSpaceRemovePage(obj);
			}
			else {
				isNewPageAndIntertContent(obj, '0');
			}
		});
		
		//初始化数据事件,屏蔽选中字体
		obj.find('.are-page-data,.are-page-data1').click(function(e) {
			$('body').focus();
			//e.stopPropagation();
		}).focus(function() {
			$('body').focus();
		});
	},
	
	/*删除当前页*/
	removeNowPage = function(obj) {
		var _html = trim(obj.html()),
			_html1 = trim(obj.children('p').html());

		if ( _html != undefined && _html1 != undefined ) {
			_html = _html.replace(/<br>/g, '');
			_html1 = _html1.replace(/<br>/g, '');
		}
		else {
			return;
		}
		
		if ( _html == '&nbsp;' || _html == '' || _html1 == '&nbsp;' || _html1 == '' ) {
			var index = obj.closest('.are-page').index();
			/*起始页不能删除*/
			if ( index == 0 ) {
				return;
			}
			var oPage = obj.closest('.are-page').prev().find('.are-page-editor');
			oPage.focus().html(oPage.html());
			//obj.closest('.are-page').prev().find('.are-page-editor').focus();
			setTimeout(function() {
				$('.are-toolbar:eq('+index+')').remove();
				obj.closest('.are-page').remove();
				oPage.focus();	
			},500);
		}
	},
	
	/*按下退格键删减内容后判断下一页是否自动删减及内容向上*/
	backSpaceRemovePage = function(obj) {
		var nextEditorPage = obj.closest('.are-page').next().find('.are-page-editor');
		
		var index = obj.closest('.are-page').index();
		var id = $('.are-toolbar:eq('+index+')').children().attr('id').replace(/cke_/g,'');
		var ockeditor = CKEDITOR.instances[id];
		ockeditor.insertHtml('<span class="testIsEnd" style="display:none;">1</span>');
		
		/*判断是否达到本页第一条删除自动向上一页聚焦*/
		if ( index != 0 && obj.children().eq(0).find('.testIsEnd').length > 0) {
			$('.testIsEnd').parent().remove();
			var oPage = obj.closest('.are-page').prev().find('.are-page-editor');
			//oPage.focus().html(oPage.html());
			oPage.focus();
			$('.testIsEnd').remove();
			return;
		}
		$('.testIsEnd').remove();
		
		if ( nextEditorPage.length == 0 ) {
			return;
		}
		var objClone = obj.closest('.are-page').clone();
		nextEditorPage.children().each(function(){
			var thisClone = $(this).clone();
			if ( thisClone.is('table') ) {
				var prevLastObj = objClone.find('.are-page-editor').children().last();
				if ( prevLastObj.is('table') ) {
					prevLastObj.append(thisClone.find('tr').eq(0));
				}
				else {
					objClone.find('.are-page-editor').append(
						$('<table>').append(prevLastObj.find('tr').eq(0))
					);
				}
				$('#are-content>div').append(objClone);
				objClone.find('.are-page-editor').scrollTop(500);
				if ( objClone.find('.are-page-editor').scrollTop() > 0 ) {
					objClone.remove();
					return false;
				}
				else {
					objClone.remove();
					/*
						判断是否存在表格
						如果上一层有表格那么直接添加tr
						如果没有则重新生成表格在添加tr
					*/
					if ( prevLastObj.is('table') ) {
						obj.children().last().append($(this).find('tr').eq(0));
					}
					else {
						obj.append(
							$('<table>').append($(this).find('tr').eq(0))
						);
					}
					if ( $(this).find('tr').length == 0 ) {
						$(this).remove();
					}
					var _html = trim(nextObj.html());
					
					/*如果没有内容则清空那一页*/
					if ( _html == '' ) {
						nextObj = nextObj.closest('.are-page');
						var index = nextObj.index();
						$('.are-toolbar:eq('+index+')').remove();
						nextObj.remove();
					}
				}
			}
			else {
				objClone.find('.are-page-editor').append($(this).clone());
				$('#are-content>div').append(objClone);
				objClone.find('.are-page-editor').scrollTop(500);
				if ( objClone.find('.are-page-editor').scrollTop() > 0 ) {
					objClone.remove();
					return false;
				}
				else {
					objClone.remove();
					var nextObj = $(this).parent();
					obj.append($(this));
					var _html = trim(nextObj.html());
					
					/*如果没有内容则清空那一页*/
					if ( _html == '' ) {
						nextObj = nextObj.closest('.are-page');
						var index = nextObj.index();
						$('.are-toolbar:eq('+index+')').remove();
						nextObj.remove();
					}
				}
			}
		});
		//backSpaceRemovePage(obj.closest('.are-page').next().find('.are-page-editor'));
	},
	
	/*点点击回车或者有新内容输入的时候判断是否新增下一页*/
	isNewPageAndIntertContent = function(obj, NowI) {
		var isEnd = false;
		obj.scrollTop(500);
		if ( obj.scrollTop() > 0 ) {
			var nextPage = obj.closest('.are-page').next();
			
			var index = obj.closest('.are-page').index();
			if ( NowI == '0' ) {
				var id = $('.are-toolbar:eq('+index+')').children().attr('id').replace(/cke_/g,'');
				var ockeditor = CKEDITOR.instances[id];
				ockeditor.insertHtml('<span class="testIsEnd" style="display:none;">1</span>');
				//判断是否光标到达最后一行
				if ( obj.children().last().find('.testIsEnd').length > 0 ) {
					$('body').focus();
					isEnd = true;
				}
				$('.testIsEnd').remove();
			}
			
			for ( var i = 0; i < 1000; i++ ) {
				var nextPage = obj.closest('.are-page').next();
				obj.scrollTop(500);
				//alert(obj.scrollTop());
				if ( obj.scrollTop() > 0 ) {
					var lastobj = obj.children().last();
					if (lastobj.is('table')) {
						var tableClass = '';
						if ( lastobj.hasClass('areport-each-table1') ) {
							tableClass = 'areport-each-table1';
						}
						
						//创建表格一次性加载
						var _table = $('<table>').addClass(tableClass);
						for ( var j = 0; j < 1000; j++) {
							var tr = lastobj.find('tr').last();
							obj.scrollTop(500);
							//alert(obj.scrollTop());
							if ( obj.scrollTop() > 0 ) {
								_table.prepend(tr);
							}
							else {
								break;
							}
						}

						if ( !nextPage.hasClass('are-page') ) {
							layout.newPage(_table);
						}
						else {
							if (!nextPage.find('.are-page-editor').children().eq(0).is('table')) {
								nextPage.find('.are-page-editor').prepend(_table);
							}
							else {
								nextPage.find('.are-page-editor table:eq(0)').prepend(_table.html());
							}
							
						}
						if ( lastobj.find('tr').length == 0 ) {
							lastobj.remove();
						}
					}
					else {
						if ( !nextPage.hasClass('are-page') ) {
							layout.newPage(obj.children().last());
						}
						else {
							nextPage.find('.are-page-editor').prepend(obj.children().last());
						}
					}
					if (isEnd) {
						var nextEditor = obj.closest('.are-page').next().find('.are-page-editor');
						nextEditor.focus();
					}
				}
				else {
					break;
				}
			}
			isNewPageAndIntertContent(nextPage.find('.are-page-editor'), 1);
		}
	},
	
	/*判断是否是点击了新增*/
	isNewPage = false,
	
	layout = {
		contentHeight : function() {
			var _h = $('.cke_toolbar_all:eq(0)').height();
			$('#are-toolbar>div').height(_h);
			$('#are-content').height($('body').height() - $('#are-toolbar').height() - $('#are-bottom').height());
		},
		pageDivHeight : function(oDiv) {
			oDiv.find('.are-page-editor').height(888)
			//oDiv.find('.are-page-editor').height(oDiv.find('.are-page-editor').parent().height()-3);
		},
		init : function($t, o) {
			var $this = this;
			
			$(window).resize(function() {
				$this.contentHeight();
			});
			
			$t.append(
				$('<table id="are-frame">').append(
					$('<tr>').append(
						$('<td id="are-toolbar">').append(
							$('<div>')
						)
					)
				).append(
					$('<tr>').append(
						$('<td>').append(
							$('<div id="are-content">').append(
								$('<div>')
							)
						)
					)
				).append(
					$('<tr>').append(
						$('<td id="are-bottom">')
					)
				)
			);
			var $this = this;
			setTimeout(function(){
				$this.contentHeight();
				CKEDITOR.disableAutoInline = true;
				$('table#are-frame').show();
				//$this.newPage();
				var page = $('#page-data>div');
				if ($('#page-data>div').length == 0) {
					$this.newPage();
				}
				else {
					$('#page-data>div').each(function() {
						/*
							判断是否子模板，转化数据
						*/
						if ( EDITTYPE == 1 ) {
							$(this).find('.are-page-data').each(function() {
								getDataObject($(this), $(this).attr('key'));
								$(this).removeClass('are-page-data-css');
							});
							$('.areport-each-table').remove();
							$('.areport-each-table1').show();
						}
						else {
							$('*[status="st"]').remove();
							$('.areport-each-table1').remove();
						}
						$this.newPage($(this).html());
					});
					//进行数据处理 判断是否需要新增行
					$('.are-page').each(function() {
						var obj = $(this).find('.are-page-editor');
						obj.scrollTop(500);
						if ( obj.scrollTop() > 0 ) {
							setTimeout(function(){
								isNewPageAndIntertContent(obj, 1);
							},1000);
							//isNewPageAndIntertContent(obj, 1);
							return false;
						}
					});
				}
			},300);
			
			$('body').keydown(function(e){
				if ( e.keyCode == 8 ) {
					return false;
				}
				if (e.keyCode == 83 && e.ctrlKey) {
					a_save();
				}
			});
			//this.newPage();
		},
		newPage : function(_html) {
			if (_html == '' || _html == undefined) {
				_html = '<p></p>';
			}
			var oDiv;
			$('#are-content>div').append(
				oDiv = $('<div>').addClass('are-page').append(
					$('<table>').append(
						$('<tr>').append(
							$('<td>').addClass('are-page-left')
						).append(
							$('<td valign="bottom">').append(
								$('<div>').addClass('are-page-left-top are-page-border')
							).append(
								$('<div>').addClass('are-page-right-top are-page-border')
							)
						).append(
							$('<td>').addClass('are-page-right')
						)
					).append(
						$('<tr>').append(
							$('<td>')
						).append(
							$('<td valign="top">').append(
								$('<div>').addClass('are-page-editor').attr({
									'contenteditable' : true
								}).html(
									_html
								).ckeditor()
							)
						).append(
							$('<td>')
						)
					).append(
						$('<tr>').append(
							$('<td>').addClass('are-page-left')
						).append(
							$('<td valign="top">').append(
								$('<div>').addClass('are-page-left-bottom are-page-border')
							).append(
								$('<div>').addClass('are-page-right-bottom are-page-border')
							)
						).append(
							$('<td>').addClass('are-page-right')
						)
					)
				)
			);
			
			var index = oDiv.index();
			var $this = this;

			var getEditor = setInterval(function(){
				if ($('.cke_toolbar_all').eq(index).length > 0) {
					clearInterval(getEditor);
					
					$('#are-toolbar>div').append(
						$('<div>').addClass('are-toolbar').append(
							$('.cke_toolbar_all').eq(index).show()
						).css('z-index','5000')
					);
					if ( index == 0 ) {
						$('.cke_toolbar_all:eq('+index+')').parent().css('z-index','6000')
					}
					$this.contentHeight();
					addEvent(oDiv.find('.are-page-editor'));
					if (isNewPage) {
						oDiv.find('.are-page-editor').focus();
						$('#are-content').scrollTop(oDiv.offset().top + $('#are-content').scrollTop() - $('.cke_toolbar_all:eq(0)').height() - 10);
					}
				}
			},300);

			this.pageDivHeight(oDiv);
			
			return oDiv;
		}
	},
	loadJS = {
		url : [
			'ck/ajaxconfig.js',
			'ck/ckeditor.js',
			'ck/adapters/jquery.js',
			'ck/extends/moduleCode.js',
			'lodop/LodopFuncs.js'
		],
		importCss: function(path) {
			var head = document.getElementsByTagName('head')[0];
	        var link = document.createElement('link');
	        link.href = path;
	        link.rel = 'stylesheet';
	        link.type = 'text/css';
	        head.appendChild(link);
		},
		index : 0,
		init : function(path, o, $t) {
			var $this = this;
			
			var url = $this.url[$this.index];
			
			$.getScript(path + url, function() {
			
				$this.index += 1;
				
				if ( $this.index < $this.url.length ) {
					$this.init(path, o, $t);
				}
				else {
					$('head').append(
						'<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>'+
							'<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>'+
						'</object>'
					);
					$this.importCss(path+'areport.css?v='+new Date());
					loadJS = null;
					$.ajax({
						url:ajaxConfig('getData'),
						type:'POST',
						async:false,
						data:{'labReportTagVo.labReportId':$('#reportId').val()},
						dataType:'text',
						success:function(data){
							AREDATA = eval('('+data+')');
						}
			   		});
			   		$.ajax({
						url:ajaxConfig('getTableInfo'),
						type:'POST',
						async:false,
						data:{'labReportTagVo.labReportId':$('#reportId').val()},
						dataType:'text',
						success:function(data){
							TABLEDATA = eval('('+data+')');
						}
			   		});
					layout.init($t, o);
				}
			});
		}/*,
		setLoadScript: function(path, opt, $t) {
			var data = getModuleInfo();
			for (var i in data){
				//loadJS.url.push('ck/extends/'+data[i].code+'.js');
			}
			loadJS.init(path, opt, $t);
		}*/
	};
	$.fn.extend({
		areport: function( _opt ) {
			//初始化type值
			EDITTYPE = parseInt($('#editType').val());
			var opt=$.extend({}, par, _opt),$t=$(this);
			$('script').each(function() {
				var src = $(this).attr('src');
				if ( src.indexOf('areport.js') > 0 ) {
					var path = src.substring(0, src.indexOf('areport.js'));
					loadJS.init(path, opt, $t);
					return false;
				}
			});
			//任意地方点击后释放数据层对象
			return $t;
		}
	});
	
	/*
		递归获取数据对象
	*/
	function getDataObject(obj, sHtml) {
		//alert(obj.children());
		if ( obj.children().html() != undefined ) {
			getDataObject(obj.children(), sHtml);
		}
		else {
			obj.html(sHtml);
		}
	}
	
	/*报告保存*/
	function a_save() {
		var pageEditor = $('.are-page-editor').clone();
		pageEditor.find('p').each(function(){
			if($(this).html().length < 1) {
				$(this).html('&nbsp;');
			}
		});
		
		/*
			获取数据
		*/
		pageEditor.find('.are-page-data').each(function() {
			$(this).attr({
				'key': '${'+$(this).attr('key1')+'}'
			});
		});
		pageEditor.find('.areport-each-table').each(function() {
			var table = $(this).clone('areport-each-table');
			table.hide();
			table.removeClass().addClass('areport-each-table1');
			table.find('td').each(function() {
				$(this).html('${'+$(this).attr('code')+'}');
			});
			table.find('tbody').prepend('<areport value="'+$(this).attr('code')+'" status="st"></areport>').append('<tr></tr>');
			$(this).before(table);
		});
		
		/*
			保存设计页面
		*/
		
		var html = '';//getHtmlContent(body.find('.each-page-editor-1').html(),'-1');
		pageEditor.each(function(){
			html += '<div>' + $(this).html()/*.toLocaleLowerCase()*/ + '</div>';
		});
		if(document.all) {
			html = html.replace(/\b([A-Za-z_]\w*)\s*=\s*([^\b\s>"']+)/gi,"$1=\"$2\"");
		}
		html = html.replace(/<areport/g,
					'<s:iterator').replace(/<AREPORT/g,
					'<s:iterator').replace(/<\/areport>/g,
					'').replace(/<\/AREPORT>/g,
					'').replace(/<tr><\/tr>/g,
					'</s:iterator>').replace(/<TR><\/TR>/g,'</s:iterator>');
		var url='';
		var param='';
		/*
			EDITTYPE=='0'
			保存为模板
			否则
			保存为实际业务
		*/
		if(EDITTYPE == 0){
			url = ajaxConfig('saveModule');
			param = {'labReportVo.file':html,'labReportVo.id':$('#reportId').val()};
		}else{
			url = ajaxConfig('saveReport4bus');
			param = {'labReportVo.file':html,'labReportVo.id':$('#reportId').val(),'labReportVo.path':$('#path').val()};
		}
		$.ajax({
			type:'post',
			url:url,
			dataType:'text',
			data:param,
			success:function(data){
				alert('保存成功');
			},
			error:function() {
				myMask.hide();
				alert('出现异常，保存失败，请您重新保存！');
			}
		});		
	}
	
	/*打印报告*/
	function a_print() {
		//获取lodop url地址
		var path = '';
		$('script').each(function() {
			var src = $(this).attr('src');
			if ( src.indexOf('areport.js') > 0 ) {
				path = src.substring(0, src.indexOf('areport.js')) + 'lodop/';
				return false;
			}
		});
		/*报告打印*/
		var LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'),path);  
		//LODOP.PRINT_INITA(0,0,800,1127,"");
		
		var strStyleCSS = '<link rel="stylesheet" type="text/css" href="/areport/areport.css">';
		
		var strBodyStyle =  '<style>'+
								'.are-page-border{border:0px;}'+
								'.are-page-editor table{width:100%;border-collapse:collapse;table-layout:fixed;border:1px solid #000}'+
								'.are-page-editor table td{border-collapse:collapse;border:1px solid #000;word-wrap:break-word;word-break:break-all;text-align:center;font-size:13px;}'+
							'</style>';
		
		var _l = $('.are-page').length;
		
		$('.are-page').each(function(i){
			$(this).find('.are-page-editor p').each(function(){
				if($(this).html().length < 1) {
					$(this).html('&nbsp;');
				}
			});
		
			var html = $(this).html();
			html = html.replace(/\b([A-Za-z_]\w*)\s*=\s*([^\b\s>"']+)/gi,"$1=\"$2\"");
			LODOP.ADD_PRINT_HTM(0,0,'100%','100%','<!DOCTYPE HTML>'+strStyleCSS+strBodyStyle+html);
			LODOP.SET_PRINT_PAGESIZE(0,0,0,'A4');
			if(i < _l) {
				LODOP.NewPage();
			}
		});
		//LODOP.PRINT_DESIGN();
		LODOP.PREVIEW();
	}
	
	/*areport对外接口*/
	jQuery.interface = function(_fun) {
		eval(_fun+'()');
	};
})(jQuery);