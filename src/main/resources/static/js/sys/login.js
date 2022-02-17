layui.use('element', function() {
    var element = layui.element
        , $ = layui.jquery
        , form = layui.form
        , layer = layui.layer
        ,element = layui.element;
    sessionStorage.setItem("role","admin")

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

    $(".role").on("click",function (data) {
        if(data.target.innerHTML == '管理员登录'){
            sessionStorage.setItem("role","admin")
            $('#role').val('admin');
            $("#sub").html('管理员登录')
        }else{
            sessionStorage.setItem("role","user")
            $('#role').val('user');
            $("#sub").html('用户登录')
        }
    })

})
