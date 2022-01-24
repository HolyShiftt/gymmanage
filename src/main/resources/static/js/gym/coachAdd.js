layui.use([ 'layer', 'form' ], function() {
    var $ = layui.$
    var layer = layui.layer
    var form = layui.form

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/coach/coachAdd', data.field, function(d) {
            if (d.success) {
                layer.msg("添加成功", {
                    icon : 6,
                    time : 2000
                }, function() {
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                    parent.coachTable.reload();
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
