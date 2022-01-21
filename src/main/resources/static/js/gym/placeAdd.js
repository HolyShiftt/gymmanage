layui.use([ 'layer', 'form' ], function() {
    var $ = layui.$
    var layer = layui.layer
    var form = layui.form

    // 场地类型下拉列表
    $.ajax({
        url:'/place/kindList',
        async : false,
        success : function(d) {
            $.each(d, function (index, item) {
                $('#kind').append(new Option(item.name, item.id));
            });
        }
    })

    form.render();

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/place/placeAdd', data.field, function(d) {
            if (d.success) {
                layer.msg("添加成功", {
                    icon : 6,
                    time : 2000
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.placeTable.reload();
                })
            } else {
                layer.alert(d.msg)
                layer.closeAll('loading');
            }
        });
        return false;// 阻止表单提交，使用ajax提交
    })


    $('#close').click(function() {
        var index = parent.layer.getFrameIndex(window.name)
        parent.layer.close(index)
    })

})
