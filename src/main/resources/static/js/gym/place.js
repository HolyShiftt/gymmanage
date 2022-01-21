var placeTbale;
layui.use('table', function(){
    var table = layui.table,
        form = layui.form ,
        $ = layui.jquery;

    // 场地类型表格
    var kindTable = table.render({
        elem: '#kindTable'
        ,url:'/place/getAllPlaceKind'
        ,cols: [[
            {field:'name', title: '类型'}
            ,{field:'managerName', title: '管理人员'}

        ]]
    });

    // 场地详情表格
    placeTable = table.render({
        elem: '#placeTable'
        ,url:'/place/getAllPlace'
        ,cols: [[
            {field:'name', title: '场馆名'}
            ,{field:'kindName', title: '类型'}
            ,{field:'size', title: '大小'}
            ,{field:'managerName', title: '管理人员'}
            ,{field:'price', title: '消费说明',templet: function (data) {return data.price+"元/小时"}}
            ,{field:'state', title: '场馆状态',templet: function (data) {
                switch (data.state) {
                    case 0: return "空闲";
                    case 1: return "使用中";
                    case 2: return "已预约";
                    case 3: return "暂停使用";
                }
            }}
            ,{fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]
        ,page:true

    });

    // 场地类型管理员下拉列表
    $.ajax({
        url:'/user/freeUser',
        async : false,
        success : function(d) {
            $.each(d, function (index, item) {
                $('#kindManager').append(new Option(item.name, item.id));
            });
        }
    })

    // 渲染表单
    form.render();

    // 场馆类型点击事件
    table.on('row(kindTable)', function(obj){
        placeTable.reload({
            where:{kindId:obj.data.id}
        });
    });

    // 展示全部表格信息
    $("#showBtn").click(function () {
        placeTable.reload({
            where:{kindId:''}
        });
    })

    // 添加场馆类型
    $("#addKindBtn").click(function () {
        layer.open({
            title : '添加',
            type : 1,
            area : [ '400px', '300px' ],
            content : $("#addKind")
        })
    });
    // 表单的提交事件
    form.on('submit(formDemo)', function(data) {
        layer.load();
        $.post('/place/kindAdd', data.field, function(d) {
            if (d.success) {
                layer.msg(d.msg, {
                    icon : 6,
                    time : 2000
                }, function() {
                    layer.closeAll();
                    kindTable.reload();
                })
            } else {
                layer.alert(d.msg)
                layer.closeAll('loading');
            }
        });
        return false;
    });
    // 预约提交
    form.on('submit(sub)', function(data) {
        data.field.startTime = layui.util.toDateString(data.field.startTime, 'yyyy-MM-dd HH:mm')
        data.field.endTime = layui.util.toDateString(data.field.endTime, 'yyyy-MM-dd HH:mm')
        layer.load();
        $.post('/book/apply', data.field, function(d) {
            if (d.success) {
                layer.msg(d.msg, {
                    icon : 6,
                    time : 2000
                }, function() {
                    layer.closeAll();
                    placeTable.reload()
                })
            } else {
                layer.alert(d.msg)
                layer.closeAll('loading');
            }
        });
        return false;
    });

    // 添加场馆
    $("#addPlaceBtn").click(function () {
        layer.open({
            title : '添加',
            type : 2,
            area : [ '600px', '530px' ],
            content : '/place/placeAddPage'
        })
    })

    table.on('tool(placeTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'apply'){
            apply(data.id,data.state);
        }else if (obj.event === 'edit'){
            console.log('edit')
        }
    })

    $(".close").click(function (){
        layer.closeAll();
    })

    function apply(id,state) {
        if (state == 1){
            layer.msg("正在使用中，无法预约", {
                icon : 2,
                time : 2000
            })
        }else if(state == 2){
            layer.msg("已预约，无需重复预约", {
                icon : 1,
                time : 2000
            })
        }else if (state == 3){
            layer.msg("暂停使用，无法预约", {
                icon : 2,
                time : 2000
            })
        }else{
            $("#placeId").val(id);
            layer.open({
                title : '预约',
                type : 1,
                area : [ '400px', '300px' ],
                content : $("#apply")
            })
        }
    }
});
