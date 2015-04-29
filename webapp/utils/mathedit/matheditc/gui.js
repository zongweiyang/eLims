

Ext.onReady(function() {
        Ext.QuickTips.init();
      var cmlfile="matheditcml.xml";
      var ftfile="ftemplates.xml";
      var tfile="templates.xml";
      var xmlDoc=createxmlDoc(cmlfile);

var TemplatesMenuItems = [];


   var logo={
     icon:'image/logo.png',
     cls:'x-btn-icon'
        };

   var menus=xmlDoc.getElementsByTagName("menu");
   if(menus.length>=1)
   {
     var tbspace={xtype: 'tbspacer'};
     var tbsep={xtype: 'tbseparator'};
     var tbfill={xtype: 'tbfill'};
     var fb = new Ext.Toolbar('filebar');
     fb.add(tbspace);
     fb.add(logo);
     fb.add(tbsep);
     var re =/\(/gi;
     for(var i=0;i<menus.length;i++)
     {
        var mname=menus[i].getAttribute("name");
        var submenus=menus[i].getElementsByTagName("submenu");
        var submenu={};
        submenu.id=menus[i].getAttribute("name");
        var submenuitems=[];

         if(submenu.id == "Help")
         {
                var item1={};
                item1.text = "FAQ";
                item1.handler = function()
                    {
                        window.open("FAQ.html","","");
                    }

                submenuitems.push(item1);

                var item2 ={};
                item2.text = "About";
                item2.handler = function ()
                        {
                          var popWin=  new Ext.Window({
                                    title : "About MathEdit",
                                    width : 400,
                                    height : 250,
                                    html : "<p>MathEdit Version 3.0.</p><br><p>Developed By WME&MKM Group, ICCM Lab, Lanzhou University.</p><br><p>Lanzhou University All Rights Reserved.</p>",
                                    resizable : false,
                                    draggable : false,
                                    listeners:[{
                                        "close" : function (){popWin.hide()},
                                        "blur" : function (){popWin.hide()}
                                    }]

                                    });
                            popWin.show();
                                }
                  submenuitems.push(item2);

               // break;
            }

        for(var j=0;j<submenus.length;j++)
        {
            var mid=submenus[j].getAttribute("mid");
            if(mid=="tline")
            {
                submenuitems.push("-");
            }else if(mid.search(/s[0-9]/i)!=-1 && mid.length<=3)//Template
            {
                var item={}
                item.id=mid; //group  id  e.g s1,s2
                item.text=submenus[j].childNodes[0].nodeValue;
                item.checked=false;
                item.checkHandler=showtemplates;

                TemplatesMenuItems.push(item);

                var smenu={};
                smenu.items=getcmls(tfile,mid,"s","","",0);

                item.menu=new Ext.menu.Menu(smenu);
                submenuitems.push(item);
            }else if(submenu.id == "Format")//Format
            {

                    var item={};
                    item.text=submenus[j].childNodes[0].nodeValue;

                    if(item.text =="Bold")
                    {
                        item.text="<b>Bold<b>";
                    }
                    else if(item.text=="Italic")
                    {
                        item.text ="<i>Italic<i>";
                    }else if(item.text=="Font" || item.text =="Font Size")
                    {
                        var sfmenu = {};
                        sfmenu.items = getitems(ftfile,"t",mid,"f");
                        item.menu = new Ext.menu.Menu(sfmenu);
                    }
                    if(item.text != "Font" && item.text != "Font Size")
                    {
                        item.icon=getcmlitem(ftfile,mid,"img","t");
                        if(item.text == "Text Color")
                        {
                            item.handler = eval("");
                            item.menu = new Ext.menu.ColorMenu({
                                                                                id:"forecolor",
                                                                                selectHandler:function(item,color)
                                                                                {
                                                                setColor("#"+color,"Set_ForeColor")
                                                                                }
                                                                                            });
                        }
                        else if(item.text == "BackGround Color" )
                        {
                            item.handler = eval("");
                            item.menu = new Ext.menu.ColorMenu({
                                                                                id:"backcolor",
                                                                                selectHandler:function(item,color)
                                                                                {
                                                                setColor("#"+color,"Set_BackgroundColor")
                                                                                }
                                                                                        });
                        }
                        else
                        {
                            var func = getcmlitem(ftfile,mid,"action","t");
                            func = func.replace(re,".createCallback(");
                            item.handler=eval(func);

                        }
                    }
                    submenuitems.push(item);

            }
            else if(submenu.id == "Tools")//Tools
            {
                var item={};
                item.text=submenus[j].childNodes[0].nodeValue;
                item.icon = getcmlitem(ftfile,mid,"img","t");
                var func = getcmlitem(ftfile,mid,"action","t");
                func = func.replace(re,".createCallback(");
                item.handler=eval(func);
                submenuitems.push(item);

            }
            else//any other
            {
             var item={};
             item.text=submenus[j].childNodes[0].nodeValue;
             item.icon=getcmlitem(ftfile,mid,"img","t");
             item.handler=eval(getcmlitem(ftfile,mid,"action","t"));
             submenuitems.push(item);
          }

        }
        submenu.items=submenuitems;
        var menujson={};
        menujson.text=mname;
        menujson.menu=new Ext.menu.Menu(submenu);
        fb.add(menujson);
     }
   }
 //end Add menubar
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //the follow variable is defined for the fontsize select on the toolbar
function AddFontAndSize()
{

    var FontList = new Array();
    var FontSizeList = new Array();
    var tmpobj;
    var i;

    ////get Font list
    tmpobj= getitems(ftfile,"t","tfont","f");
    for(i = 0;i < tmpobj.length;i++)
    {

         FontList[i] = new Array(tmpobj[i].text);
         //alert(FontList+"\n"+FontList[i]+"\n\n\n"+tt[i]+"\n"+tt[i].text);
    }

    ///// get font size list
    tmpobj = getitems(ftfile,"t","tfontsize","f");
    for(i = 0;i < tmpobj.length;i++)
    {

         FontSizeList[i] = new Array(tmpobj[i].text);
    }
    /* // the static mode implement
	var FontList = [

									["Courier"],
									["Garamond"],
									["Serif"],
									["Times New Roman"],
								];    */
/*var FontSizeList = [
											["8"],
											["10"],
											["12"],
											["16"],
											["20"],
											["24"],
											["28"],
											["32"],
											["40"]
										];
										*/
//////we can simplify the fields to only one just set the "text"  the same as the "value"
    var StoreFL = new Ext.data.SimpleStore({
                        fields:["Font"],
                        data:FontList
                                                                            });

    var StoreFSL = new Ext.data.SimpleStore({
                        fields:["FontSize"],
                        data:FontSizeList
                                                                                });

    var FontSelect = new Ext.form.ComboBox({
                        title:"Font",
                        id:"F",
                        store:StoreFL,
                        editable:false,
                        width:120,
                        emptyText:"Font",
                        displayField:"Font",
                        mode:"local",
                        triggerAction:"all",
                        selectOnFocus:false
                        //hiddenName:"SizeValue"
                        });
    var FontSizeSelect = new Ext.form.ComboBox({
                        title:"Size",
                        id:"FS",
                        store:StoreFSL,
                        editable:false,
                        width:55,
                        emptyText:"Size",
                        displayField:"FontSize",
                        mode:"local",
                        triggerAction:"all",
                        selectOnFocus:true
                        //hiddenName:"SizeValue"
                        });


    // the action may be the handler of the items and the follow is all the same
    // but if the doStyle() is changed ,the following code should be changed
        FontSizeSelect.on("select",function ()
        {
            doStyle("si",Ext.get("FS").dom.value);
        });
        FontSelect.on("select",function ()
        {
            doStyle("fa",Ext.get("F").dom.value);
        });
    tb.addField(FontSelect);
    tb.addField(FontSizeSelect);
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 // Add toolbar
    var osv=getOs();
    var toolbars=xmlDoc.getElementsByTagName("toolbar");
    var re = /\(/gi;
    if(toolbars.length>=1)
    {
      var tb = new Ext.Toolbar('toolbar');
      for(var i=0;i<toolbars.length;i++)
      {
        var tools=toolbars[i].getElementsByTagName("tool");
        for(var j=0;j<tools.length;j++)
        {
            //alert("toolbars.length "+toolbars.length+"\nj"+j+"\nmid "+tools[j].getAttribute("mid"));
          var tooljson={};
          if(tools[j].getAttribute("mid")=="tdragdrop")
          {
          // if(osv!=1)
          // {
             tooljson.id=tools[j].getAttribute("mid");
             tooljson.iconCls='icon_dd1';
          // }
          }
          else
          {
            var icontempA=getcmlitem(ftfile,tools[j].getAttribute("mid"),"img","t");
            if(!IsFileExist(icontempA))
            {
                tooljson.text=getcmlitem(ftfile,tools[j].getAttribute("mid"),"tooltip","t");
                }
                else
                {
                    tooljson.icon=icontempA;
                }
          }

          var func=getcmlitem(ftfile,tools[j].getAttribute("mid"),"action","t");
          func=func.replace(re,".createCallback(");
          tooljson.handler=eval(func);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//use the following code to change the html setColor (version 1.0)to ext (version 2.1)

           if(tools[j].getAttribute("mid") == "tforecolor" || tools[j].getAttribute("mid") == "tbackcolor")
          {
            //discard the infomation gained above and assign new
            //alert(tools[j].getAttribute("mid"));
            tooljson = new Ext.Toolbar.Button();
            if(tools[j].getAttribute("mid") == "tforecolor")
            {
                tooljson.menu = new Ext.menu.ColorMenu({
                                                                                id:"forecolor",
                                                                                selectHandler:function(item,color)
                                                                                {
                                                                setColor("#"+color,"Set_ForeColor")
                                                                                }

                                                                                            });
            }
            else
            {
                tooljson.menu = new Ext.menu.ColorMenu({
                                                                                id:"backcolor",
                                                                                selectHandler:function(item,color)
                                                                                {
                                                                setColor("#"+color,"Set_BackgroundColor")
                                                                                }
                                                                                            });

            }


            //tooljson.icon=getcmlitem(ftfile,tools[j].getAttribute("mid"),"img","t");
                    var icontempB=getcmlitem(ftfile,tools[j].getAttribute("mid"),"img","t");
          if(!IsFileExist(icontempB))
          {
            tooljson.text=getcmlitem(ftfile,tools[j].getAttribute("mid"),"tooltip","t");
            }
            else
            {
                tooljson.icon=icontempB;
            }
            tooljson.handler=eval("");

          }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          tooljson.tooltip=getcmlitem(ftfile,tools[j].getAttribute("mid"),"tooltip","t");
          tooljson.cls='x-btn-icon';
          tb.add(tooljson);
          if(tools[j].getAttribute("mid")=="tdragdrop")
          {
            AddFontAndSize();//////////new add
          }
        }
      }
      var closetb={
       icon:'image/close.png',
       cls:'x-btn-icon',
       handler:function(){tb.hide();}
      };


      tb.add(tbfill);
     // tb.add(closetb); no close for the toolbar
   }
 //end Add toolbar

 //Add templates
    var templates=xmlDoc.getElementsByTagName("templates");
    if(templates.length>=1)
    {
      var mb = new Ext.Toolbar('templatebar');
      var re = /\(/gi;
      for(var i=0;i<templates.length;i++)
      {
        var temps=templates[i].getElementsByTagName("template");
        for(var j=0;j<temps.length;j++)
        {
            /*if(osv==1)
            {
             var tempjson={};
           var func=getcmlitem(tfile,temps[j].getAttribute("mid"),"action","s");

              func=func.replace(re,".createCallback(");
              tempjson.handler=eval(func);

            }
            else //here calls the inserttemp() which makes the divide template no action
            {
           var tempjson=new Ext.Toolbar.Button();

           tempjson.handler=eval("inserttemp.createCallback('"+temps[j].getAttribute('mid')+"')");
         }
*/
            var tempjson=new Ext.Toolbar.Button();

           tempjson.handler=eval("inserttemp.createCallback('"+temps[j].getAttribute('mid')+"')");
          //tempjson.icon=getcmlitem(tfile,temps[j].getAttribute("mid"),"img","s");
          var icontempC=getcmlitem(tfile,temps[j].getAttribute("mid"),"img","s");
          if(!IsFileExist(icontempC))
          {
            tempjson.text=getcmlitem(tfile,temps[j].getAttribute("mid"),"tooltip","s");
            }
            else
            {
                tempjson.icon=icontempC;
            }

          tempjson.tooltip=getcmlitem(tfile,temps[j].getAttribute("mid"),"tooltip","s");
          tempjson.cls='x-btn-icon';
         // tempjson.addListener('Click',dd);

        // alert("icon: "+tempjson.icon+"\nfunc:  "+tempjson.handler);
          mb.add(tempjson);

        }
      }
      var closemb={
       icon:'image/close.png',
       cls:'x-btn-icon',
       handler:function(){mb.hide();}
      };
      mb.add(tbfill);
     // mb.add(closemb); no close for the template bar
     // mb.addListener('mouseout','dd');
    }
  //end Add templates


   var design={
     region:'center',
     bodyStyle: 'padding:0px 0px 0px 0px',
     contentEl: 'designarea'
     };


   var actionPanel = {
        region:'north',
        border:false,
        contentEl: 'head'
    };
    var acitems=[];
    if(fb!=null && fb!="undefined")
    {
        acitems.push(fb);
    }
    if(tb!=null && tb!="undefined")
    {
        acitems.push(tb);
    }
    if(mb!=null && mb!="undefined")
    {
        acitems.push(mb);
    }
    actionPanel.items=acitems;
    // Main (Tabbed) Panel

    var mmlcreplace=new Ext.Button({text:'replace',handler:editmmlc});
    var mmlcinput=new Ext.form.TextArea({
        id:'mmlcinput',
        hideLabel:1,
      name: 'mmlcinput',
      height:183,
      autocomplete: 'off',
      anchor:'100%'
        });
    mmlcinput.on('focus',infixfocus);
    mmlcinput.on('blur',infixblur);
    var mmlcform = new Ext.FormPanel({
          frame:true,
          border:false,
        items:[mmlcinput],
        buttons:[mmlcreplace]
    });


    var mmlpinput=new Ext.form.TextArea({
        id:'mmlpinput',
        hideLabel:1,
      name: 'mmlpinput',
      height:183,
      autocomplete: 'off',
      readOnly:true,
      anchor:'100%'
        });
    mmlpinput.on('focus',infixfocus);
    mmlpinput.on('blur',infixblur);
    var mmlpform = new Ext.FormPanel({
          frame:true,
          border:false,
          height:230,
        items:[mmlpinput]
    });


    var openmreplace=new Ext.Button({text:'replace',handler:editopenm});
    var openminput=new Ext.form.TextArea({
        id:'openminput',
        hideLabel:1,
      name: 'openminput',
      height:183,
      autocomplete: 'off',
      anchor:'100%'
        });
    openminput.on('focus',infixfocus);
    openminput.on('blur',infixblur);
    var openmform = new Ext.FormPanel({
          frame:true,
          border:false,
        items:[openminput],
        buttons:[openmreplace]
    });

    var latexinput=new Ext.form.TextArea({
        id:'latexinput',
        hideLabel:1,
      name: 'latexinput',
      height:183,
      autocomplete: 'off',
      readOnly:true,
      anchor:'100%'
        });
    latexinput.on('focus',infixfocus);
    latexinput.on('blur',infixblur);
    var latexform = new Ext.FormPanel({
          frame:true,
          border:false,
          height:230,
        items:[latexinput]
    });

//*brailletab
    var brailleinput=new Ext.form.TextArea({
        id:'brailleinput',
        hideLabel:1,
      name: 'brailleinput',
      height:183,
      autocomplete: 'off',
      readOnly:true,
      anchor:'100%'
        });
    brailleinput.on('focus',infixfocus);
    brailleinput.on('blur',infixblur);
    var brailleform = new Ext.FormPanel({
          frame:true,
          border:false,
          height:230,
        items:[brailleinput]
    });
//*/

//Add infix input form
    infixinputbox1=new Ext.form.TextField({
         hideLabel:1,
       id:'infixinputbox',
       name: 'infixinputbox',
       height:34,
       cls:'infixin',
       autocomplete: 'off',
       anchor:'100%'
    });
    infixinputbox1.on('focus',infixfocus);
    infixinputbox1.on('blur',infixblur);
    var inreplace=new Ext.Button({text:'replace',handler:disInfixChange});
    var inform = new Ext.FormPanel({
        frame:true,
        title: 'Infix for Whole Expression',
        style:'padding:16px 5px 0px 5px',

        items: [infixinputbox1],
        buttons: [inreplace]
    });
//End Add infix input form

    sinfixinputbox=new Ext.form.TextField({
         hideLabel:1,
       id:'sinfixinputbox',
       name: 'sinfixinputbox',
       height:34,
       cls:'infixin',
       autocomplete: 'off',
       anchor:'100%'
    });
    sinfixinputbox.on('focus',infixfocus);
    sinfixinputbox.on('blur',infixblur);
    var sinreplace=new Ext.Button({text:'replace',handler:selectedInfixChange});
    var sinform = new Ext.FormPanel({
        frame:true,
        title: 'Infix for Selected Expression',
        style:'padding:43px 5px 0px 5px',
        items: [sinfixinputbox],
        buttons: [sinreplace]
    });

    var linearinputs=xmlDoc.getElementsByTagName("linearinput");
    var lineartype=0;
    if(linearinputs.length>=1)
    {
        var leftitems=[];
        var tabPanel = new Ext.TabPanel({
        	style:"display:none;",
            id:"tabPanel",
            columnWidth:.55,
            deferredRender:false,
            layoutOnTabChange:true,
            autoScroll: false,
            height:280,
        minHeight: 0,
        border:false,
        collapsible: true,
        collapseMode: 'mini',
            activeTab:0
        });


         //use six bit: 1st,infix; 2nd,sinfix; 3rd, mmlc; 4th, mmlp; 5th, openm; 6th, latex
        for(var i=0;i<linearinputs.length;i++)
        {
           // alert(linearinputs.length);
         if(linearinputs[i].getAttribute("mid")=="infix")
       {
        leftitems.push(inform);
        lineartype=lineartype|1;
       }
       if(linearinputs[i].getAttribute("mid")=="sinfix")
       {
        leftitems.push(sinform);
        lineartype=lineartype|2;
       }
       if(linearinputs[i].getAttribute("mid")=="mmlc")
       {
        var tab1={
                title: 'MathML-Content',
                contentEl:'mmlc',
                closable:true,
                autoScroll:true,
                items:[mmlcform]
            };
            tabPanel.add(tab1);
        lineartype=lineartype|4;
       }
       if(linearinputs[i].getAttribute("mid")=="mmlp")
       {
        var tab2={
                title: 'MathML-Presentation',
                contentEl:'mmlp',
                closable:true,
                autoScroll:true,
                items:[mmlpform]
            };
            tabPanel.add(tab2);
        lineartype=lineartype|8;
       }
       if(linearinputs[i].getAttribute("mid")=="openm")
       {
        var tab3={
                title: 'OpenMath',
                contentEl:'openm',
                closable:true,
                autoScroll:true,
                items:[openmform]
            };
        tabPanel.add(tab3);
        lineartype=lineartype|16;
       }
       if(linearinputs[i].getAttribute("mid")=="latex")
       {
        var tab4={
                title: 'LaTex',
                contentEl:'latex',
                closable:true,
                autoScroll:true,
                items:[latexform]
            };
        tabPanel.add(tab4);
        lineartype=lineartype|16;
       }
//*brailletab
       if(linearinputs[i].getAttribute("mid")=="braille")
       {
        var tab5={
                title: 'Braille',
                contentEl:'braille',
                closable:true,
                autoScroll:true,
                items:[brailleform]
            };
        tabPanel.add(tab5);
        lineartype=lineartype|16;
       }

//*/
      }

       var srcarea={
       	style:"display:none;",
        title:' ',
        region:'south',
        collapsible: true,
        layout:'column',
        height:300,
        baseCls:'x-plain'
       };
       if(lineartype>0)
       {
         var srcareaitems=[];
       }
       if(lineartype&3>0)
       {
        var srcarealeft={};
        srcarealeft.columnWidth=.40;
        srcarealeft.baseCls='x-plain';
        srcarealeft.items=leftitems;
        srcareaitems.push(srcarealeft);
       }
       if(lineartype&28>0)
       {
        srcareaitems.push(tabPanel);
       }
       if(lineartype>0)
       {
        srcarea.items=srcareaitems;
       }
    }
    var viewportitems=[];
    viewportitems.push(actionPanel);
    viewportitems.push(design);
    if(lineartype>0)
    {
        viewportitems.push(srcarea);
    }
    viewport = new Ext.Viewport({
           layout:'border',
           items:viewportitems
           });
    function clickHandler(item, e) {
        alert('Clicked on the menu item: ' + item.text);
    }

    function checkHandler(item, e) {
        alert('Checked the item: ' + item.text);
    }


/////////Set tabs content when the tab is active

       tabPanel.on("tabchange",function()
        {
            //alert(tabPanel.getActiveTab().title);;
            chgsrcWhenActive(tabPanel.getActiveTab());
        });


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//the original code start

/*
    var inwindow = new Ext.Window({
        width: 300,
        height:100,
        minWidth: 100,
        minHeight: 30,
        html:"",
        layout: 'fit',
        plain:true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        x:300,
        y:100
    });

    function showtemplates(item,e)
    {
    	inwindow.setTitle(item.text);
    	inwindow.show();
    }
  */

//the original code end
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////
//the code modified start



//windows's manager for submenus of the Templates menu as window s1,s2,s3,s4,s5,s6,s7,s8,s9 from top to bottom
var templateWindowGroup = new Ext.WindowGroup();

var sumWins = 0; //record the current popup windows;
//create a new according to the argument item which is one of the submenus of the Templates menu
//item attributes:id,text,checked was  set building the file menu;
function createANewWindow(item)
{

    var templateItems = getcmls(tfile,item.id,"s","","plainjs",1);
    //tempjson.handler=eval("inserttemp.createCallback('"+temps[j].getAttribute('mid')+"')");
    var htmlcontent="";
    for(var i = 0; i < templateItems.length; i++)
    {
        templateItems[i].handler = "inserttemp('"+templateItems[i].text+"')";
        var end = templateItems[i].text.length;
            //show as icon
            htmlcontent += "<a href=\"#\"  onclick =\""+templateItems[i].handler+"\"><img src=\"" + templateItems[i].icon + "\" title=\"" +templateItems[i].text.substring(2,end) +"\" /></a>";
            //show as button
        //htmlcontent +="<button border=0"+ " alt=\"" +templateItems[i].text + "\" title=\"" +templateItems[i].text+"\"  onclick =\""+templateItems[i].handler +"\">"+"<img src=\"" + templateItems[i].icon +"\">"+"</button>&nbsp;&nbsp"
    }

//    var docWidth = document.body.clientWidth;
//    var docHeight = document.body.clientHeight;
    var awindow = new Ext.Window({
                title:item.text,
                id:item.id,
                width: 315,
                height:135,
                minWidth: 100,
                minHeight: 30,
                html:htmlcontent,
                layout: 'fit',
                plain:true,
                modal:false,
                floating:true,
                shadow:true,
                bodyStyle:'padding:5px;',
                buttonAlign:'center',
                //x:docWidth,
                //y:docHeight,
                draggable:true,
                resizable:false,
                manager:templateWindowGroup
    });
    sumWins++;
//    awindow.setPosition(docWidth-315-270+sumWins * 30,docHeight-300-135-270+sumWins * 30);
    return awindow;
}

function showtemplates(item)
{

    var docWidth = document.body.clientWidth;
    var docHeight = document.body.clientHeight;
//alert(Ext.getCmp(item.id).checkHandler);

    var aTemplateBox;
    //show the templatebox window
    if(!templateWindowGroup.get(item.id))
    {
        aTemplateBox  = createANewWindow(item);

    }
    else
    {
        aTemplateBox = templateWindowGroup.get(item.id);

    }

   // getWthHght();
    //check the checkbox
    if(item.checked == true)
    {
        //aTemplateBox.setPosition(docWidth-315-500+sumWins * 50,docHeight-300-135-300+sumWins * 30);
        aTemplateBox.setPosition(docWidth-275-sumWins * 50,docHeight-785+150+sumWins * 30);
        aTemplateBox.show(this);
        aTemplateBox.syncSize();
/*        aTemplateBox.syncSize();
        aTemplateBox.syncSize();*/
    }
    else
    {
        aTemplateBox.close();
    }


    aTemplateBox.on("close",function()
                                                    {
                                                        aTemplateBox.hide();
                                                        item.setChecked(false);
                                                        sumWins--;
                                                    }
                                    );
}

/////////
function getWthHght(item)
{
    var arrWidth = new Array();
    var arrHeight = new Array();
    for(var i = 1; i < 10; i++)
    {
        alert(TemplatesMenuItems[i-1].checked);
        var tmpItem = templateWindowGroup.get("s"+i);
       // alert("index = "+i +"\ntmpItem = "+tmpItem);
        if(tmpItem != null && TemplatesMenuItems[i-1].checked == true)
        {
            alert("12345="+tmpItem.id);
            arrWidth[i-1] = tmpItem.getWidth();
            arrHeight[i-1] = tmpItem.getHeight();
        }
    }
    alert("width = "+arrWidth);
    alert("height = "+arrHeight.join('--'));

}

////the code modified end
/////////////////////////////////////////////////////////////////////////////////////////////////////

});