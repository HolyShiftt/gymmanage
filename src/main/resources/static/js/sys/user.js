layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;


    // 用户信息表格
    userTable = table.render({
        elem: '#userTable'
        , url: '/user/userList'
        , page: true
        , cols: [[
            {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'username', title: '用户名'}
            , {field: 'name', title: '姓名'}
            , {field: 'tel', title: '电话号码'}
            , {field: 'idNumber', title: '身份证号码'}
            , {field: 'role', title: '职位', sort: true}
            , {field: 'roleTime', title: '入职时间'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]

    });

    // 添加按钮
    $("#addUser").click(function () {
        layer.open({
            title: '添加',
            type: 2,
            area: ['30%', '70%'],
            content: '/user/userAddPage'
        })
    })

    table.on('tool(userTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del'){
            // 删除按钮
            layer.confirm('确定要删除这个用户吗', function (index) {
                $.ajax({
                    type: "POST",
                    data: {id: data.id},
                    url: "/user/userDelete",
                    success: function (d) {
                        if (d.success) {
                            layer.msg("操作成功", {time: 1000}, function () {
                                layer.closeAll();//关闭弹窗
                                userTable.reload()//保存成功刷新
                            });
                        } else {
                            layer.alert(d.msg)
                        }
                    }
                });
                layer.close(index);
            })
        }else if (obj.event === 'edit'){
            //编辑按钮
            layer.open({
                title: '编辑',
                type: 2,
                area: ['30%', '70%'],
                content: '/user/userUpdatePage?id='+data.id
            })
        }
    })

});
