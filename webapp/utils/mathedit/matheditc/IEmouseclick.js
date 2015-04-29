/**
    author : Zhang Liao
    Discription : This file is mainly defined for the mouse click to select the editing focus in IE.

*/



////para:
////xml:xml may be an xml document or a xml string;
////xmltype:xmltype = 1 means that the xml is a document,xmltype=2 means that the xml is a string
function LoadXML(xml,xmltype)
{

    var xmlObj;
    // code for IE
    if (window.ActiveXObject)
    {
        if(xmltype == 1)
        {
            xmlObj=new ActiveXObject("Microsoft.XMLDOM");
            xmlObj.load(xml);
        }
        else if(xmltype == 2)
        {
            var aVersions = [ "MSXML2.DOMDocument.5.0",
                              "MSXML2.DOMDocument.4.0",
                              "MSXML2.DOMDocument.3.0",
                              "MSXML2.DOMDocument",
                              "Microsoft.XMLDOM"
                            ];
            for (var i = 0; i < aVersions.length; i++)
            {
                try {
                       xmlObj = new ActiveXObject(aVersions[i]);
        	           xmlObj.loadXML(xml);
        	           xmlObj.async=false;
                       return xmlObj;
                 } catch (oError)
                  {
                  }
            }
            throw new Error("MSXML is not installed.");
        }
        else
        {
            alert("Error:Parameter Error in function LoadXML")
        }
    }
    // code for Mozilla, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {

         if(xmltype == 1)
        {
            xmlObj=document.implementation.createDocument("","",null);
            xmlObj.load(xml);
        }
        else if(xmltype == 2)
        {
            var parser = new DOMParser();
            xmlObj = parser.parseFromString(xml, "text/xml");
        }
        else
        {
            alert("Error:Parameter Error in function LoadXML")
        }
    }
    else
    {
        alert('Your browser cannot handle this script');
    }
    xmlObj.async=false;
    return xmlObj;
}

///////this function will use the global variable currentmath
function DoIEMsClck(id)
{
    var xmlObj = LoadXML(currentmath, 2);
    var xmlDocRoot = xmlObj.documentElement;

////get the old focus tag end
    var mstyleTags = xmlObj.getElementsByTagName(mml+"mstyle")////mml="mml:" from editing.js
    if(mstyleTags.length == 0)
    {
        mstyleTags = xmlObj.getElementsByTagName("mstyle")
    }
    var cntr = 0;//counter for the circulation
    var oldEditFocus;//record the position of the cursor
    for(cntr = 0; cntr < mstyleTags.length; cntr++)
    {
        if(mstyleTags[cntr].getAttribute("tempinput") == "1")
        {
            oldEditFocus = mstyleTags[cntr];
            break;
        }
    }
////get the old focus tag end

////remove the oldfocus tag

    var oldEditFocusPrnt = oldEditFocus.parentNode;
    var oldEditFocusElmt = oldEditFocus.childNodes[0].cloneNode(true);
    oldEditFocusPrnt.replaceChild(oldEditFocusElmt,oldEditFocus);
////remove the oldfocus tag end

////create a focus tag
    var tmpMstyleTag;
    if(getNavigatorType() == 1)
    {
        tmpMstyleTag = xmlObj.createElement(mml+"mstyle");//mml="mml:" from editing.js
    }
    tmpMstyleTag.setAttribute("mathbackground", "#00ff00");
    tmpMstyleTag.setAttribute("tempinput","1");
////create a focus tag end

////get the clicked element

    var ciTags = xmlObj.getElementsByTagName(mml+"ci")//mml="mml:" from editing.js
    if(ciTags.length == 0)
    {
        ciTags = xmlObj.getElementsByTagName("ci")
    }
    var newEditFocus;//the clicked element
    for(cntr = 0; cntr < ciTags.length; cntr++)
    {
        if(ciTags[cntr].getAttribute("id") == id )
        {
            newEditFocus = ciTags[cntr];
            break;
        }
    }

    if(typeof(newEditFocus) == "undefined")
    {
        var cnTags = xmlObj.getElementsByTagName(mml+"cn")//mml="mml:" from editing.js
        if(cnTags.length == 0)
        {
            cnTags = xmlObj.getElementsByTagName("cn")
        }

        var newEditFocus = "";//the clicked element
        for(cntr = 0; cntr < cnTags.length; cntr++)
        {
            if(cnTags[cntr].getAttribute("id") == id )
            {
                newEditFocus = cnTags[cntr];
                break;
            }
        }

    }
////get the clicked element end

////add the new focus tag
    var newEditFocusPrnt = newEditFocus.parentNode;
    var newEditFocusElmt = newEditFocus;
//    alert(newEditFocusPrnt.xml);
    var tmpEditFocusElmt = newEditFocusElmt.cloneNode(true);
    tmpMstyleTag.appendChild(tmpEditFocusElmt);
    newEditFocusPrnt.replaceChild(tmpMstyleTag,newEditFocusElmt);
////add the new focus tag

    currentmath=xmlDocRoot.xml;
    renew();
}

