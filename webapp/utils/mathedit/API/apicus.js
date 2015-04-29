var xmlHttpReq;
var j=0;      
var items2;   
var items4;
var k=0;
var k1=0;
var num;
var num1;
var parentID;
var t_body1;
var t_body;
var t_r1;

function createMSXML2() 
{
  var aVersions = [ "MSXML2.DOMDocument.5.0",
                 "MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.3.0",
                 "MSXML2.DOMDocument","Microsoft.XmlDom"
                ];
  for (var i = 0; i < aVersions.length; i++) 
  {
  try {
     var xmlDoc = new ActiveXObject(aVersions[i]);
     return xmlDoc;
  } catch (oError) {
       // 不做任何处理
  }
  }
  throw new Error("MSXML is not installed.");
}

function getOs() 
{ 
   var OsObject = ""; 
   if(navigator.userAgent.indexOf("MSIE")>0) { 
   return 1; 
   } 
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
   return 2; 
   } 
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
   return 3; 
   } 
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
   return 4; 
   } 
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
   return 5; 
   } 
   return 0;
}
function createxmlDoc(xmlfileName)
{
	var osv=getOs();
  var xmlDoc=null;
  if(osv==1)
  {
   xmlDoc=createMSXML2();
   xmlDoc.async=false;
   xmlDoc.load(xmlfileName);
  }else if(osv==2 )
  {
  	var req = new XMLHttpRequest();
    req.open('GET', xmlfileName, false);
    req.send(null);
    xmlDoc=req.responseXML;
   }
   return xmlDoc;
}
function apistart()
{         
  start();
  var xmlDoc=createxmlDoc("ftemplates.xml");
  var xmlDoc1=createxmlDoc("templates.xml");
  items2 = xmlDoc.getElementsByTagName("t");             
  t_body=document.createElement("tbody");         
  t_r=document.createElement("tr");        
  t_body.appendChild(t_r);      
  items4 = xmlDoc1.getElementsByTagName("s");             
  t_body1=document.createElement("tbody");          
  t_r1=document.createElement("tr");        
  t_body1.appendChild(t_r1);
  parentID=parent.mathedit.foo(window);                 
}     

function gradetemp(gcode){   	 	 
  for(i=0;i<items4.length;i++)
  { 
  	var it4=items4[i];         
    if(it4.getAttribute("level")<=gcode)
    { 
       var t_d1=document.createElement("td");
       t_d1.setAttribute("align","center");
       t_d1.setAttribute("bgcolor","#ece9d8");     
       var timg1=showtmp(it4.getElementsByTagName("imgurl")[0].firstChild.nodeValue,it4.getElementsByTagName("action")[0].firstChild.nodeValue);                 
       t_d1.appendChild(timg1);
       t_r1.appendChild(t_d); 
       if(++k1%num1==0)
       {
   	     t_r1=document.createElement("tr");       
         t_body1.appendChild(t_r1);
        }     
     }                     
 }        
} 

function categorytemp(ccode){
 for(var i=0;i<items4.length;i++){ 
 	  var it4=items4[i];           
   if(it4.getAttribute("type")==ccode){
     	var t_d1=document.createElement("td");   
     	t_d1.setAttribute("align","center"); 
     	t_d1.setAttribute("bgcolor","#ece9d8");                
      var timg1=showtmp(it4.getElementsByTagName("imgurl")[0].firstChild.nodeValue,it4.getElementsByTagName("action")[0].firstChild.nodeValue);                 
      t_d1.appendChild(timg1);
      t_r1.appendChild(t_d1);
      if(++k1%num1==0){
   	   t_r1=document.createElement("tr");        
       t_body1.appendChild(t_r1);
       }  
    }                      
}        
} 

function singletemp(scode){        	
  for(var i=0;i<items4.length;i++)
  {
   var it4=items4[i];             
   if(it4.getAttribute("id")==scode)
   { 
   	var t_d1=document.createElement("td");
   	t_d1.setAttribute("align","center");
   	t_d1.setAttribute("bgcolor","#ece9d8");                   
    var timg1=showtmp(it4.getElementsByTagName("imgurl")[0].firstChild.nodeValue,it4.getElementsByTagName("action")[0].firstChild.nodeValue);
    t_d1.appendChild(timg1);
    t_r1.appendChild(t_d1);
    if(++k1%num1==0){
   	   t_r1=document.createElement("tr");        
       t_body1.appendChild(t_r1);
    }  
   }           
 }        
} 

