layui.use(['layer', 'form','laydate'], function () {
    var $ = layui.$
        , layer = layui.layer
        , form = layui.form
        , laydate = layui.laydate;

    laydate.render({
        elem: '#roleTime'
        , value: new Date(new Date())
        ,change: function(value, date, endDate) {
            $("#roleTime").val(value);
            if ($(".layui-laydate").length) {
                $(".layui-laydate").remove();
            }
        }
    });

    // 职位下拉列表
    $.ajax({
        url:'/role/roleList',
        async : false,
        success : function(d) {
            $.each(d, function (index, item) {
                $('#role').append(new Option(item.name, item.id));
            });
        }
    })

    // 获取该行数据
    $.ajax('/user/getOne', {
        async : false,
        data : {
            id : $('#id').val()
        },
        success : function(d) {
            form.val("myForm", d);
        }
    });

    // 渲染表单
    form.render();

    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/user/userUpdate', data.field, function(d) {
            if (d.success) {
                layer.msg(d.msg, {
                    icon : 6,
                    time : 2000
                }, function() {
                    parent.layer.close(parent.layer.getFrameIndex(window.name))
                    parent.userTable.reload();
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
