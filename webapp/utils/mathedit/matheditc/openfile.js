var str;
var mmlparse='<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:mml="http://www.w3.org/1998/Math/MathML"><object id="matheditdemo1" classid="clsid:32F66A20-7614-11D4-BD11-00104BD3F987" codebase="http://www.dessci.com/dl/mathplayer.cab"></object>';
mmlparse=mmlparse+'<?import namespace="mml" implementation="#matheditdemo1" ?>';
var mmlparse1='</html>';
function preview1()
{	
	var x1 = document.form1.file1;
	//var filex="file:\\\\localhost\\" + x1.value;
  //alert(filex);
  var xmlDoc=createMSXML();
  xmlDoc.async=false;
  var fso = new ActiveXObject("Scripting.FileSystemObject");
  var ts = fso.OpenTextFile(x1.value, 1); 
  var s = ts.ReadLine(); 
	xmlDoc.loadXML(s);
  var root=xmlDoc.documentElement;
  filepre.document.write(mmlparse+root.xml+mmlparse1);
}
function createMSXML() 
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
function doCancel()
{
	window.close();
}
function doReturn()//return to the parent window if MathEdit is opened in a pop-up window
{ 
	str=filepre.document.body.innerHTML;
	if(str=="" ||str==null)
	{
	  window.returnValue="return value";
  }else
  {
  	window.returnValue=str;
  }
  window.close();
}