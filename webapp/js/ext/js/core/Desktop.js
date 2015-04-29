/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.Desktop
 * @extends Ext.panel.Panel
 * <p>This class manages the wallpaper, shortcuts and taskbar.</p>
 */
var desktopThis;
function desktopIconOder(){
	desktopThis.handleUpdate();
}
Ext.define('Ext.ux.desktop.Desktop', {
    extend: 'Ext.panel.Panel',

    alias: 'widget.desktop',
	
	id:'desktopPanel',
	
    uses: [
        'Ext.util.MixedCollection',
        'Ext.menu.Menu',
        'Ext.view.View', // dataview
        'Ext.window.Window',

        'Ext.ux.desktop.TaskBar',
        'Ext.ux.desktop.Wallpaper'
    ],

    activeWindowCls: 'ux-desktop-active-win',
    inactiveWindowCls: 'ux-desktop-inactive-win',
    lastActiveWindow: null,

    border: false,
    html: '&#160;',
    layout: 'fit',

    xTickSize: 1,
    yTickSize: 1,

    app: null,

    /**
     * @cfg {Array|Store} shortcuts
     * The items to add to the DataView. This can be a {@link Ext.data.Store Store} or a
     * simple array. Items should minimally provide the fields in the
     * {@link Ext.ux.desktop.ShorcutModel ShortcutModel}.
     */
    shortcuts: null,

    /**
     * @cfg {String} shortcutItemSelector
     * This property is passed to the DataView for the desktop to select shortcut items.
     * If the {@link #shortcutTpl} is modified, this will probably need to be modified as
     * well.
     */
    shortcutItemSelector: 'div.ux-desktop-shortcut',

    /**
     * @cfg {String} shortcutTpl
     * This XTemplate is used to render items in the DataView. If this is changed, the
     * {@link shortcutItemSelect} will probably also need to changed.
     */
    shortcutTpl: [
        '<tpl for=".">',
            '<div class="ux-desktop-shortcut ux-desktop-shortcut1" id="{name}-shortcut" name="shortcut-{rootId}">',
            	'<div class="count count{count}"></div>',
                '<div class="ux-desktop-shortcut-icon {iconCls}">',
                    '<img src="',Ext.BLANK_IMAGE_URL,'" title="{name}">',
                '</div>',
                '<div class="ux-desktop-shortcut-text">',
                	'<table>',
                		'<tr>',
                			'<td class="ux-table-left"></td>',
                			'<td class="ux-table-name">{name}</td>',
                			'<td class="ux-table-right"></td>',
                		'</tr>',
                	'</table>',
                '</div>',
            '</div>',
        '</tpl>',
        '<div class="x-clear"></div>'
    ],

    /**
     * @cfg {Object} taskbarConfig
     * The config object for the TaskBar.
     */
    taskbarConfig: null,

    windowMenu: null,

    initComponent: function () {
        var me = this;

        me.windowMenu = new Ext.menu.Menu(me.createWindowMenu());

		/*var task = me.taskbar = new Ext.ux.desktop.TaskBar(me.taskbarConfig);
		var panel = Ext.create('Ext.Panel', {
	        id: 'taskbar-main-panel',
	        layout: 'fit',
	        renderTo: Ext.getBody(),
	        border:false,
	        frame:false,
	        tbar: [{xtype : "tbfill"},task]
	    });*/

        me.bbar = me.taskbar = new Ext.ux.desktop.TaskBar(me.taskbarConfig);
        me.taskbar.windowMenu = me.windowMenu;
        
        me.windows = new Ext.util.MixedCollection();

        me.contextMenu = new Ext.menu.Menu(me.createDesktopMenu());

        me.items = [
            { xtype: 'wallpaper', id: me.id+'_wallpaper' },
            me.createDataView()
        ];

        me.callParent();

        me.shortcutsView = me.items.getAt(1);
        me.shortcutsView.on('itemclick', me.onShortcutItemClick, me);

        var wallpaper = me.wallpaper;
        me.wallpaper = me.items.getAt(0);
        if (wallpaper) {
            me.setWallpaper(wallpaper, me.wallpaperStretch);
        }
    },

    afterRender: function () {
        var me = this;
        me.callParent();
        me.el.on('contextmenu', me.onDesktopMenu, me);
    },

    //------------------------------------------------------
    // Overrideable configuration creation methods

    createDataView: function () {

        var me = this;
        return {
            xtype: 'dataview',
            overItemCls: 'x-view-over',
            trackOver: true,
            itemSelector: me.shortcutItemSelector,
            store: me.shortcuts,
            style: {
                position: 'absolute'
            },
            x: 0, y: 0,
            tpl: new Ext.XTemplate(me.shortcutTpl),
            listeners:{  
				resize:function(){ 
				    me.initShortcut();
                } 
            }
            
        };
    },

    createDesktopMenu: function () {
        var me = this, ret = {
            items: me.contextMenuItems || []
        };

        if (ret.items.length) {
            ret.items.push('-');
        }

        ret.items.push(
                { text: '窗口分布排列', handler: me.tileWindows, scope: me, minWindows: 1 },
                { text: '窗口叠加排列', handler: me.cascadeWindows, scope: me, minWindows: 1 })

        return ret;
    },

    createWindowMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                { text: '还原', handler: me.onWindowMenuRestore, scope: me },
                { text: '最小化', handler: me.onWindowMenuMinimize, scope: me },
                { text: '最大化', handler: me.onWindowMenuMaximize, scope: me },
                '-',
                { text: '关闭', handler: me.onWindowMenuClose, scope: me }
            ],
            listeners: {
                beforeshow: me.onWindowMenuBeforeShow,
                hide: me.onWindowMenuHide,
                scope: me
            }
        };
    },

    //------------------------------------------------------
    // Event handler methods

    onDesktopMenu: function (e) {
        var me = this, menu = me.contextMenu;
        e.stopEvent();
        if (!menu.rendered) {
            menu.on('beforeshow', me.onDesktopMenuBeforeShow, me);
        }
        menu.showAt(e.getXY());
        menu.doConstrain();
    },

    onDesktopMenuBeforeShow: function (menu) {
        var me = this, count = me.windows.getCount();

        menu.items.each(function (item) {
            var min = item.minWindows || 0;
            item.setDisabled(count < min);
        });
    },

    onShortcutItemClick: function (dataView, record) {
    	/*record.data.func 传递具体功能*/
        var me = this, module = me.app.getModule(record.data.module),
            win = module && module.createWindow('min-'+record.data.iconCls, record.data.func);
            
        if (win) {
            me.restoreWindow(win);
            
            /*扩展参数初始化window是否全屏 true为默认全屏*/
            if(win.initMax) {
            	win.maximize();
        		win.toFront();
            }
        }
    },

    onWindowClose: function(win) {
        var me = this;
        if(win.maskModule != undefined && win.maskModule.getEl() != undefined){
        	win.maskModule.getEl().unmask();
        }
        
        if(Ext.getCmp(win.maskWin)){
        	Ext.getCmp(win.maskWin).unmask();
        }
        
        if(win.id == 'publicwindow-win' || win.id == 'publicwindow1-win' ) {
        	Ext.getBody().unmask();
        }
        me.windows.remove(win);
        me.taskbar.removeTaskButton(win.taskButton);
        me.updateActiveWindow();
        /******************************************************/
    },

    //------------------------------------------------------
    // Window context menu handlers

    onWindowMenuBeforeShow: function (menu) {
        var items = menu.items.items, win = menu.theWin;
        items[0].setDisabled(win.maximized !== true && win.hidden !== true); // Restore
        var min = win.minimized === true,
        	max = win.maximized === true || win.hidden === true;
        if(!win.minimizable){
        	min = true;
        }
        items[1].setDisabled(min); // Minimize
        
        if(!win.maximizable){
        	max = true;
        }
        items[2].setDisabled(max); // Maximize
    },

    onWindowMenuClose: function () {
        var me = this, win = me.windowMenu.theWin;
        win.close();
    },

    onWindowMenuHide: function (menu) {
        Ext.defer(function() {
            menu.theWin = null;
        }, 1);
    },

    onWindowMenuMaximize: function () {
        var me = this, win = me.windowMenu.theWin;

        win.maximize();
        win.toFront();
    },

    onWindowMenuMinimize: function () {
        var me = this, win = me.windowMenu.theWin;
        
        win.minimize();
    },

    onWindowMenuRestore: function () {
        var me = this, win = me.windowMenu.theWin;

        me.restoreWindow(win);
    },

    //------------------------------------------------------
    // Dynamic (re)configuration methods

    getWallpaper: function () {
        return this.wallpaper.wallpaper;
    },

    setTickSize: function(xTickSize, yTickSize) {
        var me = this,
            xt = me.xTickSize = xTickSize,
            yt = me.yTickSize = (arguments.length > 1) ? yTickSize : xt;

        me.windows.each(function(win) {
            var dd = win.dd, resizer = win.resizer;
            dd.xTickSize = xt;
            dd.yTickSize = yt;
            resizer.widthIncrement = xt;
            resizer.heightIncrement = yt;
        });
    },

    setWallpaper: function (wallpaper, stretch) {
        this.wallpaper.setWallpaper(wallpaper, stretch);
        return this;
    },

    //------------------------------------------------------
    // Window management methods

    cascadeWindows: function() {
        var x = 0, y = 0,
            zmgr = this.getDesktopZIndexManager();

        zmgr.eachBottomUp(function(win) {
            if (win.isWindow && win.isVisible() && !win.maximized) {
                win.setPosition(x, y);
                x += 20;
                y += 20;
            }
        });
    },

    createWindow: function(config, cls) {
        var me = this, win, cfg = Ext.applyIf(config || {}, {
                stateful: false,
                isWindow: true,
                //constrainHeader: true,
                constrainHeader: true,
                //collapsible: true,
                minimizable: true,
                maximizable: true
            });
        cls = cls || Ext.window.Window;
        win = me.add(new cls(cfg));
        
        //alert(win.id);
        if(win.maskModule != undefined && win.maskModule.getEl() != undefined){
        	win.maskModule.getEl().mask();
        }
        
        if(Ext.getCmp(win.maskWin)){
        	Ext.getCmp(win.maskWin).mask();
        }

        me.windows.add(win);
		        
        win.taskButton = me.taskbar.addTaskButton(win);
        /********去掉效果*********/
        //win.animateTarget = win.taskButton.el;
		
		win.height = (win.height > Ext.get('desktopPanel-body').getHeight())? whInit('max').height : win.height;
		
        win.on({
            activate: me.updateActiveWindow,
            beforeshow: me.updateActiveWindow,
            deactivate: me.updateActiveWindow,
            minimize: me.minimizeWindow,
            destroy:me.onWindowClose,
            scope: me
        });

        win.on({
            boxready: function () {
                win.dd.xTickSize = me.xTickSize;
                win.dd.yTickSize = me.yTickSize;

                if (win.resizer) {
                    win.resizer.widthIncrement = me.xTickSize;
                    win.resizer.heightIncrement = me.yTickSize;
                }
            },
            single: true
        });

        // replace normal window close w/fadeOut animation:
        win.doClose = function ()  {
            win.doClose = Ext.emptyFn; // dblclick can call again...
            win.el.disableShadow();
            win.destroy();
            /*
            win.el.fadeOut({
                listeners: {
                    afteranimate: function () {
                        win.destroy();
                    }
                }
            });*/
        };

        return win;
    },

    getActiveWindow: function () {
        var win = null,
            zmgr = this.getDesktopZIndexManager();

        if (zmgr) {
            // We cannot rely on activate/deactive because that fires against non-Window
            // components in the stack.

            zmgr.eachTopDown(function (comp) {
                if (comp.isWindow && !comp.hidden) {
                    win = comp;
                    return false;
                }
                return true;
            });
        }

        return win;
    },

    getDesktopZIndexManager: function () {
        var windows = this.windows;
        // TODO - there has to be a better way to get this...
        return (windows.getCount() && windows.getAt(0).zIndexManager) || null;
    },

    getWindow: function(id) {
        return this.windows.get(id);
    },

    minimizeWindow: function(win) {
        win.minimized = true;
        win.hide();
    },

    restoreWindow: function (win) {
        if (win.isVisible()) {
            win.restore();
            win.toFront();
        } else {
            win.show();
        }
        return win;
    },

    tileWindows: function() {
        var me = this, availWidth = me.body.getWidth(true);
        var x = me.xTickSize, y = me.yTickSize, nextY = y;

        me.windows.each(function(win) {
            if (win.isVisible() && !win.maximized) {
                var w = win.el.getWidth();

                // Wrap to next row if we are not at the line start and this Window will
                // go off the end
                if (x > me.xTickSize && x + w > availWidth) {
                    x = me.xTickSize;
                    y = nextY;
                }

                win.setPosition(x, y);
                x += w + me.xTickSize;
                nextY = Math.max(nextY, y + win.el.getHeight() + me.yTickSize);
            }
        });
    },

    updateActiveWindow: function () {
        var me = this, activeWindow = me.getActiveWindow(), last = me.lastActiveWindow;
        if (activeWindow === last) {
            return;
        }

        if (last) {
            if (last.el.dom) {
                last.addCls(me.inactiveWindowCls);
                last.removeCls(me.activeWindowCls);
            }
            last.active = false;
        }

        me.lastActiveWindow = activeWindow;

        if (activeWindow) {
            activeWindow.addCls(me.activeWindowCls);
            activeWindow.removeCls(me.inactiveWindowCls);
            activeWindow.minimized = false;
            activeWindow.active = true;
        }

        me.taskbar.setActiveButton(activeWindow && activeWindow.taskButton);
    },
    initShortcut : function() {
		var btnHeight = 98;
		var btnWidth = 100;
		var btnPadding = 8;
		this.col = null;
		this.row = null;
		var bottom;
		var bodyHeight = Ext.getBody().getHeight();
		function initColRow() {
			col = {
				index : 1,
				x : btnPadding
			};  
			row = {
				index : 1,
				y : btnPadding
			};
		}
		this.setXY = function(item) {
			bottom = row.y + btnHeight + 20;
			if (bottom > bodyHeight && bottom > (btnHeight + btnPadding)) {
				col = {
					index : col.index++,
					x : col.x + btnWidth + btnPadding
				};
				row = {
					index : 1,
					y : btnPadding
				};
			}
			Ext.fly(item).setXY([col.x, row.y]);
			row.y = row.y + btnHeight + btnPadding + 4;
		}
		this.handleUpdate = function() {
			initColRow();
			var items = Ext.query(".ux-desktop-shortcut1");
			for (var i = 0, len = items.length; i < len; i++) {
                this.setXY(items[i]); 
			}
		};
		desktopThis = this;
		Ext.EventManager.onWindowResize(this.handleUpdate, this, {delay:50});
        var _this = this;
        setTimeout(function(){
	        Ext.EventManager.onDocumentReady(_this.handleUpdate,_this);
	        _this = null;
	        if($('#desktop_loading .loading',parent.document).length>0){
	        	$('#taskbar-main-panel .x-toolbar-default,#taskbar-main-panel  .x-toolbar-default').css('background-color','transparent');
	        	$('div[name="shortcut-home"]').addClass('neverHidden').removeClass('ux-desktop-shortcut1').click();
	        	$('#desktop_loading .loading',parent.document).animate({width:'100%'},10,function(){
	        		$('#desktop_loading .font',parent.document).html('100%');
	        		$('.loadingFront',parent.document).html('初始化完成');
	        		setTimeout(function(){
						$('#desktopPanel>.x-toolbar-default').css('background-color','#008AE7');
	        			$('#desktop_loading',parent.document).remove();
	        			/*回收内存*/
	        			getStartMenu = null;
						iconModule = null;
						userName = null;
						system = null;
						//MessageError('消息提示','您有 X 项待办工作，请及时办理！');
	        		},50);
				});
	        }
        },50);
	} 

});
