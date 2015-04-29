 /*
 * jQuery UI Shadowdiv 1.0.0
 *	Date 2011-12-2
 *	Aut: Aaron.Yang
 */
(function($){
	var par = {
		$obj:''
	},
	setUI = function($t,o){
		$t.prepend(
			$('<div>',{'style':'position:absolute;width:'+$t.width()+'px;height:'+$t.height()+'px;background:#ccc;opacity:0.6;filter:Alpha(Opacity=60);z-index:10000;'}).append(
				$('<img>',{'src':basePath+'img/loading_16x16.gif','style':'margin-top:'+($t.height()-32)/2+'px;margin-left:'+($t.width()-32)/2+'px'})
			)
		);
	};
	$.fn.shadow=function(_opt,_par){
		if($.isPlainObject(_opt)||undefined==_opt){
			var opt=$.extend({},par,_opt),$t=$(this);
			setUI($t,opt);
			return $t;
		}else{
			var opt=$.extend({},par,_par),$t=$(this);
			return eval(_opt)($t,opt);
		}
	}
})(jQuery);

function shandow(t){

		var $t = $(t);
		$t.prepend(
			$('<div>').css({'position':'absolute','width':$t.width(),'height':$t.height(),'background':'#ccc','z-index':20000,'opacity':0.6,'filter':'Alpha(Opacity=60)'}).append(
				$('<img>').attr({'src':basePath+'img/loading_16x16.gif'}).css({'margin-top':($t.height()-32)/2,'margin-left':($t.width()-32)/2})
			)
		);
	}