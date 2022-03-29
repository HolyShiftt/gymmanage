layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;


    // 用户信息表格
    coachTable = table.render({
        elem: '#coachTable'
        , url: '/coach/coachList'
        , page: true
        , cols: [[
            {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'name', title: '姓名'}
            , {field: 'sex', title: '性别'}
            , {field: 'age', title: '年龄'}
            , {field: 'course', title: '课程'}
            , {field: 'price', title: '价格'}
            , {field:'state', title: '状态',templet: function (data) {
                    switch (data.state) {
                        case 0: return "空闲";
                        case 1: return "上课中";
                        case 2: return "已预约";
                    }
                }}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]

    });

    // 添加按钮
    $("#addCoach").click(function () {
        layer.open({
            title: '添加',
            type: 2,
            area: ['30%', '70%'],
            content: '/coach/coachAddPage'
        })
    })

    table.on('tool(coachTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del'){
            // 删除按钮
            layer.confirm('确定要删除这个教练吗', function (index) {
                $.ajax({
                    type: "POST",
                    data: {id: data.id},
                    url: "/coach/coachDel",
                    success: function (d) {
                        if (d.success) {
                            layer.msg("删除成功", {time: 1000}, function () {
                                layer.closeAll();//关闭弹窗
                                coachTable.reload()//保存成功刷新
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
                content: '/coach/coachUpdatePage?id='+data.id
            })
        }else if (obj.event === 'hire'){
            //编辑按钮
            layer.open({
                title: '聘请',
                type: 2,
                area: ['30%', '70%'],

            })
        }
    })

});
