// trim left space
function LTrim(str) {
	var whitespace = new String(" tnr");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}

// trim right space
function RTrim(str) {
	var whitespace = new String(" tnr");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		var i = s.length - 1;
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
			i--;
		}
		s = s.substring(0, i + 1);
	}
	return s;
}
// trim both space
function Trim(str) {
	return RTrim(LTrim(str));
}
		function moveUp(target) 
				{ 
				
				    var theObjOptions=target.options;
				    for(var i=1;i<theObjOptions.length;i++) {
				    	
				        if( theObjOptions[i].selected && !theObjOptions[i-1].selected ) {
				            swapOptionProperties(theObjOptions[i],theObjOptions[i-1]);
				        }
				    }
				} 

				function moveDown(target) 
				{ 
				    var theObjOptions=target.options;
				    for(var i=theObjOptions.length-2;i>-1;i--) {
				        if( theObjOptions[i].selected && !theObjOptions[i+1].selected ) {
				            swapOptionProperties(theObjOptions[i],theObjOptions[i+1]);
				        }
				    }
				} 
				//上移到最顶层
				function moveToTop(selectObj){
				    var theObjOptions=selectObj.options;
				    var oOption=null;
				    for(var i=0;i<theObjOptions.length;i++) {
				        if( theObjOptions[i].selected && oOption) {
				            selectObj.insertBefore(theObjOptions[i],oOption);
				        }
				        else if(!oOption && !theObjOptions[i].selected) {
				            oOption=theObjOptions[i];
				        }
				    }
				}
				//下移到最底层
					function moveToBottom(selectObj){
					    var theObjOptions=selectObj.options;
					    var oOption=null;
					    for(var i=theObjOptions.length-1;i>-1;i--) {
					        if( theObjOptions[i].selected ) {
					            if(oOption) {
					                oOption=selectObj.insertBefore(theObjOptions[i],oOption);
					            }
					            else oOption=selectObj.appendChild(theObjOptions[i]);
					        }
					    }
					
					}
					
				//option调换方法
				function swapOptionProperties(option1,option2){
					    //option1.swapNode(option2);
					    var tempStr=option1.value;
					    option1.value=option2.value;
					    option2.value=tempStr;
					    tempStr=option1.text;
					    option1.text=option2.text;
					    option2.text=tempStr;
					    tempStr=option1.selected;
					    option1.selected=option2.selected;
					    option2.selected=tempStr;
				}
				
				
				//2个select 互换内容的方法，接收参数为 源select到目标select 
				 function Create(SourceSelect,TargetSelect) 
				 { 
   					 var IsCreate = true; 
   					 var theIndex  = SourceSelect.selectedIndex; 
    					var theLength = SourceSelect.length ; 
    					if (theIndex == -1 ) //如果源Select为空的话，则退出过程 
      						  return false; 
   						 while (IsCreate)     //添加到目的Select循环 
  						 { 
        					theValue = SourceSelect.options[theIndex].text;  //得到所选择的文本 
       						theKey=SourceSelect.options[theIndex].value;
       						
      						 TargetSelect.options.add(new Option(theValue,theKey));  //目的Select增加一个文本 
      
       						theIndex = theIndex + 1;   //如果是选择多列的话，对下一个进行处理 
         
     						   if (theIndex == theLength) //theLength 如果是4的话，则theIndex应该是3， 
        						{                          //如果两者想等的话，则源Select多了一个值， 
						            IsCreate = false;      //所以需要退出循环 
						            break; 
						        } 
						        if (SourceSelect.options[theIndex].selected == false)//如果没有被选择的话，则退出循环 
						        { 
						            IsCreate = false; 
						        } 
   						 }     
				     
					    while (IsCreate == false)                  //删除源select循环 
					    { 
					       SecIndex  = SourceSelect.selectedIndex; //动态得到被选择的索引 
					       theLength = SourceSelect.length ;       //动态得到Select的长度 
					       SourceSelect.remove(SecIndex);          //删除指定索引的元素 
					       if (theLength == 1)                     //表示最后一个元素已删除， 
					           return false;                       //源select空了，退出循环 
					       if (theLength == SecIndex + 1)          //表示多选的已全部删掉，退出循环 
					           return false; 
					       if (SourceSelect.options[SecIndex].selected == false) 
					       { 
					           IsCreate = true; 
					       } 
					       	
					    } 
					     
					    if(SourceSelect.options.length>0)
					   	{
					    		SourceSelect.options[theIndex-1].selected=true;
					   	}
					   	if(TargetSelect.options.length>0)
					   	{
					   		   for(var i=0;i<TargetSelect.options.length;i++) 
					   		   {
					   		   		if(TargetSelect.options[i].selected)
					   		   		{
					   		   			TargetSelect.options[i].selected=false;
					   		   		}
					   		   }
					   	}
				 }      
				 
function checkAll(name) {     
	var el = document.getElementsByTagName('input');     
	var len = el.length;     
	for(var i=0; i<len; i++)     
	{         
		if((el[i].type=="checkbox") && (el[i].name==name))         
		{             
			if(el[i].checked==false){
				el[i].checked = true;  
			}       
		}     
	} 
} 

function clearAll(name) {     
	var el = document.getElementsByTagName('input');     
	var len = el.length;     
	for(var i=0; i<len; i++)     
	{         
		if((el[i].type=="checkbox") && (el[i].name==name))         
		{             
			if(el[i].checked){
				el[i].checked = false;
			}         
		}     
	} 
} 
				 
