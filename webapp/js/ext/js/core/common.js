/*
 * 公用方法
 * 包含：增，删，改，提示消息，错误信息判断
 * 2013-6-14
 * Aaron
 */
function rightMessage(title, format){
	Ext.example1.msg(title, format);
}
//失败弹出提示消息
function MessageError(title, format){
	Ext.example.msg(title, format, 'error');
}
//成功弹出提示消息
function MessageSuccess(title, format){
    Ext.example.msg(title, format);
}
/*json对象转string*/
function json2str(o) { 
	var arr = []; 
	var fmt = function(s) { 
		if (typeof s == 'object' && s != null) return json2str(s); 
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i])); 
		return '{' + arr.join(',') + '}';
}

/*
 * 打开窗口 如新增等
 * 参数数量不限传过去都是以数组的形式存在
 */
function openWindow() {
	var para = {};
	for (var i = 1; i < arguments.length; i++) {
		para[i-1] = arguments[i];
	}

	var me = desktopThis, module = me.app.getModule(arguments[0]),
		win = module && module.createWindow(para);

    if (win) {
        me.restoreWindow(win);
        
        if (win.initMax) {
        	win.maximize();
    		win.toFront();
        }
    }
}

/* 最新版：新版本建议使用
 * 修改数据前 查询需修改的数据
 * 参数说明:
 * id:需要修改那一条数据的id值
 * url:调用action地址
 * openWinId:需要打开的win窗口id
 * win:传递所需要遮罩的对象，如果不传递默认对象是body
 */
function moduleUpdataBefore(id, url, openWinId, win) {
	var myMask = new Ext.LoadMask(win, {
        msg: '正在获取需要编辑的内容...'
    });
    myMask.show();
    
    var para = {};
    for (var i = 4; i < arguments.length; i++) {
		para[i-3] = arguments[i];
	}
	
    Ext.Ajax.request({
    	url:url,
    	method : 'post',
    	timeout:common().timeout,
    	success:function(response){
    		var json = Ext.decode(response.responseText)[0];
    		para[0] = json;
    		var me = desktopThis, module = me.app.getModule(openWinId),
				win = module && module.createWindow(para);
			
		    if (win) {
		        me.restoreWindow(win);
		        
		        if (win.initMax) {
		        	win.maximize();
		    		win.toFront();
		        }
		    }
		    myMask.hide();
    	},
    	failure:function(form, action){
			myMask.hide();
			errorInfo(form, action);
		},
		params: {id:id}
    });
}

/*
 * 修改数据前 查询需修改的数据
 * 参数说明:
 * id:需要修改那一条数据的id值
 * url:调用action地址
 * fun:成功后的回调函数(只需传递参数名即可)
 * grid：需要更新的grid对象
 * win:传递所需要遮罩的对象，如果不传递默认对象是body
 * desktop:固定参数前台必须传递
 */
function publicUpdataBefore(id, url, fun, grid, win, desktop, info){
	var myMask = new Ext.LoadMask(win, {
        msg: '正在获取需要编辑的内容...'
    });
    myMask.show();
    Ext.Ajax.request({
    	url:url,
    	method : 'post',
    	timeout:common().timeout,
    	success:function(response){	
    		var json = Ext.decode(response.responseText);
			fun = eval(fun);
			fun(json[0], desktop, grid, win, info);
			myMask.hide();
    	},
    	failure:function(form, action){
			myMask.hide();
			errorInfo(form, action);
		},
		params: {id:id}
    });
}
/*
 * 删除数据
 * 参数说明:
 * rows:获取所选对象的集合
 * url:调用action地址
 * grid：删除的grid对象
 * win:传递所需要遮罩的对象，如果不传递默认对象是body
 * treeStroe:如果存在树情况下传递树的ajax对象
 * treeId:需要更改的树父级别Id
 */
