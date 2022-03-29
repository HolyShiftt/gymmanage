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
            , {field: 'total', title: '总价'}
        ]]
    });

    table.on('tool(billTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'cancel'){
            layer.confirm('你确定要取消预约吗?', function(index){
                $.post('/bill/cancelApply', {id:data.id,placeId:data.placeId}, function(d) {
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
                billTable.reload()
            });
        }
    })
});
