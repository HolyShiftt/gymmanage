layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;


    // 预约信息表格
    bookTable = table.render({
        elem: '#bookTable'
        , url: '/book/bookList'
        , page: true
        , where:{isCancel:0}
        , cols: [[
            {field: 'startTime', title: '开始时间'}
            , {field: 'endTime', title: '结束时间'}
            , {field: 'placeName', title: '场地名称'}
            , {field: 'name', title: '预约人'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo',align:"center"}
        ]]
    });

    $("#showCanceled").click(function () {
        bookTable.reload({
            where:{
                isCancel:1
            }
            , cols: [[
                {field: 'startTime', title: '开始时间'}
                , {field: 'endTime', title: '结束时间'}
                , {field: 'placeName', title: '场地名称'}
                , {field: 'name', title: '预约人'}
            ]]
        })
    })

    $("#showApply").click(function () {
        bookTable.reload({
            where:{
                isCancel:0
            }
            , cols: [[
                {field: 'startTime', title: '开始时间'}
                , {field: 'endTime', title: '结束时间'}
                , {field: 'placeName', title: '场地名称'}
                , {field: 'name', title: '预约人'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo',align:"center"}
            ]]
        })
    })

    table.on('tool(bookTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'cancel'){
            layer.confirm('你确定要取消预约吗?', function(index){
                $.post('/book/cancelApply', {id:data.id,placeId:data.placeId}, function(d) {
                    if (d.success) {
                        layer.msg(d.msg, {
                            icon : 6,
                            time : 2000
                        }, function() {
                            bookTable.reload({
                                where:{
                                    isCancel:0
                                }
                                , cols: [[
                                    {field: 'startTime', title: '开始时间'}
                                    , {field: 'endTime', title: '结束时间'}
                                    , {field: 'placeName', title: '场地名称'}
                                    , {field: 'name', title: '预约人'}
                                    , {fixed: 'right', title: '操作', toolbar: '#barDemo',align:"center"}
                                ]]
                            })
                        })
                    } else {
                        layer.alert(d.msg)
                        layer.closeAll('loading');
                    }
                });
                layer.closeAll();
                bookTable.reload()
            });
        }
    })
});
