layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;


    // 账单信息表格
    billTable = table.render({
        elem: '#billTable'
        , url: '/bill/billList?pay=1'
        , where: {pay:0}
        , page: true
        , cols: [[
            {field: 'creat_time', title: '创建时间'}
            , {field: 'username', title: '客户名称'}
            , {field: 'place_name', title: '场地名称'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]
    });

    table.on('tool(billTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'bill'){
            $("#billInfo").empty();
            $.ajax({
                url:'/bill/getOneById',
                data:{id:data.id},
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
        }
    })
});
