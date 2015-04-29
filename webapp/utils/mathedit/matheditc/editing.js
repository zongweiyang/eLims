Ext.BLANK_IMAGE_URL = "../ext/resources/images/default/s.gif"
//[section1]: Definition of global variables
var mmlparse='<?xml version="1.0" encoding="UTF-8" standalone="no"?><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"  xmlns:mml="http://www.w3.org/1998/Math/MathML"><body>';
//var newmath='<mml:math xmlns:mml="http://www.w3.org/1998/Math/MathML"><mml:mstyle tempstyle="1" mathsize="20pt"><mml:mstyle mathbackground="#00ff00" tempinput="1"><mml:ci id="1">&#x25A1;</mml:ci></mml:mstyle></mml:mstyle></mml:math>';
var newmathIE='<mml:math xmlns:mml="http://www.w3.org/1998/Math/MathML"><mml:mstyle tempstyle="1" mathsize="20pt"><mml:mstyle mathbackground="#00ff00" tempinput="1"><mml:ci id="1">&#x25A1;</mml:ci></mml:mstyle></mml:mstyle></mml:math>';
var newmathFF='<math xmlns="http://www.w3.org/1998/Math/MathML"><mstyle tempstyle="1" mathsize="20pt"><mstyle mathbackground="#00ff00" tempinput="1"><ci id="1">&#x25A1;</ci></mstyle></mstyle></math>';
var currentmath;
if(getOs() == 1)
{
    currentmath = newmathIE;
}
else if(getOs() == 2)
{
    currentmath = newmathFF;
}
var mmlparse1='</body></html>';
var focuson;
var editMode=1; //1:design mode; 2:content mode;3:presentation mode; 4:infix mode; 5:LaTex mode;6:OpenMath mode; 5:preview mode
var histmath = new Array();
var histp=0;
var histr=0;
var copymath;
var f34key;
var sMathml0;
var sMathml;
var currentmathP;
var currentmath1;
var designWin;
var designDoc;
var designFra;
var mmlns  = "http://www.w3.org/1998/Math/MathML";
var mml="mml:";
var idnum=1;
var xslRef;
var tempid;
var entermode=0; //1:dragdrop; 0:normal;
var tex;
//designDoc.onclick=domouseclick;
var unicode; // for choose the different keys;
//[section2]:

var LocString=String(window.document.location.href);

function GetQueryString(str){
var rs=new RegExp("(^|)"+str+"=([^\&]*)(\&|$)","gi").exec(LocString),tmp;
if(tmp=rs)return tmp[2];
return "argument error";
}
var ID=GetQueryString("InstlChecked");

function start()
{
 if (getOs()==1)
 {
    //check for the mathplayer installation
   var installState = checkForMathPlayer();
   if(installState == 3) //the mathplayer is not installed
   {
        if(ID != "TRUE") //open the page for the first and the MathPlayer is not installed
         {
            if(window.confirm("尚未安装MathEdit插件，是否下载安装?")){
            	window.location.href = "MathPlayerSetup.exe"
            }
         }
         else // checked the installation but still not installed or not installed properly
         {
            alert("MathPlayer is not installed or not installed properly.\n\nWe are sorry that MathEdit couldn't work properly with the current browser.\n\nWe advise that you use the Mozilla Firefox to open this page or you can select the FAQ under the Help menu to install later.")
         }

   }
   else
   {
        //check for mathplayer.js for more operation
   }

   mmlparse=mmlparse+'<object id="matheditdemo1" classid="clsid:32F66A20-7614-11D4-BD11-00104BD3F987" codebase="http://www.dessci.com/dl/mathplayer.cab"></object><import namespace="mml" implementation="#matheditdemo1">';
   designWin=design_frame;
   designDoc=designWin.document;

 }
 else
 {
   var myXMLHTTPRequest = new XMLHttpRequest();
   myXMLHTTPRequest.open("GET", "../matheditc/style/mathml.xsl", false);
   myXMLHTTPRequest.send(null);
   xslRef = myXMLHTTPRequest.responseXML;
   designFra=document.getElementById("design_frame");
   designWin=designFra.contentWindow;
   designDoc=designWin.document;
   solvematrixdisplay(designDoc);
 }
    if ( parent.opener != null )
    {
     try{
     if(parent.opener.mathedit.op=="edit")
     {
      currentmath = parent.opener.mathedit.getCurrentMath();
      currentmath = addFocus();
     }
     else if(parent.opener.mathedit.op=="display_edit")
     {
      if(parent.opener.mathedit.codeType=='1')
        currentmath = parent.opener.mathedit.getCurrentMath2();
      else if(parent.opener.mathedit.codeType=='2')
        currentmath = addprefix(parent.opener.mathedit.getCurrentMath2());
      currentmath = addFocus();
      }
        }catch(e){}
    }

  //  document.onkeydown = PreventDefaultActions(e);
   // document.onkeypress = PreventDefaultActions(e);
   /* document.onkeydown = function ()
    {
        //PreventDefaultActions(e);
        return false;
    }

    document.onkeypress = function ()
    {
        //PreventDefaultActions(e);
        return false;
    }*/

    document.onkeydown = function(e)
    {
        return PreventDefaultActions(e);
    }
    document.onkeypress = function(e)
    {
        return PreventDefaultActions(e);
    }
    renew();
    if(getOs()==1)
    {
      designDoc.attachEvent("onkeydown", doKey);
      designDoc.attachEvent("onkeypress",doKeyPress);
      designDoc.attachEvent("onclick",domouseclick);
    }else
    {
         designDoc.addEventListener("keydown", doKey, false);
         designDoc.addEventListener("keypress",doKeyPress, false);
         designDoc.addEventListener("click", domouseclick, false);
    }
  histmath[0]=currentmath;
}
function solvematrixdisplay(target1) // //The function is to solve the problem of matrix display
{
     var mmlns="http://www.w3.org/1998/Math/MathML";
   var d= designDoc.createDocumentFragment();
   var m = designDoc.createElementNS(mmlns,"math");
   m.setAttribute("xmlns",mmlns);
   var box = designDoc.createElementNS(mmlns,"mi");
   box.appendChild(designDoc.createTextNode("\u25A1"));
   m.appendChild(box);
   d.appendChild(m);
   target1.body.appendChild(d);
}
function doNew()
{
    if(getOs() == 1)
    {
        currentmath = newmathIE;
    }
    else if(getOs() == 2)
    {
        currentmath = newmathFF;
    }
    renew();
  savehist();
}
function renew()
{
    if(entermode == 0)
    {
        tempid = "";
        if(getOs() == 1)
        {
            designDoc.body.style.cursor = "url(image/"+tempid+".cur)";
        }
        else if(getOs() == 2)
        {
            designDoc.body.setAttribute("style","cursor:url('image/"+tempid+".cur'),default");
        }
        else
        {}
    }
    designDoc.body.innerHTML="";
   if(getOs()==1)
    {
        var outMathForIE = SetMactionTagToEveryLeafTag(currentmath);
        if(entermode == 1)
        {
            if(tempid!="")
            {
                  designDoc.body.style.cursor = "url(image/"+tempid+".cur)";
            }
           // alert(mmlparse);
        }
        var xmlDocmt = LoadXML(currentmath, 2);
        var ciNodes=xmlDocmt.getElementsByTagName("ci");
        if(ciNodes.length == 0)
        {
            xmlDocmt.getElementsByTagName(mml+"ci")
        }
        var cnNodes=xmlDocmt.getElementsByTagName("cn");
        if(cnNodes.length == 0)
        {
            xmlDocmt.getElementsByTagName(mml+"cn");
        }

        for(var i=0;i<ciNodes.length;i++)
        {
            ciNodes[i].attachEvent("onmouseover", domouseover);
        }
        for(var i=0;i<cnNodes.length;i++)
        {
            cnNodes[i].attachEvent("mouseover", domouseover);
        }
         designDoc.write(mmlparse+outMathForIE.xml+mmlparse1);
    }
    else
    {
       if(entermode == 1)
       {
        if(tempid!="")
        {
            designDoc.body.setAttribute("style","cursor:url('image/"+tempid+".cur'),default");
        }
      }
        timeout1();
    }
    var af=new Array();
  af=findfocus();
  var selectedMath;
  if(getOs()==1)
  {
   selectedMath=af[0].firstChild.xml;
   window.status=selectedMath.replace(/xmlns:mml="http:\/\/www.w3.org\/1998\/Math\/MathML"/i,"");
  }
  else
  {
   var ds=document.getElementById("selectedMath");
   ds.innerHTML="";
   var dsc=af[0].firstChild.cloneNode(true);
   ds.appendChild(dsc);
   selectedMath=ds.innerHTML;
   window.status=selectedMath;
  }
  //alert(Ext.getCmp('tabPanel').getActiveTab().title);
  chgsrcWhenActive(Ext.getCmp('tabPanel').getActiveTab())
  //chgsrc();
  disInfix();
  selectedInfix();
}
function chgsrc()
{
    var mmlcinput1=Ext.getCmp('mmlcinput');
    if(mmlcinput1!=null && mmlcinput1!="")
    {
      mmlcinput1.setValue(currentmath);
    }
  var mmlpinput=Ext.getCmp('mmlpinput');
  if(mmlpinput!=null && mmlpinput!="")
    {
    var mp=mctompStr(currentmath);
    mmlpinput.setValue(mp);
  }
  var openminput=Ext.getCmp('openminput');
  if(openminput!=null && openminput!="")
    {
   var mc=currentmath.replace(/mml:/g,'');
     mc="<?xml version='1.0' encoding='UTF-8'?>"+mc;
     if(getOs()!=1)
     {
        mc=mc.replace(/xmlns=/g,'xmlns:mml=');
     }
   var om=renderxx(mc,"style/cmmltoom.xsl");
   openminput.setValue(om);
  }
  var latexinput=Ext.getCmp('latexinput');
  if(latexinput!=null && latexinput!="")
    {
    tex=renderxx("<?xml version='1.0' encoding='UTF-8'?>"+currentmath,"style/mmltex.xsl");
    latexinput.setValue(tex);
  }

//* mathml content to brail
  var brlinput = Ext.getCmp('brailleinput');
    if(brlinput != null && brlinput != "")
    {
          var brlcrrntmth = currentmath;
          brlcrrntmth = brlcrrntmth.replace('xmlns="http://www.w3.org/1998/Math/MathML"',"");
          var brl = renderxx(brlcrrntmth,"style/mml2brl.xsl");
          brlinput.setValue(brl);
    }

 // */
  //////
}


function chgsrcWhenActive(tabobj)
{
    if(tabobj.title =="MathML-Content")
    {
        var mmlcinput1=Ext.getCmp('mmlcinput');
        if(mmlcinput1!=null && mmlcinput1!="")
        {
         mmlcinput1.setValue(currentmath);
        }
    }
    else if(tabobj.title =="MathML-Presentation")
    {
        var mmlpinput=Ext.getCmp('mmlpinput');
        if(mmlpinput!=null && mmlpinput!="")
        {
            var mp=mctompStr(currentmath);
            mmlpinput.setValue(mp);
        }
    }
    else if(tabobj.title =="OpenMath")
    {
        var openminput=Ext.getCmp('openminput');
        if(openminput!=null && openminput!="")
        {
            var mc=currentmath.replace(/mml:/g,'');
            mc="<?xml version='1.0' encoding='UTF-8'?>"+mc;
            if(getOs()!=1)
            {
                mc=mc.replace(/xmlns=/g,'xmlns:mml=');
            }
            var om=renderxx(mc,"style/cmmltoom.xsl");
            openminput.setValue(om);
        }
    }
    else if(tabobj.title== "LaTex")
    {
          var latexinput=Ext.getCmp('latexinput');
          if(latexinput!=null && latexinput!="")
          {
            tex=renderxx("<?xml version='1.0' encoding='UTF-8'?>"+currentmath,"style/mmltex.xsl");
            latexinput.setValue(tex);
          }
    }else if(tabobj.title == "Braille")
    {
        //* mathml content to brail
        var brlinput = Ext.getCmp('brailleinput');
        if(brlinput != null && brlinput != "")
        {
          var brlcrrntmth = currentmath;
          brlcrrntmth = brlcrrntmth.replace('xmlns="http://www.w3.org/1998/Math/MathML"',"");
          brlcrrntmth = brlcrrntmth.replace(/mml:/g,'');
          var brl = renderxx(brlcrrntmth,"style/mml2brl.xsl");
          brlinput.setValue(brl);
        }
    }
    else
    {
        alert("no tab with the title " +tabobj.title);
    }
 // */
  //////
}

