/*
	扩展保存按钮
	2014-4-2
	Aaron
*/
(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
			$.interface('a_save');
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='submit';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('submit',{
                label:'保存',
                icon: this.path + 'save.gif',
                command:b
            });
        }
    });
})();