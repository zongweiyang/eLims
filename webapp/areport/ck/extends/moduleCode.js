 /*
 *  模块js
 *	Date 2014-07-9
 *	Aut: Aaron.Yang
 */
/*标题标签(1)*/
function code4label1(oEditor) {
	var sHtml = '<div style="float: left;margin-left:10px;min-width:110px;">';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:right;">标题：</div>';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:left;">标签</div>';
	   sHtml += '</div>';
	insertEditHtml(oEditor, sHtml);
}
/*标题标签(2)*/
function code4label2(oEditor) {
	var sHtml = '<div style="float: left;margin-left:10px;min-width:110px;">';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:right;">标题1：</div>';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:left;">标签1</div>';
	   sHtml += '</div>';
	   
	   sHtml += '<div style="float: right;margin-right:10px;min-width:110px;">';
	   		sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:right;">标题2：</div>';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:left;">标签2</div>';
	   sHtml += '</div>';
	insertEditHtml(oEditor, sHtml);
}
/*标题标签(3)*/
function code4label3(oEditor) {
	var sHtml = '<div style="float: left;margin-left:10px;min-width:110px;">';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:right;">标题1：</div>';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:left;">标签1</div>';
	   sHtml += '</div>';
	   
	   		sHtml += '<div style="margin-left:100px;float:left;font-size: 15px;min-width:50px;text-align:right;">标题3：</div>';
	        sHtml += '<div style="margin-left:10px;float:left;font-size: 15px;min-width:50px;text-align:left;">标签3</div>';
	   
	    sHtml += '<div style="float: right;margin-right:10px;min-width:110px;">';
	   		sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:right;">标题2：</div>';
	        sHtml += '<div style="float:left;font-size: 15px;min-width:50px;text-align:left;">标签2</div>';
	   sHtml += '</div>';
	insertEditHtml(oEditor, sHtml);
}
/*双标签*/
function code4tag2(oEditor) {
	var sHtml = '<div style="float: left;font-size: 15px;min-width:50px;text-align:left;margin-left:24px;">标签1</div>';
	sHtml += '<div style="float: right;font-size: 15px;min-width:50px;text-align:right;margin-right:24px;">标签2</div>';
	insertEditHtml(oEditor, sHtml);
}
/*动态插入html标签-公用方法*/
function insertEditHtml(oEditor, sHtml) {
	oEditor.focus();
	oEditor.fire( 'saveSnapshot' );
	oEditor.insertHtml(sHtml);
}