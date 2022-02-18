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

        ]]
    });

    // 场地详情表格
    placeTable = table.render({
        elem: '#placeTable'
        ,url:'/place/getAllPlaceBook'
        ,cols: [[
            {field:'name', title: '场馆名'}
            ,{field:'kindName', title: '类型'}
            ,{field:'size', title: '大小'}
            ,{field:'price', title: '消费说明',templet: function (data) {return data.price+"元/小时"}}
            ,{field:'nextBook', title: '可预约时间',templet:function (data) {
                if (data.state == 0 && data.nextBook==null){
                    return "现在"
                }else if(data.state == 3){
                    return "不可预约"
                }else{
                    data.nextBook = layui.util.toDateString(data.nextBook, 'yyyy-MM-dd HH:mm')
                    return data.nextBook
                    }
                }}
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

    // 预约提交
    form.on('submit(sub)', function(data) {
        var startD,endD,startH,endH,bookH,bookD;
        data.field.startTime = layui.util.toDateString(data.field.startTime, 'yyyy-MM-dd HH:mm')
        data.field.endTime = layui.util.toDateString(data.field.endTime, 'yyyy-MM-dd HH:mm')
        startD = layui.util.toDateString(data.field.startTime, 'yyyy-MM-dd')
        endD = layui.util.toDateString(data.field.endTime, 'yyyy-MM-dd')
        bookD = layui.util.toDateString(data.nextBook, 'yyyy-MM-dd')
        startH = layui.util.toDateString(data.field.startTime, 'HH:mm')
        endH = layui.util.toDateString(data.field.endTime, 'HH:mm')
        bookH = layui.util.toDateString(data.nextBook, 'HH:mm')
        endD = endD.split("-");
        startD = startD.split("-");
        bookD = bookD.split("-");
        endH = endH.split(":");
        startH = startH.split(":");
        bookH = bookH.split(":");
        if (endD[2] != startD[2]){
            layer.alert("预约范围只能在当天")
        } else if(endH[0] < startH[0]){
            layer.alert("预约开始时间不能早于结束时间")
        } else if(endH[0] - startH[0] == 2 && endH[1] < startH[1]) {
            layer.alert("预约最少两小时")
        } else if(endH[0] == startH[0] && endH[1] < startH[1] ){
            layer.alert("预约开始时间不能早于结束时间")
        } else if(endH[0] - startH[0] < 2){
            layer.alert("预约最少两小时")
        } else if ((bookD[1] <= startD[1]&&bookD[2] > startD[2]) || (bookD[0] == startD[0] && bookD[1] > startD[1])
        || bookH[0]>startH[0] || (bookH[0]<startH[0] && bookH[1]>startH[1])){
            layer.alert("预约时间超出可预约范围")
        }else{
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


    table.on('tool(placeTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'apply'){

            apply(data.id,data.state,data.nextBook);
        }
    })

    $(".close").click(function (){
        layer.closeAll();
    })


    function apply(id,state,time) {
        if (state == 3){
            layer.msg("暂停使用，无法预约", {
                icon : 2,
                time : 2000
            })
        }else if (state == 2 || state == 1){
            console.log(time)
            $("#placeId").val(id);
            $("#startTime").val(time.replace(" ","T"))
            // $("#endTime").val(d.endTime.replace(" ","T"));
            $("#bookName").val(sessionStorage.getItem("username"));
            form.render();
            layer.open({
                title : '预约信息',
                type : 1,
                area : [ '400px', '300px' ],
                content : $("#apply")
            })
        }else{
            $("#placeId").val(id);
            $("#startTime").val("")
            $("#endTime").val("");
            $("#bookName").val(sessionStorage.getItem("username"));
            form.render();
            layer.open({
                title : '预约信息',
                type : 1,
                area : [ '400px', '300px' ],
                content : $("#apply")
            })
        }
    }
});
