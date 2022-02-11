layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;

    clientTable = table.render({
        elem: '#clientTable'
        , url: '/client/clientList'
        , page: true
        , cols: [[
            {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'user_name', title: '姓名'}
            , {field: 'tel', title: '手机号'}
            , {field: 'sex', title: '性别'}
            , {field: 'age', title: '年龄'}
            , {field: 'birth', title: '生日'}
            , {field: 'address', title: '住址'}
            , {field:'is_vip', title: '是否会员',templet: function (data) {
                    switch (data.is_vip) {
                        case 1: return "是";
                        case 0: return "否";
                    }
                }}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]

    });

    // 添加按钮
    $("#addClient").click(function () {
        layer.open({
            title: '添加',
            type: 2,
            area: ['30%', '70%'],
            content: '/client/clientAddPage'
        })
    })

    table.on('tool(clientTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del'){
            // 删除按钮
            layer.confirm('确定要删除这个客户吗', function (index) {
                $.ajax({
                    type: "POST",
                    data: {id: data.id},
                    url: "/client/clientDel",
                    success: function (d) {
                        if (d.success) {
                            layer.msg("删除成功", {time: 1000}, function () {
                                layer.closeAll();//关闭弹窗
                                clientTable.reload()//保存成功刷新
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
                content: '/client/clientUpdatePage?id='+data.id
            })
        }
    })

});