function CreateAMactionTag(xmldoc)
{
    var aMactionTagNode;
    if(getNavigatorType() == 1)
    {
        aMactionTagNode = xmldoc.createElement(mml+"maction");//mml="mml:" from editing.js
    }
   // aMactionTagNode.setAttribute("actiontype","link");
    //the following attribute will be reset in  function SetMactionTagTOEveryLeafTag
    aMactionTagNode.setAttribute("dsi:href","javascript:parent.DoIEMsClck();");

    return aMactionTagNode;
}

function SetMactionTagToEveryLeafTag(crrntmth)
{
    var xmlObj = LoadXML(crrntmth, 2);//convert crrntmth to xml object ,function LoadXML(xml,xmltype) defined above

    var tmpMactionTag; //a cache for all maction tags to be added
    var tmpCiTag; // a cache for all ci tag to be retrivaled
////get ci tags collection
    var ciTags = xmlObj.getElementsByTagName(mml+"ci")//mml="mml:" from editing.js
    if(ciTags.length == 0)
    {
        ciTags = xmlObj.getElementsByTagName("ci")
    }
////////get ci tags collection end
    var cntr = 0; //counter for the circulation
    var tagID; // set the same id attribute between the parent maction and its child ci;
////set a maction tag for all ci tags
    for(cntr = 0; cntr < ciTags.length; cntr++)
    {
        tmpMactionTag = CreateAMactionTag(xmlObj);
        tmpCiTag = ciTags[cntr].cloneNode(true);
        tagID = tmpCiTag.getAttribute("id");
        tmpMactionTag.setAttribute("id",tagID);
        tmpMactionTag.setAttribute("dsi:href","javascript:parent.DoIEMsClck("+tagID +")");
        tmpMactionTag.appendChild(tmpCiTag);
        ciTags[cntr].parentNode.replaceChild(tmpMactionTag,ciTags[cntr]);
    }
////set a maction tag for every ci tags


    var tmpCnTag; // a cache for all ci tag to be retrivaled
////get cn tags collection
    var cnTags = xmlObj.getElementsByTagName(mml+"cn")//mml="mml:" from editing.js
    if(cnTags.length == 0)
    {
        cnTags = xmlObj.getElementsByTagName("cn")
    }
////////get cn tags collection end

////set a maction tag for all ci tags
    for(cntr = 0; cntr < cnTags.length; cntr++)
    {
        tmpMactionTag = CreateAMactionTag(xmlObj);
        tmpCnTag = cnTags[cntr].cloneNode(true);
        tagID = tmpCnTag.getAttribute("id");
        tmpMactionTag.setAttribute("id",tagID);
        tmpMactionTag.setAttribute("dsi:href","javascript:parent.DoIEMsClck("+tagID +")");
        tmpMactionTag.appendChild(tmpCnTag);
        cnTags[cntr].parentNode.replaceChild(tmpMactionTag,cnTags[cntr]);
    }
////set a maction tag for every ci tags

//alert(xmlObj.xml);
    return xmlObj;
}

function DoIEDrapAndDrop()
{
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
        if(tempid!="")
        {
            mp.firstChild.setAttribute("style","cursor:url('image/"+tempid+".cur'),default");
        }
    }
    //designDoc.body.appendChild(mp);

    var tempstr = designDoc.body.innerHTML;
    if(tempstr == "")
    {
        designDoc.body.appendChild(mp);
    }
    else
        {
            var dfrg = document.createDocumentFragment();
            dfrag.appendChild(tempstr);
            designDoc.body.appendChild(mp);
        }

}


function getNavigatorType()
{
    if(navigator.userAgent.indexOf("MSIE")>0) // Microsoft Internet Explorer
    {
        return 1;
    }
    else if(navigator.userAgent.indexOf("Firefox")>0) // Mozilla Firefox
    {
        return 2;
    }
    else if(navigator.userAgent.indexOf("Safari")>0) // Apple Safari
    {
        return 3;
    }
    else if(navigator.userAgent.indexOf("Camino")>0)
    {
        return 4;
    }
    else if(navigator.userAgent.indexOf("Gecko")>0)
    {
        return 5;
    }
    else
    {
        return 0; // unknown browser
    }
}