layui.use('table', function () {
    var table = layui.table,
        form = layui.form,
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

    // 场馆下拉框
    $.ajax({
        url:'/place/getPlaceByState?state=1',
        async : false,
        success : function(d) {
            $.each(d, function (index, item) {
                $('#place').append(new Option(item.name, item.id));
            });
        }
    })
    form.render();
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
            $("#id").val(data.id);
            //编辑按钮
            layer.open({
                title: '聘请',
                type: 1,
                area: ['30%', '30%'],
                content: $("#hire")
            })
        }
    })

    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/coach/buyCoach', data.field, function(d) {
            if (d.success) {
                layer.msg(d.msg, {
                    icon : 6,
                    time : 2000
                }, function() {
                    layer.closeAll();
                    coachTable.reload()
                })
            } else {
                layer.alert(d.msg)
                layer.closeAll('loading');
            }
        });
        return false;
    });

    $('.close').click(function() {
        layer.closeAll()
    })

});
