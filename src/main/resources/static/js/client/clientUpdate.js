layui.use(['layer', 'form','laydate'], function () {
    var $ = layui.$
        , layer = layui.layer
        , form = layui.form
        , laydate = layui.laydate;

    // 获取该行数据
    $.ajax('/client/getOne', {
        async : false,
        data : {
            id : $('#id').val()
        },
        success : function(d) {
            form.val("clientUpdate", d);
        }
    });

    // 渲染表单
    form.render();

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/client/clientUpdate', data.field, function(d) {
            if (d.success) {
                layer.msg("修改成功", {
                    icon : 6,
                    time : 2000
                }, function() {
                    parent.layer.close(parent.layer.getFrameIndex(window.name))
                    parent.clientTable.reload();
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
