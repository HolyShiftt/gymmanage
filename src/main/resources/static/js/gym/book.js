layui.use('table', function () {
    var table = layui.table,
        $ = layui.jquery;


    // 用户信息表格
    bookTable = table.render({
        elem: '#bookTable'
        , url: '/book/bookList'
        , page: true
        , cols: [[
            {field: 'id', title: 'ID', fixed: 'left'}
            , {field: 'start_time', title: '开始时间'}
            , {field: 'end_time', title: '结束时间'}
            , {field: 'placeName', title: '场地名称'}
            , {field: 'userName', title: '预约人'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
        ]]

    });

});
