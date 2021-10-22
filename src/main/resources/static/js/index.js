
layui.use(['element', 'layer', 'util','tree'], function(){
    var element = layui.element
        ,layer = layui.layer
        ,util = layui.util
        ,$ = layui.jquery
        ,tree = layui.tree;

    //头部事件
    util.event('lay-header-event', {
        //左侧菜单事件
        menuLeft: function(othis){
            layer.msg('展开左侧菜单的操作', {icon: 0});
        }
        ,menuRight: function(){
            layer.open({
                type: 1
                ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                ,area: ['260px', '100%']
                ,offset: 'rt' //右上角
                ,anim: 5
                ,shadeClose: true
            });
        }
    });

    $.ajax({
        method:"get"
        ,url:"/menu/menuTree"
        ,success:function (data) {
            // 菜单树
            tree.render({
                elem: '#tree'  //绑定元素
                ,accordion: true //开启手风琴
                ,isJump: true//开启跳转
                ,id:"tree"
                ,data: data.data
                ,click:function (obj) {
                    var data = obj.data;
                    // 判断该项是否有内容
                    if(data.url){
                        // 判断点击的该项是否已经存在
                        if (!$("[lay-id='"+data.id+"']").length){
                            // 添加内容
                            var height = $(document).height() - 200;
                            element.tabAdd('tabs', {
                                title: data.title
                                ,content: "<iframe src="+data.url+" frameborder='0' width='100%' height="+height+"></iframe>"
                                ,id: data.id
                            });
                            //切换到指定Tab项
                            element.tabChange('tabs', data.id);
                        }else{
                            element.tabChange('tabs', data.id);
                        }
                    }
                }
            });

        }
    });

});