function showtmp(curl,caction){
  var t_img=document.createElement("img");    
  t_img.setAttribute("src",curl);
  t_img.setAttribute("align","center");
  t_img.setAttribute("onmouseover","this.style.border='solid 1px #00C0C0'");
  t_img.setAttribute("onmouseout","this.style.border='none'");
  t_img.setAttribute("style","cursor:pointer");
  t_img.setAttribute("onclick",caction);
  return(t_img);    
}  

function tooltemp(tcode){
 for(var i=0;i<items2.length;i++)
 {    
   var it=items2[i];       
   if(it.getAttribute("id")==tcode)
   { 
   	   var t_d=document.createElement("td");
   	   t_d.setAttribute("align","center"); 
   	   t_d.setAttribute("bgcolor","#ece9d8");
   	   t_d.setAttribute("width","40");
   	   t_d.setAttribute("height","25");
       var timg=showtmp(it.getElementsByTagName("imgurl")[0].firstChild.nodeValue,it.getElementsByTagName("action")[0].firstChild.nodeValue);                 
       t_d.appendChild(timg);  
       if(++k%num==0){
   	   var t_r=document.createElement("tr");        
       t_body.appendChild(t_r);
       }                
    }           
 }
 return(t_d);   
} 
function ftemp(tcode){
 for(var i=0;i<items2.length;i++)
 {              
   var it2=items2[i];
   if(it2.getAttribute("id")==tcode)
   {             	  
   	  t_d=document.createElement("td");
   	  t_d.setAttribute("align","center");              	                       
      var ts=showselect();
      t_d.appendChild(ts);
   }                  
  }
  return(t_d);  
} 

function showselect(){
 var t2;
 fs = t2.getElementsByTagName("f");
 t_s=document.createElement("select");                  	               	  
 t_s.setAttribute("onchange",t2.childNodes[fs.length].firstChild.nodeValue);                 
 for(m=0;m<fs.length;m++)
    {                 	 
     t_o=document.createElement("option");
   	 t_o.setAttribute("value",t2.childNodes[m].firstChild.nodeValue);             	                     
     t_o.appendChild(document.createTextNode(t2.childNodes[m].firstChild.nodeValue));
     t_s.appendChild(t_o);
     }  
 return(t_s);            
}       

