/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.ShortcutModel
 * @extends Ext.data.Model
 * This model defines the minimal set of fields for desktop shortcuts.
 */
Ext.define('Ext.ux.desktop.ShortcutModel', {
    extend: 'Ext.data.Model',
    fields: [
       { name: 'name' },
       { name: 'iconCls' },
       { name: 'module' },
       { name: 'rootId' },
       { name: 'count' },
       { name: 'func' }
    ]
});

/*
 * 2013-6-14
 * Aaron
 * 公用窗口
 */
Ext.define('MyDesktop.PublicWindow', {
    extend: 'Ext.ux.desktop.Module',

    id:'publicwindow-win',

    createWindow : function(obj){
    	
    	var width = obj.width;
    	var height = obj.height;
    	var title = obj.title;
    	
    	if ( title == undefined ) {
    		title = ''
    	}
    	
    	if ( width == undefined ) {
    		width = whInit('mid').width;
    	}
    	
    	if ( height == undefined ) {
    		height = whInit('mid').height;
    	}
    	
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('publicwindow-win');
        if(!win){
            win = desktop.createWindow({
            	id:'publicwindow-win',
                title: title,
                width: width,
                height: height,
                minimizable:false,
                maximizable: (obj.max)?true:false,
                resizable: (obj.max)?true:false,
                layout: 'fit',
                modal:true,
                plain: true,
                html:'<iframe style="width:100%;height:100%;" frameborder="no" border="0" src="'+obj.url+'"></iframe>'
            });
        }
        win.show();
        return win;
    }
});

Ext.define('MyDesktop.PublicWindow1', {
    extend: 'Ext.ux.desktop.Module',

    id:'publicwindow1-win',

    createWindow : function(obj){
    	
    	var width = obj.width;
    	var height = obj.height;
    	var title = obj.title;
    	
    	if ( title == undefined ) {
    		title = ''
    	}
    	
    	if ( width == undefined ) {
    		width = whInit('mid').width;
    	}
    	
    	if ( height == undefined ) {
    		height = whInit('mid').height;
    	}
    	
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('publicwindow1-win');
        if(!win){
            win = desktop.createWindow({
            	id:'publicwindow1-win',
                title: title,
                width: width,
                height: height,
                minimizable:false,
                maximizable: (obj.max)?true:false,
                layout: 'fit',
                modal:true,
                plain: true,
                html:'<iframe style="width:100%;height:100%;" frameborder="no" border="0" src="'+obj.url+'"></iframe>'
            });
        }
        win.show();
        return win;
    }
});

function closePublicWindow() {
	Ext.getCmp('publicwindow-win').close();
}

function parentCloseWindow(id) {
	Ext.getCmp(id).close();
}
function closePublicWindow1() {
	Ext.getCmp('publicwindow1-win').close();
}



/*短信发邮件*/
Ext.define('MyDesktop.SMS', {
    extend: 'Ext.ux.desktop.Module',

    id:'sms-win',

    createWindow : function(){
    	
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('sms-win');
        if(!win){
            win = desktop.createWindow({
            	id:'sms-win',
                title: '发送短信',
                width: 400,
                maximizable:false,
                layout: 'fit',
                plain: true,
                items: Ext.create('Ext.form.Panel', {
			        bodyPadding : 5,
			        overflowY : 'auto',
			        border:false,
		         	url : '/samp/register/addSampRegister.action',
			        defaultType : 'textfield',
			        fieldDefaults: {
			            anchor: '100%',
			            allowBlank: true,
			            labelWidth:70,
			            msgTarget: 'side'
			        },
		        	items: [{
			        	fieldLabel: '收信人',
			        	value:'dhjtyxgs@123.com,'
			        },{
			        	fieldLabel: '收信人名称',
			        	value:'上海航空公司,'
			        },{
			        	fieldLabel: '短信内容',
			        	height:70,
			        	xtype:'textarea'
			        }],
			        buttons:[{
		             	text:'发送',
						handler:function(){
							formSubmit(form, _this.window, grid);
						}
		            }]
			    })
            });
        }
        win.show();
        return win;
    }
});
Ext.define('MyDesktop.Email', {
    extend: 'Ext.ux.desktop.Module',

    id:'email-win',

    createWindow : function(){
    	
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('email-win');
        if(!win){
            win = desktop.createWindow({
            	id:'email-win',
                title: '发邮件',
                width:whInit('mid').width,
                height:515,
//                maximizable: (obj.max)?true:false,
                layout: 'fit',
                plain: true,
                items: Ext.create('Ext.form.Panel', {
			        bodyPadding : 5,
			        overflowY : 'auto',
			        border:false,
		         	url : '/samp/register/addSampRegister.action',
			        defaultType : 'textfield',
			        fieldDefaults: {
			            anchor: '100%',
			            allowBlank: true,
			            labelWidth:80,
			            msgTarget: 'side'
			        },
		        	items: [{
			        	fieldLabel: '外部收件人',
			        	value:'dhjtyxgs@123.com,'
			        },{
			        	fieldLabel: '收件人名称',
			        	value:'上海航空公司,'
			        },{
			        	fieldLabel: 'Internet邮箱',
	                    xtype: 'combo',
	                    displayField: 'name',
				        queryMode: 'local',
				        typeAhead: true,
				        editable:false,
				        value: '234756@qq.com',
				        store: Ext.create('Ext.data.Store', {
					        autoDestroy: true,
					        fields: [
						        {type: 'string', name: 'name'}
						    ],
					        data: [
					        	{'name':'234756@qq.com'},
					        	{'name':'sales@163.com'}
							]
					    })
			        },{
			        	fieldLabel: '邮件主题'
			        },{
			        	fieldLabel: '邮件内容',
			        	height:300,
			        	xtype:'htmleditor'
			        },{
			        	fieldLabel: '附件选择',
		                xtype: 'fileuploadfield',
		                emptyText: '请选择您本地的文件',
		                buttonConfig: {
		                	text: '浏览',
			                iconCls: 'upload-icon'
			            }
			        }],
			        buttons:[{
		             	text:'立即发送',
						handler:function(){
							//formSubmit(form, _this.window, grid);
						}
		            },{
		             	text:'保存至草稿箱',
						handler:function(){
							//formSubmit(form, _this.window, grid);
						}
		            }]
			    })
            });
        }
        win.show();
        return win;
    }
});
