layui.use('element', function() {
    var element = layui.element
        , $ = layui.jquery
        , form = layui.form
        , layer = layui.layer;

    // 表单提交，判断用户登录
    form.on('submit(sub)',function (data) {
        layer.load();
        $.ajax("/user/userCheck", {
            method:'post',
            data:data.field,
            success : function(d) {
                if (d.success){
                    layer.msg("登录成功", {
                        icon : 6,
                        time : 2000
                    }, function() {
                        window.location.href = "/";
                    })
                }else{
                    layer.msg("用户名或密码错误");
                    layer.closeAll('loading');
                }
            }
        });
        return false;
    })



})
