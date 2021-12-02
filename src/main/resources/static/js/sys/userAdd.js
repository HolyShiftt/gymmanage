layui.use(['layer', 'form'], function () {
    var $ = layui.$
    var layer = layui.layer
    var form = layui.form

    // 渲染表单
    form.render();

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/user/userAdd', data.field, function(d) {
            if (d.code == 0) {
                layer.msg("添加成功", {
                    icon : 6,
                    time : 2000
                }, function() {
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                })
            } else {
                layer.alert(d.msg || d.message)
                layer.closeAll('loading');
            }
        });
        return false;
    })

    $('#close').click(function() {
        parent.layer.close(parent.layer.getFrameIndex(window.name))
    })

})
