//阻止click冒泡
function stopBubble(e){
   //如果提供了事件对象，则这是一个非IE浏览器
   if (e && e.stopPropagation ){
		e.stopPropagation();
   }
   else{
   //否则，我们需要使用IE的方式来取消事件冒泡
		window.event.cancelBubble = true;
   }
}
$.fn.insertSpeChare = function(){
	var _config = [
		{'code':'<sub></sub>','name':'演示：H<sub>2</sub>O','title':'代码：H<sub>2</sub>O'},
		{'code':'<sup></sup>','name':'演示：i<sup>2</sup>','title':'代码：i<sup>2</sup>'}
	];
	var $input = $(this);
	var _w = $input.width()+13;
	var _h = $input.height()+2;
	var _l = $input.offset().left+_w;
	$('<div>').attr('title','特殊字符').addClass('insertSpeChare').css({'left':_w+'px','margin-top':'-'+_h+'px'}).insertAfter($input).click(function(){
		if($(this).children('.speCharePanel').length>0){
			if($(this).children('.speCharePanel').css('display') != 'none'){
				$(this).children('.speCharePanel').hide();
				return;
			}
		}
		$('.speCharePanel').hide();
		if($(this).children('.speCharePanel').length==0){
			$(this).append(
				$('<div>').addClass('speCharePanel').append(
					$('<div>').addClass('head').append(
						$('<div>').addClass('font')
					).append(
						$('<div>').addClass('colse').click(function(){
							$(this).parent().parent().hide();
						})
					)
				).click(function(e){
					stopBubble(e);
				})
			);
			$(this).children('.speCharePanel').append(
				$('<table>')
			)
			for(var i in _config){
				var title = _config[i].title;
				while(title.indexOf( "<" ) != -1 ) {
					title = title.replace("<","&lt;");
					title = title.replace(">","&gt;");
				}

				$(this).find('table').append(
					$('<tr>').append(
						$('<td>').html(_config[i].name)
					).append(
						$('<td>').html(title)
					)
					.append(
						$('<td>').append(
							$('<span>').addClass('insert-btn').attr('key',encodeURIComponent(_config[i].code)).html('插入').click(function(){
								$(this).closest('.insertSpeChare').prev().val($(this).closest('.insertSpeChare').prev().val()+decodeURIComponent($(this).attr('key')));
							})
						)
					)
				);
			}
			$(this).children('.speCharePanel').height($(this).find('.content:eq(0)').height()*(parseInt(i)+1));
		}
		else{
			$(this).children('.speCharePanel').show();
		}
	});
}