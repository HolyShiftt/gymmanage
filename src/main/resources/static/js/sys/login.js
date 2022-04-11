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
        $("#name").val('')
        $("#phone").val('')
        $("#pwd1").val('')
        $("#pwd2").val('')
        layer.open({
            title: '注册',
            type: 1,
            area: ['45%', '45%'],
            content: $("#signUp")
        })
    })
    // 注册提交
    form.on('submit(formDemo)',function (data) {
        if($("#pwd1").val() != $("#pwd2").val()){
            layer.msg("两次密码输入不一致");
        }else{
            $.ajax("/user/signIn", {
                method:'post',
                data:data.field,
                success : function(d) {
                    if (d.success){
                        layer.msg("注册成功", {
                            icon : 6,
                            time : 2000
                        }, function() {
                            layer.closeAll();
                        })
                    }else{
                        layer.msg("该手机号已被注册");
                    }
                }
            });
        }
        return false;
    })

})