function renderxx(mc,xslfile) //render xml(string) by using xsl(file).
{
     if(getOs()==1)
   {
        var oXslDom = new XslDom();
    oXslDom.async=false;
    oXslDom.load(xslfile);
    var oXslTemplate=new XSLTemplate();
    oXslTemplate.stylesheet = oXslDom;
    var oXslProcessor=oXslTemplate.createProcessor();
    var aVersions = [ "MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.5.0","MSXML2.DOMDocument.3.0",
       "MSXML2.DOMDocument","Microsoft.XmlDom"];
    for (var i = 0; i < aVersions.length; i++)
    {
       try {
             var xmlDoc3 = new ActiveXObject(aVersions[i]);
               xmlDoc3.async=false;
               xmlDoc3.loadXML(mc.toString());
         } catch (oError) {
        }
    }
    oXslProcessor.input=xmlDoc3;
    oXslProcessor.transform();
    return oXslProcessor.output.toString();
   }else
   {
    var myXMLHTTPRequest = new XMLHttpRequest();
    myXMLHTTPRequest.open("GET", xslfile, false);
    myXMLHTTPRequest.send(null);
    var mc2omxsl = myXMLHTTPRequest.responseXML;
    var xsltProcessor = new XSLTProcessor();
    xsltProcessor.importStylesheet(mc2omxsl);
    var parser = new DOMParser();
    var myDocument = parser.parseFromString(mc,"text/xml");
    var fragment = xsltProcessor.transformToFragment(myDocument, document);
    var xmls = new XMLSerializer();
      var xmlStr=xmls.serializeToString(fragment,"text/xml");
      return xmlStr;
   }
}
function inserttemp(tid)
{
    if(entermode==1)
    {
     tempid=tid;
     renew();
    }else
    {
        tempid=tid;
        execfunc();
    }
}
function timeout1()
{
  var mp=mctomp(currentmath);
  var mpfc=mp.firstChild;
  if(entermode==1)
  {
    var ciNodes=getElementsByTagName1(mpfc,"mi");
    var cnNodes=getElementsByTagName1(mpfc,"mn");
    for(var i=0;i<ciNodes.length;i++)
    {
      ciNodes[i].addEventListener("mouseover", domouseover, false);
    }
    for(var i=0;i<cnNodes.length;i++)
    {
        cnNodes[i].addEventListener("mouseover", domouseover, false);
    }
    mp.replaceChild(mpfc,mp.firstChild);
    //mp.firstChild.setAttribute("style","cursor:url('image/"+tempid+".cur'),default");

  }
    //designDoc.body.appendChild(mp);

        var tempstr = designDoc.body.innerHTML;
    if(tempstr == "")
    {
            designDoc.body.appendChild(mp);
    }
    else
    {       var dfrg = document.createDocumentFragment();
                dfrg.appendChild(tempstr);
                designDoc.body.appendChild(mp);
    }
}

////mouse click for changing the editing focus
function domouseclicknormal()
{
   var s;

    if(getOs()==1)
    {
        //alert(designDoc.body.createTextRange().text);
        //s = designDoc.selection.createRange().text;
        //s = designDoc.body.createTextRange().text;
        return;
    }
    else if(getOs() == 2)
    {
        s = designWin.getSelection();
    }
    if(editMode==2 || editMode==3 || editMode==4)return;
    if(editMode==1)
    {
     var cpnum1=s.focusNode.parentNode.getAttribute("id");
       var xmlDoc=createMSXML();
       var root=xmlDoc.documentElement;
       var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
       num_mstyles=mstyles.length;
       for (i = 0; i < num_mstyles; i++)
     {
        mstyle= mstyles.item(i);
        mstyleAttr = mstyle.getAttribute("tempinput");
       if(mstyleAttr=="1")
       {
         var focuson=mstyle;
        }
     }
     var focuschild1=focuson.firstChild;
     var cpnum2=focuschild1.getAttribute("id");
     if(cpnum1==cpnum2)
     {
       return;
      }
     else if(cpnum1!=null)
     {
         var nf=xmlDoc.getElementById(cpnum1);
         var nf1=nf.cloneNode(true);
         focuson.parentNode.replaceChild(focuschild1,focuson);
         var ms=createElement1(xmlDoc,"mstyle");
       ms.setAttribute("mathbackground","#00ff00");
       ms.setAttribute("tempinput","1");
       ms.appendChild(nf1);
       nf.parentNode.replaceChild(ms,nf);
       currentmath=getxml(root);
       renew();
     }
  }
}
function mctompStr(mc)
{
    if(getOs()==1)
 {
        var oXslDom = new XslDom();
    oXslDom.async=false;
    oXslDom.load("style/mathml.xsl");
    var oXslTemplate=new XSLTemplate();
    oXslTemplate.stylesheet = oXslDom;
    var oXslProcessor=oXslTemplate.createProcessor();
    var aVersions = [ "MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.5.0","MSXML2.DOMDocument.3.0",
       "MSXML2.DOMDocument","Microsoft.XmlDom"];
    for (var i = 0; i < aVersions.length; i++)
    {
       try {
             var xmlDoc3 = new ActiveXObject(aVersions[i]);
               xmlDoc3.async=false;
               xmlDoc3.loadXML(mc.toString());
         } catch (oError) {
        }
    }
    oXslProcessor.input=xmlDoc3;
    oXslProcessor.transform();
    return oXslProcessor.output.toString();
 }else
 {
     var mpDom=mctomp(mc);
     var xmls = new XMLSerializer();
     var xmlStr=xmls.serializeToString(mpDom,"text/xml");
     xmlStr=xmlStr.replace(/<a0:/gi,"<mml:");
     xmlStr=xmlStr.replace(/<\/a0:/gi,"</mml:");
     xmlStr=xmlStr.replace(/xmlns:a0\=/gi,"xmlns:mml\=");
     return xmlStr;
 }
}

function mctomp(mc)
{
    var xsltProcessor = new XSLTProcessor();
  xsltProcessor.importStylesheet(xslRef);
  var parser = new DOMParser();
  var myDocument = parser.parseFromString(mc.toString(),"text/xml");
  var fragment = xsltProcessor.transformToFragment(myDocument, document);
  return fragment;
}

function doOpen()
{
    var file1=window.showModalDialog("D:/new.mml","openfile","dialogWidth=540px;dialogHeight=540px");
  var msta=file1.match("<mml:math").index;
  var mend=file1.match("</mml:math>").index;
  currentmath=file1.substring(msta,mend+11);
  currentmath=addFocus();
  renew();
  savehist();
}

