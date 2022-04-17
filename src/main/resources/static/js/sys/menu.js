layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;

    // 父菜单表格
    var pMenuTable = table.render({
        elem: '#pMenuTable'
        ,url:'/menu/menuList?pid=0'
        ,cols: [[
            {field:'title', title: '标题'}
        ]]
    });

    // 子菜单表格
    table.on('row(pMenuTable)', function(obj){
        table.render({
            elem: '#cMenuTable'
            ,url: '/menu/menuList'
            ,where:{pid:obj.data.id}
            ,cols: [[
                {field:'title', title: '标题'}
                ,{field:'url', title: '地址'}

            ]]
        });
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
