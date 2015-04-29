/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    init: function() {
        // custom logic before getXYZ methods get called...
        this.callParent();
        this.desktop.initShortcut();
        // now ready...
    },

    getModules : function(){
    	return getStartMenu;
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();
        
        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

            contextMenuItems: [
            	/*{ text: '刷新', handler: function(){
            		location.reload();
            	}, scope: me },*/
                { text: '更换背景', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: iconModule
            }),

            wallpaper: '/ext/images/wallpapers/desk.jpg',
            wallpaperStretch: true
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();
        var name = userName;
		userName = null;
        return Ext.apply(ret, {
            title: name,
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'设置背景',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'安全退出',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },
    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [/*
                { name: '你好', iconCls: 'accordion', module: 'acc-win' },
                { name: '你好1', iconCls: 'icon-grid', module: 'grid-win' },
                { name: '你好2', iconCls: 'icon-grid', module: 'grid-win' }
            */]/*,
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]*/
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('安全退出', '您是否确定要退出系统？',
        
            function(btn){
                if(btn == 'yes'){
                
					Ext.MessageBox.wait('安全退出', '正在安全退出中', {
					   interval : 100
					});
					
					Ext.Ajax.request({
					    url : 'a.aa',
					    method : 'POST',
					    params : {ids : ''},
					    success : function(response, options) {
					    },
					    failure : function(){
					        window.opener=null;
					        window.open('','_self');
					        window.close();
					    }
					}); 
                }
            }
        );
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