var bianLiang = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
Array.prototype.contains = function(val){
	for(var iIndex=0;iIndex<this.length;iIndex++){
		if(this[iIndex]==val){
			return true;
		}
	}
	return false;
}
function doSave()
{
 try{
 	
 	var value = $("#infixinputbox").val();
 	var newValue = "";
	value = value.split("");
	$.each(value,function(){
		if(bianLiang.contains(this)){
			newValue += "#{"+this+"}";
		}else{
			newValue += this;
		}	
	})
	newValue = newValue.replace(/(.+)\^\((.+)\/(.+)\)/g,"pow($1,$2/$3)");
	newValue = newValue.replace(/(#{[A-Z]})\^(\d+|#{[A-Z]})/g,"pow($1,$2)");
	newValue = newValue.replace(/(\d+)\^(\d+|#{[A-Z]})/g,"pow($1,$2)");
	newValue = newValue.replace(/(.+)\^(\d+|#{[A-Z]})/g,"pow($1,$2)");
	parentWindow = window.parent;
  	var p$ = parentWindow.$;
 	 p$("#editor").val(newValue);
 	 var html = Ext.getCmp('mmlcinput').getValue();
 	p$("#showLabFormula").val(base64encode(html));
 	
 }catch(e){
    alert(e.description);
 }
}
function doReturn()
{
    if(parent.opener==null)
    {
        alert("When MathEdit was not openned by newMath() methode,The return button can not be used");
      return;
    }
    cm1=new Array();
  cm1=getCode();
    try{
      parent.opener.mathedit.getMath(cm1);
  }catch(e){
  alert(e.message+e.lineNumber);
    }
}
function findfocus()
{
  var xmlDoc=createMSXML();
  var root=xmlDoc.documentElement;
  var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
  num_mstyles=mstyles.length;
  for (i = 0; i < num_mstyles; i++)
  {
    mstyle= mstyles.item(i);
    mstyleAttr = mstyle.getAttribute("tempinput");
    if(mstyleAttr=="1" || mstyleAttr=="2")
    {
       focuson=mstyle;
     }
  }
  var ra=new Array();
  ra[0]=focuson; ra[1]=root;ra[2]=xmlDoc;
  return ra;

}
function doCopy()
{
  var af=new Array();
  af=findfocus();
  copymath=af[0];
}
function doCut()
{
    var af=new Array();
  af=findfocus();
  copymath=af[0];
    doDelete();
}
function doPaste()
{
    if(editMode==2||editMode==3||editMode==4)
  {
     alert("This function can not be used in MathML Content or Infix Mode");
     return;
  }
    if(copymath==null)
    {
        return;
    }
    var af=new Array();
  af=findfocus();
  focuson=af[0];
    copymath1=addIdAttribute(copymath,1);
  focuson.parentNode.replaceChild(copymath1,focuson);
    currentmath=getxml(af[1]);
    renew();
  savehist();
}

function doStyle(kd,sty)
{
    if(editMode==2 || editMode==3 || editMode==4)
  {
     alert("This function can not be used in Code Mode");
     return;
  }
    var xmlDoc=createMSXML();
  var root=xmlDoc.documentElement;
  var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
  num_mstyles=mstyles.length;
  for (i = 0; i < num_mstyles; i++)
  {
    mstyle= mstyles.item(i);
    mstyleAttr = mstyle.getAttribute("tempstyle");
    if(mstyleAttr=="1")
    {
    fon=mstyle;
    }
  }
  if(kd=="va") //mathvariant
  {
       exsty = fon.getAttribute("mathvariant");
       if(exsty==sty)
       {
        fon.removeAttribute("mathvariant");
       }else if(exsty=="bold" && sty=="italic")
       {
         fon.setAttribute("mathvariant","bold-italic");
       }
       else if(exsty=="italic" && sty=="bold")
       {
         fon.setAttribute("mathvariant","bold-italic");
       }else
       {
         fon.setAttribute("mathvariant",sty);
       }
  }
  if(kd=="si")//mathsize
  {
    if(sty=="Size")return;
    if(sty=="-1")
    {
            ifs=prompt("Please Input Font Size (only the numeric):",20)
            if(ifs==null)return;
            sty=parseInt(ifs);
        if(isNaN(sty))
        {
          alert("please only input the numeric, not include the pt,%,etc.");
            return;
          }
        }
    fon.setAttribute("mathsize",sty+"pt");
   }
   if(kd=="co") //mathcolor and mathbackground
   {
       if(sty=="f")
         var colorwin=window.open("color.html","Set_ForeColor","toolbar=no,menubar=no,personalbar=no,width=250,height=190,scrollbars=no,resizable=no");
       if(sty=="b")
       var colorwin=window.open("color.html","Set_BackgroundColor","toolbar=no,menubar=no,personalbar=no,width=250,height=190,scrollbars=no,resizable=no");
       colorwin.focus();
         return;
   }
   if(kd=="fa") //fontfamily
   {
    fon.setAttribute("fontfamily",sty);
   }
  currentmath=getxml(root);
  renew();
  savehist();
}
function setColor(colorvalue,obj)
{
   if(editMode==2 || editMode==3 || editMode==4)
  {
     alert("This function can not be used in Code Mode");
     return;
  }
    var xmlDoc=createMSXML();
  var root=xmlDoc.documentElement;
  var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
  num_mstyles=mstyles.length;
  for (i = 0; i < num_mstyles; i++)
  {
    mstyle= mstyles.item(i);
    mstyleAttr = mstyle.getAttribute("tempstyle");
    if(mstyleAttr=="1")
    {
    fon=mstyle;
    }
  }
  if(obj=="Set_ForeColor")
  {
     fon.setAttribute("mathcolor",colorvalue);
  }
  if(obj=="Set_BackgroundColor")
  {
    fon.setAttribute("mathbackground",colorvalue);
  }
  currentmath=getxml(root);
  renew();
  savehist();
}

function selectedMathml()
{
    var dlg1=window.open("selectedMathml.html","Selected_MathML_Content_Code","toolbar=no,menubar=no,personalbar=no,width=550,height=250,scrollbars=no,resizable=yes");
}
function setSelectedMathml(openWin)
{
    var af=new Array();
  af=findfocus();
  var selectedMath1=getxml(af[0].firstChild);
  if(getOs()==1)
  {
    selectedMath1=selectedMath1.replace(/xmlns:mml="http:\/\/www.w3.org\/1998\/Math\/MathML"/i,"");
  }
    if(openWin!=null)
    {
        openWin.document.f6.mathcode.value=selectedMath1;
        return;
  }
}
function returnSelectedMathml(Str)
{
  var af=new Array();
  af=findfocus();
  Str='<mml:mstyle mathbackground="#00ff00" tempinput="1" xmlns:mml="http://www.w3.org/1998/Math/MathML">'+Str+'</mml:mstyle>';
  var xmlDoc1=createMSXML(Str);
  var root1=xmlDoc1.documentElement;
  addIdAttribute(root1,0);
  af[0].parentNode.replaceChild(root1,af[0]);
  currentmath=getxml(af[1]);
  renew();
  savehist();
}

//zyj
function infixfocus()
{
     editMode=6;
}
function infixblur()
{
     editMode=1;
}
function selectedInfix()
{
 cm1=new Array();
 cm1=getCode();
 currentmath1=cm1[0];
 var af=new Array();
 af=findfocus();
 currentmath1=getxml(af[1]);
 var selectedMath1=getxml(af[0].firstChild);
 sMathml=selectedMath1.replace(/xmlns:mml="http:\/\/www.w3.org\/1998\/Math\/MathML"/i,"");
 var selectedMathml='<mml:math xmlns:mml="http://www.w3.org/1998/Math/MathML">'+sMathml.substr(0,sMathml.length)+'</mml:math>';
 var outmathml=strConvert(selectedMathml);
 var infixinputbox1=Ext.getCmp('sinfixinputbox');
 if(infixinputbox1!=null) infixinputbox1.setValue(outmathml);
}
function disInfix()
{
 cm1=new Array();
 cm1=getCode();
 currentmath1=cm1[0];
 var outmathml=strConvert(currentmath1);
 var infixinputbox1=Ext.getCmp('infixinputbox');
 //alert(infixinputbox1.getValue());
 if(infixinputbox1!=null) infixinputbox1.setValue(outmathml);
}
function disInfixChange()
{
    var infixinputbox1=Ext.getCmp('infixinputbox');
    var iv=infixinputbox1.getValue();
    var c=currentmath;
    currentmath=addprefix(iv);
    if(getOs()>=2)
    {
     designDoc.body.innerHTML="";
     solvematrixdisplay(designDoc);
    }
    renew();
    var c1=c.replace(/id="."/gi,"");
    var c2=currentmath.replace(/id="."/gi,"");
    if(c1!=c2)
    {
     savehist();
    }
}
function selectedInfixChange(){
 var infixinputbox1=Ext.getCmp('sinfixinputbox');
 var iv=infixinputbox1.getValue();
 var result=convertToContentDOM(iv);
 var af=new Array();
 af=findfocus();
 var focuson=af[0];
 var xmlDoc=createMSXML();
 var s=createElement1(xmlDoc,"mstyle");
  s.setAttribute("mathbackground","#00ff00");
  s.setAttribute("tempinput","1");
  var result1=result.cloneNode(true);
  s.appendChild(result1);
 focuson.parentNode.replaceChild(s,focuson);
 var root1=addIdAttribute(af[1],0);
 currentmath=getxml(root1);
 currentmath=addfn(currentmath);
 renew();
 savehist();
}

function addIdAttribute(node1,change)
{
    var ciNodes=getElementsByTagName1(node1,"ci");
    var cnNodes=getElementsByTagName1(node1,"cn");
    for(var i=0;i<ciNodes.length;i++)
    {

        if(ciNodes[i].id!=null && change==0)
        {
      }
        else
      {
        ciNodes[i].setAttribute("id",++idnum);
      }
    }
    for(var i=0;i<cnNodes.length;i++)
    {
        if(cnNodes[i].id!=null && change==0)
        {
      }
        else
      {
        cnNodes[i].setAttribute("id",++idnum);
      }
    }
    return node1;
}

function insertChar(ic,kd)
{
     var xmlDoc=createMSXML();
     var root=xmlDoc.documentElement;
     var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
     num_mstyles=mstyles.length;
     for (i = 0; i < num_mstyles; i++)
   {
     mstyle= mstyles.item(i);
     mstyleAttr = mstyle.getAttribute("tempinput");
     if(mstyleAttr=="1" || mstyleAttr=="2")
     {
        focuson=mstyle;
     }
   }
  focuschild=focuson.firstChild;
  var focusparent=focuson.parentNode;
  var oldchar;
  oldchar=getxml(focuschild.firstChild);
  var s=createElement1(xmlDoc,"mstyle");
  s.setAttribute("mathbackground","#00ff00");
  s.setAttribute("tempinput","1");
  if(focuschild.tagName=="mml:ci" ||focuschild.tagName=="ci")
  {
    if(oldchar.charCodeAt(0)!=9633)
    {
      ic=oldchar+ic;
      kd=1;
    }
  }else if(focuschild.tagName=="mml:cn" ||focuschild.tagName=="cn")
  {
    if(kd==1)
    {
      kd=3;
    }else if(oldchar.charCodeAt(0)!=9633)
    {
      ic=oldchar+ic;
    }
  }
  if(kd==1)
  {
    var box0 = createElement1(xmlDoc,"ci");
    box0.appendChild(xmlDoc.createTextNode(ic));
    box0.setAttribute("id",++idnum);
    s.appendChild(box0);
    focusparent.replaceChild(s,focuson);
  }
  if(kd==2)
  {
    var box0 = createElement1(xmlDoc,"cn");
    box0.appendChild(xmlDoc.createTextNode(ic));
    box0.setAttribute("id",++idnum);
    s.appendChild(box0);
    focusparent.replaceChild(s,focuson);
  }
  if(kd==3)
  {
    var ap0 =createElement1(xmlDoc,"apply");
    var times = createElement1(xmlDoc,"times");
    var box0 = createElement1(xmlDoc,"ci");
    box0.appendChild(xmlDoc.createTextNode(ic));
    box0.setAttribute("id",++idnum);
    s.appendChild(box0);
    ap0.appendChild(times);
    ap0.appendChild(focuschild);
    ap0.appendChild(s);
    focusparent.replaceChild(ap0,focuson);
  }
  currentmath=getxml(root);
  renew();
  savehist();
}

function insertElement(de,cp)
{
   var xmlDoc=createMSXML();
     var root=xmlDoc.documentElement;
     var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
     num_mstyles=mstyles.length;
     for (i = 0; i < num_mstyles; i++)
   {
   mstyle= mstyles.item(i);
   mstyleAttr = mstyle.getAttribute("tempinput");
   if(mstyleAttr=="1" || mstyleAttr=="2")
   {
    focuson=mstyle;
    }
   }
  var ap0 = createElement1(xmlDoc,"apply");
  var cde = createElement1(xmlDoc,de);
  var s=createElement1(xmlDoc,"mstyle");
  s.setAttribute("mathbackground","#00ff00");
  s.setAttribute("tempinput","1");
  var box0 = createElement1(xmlDoc,"ci");
  box0.appendChild(xmlDoc.createTextNode("\u25A1"));
  var box1 = createElement1(xmlDoc,"ci");
  box1.appendChild(xmlDoc.createTextNode("\u25A1"));
  var box2 = createElement1(xmlDoc,"ci");
  box2.appendChild(xmlDoc.createTextNode("\u25A1"));
  ap0.appendChild(cde);
  var bvar1=createElement1(xmlDoc,"bvar");
  var ciBvar=createElement1(xmlDoc,"ci");
  ciBvar.setAttribute("type","bvar");
  bvar1.appendChild(ciBvar);
  lowlimit1=createElement1(xmlDoc,"lowlimit");
  uplimit1=createElement1(xmlDoc,"uplimit");
  var ap1 = createElement1(xmlDoc,"apply");

  if(cp==1)
  {
     s.appendChild(focuson.firstChild);
     ap0.appendChild(s);
  }
  if(cp==2)
  {
    box0.setAttribute("id",++idnum);

    if(de=="power" || de=="divide")
    {
        var phchar=focuson.firstChild.firstChild.nodeValue.charCodeAt();
      if(focuson.firstChild.tagName.search("ci")!=-1 && phchar==9633)
      {
        s.appendChild(focuson.firstChild);
        ap0.appendChild(s);
        ap0.appendChild(box0);
      }else
      {
        ap0.appendChild(focuson.firstChild);
        s.appendChild(box0);
        ap0.appendChild(s);
      }
    }
    else
    {
      ap0.appendChild(focuson.firstChild);
      s.appendChild(box0);
      ap0.appendChild(s);
    }
  }
  if(cp==6)
  {
     s.appendChild(focuson.firstChild);
     var ap3 = createElement1(xmlDoc,"fn");
     var ap4 = createElement1(xmlDoc,"apply");
     ap3.appendChild(cde);
     ap4.appendChild(ap3);
     ap4.appendChild(s);
  }
  if(cp==7) //forall,exists
  {
    box0.setAttribute("id",++idnum);
    box1.setAttribute("id",++idnum);
    ciBvar.appendChild(box1);
    ap0.appendChild(bvar1);
    s.appendChild(box0);
    ap0.appendChild(s);
  }
  if(cp==8)      //root
  {
    var degree= createElement1(xmlDoc,"degree");
    box0.setAttribute("id",++idnum);
    degree.appendChild(box0);
    s.appendChild(focuson.firstChild);
    ap0.appendChild(degree);
    ap0.appendChild(s);
  }
  if(cp==9)  //log
  {
    var logbase= createElement1(xmlDoc,"logbase");
    box0.setAttribute("id",++idnum);
    logbase.appendChild(box0);
    s.appendChild(focuson.firstChild);
    ap0.appendChild(logbase);
    ap0.appendChild(s);
  }
  if(cp==10)  //sum(2),product(2)
  {
    domain0=createElement1(xmlDoc,"domainofapplication");
    s.appendChild(box0);
    domain0.appendChild(s);
    ap0.appendChild(domain0);
    box0.setAttribute("id",++idnum);
    ap0.appendChild(focuson.firstChild);

  }
  if(cp==11)   // int(2)
  {
    lowlimit1.appendChild(s);
    s.appendChild(focuson.firstChild);
    ap1=createElement1(xmlDoc,"apply");
    ap1=createElement1(xmlDoc,"apply");
    var cde1 = createElement1(xmlDoc,de);
    box0.setAttribute("id",++idnum);
    ciBvar.appendChild(box0);
    ap1.appendChild(cde1);
    ap1.appendChild(bvar1);
    ap1.appendChild(lowlimit1);
    box1.setAttribute("id",++idnum);
    ap1.appendChild(box1);
    ap0.appendChild(ap1);
  }
  if(cp==12)   // sum(3); product(2); int; limit
  {
    s.appendChild(focuson.firstChild);
    lowlimit1.appendChild(s);
    box0.setAttribute("id",++idnum);
    uplimit1.appendChild(box0);
    if(de=="int" || de=="limit")
    {
        ciBvar.appendChild(s);
        ap0.appendChild(bvar1);
        box1.setAttribute("id",++idnum);
        lowlimit1.appendChild(box1);
    }
    ap0.appendChild(lowlimit1);
    if(de!="limit")
    { lowlimit1.appendChild(s);
        box1.setAttribute("id",++idnum);
        ciBvar.appendChild(box1);
        ap0.appendChild(uplimit1);
    }
    box2.setAttribute("id",++idnum);
    ap0.appendChild(box2);
  }
  if(cp==13 || cp==14 )
  {
    if(cp==13)
    {
        rs1=3;cs1=3;
    }
    if(cp==14)
    {
      var rs=prompt("Please Input the Number of Rows:","5");
      var cs=prompt("Please Input the Number of Columns:","4");
      if(rs==null || cs==null)return;
      rs1=parseInt(rs);
      cs1=parseInt(cs);
      if(isNaN(rs1))
      {
        alert("Your input row is not a number.");return;
      }
    }

    if(isNaN(cs1))
    {
        alert("Your input column is not a number.");return;
    }
    matrow=new Array();
    matcol=new Array();
    for(var j=0;j<cs1*rs1;j++)
    {
        matcol[j]=createElement1(xmlDoc,"ci");
        matcol[j].setAttribute("id",++idnum);
        matcol[j].appendChild(xmlDoc.createTextNode("\u25A1"));
    }
    for(var i=0;i<rs1;i++)
    {
        matrow[i]=createElement1(xmlDoc,"matrixrow");
        matcol[i]=new Array();
        for(j=0;j<cs1;j++)
      {
          matcol[i][j]=createElement1(xmlDoc,"ci");
           matcol[i][j].setAttribute("id",++idnum);
          matcol[i][j].appendChild(xmlDoc.createTextNode("\u25A1"));
          if(i==0 && j==0)
          {
            s.appendChild(matcol[i][j]);
            matrow[i].appendChild(s);
          }else
          {
            matrow[i].appendChild(matcol[i][j]);
          }
      }
        cde.appendChild(matrow[i]);
    }

  }
  if(cp==15)
  {
        var cs=prompt("Please Input the Number of Columns:","4");
        if(cs==null)return;
        cs1=parseInt(cs);
        if(isNaN(cs1))
      {
          alert("Your input column is not a number.");return;
      }
      var verc=new Array();
        for(i=0;i<cs1;i++)
      {
          verc[i]=createElement1(xmlDoc,"ci");
          verc[i].setAttribute("id",++idnum);
          verc[i].appendChild(xmlDoc.createTextNode("\u25A1"));
        if(i==0)
          {
            s.appendChild(verc[i]);
            cde.appendChild(s);
          }
          else
               cde.appendChild(verc[i]);

      }

    }
  if(cp>=16 && cp<=19)
  {
    box0.setAttribute("id",++idnum);
    s.appendChild(box0);
    switch(cp)
    {
        case 16:
          cde.setAttribute("closure","closed"); break;
        case 17:
          cde.setAttribute("closure","open-closed"); break;
        case 18:
          cde.setAttribute("closure","closed-open"); break;
        case 19:
          cde.setAttribute("closure","open"); break;
    }
    cde.appendChild(focuson.firstChild);
    cde.appendChild(s);
  }
  if(cp==20) //*
  {
      var tn=focuson.parentNode.firstChild.tagName;
      var su=0;
      if(tn.search(de)!=-1)
      {
        box0.setAttribute("id",++idnum);
        s.appendChild(box0);
        su=1;
      }else
      {
       ap0.appendChild(focuson.firstChild);
       box0.setAttribute("id",++idnum);
       s.appendChild(box0);
       ap0.appendChild(s);
      }
  }
  if(cp==21) //-
  {
      var focusparent=focuson.parentNode;
      var fp=focusparent;
      var tn=focusparent.firstChild.tagName;
      var su=0;
      if(tn.search("plus")!=-1 || tn.search("minus")!=-1)
      {
        focusparent.replaceChild(focuson.firstChild,focuson);
        var sem = createElement1(xmlDoc,"semantics");
        var fpc=focusparent.cloneNode(true);
        sem.appendChild(fpc);
        ap0.appendChild(sem);
        box0.setAttribute("id",++idnum);
        s.appendChild(box0);
        ap0.appendChild(s);
        su=1;
      }else if(tn.search("times")!=-1)
      {
        var fptg=focusparent.parentNode.firstChild.tagName;
        if(fptg.search("plus")!=-1 || fptg.search("minus")!=-1)
        {
            focusparent.replaceChild(focuson.firstChild,focuson);
            var sem = createElement1(xmlDoc,"semantics");
            var fppc=focusparent.parentNode.cloneNode(true);
          sem.appendChild(fppc);
          ap0.appendChild(sem);
          box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          ap0.appendChild(s);
          su=2;
        }else
        {
            focusparent.replaceChild(focuson.firstChild,focuson);
            var fpc=focusparent.cloneNode(true);
          ap0.appendChild(fpc);
          box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          ap0.appendChild(s);
          su=1;
        }
      }else
      {
          ap0.appendChild(focuson.firstChild);
          box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          ap0.appendChild(s);
          su=3;
      }
  }
  if(cp==22) //+
  {
      var fp=focuson.parentNode;
      var tn=fp.firstChild.tagName;
      var su=0;
      if(tn.search("plus")!=-1)
      {
        box0.setAttribute("id",++idnum);
        s.appendChild(box0);
        su=1;
      }else if(tn.search("times")!=-1)
      {
        var fptg=fp.parentNode.firstChild.tagName;
        if(fptg.search("plus")!=-1)
        {
            box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          su=2;
        }else if(fptg.search("minus")!=-1)
        {
            fp.replaceChild(focuson.firstChild,focuson);
          var sem = createElement1(xmlDoc,"semantics");
          var fppc=fp.parentNode.cloneNode(true);
          sem.appendChild(fppc);
          ap0.appendChild(sem);
          box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          ap0.appendChild(s);
          su=3;
        }else
        {
            fp.replaceChild(focuson.firstChild,focuson);
            var fpc=fp.cloneNode(true);
          ap0.appendChild(fpc);
          box0.setAttribute("id",++idnum);
          s.appendChild(box0);
          ap0.appendChild(s);
          su=4;
        }
      }else if(tn.search("minus")!=-1)
      {
        fp.replaceChild(focuson.firstChild,focuson);
        var sem = createElement1(xmlDoc,"semantics");
        var fpc=fp.cloneNode(true);
        sem.appendChild(fpc);
        ap0.appendChild(sem);
        box0.setAttribute("id",++idnum);
        s.appendChild(box0);
        ap0.appendChild(s);
        su=4;
      }else
      {
         ap0.appendChild(focuson.firstChild);
         box0.setAttribute("id",++idnum);
         s.appendChild(box0);
         ap0.appendChild(s);
      }
  }
  var focusparent=focuson.parentNode;
  if(cp>=13 && cp<=19)
  {
    focusparent.replaceChild(cde,focuson);
  }else if(cp==20)
  {
    if(su==1)
    {
        focusparent.replaceChild(focuson.firstChild,focuson);
        focusparent.appendChild(s);
    }else
    {
        focusparent.replaceChild(ap0,focuson);
    }
  }else if(cp==21)
  {
    if(su==1)
    {
        fp.parentNode.replaceChild(ap0,fp);
    }else if(su==2)
    {
        var fpp=fp.parentNode;
        fpp.parentNode.replaceChild(ap0,fpp);
    }else if(su==3)
    {
        focusparent.replaceChild(ap0,focuson);
    }
  }else if(cp==22)
  {
    if(su==1)
    {
        focusparent.replaceChild(focuson.firstChild,focuson);
        focusparent.appendChild(s);
    }else if(su==2)
    {
        focusparent.replaceChild(focuson.firstChild,focuson);
        focusparent.parentNode.appendChild(s);
    }else if(su==3)
    {
        fp.parentNode.parentNode.replaceChild(ap0,fp.parentNode);
    }else if(su==4)
    {
        fp.parentNode.replaceChild(ap0,fp);
    }else if(su==5)
    {
        fp.parentNode.replaceChild(ap0,fp);
    }else if(su==0)
    {
        focusparent.replaceChild(ap0,focuson);
    }
  }else if(cp==6)
  {
    focusparent.replaceChild(ap4,focuson);
  }else
  {
    focusparent.replaceChild(ap0,focuson);
    }
  currentmath=getxml(root);
  renew();
  savehist();
}

function doEnter()
{
    var af=new Array();
  af=findfocus();
  var focuson=af[0];
  var root=af[1];
  var xmlDoc=af[2];
  var ps=focuson;
  var ps1=ps.previousSibling;
  while(ps1!=null)
  {
    ps=ps1;
    ps1=ps.previousSibling;
  }
  var pt=ps.tagName;
  if(pt=="mml:plus" || pt=="mml:times" || pt=="mml:and" ||pt=="mml:or" || pt=="mml:xor"|| pt=="mml:union"|| pt=="mml:intersect"|| pt=="mml:cartesianproduct")
  {

   var s=createElement1(xmlDoc,"mstyle");
   s.setAttribute("mathbackground","#00ff00");
   s.setAttribute("tempinput","1");
   var box0 = createElement1(xmlDoc,"ci");
   box0.appendChild(xmlDoc.createTextNode("\u25A1"));
   s.appendChild(box0);
   var fc=focuson.firstChild;
   var fp=focuson.parentNode;
   fp.replaceChild(fc,focuson);
   fp.appendChild(s);
  }
  currentmath=getxml(root);
  renew();
  savehist();
}

//[section3]:
function doF34key(a)
{
    if(a==3)
    {
        f34key=3;
    }
    if(a==5)
    {
        f34key=5;
    }
}

function doKeyPress(evt1, a)
{
        var evt, ic, kd; //ci: kd=1; cn: kd=2;
        if(a == 1)
        {
                evt = window.event ? window.event : evt1;
        }
        else
        {
                evt = designWin.event ? designWin.event : evt1;
        }
        unicode = evt.charCode ? evt.charCode : evt.keyCode;
}

function choosePlusEquals()
{
        if(unicode == 43 || unicode == null)
        {
                insertElement("plus", 22);
                ic = "operation";
         }
         else
         {
                insertElement("eq", 2);
                ic = "operation";
         }

}
function doKey(evt1,a)
{
  var evt,ic,kd;  //ci: kd=1; cn: kd=2
  if(a==1)
  {
    evt=window.event ? window.event : evt1;
  }else{
    evt=designWin.event?designWin.event:evt1;
  }
 if(editMode==2||editMode==4||editMode==6)
 {
  if(evt.keyCode==8) return;
  return;
 }
 //alert(evt.keyCode);
  if(f34key==3 )
 {
    switch(evt.keyCode)
    {
      case 18:
        return;
      case 114:
        return;
      case 65:
        insertElement('arcsin',6); break;
      case 67:
        insertElement('cos',6); break;
      case 68:
        insertElement('arcsec',6); break;
      case 69:
        insertElement('sec',6); break;
      case 70:
        insertElement('arccsc',6); break;
      case 71:
        insertElement('arccot',6); break;
      case 73:
        insertElement('sin',6); break;
      case 78:
        insertElement('arctan',6); break;
      case 79:
        insertElement('cot',6); break;
      case 82:
        insertElement('arccos',6); break;
      case 83:
        insertElement('csc',6); break;
      case 84:
        insertElement('tan',6); break;
     }
    f34key=0;
    return;
 }
  if(f34key==5)
 {
    switch(evt.keyCode)
    {
      case 18:
        return;
      case 116:
        return;
      case 65:
        insertChar('\u03B1',1); break;
      case 66:
        insertChar('\u03B2',1); break;
      case 67:
        insertChar('\u03C8',1); break;
      case 68:
        insertChar('\u03B4',1); break;
      case 69:
        insertChar('\u03B5',1); break;
      case 71:
        insertChar('\u03B3',1); break;
      case 72:
        insertChar('\u03C6',1); break;
      case 73:
        insertChar('\u221E',1); break;
      case 76:
       insertChar('\u03BB',1); break;
      case 77:
        insertChar('\u03BC',1); break;
      case 78:
       insertChar('\u03B7',1); break;
      case 79:
        insertChar('\u03C9',1); break;
      case 80:
        insertChar('\u03C0',1); break;
      case 82:
        insertChar('\u03C1',1); break;
      case 83:
        insertChar('\u03C3',1); break;
      case 84:
        insertChar('\u03B8',1); break;
     }
    f34key=0;
    return;
 }
 if(evt.ctrlKey==1)
  {
    switch(evt.keyCode)
    {
        case 38:
          goTop(); break;
        case 49:
        doContent(); break;
      case 50:
        doPresentation(); break;
      case 67:
        doCopy(); break;
      case 69:
        doDesign(); break;
      case 71:
        doStyle('co','b'); break;
      case 75:
        doStyle('va','italic'); break;
      case 78:
        doNew(); break;
      case 79:
        doOpen(); break;
      case 81:
        doStyle('va','bold'); break;
      case 80:
        doPreview(); break;
      case 82:
        doReturn(); break;
      case 83:
        doSave(); break;
      case 84:
        doStyle('co','f'); break;
      case 86:
        doPaste(); break;
      case 88:
        doCut(); break;
      case 89:
        redo(); break;
      case 90:
        undo(); break;

    }
    return;
  }
  if(evt.altKey==1)
  {
    if(evt.shiftKey==1)
    {
        switch(evt.keyCode)
      {
        case 48:
          insertElement('interval',19); break;
          case 49:
          insertElement('neq',2); break;
        case 50:
          insertElement('product',1); break;
        case 51:
          insertElement('product',12); break;
        case 55:
          insertElement('and',2); break;
        case 57:
          insertElement('interval',17); break;
        case 220:
          insertElement('or',2); break;
      }return;
    }
    if(evt.keyCode==114)
    {
        doF34key(3);return;
    }
    if(evt.keyCode==116)
    {
        doF34key(5);return;
    }
    switch(evt.keyCode)
    {
        case 48:
        insertChar('\u2205',1); break;
        case 49:
        insertElement('not',1); break;
      case 50:
        insertElement('sum',1); break;
      case 51:
        insertElement('sum',10); break;
      case 52:
        insertElement('sum',12); break;
      case 53:
        insertElement('matrix',14); break;
      case 54:
      insertElement('power',2); break;
      case 55:
        insertElement('intersect',2); break;
      case 56:
        insertElement('cartesianproduct',2); break;
      case 57:
        insertElement('implies',2); break;
      case 107:  //firefox '='
         insertElement('equivalent',2); break;
      case 61:  //firefox '='
         insertElement('equivalent',2); break;
      case 65:
        insertElement('abs',1); break;
      case 66:
        insertElement('notin',2); break;
      case 67:
        insertElement('ceiling',1); break;
      case 68:
        insertElement('diff',7); break;
      case 69:
        insertElement('exists',7); break;
      case 70:
        insertElement('floor',1); break;
      case 71:
        insertElement('log',9); break;
      case 72:
        insertElement('int',12); break;
      case 73:
        insertElement('in',2); break;
      case 74:
       insertElement('notprsubset',2); break;
      case 75:
       insertElement('notsubset',2); break;
      case 76:
       insertElement('log',1); break;
      case 77:
        insertElement('limit',12); break;
        case 78:
        insertElement('ln',1); break;
      case 79:
        insertElement('card',1); break;
      case 80:
        insertElement('prsubset',2); break;
      case 81:
        insertElement('partialdiff',7); break;
      case 82:
        insertElement('root',8); break;
      case 83:
        insertElement('root',1); break;
      case 84:
        insertElement('matrix',13); break;
      case 85:
        insertElement('subset',2); break;
      case 86:
        insertElement('vector',15); break;
      case 87:
        insertElement('int',11); break;
      case 88:
       insertElement('xor',2); break;
      case 89:
      insertElement('forall',7); break;
      case 90:
       insertElement('factorof',2); break;
      case 109:
        insertElement('minus',1); break;
      case 187:
        insertElement('equivalent',2); break;
      case 188:
        insertElement('leq',2); break;
      case 189:
        insertElement('minus',1); break;
      case 190:
        insertElement('geq',2); break;
      case 191:
        insertElement('setdiff',2); break;
      case 219:
        insertElement('interval',18); break;
      case 220:
        insertElement('union',2); break;
      case 221:
        insertElement('interval',16); break;
    }return;
  }
  if(evt.shiftKey==1)
  {
    switch(evt.keyCode)
    {
        case 16:
           ic="shift";  break;
        case 48:
           ic=")"; kd=1; break;
        case 49:
          insertElement('factorial',1);ic="operation";break;
        case 50:
           ic="@"; kd=1; break;
        case 51:
        case 52:
        case 53:
           ic=String.fromCharCode(evt.keyCode-16);kd=1;
           break;
        case 54:
           insertElement('power',2); break;
        case 55:
           ic="&"; kd=1; break;
        case 56:
           insertElement("times",20); ic="operation"; break;
        case 57:
           ic="("; kd=1; break;
        case 61:
           insertElement("plus",22); ic="operation";  break;
        case 107:
           insertElement("plus",22); ic="operation";  break;
        case 109:
            ic="_"; kd=1; break;
        case 186:
         ic=":"; kd=1; break;
        case 187:
           insertElement("plus",22); ic="operation";  break;
        case 188:
           insertElement("lt",2); ic="operation"; break;
      case 189:
            ic="_"; kd=1; break;
        case 190:
           insertElement("gt",2); ic="operation"; break;
      case 191:
            ic="?"; kd=1; break;
      case 192:
          insertElement('approx',2); ic="operation"; break;
      case 219:
         ic="{"; kd=1; break;
      case 220:
         ic="|"; kd=1; break;
      case 221:
         ic="}"; kd=1; break;
      case 222:
           ic="\""; kd=1; break;
      }
    if(evt.keyCode>=65 && evt.keyCode<=90 )//uppercase
    {
      ic=String.fromCharCode(evt.keyCode);
      kd=1;
    }
  }else //not press shift key
  {
    switch(evt.keyCode)
    {
        case 107:
           //insertElement("eq",2); ic="operation"; break;
           choosePlusEquals();break;
        case 106:
           insertElement("times",20); ic="operation"; break;
        case 61:
           insertElement("plus",22); ic="operation"; break;
        case 109:
           insertElement("minus",21); ic="operation"; break;
      case 111:
           insertElement("divide",2); ic="operation"; break;
        case 186:
         ic=";"; kd=1; break;
        case 187:
           insertElement("eq",2); ic="operation"; break;
      case 188:
         ic=","; kd=1; break;
      case 189:
           insertElement("minus",21); ic="operation"; break;
          case 110:
      case 190:
           ic="."; kd=2; break;
        case 191:
           insertElement("divide",2); ic="operation"; break;
        case 219:
         ic="["; kd=1; break;
      case 220:
         ic="\\"; kd=1; break;
      case 221:
         ic="]"; kd=1; break;
        case 222:
           ic="'"; kd=1; break;

    }
    if(evt.keyCode>=65 && evt.keyCode<=90)//lowercase
    {
         ic=String.fromCharCode(evt.keyCode+32);
         kd=1;
    }
    if(evt.keyCode >=48 && evt.keyCode<=57)//numeric key
    {
         ic=String.fromCharCode(evt.keyCode);
         kd=2;
    }
    if(evt.keyCode >=96 && evt.keyCode<=105)//numeric key
    {
         ic=String.fromCharCode(evt.keyCode-48);
         kd=1;
    }
  }
  if(evt.keyCode>=37 && evt.keyCode<=40 || evt.keyCode==34 || evt.keyCode==33 || evt.keyCode==9)
  {
    doNavigate(evt.keyCode);
  }else if(evt.keyCode==8 || evt.keyCode==46)
  {
        doDelete(evt.keyCode);
    }else if(evt.keyCode==13)
  {
        doEnter();
    }else if(ic!="shift" && ic!="operation" && ic!=undefined)
  {
      insertChar(ic,kd);
  }
}
function isdragdrop()
{
    var tdde=Ext.getCmp('tdragdrop');
    if(entermode==0)
    {
     tdde.setIconClass('icon_dd');
     entermode=1;
    }
    else
    {
     tdde.setIconClass('icon_dd1');
     entermode=0;
    }
    renew();
}
function domouseclick()
{
   if(entermode==1)
   {
    execfunc();
   }else
   {
    domouseclicknormal();
   }
}
function execfunc()
{
     var tfile="templates.xml";
   if(tempid!="")
   {
    var func=getcmlitem(tfile,tempid,"action","s");
   // tempid="";
    eval(func);
   }
}
function domouseover(evt1)
{
    var cpnum1;

    if(getOs()==1)
    {
        cpnum1 = evt1.srcElement.getAttribute("id");
        //return;
    }
    else if(getOs() == 2)
    {
        cpnum1=evt1.target.getAttribute("id");
    }
    if(editMode==2 || editMode==3 || editMode==4)return;
    if(editMode==1)
    {
       var xmlDoc=createMSXML();
       var root=xmlDoc.documentElement;
       var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
       num_mstyles=mstyles.length;
       for (i = 0; i < num_mstyles; i++)
     {
        mstyle= mstyles.item(i);
        mstyleAttr = mstyle.getAttribute("tempinput");
       if(mstyleAttr=="1")
       {
         var focuson=mstyle;
        }
     }
     var focuschild1=focuson.firstChild;
     var cpnum2=focuschild1.getAttribute("id");
     if(cpnum1==cpnum2)
     {
       return;
     }
     else if(cpnum1!=null)
     {
         var nf=xmlDoc.getElementById(cpnum1);
         var nf1=nf.cloneNode(true);
         focuson.parentNode.replaceChild(focuschild1,focuson);
         var ms=createElement1(xmlDoc,"mstyle");
       ms.setAttribute("mathbackground","#00ff00");
       ms.setAttribute("tempinput","1");
       ms.appendChild(nf1);
       nf.parentNode.replaceChild(ms,nf);
       currentmath=getxml(root);
       renew();
     }
  }
}
function goTop()
{
    cm1=new Array();
  cm1=getCode();
    currentmath=cm1[0];
    currentmath=addFocus();
    renew();
}
function getElementsByTagName1(xmlDoc1,tagName1)
{
        var tagName2=mml+tagName1;
        var et=xmlDoc1.getElementsByTagName(tagName2);
        if(et.length==0)
         et=xmlDoc1.getElementsByTagName(tagName1);
        return et;
}
function createElement1(xmlDoc1,element1)
{
    if(getOs()==1)
    {
        var element2=mml+element1;
        return xmlDoc1.createElement(element2);
    }else
    {
        return xmlDoc1.createElementNS(mmlns,element1);
    }
}
function doNavigate(kc)
{
     var xmlDoc=createMSXML();
     var root=xmlDoc.documentElement;
     var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
     num_mstyles=mstyles.length;
     for (i = 0; i < num_mstyles; i++)
   {
    mstyle= mstyles.item(i);
    mstyleAttr = mstyle.getAttribute("tempinput");
    if(mstyleAttr=="1")
    {
     focuson=mstyle;
    }
   }
    var focusparent=focuson.parentNode;
    var focuschild=focuson.firstChild;
    var mstyle=createElement1(xmlDoc,"mstyle");
  mstyle.setAttribute("mathbackground","#00ff00");
  mstyle.setAttribute("tempinput","1");
  var nesting1;
  var node2=findnode(focuson,kc);
  if(typeof(node2)=="string")
    {
        if(node2=="notfind") return;
    }
    focusparent.replaceChild(focuschild,focuson);
    node2.parentNode.replaceChild(mstyle,node2);
  mstyle.appendChild(node2);
  currentmath=getxml(root);
  renew();
}
function getxml(node1,ns)
{
    if(getOs()==1)
    {
        return node1.xml;
    }else
    {

     var ds=document.getElementById("selectedMath");
   ds.innerHTML="";
   var dsc=node1.cloneNode(true);
   ds.appendChild(dsc);
   var nodexml=ds.innerHTML;
   nodexml=nodexml.replace("mml=","xmlns=");
   return nodexml;
//   var nodexml=GetMathML(node1);
//   alert("suwei"+nodexml);
//   return nodexml;
  }
}

function findnode(node1,orient)
{
    var finalNode;
    if(orient==34 || orient==9)//pageDown
    {
        var finalNode=node1.firstChild.firstChild.nextSibling;
        if(finalNode==null || finalNode.firstChild==null)
        {

            finalNode=node1.nextSibling;
            if(finalNode==null)
          {
            nodeP=node1.parentNode;
            finalNode=nodeP.nextSibling;
            while(nodeP.nextSibling==null)
            {
              tempstyleAttr = nodeP.getAttribute("tempstyle");
              if(tempstyleAttr=="1")
              {
                finalNode=nodeP.firstChild;
                break;
              }
              nodeP=nodeP.parentNode;
              tempstyleAttr = nodeP.getAttribute("tempstyle");
              if(tempstyleAttr=="1")
              {
                finalNode=nodeP.firstChild;
                break;
              }
              finalNode=nodeP.nextSibling;
            }

          }
        }
        if(judgenesting(finalNode)==1)
    {
          while(judgenesting(finalNode)==1)
          finalNode=finalNode.firstChild;
    }
    }
    if(orient==33)//pageUp
    {
        var finalNode=node1.previousSibling;
        if(finalNode==null || finalNode.firstChild==null)
        {

            finalNode=node1.parentNode;
      tempstyleAttr = finalNode.getAttribute("tempstyle");
      if(tempstyleAttr=="1")
        {
            finalNode=node1.lastChild;
            while(finalNode.lastChild.lastChild!=null)
            {
              finalNode=finalNode.lastChild;
            }
        }else
        {
            if(judgenesting(finalNode)==1)
        {
             finalNode=findnode(finalNode,33)
         }
        }
        }else
        {
        while(finalNode.lastChild.lastChild!=null)
        {
          finalNode=finalNode.lastChild;
         }
    if(judgenesting(finalNode)==1)
    {
          while(judgenesting(finalNode)==1)
          finalNode=finalNode.firstChild;
    }
        }

    }
    if(orient==37)//left
    {
        var preFocuson=node1.previousSibling;
      if(preFocuson==null || preFocuson.firstChild==null || judgetf(preFocuson.firstChild)==1)
        {
            if(judgenesting(node1.parentNode)==1)
            {
                finalNode=findnode(node1.parentNode,orient);
            }else
            {
              finalNode="notfind";
          }
        }else
        {
            while(judgenesting(preFocuson)==1)
            {
                preFocuson=preFocuson.firstChild;
          }
          finalNode=preFocuson;
        }
    }
    if(orient==40)//down
    {
        var focusgrandchild=node1.firstChild.firstChild.nextSibling;
        if(focusgrandchild==null || focusgrandchild.firstChild==null)
        {
            finalNode="notfind";
        }else
        {
          if(judgenesting(focusgrandchild)==1)
      {
          while(judgenesting(focusgrandchild)==1)
          focusgrandchild=focusgrandchild.firstChild;
          finalNode=focusgrandchild;
       }else{
          finalNode=focusgrandchild;
           }
       }
    }
    if(orient==38)//up
    {
        nodeP=node1.parentNode;
        tempstyleAttr = nodeP.getAttribute("tempstyle");
      if(tempstyleAttr=="1")
        {
            finalNode="notfind";
        }else
        {
       if(judgenesting(nodeP)==1)
       {
        // while(judgenesting(nodeP)==1)
        //   nodeP=nodeP.parentNode;
         finalNode=findnode(nodeP,orient);
       }else{
        finalNode=nodeP;
         }
      }
    }
    if(orient==39)//right
    {
        var focusparent=node1.parentNode;
        nesting1=judgenesting(focusparent);
      var nodenext=node1.nextSibling;
        if(nodenext==null)
        {
            if(nesting1==1)
            {
                finalNode=findnode(focusparent,39);
          }else
          {
            finalNode="notfind";
          }
        }
         else
        {
          while(judgenesting(nodenext)==1)
          {
            nodenext=nodenext.firstChild;
          }
          finalNode=nodenext;
        }
    }
    return finalNode;
}
function judgetf(node1)
{
    var ne=new Array();
    var tagname1=node1.tagName;

  ne[0]="sin";ne[1]="arcsin";ne[2]="cos";ne[3]="arccos";
  ne[4]="tan";ne[5]="arctan";ne[6]="cot";ne[7]="arccot";
  ne[8]="sec";ne[9]="arcsec";ne[10]="csc";ne[11]="arccsc";   //nesting element
  var tf=0;
    for(var i=0;i<ne.length;i++)
  {
    if(tagname1==mml+ne[i] || tagname1==ne[i]) tf=1;
  }
    return tf;
}
function judgenesting(node1)
{
    var tagname1=node1.tagName;
    var ne=new Array();
  ne[0]="uplimit";ne[1]="lowlimit";ne[2]="degree";
  ne[3]="matrixrow";ne[4]="condition";
  ne[5]="domainofapplication";ne[6]="logbase";
  ne[7]="bvar"; ne[8]="mstyle";ne[9]="fn";//nesting element
  ne[10]="semantics";
  var nesting1=0;
    for(var i=0;i<ne.length;i++)
  {
    if(tagname1==mml+ne[i] || tagname1==ne[i]) nesting1=1;
  }
  if(tagname1==mml+"ci" ||tagname1=="ci")
  {
    if(node1.getAttribute("type")=="bvar")
    nesting1=1;
  }
  return nesting1;
}

function doDelete(kc)
{
     var xmlDoc=createMSXML();
     var root=xmlDoc.documentElement;
     var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
     num_mstyles=mstyles.length;
     for (i = 0; i < num_mstyles; i++)
   {
     mstyle= mstyles.item(i);
     mstyleAttr = mstyle.getAttribute("tempinput");
     if(mstyleAttr=="1" || mstyleAttr=="2")
     {
      focuson=mstyle;
     }
   }
   var s=createElement1(xmlDoc,"mstyle");
   s.setAttribute("mathbackground","#00ff00");
   s.setAttribute("tempinput","1");
   if((focuson.firstChild.nodeName=="mml:ci" || focuson.firstChild.nodeName=="mml:cn" || focuson.firstChild.nodeName=="cn" || focuson.firstChild.nodeName=="ci" || focuson.firstChild.nodeName=="cn") && kc==8 && focuson.firstChild.firstChild.length>1)
   {
      var ciStr=focuson.firstChild.firstChild.text;
      ciStr=ciStr.substr(0,(ciStr.length-1));
      var box1 = createElement1(xmlDoc,"ci");
      box1.setAttribute("id",++idnum);
      box1.appendChild(xmlDoc.createTextNode(ciStr));
      s.appendChild(box1);
   }else
   {
     var box0 = createElement1(xmlDoc,"ci");
     box0.appendChild(xmlDoc.createTextNode("\u25A1"));
     box0.setAttribute("id",++idnum);
     s.appendChild(box0);
   }
   PN=focuson.parentNode;
   PN.replaceChild(s,focuson);
   currentmath=getxml(root);
   renew();
   savehist();
}

function editmmlc()
{
	var c=currentmath;
    var mmlcinput=Ext.getCmp('mmlcinput');
	var parentWindow = window.parent;
	var p$ = parentWindow.$;
	var math = p$("#showLabFormula").val();
	
	if(math!=null && math!=""){
		mmlcinput.setValue(base64decode(math));
	}
   currentmath=mmlcinput.getValue();
   if(getOs()>=2)
   {
     solvematrixdisplay(designDoc);
     designDoc.addEventListener("keydown", doKey, false);
     designDoc.addEventListener("click", domouseclick, false);
   }
   renew();
   if(c!=currentmath)
   {
    savehist();
   }
}
function editopenm()
{
     var c=currentmath;
     var openminput=Ext.getCmp('openminput');
   var openmath=openminput.getValue();
   currentmath=renderxx(openmath,"style/omtocmml.xsl");
   addStyle(currentmath);
   if(getOs()>=2)
   {
     solvematrixdisplay(designDoc);
     designDoc.addEventListener("keydown", doKey, false);
     designDoc.addEventListener("click", domouseclick, false);
   }
   renew();
   var c1=c.replace(/id="."/gi,"");
   var c1=c1.replace(/tempinput="."/gi,"");
   var c2=currentmath.replace(/id="."/gi,"");
   var c2=c2.replace(/tempinput="."/gi,"");
   if(c1!=c2)
   {
     savehist();
   }
}

function doDesign()
{
     var ms=document.getElementById("mathsign");
   var tb=document.getElementById("toolbar2");
   var si=document.getElementById("sinfix");
   if(editMode==1)
   {
     alert("already in Design Mode");
    return;}
  if(editMode==2)
  { var c=currentmath;
    currentmath=designDoc.f2.mathcode.value;
    if(getOs()>=2)
    {
     designDoc.body.innerHTML="";
     solvematrixdisplay(designDoc);
     designDoc.addEventListener("keydown", doKey, false);
     designDoc.addEventListener("click", domouseclick, false);
    }
   renew();
   if(c!=currentmath)
   {
    savehist();
   }
   if(si!=null && si!="")
   { si.style.display="";}
   if(ms!=null || ms!="")
   { ms.style.display="";}
   if(tb!=null ||tb!="")
   {tb.style.display="";}
  }
   if(editMode==3)
  {
   if(getOs()>=2)
    {
     designDoc.body.innerHTML="";
     solvematrixdisplay(designDoc);
     designDoc.addEventListener("keydown", doKey, false);
     designDoc.addEventListener("click", domouseclick, false);
    }
   renew();
    if(si!=null && si!="")
   { si.style.display="";}
   if(ms!=null || ms!="")
   { ms.style.display="";}
   if(tb!=null ||tb!="")
   {tb.style.display="";}
   }
  if(editMode==4)
  { var c=currentmath;
    currentmath=addprefix(designDoc.f4.mathcode.value);
    if(getOs()>=2)
    {
     designDoc.body.innerHTML="";
     solvematrixdisplay(designDoc);
     designDoc.addEventListener("keydown", doKey, false);
     designDoc.addEventListener("click", domouseclick, false);
    }
    renew();
    var c1=c.replace(/id="."/gi,"");
    var c2=currentmath.replace(/id="."/gi,"");
    if(c1!=c2)
    {
     savehist();
    }
   if(si!=null && si!="")
   { si.style.display="";}
   if(ms!=null || ms!="")
   { ms.style.display="";}
   if(tb!=null ||tb!="")
   {tb.style.display="";}
   }

   if(editMode==6)
 {  var mcurrentmath=designDoc.f6.mathcode.value;
     mcurrentmath="<?xml version='1.0' encoding='UTF-8'?>"+mcurrentmath;
   var xmldoc = new ActiveXObject("Microsoft.XMLDOM");
         xmldoc.async=false;
         xmldoc.loadXML(mcurrentmath);
     var xsl = new ActiveXObject("Microsoft.XMLDOM");
       xsl.async = false;
       xsl.load("omtocmml.xsl");
   var cmml=xmldoc.transformNode(xsl);
   cmml = cmml.substring(39,cmml.length);
     currentmath=addStyle(cmml) ;
     renew();
    mathsign.style.display="";
    toolbar2.style.display="";
  }
  editMode=1;
}


function doContent()
{
   var ms=document.getElementById("mathsign");
   var tb=document.getElementById("toolbar2");
   var si=document.getElementById("sinfix");
   if(editMode==2)
   {
     alert("already in MathML Content Mode");
     return;
   }
   if(editMode==1)
   {
   designDoc.body.innerHTML="";
   designDoc.write("<div style='background-color:#0066ff;color:#ff6600;font-weight: bold'>The MathML Content Code are:</div>");
   designDoc.write("<form name='f2'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+currentmath+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
   }
    if(editMode==3)
  {
   designDoc.body.innerHTML="";
   designDoc.write("<div style='background-color:#0066ff;color:#ff6600;font-weight: bold'>The MathML Content Code are:</div>");
   designDoc.write("<form name='f2'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+currentmath+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
   }
  if(editMode==4)
 { currentmath=addprefix(designDoc.f4.mathcode.value);
   designDoc.body.innerHTML="";
   designDoc.write("<div style='background-color:#0066ff;color:#ff6600;font-weight: bold'>The MathML Content Code are:</div>");
   designDoc.write("<form name='f2'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+currentmath+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
  }
  editMode=2;
}

function doPresentation()
{
     var ms=document.getElementById("mathsign");
   var tb=document.getElementById("toolbar2");
   var si=document.getElementById("sinfix");
   if(editMode==3)
   {
     alert("already in MathML Presentation Mode");
     return;
   }
  if(editMode==1)
  {

    var mp=mctompStr(currentmath);
    designDoc.body.innerHTML="";
    designDoc.write("<div style='background-color:#0066ff;color:#ffff00;font-weight: bold'>The MathML Presentation Code are:</div>");
    designDoc.write("<form name='f3'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+mp+"</textarea></form>");
    if(si!=null && si!="")
    { si.style.display="none";}
    if(ms!=null || ms!="")
    { ms.style.display="none";}
    if(tb!=null ||tb!="")
    {tb.style.display="none";}
   }
  if(editMode==2)
  {
    var currentmathP=mctompStr(designDoc.f2.mathcode.value);
    designDoc.body.innerHTML="";
    designDoc.write("<div style='background-color:#0066ff;color:#ffff00;font-weight: bold'>The MathML Presentation Code are:</div>");
    designDoc.write("<form name='f3'><textarea name='mathcode'  cols='68' rows='15' style='border:0'>"+currentmathP+"</textarea></form>");
    if(si!=null && si!="")
    { si.style.display="none";}
    if(ms!=null || ms!="")
    { ms.style.display="none";}
    if(tb!=null ||tb!="")
    {tb.style.display="none";}
  }
  if(editMode==4)
  {
    currentmath=addprefix(designDoc.f4.mathcode.value);
      var currentmathP=mctompStr(currentmath);
    designDoc.body.innerHTML="";
    designDoc.write("<div style='background-color:#0066ff;color:#ffff00;font-weight: bold'>The MathML Presentation Code are:</div>");
    designDoc.write("<form name='f3'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+currentmathP+"</textarea></form>");
    if(si!=null && si!="")
    { si.style.display="none";}
    if(ms!=null || ms!="")
    { ms.style.display="none";}
    if(tb!=null ||tb!="")
    {tb.style.display="none";}
  }
  editMode=3;
}

function doInfix()
{
     var ms=document.getElementById("mathsign");
   var tb=document.getElementById("toolbar2");
   var si=document.getElementById("sinfix");
     if(editMode==4)
   {
     alert("already in infix Mode");
     return;
   }
   if(editMode==1)
   {
   var outmathml=strConvert(currentmath);
   designDoc.body.innerHTML="";
   designDoc.write("Infix display:");
   designDoc.write("<form name='f4'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+outmathml+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
   }
  if(editMode==2)
  {
   var outmathml=strConvert(designDoc.f2.mathcode.value);
   designDoc.body.innerHTML="";
   designDoc.write("Infix display:");
   designDoc.write("<form name='f4'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+outmathml+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
  }
  if(editMode==3)
  {
   var outmathml=strConvert(currentmath);
   designDoc.body.innerHTML="";
   designDoc.write("Infix display:");
   designDoc.write("<form name='f4'><textarea name='mathcode' wrap='virtual' cols='68' rows='15' style='border:0'>"+outmathml+"</textarea></form>");
   if(si!=null && si!="")
   { si.style.display="none";}
   if(ms!=null || ms!="")
   { ms.style.display="none";}
   if(tb!=null ||tb!="")
   {tb.style.display="none";}
  }
   editMode=4;
}



function doPreview()
{
 var dlg;
 if(editMode==1)
 {
    cm1=new Array();
  cm1=getCode();
  var currentmath3=cm1[0];
  dlg=window.open("", "preview",
                  "toolbar=no,menubar=no,personalbar=no,width=400,height=400," +
                  "scrollbars=no,resizable=yes");

  dlg.document.body.innerHTML="";
  dlg.document.title="Preview Your Mathematical Expression";
  if(getOs()==1)
  {
   dlg.document.write(mmlparse+currentmath3+mmlparse1);
  }else
  {
    var mp=mctomp(mmlparse+currentmath3+mmlparse1);
    solvematrixdisplay(dlg.document);
    dlg.document.body.innerHTML="";
    dlg.document.body.appendChild(mp);
  }
 }
 if(editMode==2)
 {
    currentmath=designDoc.f2.mathcode.value;
    cm1=new Array();
  cm1=getCode();
  var currentmath3=cm1[0];
 dlg=window.open("", "preview",
                  "toolbar=no,menubar=no,personalbar=no,width=400,height=400," +
                  "scrollbars=no,resizable=yes");

 dlg.document.body.innerHTML="";
 dlg.document.title="Preview Your Mathematical Expression";
 if(getOs()==1)
  {
   dlg.document.write(mmlparse+currentmath3+mmlparse1);
  }else
  {
    var mp=mctomp(mmlparse+currentmath3+mmlparse1);
    solvematrixdisplay(dlg.document);
    dlg.document.body.innerHTML="";
    dlg.document.body.appendChild(mp);
  }
 }

 if(editMode==3)
 {
  cm1=new Array();
  cm1=getCode();
  var currentmath3=cm1[0];
 dlg=window.open("", "preview",
                  "toolbar=no,menubar=no,personalbar=no,width=400,height=400," +
                  "scrollbars=no,resizable=yes");

 dlg.document.body.innerHTML="";
 dlg.document.title="Preview Your Mathematical Expression";
 if(getOs()==1)
  {
   dlg.document.write(mmlparse+currentmath3+mmlparse1);
  }else
  {
    var mp=mctomp(mmlparse+currentmath3+mmlparse1);
    solvematrixdisplay(dlg.document);
    dlg.document.body.innerHTML="";
    dlg.document.body.appendChild(mp);
  }
}

 if(editMode==4)
 {
  currentmath=addprefix(designDoc.f4.mathcode.value);
  cm1=new Array();
  cm1=getCode();
  var currentmath3=cm1[0];
 dlg=window.open("", "preview",
                  "toolbar=no,menubar=no,personalbar=no,width=400,height=400," +
                  "scrollbars=no,resizable=yes");

 dlg.document.body.innerHTML="";
 dlg.document.title="Preview Your Mathematical Expression";
 if(getOs()==1)
  {
   dlg.document.write(mmlparse+currentmath3+mmlparse1);
  }else
  {
    var mp=mctomp(mmlparse+currentmath3+mmlparse1);
    solvematrixdisplay(dlg.document);
    dlg.document.body.innerHTML="";
    dlg.document.body.appendChild(mp);
  }
 }
}

function addFocus(xmlDoc)
{
 if(xmlDoc=="undifine" || xmlDoc==null || xmlDoc=="")
 {
  var xmlDoc=createMSXML();
 }
 var root=xmlDoc.documentElement;
 var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
 var num_mstyles=mstyles.length;
 var mstyle=null;
 var mstyleAttr="";
 var tmpStyle=null;
 var tmpStyleAttr;

 for (i = 0; i < num_mstyles; i++)
 {
   mstyle= mstyles.item(i);
   mstyleAttr = mstyle.getAttribute("tempinput");
   if(mstyleAttr=="1")
   {
    if(mstyle.getAttribute("mathbackground")!="" || mstyle.getAttribute("mathbackground")!=null)
    return currentmath;
    }
  }
 for (i = 0; i < num_mstyles; i++)
 {
  tmpStyle= mstyles.item(i);
  tmpStyleAttr = tmpStyle.getAttribute("tempstyle");
  if(tmpStyleAttr=="1")
  {
    if(tmpStyle.parentNode.tagName=="mml:math" ||tmpStyle.parentNode.tagName=="math")
    {
        var s=createElement1(xmlDoc,"mstyle");
      s.setAttribute("mathbackground","#00ff00");
      s.setAttribute("tempinput","1");
        var tmpChild=tmpStyle.firstChild;
        tmpStyle.replaceChild(s,tmpChild);
      s.appendChild(tmpChild);
      return getxml(root);
    }
  }else return currentmath;
 }
}
function XslDom()
{
 var arrSignatures = ["MSXML2.FreeThreadedDOMDocument.4.0","MSXML2.FreeThreadedDOMDocument.3.0"];
 for(var i=0; i<arrSignatures.length;i++)
 {
  try
  {
     var oXslDom=new ActiveXObject(arrSignatures[i]);
     return oXslDom;
  }catch(oError)
  {
  }
 }
 throw new Error("IE v6.0+ is required!");
}
function XSLTemplate(){
 var arrSignatures = ["Msxml2.XSLTemplate.4.0","MSXML2.XSLTemplate","MSXML2.XSLTemplate.4.0","Msxml2.XSLTemplate.5.0","MSXML2.XSLTemplate.3.0"];
 for(var i=0; i<arrSignatures.length;i++)
 {
  try{

   var tempalte=new ActiveXObject(arrSignatures[i]);
         return tempalte;

  }catch(oError) {

  }
 }
 throw new Error("IE v6.0+ is required!");
}

function getCode()
{
 var xmlDoc=createMSXML();
 var root=xmlDoc.documentElement;
 var mstyles=getElementsByTagName1(xmlDoc,"mstyle");
 num_mstyles=mstyles.length;
 for (i = 0; i < num_mstyles; i++)
 {
   var mstyle= mstyles.item(i);
   var mstyleAttr = mstyle.getAttribute("tempinput");
   if(mstyleAttr=="1")
   {
    focuson=mstyle;
    }
  }
  focuson.parentNode.replaceChild(focuson.firstChild,focuson);
  mc= new Array(5);
  mc[0]=getxml(root);
  var outmathml=strConvert(mc[0]);
  mc[1]=outmathml;
  var infpre=mctompStr(mc[0]);
  mc[2]=infpre;
  return(mc);
}

function savehist()
{
  if(histp==49)
  {histp=0;}
  else
  {histp=histp+1;}
  histmath[histp]=currentmath;
  histr=0;
}
function undo()
{
    //alert(histr);
   if(histr==49)
   {
    alert("Can't Undo"); return;
   }
   if(histp==0)
   {
     if(histmath[49]==undefined || histmath[49]=="")
     {
      alert("Can't Undo");
      return;
     }else
     {
       currentmath=histmath[49];
       histp=49;
     }
   }else
   {
    currentmath=histmath[--histp];
   }
  renew();
  histr=histr+1;
}
function redo()
{
  if(histr==0)
   {
    alert("Can't Redo"); return;
   }else
   {
     if(histp==49)
     {
       histp=0;
       currentmath=histmath[histp];
     }else
     {
       currentmath=histmath[++histp];
     }
   }
  renew();
  histr=histr-1;
}
function createMSXML(xcm) //Load XML in firefox and IE.
{

    if(xcm=="" || xcm==null || xcm=="undefined")
    {
        xcm=currentmath;
    }
  if(getOs()>=2)
  {
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(xcm.toString(), "text/xml");
    return xmlDoc;
  }
  else if(getOs()==1)
  {
    var aVersions = [ "MSXML2.DOMDocument.5.0",
       "MSXML2.DOMDocument.4.0","MSXML2.DOMDocument.3.0",
       "MSXML2.DOMDocument","Microsoft.XmlDom"];
    for (var i = 0; i < aVersions.length; i++)
    {
        try {
             var xmlDoc = new ActiveXObject(aVersions[i]);
               xmlDoc.async=false;
               xmlDoc.loadXML(xcm);
             return xmlDoc;
         } catch (oError) {
        }
    }
    throw new Error("MSXML is not installed.");
  }
}

function addprefix(con)
{
  var result=convertToContentDOM(con);
  if(getOs()==1)
  {
   var STR = GetMathMLEx(result);
  }else
  {
    var STR = GetMathMLEx(result,"mml");
  }
  var currentmath3='<mml:math xmlns:mml="http://www.w3.org/1998/Math/MathML">'+STR+'</mml:math>';
  var xmlDoc1=createMSXML(currentmath3);
  var root1=xmlDoc1.documentElement;
  var s=createElement1(xmlDoc1,"mstyle");
  s.setAttribute("tempstyle","1");
  s.setAttribute("mathsize","20pt");
  s.appendChild(root1.firstChild);
  root1.appendChild(s);
  addIdAttribute(root1,0);
  currentmath=getxml(root1);
  currentmath=addFocus();
  currentmath=addfn(currentmath);
  return currentmath;
}
function addfn(cm)
{
    cm=cm.replace(/<sin><\/sin>/gi,"<fn><sin/></fn>");
    cm=cm.replace(/<mml:sin\/>/gi,"<mml:fn><mml:sin/></mml:fn>");
    cm=cm.replace(/<cos><\/cos>/gi,"<fn><cos/></fn>");
    cm=cm.replace(/<mml:cos\/>/gi,"<mml:fn><mml:cos/></mml:fn>");
    cm=cm.replace(/<tan><\/tan>/gi,"<fn><tan/></fn>");
    cm=cm.replace(/<mml:tan\/>/gi,"<mml:fn><mml:tan/></mml:fn>");
    cm=cm.replace(/<cot><\/cot>/gi,"<fn><cot/></fn>");
    cm=cm.replace(/<mml:cot\/>/gi,"<mml:fn><mml:cot/></mml:fn>");
    cm=cm.replace(/<sec><\/sec>/gi,"<fn><sec/></fn>");
    cm=cm.replace(/<mml:sec\/>/gi,"<mml:fn><mml:sec/></mml:fn>");
    cm=cm.replace(/<csc><\/csc>/gi,"<fn><csc/></fn>");
    cm=cm.replace(/<mml:csc\/>/gi,"<mml:fn><mml:csc/></mml:fn>");

    cm=cm.replace(/<arcsin><\/arcsin>/gi,"<fn><arcsin/></fn>");
    cm=cm.replace(/<mml:arcsin\/>/gi,"<mml:fn><mml:arcsin/></mml:fn>");
    cm=cm.replace(/<arccos><\/arccos>/gi,"<fn><arccos/></fn>");
    cm=cm.replace(/<mml:arccos\/>/gi,"<mml:fn><mml:arccos/></mml:fn>");
    cm=cm.replace(/<arctan><\/arctan>/gi,"<fn><arctan/></fn>");
    cm=cm.replace(/<mml:arctan\/>/gi,"<mml:fn><mml:arctan/></mml:fn>");
    cm=cm.replace(/<arccot><\/arccot>/gi,"<fn><arccot/></fn>");
    cm=cm.replace(/<mml:arccot\/>/gi,"<mml:fn><mml:arccot/></mml:fn>");
    cm=cm.replace(/<arcsec><\/arcsec>/gi,"<fn><arcsec/></fn>");
    cm=cm.replace(/<mml:arcsec\/>/gi,"<mml:fn><mml:arcsec/></mml:fn>");
    cm=cm.replace(/<arccsc><\/arccsc>/gi,"<fn><arccsc/></fn>");
    cm=cm.replace(/<mml:arccsc\/>/gi,"<mml:fn><mml:arccsc/></mml:fn>");
    return cm;
}
function rmfn(codeStr)
{
    codeStr=codeStr.replace(/<fn><sin><\/sin><\/fn>/gi,"<sin/>");
    codeStr=codeStr.replace(/<mml:fn><mml:sin\/><\/mml:fn>/gi,"<mml:sin/>");
    codeStr=codeStr.replace(/<fn><cos><\/cos><\/fn>/gi,"<cos/>");
    codeStr=codeStr.replace(/<mml:fn><mml:cos\/><\/mml:fn>/gi,"<mml:cos/>");
    codeStr=codeStr.replace(/<fn><tan><\/tan><\/fn>/gi,"<tan/>");
    codeStr=codeStr.replace(/<mml:fn><mml:tan\/><\/mml:fn>/gi,"<mml:tan/>");
    codeStr=codeStr.replace(/<fn><cot><\/cot><\/fn>/gi,"<cot/>");
    codeStr=codeStr.replace(/<mml:fn><mml:cot\/><\/mml:fn>/gi,"<mml:cot/>");
    codeStr=codeStr.replace(/<fn><sec><\/sec><\/fn>/gi,"<sec/>");
    codeStr=codeStr.replace(/<mml:fn><mml:sec\/><\/mml:fn>/gi,"<mml:sec/>");
    codeStr=codeStr.replace(/<fn><csc><\/csc><\/fn>/gi,"<csc/>");
    codeStr=codeStr.replace(/<mml:fn><mml:csc\/><\/mml:fn>/gi,"<mml:csc/>");

    codeStr=codeStr.replace(/<fn><arcsin><\/arcsin><\/fn>/gi,"<arcsin/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arcsin\/><\/mml:fn>/gi,"<mml:arcsin/>");
    codeStr=codeStr.replace(/<fn><arccos><\/arccos><\/fn>/gi,"<arccos/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arccos\/><\/mml:fn>/gi,"<mml:arccos/>");
    codeStr=codeStr.replace(/<fn><arctan><\/arctan><\/fn>/gi,"<arctan/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arctan\/><\/mml:fn>/gi,"<mml:arctan/>");
    codeStr=codeStr.replace(/<fn><arccot><\/arccot><\/fn>/gi,"<arccot/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arccot\/><\/mml:fn>/gi,"<mml:arccot/>");
    codeStr=codeStr.replace(/<fn><arcsec><\/arcsec><\/fn>/gi,"<arcsec/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arcsec\/><\/mml:fn>/gi,"<mml:arcsec/>");
    codeStr=codeStr.replace(/<fn><arccsc><\/arccsc><\/fn>/gi,"<arccsc/>");
    codeStr=codeStr.replace(/<mml:fn><mml:arccsc\/><\/mml:fn>/gi,"<mml:arccsc/>");
    return codeStr;
}
function addprefixP(con)
{
    var result=convertToPresentDOM(con);
    if(getOs()==1)
    {
     var STR = GetMathMLExP(result);
    }else
    {
     var STR = GetMathMLExP(result,"mml");
    }
      currentmath1=STR;
      currentmath1='<mml:math xmlns:mml="http://www.w3.org/1998/Math/MathML">'+currentmath1.substr(10,currentmath1.length-9);
    return currentmath1;
}
function replaceStr(str,oldStr,newStr,h)
{
    var tmpStr;
    var pos1;
    pos1=str.indexOf('<mml:mstyle mathbackground="#00ff00" tempinput="1">');
  pos2=pos1+oldStr.length+h+13;
    if(pos1<0){
        tmpStr=str;
        return tmpStr;
    }
        tmpStr=str.substring(0,pos1);
        tmpStr += newStr;
        str = str.substring(pos2-1,str.length);
    tmpStr += str;
      return tmpStr;
}


function val_currentmath(str)
{
  currentmath=str;
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

function getMathmlCode(Expr,ns)
{
    var MathMLValue="";
    if (Expr.nodeType == 3) {
        var nv=Expr.nodeValue;
        var nv1=nv.charCodeAt();
        var nv2="";
    switch(nv1)
    {
        case 9633: nv2="&#x25A1;"; break;
        case 8734: nv2="";break;
        default: nv2=nv; break;
    }
        MathMLValue =  MathMLValue + " "+nv2+" ";
        return MathMLValue;
    }
    if (Expr.childNodes.length == 0) {
        MathMLValue = MathMLValue + "<"+Expr.nodeName+getAtt(Expr)+"/>";
        return MathMLValue;
    }
    else {
        MathMLValue = MathMLValue + "<"+Expr.nodeName+getAtt(Expr)+">";
        for (var i=0; i<Expr.childNodes.length; i++) {
            MathMLValue += getMathmlCode(Expr.childNodes[i]);
        }
        MathMLValue = MathMLValue + "</"+Expr.nodeName+">";
    }
    return MathMLValue;
}

function getAtt(node)
{
    var list = " ";
    for (var i=0; i<node.attributes.length; i++) {
            list = list+ node.attributes[i].nodeName + '="' + node.attributes[i].nodeValue + '" ';
    }
    return list;
}

function addStyle(str)
{
    var xmlDoc1=createMSXML(str);
    var root1=xmlDoc1.documentElement;
    var s=createElement1(xmlDoc1,"mstyle");
    s.setAttribute("tempstyle","1");
    s.setAttribute("mathsize","20pt");
    s.appendChild(root1.firstChild);
    root1.appendChild(s);
    addIdAttribute(root1,0);
    currentmath=addFocus(xmlDoc1);
    currentmath=addfn(currentmath);
    return currentmath;
}

function initajax()
{
var ajax;
if (window.XMLHttpRequest) { // Mozilla, Safari, ...
             ajax= new XMLHttpRequest();
             //alert("ff");
          }
        else if (window.ActiveXObject) { // IE

              ajax = new ActiveXObject("Microsoft.XMLHTTP");
              //alert("ie");
          }

return ajax;
}


function latexts(st)
{
        var latexinput=Ext.getCmp('latexinput');
        if(latexinput!=null && latexinput!="")
        {
            tex=renderxx("<?xml version='1.0' encoding='UTF-8'?>"+currentmath,"style/mmltex.xsl");
            latexinput.setValue(tex);
        }
        else
        {
            alert("Convert to latex error could not convert");
            return;
        }
    //alert("in function latexts(st)\n"+"tex = "+tex+"\nst = "+st);
  var latexexp=tex;
  var stu=st;
  var outputFormat = "";
  var loginajax=initajax();
   if(stu == 1)
   {
        outputFormat = "gif";
   }
   else if(stu == 2)
   {
        outputFormat = "png";
   }
   else if(stu == 3)
   {
        outputFormat = "dvi";
   }
   else if(stu == 4)
     {
            outputFormat = "ps";
     }
     else if(stu == 5)
     {
        outputFormat = "pdf";
     }


     var OutPutSize = "20pt";
     if(window.location.href.indexOf("wme.lzu.edu.cn") != -1)
     {
        var url="http://wme.lzu.edu.cn/mathedit/mets/METS.php?reqFormat=latex&reqCode="+encodeURIComponent(latexexp)+"&outFormat="+encodeURIComponent(outputFormat)+"&size =20pt";
     }
     else if(window.location.href.indexOf("mathedit.lzu.edu.cn") != -1)
     {
        var url="http://mathedit.lzu.edu.cn/mets/METS.php?reqFormat=latex&reqCode="+encodeURIComponent(latexexp)+"&outFormat="+encodeURIComponent(outputFormat)+"&size =20pt";
     }
     else
     {
            alert("bad url you are visiting");
            return;
     }
   //var url="http://wme.lzu.edu.cn/mathedit/mets/METS.php?reqFormat=latex&reqCode="+encodeURIComponent(latexexp)+"&outFormat="+encodeURIComponent(outputFormat)+"&size =20pt";
   //var url="http://wme.lzu.edu.cn/mathedit/mets/METS.php?reqFormat=latex&reqCode="+encodeURIComponent(latexexp)+"&outFormat="+encodeURIComponent(outputFormat)+"&size="+encodeURIComponent(OutPutSize);
  loginajax.open("GET",url,true);

  loginajax.onreadystatechange=function()
  { var latexts;
    latexts=window.open("", "latexts",
                  "toolbar=no,menubar=no,personalbar=no,width=500,height=400," +
                  "scrollbars=no,resizable=yes");
  latexts.document.title="Preview Your latexs Expression";

  if(loginajax.readyState<4)
  {
  latexts.document.body.innerHTML="Connect the server, please wait!";
  }
  if(loginajax.readyState==4 && loginajax.status==200)
  {

  if(stu==1||stu==2)
  {
  latexts.document.body.innerHTML='<br/><img src="'+loginajax.responseText+'"><br/><p></p><a href="'+loginajax.responseText+'">'+loginajax.responseText+'</a>';
  }
  else
  latexts.document.body.innerHTML='<a href="'+loginajax.responseText+'">'+loginajax.responseText+'</a>';
}
}
loginajax.send(null);
//loginajax.send("latexexp="+latexexp+"&stu="+stu);
return latexts;
}