function removeData(rows, url, grid, win, treeStore, treeId){
	var content = 1;
	if(typeof(rows) == 'object'){
		content = rows.length;
	}
	Ext.MessageBox.confirm('提示框', '您确定要删除这 <span style="color:red;font-weight:bold;">'+content+'</span> 条数据吗？',function(btn){ 
		if(btn=='yes'){
			if(win == undefined){
				win = Ext.getBody();
			}
			var myMask = new Ext.LoadMask(win, {
                msg: '正在删除，请稍后...'
            });
            myMask.show();
            
			var ids = []; //要删除的id
			if(typeof(rows) == 'object'){
	            Ext.each(rows, function (item) {
	                ids.push(item.data.id);
	            });
            }
            else{
            	ids.push(rows);
            }
			Ext.Ajax.request({
				url:url,
				method : 'post',
				timeout:common().timeout,
				success:function(response){
					var json = Ext.decode(response.responseText);
					myMask.hide();
					if(json.success) {
						MessageSuccess('消息','您成功的删除了 <span style="color:red;font-weight:bold;">'+content+'</span> 条数据！');
			            grid.getStore().reload({callback:function(){
			            	if(treeStore != undefined){
				            	for(var i=0 in ids){
				            		try{
										treeStore.getNodeById(ids[i]).remove();
									}catch(e){}
								}
								if(grid.getStore().getCount()==0){
									treeStore.getNodeById(treeId).set('expanded', false);
            						treeStore.getNodeById(treeId).set('leaf', true);
								}
				            }
			            }});
					}
					else{
						MessageError('错误', '服务器异常，删除失败！');
					}
				},
				failure:function(form, action){
					myMask.hide();
					errorInfo(form, action);
				},
				params: {id:ids.join(',')}
			});
		}
	});
}
/*
 * 表单保存提交
 * 参数说明
 * form：需要提交的form对象
 * win:form表单所在的window对象
 * grid：保存成功后需要即使查看到的gird对象
 */
function formSubmit(form, win, grid) {
	saveFormData(form, win, grid, '保存成功！');
}
/*
 * 表单修改
 * 参数说明
 * form：需要提交的form对象
 * win:form表单所在的window对象
 * grid：保存成功后需要即使查看到的gird对象
 * 扩展form保存成功后的回调函数callback
 */
function formUpdata(form, win, grid) {
	if(form.isDirty()){
		saveFormData(form, win, grid, '修改成功！');
	}
	else{
		MessageError('警告','您没有任何修改项无法提交！');
	}
}

/*获取列表grid 右边菜单宽度以及 是否显示*/
function getGridMenuWidth(menu) {
	var width = 10;
	
	switch(menu.length){
		case 1:
			width = 26;
			break;
		case 2:
			width = 47;
			break;
		case 3:
			width = 68;
			break;
	}
	var _obj = {
		'width': width,
		'show':(menu.length==0)?true:false
	};
	
	return _obj;
}

