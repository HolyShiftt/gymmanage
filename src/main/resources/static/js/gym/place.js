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
            ,{fixed: 'right', title: '操作', toolbar: '#barDemo', width:300}
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
        var startD,endD,startH,endH;
        data.field.startTime = layui.util.toDateString(data.field.startTime, 'yyyy-MM-dd HH:mm')
        data.field.endTime = layui.util.toDateString(data.field.endTime, 'yyyy-MM-dd HH:mm')
        startD = layui.util.toDateString(data.field.startTime, 'yyyy-MM-dd')
        endD = layui.util.toDateString(data.field.endTime, 'yyyy-MM-dd')
        startH = layui.util.toDateString(data.field.startTime, 'HH:mm')
        endH = layui.util.toDateString(data.field.endTime, 'HH:mm')
        endD = endD.split("-");
        startD = startD.split("-");
        endH = endH.split(":");
        startH = startH.split(":");
        if (endD[2] - startD[2] != 0){
            layer.alert("预约范围只能在当天")
        } else if(endH[0] - startH[0] < 0){
            layer.alert("预约开始时间不能早于结束时间")
        } else if(endH[0] - startH[0] == 2 && endH[1] - startH[1] < 0) {
            layer.alert("预约最少两小时")
        } else if(endH[0] - startH[0] == 0 && endH[1] - startH[1] < 0){
            layer.alert("预约开始时间不能早于结束时间")
        } else if(endH[0] - startH[0] < 2){
            layer.alert("预约最少两小时")
        } else{
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
        }

        return false;
    });

    // 添加场馆
    $("#addPlaceBtn").click(function () {
        layer.open({
            title : '添加',
            type : 2,
            area : [ '500px', '400px' ],
            content : '/place/placeAddPage'
        })
    })

    table.on('tool(placeTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'apply'){
            apply(data.id,data.state);
        }else if (obj.event === 'open'){
            if (data.state == 1){
                layer.alert("无需重复开场")
            }else if(data.state == 3){
                layer.alert("该场地已暂停使用")
            }else{
                $.ajax({
                    url:'/place/changeState',
                    data:{id:data.id,state:1,pay:0},
                    success : function(d) {
                        layer.alert("开场成功")
                        placeTable.reload()
                    }
                })
            }
        }else if (obj.event === 'edit'){
            layer.open({
                title : '编辑',
                type : 2,
                area : [ '500px', '400px' ],
                content : '/place/placeUpdatePage?id='+data.id
            })
        }else if (obj.event === 'bill'){
            if (data.state != 1){
                layer.alert("该场地未在使用中")
            }else{
                $("#billInfo").empty();
                $.ajax({
                    url:'/bill/getOneByPlaceId',
                    data:{placeId:data.id},
                    success : function(d) {
                        var total = 0
                        $("#billInfo").append(`<div class="layui-form-item"><label style="margin-left: 30px">创建时间：`+d.creat_time+`</label></div>
                    <div class="layui-form-item"><label class="layui-col-md2" style="margin-left: 50px">名称</label><label class="layui-col-md2">单价</label>
                    <label class="layui-col-md2">数量</label><label class="layui-col-md2">共计</label></div>
                    <div class="layui-form-item"><label class="layui-col-md2" style="margin-left: 50px">`+d.place_name+`</label>
                    <label class="layui-col-md2">`+d.place_price+`</label><label class="layui-col-md2">`+d.place_time+`</label>
                    <label class="layui-col-md2">`+d.place_price * d.place_time+`</label></div>`)
                        total+=d.place_price * d.place_time;
                        if (d.coachList){
                            for (let i = 0; i < d.coachList.length; i++) {
                                $("#billInfo").append(`<div class="layui-form-item"><label class="layui-col-md2" style="margin-left: 50px">`+d.coachList[i].name+`</label>
                    <label class="layui-col-md2">`+d.coachList[i].price+`</label><label class="layui-col-md2">`+d.coachList[i].time+`</label>
                    <label class="layui-col-md2">`+d.coachList[i].price *d.coachList[i].time+`</label></div>`)
                                total+=d.coachList[i].price *d.coachList[i].time;
                            }
                        }
                        if (d.objectList){
                            for (let i = 0; i < d.objectList.length; i++) {
                                $("#billInfo").append(`<div class="layui-form-item"><label class="layui-col-md2" style="margin-left: 50px">`+d.objectList[i].name+`</label>
                    <label class="layui-col-md2">`+d.objectList[i].price+`</label><label class="layui-col-md2">`+d.objectList[i].billNum+`</label>
                    <label class="layui-col-md2">`+d.objectList[i].price *d.objectList[i].billNum+`</label></div>`)
                                total+=d.objectList[i].price *d.objectList[i].billNum;
                            }
                        }

                        $("#billInfo").append(`<div class="layui-form-item"><label class="layui-col-md5" style="float: right">总价：`+total+`</label>`);
                        layer.open({
                            title : '账单信息',
                            type : 1,
                            area : [ '40%', '80%' ],
                            content : $("#bill")
                        })

                    }

                })
                $("#pay").click(function () {
                    $.ajax({
                        url:'/place/changeState',
                        data:{id:data.id,state:0,pay:1},
                        success : function(d) {
                            placeTable.reload()
                        }
                    })
                })
            }
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
            $("#placeId").val(id);
            $("#cancel").css("display","inline")
            $.ajax({
                url:'/book/getBookByPlaceId',
                data:{id:id},
                success : function(d) {
                    $("#id").val(d.id);
                    $("#startTime").val(d.startTime.replace(" ","T"));
                    $("#endTime").val(d.endTime.replace(" ","T"));
                    $("#bookName").val(d.name);
                    form.render();
                    layer.open({
                        title : '预约信息',
                        type : 1,
                        area : [ '400px', '300px' ],
                        content : $("#apply")
                    })
                }
            })

        }else if (state == 3){
            layer.msg("暂停使用，无法预约", {
                icon : 2,
                time : 2000
            })
        }else{
            $("#placeId").val(id);
            $("#id").val("");
            $("#startTime").val("");
            $("#endTime").val("");
            $("#bookName").val("");
            $("#cancel").css("display","none")
            layer.open({
                title : '预约',
                type : 1,
                area : [ '400px', '300px' ],
                content : $("#apply")
            })
        }
    }

    $("#cancel").click(function () {
        layer.confirm('你确定要取消预约吗?', function(index){
            $.post('/book/cancelApply', {id:$("#id").val(),placeId:$("#placeId").val()}, function(d) {
                if (d.success) {
                    layer.msg(d.msg, {
                        icon : 6,
                        time : 2000
                    }, function() {

                    })
                } else {
                    layer.alert(d.msg)
                    layer.closeAll('loading');
                }
            });
            layer.closeAll();
            placeTable.reload()
        });
        return false;
    })
});
