layui.use([ 'layer', 'form','xmSelect' ], function() {
    var $ = layui.$
    var layer = layui.layer
    var form = layui.form

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/busClient/add', data.field, function(d) {
            if (d.code == 0) {
                layer.msg("添加成功", {
                    icon : 6,
                    time : 2000
                }, function() {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.tableIns.reload({
                        where: {
                            name: $("#name").val()
                        }
                    });
                })
            } else {
                layer.alert(d.msg || d.message)
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
