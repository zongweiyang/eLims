/*
	扩展打印按钮
	2014-4-2
	Aaron
*/
(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
            $.interface('a_print');
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='aprint';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('aprint',{
                label:'打印',
                icon: this.path + 'print.gif',
                command:b
            });
        }
    });
})();