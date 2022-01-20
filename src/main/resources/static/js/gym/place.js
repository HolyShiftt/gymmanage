var placeTbale;
layui.use('table', function(){
    var table = layui.table,
        form = layui.form ,
        $ = layui.jquery;

    // 场地类型表格
    var kindTbale = table.render({
        elem: '#kindTable'
        ,url:'/place/getAllPlaceKind'
        ,cols: [[
            {field:'name', title: '类型'}
            ,{field:'managerName', title: '管理人员'}

        ]]
    });

    // 场地详情表格
    var placeTable = table.render({
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
                console.log(item)
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
    })

    // 添加场馆
    $("#addPlaceBtn").click(function () {
        layer.open({
            title : '添加',
            type : 2,
            area : [ '600px', '530px' ],
            content : '/place/placeAdd'
        })
    })

    $(".close").click(function (){
        layer.closeAll();
    })
});
