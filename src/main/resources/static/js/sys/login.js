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
            $('#yhm').html('用户名');
            $('#role').val('admin');
            $("#sub").html('管理员登录')
        }else{
            sessionStorage.setItem("role","user")
            $('#yhm').html('手机号');
            $('#role').val('user');
            $("#sub").html('客户登录')
        }
    })
    $("#signUpBtn").click(function () {
        layer.open({
            title: '注册',
            type: 1,
            area: ['40%', '40%'],
            content: $("#signUp")
        })
    })

})
