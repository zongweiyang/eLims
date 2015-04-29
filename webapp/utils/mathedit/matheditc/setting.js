function createxmlDoc(xmlfileName)//create a xml document or document segment with the
{                                                                   //argument filename
    var osv=getOs();
  var xmlDoc=null;
  if(osv==1)
  {
   xmlDoc=createMSXML2();//defined in this file
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
       // �����κδ���
    }
  }
  throw new Error("MSXML is not installed.");
}
//the original function
//function getcmls(furl,ttype,tname,xtype)


//the modified function
//function getcmls(furl,ttype,tname,xtype,functype)


//the argument "functype" indicate that which situation the handler is in
//if the argument value is "plainjs" means it is plain javascript
//else it means that it is available for ext
//The reason to set this argument is that operation of  plainjs  is different from extjs
//and the function will work with both
function getcmls(furl,ttype,tname,xtype,functype,needprefix)//get a collection of item e.g. a submenu of Templates
{
    var xmlDoc1=createxmlDoc(furl);
    var ts=xmlDoc1.getElementsByTagName(tname);
    var items=[];
    var re = /\(/gi;


    for(var i=0;i<ts.length;i++)
    {
      if("s"+ts[i].getAttribute("type")==ttype)
        {
            var item={};


////////////////////////////////////////////////////////////////////////////////////////////////
//the original code start
                //item.text=ts[i].getAttribute("id");
//the original code end
//////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////
//the modified code start
                    item.text=ts[i].getAttribute("id");
                    //the following two line is to remove  the prefix "s1" and some thing like that
                    if(needprefix == 0)
                    {
                        var end = ts[i].getAttribute("id").length;
                        item.text = item.text.substring(2,end);
                    }

//the modified code end
////////////////////////////////////////////////////////////////////////////////////////////////

            item.icon=ts[i].getElementsByTagName("imgurl")[0].firstChild.nodeValue;

/////////////////////////////////////////////////////////////////////////////////////////////////
//the original code start

/*
			var func=ts[i].getElementsByTagName("action")[0].firstChild.nodeValue;
		  func=func.replace(re,".createCallback(");
      item.handler=eval(func);
 */
 //the original code end
//////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////
//the code modified start

            var func=ts[i].getElementsByTagName("action")[0].firstChild.nodeValue;
            if(functype == "plainjs")
            {
                item.handler = func;
            }
            else
            {
                func=func.replace(re,".createCallback(");
                item.handler=eval(func);
            }
//the code modified end
///////////////////////////////////////////////////////////////////////////////////////////////////


      if(xtype=="button")
            {
                item.xtype="button";
                inwindow.push(item);
            }else
            {
              items.push(item);
          }
        }
    }
    return items;
}
function getcmlitem(furl,mid,item,tname)//get a single item  e.g. a sub menu of File
{

    var xmlDoc1=createxmlDoc(furl);
    var ac;


//	alert(furl+"\n"+mid+"\n"+item+"\n"+tname);
    if(item=="action" || item=="img" || item == "tooltip")
    {
        var ts=xmlDoc1.getElementsByTagName(tname);
        for(var i=0;i<ts.length;i++)
      {
          if(ts[i].getAttribute("id")==mid)
          {
            if(item=="action")
            {
               ac=ts[i].getElementsByTagName("action")[0].firstChild.nodeValue;
              }else if(item=="img")
              {
                ac=ts[i].getElementsByTagName("imgurl")[0].firstChild.nodeValue;
              }
              else if(item == "tooltip")
              {
                ac=ts[i].getElementsByTagName("tooltip")[0].firstChild.nodeValue;
              }
              if(tname!="s")
              {
                ac=ac.replace("()","");
              }
          }
      }
    }
    return ac;
}

///////////////////////////////////////////////////////////////////////////////////////////
//function added
//This function is defined for special use ,Now only used to get "font","fontsize"
function getitems(xmlfilename,tagname,tagid,subtagname)
{
    //alert(xmlfilename+"\n"+tagname+"\n"+tagid+"\n"+subtagname);
    var items = [];
    var re=/\(/gi;
    var l_xmldoc = createxmlDoc(xmlfilename);
    var xmltags = l_xmldoc.getElementsByTagName(tagname);

    for(var i = 0; i < xmltags.length; i++)
    {

        if(xmltags[i].getAttribute("id") == tagid)
        {
            var xmlsubtags = xmltags[i].getElementsByTagName(subtagname);
            for(var j = 1; j < xmlsubtags.length; j++ )//if both of(<f>Font</f> and <f>Size</f>) are in ftemplates.xml
            {                                          //the starting value of  variable j is 1
                var item = {};                           // if none of them is in ftemplates.xml, the value shuld be 0
                                                                                                 // and no other situation permited
                item.text = xmlsubtags[j].childNodes[0].nodeValue;
                var func = xmltags[i].getElementsByTagName("action")[0].firstChild.nodeValue;
                func = func.replace(re,".createCallback(");
                if(tagid=="tfont" || tagid == "tfontsize")
                {
                    func = func.replace("this.value","'"+item.text+"'");

                }
                /*  // The following the code for the fontsize is not  number like normal or "���" etc.
				if(tagid=="tfont")
				{
					func = func.replace("this.value","'"+item.text+"'");
					alert("AA"+func);
				}
				else if(tagid =="tfontsize")
				{
					if(item.text == "Small")
					{
						func = func.replace("this.value",12);
					}
					else if(item.text == "Normal")
					{
						func = func.replace("this.value",28);
					}
					else if(item.text == "Big")
					{
						func = func.replace("this.value",40);
					}
					func = func.replace("this.value","'"+item.text+"'");

				}*/
                item.handler = eval(func);
                items.push(item);

            }
        }

    }
    return items;
}
function IsFileExist(filepath)
{
    try
    {
        var xmlhttp = new XMLHttpRequest();
    }
    catch(e)
    {
        var xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
    }

        if(location.href.split("/")[0] == "file:")
        {

            return 1;
        }
    xmlhttp.open("GET",filepath,false);
    xmlhttp.send(null);

    if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
    {
        return true;
    }
    else
    {
      return false;
    }
}

///////////////////////////////////////////////////////////////////////////////////////////