function showT(tempID)	  
{ 	
  var iw=document.getElementById('design_frame').getAttribute("width");
  num1=parseInt(iw/40);       	  	  
  switch(tempID){
   case "ms":  gradetemp("1"); break;      	   	  	         	   	  	    
   case "hs":  gradetemp("2"); break;  
   case "cs":  gradetemp("3"); break;      
   case "arithmetic1": categorytemp("1");break;
 	 case "arithmetic2": categorytemp("2");break;
 	 case "relation": categorytemp("3");break;
 	 case "logic": categorytemp("4");break;
 	 case "set": categorytemp("5");break;
   case "trigonometric": categorytemp("6");break;
 	 case "calculus": categorytemp("7");break;
 	 case "greek": categorytemp("8");break;       	   	  
 	 case "layout": categorytemp("9");break;
   default: singletemp(tempID);
   }
          	    
 var table1;
 table1=document.createElement("table");
 table1.setAttribute("border","0");        
 table1.setAttribute("cellpadding","0");        
 table1.setAttribute("cellspacing","1");
 table1.appendChild(t_body1);
 var math1=document.getElementById('mathsign');
 if(getOs()==1)
  math1.innerHTML=table1.innerHTML;
 else if(getOs()==2)   
  math1.appendChild(table1); 
}
function showFT(ftempID)	  
{ 	
 var iw=document.getElementById('design_frame').getAttribute("width");
 num=parseInt(iw/20); 	  	  
 switch(ftempID)
 {
    case "tfont": td=ftemp(ftempID); t_r.appendChild(td);break;
    case "tfontsize": td=ftemp(ftempID); t_r.appendChild(td);break;
    case "selectedinfix" : showSI(0);return;
    case "wholeinfix" : showSI(1);return;
    default: td=tooltemp(ftempID); t_r.appendChild(td);
 }  
 var table2=document.createElement("table");
 table2.setAttribute("border","0");        
 table2.setAttribute("cellpadding","0");        
 table2.setAttribute("cellspacing","1");	 
 table2.appendChild(t_body);   
 var tool1=document.getElementById('toolbar2');
 if(getOs()==1)
  tool1.innerHTML=table2.innerHTML;
 else if(getOs()==2)   
  tool1.appendChild(table2); 
}
function showSI(it)
{
 var iw=document.getElementById('design_frame').getAttribute("width");  	  
 var num=iw/10;
 var xmlDoc=createxmlDoc("ftemplates.xml");
 var siElements=xmlDoc.getElementsByTagName("si");
 var p=document.createElement("p");
 var inputbox=document.createElement("input");
 inputbox.setAttribute("type","text"); 
 inputbox.setAttribute("id","infixinputbox");
 inputbox.setAttribute("size","40");
 inputbox.setAttribute("value","Presentation Mode");
 inputbox.setAttribute("disabled","yes");
 var siElement;
 if(it==0)
 {
 	siElement=siElements[0];
 }else
 {
 	siElement=siElements[1];
 }
 for(var j;j<(siElement.getElementsByTagName("action").length-1);j++)
 {
  inputbox.setAttribute(siElement.getElementsByTagName("action")[j].getAttribute("evt"),siElement.getElementsByTagName("action")[j].firstChild.nodeValue);
 }
 var img=showtmp(siElement.getElementsByTagName("imgurl")[0].firstChild.nodeValue,siElement.getElementsByTagName("action")[2].firstChild.nodeValue);
 p.appendChild(inputbox);
 p.appendChild(img); 
 var tool1=document.getElementById('sinfix');
 if(getOs()==1)
  tool1.innerHTML=p.innerHTML;
 else if(getOs()==2)   
  tool1.appendChild(p); 
}
function showEM(emStr)	  
{ 	
 var iw=document.getElementById('design_frame').getAttribute("width");  	  
 var num=iw/10;
 if(emStr=="" || emStr==null ||emStr=="undefine") return;
 var emStrs=emStr.split(",");
 var xmlDoc=createxmlDoc("ftemplates.xml");
 var emElements=xmlDoc.getElementsByTagName("m");
 var t_body2=document.createElement("tbody"); 
 var k=0; 
 for(var i=0;i<emStrs.length;i++)
 {  
   for(var j=0;j<emElements.length;j++)
   {
   	if(emStrs[i]==emElements[j].getAttribute("id"))
   	{
   		 var t_d=document.createElement("td");
   	   t_d.setAttribute("align","center"); 
   	   t_d.setAttribute("bgcolor","#ece9d8");
   	   t_d.setAttribute("width","40");
   	   t_d.setAttribute("height","25");
       var ahref=document.createElement("a");
       ahref.setAttribute("href","#");
       ahref.setAttribute("onclick",emElements[j].getElementsByTagName("action")[0].firstChild.nodeValue); 
       var atext=document.createTextNode(emElements[j].getElementsByTagName("text")[0].firstChild.nodeValue);
       ahref.appendChild(atext);
       t_d.appendChild(ahref);  
       if((k++)%num==0){
   	   var t_r=document.createElement("tr");        
       }
       t_r.appendChild(t_d);
     }
    }
 }
 t_body2.appendChild(t_r);
 var table2=document.createElement("table");
 table2.setAttribute("border","0");        
 table2.setAttribute("cellpadding","0");        
 table2.setAttribute("cellspacing","1");	 
 table2.appendChild(t_body2);   
 var tool1=document.getElementById('toolbar3');
 if(getOs()==1)
  tool1.innerHTML=table2.innerHTML;
 else if(getOs()==2)   
  tool1.appendChild(table2); 
}
function editarea(iw,ih){
  document.getElementById('design_frame').setAttribute("width",iw);
  document.getElementById('design_frame').setAttribute("height",ih);
}