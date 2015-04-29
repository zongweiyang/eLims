function initTableRow(table,input,trTd,num){
		var trTd = trTd;
		var _$n = 30;
		if(num != undefined){
			_$n = parseInt(num);
		}
		
		var _$t = $('#'+table);
		var _$i = $('#'+input);

		if(trTd == undefined||trTd == ''){
			trTd = _$t.children('tbody').children('tr').eq(0).clone();
			trTd.children().find('input').val('');
			trTd = trTd.html();
		}
		_$i.blur(function(){
			_setTableNum(_$t,parseInt($(this).val()),trTd);
		}).keydown(function(e){
			if((e.keyCode<48||e.keyCode>57)&&(e.keyCode<96||e.keyCode>105)&&e.keyCode!=8&&e.keyCode!=37&&e.keyCode!=39){
				window.event.returnValue=false;
			}
		}).keyup(function(e){
			if(parseInt($(this).val())>_$n){
				$(this).val(_$n);
				alert('最多只支持'+_$n+'行！');
				return null;
			}
			if(e.keyCode == 13){
				_setTableNum(_$t,parseInt($(this).val()),trTd);
			}
		});
}
function _setTableNum($t,_n,_td){
	var _l = $t.children('tbody').children('tr').length;
	if(_n!=_l){
		if(_n > _l){
			for(var i=0;i<(_n-_l);i++){
				$t.append(
					$('<tr>').html(_td)
				);
			}
		}
		else{
			for(var i=0;i<(_l-_n);i++){
				$t.children('tbody').children('tr').last().remove();
			}
		}
	}
}