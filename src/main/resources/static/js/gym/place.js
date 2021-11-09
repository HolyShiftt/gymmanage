layui.use('table', function(){
    var table = layui.table,
        $ = layui.jquery;

    // 场地类型表格
    var kindTbale = table.render({
        elem: '#kindTable'
        ,url:'/gym/getAllPlaceKind'
        ,cols: [[
            {field:'name', title: '类型'}
            ,{field:'managerName', title: '管理人员'}

        ]]
    });

    // 场地详情表格
    var placeTable = table.render({
        elem: '#placeTable'
        ,url:'/gym/getAllPlace'
        ,cols: [[
            {field:'name', title: '场馆名'}
            ,{field:'kindName', title: '类型'}
            ,{field:'size', title: '大小'}
            ,{field:'managerName', title: '管理人员'}
            ,{field:'price', title: '消费说明'}
            ,{field:'state', title: '场馆状态',templet: function (data) {
                switch (data.state) {
                    case 0: return "空闲";
                    case 1: return "使用中";
                    case 2: return "已预约";
                    case 3: return "暂停使用";
                }
            }}
        ]]
        ,page:true
    });

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

    // 添加场馆
    $("#addBtn").click(function () {
        layer.open({
            title : '添加',
            type : 2,
            area : [ '600px', '530px' ],
            content : '/gym/placeAdd'
        })
    })
});