function saveFormData(form, win, grid, msg) {
	form.getForm().submit({
		waitMsg: '正在保存...',
		success:function(form1, action){
			if(form.close != 'show'){
				win.close();
			}
			if(form.callback!=undefined){
				form.callback(action.result.id);
			}
			//form.getForm().reset();
			setTimeout(function(){
				MessageSuccess('消息',msg);
				if(grid){
					if(Ext.get(grid.id)){
						grid.getStore().reload();
					}
				}
			},300);
		},
		failure:function(form, action){
			errorInfo(form, action);
		}
	});
}
//错误提示信息
function errorInfo(form, action) {
	if(action.failureType == 'server'){
		//服务器端你自己返回success为false时
		MessageError('友情提示', '服务器返回success不为true');
	}else if(action.failureType == 'connect'){
		//服务器指定的路径链接不上时
		//MessageError('连接错误','指定路径连接错误!');
		MessageError('错误','存在异常!');
	}else if(action.failureType == 'client'){
		//客户端数据验证失败的情况下，例如客户端验证邮件格式不正确的情况下提交表单
		MessageError('提示','数据错误，无法提交！');
	}else{
		//其它类型的错误
		MessageError('警告', '服务器连接超时，请稍后重试！');
	}
}
//公共参数
function common(){
	var data = {
		/*分页参数*/
		pagesize:20,
		/*ajax服务器超时时间*/
		timeout:50000,
		/*input最大输入值*/
		maxlength:64,
		required:'<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
	};
	return data;
}
//正则表达式
function regex(){
	var data = {
		/*数字*/
		num:/^\d*$/,
		email:/^[A-Za-z0-9d]+([-_.][A-Za-z0-9d]+)*@([A-Za-z0-9d]+[-.])+[A-Za-zd]{2,5}$/
	};
	return data;
}
/*公共Store数据 data:需获取的参数格式如[{name:'XXX'}]*/
function publicStore(url,data){
	var store = Ext.create('Ext.data.JsonStore', {
        autoDestroy: true,
        autoSync: true,
        proxy: {
            type: 'ajax',
            url: url,
            reader: {
                type: 'json',
                root: 'data',
                totalProperty: 'total'
            }
        },
        fields: data,
        pageSize: common().pagesize
    });
	return store;
}
/*grid底部显示信息*/
function toolbar(store){
	var toolbar  = Ext.create('Ext.toolbar.Paging', {
        dock: 'bottom',
        pageSize: common().pagesize,
        store: store,
        displayInfo: true,
        displayMsg: "第 {0} - {1} 条记录,  共  {2} 条",
        emptyMsg: "没有记录",
        beforePageText:'第',
        afterPageText:'页 共{0}页',
        firstText:'首页',
        prevText:'上一页',
        nextText:'下一页',
        lastText:'尾页',
        refreshText:'刷新'
    });
	return toolbar;
}
/*ext日志查询*/
function adviceList4Jsp(className,busId,desktop){
	var win = desktop.getWindow('adviceList4Jsp-win');
	if(!win){
        win = desktop.createWindow({
            id: 'adviceList4Jsp-win',
            title: '日志记录 - 查看',
            width:700,
            height:350,
            modal:true,
            layout: 'fit',
            html:'<iframe style="width:100%;height:100%;" frameborder="no" border="0" src="/business/log/listLogAdvice4List.action?logAdviceVo.className='+className+'&logAdviceVo.busId='+busId+'"></iframe>'
        });
    }
    win.show();
}
/*select combox默认选中第一个*/
/*
 *参数说明：
 *store: combox store对象 类型:object
 *id: combox id名 类型：string
 */
function comboDefaultFirst(store, id){
	store.on('load', function(){
	    Ext.getCmp(id).select(store.getAt(0));
	});
	store.load();
}

/*初始化高宽*/
function whInit(v){
	/*可动态传参*/
	var _v = v;
	
	/*默认不传参宽高是屏幕的一半*/
	if(v == undefined) {
		_v = 1/2;
	}
	
	switch(v) {
		/*大*/
		case 'max': _v = 5/6;break;
		/*中*/
		case 'mid': _v = 2/3;break;
		/*小*/
		case 'min': _v = 1/4;break;
	}

	var init = {
		width: Ext.get('desktopPanel-body').getWidth()*_v,
		height: Ext.get('desktopPanel-body').getHeight()*_v
	};

	return init;
}

/*
 *设置模块提示数字
 *name 需要修改的模块名称 类型string
 *i 剩余模块条数 范围0~10
 */
function setModuleNumber(name, i){
	$('div[id^="'+name+'"]').removeClass().addClass('count').addClass('count'+i);
}

function getDesktopIcon() {
	var icon = [
		'code-shortcut',
		'org-shortcut',
		'station-shortcut',
		'user-shortcut',
		'role-shortcut',
		'function-shortcut',
		'appapparatus-shortcut',
		'klgconnects-shortcut',
		'klgitem-shortcut',
		'klgmethod-shortcut',
		'ofctempfile-shortcut',
		'klgcategory-shortcut',
		'klgstandard-shortcut',
		'reareagent-shortcut',
		'sampregister-shortcut',
		'sampreport-shortcut',
		'sampassign-shortcut',
		'sampsend-shortcut',
		'sampaudit-shortcut',
		'sampcustomer-shortcut',
		'kfgl-shortcut',
		'khxx-shortcut',
		'xsjh-shortcut',
		'khlxr-shortcut',
		'rcgl-shortcut',
		'txl-shortcut',
		'ggtz-shortcut'
	];
	return icon;
}

function openPublicWindow(obj) {
	var me = desktopThis, module = me.app.getModule('publicwindow-win'),
		win = module && module.createWindow(obj);

    if (win) {
        me.restoreWindow(win);
        Ext.getBody().mask();
    }
}
function openPublicWindow1(obj) {
	var me = desktopThis, module = me.app.getModule('publicwindow1-win'),
		win = module && module.createWindow(obj);

    if (win) {
        me.restoreWindow(win);
        Ext.getBody().mask();
    }
}